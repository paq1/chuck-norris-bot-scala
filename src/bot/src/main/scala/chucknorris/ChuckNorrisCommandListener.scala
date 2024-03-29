package chucknorris

import chucknorris.commandHandler.CommandHandler
import chucknorris.commandHandler.responses.MessageResponse
import chucknorris.services.JokeChuckNorrisService
import com.typesafe.scalalogging.{LazyLogging, Logger}
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import shared.Context

import java.time.Instant
import scala.concurrent.{ExecutionContext, Future}

class ChuckNorrisCommandListener(
    jokeChuckNorrisService: JokeChuckNorrisService,
    commandHandlers: List[CommandHandler[Future, String]]
)(using ec: ExecutionContext)
    extends ListenerAdapter
    with LazyLogging {

  override def onSlashCommandInteraction(
      event: SlashCommandInteractionEvent
  ): Unit = {

    val time = System.currentTimeMillis
    val now = Instant.now()

    given context: Context =
      Context(event.getUser.getName, event.getChannel.getName, now)

    commandHandlers
      .foreach { commandHandler =>
        if (commandHandler.commandName == event.getName) {
          logger.debug(s"command context : $context")
          commandHandler
            .onCommand(event.getName)
            .map {
              case MessageResponse(content) =>
                event
                  .reply(content)
                  .setEphemeral(false)
                  .queue()
              case _ =>
                logger.error("type de reponse non catchée")
            }

        }
      }
  }
}
