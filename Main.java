package com.craftilandia.blockguard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
@Override
public void onEnable() {
getServer().getPluginManager().registerEvents(this, this);
}

@EventHandler
public void some(BlockBreakEvent e){
	
}
@EventHandler
public void some(BlockPlaceEvent e){
	
}

}
