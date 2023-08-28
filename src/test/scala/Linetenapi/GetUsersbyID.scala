package Linetenapi

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetUsersbyID extends Simulation
{

  //protocol

  val httpProtocol= {
    http.baseUrl("https://linetencustomerapi.azurewebsites.net/Api/get")
  }

  //scenario
  val scn=scenario("Get User BY ID").exec(
      http("get user by id")
        .get("/c241a259-58b0-4936-b27d-6a393f0c95eb")
        .check(
          status.is(200),
          jsonPath("$.Name").is("John Doe"))
    )
    .pause(1)


  //setup
  setUp(

    scn.inject(rampUsers(10).during(5)))
    .protocols(httpProtocol)

}



