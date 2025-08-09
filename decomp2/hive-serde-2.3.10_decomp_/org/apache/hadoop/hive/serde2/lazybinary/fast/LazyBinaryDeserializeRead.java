package org.apache.hadoop.hive.serde2.lazybinary.fast;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.hive.serde2.fast.DeserializeRead;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.WritableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LazyBinaryDeserializeRead extends DeserializeRead {
   public static final Logger LOG = LoggerFactory.getLogger(LazyBinaryDeserializeRead.class.getName());
   private byte[] bytes;
   private int start;
   private int offset;
   private int end;
   private int fieldCount;
   private int fieldStart;
   private int fieldIndex;
   private byte nullByte;
   private LazyBinaryUtils.VInt tempVInt;
   private LazyBinaryUtils.VLong tempVLong;

   public LazyBinaryDeserializeRead(TypeInfo[] typeInfos, boolean useExternalBuffer) {
      super(typeInfos, useExternalBuffer);
      this.fieldCount = typeInfos.length;
      this.tempVInt = new LazyBinaryUtils.VInt();
      this.tempVLong = new LazyBinaryUtils.VLong();
      this.currentExternalBufferNeeded = false;
   }

   private LazyBinaryDeserializeRead() {
   }

   public void set(byte[] bytes, int offset, int length) {
      this.bytes = bytes;
      this.offset = offset;
      this.start = offset;
      this.end = offset + length;
      this.fieldIndex = 0;
   }

   public String getDetailedReadPositionString() {
      StringBuffer sb = new StringBuffer();
      sb.append("Reading byte[] of length ");
      sb.append(this.bytes.length);
      sb.append(" at start offset ");
      sb.append(this.start);
      sb.append(" for length ");
      sb.append(this.end - this.start);
      sb.append(" to read ");
      sb.append(this.fieldCount);
      sb.append(" fields with types ");
      sb.append(Arrays.toString(this.typeInfos));
      sb.append(".  Read field #");
      sb.append(this.fieldIndex);
      sb.append(" at field start position ");
      sb.append(this.fieldStart);
      sb.append(" current read offset ");
      sb.append(this.offset);
      return sb.toString();
   }

   public boolean readNextField() throws IOException {
      if (this.fieldIndex >= this.fieldCount) {
         return false;
      } else {
         this.fieldStart = this.offset;
         if (this.fieldIndex == 0) {
            if (this.offset >= this.end) {
               throw new EOFException();
            }

            this.nullByte = this.bytes[this.offset++];
         }

         if ((this.nullByte & 1 << this.fieldIndex % 8) == 0) {
            ++this.fieldIndex;
            if (this.fieldIndex < this.fieldCount && this.fieldIndex % 8 == 0) {
               if (this.offset >= this.end) {
                  throw new EOFException();
               }

               this.nullByte = this.bytes[this.offset++];
            }

            return false;
         } else if (this.offset >= this.end) {
            throw new EOFException();
         } else {
            switch (this.primitiveCategories[this.fieldIndex]) {
               case BOOLEAN:
                  this.currentBoolean = this.bytes[this.offset++] != 0;
                  break;
               case BYTE:
                  this.currentByte = this.bytes[this.offset++];
                  break;
               case SHORT:
                  if (this.offset + 2 > this.end) {
                     throw new EOFException();
                  }

                  this.currentShort = LazyBinaryUtils.byteArrayToShort(this.bytes, this.offset);
                  this.offset += 2;
                  break;
               case INT:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  this.currentInt = this.tempVInt.value;
                  break;
               case LONG:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVLong(this.bytes, this.offset, this.tempVLong);
                  this.offset += this.tempVLong.length;
                  this.currentLong = this.tempVLong.value;
                  break;
               case FLOAT:
                  if (this.offset + 4 > this.end) {
                     throw new EOFException();
                  }

                  this.currentFloat = Float.intBitsToFloat(LazyBinaryUtils.byteArrayToInt(this.bytes, this.offset));
                  this.offset += 4;
                  break;
               case DOUBLE:
                  if (this.offset + 8 > this.end) {
                     throw new EOFException();
                  }

                  this.currentDouble = Double.longBitsToDouble(LazyBinaryUtils.byteArrayToLong(this.bytes, this.offset));
                  this.offset += 8;
                  break;
               case BINARY:
               case STRING:
               case CHAR:
               case VARCHAR:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  int saveStart = this.offset;
                  int length = this.tempVInt.value;
                  this.offset += length;
                  if (this.offset > this.end) {
                     throw new EOFException();
                  }

                  this.currentBytes = this.bytes;
                  this.currentBytesStart = saveStart;
                  this.currentBytesLength = length;
                  break;
               case DATE:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  this.currentDateWritable.set(this.tempVInt.value);
                  break;
               case TIMESTAMP:
                  int length = TimestampWritable.getTotalLength(this.bytes, this.offset);
                  int saveStart = this.offset;
                  this.offset += length;
                  if (this.offset > this.end) {
                     throw new EOFException();
                  }

                  this.currentTimestampWritable.set(this.bytes, saveStart);
                  break;
               case INTERVAL_YEAR_MONTH:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  this.currentHiveIntervalYearMonthWritable.set(this.tempVInt.value);
                  break;
               case INTERVAL_DAY_TIME:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) >= this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVLong(this.bytes, this.offset, this.tempVLong);
                  this.offset += this.tempVLong.length;
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  this.currentHiveIntervalDayTimeWritable.set(this.tempVLong.value, this.tempVInt.value);
                  break;
               case DECIMAL:
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) >= this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  int readScale = this.tempVInt.value;
                  if (this.offset + WritableUtils.decodeVIntSize(this.bytes[this.offset]) > this.end) {
                     throw new EOFException();
                  }

                  LazyBinaryUtils.readVInt(this.bytes, this.offset, this.tempVInt);
                  this.offset += this.tempVInt.length;
                  int saveStart = this.offset;
                  this.offset += this.tempVInt.value;
                  if (this.offset > this.end) {
                     throw new EOFException();
                  }

                  int length = this.offset - saveStart;
                  this.currentHiveDecimalWritable.setFromBigIntegerBytesAndScale(this.bytes, saveStart, length, readScale);
                  boolean decimalIsNull = !this.currentHiveDecimalWritable.isSet();
                  if (!decimalIsNull) {
                     DecimalTypeInfo decimalTypeInfo = (DecimalTypeInfo)this.typeInfos[this.fieldIndex];
                     int precision = decimalTypeInfo.getPrecision();
                     int scale = decimalTypeInfo.getScale();
                     decimalIsNull = !this.currentHiveDecimalWritable.mutateEnforcePrecisionScale(precision, scale);
                  }

                  if (decimalIsNull) {
                     ++this.fieldIndex;
                     if (this.fieldIndex < this.fieldCount && this.fieldIndex % 8 == 0) {
                        if (this.offset >= this.end) {
                           throw new EOFException();
                        }

                        this.nullByte = this.bytes[this.offset++];
                     }

                     return false;
                  }
                  break;
               default:
                  throw new Error("Unexpected primitive category " + this.primitiveCategories[this.fieldIndex].name());
            }

            ++this.fieldIndex;
            if (this.fieldIndex < this.fieldCount && this.fieldIndex % 8 == 0) {
               if (this.offset >= this.end) {
                  throw new EOFException();
               }

               this.nullByte = this.bytes[this.offset++];
            }

            return true;
         }
      }
   }

   public void skipNextField() throws IOException {
      this.readNextField();
   }

   public boolean isEndOfInputReached() {
      return this.offset == this.end;
   }
}
