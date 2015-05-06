package com.craftilandia.blockguard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {
@Override
public void onEnable() {
getServer().getPluginManager().registerEvents(this, this);
getConfig().options().copyDefaults(true);
saveConfig();}
@EventHandler
public void nobreak(BlockBreakEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "Can not break on " + jefaso + " chunk.");}}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}
if(jefaso.equals("")){e.setCancelled(false);}}
@EventHandler
public void noplace(BlockPlaceEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "Can not build on " + jefaso + " land.");}}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}}
@EventHandler
public void nouse(PlayerInteractEvent e){
	if(e.getClickedBlock() != null) {
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){	
if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
Material b = e.getClickedBlock().getType();
			if ((b == Material.DISPENSER) || (b == Material.FENCE_GATE) || (b == Material.DROPPER) || (b == Material.HOPPER) || (b == Material.BEACON) || (b == Material.ANVIL) ||  (b == Material.BREWING_STAND) || (b == Material.JUKEBOX) || (b == Material.FURNACE) || (b == Material.CHEST) || (b == Material.LEVER) || (b == Material.WOODEN_DOOR) || (b == Material.STONE_BUTTON) || (b == Material.WOOD_BUTTON) || (b == Material.TRAP_DOOR)){	
		e.setCancelled(true);
		e.getPlayer().sendMessage(ChatColor.RED + "Can not use that on " + jefaso + " land.");}}}}
		if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}}}
@EventHandler
public void nobaldes(PlayerBucketEmptyEvent e){
String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ()), "").toLowerCase();
String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
if(!jefaso.equals("")){
	if(!jefaso.equals(e.getPlayer().getName())){
		if(!amigaso.equals(e.getPlayer().getName())){
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Can not use buckets on " + jefaso + " land.");}}
if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}}
@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player){
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("invite")){
			if(args.length == 0){
				p.sendMessage("Add the name of the player?");
				return false;
			}if(args.length == 1){
				getConfig().set("friends." + p.getName() + "." + args[0], args[0]);
				saveConfig();
				p.sendMessage("Friend " + args[0] + " Added to your chunks");
			}if(args.length >= 2){
				p.sendMessage("You may invite one by one");
				return false;}}
		if(command.getName().equalsIgnoreCase("tpto")){
			if(args.length == 0){
				p.sendMessage("/tpto set chunk1 -o- /tpto tp chunk1");
				return false;
			}if(args.length == 1){
				p.sendMessage("/tpto set chunk1 -o- /tpto tp chunk1");
				return false;
			}if(args.length == 2){
				if(args[0].equalsIgnoreCase("set")){
					getConfig().set("teleport." + args[1] + "." + args[1], p.getPlayer().getLocation().getX() + "." + p.getLocation().getZ());
					saveConfig();
					p.sendMessage("teleport " + args[1] + " establecido");}
				if(args[0].equalsIgnoreCase("tp")){
					p.sendMessage("Working on this feature.");}}
				if(args.length >= 3){
					p.sendMessage("/tpto set chunk1 -o- /tpto tp chunk1");
					return false;}}
		if(command.getName().equalsIgnoreCase("uninvite")){
			if(args.length == 0){
				p.sendMessage("Add the player to be remove?");
				return false;
			}if(args.length == 1){
				getConfig().set("friends." + p.getName() + "." + args[0], "");
				saveConfig();
				p.sendMessage("Player " + args[0] + " removed");
			}if(args.length >= 2){
				p.sendMessage("You may remove one by one");
				return false;}
		}if(command.getName().equalsIgnoreCase("claim")){
			if(args.length == 0){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(p.getLocation().getChunk().getX()) + "." + String.valueOf(p.getLocation().getChunk().getZ()), "").toLowerCase();
				if(jefaso.equals("")){
				getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), p.getPlayer().getName());
				saveConfig();	
				p.getPlayer().sendMessage(ChatColor.GREEN + "This Chunk has new owner: " + p.getPlayer().getName());}
			if(!jefaso.equals("")){
				if(jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.GREEN + "This chunk is already yours");}
				if(!jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk is taken by " + jefaso + ".");}}}
			if(args.length >= 1){
				p.sendMessage("just one command /claim");}}
		if(command.getName().equalsIgnoreCase("unclaim")){
			if(args.length == 0){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(p.getLocation().getChunk().getX()) + "." + String.valueOf(p.getLocation().getChunk().getZ()), "").toLowerCase();
				if(!jefaso.equals("")){
					if(jefaso.equals(p.getPlayer().getName())){
						getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
						saveConfig();
						p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Chunk successfuly removed.");}
					if(!jefaso.equals(p.getPlayer().getName())){
						p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk belongs to " + jefaso + ".");}}
				if(jefaso.equals("")){
					p.getPlayer().sendMessage(ChatColor.YELLOW + "This is a free chunk.");}
				return false;
			}if(args.length >= 1){
				p.sendMessage("just one command /unclaim");
			}}}return true;}}
