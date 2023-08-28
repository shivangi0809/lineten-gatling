package Linetenapi

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AddUser extends Simulation{

  //protocol
  
  val httpProtocol = {
  http.baseUrl("https://linetencustomerapi.azurewebsites.net/Api")
  }

//scenario
val scn = scenario("createuser").exec(
    http("create user")
      .post("/add")
      .header("content-type","application-json")
      .asJson
      .body(RawFileBody("data/users.json")).asJson
      //.body(StringBody(
      // """
      //  |{
      // |  "Id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      // |  "Name": "shivangi",
      // |  "Email": "shivangi.srivastava@gmail.com",
      //  |  "Phone": "1234567890",
      // |  "Address": "London",
      // |  "City": "London",
      //  |  "State": "England",
      //  |  "Zip": "e16",
      //  |  "Country": "united kingdom",
      //  |  "Notes": "abcd"
      //  |}
      //  |""".stripMargin)).asJson
      .check(
        status is 200,
        jsonPath("$.Name") is "Shivangi",
        jsonPath("$.Email")is "shivangi.srivastava@gmail.com"
      )
  )
  .pause(1)


//setup
setUp(

  scn.inject(rampUsers(10).during(5)))
  .protocols(httpProtocol)
}
