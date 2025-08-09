package org.apache.ivy.core.settings;

import org.apache.ivy.util.StringUtils;

public class NamedTimeoutConstraint implements TimeoutConstraint {
   private String name;
   private int connectionTimeout = -1;
   private int readTimeout = -1;

   public NamedTimeoutConstraint() {
   }

   public NamedTimeoutConstraint(String name) {
      StringUtils.assertNotNullNorEmpty(name, "Name of a timeout constraint cannot be null or empty string");
      this.name = name;
   }

   public void setName(String name) {
      StringUtils.assertNotNullNorEmpty(name, "Name of a timeout constraint cannot be null or empty string");
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public int getConnectionTimeout() {
      return this.connectionTimeout;
   }

   public int getReadTimeout() {
      return this.readTimeout;
   }

   public void setConnectionTimeout(int connectionTimeout) {
      this.connectionTimeout = connectionTimeout;
   }

   public void setReadTimeout(int readTimeout) {
      this.readTimeout = readTimeout;
   }
}
