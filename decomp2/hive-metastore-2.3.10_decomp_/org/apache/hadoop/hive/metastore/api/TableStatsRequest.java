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
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
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

public class TableStatsRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TableStatsRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField COL_NAMES_FIELD_DESC = new TField("colNames", (byte)15, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TableStatsRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TableStatsRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private List colNames;
   public static final Map metaDataMap;

   public TableStatsRequest() {
   }

   public TableStatsRequest(String dbName, String tblName, List colNames) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
      this.colNames = colNames;
   }

   public TableStatsRequest(TableStatsRequest other) {
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetColNames()) {
         List<String> __this__colNames = new ArrayList(other.colNames);
         this.colNames = __this__colNames;
      }

   }

   public TableStatsRequest deepCopy() {
      return new TableStatsRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.colNames = null;
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

   public int getColNamesSize() {
      return this.colNames == null ? 0 : this.colNames.size();
   }

   @Nullable
   public Iterator getColNamesIterator() {
      return this.colNames == null ? null : this.colNames.iterator();
   }

   public void addToColNames(String elem) {
      if (this.colNames == null) {
         this.colNames = new ArrayList();
      }

      this.colNames.add(elem);
   }

   @Nullable
   public List getColNames() {
      return this.colNames;
   }

   public void setColNames(@Nullable List colNames) {
      this.colNames = colNames;
   }

   public void unsetColNames() {
      this.colNames = null;
   }

   public boolean isSetColNames() {
      return this.colNames != null;
   }

   public void setColNamesIsSet(boolean value) {
      if (!value) {
         this.colNames = null;
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
         case TBL_NAME:
            if (value == null) {
               this.unsetTblName();
            } else {
               this.setTblName((String)value);
            }
            break;
         case COL_NAMES:
            if (value == null) {
               this.unsetColNames();
            } else {
               this.setColNames((List)value);
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
         case COL_NAMES:
            return this.getColNames();
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
            case COL_NAMES:
               return this.isSetColNames();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TableStatsRequest ? this.equals((TableStatsRequest)that) : false;
   }

   public boolean equals(TableStatsRequest that) {
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

         boolean this_present_colNames = this.isSetColNames();
         boolean that_present_colNames = that.isSetColNames();
         if (this_present_colNames || that_present_colNames) {
            if (!this_present_colNames || !that_present_colNames) {
               return false;
            }

            if (!this.colNames.equals(that.colNames)) {
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

      hashCode = hashCode * 8191 + (this.isSetColNames() ? 131071 : 524287);
      if (this.isSetColNames()) {
         hashCode = hashCode * 8191 + this.colNames.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TableStatsRequest other) {
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

               lastComparison = Boolean.compare(this.isSetColNames(), other.isSetColNames());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetColNames()) {
                     lastComparison = TBaseHelper.compareTo(this.colNames, other.colNames);
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
      return TableStatsRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TableStatsRequest(");
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
      if (!first) {
         sb.append(", ");
      }

      sb.append("colNames:");
      if (this.colNames == null) {
         sb.append("null");
      } else {
         sb.append(this.colNames);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTblName()) {
         throw new TProtocolException("Required field 'tblName' is unset! Struct:" + this.toString());
      } else if (!this.isSetColNames()) {
         throw new TProtocolException("Required field 'colNames' is unset! Struct:" + this.toString());
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
      tmpMap.put(TableStatsRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TableStatsRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TableStatsRequest._Fields.COL_NAMES, new FieldMetaData("colNames", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TableStatsRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      COL_NAMES((short)3, "colNames");

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
               return COL_NAMES;
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

   private static class TableStatsRequestStandardSchemeFactory implements SchemeFactory {
      private TableStatsRequestStandardSchemeFactory() {
      }

      public TableStatsRequestStandardScheme getScheme() {
         return new TableStatsRequestStandardScheme();
      }
   }

   private static class TableStatsRequestStandardScheme extends StandardScheme {
      private TableStatsRequestStandardScheme() {
      }

      public void read(TProtocol iprot, TableStatsRequest struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list388 = iprot.readListBegin();
                  struct.colNames = new ArrayList(_list388.size);

                  for(int _i390 = 0; _i390 < _list388.size; ++_i390) {
                     String _elem389 = iprot.readString();
                     struct.colNames.add(_elem389);
                  }

                  iprot.readListEnd();
                  struct.setColNamesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TableStatsRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TableStatsRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(TableStatsRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(TableStatsRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.colNames != null) {
            oprot.writeFieldBegin(TableStatsRequest.COL_NAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.colNames.size()));

            for(String _iter391 : struct.colNames) {
               oprot.writeString(_iter391);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TableStatsRequestTupleSchemeFactory implements SchemeFactory {
      private TableStatsRequestTupleSchemeFactory() {
      }

      public TableStatsRequestTupleScheme getScheme() {
         return new TableStatsRequestTupleScheme();
      }
   }

   private static class TableStatsRequestTupleScheme extends TupleScheme {
      private TableStatsRequestTupleScheme() {
      }

      public void write(TProtocol prot, TableStatsRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         oprot.writeI32(struct.colNames.size());

         for(String _iter392 : struct.colNames) {
            oprot.writeString(_iter392);
         }

      }

      public void read(TProtocol prot, TableStatsRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         TList _list393 = iprot.readListBegin((byte)11);
         struct.colNames = new ArrayList(_list393.size);

         for(int _i395 = 0; _i395 < _list393.size; ++_i395) {
            String _elem394 = iprot.readString();
            struct.colNames.add(_elem394);
         }

         struct.setColNamesIsSet(true);
      }
   }
}
