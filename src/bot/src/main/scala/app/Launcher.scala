package app

import chucknorris.ChuckNorrisCommandListener
import chucknorris.services.{JokeChuckNorrisService, JokeChuckNorrisServiceImpl}
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.Commands

import scala.concurrent.ExecutionContext.Implicits.global

object Launcher {

  private val chuckNorrisJokeService: JokeChuckNorrisService =
    new JokeChuckNorrisServiceImpl

  def main(args: Array[String]): Unit = {
    val jda = JDABuilder
      .createDefault(
        "xxx"
      )
      .addEventListeners(new ChuckNorrisCommandListener(chuckNorrisJokeService))
      .setActivity(Activity.playing("Type /ping"))
      .build

    jda
      .updateCommands()
      .addCommands(
        List(
          Commands.slash("ping", "Pong"),
          Commands.slash("joke", "Get Chuck Norris joke")
        ) *
      )
      .submit()
  }

}
