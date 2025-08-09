package org.bouncycastle.crypto.signers;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class Ed448Signer implements Signer {
   private final Buffer buffer = new Buffer();
   private final byte[] context;
   private boolean forSigning;
   private Ed448PrivateKeyParameters privateKey;
   private Ed448PublicKeyParameters publicKey;

   public Ed448Signer(byte[] var1) {
      if (null == var1) {
         throw new NullPointerException("'context' cannot be null");
      } else {
         this.context = Arrays.clone(var1);
      }
   }

   public void init(boolean var1, CipherParameters var2) {
      this.forSigning = var1;
      if (var2 instanceof ParametersWithRandom) {
         var2 = ((ParametersWithRandom)var2).getParameters();
      }

      if (var1) {
         this.privateKey = (Ed448PrivateKeyParameters)var2;
         this.publicKey = null;
      } else {
         this.privateKey = null;
         this.publicKey = (Ed448PublicKeyParameters)var2;
      }

      CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("Ed448", 224, var2, var1));
      this.reset();
   }

   public void update(byte var1) {
      this.buffer.write(var1);
   }

   public void update(byte[] var1, int var2, int var3) {
      this.buffer.write(var1, var2, var3);
   }

   public byte[] generateSignature() {
      if (this.forSigning && null != this.privateKey) {
         return this.buffer.generateSignature(this.privateKey, this.context);
      } else {
         throw new IllegalStateException("Ed448Signer not initialised for signature generation.");
      }
   }

   public boolean verifySignature(byte[] var1) {
      if (!this.forSigning && null != this.publicKey) {
         return this.buffer.verifySignature(this.publicKey, this.context, var1);
      } else {
         throw new IllegalStateException("Ed448Signer not initialised for verification");
      }
   }

   public void reset() {
      this.buffer.reset();
   }

   private static final class Buffer extends ByteArrayOutputStream {
      private Buffer() {
      }

      synchronized byte[] generateSignature(Ed448PrivateKeyParameters var1, byte[] var2) {
         byte[] var3 = new byte[114];
         var1.sign(0, var2, this.buf, 0, this.count, var3, 0);
         this.reset();
         return var3;
      }

      synchronized boolean verifySignature(Ed448PublicKeyParameters var1, byte[] var2, byte[] var3) {
         if (114 != var3.length) {
            this.reset();
            return false;
         } else {
            boolean var4 = var1.verify(0, var2, this.buf, 0, this.count, var3, 0);
            this.reset();
            return var4;
         }
      }

      public synchronized void reset() {
         Arrays.fill((byte[])this.buf, 0, this.count, (byte)0);
         this.count = 0;
      }
   }
}
