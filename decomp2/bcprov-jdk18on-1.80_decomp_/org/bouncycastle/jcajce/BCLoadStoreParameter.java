package org.bouncycastle.jcajce;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;

public class BCLoadStoreParameter implements KeyStore.LoadStoreParameter {
   private final InputStream in;
   private final OutputStream out;
   private final KeyStore.ProtectionParameter protectionParameter;

   public BCLoadStoreParameter(OutputStream var1, char[] var2) {
      this((OutputStream)var1, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var2)));
   }

   public BCLoadStoreParameter(InputStream var1, char[] var2) {
      this((InputStream)var1, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var2)));
   }

   public BCLoadStoreParameter(InputStream var1, KeyStore.ProtectionParameter var2) {
      this(var1, (OutputStream)null, var2);
   }

   public BCLoadStoreParameter(OutputStream var1, KeyStore.ProtectionParameter var2) {
      this((InputStream)null, var1, var2);
   }

   BCLoadStoreParameter(InputStream var1, OutputStream var2, KeyStore.ProtectionParameter var3) {
      this.in = var1;
      this.out = var2;
      this.protectionParameter = var3;
   }

   public KeyStore.ProtectionParameter getProtectionParameter() {
      return this.protectionParameter;
   }

   public OutputStream getOutputStream() {
      if (this.out == null) {
         throw new UnsupportedOperationException("parameter not configured for storage - no OutputStream");
      } else {
         return this.out;
      }
   }

   public InputStream getInputStream() {
      if (this.out != null) {
         throw new UnsupportedOperationException("parameter configured for storage OutputStream present");
      } else {
         return this.in;
      }
   }
}
