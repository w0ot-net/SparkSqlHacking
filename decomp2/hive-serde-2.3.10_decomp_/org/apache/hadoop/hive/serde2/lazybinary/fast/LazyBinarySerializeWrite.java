package org.apache.hadoop.hive.serde2.lazybinary.fast;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.fast.SerializeWrite;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinarySerDe;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinarySerializeWrite implements SerializeWrite {
   public static final Logger LOG = LoggerFactory.getLogger(LazyBinarySerializeWrite.class.getName());
   private ByteStream.Output output;
   private int fieldCount;
   private int fieldIndex;
   private byte nullByte;
   private long nullOffset;
   private TimestampWritable timestampWritable;
   private HiveIntervalYearMonthWritable hiveIntervalYearMonthWritable;
   private HiveIntervalDayTimeWritable hiveIntervalDayTimeWritable;
   private HiveIntervalDayTime hiveIntervalDayTime;
   private byte[] vLongBytes;
   private long[] scratchLongs;
   private byte[] scratchBuffer;

   public LazyBinarySerializeWrite(int fieldCount) {
      this();
      this.vLongBytes = new byte[9];
      this.fieldCount = fieldCount;
   }

   private LazyBinarySerializeWrite() {
   }

   public void set(ByteStream.Output output) {
      this.output = output;
      output.reset();
      this.fieldIndex = 0;
      this.nullByte = 0;
      this.nullOffset = 0L;
   }

   public void setAppend(ByteStream.Output output) {
      this.output = output;
      this.fieldIndex = 0;
      this.nullByte = 0;
      this.nullOffset = (long)output.getLength();
   }

   public void reset() {
      this.output.reset();
      this.fieldIndex = 0;
      this.nullByte = 0;
      this.nullOffset = 0L;
   }

   public void writeNull() throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeBoolean(boolean v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.output.write((byte)(v ? 1 : 0));
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeByte(byte v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.output.write(v);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeShort(short v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.output.write((byte)(v >> 8));
      this.output.write((byte)v);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeInt(int v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.writeVInt(v);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeLong(long v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.writeVLong(v);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeFloat(float vf) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      int v = Float.floatToIntBits(vf);
      this.output.write((byte)(v >> 24));
      this.output.write((byte)(v >> 16));
      this.output.write((byte)(v >> 8));
      this.output.write((byte)v);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeDouble(double v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      LazyBinaryUtils.writeDouble(this.output, v);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeString(byte[] v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      int length = v.length;
      this.writeVInt(length);
      this.output.write(v, 0, length);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeString(byte[] v, int start, int length) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.writeVInt(length);
      this.output.write(v, start, length);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeHiveChar(HiveChar hiveChar) throws IOException {
      String string = hiveChar.getStrippedValue();
      byte[] bytes = string.getBytes();
      this.writeString(bytes);
   }

   public void writeHiveVarchar(HiveVarchar hiveVarchar) throws IOException {
      String string = hiveVarchar.getValue();
      byte[] bytes = string.getBytes();
      this.writeString(bytes);
   }

   public void writeBinary(byte[] v) throws IOException {
      this.writeString(v);
   }

   public void writeBinary(byte[] v, int start, int length) throws IOException {
      this.writeString(v, start, length);
   }

   public void writeDate(Date date) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.writeVInt(DateWritable.dateToDays(date));
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeDate(int dateAsDays) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      this.writeVInt(dateAsDays);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeTimestamp(Timestamp v) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      if (this.timestampWritable == null) {
         this.timestampWritable = new TimestampWritable();
      }

      this.timestampWritable.set(v);
      this.timestampWritable.writeToByteStream(this.output);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeHiveIntervalYearMonth(HiveIntervalYearMonth viyt) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      if (this.hiveIntervalYearMonthWritable == null) {
         this.hiveIntervalYearMonthWritable = new HiveIntervalYearMonthWritable();
      }

      this.hiveIntervalYearMonthWritable.set(viyt);
      this.hiveIntervalYearMonthWritable.writeToByteStream(this.output);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeHiveIntervalYearMonth(int totalMonths) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      if (this.hiveIntervalYearMonthWritable == null) {
         this.hiveIntervalYearMonthWritable = new HiveIntervalYearMonthWritable();
      }

      this.hiveIntervalYearMonthWritable.set(totalMonths);
      this.hiveIntervalYearMonthWritable.writeToByteStream(this.output);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeHiveIntervalDayTime(HiveIntervalDayTime vidt) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      if (this.hiveIntervalDayTimeWritable == null) {
         this.hiveIntervalDayTimeWritable = new HiveIntervalDayTimeWritable();
      }

      this.hiveIntervalDayTimeWritable.set(vidt);
      this.hiveIntervalDayTimeWritable.writeToByteStream(this.output);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeHiveDecimal(HiveDecimal dec, int scale) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      if (this.scratchLongs == null) {
         this.scratchLongs = new long[6];
         this.scratchBuffer = new byte[49];
      }

      LazyBinarySerDe.writeToByteStream(this.output, (HiveDecimal)dec, this.scratchLongs, this.scratchBuffer);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   public void writeHiveDecimal(HiveDecimalWritable decWritable, int scale) throws IOException {
      if (this.fieldIndex % 8 == 0) {
         if (this.fieldIndex > 0) {
            this.output.writeByte(this.nullOffset, this.nullByte);
            this.nullByte = 0;
            this.nullOffset = (long)this.output.getLength();
         }

         this.output.reserve(1);
      }

      this.nullByte = (byte)(this.nullByte | 1 << this.fieldIndex % 8);
      if (this.scratchLongs == null) {
         this.scratchLongs = new long[6];
         this.scratchBuffer = new byte[49];
      }

      LazyBinarySerDe.writeToByteStream(this.output, (HiveDecimalWritable)decWritable, this.scratchLongs, this.scratchBuffer);
      ++this.fieldIndex;
      if (this.fieldIndex == this.fieldCount) {
         this.output.writeByte(this.nullOffset, this.nullByte);
      }

   }

   private void writeVInt(int v) {
      int len = LazyBinaryUtils.writeVLongToByteArray(this.vLongBytes, (long)v);
      this.output.write(this.vLongBytes, 0, len);
   }

   private void writeVLong(long v) {
      int len = LazyBinaryUtils.writeVLongToByteArray(this.vLongBytes, v);
      this.output.write(this.vLongBytes, 0, len);
   }
}
