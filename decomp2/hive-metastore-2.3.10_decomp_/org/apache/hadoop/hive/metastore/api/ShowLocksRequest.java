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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class ShowLocksRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ShowLocksRequest");
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)1);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)2);
   private static final TField PARTNAME_FIELD_DESC = new TField("partname", (byte)11, (short)3);
   private static final TField IS_EXTENDED_FIELD_DESC = new TField("isExtended", (byte)2, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ShowLocksRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ShowLocksRequestTupleSchemeFactory();
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private String partname;
   private boolean isExtended;
   private static final int __ISEXTENDED_ISSET_ID = 0;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ShowLocksRequest() {
      this.isExtended = false;
   }

   public ShowLocksRequest(ShowLocksRequest other) {
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetPartname()) {
         this.partname = other.partname;
      }

      this.isExtended = other.isExtended;
   }

   public ShowLocksRequest deepCopy() {
      return new ShowLocksRequest(this);
   }

   public void clear() {
      this.dbname = null;
      this.tablename = null;
      this.partname = null;
      this.isExtended = false;
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

   @Nullable
   public String getPartname() {
      return this.partname;
   }

   public void setPartname(@Nullable String partname) {
      this.partname = partname;
   }

   public void unsetPartname() {
      this.partname = null;
   }

   public boolean isSetPartname() {
      return this.partname != null;
   }

   public void setPartnameIsSet(boolean value) {
      if (!value) {
         this.partname = null;
      }

   }

   public boolean isIsExtended() {
      return this.isExtended;
   }

   public void setIsExtended(boolean isExtended) {
      this.isExtended = isExtended;
      this.setIsExtendedIsSet(true);
   }

   public void unsetIsExtended() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIsExtended() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIsExtendedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
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
         case PARTNAME:
            if (value == null) {
               this.unsetPartname();
            } else {
               this.setPartname((String)value);
            }
            break;
         case IS_EXTENDED:
            if (value == null) {
               this.unsetIsExtended();
            } else {
               this.setIsExtended((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DBNAME:
            return this.getDbname();
         case TABLENAME:
            return this.getTablename();
         case PARTNAME:
            return this.getPartname();
         case IS_EXTENDED:
            return this.isIsExtended();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case DBNAME:
               return this.isSetDbname();
            case TABLENAME:
               return this.isSetTablename();
            case PARTNAME:
               return this.isSetPartname();
            case IS_EXTENDED:
               return this.isSetIsExtended();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ShowLocksRequest ? this.equals((ShowLocksRequest)that) : false;
   }

   public boolean equals(ShowLocksRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_partname = this.isSetPartname();
         boolean that_present_partname = that.isSetPartname();
         if (this_present_partname || that_present_partname) {
            if (!this_present_partname || !that_present_partname) {
               return false;
            }

            if (!this.partname.equals(that.partname)) {
               return false;
            }
         }

         boolean this_present_isExtended = this.isSetIsExtended();
         boolean that_present_isExtended = that.isSetIsExtended();
         if (this_present_isExtended || that_present_isExtended) {
            if (!this_present_isExtended || !that_present_isExtended) {
               return false;
            }

            if (this.isExtended != that.isExtended) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetDbname() ? 131071 : 524287);
      if (this.isSetDbname()) {
         hashCode = hashCode * 8191 + this.dbname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTablename() ? 131071 : 524287);
      if (this.isSetTablename()) {
         hashCode = hashCode * 8191 + this.tablename.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartname() ? 131071 : 524287);
      if (this.isSetPartname()) {
         hashCode = hashCode * 8191 + this.partname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetIsExtended() ? 131071 : 524287);
      if (this.isSetIsExtended()) {
         hashCode = hashCode * 8191 + (this.isExtended ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(ShowLocksRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

               lastComparison = Boolean.compare(this.isSetPartname(), other.isSetPartname());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPartname()) {
                     lastComparison = TBaseHelper.compareTo(this.partname, other.partname);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetIsExtended(), other.isSetIsExtended());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetIsExtended()) {
                        lastComparison = TBaseHelper.compareTo(this.isExtended, other.isExtended);
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
      return ShowLocksRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ShowLocksRequest(");
      boolean first = true;
      if (this.isSetDbname()) {
         sb.append("dbname:");
         if (this.dbname == null) {
            sb.append("null");
         } else {
            sb.append(this.dbname);
         }

         first = false;
      }

      if (this.isSetTablename()) {
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
      }

      if (this.isSetPartname()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partname:");
         if (this.partname == null) {
            sb.append("null");
         } else {
            sb.append(this.partname);
         }

         first = false;
      }

      if (this.isSetIsExtended()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("isExtended:");
         sb.append(this.isExtended);
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
      optionals = new _Fields[]{ShowLocksRequest._Fields.DBNAME, ShowLocksRequest._Fields.TABLENAME, ShowLocksRequest._Fields.PARTNAME, ShowLocksRequest._Fields.IS_EXTENDED};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ShowLocksRequest._Fields.DBNAME, new FieldMetaData("dbname", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksRequest._Fields.TABLENAME, new FieldMetaData("tablename", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksRequest._Fields.PARTNAME, new FieldMetaData("partname", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksRequest._Fields.IS_EXTENDED, new FieldMetaData("isExtended", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ShowLocksRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DBNAME((short)1, "dbname"),
      TABLENAME((short)2, "tablename"),
      PARTNAME((short)3, "partname"),
      IS_EXTENDED((short)4, "isExtended");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DBNAME;
            case 2:
               return TABLENAME;
            case 3:
               return PARTNAME;
            case 4:
               return IS_EXTENDED;
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

   private static class ShowLocksRequestStandardSchemeFactory implements SchemeFactory {
      private ShowLocksRequestStandardSchemeFactory() {
      }

      public ShowLocksRequestStandardScheme getScheme() {
         return new ShowLocksRequestStandardScheme();
      }
   }

   private static class ShowLocksRequestStandardScheme extends StandardScheme {
      private ShowLocksRequestStandardScheme() {
      }

      public void read(TProtocol iprot, ShowLocksRequest struct) throws TException {
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
                     struct.dbname = iprot.readString();
                     struct.setDbnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tablename = iprot.readString();
                     struct.setTablenameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.partname = iprot.readString();
                     struct.setPartnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 2) {
                     struct.isExtended = iprot.readBool();
                     struct.setIsExtendedIsSet(true);
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

      public void write(TProtocol oprot, ShowLocksRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ShowLocksRequest.STRUCT_DESC);
         if (struct.dbname != null && struct.isSetDbname()) {
            oprot.writeFieldBegin(ShowLocksRequest.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null && struct.isSetTablename()) {
            oprot.writeFieldBegin(ShowLocksRequest.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.partname != null && struct.isSetPartname()) {
            oprot.writeFieldBegin(ShowLocksRequest.PARTNAME_FIELD_DESC);
            oprot.writeString(struct.partname);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIsExtended()) {
            oprot.writeFieldBegin(ShowLocksRequest.IS_EXTENDED_FIELD_DESC);
            oprot.writeBool(struct.isExtended);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ShowLocksRequestTupleSchemeFactory implements SchemeFactory {
      private ShowLocksRequestTupleSchemeFactory() {
      }

      public ShowLocksRequestTupleScheme getScheme() {
         return new ShowLocksRequestTupleScheme();
      }
   }

   private static class ShowLocksRequestTupleScheme extends TupleScheme {
      private ShowLocksRequestTupleScheme() {
      }

      public void write(TProtocol prot, ShowLocksRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetDbname()) {
            optionals.set(0);
         }

         if (struct.isSetTablename()) {
            optionals.set(1);
         }

         if (struct.isSetPartname()) {
            optionals.set(2);
         }

         if (struct.isSetIsExtended()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetDbname()) {
            oprot.writeString(struct.dbname);
         }

         if (struct.isSetTablename()) {
            oprot.writeString(struct.tablename);
         }

         if (struct.isSetPartname()) {
            oprot.writeString(struct.partname);
         }

         if (struct.isSetIsExtended()) {
            oprot.writeBool(struct.isExtended);
         }

      }

      public void read(TProtocol prot, ShowLocksRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            struct.dbname = iprot.readString();
            struct.setDbnameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.tablename = iprot.readString();
            struct.setTablenameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.partname = iprot.readString();
            struct.setPartnameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.isExtended = iprot.readBool();
            struct.setIsExtendedIsSet(true);
         }

      }
   }
}
