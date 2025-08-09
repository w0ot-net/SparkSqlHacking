package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.internal.PrimitiveWrapper;
import com.google.crypto.tink.streamingaead.internal.LegacyFullStreamingAead;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class StreamingAeadWrapper implements PrimitiveWrapper {
   private static final StreamingAeadWrapper WRAPPER = new StreamingAeadWrapper();
   private static final PrimitiveConstructor LEGACY_FULL_STREAMING_AEAD_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(LegacyFullStreamingAead::create, LegacyProtoKey.class, StreamingAead.class);

   StreamingAeadWrapper() {
   }

   public StreamingAead wrap(final PrimitiveSet primitives) throws GeneralSecurityException {
      List<StreamingAead> allStreamingAeads = new ArrayList();

      for(List entryList : primitives.getAll()) {
         for(PrimitiveSet.Entry entry : entryList) {
            if (entry.getFullPrimitive() == null) {
               throw new GeneralSecurityException("No full primitive set for key id " + entry.getKeyId());
            }

            allStreamingAeads.add((StreamingAead)entry.getFullPrimitive());
         }
      }

      PrimitiveSet.Entry<StreamingAead> primary = primitives.getPrimary();
      if (primary != null && primary.getFullPrimitive() != null) {
         return new StreamingAeadHelper(allStreamingAeads, (StreamingAead)primary.getFullPrimitive());
      } else {
         throw new GeneralSecurityException("No primary set");
      }
   }

   public Class getPrimitiveClass() {
      return StreamingAead.class;
   }

   public Class getInputPrimitiveClass() {
      return StreamingAead.class;
   }

   public static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(LEGACY_FULL_STREAMING_AEAD_PRIMITIVE_CONSTRUCTOR);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }
}
