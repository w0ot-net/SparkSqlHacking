package org.apache.derby.impl.services.uuid;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StringReader;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.Formatable;

public class BasicUUID implements UUID, Formatable {
   private long majorId;
   private long timemillis;
   private int sequence;

   public BasicUUID(long var1, long var3, int var5) {
      this.majorId = var1;
      this.timemillis = var3;
      this.sequence = var5;
   }

   public BasicUUID(String var1) {
      StringReader var2 = new StringReader(var1);
      this.sequence = (int)readMSB(var2);
      long var3 = readMSB(var2) << 32;
      var3 += readMSB(var2) << 16;
      var3 += readMSB(var2);
      this.timemillis = var3;
      this.majorId = readMSB(var2);
   }

   public BasicUUID() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeLong(this.majorId);
      var1.writeLong(this.timemillis);
      var1.writeInt(this.sequence);
   }

   public void readExternal(ObjectInput var1) throws IOException {
      this.majorId = var1.readLong();
      this.timemillis = var1.readLong();
      this.sequence = var1.readInt();
   }

   public int getTypeFormatId() {
      return 131;
   }

   private static void writeMSB(char[] var0, int var1, long var2, int var4) {
      for(int var5 = var4 - 1; var5 >= 0; --var5) {
         long var6 = (var2 & 255L << 8 * var5) >>> 8 * var5;
         int var8 = (int)((var6 & 240L) >> 4);
         var0[var1++] = (char)(var8 < 10 ? var8 + 48 : var8 - 10 + 97);
         var8 = (int)(var6 & 15L);
         var0[var1++] = (char)(var8 < 10 ? var8 + 48 : var8 - 10 + 97);
      }

   }

   private static long readMSB(StringReader var0) {
      long var1 = 0L;

      int var3;
      int var4;
      try {
         for(; (var3 = var0.read()) != -1 && var3 != 45; var1 += (long)var4) {
            var1 <<= 4;
            if (var3 <= 57) {
               var4 = var3 - 48;
            } else if (var3 <= 70) {
               var4 = var3 - 65 + 10;
            } else {
               var4 = var3 - 97 + 10;
            }
         }
      } catch (Exception var5) {
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof BasicUUID var2)) {
         return false;
      } else {
         return this.sequence == var2.sequence && this.timemillis == var2.timemillis && this.majorId == var2.majorId;
      }
   }

   public int hashCode() {
      long var1 = this.majorId ^ this.timemillis;
      return this.sequence ^ (int)(var1 >> 4);
   }

   public String toString() {
      return this.stringWorkhorse('-');
   }

   public String toANSIidentifier() {
      return "U" + this.stringWorkhorse('X');
   }

   public String stringWorkhorse(char var1) {
      char[] var2 = new char[36];
      writeMSB(var2, 0, (long)this.sequence, 4);
      int var3 = 8;
      if (var1 != 0) {
         var2[var3++] = var1;
      }

      long var4 = this.timemillis;
      writeMSB(var2, var3, (var4 & 281470681743360L) >>> 32, 2);
      var3 += 4;
      if (var1 != 0) {
         var2[var3++] = var1;
      }

      writeMSB(var2, var3, (var4 & 4294901760L) >>> 16, 2);
      var3 += 4;
      if (var1 != 0) {
         var2[var3++] = var1;
      }

      writeMSB(var2, var3, var4 & 65535L, 2);
      var3 += 4;
      if (var1 != 0) {
         var2[var3++] = var1;
      }

      writeMSB(var2, var3, this.majorId, 6);
      var3 += 12;
      return new String(var2, 0, var3);
   }

   public UUID cloneMe() {
      return new BasicUUID(this.majorId, this.timemillis, this.sequence);
   }
}
