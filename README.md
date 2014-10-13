#RP-Core
---

###To-Do List:
* Fix Chat Bug - With local and RP chats.
* ~~Add Party Support~~ 
* ~~Add Party Exp Gain~~
* Add Party Loot Roll
* ~~Add Toggle Bounty Gain Money Msg~~
* *Add Mob System*
* *Add Leveling/Skill System*
* Add Race System: Nurenar, Tarnean, and Forsaken
* Add Character Card Methods
* *Link methods to MySQL DB*
* Add Horse Mounts for Players Reaching certain level.
* Schematic Placement System
* ~~Add Permission System~~
* **Add Premium Currency(currency name Sol(s))**
* **Work on implementing custom items**
* **Make it so that Quests are external Jars**
* Etc.

###Gists (helpful code-bits):
* https://gist.github.com/Min3CraftDud3

###Structures:
* Anvil = inventory gui with ores to make different weapons/armor - Anvil ping noise - Need forge hammer
* Forge = lava on glass - GUI to smelt items.
* FirePit = 4 cobble slabs & 1 netherrack - Despawns within 30 minutes unless stoked - Has GUI for stoke, boil water, cook food, put out, etc.
 
###Schematic Placement
* [FARMS]
 - Need a check for [Farmland]
 - Place fully grown of the required crop over the farmland
 - Place water under farmland as long as there is not air to the side or underneath where the water would go
* [ROADS]
 - When putting in schematic replace grass with road blocks keep air clear
* [Possible Commands]
 - /build [race] [size] [grid #] {varient #}
  * ex1. /build beastmen small 1a 4 * builds small farm with mixed crops
  * ex2. /build beastmen small 1a 1 * builds small farm with wheat
 - /build [schematic name]

###World Effects:
* Mob levels - epicenter & health increase
* ~~Disable "Big Tree Growth"~~
* Anti-Loot Steal

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
* World event: meteor - mining 

###Skills:
* Smithing
* Smelting
* Mining
* Cooking
* Fishing
* ~~Woodcutting~~
* Ranged
* Farming
* Firemaking
* Alchemy - potions and stuff (Idea not sure, need input) (Possibly weapon or armor enhancements using essences and oils?)
* Luck - increases item drop/special crafting chance
* Woodcrafting
* Leatherworking
* Strength
* Health
* Defense

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
* Crafting modifiers (items you can place in the crafting interface while making something to modify that item at creation ex. + damage increases the damage value of that item)
* ~~Teleport Animation~~

###Enchants/Effects:
* ~~Poison: poisons target~~
* ~~Erosion: Wither + Slowness applied to target~~
* ~~Molten: sets target on fire~~
* ~~Life-Steal: steals life from target~~
* [ARMOR] Lightweight - Reduce Item Weight and Protection.
* [ARMOR] Heavyweight - Increase Item Weight and Protection
* ~~[ARMOR] Brittle - Reduce Absorbed Damage~~
* ~~[ARMOR] Tempered - Increase Absorbed Damage~~
* ~~[WEAPON] Jagged - apply bleed to player.~~
* ~~Dull - decreased damage.~~
* ~~Sharp - increased damage.~~
* [BOOTS] Quick Step/Traction - faster walking speed.
* ~~[ARMOR] Smoldering - Attacker has a chance of being set on fire.~~
* [ARMOR] Reflection - Chance for attacker to recive 2x damage, victim doesnt take damage.
* [ARMOR] Leech - Attacking player has chance to have health leached to victim (armor version of lifesteal)
* ~~[ARMOR] Barbed - Attacker has a chance to bleed.~~
* ~~[ARMOR] Blackened - Attacker has chance of being withered.~~
* ~~[WEAPON] Glint - Target has a chance of receiving blindness (very short duration).~~
* [WEAPON] Rise - Chance to send target into air.
* [ARMOR] Void - Chance to teleport player 1-5 blocks away from victim.
* [ARMOR/WEAPON] Static - Chance to stun/freeze attacker.
* [ARMOR/WEAPON] Frosted - Chance to slow target.
* [WEAPON] Plague - Chance to nauseate target.
* [WEAPON] Unstable - explosion. 
* [WEAPON] Enigma - random effect.


###Equations:
* Combat Level: ((health+Defense)+(highest(str OR ranged))+(Lowest(str OR ranged)/2))/3 or Average levels.


###Item Creator Edits:
* ~~Add checkboxes for (Weapon - Armor - Neither)~~
* ~~Add Damage / Protection Value for Weapons and Armor (Max/Min)~~

###In Game GUI Elements
* Crafting Interface
 - Area to select the item you wish to craft with filters to narrow down the choices
  * Filters: Level Required to Make, Have Materials, Item Type (sword, helmet, etc...), Item Tier, Item Quality
   - Item quality is a checkbox, dropdown menu combination.
   - Check box is labeled "Minimum Quality"
    * If checked show all items of that quality or higher
    * If unchecked show all items of that quality only
