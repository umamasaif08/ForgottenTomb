# 🏺 The Forgotten Tomb

A text-based "Choose Your Own Adventure" game written in plain Java. No frameworks, no dependencies — just you, a `Scanner`, and a cursed tomb full of bad decisions waiting to happen.

## What is this?

You wake up at the entrance of an ancient tomb with no memory of how you got there. Every choice you type — `go left`, `open door`, `fight`, `run` — pushes the story down a different path. Some paths lead to treasure. Some lead to a very early death. One or two lead somewhere... unexpected.

## Requirements

- **JDK 17+** if running locally, **or**
- **Docker** (no Java install needed)

## How to Play

### Option A — With Docker
```bash
docker build -t forgotten-tomb .
docker run -it forgotten-tomb
```
⚠️ **Key trick:** you *must* use `-it`, not just `docker run forgotten-tomb`. This game reads input from the keyboard (`Scanner`), and without `-it` Docker won't attach your terminal — the container will just hang or exit with no way to type anything.

### Option B — Without Docker
```bash
javac ForgottenTomb.java
java ForgottenTomb
```

Once it's running, just type your choices when prompted — plain English like `go left` or `open door`, no need for exact casing.

## Tips & Tricks

- 🔦 **Pick up every item you're offered.** Nothing in your inventory is useless — some items silently unlock a different ending later, even if it's not obvious at the time.
- 🚪 **Read the room descriptions closely.** Small details (a draft, a smell, a sound) are usually hints about which choice is "safer."
- 💀 **Dying isn't a dead end — it's an invitation to replay.** The game loops back to the start when it's over, so try a completely different set of choices next run.
- 🕵️ **There's a secret/bonus ending.** It typically requires combining two specific items or choices that don't seem related at first glance.
- ⌨️ **Typos won't crash the game.** Invalid input just re-prompts you — so feel free to experiment with phrasing instead of worrying about exact commands.

## Key Points (what's under the hood)

- Single `.java` file, no external libraries — easy to read, easy to Dockerize
- Story branching handled with `if-else` / `switch` statements
- Simple inventory/stat system that affects which choices and endings are available
- At least 3 distinct endings (good, bad, and secret)
- Input validation so bad input never crashes the program
- "Play again?" loop at the end so you don't have to restart the container each time



Good luck. The tomb is not forgiving.
