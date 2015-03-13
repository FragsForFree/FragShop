package com.github.fragsforfree.economy;

import java.util.logging.Level;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.github.fragsforfree.FragShop;
import com.github.fragsforfree.messages.MessageHandler;

public class EconomyHandler {

	private FragShop plugin;
	private Economy economy;
		
	public EconomyHandler(FragShop plugin){
		this.plugin = plugin;
		
		// Setup Vault
		RegisteredServiceProvider<Economy> economyProvider = this.plugin.getServer().getServicesManager().getRegistration(
				Economy.class);
		if (economyProvider != null)
			economy = economyProvider.getProvider();
		else {
			// Disable if no economy plugin was found
			this.plugin.getServer().getLogger().log(Level.SEVERE, "Failed to load an economy plugin. Disabling...");
			this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
			return;
		}
	}
	
	
	public boolean doesPlayerHaveEnough(Player player, double money) {
		return economy.getBalance(player) - money >= 0;
	}

	public String formatCost(double money) {
		return economy.format(money);
	}

	public void withdraw(Player player, double money) {
		if (this.doesPlayerHaveEnough(player, money)){
			economy.withdrawPlayer(player, money);
			MessageHandler.sendPlayerMessage(player, "you pay " + this.formatCost(money), false);
		}
		else
		{
			MessageHandler.sendPlayerMessage(player, "you do not have enought money!", true);
		}
		
	}
}
