package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import shaded.parquet.org.apache.thrift.meta_data.EnumMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class SchemaElement implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SchemaElement");
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)1);
   private static final TField TYPE_LENGTH_FIELD_DESC = new TField("type_length", (byte)8, (short)2);
   private static final TField REPETITION_TYPE_FIELD_DESC = new TField("repetition_type", (byte)8, (short)3);
   private static final TField NAME_FIELD_DESC = new TField("name", (byte)11, (short)4);
   private static final TField NUM_CHILDREN_FIELD_DESC = new TField("num_children", (byte)8, (short)5);
   private static final TField CONVERTED_TYPE_FIELD_DESC = new TField("converted_type", (byte)8, (short)6);
   private static final TField SCALE_FIELD_DESC = new TField("scale", (byte)8, (short)7);
   private static final TField PRECISION_FIELD_DESC = new TField("precision", (byte)8, (short)8);
   private static final TField FIELD_ID_FIELD_DESC = new TField("field_id", (byte)8, (short)9);
   private static final TField LOGICAL_TYPE_FIELD_DESC = new TField("logicalType", (byte)12, (short)10);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SchemaElementStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SchemaElementTupleSchemeFactory();
   @Nullable
   public Type type;
   public int type_length;
   @Nullable
   public FieldRepetitionType repetition_type;
   @Nullable
   public String name;
   public int num_children;
   @Nullable
   public ConvertedType converted_type;
   public int scale;
   public int precision;
   public int field_id;
   @Nullable
   public LogicalType logicalType;
   private static final int __TYPE_LENGTH_ISSET_ID = 0;
   private static final int __NUM_CHILDREN_ISSET_ID = 1;
   private static final int __SCALE_ISSET_ID = 2;
   private static final int __PRECISION_ISSET_ID = 3;
   private static final int __FIELD_ID_ISSET_ID = 4;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public SchemaElement() {
      this.__isset_bitfield = 0;
   }

   public SchemaElement(String name) {
      this();
      this.name = name;
   }

   public SchemaElement(SchemaElement other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetType()) {
         this.type = other.type;
      }

      this.type_length = other.type_length;
      if (other.isSetRepetition_type()) {
         this.repetition_type = other.repetition_type;
      }

      if (other.isSetName()) {
         this.name = other.name;
      }

      this.num_children = other.num_children;
      if (other.isSetConverted_type()) {
         this.converted_type = other.converted_type;
      }

      this.scale = other.scale;
      this.precision = other.precision;
      this.field_id = other.field_id;
      if (other.isSetLogicalType()) {
         this.logicalType = new LogicalType(other.logicalType);
      }

   }

   public SchemaElement deepCopy() {
      return new SchemaElement(this);
   }

   public void clear() {
      this.type = null;
      this.setType_lengthIsSet(false);
      this.type_length = 0;
      this.repetition_type = null;
      this.name = null;
      this.setNum_childrenIsSet(false);
      this.num_children = 0;
      this.converted_type = null;
      this.setScaleIsSet(false);
      this.scale = 0;
      this.setPrecisionIsSet(false);
      this.precision = 0;
      this.setField_idIsSet(false);
      this.field_id = 0;
      this.logicalType = null;
   }

   @Nullable
   public Type getType() {
      return this.type;
   }

   public SchemaElement setType(@Nullable Type type) {
      this.type = type;
      return this;
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   public int getType_length() {
      return this.type_length;
   }

   public SchemaElement setType_length(int type_length) {
      this.type_length = type_length;
      this.setType_lengthIsSet(true);
      return this;
   }

   public void unsetType_length() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetType_length() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setType_lengthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   @Nullable
   public FieldRepetitionType getRepetition_type() {
      return this.repetition_type;
   }

   public SchemaElement setRepetition_type(@Nullable FieldRepetitionType repetition_type) {
      this.repetition_type = repetition_type;
      return this;
   }

   public void unsetRepetition_type() {
      this.repetition_type = null;
   }

   public boolean isSetRepetition_type() {
      return this.repetition_type != null;
   }

   public void setRepetition_typeIsSet(boolean value) {
      if (!value) {
         this.repetition_type = null;
      }

   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public SchemaElement setName(@Nullable String name) {
      this.name = name;
      return this;
   }

   public void unsetName() {
      this.name = null;
   }

   public boolean isSetName() {
      return this.name != null;
   }

   public void setNameIsSet(boolean value) {
      if (!value) {
         this.name = null;
      }

   }

   public int getNum_children() {
      return this.num_children;
   }

   public SchemaElement setNum_children(int num_children) {
      this.num_children = num_children;
      this.setNum_childrenIsSet(true);
      return this;
   }

   public void unsetNum_children() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetNum_children() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setNum_childrenIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   @Nullable
   public ConvertedType getConverted_type() {
      return this.converted_type;
   }

   public SchemaElement setConverted_type(@Nullable ConvertedType converted_type) {
      this.converted_type = converted_type;
      return this;
   }

   public void unsetConverted_type() {
      this.converted_type = null;
   }

   public boolean isSetConverted_type() {
      return this.converted_type != null;
   }

   public void setConverted_typeIsSet(boolean value) {
      if (!value) {
         this.converted_type = null;
      }

   }

   public int getScale() {
      return this.scale;
   }

   public SchemaElement setScale(int scale) {
      this.scale = scale;
      this.setScaleIsSet(true);
      return this;
   }

   public void unsetScale() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetScale() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setScaleIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   public int getPrecision() {
      return this.precision;
   }

   public SchemaElement setPrecision(int precision) {
      this.precision = precision;
      this.setPrecisionIsSet(true);
      return this;
   }

   public void unsetPrecision() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 3);
   }

   public boolean isSetPrecision() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 3);
   }

   public void setPrecisionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 3, value);
   }

   public int getField_id() {
      return this.field_id;
   }

   public SchemaElement setField_id(int field_id) {
      this.field_id = field_id;
      this.setField_idIsSet(true);
      return this;
   }

   public void unsetField_id() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 4);
   }

   public boolean isSetField_id() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 4);
   }

   public void setField_idIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 4, value);
   }

   @Nullable
   public LogicalType getLogicalType() {
      return this.logicalType;
   }

   public SchemaElement setLogicalType(@Nullable LogicalType logicalType) {
      this.logicalType = logicalType;
      return this;
   }

   public void unsetLogicalType() {
      this.logicalType = null;
   }

   public boolean isSetLogicalType() {
      return this.logicalType != null;
   }

   public void setLogicalTypeIsSet(boolean value) {
      if (!value) {
         this.logicalType = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((Type)value);
            }
            break;
         case TYPE_LENGTH:
            if (value == null) {
               this.unsetType_length();
            } else {
               this.setType_length((Integer)value);
            }
            break;
         case REPETITION_TYPE:
            if (value == null) {
               this.unsetRepetition_type();
            } else {
               this.setRepetition_type((FieldRepetitionType)value);
            }
            break;
         case NAME:
            if (value == null) {
               this.unsetName();
            } else {
               this.setName((String)value);
            }
            break;
         case NUM_CHILDREN:
            if (value == null) {
               this.unsetNum_children();
            } else {
               this.setNum_children((Integer)value);
            }
            break;
         case CONVERTED_TYPE:
            if (value == null) {
               this.unsetConverted_type();
            } else {
               this.setConverted_type((ConvertedType)value);
            }
            break;
         case SCALE:
            if (value == null) {
               this.unsetScale();
            } else {
               this.setScale((Integer)value);
            }
            break;
         case PRECISION:
            if (value == null) {
               this.unsetPrecision();
            } else {
               this.setPrecision((Integer)value);
            }
            break;
         case FIELD_ID:
            if (value == null) {
               this.unsetField_id();
            } else {
               this.setField_id((Integer)value);
            }
            break;
         case LOGICAL_TYPE:
            if (value == null) {
               this.unsetLogicalType();
            } else {
               this.setLogicalType((LogicalType)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TYPE:
            return this.getType();
         case TYPE_LENGTH:
            return this.getType_length();
         case REPETITION_TYPE:
            return this.getRepetition_type();
         case NAME:
            return this.getName();
         case NUM_CHILDREN:
            return this.getNum_children();
         case CONVERTED_TYPE:
            return this.getConverted_type();
         case SCALE:
            return this.getScale();
         case PRECISION:
            return this.getPrecision();
         case FIELD_ID:
            return this.getField_id();
         case LOGICAL_TYPE:
            return this.getLogicalType();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TYPE:
               return this.isSetType();
            case TYPE_LENGTH:
               return this.isSetType_length();
            case REPETITION_TYPE:
               return this.isSetRepetition_type();
            case NAME:
               return this.isSetName();
            case NUM_CHILDREN:
               return this.isSetNum_children();
            case CONVERTED_TYPE:
               return this.isSetConverted_type();
            case SCALE:
               return this.isSetScale();
            case PRECISION:
               return this.isSetPrecision();
            case FIELD_ID:
               return this.isSetField_id();
            case LOGICAL_TYPE:
               return this.isSetLogicalType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SchemaElement ? this.equals((SchemaElement)that) : false;
   }

   public boolean equals(SchemaElement that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         boolean this_present_type_length = this.isSetType_length();
         boolean that_present_type_length = that.isSetType_length();
         if (this_present_type_length || that_present_type_length) {
            if (!this_present_type_length || !that_present_type_length) {
               return false;
            }

            if (this.type_length != that.type_length) {
               return false;
            }
         }

         boolean this_present_repetition_type = this.isSetRepetition_type();
         boolean that_present_repetition_type = that.isSetRepetition_type();
         if (this_present_repetition_type || that_present_repetition_type) {
            if (!this_present_repetition_type || !that_present_repetition_type) {
               return false;
            }

            if (!this.repetition_type.equals(that.repetition_type)) {
               return false;
            }
         }

         boolean this_present_name = this.isSetName();
         boolean that_present_name = that.isSetName();
         if (this_present_name || that_present_name) {
            if (!this_present_name || !that_present_name) {
               return false;
            }

            if (!this.name.equals(that.name)) {
               return false;
            }
         }

         boolean this_present_num_children = this.isSetNum_children();
         boolean that_present_num_children = that.isSetNum_children();
         if (this_present_num_children || that_present_num_children) {
            if (!this_present_num_children || !that_present_num_children) {
               return false;
            }

            if (this.num_children != that.num_children) {
               return false;
            }
         }

         boolean this_present_converted_type = this.isSetConverted_type();
         boolean that_present_converted_type = that.isSetConverted_type();
         if (this_present_converted_type || that_present_converted_type) {
            if (!this_present_converted_type || !that_present_converted_type) {
               return false;
            }

            if (!this.converted_type.equals(that.converted_type)) {
               return false;
            }
         }

         boolean this_present_scale = this.isSetScale();
         boolean that_present_scale = that.isSetScale();
         if (this_present_scale || that_present_scale) {
            if (!this_present_scale || !that_present_scale) {
               return false;
            }

            if (this.scale != that.scale) {
               return false;
            }
         }

         boolean this_present_precision = this.isSetPrecision();
         boolean that_present_precision = that.isSetPrecision();
         if (this_present_precision || that_present_precision) {
            if (!this_present_precision || !that_present_precision) {
               return false;
            }

            if (this.precision != that.precision) {
               return false;
            }
         }

         boolean this_present_field_id = this.isSetField_id();
         boolean that_present_field_id = that.isSetField_id();
         if (this_present_field_id || that_present_field_id) {
            if (!this_present_field_id || !that_present_field_id) {
               return false;
            }

            if (this.field_id != that.field_id) {
               return false;
            }
         }

         boolean this_present_logicalType = this.isSetLogicalType();
         boolean that_present_logicalType = that.isSetLogicalType();
         if (this_present_logicalType || that_present_logicalType) {
            if (!this_present_logicalType || !that_present_logicalType) {
               return false;
            }

            if (!this.logicalType.equals(that.logicalType)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetType_length() ? 131071 : 524287);
      if (this.isSetType_length()) {
         hashCode = hashCode * 8191 + this.type_length;
      }

      hashCode = hashCode * 8191 + (this.isSetRepetition_type() ? 131071 : 524287);
      if (this.isSetRepetition_type()) {
         hashCode = hashCode * 8191 + this.repetition_type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetName() ? 131071 : 524287);
      if (this.isSetName()) {
         hashCode = hashCode * 8191 + this.name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetNum_children() ? 131071 : 524287);
      if (this.isSetNum_children()) {
         hashCode = hashCode * 8191 + this.num_children;
      }

      hashCode = hashCode * 8191 + (this.isSetConverted_type() ? 131071 : 524287);
      if (this.isSetConverted_type()) {
         hashCode = hashCode * 8191 + this.converted_type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetScale() ? 131071 : 524287);
      if (this.isSetScale()) {
         hashCode = hashCode * 8191 + this.scale;
      }

      hashCode = hashCode * 8191 + (this.isSetPrecision() ? 131071 : 524287);
      if (this.isSetPrecision()) {
         hashCode = hashCode * 8191 + this.precision;
      }

      hashCode = hashCode * 8191 + (this.isSetField_id() ? 131071 : 524287);
      if (this.isSetField_id()) {
         hashCode = hashCode * 8191 + this.field_id;
      }

      hashCode = hashCode * 8191 + (this.isSetLogicalType() ? 131071 : 524287);
      if (this.isSetLogicalType()) {
         hashCode = hashCode * 8191 + this.logicalType.hashCode();
      }

      return hashCode;
   }

   public int compareTo(SchemaElement other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetType()) {
               lastComparison = TBaseHelper.compareTo((Comparable)this.type, (Comparable)other.type);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetType_length(), other.isSetType_length());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetType_length()) {
                  lastComparison = TBaseHelper.compareTo(this.type_length, other.type_length);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetRepetition_type(), other.isSetRepetition_type());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetRepetition_type()) {
                     lastComparison = TBaseHelper.compareTo((Comparable)this.repetition_type, (Comparable)other.repetition_type);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetName(), other.isSetName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetName()) {
                        lastComparison = TBaseHelper.compareTo(this.name, other.name);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetNum_children(), other.isSetNum_children());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetNum_children()) {
                           lastComparison = TBaseHelper.compareTo(this.num_children, other.num_children);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetConverted_type(), other.isSetConverted_type());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetConverted_type()) {
                              lastComparison = TBaseHelper.compareTo((Comparable)this.converted_type, (Comparable)other.converted_type);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetScale(), other.isSetScale());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetScale()) {
                                 lastComparison = TBaseHelper.compareTo(this.scale, other.scale);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetPrecision(), other.isSetPrecision());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetPrecision()) {
                                    lastComparison = TBaseHelper.compareTo(this.precision, other.precision);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetField_id(), other.isSetField_id());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetField_id()) {
                                       lastComparison = TBaseHelper.compareTo(this.field_id, other.field_id);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetLogicalType(), other.isSetLogicalType());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetLogicalType()) {
                                          lastComparison = TBaseHelper.compareTo((Comparable)this.logicalType, (Comparable)other.logicalType);
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
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return SchemaElement._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SchemaElement(");
      boolean first = true;
      if (this.isSetType()) {
         sb.append("type:");
         if (this.type == null) {
            sb.append("null");
         } else {
            sb.append(this.type);
         }

         first = false;
      }

      if (this.isSetType_length()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("type_length:");
         sb.append(this.type_length);
         first = false;
      }

      if (this.isSetRepetition_type()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("repetition_type:");
         if (this.repetition_type == null) {
            sb.append("null");
         } else {
            sb.append(this.repetition_type);
         }

         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("name:");
      if (this.name == null) {
         sb.append("null");
      } else {
         sb.append(this.name);
      }

      first = false;
      if (this.isSetNum_children()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("num_children:");
         sb.append(this.num_children);
         first = false;
      }

      if (this.isSetConverted_type()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("converted_type:");
         if (this.converted_type == null) {
            sb.append("null");
         } else {
            sb.append(this.converted_type);
         }

         first = false;
      }

      if (this.isSetScale()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("scale:");
         sb.append(this.scale);
         first = false;
      }

      if (this.isSetPrecision()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("precision:");
         sb.append(this.precision);
         first = false;
      }

      if (this.isSetField_id()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("field_id:");
         sb.append(this.field_id);
         first = false;
      }

      if (this.isSetLogicalType()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("logicalType:");
         if (this.logicalType == null) {
            sb.append("null");
         } else {
            sb.append(this.logicalType);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.name == null) {
         throw new TProtocolException("Required field 'name' was not present! Struct: " + this.toString());
      }
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
      optionals = new _Fields[]{SchemaElement._Fields.TYPE, SchemaElement._Fields.TYPE_LENGTH, SchemaElement._Fields.REPETITION_TYPE, SchemaElement._Fields.NUM_CHILDREN, SchemaElement._Fields.CONVERTED_TYPE, SchemaElement._Fields.SCALE, SchemaElement._Fields.PRECISION, SchemaElement._Fields.FIELD_ID, SchemaElement._Fields.LOGICAL_TYPE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(SchemaElement._Fields.TYPE, new FieldMetaData("type", (byte)2, new EnumMetaData((byte)-1, Type.class)));
      tmpMap.put(SchemaElement._Fields.TYPE_LENGTH, new FieldMetaData("type_length", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(SchemaElement._Fields.REPETITION_TYPE, new FieldMetaData("repetition_type", (byte)2, new EnumMetaData((byte)-1, FieldRepetitionType.class)));
      tmpMap.put(SchemaElement._Fields.NAME, new FieldMetaData("name", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(SchemaElement._Fields.NUM_CHILDREN, new FieldMetaData("num_children", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(SchemaElement._Fields.CONVERTED_TYPE, new FieldMetaData("converted_type", (byte)2, new EnumMetaData((byte)-1, ConvertedType.class)));
      tmpMap.put(SchemaElement._Fields.SCALE, new FieldMetaData("scale", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(SchemaElement._Fields.PRECISION, new FieldMetaData("precision", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(SchemaElement._Fields.FIELD_ID, new FieldMetaData("field_id", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(SchemaElement._Fields.LOGICAL_TYPE, new FieldMetaData("logicalType", (byte)2, new StructMetaData((byte)12, LogicalType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SchemaElement.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPE((short)1, "type"),
      TYPE_LENGTH((short)2, "type_length"),
      REPETITION_TYPE((short)3, "repetition_type"),
      NAME((short)4, "name"),
      NUM_CHILDREN((short)5, "num_children"),
      CONVERTED_TYPE((short)6, "converted_type"),
      SCALE((short)7, "scale"),
      PRECISION((short)8, "precision"),
      FIELD_ID((short)9, "field_id"),
      LOGICAL_TYPE((short)10, "logicalType");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPE;
            case 2:
               return TYPE_LENGTH;
            case 3:
               return REPETITION_TYPE;
            case 4:
               return NAME;
            case 5:
               return NUM_CHILDREN;
            case 6:
               return CONVERTED_TYPE;
            case 7:
               return SCALE;
            case 8:
               return PRECISION;
            case 9:
               return FIELD_ID;
            case 10:
               return LOGICAL_TYPE;
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

   private static class SchemaElementStandardSchemeFactory implements SchemeFactory {
      private SchemaElementStandardSchemeFactory() {
      }

      public SchemaElementStandardScheme getScheme() {
         return new SchemaElementStandardScheme();
      }
   }

   private static class SchemaElementStandardScheme extends StandardScheme {
      private SchemaElementStandardScheme() {
      }

      public void read(TProtocol iprot, SchemaElement struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.type = Type.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.type_length = iprot.readI32();
                     struct.setType_lengthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.repetition_type = FieldRepetitionType.findByValue(iprot.readI32());
                     struct.setRepetition_typeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.name = iprot.readString();
                     struct.setNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.num_children = iprot.readI32();
                     struct.setNum_childrenIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.converted_type = ConvertedType.findByValue(iprot.readI32());
                     struct.setConverted_typeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 8) {
                     struct.scale = iprot.readI32();
                     struct.setScaleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 8) {
                     struct.precision = iprot.readI32();
                     struct.setPrecisionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 8) {
                     struct.field_id = iprot.readI32();
                     struct.setField_idIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 12) {
                     struct.logicalType = new LogicalType();
                     struct.logicalType.read(iprot);
                     struct.setLogicalTypeIsSet(true);
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

      public void write(TProtocol oprot, SchemaElement struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SchemaElement.STRUCT_DESC);
         if (struct.type != null && struct.isSetType()) {
            oprot.writeFieldBegin(SchemaElement.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.isSetType_length()) {
            oprot.writeFieldBegin(SchemaElement.TYPE_LENGTH_FIELD_DESC);
            oprot.writeI32(struct.type_length);
            oprot.writeFieldEnd();
         }

         if (struct.repetition_type != null && struct.isSetRepetition_type()) {
            oprot.writeFieldBegin(SchemaElement.REPETITION_TYPE_FIELD_DESC);
            oprot.writeI32(struct.repetition_type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.name != null) {
            oprot.writeFieldBegin(SchemaElement.NAME_FIELD_DESC);
            oprot.writeString(struct.name);
            oprot.writeFieldEnd();
         }

         if (struct.isSetNum_children()) {
            oprot.writeFieldBegin(SchemaElement.NUM_CHILDREN_FIELD_DESC);
            oprot.writeI32(struct.num_children);
            oprot.writeFieldEnd();
         }

         if (struct.converted_type != null && struct.isSetConverted_type()) {
            oprot.writeFieldBegin(SchemaElement.CONVERTED_TYPE_FIELD_DESC);
            oprot.writeI32(struct.converted_type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.isSetScale()) {
            oprot.writeFieldBegin(SchemaElement.SCALE_FIELD_DESC);
            oprot.writeI32(struct.scale);
            oprot.writeFieldEnd();
         }

         if (struct.isSetPrecision()) {
            oprot.writeFieldBegin(SchemaElement.PRECISION_FIELD_DESC);
            oprot.writeI32(struct.precision);
            oprot.writeFieldEnd();
         }

         if (struct.isSetField_id()) {
            oprot.writeFieldBegin(SchemaElement.FIELD_ID_FIELD_DESC);
            oprot.writeI32(struct.field_id);
            oprot.writeFieldEnd();
         }

         if (struct.logicalType != null && struct.isSetLogicalType()) {
            oprot.writeFieldBegin(SchemaElement.LOGICAL_TYPE_FIELD_DESC);
            struct.logicalType.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SchemaElementTupleSchemeFactory implements SchemeFactory {
      private SchemaElementTupleSchemeFactory() {
      }

      public SchemaElementTupleScheme getScheme() {
         return new SchemaElementTupleScheme();
      }
   }

   private static class SchemaElementTupleScheme extends TupleScheme {
      private SchemaElementTupleScheme() {
      }

      public void write(TProtocol prot, SchemaElement struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.name);
         BitSet optionals = new BitSet();
         if (struct.isSetType()) {
            optionals.set(0);
         }

         if (struct.isSetType_length()) {
            optionals.set(1);
         }

         if (struct.isSetRepetition_type()) {
            optionals.set(2);
         }

         if (struct.isSetNum_children()) {
            optionals.set(3);
         }

         if (struct.isSetConverted_type()) {
            optionals.set(4);
         }

         if (struct.isSetScale()) {
            optionals.set(5);
         }

         if (struct.isSetPrecision()) {
            optionals.set(6);
         }

         if (struct.isSetField_id()) {
            optionals.set(7);
         }

         if (struct.isSetLogicalType()) {
            optionals.set(8);
         }

         oprot.writeBitSet(optionals, 9);
         if (struct.isSetType()) {
            oprot.writeI32(struct.type.getValue());
         }

         if (struct.isSetType_length()) {
            oprot.writeI32(struct.type_length);
         }

         if (struct.isSetRepetition_type()) {
            oprot.writeI32(struct.repetition_type.getValue());
         }

         if (struct.isSetNum_children()) {
            oprot.writeI32(struct.num_children);
         }

         if (struct.isSetConverted_type()) {
            oprot.writeI32(struct.converted_type.getValue());
         }

         if (struct.isSetScale()) {
            oprot.writeI32(struct.scale);
         }

         if (struct.isSetPrecision()) {
            oprot.writeI32(struct.precision);
         }

         if (struct.isSetField_id()) {
            oprot.writeI32(struct.field_id);
         }

         if (struct.isSetLogicalType()) {
            struct.logicalType.write(oprot);
         }

      }

      public void read(TProtocol prot, SchemaElement struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.name = iprot.readString();
         struct.setNameIsSet(true);
         BitSet incoming = iprot.readBitSet(9);
         if (incoming.get(0)) {
            struct.type = Type.findByValue(iprot.readI32());
            struct.setTypeIsSet(true);
         }

         if (incoming.get(1)) {
            struct.type_length = iprot.readI32();
            struct.setType_lengthIsSet(true);
         }

         if (incoming.get(2)) {
            struct.repetition_type = FieldRepetitionType.findByValue(iprot.readI32());
            struct.setRepetition_typeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.num_children = iprot.readI32();
            struct.setNum_childrenIsSet(true);
         }

         if (incoming.get(4)) {
            struct.converted_type = ConvertedType.findByValue(iprot.readI32());
            struct.setConverted_typeIsSet(true);
         }

         if (incoming.get(5)) {
            struct.scale = iprot.readI32();
            struct.setScaleIsSet(true);
         }

         if (incoming.get(6)) {
            struct.precision = iprot.readI32();
            struct.setPrecisionIsSet(true);
         }

         if (incoming.get(7)) {
            struct.field_id = iprot.readI32();
            struct.setField_idIsSet(true);
         }

         if (incoming.get(8)) {
            struct.logicalType = new LogicalType();
            struct.logicalType.read(iprot);
            struct.setLogicalTypeIsSet(true);
         }

      }
   }
}
