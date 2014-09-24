package com.SinfulPixel.RPCore.Schematics;

/**
 * Created by Min3 on 9/24/2014.
 */
/*
USAGE!
Evas evas;
//Create a new Evas and save it
evas = new Evas(Bukkit.getWorld("world"), 0, 0, 0, 10, 10, 10, EvasType.Square, "Spawn", "plugins/YourName/Evas");
evas.save();

//Load an Evas
evas = new Evas("Spawn", "plugins/YourName/Evas");

//Reset the Evas to the last saved location
evas.reset();
*/
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Evas {

    public enum EvasType{
        Square;
        public static EvasType type;
    }

    private int CordinateX, CordinateY, CordinateZ, CordinateX1, CordinateY1, CordinateZ1, size;
    private World CordinateWorld;
    public static  EvasType evasType;
    private String name;
    private File file;
    private YamlConfiguration yml;

    /**
     *  Create's a new Evas
     *  World world : is the World of the Evas
     *  Integer x : is the x Cordinate, Corner 1 case EvasType.Square else the center x Cordinate of the Circle
     *  Integer y : is the y Cordinate, Corner 1 case EvasType.Square else the center y Cordinate of the Circle
     *  Integer z : is the z Cordinate, Corner 1 case EvasType.Square else the center z Cordinate of the Circle
     *  Integer x1 : is the x Cordinate, Corner 2 case EvasType.Square
     *  Integer y1 : is the y Cordinate, Corner 2 case EvasType.Square
     *  Integer z1 : is the t Cordinate, Corner 2 case EvasType.Square
     */
    public Evas(World world, int x, int y, int z, int x1, int y1, int z1, EvasType type, String name, String savePath){
        this.CordinateWorld = world;
        this.name = name;
//this.file = new File("plugins/Evas/Evas", name + ".evas");
        this.file = new File(savePath, name + ".evas");
        try {
            this.file.createNewFile();
            this.file.setReadable(false);
            this.yml = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (type) {
            case Square:
                if(y > y1){
                    this.CordinateX = Math.min(x1, x);
                    this.CordinateY = Math.min(y1, y);
                    this.CordinateZ = Math.min(z1, z);
                    this.CordinateX1 = Math.max(x1, x);
                    this.CordinateY1 = Math.max(y1, y);
                    this.CordinateZ1 = Math.max(z1, z);
                }else{
                    this.CordinateX = Math.min(x, x1);
                    this.CordinateY = Math.min(y, y1);
                    this.CordinateZ = Math.min(z, z1);
                    this.CordinateX1 = Math.max(x, x1);
                    this.CordinateY1 = Math.max(y, y1);
                    this.CordinateZ1 = Math.max(z, z1);
                }
                break;
        }
        this.evasType = type;
    }


    /**
     * Load an Evas throw the name
     */
    public Evas(String name, String savePath){
//this.file = new File("plugins/Evas/Evas/" + name + ".evas");
        this.file = new File(savePath, name + ".evas");
        this.yml = YamlConfiguration.loadConfiguration(this.file);
        ArrayList<String> importantData = (ArrayList<String>) yml.getList("ImportantData");
        this.CordinateWorld = Bukkit.getWorld(importantData.get(0));
        this.CordinateX = Integer.valueOf(importantData.get(1));
        this.CordinateY = Integer.valueOf(importantData.get(2));
        this.CordinateZ = Integer.valueOf(importantData.get(3));
        this.CordinateX1 = Integer.valueOf(importantData.get(4));
        this.CordinateY1 = Integer.valueOf(importantData.get(5));
        this.CordinateZ1 = Integer.valueOf(importantData.get(6));
        this.size = Integer.valueOf(importantData.get(7));
    }

    /**
     * Get important values from the Evas
     */
    public int getX(){
        return this.CordinateX;
    }
    public int getY(){
        return this.CordinateY;
    }
    public int getZ(){
        return this.CordinateZ;
    }
    public int getX1(){
        return this.CordinateX1;
    }
    public int getY1(){
        return this.CordinateY1;
    }
    public int getZ1(){
        return this.CordinateZ1;
    }
    public World getWorld(){
        return this.CordinateWorld;
    }
    public String getName(){
        return this.name;
    }

    /**
     *
     * @return Returns the amount of blocks in the Evas
     */
    public int size(){
        this.size = getBlocks().size();
        return getBlocks().size();
    }

    /**
     * Fills the Block which are Air with the given Material
     * @param material The material type
     * @param damage The material byte
     */
    public void fill(Material material, byte damage){
        List<Block> blocks = getBlocks();
        for(Block block : blocks){
            Block locBlock = block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ());
            if(locBlock.getType() == Material.AIR){
                locBlock.setType(material);
                locBlock.setData(damage);
            }
        }
    }

    /**
     * Set's all blocks to a material
     * @param material Set the block material
     * @param damage Set the block's byte
     */
    public void set(Material material, byte damage){
        List<Block> blocks = getBlocks();
        for(Block block : blocks){
            Block locBlock = block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ());
            locBlock.setType(material);
            locBlock.setData(damage);
        }
    }

    /**
     * @param material Replaced Material
     * @param damage Replaced Material byte
     * @param toReplace Material where get's replaced
     * @param toReplaceByte Material where get's replaced byte
     */
    public void replace(Material material, byte damage, Material toReplace, byte toReplaceByte){
        List<Block> blocks = getBlocks();
        for(Block block : blocks){
            Block locBlock = block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ());
            if(locBlock.getType() == toReplace && locBlock.getData() == toReplaceByte){
                locBlock.setType(material);
                locBlock.setData(damage);
            }
        }
    }


    /**
     *
     * @return Returns a List of all Blocks
     */
    public List<Block> getBlocks() {
        Iterator<Block> block = this.iterator();
        List<Block> copy = new ArrayList<Block>();
        while (block.hasNext())
            copy.add(block.next());
        return copy;
    }

    public Iterator<Block> iterator() {
        return new EvasIterator(this.CordinateWorld, this.CordinateX, this.CordinateY, this.CordinateZ, this.CordinateX1, this.CordinateY1, this.CordinateZ1);
    }

    public class EvasIterator implements Iterator<Block> {
        private World w;
        private int baseX, baseY, baseZ;
        private int x, y, z;
        private int sizeX, sizeY, sizeZ;

        public EvasIterator(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
            this.w = w;
            this.baseX = x1;
            this.baseY = y1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeY = Math.abs(y2 - y1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            this.x = this.y = this.z = 0;
        }

        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }

        public Block next() {
            Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }
            return b;
        }

        @Override
        public void remove() {

        }


    }

    /**
     * Save the Evas to the file
     */
    public void save(){
        ArrayList<String> importantData = new ArrayList<>();
        importantData.add(this.CordinateWorld.getName());
        importantData.add(String.valueOf(this.CordinateX));
        importantData.add(String.valueOf(this.CordinateY));
        importantData.add(String.valueOf(this.CordinateZ));
        importantData.add(String.valueOf(this.CordinateX1));
        importantData.add(String.valueOf(this.CordinateY1));
        importantData.add(String.valueOf(this.CordinateZ1));
        importantData.add(String.valueOf(size()));
//String[] importantData = {this.CordinateWorld.getName(), String.valueOf(this.CordinateX), String.valueOf(this.CordinateY), String.valueOf(this.CordinateZ), String.valueOf(this.CordinateX1), String.valueOf(this.CordinateY1), String.valueOf(this.CordinateZ1), String.valueOf(size())};
        this.yml.set("ImportantData", importantData);
        List<Block> blocks = getBlocks();
        int count = 0;
        for(Block block : blocks){
            List<String> blockString = new ArrayList<>();
            blockString.add(String.valueOf(block.getX()));
            blockString.add(String.valueOf(block.getY()));
            blockString.add(String.valueOf(block.getZ()));
            blockString.add(String.valueOf(block.getType()));
            blockString.add(String.valueOf(block.getData()));
//blockString = new String[]{block.getWorld().getName(), String.valueOf(block.getX()), String.valueOf(block.getY()), String.valueOf(block.getZ()), String.valueOf(block.getType()), String.valueOf(block.getData())};
            count++;
            yml.set(String.valueOf(count), blockString);
        }
        try {
            this.yml.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Rests the Evas to the last saving point
     */
    public void reset(){
        ArrayList<String> importantData = (ArrayList<String>) yml.getList("ImportantData");
        int size = Integer.valueOf((String) importantData.get(7));
        String world = importantData.get(0);
        for(int i = 1; i <= size; i++){
            ArrayList<String> blockString = (ArrayList<String>) this.yml.getList(String.valueOf(i));
            Block b = Bukkit.getWorld(world).getBlockAt(Integer.valueOf(blockString.get(0)), Integer.valueOf(blockString.get(1)), Integer.valueOf(blockString.get(2)));
            b.setType(Material.valueOf(blockString.get(3)));
            b.setData(Byte.valueOf(blockString.get(4)));
        }
    }


    /**
     * Check if a Evas Schematic exists with that name
     * @return
     */
    public static boolean exists(String name){
        File f = new File("plugins/Evas/Evas/");
        for(File file : f.listFiles()){
            if(file.getName().equals(name + ".evas")){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }


    /**
     * Get the center Location of the Evas
     * @return
     */
    public Location getCenter() {
        int x1 = this.CordinateX + 1;
        int y1 = this.CordinateY + 1;
        int z1 = this.CordinateZ + 1;
        return new Location(this.getWorld(), this.CordinateX1 + (x1 - this.CordinateX1) / 2.0, this.CordinateY1 + (y1 - this.CordinateY1) / 2.0, this.CordinateZ1 + (z1 - this.CordinateZ1) / 2.0);
    }

    /**
     * Check if the location is in the Evas
     * @return
     */
    public boolean contains(int x, int y, int z) {
        return x >= this.CordinateX && x <= this.CordinateX1 && y >= this.CordinateY && y <= this.CordinateY1 && z >= this.CordinateZ && z <= this.CordinateZ1;
    }

    /**
     * Use this in the PlayerMoveEvent to check if the player enter's it
     * @param a e.getTo();
     * @param b e.getFrom();
     * @return
     */
    public boolean enter(Location a, Location b){
        if(this.contains(a.getBlockX(), a.getBlockY(), a.getBlockZ()) && !this.contains(b.getBlockX(), b.getBlockY(), b.getBlockZ())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Use this in the PlayerMoveEvent to check if the player leave's it
     * @param a e.getTo();
     * @param b e.getFrom();
     * @return
     */
    public boolean leave(Location a, Location b){
        if(!this.contains(a.getBlockX(), a.getBlockY(), a.getBlockZ()) && this.contains(b.getBlockX(), b.getBlockY(), b.getBlockZ())){
            return true;
        }else{
            return false;
        }
    }

}