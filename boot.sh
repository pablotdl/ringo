#!/bin/sh

if [ "$#" -ne 4 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

echo "Starting up monitoring node with paramters $@"


cd "$( dirname "$0" )"
git pull

./startup.sh $@
