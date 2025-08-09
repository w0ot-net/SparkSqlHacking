package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
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

public class PartitionValuesResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionValuesResponse");
   private static final TField PARTITION_VALUES_FIELD_DESC = new TField("partitionValues", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionValuesResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionValuesResponseTupleSchemeFactory();
   @Nullable
   private List partitionValues;
   public static final Map metaDataMap;

   public PartitionValuesResponse() {
   }

   public PartitionValuesResponse(List partitionValues) {
      this();
      this.partitionValues = partitionValues;
   }

   public PartitionValuesResponse(PartitionValuesResponse other) {
      if (other.isSetPartitionValues()) {
         List<PartitionValuesRow> __this__partitionValues = new ArrayList(other.partitionValues.size());

         for(PartitionValuesRow other_element : other.partitionValues) {
            __this__partitionValues.add(new PartitionValuesRow(other_element));
         }

         this.partitionValues = __this__partitionValues;
      }

   }

   public PartitionValuesResponse deepCopy() {
      return new PartitionValuesResponse(this);
   }

   public void clear() {
      this.partitionValues = null;
   }

   public int getPartitionValuesSize() {
      return this.partitionValues == null ? 0 : this.partitionValues.size();
   }

   @Nullable
   public Iterator getPartitionValuesIterator() {
      return this.partitionValues == null ? null : this.partitionValues.iterator();
   }

   public void addToPartitionValues(PartitionValuesRow elem) {
      if (this.partitionValues == null) {
         this.partitionValues = new ArrayList();
      }

      this.partitionValues.add(elem);
   }

   @Nullable
   public List getPartitionValues() {
      return this.partitionValues;
   }

   public void setPartitionValues(@Nullable List partitionValues) {
      this.partitionValues = partitionValues;
   }

   public void unsetPartitionValues() {
      this.partitionValues = null;
   }

   public boolean isSetPartitionValues() {
      return this.partitionValues != null;
   }

   public void setPartitionValuesIsSet(boolean value) {
      if (!value) {
         this.partitionValues = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PARTITION_VALUES:
            if (value == null) {
               this.unsetPartitionValues();
            } else {
               this.setPartitionValues((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PARTITION_VALUES:
            return this.getPartitionValues();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PARTITION_VALUES:
               return this.isSetPartitionValues();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionValuesResponse ? this.equals((PartitionValuesResponse)that) : false;
   }

   public boolean equals(PartitionValuesResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_partitionValues = this.isSetPartitionValues();
         boolean that_present_partitionValues = that.isSetPartitionValues();
         if (this_present_partitionValues || that_present_partitionValues) {
            if (!this_present_partitionValues || !that_present_partitionValues) {
               return false;
            }

            if (!this.partitionValues.equals(that.partitionValues)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPartitionValues() ? 131071 : 524287);
      if (this.isSetPartitionValues()) {
         hashCode = hashCode * 8191 + this.partitionValues.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionValuesResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPartitionValues(), other.isSetPartitionValues());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPartitionValues()) {
               lastComparison = TBaseHelper.compareTo(this.partitionValues, other.partitionValues);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PartitionValuesResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionValuesResponse(");
      boolean first = true;
      sb.append("partitionValues:");
      if (this.partitionValues == null) {
         sb.append("null");
      } else {
         sb.append(this.partitionValues);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPartitionValues()) {
         throw new TProtocolException("Required field 'partitionValues' is unset! Struct:" + this.toString());
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
      tmpMap.put(PartitionValuesResponse._Fields.PARTITION_VALUES, new FieldMetaData("partitionValues", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, PartitionValuesRow.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionValuesResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PARTITION_VALUES((short)1, "partitionValues");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PARTITION_VALUES;
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

   private static class PartitionValuesResponseStandardSchemeFactory implements SchemeFactory {
      private PartitionValuesResponseStandardSchemeFactory() {
      }

      public PartitionValuesResponseStandardScheme getScheme() {
         return new PartitionValuesResponseStandardScheme();
      }
   }

   private static class PartitionValuesResponseStandardScheme extends StandardScheme {
      private PartitionValuesResponseStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionValuesResponse struct) throws TException {
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

                  TList _list476 = iprot.readListBegin();
                  struct.partitionValues = new ArrayList(_list476.size);

                  for(int _i478 = 0; _i478 < _list476.size; ++_i478) {
                     PartitionValuesRow _elem477 = new PartitionValuesRow();
                     _elem477.read(iprot);
                     struct.partitionValues.add(_elem477);
                  }

                  iprot.readListEnd();
                  struct.setPartitionValuesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PartitionValuesResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionValuesResponse.STRUCT_DESC);
         if (struct.partitionValues != null) {
            oprot.writeFieldBegin(PartitionValuesResponse.PARTITION_VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitionValues.size()));

            for(PartitionValuesRow _iter479 : struct.partitionValues) {
               _iter479.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionValuesResponseTupleSchemeFactory implements SchemeFactory {
      private PartitionValuesResponseTupleSchemeFactory() {
      }

      public PartitionValuesResponseTupleScheme getScheme() {
         return new PartitionValuesResponseTupleScheme();
      }
   }

   private static class PartitionValuesResponseTupleScheme extends TupleScheme {
      private PartitionValuesResponseTupleScheme() {
      }

      public void write(TProtocol prot, PartitionValuesResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.partitionValues.size());

         for(PartitionValuesRow _iter480 : struct.partitionValues) {
            _iter480.write(oprot);
         }

      }

      public void read(TProtocol prot, PartitionValuesResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list481 = iprot.readListBegin((byte)12);
         struct.partitionValues = new ArrayList(_list481.size);

         for(int _i483 = 0; _i483 < _list481.size; ++_i483) {
            PartitionValuesRow _elem482 = new PartitionValuesRow();
            _elem482.read(iprot);
            struct.partitionValues.add(_elem482);
         }

         struct.setPartitionValuesIsSet(true);
      }
   }
}
