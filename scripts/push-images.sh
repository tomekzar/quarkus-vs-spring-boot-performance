#!/bin/bash

docker push tomekzar/quarkus-vs-spring-boot-performance:quarkus-jvm
docker push tomekzar/quarkus-vs-spring-boot-performance:quarkus-native
docker push tomekzar/quarkus-vs-spring-boot-performance:quarkus-mutiny-jvm
docker push tomekzar/quarkus-vs-spring-boot-performance:quarkus-mutiny-native
docker push tomekzar/quarkus-vs-spring-boot-performance:quarkus-vertx-jvm
docker push tomekzar/quarkus-vs-spring-boot-performance:quarkus-vertx-native
docker push tomekzar/quarkus-vs-spring-boot-performance:spring-boot-jvm
docker push tomekzar/quarkus-vs-spring-boot-performance:spring-boot-webflux-jvm