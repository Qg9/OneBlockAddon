package fr.qg.oneblock.commands

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI
import fr.qg.oneblock.manager.IslandsManager
import fr.qg.oneblock.model.Message
import fr.qg.oneblock.model.Phase
import fr.stonks.command.BiCommandAction
import fr.stonks.command.PlayerCommandAction
import fr.stonks.command.result.CommandResult
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object PhaseCommand : BiCommandAction {
	
	override fun execute(sender: CommandSender, result: CommandResult): String {
		val target = result.get<Player>("target")
		val type = IslandsManager.types[result.get("type")] ?: return Message.ERROR_TYPE_DOES_NOT_EXIST.get()
		val phase = result.get<Phase>("phase")
		val island = SuperiorSkyblockAPI.getPlayer(target)?.island
		
		val oneblock = type.oneblocks[island ?: return Message.ERROR_NO_ISLAND.get()] ?: return Message.ERROR_NO_ISLAND.get()
		
		oneblock.phase = phase
		
		type.table.save(island, oneblock)
		
		return Message.CHANGE_ONEBLOCK_PHASE.get("%player%" to target.name)
	}
}
