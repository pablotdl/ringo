#!/bin/sh

if [ "$#" -ne 4 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

cd "$( dirname "$0" )"

cd agent
mvn antrun:run -P run-on-felix -Dagent.sla=$1 -Dagent.nod=$2 -Des.host=$3 -Des.port=$4 & 
cd ..

cd  mock-app
mvn jetty:run

