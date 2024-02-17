package chucknorris.services

import com.typesafe.scalalogging.LazyLogging
import sttp.client4.Response
import sttp.client4.quick.*

import scala.concurrent.Future

class JokeChuckNorrisServiceImpl
    extends JokeChuckNorrisService
    with LazyLogging {
  def getRandomJoke: Future[String] = {
    val url = "https://chuckn.neant.be/api/rand"
    val result: Response[String] = quickRequest
      .get(uri"$url")
      .send()
    logger.debug(s"call $url")

    val joke = ujson.read(result.body)("joke").str
    Future.successful(joke)
  }
}
