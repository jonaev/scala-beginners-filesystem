package com.pka.fs.oop.filesystem

import com.pka.fs.oop.files.{DirEntry, Directory}

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

  def updateStructure(previousRoot: Directory, allDirsInPath: List[String], newEntry: DirEntry): Directory = {
    /*
    somedir
      /a
      /b
      (new) /d
     => new someDir (totally new instance with the same name and parentPath)
      /a
      /b
      /d

      /a/b
          /c
          /d
          (new) /e

       create new /a with newly created /b
       create new /b folder with existing /c and /d and new  /e
     */
    if (allDirsInPath.isEmpty) previousRoot.addEntry(newEntry)
    else {
      /*
      /a/b
          /c
          /d
          (new entry)
      currentDir = /a
      path = ["b"]
       */
      val oldEntry = previousRoot.goTo(allDirsInPath.head).asDirectory
      previousRoot.replaceEntry(oldEntry.name, updateStructure(oldEntry, allDirsInPath.tail, newEntry))
    }
    /*
    /a/b
      (contents)
      (new entry) /e

    updateStructure(root, ["a", "b"], /e) = previousRoot.replaceEntry("a", /a.replaceEntry("b", /b.addEntry(/e)))
    => path.isEmpty? false
    => oldEntry = /a
    previousRoot.replaceEntry("a", updateStructure(/a, ["b"], /e)
     => path.isEmpty? false
     => oldEntry = /b
      /a.replaceEntry("b", updateStructure(/b, [], /e)
        => path.isEmpty? true
        => /b.addEntry(/e)
        -----

     */
  }
}