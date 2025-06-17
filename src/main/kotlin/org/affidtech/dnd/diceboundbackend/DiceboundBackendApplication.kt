package org.affidtech.dnd.diceboundbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiceboundBackendApplication

fun main(args: Array<String>) {
	runApplication<DiceboundBackendApplication>(*args)
}
