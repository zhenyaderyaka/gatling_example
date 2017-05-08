package User_files.floodio

import User_files.scenarios.floodioScenario
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class floodioSimulation extends Simulation{

  val httpProtocol = http
    .baseURL("https://challengers.flood.io")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())

  val scenario = new floodioScenario()

  setUp(scenario.scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
