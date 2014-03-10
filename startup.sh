#!/bin/sh

if [ "$#" -ne 4 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

echo "Starting up monitoring node"
echo "SLA: $1"
echo "Node: $2"
echo "ES host: $3"
echo "ES port: $4"


cd "$( dirname "$0" )"
git pull

cd agent
mvn antrun:run -P run-on-felix $@ & 
cd ..

cd  mock-app
mvn jetty:run

