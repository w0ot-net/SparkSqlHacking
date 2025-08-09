package org.bouncycastle.jcajce.provider.keystore.util;

import java.io.IOException;
import java.security.KeyStore;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ParameterUtil {
   public static char[] extractPassword(KeyStore.LoadStoreParameter var0) throws IOException {
      KeyStore.ProtectionParameter var1 = var0.getProtectionParameter();
      if (var1 == null) {
         return null;
      } else if (var1 instanceof KeyStore.PasswordProtection) {
         return ((KeyStore.PasswordProtection)var1).getPassword();
      } else if (var1 instanceof KeyStore.CallbackHandlerProtection) {
         CallbackHandler var2 = ((KeyStore.CallbackHandlerProtection)var1).getCallbackHandler();
         PasswordCallback var3 = new PasswordCallback("password: ", false);

         try {
            var2.handle(new Callback[]{var3});
            return var3.getPassword();
         } catch (UnsupportedCallbackException var5) {
            throw new IllegalArgumentException("PasswordCallback not recognised: " + var5.getMessage(), var5);
         }
      } else {
         throw new IllegalArgumentException("no support for protection parameter of type " + var1.getClass().getName());
      }
   }
}
