package Linetenapi
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class GetUsers extends Simulation {

  //protocol

  val httpProtocol = {
    http.baseUrl("https://linetencustomerapi.azurewebsites.net/Api")
  }

  //scenario
  val scn = scenario("Get api").exec(
      http("Get Users")
        .get("/get")
        .check(
          status.is(200))
        )
    .pause(1)


        //setup
  setUp(

    scn.inject(rampUsers(10).during(5)))
    .protocols(httpProtocol)

}



