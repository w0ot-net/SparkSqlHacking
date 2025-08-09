package org.glassfish.jersey.client.internal.inject;

import org.glassfish.jersey.client.inject.ParameterUpdater;

final class PrimitiveValueOfUpdater implements ParameterUpdater {
   private final String parameter;
   private final String defaultValue;
   private final Object defaultPrimitiveTypeValue;

   public PrimitiveValueOfUpdater(String parameter, String defaultValue, Object defaultPrimitiveTypeValue) {
      this.parameter = parameter;
      this.defaultValue = defaultValue;
      this.defaultPrimitiveTypeValue = defaultPrimitiveTypeValue;
   }

   public String getName() {
      return this.parameter;
   }

   public String getDefaultValueString() {
      return this.defaultValue;
   }

   public String update(Object value) {
      if (value != null) {
         return value.toString();
      } else {
         return this.defaultValue != null ? this.defaultValue : this.defaultPrimitiveTypeValue.toString();
      }
   }
}
