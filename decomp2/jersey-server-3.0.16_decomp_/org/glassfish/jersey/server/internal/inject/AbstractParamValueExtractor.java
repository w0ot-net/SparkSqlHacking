package org.glassfish.jersey.server.internal.inject;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.ParamConverter;
import org.glassfish.jersey.internal.inject.ExtractorException;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

abstract class AbstractParamValueExtractor {
   private final ParamConverter paramConverter;
   private final String parameterName;
   private final String defaultValueString;
   private final UnsafeValue convertedDefaultValue;

   protected AbstractParamValueExtractor(ParamConverter converter, String parameterName, final String defaultValueString) {
      this.paramConverter = converter;
      this.parameterName = parameterName;
      this.defaultValueString = defaultValueString;
      if (defaultValueString != null) {
         this.convertedDefaultValue = Values.lazy(new UnsafeValue() {
            public Object get() throws RuntimeException {
               return AbstractParamValueExtractor.this.convert(defaultValueString);
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
      return this.defaultValueString;
   }

   protected final Object fromString(String value) {
      T result = (T)this.convert(value);
      return result == null ? this.defaultValue() : result;
   }

   private Object convert(String value) {
      try {
         return this.paramConverter.fromString(value);
      } catch (WebApplicationException wae) {
         throw wae;
      } catch (IllegalArgumentException iae) {
         throw iae;
      } catch (Exception ex) {
         throw new ExtractorException(ex);
      }
   }

   protected final boolean isDefaultValueRegistered() {
      return this.defaultValueString != null;
   }

   protected final Object defaultValue() {
      return !this.isDefaultValueRegistered() ? null : this.convertedDefaultValue.get();
   }
}
