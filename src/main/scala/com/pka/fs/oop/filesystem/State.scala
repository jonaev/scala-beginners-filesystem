package com.pka.fs.oop.filesystem

import com.pka.fs.oop.files.Directory

class State(val root: Directory, val workingDir: Directory, val previousOutput: String) {

  def show = {
    println(previousOutput)
    print(State.COMMAND_PROMPT)
  }

  def setMessageAndReturnThis(message: String): State =
    State.apply(root, workingDir, message)
}

object State {
  val COMMAND_PROMPT = "$ "

  //default value of output
  def apply(root: Directory, workingDir: Directory, output: String = ""): State =
    new State(root, workingDir, output)
}