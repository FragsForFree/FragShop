package com.github.fragsforfree.messages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.github.fragsforfree.command.EnumCommandhelp;

public final class MessageHandler {

	private MessageHandler(){
		
	}
	
	public static void sendPlayerMessage(Player player, String Message, boolean Error){
		String chatmessage = ChatColor.GOLD + "[" + ChatColor.GREEN + "FragShop" + ChatColor.GOLD + "] "; 
	    if (Error == true){
	    	chatmessage = chatmessage + ChatColor.RED + "ERROR ";
	    }
	    chatmessage = chatmessage + ChatColor.WHITE + Message;
		player.sendMessage(chatmessage);		
	}
	
	public static void sendCommandHelp(Player player, String line){
		if (EnumCommandhelp.valueOf(line) != null){		
			player.sendMessage(ChatColor.GOLD + EnumCommandhelp.valueOf(line).getCommand() + ChatColor.WHITE + EnumCommandhelp.valueOf(line).getTip());
		}
	}
	
}
