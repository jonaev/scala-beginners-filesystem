package com.pka.fs.oop.commands
import com.pka.fs.oop.files.{DirEntry, Directory, File}
import com.pka.fs.oop.filesystem.State

class Touch(name: String) extends Command {

  private def checkIllegal(name: String): Boolean = {
    name.contains(".") || name.contains(Directory.SEPARATOR)
  }

  def doTouch(previousState: State, name: String): State = {

    val workingDir = previousState.workingDir
    //1. all directories in the full path
    val allDirsInPath = workingDir.getAllFoldersInPath
    //2. create new file entry in working dir
    val newFile = File.empty(workingDir.path, name)
    //3. update the whole directory structure starting from the root
    val newRoot = State.updateStructure(previousState.root, allDirsInPath, newFile)
    //4. find new working directory instance given full path in the new directory structure
    val newWorkingDir = newRoot.navigateTo(allDirsInPath)
    State(newRoot, newWorkingDir)
  }

  override def apply(previousState: State): State = {
    val workingDir = previousState.workingDir
    if (workingDir.hasEntry(name))
      previousState.setMessageAndReturnThis(s"Entry $name already exists!")
    else if (checkIllegal(name))
      previousState.setMessageAndReturnThis(name + ": illegal name!")
    else
      doTouch(previousState, name)
  }
}
