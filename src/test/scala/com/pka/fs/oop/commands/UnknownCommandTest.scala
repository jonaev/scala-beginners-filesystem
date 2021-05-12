package com.pka.fs.oop.commands

import com.pka.fs.oop.files.Directory
import com.pka.fs.oop.filesystem.State
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class UnknownCommandTest extends CommandsSuite {

  test("Unknown command shouldn't change current state") {
    val command = new UnknownCommand
    val root = Directory.ROOT
    val state = State(root, root)
    val testState = command.apply(state)
    testState.root shouldBe state.root
    testState.workingDir shouldBe state.workingDir
    testState.previousOutput shouldBe "Command not found..."
//    assert(testState.root == state.root)
//    assert(testState.workingDir == state.workingDir)
//    assert(testState.previousOutput == "Command not found...")

  }
  test("test") {
    "hello world" should include ("hell")
//    "hello world".should(include)("hell")
//    "hello world" shouldNot include "hi"
    "hello world".should(not).include("hi")
  }

  ignore("test ignore") (true)

  test("test pending") {
    println("testing...")
    pending //anything after pending won't be executed!
  }

  ignore("test ignore and pending") {
    println("testing ignore...")
    pending
    throw new Exception
  }

}
