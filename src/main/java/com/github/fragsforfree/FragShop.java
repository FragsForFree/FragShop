package com.github.fragsforfree;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.fragsforfree.command.EnumCommandhelp; 
import com.github.fragsforfree.command.FragShopCommandExecuter;
import com.github.fragsforfree.economy.EconomyHandler;
import com.github.fragsforfree.config.ConfigurationManager;
import com.github.fragsforfree.messages.MessageHandler;
import com.github.fragsforfree.repair.RepairShop;
import com.github.fragsforfree.shop.Shop;

public class FragShop extends JavaPlugin{

	private RepairShop repairshop;
	public EconomyHandler economyhandler;
	private Shop shop;
	private ConfigurationManager configManager;
	
	/**
	 * standard onEnable method
	 */
    public void onEnable(){
    	this.configManager = new ConfigurationManager(this);
    	this.economyhandler = new EconomyHandler(this);
    	this.repairshop = new RepairShop(this);
    	this.shop = new Shop(this);
    			
		getCommand("fragshop").setExecutor(new FragShopCommandExecuter(this));
    	
    }
    
    /**
     * standard onDisable method
     */
    public void onDisable(){ 
    }    
    
    public double getShopPrice(String materialname){
    	return configManager.getShopPrice(materialname);
    }
    
    public void prepareRepair(Player player, boolean showcosts){
    	if (this.isMaterialInHand(player)){
    		this.repairshop.commandrepair(player, showcosts);
    	}
    	else
    	{
    		MessageHandler.sendPlayerMessage(player, "you need to hold the item in your hand, you like to repair " + player.getItemInHand().getType().toString(), true);
    	}
    }
    
    public void prepareBuyItem(Player player, String item, String value, boolean showcosts){    	    	
    	Material material = Material.getMaterial(item.toUpperCase());
        if (isNumeric(value) && (material != null)) {
        	this.shop.buy(player, material, Short.valueOf(value), showcosts);
    	}
        else
        {
        	if (showcosts == true) {MessageHandler.sendCommandHelp(player, EnumCommandhelp.Cmd_Buy_Item.toString());}
        	if (showcosts == false) {MessageHandler.sendCommandHelp(player, EnumCommandhelp.Cmd_Buy_Item_Cost.toString());}
        }    	    
    }
    
    private boolean isMaterialInHand(Player player){
    	Material material = Material.getMaterial(player.getItemInHand().getType().toString());
    	if (material != null) {return true;}
    	return false;
    }
    
    private static boolean isNumeric(String str)  
    {  
      try  
      {  
        Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }

	public void showMateriallist(Player sender) {
		this.configManager.showMateriallist(sender);
	}
}
