package fr.qg.oneblock.listeners

import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent
import com.bgsoftware.superiorskyblock.api.events.IslandDisbandEvent
import fr.qg.oneblock.manager.IslandsManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object IslandListener : Listener {

	@EventHandler fun onCreate(event: IslandCreateEvent) {
		IslandsManager.types.values.forEach {
			it.create(event.island)
		}
	}
	
	@EventHandler fun onDelete(event: IslandDisbandEvent) {
		IslandsManager.types.values.forEach {
			it.delete(event.island)
		}
	}
}