package com.github.fragsforfree.config;

public enum EnumConfig {
	PLUGIN_CONFIGVERSION("Plugin.Configversion", 1),
	PLUGIN_DEBUG("Plugin.Debug", false),
	PLUGIN_PATH_DATA("//Data"),
	ADMIN_SHOP_ITEM_DIRT("Admin.Shop.Items.Dirt", 0.5),
	ADMIN_SHOP_ITEM_GLOWSTONE("Admin.Shop.Items.Glowstone", 5.0),
	ADMIN_SHOP_ITEM_GOLD_INGOT("Admin.Shop.Items.Gold_Ingot", 28.0),
	ADMIN_SHOP_ITEM_OBSIDIAN("Admin.Shop.Items.Obsidian", 4.0),
	ADMIN_SHOP_ITEM_DIAMOND("Admin.Shop.Items.Diamond", 600.0),
	AMDIN_SHOP_ITEM_EMERALD("Admin.Shop.Items.Emerald", 1000.0);
	
	private String path;
	private int intdef;	
	private boolean booldef;
	private String stringdef;
	private double doubledef;
	
	EnumConfig(String path, int intdef){
		this.path = path;
		this.intdef = intdef;
	}

	EnumConfig(String path, double doubledef){
		this.path = path;
		this.doubledef = doubledef;
	}	
	
	EnumConfig(String path, boolean booldef){
		this.path = path;
		this.booldef = booldef;
	}	
	
	EnumConfig(String path, String stringdef){
		this.path = path;
		this.stringdef = stringdef;
	}

	EnumConfig(String path){
		this.path = path;
	}	
	
	public String getPath(){
		return this.path;
	}
	
	public int getInt(){
		return this.intdef;
	}
	
	public boolean getBoolean(){
		return this.booldef;
	}	
	
	public String getString(){
		return this.stringdef;
	}
	
	public double getDouble(){
		return this.doubledef;
	}
}
