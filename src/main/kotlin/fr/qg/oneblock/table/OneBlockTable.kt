package fr.qg.oneblock.table

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI
import com.bgsoftware.superiorskyblock.api.island.Island
import fr.qg.oneblock.manager.IslandsManager
import fr.qg.oneblock.model.OneBlock
import fr.qg.oneblock.utils.BlobUtils
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class OneBlockTable(name: String) : Table(name) {
	var id = varchar("id", 36)
	var mined = integer("mined")
	var phase = varchar("phase", 16)
	var block = blob("block")
	
	fun load(manager: IslandsManager) : MutableMap<Island, OneBlock> {
		val map = mutableMapOf<Island, OneBlock>()
		transaction {
			this@OneBlockTable.selectAll().forEach {
				val island = SuperiorSkyblockAPI.getIslandByUUID(UUID.fromString(it[this@OneBlockTable.id]))!!
				val phase = manager.getPhase(it[phase]);
				val oneblock = OneBlock(it[mined],
					BlobUtils.fromByteGetVector(it[block].bytes, island.getCenter(manager.env).world).block, phase);
				map[island] = oneblock
			}
		}
		return map
	}
	
	fun insert(island: Island, oneBlock: OneBlock) = transaction {
		insert {
			it[id] = island.uniqueId.toString()
			it[block] = ExposedBlob(BlobUtils.asVectorToByte(oneBlock.block.location))
			it[mined] = oneBlock.mined
			it[phase] = oneBlock.phase.token
		}
	}
	
	fun remove(island: Island) = transaction {
		deleteWhere { this@OneBlockTable.id eq island.uniqueId.toString() }}
	
	
	fun save(island: Island, oneBlock: OneBlock) = transaction {
		update({ this@OneBlockTable.id eq island.uniqueId.toString() }) {
			it[block] = ExposedBlob(BlobUtils.asVectorToByte(oneBlock.block.location))
			it[mined] = oneBlock.mined
			it[phase] = oneBlock.phase.token
		}
	}
	
	fun create(): OneBlockTable {
		transaction {
			SchemaUtils.create(this@OneBlockTable)
		}
		return this@OneBlockTable
	}
}