#!/bin/bash
#############################################################
# This bat script will either startup the in tools in
# its specific docker images and/or the core microservices.
#############################################################

declare base_project_dir=$(dirname $0)
declare docker_compose_file=${base_project_dir}/docker-compose.yml

function build_parent_pom() {
  echo "Building and packaging all maven projects"
  ./mvnw clean package -DskipTests
}

function clean_all() {
  echo "Cleaning all maven projects"
  ./mvnw clean
}

function build_child_pom() {
  echo "Building and packaging $1"
  child_dir=$1
  (cd $child_dir; ./mvnw clean package -DskipTests; cd $(dirname "${base_project_dir}"))
}

function start_all_infra_tools() {

  start_infra_tool vault
  start_infra_tool setup-vault
  start_infra_tool mysql
  start_infra_tool zipkin
  start_infra_tool prometheus
  start_infra_tool grafana
  start_infra_tool config-server
  start_infra_tool registry-service

  docker-compose -f ${docker_compose_file} logs -f
}

function start_infra_tool() {
  echo "Starting $1..."
  run_docker_compose $1
}

function start_all () {
  echo "Starting all infrastructure tools and services"
  build_parent_pom
  run_docker_compose
  docker-compose -f ${docker_compose_file} logs -f
}

function start_service () {
  echo "Starting service $1"
  run_docker_compose $1
}

function stop_all () {
  echo "Stopping all services"
  docker-compose -f ${docker_compose_file} down
  docker-compose -f ${docker_compose_file} rm -f
}

function stop_service () {
  echo "Stopping service $1"
  docker-compose -f ${docker_compose_file} stop $1
  docker-compose -f ${docker_compose_file} rm -f $1
}

function restart () {
  echo "Restarting all services"
  stop_all
  start_all
}

# Should not be called explicitly
function run_docker_compose() {
  docker-compose -f ${docker_compose_file} up --build --remove-orphans -d $1
}

if [ $# == 0 ]
then
  echo "Invalid argument"
  exit
fi

if [ $1 == '-help' ]
then
  echo "The following usages show how to use infrastructure tools and core services in their specifc docker containers."
  echo "Usage: <script> <action> <service>"
  echo "       ./start.sh build_child_pom inventory-service"
  echo "-------------------------------------------------------------------------------"
  echo "     build_parent_pom (Build all projects)"
  echo "     build_child_pom <container_name> (Build specific service)"
  echo "     start_all_infra_tools (Start all infrastrucure tools)"
  echo "     start_infra_tool <container_name> (Start specific infrastructure tool)"
  echo "     start_all (Start all core services and infrastructure tools)"
  echo "     start_service <container_name> (Start specific service)"
  echo "     stop_all (Stop all core services and infrastructure tools)"
  echo "     stop_service <container_name> (Stop specific service)"
  echo "     restart (Restart everything)"
else
  eval $@
fi