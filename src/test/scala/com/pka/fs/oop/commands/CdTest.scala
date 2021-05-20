package com.pka.fs.oop.commands

import com.pka.fs.oop.files.Directory
import com.pka.fs.oop.filesystem.State
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class CdTest extends AnyFlatSpec with should.Matchers {
  it should "not change state when provide ." in {
    val command = new Cd(".")
    val state = getState
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe state.workingDir
    testState.previousOutput shouldBe ""
  }

  it should "go to root when provide /" in {
    val command = new Cd("/")
    val state = getState
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe state.root
    testState.previousOutput shouldBe ""
  }

  it should "go to parent directory when provide .." in {
    val command = new Cd("..")
    val c = Directory.empty("/a/b", "c")
    val b = new Directory("/a", "b", List(c))
    val a = new Directory("/", "a", List(b))
    val root = new Directory("", "", List(a))
    val state = State(root, c)
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe b
    testState.previousOutput shouldBe ""
  }

  it should "go to child directory when provide directory name that exists" in {
    val command = new Cd("c")
    val c = Directory.empty("/a/b", "c")
    val b = new Directory("/a", "b", List(c))
    val a = new Directory("/", "a", List(b))
    val root = new Directory("", "", List(a))
    val state = State(root, b)
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe c
    testState.previousOutput shouldBe ""
  }

  it should "not change state and output an error when provide directory name that doesn't exist" in {
    val command = new Cd("d")
    val c = Directory.empty("/a/b", "c")
    val b = new Directory("/a", "b", List(c))
    val a = new Directory("/", "a", List(b))
    val root = new Directory("", "", List(a))
    val state = State(root, b)
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe state.workingDir
    testState.previousOutput shouldBe "Unknown directory"
  }

  def getState: State = {
    val c = Directory.empty("/a/b", "c")
    val b = new Directory("/a", "b", List(c))
    val a = new Directory("/", "a", List(b))
    val root = new Directory("", "", List(a))
    State(root, b)
  }
}
