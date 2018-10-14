package com.ddubson

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate


@SpringBootApplication
@Configuration
class App(val jdbcTemplate: JdbcTemplate) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val query = "SELECT id, title FROM movies"

        println("Querying...")
        jdbcTemplate.query(query) { rs, _ ->
            Movie(rs.getInt("id"), rs.getString("title"))
        }.forEach { movie ->
            println("Movie: ${movie.id} - ${movie.title}")
        }
    }
}

data class Movie(val id: Int, val title: String)

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}