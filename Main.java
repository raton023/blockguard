package com.craftilandia.blockguard;
import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	ArrayList<Chunk> protectblocks = new ArrayList<Chunk>();	
@Override
public void onEnable() {
getServer().getPluginManager().registerEvents(this, this);
getConfig().options().copyDefaults(true);
saveConfig();
}
@EventHandler
public void select(PlayerInteractEvent e){
if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
		Chunk block1 = e.getClickedBlock().getLocation().getChunk();
		protectblocks.add(block1);
		getConfig().set("Chunks", protectblocks);
		saveConfig();
		e.getPlayer().sendMessage("Block Protected");
}
if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	Chunk block2 = e.getClickedBlock().getLocation().getChunk();
	getConfig().set("Chunks", protectblocks);
	saveConfig();
	protectblocks.remove(block2);
	e.getPlayer().sendMessage("Block UnProtected");
}}
@EventHandler
public void nobreak(BlockBreakEvent e){
if(protectblocks.contains(e.getBlock().getLocation().getChunk())){
	e.setCancelled(true);
}}}
