FROM postgres:latest

COPY database/init.sql /docker-entrypoint-initdb.d/
