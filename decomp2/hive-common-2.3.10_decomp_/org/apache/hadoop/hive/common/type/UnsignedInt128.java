package org.apache.hadoop.hive.common.type;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import org.apache.hive.common.util.Decimal128FastBuffer;

public final class UnsignedInt128 implements Comparable, Serializable {
   public static final int INT_COUNT = 4;
   public static final int BYTE_SIZE = 16;
   public static final int MAX_DIGITS = 38;
   public static final UnsignedInt128 MAX_VALUE = new UnsignedInt128(-1, -1, -1, -1);
   public static final UnsignedInt128 MIN_VALUE = new UnsignedInt128(0L);
   public static final UnsignedInt128 TEN_TO_THIRTYEIGHT = new UnsignedInt128(0, 160047680, 1518781562, 1262177448);
   private int[] v = new int[4];
   private byte count;

   public static int getIntsPerElement(int precision) {
      assert precision >= 0 && precision <= 38;

      if (precision <= 9) {
         return 1;
      } else if (precision <= 19) {
         return 2;
      } else {
         return precision <= 28 ? 3 : 4;
      }
   }

   public UnsignedInt128() {
      this.zeroClear();
   }

   public UnsignedInt128(UnsignedInt128 o) {
      this.update(o);
   }

   public UnsignedInt128(int v0, int v1, int v2, int v3) {
      this.update(v0, v1, v2, v3);
   }

   public UnsignedInt128(long v) {
      this.update(v);
   }

   public UnsignedInt128(String str) {
      this.update(str);
   }

   public UnsignedInt128(char[] str, int offset, int length) {
      this.update(str, offset, length);
   }

   public UnsignedInt128(BigInteger bigInt) {
      this.update(bigInt);
   }

   public void update(BigInteger bigInt) {
      int v0 = bigInt.intValue();
      int v1 = bigInt.shiftRight(32).intValue();
      int v2 = bigInt.shiftRight(64).intValue();
      int v3 = bigInt.shiftRight(96).intValue();
      this.update(v0, v1, v2, v3);
   }

   public int getV0() {
      return this.v[0];
   }

   public int getV1() {
      return this.v[1];
   }

   public int getV2() {
      return this.v[2];
   }

   public int getV3() {
      return this.v[3];
   }

   public void setV0(int val) {
      this.v[0] = val;
      this.updateCount();
   }

   public void setV1(int val) {
      this.v[1] = val;
      this.updateCount();
   }

   public void setV2(int val) {
      this.v[2] = val;
      this.updateCount();
   }

   public void setV3(int val) {
      this.v[3] = val;
      this.updateCount();
   }

   public boolean exceedsTenToThirtyEight() {
      if (this.v[3] != 1262177448) {
         return this.v[3] < 0 || this.v[3] > 1262177448;
      } else if (this.v[2] != 1518781562) {
         return this.v[2] < 0 || this.v[2] > 1518781562;
      } else {
         return this.v[1] < 0 || this.v[1] > 160047680;
      }
   }

   public void throwIfExceedsTenToThirtyEight() {
      if (this.exceedsTenToThirtyEight()) {
         SqlMathUtil.throwOverflowException();
      }

   }

   public long asLong() {
      if (this.count > 2 || this.v[1] < 0) {
         SqlMathUtil.throwOverflowException();
      }

      return (long)this.v[1] << 32 | (long)this.v[0];
   }

   public void zeroClear() {
      this.v[0] = 0;
      this.v[1] = 0;
      this.v[2] = 0;
      this.v[3] = 0;
      this.count = 0;
   }

   public boolean isZero() {
      return this.count == 0;
   }

   public boolean isOne() {
      return this.v[0] == 1 && this.count == 1;
   }

   public boolean fitsInt32() {
      return this.count <= 1;
   }

   public void update(UnsignedInt128 o) {
      this.update(o.v[0], o.v[1], o.v[2], o.v[3]);
   }

   public void update(long v) {
      assert v >= 0L;

      this.update((int)v, (int)(v >> 32), 0, 0);
   }

   public void update(int v0, int v1, int v2, int v3) {
      this.v[0] = v0;
      this.v[1] = v1;
      this.v[2] = v2;
      this.v[3] = v3;
      this.updateCount();
   }

   public void update(IntBuffer buf, int precision) {
      switch (getIntsPerElement(precision)) {
         case 1:
            this.update32(buf);
            break;
         case 2:
            this.update64(buf);
            break;
         case 3:
            this.update96(buf);
            break;
         case 4:
            this.update128(buf);
            break;
         default:
            throw new RuntimeException();
      }

   }

   public void update128(IntBuffer buf) {
      buf.get(this.v, 0, 4);
      this.updateCount();
   }

   public void update96(IntBuffer buf) {
      buf.get(this.v, 0, 3);
      this.v[3] = 0;
      this.updateCount();
   }

   public void update64(IntBuffer buf) {
      buf.get(this.v, 0, 2);
      this.v[2] = 0;
      this.v[3] = 0;
      this.updateCount();
   }

   public void update32(IntBuffer buf) {
      this.v[0] = buf.get();
      this.v[1] = 0;
      this.v[2] = 0;
      this.v[3] = 0;
      this.updateCount();
   }

   public void update(int[] array, int offset, int precision) {
      switch (getIntsPerElement(precision)) {
         case 1:
            this.update32(array, offset);
            break;
         case 2:
            this.update64(array, offset);
            break;
         case 3:
            this.update96(array, offset);
            break;
         case 4:
            this.update128(array, offset);
            break;
         default:
            throw new RuntimeException();
      }

   }

   public void update128(int[] array, int offset) {
      System.arraycopy(array, offset, this.v, 0, 4);
      this.updateCount();
   }

   public void update96(int[] array, int offset) {
      System.arraycopy(array, offset, this.v, 0, 3);
      this.v[3] = 0;
      this.updateCount();
   }

   public void update64(int[] array, int offset) {
      System.arraycopy(array, offset, this.v, 0, 2);
      this.v[2] = 0;
      this.v[3] = 0;
      this.updateCount();
   }

   public void update32(int[] array, int offset) {
      this.v[0] = array[offset];
      this.v[1] = 0;
      this.v[2] = 0;
      this.v[3] = 0;
      this.updateCount();
   }

   public void update(String str) {
      this.update((char[])str.toCharArray(), 0, str.length());
   }

   public void update(char[] str, int offset, int length) {
      int end = offset + length;

      assert end <= str.length;

      int cursor;
      for(cursor = offset; cursor < end && str[cursor] == '0'; ++cursor) {
      }

      if (cursor == end) {
         this.zeroClear();
      } else {
         if (end - cursor > 38) {
            SqlMathUtil.throwOverflowException();
         }

         int accumulated = 0;

         int accumulatedCount;
         for(accumulatedCount = 0; cursor < end; ++cursor) {
            if (str[cursor] < '0' || str[cursor] > '9') {
               throw new NumberFormatException("Invalid string:" + new String(str, offset, length));
            }

            if (accumulatedCount == 9) {
               this.scaleUpTenDestructive((short)accumulatedCount);
               this.addDestructive(accumulated);
               accumulated = 0;
               accumulatedCount = 0;
            }

            int digit = str[cursor] - 48;
            accumulated = accumulated * 10 + digit;
            ++accumulatedCount;
         }

         if (accumulatedCount > 0) {
            this.scaleUpTenDestructive((short)accumulatedCount);
            this.addDestructive(accumulated);
         }

      }
   }

   public void serializeTo(IntBuffer buf, int precision) {
      buf.put(this.v, 0, getIntsPerElement(precision));
   }

   public void serializeTo128(IntBuffer buf) {
      buf.put(this.v, 0, 4);
   }

   public void serializeTo96(IntBuffer buf) {
      assert this.v[3] == 0;

      buf.put(this.v, 0, 3);
   }

   public void serializeTo64(IntBuffer buf) {
      assert this.v[2] == 0;

      assert this.v[3] == 0;

      buf.put(this.v, 0, 2);
   }

   public void serializeTo32(IntBuffer buf) {
      assert this.v[1] == 0;

      assert this.v[2] == 0;

      assert this.v[3] == 0;

      buf.put(this.v[0]);
   }

   public void serializeTo(int[] array, int offset, int precision) {
      System.arraycopy(this.v, 0, array, offset, getIntsPerElement(precision));
   }

   public void serializeTo128(int[] array, int offset) {
      System.arraycopy(this.v, 0, array, offset, 4);
   }

   public void serializeTo96(int[] array, int offset) {
      assert this.v[3] == 0;

      System.arraycopy(this.v, 0, array, offset, 3);
   }

   public void serializeTo64(int[] array, int offset) {
      assert this.v[2] == 0;

      assert this.v[3] == 0;

      System.arraycopy(this.v, 0, array, offset, 2);
   }

   public void serializeTo32(int[] array, int offset) {
      assert this.v[1] == 0;

      assert this.v[2] == 0;

      assert this.v[3] == 0;

      array[0] = this.v[0];
   }

   public int compareTo(UnsignedInt128 o) {
      return this.compareTo(o.v);
   }

   public int compareTo(int[] o) {
      return this.compareTo(o[0], o[1], o[2], o[3]);
   }

   public int compareTo(int o0, int o1, int o2, int o3) {
      if (this.v[3] != o3) {
         return SqlMathUtil.compareUnsignedInt(this.v[3], o3);
      } else if (this.v[2] != o2) {
         return SqlMathUtil.compareUnsignedInt(this.v[2], o2);
      } else {
         return this.v[1] != o1 ? SqlMathUtil.compareUnsignedInt(this.v[1], o1) : SqlMathUtil.compareUnsignedInt(this.v[0], o0);
      }
   }

   public int compareToScaleTen(UnsignedInt128 o, short tenScale) {
      if (tenScale == 0) {
         return this.compareTo(o);
      } else if (o.isZero()) {
         return this.isZero() ? 0 : 1;
      } else if (this.isZero()) {
         if (tenScale > 0) {
            return -1;
         } else if (tenScale < -38) {
            return 0;
         } else {
            boolean oZero = o.compareTo(SqlMathUtil.ROUND_POWER_TENS_INT128[-tenScale]) < 0;
            return oZero ? 0 : -1;
         }
      } else if (this.fitsInt32() && o.fitsInt32() && tenScale <= 9) {
         long v0Long = (long)this.v[0] & 4294967295L;
         long o0;
         if (tenScale < 0) {
            if (tenScale < -9) {
               o0 = 0L;
            } else {
               o0 = ((long)o.v[0] & 4294967295L) / (long)SqlMathUtil.POWER_TENS_INT31[-tenScale];
               long remainder = ((long)o.v[0] & 4294967295L) % (long)SqlMathUtil.POWER_TENS_INT31[-tenScale];
               if (remainder >= (long)SqlMathUtil.ROUND_POWER_TENS_INT31[-tenScale]) {
                  assert o0 >= 0L;

                  ++o0;
               }
            }
         } else {
            o0 = ((long)o.v[0] & 4294967295L) * ((long)SqlMathUtil.POWER_TENS_INT31[tenScale] & 4294967295L);
         }

         return SqlMathUtil.compareUnsignedLong(v0Long, o0);
      } else {
         int[] ov = (int[])o.v.clone();
         if (tenScale < 0) {
            scaleDownTenArray4RoundUp(ov, (short)(-tenScale));
         } else {
            boolean overflow = scaleUpTenArray(ov, tenScale);
            if (overflow) {
               return -1;
            }
         }

         return this.compareTo(ov);
      }
   }

   public int hashCode() {
      return this.v[0] * 716283427 + this.v[1] * 1226369739 + this.v[2] * -265268825 + this.v[3];
   }

   public boolean equals(Object obj) {
      return !(obj instanceof UnsignedInt128) ? false : this.equals((UnsignedInt128)obj);
   }

   public boolean equals(UnsignedInt128 o) {
      return this.v[0] == o.v[0] && this.v[1] == o.v[1] && this.v[2] == o.v[2] && this.v[3] == o.v[3];
   }

   public boolean equals(int o0, int o1, int o2, int o3) {
      return this.v[0] == o0 && this.v[1] == o1 && this.v[2] == o2 && this.v[3] == o3;
   }

   protected Object clone() throws CloneNotSupportedException {
      return new UnsignedInt128(this);
   }

   public BigInteger toBigIntegerSlow() {
      BigInteger bigInt = BigInteger.valueOf((long)this.v[3] & 4294967295L);
      bigInt = bigInt.shiftLeft(32);
      bigInt = bigInt.add(BigInteger.valueOf((long)this.v[2] & 4294967295L));
      bigInt = bigInt.shiftLeft(32);
      bigInt = bigInt.add(BigInteger.valueOf((long)this.v[1] & 4294967295L));
      bigInt = bigInt.shiftLeft(32);
      bigInt = bigInt.add(BigInteger.valueOf((long)this.v[0] & 4294967295L));
      return bigInt;
   }

   public String toFormalString() {
      char[] buf = new char[39];
      int bufCount = 0;
      int nonZeroBufCount = 0;
      int tenScale = 9;
      int tenPower = SqlMathUtil.POWER_TENS_INT31[9];
      UnsignedInt128 tmp = new UnsignedInt128(this);

      while(!tmp.isZero()) {
         int remainder = tmp.divideDestructive(tenPower);

         for(int i = 0; i < 9 && bufCount < buf.length; ++i) {
            int digit = remainder % 10;
            remainder /= 10;
            buf[bufCount] = (char)(digit + 48);
            ++bufCount;
            if (digit != 0) {
               nonZeroBufCount = bufCount;
            }
         }
      }

      if (bufCount == 0) {
         return "0";
      } else {
         char[] reversed = new char[nonZeroBufCount];

         for(int i = 0; i < nonZeroBufCount; ++i) {
            reversed[i] = buf[nonZeroBufCount - i - 1];
         }

         return new String(reversed);
      }
   }

   public char[] getDigitsArray(int[] meta) {
      char[] buf = new char[39];
      int bufCount = 0;
      int nonZeroBufCount = 0;
      int trailingZeros = 0;
      int tenScale = 9;
      int tenPower = SqlMathUtil.POWER_TENS_INT31[9];
      UnsignedInt128 tmp = new UnsignedInt128(this);

      while(!tmp.isZero()) {
         int remainder = tmp.divideDestructive(tenPower);

         for(int i = 0; i < 9 && bufCount < buf.length; ++i) {
            int digit = remainder % 10;
            remainder /= 10;
            buf[bufCount] = (char)(digit + 48);
            ++bufCount;
            if (digit != 0) {
               nonZeroBufCount = bufCount;
            }

            if (nonZeroBufCount == 0) {
               ++trailingZeros;
            }
         }
      }

      if (bufCount == 0) {
         meta[0] = 1;
         meta[1] = 1;
         buf[0] = '0';
         return buf;
      } else {
         int i = 0;

         for(int j = nonZeroBufCount - 1; i < j; --j) {
            char t = buf[i];
            buf[i] = buf[j];
            buf[j] = t;
            ++i;
         }

         meta[0] = nonZeroBufCount;
         meta[1] = trailingZeros;
         return buf;
      }
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append("Int128: count=" + this.count + ",");
      str.append("v[0]=" + this.v[0] + "(0x" + Integer.toHexString(this.v[0]) + "), ");
      str.append("v[1]=" + this.v[1] + "(0x" + Integer.toHexString(this.v[1]) + "), ");
      str.append("v[2]=" + this.v[2] + "(0x" + Integer.toHexString(this.v[2]) + "), ");
      str.append("v[3]=" + this.v[3] + "(0x" + Integer.toHexString(this.v[3]) + "), ");
      str.append("BigInteger#toString=" + this.toBigIntegerSlow().toString());
      return new String(str);
   }

   public void addDestructive(UnsignedInt128 right) {
      this.addDestructive(right.v);
   }

   public void addDestructive(int[] r) {
      long sum = 0L;

      for(int i = 0; i < 4; ++i) {
         sum = ((long)this.v[i] & 4294967295L) + ((long)r[i] & 4294967295L) + (sum >>> 32);
         this.v[i] = (int)sum;
      }

      this.updateCount();
      if (sum >> 32 != 0L) {
         SqlMathUtil.throwOverflowException();
      }

   }

   public void addDestructive(int r) {
      if (((long)this.v[0] & 4294967295L) + ((long)r & 4294967295L) >= 4294967296L) {
         int[] var10000 = this.v;
         var10000[0] += r;
         if (this.v[1] == -1) {
            this.v[1] = 0;
            if (this.v[2] == -1) {
               this.v[2] = 0;
               if (this.v[3] == -1) {
                  SqlMathUtil.throwOverflowException();
               } else {
                  int var10002 = this.v[3]++;
               }
            } else {
               int var3 = this.v[2]++;
            }
         } else {
            int var4 = this.v[1]++;
         }
      } else {
         int[] var2 = this.v;
         var2[0] += r;
      }

      this.updateCount();
   }

   public void incrementDestructive() {
      incrementArray(this.v);
      this.updateCount();
   }

   public void decrementDestructive() {
      decrementArray(this.v);
      this.updateCount();
   }

   public void addDestructiveScaleTen(UnsignedInt128 right, short tenScale) {
      if (tenScale == 0) {
         this.addDestructive(right);
      } else {
         int[] r = (int[])right.v.clone();
         if (tenScale < 0) {
            scaleDownTenArray4RoundUp(r, (short)(-tenScale));
         } else if (tenScale > 0) {
            boolean overflow = scaleUpTenArray(r, tenScale);
            if (overflow) {
               SqlMathUtil.throwOverflowException();
            }
         }

         this.addDestructive(r);
      }
   }

   public void subtractDestructive(UnsignedInt128 right) {
      this.subtractDestructive(right.v);
   }

   public void subtractDestructive(int[] r) {
      long sum = 0L;

      for(int i = 0; i < 4; ++i) {
         sum = ((long)this.v[i] & 4294967295L) - ((long)r[i] & 4294967295L) - (long)((int)(-(sum >> 32)));
         this.v[i] = (int)sum;
      }

      this.updateCount();
      if (sum >> 32 != 0L) {
         SqlMathUtil.throwOverflowException();
      }

   }

   public static byte difference(UnsignedInt128 left, UnsignedInt128 right, UnsignedInt128 result) {
      return differenceInternal(left, right.v, result);
   }

   public static byte differenceScaleTen(UnsignedInt128 left, UnsignedInt128 right, UnsignedInt128 result, short tenScale) {
      if (tenScale == 0) {
         return difference(left, right, result);
      } else {
         int[] r = (int[])right.v.clone();
         if (tenScale < 0) {
            scaleDownTenArray4RoundUp(r, (short)(-tenScale));
         } else {
            boolean overflow = scaleUpTenArray(r, tenScale);
            if (overflow) {
               SqlMathUtil.throwOverflowException();
            }
         }

         return differenceInternal(left, r, result);
      }
   }

   public void multiplyDestructive(int right) {
      if (right == 0) {
         this.zeroClear();
      } else if (right != 1) {
         long sum = 0L;
         long rightUnsigned = (long)right & 4294967295L;

         for(int i = 0; i < 4; ++i) {
            sum = ((long)this.v[i] & 4294967295L) * rightUnsigned + (sum >>> 32);
            this.v[i] = (int)sum;
         }

         this.updateCount();
         if (sum >> 32 != 0L) {
            SqlMathUtil.throwOverflowException();
         }

      }
   }

   public void multiplyDestructive(UnsignedInt128 right) {
      if (this.fitsInt32() && right.fitsInt32()) {
         this.multiplyDestructiveFitsInt32(right, (short)0, (short)0);
      } else {
         multiplyArrays4And4To4NoOverflow(this.v, right.v);
         this.updateCount();
      }
   }

   public void multiplyShiftDestructive(UnsignedInt128 right, short rightShifts) {
      if (this.fitsInt32() && right.fitsInt32()) {
         this.multiplyDestructiveFitsInt32(right, rightShifts, (short)0);
      } else {
         int[] z = multiplyArrays4And4To8(this.v, right.v);
         shiftRightArray(rightShifts, z, this.v, true);
         this.updateCount();
      }
   }

   public void multiplyScaleDownTenDestructive(UnsignedInt128 right, short tenScale) {
      assert tenScale >= 0;

      if (this.fitsInt32() && right.fitsInt32()) {
         this.multiplyDestructiveFitsInt32(right, (short)0, tenScale);
      } else {
         int[] z = multiplyArrays4And4To8(this.v, right.v);
         scaleDownTenArray8RoundUp(z, tenScale);
         this.update(z[0], z[1], z[2], z[3]);
      }
   }

   public void divideDestructive(UnsignedInt128 right, UnsignedInt128 remainder) {
      if (right.isZero()) {
         assert right.isZero();

         SqlMathUtil.throwZeroDivisionException();
      }

      if (right.count == 1) {
         assert right.v[1] == 0;

         assert right.v[2] == 0;

         assert right.v[3] == 0;

         int rem = this.divideDestructive(right.v[0]);
         remainder.update((long)rem);
      } else {
         int[] quotient = new int[5];
         int[] rem = SqlMathUtil.divideMultiPrecision(this.v, right.v, quotient);
         this.update(quotient[0], quotient[1], quotient[2], quotient[3]);
         remainder.update(rem[0], rem[1], rem[2], rem[3]);
      }
   }

   public void divideScaleUpTenDestructive(UnsignedInt128 right, short tenScale, UnsignedInt128 remainder) {
      if (tenScale > 38) {
         SqlMathUtil.throwOverflowException();
      }

      int[] scaledUp = this.multiplyConstructive256(SqlMathUtil.POWER_TENS_INT128[tenScale]);
      int[] quotient = new int[5];
      int[] rem = SqlMathUtil.divideMultiPrecision(scaledUp, right.v, quotient);
      this.update(quotient[0], quotient[1], quotient[2], quotient[3]);
      remainder.update(rem[0], rem[1], rem[2], rem[3]);
   }

   public int divideDestructive(int right) {
      assert right >= 0;

      long rightUnsigned = (long)right & 4294967295L;
      long remainder = 0L;

      for(int i = 3; i >= 0; --i) {
         remainder = ((long)this.v[i] & 4294967295L) + (remainder << 32);
         long quotient = remainder / rightUnsigned;
         remainder %= rightUnsigned;
         this.v[i] = (int)quotient;
      }

      this.updateCount();
      return (int)remainder;
   }

   public long divideDestructive(long right) {
      assert right >= 0L;

      long remainder = 0L;

      for(int i = 3; i >= 0; --i) {
         remainder = ((long)this.v[i] & 4294967295L) + (remainder << 32);
         long quotient = remainder / right;
         remainder %= right;
         this.v[i] = (int)quotient;
      }

      this.updateCount();
      return remainder;
   }

   public void shiftRightDestructive(int bits, boolean roundUp) {
      assert bits >= 0;

      this.shiftRightDestructive(bits / 32, bits % 32, roundUp);
   }

   public void shiftLeftDestructive(int bits) {
      assert bits >= 0;

      this.shiftLeftDestructive(bits / 32, bits % 32);
   }

   public void shiftLeftDestructiveCheckOverflow(int bits) {
      if (this.bitLength() + bits >= 128) {
         SqlMathUtil.throwOverflowException();
      }

      this.shiftLeftDestructive(bits);
   }

   public void scaleDownTenDestructive(short tenScale) {
      if (tenScale != 0) {
         if (tenScale < 0) {
            throw new IllegalArgumentException();
         } else if (!this.isZero()) {
            scaleDownTenArray4RoundUp(this.v, tenScale);
            this.updateCount();
         }
      }
   }

   public void scaleDownFiveDestructive(short fiveScale) {
      if (fiveScale != 0) {
         if (fiveScale < 0) {
            throw new IllegalArgumentException();
         } else if (!this.isZero()) {
            scaleDownFiveArrayRoundUp(this.v, fiveScale);
            this.updateCount();
         }
      }
   }

   public void scaleUpTenDestructive(short tenScale) {
      if (tenScale != 0) {
         if (tenScale < 0) {
            throw new IllegalArgumentException();
         } else if (!this.isZero()) {
            this.shiftLeftDestructiveCheckOverflow(tenScale);
            this.scaleUpFiveDestructive(tenScale);
         }
      }
   }

   public void scaleUpFiveDestructive(short fiveScale) {
      if (fiveScale != 0) {
         if (fiveScale < 0) {
            throw new IllegalArgumentException();
         } else if (!this.isZero()) {
            while(fiveScale > 0) {
               int powerFive = Math.min(fiveScale, 13);
               this.multiplyDestructive(SqlMathUtil.POWER_FIVES_INT31[powerFive]);
               fiveScale = (short)(fiveScale - powerFive);
            }

         }
      }
   }

   public UnsignedInt128 addConstructive(UnsignedInt128 right) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.addDestructive(right);
      return ret;
   }

   public UnsignedInt128 incrementConstructive() {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.incrementDestructive();
      return ret;
   }

   public UnsignedInt128 subtractConstructive(UnsignedInt128 right) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.subtractDestructive(right);
      return ret;
   }

   public UnsignedInt128 decrementConstructive() {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.decrementDestructive();
      return ret;
   }

   public UnsignedInt128 multiplyConstructive(int right) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.multiplyDestructive(right);
      return ret;
   }

   public UnsignedInt128 multiplyConstructive(UnsignedInt128 right) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.multiplyDestructive(right);
      return ret;
   }

   public int[] multiplyConstructive256(UnsignedInt128 right) {
      return multiplyArrays4And4To8(this.v, right.v);
   }

   public UnsignedInt128 divideConstructive(int right) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.divideDestructive(right);
      return ret;
   }

   public UnsignedInt128 divideConstructive(UnsignedInt128 right, UnsignedInt128 remainder) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.divideDestructive(right, remainder);
      return ret;
   }

   public UnsignedInt128 shiftRightConstructive(int bits, boolean roundUp) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.shiftRightDestructive(bits, roundUp);
      return ret;
   }

   public UnsignedInt128 shiftLeftConstructive(int bits) {
      UnsignedInt128 ret = new UnsignedInt128(this);
      ret.shiftLeftDestructive(bits);
      return ret;
   }

   private short bitLength() {
      return SqlMathUtil.bitLength(this.v[0], this.v[1], this.v[2], this.v[3]);
   }

   private void shiftRightDestructive(int wordShifts, int bitShiftsInWord, boolean roundUp) {
      if (wordShifts != 0 || bitShiftsInWord != 0) {
         assert wordShifts >= 0;

         assert bitShiftsInWord >= 0;

         assert bitShiftsInWord < 32;

         if (wordShifts >= 4) {
            this.zeroClear();
         } else {
            int shiftRestore = 32 - bitShiftsInWord;
            boolean noRestore = bitShiftsInWord == 0;
            int roundCarryNoRestoreMask = Integer.MIN_VALUE;
            int roundCarryMask = 1 << bitShiftsInWord - 1;
            int z0 = 0;
            int z1 = 0;
            int z2 = 0;
            int z3 = 0;
            boolean roundCarry;
            switch (wordShifts) {
               case 0:
                  roundCarry = (noRestore ? 0 : this.v[0] & roundCarryMask) != 0;
                  z3 = this.v[3] >>> bitShiftsInWord;
                  z2 = (noRestore ? 0 : this.v[3] << shiftRestore) | this.v[2] >>> bitShiftsInWord;
                  z1 = (noRestore ? 0 : this.v[2] << shiftRestore) | this.v[1] >>> bitShiftsInWord;
                  z0 = (noRestore ? 0 : this.v[1] << shiftRestore) | this.v[0] >>> bitShiftsInWord;
                  break;
               case 1:
                  roundCarry = (noRestore ? this.v[0] & Integer.MIN_VALUE : this.v[1] & roundCarryMask) != 0;
                  z2 = this.v[3] >>> bitShiftsInWord;
                  z1 = (noRestore ? 0 : this.v[3] << shiftRestore) | this.v[2] >>> bitShiftsInWord;
                  z0 = (noRestore ? 0 : this.v[2] << shiftRestore) | this.v[1] >>> bitShiftsInWord;
                  break;
               case 2:
                  roundCarry = (noRestore ? this.v[1] & Integer.MIN_VALUE : this.v[2] & roundCarryMask) != 0;
                  z1 = this.v[3] >>> bitShiftsInWord;
                  z0 = (noRestore ? 0 : this.v[3] << shiftRestore) | this.v[2] >>> bitShiftsInWord;
                  break;
               case 3:
                  roundCarry = (noRestore ? this.v[2] & Integer.MIN_VALUE : this.v[3] & roundCarryMask) != 0;
                  z0 = this.v[3] >>> bitShiftsInWord;
                  break;
               default:
                  assert false;

                  throw new RuntimeException();
            }

            this.update(z0, z1, z2, z3);
            if (roundUp && roundCarry) {
               this.incrementDestructive();
            }

         }
      }
   }

   private void shiftLeftDestructive(int wordShifts, int bitShiftsInWord) {
      if (wordShifts != 0 || bitShiftsInWord != 0) {
         assert wordShifts >= 0;

         assert bitShiftsInWord >= 0;

         assert bitShiftsInWord < 32;

         if (wordShifts >= 4) {
            this.zeroClear();
         } else {
            int shiftRestore = 32 - bitShiftsInWord;
            boolean noRestore = bitShiftsInWord == 0;
            int z0 = 0;
            int z1 = 0;
            int z2 = 0;
            int z3 = 0;
            switch (wordShifts) {
               case 0:
                  z0 = this.v[0] << bitShiftsInWord;
                  z1 = (noRestore ? 0 : this.v[0] >>> shiftRestore) | this.v[1] << bitShiftsInWord;
                  z2 = (noRestore ? 0 : this.v[1] >>> shiftRestore) | this.v[2] << bitShiftsInWord;
                  z3 = (noRestore ? 0 : this.v[2] >>> shiftRestore) | this.v[3] << bitShiftsInWord;
                  break;
               case 1:
                  z1 = this.v[0] << bitShiftsInWord;
                  z2 = (noRestore ? 0 : this.v[0] >>> shiftRestore) | this.v[1] << bitShiftsInWord;
                  z3 = (noRestore ? 0 : this.v[1] >>> shiftRestore) | this.v[2] << bitShiftsInWord;
                  break;
               case 2:
                  z2 = this.v[0] << bitShiftsInWord;
                  z3 = (noRestore ? 0 : this.v[0] >>> shiftRestore) | this.v[1] << bitShiftsInWord;
                  break;
               case 3:
                  z3 = this.v[0] << bitShiftsInWord;
                  break;
               default:
                  assert false;
            }

            this.update(z0, z1, z2, z3);
         }
      }
   }

   private static void multiplyArrays4And4To4NoOverflow(int[] left, int[] right) {
      assert left.length == 4;

      assert right.length == 4;

      long product = ((long)right[0] & 4294967295L) * ((long)left[0] & 4294967295L);
      int z0 = (int)product;
      product = ((long)right[0] & 4294967295L) * ((long)left[1] & 4294967295L) + ((long)right[1] & 4294967295L) * ((long)left[0] & 4294967295L) + (product >>> 32);
      int z1 = (int)product;
      product = ((long)right[0] & 4294967295L) * ((long)left[2] & 4294967295L) + ((long)right[1] & 4294967295L) * ((long)left[1] & 4294967295L) + ((long)right[2] & 4294967295L) * ((long)left[0] & 4294967295L) + (product >>> 32);
      int z2 = (int)product;
      product = ((long)right[0] & 4294967295L) * ((long)left[3] & 4294967295L) + ((long)right[1] & 4294967295L) * ((long)left[2] & 4294967295L) + ((long)right[2] & 4294967295L) * ((long)left[1] & 4294967295L) + ((long)right[3] & 4294967295L) * ((long)left[0] & 4294967295L) + (product >>> 32);
      int z3 = (int)product;
      if (product >>> 32 != 0L) {
         SqlMathUtil.throwOverflowException();
      }

      if (right[3] != 0 && (left[3] != 0 || left[2] != 0 || left[1] != 0) || right[2] != 0 && (left[3] != 0 || left[2] != 0) || right[1] != 0 && left[3] != 0) {
         SqlMathUtil.throwOverflowException();
      }

      left[0] = z0;
      left[1] = z1;
      left[2] = z2;
      left[3] = z3;
   }

   private static int[] multiplyArrays4And4To8(int[] left, int[] right) {
      assert left.length == 4;

      assert right.length == 4;

      int[] z = new int[8];
      long product = ((long)right[0] & 4294967295L) * ((long)left[0] & 4294967295L);
      z[0] = (int)product;
      product = ((long)right[0] & 4294967295L) * ((long)left[1] & 4294967295L) + ((long)right[1] & 4294967295L) * ((long)left[0] & 4294967295L) + (product >>> 32);
      z[1] = (int)product;
      product = ((long)right[0] & 4294967295L) * ((long)left[2] & 4294967295L) + ((long)right[1] & 4294967295L) * ((long)left[1] & 4294967295L) + ((long)right[2] & 4294967295L) * ((long)left[0] & 4294967295L) + (product >>> 32);
      z[2] = (int)product;
      product = ((long)right[0] & 4294967295L) * ((long)left[3] & 4294967295L) + ((long)right[1] & 4294967295L) * ((long)left[2] & 4294967295L) + ((long)right[2] & 4294967295L) * ((long)left[1] & 4294967295L) + ((long)right[3] & 4294967295L) * ((long)left[0] & 4294967295L) + (product >>> 32);
      z[3] = (int)product;
      product = ((long)right[1] & 4294967295L) * ((long)left[3] & 4294967295L) + ((long)right[2] & 4294967295L) * ((long)left[2] & 4294967295L) + ((long)right[3] & 4294967295L) * ((long)left[1] & 4294967295L) + (product >>> 32);
      z[4] = (int)product;
      product = ((long)right[2] & 4294967295L) * ((long)left[3] & 4294967295L) + ((long)right[3] & 4294967295L) * ((long)left[2] & 4294967295L) + (product >>> 32);
      z[5] = (int)product;
      product = ((long)right[3] & 4294967295L) * ((long)left[3] & 4294967295L) + (product >>> 32);
      z[6] = (int)product;
      z[7] = (int)(product >>> 32);
      return z;
   }

   private static void incrementArray(int[] array) {
      for(int i = 0; i < 4; ++i) {
         if (array[i] != -1) {
            array[i] = (int)(((long)array[i] & 4294967295L) + 1L);
            break;
         }

         array[i] = 0;
         if (i == 3) {
            SqlMathUtil.throwOverflowException();
         }
      }

   }

   private static void decrementArray(int[] array) {
      for(int i = 0; i < 4; ++i) {
         if (array[i] != 0) {
            array[i] = (int)(((long)array[i] & 4294967295L) - 1L);
            break;
         }

         array[i] = -1;
         if (i == 3) {
            SqlMathUtil.throwOverflowException();
         }
      }

   }

   private static byte differenceInternal(UnsignedInt128 left, int[] r, UnsignedInt128 result) {
      int cmp = left.compareTo(r);
      if (cmp == 0) {
         result.zeroClear();
         return 0;
      } else {
         long sum = 0L;
         if (cmp > 0) {
            for(int i = 0; i < 4; ++i) {
               sum = ((long)left.v[i] & 4294967295L) - ((long)r[i] & 4294967295L) - (long)((int)(-(sum >> 32)));
               result.v[i] = (int)sum;
            }
         } else {
            for(int i = 0; i < 4; ++i) {
               sum = ((long)r[i] & 4294967295L) - ((long)left.v[i] & 4294967295L) - (long)((int)(-(sum >> 32)));
               result.v[i] = (int)sum;
            }
         }

         if (sum >> 32 != 0L) {
            SqlMathUtil.throwOverflowException();
         }

         result.updateCount();
         return (byte)(cmp > 0 ? 1 : -1);
      }
   }

   private static int compareTo(int l0, int l1, int l2, int l3, int r0, int r1, int r2, int r3) {
      if (l3 != r3) {
         return SqlMathUtil.compareUnsignedInt(l3, r3);
      } else if (l2 != r2) {
         return SqlMathUtil.compareUnsignedInt(l2, r2);
      } else if (l1 != r1) {
         return SqlMathUtil.compareUnsignedInt(l1, r1);
      } else {
         return l0 != r0 ? SqlMathUtil.compareUnsignedInt(l0, r0) : 0;
      }
   }

   private static boolean scaleUpTenArray(int[] array, short tenScale) {
      while(tenScale > 0) {
         long sum = 0L;
         int powerTen = Math.min(tenScale, 9);
         tenScale = (short)(tenScale - powerTen);
         long rightUnsigned = (long)SqlMathUtil.POWER_TENS_INT31[powerTen] & 4294967295L;

         for(int i = 0; i < 4; ++i) {
            sum = ((long)array[i] & 4294967295L) * rightUnsigned + (sum >>> 32);
            array[i] = (int)sum;
         }

         if (sum >> 32 != 0L) {
            return true;
         }
      }

      return false;
   }

   private static void scaleDownTenArray4RoundUp(int[] array, short tenScale) {
      scaleDownFiveArray(array, tenScale);
      shiftRightArray(tenScale, array, array, true);
   }

   private static void scaleDownTenArray8RoundUp(int[] array, short tenScale) {
      assert array.length == 8;

      if (tenScale > 38) {
         Arrays.fill(array, 0);
      } else if (tenScale <= 9) {
         int divisor = SqlMathUtil.POWER_TENS_INT31[tenScale];

         assert divisor > 0;

         boolean round = divideCheckRound(array, divisor);
         if (round) {
            incrementArray(array);
         }

      } else {
         int[] inverse = SqlMathUtil.INVERSE_POWER_TENS_INT128[tenScale].v;
         int inverseWordShift = SqlMathUtil.INVERSE_POWER_TENS_INT128_WORD_SHIFTS[tenScale];

         assert inverseWordShift <= 3;

         assert inverse[3] != 0;

         for(int i = 5 + inverseWordShift; i < 8; ++i) {
            if (array[i] != 0) {
               SqlMathUtil.throwOverflowException();
            }
         }

         int z4 = 0;
         int z5 = 0;
         int z6 = 0;
         int z7 = 0;
         int z8 = 0;
         int z9 = 0;
         int z10 = 0;
         long product = 0L;
         product += ((long)inverse[0] & 4294967295L) * ((long)array[4] & 4294967295L) + ((long)inverse[1] & 4294967295L) * ((long)array[3] & 4294967295L) + ((long)inverse[2] & 4294967295L) * ((long)array[2] & 4294967295L) + ((long)inverse[3] & 4294967295L) * ((long)array[1] & 4294967295L);
         z4 = (int)product;
         product >>>= 32;
         product += ((long)inverse[0] & 4294967295L) * ((long)array[5] & 4294967295L) + ((long)inverse[1] & 4294967295L) * ((long)array[4] & 4294967295L) + ((long)inverse[2] & 4294967295L) * ((long)array[3] & 4294967295L) + ((long)inverse[3] & 4294967295L) * ((long)array[2] & 4294967295L);
         z5 = (int)product;
         product >>>= 32;
         product += ((long)inverse[0] & 4294967295L) * ((long)array[6] & 4294967295L) + ((long)inverse[1] & 4294967295L) * ((long)array[5] & 4294967295L) + ((long)inverse[2] & 4294967295L) * ((long)array[4] & 4294967295L) + ((long)inverse[3] & 4294967295L) * ((long)array[3] & 4294967295L);
         z6 = (int)product;
         product >>>= 32;
         product += ((long)inverse[0] & 4294967295L) * ((long)array[7] & 4294967295L) + ((long)inverse[1] & 4294967295L) * ((long)array[6] & 4294967295L) + ((long)inverse[2] & 4294967295L) * ((long)array[5] & 4294967295L) + ((long)inverse[3] & 4294967295L) * ((long)array[4] & 4294967295L);
         z7 = (int)product;
         product >>>= 32;
         if (inverseWordShift >= 1) {
            product += ((long)inverse[1] & 4294967295L) * ((long)array[7] & 4294967295L) + ((long)inverse[2] & 4294967295L) * ((long)array[6] & 4294967295L) + ((long)inverse[3] & 4294967295L) * ((long)array[5] & 4294967295L);
            z8 = (int)product;
            product >>>= 32;
            if (inverseWordShift >= 2) {
               product += ((long)inverse[2] & 4294967295L) * ((long)array[7] & 4294967295L) + ((long)inverse[3] & 4294967295L) * ((long)array[6] & 4294967295L);
               z9 = (int)product;
               product >>>= 32;
               if (inverseWordShift >= 3) {
                  product += ((long)inverse[3] & 4294967295L) * ((long)array[7] & 4294967295L);
                  z10 = (int)product;
                  product >>>= 32;
               }
            }
         }

         if (product != 0L) {
            SqlMathUtil.throwOverflowException();
         }

         switch (inverseWordShift) {
            case 1:
               z4 = z5;
               z5 = z6;
               z6 = z7;
               z7 = z8;
               break;
            case 2:
               z4 = z6;
               z5 = z7;
               z6 = z8;
               z7 = z9;
               break;
            case 3:
               z4 = z7;
               z5 = z8;
               z6 = z9;
               z7 = z10;
         }

         int[] power = SqlMathUtil.POWER_TENS_INT128[tenScale].v;
         int[] half = SqlMathUtil.ROUND_POWER_TENS_INT128[tenScale].v;
         product = ((long)array[0] & 4294967295L) - ((long)power[0] & 4294967295L) * ((long)z4 & 4294967295L);
         int d0 = (int)product;
         product = ((long)array[1] & 4294967295L) - ((long)power[0] & 4294967295L) * ((long)z5 & 4294967295L) - ((long)power[1] & 4294967295L) * ((long)z4 & 4294967295L) - (long)((int)(-(product >> 32)));
         int d1 = (int)product;
         product = ((long)array[2] & 4294967295L) - ((long)power[0] & 4294967295L) * ((long)z6 & 4294967295L) - ((long)power[1] & 4294967295L) * ((long)z5 & 4294967295L) - ((long)power[2] & 4294967295L) * ((long)z4 & 4294967295L) - (long)((int)(-(product >> 32)));
         int d2 = (int)product;
         product = ((long)array[3] & 4294967295L) - ((long)power[0] & 4294967295L) * ((long)z7 & 4294967295L) - ((long)power[1] & 4294967295L) * ((long)z6 & 4294967295L) - ((long)power[2] & 4294967295L) * ((long)z5 & 4294967295L) - ((long)power[3] & 4294967295L) * ((long)z4 & 4294967295L) - (long)((int)(-(product >> 32)));
         int d3 = (int)product;
         product = ((long)array[4] & 4294967295L) - ((long)power[1] & 4294967295L) * ((long)z7 & 4294967295L) - ((long)power[2] & 4294967295L) * ((long)z6 & 4294967295L) - ((long)power[3] & 4294967295L) * ((long)z5 & 4294967295L) - (long)((int)(-(product >> 32)));
         int d4 = (int)product;
         boolean increment = d4 != 0 || compareTo(d0, d1, d2, d3, half[0], half[1], half[2], half[3]) >= 0;
         array[0] = z4;
         array[1] = z5;
         array[2] = z6;
         array[3] = z7;
         if (increment) {
            incrementArray(array);
         }

      }
   }

   private static boolean scaleDownFiveArray(int[] array, short fiveScale) {
      while(true) {
         int powerFive = Math.min(fiveScale, 13);
         fiveScale = (short)(fiveScale - powerFive);
         int divisor = SqlMathUtil.POWER_FIVES_INT31[powerFive];

         assert divisor > 0;

         if (fiveScale == 0) {
            return divideCheckRound(array, divisor);
         }

         divideCheckRound(array, divisor);
      }
   }

   private static boolean divideCheckRound(int[] array, int divisor) {
      long remainder = 0L;

      for(int i = array.length - 1; i >= 0; --i) {
         remainder = ((long)array[i] & 4294967295L) + (remainder << 32);
         array[i] = (int)(remainder / (long)divisor);
         remainder %= (long)divisor;
      }

      return remainder >= (long)(divisor >> 1);
   }

   private static void scaleDownFiveArrayRoundUp(int[] array, short tenScale) {
      boolean rounding = scaleDownFiveArray(array, tenScale);
      if (rounding) {
         incrementArray(array);
      }

   }

   private static void shiftRightArray(int rightShifts, int[] z, int[] result, boolean round) {
      assert rightShifts >= 0;

      if (rightShifts == 0) {
         for(int i = 0; i < 4; ++i) {
            if (z[i + 4] != 0) {
               SqlMathUtil.throwOverflowException();
            }
         }

         result[0] = z[0];
         result[1] = z[1];
         result[2] = z[2];
         result[3] = z[3];
      } else {
         int wordShifts = rightShifts / 32;
         int bitShiftsInWord = rightShifts % 32;
         int shiftRestore = 32 - bitShiftsInWord;
         boolean noRestore = bitShiftsInWord == 0;
         if (z.length > 4) {
            if (wordShifts + 4 < z.length && z[wordShifts + 4] >>> bitShiftsInWord != 0) {
               SqlMathUtil.throwOverflowException();
            }

            for(int i = 1; i < 4; ++i) {
               if (i + wordShifts < z.length - 4 && z[i + wordShifts + 4] != 0) {
                  SqlMathUtil.throwOverflowException();
               }
            }
         }

         boolean roundCarry = false;
         if (round) {
            if (bitShiftsInWord == 0) {
               assert wordShifts > 0;

               roundCarry = z[wordShifts - 1] < 0;
            } else {
               roundCarry = (z[wordShifts] & 1 << bitShiftsInWord - 1) != 0;
            }
         }

         for(int i = 0; i < 4; ++i) {
            int val = 0;
            if (!noRestore && i + wordShifts + 1 < z.length) {
               val = z[i + wordShifts + 1] << shiftRestore;
            }

            if (i + wordShifts < z.length) {
               val |= z[i + wordShifts] >>> bitShiftsInWord;
            }

            result[i] = val;
         }

         if (roundCarry) {
            incrementArray(result);
         }
      }

   }

   private void multiplyDestructiveFitsInt32(UnsignedInt128 right, short rightShifts, short tenScaleDown) {
      assert this.fitsInt32() && right.fitsInt32();

      assert rightShifts == 0 || tenScaleDown == 0;

      if (!this.isZero()) {
         if (right.isZero()) {
            this.zeroClear();
         } else {
            if (this.isOne()) {
               this.update(right);
            } else {
               this.multiplyDestructive(right.v[0]);
            }

            if (rightShifts > 0) {
               this.shiftRightDestructive(rightShifts, true);
            } else if (tenScaleDown > 0) {
               this.scaleDownTenDestructive(tenScaleDown);
            }

         }
      }
   }

   private void updateCount() {
      if (this.v[3] != 0) {
         this.count = 4;
      } else if (this.v[2] != 0) {
         this.count = 3;
      } else if (this.v[1] != 0) {
         this.count = 2;
      } else if (this.v[0] != 0) {
         this.count = 1;
      } else {
         this.count = 0;
      }

   }

   private static void fastSerializeIntPartForHiveDecimal(ByteBuffer buf, int pos, int value, byte signum, boolean isFirstNonZero) {
      if (signum == -1 && value != 0) {
         value = isFirstNonZero ? -value : ~value;
      }

      buf.putInt(pos, value);
   }

   public int fastSerializeForHiveDecimal(Decimal128FastBuffer scratch, byte signum) {
      int bufferUsed = this.count;
      ByteBuffer buf = scratch.getByteBuffer(bufferUsed);
      buf.put(0, signum == 1 ? 0 : signum);
      int pos = 1;

      int firstNonZero;
      for(firstNonZero = 0; firstNonZero < this.count && this.v[firstNonZero] == 0; ++firstNonZero) {
      }

      switch (this.count) {
         case 4:
            fastSerializeIntPartForHiveDecimal(buf, pos, this.v[3], signum, firstNonZero == 3);
            pos += 4;
         case 3:
            fastSerializeIntPartForHiveDecimal(buf, pos, this.v[2], signum, firstNonZero == 2);
            pos += 4;
         case 2:
            fastSerializeIntPartForHiveDecimal(buf, pos, this.v[1], signum, firstNonZero == 1);
            pos += 4;
         case 1:
            fastSerializeIntPartForHiveDecimal(buf, pos, this.v[0], signum, true);
         default:
            return bufferUsed;
      }
   }

   public byte fastUpdateFromInternalStorage(byte[] internalStorage) {
      byte signum = (byte)0;
      int skip = 0;
      this.count = 0;
      byte firstByte = internalStorage[0];
      if (firstByte == 0 || firstByte == -1) {
         while(skip < internalStorage.length && internalStorage[skip] == firstByte) {
            ++skip;
         }
      }

      if (skip == internalStorage.length) {
         assert firstByte == 0 || firstByte == -1;

         if (firstByte == -1) {
            signum = (byte)-1;
            this.count = 1;
            this.v[0] = 1;
         } else {
            signum = (byte)0;
         }
      } else {
         signum = (byte)(firstByte < 0 ? -1 : 1);
         int length = internalStorage.length - skip;
         int pos = skip;
         int intLength = 0;
         switch (length) {
            case 1:
               break;
            case 16:
               ++intLength;
            case 15:
               ++intLength;
            case 14:
               ++intLength;
            case 13:
               ++intLength;
               this.v[3] = this.fastUpdateIntFromInternalStorage(internalStorage, signum, skip, intLength);
               ++this.count;
               pos = skip + intLength;
               intLength = 0;
            case 12:
               ++intLength;
            case 11:
               ++intLength;
            case 10:
               ++intLength;
            case 9:
               ++intLength;
               this.v[2] = this.fastUpdateIntFromInternalStorage(internalStorage, signum, pos, intLength);
               ++this.count;
               pos += intLength;
               intLength = 0;
            case 8:
               ++intLength;
            case 7:
               ++intLength;
            case 6:
               ++intLength;
            case 5:
               ++intLength;
               this.v[1] = this.fastUpdateIntFromInternalStorage(internalStorage, signum, pos, intLength);
               ++this.count;
               pos += intLength;
               intLength = 0;
            case 4:
               ++intLength;
            case 3:
               ++intLength;
            case 2:
               ++intLength;
               break;
            default:
               throw new RuntimeException("Impossible HiveDecimal internal storage length!");
         }

         ++intLength;
         this.v[0] = this.fastUpdateIntFromInternalStorage(internalStorage, signum, pos, intLength);
         ++this.count;
         if (signum == -1) {
            for(int i = 0; i < this.count; ++i) {
               if (this.v[i] != 0) {
                  this.v[i] = (int)(((long)this.v[i] & 4294967295L) + 1L);
                  if (this.v[i] != 0) {
                     break;
                  }
               }
            }
         }
      }

      return signum;
   }

   private int fastUpdateIntFromInternalStorage(byte[] internalStorage, byte signum, int pos, int length) {
      byte b1;
      byte b2;
      byte b3;
      if (signum == -1) {
         b3 = -1;
         b2 = -1;
         b1 = -1;
      } else {
         b3 = 0;
         b2 = 0;
         b1 = 0;
      }

      switch (length) {
         case 4:
            b3 = internalStorage[pos];
            ++pos;
         case 3:
            b2 = internalStorage[pos];
            ++pos;
         case 2:
            b1 = internalStorage[pos];
            ++pos;
         case 1:
            byte b0 = internalStorage[pos];
            int value = b0 & 255 | b1 << 8 & '\uff00' | b2 << 16 & 16711680 | b3 << 24 & -16777216;
            if (signum == -1 && value != 0) {
               int mask = -1 >>> 8 * (4 - length);
               value = ~value & mask;
            }

            return value;
         default:
            throw new RuntimeException("Impossible HiveDecimal internal storage position!");
      }
   }

   public int[] getV() {
      return this.v;
   }

   public void setV(int[] v) {
      this.v[0] = v[0];
      this.v[1] = v[1];
      this.v[2] = v[2];
      this.v[3] = v[3];
      this.updateCount();
   }

   public byte getCount() {
      return this.count;
   }

   public void setCount(byte count) {
      this.count = count;
   }
}
