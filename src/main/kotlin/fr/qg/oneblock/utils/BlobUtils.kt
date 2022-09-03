package fr.qg.oneblock.utils

import org.bukkit.*
import java.nio.ByteBuffer

object BlobUtils {
	
	fun asVectorToByte(location: Location): ByteArray {
		val buffer = ByteBuffer.allocate(4*3)
		buffer.putInt(location.blockX)
		buffer.putInt(location.blockY)
		buffer.putInt(location.blockZ)
		return buffer.array()
	}
	
	fun fromByteGetVector(array: ByteArray, world: World): Location {
		val buffer = ByteBuffer.wrap(array)
		return Location(world, buffer.int.toDouble(), buffer.int.toDouble(), buffer.int.toDouble())
	}

}