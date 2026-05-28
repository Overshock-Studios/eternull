# Eternull Release Plan

## Maintained Lines

| Branch | Minecraft | Loader | Java | Purpose |
| --- | --- | --- | --- | --- |
| `main` | 26.1.x | Fabric/NeoForge target | 25+ | Forward port and active development |
| `release/1.20.4-neoforge` | 1.20.4 | NeoForge | 17 | Recover `1.2`, ship redesign as `1.3.0` |
| `release/1.20.1-forge` | 1.20.1 | Forge | 17 | Backport `1.3.0` |
| `release/1.19.2-forge` | 1.19.2 | Forge | 17 | Backport `1.3.0` |

## Tags

Use tags for released artifacts:

- `v1.3.0+mc1.20.4-neoforge`
- `v1.3.0+mc1.20.1-forge`
- `v1.3.0+mc1.19.2-forge`
- `v1.3.0+mc26.1-neoforge`
- `v1.3.0+mc26.1-fabric`

## Recovery Source Jars

CurseForge file IDs:

- `5473444`: `eternull-1.2-neoforge-1.20.4.jar`
- `5296977`: `ETERNULL-v1.1.0.jar` for Forge 1.20.1
- Latest 1.19.2 Forge release shown by CurseForge: `EterNull v1.0.2.jar`

Downloaded jars and decompiler output belong in `.recovery/`, which is intentionally gitignored.

## Immediate Work

1. Recover the 1.20.4 NeoForge `1.2` behavior from the jar.
2. Rebuild it as clean source on `release/1.20.4-neoforge`.
3. Redesign corruption and horror systems for `1.3.0`.
4. Backport the finished `1.3.0` behavior to 1.20.1 Forge and 1.19.2 Forge.
5. Forward port to 26.1 and add Fabric support.
