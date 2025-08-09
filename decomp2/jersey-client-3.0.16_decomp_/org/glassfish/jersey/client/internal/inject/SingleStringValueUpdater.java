package org.glassfish.jersey.client.internal.inject;

import org.glassfish.jersey.client.inject.ParameterUpdater;

final class SingleStringValueUpdater implements ParameterUpdater {
   private final String paramName;
   private final String defaultValue;

   public SingleStringValueUpdater(String parameterName, String defaultValue) {
      this.paramName = parameterName;
      this.defaultValue = defaultValue;
   }

   public String getName() {
      return this.paramName;
   }

   public String getDefaultValueString() {
      return this.defaultValue;
   }

   public String update(String value) {
      return value != null ? value : this.defaultValue;
   }
}
