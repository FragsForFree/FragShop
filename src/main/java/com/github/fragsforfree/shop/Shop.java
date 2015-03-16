package com.github.fragsforfree.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.fragsforfree.FragShop;
import com.github.fragsforfree.messages.MessageHandler;

public class Shop {

	private FragShop plugin;
	
	public Shop(FragShop plugin){
		this.plugin = plugin;
	}
	
	public void buy(Player player, Material material, int amount, boolean showcosts){
		
		if(player.getInventory().firstEmpty() > 0){		
			ItemStack item = new ItemStack(material);
			int maxstacksize = item.getMaxStackSize();
			if (amount > maxstacksize){
				amount = maxstacksize;
				MessageHandler.sendPlayerMessage(player, "your amount is set to the maxstacksize of " + maxstacksize, true);
			}						
			double price = (double) (amount * plugin.getShopPrice(material.name()));
			
			if (price != 0){
			
				if (showcosts == false){			
					if (plugin.economyhandler.doesPlayerHaveEnough(player, price)){
						plugin.economyhandler.withdraw(player, price);
						item.setAmount(amount);			
						player.getInventory().addItem(item);										
					}
					else
					{
						MessageHandler.sendPlayerMessage(player, "you do not have enought money!", true);
					}
				}
				else
				{
					MessageHandler.sendPlayerMessage(player, "to buy " + amount + " of " + material.name().toUpperCase() + " costs " + this.plugin.economyhandler.formatCost(price), false);
				}
				
			}
			else
			{
				MessageHandler.sendPlayerMessage(player, "the material " + material.name().toUpperCase() + " is not on the selling list!", true);
			}
		}
		else
		{
			MessageHandler.sendPlayerMessage(player, "no free inventory slots left, make more space for transaction", true);		
		}
	}
	
}
