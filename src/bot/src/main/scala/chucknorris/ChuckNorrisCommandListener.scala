package chucknorris

import com.typesafe.scalalogging.Logger
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ChuckNorrisCommandListener extends ListenerAdapter {

  override def onSlashCommandInteraction(
      event: SlashCommandInteractionEvent
  ): Unit = {
    // make sure we handle the right command
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

      case _ =>
        logger.info(
          s"Unknown command ${event.getName} used by ${event.getName}"
        )
    }
  }

  private val logger = Logger(getClass.getName)
}
