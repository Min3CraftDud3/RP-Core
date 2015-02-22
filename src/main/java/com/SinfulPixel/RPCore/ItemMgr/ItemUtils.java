package com.SinfulPixel.RPCore.ItemMgr;

import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Min3 on 9/19/2014.
 * Preforming checks for attributes/enchants
 */
public class ItemUtils {
    public static Material[] weapons = {Material.WOOD_SWORD,Material.STONE_SWORD,Material.IRON_SWORD,
                                        Material.GOLD_SWORD,Material.DIAMOND_SWORD,Material.BOW};

    public static Material[] armor = {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS,Material.LEATHER_CHESTPLATE,Material.LEATHER_HELMET,
                                      Material.IRON_BOOTS, Material.IRON_LEGGINGS,Material.IRON_CHESTPLATE,Material.IRON_HELMET,
                                      Material.CHAINMAIL_BOOTS,Material.CHAINMAIL_LEGGINGS,Material.CHAINMAIL_CHESTPLATE,Material.CHAINMAIL_HELMET,
                                      Material.GOLD_BOOTS, Material.GOLD_LEGGINGS,Material.GOLD_CHESTPLATE,Material.CHAINMAIL_HELMET,
                                      Material.DIAMOND_BOOTS,Material.DIAMOND_LEGGINGS,Material.DIAMOND_CHESTPLATE,Material.DIAMOND_HELMET};

    public static Material[] tools = {Material.WOOD_AXE,Material.WOOD_HOE, Material.WOOD_SPADE,Material.WOOD_PICKAXE,
                                      Material.STONE_AXE,Material.STONE_HOE,Material.STONE_SPADE, Material.STONE_PICKAXE,
                                      Material.IRON_AXE,Material.IRON_HOE,Material.IRON_SPADE,Material.IRON_PICKAXE,
                                      Material.GOLD_AXE,Material.GOLD_HOE,Material.GOLD_SPADE,Material.GOLD_PICKAXE,
                                      Material.DIAMOND_AXE,Material.DIAMOND_HOE, Material.DIAMOND_SPADE,Material.DIAMOND_PICKAXE};

    public static Material[] otherTools = {Material.FISHING_ROD, Material.SHEARS,Material.FLINT_AND_STEEL};

    public static boolean weaponCheck(ItemStack i){ //Checks to see if the ItemStack is in the weapons array;
        if(Arrays.asList(weapons).contains(i.getType())){return true;}
        return false;
    }
    public static boolean armorCheck(ItemStack i){ //Checks to see if the ItemStack is in the armor array;
        if(Arrays.asList(armor).contains(i.getType())){return true;}
        return false;
    }
    public static boolean toolCheck(ItemStack i){ //Checks to see if the ItemStack is in the tools array;
        if(Arrays.asList(tools).contains(i.getType())){return true;}
        return false;
    }
    public static ItemStack addGlow(ItemStack item){
        net.minecraft.server.v1_8_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }

}
