package com.craftilandia.blockguard;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
public void select(PlayerInteractEvent e){
	if(e.getClickedBlock() != null) {
if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	int chunkx = e.getClickedBlock().getLocation().getChunk().getX();
	int chunkz = e.getClickedBlock().getLocation().getChunk().getZ();
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	if(jefaso.equals("")){
	getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), e.getPlayer().getName());
	saveConfig();	
	e.getPlayer().sendMessage(ChatColor.GREEN + "Este chunk ahora es tuyo " + e.getPlayer().getName());
	}
if(!jefaso.equals("")){
		e.getPlayer().sendMessage("Este chunk es de " + jefaso + " no puedes registrarlo.");
	}
if(jefaso.equals(e.getPlayer().getName())){
	e.getPlayer().sendMessage("Este chunk ya es tuyo");
	
}
	}

if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	int chunkx = e.getClickedBlock().getLocation().getChunk().getX();
	int chunkz = e.getClickedBlock().getLocation().getChunk().getZ();
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	
	if(jefaso.equals(e.getPlayer().getName())){
	getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
	saveConfig();
	e.getPlayer().sendMessage(ChatColor.RED + "Chunk desprotegido");
	}
	if(!jefaso.equals("")){
		e.getPlayer().sendMessage("Este chunk es de " + jefaso);
	}
	if(jefaso.equals("")){
		e.getPlayer().sendMessage("Este chunk no esta protegido");
	}
	
	
}
}}
@EventHandler
public void nobreak(BlockBreakEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){e.setCancelled(true);}
	if(jefaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}
@EventHandler
public void noplace(BlockPlaceEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){e.setCancelled(true);}
	if(jefaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}
@EventHandler
public void nouse(PlayerInteractEvent e){
	if(e.getClickedBlock() != null) {
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){
		if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
			Material b = e.getClickedBlock().getType();
			if ((b == Material.DISPENSER) || (b == Material.FENCE_GATE) || (b == Material.DROPPER) || (b == Material.HOPPER) || (b == Material.BEACON) || (b == Material.ANVIL) ||  (b == Material.BREWING_STAND) || (b == Material.JUKEBOX) || (b == Material.FURNACE) || (b == Material.CHEST) || (b == Material.LEVER) || (b == Material.WOODEN_DOOR) || (b == Material.STONE_BUTTON) || (b == Material.WOOD_BUTTON) || (b == Material.TRAP_DOOR)){
		e.setCancelled(true);
		}}}
	if(jefaso.equals(e.getPlayer().getName())){e.setCancelled(false);}
	}}
@EventHandler
public void nobaldes(PlayerBucketEmptyEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){
e.setCancelled(true);	}
	if(jefaso.equals(e.getPlayer().getName())){e.setCancelled(false);}
}

}


