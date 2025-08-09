package org.apache.hadoop.hive.serde2.lazy.fast;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Arrays;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.serde2.fast.DeserializeRead;
import org.apache.hadoop.hive.serde2.lazy.LazyBinary;
import org.apache.hadoop.hive.serde2.lazy.LazyByte;
import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.lazy.LazySerDeParameters;
import org.apache.hadoop.hive.serde2.lazy.LazyShort;
import org.apache.hadoop.hive.serde2.lazy.LazyUtils;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.Text;
import org.apache.hive.common.util.TimestampParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LazySimpleDeserializeRead extends DeserializeRead {
   public static final Logger LOG = LoggerFactory.getLogger(LazySimpleDeserializeRead.class.getName());
   private int[] startPosition;
   private final byte separator;
   private final boolean isEscaped;
   private final byte escapeChar;
   private final int[] escapeCounts;
   private final byte[] nullSequenceBytes;
   private final boolean isExtendedBooleanLiteral;
   private final int fieldCount;
   private byte[] bytes;
   private int start;
   private int end;
   private boolean parsed;
   private int nextFieldIndex;
   private int currentFieldIndex;
   private int currentFieldStart;
   private int currentFieldLength;
   private int internalBufferLen;
   private byte[] internalBuffer;
   private final TimestampParser timestampParser;
   private boolean isEndOfInputReached;
   private static final byte[] maxLongBytes = Long.valueOf(Long.MAX_VALUE).toString().getBytes();

   public LazySimpleDeserializeRead(TypeInfo[] typeInfos, boolean useExternalBuffer, byte separator, LazySerDeParameters lazyParams) {
      super(typeInfos, useExternalBuffer);
      this.fieldCount = typeInfos.length;
      this.startPosition = new int[this.fieldCount + 1];
      this.separator = separator;
      this.isEscaped = lazyParams.isEscaped();
      if (this.isEscaped) {
         this.escapeChar = lazyParams.getEscapeChar();
         this.escapeCounts = new int[this.fieldCount];
      } else {
         this.escapeChar = 0;
         this.escapeCounts = null;
      }

      this.nullSequenceBytes = lazyParams.getNullSequence().getBytes();
      this.isExtendedBooleanLiteral = lazyParams.isExtendedBooleanLiteral();
      if (lazyParams.isLastColumnTakesRest()) {
         throw new RuntimeException("serialization.last.column.takes.rest not supported");
      } else {
         this.timestampParser = new TimestampParser();
         this.internalBufferLen = -1;
      }
   }

   public LazySimpleDeserializeRead(TypeInfo[] typeInfos, boolean useExternalBuffer, LazySerDeParameters lazyParams) {
      this(typeInfos, useExternalBuffer, lazyParams.getSeparators()[0], lazyParams);
   }

   public void set(byte[] bytes, int offset, int length) {
      this.bytes = bytes;
      this.start = offset;
      this.end = offset + length;
      this.parsed = false;
      this.nextFieldIndex = -1;
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
      sb.append(".  ");
      if (!this.parsed) {
         sb.append("Error during field separator parsing");
      } else {
         sb.append("Read field #");
         sb.append(this.currentFieldIndex);
         sb.append(" at field start position ");
         sb.append(this.startPosition[this.currentFieldIndex]);
         int currentFieldLength = this.startPosition[this.currentFieldIndex + 1] - this.startPosition[this.currentFieldIndex] - 1;
         sb.append(" for field length ");
         sb.append(currentFieldLength);
      }

      return sb.toString();
   }

   private void parse() {
      int fieldId = 0;
      int fieldByteBegin = this.start;
      int fieldByteEnd = this.start;
      byte separator = this.separator;
      int fieldCount = this.fieldCount;
      int[] startPosition = this.startPosition;
      byte[] bytes = this.bytes;
      int end = this.end;
      if (this.isEscaped) {
         byte escapeChar = this.escapeChar;
         int endLessOne = end - 1;
         int[] escapeCounts = this.escapeCounts;
         int escapeCount = 0;

         while(fieldByteEnd < endLessOne) {
            if (bytes[fieldByteEnd] == separator) {
               escapeCounts[fieldId] = escapeCount;
               escapeCount = 0;
               startPosition[fieldId++] = fieldByteBegin;
               if (fieldId == fieldCount) {
                  break;
               }

               ++fieldByteEnd;
               fieldByteBegin = fieldByteEnd;
            } else if (bytes[fieldByteEnd] == escapeChar) {
               fieldByteEnd += 2;
               ++escapeCount;
            } else {
               ++fieldByteEnd;
            }
         }

         if (fieldByteEnd == endLessOne && fieldId < fieldCount) {
            if (bytes[fieldByteEnd] == separator) {
               escapeCounts[fieldId] = escapeCount;
               escapeCount = 0;
               startPosition[fieldId++] = fieldByteBegin;
               if (fieldId <= fieldCount) {
                  ++fieldByteEnd;
                  fieldByteBegin = fieldByteEnd;
               }
            } else {
               ++fieldByteEnd;
            }
         }

         if (fieldByteEnd == end && fieldId < fieldCount) {
            escapeCounts[fieldId] = escapeCount;
            startPosition[fieldId++] = fieldByteBegin;
         }
      } else {
         while(true) {
            if (fieldByteEnd < end) {
               if (bytes[fieldByteEnd] != separator) {
                  ++fieldByteEnd;
                  continue;
               }

               startPosition[fieldId++] = fieldByteBegin;
               if (fieldId != fieldCount) {
                  ++fieldByteEnd;
                  fieldByteBegin = fieldByteEnd;
                  continue;
               }
            }

            if (fieldByteEnd == end && fieldId < fieldCount) {
               startPosition[fieldId++] = fieldByteBegin;
            }
            break;
         }
      }

      if (fieldId == fieldCount || fieldByteEnd == end) {
         Arrays.fill(startPosition, fieldId, startPosition.length, fieldByteEnd + 1);
      }

      this.isEndOfInputReached = fieldByteEnd == end;
   }

   public boolean readNextField() throws IOException {
      if (this.nextFieldIndex + 1 >= this.fieldCount) {
         return false;
      } else {
         ++this.nextFieldIndex;
         return this.readField(this.nextFieldIndex);
      }
   }

   public void skipNextField() throws IOException {
      if (!this.parsed) {
         this.parse();
         this.parsed = true;
      }

      if (this.nextFieldIndex + 1 < this.fieldCount) {
         ++this.nextFieldIndex;
      }

   }

   public boolean isReadFieldSupported() {
      return true;
   }

   private boolean checkNull(byte[] bytes, int start, int len) {
      if (len != this.nullSequenceBytes.length) {
         return false;
      } else {
         byte[] nullSequenceBytes = this.nullSequenceBytes;
         switch (len) {
            case 0:
               return true;
            case 1:
            case 3:
            default:
               for(int i = 0; i < nullSequenceBytes.length; ++i) {
                  if (bytes[start + i] != nullSequenceBytes[i]) {
                     return false;
                  }
               }

               return true;
            case 2:
               return bytes[start] == nullSequenceBytes[0] && bytes[start + 1] == nullSequenceBytes[1];
            case 4:
               return bytes[start] == nullSequenceBytes[0] && bytes[start + 1] == nullSequenceBytes[1] && bytes[start + 2] == nullSequenceBytes[2] && bytes[start + 3] == nullSequenceBytes[3];
         }
      }
   }

   public boolean readField(int fieldIndex) throws IOException {
      if (!this.parsed) {
         this.parse();
         this.parsed = true;
      }

      this.currentFieldIndex = fieldIndex;
      int fieldStart = this.startPosition[fieldIndex];
      this.currentFieldStart = fieldStart;
      int fieldLength = this.startPosition[fieldIndex + 1] - this.startPosition[fieldIndex] - 1;
      this.currentFieldLength = fieldLength;
      if (fieldLength < 0) {
         return false;
      } else {
         byte[] bytes = this.bytes;
         if (this.nullSequenceBytes != null && this.checkNull(bytes, fieldStart, fieldLength)) {
            return false;
         } else {
            try {
               switch (this.primitiveCategories[fieldIndex]) {
                  case BOOLEAN:
                     if (fieldLength == 4) {
                        if (bytes[fieldStart] != 84 && bytes[fieldStart] != 116 || bytes[fieldStart + 1] != 82 && bytes[fieldStart + 1] != 114 || bytes[fieldStart + 2] != 85 && bytes[fieldStart + 2] != 117 || bytes[fieldStart + 3] != 69 && bytes[fieldStart + 3] != 101) {
                           return false;
                        }

                        this.currentBoolean = true;
                     } else if (fieldLength == 5) {
                        if (bytes[fieldStart] != 70 && bytes[fieldStart] != 102 || bytes[fieldStart + 1] != 65 && bytes[fieldStart + 1] != 97 || bytes[fieldStart + 2] != 76 && bytes[fieldStart + 2] != 108 || bytes[fieldStart + 3] != 83 && bytes[fieldStart + 3] != 115 || bytes[fieldStart + 4] != 69 && bytes[fieldStart + 4] != 101) {
                           return false;
                        }

                        this.currentBoolean = false;
                     } else {
                        if (!this.isExtendedBooleanLiteral || fieldLength != 1) {
                           return false;
                        }

                        byte b = bytes[fieldStart];
                        if (b != 49 && b != 116 && b != 84) {
                           if (b != 48 && b != 102 && b != 70) {
                              return false;
                           }

                           this.currentBoolean = false;
                        } else {
                           this.currentBoolean = true;
                        }
                     }

                     return true;
                  case BYTE:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentByte = LazyByte.parseByte(bytes, fieldStart, fieldLength, 10);
                     return true;
                  case SHORT:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentShort = LazyShort.parseShort(bytes, fieldStart, fieldLength, 10);
                     return true;
                  case INT:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentInt = LazyInteger.parseInt(bytes, fieldStart, fieldLength, 10);
                     return true;
                  case LONG:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentLong = LazyLong.parseLong(bytes, fieldStart, fieldLength, 10);
                     return true;
                  case FLOAT:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentFloat = Float.parseFloat(new String(bytes, fieldStart, fieldLength, StandardCharsets.UTF_8));
                     return true;
                  case DOUBLE:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentDouble = StringToDouble.strtod(bytes, fieldStart, fieldLength);
                     return true;
                  case STRING:
                  case CHAR:
                  case VARCHAR:
                     if (this.isEscaped) {
                        if (this.escapeCounts[fieldIndex] == 0) {
                           this.currentExternalBufferNeeded = false;
                           this.currentBytes = bytes;
                           this.currentBytesStart = fieldStart;
                           this.currentBytesLength = fieldLength;
                        } else {
                           int unescapedLength = fieldLength - this.escapeCounts[fieldIndex];
                           if (this.useExternalBuffer) {
                              this.currentExternalBufferNeeded = true;
                              this.currentExternalBufferNeededLen = unescapedLength;
                           } else {
                              this.currentExternalBufferNeeded = false;
                              if (this.internalBufferLen < unescapedLength) {
                                 this.internalBufferLen = unescapedLength;
                                 this.internalBuffer = new byte[this.internalBufferLen];
                              }

                              this.copyToBuffer(this.internalBuffer, 0, unescapedLength);
                              this.currentBytes = this.internalBuffer;
                              this.currentBytesStart = 0;
                              this.currentBytesLength = unescapedLength;
                           }
                        }
                     } else {
                        this.currentExternalBufferNeeded = false;
                        this.currentBytes = bytes;
                        this.currentBytesStart = fieldStart;
                        this.currentBytesLength = fieldLength;
                     }

                     return true;
                  case BINARY:
                     byte[] recv = new byte[fieldLength];
                     System.arraycopy(bytes, fieldStart, recv, 0, fieldLength);
                     byte[] decoded = LazyBinary.decodeIfNeeded(recv);
                     decoded = decoded.length > 0 ? decoded : recv;
                     this.currentBytes = decoded;
                     this.currentBytesStart = 0;
                     this.currentBytesLength = decoded.length;
                     return true;
                  case DATE:
                     if (!LazyUtils.isDateMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     }

                     this.currentDateWritable.set(Date.valueOf(new String(bytes, fieldStart, fieldLength, StandardCharsets.UTF_8)));
                     return true;
                  case TIMESTAMP:
                     if (!LazyUtils.isDateMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     } else {
                        String s = new String(bytes, fieldStart, fieldLength, StandardCharsets.US_ASCII);
                        if (s.compareTo("NULL") == 0) {
                           this.logExceptionMessage(bytes, fieldStart, fieldLength, "TIMESTAMP");
                           return false;
                        } else {
                           try {
                              this.currentTimestampWritable.set(this.timestampParser.parseTimestamp(s));
                           } catch (IllegalArgumentException var11) {
                              this.logExceptionMessage(bytes, fieldStart, fieldLength, "TIMESTAMP");
                              return false;
                           }

                           return true;
                        }
                     }
                  case INTERVAL_YEAR_MONTH:
                     if (fieldLength == 0) {
                        return false;
                     } else {
                        try {
                           String s = new String(bytes, fieldStart, fieldLength, StandardCharsets.UTF_8);
                           this.currentHiveIntervalYearMonthWritable.set(HiveIntervalYearMonth.valueOf(s));
                        } catch (Exception var10) {
                           this.logExceptionMessage(bytes, fieldStart, fieldLength, "INTERVAL_YEAR_MONTH");
                           return false;
                        }

                        return true;
                     }
                  case INTERVAL_DAY_TIME:
                     if (fieldLength == 0) {
                        return false;
                     } else {
                        try {
                           String s = new String(bytes, fieldStart, fieldLength, StandardCharsets.UTF_8);
                           this.currentHiveIntervalDayTimeWritable.set(HiveIntervalDayTime.valueOf(s));
                        } catch (Exception var9) {
                           this.logExceptionMessage(bytes, fieldStart, fieldLength, "INTERVAL_DAY_TIME");
                           return false;
                        }

                        return true;
                     }
                  case DECIMAL:
                     if (!LazyUtils.isNumberMaybe(bytes, fieldStart, fieldLength)) {
                        return false;
                     } else {
                        this.currentHiveDecimalWritable.setFromBytes(bytes, fieldStart, fieldLength, true);
                        boolean decimalIsNull = !this.currentHiveDecimalWritable.isSet();
                        if (!decimalIsNull) {
                           DecimalTypeInfo decimalTypeInfo = (DecimalTypeInfo)this.typeInfos[fieldIndex];
                           int precision = decimalTypeInfo.getPrecision();
                           int scale = decimalTypeInfo.getScale();
                           decimalIsNull = !this.currentHiveDecimalWritable.mutateEnforcePrecisionScale(precision, scale);
                        }

                        if (decimalIsNull) {
                           if (LOG.isDebugEnabled()) {
                              LOG.debug("Data not in the HiveDecimal data type range so converted to null. Given data is :" + new String(bytes, fieldStart, fieldLength, StandardCharsets.UTF_8));
                           }

                           return false;
                        }

                        return true;
                     }
                  default:
                     throw new Error("Unexpected primitive category " + this.primitiveCategories[fieldIndex].name());
               }
            } catch (NumberFormatException var12) {
               this.logExceptionMessage(bytes, fieldStart, fieldLength, this.primitiveCategories[fieldIndex]);
               return false;
            } catch (IllegalArgumentException var13) {
               this.logExceptionMessage(bytes, fieldStart, fieldLength, this.primitiveCategories[fieldIndex]);
               return false;
            }
         }
      }
   }

   public void copyToExternalBuffer(byte[] externalBuffer, int externalBufferStart) {
      this.copyToBuffer(externalBuffer, externalBufferStart, this.currentExternalBufferNeededLen);
   }

   private void copyToBuffer(byte[] buffer, int bufferStart, int bufferLength) {
      int fieldStart = this.currentFieldStart;
      int k = 0;

      for(int i = 0; i < bufferLength; ++i) {
         byte b = this.bytes[fieldStart + i];
         if (b == this.escapeChar && i < bufferLength - 1) {
            ++i;
            if (this.bytes[fieldStart + i] == 114) {
               buffer[bufferStart + k++] = 13;
            } else if (this.bytes[fieldStart + i] == 110) {
               buffer[bufferStart + k++] = 10;
            } else {
               buffer[bufferStart + k++] = this.bytes[fieldStart + i];
            }
         } else {
            buffer[bufferStart + k++] = b;
         }
      }

   }

   public boolean isEndOfInputReached() {
      return this.isEndOfInputReached;
   }

   public void logExceptionMessage(byte[] bytes, int bytesStart, int bytesLength, PrimitiveObjectInspector.PrimitiveCategory dataCategory) {
      String dataType;
      switch (dataCategory) {
         case BYTE:
            dataType = "TINYINT";
            break;
         case SHORT:
            dataType = "SMALLINT";
            break;
         case INT:
         default:
            dataType = dataCategory.toString();
            break;
         case LONG:
            dataType = "BIGINT";
      }

      this.logExceptionMessage(bytes, bytesStart, bytesLength, dataType);
   }

   public void logExceptionMessage(byte[] bytes, int bytesStart, int bytesLength, String dataType) {
      try {
         if (LOG.isDebugEnabled()) {
            String byteData = Text.decode(bytes, bytesStart, bytesLength);
            LOG.debug("Data not in the " + dataType + " data type range so converted to null. Given data is :" + byteData, new Exception("For debugging purposes"));
         }
      } catch (CharacterCodingException e1) {
         LOG.debug("Data not in the " + dataType + " data type range so converted to null.", e1);
      }

   }

   public static int byteArrayCompareRanges(byte[] arg1, int start1, byte[] arg2, int start2, int len) {
      for(int i = 0; i < len; ++i) {
         int b1 = arg1[i + start1] & 255;
         int b2 = arg2[i + start2] & 255;
         if (b1 != b2) {
            return b1 - b2;
         }
      }

      return 0;
   }
}
