package com.craftilandia.blockguard;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	ArrayList<Location> protectblocks = new ArrayList<Location>();	
@Override
public void onEnable() {
getServer().getPluginManager().registerEvents(this, this);
}
@EventHandler
public void select(PlayerInteractEvent e){
if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
		Location block1 = e.getClickedBlock().getLocation();
		protectblocks.add(block1);
		e.getPlayer().sendMessage("Bloque " + block1 + " Protegido");
}
if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	Location block2 = e.getClickedBlock().getLocation();
	protectblocks.remove(block2);
	e.getPlayer().sendMessage("Bloque " + block2 + " Desprotegido");
}}
@EventHandler
public void nobreak(BlockBreakEvent e){
if(protectblocks.contains(e.getBlock().getLocation())){
	e.setCancelled(true);
}}
@EventHandler
public void noplace(BlockPlaceEvent e){
	if(protectblocks.contains(e.getBlock().getLocation())){
		e.setCancelled(true);
}}
}
