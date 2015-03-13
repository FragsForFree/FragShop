package com.github.fragsforfree.repair;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.fragsforfree.FragShop;
import com.github.fragsforfree.messages.MessageHandler;

public class RepairShop {

	private FragShop plugin;
	
	public RepairShop(FragShop plugin) {
		this.plugin = plugin;
	}
		
	public void commandrepair(Player player, boolean showcosts){		
		ItemStack item = player.getItemInHand();
		if (this.isTool(item) || this.isArmor(item)){
			
			int enchantments = item.getEnchantments().size();				
			short repairdurability = this.getdurabilitytorepair(item);
			double price = repairdurability + (enchantments * (repairdurability / 10));
			
			if (showcosts == false) {
				if (plugin.economyhandler.doesPlayerHaveEnough(player, price)){
					plugin.economyhandler.withdraw(player, price);
					player.getItemInHand().setDurability((short) 0);
					this.updateInventory(player);
					MessageHandler.sendPlayerMessage(player, "repaired your item for " + this.plugin.economyhandler.formatCost(price), false);
				}
				else
				{
					MessageHandler.sendPlayerMessage(player, "you do not have enought money!", true);
				}
			}
			else
			{
				MessageHandler.sendPlayerMessage(player, "the repair costs " + this.plugin.economyhandler.formatCost(price), false);
			}
		}
		else
		{
			MessageHandler.sendPlayerMessage(player, "cannot repair your item!", true);
		}		
	}
	
	private short getdurabilitytorepair(ItemStack item){
		short itemdurability = item.getDurability();
		if (itemdurability > (short) 0) {
			return (short) (itemdurability);
		}
		return 0;
	}	
	
	@SuppressWarnings("deprecation")
	private void updateInventory(Player player){
		player.updateInventory();
	}
	
	private boolean isTool(ItemStack item) {
		switch (item.getType()) {
		case WOOD_PICKAXE:
		case WOOD_SPADE:
		case WOOD_HOE:
		case WOOD_SWORD:
		case WOOD_AXE:
		case STONE_PICKAXE:
		case STONE_SPADE:
		case STONE_HOE:
		case STONE_SWORD:
		case STONE_AXE:
		case GOLD_PICKAXE:
		case GOLD_SPADE:
		case GOLD_HOE:
		case GOLD_SWORD:
		case GOLD_AXE:
		case IRON_PICKAXE:
		case IRON_SPADE:
		case IRON_HOE:
		case IRON_SWORD:
		case IRON_AXE:
		case DIAMOND_PICKAXE:
		case DIAMOND_SPADE:
		case DIAMOND_HOE:
		case DIAMOND_SWORD:
		case DIAMOND_AXE:
		case BOW:
		case FLINT_AND_STEEL:
		case FISHING_ROD:
		case SHEARS:
			return true;
		default:
			return false;
		}
	}

	private boolean isArmor(ItemStack item) {
		switch (item.getType()) {
		case LEATHER_HELMET:
		case LEATHER_CHESTPLATE:
		case LEATHER_LEGGINGS:
		case LEATHER_BOOTS:
		case CHAINMAIL_HELMET:
		case CHAINMAIL_CHESTPLATE:
		case CHAINMAIL_LEGGINGS:
		case CHAINMAIL_BOOTS:
		case GOLD_HELMET:
		case GOLD_CHESTPLATE:
		case GOLD_LEGGINGS:
		case GOLD_BOOTS:
		case IRON_HELMET:
		case IRON_CHESTPLATE:
		case IRON_LEGGINGS:
		case IRON_BOOTS:
		case DIAMOND_HELMET:
		case DIAMOND_CHESTPLATE:
		case DIAMOND_LEGGINGS:
		case DIAMOND_BOOTS:
			return true;
		default:
			return false;
		}	
	}
}
