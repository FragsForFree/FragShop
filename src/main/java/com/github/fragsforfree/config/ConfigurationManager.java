package com.github.fragsforfree.config;

import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.github.fragsforfree.FragShop;
import com.github.fragsforfree.messages.MessageHandler;

public class ConfigurationManager {

	private final FragShop plugin;
	
	public ConfigurationManager(FragShop plugin){
		this.plugin = plugin;
		this.initialiseConfig();
	}
	
    /**
     * initialising the main config object, sets with default values and check
     * if there is a new version of the configfile 
     */
    private void initialiseConfig(){
    	FileConfiguration config = plugin.getConfig(); 
    	config.addDefault(EnumConfig.PLUGIN_CONFIGVERSION.getPath(), EnumConfig.PLUGIN_CONFIGVERSION.getInt());
    	config.addDefault(EnumConfig.PLUGIN_DEBUG.getPath(), EnumConfig.PLUGIN_DEBUG.getBoolean());
    	config.addDefault(EnumConfig.ADMIN_SHOP_ITEM_DIRT.getPath(), EnumConfig.ADMIN_SHOP_ITEM_DIRT.getDouble());
    	config.addDefault(EnumConfig.ADMIN_SHOP_ITEM_GLOWSTONE.getPath(), EnumConfig.ADMIN_SHOP_ITEM_GLOWSTONE.getDouble());
    	config.addDefault(EnumConfig.ADMIN_SHOP_ITEM_GOLD_INGOT.getPath(), EnumConfig.ADMIN_SHOP_ITEM_GOLD_INGOT.getDouble());
    	config.addDefault(EnumConfig.ADMIN_SHOP_ITEM_DIAMOND.getPath(), EnumConfig.ADMIN_SHOP_ITEM_DIAMOND.getDouble());
    	config.addDefault(EnumConfig.AMDIN_SHOP_ITEM_EMERALD.getPath(), EnumConfig.AMDIN_SHOP_ITEM_EMERALD.getDouble());
    	config.options().copyDefaults(true); 
    	plugin.saveConfig();
    	
    	if(config.getInt(EnumConfig.PLUGIN_CONFIGVERSION.getPath()) != EnumConfig.PLUGIN_CONFIGVERSION.getInt()){
    		MessageHandler.sendConsole(plugin, Level.WARNING, "Invalid Configfile: "
    				+ "The configversion has changed, please rename old configfile. Check the new config.xml after server restart.");
    	}
    	
    }
    
    public double getShopPrice(String materialname){
    	String key = getKey(materialname);
    	if (key != null) { 
    		return plugin.getConfig().getDouble(key);
    	}
    	else
    	{
    		return 0;
    	}

    }
    
    private String getKey(String materialname){
    	
    	Set<String> keys = plugin.getConfig().getKeys(true);
    	for (String key: keys){
    		    		
    		if (key.startsWith("Admin.Shop.Items.")) {    		    			
    			String itemtosell = key.replace("Admin.Shop.Items.", "");
    			if (itemtosell.equalsIgnoreCase(materialname)) { return key;}    			   			
    		}
    	}
    	return null;
    }

	public void showMateriallist(Player player) {
    	String message = "Items to buy: ";
		Set<String> keys = plugin.getConfig().getKeys(true);
    	for (String key: keys){
    		    		
    		if (key.startsWith("Admin.Shop.Items.")) {    		    			
    			String itemfound = key.replace("Admin.Shop.Items.",  "").toUpperCase();
    			message = message + itemfound + ", ";
    		}
    	}
    	MessageHandler.sendPlayerMessage(player, message, false);
		
	}
	
	public void addShopMaterial(Player player, Material material, double price){
		if (!plugin.getConfig().contains("Admin.Shop.Items." + material.name().toUpperCase())){
			plugin.getConfig().createSection("Admin.Shop.Items." + material.name().toUpperCase());
		}
		plugin.getConfig().set("Admin.Shop.Items." + material.name().toUpperCase(), price);
		plugin.saveConfig();
		MessageHandler.sendPlayerMessage(player, "the material is now buyable with " + this.plugin.economyhandler.formatCost(price), false);
	}
	
	public void deleteShopMaterial(Player player, Material material){
		if (!plugin.getConfig().contains("Admin.Shop.Items." + material.name().toUpperCase())){
			MessageHandler.sendPlayerMessage(player, "the material is already not in shopping list.", true);
		}
		else
		{
			plugin.getConfig().set("Admin.Shop.Items." + material.name().toUpperCase(), null);
			plugin.saveConfig();
			MessageHandler.sendPlayerMessage(player, "delete the material from shopping list.", false);			
		}

	}
	
}
