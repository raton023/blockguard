package com.craftilandia.blockguard;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

@EventHandler
public void damage(EntityDamageByEntityEvent e){	
	if (e.getEntity() instanceof Animals || e.getEntity() instanceof Villager){
		if(e.getDamager() instanceof Player){
	Player p = (Player) e.getDamager();
		String jefaso = getConfig().getString("chunk." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ()), "").toLowerCase();
		String amigaso = getConfig().getString("friends." + jefaso + "." + p.getPlayer().getName(), "").toLowerCase();	
		if(!jefaso.equals("")){
			if(!jefaso.equals(p.getPlayer().getName())){
				if(!amigaso.equals(p.getPlayer().getName())){
					e.setCancelled(true);
					p.getPlayer().sendMessage(ChatColor.RED + "Belongs to " + jefaso + "!");}}
		if(jefaso.equals(p.getPlayer().getName()) || amigaso.equals(p.getPlayer().getName())){e.setCancelled(false);}}
		}
	}
	if (e.getEntity() instanceof Player){
		if(e.getDamager() instanceof Player){
			Player intruso = (Player) e.getDamager();
			Player jefechunk = (Player) e.getEntity();
				String jefaso = getConfig().getString("chunk." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ()), "").toLowerCase();
				String amigaso = getConfig().getString("friends." + jefaso + "." + jefechunk.getPlayer().getName(), "").toLowerCase();	
				if(!jefaso.equals("")){
					if(!jefaso.equals(intruso.getPlayer().getName())){
						if(!amigaso.equals(intruso.getPlayer().getName())){
							e.setCancelled(true);
							intruso.getPlayer().sendMessage(ChatColor.RED + "Can not kill players in their chunks.");}}
				if(jefaso.equals(intruso.getPlayer().getName()) || amigaso.equals(intruso.getPlayer().getName())){e.setCancelled(false);}}
				}
	}
	
}

@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player){
		Player p = (Player)sender;
		
		if(command.getName().equalsIgnoreCase("bg")){
			if(args.length == 0){
				p.sendMessage(ChatColor.DARK_RED + "/bg <claim unclaim tp allow disallow>");
				return false;}
			if(args[0].equalsIgnoreCase("allow")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/bg allow <player>");
					return false;}
				if(args.length == 2){
					getConfig().set("friends." + p.getName() + "." + args[1], args[1]);
					saveConfig();
					p.sendMessage(ChatColor.DARK_GREEN + args[1] + " Added to your chunks");
					return true;}
				if(args.length >= 3){
					p.sendMessage(ChatColor.DARK_RED + "/bg allow <player>");
					return false;}}
			if(args[0].equalsIgnoreCase("tp")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/bg tp <set del go> <name>");
					return false;
				}
				if(args.length == 2){
				p.sendMessage(ChatColor.DARK_RED + "/bg tp <set del go> <name>");
				return false;
			}if(args.length == 3){
				if(args[1].equalsIgnoreCase("set")){
					String tpchunk = getConfig().getString("teleport." + args[2] + ".owner", "").toLowerCase();
					if(tpchunk.isEmpty()){
					int chunkx = p.getLocation().getBlockX();
					int chunky = p.getLocation().getBlockY();
					int chunkz = p.getLocation().getBlockZ();
					getConfig().set("teleport." + args[2] + ".x", chunkx);
					getConfig().set("teleport." + args[2] + ".y", chunky);
					getConfig().set("teleport." + args[2] + ".z", chunkz);
					getConfig().set("teleport." + args[2] + ".owner", p.getPlayer().getName());
					saveConfig();	
					p.sendMessage(ChatColor.GREEN + "teleport set as " + args[2] + "!");
					return true;}
				p.sendMessage(ChatColor.RED + args[2] + " is owned by " + tpchunk);}
				if(args[1].equalsIgnoreCase("del")){
					String tpchunk = getConfig().getString("teleport." + args[2] + ".owner", "").toLowerCase();
					if(tpchunk.equals(p.getName())){
					getConfig().set("teleport." + args[2] + ".x","");
					getConfig().set("teleport." + args[2] + ".y","");
					getConfig().set("teleport." + args[2] + ".z","");
					getConfig().set("teleport." + args[2] + ".owner", "");
					saveConfig();
					p.sendMessage(ChatColor.DARK_PURPLE + "removed " + args[2] + "!");
					return true;
					}if(tpchunk.equals("")){p.sendMessage(ChatColor.RED + args[2] + " does not exist!");
					return false;}
					if(!tpchunk.isEmpty() && !tpchunk.equals(p.getName())){
					p.sendMessage(ChatColor.RED + args[2] + " is owned by " + tpchunk);}
				}
				if(args[1].equalsIgnoreCase("go")){
					String tpchunk = getConfig().getString("teleport." + args[2] + ".owner", "").toLowerCase();
					int xl = getConfig().getInt("teleport." + args[2] + ".x");
					int yl = getConfig().getInt("teleport." + args[2] + ".y");
					int zl = getConfig().getInt("teleport." + args[2] + ".z");
					String owner = getConfig().getString("teleport." + args[2] + ".z");
					if(tpchunk.equals("")){
						p.sendMessage(ChatColor.DARK_RED + args[2] + " does not exist");
					return false;
					}
					if(!owner.equals("")){
					Location newloc = new Location(p.getPlayer().getWorld(), xl,yl,zl);
					p.teleport(newloc);
				p.sendMessage(ChatColor.YELLOW + "teleporting to " + args[2]);	
				}}}
			
				if(args.length >= 4){
					p.sendMessage(ChatColor.DARK_RED + "/bg tp <set del go> <name>");
					return false;}}
			
		if(args[0].equalsIgnoreCase("disallow")){
			if(args.length == 1){
				p.sendMessage(ChatColor.DARK_RED + "/bg dissallow <player>");
				return false;
			}if(args.length == 2){
				getConfig().set("friends." + p.getName() + "." + args[1], "");
				saveConfig();
				p.sendMessage(ChatColor.DARK_PURPLE + "Player " + args[1] + " removed");
			}if(args.length >= 3){
				p.sendMessage(ChatColor.DARK_RED + "/bg dissallow <player>");
				return false;}
		}if(args[0].equalsIgnoreCase("claim")){
			if(args.length == 1){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(p.getLocation().getChunk().getX()) + "." + String.valueOf(p.getLocation().getChunk().getZ()), "").toLowerCase();
				if(jefaso.equals("")){
					int contar = 0;
					if(getConfig().contains("counter." + p.getPlayer().getName())){
					contar = getConfig().getInt("counter." + p.getPlayer().getName());
					if(contar >= 6){p.sendMessage(ChatColor.DARK_RED + "You have reach limit of chunks");return false;}
					}
					getConfig().set("counter." + p.getPlayer().getName(), contar +1);
					getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), p.getPlayer().getName());
					saveConfig();	
				int altura = p.getLocation().getBlockY();
				Block block1 = p.getLocation().getChunk().getBlock(0, altura+2, 0);
				block1.setType(Material.JACK_O_LANTERN);
				Block block2 = p.getLocation().getChunk().getBlock(0, altura+2, 15);
				block2.setType(Material.JACK_O_LANTERN);
				Block block3 = p.getLocation().getChunk().getBlock(15, altura+2, 0);
				block3.setType(Material.JACK_O_LANTERN);
				Block block4 = p.getLocation().getChunk().getBlock(15, altura+2, 15);
				block4.setType(Material.JACK_O_LANTERN);
				p.getPlayer().sendMessage(ChatColor.GREEN + "Chunk Registred. Add TP /bg tp set <tpname>");
					
					
					}
			if(!jefaso.equals("")){
				if(jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.YELLOW + "This chunk is already yours");}
				if(!jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk is taken by " + jefaso + ".");}}}
			if(args.length >= 2){
				p.sendMessage(ChatColor.DARK_RED + "/bg claim");}}
		if(args[0].equalsIgnoreCase("unclaim")){
			if(args.length == 1){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(p.getLocation().getChunk().getX()) + "." + String.valueOf(p.getLocation().getChunk().getZ()), "").toLowerCase();
				if(!jefaso.equals("")){
					if(jefaso.equals(p.getPlayer().getName())){
						int contar = getConfig().getInt("counter." + p.getPlayer().getName());
						getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
						getConfig().set("counter." + p.getPlayer().getName(), contar -1);
						saveConfig();
						p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Chunk successfuly removed.");}
					if(!jefaso.equals(p.getPlayer().getName())){
						p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk belongs to " + jefaso + ".");}}
				if(jefaso.equals("")){
					p.getPlayer().sendMessage(ChatColor.AQUA + "This is a free chunk.");}
				return false;
			}if(args.length >= 2){
				p.sendMessage(ChatColor.DARK_RED + "/bg unclaim");
				return false;
			}}}
		
	}return true;}}
