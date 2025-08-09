package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class FormatableBitSet implements Formatable, Cloneable {
   private byte[] value;
   private byte bitsInLastByte;
   private transient int lengthAsBits;

   private final void checkPosition(int var1) {
      if (var1 < 0 || this.lengthAsBits <= var1) {
         throw new IllegalArgumentException("Bit position " + var1 + " is outside the legal range");
      }
   }

   private static int udiv8(int var0) {
      return var0 >> 3;
   }

   private static byte umod8(int var0) {
      return (byte)(var0 & 7);
   }

   private static int umul8(int var0) {
      return var0 << 3;
   }

   public FormatableBitSet() {
      this.value = ArrayUtil.EMPTY_BYTE_ARRAY;
   }

   public FormatableBitSet(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Bit set size " + var1 + " is not allowed");
      } else {
         this.initializeBits(var1);
      }
   }

   private void initializeBits(int var1) {
      int var2 = numBytesFromBits(var1);
      this.value = new byte[var2];
      this.bitsInLastByte = numBitsInLastByte(var1);
      this.lengthAsBits = var1;
   }

   public FormatableBitSet(byte[] var1) {
      this.value = ArrayUtil.copy(var1);
      this.bitsInLastByte = 8;
      this.lengthAsBits = this.calculateLength(var1.length);
   }

   public FormatableBitSet(FormatableBitSet var1) {
      this.bitsInLastByte = var1.bitsInLastByte;
      this.lengthAsBits = var1.lengthAsBits;
      int var2 = numBytesFromBits(var1.lengthAsBits);
      this.value = new byte[var2];
      if (var2 > 0) {
         System.arraycopy(var1.value, 0, this.value, 0, var2);
      }

   }

   public Object clone() {
      return new FormatableBitSet(this);
   }

   public boolean invariantHolds() {
      int var1 = this.value.length * 8;
      if (this.lengthAsBits > var1) {
         return false;
      } else {
         int var2 = (this.lengthAsBits - 1) / 8;
         if (this.bitsInLastByte != this.lengthAsBits - 8 * var2) {
            return false;
         } else if (this.value.length == 0) {
            return true;
         } else {
            byte var3 = this.value[var2];
            var3 = (byte)(var3 << this.bitsInLastByte);

            for(int var4 = var2 + 1; var4 < this.value.length; ++var4) {
               var3 |= this.value[var4];
            }

            return var3 == 0;
         }
      }
   }

   public int getLengthInBytes() {
      return numBytesFromBits(this.lengthAsBits);
   }

   public int getLength() {
      return this.lengthAsBits;
   }

   private int calculateLength(int var1) {
      return var1 == 0 ? 0 : (var1 - 1) * 8 + this.bitsInLastByte;
   }

   public int size() {
      return this.getLength();
   }

   public byte[] getByteArray() {
      int var1 = this.getLengthInBytes();
      if (this.value.length != var1) {
         byte[] var2 = new byte[var1];
         System.arraycopy(this.value, 0, var2, 0, var1);
         this.value = var2;
      }

      return ArrayUtil.copy(this.value);
   }

   public void grow(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Bit set cannot grow from " + this.lengthAsBits + " to " + var1 + " bits");
      } else if (var1 > this.lengthAsBits) {
         int var2 = numBytesFromBits(var1);
         if (var2 > this.value.length) {
            byte[] var3 = new byte[var2];
            int var4 = this.getLengthInBytes();
            System.arraycopy(this.value, 0, var3, 0, var4);
            this.value = var3;
         }

         this.bitsInLastByte = numBitsInLastByte(var1);
         this.lengthAsBits = var1;
      }
   }

   public void shrink(int var1) {
      if (var1 >= 0 && var1 <= this.lengthAsBits) {
         int var2 = numBytesFromBits(var1);
         this.bitsInLastByte = numBitsInLastByte(var1);
         this.lengthAsBits = var1;

         for(int var3 = var2; var3 < this.value.length; ++var3) {
            this.value[var3] = 0;
         }

         if (var2 > 0) {
            byte[] var10000 = this.value;
            var10000[var2 - 1] = (byte)(var10000[var2 - 1] & '\uff00' >> this.bitsInLastByte);
         }

      } else {
         throw new IllegalArgumentException("Bit set cannot shrink from " + this.lengthAsBits + " to " + var1 + " bits");
      }
   }

   public boolean equals(Object var1) {
      if (var1 instanceof FormatableBitSet var2) {
         if (this.getLength() != var2.getLength()) {
            return false;
         } else {
            return this.compare(var2) == 0;
         }
      } else {
         return false;
      }
   }

   public int compare(FormatableBitSet var1) {
      byte[] var6 = var1.value;
      int var4 = var1.getLengthInBytes();
      int var5 = this.getLengthInBytes();
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var4 && var3 < var5 && var6[var2] == this.value[var3]; ++var3) {
         ++var2;
      }

      if (var2 == var4 && var3 == var5) {
         if (this.getLength() == var1.getLength()) {
            return 0;
         } else {
            return var1.getLength() < this.getLength() ? 1 : -1;
         }
      } else if (var2 == var4) {
         return 1;
      } else if (var3 == var5) {
         return -1;
      } else {
         int var7 = var6[var2];
         var7 &= 255;
         int var8 = this.value[var3];
         var8 &= 255;
         return var8 > var7 ? 1 : -1;
      }
   }

   public int hashCode() {
      int var1 = 0;
      int var3 = 0;
      int var4 = this.getLengthInBytes();

      for(int var2 = 0; var2 < var4; ++var2) {
         var1 ^= (this.value[var2] & 255) << var3;
         var3 += 8;
         if (32 <= var3) {
            var3 = 0;
         }
      }

      return var1;
   }

   public final boolean isSet(int var1) {
      this.checkPosition(var1);
      int var2 = udiv8(var1);
      byte var3 = umod8(var1);
      return (this.value[var2] & 128 >> var3) != 0;
   }

   public final boolean get(int var1) {
      return this.isSet(var1);
   }

   public void set(int var1) {
      this.checkPosition(var1);
      int var2 = udiv8(var1);
      byte var3 = umod8(var1);
      byte[] var10000 = this.value;
      var10000[var2] = (byte)(var10000[var2] | 128 >> var3);
   }

   public void clear(int var1) {
      this.checkPosition(var1);
      int var2 = udiv8(var1);
      byte var3 = umod8(var1);
      byte[] var10000 = this.value;
      var10000[var2] = (byte)(var10000[var2] & ~(128 >> var3));
   }

   public void clear() {
      int var1 = this.getLengthInBytes();

      for(int var2 = 0; var2 < var1; ++var2) {
         this.value[var2] = 0;
      }

   }

   private static int numBytesFromBits(int var0) {
      return var0 + 7 >> 3;
   }

   private static byte numBitsInLastByte(int var0) {
      if (var0 == 0) {
         return 0;
      } else {
         byte var1 = umod8(var0);
         return var1 != 0 ? var1 : 8;
      }
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer(this.getLength() * 8 * 3);
      var1.append("{");
      boolean var2 = true;

      for(int var3 = 0; var3 < this.getLength(); ++var3) {
         if (this.isSet(var3)) {
            if (!var2) {
               var1.append(", ");
            }

            var2 = false;
            var1.append(var3);
         }
      }

      var1.append("}");
      return new String(var1);
   }

   public static int maxBitsForSpace(int var0) {
      return (var0 - 4) * 8;
   }

   private static byte firstSet(byte var0) {
      if ((var0 & 128) != 0) {
         return 0;
      } else if ((var0 & 64) != 0) {
         return 1;
      } else if ((var0 & 32) != 0) {
         return 2;
      } else if ((var0 & 16) != 0) {
         return 3;
      } else if ((var0 & 8) != 0) {
         return 4;
      } else if ((var0 & 4) != 0) {
         return 5;
      } else {
         return (byte)((var0 & 2) != 0 ? 6 : 7);
      }
   }

   public int anySetBit() {
      int var1 = this.getLengthInBytes();

      for(int var2 = 0; var2 < var1; ++var2) {
         byte var3 = this.value[var2];
         if (var3 != 0) {
            return umul8(var2) + firstSet(var3);
         }
      }

      return -1;
   }

   public int anySetBit(int var1) {
      ++var1;
      if (var1 >= this.lengthAsBits) {
         return -1;
      } else {
         int var2 = udiv8(var1);
         byte var3 = (byte)(this.value[var2] << umod8(var1));
         if (var3 != 0) {
            return var1 + firstSet(var3);
         } else {
            int var4 = this.getLengthInBytes();
            ++var2;

            while(var2 < var4) {
               var3 = this.value[var2];
               if (var3 != 0) {
                  return umul8(var2) + firstSet(var3);
               }

               ++var2;
            }

            return -1;
         }
      }
   }

   public void or(FormatableBitSet var1) {
      if (var1 != null) {
         int var2 = var1.getLength();
         if (var2 > this.getLength()) {
            this.grow(var2);
         }

         int var3 = var1.getLengthInBytes();

         for(int var4 = 0; var4 < var3; ++var4) {
            byte[] var10000 = this.value;
            var10000[var4] |= var1.value[var4];
         }

      }
   }

   public void copyFrom(FormatableBitSet var1) {
      System.arraycopy(var1.getByteArray(), 0, this.value, 0, var1.getLengthInBytes());
   }

   public void and(FormatableBitSet var1) {
      if (var1 == null) {
         this.clear();
      } else {
         int var2 = var1.getLength();
         if (var2 > this.getLength()) {
            this.grow(var2);
         }

         int var3 = var1.getLengthInBytes();

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            byte[] var10000 = this.value;
            var10000[var4] &= var1.value[var4];
         }

         for(int var5 = this.getLengthInBytes(); var4 < var5; ++var4) {
            this.value[var4] = 0;
         }

      }
   }

   public void xor(FormatableBitSet var1) {
      if (var1 != null) {
         int var2 = var1.getLength();
         if (var2 > this.getLength()) {
            this.grow(var2);
         }

         int var3 = var1.getLengthInBytes();

         for(int var4 = 0; var4 < var3; ++var4) {
            byte[] var10000 = this.value;
            var10000[var4] ^= var1.value[var4];
         }

      }
   }

   public int getNumBitsSet() {
      int var1 = 0;
      int var2 = this.getLengthInBytes();

      for(int var3 = 0; var3 < var2; ++var3) {
         byte var4 = this.value[var3];
         var4 = (byte)(var4 - (var4 >> 1 & 85));
         var4 = (byte)((var4 & 51) + (var4 >> 2 & 51));
         var4 = (byte)((var4 & 7) + (var4 >> 4));
         var1 += var4;
      }

      return var1;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.getLength());
      int var2 = this.getLengthInBytes();
      if (var2 > 0) {
         var1.write(this.value, 0, var2);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException {
      int var2 = var1.readInt();
      int var3 = numBytesFromBits(var2);
      this.value = new byte[var3];
      var1.readFully(this.value);
      this.bitsInLastByte = numBitsInLastByte(var2);
      this.lengthAsBits = var2;
   }

   public int getTypeFormatId() {
      return 269;
   }
}
