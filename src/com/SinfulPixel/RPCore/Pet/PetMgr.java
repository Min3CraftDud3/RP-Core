package com.SinfulPixel.RPCore.Pet;

import com.SinfulPixel.RPCore.RPCore;
import net.minecraft.server.v1_7_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_7_R3.util.UnsafeList;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;
 
public class PetMgr implements Listener{
 
	private static Field gsa;
	private static Field goalSelector;
	private static Field targetSelector;
	private static HashMap<UUID,UUID> pets = new HashMap<UUID,UUID>();
    public static HashMap<String, Boolean> hasPet = new HashMap<String,Boolean>();
 
	static {
		try {
			gsa = PathfinderGoalSelector.class.getDeclaredField("b");
			gsa.setAccessible(true);
			goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
			goalSelector.setAccessible(true);
			targetSelector = EntityInsentient.class.getDeclaredField("targetSelector");
			targetSelector.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	RPCore plugin;
	public PetMgr(RPCore plugin){
		this.plugin = plugin;
	}
	@EventHandler
	public void combust(EntityCombustEvent e){
		for(Player p:Bukkit.getOnlinePlayers()){
			if(e.getEntity().getUniqueId()==pets.get(p.getUniqueId())){
				e.setCancelled(true);
			}
		}
	}
	@SuppressWarnings("deprecation")
	public static void makePet(LivingEntity e, UUID toFollow) {
		try {
			Object nms_entity = ((CraftLivingEntity) e).getHandle();
			if (nms_entity instanceof EntityInsentient) {
				PathfinderGoalSelector goal = (PathfinderGoalSelector) goalSelector.get(nms_entity);
				PathfinderGoalSelector target = (PathfinderGoalSelector) targetSelector.get(nms_entity);
				gsa.set(goal, new UnsafeList<Object>());
				gsa.set(target, new UnsafeList<Object>());
				goal.a(0, new PathfinderGoalFloat((EntityInsentient) nms_entity));
				goal.a(1, new PathfinderGoalWalktoTile((EntityInsentient) nms_entity, toFollow));
 
			} else {
				throw new IllegalArgumentException(e.getType().getName() + " is not an instance of an EntityInsentient.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 
	public static class PathfinderGoalWalktoTile extends PathfinderGoal {
		private EntityInsentient entity;
		private PathEntity path;
		private UUID p;
		public PathfinderGoalWalktoTile(EntityInsentient entitycreature, UUID p) {
			this.entity = entitycreature;
			this.p = p;
		}
		@Override
		public boolean a() {
			if (Bukkit.getPlayer(p) == null) {return path != null;}
			Location targetLocation = Bukkit.getPlayer(p).getLocation();
			boolean flag = this.entity.getNavigation().c();
			this.entity.getNavigation().b(false);
			this.path = this.entity.getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
			this.entity.getNavigation().b(flag);
			if (this.path != null) {this.c();}
			return this.path != null;
		}
 
		@Override
		public void c() {
			this.entity.getNavigation().a(this.path, 1D);
		}
	}
	public static void removePet(Player p) {
		for (World w : Bukkit.getServer().getWorlds()) {
			for (Entity e : w.getEntities()) {
				if (e.getUniqueId() == pets.get(p.getUniqueId())) {
					e.remove();
					pets.remove(p.getUniqueId());
					break;
				}
			}
		}
	}
	public static void setupPet(Player p, String type, String mod, String name){
		switch(type){
		case "creeper":
			Creeper creeper = p.getWorld().spawn(p.getLocation(), Creeper.class);
			if(name==null){
				creeper.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				creeper.setCustomName(ChatColor.GOLD + name);	
			}
			creeper.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("powered")){
				creeper.setPowered(true);	
			}
			creeper.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			creeper.setMaxHealth(creeper.getHealth()+10000);
	        PetMgr.makePet(creeper, p.getUniqueId());
	        pets.put(p.getUniqueId(), creeper.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "zombie":
			Zombie zombie = p.getWorld().spawn(p.getLocation(), Zombie.class);
			if(name==null){
				zombie.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				zombie.setCustomName(ChatColor.GOLD + name);	
			}
			zombie.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("baby")){
				zombie.setBaby(true);	
			}
			zombie.setFireTicks(0);
			zombie.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			zombie.setMaxHealth(zombie.getHealth()+10000);
	        PetMgr.makePet(zombie, p.getUniqueId());
	        pets.put(p.getUniqueId(), zombie.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "skeleton": 
			Skeleton skeleton = p.getWorld().spawn(p.getLocation(), Skeleton.class);
			if(name==null){
				skeleton.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				skeleton.setCustomName(ChatColor.GOLD + name);	
			}
			skeleton.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("wither")){
				skeleton.setSkeletonType(SkeletonType.WITHER);
			}
			skeleton.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			skeleton.setMaxHealth(skeleton.getHealth()+10000);
	        PetMgr.makePet(skeleton, p.getUniqueId());
	        pets.put(p.getUniqueId(), skeleton.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "pigman":
			PigZombie pigman = p.getWorld().spawn(p.getLocation(), PigZombie.class);
			if(name==null){
				pigman.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				pigman.setCustomName(ChatColor.GOLD + name);	
			}
			pigman.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("baby")){
				pigman.setBaby(true);
			}
			pigman.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			pigman.setMaxHealth(pigman.getHealth()+10000);
	        PetMgr.makePet(pigman, p.getUniqueId());
	        pets.put(p.getUniqueId(), pigman.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "cow":
			Cow cow = p.getWorld().spawn(p.getLocation(), Cow.class);
			if(name==null){
				cow.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				cow.setCustomName(ChatColor.GOLD + name);	
			}
			cow.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("baby")){
				cow.setBaby();
			}
			cow.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			cow.setMaxHealth(cow.getHealth()+10000);
	        PetMgr.makePet(cow, p.getUniqueId());
	        pets.put(p.getUniqueId(), cow.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "pig": 
			Pig pig = p.getWorld().spawn(p.getLocation(), Pig.class);
			if(name==null){
				pig.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				pig.setCustomName(ChatColor.GOLD + name);	
			}
			pig.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("baby")){
				pig.setBaby();
			}
			pig.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			pig.setMaxHealth(pig.getHealth()+10000);
	        PetMgr.makePet(pig, p.getUniqueId());
	        pets.put(p.getUniqueId(), pig.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "chicken": 
			Chicken chicken = p.getWorld().spawn(p.getLocation(), Chicken.class);
			if(name==null){
				chicken.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				chicken.setCustomName(ChatColor.GOLD + name);	
			}
			chicken.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("baby")){
				chicken.setBaby();
			}
			chicken.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			chicken.setMaxHealth(chicken.getHealth()+10000);
	        PetMgr.makePet(chicken, p.getUniqueId());
	        pets.put(p.getUniqueId(), chicken.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		case "horse": 
			Horse horse = p.getWorld().spawn(p.getLocation(), Horse.class);
			if(name==null){
				horse.setCustomName(ChatColor.GOLD + p.getName()+"'s Pet");
			}else{
				horse.setCustomName(ChatColor.GOLD + name);	
			}
			horse.setCustomNameVisible(true);
			if(mod!=null && mod.equalsIgnoreCase("baby")){
				horse.setBaby();
			}
			horse.setMaximumNoDamageTicks(Integer.MAX_VALUE);
			horse.setMaxHealth(horse.getHealth()+10000);
	        PetMgr.makePet(horse, p.getUniqueId());
	        pets.put(p.getUniqueId(), horse.getUniqueId());
	        p.sendMessage("Your pet now follows you.");
			break;
		
		}
	}
}