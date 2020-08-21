#!/bin/bash

cd ../benchmark || exit 1

export API_BASE_URL=http://localhost:8080/api
export BENCHMARK_SCENARIO_REPEAT_COUNT=5

export BENCHMARK_STRATEGY_NAME=SINGLE
sbt "gatling:test"

export BENCHMARK_STRATEGY_NAME=CONSTANT
sbt "gatling:test"

export BENCHMARK_STRATEGY_NAME=SPIKE
sbt "gatling:test"

#export BENCHMARK_STRATEGY_NAME=2H_1K
#sbt "gatling:test"