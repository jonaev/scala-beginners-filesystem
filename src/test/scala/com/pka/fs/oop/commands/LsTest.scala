package com.pka.fs.oop.commands

import com.pka.fs.oop.files.{Directory, File}
import com.pka.fs.oop.filesystem.State
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class LsTest extends AnyFlatSpec with should.Matchers {

  "State " must "not be changed during method invocation" in {
    val command = new Ls
    val state = getState
    val testState = command.apply(state)

    testState.root shouldBe state.root
    testState.workingDir shouldBe state.workingDir

  }

  it should "print content of the current working directory" in {
    val command = new Ls
    val state = getState
    val testState = command.apply(state)

    testState.previousOutput shouldBe "a [Directory] testFile [File] "
  }

  it should "print directory tree of current working directory content when parameter is provided" in {
    pending
  }

  def getState: State = {
    val c = Directory.empty("/a/b", "c")
    val b = new Directory("/a", "b", List(c))
    val a = new Directory("/", "a", List(b))
    val aFile = File.empty("/", "testFile")
    val root = new Directory("", "", List(a, aFile))
    State(root, root)
  }
}
