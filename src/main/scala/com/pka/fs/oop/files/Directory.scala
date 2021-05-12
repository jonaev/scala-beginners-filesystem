package com.pka.fs.oop.files

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def replaceEntry(oldEntryName: String, directory: Directory): Directory =
    new Directory(parentPath, name, contents.filter(!_.name.equals(oldEntryName)) :+ directory)


  def findEntry(entryName: String): DirEntry = {
    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry =
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)

    findEntryHelper(entryName, contents)
  }


  def addEntry(newEntry: DirEntry): Directory = {
    new Directory(parentPath, name, contents :+ newEntry)
  }

  def navigateTo(allDirsInPath: List[String]): Directory = {
    /*
    /a/b/c
    navigateTo(["a", "b", "c"]) = findEntry("a").asDirectory.findEntry("b").asDirectory.findEntry("c").asDirectory.this
    navigateTo(["a", "b", "c"]) =>
    findEntry("a").asDirectory.navigateTo(["b", "c"])
      => findEntry("b").asDirectory.navigateTo(["c"])
       => findEntry("c").asDirectory.navigateTo([])
        => this
     */
    if (allDirsInPath.isEmpty) this
    else findEntry(allDirsInPath.head).asDirectory.navigateTo(allDirsInPath.tail)
  }

  def hasEntry(name: String): Boolean = {
    findEntry(name) != null
  }

  def getAllFoldersInPath: List[String] = {
    // a/b/c/d => List["a", "b", "c", "d"]
    path.substring(1).split(Directory.SEPARATOR).toList.filter(!_.isEmpty)
  }

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def isDirectory: Boolean = true

  if (contents.map(_.parentPath).distinct.size >= 2) throw new RuntimeException("you cant create such directory!")
}


object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())
}