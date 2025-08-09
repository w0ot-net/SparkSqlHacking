package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;

public abstract class ParametersParser {
   private final Bytes objectIdentifier;
   private final Class serializationClass;

   private ParametersParser(Bytes objectIdentifier, Class serializationClass) {
      this.objectIdentifier = objectIdentifier;
      this.serializationClass = serializationClass;
   }

   public abstract Parameters parseParameters(Serialization serialization) throws GeneralSecurityException;

   public final Bytes getObjectIdentifier() {
      return this.objectIdentifier;
   }

   public final Class getSerializationClass() {
      return this.serializationClass;
   }

   public static ParametersParser create(final ParametersParsingFunction function, Bytes objectIdentifier, Class serializationClass) {
      return new ParametersParser(objectIdentifier, serializationClass) {
         public Parameters parseParameters(Serialization serialization) throws GeneralSecurityException {
            return function.parseParameters(serialization);
         }
      };
   }

   public interface ParametersParsingFunction {
      Parameters parseParameters(Serialization serialization) throws GeneralSecurityException;
   }
}
