FROM openjdk:17-jdk-alpine3.14 as builder

#RUN rm -i /opt/openjdk-17/lib/security/cacerts \
#&& cp /etc/ssl/certs/java/cacerts /opt/openjdk-17/lib/security/ \
RUN apk add binutils \
&& jlink \
--verbose \
--add-modules \
java.base,\
java.xml,\
java.desktop,\
java.instrument,\
java.prefs,\
java.management,\
java.naming,\
java.net.http,\
java.security.jgss,\
java.security.sasl,\
jdk.crypto.ec,\
jdk.crypto.cryptoki,\
java.sql,\
jdk.net,\
jdk.httpserver,\
jdk.unsupported \
--strip-debug \
--no-man-pages \
--no-header-files \
--compress=2 \
--output /jre

FROM alpine:3.16

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

#ARG KEYSTORE_PATH=$JAVA_HOME/lib/security/cacerts
ARG APPLICATION_USER=appuser

COPY --from=builder /jre $JAVA_HOME
COPY --chown=1000:1000 ./target/mykudos-api.jar /app/app.jar

WORKDIR /app

RUN apk add --no-cache bash tzdata git ca-certificates \
&& cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime \
&& echo "America/Sao_Paulo" > /etc/timezone \
&& adduser --no-create-home -u 1000 -D $APPLICATION_USER \
&& chown -R $APPLICATION_USER /app

USER 1000

EXPOSE 8080

CMD [ "java","-jar", "app.jar" ]