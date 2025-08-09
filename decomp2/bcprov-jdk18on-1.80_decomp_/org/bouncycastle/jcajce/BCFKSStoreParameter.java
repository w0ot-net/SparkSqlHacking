package org.bouncycastle.jcajce;

import java.io.OutputStream;
import java.security.KeyStore;
import org.bouncycastle.crypto.util.PBKDFConfig;

/** @deprecated */
public class BCFKSStoreParameter implements KeyStore.LoadStoreParameter {
   private final KeyStore.ProtectionParameter protectionParameter;
   private final PBKDFConfig storeConfig;
   private OutputStream out;

   public BCFKSStoreParameter(OutputStream var1, PBKDFConfig var2, char[] var3) {
      this(var1, var2, (KeyStore.ProtectionParameter)(new KeyStore.PasswordProtection(var3)));
   }

   public BCFKSStoreParameter(OutputStream var1, PBKDFConfig var2, KeyStore.ProtectionParameter var3) {
      this.out = var1;
      this.storeConfig = var2;
      this.protectionParameter = var3;
   }

   public KeyStore.ProtectionParameter getProtectionParameter() {
      return this.protectionParameter;
   }

   public OutputStream getOutputStream() {
      return this.out;
   }

   public PBKDFConfig getStorePBKDFConfig() {
      return this.storeConfig;
   }
}
