package fr.qg.oneblock.arguments

import fr.qg.oneblock.manager.PhasesManager
import fr.qg.oneblock.model.Phase
import fr.stonks.command.arguments.StrictArgument

class PhaseArgument(name: String) : StrictArgument<Phase>(name, true) {
	override fun convert(arg: String): Phase = PhasesManager.get(arg)
	override fun possibilities(): List<String> = PhasesManager.phases.map { it.token }
}