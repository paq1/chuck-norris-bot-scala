package app

import chucknorris.ChuckNorrisCommandListener
import chucknorris.commandHandler.{CommandHandler, CommandHandlerSimpleReply}
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.{CommandData, Commands}

import scala.concurrent.Future

object Launcher extends Components with App {

  val jda = JDABuilder
    .createDefault(token)
    .addEventListeners(
      new ChuckNorrisCommandListener(chuckNorrisJokeService, commandHandlers)
    )
    .setActivity(Activity.playing("Type /joke or /ping"))
    .build

  jda
    .updateCommands()
    .addCommands(
      commandDatasMapper(commandHandlers)*
    )
    .submit()

  private def commandDatasMapper(
      commands: List[CommandHandler[Future, String]]
  ): List[CommandData] =
    commands
      .flatMap(commandDataMapper)

  private def commandDataMapper(
      command: CommandHandler[Future, String]
  ): Option[CommandData] =
    command match
      case reply: CommandHandlerSimpleReply[_, _] =>
        Some(Commands.slash(reply.commandName, reply.description))
      case _ => None
}
