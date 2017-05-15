package User_files.scenarios

import User_files.requests._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.typesafe.config.ConfigFactory


object FloodioScenario {

  val agesFeeder = csv("data/ages.csv").random

  val conf = ConfigFactory.parseFile(new java.io.File("floodio.properties"))

  val scn = scenario("FloodIoSteps").feed(agesFeeder)
    .exec(FloodioRequests.getMainPageRequest)
    .pause(conf.getInt("pause.minPause"), conf.getInt("pause.normalPause"))
    .exec(FloodioRequests.getStep2Page)
    .exec(FloodioRequests.setAgeRequest)
    .pause(conf.getInt("pause.normalPause"), conf.getInt("pause.longPause"))
    .exec(FloodioRequests.getStep3Page)
    .exec(
      session => {
        val inputs = session("inputs").as[Seq[String]]
        val inputValues = session("tableValues").as[List[String]].map(_.toInt).max

        println("Max: " + inputValues)
        session.set("input", inputs)
        .set("maxValue", inputValues)
      }
    )
    .exec(FloodioRequests.setMaxValueRequest)
      .pause(conf.getInt("pause.minPause"), conf.getInt("pause.shortPause"))
      .exec(FloodioRequests.getStep4Page)
      .exec(FloodioRequests.clickNextRequest)
      .pause(conf.getInt("pause.shortPause"), conf.getInt("pause.normalPause"))
      .exec(FloodioRequests.getStep5Page)
      .exec(FloodioRequests.getTimeToken)
      .exec(FloodioRequests.setTimeTokenRequest)
      .pause(conf.getInt("pause.minPause"), conf.getInt("pause.shortPause"))
      .exec(FloodioRequests.getDonePage)
}
