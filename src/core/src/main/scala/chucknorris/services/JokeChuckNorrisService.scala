package chucknorris.services

import scala.concurrent.Future

trait JokeChuckNorrisService:
  def getRandomJoke: Future[String]

