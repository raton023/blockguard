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
public void select(PlayerInteractEvent e){
	if(e.getClickedBlock() != null) {
if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	int chunkx = e.getClickedBlock().getLocation().getChunk().getX();
	int chunkz = e.getClickedBlock().getLocation().getChunk().getZ();
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	if(jefaso.equals("")){
	getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), e.getPlayer().getName());
	saveConfig();	
	e.getPlayer().sendMessage(ChatColor.GREEN + "Chunk protegido exitosamente, ahora pertenece a: " + e.getPlayer().getName());}
if(!jefaso.equals("")){
	if(jefaso.equals(e.getPlayer().getName())){
		e.getPlayer().sendMessage(ChatColor.GREEN + "Este chunk ya te pertenece");}
	if(!jefaso.equals(e.getPlayer().getName())){
		e.getPlayer().sendMessage(ChatColor.DARK_RED + "Este chunk es de " + jefaso + ".");
	}}}
if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.BOOK){
	int chunkx = e.getClickedBlock().getLocation().getChunk().getX();
	int chunkz = e.getClickedBlock().getLocation().getChunk().getZ();
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	if(!jefaso.equals("")){
		if(jefaso.equals(e.getPlayer().getName())){
			getConfig().set("chunk." + String.valueOf(chunkx) + "." + String.valueOf(chunkz), "");
			saveConfig();
			e.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "Has borrado exitosamente este chunk.");}
		if(!jefaso.equals(e.getPlayer().getName())){
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "este chunk es de " + jefaso + ".");}}
	if(jefaso.equals("")){
		e.getPlayer().sendMessage(ChatColor.YELLOW + "Este chunk esta libre.");}}}}
@EventHandler
public void nobreak(BlockBreakEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();
	if(!jefaso.equals("") || !amigaso.equals("")){
		if(!(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()))){
			e.getPlayer().sendMessage(ChatColor.RED + "Nesecitas permiso de " + jefaso + " para romper.");
			e.setCancelled(true);}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}
	}}
@EventHandler
public void noplace(BlockPlaceEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlock().getChunk().getX()) + "." + String.valueOf(e.getBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("") || !amigaso.equals(""))
	{if(!(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()))){
		e.setCancelled(true);
		e.getPlayer().sendMessage(ChatColor.RED + "Nesecitas permiso de " + jefaso + " para construir.");
	}
	if(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}}
@EventHandler
public void nouse(PlayerInteractEvent e){
	if(e.getClickedBlock() != null) {
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getClickedBlock().getChunk().getX()) + "." + String.valueOf(e.getClickedBlock().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("") || !amigaso.equals("")){
		if(!(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()))){
		if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
			Material b = e.getClickedBlock().getType();
			if ((b == Material.DISPENSER) || (b == Material.FENCE_GATE) || (b == Material.DROPPER) || (b == Material.HOPPER) || (b == Material.BEACON) || (b == Material.ANVIL) ||  (b == Material.BREWING_STAND) || (b == Material.JUKEBOX) || (b == Material.FURNACE) || (b == Material.CHEST) || (b == Material.LEVER) || (b == Material.WOODEN_DOOR) || (b == Material.STONE_BUTTON) || (b == Material.WOOD_BUTTON) || (b == Material.TRAP_DOOR)){
		e.setCancelled(true);
		e.getPlayer().sendMessage(ChatColor.RED + "Nesecitas permiso de " + jefaso + " para usar.");
		}}}
	if((jefaso.equals(e.getPlayer().getName())) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}
	}}}
@EventHandler
public void nobaldes(PlayerBucketEmptyEvent e){
	String jefaso = getConfig().getString("chunk." + String.valueOf(e.getBlockClicked().getChunk().getX()) + "." + String.valueOf(e.getBlockClicked().getChunk().getZ()), "").toLowerCase();
	String amigaso = getConfig().getString("friends." + jefaso + "." + e.getPlayer().getName(), "").toLowerCase();	
	if(!jefaso.equals("") || !amigaso.equals("")){
		if(!(jefaso.equals(e.getPlayer().getName()) || amigaso.equals(e.getPlayer().getName()))){
		e.setCancelled(true);	
		e.getPlayer().sendMessage(ChatColor.RED + "nesecitas permiso de " + jefaso + " para poner baldes.");}
	if((jefaso.equals(e.getPlayer().getName())) || amigaso.equals(e.getPlayer().getName())){e.setCancelled(false);}}}
@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	
	if(sender instanceof Player)	{
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("invite")){
			if(args.length == 0){
				p.sendMessage("a que jugador quieres invitar?");
				return false;
			}if(args.length == 1){
				getConfig().set("friends." + p.getName() + "." + args[0], args[0]);
				saveConfig();
				p.sendMessage("Amigo " + args[0] + " agregado");
			}if(args.length >= 2){
				p.sendMessage("solo puedes invitar de 1 a 1");
				return false;
			}
		}
		
	}
	
		return true;
	}

}
