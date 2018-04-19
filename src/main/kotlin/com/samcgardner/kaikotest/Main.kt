package com.samcgardner.kaikotest

import java.util.*

fun main(args: Array<String>) {
    with(Scanner(System.`in`)) {
        val linesToRead = nextInt()
        val x = nextInt()
        val y = nextInt()

        this.useDelimiter("\n")

        val finalState = this.iterator()
                .asSequence()
                .take(linesToRead)
                .map { parseCommand(it) }
                .fold(buildInitialState(x, y)) { state, command -> applyMove(state, command) }

        println(finalState.visitedCoOrdinates.size)
    }
}

fun parseCommand(input: String): Command {
    val splitString = input.split(" ".toRegex(), 2)
    return Command(Direction.valueOf(splitString[0]), splitString[1].toInt())
}

fun buildInitialState(x: Int, y: Int): ProgramState {
    val robot = Robot(x, y)
    val initialSet = setOf(Pair(x, y))
    return ProgramState(robot, initialSet)
}

fun applyMove(state: ProgramState, command: Command): ProgramState {
    if (command.steps == 0) {
        return state
    }

    val newState = when (command.direction) {
        Direction.N -> moveAndUpdate(0, 1, state)
        Direction.E -> moveAndUpdate(1, 0, state)
        Direction.S -> moveAndUpdate(0, -1, state)
        Direction.W -> moveAndUpdate(-1, 0, state)
    }

    return applyMove(newState, command.copy(steps = command.steps - 1))
}

fun moveAndUpdate(dx: Int, dy: Int, state: ProgramState): ProgramState {
    val newCoOrdinates = Pair(state.robot.x + dx, state.robot.y + dy)
    val newRobot = Robot(newCoOrdinates)
    val updatedSet = state.visitedCoOrdinates.toMutableSet().apply { this.add(newCoOrdinates) }
    return ProgramState(newRobot, updatedSet)
}
