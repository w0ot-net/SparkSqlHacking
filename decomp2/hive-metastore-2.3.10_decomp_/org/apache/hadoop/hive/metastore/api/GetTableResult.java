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
import org.apache.thrift.meta_data.StructMetaData;
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

public class GetTableResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetTableResult");
   private static final TField TABLE_FIELD_DESC = new TField("table", (byte)12, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTableResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTableResultTupleSchemeFactory();
   @Nullable
   private Table table;
   public static final Map metaDataMap;

   public GetTableResult() {
   }

   public GetTableResult(Table table) {
      this();
      this.table = table;
   }

   public GetTableResult(GetTableResult other) {
      if (other.isSetTable()) {
         this.table = new Table(other.table);
      }

   }

   public GetTableResult deepCopy() {
      return new GetTableResult(this);
   }

   public void clear() {
      this.table = null;
   }

   @Nullable
   public Table getTable() {
      return this.table;
   }

   public void setTable(@Nullable Table table) {
      this.table = table;
   }

   public void unsetTable() {
      this.table = null;
   }

   public boolean isSetTable() {
      return this.table != null;
   }

   public void setTableIsSet(boolean value) {
      if (!value) {
         this.table = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TABLE:
            if (value == null) {
               this.unsetTable();
            } else {
               this.setTable((Table)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TABLE:
            return this.getTable();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TABLE:
               return this.isSetTable();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetTableResult ? this.equals((GetTableResult)that) : false;
   }

   public boolean equals(GetTableResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_table = this.isSetTable();
         boolean that_present_table = that.isSetTable();
         if (this_present_table || that_present_table) {
            if (!this_present_table || !that_present_table) {
               return false;
            }

            if (!this.table.equals(that.table)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetTable() ? 131071 : 524287);
      if (this.isSetTable()) {
         hashCode = hashCode * 8191 + this.table.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetTableResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTable(), other.isSetTable());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTable()) {
               lastComparison = TBaseHelper.compareTo(this.table, other.table);
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
      return GetTableResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetTableResult(");
      boolean first = true;
      sb.append("table:");
      if (this.table == null) {
         sb.append("null");
      } else {
         sb.append(this.table);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTable()) {
         throw new TProtocolException("Required field 'table' is unset! Struct:" + this.toString());
      } else {
         if (this.table != null) {
            this.table.validate();
         }

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
      tmpMap.put(GetTableResult._Fields.TABLE, new FieldMetaData("table", (byte)1, new StructMetaData((byte)12, Table.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetTableResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TABLE((short)1, "table");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TABLE;
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

   private static class GetTableResultStandardSchemeFactory implements SchemeFactory {
      private GetTableResultStandardSchemeFactory() {
      }

      public GetTableResultStandardScheme getScheme() {
         return new GetTableResultStandardScheme();
      }
   }

   private static class GetTableResultStandardScheme extends StandardScheme {
      private GetTableResultStandardScheme() {
      }

      public void read(TProtocol iprot, GetTableResult struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.table = new Table();
                     struct.table.read(iprot);
                     struct.setTableIsSet(true);
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

      public void write(TProtocol oprot, GetTableResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetTableResult.STRUCT_DESC);
         if (struct.table != null) {
            oprot.writeFieldBegin(GetTableResult.TABLE_FIELD_DESC);
            struct.table.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetTableResultTupleSchemeFactory implements SchemeFactory {
      private GetTableResultTupleSchemeFactory() {
      }

      public GetTableResultTupleScheme getScheme() {
         return new GetTableResultTupleScheme();
      }
   }

   private static class GetTableResultTupleScheme extends TupleScheme {
      private GetTableResultTupleScheme() {
      }

      public void write(TProtocol prot, GetTableResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.table.write(oprot);
      }

      public void read(TProtocol prot, GetTableResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.table = new Table();
         struct.table.read(iprot);
         struct.setTableIsSet(true);
      }
   }
}
