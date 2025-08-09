package org.bouncycastle.pqc.legacy.crypto.qtesla;

public class QTESLASecurityCategory {
   public static final int PROVABLY_SECURE_I = 5;
   public static final int PROVABLY_SECURE_III = 6;

   private QTESLASecurityCategory() {
   }

   static void validate(int var0) {
      switch (var0) {
         case 5:
         case 6:
            return;
         default:
            throw new IllegalArgumentException("unknown security category: " + var0);
      }
   }

   static int getPrivateSize(int var0) {
      switch (var0) {
         case 5:
            return 5224;
         case 6:
            return 12392;
         default:
            throw new IllegalArgumentException("unknown security category: " + var0);
      }
   }

   static int getPublicSize(int var0) {
      switch (var0) {
         case 5:
            return 14880;
         case 6:
            return 38432;
         default:
            throw new IllegalArgumentException("unknown security category: " + var0);
      }
   }

   static int getSignatureSize(int var0) {
      switch (var0) {
         case 5:
            return 2592;
         case 6:
            return 5664;
         default:
            throw new IllegalArgumentException("unknown security category: " + var0);
      }
   }

   public static String getName(int var0) {
      switch (var0) {
         case 5:
            return "qTESLA-p-I";
         case 6:
            return "qTESLA-p-III";
         default:
            throw new IllegalArgumentException("unknown security category: " + var0);
      }
   }
}
