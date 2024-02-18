package chucknorris.commandHandler

import chucknorris.commandHandler.responses.{MessageResponse, Response}
import com.typesafe.scalalogging.LazyLogging
import shared.Context

import scala.concurrent.{ExecutionContext, Future}

class PingCommandHandler(using ec: ExecutionContext)
    extends CommandHandlerSimpleReply[Future, String]
    with LazyLogging {

  override def commandName: String = "ping"
  override def description: String = "Pong"

  override def onCommand(
      cmd: String
  )(using ctx: Context): Future[Response] = {
    Future.successful(MessageResponse("reply Pong"))
  }
  
}
