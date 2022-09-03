package fr.qg.oneblock.listeners

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblock
import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI
import fr.qg.oneblock.OneblockPlugin
import fr.qg.oneblock.manager.IslandsManager
import org.bukkit.Bukkit
import org.bukkit.World.Environment.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

object BlockListener : Listener{
	
	@EventHandler
	fun onBreak(event: BlockBreakEvent) {
		
		val player = event.player
		val isplayer = SuperiorSkyblockAPI.getPlayer(player)
		
		val block = event.block
		
		if (isplayer.isInsideIsland){
			event.isCancelled = false
			
			val island = isplayer.island ?: return
			val oneblock = IslandsManager.types[player.world.environment]?.oneblocks?.get(island) ?: return
				
			if (block == oneblock.block) {
				oneblock.mined+=1
				val mat = oneblock.phase.generateNextBlock()
				Bukkit.getScheduler().runTaskLater(
					OneblockPlugin.inst, Runnable { block.type = mat }, 1)
			}
				
		} else {
			event.isCancelled = true
		}
	}
}