package app

import chucknorris.ChuckNorrisCommandListener
import chucknorris.commandHandler.{CommandHandler, CommandHandlerSimpleReply, JokeCommandHandler, PingCommandHandler}
import chucknorris.services.{JokeChuckNorrisService, JokeChuckNorrisServiceImpl}
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.{CommandData, Commands}

import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.Future

object Launcher {

  private val config: Config = ConfigFactory.load()
  private val token: String = config.getString("bot.token")

  private val chuckNorrisJokeService: JokeChuckNorrisService =
    new JokeChuckNorrisServiceImpl

  private val commandHandlers: List[CommandHandler[Future, String]] =
    List(
      new JokeCommandHandler(chuckNorrisJokeService),
      new PingCommandHandler,
    )

  def main(args: Array[String]): Unit = {
    val jda = JDABuilder
      .createDefault(token)
      .addEventListeners(new ChuckNorrisCommandListener(chuckNorrisJokeService, commandHandlers))
      .setActivity(Activity.playing("Type /joke or /ping"))
      .build

    jda
      .updateCommands()
      .addCommands(
        commandDatasMapper(commandHandlers)*
      )
      .submit()
  }

  private def commandDatasMapper(commands: List[CommandHandler[Future, String]]): List[CommandData] =
    commands
      .flatMap(commandDataMapper)

  private def commandDataMapper(command: CommandHandler[Future, String]): Option[CommandData] =
    command match
      case reply: CommandHandlerSimpleReply[_, _] =>
        Some(Commands.slash(reply.commandName, reply.description))
      case _ => None
}
