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

public class DropPartitionsResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DropPartitionsResult");
   private static final TField PARTITIONS_FIELD_DESC = new TField("partitions", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DropPartitionsResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DropPartitionsResultTupleSchemeFactory();
   @Nullable
   private List partitions;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DropPartitionsResult() {
   }

   public DropPartitionsResult(DropPartitionsResult other) {
      if (other.isSetPartitions()) {
         List<Partition> __this__partitions = new ArrayList(other.partitions.size());

         for(Partition other_element : other.partitions) {
            __this__partitions.add(new Partition(other_element));
         }

         this.partitions = __this__partitions;
      }

   }

   public DropPartitionsResult deepCopy() {
      return new DropPartitionsResult(this);
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
      return that instanceof DropPartitionsResult ? this.equals((DropPartitionsResult)that) : false;
   }

   public boolean equals(DropPartitionsResult that) {
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

   public int compareTo(DropPartitionsResult other) {
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
      return DropPartitionsResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DropPartitionsResult(");
      boolean first = true;
      if (this.isSetPartitions()) {
         sb.append("partitions:");
         if (this.partitions == null) {
            sb.append("null");
         } else {
            sb.append(this.partitions);
         }

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
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{DropPartitionsResult._Fields.PARTITIONS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DropPartitionsResult._Fields.PARTITIONS, new FieldMetaData("partitions", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, Partition.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DropPartitionsResult.class, metaDataMap);
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

   private static class DropPartitionsResultStandardSchemeFactory implements SchemeFactory {
      private DropPartitionsResultStandardSchemeFactory() {
      }

      public DropPartitionsResultStandardScheme getScheme() {
         return new DropPartitionsResultStandardScheme();
      }
   }

   private static class DropPartitionsResultStandardScheme extends StandardScheme {
      private DropPartitionsResultStandardScheme() {
      }

      public void read(TProtocol iprot, DropPartitionsResult struct) throws TException {
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

                  TList _list428 = iprot.readListBegin();
                  struct.partitions = new ArrayList(_list428.size);

                  for(int _i430 = 0; _i430 < _list428.size; ++_i430) {
                     Partition _elem429 = new Partition();
                     _elem429.read(iprot);
                     struct.partitions.add(_elem429);
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

      public void write(TProtocol oprot, DropPartitionsResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DropPartitionsResult.STRUCT_DESC);
         if (struct.partitions != null && struct.isSetPartitions()) {
            oprot.writeFieldBegin(DropPartitionsResult.PARTITIONS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitions.size()));

            for(Partition _iter431 : struct.partitions) {
               _iter431.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DropPartitionsResultTupleSchemeFactory implements SchemeFactory {
      private DropPartitionsResultTupleSchemeFactory() {
      }

      public DropPartitionsResultTupleScheme getScheme() {
         return new DropPartitionsResultTupleScheme();
      }
   }

   private static class DropPartitionsResultTupleScheme extends TupleScheme {
      private DropPartitionsResultTupleScheme() {
      }

      public void write(TProtocol prot, DropPartitionsResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetPartitions()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetPartitions()) {
            oprot.writeI32(struct.partitions.size());

            for(Partition _iter432 : struct.partitions) {
               _iter432.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, DropPartitionsResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list433 = iprot.readListBegin((byte)12);
            struct.partitions = new ArrayList(_list433.size);

            for(int _i435 = 0; _i435 < _list433.size; ++_i435) {
               Partition _elem434 = new Partition();
               _elem434.read(iprot);
               struct.partitions.add(_elem434);
            }

            struct.setPartitionsIsSet(true);
         }

      }
   }
}
