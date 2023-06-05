# SimpleWarps

A plugin for creating warps.

## Commands

|Command|Description|Permission|
|-------|-----------|----------|
|`/warp <name>`|Teleports you to a warp.|`simplewarps.warp`|
|`/setwarp <name>`|Sets a warp.|`simplewarps.setwarp`|
|`/delwarp <name>`|Deletes a warp.|`simplewarps.delwarp`|
|`/warps`|Lists all available warps.|`simplewarps.warps`|

## Configuration

The default configuration file can be found [here](https://github.com/Meeples10/SimpleWarps/blob/master/src/main/resources/config.yml).

|Key|Description|
|---|-----------|
|`play-sound`|If true, a sound will be played when the player is teleported.|
|`teleport-sound`|If play-sound is true, this sound will be played when a player uses `/warp`. See [the Minecraft Wiki](https://minecraft.fandom.com/wiki/Sounds.json#Sound_events) for a list of available sounds.|
|`reset-velocity`|If true, the player's velocity will be reset when teleporting. This prevents fall damage if the player uses `/warp` while falling.|
|`messages`|Messages displayed by the plugin.|
