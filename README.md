#RP-Core
---

###To-Do List:
* Fix Chat Bug - With local and RP chats.
* ~~Add Party Support~~ 
* Add Party Exp Gain
* Add Party Loot Roll
* Add Toggle Bounty Gain Money Msg
* Add Mob System
* Add Leveling System
* Add Skill System
* Add Better Race System
* Add Character Card Methods
* Link methods to MySQL DB
* Add Horse Mounts for Players Reaching level 15
* Schematic Placement System
* **Add Permission System**
* Etc.

###Gists (helpful code-bits):
* https://gist.github.com/Min3CraftDud3

###Structures:
* Anvil = inventory gui with ores to make different weapons/armor - Anvil ping noise - Need forge hammer
* Forge = lava on glass - GUI to smelt items.
* FirePit = 4 cobble slabs & 1 netherrack - Despawns within 30 minutes unless stoked - Has GUI for stoke, boil water, cook food, put out, etc.

###World Effects:
* Mob levels - epicenter & health increase
* Disable "Big Tree Growth"
 - All tree growth
 - Code something that selects one of our schematic trees based on the sapling used and replaces the sapling with the schematic of the tree after a semi random wait time based on the type of tree


###Player Effects:
* Item/Armor weight
  - Institute maximum weight limit of 100 kg
  - Have the player slow down by 1% per k they are over the weight limit
  - Minimum player speed of 10% (so players do not get stuck if they carry to much)
* Health

###RPG Effects:
* Guilds
* Bounty
* Guild bases

###Skills:
* Smithing
* Smelting
* Mining
* Cooking
* Fishing
* Woodcutting
* Ranged
* Farming
* Firemaking
* Alchemy - potions and stuff (Idea not sure, need input) (Possibly weapon or armor enhancements using essences and oils?)
* Luck - increases item drop/special crafting chance
* Woodcrafting
* Leatherworking

###Sounds:
* forge = Lava blub noise
* Cooking = fire/mob extinguish by rain noise
* Smithing =  anvil ting noise

###Ideas:
* Add gui achievement system.
* NPC shops/Quest givers - Nether Star
* Crafting Kit - Used to craft all items. Disable crafting table.
* Crafting Kit - Lvl 0+
* Advanced Crafting Kit - Lvl 40+
* Master Crafting Kit - Lvl 80+
* Skyrim like soulgem enchanting/power decrease system. (Name: Mana Shards)
* Portal system - for fast travel 2x1 portal **{Gist}** (Temporary player spell/item or Town Building? or both)

###Enchants/Effects:
* ~~Poison: poisons target~~
* ~~Erosion: Wither + Slowness applied to target~~
* ~~Fire: sets target on fire~~
* ~~Life-Steal: steals life from target~~
* [ARMOR] Lightweight - reduce item weight.
* [ARMOR] Heavyweight - Increase Item Weight and Protection
* [ARMOR] Brittle - Reduce Absorbed Damage
* [ARMOR] Tempered - Increase Absorbed Damage
* [WEAPON] Jagged - apply bleed to player.
* ~~Dull - decreased damage.~~
* ~~Sharp - increased damage.~~
* [BOOTS] Quick Step - faster walking speed.
* [WEAPON] Snare - Freeze player for X seconds.

###Equations:
* Combat Level: ((health+Defense)+(highest(str OR ranged))+(Lowest(str OR ranged)/2))/3 or Average levels.


###Item Creator Edits:
* ~~Add checkboxes for (Weapon - Armor - Neither)~~
* ~~Add Damage / Protection Value for Weapons and Armor (Max/Min)~~
