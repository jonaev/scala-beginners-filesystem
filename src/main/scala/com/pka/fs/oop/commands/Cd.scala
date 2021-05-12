package com.pka.fs.oop.commands
import com.pka.fs.oop.files.{DirEntry, Directory}
import com.pka.fs.oop.filesystem.State

//TODO implement chained paths
class Cd(name: String) extends Command {

  private def findNewWD(workingDirectory: Directory): Option[DirEntry] = {
//    workingDirectory.contents.foreach(print(_))
    workingDirectory.contents.filter(_.isDirectory).find(dir => dir.name.equals(name))
  }

  override def apply(previousState: State): State = {
    name match {
      case "/" => State(previousState.root, previousState.root)
      case "." => previousState
      case ".." =>
        val previousWD = previousState.workingDir
        val newWD = previousWD.navigateTo(previousWD.getAllFoldersInPath.tail)
        State(previousState.root, newWD)
      case _ =>
        val optionalWD = findNewWD(previousState.workingDir)
        if (optionalWD.isEmpty)
          previousState.setMessageAndReturnThis("Unknown directory")
        else
          State(previousState.root, optionalWD.get.asDirectory)
    }

  }
}
