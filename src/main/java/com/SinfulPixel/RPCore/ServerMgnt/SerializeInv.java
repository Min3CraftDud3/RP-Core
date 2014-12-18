package com.SinfulPixel.RPCore.ServerMgnt;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Usage:
 * Inventory i = StringToInventory(inv);
 * player.getInventory().setContents(i.getContents());
 */
public class SerializeInv {
    public static String InventoryToString(Inventory invInventory) {
        String serialization = invInventory.getSize() + "_";
        for (int i = 0; i < invInventory.getSize(); i++) {
            ItemStack is = invInventory.getItem(i);
            if (is != null) {
                String serializedItemStack = new String();
                String isType = String.valueOf(is.getType().getId());
                serializedItemStack += "t@" + isType;
                if(is.hasItemMeta()){
                    if(is.getItemMeta().hasDisplayName()){
                        String isName = is.getItemMeta().getDisplayName();
                        serializedItemStack += ";n@" +"*"+isName+"*";
                    }
                    if(is.getItemMeta().hasLore()){
                        List<String> st = new ArrayList<>();
                        for(int j=0;j<is.getItemMeta().getLore().size();j++){
                            st.add(is.getItemMeta().getLore().get(j));
                        }
                        String isLore = StringUtils.join(st, "-");
                        serializedItemStack += ";l@" + isLore;
                    }
                }
                if (is.getDurability() != 0) {
                    String isDurability = String.valueOf(is.getDurability());
                    serializedItemStack += ";d@" + isDurability;
                }
                if (is.getAmount() != 1) {
                    String isAmount = String.valueOf(is.getAmount());
                    serializedItemStack += ";a@" + isAmount;
                }
                Map<Enchantment, Integer> isEnch = is.getEnchantments();
                if (isEnch.size() > 0) {
                    for (Entry<Enchantment, Integer> ench : isEnch.entrySet()) {
                        serializedItemStack += ";e@" + ench.getKey().getId() + "@" + ench.getValue();
                    }
                }
                serialization += i + "#" + serializedItemStack + "_";
            }
        }
        return serialization;
    }
    public static ItemStack[] StringToInventory(String invString) {
        String[] serializedBlocks = invString.split("_");
        String invInfo = serializedBlocks[0];
        Inventory deserializedInventory = Bukkit.getServer().createInventory(null, Integer.valueOf(invInfo));

        for (int i = 1; i < serializedBlocks.length; i++) {
            String[] serializedBlock = serializedBlocks[i].split("#");
            int stackPosition = Integer.valueOf(serializedBlock[0]);

            if (stackPosition >= deserializedInventory.getSize()) {
                continue;
            }
            ItemStack is = null;
            Boolean createdItemStack = false;
            String[] serializedItemStack = serializedBlock[1].split(";");
            ItemMeta im = null;
            for (String itemInfo : serializedItemStack) {
                String[] itemAttribute = itemInfo.split("@");
                if (itemAttribute[0].equals("t")) {
                    is = new ItemStack(Material.getMaterial(Integer.valueOf(itemAttribute[1])));
                    im = is.getItemMeta();
                    createdItemStack = true;
                }else if (itemAttribute[0].equals("n") && createdItemStack) {
                    im.setDisplayName(itemAttribute[1].replace("*",""));
                    System.out.println(itemAttribute[1]);
                } else if (itemAttribute[0].equals("l") && createdItemStack){
                    List<String> lr = new ArrayList<>();
                    for(String s: itemAttribute[1].split("-")) {
                        s.toString();
                        s.replace("-","");
                        lr.add(s);
                    }
                    im.setLore(lr);
                } else if (itemAttribute[0].equals("d") && createdItemStack) {
                    is.setDurability(Short.valueOf(itemAttribute[1]));
                } else if (itemAttribute[0].equals("a") && createdItemStack) {
                    is.setAmount(Integer.valueOf(itemAttribute[1]));
                } else if (itemAttribute[0].equals("e") && createdItemStack) {
                    is.addUnsafeEnchantment(Enchantment.getById(Integer.valueOf(itemAttribute[1])), Integer.valueOf(itemAttribute[2]));
                }
            }
            if(im != null) {
                is.setItemMeta(im);
            }
            deserializedInventory.setItem(stackPosition, is);
        }
        return deserializedInventory.getContents();
    }
}
