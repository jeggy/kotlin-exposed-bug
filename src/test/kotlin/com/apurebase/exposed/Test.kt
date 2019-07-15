package com.apurebase.exposed

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test {

    object TestTable : Table("delivery.test") {
        val key = varchar("key", 24).primaryKey()
        val value = varchar("value", 50)
    }

    val db = HikariDataSource(
        HikariConfig().also {
            it.username = "test"
            it.password = "test"
            it.jdbcUrl = "jdbc:mysql://localhost:3311"
            it.driverClassName = "org.mariadb.jdbc.Driver"
        }
    )

    @Test
    fun before() {
        // Connect
        Database.connect(db)
        transaction {
            SchemaUtils.drop(TestTable)
            SchemaUtils.create(TestTable)
            TestTable.insert {
                it[key] = "1"
                it[value] = "-2"
            }
            TestTable.insert {
                it[key] = "2"
                it[value] = "1235"
            }
            Unit
        }
        println("I want a fresh table with these 2 values")
    }
}
