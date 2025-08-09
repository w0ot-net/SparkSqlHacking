package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class SortingColumn implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SortingColumn");
   private static final TField COLUMN_IDX_FIELD_DESC = new TField("column_idx", (byte)8, (short)1);
   private static final TField DESCENDING_FIELD_DESC = new TField("descending", (byte)2, (short)2);
   private static final TField NULLS_FIRST_FIELD_DESC = new TField("nulls_first", (byte)2, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SortingColumnStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SortingColumnTupleSchemeFactory();
   public int column_idx;
   public boolean descending;
   public boolean nulls_first;
   private static final int __COLUMN_IDX_ISSET_ID = 0;
   private static final int __DESCENDING_ISSET_ID = 1;
   private static final int __NULLS_FIRST_ISSET_ID = 2;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public SortingColumn() {
      this.__isset_bitfield = 0;
   }

   public SortingColumn(int column_idx, boolean descending, boolean nulls_first) {
      this();
      this.column_idx = column_idx;
      this.setColumn_idxIsSet(true);
      this.descending = descending;
      this.setDescendingIsSet(true);
      this.nulls_first = nulls_first;
      this.setNulls_firstIsSet(true);
   }

   public SortingColumn(SortingColumn other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.column_idx = other.column_idx;
      this.descending = other.descending;
      this.nulls_first = other.nulls_first;
   }

   public SortingColumn deepCopy() {
      return new SortingColumn(this);
   }

   public void clear() {
      this.setColumn_idxIsSet(false);
      this.column_idx = 0;
      this.setDescendingIsSet(false);
      this.descending = false;
      this.setNulls_firstIsSet(false);
      this.nulls_first = false;
   }

   public int getColumn_idx() {
      return this.column_idx;
   }

   public SortingColumn setColumn_idx(int column_idx) {
      this.column_idx = column_idx;
      this.setColumn_idxIsSet(true);
      return this;
   }

   public void unsetColumn_idx() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetColumn_idx() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setColumn_idxIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public boolean isDescending() {
      return this.descending;
   }

   public SortingColumn setDescending(boolean descending) {
      this.descending = descending;
      this.setDescendingIsSet(true);
      return this;
   }

   public void unsetDescending() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetDescending() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setDescendingIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public boolean isNulls_first() {
      return this.nulls_first;
   }

   public SortingColumn setNulls_first(boolean nulls_first) {
      this.nulls_first = nulls_first;
      this.setNulls_firstIsSet(true);
      return this;
   }

   public void unsetNulls_first() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetNulls_first() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setNulls_firstIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COLUMN_IDX:
            if (value == null) {
               this.unsetColumn_idx();
            } else {
               this.setColumn_idx((Integer)value);
            }
            break;
         case DESCENDING:
            if (value == null) {
               this.unsetDescending();
            } else {
               this.setDescending((Boolean)value);
            }
            break;
         case NULLS_FIRST:
            if (value == null) {
               this.unsetNulls_first();
            } else {
               this.setNulls_first((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COLUMN_IDX:
            return this.getColumn_idx();
         case DESCENDING:
            return this.isDescending();
         case NULLS_FIRST:
            return this.isNulls_first();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COLUMN_IDX:
               return this.isSetColumn_idx();
            case DESCENDING:
               return this.isSetDescending();
            case NULLS_FIRST:
               return this.isSetNulls_first();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SortingColumn ? this.equals((SortingColumn)that) : false;
   }

   public boolean equals(SortingColumn that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_column_idx = true;
         boolean that_present_column_idx = true;
         if (this_present_column_idx || that_present_column_idx) {
            if (!this_present_column_idx || !that_present_column_idx) {
               return false;
            }

            if (this.column_idx != that.column_idx) {
               return false;
            }
         }

         boolean this_present_descending = true;
         boolean that_present_descending = true;
         if (this_present_descending || that_present_descending) {
            if (!this_present_descending || !that_present_descending) {
               return false;
            }

            if (this.descending != that.descending) {
               return false;
            }
         }

         boolean this_present_nulls_first = true;
         boolean that_present_nulls_first = true;
         if (this_present_nulls_first || that_present_nulls_first) {
            if (!this_present_nulls_first || !that_present_nulls_first) {
               return false;
            }

            if (this.nulls_first != that.nulls_first) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.column_idx;
      hashCode = hashCode * 8191 + (this.descending ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.nulls_first ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(SortingColumn other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColumn_idx(), other.isSetColumn_idx());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColumn_idx()) {
               lastComparison = TBaseHelper.compareTo(this.column_idx, other.column_idx);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetDescending(), other.isSetDescending());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetDescending()) {
                  lastComparison = TBaseHelper.compareTo(this.descending, other.descending);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetNulls_first(), other.isSetNulls_first());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetNulls_first()) {
                     lastComparison = TBaseHelper.compareTo(this.nulls_first, other.nulls_first);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return SortingColumn._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SortingColumn(");
      boolean first = true;
      sb.append("column_idx:");
      sb.append(this.column_idx);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("descending:");
      sb.append(this.descending);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("nulls_first:");
      sb.append(this.nulls_first);
      first = false;
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(SortingColumn._Fields.COLUMN_IDX, new FieldMetaData("column_idx", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(SortingColumn._Fields.DESCENDING, new FieldMetaData("descending", (byte)1, new FieldValueMetaData((byte)2)));
      tmpMap.put(SortingColumn._Fields.NULLS_FIRST, new FieldMetaData("nulls_first", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SortingColumn.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COLUMN_IDX((short)1, "column_idx"),
      DESCENDING((short)2, "descending"),
      NULLS_FIRST((short)3, "nulls_first");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COLUMN_IDX;
            case 2:
               return DESCENDING;
            case 3:
               return NULLS_FIRST;
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

   private static class SortingColumnStandardSchemeFactory implements SchemeFactory {
      private SortingColumnStandardSchemeFactory() {
      }

      public SortingColumnStandardScheme getScheme() {
         return new SortingColumnStandardScheme();
      }
   }

   private static class SortingColumnStandardScheme extends StandardScheme {
      private SortingColumnStandardScheme() {
      }

      public void read(TProtocol iprot, SortingColumn struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetColumn_idx()) {
                  throw new TProtocolException("Required field 'column_idx' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetDescending()) {
                  throw new TProtocolException("Required field 'descending' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetNulls_first()) {
                  throw new TProtocolException("Required field 'nulls_first' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.column_idx = iprot.readI32();
                     struct.setColumn_idxIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 2) {
                     struct.descending = iprot.readBool();
                     struct.setDescendingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 2) {
                     struct.nulls_first = iprot.readBool();
                     struct.setNulls_firstIsSet(true);
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

      public void write(TProtocol oprot, SortingColumn struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SortingColumn.STRUCT_DESC);
         oprot.writeFieldBegin(SortingColumn.COLUMN_IDX_FIELD_DESC);
         oprot.writeI32(struct.column_idx);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SortingColumn.DESCENDING_FIELD_DESC);
         oprot.writeBool(struct.descending);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SortingColumn.NULLS_FIRST_FIELD_DESC);
         oprot.writeBool(struct.nulls_first);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SortingColumnTupleSchemeFactory implements SchemeFactory {
      private SortingColumnTupleSchemeFactory() {
      }

      public SortingColumnTupleScheme getScheme() {
         return new SortingColumnTupleScheme();
      }
   }

   private static class SortingColumnTupleScheme extends TupleScheme {
      private SortingColumnTupleScheme() {
      }

      public void write(TProtocol prot, SortingColumn struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.column_idx);
         oprot.writeBool(struct.descending);
         oprot.writeBool(struct.nulls_first);
      }

      public void read(TProtocol prot, SortingColumn struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.column_idx = iprot.readI32();
         struct.setColumn_idxIsSet(true);
         struct.descending = iprot.readBool();
         struct.setDescendingIsSet(true);
         struct.nulls_first = iprot.readBool();
         struct.setNulls_firstIsSet(true);
      }
   }
}
