# Frozen Bubble — OUYA port

A native port of **Frozen Bubble** to the [OUYA](https://en.wikipedia.org/wiki/Ouya)
microconsole, with full controller support and the multiplayer modes the
game is known for.

Frozen Bubble is the classic bubble-popping puzzle game (think *Puzzle
Bobble* / *Bust-a-Move*): aim, match three or more bubbles of the same
colour, and clear the board before the bubbles reach the bottom.

This port is based on the official Android version of Frozen Bubble by
**Eric Fortin** and **Pawel Aleksander Fedorynski**
([videogameboy76/frozenbubbleandroid](https://github.com/videogameboy76/frozenbubbleandroid)),
itself derived from the original game by Guillaume Cottenceau et al. It is
**GPL v2/v3** — see [Credits & licence](#credits--licence).

## Features

- **Runs natively on the OUYA** (Android 4.1 / API 16, Tegra 3, armeabi-v7a).
- **Full OUYA controller support** — menus, gameplay, and the in-game
  options menu are all reachable without a touchscreen.
- **Analog-stick aiming** in addition to the D-pad and shoulder buttons.
- **All the original game modes**: Puzzle, Arcade, and Player vs. CPU.
- **Multiplayer**:
  - **2 players on one OUYA** with two controllers (split screen).
  - **LAN / Wi-Fi** play between two consoles.
- **MOD chiptune soundtrack** (libmodplug, compiled natively for armv7).
- Renders full-screen in landscape.

## Controls

| Action | OUYA controller |
| --- | --- |
| Aim | D-pad ◀ ▶ · **left analog stick** · L1 / R1 |
| Fire bubble | **O** |
| Swap bubble | **U** · D-pad ▼ |
| Confirm (menus) | **O** |
| Cancel / back / exit game | **A** |
| In-game options menu | **Y** |

The OUYA controller has no Start, Back or Menu key, so those functions are
mapped onto the face buttons (see [docs/OUYA_PORT.md](docs/OUYA_PORT.md) for
the rationale).

## Install

Grab `FrozenBubble-OUYA-1.0.apk` from the
[Releases](../../releases) page and side-load it:

```sh
adb connect <ouya-ip>:5555
adb install FrozenBubble-OUYA-1.0.apk
```

The game registers under the OUYA **Games** menu
(`tv.ouya.intent.category.GAME`).

## Build from source

Requirements:

- JDK **17** (required by Android Gradle Plugin 8.4.2)
- Android SDK with platform **34** and build-tools **34.0.0**
- Android NDK **23.2.8568313** (builds the native libmodplug audio library)

```sh
# debug build
JAVA_HOME=/path/to/jdk-17 ./gradlew :frozenbubble:assembleDebug

# signed release build (needs keystore.properties, see below)
JAVA_HOME=/path/to/jdk-17 ./gradlew :frozenbubble:assembleRelease
```

To produce a signed release, create a `keystore.properties` file at the
repository root (kept out of version control):

```properties
storeFile=deps/frozenbubble-release.jks
storePassword=********
keyAlias=********
keyPassword=********
```

Without it, `assembleRelease` still builds but produces an unsigned APK.

See **[docs/OUYA_PORT.md](docs/OUYA_PORT.md)** for the full technical
write-up of what was changed to bring the game to the OUYA.

## Credits & licence

Frozen Bubble is distributed under the **GNU General Public License,
version 2 or 3**.

- Original game & Perl source — Guillaume Cottenceau
- Java version — Glenn Sanson
- Android port — Pawel Aleksander Fedorynski & Eric Fortin
- Artwork — Alexis Younes, Amaury Amblard-Ladurantie
- Soundtrack — Matthias Le Bidan
- OUYA port — this repository

This is an unofficial, fan-made console port and is not affiliated with or
endorsed by the original authors or OUYA.
