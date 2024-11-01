package com.gleb.lemana.task

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform