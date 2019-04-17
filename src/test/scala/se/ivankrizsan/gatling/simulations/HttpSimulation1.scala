/*
 * Copyright 2016 Ivan Krizsan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.ivankrizsan.gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder

/**
  * Example Gatling load test that sends one HTTP GET requests to a URL.
  * Note that the request is redirected and this causes the request count to become two.
  * Run this simulation with:
  * mvn -Dgatling.simulation.name=HttpSimulation1 gatling:execute
  *
  * @author Ivan Krizsan
  */
class HttpSimulation1 extends Simulation {
    /* Place for arbitrary Scala code that is to be executed before the simulation begins. */
    before {
        println("***** My simulation is about to begin! *****")
    }

    /* Place for arbitrary Scala code that is to be executed after the simulation has ended. */
    after {
        println("***** My simulation has ended! ******")
    }

    /*
     * A HTTP protocol builder is used to specify common properties of request(s) to be sent,
     * for instance the base URL, HTTP headers that are to be enclosed with all requests etc.
     */
    val theHttpProtocolBuilder = http
        //.baseURL("http://computer-database.gatling.io")
        .baseURL("http://localhost:9000")

    /*
     * A scenario consists of one or more requests. For instance logging into a e-commerce
     * website, placing an order and then logging out.
     * One simulation can contain many scenarios.
     */
    /* Scenario1 is a name that describes the scenario. */
    val theScenarioBuilder = scenario("Scenario1")
        .exec(
            /* myRequest1 is a name that describes the request. */
            http("myRequest1")
                .get("/")
        )

    /*
     * Define the load simulation.
     * Here we can specify how many users we want to simulate, if the number of users is to increase
     * gradually or if all the simulated users are to start sending requests at once etc.
     * We also specify the HTTP protocol builder to be used by the load simulation.
     */
    setUp(
        theScenarioBuilder.inject(atOnceUsers(1))
    ).protocols(theHttpProtocolBuilder)
}
