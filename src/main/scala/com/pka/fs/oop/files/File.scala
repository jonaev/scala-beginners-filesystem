package com.pka.fs.oop.files

class File(override val parentPath: String, override val name: String, val content: String) extends DirEntry(parentPath, name) {

  override def asDirectory: Directory = ???

  override def getType: String = "File"

  override def isDirectory: Boolean = false
}

object File {
  def empty(parentPath: String, name: String) =
    new File(parentPath, name, "")
}