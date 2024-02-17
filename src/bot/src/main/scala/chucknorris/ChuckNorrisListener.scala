import com.typesafe.scalalogging.Logger
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener

class ChuckNorrisListener extends EventListener {

  val logger = Logger(getClass.getName)

  override def onEvent(event: GenericEvent): Unit = {

    logger.info("iciiiiiii")

    event match
      case event: ReadyEvent =>
        logger.info("Bot is ready")
  }
}

object ChuckNorrisListener {

}
