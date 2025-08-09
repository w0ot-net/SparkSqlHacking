package com.google.crypto.tink.mac;

import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveSet;
import com.google.crypto.tink.internal.PrimitiveWrapper;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChunkedMacWrapper implements PrimitiveWrapper {
   private static final ChunkedMacWrapper WRAPPER = new ChunkedMacWrapper();

   private ChunkedMacWrapper() {
   }

   public ChunkedMac wrap(final PrimitiveSet primitives) throws GeneralSecurityException {
      if (primitives == null) {
         throw new GeneralSecurityException("primitive set must be non-null");
      } else if (primitives.getPrimary() == null) {
         throw new GeneralSecurityException("no primary in primitive set");
      } else {
         for(List list : primitives.getAll()) {
            for(PrimitiveSet.Entry entry : list) {
               ChunkedMac var6 = (ChunkedMac)entry.getFullPrimitive();
            }
         }

         return new WrappedChunkedMac(primitives);
      }
   }

   public Class getPrimitiveClass() {
      return ChunkedMac.class;
   }

   public Class getInputPrimitiveClass() {
      return ChunkedMac.class;
   }

   static void register() throws GeneralSecurityException {
      MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(WRAPPER);
   }

   public static void registerToInternalPrimitiveRegistry(PrimitiveRegistry.Builder primitiveRegistryBuilder) throws GeneralSecurityException {
      primitiveRegistryBuilder.registerPrimitiveWrapper(WRAPPER);
   }

   private static class WrappedChunkedMacVerification implements ChunkedMacVerification {
      private final List verifications;

      private WrappedChunkedMacVerification(List verificationEntries) {
         this.verifications = verificationEntries;
      }

      public void update(ByteBuffer data) throws GeneralSecurityException {
         ByteBuffer clonedData = data.duplicate();
         clonedData.mark();

         for(ChunkedMacVerification entry : this.verifications) {
            clonedData.reset();
            entry.update(clonedData);
         }

         data.position(data.limit());
      }

      public void verifyMac() throws GeneralSecurityException {
         GeneralSecurityException errorSink = new GeneralSecurityException("MAC verification failed for all suitable keys in keyset");

         for(ChunkedMacVerification entry : this.verifications) {
            try {
               entry.verifyMac();
               return;
            } catch (GeneralSecurityException e) {
               errorSink.addSuppressed(e);
            }
         }

         throw errorSink;
      }
   }

   @Immutable
   private static class WrappedChunkedMac implements ChunkedMac {
      private final PrimitiveSet primitives;

      private WrappedChunkedMac(PrimitiveSet primitives) {
         this.primitives = primitives;
      }

      public ChunkedMacComputation createComputation() throws GeneralSecurityException {
         return this.getChunkedMac(this.primitives.getPrimary()).createComputation();
      }

      private ChunkedMac getChunkedMac(PrimitiveSet.Entry entry) {
         return (ChunkedMac)entry.getFullPrimitive();
      }

      public ChunkedMacVerification createVerification(final byte[] tag) throws GeneralSecurityException {
         byte[] prefix = Arrays.copyOf(tag, 5);
         List<ChunkedMacVerification> verifications = new ArrayList();

         for(PrimitiveSet.Entry primitive : this.primitives.getPrimitive(prefix)) {
            verifications.add(this.getChunkedMac(primitive).createVerification(tag));
         }

         for(PrimitiveSet.Entry primitive : this.primitives.getRawPrimitives()) {
            verifications.add(this.getChunkedMac(primitive).createVerification(tag));
         }

         return new WrappedChunkedMacVerification(verifications);
      }
   }
}
