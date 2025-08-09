module org.apache.log4j {
   requires java.base;
   requires java.desktop;
   requires transitive java.management;
   requires transitive java.xml;
   requires static javax.jms.api;
   requires transitive org.apache.logging.log4j;
   requires static org.apache.logging.log4j.core;

   exports org.apache.log4j;
   exports org.apache.log4j.builders;
   exports org.apache.log4j.config;
   exports org.apache.log4j.helpers;
   exports org.apache.log4j.jmx;
   exports org.apache.log4j.or;
   exports org.apache.log4j.or.jms;
   exports org.apache.log4j.pattern;
   exports org.apache.log4j.rewrite;
   exports org.apache.log4j.spi;
   exports org.apache.log4j.varia;
   exports org.apache.log4j.xml;

   opens org.apache.log4j.builders to
      org.apache.logging.log4j.core;
   opens org.apache.log4j.builders.appender to
      org.apache.logging.log4j.core;
   opens org.apache.log4j.builders.filter to
      org.apache.logging.log4j.core;
   opens org.apache.log4j.builders.layout to
      org.apache.logging.log4j.core;
   opens org.apache.log4j.builders.rewrite to
      org.apache.logging.log4j.core;
   opens org.apache.log4j.builders.rolling to
      org.apache.logging.log4j.core;
   opens org.apache.log4j.config to
      org.apache.logging.log4j.core;
}
