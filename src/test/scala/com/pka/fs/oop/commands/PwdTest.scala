package com.pka.fs.oop.commands

import com.pka.fs.oop.files.Directory
import com.pka.fs.oop.filesystem.State

class PwdTest extends CommandsSuite {

  test("pwd command should print /a/b/c") {
    val c = Directory.empty("/a/b", "c")
    val b = new Directory("/a", "b", List(c))
    val a = new Directory("/", "a", List(b))
    val root = new Directory("", "", List(a))
    val command = new Pwd
    val state = State(root, c)
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe state.workingDir
    testState.previousOutput.shouldBe("/a/b/c")
  }
}
