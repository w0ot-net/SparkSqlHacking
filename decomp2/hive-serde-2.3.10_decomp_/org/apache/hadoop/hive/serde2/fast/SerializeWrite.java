package org.apache.hadoop.hive.serde2.fast;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public interface SerializeWrite {
   void set(ByteStream.Output var1);

   void setAppend(ByteStream.Output var1);

   void reset();

   void writeNull() throws IOException;

   void writeBoolean(boolean var1) throws IOException;

   void writeByte(byte var1) throws IOException;

   void writeShort(short var1) throws IOException;

   void writeInt(int var1) throws IOException;

   void writeLong(long var1) throws IOException;

   void writeFloat(float var1) throws IOException;

   void writeDouble(double var1) throws IOException;

   void writeString(byte[] var1) throws IOException;

   void writeString(byte[] var1, int var2, int var3) throws IOException;

   void writeHiveChar(HiveChar var1) throws IOException;

   void writeHiveVarchar(HiveVarchar var1) throws IOException;

   void writeBinary(byte[] var1) throws IOException;

   void writeBinary(byte[] var1, int var2, int var3) throws IOException;

   void writeDate(Date var1) throws IOException;

   void writeDate(int var1) throws IOException;

   void writeTimestamp(Timestamp var1) throws IOException;

   void writeHiveIntervalYearMonth(HiveIntervalYearMonth var1) throws IOException;

   void writeHiveIntervalYearMonth(int var1) throws IOException;

   void writeHiveIntervalDayTime(HiveIntervalDayTime var1) throws IOException;

   void writeHiveDecimal(HiveDecimal var1, int var2) throws IOException;

   void writeHiveDecimal(HiveDecimalWritable var1, int var2) throws IOException;
}
