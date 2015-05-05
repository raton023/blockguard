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
if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	int chunkx = e.getClickedBlock().getLocation().getChunk().getX();
	int chunkz = e.getClickedBlock().getLocation().getChunk().getZ();
	getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), e.getPlayer().getName());
	saveConfig();	
	e.getPlayer().sendMessage(ChatColor.GREEN + "Este chunk ahora es tuyo " + e.getPlayer().getName());}
if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	int chunkx = e.getClickedBlock().getLocation().getChunk().getX();
	int chunkz = e.getClickedBlock().getLocation().getChunk().getZ();
	getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
	saveConfig();
	e.getPlayer().sendMessage(ChatColor.RED + "Chunk desprotegido");}}
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
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){
		if ((e.getAction().equals(Action.LEFT_CLICK_BLOCK)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
		@SuppressWarnings("deprecation")
		int b = e.getClickedBlock().getTypeId();
		if ((b == 390) || (b == 23) || (b == 107) || (b == 158) || (b == 154) || (b == 138) || (b == 145) || (b == 118) || (b == 380) || (b == 117) || (b == 379) || (b == 84) || (b == 61) || (b == 54) || (b == 69) || (b == 64) || (b == 77) || (b == 143) || (b == 96) || (b == 107)){
		e.setCancelled(true);
		}
	if(jefaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}	
}}
@EventHandler
public void nobaldes(PlayerBucketEmptyEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){
e.setCancelled(true);	}
	if(jefaso.equals(e.getPlayer().getName())){e.setCancelled(false);}
}

}


