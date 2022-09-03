package fr.qg.gui

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class InventoryBuilder(private val inventory: Inventory) {
	
	constructor(size: Int, title: String) : this(Bukkit.createInventory(null, size*9, title))
	
	fun add(vararg items: ItemStack) : InventoryBuilder {
		inventory.addItem(*items)
		return this
	}
	
	fun add(items: List<ItemStack>) : InventoryBuilder {
		return add(*items.toTypedArray())
	}
	
	fun set(item: ItemStack, slot: Int): InventoryBuilder {
		inventory.setItem(slot, item)
		return this
	}
	
	fun convert() : Inventory = inventory
}