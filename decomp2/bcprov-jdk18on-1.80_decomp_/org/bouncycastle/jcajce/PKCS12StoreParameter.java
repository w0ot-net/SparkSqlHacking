package org.bouncycastle.jcajce;

import java.io.OutputStream;
import java.security.KeyStore;

public class PKCS12StoreParameter implements KeyStore.LoadStoreParameter {
   private final OutputStream out;
   private final KeyStore.ProtectionParameter protectionParameter;
   private final boolean forDEREncoding;
   private final boolean overwriteFriendlyName;

   public PKCS12StoreParameter(OutputStream var1, char[] var2) {
      this(var1, var2, false);
   }

   public PKCS12StoreParameter(OutputStream var1, KeyStore.ProtectionParameter var2) {
      this(var1, var2, false, true);
   }

   public PKCS12StoreParameter(OutputStream var1, char[] var2, boolean var3) {
      this(var1, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var2)), var3, true);
   }

   public PKCS12StoreParameter(OutputStream var1, KeyStore.ProtectionParameter var2, boolean var3) {
      this(var1, var2, var3, true);
   }

   public PKCS12StoreParameter(OutputStream var1, char[] var2, boolean var3, boolean var4) {
      this(var1, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var2)), var3, var4);
   }

   public PKCS12StoreParameter(OutputStream var1, KeyStore.ProtectionParameter var2, boolean var3, boolean var4) {
      this.out = var1;
      this.protectionParameter = var2;
      this.forDEREncoding = var3;
      this.overwriteFriendlyName = var4;
   }

   public OutputStream getOutputStream() {
      return this.out;
   }

   public KeyStore.ProtectionParameter getProtectionParameter() {
      return this.protectionParameter;
   }

   public boolean isForDEREncoding() {
      return this.forDEREncoding;
   }

   public boolean isOverwriteFriendlyName() {
      return this.overwriteFriendlyName;
   }
}
