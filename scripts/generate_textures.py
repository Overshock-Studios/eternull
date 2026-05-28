#!/usr/bin/env python3
"""Generate derived Eternull block textures from existing source textures.

This script intentionally uses only the Python standard library so it can run
without Pillow. It supports the simple 8-bit non-interlaced PNGs used by the
current texture set.
"""

from __future__ import annotations

import math
import struct
import zlib
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
TEXTURE_DIR = ROOT / "src/main/resources/assets/eternull/textures/block"


def _paeth(a: int, b: int, c: int) -> int:
    p = a + b - c
    pa = abs(p - a)
    pb = abs(p - b)
    pc = abs(p - c)
    if pa <= pb and pa <= pc:
        return a
    if pb <= pc:
        return b
    return c


def read_png(path: Path) -> tuple[int, int, list[tuple[int, int, int, int]]]:
    data = path.read_bytes()
    if data[:8] != b"\x89PNG\r\n\x1a\n":
        raise ValueError(f"{path} is not a PNG")

    offset = 8
    width = height = color_type = bit_depth = interlace = None
    palette: list[tuple[int, int, int]] = []
    transparency: bytes | None = None
    compressed = bytearray()

    while offset < len(data):
        length = struct.unpack(">I", data[offset : offset + 4])[0]
        chunk_type = data[offset + 4 : offset + 8]
        chunk_data = data[offset + 8 : offset + 8 + length]
        offset += 12 + length

        if chunk_type == b"IHDR":
            width, height, bit_depth, color_type, _compression, _filter, interlace = struct.unpack(">IIBBBBB", chunk_data)
        elif chunk_type == b"PLTE":
            palette = [tuple(chunk_data[i : i + 3]) for i in range(0, len(chunk_data), 3)]
        elif chunk_type == b"tRNS":
            transparency = chunk_data
        elif chunk_type == b"IDAT":
            compressed.extend(chunk_data)
        elif chunk_type == b"IEND":
            break

    if width is None or height is None or color_type is None or bit_depth != 8 or interlace != 0:
        raise ValueError(f"{path} must be an 8-bit non-interlaced PNG")

    channels_by_color = {0: 1, 2: 3, 3: 1, 4: 2, 6: 4}
    channels = channels_by_color.get(color_type)
    if channels is None:
        raise ValueError(f"{path} uses unsupported color type {color_type}")

    raw = zlib.decompress(bytes(compressed))
    stride = width * channels
    rows: list[bytearray] = []
    source = 0
    previous = bytearray(stride)

    for _y in range(height):
        filter_type = raw[source]
        source += 1
        row = bytearray(raw[source : source + stride])
        source += stride

        for i in range(stride):
            left = row[i - channels] if i >= channels else 0
            up = previous[i]
            upper_left = previous[i - channels] if i >= channels else 0
            if filter_type == 1:
                row[i] = (row[i] + left) & 0xFF
            elif filter_type == 2:
                row[i] = (row[i] + up) & 0xFF
            elif filter_type == 3:
                row[i] = (row[i] + ((left + up) // 2)) & 0xFF
            elif filter_type == 4:
                row[i] = (row[i] + _paeth(left, up, upper_left)) & 0xFF
            elif filter_type != 0:
                raise ValueError(f"{path} uses unsupported PNG filter {filter_type}")

        rows.append(row)
        previous = row

    pixels: list[tuple[int, int, int, int]] = []
    for row in rows:
        for x in range(width):
            i = x * channels
            if color_type == 0:
                value = row[i]
                pixels.append((value, value, value, 255))
            elif color_type == 2:
                pixels.append((row[i], row[i + 1], row[i + 2], 255))
            elif color_type == 3:
                idx = row[i]
                r, g, b = palette[idx]
                a = transparency[idx] if transparency is not None and idx < len(transparency) else 255
                pixels.append((r, g, b, a))
            elif color_type == 4:
                value = row[i]
                pixels.append((value, value, value, row[i + 1]))
            elif color_type == 6:
                pixels.append((row[i], row[i + 1], row[i + 2], row[i + 3]))

    return width, height, pixels


def write_png(path: Path, width: int, height: int, pixels: list[tuple[int, int, int, int]]) -> None:
    def chunk(kind: bytes, payload: bytes) -> bytes:
        return struct.pack(">I", len(payload)) + kind + payload + struct.pack(">I", zlib.crc32(kind + payload) & 0xFFFFFFFF)

    raw = bytearray()
    for y in range(height):
        raw.append(0)
        for x in range(width):
            raw.extend(bytes(pixels[y * width + x]))

    payload = (
        b"\x89PNG\r\n\x1a\n"
        + chunk(b"IHDR", struct.pack(">IIBBBBB", width, height, 8, 6, 0, 0, 0))
        + chunk(b"IDAT", zlib.compress(bytes(raw), 9))
        + chunk(b"IEND", b"")
    )
    path.write_bytes(payload)


def blend(base: tuple[int, int, int, int], overlay: tuple[int, int, int], alpha: float) -> tuple[int, int, int, int]:
    r, g, b, a = base
    return (
        int(r * (1.0 - alpha) + overlay[0] * alpha),
        int(g * (1.0 - alpha) + overlay[1] * alpha),
        int(b * (1.0 - alpha) + overlay[2] * alpha),
        a,
    )


def make_null_ward() -> None:
    width, height, pixels = read_png(TEXTURE_DIR / "nullite_ore_block.png")
    out = list(pixels)
    cx = (width - 1) / 2
    cy = (height - 1) / 2

    for y in range(height):
        for x in range(width):
            idx = y * width + x
            dist = abs(x - cx) + abs(y - cy)
            ring = abs(math.hypot(x - cx, y - cy) - min(width, height) * 0.34)
            diagonal = abs((x - cx) - (y - cy)) < 1.2 or abs((x - cx) + (y - cy)) < 1.2
            if ring < 1.4:
                out[idx] = blend(out[idx], (92, 226, 255), 0.72)
            elif diagonal and dist < min(width, height) * 0.75:
                out[idx] = blend(out[idx], (35, 176, 225), 0.55)
            else:
                out[idx] = blend(out[idx], (8, 18, 32), 0.20)

    for y in range(height // 2 - 1, height // 2 + 1):
        for x in range(width // 2 - 1, width // 2 + 1):
            out[y * width + x] = blend(out[y * width + x], (210, 255, 255), 0.9)

    write_png(TEXTURE_DIR / "null_ward.png", width, height, out)


def make_null_heart() -> None:
    width, height, pixels = read_png(TEXTURE_DIR / "null.png")
    out = list(pixels)
    cx = (width - 1) / 2
    cy = (height - 1) / 2

    heart_points: set[tuple[int, int]] = set()
    for y in range(height):
        for x in range(width):
            nx = (x - cx) / (width * 0.42)
            ny = (y - cy + height * 0.05) / (height * 0.42)
            equation = (nx * nx + ny * ny - 0.42) ** 3 - nx * nx * ny**3
            if equation <= 0.0 and y > height * 0.18:
                heart_points.add((x, y))

    for y in range(height):
        for x in range(width):
            idx = y * width + x
            out[idx] = blend(out[idx], (20, 0, 8), 0.30)
            if (x, y) in heart_points:
                edge = min(abs(x - cx), abs(y - cy))
                alpha = 0.82 if edge > 1 else 0.95
                out[idx] = blend(out[idx], (188, 12, 34), alpha)
            if x == width // 2 or y == height // 2 or abs((x - cx) - (y - cy)) < 0.8:
                if (x + y) % 3 == 0:
                    out[idx] = blend(out[idx], (255, 42, 65), 0.65)

    for y in range(max(0, height // 2 - 2), min(height, height // 2 + 2)):
        for x in range(max(0, width // 2 - 2), min(width, width // 2 + 2)):
            out[y * width + x] = blend(out[y * width + x], (255, 96, 96), 0.85)

    write_png(TEXTURE_DIR / "null_heart.png", width, height, out)


def main() -> None:
    make_null_ward()
    make_null_heart()
    print("Generated null_ward.png and null_heart.png")


if __name__ == "__main__":
    main()
