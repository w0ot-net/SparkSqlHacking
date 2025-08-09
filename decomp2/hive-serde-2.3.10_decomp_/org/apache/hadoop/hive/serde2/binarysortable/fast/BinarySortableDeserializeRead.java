package org.apache.hadoop.hive.serde2.binarysortable.fast;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.hadoop.hive.serde2.binarysortable.InputByteBuffer;
import org.apache.hadoop.hive.serde2.fast.DeserializeRead;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BinarySortableDeserializeRead extends DeserializeRead {
   public static final Logger LOG = LoggerFactory.getLogger(BinarySortableDeserializeRead.class.getName());
   private boolean[] columnSortOrderIsDesc;
   private int fieldIndex;
   private int fieldCount;
   private int start;
   private int end;
   private int fieldStart;
   private int bytesStart;
   private int internalBufferLen;
   private byte[] internalBuffer;
   private byte[] tempTimestampBytes;
   private byte[] tempDecimalBuffer;
   private InputByteBuffer inputByteBuffer;

   public BinarySortableDeserializeRead(PrimitiveTypeInfo[] primitiveTypeInfos, boolean useExternalBuffer) {
      this(primitiveTypeInfos, useExternalBuffer, (boolean[])null);
   }

   public BinarySortableDeserializeRead(TypeInfo[] typeInfos, boolean useExternalBuffer, boolean[] columnSortOrderIsDesc) {
      super(typeInfos, useExternalBuffer);
      this.inputByteBuffer = new InputByteBuffer();
      this.fieldCount = typeInfos.length;
      if (columnSortOrderIsDesc != null) {
         this.columnSortOrderIsDesc = columnSortOrderIsDesc;
      } else {
         this.columnSortOrderIsDesc = new boolean[typeInfos.length];
         Arrays.fill(this.columnSortOrderIsDesc, false);
      }

      this.inputByteBuffer = new InputByteBuffer();
      this.internalBufferLen = -1;
   }

   private BinarySortableDeserializeRead() {
      this.inputByteBuffer = new InputByteBuffer();
   }

   public void set(byte[] bytes, int offset, int length) {
      this.fieldIndex = -1;
      this.start = offset;
      this.end = offset + length;
      this.inputByteBuffer.reset(bytes, this.start, this.end);
   }

   public String getDetailedReadPositionString() {
      StringBuffer sb = new StringBuffer();
      sb.append("Reading inputByteBuffer of length ");
      sb.append(this.inputByteBuffer.getEnd());
      sb.append(" at start offset ");
      sb.append(this.start);
      sb.append(" for length ");
      sb.append(this.end - this.start);
      sb.append(" to read ");
      sb.append(this.fieldCount);
      sb.append(" fields with types ");
      sb.append(Arrays.toString(this.typeInfos));
      sb.append(".  ");
      if (this.fieldIndex == -1) {
         sb.append("Before first field?");
      } else {
         sb.append("Read field #");
         sb.append(this.fieldIndex);
         sb.append(" at field start position ");
         sb.append(this.fieldStart);
         sb.append(" current read offset ");
         sb.append(this.inputByteBuffer.tell());
      }

      sb.append(" column sort order ");
      sb.append(Arrays.toString(this.columnSortOrderIsDesc));
      return sb.toString();
   }

   public boolean readNextField() throws IOException {
      ++this.fieldIndex;
      if (this.fieldIndex >= this.fieldCount) {
         return false;
      } else if (this.inputByteBuffer.isEof()) {
         return false;
      } else {
         this.fieldStart = this.inputByteBuffer.tell();
         byte isNullByte = this.inputByteBuffer.read(this.columnSortOrderIsDesc[this.fieldIndex]);
         if (isNullByte == 0) {
            return false;
         } else {
            switch (this.primitiveCategories[this.fieldIndex]) {
               case BOOLEAN:
                  this.currentBoolean = this.inputByteBuffer.read(this.columnSortOrderIsDesc[this.fieldIndex]) == 2;
                  return true;
               case BYTE:
                  this.currentByte = (byte)(this.inputByteBuffer.read(this.columnSortOrderIsDesc[this.fieldIndex]) ^ 128);
                  return true;
               case SHORT:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int v = this.inputByteBuffer.read(invert) ^ 128;
                  v = (v << 8) + (this.inputByteBuffer.read(invert) & 255);
                  this.currentShort = (short)v;
                  return true;
               case INT:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int v = this.inputByteBuffer.read(invert) ^ 128;

                  for(int i = 0; i < 3; ++i) {
                     v = (v << 8) + (this.inputByteBuffer.read(invert) & 255);
                  }

                  this.currentInt = v;
                  return true;
               case LONG:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  long v = (long)(this.inputByteBuffer.read(invert) ^ 128);

                  for(int i = 0; i < 7; ++i) {
                     v = (v << 8) + (long)(this.inputByteBuffer.read(invert) & 255);
                  }

                  this.currentLong = v;
                  return true;
               case DATE:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int v = this.inputByteBuffer.read(invert) ^ 128;

                  for(int i = 0; i < 3; ++i) {
                     v = (v << 8) + (this.inputByteBuffer.read(invert) & 255);
                  }

                  this.currentDateWritable.set(v);
                  return true;
               case TIMESTAMP:
                  if (this.tempTimestampBytes == null) {
                     this.tempTimestampBytes = new byte[11];
                  }

                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];

                  for(int i = 0; i < this.tempTimestampBytes.length; ++i) {
                     this.tempTimestampBytes[i] = this.inputByteBuffer.read(invert);
                  }

                  this.currentTimestampWritable.setBinarySortable(this.tempTimestampBytes, 0);
                  return true;
               case FLOAT:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int v = 0;

                  for(int i = 0; i < 4; ++i) {
                     v = (v << 8) + (this.inputByteBuffer.read(invert) & 255);
                  }

                  if ((v & Integer.MIN_VALUE) == 0) {
                     v = ~v;
                  } else {
                     v ^= Integer.MIN_VALUE;
                  }

                  this.currentFloat = Float.intBitsToFloat(v);
                  return true;
               case DOUBLE:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  long v = 0L;

                  for(int i = 0; i < 8; ++i) {
                     v = (v << 8) + (long)(this.inputByteBuffer.read(invert) & 255);
                  }

                  if ((v & Long.MIN_VALUE) == 0L) {
                     v = ~v;
                  } else {
                     v ^= Long.MIN_VALUE;
                  }

                  this.currentDouble = Double.longBitsToDouble(v);
                  return true;
               case BINARY:
               case STRING:
               case CHAR:
               case VARCHAR:
                  this.bytesStart = this.inputByteBuffer.tell();
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int length = 0;

                  while(true) {
                     byte b = this.inputByteBuffer.read(invert);
                     if (b == 0) {
                        if (length != 0 && (invert || length != this.inputByteBuffer.tell() - this.bytesStart - 1)) {
                           if (this.useExternalBuffer) {
                              this.currentExternalBufferNeeded = true;
                              this.currentExternalBufferNeededLen = length;
                           } else {
                              this.currentExternalBufferNeeded = false;
                              if (this.internalBufferLen < length) {
                                 this.internalBufferLen = length;
                                 this.internalBuffer = new byte[this.internalBufferLen];
                              }

                              this.copyToBuffer(this.internalBuffer, 0, length);
                              this.currentBytes = this.internalBuffer;
                              this.currentBytesStart = 0;
                              this.currentBytesLength = length;
                           }
                        } else {
                           this.currentExternalBufferNeeded = false;
                           this.currentBytes = this.inputByteBuffer.getData();
                           this.currentBytesStart = this.bytesStart;
                           this.currentBytesLength = length;
                        }

                        return true;
                     }

                     if (b == 1) {
                        this.inputByteBuffer.read(invert);
                     }

                     ++length;
                  }
               case INTERVAL_YEAR_MONTH:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int v = this.inputByteBuffer.read(invert) ^ 128;

                  for(int i = 0; i < 3; ++i) {
                     v = (v << 8) + (this.inputByteBuffer.read(invert) & 255);
                  }

                  this.currentHiveIntervalYearMonthWritable.set(v);
                  return true;
               case INTERVAL_DAY_TIME:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  long totalSecs = (long)(this.inputByteBuffer.read(invert) ^ 128);

                  for(int i = 0; i < 7; ++i) {
                     totalSecs = (totalSecs << 8) + (long)(this.inputByteBuffer.read(invert) & 255);
                  }

                  int nanos = this.inputByteBuffer.read(invert) ^ 128;

                  for(int i = 0; i < 3; ++i) {
                     nanos = (nanos << 8) + (this.inputByteBuffer.read(invert) & 255);
                  }

                  this.currentHiveIntervalDayTimeWritable.set(totalSecs, nanos);
                  return true;
               case DECIMAL:
                  boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
                  int b = this.inputByteBuffer.read(invert) - 1;
                  if (b != 1 && b != -1 && b != 0) {
                     throw new IOException("Unexpected byte value " + b + " in binary sortable format data (invert " + invert + ")");
                  } else {
                     boolean positive = b != -1;
                     int factor = this.inputByteBuffer.read(invert) ^ 128;

                     for(int i = 0; i < 3; ++i) {
                        factor = (factor << 8) + (this.inputByteBuffer.read(invert) & 255);
                     }

                     if (!positive) {
                        factor = -factor;
                     }

                     int decimalStart = this.inputByteBuffer.tell();
                     int length = 0;

                     while(true) {
                        b = this.inputByteBuffer.read(positive ? invert : !invert);
                        if (b == 1) {
                           throw new IOException("Expected -1 and found byte value " + b + " in binary sortable format data (invert " + invert + ")");
                        }

                        if (b == 0) {
                           if (this.tempDecimalBuffer == null || this.tempDecimalBuffer.length < length) {
                              this.tempDecimalBuffer = new byte[length];
                           }

                           this.inputByteBuffer.seek(decimalStart);

                           for(int i = 0; i < length; ++i) {
                              this.tempDecimalBuffer[i] = this.inputByteBuffer.read(positive ? invert : !invert);
                           }

                           this.inputByteBuffer.read(positive ? invert : !invert);
                           new String(this.tempDecimalBuffer, 0, length, StandardCharsets.UTF_8);
                           int scale = length - factor;
                           this.currentHiveDecimalWritable.setFromDigitsOnlyBytesWithScale(!positive, this.tempDecimalBuffer, 0, length, scale);
                           boolean decimalIsNull = !this.currentHiveDecimalWritable.isSet();
                           if (!decimalIsNull) {
                              DecimalTypeInfo decimalTypeInfo = (DecimalTypeInfo)this.typeInfos[this.fieldIndex];
                              int enforcePrecision = decimalTypeInfo.getPrecision();
                              int enforceScale = decimalTypeInfo.getScale();
                              decimalIsNull = !this.currentHiveDecimalWritable.mutateEnforcePrecisionScale(enforcePrecision, enforceScale);
                           }

                           if (decimalIsNull) {
                              return false;
                           }

                           return true;
                        }

                        ++length;
                     }
                  }
               default:
                  throw new RuntimeException("Unexpected primitive type category " + this.primitiveCategories[this.fieldIndex]);
            }
         }
      }
   }

   public void skipNextField() throws IOException {
      this.readNextField();
   }

   public void copyToExternalBuffer(byte[] externalBuffer, int externalBufferStart) throws IOException {
      this.copyToBuffer(externalBuffer, externalBufferStart, this.currentExternalBufferNeededLen);
   }

   private void copyToBuffer(byte[] buffer, int bufferStart, int bufferLength) throws IOException {
      boolean invert = this.columnSortOrderIsDesc[this.fieldIndex];
      this.inputByteBuffer.seek(this.bytesStart);

      for(int i = 0; i < bufferLength; ++i) {
         byte b = this.inputByteBuffer.read(invert);
         if (b == 1) {
            b = (byte)(this.inputByteBuffer.read(invert) - 1);
         }

         buffer[bufferStart + i] = b;
      }

      byte b = this.inputByteBuffer.read(invert);
      if (b != 0) {
         throw new RuntimeException("Expected 0 terminating byte");
      }
   }

   public boolean isEndOfInputReached() {
      return this.inputByteBuffer.isEof();
   }
}
