package chucknorris.services

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import sttp.client4.Response
import sttp.client4.quick.*

import scala.concurrent.Future

class JokeChuckNorrisServiceImpl(config: Config)
    extends JokeChuckNorrisService
    with LazyLogging {

  private val url = config.getString("chuckNorrisApi.url")
  def getRandomJoke: Future[String] = {
    val result: Response[String] = quickRequest
      .get(uri"$url")
      .send()
    logger.debug(s"call $url")

    val joke = ujson.read(result.body)("joke").str
    Future.successful(joke)
  }
}
