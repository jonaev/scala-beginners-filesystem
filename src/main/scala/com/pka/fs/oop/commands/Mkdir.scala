package com.pka.fs.oop.commands
import com.pka.fs.oop.files.Directory
import com.pka.fs.oop.filesystem.State

class Mkdir(name: String) extends Command {

  private def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  override def apply(previousState: State): State = {
    val workingDir = previousState.workingDir
    if (workingDir.hasEntry(name))
      previousState.setMessageAndReturnThis(s"Entry $name already exists!")
    else if (name.contains(Directory.SEPARATOR))
      //mkdir something/somethingelse
      //TODO mkdir -p allow it
      previousState.setMessageAndReturnThis("Wrong folder name")
    else if (checkIllegal(name))
      previousState.setMessageAndReturnThis(name + ": illegal name!")
    else
      doMkdir(previousState, name)
  }

  def doMkdir(previousState: State, name: String): State = {

    val workingDir = previousState.workingDir
    //1. all directories in the full path
    val allDirsInPath = workingDir.getAllFoldersInPath
    //2. create new directory entry in working dir
    val newDir = Directory.empty(workingDir.path, name)
    //3. update the whole directory structure starting from the root
    val newRoot = State.updateStructure(previousState.root, allDirsInPath, newDir)
    //4. find new working directory instance given full path in the new directory structure
    val newWorkingDir = newRoot.navigateTo(allDirsInPath)
    State(newRoot, newWorkingDir)
  }
}
