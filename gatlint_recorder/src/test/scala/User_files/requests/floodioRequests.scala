package User_files.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class floodioRequests {

  val agesFeeder = csv("data/ages.csv")

  def getMaxValue(array: Array[Int]) = {
    (array.max)
  }

  val headers_0 = Map(
    "Cache-Control" -> "max-age=0",
    "If-None-Match" -> """W/"b4b5212905c7ff2bba450bef399a39b2"""",
    "Upgrade-Insecure-Requests" -> "1"
  )

  val headers_4 = Map(
    "Cache-Control" -> "max-age=0",
    "Origin" -> "https://challengers.flood.io",
    "Upgrade-Insecure-Requests" -> "1"
  )

  val getMainPageRequest = http("get_main_page_request").get("/").headers(headers_0)
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))

  val getStep2Page = http("get_step_2_page").get("/step/2")
    .headers(headers_4)
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))

  val getStep3Page = http("get_step_3_page").get("/step/3")
    .headers(headers_4)
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))
    .check(xpath(".//*[@id='new_challenger']/table//label[@class='collection_radio_buttons']")
      .findAll.saveAs("tableValues"))


  val getStep4Page = http("get_step_4_page").get("/step/4")
    .headers(headers_4)
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))

  val getStep5Page = http("get_step_5_page").get("/step/5")
    .headers(headers_4)
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))
    .check(xpath(".span[@class='token']/@value").saveAs("timeToken"))


  val setMaxValueRequest = http("set_max_value_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "3")
    .formParam("challenger[largest_order]", "${tableValues}")
    .formParam("challenger[order_selected]", "ZEVETTYyaHJDcVV3RmJzajNxc3ljUT09LS1BalFBazRYVXhuOEJQYml1Y29XRU1nPT0=--a75df043152f43b68df80e6d3dcd46aa64febab6")
    .formParam("commit", "Next")

  val clickNextRequest = http("just_click_next_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "4")
    .formParam("commit", "Next")


  val setTimeTokenRequest = http("det_time_token_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "5")
    .formParam("challenger[one_time_token]", "${timeToken}")
    .formParam("commit", "Next")

  val setAgeRequest = http("set_age_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "2")
    .formParam("challenger[age]", agesFeeder)
    .formParam("commit", "Next")
}
