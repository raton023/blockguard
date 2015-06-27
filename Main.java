package com.craftilandia.chunkprotector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {
	File playerfile = new File(getDataFolder(), "playerinfo.yml");
	FileConfiguration getConfigPlayers = YamlConfiguration.loadConfiguration(playerfile);
	File blockfile = new File(getDataFolder(), "blockinfo.yml");
	FileConfiguration getConfigBlocks = YamlConfiguration.loadConfiguration(blockfile);
    public static Economy econ = null;
@Override
public void onEnable() {
	if(!setupEconomy()){getServer().getLogger().severe(String.format("[%s] - ERROR No Vault dependency found!", getDescription().getName()));return;}
	saveDefaultConfig();
getServer().getPluginManager().registerEvents(this, this);
if(!blockfile.exists()){try {blockfile.createNewFile();}catch(IOException e) {getServer().getLogger().severe("COULD NOT CREATE blockinfo.yml please reload");}}
if(!playerfile.exists()){try {playerfile.createNewFile();}catch(IOException e) {getServer().getLogger().severe("COULD NOT CREATE playerinfo.yml please reload");}}
//getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
//	@Override 
//	public void run() {taxes();}}, 180, getConfig().getInt("tax.time"));
}
private boolean setupEconomy() {if (getServer().getPluginManager().getPlugin("Vault") == null) {return false;}
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {return false;}
    econ = rsp.getProvider();
    return econ != null;}
@EventHandler
public void nobreak(BlockBreakEvent e){
	if(e.isCancelled()){return;}
	String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ())+".owner");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(owner == null){
		if(e.getPlayer().hasPermission("chunkprotector.nobreak")){e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"you do not have permission to break.");e.setCancelled(true);return;}
		if(!e.getPlayer().hasPermission("chunkprotector.nobreak")){e.setCancelled(false);return;}
	}
	if(!owner.equals("")){
		if(!owner.equals(e.getPlayer().getName())){
			if(friend ==null || !friend.contains(e.getPlayer().getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.breakmsg").replace("OWNER", owner);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				e.getPlayer().sendMessage(message);return;}}
	if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
}


	public void taxes() {
		List<?> landlords=getConfigPlayers.getList("landlords");
		if(landlords == null){return;}
		OfflinePlayer[] jugadores = getServer().getOfflinePlayers();
		for(int i=0;jugadores.length>i;i++){
			//if(jugadores.toString().contains((CharSequence) landlords)){}
			String nombre = jugadores[i].getName();
			if(landlords != null){
			if(landlords.contains(nombre)){
				double val=getConfigPlayers.getDouble("counter." + nombre);
				if(econ.getBalance(jugadores[i]) < getConfig().getInt("tax.amount")*val){
					String sndmsg = getConfig().getString("messages.taxnopaid").replace("AMOUNT", String.valueOf(getConfig().getInt("tax.amount")*val));
					String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
					if(jugadores[i].isOnline()){jugadores[i].getPlayer().sendMessage(message);}
					List<String> getlist = getConfigPlayers.getStringList("listchunks." + nombre);
					for(int c=0;c<getlist.size();c++){
						getlist.get(c);
						int chunkx = getConfigPlayers.getInt("teleport." + getlist.get(c) + ".xchunk");
						int chunkz = getConfigPlayers.getInt("teleport." + getlist.get(c) + ".zchunk");
						String world = getConfigPlayers.getString("teleport." + getlist.get(c) + ".world");
						getConfigPlayers.set("listchunks." + nombre, "");
						getConfigPlayers.set("teleport." + getlist.get(c), "");
						if(landlords.contains(nombre)){landlords.remove(nombre);}
						getConfigPlayers.set("landlords",landlords);
						getConfigBlocks.set(getServer().getWorld(world).getName()+"."+String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
						getConfigPlayers.set("counter." + nombre, 0);
						try {getConfigBlocks.save(blockfile);} catch (IOException e) {}
						try {getConfigPlayers.save(playerfile);} catch (IOException e) {}
					}}
				if(econ.getBalance(jugadores[i]) > getConfig().getInt("tax.amount")*val){
					//if(jugadores[i].getPlayer().hasPermission("chunkprotector.pay")){
						econ.withdrawPlayer(jugadores[i], getConfig().getInt("tax.amount")*val);}
						String sndmsg = getConfig().getString("messages.taxcollect").replace("AMOUNT", String.valueOf(getConfig().getInt("tax.amount")*val));
						String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
						if(jugadores[i].isOnline()){jugadores[i].getPlayer().sendMessage(message);}
				}}}}
@EventHandler
public void noplace(BlockPlaceEvent e){
	if(e.isCancelled()){return;}
	String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ())+".owner", "");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(owner == null){
		if(e.getPlayer().hasPermission("chunkprotector.nobuild")){e.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"you do not have permission to build.");e.setCancelled(true);return;}
		if(!e.getPlayer().hasPermission("chunkprotector.nobuild")){e.setCancelled(false);return;}
	}
	if(!owner.equals("")){
		if(!owner.equals(e.getPlayer().getName())){
			if(friend ==null || !friend.contains(e.getPlayer().getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.buildmsg").replace("OWNER", owner);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				e.getPlayer().sendMessage(message);return;}}
	if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
	
}
@EventHandler
public void nouse(PlayerInteractEvent e){
	if(e.isCancelled()){return;}
	if(e.getClickedBlock() != null) {
	String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ())+".owner", "");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(!owner.equals("")){
		if(!owner.equals(e.getPlayer().getName())){
			
			if(friend ==null || !friend.contains(e.getPlayer().getName())){	
if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))){
Material b = e.getClickedBlock().getType();
			if ((b == Material.DISPENSER) || (b == Material.FENCE_GATE) || (b == Material.DROPPER) || (b == Material.HOPPER) || (b == Material.BEACON) || (b == Material.ANVIL) ||  (b == Material.BREWING_STAND) || (b == Material.JUKEBOX) || (b == Material.FURNACE) || (b == Material.CHEST) || (b == Material.LEVER) || (b == Material.WOODEN_DOOR) || (b == Material.STONE_BUTTON) || (b == Material.WOOD_BUTTON) || (b == Material.TRAP_DOOR) || (b == Material.ARMOR_STAND)){	
		e.setCancelled(true);
		String sndmsg = getConfig().getString("messages.usemsg").replace("OWNER", owner).replace("CONTAINER", b.toString());
		String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
		e.getPlayer().sendMessage(message);}}return;}}
		if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}}
@EventHandler
public void nobaldesponer(PlayerBucketEmptyEvent e){
	if(e.isCancelled()){return;}
String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ())+".owner", "");
List<?> friend = getConfigPlayers.getList("friends." + owner);	
if(!owner.equals("")){
	if(!owner.equals(e.getPlayer().getName())){
		if(friend ==null || !friend.contains(e.getPlayer().getName())){
			e.setCancelled(true);
			String sndmsg = getConfig().getString("messages.emptybucket").replace("OWNER", owner);
			String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
			e.getPlayer().sendMessage(message);return;}}
if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}

@EventHandler
public void nobaldestomar(PlayerBucketFillEvent e){
	if(e.isCancelled()){return;}
String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ())+".owner", "");
List<?> friend = getConfigPlayers.getList("friends." + owner);	
if(!owner.equals("")){
	if(!owner.equals(e.getPlayer().getName())){
		if(friend ==null || !friend.contains(e.getPlayer().getName())){
			e.setCancelled(true);
			String sndmsg = getConfig().getString("messages.fillbucket").replace("OWNER", owner);
			String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
			e.getPlayer().sendMessage(message);return;}}
if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}

@EventHandler
public void damage(EntityDamageByEntityEvent e){
	if(e.isCancelled()){return;}
	if (e.getEntity() instanceof Animals || e.getEntity() instanceof Villager){
		if(e.getDamager() instanceof Player){
	Player p = (Player) e.getDamager();
		String owner = getConfigBlocks.getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ())+".owner", "");
		List<?> friend = getConfigPlayers.getList("friends." + owner);	
		if(!owner.equals("")){
			if(!owner.equals(p.getPlayer().getName())){
				if(friend ==null || !friend.contains(p.getPlayer().getName())){
					e.setCancelled(true);
					String sndmsg = getConfig().getString("messages.pets").replace("OWNER", owner).replace("THEPET", e.getEntity().getName().toString());
					String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
					p.getPlayer().sendMessage(message);return;}}
		if(owner.equals(p.getPlayer().getName()) || friend.contains(p.getPlayer().getName()) || p.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
		}
	}
	if (e.getEntity() instanceof Player){
		if(e.getDamager() instanceof Player){
			Player intruso = (Player) e.getDamager();
			Player jefechunk = (Player) e.getEntity();
				String owner = getConfigBlocks.getString(e.getEntity().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ())+".owner", "");
				List<?> friend = getConfigPlayers.getList("friends." + owner);	
				if(!owner.equals("")){
					if(!owner.equals(jefechunk.getPlayer().getName())){
						if(friend ==null || !friend.contains(jefechunk.getPlayer().getName())){
							e.setCancelled(true);
							String sndmsg = getConfig().getString("messages.pvpoff").replace("OWNER", owner);
							String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
							intruso.getPlayer().sendMessage(message);return;}}
				if(owner.equals(jefechunk.getPlayer().getName()) || friend.contains(jefechunk.getPlayer().getName())){e.setCancelled(false);}}
				}}
	
	if (e.getEntity() instanceof ItemFrame) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
			String owner = getConfigBlocks.getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "");
			List<?> friend = getConfigPlayers.getList("friends." + owner);	
			if(!owner.equals("")){
				if(!owner.equals(p.getName())){
					if(friend ==null || !friend.contains(p.getName())){
						e.setCancelled(true);
						String sndmsg = getConfig().getString("messages.getframe").replace("OWNER", owner);
						String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
						p.sendMessage(message);return;}}
			if(owner.equals(p.getName()) || friend.contains(p.getName()) || p.hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
			} 
		if (e.getDamager() instanceof Creeper || e.getDamager() instanceof Arrow || e.getDamager() instanceof TNTPrimed) {
						e.setCancelled(true);}}
if(e.getEntity() instanceof ArmorStand){
	Player p = (Player)e.getDamager();
	String owner = getConfigBlocks.getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(!owner.equals("")){
		if(!owner.equals(p.getName())){
			if(friend ==null || !friend.contains(p.getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.breakstand").replace("OWNER", owner);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				p.sendMessage(message);return;}}
	if(owner.equals(p.getName()) || friend.contains(p.getName()) || p.hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
}
}
@EventHandler
public void crearcartel(HangingPlaceEvent e){
	if(e.isCancelled()){return;}
	Player p = e.getPlayer();
	String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(!owner.equals("")){
		if(!owner.equals(p.getName())){
			if(friend ==null || !friend.contains(p.getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.putframe").replace("OWNER", owner);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				p.sendMessage(message);return;}}
	if(owner.equals(p.getName()) || friend.contains(p.getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
	
}
@EventHandler
public void rompercartel(HangingBreakByEntityEvent e){
	if(e.isCancelled()){return;}
	if(e.getRemover() instanceof Player){
	Player p = (Player) e.getRemover();
	String owner = getConfigBlocks.getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(!owner.equals("")){
		if(friend ==null || !owner.equals(p.getName()) || !friend.contains(p.getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.breakframe").replace("OWNER", owner);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				p.sendMessage(message);return;}	
		if(owner.equals(p.getName()) || friend.contains(p.getName()) || p.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
	}
}
@EventHandler
public void moverentidad(PlayerInteractEntityEvent e){
	if(e.isCancelled()){return;}
if(e.getPlayer() instanceof Player){
	if(e.getRightClicked() instanceof ItemFrame){
	String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getZ())+".owner", "");
	List<?> friend = getConfigPlayers.getList("friends." + owner);	
	if(!owner.equals("")){
		if(!owner.equals(e.getPlayer().getName())){
			if(friend ==null || !friend.contains(e.getPlayer().getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.moveframe").replace("OWNER", owner);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				e.getPlayer().sendMessage(message);return;}}
	if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}
}}

@EventHandler
public void armorstand(PlayerInteractAtEntityEvent e){
	if(e.isCancelled()){return;}
	if(e.getRightClicked() instanceof ArmorStand){
		if(e.getPlayer() instanceof Player){
		String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getZ())+".owner", "");
		List<?> friend = getConfigPlayers.getList("friends." + owner);	
		if(!owner.equals("")){
			if(!owner.equals(e.getPlayer().getName())){
				if(friend ==null || !friend.contains(e.getPlayer().getName())){
					e.setCancelled(true);
					String sndmsg = getConfig().getString("messages.armorstand").replace("OWNER", owner);
					String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
					e.getPlayer().sendMessage(message);return;}}
		if(owner.equals(e.getPlayer().getName()) || friend.contains(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}
}}

@EventHandler
public void empujapistones(BlockPistonExtendEvent e){
	if(e.isCancelled()){return;}
	for(Block movidos : e.getBlocks()){
		String mov = getConfigBlocks.getString(e.getBlock().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(movidos.getChunk().getX()) + "." + String.valueOf(movidos.getChunk().getZ())+".owner", "");
if(!mov.isEmpty()){if(movidos.getChunk() != e.getBlock().getChunk()){
	e.setCancelled(true);
	}}}}
@EventHandler
public void incendio(BlockBurnEvent e){
	if(e.isCancelled()){return;}
	String owner = getConfigBlocks.getString(e.getBlock().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ())+".owner", "");
if(owner.isEmpty()){
e.setCancelled(false);}
if(!owner.isEmpty()){
e.setCancelled(true);}}

@EventHandler
public void caminando(PlayerMoveEvent e){
	if(e.getTo().getChunk() != e.getFrom().getChunk()){
		String jefaso = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ()), "");
		String fromjefaso = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ()), "");

		if(!fromjefaso.isEmpty()){
			String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ())+".owner");
			String owner2 = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ())+".owner");
			String chunkname = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ())+".chunkname");
			String sndleavemsg = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ())+".leavemsg").replace("OWNER", owner).replace("CHUNKNAME", chunkname).replace("PLAYER", e.getPlayer().getName());
			String enviamsg = ChatColor.translateAlternateColorCodes('&', sndleavemsg);
			if(owner.equals(owner2)){return;}
			e.getPlayer().sendMessage(enviamsg);
			}
		if(!jefaso.isEmpty()){
			String owner = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ())+".owner");
			String owner2 = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ())+".owner");
			String chunkname = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ())+".chunkname");
			String sndentermsg = getConfigBlocks.getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ())+".entermsg").replace("OWNER", owner).replace("CHUNKNAME", chunkname).replace("PLAYER", e.getPlayer().getName());
			String enviarmsg = ChatColor.translateAlternateColorCodes('&', sndentermsg);
			if(owner.equals(owner2)){return;}
			e.getPlayer().sendMessage(enviarmsg);
		}}}

@EventHandler
public void entrando(PlayerJoinEvent e){
	List<String> getlist = getConfigPlayers.getStringList("addedto." + e.getPlayer().getName());
	List<?> landlords = getConfigPlayers.getList("landlords");
	List<Player> players = e.getPlayer().getWorld().getPlayers();
	String friends = getlist.toString();
	for(int i=0;i<players.size();i++){
		if(landlords.contains(players.get(i).getName().toString()) && players.get(i).hasPermission("chunkprotector.nopay")){
			landlords.remove(players.get(i).getName());
			getConfigPlayers.set("landlords", landlords);
			try {getConfigPlayers.save(playerfile);} catch (IOException e1) {}}
		if(friends.contains(players.get(i).getName().toString())){
			String sndmsg = getConfig().getString("messages.friendjoin").replace("PLAYER", players.get(i).getName()).replace("FRIEND", e.getPlayer().getName());
			String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
			players.get(i).sendMessage(message);}}}

@EventHandler
public void blockprotecttnt(EntityExplodeEvent e){
	String owner = getConfigBlocks.getString(e.getEntity().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ())+".owner", "");
	if(e.getEntity() instanceof Creeper){
		if(owner.isEmpty()){e.setCancelled(false);}
		if(!owner.isEmpty()){e.setCancelled(true);}}
	if(e.getEntity() instanceof TNTPrimed){
		for(Block tronados : e.blockList()){
			String tronadoschunk = getConfigBlocks.getString(e.getEntity().getWorld().getWorldFolder().toString().replace("./", "")+"."+ String.valueOf(tronados.getChunk().getX()) + "." + String.valueOf(tronados.getChunk().getZ())+".owner", "");
			if(!tronadoschunk.isEmpty()){e.setCancelled(true);}}}}
@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player){
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("landtimer")){
			if(args.length==0){
				for(int i=0;10>i;i++){
				p.sendMessage("Current time: "+p.getWorld().getTime());
				try {Thread.sleep(120000);} catch (InterruptedException e) {p.sendMessage("no work timer");}
				}
			}
		}
		if(command.getName().equalsIgnoreCase("landtax")){
			if(args.length==0){
				if(p.isOp() || p.hasPermission("chunkprotector.admin")){
					p.sendMessage(ChatColor.BLUE+"Charging tax to all landlords");
					taxes();
					return true;
					//getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				//	@Override 
				//	public void run() {taxes();}}, 60, getConfig().getInt("tax.time"));p.sendMessage("Starting the tax charges to all landlords");}	
				}
				p.sendMessage("You can not use this command");
				return true;
			}return false;
		}
		if(command.getName().equalsIgnoreCase("landclaim")){		
			if(args.length == 1){	
				String disworlds=p.getWorld().getWorldFolder().toString().replace("./", "");	
				List<String> disworld=getConfig().getStringList("disableworld");	
				if(disworld.contains(disworlds)){
					String sndmsg = getConfig().getString("messages.disabledworldclaim").replace("PLAYER", p.getName());
					String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
					p.sendMessage(message);return true;}
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfigBlocks.getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner");
				if(jefaso == null){
						int max = 0;
						for(int i=0;i<30;i++){if(p.hasPermission("chunkprotector."+i)){max = i;}}
						if(p.hasPermission("chunkprotector.unlimited")){max = 1000;}
						int registradas = 0;
						registradas = getConfigPlayers.getInt("counter." + p.getPlayer().getName());
						if(registradas >= max){
							String sndmsg = getConfig().getString("messages.reachlimitchunk").replace("PLAYER", p.getName());
							String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
							p.sendMessage(message);return true;}
						if(econ.getBalance(p)<getConfig().getInt("claim.cost") && !p.hasPermission("chunkprotector.nopay")){p.sendMessage("you do not have enougth money.");return true;}
						String nombre = getConfigPlayers.getString("teleport." + args[0] + ".name");
						String tpchunk = getConfigPlayers.getString("teleport." + args[0] + ".owner", "");
						if(tpchunk.isEmpty()){
							if(!p.hasPermission("chunkprotector.nopay")){
							econ.withdrawPlayer(p, getConfig().getDouble("claim.cost"));}
						getConfigPlayers.set("counter." + p.getPlayer().getName(), registradas + 1);
						getConfigPlayers.set("teleport." + args[0] + ".x", p.getLocation().getBlockX());
						getConfigPlayers.set("teleport." + args[0] + ".y", p.getLocation().getBlockY());
						getConfigPlayers.set("teleport." + args[0] + ".z", p.getLocation().getBlockZ());
						getConfigPlayers.set("teleport." + args[0] + ".xchunk", p.getLocation().getChunk().getX());
						getConfigPlayers.set("teleport." + args[0] + ".zchunk", p.getLocation().getChunk().getZ());
						getConfigPlayers.set("teleport." + args[0] + ".owner", p.getName());
						getConfigPlayers.set("teleport." + args[0] + ".name", args[0]);
						getConfigPlayers.set("teleport." + args[0] + ".world", p.getWorld().getWorldFolder().toString().replace("./", ""));
						List<String> getlist2 = getConfigPlayers.getStringList("landlords");
						if(!getlist2.contains(p.getName()) && !p.hasPermission("chunkprotector.nopay")){getlist2.add("" + p.getName());getConfigPlayers.set("landlords", getlist2);} 
						List<String> getlist = getConfigPlayers.getStringList("listchunks." + p.getName());
						getlist.add("" + args[0]);
						getConfigPlayers.set("listchunks." + p.getName(), getlist); 
						String entermsg = getConfig().getString("messages.defaultenter");
						String exitmsg = getConfig().getString("messages.defaultleave");
						getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner", p.getPlayer().getName());
						getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".entermsg", entermsg);
						getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".leavemsg", exitmsg);
						getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".chunkname", args[0]);
						try {getConfigBlocks.save(blockfile);} catch (IOException e) {}
						try {getConfigPlayers.save(playerfile);} catch (IOException e) {}
						p.getPlayer().sendMessage(ChatColor.GREEN + "This chunk is now yours.");
						if(!p.hasPermission("chunkprotector.nopay")){p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Remember to pay "+getConfig().getInt("tax.amount")+" tax rent per claim.");}
					return true;}
					if(!nombre.isEmpty()){if(nombre.equals(args[0])){p.sendMessage(ChatColor.DARK_RED  + nombre + " is claimed by " + tpchunk);}}}
				if(jefaso != null){if(jefaso.equals(p.getPlayer().getName())){p.getPlayer().sendMessage(ChatColor.YELLOW + "This chunk is already yours");}	
				if(!jefaso.equals(p.getPlayer().getName())){p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk is taken by " + jefaso + ".");}}
				return true;}return false;}
		if(command.getName().equalsIgnoreCase("landunclaim")){
			if(args.length == 1){
				int chunkx = getConfigPlayers.getInt("teleport." + args[0] + ".xchunk");
				int chunkz = getConfigPlayers.getInt("teleport." + args[0] + ".zchunk");
				String world = getConfigPlayers.getString("teleport." + args[0] + ".world");
				String jefaso = getConfigBlocks.getString(world+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner", "");
				if(world == null){p.getPlayer().sendMessage(ChatColor.DARK_PURPLE+args[0]+" is not claimed");}
				if(!jefaso.equals("")){
					if(p.hasPermission("chunkprotector.admin")){
						String tpchunk = getConfigPlayers.getString("teleport." + args[0] + ".owner", "");
						int contar = getConfigPlayers.getInt("counter." + tpchunk);
						getConfigPlayers.set("teleport." + args[0], null);
						List<String> getlist = getConfigPlayers.getStringList("listchunks." + tpchunk);
						if(getlist.contains(args[0])){getlist.remove(args[0]);}
						getConfigPlayers.set("listchunks." +tpchunk, getlist);
						getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"."+String.valueOf(chunkx) + "." + String.valueOf(chunkz), null);
						getConfigPlayers.set("counter." + tpchunk, contar -1);
						if(contar == 1){
							List<String> getlist2 = getConfigPlayers.getStringList("landlords");
							if(getlist2.contains(tpchunk)){getlist2.remove("" + tpchunk);}
							getConfigPlayers.set("landlords", getlist2); 
						}
						try {getConfigPlayers.save(playerfile);} catch (IOException e) {}
						try {getConfigBlocks.save(blockfile);} catch (IOException e) {}
						p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Chunk of "+tpchunk+" successfuly removed.");
					}
					if(jefaso.equals(p.getPlayer().getName())){
						String tpchunk = getConfigPlayers.getString("teleport." + args[0] + ".owner", "");
						if(tpchunk.equals(p.getName())){
						int contar = getConfigPlayers.getInt("counter." + p.getPlayer().getName());
						getConfigPlayers.set("teleport." + args[0], null);
						List<String> getlist = getConfigPlayers.getStringList("listchunks." + p.getName());
						if(getlist.contains(args[0])){getlist.remove(args[0]);}
						getConfigPlayers.set("listchunks." + p.getName(), getlist);
						getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"."+String.valueOf(chunkx) + "." + String.valueOf(chunkz), null);
						getConfigPlayers.set("counter." + p.getPlayer().getName(), contar -1);
						if(contar == 1){
							List<String> getlist2 = getConfigPlayers.getStringList("landlords");
							if(getlist2.contains(p.getName())){getlist2.remove("" + p.getName());}
							getConfigPlayers.set("landlords", getlist2); 
						}
						try {getConfigPlayers.save(playerfile);} catch (IOException e) {}
						try {getConfigBlocks.save(blockfile);} catch (IOException e) {}
						p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Chunk successfuly removed.");}}
					if(!jefaso.equals(p.getPlayer().getName())){p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk belongs to " + jefaso + ".");}}
				return true;}return false;}
		if(command.getName().equalsIgnoreCase("landtp")){
			if(args.length == 1){
				String tpchunk = getConfigPlayers.getString("teleport." + args[0] + ".owner");
				if(tpchunk == null){p.sendMessage(ChatColor.DARK_RED + args[0] + " does not exist");return true;}
				if(!tpchunk.equals("")){
					for(int s=0;s<getServer().getWorlds().size();s++){
					if(getServer().getWorlds().get(s).getWorldFolder().toString().replace("./", "").equals(getConfigPlayers.getString("teleport."+args[0]+".world")) ){
						int xl = getConfigPlayers.getInt("teleport." + args[0] + ".x");
						int yl = getConfigPlayers.getInt("teleport." + args[0] + ".y");
						int zl = getConfigPlayers.getInt("teleport." + args[0] + ".z");
						Location newloc = new Location(getServer().getWorlds().get(s), xl,yl,zl);
						p.teleport(newloc);
						String sndmsg = getConfig().getString("messages.teleportmsg").replace("LOCATION", args[0]).replace("PLAYER", p.getName());
						String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
						p.sendMessage(message);	
					}}}return true;}return false;}
		if(command.getName().equalsIgnoreCase("landtpset")){
			if(args.length == 0){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfigBlocks.getString(p.getWorld().getName()+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".chunkname");
				String owner = getConfigPlayers.getString("teleport."+jefaso+".owner");
				if(getConfigPlayers.getString("teleport."+jefaso) == null){p.sendMessage(jefaso+" is not claimed.");return true;}
				if(!owner.equals(p.getName())){p.sendMessage("the land "+jefaso+" is not yours.");return true;}
				if(jefaso == null){p.sendMessage("you need to be inside "+jefaso);return true;}
				if(!jefaso.equals(jefaso)){p.sendMessage("you need to be inside "+jefaso);return true;}
				getConfigPlayers.set("teleport." + jefaso + ".x",p.getLocation().getX());
				getConfigPlayers.set("teleport." + jefaso + ".y",p.getLocation().getY());
				getConfigPlayers.set("teleport." + jefaso + ".z",p.getLocation().getZ());
				p.sendMessage(ChatColor.GREEN+"new teleport point set");
				try {getConfigPlayers.save(playerfile);} catch (IOException e) {e.printStackTrace();}
				return true;
			}return false;}
		if(command.getName().equalsIgnoreCase("landlist")){
			if(args.length == 0){
				List<String> getlist = getConfigPlayers.getStringList("listchunks." + p.getName());
				p.sendMessage(ChatColor.DARK_GREEN + p.getName() + " list: " + ChatColor.YELLOW + getlist + ".");
				return true;}
			if(args.length == 1){
				List<String> getlist = getConfigPlayers.getStringList("listchunks." + args[0]);
				p.sendMessage(ChatColor.DARK_GREEN + args[0] + " list: " + ChatColor.YELLOW + getlist + ".");
				return true;}return false;}
		if(command.getName().equalsIgnoreCase("landallow")){
			if(args.length == 1){
				List<String> getlist = getConfigPlayers.getStringList("friends." + p.getName());
				if(getlist.contains(args[0])){p.sendMessage(args[0]+" is already your friend");return true;}
				getlist.add("" + args[0]);
				getConfigPlayers.set("friends." + p.getName(), getlist); 
				List<String> getlist2 = getConfigPlayers.getStringList("addedto." + args[0]);
				getlist2.add("" + p.getName());
				getConfigPlayers.set("addedto." + args[0], getlist2); 
				try {getConfigPlayers.save(playerfile);} catch (IOException e) {e.printStackTrace();}
				p.sendMessage(ChatColor.DARK_GREEN + args[0] + " Added to your friendlist");
				return true;}return false;}
		if(command.getName().equalsIgnoreCase("landdisallow")){
			if(args.length == 1){
				List<String> getlist = getConfigPlayers.getStringList("friends." + p.getName());
				if(getlist.contains(args[0])){getlist.remove("" + args[0]);}
				getConfigPlayers.set("friends." + p.getName(), getlist); 
				List<String> getlist2 = getConfigPlayers.getStringList("addedto." + args[0]);
				if(getlist2.contains(p.getName())){getlist2.remove("" + p.getName());}
				getConfigPlayers.set("addedto." + args[0], getlist2); 
				try {getConfigPlayers.save(playerfile);} catch (IOException e) {e.printStackTrace();}
				p.sendMessage(ChatColor.DARK_GREEN + args[0] + " Removed from your friendlist");
				return true;}return false;}
		if(command.getName().equalsIgnoreCase("landallowlist")){
			if(args.length == 0){
				List<String> getlist = getConfigPlayers.getStringList("friends." + p.getName());
				p.sendMessage(ChatColor.GREEN +"friends: "+getlist);
				return true;}
			if(args.length == 1){
				List<String> getlist = getConfigPlayers.getStringList("friends." + args[0]);
				p.sendMessage(ChatColor.GREEN +args[0]+" friends: "+getlist);
				return true;}
			return false;}
		if(command.getName().equalsIgnoreCase("landenter")){
			if(args.length > 1){
				String owner = getConfigPlayers.getString("teleport." + args[0] + ".owner");
				if(!owner.equals(p.getName())){p.sendMessage("the land "+args[0]+" is not yours.");return true;}
				List<String> message = new ArrayList<String>();
				for(int i=0;i<=args.length-1;i++){message.add(args[i]);}
				int chunkx=getConfigPlayers.getInt("teleport."+args[0]+".xchunk");
				int chunkz=getConfigPlayers.getInt("teleport."+args[0]+".zchunk");
				getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".entermsg", message.toString().replace("[", "").replace("]", "").replace(args[0], "").replace(",", ""));
				try {getConfigBlocks.save(blockfile);} catch (IOException e) {}
				p.sendMessage(ChatColor.YELLOW+"Enter message set to: "+ChatColor.translateAlternateColorCodes('&', message.toString().replace("[", "").replace("]", "").replace(args[0], "").replace(",", "")));
				message.clear();return true;}return false;}
		if(command.getName().equalsIgnoreCase("landexit")){
			if(args.length > 1){
				String owner = getConfigPlayers.getString("teleport." + args[0] + ".owner");
				if(!owner.equals(p.getName())){p.sendMessage("the land "+args[0]+" is not yours.");return true;}
				List<String> message = new ArrayList<String>();
				for(int i=0;i<=args.length-1;i++){message.add(args[i]);}
				int chunkx=getConfigPlayers.getInt("teleport."+args[0]+".xchunk");
				int chunkz=getConfigPlayers.getInt("teleport."+args[0]+".zchunk");
				getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".leavemsg", message.toString().replace("[", "").replace("]", "").replace(args[0], "").replace(",", ""));
				try {getConfigBlocks.save(blockfile);} catch (IOException e) {}
				p.sendMessage(ChatColor.YELLOW+"Leave message set to: "+ChatColor.translateAlternateColorCodes('&', message.toString().replace("[", "").replace("]", "").replace(args[0], "").replace(",", "")));
				message.clear();return true;}return false;}
		if(command.getName().equalsIgnoreCase("landgive")){
			if(args.length == 2){//add tab autocomplete for players and player landlist pogo71
				String world = getConfigPlayers.getString("teleport." + args[0] + ".world");
				if(world == null){p.sendMessage("the land "+args[0]+" is not claimed.");return true;}
				boolean noencontrado = true;
				String owner = getConfigPlayers.getString("teleport." + args[0] + ".owner");
				if(!owner.equals(p.getName())){p.sendMessage(args[0]+" Belongs to "+owner);return true;}
				OfflinePlayer[] jugadores = getServer().getOfflinePlayers();
				for(int i=0;jugadores.length-1>=i;i++){
					String nombre = jugadores[i].getName();
					if(args[1].equals(nombre)){noencontrado = false;
					}
				}if(noencontrado){p.sendMessage(args[1]+" has never join the server");return true;}
				if(!noencontrado){
					int vendedorcount = getConfigPlayers.getInt("counter." + p.getName());
					int compradorcount = getConfigPlayers.getInt("counter." + args[1]);
					getConfigPlayers.set("counter." + args[1], compradorcount + 1);
					getConfigPlayers.set("counter." + p.getName(), vendedorcount - 1);
					getConfigPlayers.set("teleport." + args[0] + ".owner", args[1]);
					List<String> getlist3 = getConfigPlayers.getStringList("landlords");
					if(getlist3.contains(p.getName())){getlist3.remove("" + p.getName());}
					if(!getlist3.contains(args[1])){getlist3.add(""+args[1]);}
					if(!getlist3.contains(p.getName()) && vendedorcount >=2 && !p.hasPermission("chunkprotector.nopay")){getlist3.add(""+p.getName());}
					getConfigPlayers.set("landlords", getlist3);
					int chunkx=getConfigPlayers.getInt("teleport."+args[0]+".xchunk");
					int chunkz=getConfigPlayers.getInt("teleport."+args[0]+".zchunk");
					getConfigBlocks.set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner", args[1]);
					List<String> getlist = getConfigPlayers.getStringList("listchunks." + args[1]);
					getlist.add("" + args[0]);
					getConfigPlayers.set("listchunks." + args[1], getlist);
					List<String> getlist2 = getConfigPlayers.getStringList("listchunks." + p.getName());
					getlist2.remove(args[0]);
					getConfigPlayers.set("listchunks." + p.getName(), getlist2);
					try {getConfigPlayers.save(playerfile);} catch (IOException e) {e.printStackTrace();}
					try {getConfigBlocks.save(blockfile);} catch (IOException e) {e.printStackTrace();}
					p.sendMessage(args[0]+" now belongs to "+args[1]);
					return true;
				}}return false;}
		}return true;}}
