#!/bin/bash

cd ../benchmark || exit 1

api_host=$1

if [ -z "${api_host}" ]
  then
    export API_BASE_URL=http://localhost:8080/api
  else
    export API_BASE_URL="http://${api_host}/api"
fi

echo "Base API URL: ${API_BASE_URL}"

export BENCHMARK_SCENARIO_REPEAT_COUNT=5

echo "Running warmup..."
export BENCHMARK_STRATEGY_NAME=SINGLE
sbt "gatling:test"

echo "Running test 1/2..."
export BENCHMARK_STRATEGY_NAME=CONSTANT
sbt "gatling:test"

echo "Running test 2/2..."
export BENCHMARK_STRATEGY_NAME=SPIKE
sbt "gatling:test"