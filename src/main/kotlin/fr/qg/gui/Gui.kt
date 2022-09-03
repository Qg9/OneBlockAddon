package fr.qg.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface Gui {
	
	fun create(player: Player) : InventoryBuilder
	
	fun click(player: Player, slot: Int, item: ItemStack?, type: ClickType, inventory: Inventory) : Boolean
}