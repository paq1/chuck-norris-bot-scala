package utils

import scala.concurrent.ExecutionContext

trait FutureComponents {
  given executionContext: ExecutionContext =
    scala.concurrent.ExecutionContext.global
}
