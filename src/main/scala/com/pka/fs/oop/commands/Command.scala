package com.pka.fs.oop.commands

import com.pka.fs.oop.filesystem.State

trait Command {

  def apply(previousState: State): State

}

object Command {
  def from(input: String): Command =
    new UnknownCommand
}