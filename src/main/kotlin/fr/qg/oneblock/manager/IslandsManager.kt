package fr.qg.oneblock.manager

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI
import com.bgsoftware.superiorskyblock.api.island.Island
import fr.qg.oneblock.OneblockPlugin
import fr.qg.oneblock.model.OneBlock
import fr.qg.oneblock.model.Phase
import fr.qg.oneblock.table.OneBlockTable
import kotlinx.coroutines.runBlocking
import org.bukkit.World.Environment
import org.bukkit.World.Environment.*

class IslandsManager(val env: Environment) {
	
	companion object {
		val types: MutableMap<Environment, IslandsManager> = mutableMapOf()
		
		init {
			if(SuperiorSkyblockAPI.getProviders().worldsProvider.isNormalEnabled) types[NORMAL] = IslandsManager(NORMAL)
			if(SuperiorSkyblockAPI.getProviders().worldsProvider.isNetherEnabled) types[NETHER] = IslandsManager(NETHER)
			if(SuperiorSkyblockAPI.getProviders().worldsProvider.isEndEnabled) types[THE_END] = IslandsManager(THE_END)
		}
		
	}
	
	val name get() = env.name

	val table = OneBlockTable(this.name).create()
	val oneblocks = table.load(this@IslandsManager)
	
	var defaultPhase: Phase = PhasesManager.phases.firstOrNull {
		it.token.equals(OneblockPlugin.inst.config.getString("Config.default.${this.name}")!!, true)
	} ?: Phase("", "", mutableMapOf())
	
	fun create(island: Island) {
		val oneblock = OneBlock(0, island.getCenter(env).block, defaultPhase)
		oneblocks[island] = oneblock
		table.insert(island, oneblock)
	}

	fun delete(island: Island) = runBlocking {
		table.remove(island)
		oneblocks.remove(island)
	}
	
	fun getPhase(token: String) : Phase = PhasesManager.get(token)
}