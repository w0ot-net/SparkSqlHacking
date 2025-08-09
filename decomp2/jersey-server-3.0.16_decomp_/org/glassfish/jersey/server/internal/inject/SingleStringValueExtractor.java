package org.glassfish.jersey.server.internal.inject;

import jakarta.ws.rs.core.MultivaluedMap;

final class SingleStringValueExtractor implements MultivaluedParameterExtractor {
   private final String paramName;
   private final String defaultValue;

   public SingleStringValueExtractor(String parameterName, String defaultValue) {
      this.paramName = parameterName;
      this.defaultValue = defaultValue;
   }

   public String getName() {
      return this.paramName;
   }

   public String getDefaultValueString() {
      return this.defaultValue;
   }

   public String extract(MultivaluedMap parameters) {
      String value = (String)parameters.getFirst(this.paramName);
      return value != null ? value : this.defaultValue;
   }
}
