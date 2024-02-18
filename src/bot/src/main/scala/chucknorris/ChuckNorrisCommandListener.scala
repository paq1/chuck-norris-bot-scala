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
)(implicit ec: ExecutionContext)
    extends ListenerAdapter
    with LazyLogging {

  override def onSlashCommandInteraction(
      event: SlashCommandInteractionEvent
  ): Unit = {

    val time = System.currentTimeMillis
    val now = Instant.now()
    implicit val context: Context = Context(event.getName, now)

    commandHandlers
      .foreach { commandHandler =>
        if (commandHandler.commandName == event.getName) {
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
