package org.apache.ivy.core.module.descriptor;

public interface ConfigurationAware {
   String[] getConfigurations();

   void addConfiguration(String var1);
}
