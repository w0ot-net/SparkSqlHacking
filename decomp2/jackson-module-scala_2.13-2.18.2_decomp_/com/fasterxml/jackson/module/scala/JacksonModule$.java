package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.InputStream;
import java.util.Properties;
import scala.collection.JavaConverters.;
import scala.collection.mutable.Map;

public final class JacksonModule$ {
   public static final JacksonModule$ MODULE$ = new JacksonModule$();
   private static Map buildProps;
   private static Version version;
   private static final Class cls = JacksonModule.class;
   private static final String buildPropsFilename;
   private static volatile byte bitmap$0;

   static {
      buildPropsFilename = (new StringBuilder(17)).append(MODULE$.cls().getPackage().getName().replace('.', '/')).append("/build.properties").toString();
   }

   private Class cls() {
      return cls;
   }

   private String buildPropsFilename() {
      return buildPropsFilename;
   }

   private Map buildProps$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            Properties props = new Properties();
            InputStream stream = this.cls().getClassLoader().getResourceAsStream(this.buildPropsFilename());
            if (stream != null) {
               props.load(stream);
            }

            buildProps = (Map).MODULE$.propertiesAsScalaMapConverter(props).asScala();
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return buildProps;
   }

   public Map buildProps() {
      return (byte)(bitmap$0 & 1) == 0 ? this.buildProps$lzycompute() : buildProps;
   }

   private Version version$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            String groupId = (String)this.buildProps().apply("groupId");
            String artifactId = (String)this.buildProps().apply("artifactId");
            String version = (String)this.buildProps().apply("version");
            JacksonModule$.version = VersionUtil.parseVersion(version, groupId, artifactId);
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return JacksonModule$.version;
   }

   public Version version() {
      return (byte)(bitmap$0 & 2) == 0 ? this.version$lzycompute() : version;
   }

   private JacksonModule$() {
   }
}
