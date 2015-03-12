package com.github.fragsforfree;

import java.util.logging.Level;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import com.github.fragsforfree.command.FragShopCommandExecuter;
import com.github.fragsforfree.economy.EconomyHandler;
import com.github.fragsforfree.repair.RepairShop;

public class FragShop extends JavaPlugin{

	public RepairShop repairshop;
	public EconomyHandler economyhandler;
	
	/**
	 * standard onEnable method
	 */
    public void onEnable(){    
    	this.economyhandler = new EconomyHandler(this);
    	this.repairshop = new RepairShop(this);    	
    			
		getCommand("fragshop").setExecutor(new FragShopCommandExecuter(this));
    	
    }
    
    /**
     * standard onDisable method
     */
    public void onDisable(){ 
    }
}
