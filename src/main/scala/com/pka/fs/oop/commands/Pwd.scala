package com.pka.fs.oop.commands
import com.pka.fs.oop.filesystem.State

class Pwd extends Command {

  def printPath(folders: List[String]): String = {
    if (folders.isEmpty) ""
    else if (folders.tail.isEmpty) s"${folders.head}"
    else s"${folders.head}/${printPath(folders.tail)}"
  }

  override def apply(previousState: State): State = {

    val foldersInPath = previousState.workingDir.getAllFoldersInPath
    if (foldersInPath.isEmpty)
      previousState.setMessageAndReturnThis("/")
    else
      previousState.setMessageAndReturnThis("/" + printPath(foldersInPath))
  }
}
