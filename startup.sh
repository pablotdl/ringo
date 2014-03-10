#!/bin/sh

if [ "$#" -ne 4 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

cd agent
mvn antrun:run -P run-on-felix $@ & 
cd ..

cd  mock-app
mvn jetty:run

