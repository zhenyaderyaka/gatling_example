package User_files.scenarios

import User_files.requests.floodioRequests
import io.gatling.core.Predef._
import com.typesafe.config.ConfigFactory

class floodioScenario {

  val requests = new floodioRequests()

  val conf = ConfigFactory.parseFile(new java.io.File("floodio.properties"))

  val scn = scenario("FloodIoSteps")
    .exec(requests.getMainPageRequest)
    .pause(conf.getInt("pause.minPause"), conf.getInt("pause.normalPause"))
    .exec(requests.getStep2Page)
    .exec(requests.setAgeRequest)
    .pause(conf.getInt("pause.normalPause"), conf.getInt("pause.longPause"))
    .exec(requests.getStep3Page)
    .exec(
      session => {
        session("tableValues").as[Array[Int]].max
        session
      }
  )
    .exec(requests.setMaxValueRequest)
    .pause(conf.getInt("pause.minPause"), conf.getInt("pause.shortPause"))
    .exec(requests.getStep4Page)
    .exec(requests.clickNextRequest)
    .pause(conf.getInt("pause.shortPause"), conf.getInt("pause.normalPause"))
    .exec(requests.getStep5Page)
    .exec(requests.setTimeTokenRequest)
}
