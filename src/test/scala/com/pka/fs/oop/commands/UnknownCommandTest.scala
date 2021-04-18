package com.pka.fs.oop.commands

import com.pka.fs.oop.files.Directory
import com.pka.fs.oop.filesystem.State
import org.scalatest.funsuite.AnyFunSuite

class UnknownCommandTest extends AnyFunSuite {

  test("UnknownCommand.apply") {
    val command = new UnknownCommand
    val root = Directory.ROOT
    val state = State(root, root)
    val testState = command.apply(state)
    assert(testState.root == state.root)
    assert(testState.workingDir == state.workingDir)
    assert(testState.previousOutput == "Command not found...")
  }

}
