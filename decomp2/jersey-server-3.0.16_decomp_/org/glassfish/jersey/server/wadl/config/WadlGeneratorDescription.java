package org.glassfish.jersey.server.wadl.config;

import java.util.Properties;
import org.glassfish.jersey.server.wadl.WadlGenerator;

public class WadlGeneratorDescription {
   private Class generatorClass;
   private Properties properties;
   private Class configuratorClass;

   public WadlGeneratorDescription() {
   }

   public WadlGeneratorDescription(Class generatorClass, Properties properties) {
      this.generatorClass = generatorClass;
      this.properties = properties;
   }

   public Class getGeneratorClass() {
      return this.generatorClass;
   }

   public void setGeneratorClass(Class generatorClass) {
      this.generatorClass = generatorClass;
   }

   public Properties getProperties() {
      return this.properties;
   }

   public void setProperties(Properties properties) {
      this.properties = properties;
   }

   public Class getConfiguratorClass() {
      return this.configuratorClass;
   }

   void setConfiguratorClass(Class configuratorClass) {
      this.configuratorClass = configuratorClass;
   }
}
