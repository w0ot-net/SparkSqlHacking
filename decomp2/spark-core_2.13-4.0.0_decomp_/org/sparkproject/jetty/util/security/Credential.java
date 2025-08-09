package org.sparkproject.jetty.util.security;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class Credential implements Serializable {
   private static final long serialVersionUID = -7760551052768181572L;
   private static final List CREDENTIAL_PROVIDERS = (List)ServiceLoader.load(CredentialProvider.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());

   public abstract boolean check(Object var1);

   public static Credential getCredential(String credential) {
      if (credential.startsWith("CRYPT:")) {
         return new Crypt(credential);
      } else if (credential.startsWith("MD5:")) {
         return new MD5(credential);
      } else {
         for(CredentialProvider cp : CREDENTIAL_PROVIDERS) {
            if (credential.startsWith(cp.getPrefix())) {
               Credential credentialObj = cp.getCredential(credential);
               if (credentialObj != null) {
                  return credentialObj;
               }
            }
         }

         return new Password(credential);
      }
   }

   protected static boolean stringEquals(String known, String unknown) {
      boolean sameObject = known == unknown;
      if (sameObject) {
         return true;
      } else if (known != null && unknown != null) {
         boolean result = true;
         int l1 = known.length();
         int l2 = unknown.length();

         for(int i = 0; i < l2; ++i) {
            result &= (l1 == 0 ? unknown.charAt(l2 - i - 1) : known.charAt(i % l1)) == unknown.charAt(i);
         }

         return result && l1 == l2;
      } else {
         return false;
      }
   }

   protected static boolean byteEquals(byte[] known, byte[] unknown) {
      if (known == unknown) {
         return true;
      } else if (known != null && unknown != null) {
         boolean result = true;
         int l1 = known.length;
         int l2 = unknown.length;

         for(int i = 0; i < l2; ++i) {
            result &= (l1 == 0 ? unknown[l2 - i - 1] : known[i % l1]) == unknown[i];
         }

         return result && l1 == l2;
      } else {
         return false;
      }
   }

   public static class Crypt extends Credential {
      private static final long serialVersionUID = -2027792997664744210L;
      private static final String __TYPE = "CRYPT:";
      private final String _cooked;

      Crypt(String cooked) {
         this._cooked = cooked.startsWith("CRYPT:") ? cooked.substring("CRYPT:".length()) : cooked;
      }

      public boolean check(Object credentials) {
         if (credentials instanceof char[]) {
            credentials = new String((char[])credentials);
         }

         return stringEquals(this._cooked, UnixCrypt.crypt(credentials.toString(), this._cooked));
      }

      public boolean equals(Object credential) {
         if (!(credential instanceof Crypt)) {
            return false;
         } else {
            Crypt c = (Crypt)credential;
            return stringEquals(this._cooked, c._cooked);
         }
      }

      public static String crypt(String user, String pw) {
         String var10000 = UnixCrypt.crypt(pw, user);
         return "CRYPT:" + var10000;
      }
   }

   public static class MD5 extends Credential {
      private static final long serialVersionUID = 5533846540822684240L;
      private static final String __TYPE = "MD5:";
      private static final AutoLock __md5Lock = new AutoLock();
      private static MessageDigest __md;
      private final byte[] _digest;

      MD5(String digest) {
         digest = digest.startsWith("MD5:") ? digest.substring("MD5:".length()) : digest;
         this._digest = StringUtil.fromHexString(digest);
      }

      public byte[] getDigest() {
         return this._digest;
      }

      public boolean check(Object credentials) {
         try {
            if (credentials instanceof char[]) {
               credentials = new String((char[])credentials);
            }

            if (!(credentials instanceof Password) && !(credentials instanceof String)) {
               if (credentials instanceof MD5) {
                  return this.equals(credentials);
               } else {
                  return credentials instanceof Credential ? ((Credential)credentials).check(this) : false;
               }
            } else {
               byte[] digest;
               try (AutoLock l = __md5Lock.lock()) {
                  if (__md == null) {
                     __md = MessageDigest.getInstance("MD5");
                  }

                  __md.reset();
                  __md.update(credentials.toString().getBytes(StandardCharsets.ISO_8859_1));
                  digest = __md.digest();
               }

               return byteEquals(this._digest, digest);
            }
         } catch (Exception var8) {
            return false;
         }
      }

      public boolean equals(Object obj) {
         return obj instanceof MD5 ? byteEquals(this._digest, ((MD5)obj)._digest) : false;
      }

      public static String digest(String password) {
         try {
            try (AutoLock l = __md5Lock.lock()) {
               if (__md == null) {
                  try {
                     __md = MessageDigest.getInstance("MD5");
                  } catch (Exception e) {
                     System.err.println("Unable to access MD5 message digest");
                     e.printStackTrace();
                     return null;
                  }
               }

               __md.reset();
               __md.update(password.getBytes(StandardCharsets.ISO_8859_1));
               byte[] digest = __md.digest();
               return "MD5:" + StringUtil.toHexString(digest);
            }
         } catch (Exception e) {
            System.err.println("Message Digest Failure");
            e.printStackTrace();
            return null;
         }
      }
   }
}
