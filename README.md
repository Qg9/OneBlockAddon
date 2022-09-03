# OneBlockAddon

An Addon for SuperiorSkyblock2 that permit to make multi-island oneblock !

## How to use

First, configure ``phases.yml`` and after ``configure.yml``.
After that, reload or restart ur server and do : ``/qgoneblock`` for the help message

The plugin require **SuperiorSkyblock2** and **PlaceholderAPI** to work

## API

Get a oneblock object from island :

```kotlin
val manager = IslandsManager.types[/*Your environment*/]
val oneblock = manager.oneblocks[/*Your Island*/]

// do stuff

manager.table.save(/*Your island*/, oneblock)

```

Get / Add a phase 

```kotlin

val phase = PhasesManager.get("token")
PhasesManager.phases.add(phase)
```
