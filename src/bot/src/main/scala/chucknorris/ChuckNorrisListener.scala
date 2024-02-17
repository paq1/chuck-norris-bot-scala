package chucknorris

import com.typesafe.scalalogging.{LazyLogging, Logger}
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener

class ChuckNorrisListener extends EventListener with LazyLogging {
  override def onEvent(event: GenericEvent): Unit = {

    event match
      case event: ReadyEvent =>
        logger.info("Bot is ready")
      case _ => logger.info("any event catch")
  }
}
