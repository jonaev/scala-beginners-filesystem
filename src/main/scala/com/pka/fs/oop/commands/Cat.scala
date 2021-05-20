package com.pka.fs.oop.commands

import com.pka.fs.oop.filesystem.State

class Cat(name: String) extends Command {
  override def apply(previousState: State): State = ???
}
