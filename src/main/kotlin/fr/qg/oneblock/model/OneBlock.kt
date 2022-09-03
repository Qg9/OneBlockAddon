package fr.qg.oneblock.model

import com.bgsoftware.superiorskyblock.api.island.Island
import org.bukkit.block.Block

data class OneBlock(
	var mined: Int,
	var block: Block,
	var phase: Phase,
) {
}