package chucknorris

import chucknorris.services.{JokeChuckNorrisService, JokeChuckNorrisServiceImpl}
import com.typesafe.scalalogging.Logger
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

import scala.concurrent.ExecutionContext

class ChuckNorrisCommandListener(
    jokeChuckNorrisService: JokeChuckNorrisService
)(implicit ec: ExecutionContext)
    extends ListenerAdapter {

  override def onSlashCommandInteraction(
      event: SlashCommandInteractionEvent
  ): Unit = {

    event.getName match {

      case "ping" =>
        val time = System.currentTimeMillis
        logger.info(s"command ${event.getName} used by ${event.getName}")
        event
          .reply("Pong! ")
          .setEphemeral(false)
          .flatMap(v =>
            event.getHook.editOriginalFormat(
              s"Pong: ${System.currentTimeMillis() - time} ms"
            ) // then edit original
          )
          .queue()

      case "joke" =>
        jokeChuckNorrisService.getRandomJoke
          .map { joke =>
            logger.info(s"command ${event.getName} used by ${event.getName}")
            logger.info(s"joke : $joke")
            event
              .reply(joke)
              .setEphemeral(false)
              .queue()
          }

      case _ =>
        logger.info(
          s"Unknown command ${event.getName} used by ${event.getName}"
        )
    }
  }

  private val logger = Logger(getClass.getName)
}
