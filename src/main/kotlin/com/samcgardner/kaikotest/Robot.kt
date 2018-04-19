package com.samcgardner.kaikotest

data class Robot(val x : Int, val y : Int) {
    constructor(pair : Pair<Int, Int>) : this(pair.first, pair.second)
}