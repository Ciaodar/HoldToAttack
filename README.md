# Hold To Attack

<div align="center">
  <img src="https://img.shields.io/badge/Minecraft-26.3-brightgreen" alt="Minecraft Version">
  <img src="https://img.shields.io/badge/Loader-Fabric%20%7C%20NeoForge-blue" alt="Mod Loader">
  <img src="https://img.shields.io/badge/License-CC0-yellow" alt="License">
</div>

**Hold To Attack** is a simple, highly customizable Quality of Life (QoL) mod for Minecraft that allows players to continuously attack simply by holding down the attack button (default: Left Click). No more spam clicking to deal maximum damage!

## ✨ Features

- **Auto-Attack:** Automatically attacks the moment your attack cooldown reaches 100% while holding the attack key.
- **Require Target (Configurable):** By default, the mod only attacks if you are actually looking at a valid entity. If you look at thin air, you won't swing. This prevents accidental swings when fighting!
- **Keep Attack Focus (Configurable):** Once an attack has hit an entity, block mining and interaction are ignored until the attack key is released, preventing accidental block destruction during combat.
- **Global Toggle:** You can quickly enable or disable the entire mod from the configuration menu—perfect for switching between PvE and PvP sessions without having to restart the game.
- **Cross-Platform:** Supports both **Fabric** and **NeoForge** loaders seamlessly using a Native Multi-Loader structure.

## ⚙️ Configuration

The mod features a fully integrated configuration screen powered by **Cloth Config API**.

- **Fabric:** Accessible via [ModMenu](https://modrinth.com/mod/modmenu).
- **NeoForge:** Accessible directly through the built-in NeoForge mod list settings.

### Available Settings
| Setting | Description | Default |
| :--- | :--- | :--- |
| **Enabled** | Master switch for the mod. Turn this off to completely disable the auto-attack feature. | `True` |
| **Require Target** | If enabled, the mod will only auto-attack when you are aiming directly at a valid entity (mob/player). | `True` |
| **Keep Attack Focus** | If enabled, once an attack has hit an entity, block mining and interaction are ignored until the attack button is released. | `True` |

## 🛠️ For Developers / Building from Source

This project uses a **Native Multi-Loader** architecture to support both Fabric and NeoForge using their respective official toolchains, without relying on third-party compatibility layers like Architectury.

### Project Structure
- `common/` - Contains the core logic, mixins, and configuration models.
- `fabric/` - Fabric-specific entrypoints and ModMenu integrations.
- `neoforge/` - NeoForge-specific entrypoints and UI registrations.

### How to Build
To build both the Fabric and NeoForge versions of the mod, run the following command in your terminal:

```bash
./gradlew build
```

This will automatically generate the jars in the following directories:
- **Universal (Fabric, Quilt & NeoForge):** `build/libs/HoldToAttack-2.0.26.3-universal-26.3.jar`
- **Fabric:** `fabric/build/libs/HoldToAttack-2.0.26.3-fabric-26.3.jar`
- **NeoForge:** `neoforge/build/libs/HoldToAttack-2.0.26.3-neoforge-26.3.jar`

---
*Created with ❤️ for the Minecraft community.*
