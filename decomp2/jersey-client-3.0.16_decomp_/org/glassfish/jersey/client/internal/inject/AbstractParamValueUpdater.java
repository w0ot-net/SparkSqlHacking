package org.glassfish.jersey.client.internal.inject;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.ParamConverter;
import org.glassfish.jersey.internal.inject.UpdaterException;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

abstract class AbstractParamValueUpdater {
   private final ParamConverter paramConverter;
   private final String parameterName;
   private final String defaultValue;
   private final UnsafeValue convertedDefaultValue;

   protected AbstractParamValueUpdater(ParamConverter converter, String parameterName, final String defaultValue) {
      this.paramConverter = converter;
      this.parameterName = parameterName;
      this.defaultValue = defaultValue;
      if (defaultValue != null) {
         this.convertedDefaultValue = Values.lazy(new UnsafeValue() {
            public String get() throws RuntimeException {
               return defaultValue;
            }
         });
         if (!converter.getClass().isAnnotationPresent(ParamConverter.Lazy.class)) {
            this.convertedDefaultValue.get();
         }
      } else {
         this.convertedDefaultValue = null;
      }

   }

   public String getName() {
      return this.parameterName;
   }

   public String getDefaultValueString() {
      return this.defaultValue;
   }

   protected final String toString(Object value) {
      String result = this.convert(value);
      return result == null ? this.defaultValue() : result;
   }

   private String convert(Object value) {
      try {
         return this.paramConverter.toString(value);
      } catch (IllegalArgumentException | WebApplicationException ex) {
         throw ex;
      } catch (Exception ex) {
         throw new UpdaterException(ex);
      }
   }

   protected final boolean isDefaultValueRegistered() {
      return this.defaultValue != null;
   }

   protected final String defaultValue() {
      return !this.isDefaultValueRegistered() ? null : (String)this.convertedDefaultValue.get();
   }
}
