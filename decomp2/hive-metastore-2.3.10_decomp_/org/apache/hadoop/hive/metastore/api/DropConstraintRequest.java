package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
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

public class DropConstraintRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DropConstraintRequest");
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)1);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)2);
   private static final TField CONSTRAINTNAME_FIELD_DESC = new TField("constraintname", (byte)11, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DropConstraintRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DropConstraintRequestTupleSchemeFactory();
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private String constraintname;
   public static final Map metaDataMap;

   public DropConstraintRequest() {
   }

   public DropConstraintRequest(String dbname, String tablename, String constraintname) {
      this();
      this.dbname = dbname;
      this.tablename = tablename;
      this.constraintname = constraintname;
   }

   public DropConstraintRequest(DropConstraintRequest other) {
      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetConstraintname()) {
         this.constraintname = other.constraintname;
      }

   }

   public DropConstraintRequest deepCopy() {
      return new DropConstraintRequest(this);
   }

   public void clear() {
      this.dbname = null;
      this.tablename = null;
      this.constraintname = null;
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
   public String getConstraintname() {
      return this.constraintname;
   }

   public void setConstraintname(@Nullable String constraintname) {
      this.constraintname = constraintname;
   }

   public void unsetConstraintname() {
      this.constraintname = null;
   }

   public boolean isSetConstraintname() {
      return this.constraintname != null;
   }

   public void setConstraintnameIsSet(boolean value) {
      if (!value) {
         this.constraintname = null;
      }

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
         case CONSTRAINTNAME:
            if (value == null) {
               this.unsetConstraintname();
            } else {
               this.setConstraintname((String)value);
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
         case CONSTRAINTNAME:
            return this.getConstraintname();
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
            case CONSTRAINTNAME:
               return this.isSetConstraintname();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DropConstraintRequest ? this.equals((DropConstraintRequest)that) : false;
   }

   public boolean equals(DropConstraintRequest that) {
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

         boolean this_present_constraintname = this.isSetConstraintname();
         boolean that_present_constraintname = that.isSetConstraintname();
         if (this_present_constraintname || that_present_constraintname) {
            if (!this_present_constraintname || !that_present_constraintname) {
               return false;
            }

            if (!this.constraintname.equals(that.constraintname)) {
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

      hashCode = hashCode * 8191 + (this.isSetConstraintname() ? 131071 : 524287);
      if (this.isSetConstraintname()) {
         hashCode = hashCode * 8191 + this.constraintname.hashCode();
      }

      return hashCode;
   }

   public int compareTo(DropConstraintRequest other) {
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

               lastComparison = Boolean.compare(this.isSetConstraintname(), other.isSetConstraintname());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetConstraintname()) {
                     lastComparison = TBaseHelper.compareTo(this.constraintname, other.constraintname);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return DropConstraintRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DropConstraintRequest(");
      boolean first = true;
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

      sb.append("constraintname:");
      if (this.constraintname == null) {
         sb.append("null");
      } else {
         sb.append(this.constraintname);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbname()) {
         throw new TProtocolException("Required field 'dbname' is unset! Struct:" + this.toString());
      } else if (!this.isSetTablename()) {
         throw new TProtocolException("Required field 'tablename' is unset! Struct:" + this.toString());
      } else if (!this.isSetConstraintname()) {
         throw new TProtocolException("Required field 'constraintname' is unset! Struct:" + this.toString());
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
      tmpMap.put(DropConstraintRequest._Fields.DBNAME, new FieldMetaData("dbname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(DropConstraintRequest._Fields.TABLENAME, new FieldMetaData("tablename", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(DropConstraintRequest._Fields.CONSTRAINTNAME, new FieldMetaData("constraintname", (byte)1, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DropConstraintRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DBNAME((short)1, "dbname"),
      TABLENAME((short)2, "tablename"),
      CONSTRAINTNAME((short)3, "constraintname");

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
               return CONSTRAINTNAME;
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

   private static class DropConstraintRequestStandardSchemeFactory implements SchemeFactory {
      private DropConstraintRequestStandardSchemeFactory() {
      }

      public DropConstraintRequestStandardScheme getScheme() {
         return new DropConstraintRequestStandardScheme();
      }
   }

   private static class DropConstraintRequestStandardScheme extends StandardScheme {
      private DropConstraintRequestStandardScheme() {
      }

      public void read(TProtocol iprot, DropConstraintRequest struct) throws TException {
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
                     struct.constraintname = iprot.readString();
                     struct.setConstraintnameIsSet(true);
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

      public void write(TProtocol oprot, DropConstraintRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DropConstraintRequest.STRUCT_DESC);
         if (struct.dbname != null) {
            oprot.writeFieldBegin(DropConstraintRequest.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null) {
            oprot.writeFieldBegin(DropConstraintRequest.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.constraintname != null) {
            oprot.writeFieldBegin(DropConstraintRequest.CONSTRAINTNAME_FIELD_DESC);
            oprot.writeString(struct.constraintname);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DropConstraintRequestTupleSchemeFactory implements SchemeFactory {
      private DropConstraintRequestTupleSchemeFactory() {
      }

      public DropConstraintRequestTupleScheme getScheme() {
         return new DropConstraintRequestTupleScheme();
      }
   }

   private static class DropConstraintRequestTupleScheme extends TupleScheme {
      private DropConstraintRequestTupleScheme() {
      }

      public void write(TProtocol prot, DropConstraintRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbname);
         oprot.writeString(struct.tablename);
         oprot.writeString(struct.constraintname);
      }

      public void read(TProtocol prot, DropConstraintRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbname = iprot.readString();
         struct.setDbnameIsSet(true);
         struct.tablename = iprot.readString();
         struct.setTablenameIsSet(true);
         struct.constraintname = iprot.readString();
         struct.setConstraintnameIsSet(true);
      }
   }
}
