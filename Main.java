package com.craftilandia.blockguard;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
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
import org.bukkit.event.player.PlayerInteractEntityEvent;
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
if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))){
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
				}}
	
	if (e.getEntity() instanceof ItemFrame) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
			String jefaso = getConfig().getString("chunk." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ()), "").toLowerCase();
			String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
			if(!jefaso.equals("")){
				if(!jefaso.equals(p.getName())){
					if(!amigaso.equals(p.getName())){
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "that frame belongs to " + jefaso + ".");}}
			if(jefaso.equals(p.getName()) || amigaso.equals(p.getName())){e.setCancelled(false);}}} 
		if (e.getDamager() instanceof Creeper || e.getDamager() instanceof Arrow || e.getDamager() instanceof TNTPrimed) {
						e.setCancelled(true);}}}
@EventHandler
public void crearcartel(HangingPlaceEvent e){
	Player p = e.getPlayer();
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(p.getName())){
			if(!amigaso.equals(p.getName())){
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "can not put that on " + jefaso + " land.");}}
	if(jefaso.equals(p.getName()) || amigaso.equals(p.getName())){e.setCancelled(false);}}
	
}
@EventHandler
public void rompercartel(HangingBreakByEntityEvent e){
	if(e.getRemover() instanceof Player){
	Player p = (Player) e.getRemover();
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(p.getName())){
			if(!amigaso.equals(p.getName())){
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "that frame belongs to " + jefaso + ".");}}
	if(jefaso.equals(p.getName()) || amigaso.equals(p.getName())){e.setCancelled(false);}}
	}
}
@EventHandler
public void movercartel(PlayerInteractEntityEvent e){
if(e.getPlayer() instanceof Player){
	if(e.getRightClicked() instanceof ItemFrame){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "that frame belongs to " + jefaso + ".");}}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}}}}
@EventHandler
public void pistones(BlockPistonExtendEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	if(jefaso.isEmpty()){
	e.setCancelled(true);
	}
	if(!jefaso.isEmpty()){Date date = new Date();
		getServer().getLogger().config(date + " " + e.getBlock().getChunk().getX() + " " + e.getBlock().getChunk().getZ() + " used on " + jefaso + " lands.");
	}
	}
@EventHandler
public void incendio(BlockBurnEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
if(jefaso.isEmpty()){
e.setCancelled(false);	
}
if(!jefaso.isEmpty()){
e.setCancelled(true);
}
	
}
@EventHandler
public void blockprotecttnt(EntityExplodeEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ()), "").toLowerCase();
	if(e.getEntity() instanceof Creeper){
		if(jefaso.isEmpty()){
	e.setCancelled(false);	
	}
	if(!jefaso.isEmpty()){
	e.setCancelled(true);
	}
	}
	if(e.getEntity() instanceof TNTPrimed){
		if(jefaso.isEmpty()){
	e.setCancelled(true);	
	}
	if(!jefaso.isEmpty()){
	e.setCancelled(false);}}
	}

@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player){
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("bg")){
			if(args.length == 0){
				p.sendMessage(ChatColor.DARK_BLUE + "/bg <claim unclaim tp list allow disallow>");
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
			
			if(args[0].equalsIgnoreCase("list")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/bg list <player>");
					return false;}
				if(args.length == 2){
					String lista0 = "free";
					String lista1 = "free";
					String lista2 = "free";
					String lista3 = "free";
					String lista4 = "free";
					String lista5 = "free";
					if(getConfig().contains("listchunks." + args[1] + ".0")){
					lista0 = getConfig().getString("listchunks." + args[1] + ".0");}
					if(getConfig().contains("listchunks." + args[1] + ".1")){
					lista1 = getConfig().getString("listchunks." + args[1] + ".1");}
					if(getConfig().contains("listchunks." + args[1] + ".2")){
					lista2 = getConfig().getString("listchunks." + args[1] + ".2");}
					if(getConfig().contains("listchunks." + args[1] + ".3")){
					lista3 = getConfig().getString("listchunks." + args[1] + ".3");}
					if(getConfig().contains("listchunks." + args[1] + ".4")){
					lista4 = getConfig().getString("listchunks." + args[1] + ".4");}
					if(getConfig().contains("listchunks." + args[1] + ".5")){
					lista5 = getConfig().getString("listchunks." + args[1] + ".5");}

					p.sendMessage(ChatColor.DARK_GREEN + args[1] + " list: " + ChatColor.YELLOW + lista0 + ", " + lista1 + ", " + lista2 + ", " + lista3 + ", " + lista4 + ", " + lista5 + ".");
					
					return true;}
				if(args.length >= 3){
					p.sendMessage(ChatColor.DARK_RED + "/bg list <player>");
					return false;}}
			
			if(args[0].equalsIgnoreCase("tp")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/bg tp <name>");
					return false;}	
				if(args.length == 2){
					String tpchunk = getConfig().getString("teleport." + args[1] + ".owner", "").toLowerCase();
					int xl = getConfig().getInt("teleport." + args[1] + ".x");
					int yl = getConfig().getInt("teleport." + args[1] + ".y");
					int zl = getConfig().getInt("teleport." + args[1] + ".z");
					String owner = getConfig().getString("teleport." + args[1] + ".z");
					if(tpchunk.equals("")){
						p.sendMessage(ChatColor.DARK_RED + args[1] + " does not exist");
					return false;}
					if(!owner.equals("")){
					Location newloc = new Location(p.getPlayer().getWorld(), xl,yl,zl);
					p.teleport(newloc);
				p.sendMessage(ChatColor.YELLOW + "teleporting to " + args[1]);	
				}}if(args.length >= 3){p.sendMessage(ChatColor.DARK_RED + "/bg tp <name>");return false;}}
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
				p.sendMessage(ChatColor.DARK_RED + "/bg claim name");}
			if(args.length == 2){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "").toLowerCase();
				if(jefaso.equals("")){
					int contar = 0;
					if(getConfig().contains("counter." + p.getPlayer().getName())){
					contar = getConfig().getInt("counter." + p.getPlayer().getName());
					if(contar == 5){p.sendMessage(ChatColor.DARK_RED + "You have reach limit of chunks");return false;}}
					String tpchunk = getConfig().getString("teleport." + args[1] + ".owner", "").toLowerCase();
					if(tpchunk.isEmpty()){
					int tx = p.getLocation().getBlockX();
					int ty = p.getLocation().getBlockY();
					int tz = p.getLocation().getBlockZ();
					getConfig().set("counter." + p.getPlayer().getName(), contar +1);
					getConfig().set("teleport." + args[1] + ".name", args[1]);
					getConfig().set("teleport." + args[1] + ".x", tx);
					getConfig().set("teleport." + args[1] + ".y", ty);
					getConfig().set("teleport." + args[1] + ".z", tz);
					getConfig().set("teleport." + args[1] + ".owner", p.getPlayer().getName());
					getConfig().set("listchunks." + p.getPlayer().getName() + "." + contar, args[1]);
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
				p.getPlayer().sendMessage(ChatColor.GREEN + "This chunk now is yours");}}
			if(!jefaso.equals("")){
				if(jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.YELLOW + "This chunk is already yours");}
				if(!jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk is taken by " + jefaso + ".");}}}
			if(args.length >= 3){
				p.sendMessage(ChatColor.DARK_RED + "/bg claim name");}}
		if(args[0].equalsIgnoreCase("unclaim")){
			if(args.length == 1){
				p.sendMessage(ChatColor.DARK_RED + "/bg unclaim name");
				return false;}
			if(args.length == 2){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(p.getLocation().getChunk().getX()) + "." + String.valueOf(p.getLocation().getChunk().getZ()), "").toLowerCase();
				if(!jefaso.equals("")){
					if(jefaso.equals(p.getPlayer().getName())){
						String tpchunk = getConfig().getString("teleport." + args[1] + ".owner", "").toLowerCase();
						if(tpchunk.equals(p.getName())){
						int contar = getConfig().getInt("counter." + p.getPlayer().getName());
						getConfig().set("teleport." + args[1] + ".name", "");
						getConfig().set("teleport." + args[1] + ".x", "");
						getConfig().set("teleport." + args[1] + ".y", "");
						getConfig().set("teleport." + args[1] + ".z", "");
						getConfig().set("teleport." + args[1] + ".owner", "");
						getConfig().set("listchunks." + p.getPlayer().getName() + "." + contar, "");
						getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
						getConfig().set("counter." + p.getPlayer().getName(), contar -1);
						saveConfig();
						p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Chunk successfuly removed.");}}
					if(!jefaso.equals(p.getPlayer().getName())){
						p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk belongs to " + jefaso + ".");}}
				if(jefaso.equals("")){
					p.getPlayer().sendMessage(ChatColor.AQUA + "This is a free chunk.");}
				return false;
			}if(args.length >= 3){
				p.sendMessage(ChatColor.DARK_RED + "/bg unclaim name");
				return false;}}}}return true;}}
