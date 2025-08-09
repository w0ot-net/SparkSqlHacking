package org.apache.commons.collections4.properties;

import java.util.Properties;

public class PropertiesFactory extends AbstractPropertiesFactory {
   public static final PropertiesFactory INSTANCE = new PropertiesFactory();

   private PropertiesFactory() {
   }

   protected Properties createProperties() {
      return new Properties();
   }
}
