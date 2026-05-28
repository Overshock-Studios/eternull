# ETERNULL

Eternull is a Minecraft horror mod about a hostile infection called **The Corruption**. It adds a corrupted biome, spreading Null blocks, corrupted mob variants, Nullite equipment, dark wood building blocks, and configurable horror systems built around world degradation that is meant to stay playable.

Current development target: **Minecraft 1.20.4 NeoForge**  
Current mod version: **1.3.0**

## Status

This repository is a recovered and redesigned codebase based on the original released jars. Version `1.3.0` is the current redesign line. Older Minecraft backports and newer loader/version ports are planned, but this branch currently builds the 1.20.4 NeoForge version.

## Core Theme

The Corruption is intended to behave like an infection rather than a normal biome gimmick. It can spread through blocks, alter trees, affect mobs, and create small atmospheric glitches. The design goal is horror with counterplay: corruption should be dangerous and persistent, but not instantly destroy a world or remove the player’s ability to respond.

## Worldgen

### The Corruption Biome

The mod adds a biome named **The Corruption**:

- Registry ID: `eternull:the_corruption`
- Dark sky, fog, water, grass, and foliage colors
- Basalt-deltas style ambient loop
- Low-probability smoke particles
- Generates corruption trees through `eternull:the_corruption_tree`
- Uses vanilla hostile-style spawning in 1.3.0:
  - Zombie
  - Skeleton
  - Creeper
  - Spider
  - Rare Enderman
  - Rare Witch

Corrupted mobs are no longer meant to rely mainly on biome spawn entries. They are created through contact with active corruption.

### Null Block World Feature

The mod includes a placed/configured feature for `eternull:nullblock`, targeting common terrain blocks such as:

- Grass block
- Dirt
- Coarse dirt
- Podzol
- Rooted dirt
- Stone
- Gravel
- Andesite
- Granite

## Corruption System

### Active Null

Block ID: `eternull:nullblock`  
Display name: **Null**

Active Null is the main corruption block. It:

- Random-ticks.
- Can spread to nearby valid blocks.
- Emits/sculks visually through particles and sounds when ambience is enabled.
- Applies effects and conversion behavior when entities interact with it.
- Can become Dormant Null when spread fails, depending on config.

By default, surface spread is suppressed during daytime if the block can see the sky. Covered areas and caves remain dangerous.

### Dormant Null

Block ID: `eternull:dormant_null_block`  
Display name: **Dormant Null**

Dormant Null is the inactive/dead state of corruption. It does not act as active spreading corruption, but still behaves as a hostile Null-family block for entity interaction.

### Protected Blocks

Corruption spread avoids replacing several important block types:

- Air, cave air, and void air
- Water and lava
- Bedrock
- Barrier
- End portal and end portal frame
- Command blocks
- Structure block
- Jigsaw block
- Blocks with block entities, such as chests and other tile/entity-backed blocks
- Existing Null, Dormant Null, Dark Log, and Dark Leaves
- Null Ward blocks and blocks protected by a nearby Null Ward

### Logs And Leaves

When corruption spreads into natural logs or leaves, it does not turn them directly into active Null. Instead:

- Logs become `eternull:dark_log`
- Leaves become `eternull:dark_leaves`

Other valid blocks become `eternull:nullblock`.

### Null Mob Footprints

The Null mob can leave corruption behind it. In 1.3.0 this is controlled and limited:

- Footprint corruption is chance-based.
- Only a small number of nearby floor blocks can convert per check.
- It respects the same spread rules and protected block checks as normal corruption.

### Null Ward

Block ID: `eternull:null_ward`

Display name: **Null Ward**

The Null Ward is direct counterplay against spreading corruption. By default, it prevents new corruption spread within a 5-block radius.

It does not automatically cleanse existing Null blocks. It protects nearby blocks from being newly converted, making it useful for containment lines, protected rooms, and base defense.

Crafting:

- Dark Core Fragment in the center
- Dark Logs on the cardinal sides
- Dark Planks in the corners

The protection radius is configurable with `nullWardRadius`.

### Dormant Reactivation

Dormant Null can wake back up when explosions occur nearby. Each Dormant Null block near affected explosion blocks has a configurable chance to reactivate into active Null.

Null Wards suppress this reactivation inside their protection radius.

## Mob Conversion

Vanilla mobs can convert into corrupted variants after sustained exposure to active Null.

Supported conversions:

- Zombie -> Corrupted Zombie
- Creeper -> Corrupted Creeper
- Charged Creeper -> Corrupted Charged Creeper
- Spider -> Corrupted Spider

Conversion is not instant by default. Mobs must remain exposed to active corruption for a configurable amount of time, then pass a configurable conversion chance.

## Entities

### Null

Entity ID: `eternull:nullmob`  
Display name: **Null**

The Null is the mod’s central hostile creature. It is fire immune, can corrupt blocks through its footprint behavior, and drops Nullite.

### Corrupted Zombie

Entity ID: `eternull:corrupted_zombie`

A fire-immune zombie variant with increased health. It uses zombie sounds and can drop Dark Core Fragments.

### Corrupted Creeper

Entity ID: `eternull:corrupted_creeper`

A fire-immune creeper variant with increased health. When struck by lightning, it transforms through the mod’s charged corrupted creeper logic.

### Corrupted Charged Creeper

Entity ID: `eternull:corrupted_charged_creeper`

A persistent, fire-immune charged corrupted creeper variant.

### Corrupted Spider

Entity ID: `eternull:corrupted_spider`

A fire-immune spider variant with increased health and speed.

### Dark Boat

Entity ID: `eternull:dark_boat`  
Item ID: `eternull:dark_boat_item`

A dark wood boat-like entity crafted from Dark Planks.

## Blocks

### Corruption Blocks

- `eternull:nullblock` - Null
- `eternull:dormant_null_block` - Dormant Null
- `eternull:null_ward` - Null Ward

### Dark Wood Set

- `eternull:dark_log`
- `eternull:dark_wood`
- `eternull:dark_leaves`
- `eternull:dark_planks`
- `eternull:dark_stairs`
- `eternull:dark_slab`
- `eternull:dark_fence`
- `eternull:dark_fence_gate`
- `eternull:dark_pressure_plate`
- `eternull:dark_button`

Dark logs and leaves are also used as corruption results when natural trees are infected.

### Nullite Block

- `eternull:nullite_block` - Block of Nullite

Nullite can be compressed into a block and decompressed back into Nullite.

## Items

### Materials

- `eternull:nullite` - Nullite
- `eternull:dark_core_fragment` - Dark Core Fragment
- `eternull:dark_core` - Dark Core

Dark Core is crafted from nine Dark Core Fragments and is used as the smithing template item for Nullite gear.

### Tools

Nullite tools are created with smithing transforms:

- Template: `eternull:dark_core`
- Base: matching iron tool
- Addition: `eternull:nullite`

Tools:

- `eternull:nullite_sword`
- `eternull:nullite_pickaxe`
- `eternull:nullite_axe`
- `eternull:nullite_shovel`
- `eternull:nullite_hoe`

Nullite tools have:

- Mining level 3
- 483 durability
- 10 mining speed
- 22 enchantability
- Nullite repair ingredient

When used to damage a living entity, Nullite tools apply Wither for 3 seconds at amplifier 2.

### Armor

Nullite armor is created with smithing transforms:

- Template: `eternull:dark_core`
- Base: matching iron armor piece
- Addition: `eternull:nullite`

Armor pieces:

- `eternull:nullite_armor_helmet`
- `eternull:nullite_armor_chestplate`
- `eternull:nullite_armor_leggings`
- `eternull:nullite_armor_boots`

Nullite armor:

- Is fire resistant as an item.
- Uses Nullite as repair ingredient.
- Uses Netherite armor equip sound.
- Has 14 enchantability.
- Has 1.0 toughness.
- Applies Wither to attackers. Each equipped armor piece adds 20 ticks of Wither duration to the attacker.

Armor defense values:

- Boots: 3
- Leggings: 7
- Chestplate: 8
- Helmet: 3

### Spawn Eggs

- `eternull:nullmob_spawn_egg`
- `eternull:corrupted_creeper_spawn_egg`
- `eternull:corrupted_charged_creeper_spawn_egg`
- `eternull:corrupted_zombie_spawn_egg`
- `eternull:corrupted_spider_spawn_egg`

## Player Effects

Standing on Null-family blocks can affect players.

If a player steps on Null or Dormant Null:

- At night, or when the block cannot see the sky, the player receives Darkness.
- Wearing Nullite Boots prevents this Darkness effect.

Players near active corruption may also receive subtle ambience when glitches are enabled:

- Reverse portal particles
- Sculk-like clicking sounds

## Advancements

### The Corruption

Advancement key: `advancements.corruption.title`  
Description: **Find the Corruption**

### Dark Mineral

Advancement key: `advancements.nullite_achievement.title`  
Description: **Obtain a Nullite**

## Recipes

The mod currently includes recipes for:

- Dark Planks from Dark Log tag items
- Dark Wood
- Dark Boat
- Dark Button
- Dark Fence
- Dark Fence Gate
- Dark Pressure Plate
- Dark Slab
- Dark Stairs
- Dark sticks
- Null Ward
- Dark Core from nine Dark Core Fragments
- Nullite Block from nine Nullite
- Nullite from Nullite Block
- Nullite tools through smithing transforms
- Nullite armor through smithing transforms

## Loot

Corrupted hostile mobs can drop Dark Core Fragments.

The Null mob drops Nullite.

Block loot tables are included for Dark Wood blocks, Null, Dormant Null, and Nullite Block.

## Configuration

Eternull registers a server config with the following options.

### Corruption

`enableCorruptionSpread`  
Default: `true`  
Allows Null blocks and Null mobs to spread corruption by converting nearby blocks.

`corruptionSpreadsOnlyAtNight`  
Default: `true`  
When enabled, active corruption spreads only at night or when the source block cannot see the sky.

`nullBlockSpreadChance`  
Default: `30`  
Percent chance for an active Null block random tick to spread into one nearby valid block.

`nullBlockDormancyChance`  
Default: `8`  
Percent chance for active Null to become Dormant Null when it fails to spread.

`nullMobFootprintChance`  
Default: `10`  
Percent chance per Null mob tick to corrupt nearby floor blocks.

`nullMobFootprintMaxBlocks`  
Default: `2`  
Maximum nearby blocks a Null mob can corrupt during one footprint tick.

`nullWardRadius`

Default: `5`
Radius in blocks where a Null Ward prevents new corruption spread.

`dormantNullExplosionReactivationChance`

Default: `35`
Percent chance for each Dormant Null block near an explosion to reactivate into active Null.

### Entities

`mobCorruptionConversion`  
Default: `true`  
Allows vanilla mobs standing in active corruption to convert into corrupted variants when a variant exists.

`mobCorruptionExposureTicks`  
Default: `80`  
Ticks a vanilla mob must stay exposed to active corruption before it can convert.

`mobCorruptionConversionChance`  
Default: `35`  
Percent chance for exposed eligible mobs to convert on each conversion check.

`nulliteArmorReflectionCooldown`

Default: `40`
Ticks between Nullite armor Wither reflection triggers per wearer.

### Horror

`ambientGlitches`  
Default: `true`  
Allows subtle particles and sound stutters near active corruption.

`playerCorruptionGlitchChance`  
Default: `3`  
Percent chance per player check for a small corruption glitch when standing on or near active corruption.

## Technical Notes

- Mod ID: `eternull`
- Java package: `com.overshock.eternull`
- License: All Rights Reserved
- Author metadata: Overshock Games
- Current branch focus: `release/1.20.4-neoforge`

## Building

This branch uses NeoForge userdev for Minecraft 1.20.4.

On this machine, Gradle must be launched with a Java version compatible with the NeoForge/Gradle toolchain. The mod itself targets Java 17.

Example:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-23'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\gradlew.bat clean build --console=plain --no-daemon
```

Successful builds output:

```text
build/libs/eternull-1.3.0.jar
```

## Current Design Direction

The 1.3.0 redesign moves Eternull toward systemic horror:

- Corruption should spread slowly enough for players to react.
- Daylight should matter.
- Caves and covered infected zones should remain dangerous.
- Mobs should become corrupted through exposure.
- Logs and leaves should become dead dark variants instead of always becoming Null.
- Future cleansing tools should build on the Null Ward so players can both prevent and reverse corruption.
