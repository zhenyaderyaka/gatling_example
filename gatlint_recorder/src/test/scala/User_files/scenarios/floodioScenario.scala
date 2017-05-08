package User_files.scenarios

import User_files.requests.floodioRequests
import io.gatling.core.Predef.scenario

class floodioScenario {

  val requests = new floodioRequests();

  val scn = scenario("FloodIoSteps")
    .exec(requests.getMainPageRequest)
    .pause()
    .exec(requests.setAgeRequest)
    .pause("10")
    .exec(requests.setMaxValueRequest)
    .pause("10")
    .exec(requests.clickNextRequest)
    .pause("10")
    .exec(requests.setTimeTokenRequest)
}
