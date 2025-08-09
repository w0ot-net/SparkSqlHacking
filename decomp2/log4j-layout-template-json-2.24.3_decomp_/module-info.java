module org.apache.logging.log4j.layout.template.json {
   requires java.base;
   requires transitive org.apache.logging.log4j;
   requires org.apache.logging.log4j.core;
   requires static org.jctools.core;

   exports org.apache.logging.log4j.layout.template.json;
   exports org.apache.logging.log4j.layout.template.json.resolver;
   exports org.apache.logging.log4j.layout.template.json.util;

   opens org.apache.logging.log4j.layout.template.json to
      org.apache.logging.log4j.core;
   opens org.apache.logging.log4j.layout.template.json.resolver to
      org.apache.logging.log4j.core;
   opens org.apache.logging.log4j.layout.template.json.util to
      org.apache.logging.log4j.core;
}
