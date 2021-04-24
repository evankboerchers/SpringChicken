FROM mysql

RUN apt update && \
    apt install -y wget unzip

RUN wget https://sp.mysqltutorial.org/wp-content/uploads/2018/03/mysqlsampledatabase.zip && \
    unzip mysqlsampledatabase.zip && \
    mkdir -p /docker-entrypoint-initdb.d/ && \
    mv mysqlsampledatabase.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
