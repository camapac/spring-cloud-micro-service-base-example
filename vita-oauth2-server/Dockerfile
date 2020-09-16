FROM adoptopenjdk/openjdk8-openj9

VOLUME /tmp
ARG DEPENDENCY=target/java-app
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app


ENTRYPOINT ["java","-cp","app:app/lib/*","com.wiiwealth.product.WiiProductApplication"]