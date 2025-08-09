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

public class FireEventRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("FireEventRequest");
   private static final TField SUCCESSFUL_FIELD_DESC = new TField("successful", (byte)2, (short)1);
   private static final TField DATA_FIELD_DESC = new TField("data", (byte)12, (short)2);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)3);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)4);
   private static final TField PARTITION_VALS_FIELD_DESC = new TField("partitionVals", (byte)15, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FireEventRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FireEventRequestTupleSchemeFactory();
   private boolean successful;
   @Nullable
   private FireEventRequestData data;
   @Nullable
   private String dbName;
   @Nullable
   private String tableName;
   @Nullable
   private List partitionVals;
   private static final int __SUCCESSFUL_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public FireEventRequest() {
      this.__isset_bitfield = 0;
   }

   public FireEventRequest(boolean successful, FireEventRequestData data) {
      this();
      this.successful = successful;
      this.setSuccessfulIsSet(true);
      this.data = data;
   }

   public FireEventRequest(FireEventRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.successful = other.successful;
      if (other.isSetData()) {
         this.data = new FireEventRequestData(other.data);
      }

      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetPartitionVals()) {
         List<String> __this__partitionVals = new ArrayList(other.partitionVals);
         this.partitionVals = __this__partitionVals;
      }

   }

   public FireEventRequest deepCopy() {
      return new FireEventRequest(this);
   }

   public void clear() {
      this.setSuccessfulIsSet(false);
      this.successful = false;
      this.data = null;
      this.dbName = null;
      this.tableName = null;
      this.partitionVals = null;
   }

   public boolean isSuccessful() {
      return this.successful;
   }

   public void setSuccessful(boolean successful) {
      this.successful = successful;
      this.setSuccessfulIsSet(true);
   }

   public void unsetSuccessful() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetSuccessful() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setSuccessfulIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public FireEventRequestData getData() {
      return this.data;
   }

   public void setData(@Nullable FireEventRequestData data) {
      this.data = data;
   }

   public void unsetData() {
      this.data = null;
   }

   public boolean isSetData() {
      return this.data != null;
   }

   public void setDataIsSet(boolean value) {
      if (!value) {
         this.data = null;
      }

   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = dbName;
   }

   public void unsetDbName() {
      this.dbName = null;
   }

   public boolean isSetDbName() {
      return this.dbName != null;
   }

   public void setDbNameIsSet(boolean value) {
      if (!value) {
         this.dbName = null;
      }

   }

   @Nullable
   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(@Nullable String tableName) {
      this.tableName = tableName;
   }

   public void unsetTableName() {
      this.tableName = null;
   }

   public boolean isSetTableName() {
      return this.tableName != null;
   }

   public void setTableNameIsSet(boolean value) {
      if (!value) {
         this.tableName = null;
      }

   }

   public int getPartitionValsSize() {
      return this.partitionVals == null ? 0 : this.partitionVals.size();
   }

   @Nullable
   public Iterator getPartitionValsIterator() {
      return this.partitionVals == null ? null : this.partitionVals.iterator();
   }

   public void addToPartitionVals(String elem) {
      if (this.partitionVals == null) {
         this.partitionVals = new ArrayList();
      }

      this.partitionVals.add(elem);
   }

   @Nullable
   public List getPartitionVals() {
      return this.partitionVals;
   }

   public void setPartitionVals(@Nullable List partitionVals) {
      this.partitionVals = partitionVals;
   }

   public void unsetPartitionVals() {
      this.partitionVals = null;
   }

   public boolean isSetPartitionVals() {
      return this.partitionVals != null;
   }

   public void setPartitionValsIsSet(boolean value) {
      if (!value) {
         this.partitionVals = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SUCCESSFUL:
            if (value == null) {
               this.unsetSuccessful();
            } else {
               this.setSuccessful((Boolean)value);
            }
            break;
         case DATA:
            if (value == null) {
               this.unsetData();
            } else {
               this.setData((FireEventRequestData)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case PARTITION_VALS:
            if (value == null) {
               this.unsetPartitionVals();
            } else {
               this.setPartitionVals((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SUCCESSFUL:
            return this.isSuccessful();
         case DATA:
            return this.getData();
         case DB_NAME:
            return this.getDbName();
         case TABLE_NAME:
            return this.getTableName();
         case PARTITION_VALS:
            return this.getPartitionVals();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SUCCESSFUL:
               return this.isSetSuccessful();
            case DATA:
               return this.isSetData();
            case DB_NAME:
               return this.isSetDbName();
            case TABLE_NAME:
               return this.isSetTableName();
            case PARTITION_VALS:
               return this.isSetPartitionVals();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof FireEventRequest ? this.equals((FireEventRequest)that) : false;
   }

   public boolean equals(FireEventRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_successful = true;
         boolean that_present_successful = true;
         if (this_present_successful || that_present_successful) {
            if (!this_present_successful || !that_present_successful) {
               return false;
            }

            if (this.successful != that.successful) {
               return false;
            }
         }

         boolean this_present_data = this.isSetData();
         boolean that_present_data = that.isSetData();
         if (this_present_data || that_present_data) {
            if (!this_present_data || !that_present_data) {
               return false;
            }

            if (!this.data.equals(that.data)) {
               return false;
            }
         }

         boolean this_present_dbName = this.isSetDbName();
         boolean that_present_dbName = that.isSetDbName();
         if (this_present_dbName || that_present_dbName) {
            if (!this_present_dbName || !that_present_dbName) {
               return false;
            }

            if (!this.dbName.equals(that.dbName)) {
               return false;
            }
         }

         boolean this_present_tableName = this.isSetTableName();
         boolean that_present_tableName = that.isSetTableName();
         if (this_present_tableName || that_present_tableName) {
            if (!this_present_tableName || !that_present_tableName) {
               return false;
            }

            if (!this.tableName.equals(that.tableName)) {
               return false;
            }
         }

         boolean this_present_partitionVals = this.isSetPartitionVals();
         boolean that_present_partitionVals = that.isSetPartitionVals();
         if (this_present_partitionVals || that_present_partitionVals) {
            if (!this_present_partitionVals || !that_present_partitionVals) {
               return false;
            }

            if (!this.partitionVals.equals(that.partitionVals)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.successful ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.isSetData() ? 131071 : 524287);
      if (this.isSetData()) {
         hashCode = hashCode * 8191 + this.data.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionVals() ? 131071 : 524287);
      if (this.isSetPartitionVals()) {
         hashCode = hashCode * 8191 + this.partitionVals.hashCode();
      }

      return hashCode;
   }

   public int compareTo(FireEventRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSuccessful(), other.isSetSuccessful());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSuccessful()) {
               lastComparison = TBaseHelper.compareTo(this.successful, other.successful);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetData(), other.isSetData());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetData()) {
                  lastComparison = TBaseHelper.compareTo(this.data, other.data);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetDbName(), other.isSetDbName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetDbName()) {
                     lastComparison = TBaseHelper.compareTo(this.dbName, other.dbName);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetTableName(), other.isSetTableName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetTableName()) {
                        lastComparison = TBaseHelper.compareTo(this.tableName, other.tableName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetPartitionVals(), other.isSetPartitionVals());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetPartitionVals()) {
                           lastComparison = TBaseHelper.compareTo(this.partitionVals, other.partitionVals);
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
      return FireEventRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("FireEventRequest(");
      boolean first = true;
      sb.append("successful:");
      sb.append(this.successful);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("data:");
      if (this.data == null) {
         sb.append("null");
      } else {
         sb.append(this.data);
      }

      first = false;
      if (this.isSetDbName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("dbName:");
         if (this.dbName == null) {
            sb.append("null");
         } else {
            sb.append(this.dbName);
         }

         first = false;
      }

      if (this.isSetTableName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("tableName:");
         if (this.tableName == null) {
            sb.append("null");
         } else {
            sb.append(this.tableName);
         }

         first = false;
      }

      if (this.isSetPartitionVals()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partitionVals:");
         if (this.partitionVals == null) {
            sb.append("null");
         } else {
            sb.append(this.partitionVals);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSuccessful()) {
         throw new TProtocolException("Required field 'successful' is unset! Struct:" + this.toString());
      } else if (!this.isSetData()) {
         throw new TProtocolException("Required field 'data' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{FireEventRequest._Fields.DB_NAME, FireEventRequest._Fields.TABLE_NAME, FireEventRequest._Fields.PARTITION_VALS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(FireEventRequest._Fields.SUCCESSFUL, new FieldMetaData("successful", (byte)1, new FieldValueMetaData((byte)2)));
      tmpMap.put(FireEventRequest._Fields.DATA, new FieldMetaData("data", (byte)1, new StructMetaData((byte)12, FireEventRequestData.class)));
      tmpMap.put(FireEventRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(FireEventRequest._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(FireEventRequest._Fields.PARTITION_VALS, new FieldMetaData("partitionVals", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(FireEventRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SUCCESSFUL((short)1, "successful"),
      DATA((short)2, "data"),
      DB_NAME((short)3, "dbName"),
      TABLE_NAME((short)4, "tableName"),
      PARTITION_VALS((short)5, "partitionVals");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SUCCESSFUL;
            case 2:
               return DATA;
            case 3:
               return DB_NAME;
            case 4:
               return TABLE_NAME;
            case 5:
               return PARTITION_VALS;
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

   private static class FireEventRequestStandardSchemeFactory implements SchemeFactory {
      private FireEventRequestStandardSchemeFactory() {
      }

      public FireEventRequestStandardScheme getScheme() {
         return new FireEventRequestStandardScheme();
      }
   }

   private static class FireEventRequestStandardScheme extends StandardScheme {
      private FireEventRequestStandardScheme() {
      }

      public void read(TProtocol iprot, FireEventRequest struct) throws TException {
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
                  if (schemeField.type == 2) {
                     struct.successful = iprot.readBool();
                     struct.setSuccessfulIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.data = new FireEventRequestData();
                     struct.data.read(iprot);
                     struct.setDataIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list606 = iprot.readListBegin();
                  struct.partitionVals = new ArrayList(_list606.size);

                  for(int _i608 = 0; _i608 < _list606.size; ++_i608) {
                     String _elem607 = iprot.readString();
                     struct.partitionVals.add(_elem607);
                  }

                  iprot.readListEnd();
                  struct.setPartitionValsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, FireEventRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(FireEventRequest.STRUCT_DESC);
         oprot.writeFieldBegin(FireEventRequest.SUCCESSFUL_FIELD_DESC);
         oprot.writeBool(struct.successful);
         oprot.writeFieldEnd();
         if (struct.data != null) {
            oprot.writeFieldBegin(FireEventRequest.DATA_FIELD_DESC);
            struct.data.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null && struct.isSetDbName()) {
            oprot.writeFieldBegin(FireEventRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null && struct.isSetTableName()) {
            oprot.writeFieldBegin(FireEventRequest.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.partitionVals != null && struct.isSetPartitionVals()) {
            oprot.writeFieldBegin(FireEventRequest.PARTITION_VALS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.partitionVals.size()));

            for(String _iter609 : struct.partitionVals) {
               oprot.writeString(_iter609);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class FireEventRequestTupleSchemeFactory implements SchemeFactory {
      private FireEventRequestTupleSchemeFactory() {
      }

      public FireEventRequestTupleScheme getScheme() {
         return new FireEventRequestTupleScheme();
      }
   }

   private static class FireEventRequestTupleScheme extends TupleScheme {
      private FireEventRequestTupleScheme() {
      }

      public void write(TProtocol prot, FireEventRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBool(struct.successful);
         struct.data.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetDbName()) {
            optionals.set(0);
         }

         if (struct.isSetTableName()) {
            optionals.set(1);
         }

         if (struct.isSetPartitionVals()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetPartitionVals()) {
            oprot.writeI32(struct.partitionVals.size());

            for(String _iter610 : struct.partitionVals) {
               oprot.writeString(_iter610);
            }
         }

      }

      public void read(TProtocol prot, FireEventRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.successful = iprot.readBool();
         struct.setSuccessfulIsSet(true);
         struct.data = new FireEventRequestData();
         struct.data.read(iprot);
         struct.setDataIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(2)) {
            TList _list611 = iprot.readListBegin((byte)11);
            struct.partitionVals = new ArrayList(_list611.size);

            for(int _i613 = 0; _i613 < _list611.size; ++_i613) {
               String _elem612 = iprot.readString();
               struct.partitionVals.add(_elem612);
            }

            struct.setPartitionValsIsSet(true);
         }

      }
   }
}
