package com.craftilandia.blockguard;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	ArrayList<String> protectblocks = new ArrayList<String>();
	
@Override
public void onEnable() {
getServer().getPluginManager().registerEvents(this, this);
}

@EventHandler
public void nobreak(BlockBreakEvent e){

}
@EventHandler
public void noplace(BlockPlaceEvent e){
	
}
@EventHandler
public void select(PlayerInteractEvent e){
	
}

}
