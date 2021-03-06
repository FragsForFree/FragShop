package com.github.fragsforfree.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.fragsforfree.FragShop;
import com.github.fragsforfree.permission.EnumPermissions;
import com.github.fragsforfree.command.EnumCommandhelp;

public class FragShopCommandExecuter implements CommandExecutor{

	private FragShop plugin;	
	
	public FragShopCommandExecuter(FragShop plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		
		if (sender instanceof Player) {  
			switch(args.length){
			
				case 1:				   							
					
					if(sender.hasPermission(EnumPermissions.FragShopRepair.toString())){
					
						if (args[0].toLowerCase().equalsIgnoreCase("repair-cost")){				   				   			
				   			this.plugin.prepareRepair((Player) sender, true);
				   			return true;
				   		}
				   		
				   		if (args[0].toLowerCase().equalsIgnoreCase("repair")){			   			
				   			this.plugin.prepareRepair((Player) sender, false);
				   			return true;
				   		}
				   		
				   	}
					
					if (sender.hasPermission(EnumPermissions.FragShopShop.toString())){
						
						if (args[0].toLowerCase().equalsIgnoreCase("buy-item-list")){
							this.plugin.showMateriallist((Player) sender);
							return true;
						}
						
					}
					
					this.sendHelp((Player) sender);
					return true;
				
				case 2:
					
					if(sender.hasPermission(EnumPermissions.FragShopAdmin.toString())){
						
						if (args[0].toLowerCase().equalsIgnoreCase("remove-item")){
							this.plugin.deleteShopMaterial((Player) sender, args[1]);;
							return true;
						}
						
					}					
					
				case 3:	
					
					if(sender.hasPermission(EnumPermissions.FragShopShop.toString())){	
					
						if (args[0].toLowerCase().equalsIgnoreCase("buy-item")){																										
							this.plugin.prepareBuyItem((Player) sender, args[1], args[2], false);
							return true;
						}

						if (args[0].toLowerCase().equalsIgnoreCase("buy-item-cost")){																												
							this.plugin.prepareBuyItem((Player) sender, args[1], args[2], true);
							return true;
						}						
						
					}
					
					if(sender.hasPermission(EnumPermissions.FragShopAdmin.toString())){
						
						if (args[0].toLowerCase().equalsIgnoreCase("set-item")){
							this.plugin.addShopMaterial((Player) sender, args[1], args[2]);
							return true;
						}
						
					}
					
					this.sendHelp((Player) sender);
					return true;
					
					
		    	default:
		    		this.sendHelp((Player) sender); 
		    		return true;
			
			}
		}

		
	return false;
	}
	
	private void sendHelp(Player player){
		player.sendMessage(ChatColor.GOLD + EnumCommandhelp.TITLE.getTip());
		if (player.hasPermission(EnumPermissions.FragShopRepair.toString())){
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Repair.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Repair.getTip());
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Repair_Cost.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Repair_Cost.getTip());
		}
		if (player.hasPermission(EnumPermissions.FragShopShop.toString())){
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Buy_Item.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Buy_Item.getTip());
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Buy_Item_Cost.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Buy_Item_Cost.getTip());
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Buy_Item_List.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Buy_Item_List.getTip());
		}
		if (player.hasPermission(EnumPermissions.FragShopAdmin.toString())){
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Set_Item.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Set_Item.getTip());
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.Cmd_Remove_Item.getCommand() + ChatColor.WHITE + EnumCommandhelp.Cmd_Remove_Item.getTip());
		}
	}
	
	public void sendHelp(Player player, String line){
		if (EnumCommandhelp.valueOf(line) != null){		
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.valueOf(line).getCommand() + ChatColor.WHITE + EnumCommandhelp.valueOf(line).getTip());
		}
	}
	
}
