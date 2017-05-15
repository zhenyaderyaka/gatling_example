package User_files.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FloodioRequests {

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
    .check(regex("""<input name="authenticity_token" type="hidden" value="(.+?)" />""").find.saveAs("token"))
    .check(regex("""id="challenger_step_id" name="challenger\[step_id\]" type="hidden" value="(.+?)"""").find.saveAs("step_id"))

  val getStep2Page = http("get_step_2_page").get("/step/2")
    .headers(headers_4)
    .check(regex("""<input name="authenticity_token" type="hidden" value="(.+?)" />""").find.saveAs("token"))
    .check(regex("""id="challenger_step_id" name="challenger\[step_id\]" type="hidden" value="(.+?)"""").find.saveAs("step_id"))

  val getStep3Page = http("get_step_3_page").get("/step/3")
    .headers(headers_4)
    .check(regex("""<input name="authenticity_token" type="hidden" value="(.+?)" />""").find.saveAs("token"))
    .check(regex("""id="challenger_step_id" name="challenger\[step_id\]" type="hidden" value="(.+?)"""").find.saveAs("step_id"))
    .check(regex("""<input class="radio_buttons optional" id="(.+?)" name="challenger\[order_selected\]" type="radio"""").findAll.saveAs("inputs"))
    .check(regex("""class="collection_radio_buttons".*?>(\d+)\<""").findAll.saveAs("tableValues"))

  val getStep4Page = http("get_step_4_page").get("/step/4")
    .headers(headers_4)
    .check(regex("""<input name="authenticity_token" type="hidden" value="(.+?)" />""").find.saveAs("token"))
    .check(regex("""id="challenger_step_id" name="challenger\[step_id\]" type="hidden" value="(.+?)"""").find.saveAs("step_id"))

  val getStep5Page = http("get_step_5_page").get("/step/5")
    .headers(headers_4)
    .check(regex("""<input name="authenticity_token" type="hidden" value="(.+?)" />""").find.saveAs("token"))
    .check(regex("""id="challenger_step_id" name="challenger\[step_id\]" type="hidden" value="(.+?)"""").find.saveAs("step_id"))

  val getTimeToken = http("get_time_token").get("/code")
    .headers(headers_4)
    .check(jsonPath("$..code").find.saveAs("timeToken"))

  val getDonePage = http("get_done_page_request").get("/done")
  .check(regex("You're Done!").find)

  val setAgeRequest = http("set_age_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "2")
    .formParam("challenger[age]", "${age}")
    .formParam("commit", "Next")

  val setMaxValueRequest = http("set_max_value_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "3")
    .formParam("challenger[largest_order]", "${maxValue}")
    .formParam("challenger[order_selected]", "${input}")
    .formParam("commit", "Next")

  val clickNextRequest = http("just_click_next_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "4")
    .formParam("commit", "Next")


  val setTimeTokenRequest = http("set_time_token_request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .formParam("authenticity_token", "${token}")
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "5")
    .formParam("challenger[one_time_token]", "${timeToken}")
    .formParam("commit", "Next")

}
