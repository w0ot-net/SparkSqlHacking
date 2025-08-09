package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TI32Column implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TI32Column");
   private static final TField VALUES_FIELD_DESC = new TField("values", (byte)15, (short)1);
   private static final TField NULLS_FIELD_DESC = new TField("nulls", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TI32ColumnStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TI32ColumnTupleSchemeFactory();
   @Nullable
   private List values;
   @Nullable
   private ByteBuffer nulls;
   public static final Map metaDataMap;

   public TI32Column() {
   }

   public TI32Column(List values, ByteBuffer nulls) {
      this();
      this.values = values;
      this.nulls = TBaseHelper.copyBinary(nulls);
   }

   public TI32Column(TI32Column other) {
      if (other.isSetValues()) {
         List<Integer> __this__values = new ArrayList(other.values);
         this.values = __this__values;
      }

      if (other.isSetNulls()) {
         this.nulls = TBaseHelper.copyBinary(other.nulls);
      }

   }

   public TI32Column deepCopy() {
      return new TI32Column(this);
   }

   public void clear() {
      this.values = null;
      this.nulls = null;
   }

   public int getValuesSize() {
      return this.values == null ? 0 : this.values.size();
   }

   @Nullable
   public Iterator getValuesIterator() {
      return this.values == null ? null : this.values.iterator();
   }

   public void addToValues(int elem) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(elem);
   }

   @Nullable
   public List getValues() {
      return this.values;
   }

   public void setValues(@Nullable List values) {
      this.values = values;
   }

   public void unsetValues() {
      this.values = null;
   }

   public boolean isSetValues() {
      return this.values != null;
   }

   public void setValuesIsSet(boolean value) {
      if (!value) {
         this.values = null;
      }

   }

   public byte[] getNulls() {
      this.setNulls(TBaseHelper.rightSize(this.nulls));
      return this.nulls == null ? null : this.nulls.array();
   }

   public ByteBuffer bufferForNulls() {
      return TBaseHelper.copyBinary(this.nulls);
   }

   public void setNulls(byte[] nulls) {
      this.nulls = nulls == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)nulls).clone());
   }

   public void setNulls(@Nullable ByteBuffer nulls) {
      this.nulls = TBaseHelper.copyBinary(nulls);
   }

   public void unsetNulls() {
      this.nulls = null;
   }

   public boolean isSetNulls() {
      return this.nulls != null;
   }

   public void setNullsIsSet(boolean value) {
      if (!value) {
         this.nulls = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case VALUES:
            if (value == null) {
               this.unsetValues();
            } else {
               this.setValues((List)value);
            }
            break;
         case NULLS:
            if (value == null) {
               this.unsetNulls();
            } else if (value instanceof byte[]) {
               this.setNulls((byte[])value);
            } else {
               this.setNulls((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case VALUES:
            return this.getValues();
         case NULLS:
            return this.getNulls();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case VALUES:
               return this.isSetValues();
            case NULLS:
               return this.isSetNulls();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TI32Column ? this.equals((TI32Column)that) : false;
   }

   public boolean equals(TI32Column that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_values = this.isSetValues();
         boolean that_present_values = that.isSetValues();
         if (this_present_values || that_present_values) {
            if (!this_present_values || !that_present_values) {
               return false;
            }

            if (!this.values.equals(that.values)) {
               return false;
            }
         }

         boolean this_present_nulls = this.isSetNulls();
         boolean that_present_nulls = that.isSetNulls();
         if (this_present_nulls || that_present_nulls) {
            if (!this_present_nulls || !that_present_nulls) {
               return false;
            }

            if (!this.nulls.equals(that.nulls)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetValues() ? 131071 : 524287);
      if (this.isSetValues()) {
         hashCode = hashCode * 8191 + this.values.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetNulls() ? 131071 : 524287);
      if (this.isSetNulls()) {
         hashCode = hashCode * 8191 + this.nulls.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TI32Column other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetValues(), other.isSetValues());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetValues()) {
               lastComparison = TBaseHelper.compareTo(this.values, other.values);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetNulls(), other.isSetNulls());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetNulls()) {
                  lastComparison = TBaseHelper.compareTo(this.nulls, other.nulls);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TI32Column._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TI32Column(");
      boolean first = true;
      sb.append("values:");
      if (this.values == null) {
         sb.append("null");
      } else {
         sb.append(this.values);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("nulls:");
      if (this.nulls == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.nulls, sb);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetValues()) {
         throw new TProtocolException("Required field 'values' is unset! Struct:" + this.toString());
      } else if (!this.isSetNulls()) {
         throw new TProtocolException("Required field 'nulls' is unset! Struct:" + this.toString());
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
      tmpMap.put(TI32Column._Fields.VALUES, new FieldMetaData("values", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)8))));
      tmpMap.put(TI32Column._Fields.NULLS, new FieldMetaData("nulls", (byte)1, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TI32Column.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VALUES((short)1, "values"),
      NULLS((short)2, "nulls");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VALUES;
            case 2:
               return NULLS;
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

   private static class TI32ColumnStandardSchemeFactory implements SchemeFactory {
      private TI32ColumnStandardSchemeFactory() {
      }

      public TI32ColumnStandardScheme getScheme() {
         return new TI32ColumnStandardScheme();
      }
   }

   private static class TI32ColumnStandardScheme extends StandardScheme {
      private TI32ColumnStandardScheme() {
      }

      public void read(TProtocol iprot, TI32Column struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list78 = iprot.readListBegin();
                  struct.values = new ArrayList(_list78.size);

                  for(int _i80 = 0; _i80 < _list78.size; ++_i80) {
                     int _elem79 = iprot.readI32();
                     struct.values.add(_elem79);
                  }

                  iprot.readListEnd();
                  struct.setValuesIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.nulls = iprot.readBinary();
                     struct.setNullsIsSet(true);
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

      public void write(TProtocol oprot, TI32Column struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TI32Column.STRUCT_DESC);
         if (struct.values != null) {
            oprot.writeFieldBegin(TI32Column.VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)8, struct.values.size()));

            for(int _iter81 : struct.values) {
               oprot.writeI32(_iter81);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.nulls != null) {
            oprot.writeFieldBegin(TI32Column.NULLS_FIELD_DESC);
            oprot.writeBinary(struct.nulls);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TI32ColumnTupleSchemeFactory implements SchemeFactory {
      private TI32ColumnTupleSchemeFactory() {
      }

      public TI32ColumnTupleScheme getScheme() {
         return new TI32ColumnTupleScheme();
      }
   }

   private static class TI32ColumnTupleScheme extends TupleScheme {
      private TI32ColumnTupleScheme() {
      }

      public void write(TProtocol prot, TI32Column struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.values.size());

         for(int _iter82 : struct.values) {
            oprot.writeI32(_iter82);
         }

         oprot.writeBinary(struct.nulls);
      }

      public void read(TProtocol prot, TI32Column struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list83 = iprot.readListBegin((byte)8);
         struct.values = new ArrayList(_list83.size);

         for(int _i85 = 0; _i85 < _list83.size; ++_i85) {
            int _elem84 = iprot.readI32();
            struct.values.add(_elem84);
         }

         struct.setValuesIsSet(true);
         struct.nulls = iprot.readBinary();
         struct.setNullsIsSet(true);
      }
   }
}
