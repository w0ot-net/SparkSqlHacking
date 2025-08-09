package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class PartitionListComposingSpec implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionListComposingSpec");
   private static final TField PARTITIONS_FIELD_DESC = new TField("partitions", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionListComposingSpecStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionListComposingSpecTupleSchemeFactory();
   @Nullable
   private List partitions;
   public static final Map metaDataMap;

   public PartitionListComposingSpec() {
   }

   public PartitionListComposingSpec(List partitions) {
      this();
      this.partitions = partitions;
   }

   public PartitionListComposingSpec(PartitionListComposingSpec other) {
      if (other.isSetPartitions()) {
         List<Partition> __this__partitions = new ArrayList(other.partitions.size());

         for(Partition other_element : other.partitions) {
            __this__partitions.add(new Partition(other_element));
         }

         this.partitions = __this__partitions;
      }

   }

   public PartitionListComposingSpec deepCopy() {
      return new PartitionListComposingSpec(this);
   }

   public void clear() {
      this.partitions = null;
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

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PARTITIONS:
            if (value == null) {
               this.unsetPartitions();
            } else {
               this.setPartitions((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PARTITIONS:
            return this.getPartitions();
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
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionListComposingSpec ? this.equals((PartitionListComposingSpec)that) : false;
   }

   public boolean equals(PartitionListComposingSpec that) {
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

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPartitions() ? 131071 : 524287);
      if (this.isSetPartitions()) {
         hashCode = hashCode * 8191 + this.partitions.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionListComposingSpec other) {
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

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PartitionListComposingSpec._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionListComposingSpec(");
      boolean first = true;
      sb.append("partitions:");
      if (this.partitions == null) {
         sb.append("null");
      } else {
         sb.append(this.partitions);
      }

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
      tmpMap.put(PartitionListComposingSpec._Fields.PARTITIONS, new FieldMetaData("partitions", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, Partition.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionListComposingSpec.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PARTITIONS((short)1, "partitions");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PARTITIONS;
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

   private static class PartitionListComposingSpecStandardSchemeFactory implements SchemeFactory {
      private PartitionListComposingSpecStandardSchemeFactory() {
      }

      public PartitionListComposingSpecStandardScheme getScheme() {
         return new PartitionListComposingSpecStandardScheme();
      }
   }

   private static class PartitionListComposingSpecStandardScheme extends StandardScheme {
      private PartitionListComposingSpecStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionListComposingSpec struct) throws TException {
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

                  TList _list252 = iprot.readListBegin();
                  struct.partitions = new ArrayList(_list252.size);

                  for(int _i254 = 0; _i254 < _list252.size; ++_i254) {
                     Partition _elem253 = new Partition();
                     _elem253.read(iprot);
                     struct.partitions.add(_elem253);
                  }

                  iprot.readListEnd();
                  struct.setPartitionsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PartitionListComposingSpec struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionListComposingSpec.STRUCT_DESC);
         if (struct.partitions != null) {
            oprot.writeFieldBegin(PartitionListComposingSpec.PARTITIONS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitions.size()));

            for(Partition _iter255 : struct.partitions) {
               _iter255.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionListComposingSpecTupleSchemeFactory implements SchemeFactory {
      private PartitionListComposingSpecTupleSchemeFactory() {
      }

      public PartitionListComposingSpecTupleScheme getScheme() {
         return new PartitionListComposingSpecTupleScheme();
      }
   }

   private static class PartitionListComposingSpecTupleScheme extends TupleScheme {
      private PartitionListComposingSpecTupleScheme() {
      }

      public void write(TProtocol prot, PartitionListComposingSpec struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetPartitions()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetPartitions()) {
            oprot.writeI32(struct.partitions.size());

            for(Partition _iter256 : struct.partitions) {
               _iter256.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, PartitionListComposingSpec struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list257 = iprot.readListBegin((byte)12);
            struct.partitions = new ArrayList(_list257.size);

            for(int _i259 = 0; _i259 < _list257.size; ++_i259) {
               Partition _elem258 = new Partition();
               _elem258.read(iprot);
               struct.partitions.add(_elem258);
            }

            struct.setPartitionsIsSet(true);
         }

      }
   }
}
