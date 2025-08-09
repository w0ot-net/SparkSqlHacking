package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.org.apache.thrift.EncodingUtils;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class Statistics implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Statistics");
   private static final TField MAX_FIELD_DESC = new TField("max", (byte)11, (short)1);
   private static final TField MIN_FIELD_DESC = new TField("min", (byte)11, (short)2);
   private static final TField NULL_COUNT_FIELD_DESC = new TField("null_count", (byte)10, (short)3);
   private static final TField DISTINCT_COUNT_FIELD_DESC = new TField("distinct_count", (byte)10, (short)4);
   private static final TField MAX_VALUE_FIELD_DESC = new TField("max_value", (byte)11, (short)5);
   private static final TField MIN_VALUE_FIELD_DESC = new TField("min_value", (byte)11, (short)6);
   private static final TField IS_MAX_VALUE_EXACT_FIELD_DESC = new TField("is_max_value_exact", (byte)2, (short)7);
   private static final TField IS_MIN_VALUE_EXACT_FIELD_DESC = new TField("is_min_value_exact", (byte)2, (short)8);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new StatisticsStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new StatisticsTupleSchemeFactory();
   @Nullable
   public ByteBuffer max;
   @Nullable
   public ByteBuffer min;
   public long null_count;
   public long distinct_count;
   @Nullable
   public ByteBuffer max_value;
   @Nullable
   public ByteBuffer min_value;
   public boolean is_max_value_exact;
   public boolean is_min_value_exact;
   private static final int __NULL_COUNT_ISSET_ID = 0;
   private static final int __DISTINCT_COUNT_ISSET_ID = 1;
   private static final int __IS_MAX_VALUE_EXACT_ISSET_ID = 2;
   private static final int __IS_MIN_VALUE_EXACT_ISSET_ID = 3;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public Statistics() {
   }

   public Statistics(Statistics other) {
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetMax()) {
         this.max = TBaseHelper.copyBinary(other.max);
      }

      if (other.isSetMin()) {
         this.min = TBaseHelper.copyBinary(other.min);
      }

      this.null_count = other.null_count;
      this.distinct_count = other.distinct_count;
      if (other.isSetMax_value()) {
         this.max_value = TBaseHelper.copyBinary(other.max_value);
      }

      if (other.isSetMin_value()) {
         this.min_value = TBaseHelper.copyBinary(other.min_value);
      }

      this.is_max_value_exact = other.is_max_value_exact;
      this.is_min_value_exact = other.is_min_value_exact;
   }

   public Statistics deepCopy() {
      return new Statistics(this);
   }

   public void clear() {
      this.max = null;
      this.min = null;
      this.setNull_countIsSet(false);
      this.null_count = 0L;
      this.setDistinct_countIsSet(false);
      this.distinct_count = 0L;
      this.max_value = null;
      this.min_value = null;
      this.setIs_max_value_exactIsSet(false);
      this.is_max_value_exact = false;
      this.setIs_min_value_exactIsSet(false);
      this.is_min_value_exact = false;
   }

   public byte[] getMax() {
      this.setMax(TBaseHelper.rightSize(this.max));
      return this.max == null ? null : this.max.array();
   }

   public ByteBuffer bufferForMax() {
      return TBaseHelper.copyBinary(this.max);
   }

   public Statistics setMax(byte[] max) {
      this.max = max == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)max).clone());
      return this;
   }

   public Statistics setMax(@Nullable ByteBuffer max) {
      this.max = TBaseHelper.copyBinary(max);
      return this;
   }

   public void unsetMax() {
      this.max = null;
   }

   public boolean isSetMax() {
      return this.max != null;
   }

   public void setMaxIsSet(boolean value) {
      if (!value) {
         this.max = null;
      }

   }

   public byte[] getMin() {
      this.setMin(TBaseHelper.rightSize(this.min));
      return this.min == null ? null : this.min.array();
   }

   public ByteBuffer bufferForMin() {
      return TBaseHelper.copyBinary(this.min);
   }

   public Statistics setMin(byte[] min) {
      this.min = min == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)min).clone());
      return this;
   }

   public Statistics setMin(@Nullable ByteBuffer min) {
      this.min = TBaseHelper.copyBinary(min);
      return this;
   }

   public void unsetMin() {
      this.min = null;
   }

   public boolean isSetMin() {
      return this.min != null;
   }

   public void setMinIsSet(boolean value) {
      if (!value) {
         this.min = null;
      }

   }

   public long getNull_count() {
      return this.null_count;
   }

   public Statistics setNull_count(long null_count) {
      this.null_count = null_count;
      this.setNull_countIsSet(true);
      return this;
   }

   public void unsetNull_count() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetNull_count() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setNull_countIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public long getDistinct_count() {
      return this.distinct_count;
   }

   public Statistics setDistinct_count(long distinct_count) {
      this.distinct_count = distinct_count;
      this.setDistinct_countIsSet(true);
      return this;
   }

   public void unsetDistinct_count() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetDistinct_count() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setDistinct_countIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public byte[] getMax_value() {
      this.setMax_value(TBaseHelper.rightSize(this.max_value));
      return this.max_value == null ? null : this.max_value.array();
   }

   public ByteBuffer bufferForMax_value() {
      return TBaseHelper.copyBinary(this.max_value);
   }

   public Statistics setMax_value(byte[] max_value) {
      this.max_value = max_value == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)max_value).clone());
      return this;
   }

   public Statistics setMax_value(@Nullable ByteBuffer max_value) {
      this.max_value = TBaseHelper.copyBinary(max_value);
      return this;
   }

   public void unsetMax_value() {
      this.max_value = null;
   }

   public boolean isSetMax_value() {
      return this.max_value != null;
   }

   public void setMax_valueIsSet(boolean value) {
      if (!value) {
         this.max_value = null;
      }

   }

   public byte[] getMin_value() {
      this.setMin_value(TBaseHelper.rightSize(this.min_value));
      return this.min_value == null ? null : this.min_value.array();
   }

   public ByteBuffer bufferForMin_value() {
      return TBaseHelper.copyBinary(this.min_value);
   }

   public Statistics setMin_value(byte[] min_value) {
      this.min_value = min_value == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)min_value).clone());
      return this;
   }

   public Statistics setMin_value(@Nullable ByteBuffer min_value) {
      this.min_value = TBaseHelper.copyBinary(min_value);
      return this;
   }

   public void unsetMin_value() {
      this.min_value = null;
   }

   public boolean isSetMin_value() {
      return this.min_value != null;
   }

   public void setMin_valueIsSet(boolean value) {
      if (!value) {
         this.min_value = null;
      }

   }

   public boolean isIs_max_value_exact() {
      return this.is_max_value_exact;
   }

   public Statistics setIs_max_value_exact(boolean is_max_value_exact) {
      this.is_max_value_exact = is_max_value_exact;
      this.setIs_max_value_exactIsSet(true);
      return this;
   }

   public void unsetIs_max_value_exact() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetIs_max_value_exact() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setIs_max_value_exactIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   public boolean isIs_min_value_exact() {
      return this.is_min_value_exact;
   }

   public Statistics setIs_min_value_exact(boolean is_min_value_exact) {
      this.is_min_value_exact = is_min_value_exact;
      this.setIs_min_value_exactIsSet(true);
      return this;
   }

   public void unsetIs_min_value_exact() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 3);
   }

   public boolean isSetIs_min_value_exact() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 3);
   }

   public void setIs_min_value_exactIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 3, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case MAX:
            if (value == null) {
               this.unsetMax();
            } else if (value instanceof byte[]) {
               this.setMax((byte[])value);
            } else {
               this.setMax((ByteBuffer)value);
            }
            break;
         case MIN:
            if (value == null) {
               this.unsetMin();
            } else if (value instanceof byte[]) {
               this.setMin((byte[])value);
            } else {
               this.setMin((ByteBuffer)value);
            }
            break;
         case NULL_COUNT:
            if (value == null) {
               this.unsetNull_count();
            } else {
               this.setNull_count((Long)value);
            }
            break;
         case DISTINCT_COUNT:
            if (value == null) {
               this.unsetDistinct_count();
            } else {
               this.setDistinct_count((Long)value);
            }
            break;
         case MAX_VALUE:
            if (value == null) {
               this.unsetMax_value();
            } else if (value instanceof byte[]) {
               this.setMax_value((byte[])value);
            } else {
               this.setMax_value((ByteBuffer)value);
            }
            break;
         case MIN_VALUE:
            if (value == null) {
               this.unsetMin_value();
            } else if (value instanceof byte[]) {
               this.setMin_value((byte[])value);
            } else {
               this.setMin_value((ByteBuffer)value);
            }
            break;
         case IS_MAX_VALUE_EXACT:
            if (value == null) {
               this.unsetIs_max_value_exact();
            } else {
               this.setIs_max_value_exact((Boolean)value);
            }
            break;
         case IS_MIN_VALUE_EXACT:
            if (value == null) {
               this.unsetIs_min_value_exact();
            } else {
               this.setIs_min_value_exact((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case MAX:
            return this.getMax();
         case MIN:
            return this.getMin();
         case NULL_COUNT:
            return this.getNull_count();
         case DISTINCT_COUNT:
            return this.getDistinct_count();
         case MAX_VALUE:
            return this.getMax_value();
         case MIN_VALUE:
            return this.getMin_value();
         case IS_MAX_VALUE_EXACT:
            return this.isIs_max_value_exact();
         case IS_MIN_VALUE_EXACT:
            return this.isIs_min_value_exact();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case MAX:
               return this.isSetMax();
            case MIN:
               return this.isSetMin();
            case NULL_COUNT:
               return this.isSetNull_count();
            case DISTINCT_COUNT:
               return this.isSetDistinct_count();
            case MAX_VALUE:
               return this.isSetMax_value();
            case MIN_VALUE:
               return this.isSetMin_value();
            case IS_MAX_VALUE_EXACT:
               return this.isSetIs_max_value_exact();
            case IS_MIN_VALUE_EXACT:
               return this.isSetIs_min_value_exact();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Statistics ? this.equals((Statistics)that) : false;
   }

   public boolean equals(Statistics that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_max = this.isSetMax();
         boolean that_present_max = that.isSetMax();
         if (this_present_max || that_present_max) {
            if (!this_present_max || !that_present_max) {
               return false;
            }

            if (!this.max.equals(that.max)) {
               return false;
            }
         }

         boolean this_present_min = this.isSetMin();
         boolean that_present_min = that.isSetMin();
         if (this_present_min || that_present_min) {
            if (!this_present_min || !that_present_min) {
               return false;
            }

            if (!this.min.equals(that.min)) {
               return false;
            }
         }

         boolean this_present_null_count = this.isSetNull_count();
         boolean that_present_null_count = that.isSetNull_count();
         if (this_present_null_count || that_present_null_count) {
            if (!this_present_null_count || !that_present_null_count) {
               return false;
            }

            if (this.null_count != that.null_count) {
               return false;
            }
         }

         boolean this_present_distinct_count = this.isSetDistinct_count();
         boolean that_present_distinct_count = that.isSetDistinct_count();
         if (this_present_distinct_count || that_present_distinct_count) {
            if (!this_present_distinct_count || !that_present_distinct_count) {
               return false;
            }

            if (this.distinct_count != that.distinct_count) {
               return false;
            }
         }

         boolean this_present_max_value = this.isSetMax_value();
         boolean that_present_max_value = that.isSetMax_value();
         if (this_present_max_value || that_present_max_value) {
            if (!this_present_max_value || !that_present_max_value) {
               return false;
            }

            if (!this.max_value.equals(that.max_value)) {
               return false;
            }
         }

         boolean this_present_min_value = this.isSetMin_value();
         boolean that_present_min_value = that.isSetMin_value();
         if (this_present_min_value || that_present_min_value) {
            if (!this_present_min_value || !that_present_min_value) {
               return false;
            }

            if (!this.min_value.equals(that.min_value)) {
               return false;
            }
         }

         boolean this_present_is_max_value_exact = this.isSetIs_max_value_exact();
         boolean that_present_is_max_value_exact = that.isSetIs_max_value_exact();
         if (this_present_is_max_value_exact || that_present_is_max_value_exact) {
            if (!this_present_is_max_value_exact || !that_present_is_max_value_exact) {
               return false;
            }

            if (this.is_max_value_exact != that.is_max_value_exact) {
               return false;
            }
         }

         boolean this_present_is_min_value_exact = this.isSetIs_min_value_exact();
         boolean that_present_is_min_value_exact = that.isSetIs_min_value_exact();
         if (this_present_is_min_value_exact || that_present_is_min_value_exact) {
            if (!this_present_is_min_value_exact || !that_present_is_min_value_exact) {
               return false;
            }

            if (this.is_min_value_exact != that.is_min_value_exact) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetMax() ? 131071 : 524287);
      if (this.isSetMax()) {
         hashCode = hashCode * 8191 + this.max.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMin() ? 131071 : 524287);
      if (this.isSetMin()) {
         hashCode = hashCode * 8191 + this.min.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetNull_count() ? 131071 : 524287);
      if (this.isSetNull_count()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.null_count);
      }

      hashCode = hashCode * 8191 + (this.isSetDistinct_count() ? 131071 : 524287);
      if (this.isSetDistinct_count()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.distinct_count);
      }

      hashCode = hashCode * 8191 + (this.isSetMax_value() ? 131071 : 524287);
      if (this.isSetMax_value()) {
         hashCode = hashCode * 8191 + this.max_value.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMin_value() ? 131071 : 524287);
      if (this.isSetMin_value()) {
         hashCode = hashCode * 8191 + this.min_value.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetIs_max_value_exact() ? 131071 : 524287);
      if (this.isSetIs_max_value_exact()) {
         hashCode = hashCode * 8191 + (this.is_max_value_exact ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetIs_min_value_exact() ? 131071 : 524287);
      if (this.isSetIs_min_value_exact()) {
         hashCode = hashCode * 8191 + (this.is_min_value_exact ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(Statistics other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMax(), other.isSetMax());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMax()) {
               lastComparison = TBaseHelper.compareTo((Comparable)this.max, (Comparable)other.max);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMin(), other.isSetMin());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMin()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.min, (Comparable)other.min);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetNull_count(), other.isSetNull_count());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetNull_count()) {
                     lastComparison = TBaseHelper.compareTo(this.null_count, other.null_count);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetDistinct_count(), other.isSetDistinct_count());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetDistinct_count()) {
                        lastComparison = TBaseHelper.compareTo(this.distinct_count, other.distinct_count);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetMax_value(), other.isSetMax_value());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetMax_value()) {
                           lastComparison = TBaseHelper.compareTo((Comparable)this.max_value, (Comparable)other.max_value);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetMin_value(), other.isSetMin_value());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetMin_value()) {
                              lastComparison = TBaseHelper.compareTo((Comparable)this.min_value, (Comparable)other.min_value);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetIs_max_value_exact(), other.isSetIs_max_value_exact());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetIs_max_value_exact()) {
                                 lastComparison = TBaseHelper.compareTo(this.is_max_value_exact, other.is_max_value_exact);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetIs_min_value_exact(), other.isSetIs_min_value_exact());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetIs_min_value_exact()) {
                                    lastComparison = TBaseHelper.compareTo(this.is_min_value_exact, other.is_min_value_exact);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 return 0;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Statistics._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Statistics(");
      boolean first = true;
      if (this.isSetMax()) {
         sb.append("max:");
         if (this.max == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.max, sb);
         }

         first = false;
      }

      if (this.isSetMin()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("min:");
         if (this.min == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.min, sb);
         }

         first = false;
      }

      if (this.isSetNull_count()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("null_count:");
         sb.append(this.null_count);
         first = false;
      }

      if (this.isSetDistinct_count()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("distinct_count:");
         sb.append(this.distinct_count);
         first = false;
      }

      if (this.isSetMax_value()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("max_value:");
         if (this.max_value == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.max_value, sb);
         }

         first = false;
      }

      if (this.isSetMin_value()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("min_value:");
         if (this.min_value == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.min_value, sb);
         }

         first = false;
      }

      if (this.isSetIs_max_value_exact()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("is_max_value_exact:");
         sb.append(this.is_max_value_exact);
         first = false;
      }

      if (this.isSetIs_min_value_exact()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("is_min_value_exact:");
         sb.append(this.is_min_value_exact);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{Statistics._Fields.MAX, Statistics._Fields.MIN, Statistics._Fields.NULL_COUNT, Statistics._Fields.DISTINCT_COUNT, Statistics._Fields.MAX_VALUE, Statistics._Fields.MIN_VALUE, Statistics._Fields.IS_MAX_VALUE_EXACT, Statistics._Fields.IS_MIN_VALUE_EXACT};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Statistics._Fields.MAX, new FieldMetaData("max", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(Statistics._Fields.MIN, new FieldMetaData("min", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(Statistics._Fields.NULL_COUNT, new FieldMetaData("null_count", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(Statistics._Fields.DISTINCT_COUNT, new FieldMetaData("distinct_count", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(Statistics._Fields.MAX_VALUE, new FieldMetaData("max_value", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(Statistics._Fields.MIN_VALUE, new FieldMetaData("min_value", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(Statistics._Fields.IS_MAX_VALUE_EXACT, new FieldMetaData("is_max_value_exact", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(Statistics._Fields.IS_MIN_VALUE_EXACT, new FieldMetaData("is_min_value_exact", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Statistics.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MAX((short)1, "max"),
      MIN((short)2, "min"),
      NULL_COUNT((short)3, "null_count"),
      DISTINCT_COUNT((short)4, "distinct_count"),
      MAX_VALUE((short)5, "max_value"),
      MIN_VALUE((short)6, "min_value"),
      IS_MAX_VALUE_EXACT((short)7, "is_max_value_exact"),
      IS_MIN_VALUE_EXACT((short)8, "is_min_value_exact");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MAX;
            case 2:
               return MIN;
            case 3:
               return NULL_COUNT;
            case 4:
               return DISTINCT_COUNT;
            case 5:
               return MAX_VALUE;
            case 6:
               return MIN_VALUE;
            case 7:
               return IS_MAX_VALUE_EXACT;
            case 8:
               return IS_MIN_VALUE_EXACT;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class StatisticsStandardSchemeFactory implements SchemeFactory {
      private StatisticsStandardSchemeFactory() {
      }

      public StatisticsStandardScheme getScheme() {
         return new StatisticsStandardScheme();
      }
   }

   private static class StatisticsStandardScheme extends StandardScheme {
      private StatisticsStandardScheme() {
      }

      public void read(TProtocol iprot, Statistics struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 11) {
                     struct.max = iprot.readBinary();
                     struct.setMaxIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.min = iprot.readBinary();
                     struct.setMinIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.null_count = iprot.readI64();
                     struct.setNull_countIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 10) {
                     struct.distinct_count = iprot.readI64();
                     struct.setDistinct_countIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.max_value = iprot.readBinary();
                     struct.setMax_valueIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.min_value = iprot.readBinary();
                     struct.setMin_valueIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 2) {
                     struct.is_max_value_exact = iprot.readBool();
                     struct.setIs_max_value_exactIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 2) {
                     struct.is_min_value_exact = iprot.readBool();
                     struct.setIs_min_value_exactIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, Statistics struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Statistics.STRUCT_DESC);
         if (struct.max != null && struct.isSetMax()) {
            oprot.writeFieldBegin(Statistics.MAX_FIELD_DESC);
            oprot.writeBinary(struct.max);
            oprot.writeFieldEnd();
         }

         if (struct.min != null && struct.isSetMin()) {
            oprot.writeFieldBegin(Statistics.MIN_FIELD_DESC);
            oprot.writeBinary(struct.min);
            oprot.writeFieldEnd();
         }

         if (struct.isSetNull_count()) {
            oprot.writeFieldBegin(Statistics.NULL_COUNT_FIELD_DESC);
            oprot.writeI64(struct.null_count);
            oprot.writeFieldEnd();
         }

         if (struct.isSetDistinct_count()) {
            oprot.writeFieldBegin(Statistics.DISTINCT_COUNT_FIELD_DESC);
            oprot.writeI64(struct.distinct_count);
            oprot.writeFieldEnd();
         }

         if (struct.max_value != null && struct.isSetMax_value()) {
            oprot.writeFieldBegin(Statistics.MAX_VALUE_FIELD_DESC);
            oprot.writeBinary(struct.max_value);
            oprot.writeFieldEnd();
         }

         if (struct.min_value != null && struct.isSetMin_value()) {
            oprot.writeFieldBegin(Statistics.MIN_VALUE_FIELD_DESC);
            oprot.writeBinary(struct.min_value);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIs_max_value_exact()) {
            oprot.writeFieldBegin(Statistics.IS_MAX_VALUE_EXACT_FIELD_DESC);
            oprot.writeBool(struct.is_max_value_exact);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIs_min_value_exact()) {
            oprot.writeFieldBegin(Statistics.IS_MIN_VALUE_EXACT_FIELD_DESC);
            oprot.writeBool(struct.is_min_value_exact);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class StatisticsTupleSchemeFactory implements SchemeFactory {
      private StatisticsTupleSchemeFactory() {
      }

      public StatisticsTupleScheme getScheme() {
         return new StatisticsTupleScheme();
      }
   }

   private static class StatisticsTupleScheme extends TupleScheme {
      private StatisticsTupleScheme() {
      }

      public void write(TProtocol prot, Statistics struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetMax()) {
            optionals.set(0);
         }

         if (struct.isSetMin()) {
            optionals.set(1);
         }

         if (struct.isSetNull_count()) {
            optionals.set(2);
         }

         if (struct.isSetDistinct_count()) {
            optionals.set(3);
         }

         if (struct.isSetMax_value()) {
            optionals.set(4);
         }

         if (struct.isSetMin_value()) {
            optionals.set(5);
         }

         if (struct.isSetIs_max_value_exact()) {
            optionals.set(6);
         }

         if (struct.isSetIs_min_value_exact()) {
            optionals.set(7);
         }

         oprot.writeBitSet(optionals, 8);
         if (struct.isSetMax()) {
            oprot.writeBinary(struct.max);
         }

         if (struct.isSetMin()) {
            oprot.writeBinary(struct.min);
         }

         if (struct.isSetNull_count()) {
            oprot.writeI64(struct.null_count);
         }

         if (struct.isSetDistinct_count()) {
            oprot.writeI64(struct.distinct_count);
         }

         if (struct.isSetMax_value()) {
            oprot.writeBinary(struct.max_value);
         }

         if (struct.isSetMin_value()) {
            oprot.writeBinary(struct.min_value);
         }

         if (struct.isSetIs_max_value_exact()) {
            oprot.writeBool(struct.is_max_value_exact);
         }

         if (struct.isSetIs_min_value_exact()) {
            oprot.writeBool(struct.is_min_value_exact);
         }

      }

      public void read(TProtocol prot, Statistics struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(8);
         if (incoming.get(0)) {
            struct.max = iprot.readBinary();
            struct.setMaxIsSet(true);
         }

         if (incoming.get(1)) {
            struct.min = iprot.readBinary();
            struct.setMinIsSet(true);
         }

         if (incoming.get(2)) {
            struct.null_count = iprot.readI64();
            struct.setNull_countIsSet(true);
         }

         if (incoming.get(3)) {
            struct.distinct_count = iprot.readI64();
            struct.setDistinct_countIsSet(true);
         }

         if (incoming.get(4)) {
            struct.max_value = iprot.readBinary();
            struct.setMax_valueIsSet(true);
         }

         if (incoming.get(5)) {
            struct.min_value = iprot.readBinary();
            struct.setMin_valueIsSet(true);
         }

         if (incoming.get(6)) {
            struct.is_max_value_exact = iprot.readBool();
            struct.setIs_max_value_exactIsSet(true);
         }

         if (incoming.get(7)) {
            struct.is_min_value_exact = iprot.readBool();
            struct.setIs_min_value_exactIsSet(true);
         }

      }
   }
}
