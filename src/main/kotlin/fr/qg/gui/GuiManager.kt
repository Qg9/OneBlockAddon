package fr.qg.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

object GuiManager {

	val guis: MutableMap<Inventory, Gui> = mutableMapOf()

	fun load(plugin: JavaPlugin) {
		plugin.server.pluginManager.registerEvents(GuiListener, plugin)
	}
	
	fun Gui.blank(lines: Int = 3, name: String = "") : InventoryBuilder =
		InventoryBuilder(lines, name)
	
	fun Gui.open(player: Player) {
		val inv = this.create(player).convert()
		player.openInventory(inv)
		guis[inv] = this
	}
}