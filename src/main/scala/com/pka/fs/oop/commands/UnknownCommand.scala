package com.pka.fs.oop.commands
import com.pka.fs.oop.filesystem.State

class UnknownCommand extends Command {
  override def apply(previousState: State): State =
    previousState.setMessageAndReturnThis("Command not found...")
}
//fsdf