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
  * Example Gatling load test simulating ten users that each sends one single HTTP GET request.
  * All the users will start sending requests immediately when the simulation is started.
  * Run this simulation with:
  * mvn -Dgatling.simulation.name=HttpSimulation3 gatling:execute
  *
  * @author Ivan Krizsan
  */
class HttpSimulation3 extends Simulation {

    val theHttpProtocolBuilder = http
        //.baseURL("http://computer-database.gatling.io")
        .baseURL("http://106.125.45.53:9000")

    val theScenarioBuilder = scenario("Scenario1")
        .exec(
            http("myRequest1")
                .get("/computers"))

    setUp(
        /*
         * Here we specify that ten simulated users shall start sending requests immediately
         * in the Scenario1 scenario.
         */
        theScenarioBuilder.inject(atOnceUsers(100))
    ).protocols(theHttpProtocolBuilder)
}
