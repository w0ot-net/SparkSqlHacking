package org.sparkproject.guava.hash;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.ByteBuffer;
import java.util.zip.Checksum;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Throwables;

@Immutable
@ElementTypesAreNonnullByDefault
final class ChecksumHashFunction extends AbstractHashFunction implements Serializable {
   private final ImmutableSupplier checksumSupplier;
   private final int bits;
   private final String toString;
   private static final long serialVersionUID = 0L;

   ChecksumHashFunction(ImmutableSupplier checksumSupplier, int bits, String toString) {
      this.checksumSupplier = (ImmutableSupplier)Preconditions.checkNotNull(checksumSupplier);
      Preconditions.checkArgument(bits == 32 || bits == 64, "bits (%s) must be either 32 or 64", bits);
      this.bits = bits;
      this.toString = (String)Preconditions.checkNotNull(toString);
   }

   public int bits() {
      return this.bits;
   }

   public Hasher newHasher() {
      return new ChecksumHasher((Checksum)this.checksumSupplier.get());
   }

   public String toString() {
      return this.toString;
   }

   private final class ChecksumHasher extends AbstractByteHasher {
      private final Checksum checksum;

      private ChecksumHasher(Checksum checksum) {
         this.checksum = (Checksum)Preconditions.checkNotNull(checksum);
      }

      protected void update(byte b) {
         this.checksum.update(b);
      }

      protected void update(byte[] bytes, int off, int len) {
         this.checksum.update(bytes, off, len);
      }

      protected void update(ByteBuffer b) {
         if (!ChecksumHashFunction.ChecksumMethodHandles.updateByteBuffer(this.checksum, b)) {
            super.update(b);
         }

      }

      public HashCode hash() {
         long value = this.checksum.getValue();
         return ChecksumHashFunction.this.bits == 32 ? HashCode.fromInt((int)value) : HashCode.fromLong(value);
      }
   }

   private static final class ChecksumMethodHandles {
      private static final @Nullable MethodHandle UPDATE_BB = updateByteBuffer();

      @IgnoreJRERequirement
      static boolean updateByteBuffer(Checksum cs, ByteBuffer bb) {
         if (UPDATE_BB != null) {
            try {
               UPDATE_BB.invokeExact(cs, bb);
               return true;
            } catch (Throwable e) {
               Throwables.throwIfUnchecked(e);
               throw new UndeclaredThrowableException(e);
            }
         } else {
            return false;
         }
      }

      private static @Nullable MethodHandle updateByteBuffer() {
         try {
            Class<?> clazz = Class.forName("java.util.zip.Checksum");
            return MethodHandles.lookup().findVirtual(clazz, "update", MethodType.methodType(Void.TYPE, ByteBuffer.class));
         } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
         } catch (IllegalAccessException e) {
            throw newLinkageError(e);
         } catch (NoSuchMethodException var3) {
            return null;
         }
      }

      private static LinkageError newLinkageError(Throwable cause) {
         return new LinkageError(cause.toString(), cause);
      }
   }
}
