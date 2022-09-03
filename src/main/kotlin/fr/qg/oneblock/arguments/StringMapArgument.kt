package fr.qg.oneblock.arguments

import fr.stonks.command.arguments.StrictArgument

class StringMapArgument<V>(name: String, val t: Map<String, V>) : StrictArgument<V>(name, true) {
	override fun convert(arg: String): V = t[arg]!!
	override fun possibilities(): List<String> = t.keys.toList()
}