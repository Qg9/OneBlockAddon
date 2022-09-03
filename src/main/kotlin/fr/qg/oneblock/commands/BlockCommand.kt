package fr.qg.oneblock.commands

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI
import com.destroystokyo.paper.block.TargetBlockInfo.FluidMode.NEVER
import fr.qg.oneblock.manager.IslandsManager
import fr.qg.oneblock.model.Message
import fr.stonks.command.PlayerCommandAction
import fr.stonks.command.result.CommandResult
import org.bukkit.World.Environment
import org.bukkit.entity.Player

object BlockCommand : PlayerCommandAction {
	
	override fun execute(player: Player, result: CommandResult): String {
		
		val type = IslandsManager.types[result.get("type")] ?: return Message.ERROR_TYPE_DOES_NOT_EXIST.get()
		val island = SuperiorSkyblockAPI.getPlayer(player)?.island
		val oneblock = type.oneblocks[island ?: return Message.ERROR_NO_ISLAND.get()] ?:
			return Message.ERROR_NO_ISLAND.get()
		
		oneblock.block = player.getTargetBlock(10, NEVER) ?: return Message.ERROR_NO_BLOCK_TARGETED.get()
		type.table.save(island, oneblock)
		return Message.CHANGE_ONEBLOCK_POSITION.get()
	}
	
}
