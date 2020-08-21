package com.cogrammer

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class QuarkusVsSpringBootBenchmark extends Simulation {

  private val warmupStrategy = List(
    constantUsersPerSec(1) during (3 minute)
  )

  private val constantUsersStrategy = List(
    constantUsersPerSec(10) during (10 minutes)
  )

  private val spikeStrategy = List(
    rampUsersPerSec(0) to 30 during (20 minutes)
  )

  private val loadStrategies = Map(
    "WARMUP" -> warmupStrategy,
    "CONSTANT" -> constantUsersStrategy,
    "SPIKE" -> spikeStrategy
  )

  private val strategyName = sys.env.getOrElse("BENCHMARK_STRATEGY_NAME", "WARMUP")
  private val scenarioRepeatCount = sys.env.getOrElse("BENCHMARK_SCENARIO_REPEAT_COUNT", "1").toInt
  private val apiBaseUrl = sys.env.getOrElse("API_BASE_URL", "http://localhost:8080/api")

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(apiBaseUrl)

  val scn: ScenarioBuilder = scenario("Benchmark")
    .repeat(scenarioRepeatCount) {
      exec(http("/hello")
        .get("/hello"))
        .pause(3)
      .exec(http("/cities")
        .get("/cities?country_code=NLD"))
        .pause(2)
    }

  setUp(scn.inject(loadStrategies.getOrElse(strategyName, warmupStrategy)).protocols(httpProtocol))
}
