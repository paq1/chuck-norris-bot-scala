package shared

import java.time.Instant

case class Context(
    user: String,
    channel: String,
    time: Instant
)
