package app

import chucknorris.ChuckNorrisCommandListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.build.Commands

object Launcher {

  def main(args: Array[String]): Unit = {
    val jda = JDABuilder
      .createDefault("xxx")
//      .addEventListeners(new ChuckNorrisListener)
      .addEventListeners(new ChuckNorrisCommandListener)
      .setActivity(Activity.playing("Type /ping"))
      .build

    jda
      .updateCommands()
      .addCommands(
        Commands.slash("ping", "Calculate ping of the bot")
      )
      .queue()
  }

}
