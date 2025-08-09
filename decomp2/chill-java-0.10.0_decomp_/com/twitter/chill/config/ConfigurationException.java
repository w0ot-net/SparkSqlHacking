package com.twitter.chill.config;

public class ConfigurationException extends Exception {
   public ConfigurationException(String var1) {
      super(var1);
   }

   public ConfigurationException(Exception var1) {
      super(var1);
   }

   public ConfigurationException(String var1, Exception var2) {
      super(var1, var2);
   }
}
