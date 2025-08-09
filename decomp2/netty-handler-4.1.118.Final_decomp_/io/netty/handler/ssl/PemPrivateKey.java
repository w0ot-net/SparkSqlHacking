package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.CharsetUtil;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;
import java.security.PrivateKey;

public final class PemPrivateKey extends AbstractReferenceCounted implements PrivateKey, PemEncoded {
   private static final long serialVersionUID = 7978017465645018936L;
   private static final byte[] BEGIN_PRIVATE_KEY;
   private static final byte[] END_PRIVATE_KEY;
   private static final String PKCS8_FORMAT = "PKCS#8";
   private final ByteBuf content;

   static PemEncoded toPEM(ByteBufAllocator allocator, boolean useDirect, PrivateKey key) {
      if (key instanceof PemEncoded) {
         return ((PemEncoded)key).retain();
      } else {
         byte[] bytes = key.getEncoded();
         if (bytes == null) {
            throw new IllegalArgumentException(key.getClass().getName() + " does not support encoding");
         } else {
            return toPEM(allocator, useDirect, bytes);
         }
      }
   }

   static PemEncoded toPEM(ByteBufAllocator allocator, boolean useDirect, byte[] bytes) {
      ByteBuf encoded = Unpooled.wrappedBuffer(bytes);

      PemValue var9;
      try {
         ByteBuf base64 = SslUtils.toBase64(allocator, encoded);

         try {
            int size = BEGIN_PRIVATE_KEY.length + base64.readableBytes() + END_PRIVATE_KEY.length;
            boolean success = false;
            ByteBuf pem = useDirect ? allocator.directBuffer(size) : allocator.buffer(size);

            try {
               pem.writeBytes(BEGIN_PRIVATE_KEY);
               pem.writeBytes(base64);
               pem.writeBytes(END_PRIVATE_KEY);
               PemValue value = new PemValue(pem, true);
               success = true;
               var9 = value;
            } finally {
               if (!success) {
                  SslUtils.zerooutAndRelease(pem);
               }

            }
         } finally {
            SslUtils.zerooutAndRelease(base64);
         }
      } finally {
         SslUtils.zerooutAndRelease(encoded);
      }

      return var9;
   }

   public static PemPrivateKey valueOf(byte[] key) {
      return valueOf(Unpooled.wrappedBuffer(key));
   }

   public static PemPrivateKey valueOf(ByteBuf key) {
      return new PemPrivateKey(key);
   }

   private PemPrivateKey(ByteBuf content) {
      this.content = (ByteBuf)ObjectUtil.checkNotNull(content, "content");
   }

   public boolean isSensitive() {
      return true;
   }

   public ByteBuf content() {
      int count = this.refCnt();
      if (count <= 0) {
         throw new IllegalReferenceCountException(count);
      } else {
         return this.content;
      }
   }

   public PemPrivateKey copy() {
      return this.replace(this.content.copy());
   }

   public PemPrivateKey duplicate() {
      return this.replace(this.content.duplicate());
   }

   public PemPrivateKey retainedDuplicate() {
      return this.replace(this.content.retainedDuplicate());
   }

   public PemPrivateKey replace(ByteBuf content) {
      return new PemPrivateKey(content);
   }

   public PemPrivateKey touch() {
      this.content.touch();
      return this;
   }

   public PemPrivateKey touch(Object hint) {
      this.content.touch(hint);
      return this;
   }

   public PemPrivateKey retain() {
      return (PemPrivateKey)super.retain();
   }

   public PemPrivateKey retain(int increment) {
      return (PemPrivateKey)super.retain(increment);
   }

   protected void deallocate() {
      SslUtils.zerooutAndRelease(this.content);
   }

   public byte[] getEncoded() {
      throw new UnsupportedOperationException();
   }

   public String getAlgorithm() {
      throw new UnsupportedOperationException();
   }

   public String getFormat() {
      return "PKCS#8";
   }

   public void destroy() {
      this.release(this.refCnt());
   }

   public boolean isDestroyed() {
      return this.refCnt() == 0;
   }

   static {
      BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n".getBytes(CharsetUtil.US_ASCII);
      END_PRIVATE_KEY = "\n-----END PRIVATE KEY-----\n".getBytes(CharsetUtil.US_ASCII);
   }
}
