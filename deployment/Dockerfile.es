FROM fgrehm/elasticsearch

ENTRYPOINT ["/usr/share/elasticsearch/bin/elasticsearch", "-f"]

EXPOSE 9200
EXPOSE 9300

