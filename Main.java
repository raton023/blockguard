package com.craftilandia.chunkprotector;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {
		
@Override
public void onEnable() {
getServer().getPluginManager().registerEvents(this, this);
getConfig().options().copyDefaults(true);
saveConfig();
}
@EventHandler
public void enreload(PluginEnableEvent e){
}

@EventHandler
public void nobreak(BlockBreakEvent e){
	if(e.isCancelled()){return;}
	String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.breakmsg").replace("OWNER", jefaso);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				e.getPlayer().sendMessage(message);}}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
if(jefaso.equals("")){e.setCancelled(false);}
}
@EventHandler
public void noplace(BlockPlaceEvent e){
	if(e.isCancelled()){return;}
	String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.buildmsg").replace("OWNER", jefaso);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				e.getPlayer().sendMessage(message);}}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}
@EventHandler
public void nouse(PlayerInteractEvent e){
	if(e.isCancelled()){return;}
	if(e.getClickedBlock() != null) {
	String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){	
if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK))){
Material b = e.getClickedBlock().getType();
			if ((b == Material.DISPENSER) || (b == Material.FENCE_GATE) || (b == Material.DROPPER) || (b == Material.HOPPER) || (b == Material.BEACON) || (b == Material.ANVIL) ||  (b == Material.BREWING_STAND) || (b == Material.JUKEBOX) || (b == Material.FURNACE) || (b == Material.CHEST) || (b == Material.LEVER) || (b == Material.WOODEN_DOOR) || (b == Material.STONE_BUTTON) || (b == Material.WOOD_BUTTON) || (b == Material.TRAP_DOOR) || (b == Material.ARMOR_STAND)){	
		e.setCancelled(true);
		String sndmsg = getConfig().getString("messages.usemsg").replace("OWNER", jefaso).replace("CONTAINER", b.toString());
		String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
		e.getPlayer().sendMessage(message);}}}}
		if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}}
@EventHandler
public void nobaldesponer(PlayerBucketEmptyEvent e){
	if(e.isCancelled()){return;}
String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ())+".owner", "").toLowerCase();
String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
if(!jefaso.equals("")){
	if(!jefaso.equals(e.getPlayer().getName())){
		if(!amigaso.equals(e.getPlayer().getName())){
			e.setCancelled(true);
			String sndmsg = getConfig().getString("messages.emptybucket").replace("OWNER", jefaso);
			String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
			e.getPlayer().sendMessage(message);}}
if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}

@EventHandler
public void nobaldestomar(PlayerBucketFillEvent e){
	if(e.isCancelled()){return;}
String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ())+".owner", "").toLowerCase();
String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
if(!jefaso.equals("")){
	if(!jefaso.equals(e.getPlayer().getName())){
		if(!amigaso.equals(e.getPlayer().getName())){
			e.setCancelled(true);
			String sndmsg = getConfig().getString("messages.fillbucket").replace("OWNER", jefaso);
			String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
			e.getPlayer().sendMessage(message);}}
if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}



@EventHandler
public void damage(EntityDamageByEntityEvent e){
	if(e.isCancelled()){return;}
	if (e.getEntity() instanceof Animals || e.getEntity() instanceof Villager){
		if(e.getDamager() instanceof Player){
	Player p = (Player) e.getDamager();
		String jefaso = getConfig().getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ())+".owner", "").toLowerCase();
		String amigaso = getConfig().getString("friends." + jefaso + "." + p.getPlayer().getName(), "").toLowerCase();	
		if(!jefaso.equals("")){
			if(!jefaso.equals(p.getPlayer().getName())){
				if(!amigaso.equals(p.getPlayer().getName())){
					e.setCancelled(true);
					String sndmsg = getConfig().getString("messages.pets").replace("OWNER", jefaso).replace("THEPET", e.getEntity().getName().toString());
					String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
					p.getPlayer().sendMessage(message);}}
		if(jefaso.equals(p.getPlayer().getName()) || amigaso.equals(p.getPlayer().getName()) || p.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
		}
	}
	if (e.getEntity() instanceof Player){
		if(e.getDamager() instanceof Player){
			Player intruso = (Player) e.getDamager();
			Player jefechunk = (Player) e.getEntity();
				String jefaso = getConfig().getString(e.getEntity().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ())+".owner", "").toLowerCase();
				String amigaso = getConfig().getString("friends." + jefaso + "." + jefechunk.getPlayer().getName(), "").toLowerCase();	
				if(!jefaso.equals("")){
					if(!jefaso.equals(intruso.getPlayer().getName())){
						if(!amigaso.equals(intruso.getPlayer().getName())){
							e.setCancelled(true);
							String sndmsg = getConfig().getString("messages.pvpoff").replace("OWNER", jefaso);
							String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
							intruso.getPlayer().sendMessage(message);}}
				if(jefaso.equals(intruso.getPlayer().getName()) || amigaso.equals(intruso.getPlayer().getName())){e.setCancelled(false);}}
				}}
	
	if (e.getEntity() instanceof ItemFrame) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
			String jefaso = getConfig().getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "").toLowerCase();
			String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
			if(!jefaso.equals("")){
				if(!jefaso.equals(p.getName())){
					if(!amigaso.equals(p.getName())){
						e.setCancelled(true);
						String sndmsg = getConfig().getString("messages.getframe").replace("OWNER", jefaso);
						String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
						p.sendMessage(message);}}
			if(jefaso.equals(p.getName()) || amigaso.equals(p.getName()) || p.hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
			} 
		if (e.getDamager() instanceof Creeper || e.getDamager() instanceof Arrow || e.getDamager() instanceof TNTPrimed) {
						e.setCancelled(true);}}
if(e.getEntity() instanceof ArmorStand){
	Player p = (Player)e.getDamager();
	String jefaso = getConfig().getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(p.getName())){
			if(!amigaso.equals(p.getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.breakstand").replace("OWNER", jefaso);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				p.sendMessage(message);}}
	if(jefaso.equals(p.getName()) || amigaso.equals(p.getName()) || p.hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
}
}
@EventHandler
public void crearcartel(HangingPlaceEvent e){
	if(e.isCancelled()){return;}
	Player p = e.getPlayer();
	String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(p.getName())){
			if(!amigaso.equals(p.getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.putframe").replace("OWNER", jefaso);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				p.sendMessage(message);}}
	if(jefaso.equals(p.getName()) || amigaso.equals(p.getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
	
}
@EventHandler
public void rompercartel(HangingBreakByEntityEvent e){
	if(e.isCancelled()){return;}
	if(e.getRemover() instanceof Player){
	Player p = (Player) e.getRemover();
	String jefaso = getConfig().getString(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + p.getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(p.getName())){
			if(!amigaso.equals(p.getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.breakframe").replace("OWNER", jefaso);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				p.sendMessage(message);}}
	if(jefaso.equals(p.getName()) || amigaso.equals(p.getName()) || p.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}
	}
}
@EventHandler
public void moverentidad(PlayerInteractEntityEvent e){
	if(e.isCancelled()){return;}
if(e.getPlayer() instanceof Player){
	if(e.getRightClicked() instanceof ItemFrame){
	String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getZ())+".owner", "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("")){
		if(!jefaso.equals(e.getPlayer().getName())){
			if(!amigaso.equals(e.getPlayer().getName())){
				e.setCancelled(true);
				String sndmsg = getConfig().getString("messages.moveframe").replace("OWNER", jefaso);
				String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
				e.getPlayer().sendMessage(message);}}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}
}}

@EventHandler
public void armorstand(PlayerInteractAtEntityEvent e){
	if(e.isCancelled()){return;}
	if(e.getRightClicked() instanceof ArmorStand){
		if(e.getPlayer() instanceof Player){
		String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getX()) + "." + String.valueOf(e.getRightClicked().getLocation().getBlock().getChunk().getZ())+".owner", "").toLowerCase();
		String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
		if(!jefaso.equals("")){
			if(!jefaso.equals(e.getPlayer().getName())){
				if(!amigaso.equals(e.getPlayer().getName())){
					e.setCancelled(true);
					String sndmsg = getConfig().getString("messages.armorstand").replace("OWNER", jefaso);
					String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
					e.getPlayer().sendMessage(message);}}
		if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()) || e.getPlayer().hasPermission("chunkprotector.bypass")){e.setCancelled(false);}}}
		
		
	}
	}


@EventHandler
public void empujapistones(BlockPistonExtendEvent e){
	if(e.isCancelled()){return;}
	for(Block movidos : e.getBlocks()){
		String mov = getConfig().getString(e.getBlock().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(movidos.getChunk().getX()) + "." + String.valueOf(movidos.getChunk().getZ())+".owner", "").toLowerCase();
if(!mov.isEmpty()){if(movidos.getChunk() != e.getBlock().getChunk()){
	e.setCancelled(true);
	}}}}
@EventHandler
public void incendio(BlockBurnEvent e){
	if(e.isCancelled()){return;}
	String jefaso = getConfig().getString(e.getBlock().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ())+".owner", "").toLowerCase();
if(jefaso.isEmpty()){
e.setCancelled(false);}
if(!jefaso.isEmpty()){
e.setCancelled(true);}}

@EventHandler
public void caminando(PlayerMoveEvent e){
	
	
	if(e.getTo().getChunk() != e.getFrom().getChunk()){
		String jefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ()), "").toLowerCase();
		String fromjefaso = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ()), "").toLowerCase();

		if(!fromjefaso.isEmpty()){
			String owner = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ())+".owner");
			String sndleavemsg = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getFrom().getChunk().getX()) + "." + String.valueOf(e.getFrom().getChunk().getZ())+".leavemsg").replace("OWNER", owner);
			String enviamsg = ChatColor.translateAlternateColorCodes('&', sndleavemsg);
			e.getPlayer().sendMessage(enviamsg);
			}
		if(!jefaso.isEmpty()){
			String owner = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ())+".owner");
			String sndentermsg = getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getTo().getChunk().getX()) + "." + String.valueOf(e.getTo().getChunk().getZ())+".entermsg").replace("OWNER", owner);
			String enviarmsg = ChatColor.translateAlternateColorCodes('&', sndentermsg);
			e.getPlayer().sendMessage(enviarmsg);
			//e.getPlayer().sendMessage(""+e.getPlayer().getWorld().getWorldFolder().toString().replace("./", ""));
		}
		
	}

	
	}

@EventHandler
public void blockprotecttnt(EntityExplodeEvent e){
	String jefaso = getConfig().getString(e.getEntity().getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(e.getEntity().getLocation().getChunk().getX()) + "." + String.valueOf(e.getEntity().getLocation().getChunk().getZ())+".owner", "").toLowerCase();
	if(e.getEntity() instanceof Creeper){
		if(jefaso.isEmpty()){
	e.setCancelled(false);	
	}
	if(!jefaso.isEmpty()){
	e.setCancelled(true);
	}
	}
	if(e.getEntity() instanceof TNTPrimed){

		for(Block tronados : e.blockList()){
			String tronadoschunk = getConfig().getString(e.getEntity().getWorld().getWorldFolder().toString().replace("./", "")+"."+ String.valueOf(tronados.getChunk().getX()) + "." + String.valueOf(tronados.getChunk().getZ())+".owner", "").toLowerCase();
			if(!tronadoschunk.isEmpty()){e.setCancelled(true);}}}}
@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	if(sender instanceof Player){
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("chunk")){
			if(args.length == 0){
				p.sendMessage(ChatColor.DARK_BLUE + "/chunk <claim unclaim tp list allow disallow set>");
				return true;}
			if(args[0].equalsIgnoreCase("set")){
				if(args.length <= 2){
					p.sendMessage(ChatColor.DARK_RED + "/chunk set <entermsg leavemsg> <chunkname> <message to send>");
					return true;}
				if(args.length >= 3){
if(args[1].equalsIgnoreCase("entermsg")){
	int chunkx = getConfig().getInt("teleport." + args[2] + ".xchunk");
	int chunkz = getConfig().getInt("teleport." + args[2] + ".zchunk");
	String world = getConfig().getString("teleport." + args[2] + ".world");
	String jefaso = getConfig().getString(world+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".chunkname");
	String owner = getConfig().getString(world+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner");
	if(jefaso == null||jefaso.isEmpty()||jefaso.equals("")){
		p.sendMessage("chunkname "+args[2]+" does not exist");
		return true;
	}
	if(!jefaso.equals("")){
		if(owner.equals(p.getName()) && args[2].equals(jefaso)){
			ArrayList<String> message = new ArrayList<String>();
			for(int i=0;i<=args.length-2;i++){
				message.add(args[i]);
			}
	
			getConfig().set(world+"."+ String.valueOf(chunkx)+"."+ String.valueOf(chunkz)+".entermsg", message.toString().replace("[", "").replace("]", "").replace("set, ", "").replace("entermsg, ", "").replace(args[2]+", ", "").replace(",", ""));
			saveConfig();
			p.sendMessage(ChatColor.YELLOW+"message set to: "+ChatColor.translateAlternateColorCodes('&', message.toString().replace("[", "").replace("]", "").replace("set, ", "").replace("entermsg, ", "").replace(args[2]+", ", "").replace(",", "")));
			message.clear();
			}
		if(!owner.equals(p.getName())){
			p.sendMessage(args[2]+" belongs to "+owner);
			return true;
		}
	}
					}
if(args[1].equalsIgnoreCase("leavemsg")){
	int chunkx = getConfig().getInt("teleport." + args[2] + ".xchunk");
	int chunkz = getConfig().getInt("teleport." + args[2] + ".zchunk");
	String world = getConfig().getString("teleport." + args[2] + ".world");
	String jefaso = getConfig().getString(world+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".chunkname");
	String owner = getConfig().getString(world+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner");
	if(jefaso == null||jefaso.isEmpty()||jefaso.equals("")){
		p.sendMessage("chunkname "+args[2]+" does not exist");
		return true;
	}
	if(!jefaso.equals("")){
		if(owner.equals(p.getName()) && args[2].equals(jefaso)){
			ArrayList<String> message = new ArrayList<String>();
			for(int i=0;i<=args.length-2;i++){
				message.add(args[i]);
			}
	
			getConfig().set(world+"."+ String.valueOf(chunkx)+"."+ String.valueOf(chunkz)+".leavemsg", message.toString().replace("[", "").replace("]", "").replace("set, ", "").replace("leavemsg, ", "").replace(args[2]+", ", "").replace(",", ""));
			saveConfig();
			p.sendMessage(ChatColor.YELLOW+"message set to: "+ChatColor.translateAlternateColorCodes('&', message.toString().replace("[", "").replace("]", "").replace("set, ", "").replace("leavemsg, ", "").replace(args[2]+", ", "").replace(",", "")));
			message.clear();
			return true;
			}
		if(!owner.equals(p.getName())){
			p.sendMessage(args[2]+" belongs to "+owner);
			return true;
		}
	}
}
if(!(args[1].equals("leavemsg")||args[1].equals("entermsg"))){
	p.sendMessage("/chunk set <entermsg leavemsg> <message>");
	return true;
}
					return true;}
			}
			if(args[0].equalsIgnoreCase("allow")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/chunk allow <player>");
					return true;}
				if(args.length == 2){
					getConfig().set("friends." + p.getName() + "." + args[1], args[1]);
					saveConfig();
					p.sendMessage(ChatColor.DARK_GREEN + args[1] + " Added to your chunks");
					return true;}
				if(args.length >= 3){
					p.sendMessage(ChatColor.DARK_RED + "/chunk allow <player>");
					return true;}}
			
			if(args[0].equalsIgnoreCase("list")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/chunk list <player>");
					return true;}
				if(args.length == 2){
					
					List<String> getlist = getConfig().getStringList("listchunks." + args[1]);
					p.sendMessage(ChatColor.DARK_GREEN + args[1] + " list: " + ChatColor.YELLOW + getlist + ".");
					return true;}
				if(args.length >= 3){
					p.sendMessage(ChatColor.DARK_RED + "/chunk list <player>");
					return true;}}
			
			if(args[0].equalsIgnoreCase("tp")){
				if(args.length == 1){
					p.sendMessage(ChatColor.DARK_RED + "/chunk tp <name>");
					return true;}	
				if(args.length == 2){
					String tpchunk = getConfig().getString("teleport." + args[1] + ".owner", "").toLowerCase();
					int xl = getConfig().getInt("teleport." + args[1] + ".x");
					int yl = getConfig().getInt("teleport." + args[1] + ".y");
					int zl = getConfig().getInt("teleport." + args[1] + ".z");
					String owner = getConfig().getString("teleport." + args[1] + ".z");
					if(tpchunk.equals("")){
						p.sendMessage(ChatColor.DARK_RED + args[1] + " does not exist");
					return true;}
					if(!owner.equals("")){
						for(int s=0;s<getServer().getWorlds().size();s++){
						if(getServer().getWorlds().get(s).getWorldFolder().toString().replace("./", "").equals(getConfig().getString("teleport."+args[1]+".world")) ){
							Location newloc = new Location(getServer().getWorlds().get(s), xl,yl,zl);
							p.teleport(newloc);
							String sndmsg = getConfig().getString("messages.teleportmsg").replace("LOCATION", args[1]);
							String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
							p.sendMessage(message);	
						}}
	
				}}if(args.length >= 3){p.sendMessage(ChatColor.DARK_RED + "/bg tp <name>");return true;}}
		if(args[0].equalsIgnoreCase("disallow")){
			if(args.length == 1){
				p.sendMessage(ChatColor.DARK_RED + "/chunk dissallow <player>");
				return true;
			}if(args.length == 2){
				getConfig().set("friends." + p.getName() + "." + args[1], "");
				saveConfig();
				p.sendMessage(ChatColor.DARK_PURPLE + "Player " + args[1] + " removed");
			}if(args.length >= 3){
				p.sendMessage(ChatColor.DARK_RED + "/chunk dissallow <player>");
				return true;}
		}if(args[0].equalsIgnoreCase("claim")){
			if(args.length == 1){
				p.sendMessage(ChatColor.DARK_RED + "/chunk claim name");}
			if(args.length == 2){
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				String jefaso = getConfig().getString("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner", "").toLowerCase();
				if(jefaso.equals("")){
					int max = 0;
					if(p.hasPermission("chunkprotector.1")){
						max = 1;
					}
					if(p.hasPermission("chunkprotector.2")){
						max = 2;
					}
					if(p.hasPermission("chunkprotector.3")){
						max = 3;
					}
					if(p.hasPermission("chunkprotector.4")){
						max = 4;
					}
					if(p.hasPermission("chunkprotector.5")){
						max = 5;
					}
					if(p.hasPermission("chunkprotector.6")){
						max = 6;
					}
					if(p.hasPermission("chunkprotector.7")){
						max = 7;
					}
					if(p.hasPermission("chunkprotector.8")){
						max = 8;
					}
					if(p.hasPermission("chunkprotector.unlimited")){
						max = 1000;
					}
					int registradas = 0;
					registradas = getConfig().getInt("counter." + p.getPlayer().getName());
					if(registradas > max){
						String sndmsg = getConfig().getString("messages.defaultenter").replace("PLAYER", p.getPlayer().getName());
						String message = ChatColor.translateAlternateColorCodes('&', sndmsg);
						p.sendMessage(message);
						return true;
					}
					
					String nombre = getConfig().getString("teleport." + args[1] + ".name");
					String tpchunk = getConfig().getString("teleport." + args[1] + ".owner", "").toLowerCase();
					if(tpchunk.isEmpty()){
					int tx = p.getLocation().getBlockX();
					int ty = p.getLocation().getBlockY();
					int tz = p.getLocation().getBlockZ();
					getConfig().set("counter." + p.getPlayer().getName(), registradas + 1);
					getConfig().set("teleport." + args[1] + ".x", tx);
					getConfig().set("teleport." + args[1] + ".y", ty);
					getConfig().set("teleport." + args[1] + ".z", tz);
					getConfig().set("teleport." + args[1] + ".xchunk", p.getLocation().getChunk().getX());
					getConfig().set("teleport." + args[1] + ".zchunk", p.getLocation().getChunk().getZ());
					getConfig().set("teleport." + args[1] + ".owner", p.getName());
					getConfig().set("teleport." + args[1] + ".name", args[1]);
					getConfig().set("teleport." + args[1] + ".world", p.getWorld().getWorldFolder().toString().replace("./", ""));
					List<String> getlist = getConfig().getStringList("listchunks." + p.getName());
					getlist.add("" + args[1]);
					getConfig().set("listchunks." + p.getName(), getlist); 
					String entermsg = getConfig().getString("messages.defaultenter");
					String exitmsg = getConfig().getString("messages.defaultleave");
					getConfig().set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner", p.getPlayer().getName());
					getConfig().set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".entermsg", entermsg);
					getConfig().set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".leavemsg", exitmsg);
					getConfig().set(p.getWorld().getWorldFolder().toString().replace("./", "")+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".chunkname", args[1]);
					saveConfig();	
					int altura = p.getLocation().getBlockY();
		              Block block1 = p.getLocation().getChunk().getBlock(0, altura + 2, 0);
		              block1.setType(Material.JACK_O_LANTERN);
		              Block block2 = p.getLocation().getChunk().getBlock(0, altura + 2, 15);
		              block2.setType(Material.JACK_O_LANTERN);
		              Block block3 = p.getLocation().getChunk().getBlock(15, altura + 2, 0);
		              block3.setType(Material.JACK_O_LANTERN);
		              Block block4 = p.getLocation().getChunk().getBlock(15, altura + 2, 15);
		              block4.setType(Material.JACK_O_LANTERN);
				p.getPlayer().sendMessage(ChatColor.GREEN + "This chunk is now yours");
				return true;}
				if(!nombre.isEmpty()){
					if(nombre.equals(args[1])){
						p.sendMessage(ChatColor.DARK_RED  + nombre + " is registred by " + tpchunk);}}}
			if(!jefaso.equals("")){
				if(jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.YELLOW + "This chunk is already yours");}
				if(!jefaso.equals(p.getPlayer().getName())){
					p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk is taken by " + jefaso + ".");}}}
			if(args.length >= 3){
				p.sendMessage(ChatColor.DARK_RED + "/chunk claim name");}}
		if(args[0].equalsIgnoreCase("unclaim")){
			if(args.length == 1){
				p.sendMessage(ChatColor.DARK_RED + "/chunk unclaim name");
				return true;}
			if(args.length == 2){
				int chunkx = getConfig().getInt("teleport." + args[1] + ".xchunk");
				int chunkz = getConfig().getInt("teleport." + args[1] + ".zchunk");
				String world = getConfig().getString("teleport." + args[1] + ".world");
				String jefaso = getConfig().getString(world+"." + String.valueOf(chunkx) + "." + String.valueOf(chunkz)+".owner", "").toLowerCase();
				if(!jefaso.equals("")){
					if(jefaso.equals(p.getPlayer().getName())){
						String tpchunk = getConfig().getString("teleport." + args[1] + ".owner", "").toLowerCase();
						if(tpchunk.equals(p.getName())){
						int contar = getConfig().getInt("counter." + p.getPlayer().getName());
						getConfig().set("teleport." + args[1], "");
						List<String> getlist = getConfig().getStringList("listchunks." + p.getName());
						if(getlist.contains(args[1])){
							getlist.remove(args[1]);}
						getConfig().set("listchunks." + p.getName(), getlist);
						getConfig().set(p.getWorld().getWorldFolder().toString().replace("./", "")+"."+String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
						getConfig().set("counter." + p.getPlayer().getName(), contar -1);
						saveConfig();
						p.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Chunk successfuly removed.");}}
					if(!jefaso.equals(p.getPlayer().getName())){
						p.getPlayer().sendMessage(ChatColor.DARK_RED + "This chunk belongs to " + jefaso + ".");}}
				if(jefaso.equals("")){
					p.getPlayer().sendMessage(ChatColor.AQUA + "This is a free chunk.");}
				return true;
			}if(args.length >= 3){
				p.sendMessage(ChatColor.DARK_RED + "/chunk unclaim name");
				return true;}}}
		
	
	}return true;}}
