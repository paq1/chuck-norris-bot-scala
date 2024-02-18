package utils

import scala.concurrent.ExecutionContext

trait FutureComponents {
  implicit def executionContext: ExecutionContext =
    scala.concurrent.ExecutionContext.global
}
