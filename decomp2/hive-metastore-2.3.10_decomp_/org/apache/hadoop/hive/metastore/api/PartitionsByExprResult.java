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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
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

public class PartitionsByExprResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionsByExprResult");
   private static final TField PARTITIONS_FIELD_DESC = new TField("partitions", (byte)15, (short)1);
   private static final TField HAS_UNKNOWN_PARTITIONS_FIELD_DESC = new TField("hasUnknownPartitions", (byte)2, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionsByExprResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionsByExprResultTupleSchemeFactory();
   @Nullable
   private List partitions;
   private boolean hasUnknownPartitions;
   private static final int __HASUNKNOWNPARTITIONS_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public PartitionsByExprResult() {
      this.__isset_bitfield = 0;
   }

   public PartitionsByExprResult(List partitions, boolean hasUnknownPartitions) {
      this();
      this.partitions = partitions;
      this.hasUnknownPartitions = hasUnknownPartitions;
      this.setHasUnknownPartitionsIsSet(true);
   }

   public PartitionsByExprResult(PartitionsByExprResult other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetPartitions()) {
         List<Partition> __this__partitions = new ArrayList(other.partitions.size());

         for(Partition other_element : other.partitions) {
            __this__partitions.add(new Partition(other_element));
         }

         this.partitions = __this__partitions;
      }

      this.hasUnknownPartitions = other.hasUnknownPartitions;
   }

   public PartitionsByExprResult deepCopy() {
      return new PartitionsByExprResult(this);
   }

   public void clear() {
      this.partitions = null;
      this.setHasUnknownPartitionsIsSet(false);
      this.hasUnknownPartitions = false;
   }

   public int getPartitionsSize() {
      return this.partitions == null ? 0 : this.partitions.size();
   }

   @Nullable
   public Iterator getPartitionsIterator() {
      return this.partitions == null ? null : this.partitions.iterator();
   }

   public void addToPartitions(Partition elem) {
      if (this.partitions == null) {
         this.partitions = new ArrayList();
      }

      this.partitions.add(elem);
   }

   @Nullable
   public List getPartitions() {
      return this.partitions;
   }

   public void setPartitions(@Nullable List partitions) {
      this.partitions = partitions;
   }

   public void unsetPartitions() {
      this.partitions = null;
   }

   public boolean isSetPartitions() {
      return this.partitions != null;
   }

   public void setPartitionsIsSet(boolean value) {
      if (!value) {
         this.partitions = null;
      }

   }

   public boolean isHasUnknownPartitions() {
      return this.hasUnknownPartitions;
   }

   public void setHasUnknownPartitions(boolean hasUnknownPartitions) {
      this.hasUnknownPartitions = hasUnknownPartitions;
      this.setHasUnknownPartitionsIsSet(true);
   }

   public void unsetHasUnknownPartitions() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetHasUnknownPartitions() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setHasUnknownPartitionsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PARTITIONS:
            if (value == null) {
               this.unsetPartitions();
            } else {
               this.setPartitions((List)value);
            }
            break;
         case HAS_UNKNOWN_PARTITIONS:
            if (value == null) {
               this.unsetHasUnknownPartitions();
            } else {
               this.setHasUnknownPartitions((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PARTITIONS:
            return this.getPartitions();
         case HAS_UNKNOWN_PARTITIONS:
            return this.isHasUnknownPartitions();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PARTITIONS:
               return this.isSetPartitions();
            case HAS_UNKNOWN_PARTITIONS:
               return this.isSetHasUnknownPartitions();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionsByExprResult ? this.equals((PartitionsByExprResult)that) : false;
   }

   public boolean equals(PartitionsByExprResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_partitions = this.isSetPartitions();
         boolean that_present_partitions = that.isSetPartitions();
         if (this_present_partitions || that_present_partitions) {
            if (!this_present_partitions || !that_present_partitions) {
               return false;
            }

            if (!this.partitions.equals(that.partitions)) {
               return false;
            }
         }

         boolean this_present_hasUnknownPartitions = true;
         boolean that_present_hasUnknownPartitions = true;
         if (this_present_hasUnknownPartitions || that_present_hasUnknownPartitions) {
            if (!this_present_hasUnknownPartitions || !that_present_hasUnknownPartitions) {
               return false;
            }

            if (this.hasUnknownPartitions != that.hasUnknownPartitions) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPartitions() ? 131071 : 524287);
      if (this.isSetPartitions()) {
         hashCode = hashCode * 8191 + this.partitions.hashCode();
      }

      hashCode = hashCode * 8191 + (this.hasUnknownPartitions ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(PartitionsByExprResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPartitions(), other.isSetPartitions());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPartitions()) {
               lastComparison = TBaseHelper.compareTo(this.partitions, other.partitions);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetHasUnknownPartitions(), other.isSetHasUnknownPartitions());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetHasUnknownPartitions()) {
                  lastComparison = TBaseHelper.compareTo(this.hasUnknownPartitions, other.hasUnknownPartitions);
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
      return PartitionsByExprResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionsByExprResult(");
      boolean first = true;
      sb.append("partitions:");
      if (this.partitions == null) {
         sb.append("null");
      } else {
         sb.append(this.partitions);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("hasUnknownPartitions:");
      sb.append(this.hasUnknownPartitions);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPartitions()) {
         throw new TProtocolException("Required field 'partitions' is unset! Struct:" + this.toString());
      } else if (!this.isSetHasUnknownPartitions()) {
         throw new TProtocolException("Required field 'hasUnknownPartitions' is unset! Struct:" + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PartitionsByExprResult._Fields.PARTITIONS, new FieldMetaData("partitions", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, Partition.class))));
      tmpMap.put(PartitionsByExprResult._Fields.HAS_UNKNOWN_PARTITIONS, new FieldMetaData("hasUnknownPartitions", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionsByExprResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PARTITIONS((short)1, "partitions"),
      HAS_UNKNOWN_PARTITIONS((short)2, "hasUnknownPartitions");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PARTITIONS;
            case 2:
               return HAS_UNKNOWN_PARTITIONS;
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

   private static class PartitionsByExprResultStandardSchemeFactory implements SchemeFactory {
      private PartitionsByExprResultStandardSchemeFactory() {
      }

      public PartitionsByExprResultStandardScheme getScheme() {
         return new PartitionsByExprResultStandardScheme();
      }
   }

   private static class PartitionsByExprResultStandardScheme extends StandardScheme {
      private PartitionsByExprResultStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionsByExprResult struct) throws TException {
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

                  TList _list354 = iprot.readListBegin();
                  struct.partitions = new ArrayList(_list354.size);

                  for(int _i356 = 0; _i356 < _list354.size; ++_i356) {
                     Partition _elem355 = new Partition();
                     _elem355.read(iprot);
                     struct.partitions.add(_elem355);
                  }

                  iprot.readListEnd();
                  struct.setPartitionsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 2) {
                     struct.hasUnknownPartitions = iprot.readBool();
                     struct.setHasUnknownPartitionsIsSet(true);
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

      public void write(TProtocol oprot, PartitionsByExprResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionsByExprResult.STRUCT_DESC);
         if (struct.partitions != null) {
            oprot.writeFieldBegin(PartitionsByExprResult.PARTITIONS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitions.size()));

            for(Partition _iter357 : struct.partitions) {
               _iter357.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(PartitionsByExprResult.HAS_UNKNOWN_PARTITIONS_FIELD_DESC);
         oprot.writeBool(struct.hasUnknownPartitions);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionsByExprResultTupleSchemeFactory implements SchemeFactory {
      private PartitionsByExprResultTupleSchemeFactory() {
      }

      public PartitionsByExprResultTupleScheme getScheme() {
         return new PartitionsByExprResultTupleScheme();
      }
   }

   private static class PartitionsByExprResultTupleScheme extends TupleScheme {
      private PartitionsByExprResultTupleScheme() {
      }

      public void write(TProtocol prot, PartitionsByExprResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.partitions.size());

         for(Partition _iter358 : struct.partitions) {
            _iter358.write(oprot);
         }

         oprot.writeBool(struct.hasUnknownPartitions);
      }

      public void read(TProtocol prot, PartitionsByExprResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list359 = iprot.readListBegin((byte)12);
         struct.partitions = new ArrayList(_list359.size);

         for(int _i361 = 0; _i361 < _list359.size; ++_i361) {
            Partition _elem360 = new Partition();
            _elem360.read(iprot);
            struct.partitions.add(_elem360);
         }

         struct.setPartitionsIsSet(true);
         struct.hasUnknownPartitions = iprot.readBool();
         struct.setHasUnknownPartitionsIsSet(true);
      }
   }
}
