package org.bouncycastle.crypto;

public enum PasswordConverter implements CharToByteConverter {
   ASCII {
      public String getType() {
         return "ASCII";
      }

      public byte[] convert(char[] var1) {
         return PBEParametersGenerator.PKCS5PasswordToBytes(var1);
      }
   },
   UTF8 {
      public String getType() {
         return "UTF8";
      }

      public byte[] convert(char[] var1) {
         return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(var1);
      }
   },
   PKCS12 {
      public String getType() {
         return "PKCS12";
      }

      public byte[] convert(char[] var1) {
         return PBEParametersGenerator.PKCS12PasswordToBytes(var1);
      }
   };

   private PasswordConverter() {
   }

   // $FF: synthetic method
   private static PasswordConverter[] $values() {
      return new PasswordConverter[]{ASCII, UTF8, PKCS12};
   }
}
