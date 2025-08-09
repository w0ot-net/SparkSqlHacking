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

public class TableMeta implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TableMeta");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)2);
   private static final TField TABLE_TYPE_FIELD_DESC = new TField("tableType", (byte)11, (short)3);
   private static final TField COMMENTS_FIELD_DESC = new TField("comments", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TableMetaStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TableMetaTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tableName;
   @Nullable
   private String tableType;
   @Nullable
   private String comments;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TableMeta() {
   }

   public TableMeta(String dbName, String tableName, String tableType) {
      this();
      this.dbName = dbName;
      this.tableName = tableName;
      this.tableType = tableType;
   }

   public TableMeta(TableMeta other) {
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetTableType()) {
         this.tableType = other.tableType;
      }

      if (other.isSetComments()) {
         this.comments = other.comments;
      }

   }

   public TableMeta deepCopy() {
      return new TableMeta(this);
   }

   public void clear() {
      this.dbName = null;
      this.tableName = null;
      this.tableType = null;
      this.comments = null;
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

   @Nullable
   public String getTableType() {
      return this.tableType;
   }

   public void setTableType(@Nullable String tableType) {
      this.tableType = tableType;
   }

   public void unsetTableType() {
      this.tableType = null;
   }

   public boolean isSetTableType() {
      return this.tableType != null;
   }

   public void setTableTypeIsSet(boolean value) {
      if (!value) {
         this.tableType = null;
      }

   }

   @Nullable
   public String getComments() {
      return this.comments;
   }

   public void setComments(@Nullable String comments) {
      this.comments = comments;
   }

   public void unsetComments() {
      this.comments = null;
   }

   public boolean isSetComments() {
      return this.comments != null;
   }

   public void setCommentsIsSet(boolean value) {
      if (!value) {
         this.comments = null;
      }

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
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case TABLE_TYPE:
            if (value == null) {
               this.unsetTableType();
            } else {
               this.setTableType((String)value);
            }
            break;
         case COMMENTS:
            if (value == null) {
               this.unsetComments();
            } else {
               this.setComments((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DB_NAME:
            return this.getDbName();
         case TABLE_NAME:
            return this.getTableName();
         case TABLE_TYPE:
            return this.getTableType();
         case COMMENTS:
            return this.getComments();
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
            case TABLE_NAME:
               return this.isSetTableName();
            case TABLE_TYPE:
               return this.isSetTableType();
            case COMMENTS:
               return this.isSetComments();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TableMeta ? this.equals((TableMeta)that) : false;
   }

   public boolean equals(TableMeta that) {
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

         boolean this_present_tableType = this.isSetTableType();
         boolean that_present_tableType = that.isSetTableType();
         if (this_present_tableType || that_present_tableType) {
            if (!this_present_tableType || !that_present_tableType) {
               return false;
            }

            if (!this.tableType.equals(that.tableType)) {
               return false;
            }
         }

         boolean this_present_comments = this.isSetComments();
         boolean that_present_comments = that.isSetComments();
         if (this_present_comments || that_present_comments) {
            if (!this_present_comments || !that_present_comments) {
               return false;
            }

            if (!this.comments.equals(that.comments)) {
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

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableType() ? 131071 : 524287);
      if (this.isSetTableType()) {
         hashCode = hashCode * 8191 + this.tableType.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetComments() ? 131071 : 524287);
      if (this.isSetComments()) {
         hashCode = hashCode * 8191 + this.comments.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TableMeta other) {
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

               lastComparison = Boolean.compare(this.isSetTableType(), other.isSetTableType());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetTableType()) {
                     lastComparison = TBaseHelper.compareTo(this.tableType, other.tableType);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetComments(), other.isSetComments());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetComments()) {
                        lastComparison = TBaseHelper.compareTo(this.comments, other.comments);
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
      return TableMeta._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TableMeta(");
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

      sb.append("tableName:");
      if (this.tableName == null) {
         sb.append("null");
      } else {
         sb.append(this.tableName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tableType:");
      if (this.tableType == null) {
         sb.append("null");
      } else {
         sb.append(this.tableType);
      }

      first = false;
      if (this.isSetComments()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("comments:");
         if (this.comments == null) {
            sb.append("null");
         } else {
            sb.append(this.comments);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTableName()) {
         throw new TProtocolException("Required field 'tableName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTableType()) {
         throw new TProtocolException("Required field 'tableType' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TableMeta._Fields.COMMENTS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TableMeta._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TableMeta._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TableMeta._Fields.TABLE_TYPE, new FieldMetaData("tableType", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TableMeta._Fields.COMMENTS, new FieldMetaData("comments", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TableMeta.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TABLE_NAME((short)2, "tableName"),
      TABLE_TYPE((short)3, "tableType"),
      COMMENTS((short)4, "comments");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TABLE_NAME;
            case 3:
               return TABLE_TYPE;
            case 4:
               return COMMENTS;
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

   private static class TableMetaStandardSchemeFactory implements SchemeFactory {
      private TableMetaStandardSchemeFactory() {
      }

      public TableMetaStandardScheme getScheme() {
         return new TableMetaStandardScheme();
      }
   }

   private static class TableMetaStandardScheme extends StandardScheme {
      private TableMetaStandardScheme() {
      }

      public void read(TProtocol iprot, TableMeta struct) throws TException {
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
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.tableType = iprot.readString();
                     struct.setTableTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.comments = iprot.readString();
                     struct.setCommentsIsSet(true);
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

      public void write(TProtocol oprot, TableMeta struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TableMeta.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(TableMeta.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null) {
            oprot.writeFieldBegin(TableMeta.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.tableType != null) {
            oprot.writeFieldBegin(TableMeta.TABLE_TYPE_FIELD_DESC);
            oprot.writeString(struct.tableType);
            oprot.writeFieldEnd();
         }

         if (struct.comments != null && struct.isSetComments()) {
            oprot.writeFieldBegin(TableMeta.COMMENTS_FIELD_DESC);
            oprot.writeString(struct.comments);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TableMetaTupleSchemeFactory implements SchemeFactory {
      private TableMetaTupleSchemeFactory() {
      }

      public TableMetaTupleScheme getScheme() {
         return new TableMetaTupleScheme();
      }
   }

   private static class TableMetaTupleScheme extends TupleScheme {
      private TableMetaTupleScheme() {
      }

      public void write(TProtocol prot, TableMeta struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tableName);
         oprot.writeString(struct.tableType);
         BitSet optionals = new BitSet();
         if (struct.isSetComments()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetComments()) {
            oprot.writeString(struct.comments);
         }

      }

      public void read(TProtocol prot, TableMeta struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tableName = iprot.readString();
         struct.setTableNameIsSet(true);
         struct.tableType = iprot.readString();
         struct.setTableTypeIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.comments = iprot.readString();
            struct.setCommentsIsSet(true);
         }

      }
   }
}
