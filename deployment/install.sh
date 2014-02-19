#!/usr/bin/env bash

ES=$(docker run -d ehazlett/elasticsearch)
ES_IP=$(docker inspect $ES | grep IPAddress | cut -d '"' -f 4)
echo "ElasticSearch. ID: $ES. IP: $ES_IP"

CONSOLE_BUILD=$(docker build - < /vagrant/Dockerfile.console)
CONSOLE_IMAGE=${CONSOLE_BUILD##* }

CONSOLE=$(docker run -w="/ringo-console" -d $CONSOLE_IMAGE mvn jetty:run -Delasticsearch-nodes=$ES_IP:9300)
CONSOLE_IP=$(docker inspect $CONSOLE | grep IPAddress | cut -d '"' -f 4)
echo "CONSOLE. ID: $CONSOLE. IP: $CONSOLE_IP"

NODE_BUILD=$(docker build - < /vagrant/Dockerfile.node)
NODE_IMAGE=${NODE_BUILD##* }

NODE1=$(docker run -w="/ringo/mock-app" -d $NODE_IMAGE mvn jetty:run -Delasticsearch-nodes=$ES_IP:9300)
NODE1_IP=$(docker inspect $NODE1 | grep IPAddress | cut -d '"' -f 4)
echo "NODE 1. ID: $NODE1. IP: $NODE1_IP"

NODE2=$(docker run -w="/ringo/mock-app" -d $NODE_IMAGE mvn jetty:run -Delasticsearch-nodes=$ES_IP:9300)
NODE2_IP=$(docker inspect $NODE2 | grep IPAddress | cut -d '"' -f 4)
echo "NODE 2. ID: $NODE2. IP: $NODE2_IP"

NODE3=$(docker run -w="/ringo/mock-app" -d $NODE_IMAGE mvn jetty:run -Delasticsearch-nodes=$ES_IP:9300)
NODE3_IP=$(docker inspect $NODE3 | grep IPAddress | cut -d '"' -f 4)
echo "NODE 3. ID: $NODE3. IP: $NODE3_IP"