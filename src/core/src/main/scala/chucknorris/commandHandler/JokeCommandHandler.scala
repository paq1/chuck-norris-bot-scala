package chucknorris.commandHandler

import chucknorris.commandHandler.responses.{MessageResponse, Response}
import chucknorris.services.JokeChuckNorrisService
import com.typesafe.scalalogging.LazyLogging
import shared.Context

import scala.concurrent.{ExecutionContext, Future}

class JokeCommandHandler(
    jokeService: JokeChuckNorrisService
)(implicit ec: ExecutionContext)
    extends CommandHandlerSimpleReply[Future, String]
    with LazyLogging {

  override def commandName: String = "joke"
  override def description: String = "Get Chuck Norris joke"

  override def onCommand(
      cmd: String
  )(implicit ctx: Context): Future[Response] = {
    jokeService.getRandomJoke
      .map { joke =>
        logger.info(s"joke : $joke")
        MessageResponse(joke)
      }
  }

}
