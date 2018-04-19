package com.samcgardner.kaikotest

data class Command(val direction : Direction, val steps : Int)

enum class Direction{ N, E, S, W }