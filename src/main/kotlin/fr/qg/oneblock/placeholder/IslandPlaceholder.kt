package fr.qg.oneblock.placeholder

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI
import fr.qg.oneblock.OneblockPlugin
import fr.qg.oneblock.manager.IslandsManager
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer
import org.bukkit.World.Environment

object IslandPlaceholder : PlaceholderExpansion() {

	init {
		this.register()
	}
	
	override fun getIdentifier(): String = "oneblock"
	override fun getAuthor(): String  = OneblockPlugin.inst.description.authors.reduce { acc, s -> "$acc, $s" }
	override fun getVersion(): String = OneblockPlugin.inst.description.version
	
	override fun onRequest(player: OfflinePlayer?, params: String): String? {
		
		val args = params.split(".")
		var env = Environment.NORMAL
		try {
			env = Environment.valueOf(args[0].uppercase())
		} catch (e: Exception) { }
		
		val oneblock = IslandsManager.types[env] ?: return "error"
		val island = SuperiorSkyblockAPI.getPlayer(player!!.uniqueId).island ?: "error"
		
		return when(args[1]) {
			"mined" -> "${oneblock.oneblocks[island]!!.mined}"
			"phase" -> oneblock.oneblocks[island]!!.phase.token
			"phasetoken" -> oneblock.oneblocks[island]!!.phase.title
			else -> "error"
		}
	}
}
