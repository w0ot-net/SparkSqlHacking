package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectOutput;
import org.apache.derby.iapi.db.DatabaseContext;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

public final class ClobStreamHeaderGenerator implements StreamHeaderGenerator {
   private static final byte MAGIC_BYTE = -16;
   private static final byte[] UNKNOWN_LENGTH = new byte[]{0, 0, -16, 0, 0};
   private static final CharStreamHeaderGenerator CHARHDRGEN = new CharStreamHeaderGenerator();
   private final StringDataValue callbackDVD;
   private Boolean isPreDerbyTenFive;

   public ClobStreamHeaderGenerator(StringDataValue var1) {
      if (var1 == null) {
         throw new IllegalStateException("dvd cannot be null");
      } else {
         this.callbackDVD = var1;
      }
   }

   public ClobStreamHeaderGenerator(boolean var1) {
      this.callbackDVD = null;
      this.isPreDerbyTenFive = var1;
   }

   public boolean expectsCharCount() {
      if (this.callbackDVD != null && this.isPreDerbyTenFive == null) {
         this.determineHeaderFormat();
      }

      return !this.isPreDerbyTenFive;
   }

   public int generateInto(byte[] var1, int var2, long var3) {
      if (this.callbackDVD != null && this.isPreDerbyTenFive == null) {
         this.determineHeaderFormat();
      }

      int var5 = 0;
      if (this.isPreDerbyTenFive == Boolean.FALSE) {
         if (var3 >= 0L) {
            var1[var2 + var5++] = (byte)((int)(var3 >>> 24));
            var1[var2 + var5++] = (byte)((int)(var3 >>> 16));
            var1[var2 + var5++] = -16;
            var1[var2 + var5++] = (byte)((int)(var3 >>> 8));
            var1[var2 + var5++] = (byte)((int)(var3 >>> 0));
         } else {
            var5 = UNKNOWN_LENGTH.length;
            System.arraycopy(UNKNOWN_LENGTH, 0, var1, var2, var5);
         }
      } else {
         var5 = CHARHDRGEN.generateInto(var1, var2, var3);
      }

      return var5;
   }

   public int generateInto(ObjectOutput var1, long var2) throws IOException {
      if (this.callbackDVD != null && this.isPreDerbyTenFive == null) {
         this.determineHeaderFormat();
      }

      int var4 = 0;
      if (this.isPreDerbyTenFive == Boolean.FALSE) {
         var4 = 5;
         if (var2 > 0L) {
            var1.writeByte((byte)((int)(var2 >>> 24)));
            var1.writeByte((byte)((int)(var2 >>> 16)));
            var1.writeByte(-16);
            var1.writeByte((byte)((int)(var2 >>> 8)));
            var1.writeByte((byte)((int)(var2 >>> 0)));
         } else {
            var1.write(UNKNOWN_LENGTH);
         }
      } else {
         var4 = CHARHDRGEN.generateInto(var1, var2);
      }

      return var4;
   }

   public int writeEOF(byte[] var1, int var2, long var3) {
      if (this.callbackDVD != null && this.isPreDerbyTenFive == null) {
         this.determineHeaderFormat();
      }

      if (!this.isPreDerbyTenFive) {
         return var3 < 0L ? CharStreamHeaderGenerator.writeEOFMarker(var1, var2) : 0;
      } else {
         return CHARHDRGEN.writeEOF(var1, var2, var3);
      }
   }

   public int writeEOF(ObjectOutput var1, long var2) throws IOException {
      if (this.callbackDVD != null && this.isPreDerbyTenFive == null) {
         this.determineHeaderFormat();
      }

      if (!this.isPreDerbyTenFive) {
         return var2 < 0L ? CharStreamHeaderGenerator.writeEOFMarker(var1) : 0;
      } else {
         return CHARHDRGEN.writeEOF(var1, var2);
      }
   }

   public int getMaxHeaderLength() {
      return 5;
   }

   private void determineHeaderFormat() {
      DatabaseContext var1 = (DatabaseContext)getContext("Database");
      if (var1 == null) {
         throw new IllegalStateException("No context, unable to determine which stream header format to generate");
      } else {
         DataDictionary var2 = var1.getDatabase().getDataDictionary();

         try {
            this.isPreDerbyTenFive = !var2.checkVersion(170, (String)null);
         } catch (StandardException var5) {
            IllegalStateException var4 = new IllegalStateException(var5.getMessage());
            var4.initCause(var5);
            throw var4;
         }

         this.callbackDVD.setStreamHeaderFormat(this.isPreDerbyTenFive);
      }
   }

   private static Context getContext(String var0) {
      return ContextService.getContext(var0);
   }
}
