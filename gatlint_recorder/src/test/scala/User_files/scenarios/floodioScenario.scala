package User_files.scenarios

import User_files.requests.FloodioRequests
import io.gatling.core.Predef._
import com.typesafe.config.ConfigFactory


class FloodioScenario {

  val requests = new FloodioRequests()
  val agesFeeder = csv("data/ages.csv").random


  val conf = ConfigFactory.parseFile(new java.io.File("floodio.properties"))

  val scn = scenario("FloodIoSteps")
    .exec(requests.getMainPageRequest)
    .pause(conf.getInt("pause.minPause"), conf.getInt("pause.normalPause"))
    .exec(requests.getStep2Page)
    .exec(
      session =>
      session.set("age", feed(agesFeeder))
    )
    .exec(requests.setAgeRequest)
    .pause(conf.getInt("pause.normalPause"), conf.getInt("pause.longPause"))
    .exec(requests.getStep3Page)
    .exec(
      session => {
        val max = session("tableValues").as[Array[Int]].max
        session.set("maxValue", max)
      }
  )
    .exec(requests.getTableValues)
    .exec(requests.setMaxValueRequest)
    .pause(conf.getInt("pause.minPause"), conf.getInt("pause.shortPause"))
    .exec(requests.getStep4Page)
    .exec(requests.clickNextRequest)
    .pause(conf.getInt("pause.shortPause"), conf.getInt("pause.normalPause"))
    .exec(requests.getStep5Page)
    .exec(requests.setTimeTokenRequest)
}
