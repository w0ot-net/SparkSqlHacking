package org.apache.hadoop.hive.metastore.api;

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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class CacheFileMetadataRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CacheFileMetadataRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField PART_NAME_FIELD_DESC = new TField("partName", (byte)11, (short)3);
   private static final TField IS_ALL_PARTS_FIELD_DESC = new TField("isAllParts", (byte)2, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CacheFileMetadataRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CacheFileMetadataRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private String partName;
   private boolean isAllParts;
   private static final int __ISALLPARTS_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public CacheFileMetadataRequest() {
      this.__isset_bitfield = 0;
   }

   public CacheFileMetadataRequest(String dbName, String tblName) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
   }

   public CacheFileMetadataRequest(CacheFileMetadataRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetPartName()) {
         this.partName = other.partName;
      }

      this.isAllParts = other.isAllParts;
   }

   public CacheFileMetadataRequest deepCopy() {
      return new CacheFileMetadataRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.partName = null;
      this.setIsAllPartsIsSet(false);
      this.isAllParts = false;
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
   public String getTblName() {
      return this.tblName;
   }

   public void setTblName(@Nullable String tblName) {
      this.tblName = tblName;
   }

   public void unsetTblName() {
      this.tblName = null;
   }

   public boolean isSetTblName() {
      return this.tblName != null;
   }

   public void setTblNameIsSet(boolean value) {
      if (!value) {
         this.tblName = null;
      }

   }

   @Nullable
   public String getPartName() {
      return this.partName;
   }

   public void setPartName(@Nullable String partName) {
      this.partName = partName;
   }

   public void unsetPartName() {
      this.partName = null;
   }

   public boolean isSetPartName() {
      return this.partName != null;
   }

   public void setPartNameIsSet(boolean value) {
      if (!value) {
         this.partName = null;
      }

   }

   public boolean isIsAllParts() {
      return this.isAllParts;
   }

   public void setIsAllParts(boolean isAllParts) {
      this.isAllParts = isAllParts;
      this.setIsAllPartsIsSet(true);
   }

   public void unsetIsAllParts() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIsAllParts() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIsAllPartsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TBL_NAME:
            if (value == null) {
               this.unsetTblName();
            } else {
               this.setTblName((String)value);
            }
            break;
         case PART_NAME:
            if (value == null) {
               this.unsetPartName();
            } else {
               this.setPartName((String)value);
            }
            break;
         case IS_ALL_PARTS:
            if (value == null) {
               this.unsetIsAllParts();
            } else {
               this.setIsAllParts((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DB_NAME:
            return this.getDbName();
         case TBL_NAME:
            return this.getTblName();
         case PART_NAME:
            return this.getPartName();
         case IS_ALL_PARTS:
            return this.isIsAllParts();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case DB_NAME:
               return this.isSetDbName();
            case TBL_NAME:
               return this.isSetTblName();
            case PART_NAME:
               return this.isSetPartName();
            case IS_ALL_PARTS:
               return this.isSetIsAllParts();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CacheFileMetadataRequest ? this.equals((CacheFileMetadataRequest)that) : false;
   }

   public boolean equals(CacheFileMetadataRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_tblName = this.isSetTblName();
         boolean that_present_tblName = that.isSetTblName();
         if (this_present_tblName || that_present_tblName) {
            if (!this_present_tblName || !that_present_tblName) {
               return false;
            }

            if (!this.tblName.equals(that.tblName)) {
               return false;
            }
         }

         boolean this_present_partName = this.isSetPartName();
         boolean that_present_partName = that.isSetPartName();
         if (this_present_partName || that_present_partName) {
            if (!this_present_partName || !that_present_partName) {
               return false;
            }

            if (!this.partName.equals(that.partName)) {
               return false;
            }
         }

         boolean this_present_isAllParts = this.isSetIsAllParts();
         boolean that_present_isAllParts = that.isSetIsAllParts();
         if (this_present_isAllParts || that_present_isAllParts) {
            if (!this_present_isAllParts || !that_present_isAllParts) {
               return false;
            }

            if (this.isAllParts != that.isAllParts) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTblName() ? 131071 : 524287);
      if (this.isSetTblName()) {
         hashCode = hashCode * 8191 + this.tblName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartName() ? 131071 : 524287);
      if (this.isSetPartName()) {
         hashCode = hashCode * 8191 + this.partName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetIsAllParts() ? 131071 : 524287);
      if (this.isSetIsAllParts()) {
         hashCode = hashCode * 8191 + (this.isAllParts ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(CacheFileMetadataRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

            lastComparison = Boolean.compare(this.isSetTblName(), other.isSetTblName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTblName()) {
                  lastComparison = TBaseHelper.compareTo(this.tblName, other.tblName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPartName(), other.isSetPartName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPartName()) {
                     lastComparison = TBaseHelper.compareTo(this.partName, other.partName);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetIsAllParts(), other.isSetIsAllParts());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetIsAllParts()) {
                        lastComparison = TBaseHelper.compareTo(this.isAllParts, other.isAllParts);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return CacheFileMetadataRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CacheFileMetadataRequest(");
      boolean first = true;
      sb.append("dbName:");
      if (this.dbName == null) {
         sb.append("null");
      } else {
         sb.append(this.dbName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tblName:");
      if (this.tblName == null) {
         sb.append("null");
      } else {
         sb.append(this.tblName);
      }

      first = false;
      if (this.isSetPartName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partName:");
         if (this.partName == null) {
            sb.append("null");
         } else {
            sb.append(this.partName);
         }

         first = false;
      }

      if (this.isSetIsAllParts()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("isAllParts:");
         sb.append(this.isAllParts);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTblName()) {
         throw new TProtocolException("Required field 'tblName' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{CacheFileMetadataRequest._Fields.PART_NAME, CacheFileMetadataRequest._Fields.IS_ALL_PARTS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(CacheFileMetadataRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(CacheFileMetadataRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(CacheFileMetadataRequest._Fields.PART_NAME, new FieldMetaData("partName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(CacheFileMetadataRequest._Fields.IS_ALL_PARTS, new FieldMetaData("isAllParts", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CacheFileMetadataRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      PART_NAME((short)3, "partName"),
      IS_ALL_PARTS((short)4, "isAllParts");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TBL_NAME;
            case 3:
               return PART_NAME;
            case 4:
               return IS_ALL_PARTS;
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

   private static class CacheFileMetadataRequestStandardSchemeFactory implements SchemeFactory {
      private CacheFileMetadataRequestStandardSchemeFactory() {
      }

      public CacheFileMetadataRequestStandardScheme getScheme() {
         return new CacheFileMetadataRequestStandardScheme();
      }
   }

   private static class CacheFileMetadataRequestStandardScheme extends StandardScheme {
      private CacheFileMetadataRequestStandardScheme() {
      }

      public void read(TProtocol iprot, CacheFileMetadataRequest struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tblName = iprot.readString();
                     struct.setTblNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.partName = iprot.readString();
                     struct.setPartNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 2) {
                     struct.isAllParts = iprot.readBool();
                     struct.setIsAllPartsIsSet(true);
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

      public void write(TProtocol oprot, CacheFileMetadataRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CacheFileMetadataRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(CacheFileMetadataRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(CacheFileMetadataRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.partName != null && struct.isSetPartName()) {
            oprot.writeFieldBegin(CacheFileMetadataRequest.PART_NAME_FIELD_DESC);
            oprot.writeString(struct.partName);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIsAllParts()) {
            oprot.writeFieldBegin(CacheFileMetadataRequest.IS_ALL_PARTS_FIELD_DESC);
            oprot.writeBool(struct.isAllParts);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CacheFileMetadataRequestTupleSchemeFactory implements SchemeFactory {
      private CacheFileMetadataRequestTupleSchemeFactory() {
      }

      public CacheFileMetadataRequestTupleScheme getScheme() {
         return new CacheFileMetadataRequestTupleScheme();
      }
   }

   private static class CacheFileMetadataRequestTupleScheme extends TupleScheme {
      private CacheFileMetadataRequestTupleScheme() {
      }

      public void write(TProtocol prot, CacheFileMetadataRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         BitSet optionals = new BitSet();
         if (struct.isSetPartName()) {
            optionals.set(0);
         }

         if (struct.isSetIsAllParts()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetPartName()) {
            oprot.writeString(struct.partName);
         }

         if (struct.isSetIsAllParts()) {
            oprot.writeBool(struct.isAllParts);
         }

      }

      public void read(TProtocol prot, CacheFileMetadataRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.partName = iprot.readString();
            struct.setPartNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.isAllParts = iprot.readBool();
            struct.setIsAllPartsIsSet(true);
         }

      }
   }
}
