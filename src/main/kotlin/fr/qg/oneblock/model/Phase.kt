package fr.qg.oneblock.model

import org.bukkit.Material
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.asKotlinRandom

data class Phase(val token: String,
                 val title: String,
                 val materials: MutableMap<Material, Rarity>,
                 var result: MutableList<Material>) {
	
	constructor(token: String, title: String, materials: MutableMap<Material, Rarity>) : this(token, title, materials, generateResult(materials))
	
	fun generateNextBlock() : Material = result.random(ThreadLocalRandom.current().asKotlinRandom())
	
	fun convert() {
		result = generateResult(materials)
	}
	
	companion object {
		private fun generateResult(materials: MutableMap<Material, Rarity>): MutableList<Material> {
			val result = mutableListOf<Material>()
			
			for((material, rarity) in materials)
				for(i in 1..rarity.power)result.add(material)
			
			return result
		}
	}
	
	enum class Rarity(val title: String, val power: Int) {
		
		COMMON("§7§lCOMMUN", 18),
		RARE("§a§lRARE", 9),
		VERY_RARE("§c§lVRAIMENT RARE", 3),
		VERY_VERY_RARE("§6§lEXTREMEMENT RARE", 1);
	}
}