package fr.qg.gui

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

object GuiListener : Listener {
	
	@EventHandler fun onClick(event: InventoryClickEvent){
		
		val inventory = event.clickedInventory ?: return
		
		if (GuiManager.guis.containsKey(inventory)) event.isCancelled = GuiManager.guis[inventory]!!
			.click(event.whoClicked as Player, event.slot, event.currentItem, event.click, inventory)
	}
	
	@EventHandler fun onClose(event: InventoryCloseEvent) {
		GuiManager.guis.remove(event.inventory)
	}
	
}
