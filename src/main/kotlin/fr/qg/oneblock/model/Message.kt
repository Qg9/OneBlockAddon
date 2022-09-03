package fr.qg.oneblock.model

import fr.qg.oneblock.OneblockPlugin

enum class Message(val default: String) {
	ERROR_NO_ISLAND("§cTu n'as pas d'ile !"),
	ERROR_NO_BLOCK_TARGETED("§cTu dois viser un block !"),
	ERROR_TYPE_DOES_NOT_EXIST("§cCe type d'ile n'existe pas !"),
	
	CHANGE_ONEBLOCK_POSITION("§7Tu as la position de ton OneBlock !"),
	CHANGE_ONEBLOCK_PHASE("§7Tu as changé la phase de l'ile du joueur : %target% !");
	
	fun get(vararg placeholders:Pair<String, String> = arrayOf()) : String {
		var result = OneblockPlugin.inst.config.getString("Messages.$name", default)!!
		if (placeholders.isNotEmpty())placeholders.forEach { result = result.replace(it.first, it.second) }
		return result
	}
}