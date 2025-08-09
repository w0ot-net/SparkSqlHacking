package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import java.security.GeneralSecurityException;

public abstract class ParametersSerializer {
   private final Class parametersClass;
   private final Class serializationClass;

   private ParametersSerializer(Class parametersClass, Class serializationClass) {
      this.parametersClass = parametersClass;
      this.serializationClass = serializationClass;
   }

   public abstract Serialization serializeParameters(Parameters parameters) throws GeneralSecurityException;

   public Class getParametersClass() {
      return this.parametersClass;
   }

   public Class getSerializationClass() {
      return this.serializationClass;
   }

   public static ParametersSerializer create(final ParametersSerializationFunction function, Class parametersClass, Class serializationClass) {
      return new ParametersSerializer(parametersClass, serializationClass) {
         public Serialization serializeParameters(Parameters parameters) throws GeneralSecurityException {
            return function.serializeParameters(parameters);
         }
      };
   }

   public interface ParametersSerializationFunction {
      Serialization serializeParameters(Parameters key) throws GeneralSecurityException;
   }
}
