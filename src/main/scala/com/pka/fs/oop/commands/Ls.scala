package com.pka.fs.oop.commands
import com.pka.fs.oop.files.DirEntry
import com.pka.fs.oop.filesystem.State

class Ls extends Command {

  //TODO add option to print whole tree
  def printContent(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else s"${contents.head.name} [${contents.head.getType}] ${printContent(contents.tail)}"
  }

  override def apply(previousState: State): State = {
    val contents = previousState.workingDir.contents
    val niceOutput = printContent(contents)

    previousState.setMessageAndReturnThis(niceOutput)

  }
}
