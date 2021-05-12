package com.pka.fs.oop.commands


class CommandTest extends CommandsSuite {
  test("Command.from should return UnknownCommand") {
    val command = Command.from("aaa")
    command shouldBe a[UnknownCommand]
  }

}
