module org.apache.logging.log4j.slf4j2.impl {
   requires java.base;
   requires transitive org.apache.logging.log4j;
   requires transitive org.slf4j;

   exports org.apache.logging.slf4j;
   exports org.apache.logging.slf4j.message;

   provides org.slf4j.spi.SLF4JServiceProvider with
      org.apache.logging.slf4j.SLF4JServiceProvider;
}
