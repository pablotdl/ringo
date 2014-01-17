#!/usr/bin/env bash

docker run -d dockerfile/elasticsearch

BUILD=$(docker build - < /vagrant/Dockerfile.node)
CONTAINER=${BUILD##* }
docker run $CONTAINER
#docker run -d $CONTAINER


docker run -d jvallelunga/ringo /bin/sh -c "while true; do echo Hello world; sleep 1; done"

