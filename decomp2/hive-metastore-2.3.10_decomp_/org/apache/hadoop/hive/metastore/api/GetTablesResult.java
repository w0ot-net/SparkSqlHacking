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

public class GetTablesResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetTablesResult");
   private static final TField TABLES_FIELD_DESC = new TField("tables", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTablesResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTablesResultTupleSchemeFactory();
   @Nullable
   private List tables;
   public static final Map metaDataMap;

   public GetTablesResult() {
   }

   public GetTablesResult(List tables) {
      this();
      this.tables = tables;
   }

   public GetTablesResult(GetTablesResult other) {
      if (other.isSetTables()) {
         List<Table> __this__tables = new ArrayList(other.tables.size());

         for(Table other_element : other.tables) {
            __this__tables.add(new Table(other_element));
         }

         this.tables = __this__tables;
      }

   }

   public GetTablesResult deepCopy() {
      return new GetTablesResult(this);
   }

   public void clear() {
      this.tables = null;
   }

   public int getTablesSize() {
      return this.tables == null ? 0 : this.tables.size();
   }

   @Nullable
   public Iterator getTablesIterator() {
      return this.tables == null ? null : this.tables.iterator();
   }

   public void addToTables(Table elem) {
      if (this.tables == null) {
         this.tables = new ArrayList();
      }

      this.tables.add(elem);
   }

   @Nullable
   public List getTables() {
      return this.tables;
   }

   public void setTables(@Nullable List tables) {
      this.tables = tables;
   }

   public void unsetTables() {
      this.tables = null;
   }

   public boolean isSetTables() {
      return this.tables != null;
   }

   public void setTablesIsSet(boolean value) {
      if (!value) {
         this.tables = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TABLES:
            if (value == null) {
               this.unsetTables();
            } else {
               this.setTables((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TABLES:
            return this.getTables();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TABLES:
               return this.isSetTables();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetTablesResult ? this.equals((GetTablesResult)that) : false;
   }

   public boolean equals(GetTablesResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_tables = this.isSetTables();
         boolean that_present_tables = that.isSetTables();
         if (this_present_tables || that_present_tables) {
            if (!this_present_tables || !that_present_tables) {
               return false;
            }

            if (!this.tables.equals(that.tables)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetTables() ? 131071 : 524287);
      if (this.isSetTables()) {
         hashCode = hashCode * 8191 + this.tables.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetTablesResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTables(), other.isSetTables());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTables()) {
               lastComparison = TBaseHelper.compareTo(this.tables, other.tables);
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
      return GetTablesResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetTablesResult(");
      boolean first = true;
      sb.append("tables:");
      if (this.tables == null) {
         sb.append("null");
      } else {
         sb.append(this.tables);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTables()) {
         throw new TProtocolException("Required field 'tables' is unset! Struct:" + this.toString());
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
      tmpMap.put(GetTablesResult._Fields.TABLES, new FieldMetaData("tables", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, Table.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetTablesResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TABLES((short)1, "tables");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TABLES;
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

   private static class GetTablesResultStandardSchemeFactory implements SchemeFactory {
      private GetTablesResultStandardSchemeFactory() {
      }

      public GetTablesResultStandardScheme getScheme() {
         return new GetTablesResultStandardScheme();
      }
   }

   private static class GetTablesResultStandardScheme extends StandardScheme {
      private GetTablesResultStandardScheme() {
      }

      public void read(TProtocol iprot, GetTablesResult struct) throws TException {
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

                  TList _list698 = iprot.readListBegin();
                  struct.tables = new ArrayList(_list698.size);

                  for(int _i700 = 0; _i700 < _list698.size; ++_i700) {
                     Table _elem699 = new Table();
                     _elem699.read(iprot);
                     struct.tables.add(_elem699);
                  }

                  iprot.readListEnd();
                  struct.setTablesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, GetTablesResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetTablesResult.STRUCT_DESC);
         if (struct.tables != null) {
            oprot.writeFieldBegin(GetTablesResult.TABLES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.tables.size()));

            for(Table _iter701 : struct.tables) {
               _iter701.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetTablesResultTupleSchemeFactory implements SchemeFactory {
      private GetTablesResultTupleSchemeFactory() {
      }

      public GetTablesResultTupleScheme getScheme() {
         return new GetTablesResultTupleScheme();
      }
   }

   private static class GetTablesResultTupleScheme extends TupleScheme {
      private GetTablesResultTupleScheme() {
      }

      public void write(TProtocol prot, GetTablesResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.tables.size());

         for(Table _iter702 : struct.tables) {
            _iter702.write(oprot);
         }

      }

      public void read(TProtocol prot, GetTablesResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list703 = iprot.readListBegin((byte)12);
         struct.tables = new ArrayList(_list703.size);

         for(int _i705 = 0; _i705 < _list703.size; ++_i705) {
            Table _elem704 = new Table();
            _elem704.read(iprot);
            struct.tables.add(_elem704);
         }

         struct.setTablesIsSet(true);
      }
   }
}
