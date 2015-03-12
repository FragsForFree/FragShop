package com.github.fragsforfree.economy;

import java.util.logging.Level;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.github.fragsforfree.FragShop;

public class EconomyHandler {

	private FragShop plugin;
	private Economy economy;
		
	public EconomyHandler(FragShop plugin){
		this.plugin = plugin;
		
		// Setup Vault
		RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(
				Economy.class);
		if (economyProvider != null)
			economy = economyProvider.getProvider();
		else {
			// Disable if no economy plugin was found
			plugin.getServer().getLogger().log(Level.SEVERE, "Failed to load an economy plugin. Disabling...");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
			return;
		}
	}
	
	
	public boolean doesPlayerHaveEnough(Player player, short money) {
		return economy.getBalance(player.getName()) - money >= 0;
	}

	public String formatCost(short money) {
		return economy.format(money);
	}

	public void withdraw(Player player, short money) {
		economy.withdrawPlayer(player.getName(), money);
	}
}
