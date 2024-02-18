package chucknorris.commandHandler

import chucknorris.commandHandler.responses.Response
import shared.Context

trait CommandHandler[F[_], COMMAND] {

  def commandName: String

  def description: String

  def onCommand(cmd: COMMAND)(implicit ctx: Context): F[Response]

}

trait CommandHandlerSimpleReply[F[_], COMMAND]
    extends CommandHandler[F, COMMAND]
