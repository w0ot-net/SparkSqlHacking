package org.apache.hadoop.hive.serde2.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.ql.util.TimestampUtils;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class TimestampWritable implements WritableComparable {
   public static final byte[] nullBytes = new byte[]{0, 0, 0, 0};
   private static final int DECIMAL_OR_SECOND_VINT_FLAG = Integer.MIN_VALUE;
   private static final int LOWEST_31_BITS_OF_SEC_MASK = Integer.MAX_VALUE;
   private static final long SEVEN_BYTE_LONG_SIGN_FLIP = -36028797018963968L;
   public static final int MAX_BYTES = 13;
   public static final int BINARY_SORTABLE_LENGTH = 11;
   private static final ThreadLocal threadLocalDateFormat = new ThreadLocal() {
      protected DateFormat initialValue() {
         return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      }
   };
   private Timestamp timestamp;
   private boolean bytesEmpty;
   private boolean timestampEmpty;
   private byte[] currentBytes;
   private final byte[] internalBytes;
   private byte[] externalBytes;
   private int offset;

   public TimestampWritable() {
      this.timestamp = new Timestamp(0L);
      this.internalBytes = new byte[13];
      this.bytesEmpty = false;
      this.currentBytes = this.internalBytes;
      this.offset = 0;
      this.clearTimestamp();
   }

   public TimestampWritable(byte[] bytes, int offset) {
      this.timestamp = new Timestamp(0L);
      this.internalBytes = new byte[13];
      this.set(bytes, offset);
   }

   public TimestampWritable(TimestampWritable t) {
      this(t.getBytes(), 0);
   }

   public TimestampWritable(Timestamp t) {
      this.timestamp = new Timestamp(0L);
      this.internalBytes = new byte[13];
      this.set(t);
   }

   public void set(byte[] bytes, int offset) {
      this.externalBytes = bytes;
      this.offset = offset;
      this.bytesEmpty = false;
      this.currentBytes = this.externalBytes;
      this.clearTimestamp();
   }

   public void setTime(long time) {
      this.timestamp.setTime(time);
      this.bytesEmpty = true;
      this.timestampEmpty = false;
   }

   public void set(Timestamp t) {
      if (t == null) {
         this.timestamp.setTime(0L);
         this.timestamp.setNanos(0);
      } else {
         this.timestamp = t;
         this.bytesEmpty = true;
         this.timestampEmpty = false;
      }
   }

   public void set(TimestampWritable t) {
      if (t.bytesEmpty) {
         this.set(t.getTimestamp());
      } else {
         if (t.currentBytes == t.externalBytes) {
            this.set(t.currentBytes, t.offset);
         } else {
            this.set(t.currentBytes, 0);
         }

      }
   }

   public static void updateTimestamp(Timestamp timestamp, long secondsAsMillis, int nanos) {
      ((Date)timestamp).setTime(secondsAsMillis);
      timestamp.setNanos(nanos);
   }

   public void setInternal(long secondsAsMillis, int nanos) {
      updateTimestamp(this.timestamp, secondsAsMillis, nanos);
      this.bytesEmpty = true;
      this.timestampEmpty = false;
   }

   private void clearTimestamp() {
      this.timestampEmpty = true;
   }

   public void writeToByteStream(ByteStream.RandomAccessOutput byteStream) {
      this.checkBytes();
      byteStream.write(this.currentBytes, this.offset, this.getTotalLength());
   }

   public long getSeconds() {
      if (!this.timestampEmpty) {
         return TimestampUtils.millisToSeconds(this.timestamp.getTime());
      } else if (!this.bytesEmpty) {
         return getSeconds(this.currentBytes, this.offset);
      } else {
         throw new IllegalStateException("Both timestamp and bytes are empty");
      }
   }

   public int getNanos() {
      if (!this.timestampEmpty) {
         return this.timestamp.getNanos();
      } else if (!this.bytesEmpty) {
         return this.hasDecimalOrSecondVInt() ? getNanos(this.currentBytes, this.offset + 4) : 0;
      } else {
         throw new IllegalStateException("Both timestamp and bytes are empty");
      }
   }

   int getTotalLength() {
      this.checkBytes();
      return getTotalLength(this.currentBytes, this.offset);
   }

   public static int getTotalLength(byte[] bytes, int offset) {
      int len = 4;
      if (hasDecimalOrSecondVInt(bytes[offset])) {
         int firstVIntLen = WritableUtils.decodeVIntSize(bytes[offset + 4]);
         len += firstVIntLen;
         if (hasSecondVInt(bytes[offset + 4])) {
            len += WritableUtils.decodeVIntSize(bytes[offset + 4 + firstVIntLen]);
         }
      }

      return len;
   }

   public Timestamp getTimestamp() {
      if (this.timestampEmpty) {
         this.populateTimestamp();
      }

      return this.timestamp;
   }

   public byte[] getBytes() {
      this.checkBytes();
      int len = this.getTotalLength();
      byte[] b = new byte[len];
      System.arraycopy(this.currentBytes, this.offset, b, 0, len);
      return b;
   }

   public byte[] getBinarySortable() {
      byte[] b = new byte[11];
      int nanos = this.getNanos();
      long seconds = this.getSeconds() ^ -36028797018963968L;
      sevenByteLongToBytes(seconds, b, 0);
      intToBytes(nanos, b, 7);
      return b;
   }

   public void setBinarySortable(byte[] bytes, int binSortOffset) {
      long seconds = readSevenByteLong(bytes, binSortOffset) ^ -36028797018963968L;
      int nanos = bytesToInt(bytes, binSortOffset + 7);
      int firstInt = (int)seconds;
      boolean hasSecondVInt = seconds < 0L || seconds > 2147483647L;
      if (nanos == 0 && !hasSecondVInt) {
         firstInt &= Integer.MAX_VALUE;
      } else {
         firstInt |= Integer.MIN_VALUE;
      }

      intToBytes(firstInt, this.internalBytes, 0);
      setNanosBytes(nanos, this.internalBytes, 4, hasSecondVInt);
      if (hasSecondVInt) {
         LazyBinaryUtils.writeVLongToByteArray(this.internalBytes, 4 + WritableUtils.decodeVIntSize(this.internalBytes[4]), seconds >> 31);
      }

      this.currentBytes = this.internalBytes;
      this.offset = 0;
   }

   private void checkBytes() {
      if (this.bytesEmpty) {
         convertTimestampToBytes(this.timestamp, this.internalBytes, 0);
         this.offset = 0;
         this.currentBytes = this.internalBytes;
         this.bytesEmpty = false;
      }

   }

   public double getDouble() {
      double seconds;
      double nanos;
      if (this.bytesEmpty) {
         seconds = (double)TimestampUtils.millisToSeconds(this.timestamp.getTime());
         nanos = (double)this.timestamp.getNanos();
      } else {
         seconds = (double)this.getSeconds();
         nanos = (double)this.getNanos();
      }

      return seconds + nanos / (double)1.0E9F;
   }

   public static long getLong(Timestamp timestamp) {
      return timestamp.getTime() / 1000L;
   }

   public void readFields(DataInput in) throws IOException {
      in.readFully(this.internalBytes, 0, 4);
      if (hasDecimalOrSecondVInt(this.internalBytes[0])) {
         in.readFully(this.internalBytes, 4, 1);
         int len = (byte)WritableUtils.decodeVIntSize(this.internalBytes[4]);
         if (len > 1) {
            in.readFully(this.internalBytes, 5, len - 1);
         }

         long vlong = LazyBinaryUtils.readVLongFromByteArray(this.internalBytes, 4);
         if (vlong < -1000000000L || vlong > 999999999L) {
            throw new IOException("Invalid first vint value (encoded nanoseconds) of a TimestampWritable: " + vlong + ", expected to be between -1000000000 and 999999999.");
         }

         if (vlong < 0L) {
            in.readFully(this.internalBytes, 4 + len, 1);
            int secondVIntLen = (byte)WritableUtils.decodeVIntSize(this.internalBytes[4 + len]);
            if (secondVIntLen > 1) {
               in.readFully(this.internalBytes, 5 + len, secondVIntLen - 1);
            }
         }
      }

      this.currentBytes = this.internalBytes;
      this.offset = 0;
   }

   public void write(DataOutput out) throws IOException {
      this.checkBytes();
      out.write(this.currentBytes, this.offset, this.getTotalLength());
   }

   public int compareTo(TimestampWritable t) {
      this.checkBytes();
      long s1 = this.getSeconds();
      long s2 = t.getSeconds();
      if (s1 == s2) {
         int n1 = this.getNanos();
         int n2 = t.getNanos();
         return n1 == n2 ? 0 : n1 - n2;
      } else {
         return s1 < s2 ? -1 : 1;
      }
   }

   public boolean equals(Object o) {
      return this.compareTo((TimestampWritable)o) == 0;
   }

   public String toString() {
      if (this.timestampEmpty) {
         this.populateTimestamp();
      }

      String timestampString = this.timestamp.toString();
      if (timestampString.length() > 19) {
         return timestampString.length() == 21 && timestampString.substring(19).compareTo(".0") == 0 ? ((DateFormat)threadLocalDateFormat.get()).format(this.timestamp) : ((DateFormat)threadLocalDateFormat.get()).format(this.timestamp) + timestampString.substring(19);
      } else {
         return ((DateFormat)threadLocalDateFormat.get()).format(this.timestamp);
      }
   }

   public int hashCode() {
      long seconds = this.getSeconds();
      seconds <<= 30;
      seconds |= (long)this.getNanos();
      return (int)(seconds >>> 32 ^ seconds);
   }

   private void populateTimestamp() {
      long seconds = this.getSeconds();
      int nanos = this.getNanos();
      this.timestamp.setTime(seconds * 1000L);
      this.timestamp.setNanos(nanos);
   }

   public static long getSeconds(byte[] bytes, int offset) {
      int lowest31BitsOfSecondsAndFlag = bytesToInt(bytes, offset);
      return lowest31BitsOfSecondsAndFlag < 0 && hasSecondVInt(bytes[offset + 4]) ? (long)(lowest31BitsOfSecondsAndFlag & Integer.MAX_VALUE) | LazyBinaryUtils.readVLongFromByteArray(bytes, offset + 4 + WritableUtils.decodeVIntSize(bytes[offset + 4])) << 31 : (long)(lowest31BitsOfSecondsAndFlag & Integer.MAX_VALUE);
   }

   public static int getNanos(byte[] bytes, int offset) {
      LazyBinaryUtils.VInt vInt = (LazyBinaryUtils.VInt)LazyBinaryUtils.threadLocalVInt.get();
      LazyBinaryUtils.readVInt(bytes, offset, vInt);
      int val = vInt.value;
      if (val < 0) {
         val = -val - 1;
      }

      int len = (int)Math.floor(Math.log10((double)val)) + 1;

      int tmp;
      for(tmp = 0; val != 0; val /= 10) {
         tmp *= 10;
         tmp += val % 10;
      }

      val = tmp;
      if (len < 9) {
         val = (int)((double)tmp * Math.pow((double)10.0F, (double)(9 - len)));
      }

      return val;
   }

   public static void convertTimestampToBytes(Timestamp t, byte[] b, int offset) {
      long millis = t.getTime();
      int nanos = t.getNanos();
      long seconds = TimestampUtils.millisToSeconds(millis);
      boolean hasSecondVInt = seconds < 0L || seconds > 2147483647L;
      boolean hasDecimal = setNanosBytes(nanos, b, offset + 4, hasSecondVInt);
      int firstInt = (int)seconds;
      if (!hasDecimal && !hasSecondVInt) {
         firstInt &= Integer.MAX_VALUE;
      } else {
         firstInt |= Integer.MIN_VALUE;
      }

      intToBytes(firstInt, b, offset);
      if (hasSecondVInt) {
         LazyBinaryUtils.writeVLongToByteArray(b, offset + 4 + WritableUtils.decodeVIntSize(b[offset + 4]), seconds >> 31);
      }

   }

   private static boolean setNanosBytes(int nanos, byte[] b, int offset, boolean hasSecondVInt) {
      int decimal = 0;
      if (nanos != 0) {
         for(int counter = 0; counter < 9; ++counter) {
            decimal *= 10;
            decimal += nanos % 10;
            nanos /= 10;
         }
      }

      if (hasSecondVInt || decimal != 0) {
         LazyBinaryUtils.writeVLongToByteArray(b, offset, hasSecondVInt ? (long)(-decimal - 1) : (long)decimal);
      }

      return decimal != 0;
   }

   public HiveDecimal getHiveDecimal() {
      if (this.timestampEmpty) {
         this.populateTimestamp();
      }

      return getHiveDecimal(this.timestamp);
   }

   public static HiveDecimal getHiveDecimal(Timestamp timestamp) {
      Double timestampDouble = TimestampUtils.getDouble(timestamp);
      HiveDecimal result = HiveDecimal.create(timestampDouble.toString());
      return result;
   }

   public static Timestamp longToTimestamp(long time, boolean intToTimestampInSeconds) {
      return new Timestamp(intToTimestampInSeconds ? time * 1000L : time);
   }

   public static void setTimestamp(Timestamp t, byte[] bytes, int offset) {
      long seconds = getSeconds(bytes, offset);
      t.setTime(seconds * 1000L);
      if (hasDecimalOrSecondVInt(bytes[offset])) {
         t.setNanos(getNanos(bytes, offset + 4));
      } else {
         t.setNanos(0);
      }

   }

   public static Timestamp createTimestamp(byte[] bytes, int offset) {
      Timestamp t = new Timestamp(0L);
      setTimestamp(t, bytes, offset);
      return t;
   }

   private static boolean hasDecimalOrSecondVInt(byte b) {
      return b >> 7 != 0;
   }

   private static boolean hasSecondVInt(byte b) {
      return WritableUtils.isNegativeVInt(b);
   }

   private final boolean hasDecimalOrSecondVInt() {
      return hasDecimalOrSecondVInt(this.currentBytes[this.offset]);
   }

   public final boolean hasDecimal() {
      return this.hasDecimalOrSecondVInt() || this.currentBytes[this.offset + 4] != -1;
   }

   private static void intToBytes(int value, byte[] dest, int offset) {
      dest[offset] = (byte)(value >> 24 & 255);
      dest[offset + 1] = (byte)(value >> 16 & 255);
      dest[offset + 2] = (byte)(value >> 8 & 255);
      dest[offset + 3] = (byte)(value & 255);
   }

   static void sevenByteLongToBytes(long value, byte[] dest, int offset) {
      dest[offset] = (byte)((int)(value >> 48 & 255L));
      dest[offset + 1] = (byte)((int)(value >> 40 & 255L));
      dest[offset + 2] = (byte)((int)(value >> 32 & 255L));
      dest[offset + 3] = (byte)((int)(value >> 24 & 255L));
      dest[offset + 4] = (byte)((int)(value >> 16 & 255L));
      dest[offset + 5] = (byte)((int)(value >> 8 & 255L));
      dest[offset + 6] = (byte)((int)(value & 255L));
   }

   private static int bytesToInt(byte[] bytes, int offset) {
      return (255 & bytes[offset]) << 24 | (255 & bytes[offset + 1]) << 16 | (255 & bytes[offset + 2]) << 8 | 255 & bytes[offset + 3];
   }

   static long readSevenByteLong(byte[] bytes, int offset) {
      return ((255L & (long)bytes[offset]) << 56 | (255L & (long)bytes[offset + 1]) << 48 | (255L & (long)bytes[offset + 2]) << 40 | (255L & (long)bytes[offset + 3]) << 32 | (255L & (long)bytes[offset + 4]) << 24 | (255L & (long)bytes[offset + 5]) << 16 | (255L & (long)bytes[offset + 6]) << 8) >> 8;
   }
}
