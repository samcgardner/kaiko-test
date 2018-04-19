package com.samcgardner.kaikotest

import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun parsesWellFormedCommand() {
        val parsedCommand = parseCommand("E 2")
        assertEquals(Direction.E, parsedCommand.direction)
        assertEquals(2, parsedCommand.steps)
    }

    @Test
    fun buildsInitialState() {
        val state = buildInitialState(0 ,0)
        assertEquals(Robot(0, 0), state.robot)
        assertEquals(setOf(Pair(0, 0)), state.visitedCoOrdinates)
    }

    @Test
    fun doesNotChangeZeroSteps() {
        val state = buildInitialState(0 ,0)
        val command = Command(Direction.N, 0)

        assertEquals(state, applyMove(state, command))
    }

    @Test
    fun movesNorth() {
        val state = buildInitialState(0 ,0)
        val command = Command(Direction.N, 2)

        val expectedRobot = Robot(0, 2)
        val expectedVisited = setOf(Pair(0, 0), Pair(0, 1), Pair(0, 2))

        assertEquals(expectedRobot, applyMove(state, command).robot)
        assertEquals(expectedVisited, applyMove(state, command).visitedCoOrdinates)
    }

    @Test
    fun movesEast() {
        val state = buildInitialState(0 ,0)
        val command = Command(Direction.E, 2)

        val expectedRobot = Robot(2, 0)
        val expectedVisited = setOf(Pair(0, 0), Pair(1, 0), Pair(2, 0))

        assertEquals(expectedRobot, applyMove(state, command).robot)
        assertEquals(expectedVisited, applyMove(state, command).visitedCoOrdinates)
    }

    @Test
    fun movesSouth() {
        val state = buildInitialState(0 ,0)
        val command = Command(Direction.S, 2)

        val expectedRobot = Robot(0, -2)
        val expectedVisited = setOf(Pair(0, 0), Pair(0, -1), Pair(0, -2))

        assertEquals(expectedRobot, applyMove(state, command).robot)
        assertEquals(expectedVisited, applyMove(state, command).visitedCoOrdinates)
    }

    @Test
    fun movesWest() {
        val state = buildInitialState(0 ,0)
        val command = Command(Direction.W, 2)

        val expectedRobot = Robot(-2, 0)
        val expectedVisited = setOf(Pair(0, 0), Pair(-1, 0), Pair(-2, 0))

        assertEquals(expectedRobot, applyMove(state, command).robot)
        assertEquals(expectedVisited, applyMove(state, command).visitedCoOrdinates)
    }

}