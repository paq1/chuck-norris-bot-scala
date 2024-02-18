package shared

import java.time.Instant

case class Context(
    user: String,
    time: Instant
)
