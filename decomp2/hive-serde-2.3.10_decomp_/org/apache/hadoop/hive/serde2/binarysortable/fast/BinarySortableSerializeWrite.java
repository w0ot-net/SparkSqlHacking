package org.apache.hadoop.hive.serde2.binarysortable.fast;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.binarysortable.BinarySortableSerDe;
import org.apache.hadoop.hive.serde2.fast.SerializeWrite;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BinarySortableSerializeWrite implements SerializeWrite {
   public static final Logger LOG = LoggerFactory.getLogger(BinarySortableSerializeWrite.class.getName());
   private ByteStream.Output output;
   private boolean[] columnSortOrderIsDesc;
   private byte[] columnNullMarker;
   private byte[] columnNotNullMarker;
   private int index;
   private int fieldCount;
   private TimestampWritable tempTimestampWritable;
   private byte[] decimalBytesScratch;

   public BinarySortableSerializeWrite(boolean[] columnSortOrderIsDesc, byte[] columnNullMarker, byte[] columnNotNullMarker) {
      this();
      this.fieldCount = columnSortOrderIsDesc.length;
      this.columnSortOrderIsDesc = columnSortOrderIsDesc;
      this.columnNullMarker = columnNullMarker;
      this.columnNotNullMarker = columnNotNullMarker;
   }

   public BinarySortableSerializeWrite(int fieldCount) {
      this();
      this.fieldCount = fieldCount;
      this.columnSortOrderIsDesc = new boolean[fieldCount];
      Arrays.fill(this.columnSortOrderIsDesc, false);
      this.columnNullMarker = new byte[fieldCount];
      Arrays.fill(this.columnNullMarker, (byte)0);
      this.columnNotNullMarker = new byte[fieldCount];
      Arrays.fill(this.columnNotNullMarker, (byte)1);
   }

   private BinarySortableSerializeWrite() {
      this.tempTimestampWritable = new TimestampWritable();
   }

   public void set(ByteStream.Output output) {
      this.output = output;
      this.output.reset();
      this.index = -1;
   }

   public void setAppend(ByteStream.Output output) {
      this.output = output;
      this.index = -1;
   }

   public void reset() {
      this.output.reset();
      this.index = -1;
   }

   public void writeNull() throws IOException {
      ++this.index;
      BinarySortableSerDe.writeByte(this.output, this.columnNullMarker[this.index], this.columnSortOrderIsDesc[this.index]);
   }

   public void writeBoolean(boolean v) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.writeByte(this.output, (byte)(v ? 2 : 1), invert);
   }

   public void writeByte(byte v) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.writeByte(this.output, (byte)(v ^ 128), invert);
   }

   public void writeShort(short v) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeShort(this.output, v, invert);
   }

   public void writeInt(int v) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeInt(this.output, v, invert);
   }

   public void writeLong(long v) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeLong(this.output, v, invert);
   }

   public void writeFloat(float vf) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeFloat(this.output, vf, invert);
   }

   public void writeDouble(double vd) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeDouble(this.output, vd, invert);
   }

   public void writeString(byte[] v) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeBytes(this.output, v, 0, v.length, invert);
   }

   public void writeString(byte[] v, int start, int length) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeBytes(this.output, v, start, length, invert);
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
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeBytes(this.output, v, 0, v.length, invert);
   }

   public void writeBinary(byte[] v, int start, int length) {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeBytes(this.output, v, start, length, invert);
   }

   public void writeDate(Date date) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeInt(this.output, DateWritable.dateToDays(date), invert);
   }

   public void writeDate(int dateAsDays) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeInt(this.output, dateAsDays, invert);
   }

   public void writeTimestamp(Timestamp vt) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      this.tempTimestampWritable.set(vt);
      BinarySortableSerDe.serializeTimestampWritable(this.output, this.tempTimestampWritable, invert);
   }

   public void writeHiveIntervalYearMonth(HiveIntervalYearMonth viyt) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeHiveIntervalYearMonth(this.output, viyt, invert);
   }

   public void writeHiveIntervalYearMonth(int totalMonths) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeInt(this.output, totalMonths, invert);
   }

   public void writeHiveIntervalDayTime(HiveIntervalDayTime vidt) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      BinarySortableSerDe.serializeHiveIntervalDayTime(this.output, vidt, invert);
   }

   public void writeHiveDecimal(HiveDecimal dec, int scale) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      if (this.decimalBytesScratch == null) {
         this.decimalBytesScratch = new byte[79];
      }

      BinarySortableSerDe.serializeHiveDecimal(this.output, dec, invert, this.decimalBytesScratch);
   }

   public void writeHiveDecimal(HiveDecimalWritable decWritable, int scale) throws IOException {
      ++this.index;
      boolean invert = this.columnSortOrderIsDesc[this.index];
      BinarySortableSerDe.writeByte(this.output, this.columnNotNullMarker[this.index], invert);
      if (this.decimalBytesScratch == null) {
         this.decimalBytesScratch = new byte[79];
      }

      BinarySortableSerDe.serializeHiveDecimal(this.output, decWritable, invert, this.decimalBytesScratch);
   }
}
