package app

import com.typesafe.config.{Config, ConfigFactory}

trait ConfigComponent:
  def config: Config = ConfigFactory.load()
