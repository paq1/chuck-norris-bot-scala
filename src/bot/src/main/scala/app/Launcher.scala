import net.dv8tion.jda.api.JDABuilder

object Launcher {

  def main(args: Array[String]): Unit = {
    // Note: It is important to register your ReadyListener before building
    val jda = JDABuilder
      .createDefault("MTIwODQxNTY0MTQ3NjgwMDU5NQ.GRTmoa.IiKBP8IZAoTNPkMZhZwOp68m6a4B-D1nd2dD94")
      .addEventListeners(new ChuckNorrisListener)
      .build
    // optionally block until JDA is ready
    jda.awaitReady
  }

}
