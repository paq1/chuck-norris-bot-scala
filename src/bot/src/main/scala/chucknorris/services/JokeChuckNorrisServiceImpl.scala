package chucknorris.services

import sttp.client4.Response
import sttp.client4.quick.*

import scala.concurrent.Future

class JokeChuckNorrisServiceImpl {
  def getRandomJoke(): Future[String] = {

    val result: Response[String] = quickRequest
      .get(uri"https://chuckn.neant.be/api/rand")
      .send()

    val joke = ujson.read(result.body)("joke").str

    Future.successful(joke)
  }
}
