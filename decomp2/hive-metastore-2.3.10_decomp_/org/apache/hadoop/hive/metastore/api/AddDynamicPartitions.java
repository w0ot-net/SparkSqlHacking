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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
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

public class AddDynamicPartitions implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("AddDynamicPartitions");
   private static final TField TXNID_FIELD_DESC = new TField("txnid", (byte)10, (short)1);
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)2);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)3);
   private static final TField PARTITIONNAMES_FIELD_DESC = new TField("partitionnames", (byte)15, (short)4);
   private static final TField OPERATION_TYPE_FIELD_DESC = new TField("operationType", (byte)8, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new AddDynamicPartitionsStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new AddDynamicPartitionsTupleSchemeFactory();
   private long txnid;
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private List partitionnames;
   @Nullable
   private DataOperationType operationType;
   private static final int __TXNID_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public AddDynamicPartitions() {
      this.__isset_bitfield = 0;
      this.operationType = DataOperationType.UNSET;
   }

   public AddDynamicPartitions(long txnid, String dbname, String tablename, List partitionnames) {
      this();
      this.txnid = txnid;
      this.setTxnidIsSet(true);
      this.dbname = dbname;
      this.tablename = tablename;
      this.partitionnames = partitionnames;
   }

   public AddDynamicPartitions(AddDynamicPartitions other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.txnid = other.txnid;
      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetPartitionnames()) {
         List<String> __this__partitionnames = new ArrayList(other.partitionnames);
         this.partitionnames = __this__partitionnames;
      }

      if (other.isSetOperationType()) {
         this.operationType = other.operationType;
      }

   }

   public AddDynamicPartitions deepCopy() {
      return new AddDynamicPartitions(this);
   }

   public void clear() {
      this.setTxnidIsSet(false);
      this.txnid = 0L;
      this.dbname = null;
      this.tablename = null;
      this.partitionnames = null;
      this.operationType = DataOperationType.UNSET;
   }

   public long getTxnid() {
      return this.txnid;
   }

   public void setTxnid(long txnid) {
      this.txnid = txnid;
      this.setTxnidIsSet(true);
   }

   public void unsetTxnid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetTxnid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setTxnidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getDbname() {
      return this.dbname;
   }

   public void setDbname(@Nullable String dbname) {
      this.dbname = dbname;
   }

   public void unsetDbname() {
      this.dbname = null;
   }

   public boolean isSetDbname() {
      return this.dbname != null;
   }

   public void setDbnameIsSet(boolean value) {
      if (!value) {
         this.dbname = null;
      }

   }

   @Nullable
   public String getTablename() {
      return this.tablename;
   }

   public void setTablename(@Nullable String tablename) {
      this.tablename = tablename;
   }

   public void unsetTablename() {
      this.tablename = null;
   }

   public boolean isSetTablename() {
      return this.tablename != null;
   }

   public void setTablenameIsSet(boolean value) {
      if (!value) {
         this.tablename = null;
      }

   }

   public int getPartitionnamesSize() {
      return this.partitionnames == null ? 0 : this.partitionnames.size();
   }

   @Nullable
   public Iterator getPartitionnamesIterator() {
      return this.partitionnames == null ? null : this.partitionnames.iterator();
   }

   public void addToPartitionnames(String elem) {
      if (this.partitionnames == null) {
         this.partitionnames = new ArrayList();
      }

      this.partitionnames.add(elem);
   }

   @Nullable
   public List getPartitionnames() {
      return this.partitionnames;
   }

   public void setPartitionnames(@Nullable List partitionnames) {
      this.partitionnames = partitionnames;
   }

   public void unsetPartitionnames() {
      this.partitionnames = null;
   }

   public boolean isSetPartitionnames() {
      return this.partitionnames != null;
   }

   public void setPartitionnamesIsSet(boolean value) {
      if (!value) {
         this.partitionnames = null;
      }

   }

   @Nullable
   public DataOperationType getOperationType() {
      return this.operationType;
   }

   public void setOperationType(@Nullable DataOperationType operationType) {
      this.operationType = operationType;
   }

   public void unsetOperationType() {
      this.operationType = null;
   }

   public boolean isSetOperationType() {
      return this.operationType != null;
   }

   public void setOperationTypeIsSet(boolean value) {
      if (!value) {
         this.operationType = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TXNID:
            if (value == null) {
               this.unsetTxnid();
            } else {
               this.setTxnid((Long)value);
            }
            break;
         case DBNAME:
            if (value == null) {
               this.unsetDbname();
            } else {
               this.setDbname((String)value);
            }
            break;
         case TABLENAME:
            if (value == null) {
               this.unsetTablename();
            } else {
               this.setTablename((String)value);
            }
            break;
         case PARTITIONNAMES:
            if (value == null) {
               this.unsetPartitionnames();
            } else {
               this.setPartitionnames((List)value);
            }
            break;
         case OPERATION_TYPE:
            if (value == null) {
               this.unsetOperationType();
            } else {
               this.setOperationType((DataOperationType)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TXNID:
            return this.getTxnid();
         case DBNAME:
            return this.getDbname();
         case TABLENAME:
            return this.getTablename();
         case PARTITIONNAMES:
            return this.getPartitionnames();
         case OPERATION_TYPE:
            return this.getOperationType();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TXNID:
               return this.isSetTxnid();
            case DBNAME:
               return this.isSetDbname();
            case TABLENAME:
               return this.isSetTablename();
            case PARTITIONNAMES:
               return this.isSetPartitionnames();
            case OPERATION_TYPE:
               return this.isSetOperationType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof AddDynamicPartitions ? this.equals((AddDynamicPartitions)that) : false;
   }

   public boolean equals(AddDynamicPartitions that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_txnid = true;
         boolean that_present_txnid = true;
         if (this_present_txnid || that_present_txnid) {
            if (!this_present_txnid || !that_present_txnid) {
               return false;
            }

            if (this.txnid != that.txnid) {
               return false;
            }
         }

         boolean this_present_dbname = this.isSetDbname();
         boolean that_present_dbname = that.isSetDbname();
         if (this_present_dbname || that_present_dbname) {
            if (!this_present_dbname || !that_present_dbname) {
               return false;
            }

            if (!this.dbname.equals(that.dbname)) {
               return false;
            }
         }

         boolean this_present_tablename = this.isSetTablename();
         boolean that_present_tablename = that.isSetTablename();
         if (this_present_tablename || that_present_tablename) {
            if (!this_present_tablename || !that_present_tablename) {
               return false;
            }

            if (!this.tablename.equals(that.tablename)) {
               return false;
            }
         }

         boolean this_present_partitionnames = this.isSetPartitionnames();
         boolean that_present_partitionnames = that.isSetPartitionnames();
         if (this_present_partitionnames || that_present_partitionnames) {
            if (!this_present_partitionnames || !that_present_partitionnames) {
               return false;
            }

            if (!this.partitionnames.equals(that.partitionnames)) {
               return false;
            }
         }

         boolean this_present_operationType = this.isSetOperationType();
         boolean that_present_operationType = that.isSetOperationType();
         if (this_present_operationType || that_present_operationType) {
            if (!this_present_operationType || !that_present_operationType) {
               return false;
            }

            if (!this.operationType.equals(that.operationType)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.txnid);
      hashCode = hashCode * 8191 + (this.isSetDbname() ? 131071 : 524287);
      if (this.isSetDbname()) {
         hashCode = hashCode * 8191 + this.dbname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTablename() ? 131071 : 524287);
      if (this.isSetTablename()) {
         hashCode = hashCode * 8191 + this.tablename.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionnames() ? 131071 : 524287);
      if (this.isSetPartitionnames()) {
         hashCode = hashCode * 8191 + this.partitionnames.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOperationType() ? 131071 : 524287);
      if (this.isSetOperationType()) {
         hashCode = hashCode * 8191 + this.operationType.getValue();
      }

      return hashCode;
   }

   public int compareTo(AddDynamicPartitions other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTxnid(), other.isSetTxnid());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTxnid()) {
               lastComparison = TBaseHelper.compareTo(this.txnid, other.txnid);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetDbname(), other.isSetDbname());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetDbname()) {
                  lastComparison = TBaseHelper.compareTo(this.dbname, other.dbname);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetTablename(), other.isSetTablename());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetTablename()) {
                     lastComparison = TBaseHelper.compareTo(this.tablename, other.tablename);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetPartitionnames(), other.isSetPartitionnames());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetPartitionnames()) {
                        lastComparison = TBaseHelper.compareTo(this.partitionnames, other.partitionnames);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetOperationType(), other.isSetOperationType());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetOperationType()) {
                           lastComparison = TBaseHelper.compareTo(this.operationType, other.operationType);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return AddDynamicPartitions._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AddDynamicPartitions(");
      boolean first = true;
      sb.append("txnid:");
      sb.append(this.txnid);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("dbname:");
      if (this.dbname == null) {
         sb.append("null");
      } else {
         sb.append(this.dbname);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tablename:");
      if (this.tablename == null) {
         sb.append("null");
      } else {
         sb.append(this.tablename);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("partitionnames:");
      if (this.partitionnames == null) {
         sb.append("null");
      } else {
         sb.append(this.partitionnames);
      }

      first = false;
      if (this.isSetOperationType()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("operationType:");
         if (this.operationType == null) {
            sb.append("null");
         } else {
            sb.append(this.operationType);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTxnid()) {
         throw new TProtocolException("Required field 'txnid' is unset! Struct:" + this.toString());
      } else if (!this.isSetDbname()) {
         throw new TProtocolException("Required field 'dbname' is unset! Struct:" + this.toString());
      } else if (!this.isSetTablename()) {
         throw new TProtocolException("Required field 'tablename' is unset! Struct:" + this.toString());
      } else if (!this.isSetPartitionnames()) {
         throw new TProtocolException("Required field 'partitionnames' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{AddDynamicPartitions._Fields.OPERATION_TYPE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(AddDynamicPartitions._Fields.TXNID, new FieldMetaData("txnid", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(AddDynamicPartitions._Fields.DBNAME, new FieldMetaData("dbname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(AddDynamicPartitions._Fields.TABLENAME, new FieldMetaData("tablename", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(AddDynamicPartitions._Fields.PARTITIONNAMES, new FieldMetaData("partitionnames", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(AddDynamicPartitions._Fields.OPERATION_TYPE, new FieldMetaData("operationType", (byte)2, new EnumMetaData((byte)16, DataOperationType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(AddDynamicPartitions.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TXNID((short)1, "txnid"),
      DBNAME((short)2, "dbname"),
      TABLENAME((short)3, "tablename"),
      PARTITIONNAMES((short)4, "partitionnames"),
      OPERATION_TYPE((short)5, "operationType");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TXNID;
            case 2:
               return DBNAME;
            case 3:
               return TABLENAME;
            case 4:
               return PARTITIONNAMES;
            case 5:
               return OPERATION_TYPE;
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

   private static class AddDynamicPartitionsStandardSchemeFactory implements SchemeFactory {
      private AddDynamicPartitionsStandardSchemeFactory() {
      }

      public AddDynamicPartitionsStandardScheme getScheme() {
         return new AddDynamicPartitionsStandardScheme();
      }
   }

   private static class AddDynamicPartitionsStandardScheme extends StandardScheme {
      private AddDynamicPartitionsStandardScheme() {
      }

      public void read(TProtocol iprot, AddDynamicPartitions struct) throws TException {
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
                  if (schemeField.type == 10) {
                     struct.txnid = iprot.readI64();
                     struct.setTxnidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.dbname = iprot.readString();
                     struct.setDbnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.tablename = iprot.readString();
                     struct.setTablenameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list574 = iprot.readListBegin();
                  struct.partitionnames = new ArrayList(_list574.size);

                  for(int _i576 = 0; _i576 < _list574.size; ++_i576) {
                     String _elem575 = iprot.readString();
                     struct.partitionnames.add(_elem575);
                  }

                  iprot.readListEnd();
                  struct.setPartitionnamesIsSet(true);
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.operationType = DataOperationType.findByValue(iprot.readI32());
                     struct.setOperationTypeIsSet(true);
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

      public void write(TProtocol oprot, AddDynamicPartitions struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(AddDynamicPartitions.STRUCT_DESC);
         oprot.writeFieldBegin(AddDynamicPartitions.TXNID_FIELD_DESC);
         oprot.writeI64(struct.txnid);
         oprot.writeFieldEnd();
         if (struct.dbname != null) {
            oprot.writeFieldBegin(AddDynamicPartitions.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null) {
            oprot.writeFieldBegin(AddDynamicPartitions.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.partitionnames != null) {
            oprot.writeFieldBegin(AddDynamicPartitions.PARTITIONNAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.partitionnames.size()));

            for(String _iter577 : struct.partitionnames) {
               oprot.writeString(_iter577);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.operationType != null && struct.isSetOperationType()) {
            oprot.writeFieldBegin(AddDynamicPartitions.OPERATION_TYPE_FIELD_DESC);
            oprot.writeI32(struct.operationType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class AddDynamicPartitionsTupleSchemeFactory implements SchemeFactory {
      private AddDynamicPartitionsTupleSchemeFactory() {
      }

      public AddDynamicPartitionsTupleScheme getScheme() {
         return new AddDynamicPartitionsTupleScheme();
      }
   }

   private static class AddDynamicPartitionsTupleScheme extends TupleScheme {
      private AddDynamicPartitionsTupleScheme() {
      }

      public void write(TProtocol prot, AddDynamicPartitions struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.txnid);
         oprot.writeString(struct.dbname);
         oprot.writeString(struct.tablename);
         oprot.writeI32(struct.partitionnames.size());

         for(String _iter578 : struct.partitionnames) {
            oprot.writeString(_iter578);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetOperationType()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetOperationType()) {
            oprot.writeI32(struct.operationType.getValue());
         }

      }

      public void read(TProtocol prot, AddDynamicPartitions struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.txnid = iprot.readI64();
         struct.setTxnidIsSet(true);
         struct.dbname = iprot.readString();
         struct.setDbnameIsSet(true);
         struct.tablename = iprot.readString();
         struct.setTablenameIsSet(true);
         TList _list579 = iprot.readListBegin((byte)11);
         struct.partitionnames = new ArrayList(_list579.size);

         for(int _i581 = 0; _i581 < _list579.size; ++_i581) {
            String _elem580 = iprot.readString();
            struct.partitionnames.add(_elem580);
         }

         struct.setPartitionnamesIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.operationType = DataOperationType.findByValue(iprot.readI32());
            struct.setOperationTypeIsSet(true);
         }

      }
   }
}
