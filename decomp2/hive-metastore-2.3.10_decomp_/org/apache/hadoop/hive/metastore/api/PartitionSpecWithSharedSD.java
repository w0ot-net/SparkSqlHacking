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

public class PartitionSpecWithSharedSD implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionSpecWithSharedSD");
   private static final TField PARTITIONS_FIELD_DESC = new TField("partitions", (byte)15, (short)1);
   private static final TField SD_FIELD_DESC = new TField("sd", (byte)12, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionSpecWithSharedSDStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionSpecWithSharedSDTupleSchemeFactory();
   @Nullable
   private List partitions;
   @Nullable
   private StorageDescriptor sd;
   public static final Map metaDataMap;

   public PartitionSpecWithSharedSD() {
   }

   public PartitionSpecWithSharedSD(List partitions, StorageDescriptor sd) {
      this();
      this.partitions = partitions;
      this.sd = sd;
   }

   public PartitionSpecWithSharedSD(PartitionSpecWithSharedSD other) {
      if (other.isSetPartitions()) {
         List<PartitionWithoutSD> __this__partitions = new ArrayList(other.partitions.size());

         for(PartitionWithoutSD other_element : other.partitions) {
            __this__partitions.add(new PartitionWithoutSD(other_element));
         }

         this.partitions = __this__partitions;
      }

      if (other.isSetSd()) {
         this.sd = new StorageDescriptor(other.sd);
      }

   }

   public PartitionSpecWithSharedSD deepCopy() {
      return new PartitionSpecWithSharedSD(this);
   }

   public void clear() {
      this.partitions = null;
      this.sd = null;
   }

   public int getPartitionsSize() {
      return this.partitions == null ? 0 : this.partitions.size();
   }

   @Nullable
   public Iterator getPartitionsIterator() {
      return this.partitions == null ? null : this.partitions.iterator();
   }

   public void addToPartitions(PartitionWithoutSD elem) {
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

   @Nullable
   public StorageDescriptor getSd() {
      return this.sd;
   }

   public void setSd(@Nullable StorageDescriptor sd) {
      this.sd = sd;
   }

   public void unsetSd() {
      this.sd = null;
   }

   public boolean isSetSd() {
      return this.sd != null;
   }

   public void setSdIsSet(boolean value) {
      if (!value) {
         this.sd = null;
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
            break;
         case SD:
            if (value == null) {
               this.unsetSd();
            } else {
               this.setSd((StorageDescriptor)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PARTITIONS:
            return this.getPartitions();
         case SD:
            return this.getSd();
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
            case SD:
               return this.isSetSd();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionSpecWithSharedSD ? this.equals((PartitionSpecWithSharedSD)that) : false;
   }

   public boolean equals(PartitionSpecWithSharedSD that) {
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

         boolean this_present_sd = this.isSetSd();
         boolean that_present_sd = that.isSetSd();
         if (this_present_sd || that_present_sd) {
            if (!this_present_sd || !that_present_sd) {
               return false;
            }

            if (!this.sd.equals(that.sd)) {
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

      hashCode = hashCode * 8191 + (this.isSetSd() ? 131071 : 524287);
      if (this.isSetSd()) {
         hashCode = hashCode * 8191 + this.sd.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionSpecWithSharedSD other) {
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

            lastComparison = Boolean.compare(this.isSetSd(), other.isSetSd());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSd()) {
                  lastComparison = TBaseHelper.compareTo(this.sd, other.sd);
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
      return PartitionSpecWithSharedSD._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionSpecWithSharedSD(");
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

      sb.append("sd:");
      if (this.sd == null) {
         sb.append("null");
      } else {
         sb.append(this.sd);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.sd != null) {
         this.sd.validate();
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
      tmpMap.put(PartitionSpecWithSharedSD._Fields.PARTITIONS, new FieldMetaData("partitions", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, PartitionWithoutSD.class))));
      tmpMap.put(PartitionSpecWithSharedSD._Fields.SD, new FieldMetaData("sd", (byte)3, new StructMetaData((byte)12, StorageDescriptor.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionSpecWithSharedSD.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PARTITIONS((short)1, "partitions"),
      SD((short)2, "sd");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PARTITIONS;
            case 2:
               return SD;
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

   private static class PartitionSpecWithSharedSDStandardSchemeFactory implements SchemeFactory {
      private PartitionSpecWithSharedSDStandardSchemeFactory() {
      }

      public PartitionSpecWithSharedSDStandardScheme getScheme() {
         return new PartitionSpecWithSharedSDStandardScheme();
      }
   }

   private static class PartitionSpecWithSharedSDStandardScheme extends StandardScheme {
      private PartitionSpecWithSharedSDStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionSpecWithSharedSD struct) throws TException {
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

                  TList _list244 = iprot.readListBegin();
                  struct.partitions = new ArrayList(_list244.size);

                  for(int _i246 = 0; _i246 < _list244.size; ++_i246) {
                     PartitionWithoutSD _elem245 = new PartitionWithoutSD();
                     _elem245.read(iprot);
                     struct.partitions.add(_elem245);
                  }

                  iprot.readListEnd();
                  struct.setPartitionsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.sd = new StorageDescriptor();
                     struct.sd.read(iprot);
                     struct.setSdIsSet(true);
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

      public void write(TProtocol oprot, PartitionSpecWithSharedSD struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionSpecWithSharedSD.STRUCT_DESC);
         if (struct.partitions != null) {
            oprot.writeFieldBegin(PartitionSpecWithSharedSD.PARTITIONS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitions.size()));

            for(PartitionWithoutSD _iter247 : struct.partitions) {
               _iter247.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.sd != null) {
            oprot.writeFieldBegin(PartitionSpecWithSharedSD.SD_FIELD_DESC);
            struct.sd.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionSpecWithSharedSDTupleSchemeFactory implements SchemeFactory {
      private PartitionSpecWithSharedSDTupleSchemeFactory() {
      }

      public PartitionSpecWithSharedSDTupleScheme getScheme() {
         return new PartitionSpecWithSharedSDTupleScheme();
      }
   }

   private static class PartitionSpecWithSharedSDTupleScheme extends TupleScheme {
      private PartitionSpecWithSharedSDTupleScheme() {
      }

      public void write(TProtocol prot, PartitionSpecWithSharedSD struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetPartitions()) {
            optionals.set(0);
         }

         if (struct.isSetSd()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetPartitions()) {
            oprot.writeI32(struct.partitions.size());

            for(PartitionWithoutSD _iter248 : struct.partitions) {
               _iter248.write(oprot);
            }
         }

         if (struct.isSetSd()) {
            struct.sd.write(oprot);
         }

      }

      public void read(TProtocol prot, PartitionSpecWithSharedSD struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            TList _list249 = iprot.readListBegin((byte)12);
            struct.partitions = new ArrayList(_list249.size);

            for(int _i251 = 0; _i251 < _list249.size; ++_i251) {
               PartitionWithoutSD _elem250 = new PartitionWithoutSD();
               _elem250.read(iprot);
               struct.partitions.add(_elem250);
            }

            struct.setPartitionsIsSet(true);
         }

         if (incoming.get(1)) {
            struct.sd = new StorageDescriptor();
            struct.sd.read(iprot);
            struct.setSdIsSet(true);
         }

      }
   }
}
