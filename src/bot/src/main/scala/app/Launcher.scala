package app

import chucknorris.ChuckNorrisCommandListener
import chucknorris.services.{JokeChuckNorrisService, JokeChuckNorrisServiceImpl}
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.Commands

import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.{Config, ConfigFactory}

object Launcher {

  private val config: Config = ConfigFactory.load()
  private val token: String = config.getString("bot.token")

  private val chuckNorrisJokeService: JokeChuckNorrisService =
    new JokeChuckNorrisServiceImpl

  def main(args: Array[String]): Unit = {
    val jda = JDABuilder
      .createDefault(token)
      .addEventListeners(new ChuckNorrisCommandListener(chuckNorrisJokeService))
      .setActivity(Activity.playing("Type /joke or /ping"))
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
