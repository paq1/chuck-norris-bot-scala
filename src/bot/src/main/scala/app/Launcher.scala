package app

import chucknorris.{ChuckNorrisCommandListener, ChuckNorrisListener}
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.{JDABuilder, Permission}
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.build.Commands

object Launcher {

  def main(args: Array[String]): Unit = {
    // Note: It is important to register your ReadyListener before building
    val jda = JDABuilder
      .createDefault("xxx")
//      .addEventListeners(new ChuckNorrisListener)
      .addEventListeners(new ChuckNorrisCommandListener)
      .setActivity(Activity.playing("Type /ping"))
      .build
    // optionally block until JDA is ready

    jda.updateCommands().addCommands(
      Commands.slash("ping", "Calculate ping of the bot")
    ).queue();
//    jda.awaitReady
  }

}
