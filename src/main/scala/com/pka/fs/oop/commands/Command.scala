package com.pka.fs.oop.commands

import com.pka.fs.oop.filesystem.State

trait Command {

  def apply(previousState: State): State

}

object Command {
  val MKDIR = "mkdir"
  val LS = "ls"
  val CD = "cd"
  val PWD = "pwd"
  val TOUCH = "touch"

  def emptyCommand: Command = (previousState: State) => previousState

  def incompleteCommand(name: String): Command = (previousState: State) => previousState.setMessageAndReturnThis(s"$name what?")

  def from(input: String): Command = {
    val tokens = input.split(" ")

    if (tokens.isEmpty || input.isEmpty) emptyCommand
    tokens(0) match {
      case MKDIR =>
        if (tokens.length != 2) incompleteCommand(tokens(0))
              else new Mkdir(tokens(1))
      case LS => new Ls
      case CD => new Cd(tokens(1))
      case PWD => new Pwd
      case TOUCH => new Touch(tokens(1))
      case _ => new UnknownCommand
    }
  }
}