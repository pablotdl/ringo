#!/bin/sh

if [ "$#" -ne 4 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

cd "$( dirname "$0" )"

pushd agent
mvn antrun:run -P run-on-felix -Dagent.sla=$1 -Dagent.nod=$2 -Des.host=$3 -Des.post=$4 & 
popd

pushd mock-app
mvn jetty:run
popd

sleep 20
curl -XGET http://localhost:8081/mock-app/generator/start

