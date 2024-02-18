package chucknorris

import app.Components
import chucknorris.commandHandler.{
  CommandHandler,
  JokeCommandHandler,
  PingCommandHandler
}
import chucknorris.services.{JokeChuckNorrisService, JokeChuckNorrisServiceImpl}

import scala.concurrent.Future

trait ChuckNorrisComponent { self: Components =>

  val chuckNorrisJokeService: JokeChuckNorrisService =
    new JokeChuckNorrisServiceImpl(config)

  val commandHandlers: List[CommandHandler[Future, String]] =
    List(
      new JokeCommandHandler(chuckNorrisJokeService),
      new PingCommandHandler
    )
}
