package app

import app.Launcher.config
import chucknorris.ChuckNorrisComponent
import utils.FutureComponents

class Components
    extends ConfigComponent
    with FutureComponents
    with ChuckNorrisComponent {

  val token: String = config.getString("bot.token")

}
