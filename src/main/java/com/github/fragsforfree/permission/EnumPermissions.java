package com.github.fragsforfree.permission;

public enum EnumPermissions {
	FragShopRepair("fragshop.repair"),
	FragShopShop("fragshop.shop"),
	FragShopAdmin("fragshop.admin")
    ;

    private final String text;

    /**
     * @param text
     */
    private EnumPermissions(final String text) {
        this.text = text;
    }

    /** (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }	
}
