#!/usr/bin/env bash


wget https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-0.90.7.zip
unzip elasticsearch-*.zip
rm -f elasticsearch-*.zip
mv elasticsearch-* elasticsearch
elasticsearch/bin/elasticsearch