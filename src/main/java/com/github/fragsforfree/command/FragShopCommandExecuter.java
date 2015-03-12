package com.github.fragsforfree.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.github.fragsforfree.FragShop;
import com.github.fragsforfree.permission.EnumPermissions;

public class FragShopCommandExecuter implements CommandExecutor{

	private FragShop plugin;	
	
	public FragShopCommandExecuter(FragShop plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		
		if (sender instanceof Player && sender.hasPermission(EnumPermissions.FragShopRepair.toString())){  
			switch(args.length){
			
				case 1:    		
				   		if (args[0].toLowerCase().equalsIgnoreCase("repair-cost")){
				   				   			
				   			sender.sendMessage(ChatColor.GOLD + "Command" + ChatColor.WHITE + " repair-cost erkannt!");
				   			this.plugin.repairshop.commandrepaircost((Player) sender);
				   		}
				   		
				   		if (args[0].toLowerCase().equalsIgnoreCase("repair")){
   				   			
				   			sender.sendMessage(ChatColor.GOLD + "Command" + ChatColor.WHITE + " repair erkannt!");
				   			this.plugin.repairshop.commandrepair((Player) sender);
				   		}
		
		    	default:
		    		return false;    
			
			}
		}

		
		return false;
	}
	
}
