package org.bouncycastle.crypto.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AlphabetMapper;

public class BasicAlphabetMapper implements AlphabetMapper {
   private Map indexMap;
   private Map charMap;

   public BasicAlphabetMapper(String var1) {
      this(var1.toCharArray());
   }

   public BasicAlphabetMapper(char[] var1) {
      this.indexMap = new HashMap();
      this.charMap = new HashMap();

      for(int var2 = 0; var2 != var1.length; ++var2) {
         if (this.indexMap.containsKey(var1[var2])) {
            throw new IllegalArgumentException("duplicate key detected in alphabet: " + var1[var2]);
         }

         this.indexMap.put(var1[var2], var2);
         this.charMap.put(var2, var1[var2]);
      }

   }

   public int getRadix() {
      return this.indexMap.size();
   }

   public byte[] convertToIndexes(char[] var1) {
      byte[] var2;
      if (this.indexMap.size() <= 256) {
         var2 = new byte[var1.length];

         for(int var3 = 0; var3 != var1.length; ++var3) {
            var2[var3] = ((Integer)this.indexMap.get(var1[var3])).byteValue();
         }
      } else {
         var2 = new byte[var1.length * 2];

         for(int var5 = 0; var5 != var1.length; ++var5) {
            int var4 = (Integer)this.indexMap.get(var1[var5]);
            var2[var5 * 2] = (byte)(var4 >> 8 & 255);
            var2[var5 * 2 + 1] = (byte)(var4 & 255);
         }
      }

      return var2;
   }

   public char[] convertToChars(byte[] var1) {
      char[] var2;
      if (this.charMap.size() <= 256) {
         var2 = new char[var1.length];

         for(int var3 = 0; var3 != var1.length; ++var3) {
            var2[var3] = (Character)this.charMap.get(var1[var3] & 255);
         }
      } else {
         if ((var1.length & 1) != 0) {
            throw new IllegalArgumentException("two byte radix and input string odd length");
         }

         var2 = new char[var1.length / 2];

         for(int var4 = 0; var4 != var1.length; var4 += 2) {
            var2[var4 / 2] = (Character)this.charMap.get(var1[var4] << 8 & '\uff00' | var1[var4 + 1] & 255);
         }
      }

      return var2;
   }
}
