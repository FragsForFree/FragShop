package com.github.fragsforfree.command;

public enum EnumCommandhelp {
	TITLE("", "Plugin FragShop v0.2.0 by FragsForFree"),
	Cmd_Help("/fragshop help ", "shows this helppage"),
	Cmd_Repair("/fragshop repair ", "repair your item in hand"),
	Cmd_Repair_Cost("/fragshop repair-cost ", "show you the price to reparing your item in hand"),
	Cmd_Buy_Item("/fragshop buy-item [materialstring] [amount] ", "buy a spezial amount of material"),
	Cmd_Buy_Item_Cost("/fragshop buy-item-cost [materialstring] [amount] ", "show you the price of a spezial amount of material"),
	Cmd_Buy_Item_List("/fragshop buy-item-list ", "show you the material you can buy"),
	Cmd_Set_Item("/fragshop set-item [materialstring] [double]", "sets or adds material with a price"),
	Cmd_Remove_Item("/fragshop remove-item [materialstring] ", "remove material from shop");
		
	private String command;
	private String tip;
	
	EnumCommandhelp(String command, String tip){
		this.command = command;
		this.tip = tip;
	}

	public String getCommand(){
		return this.command;
	}
	
	public String getTip(){
		return this.tip;
	}
}
