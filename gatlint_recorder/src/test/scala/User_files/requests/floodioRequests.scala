package User_files.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, _}

class floodioRequests {

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, sdch, br",
    "Accept-Language" -> "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4",
    "Cache-Control" -> "max-age=0",
    "Connection" -> "keep-alive",
    "If-None-Match" -> """W/"b4b5212905c7ff2bba450bef399a39b2"""",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")

  val headers_4 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4",
    "Cache-Control" -> "max-age=0",
    "Connection" -> "keep-alive",
    "Origin" -> "https://challengers.flood.io",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36")

  val headers_8 = Map(
    "Accept" -> "*/*",
    "Accept-Encoding" -> "gzip, deflate, sdch, br",
    "Accept-Language" -> "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4",
    "Connection" -> "keep-alive",
    "If-None-Match" -> "ed592dab0390771ad02bdd1a99da792c",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36",
    "X-Requested-With" -> "XMLHttpRequest")

  val getMainPageRequest = http("Get Main Page Request").get("/").headers(headers_0)

  val setMaxValueRequest = http("Set Max Value Request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .formParam("authenticity_token", "${token}")
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "3")
    .formParam("challenger[largest_order]", "264")
    .formParam("challenger[order_selected]", "ZEVETTYyaHJDcVV3RmJzajNxc3ljUT09LS1BalFBazRYVXhuOEJQYml1Y29XRU1nPT0=--a75df043152f43b68df80e6d3dcd46aa64febab6")
    .formParam("commit", "Next")

  val clickNextRequest = http("Just Click Next Request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .formParam("authenticity_token", "${token}")
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "4")
    .formParam("commit", "Next")
    .resources(http("request_8")
      .get("/code")
      .headers(headers_8))

  val setTimeTokenRequest = http("Set Time Token Request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .formParam("authenticity_token", "${token}")
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "5")
    .check(xpath(".span[@class='token']/@value").saveAs("timeToken"))
    .formParam("challenger[one_time_token]", "${timeToken}")
    .formParam("commit", "Next")

  val setAgeRequest = http("Set Age Request")
    .post("/start")
    .headers(headers_4)
    .formParam("utf8", "✓")
    .check(xpath(".//input[@name='authenticity_token']/@value").saveAs("token"))
    .formParam("authenticity_token", "${token}")
    .check(xpath(".//input[@name='challenger[step_id]']/@value").saveAs("step_id"))
    .formParam("challenger[step_id]", "${step_id}")
    .formParam("challenger[step_number]", "2")
    .formParam("challenger[age]", "20")
    .formParam("commit", "Next")
}
