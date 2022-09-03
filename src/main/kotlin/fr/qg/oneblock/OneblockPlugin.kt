package fr.qg.oneblock

import fr.qg.oneblock.arguments.PhaseArgument
import fr.qg.oneblock.arguments.StringMapArgument
import fr.qg.oneblock.commands.BlockCommand
import fr.qg.oneblock.commands.PhaseCommand
import fr.qg.oneblock.listeners.BlockListener
import fr.qg.oneblock.listeners.IslandListener
import fr.qg.oneblock.manager.IslandsManager
import fr.qg.oneblock.manager.IslandsManager.*
import fr.qg.oneblock.manager.PhasesManager
import fr.qg.oneblock.model.Phase
import fr.qg.oneblock.placeholder.IslandPlaceholder
import fr.stonks.command.arguments.*
import fr.stonks.command.create
import org.bukkit.World.Environment
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database

class OneblockPlugin : JavaPlugin() {
	
	companion object {
		lateinit var inst: JavaPlugin
	}
	
	override fun onEnable() {
		
		saveDefaultConfig()
		
		inst = this
		Database.connect("jdbc:sqlite:${this.dataFolder}/storage.db", "org.sqlite.JDBC")
		
		PhasesManager; IslandPlaceholder; IslandsManager; IslandPlaceholder
		
		create("QGoneblock")
			.forEveryone("set", PhaseCommand, "Change the oneblock phase", "ob.phase.set",
			PhaseArgument("phase"), EnumArgument<Environment>("type"), PlayerArgument("target"))
			.forPlayers("block", BlockCommand, "Change the block position", "ob.phase.block",
				EnumArgument<Environment>("type")
			)
		
		server.pluginManager.registerEvents(IslandListener, this)
		server.pluginManager.registerEvents(BlockListener, this)
	}
	
}