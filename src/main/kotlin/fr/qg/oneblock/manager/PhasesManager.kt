package fr.qg.oneblock.manager

import fr.qg.oneblock.OneblockPlugin
import fr.qg.oneblock.model.Phase
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object PhasesManager {
	
	private const val STORAGE_FILE_NAME = "phases.yml"
	
	val phases = mutableListOf<Phase>()

	var file: File
	var yaml: YamlConfiguration
	
	init {
		OneblockPlugin.inst.saveResource(STORAGE_FILE_NAME, false)
		
		file = File(OneblockPlugin.inst.dataFolder, STORAGE_FILE_NAME)
		yaml = YamlConfiguration.loadConfiguration(file)
		
		yaml.getKeys(false).forEach { key ->
			val content = yaml.getConfigurationSection(key)!!.getKeys(false).filter { it != "title" }.associate {
				val mat = Material.matchMaterial(it)
				val rar = Phase.Rarity.valueOf(yaml.getString("$key.$it")!!)
				return@associate mat!! to rar
			}
			val title = yaml.getString("$key.title", "default")!!
			phases.add(Phase(key, title, content.toMutableMap()))
		}
	}
	
	fun Phase.set(material: Material, rarity: Phase.Rarity?) {
		val section = yaml.getConfigurationSection(this.token) ?: yaml.createSection(this.token)
		section.set(material.name, rarity?.name)
		yaml.save(file)
	}
	
	fun get(token: String) : Phase =
		phases.firstOrNull { token.equals(it.token, true) } ?: IslandsManager.types.values.first().defaultPhase
}