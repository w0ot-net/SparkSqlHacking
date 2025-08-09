package org.apache.hadoop.hive.serde2.lazy.fast;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.sql.Timestamp;
import org.apache.commons.codec.binary.Base64;
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
import org.apache.hadoop.hive.serde2.lazy.LazyDate;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveDecimal;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveIntervalDayTime;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.lazy.LazySerDeParameters;
import org.apache.hadoop.hive.serde2.lazy.LazyTimestamp;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LazySimpleSerializeWrite implements SerializeWrite {
   public static final Logger LOG = LoggerFactory.getLogger(LazySimpleSerializeWrite.class.getName());
   private LazySerDeParameters lazyParams;
   private byte separator;
   private boolean[] needsEscape;
   private boolean isEscaped;
   private byte escapeChar;
   private byte[] nullSequenceBytes;
   private ByteStream.Output output;
   private int fieldCount;
   private int index;
   private DateWritable dateWritable;
   private TimestampWritable timestampWritable;
   private HiveIntervalYearMonthWritable hiveIntervalYearMonthWritable;
   private HiveIntervalDayTimeWritable hiveIntervalDayTimeWritable;
   private HiveIntervalDayTime hiveIntervalDayTime;
   private byte[] decimalScratchBuffer;

   public LazySimpleSerializeWrite(int fieldCount, byte separator, LazySerDeParameters lazyParams) {
      this();
      this.fieldCount = fieldCount;
      this.separator = separator;
      this.lazyParams = lazyParams;
      this.isEscaped = lazyParams.isEscaped();
      this.escapeChar = lazyParams.getEscapeChar();
      this.needsEscape = lazyParams.getNeedsEscape();
      this.nullSequenceBytes = lazyParams.getNullSequence().getBytes();
   }

   private LazySimpleSerializeWrite() {
   }

   public void set(ByteStream.Output output) {
      this.output = output;
      output.reset();
      this.index = 0;
   }

   public void setAppend(ByteStream.Output output) {
      this.output = output;
      this.index = 0;
   }

   public void reset() {
      this.output.reset();
      this.index = 0;
   }

   public void writeNull() throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      this.output.write(this.nullSequenceBytes);
      ++this.index;
   }

   public void writeBoolean(boolean v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (v) {
         this.output.write(LazyUtils.trueBytes, 0, LazyUtils.trueBytes.length);
      } else {
         this.output.write(LazyUtils.falseBytes, 0, LazyUtils.falseBytes.length);
      }

      ++this.index;
   }

   public void writeByte(byte v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      LazyInteger.writeUTF8(this.output, v);
      ++this.index;
   }

   public void writeShort(short v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      LazyInteger.writeUTF8(this.output, v);
      ++this.index;
   }

   public void writeInt(int v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      LazyInteger.writeUTF8(this.output, v);
      ++this.index;
   }

   public void writeLong(long v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      LazyLong.writeUTF8(this.output, v);
      ++this.index;
   }

   public void writeFloat(float vf) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      ByteBuffer b = Text.encode(String.valueOf(vf));
      this.output.write(b.array(), 0, b.limit());
      ++this.index;
   }

   public void writeDouble(double v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      ByteBuffer b = Text.encode(String.valueOf(v));
      this.output.write(b.array(), 0, b.limit());
      ++this.index;
   }

   public void writeString(byte[] v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      LazyUtils.writeEscaped(this.output, v, 0, v.length, this.isEscaped, this.escapeChar, this.needsEscape);
      ++this.index;
   }

   public void writeString(byte[] v, int start, int length) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      LazyUtils.writeEscaped(this.output, v, start, length, this.isEscaped, this.escapeChar, this.needsEscape);
      ++this.index;
   }

   public void writeHiveChar(HiveChar hiveChar) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      ByteBuffer b = Text.encode(hiveChar.getPaddedValue());
      LazyUtils.writeEscaped(this.output, b.array(), 0, b.limit(), this.isEscaped, this.escapeChar, this.needsEscape);
      ++this.index;
   }

   public void writeHiveVarchar(HiveVarchar hiveVarchar) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      ByteBuffer b = Text.encode(hiveVarchar.getValue());
      LazyUtils.writeEscaped(this.output, b.array(), 0, b.limit(), this.isEscaped, this.escapeChar, this.needsEscape);
      ++this.index;
   }

   public void writeBinary(byte[] v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      byte[] toEncode = new byte[v.length];
      System.arraycopy(v, 0, toEncode, 0, v.length);
      byte[] toWrite = Base64.encodeBase64(toEncode);
      this.output.write(toWrite, 0, toWrite.length);
      ++this.index;
   }

   public void writeBinary(byte[] v, int start, int length) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      byte[] toEncode = new byte[length];
      System.arraycopy(v, start, toEncode, 0, length);
      byte[] toWrite = Base64.encodeBase64(toEncode);
      this.output.write(toWrite, 0, toWrite.length);
      ++this.index;
   }

   public void writeDate(Date date) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.dateWritable == null) {
         this.dateWritable = new DateWritable();
      }

      this.dateWritable.set(date);
      LazyDate.writeUTF8(this.output, this.dateWritable);
      ++this.index;
   }

   public void writeDate(int dateAsDays) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.dateWritable == null) {
         this.dateWritable = new DateWritable();
      }

      this.dateWritable.set(dateAsDays);
      LazyDate.writeUTF8(this.output, this.dateWritable);
      ++this.index;
   }

   public void writeTimestamp(Timestamp v) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.timestampWritable == null) {
         this.timestampWritable = new TimestampWritable();
      }

      this.timestampWritable.set(v);
      LazyTimestamp.writeUTF8(this.output, this.timestampWritable);
      ++this.index;
   }

   public void writeHiveIntervalYearMonth(HiveIntervalYearMonth viyt) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.hiveIntervalYearMonthWritable == null) {
         this.hiveIntervalYearMonthWritable = new HiveIntervalYearMonthWritable();
      }

      this.hiveIntervalYearMonthWritable.set(viyt);
      LazyHiveIntervalYearMonth.writeUTF8(this.output, this.hiveIntervalYearMonthWritable);
      ++this.index;
   }

   public void writeHiveIntervalYearMonth(int totalMonths) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.hiveIntervalYearMonthWritable == null) {
         this.hiveIntervalYearMonthWritable = new HiveIntervalYearMonthWritable();
      }

      this.hiveIntervalYearMonthWritable.set(totalMonths);
      LazyHiveIntervalYearMonth.writeUTF8(this.output, this.hiveIntervalYearMonthWritable);
      ++this.index;
   }

   public void writeHiveIntervalDayTime(HiveIntervalDayTime vidt) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.hiveIntervalDayTimeWritable == null) {
         this.hiveIntervalDayTimeWritable = new HiveIntervalDayTimeWritable();
      }

      this.hiveIntervalDayTimeWritable.set(vidt);
      LazyHiveIntervalDayTime.writeUTF8(this.output, this.hiveIntervalDayTimeWritable);
      ++this.index;
   }

   public void writeHiveDecimal(HiveDecimal dec, int scale) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.decimalScratchBuffer == null) {
         this.decimalScratchBuffer = new byte[79];
      }

      LazyHiveDecimal.writeUTF8(this.output, (HiveDecimal)dec, scale, this.decimalScratchBuffer);
      ++this.index;
   }

   public void writeHiveDecimal(HiveDecimalWritable decWritable, int scale) throws IOException {
      if (this.index > 0) {
         this.output.write(this.separator);
      }

      if (this.decimalScratchBuffer == null) {
         this.decimalScratchBuffer = new byte[79];
      }

      LazyHiveDecimal.writeUTF8(this.output, (HiveDecimalWritable)decWritable, scale, this.decimalScratchBuffer);
      ++this.index;
   }
}
