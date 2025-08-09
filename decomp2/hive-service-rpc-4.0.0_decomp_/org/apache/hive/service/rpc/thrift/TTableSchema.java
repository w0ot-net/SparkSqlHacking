package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
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

@Public
@Stable
public class TTableSchema implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TTableSchema");
   private static final TField COLUMNS_FIELD_DESC = new TField("columns", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TTableSchemaStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TTableSchemaTupleSchemeFactory();
   @Nullable
   private List columns;
   public static final Map metaDataMap;

   public TTableSchema() {
   }

   public TTableSchema(List columns) {
      this();
      this.columns = columns;
   }

   public TTableSchema(TTableSchema other) {
      if (other.isSetColumns()) {
         List<TColumnDesc> __this__columns = new ArrayList(other.columns.size());

         for(TColumnDesc other_element : other.columns) {
            __this__columns.add(new TColumnDesc(other_element));
         }

         this.columns = __this__columns;
      }

   }

   public TTableSchema deepCopy() {
      return new TTableSchema(this);
   }

   public void clear() {
      this.columns = null;
   }

   public int getColumnsSize() {
      return this.columns == null ? 0 : this.columns.size();
   }

   @Nullable
   public Iterator getColumnsIterator() {
      return this.columns == null ? null : this.columns.iterator();
   }

   public void addToColumns(TColumnDesc elem) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(elem);
   }

   @Nullable
   public List getColumns() {
      return this.columns;
   }

   public void setColumns(@Nullable List columns) {
      this.columns = columns;
   }

   public void unsetColumns() {
      this.columns = null;
   }

   public boolean isSetColumns() {
      return this.columns != null;
   }

   public void setColumnsIsSet(boolean value) {
      if (!value) {
         this.columns = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COLUMNS:
            if (value == null) {
               this.unsetColumns();
            } else {
               this.setColumns((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COLUMNS:
            return this.getColumns();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COLUMNS:
               return this.isSetColumns();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TTableSchema ? this.equals((TTableSchema)that) : false;
   }

   public boolean equals(TTableSchema that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_columns = this.isSetColumns();
         boolean that_present_columns = that.isSetColumns();
         if (this_present_columns || that_present_columns) {
            if (!this_present_columns || !that_present_columns) {
               return false;
            }

            if (!this.columns.equals(that.columns)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetColumns() ? 131071 : 524287);
      if (this.isSetColumns()) {
         hashCode = hashCode * 8191 + this.columns.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TTableSchema other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColumns(), other.isSetColumns());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColumns()) {
               lastComparison = TBaseHelper.compareTo(this.columns, other.columns);
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
      return TTableSchema._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TTableSchema(");
      boolean first = true;
      sb.append("columns:");
      if (this.columns == null) {
         sb.append("null");
      } else {
         sb.append(this.columns);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetColumns()) {
         throw new TProtocolException("Required field 'columns' is unset! Struct:" + this.toString());
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
      tmpMap.put(TTableSchema._Fields.COLUMNS, new FieldMetaData("columns", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, TColumnDesc.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TTableSchema.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COLUMNS((short)1, "columns");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COLUMNS;
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

   private static class TTableSchemaStandardSchemeFactory implements SchemeFactory {
      private TTableSchemaStandardSchemeFactory() {
      }

      public TTableSchemaStandardScheme getScheme() {
         return new TTableSchemaStandardScheme();
      }
   }

   private static class TTableSchemaStandardScheme extends StandardScheme {
      private TTableSchemaStandardScheme() {
      }

      public void read(TProtocol iprot, TTableSchema struct) throws TException {
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

                  TList _list38 = iprot.readListBegin();
                  struct.columns = new ArrayList(_list38.size);

                  for(int _i40 = 0; _i40 < _list38.size; ++_i40) {
                     TColumnDesc _elem39 = new TColumnDesc();
                     _elem39.read(iprot);
                     struct.columns.add(_elem39);
                  }

                  iprot.readListEnd();
                  struct.setColumnsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TTableSchema struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TTableSchema.STRUCT_DESC);
         if (struct.columns != null) {
            oprot.writeFieldBegin(TTableSchema.COLUMNS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.columns.size()));

            for(TColumnDesc _iter41 : struct.columns) {
               _iter41.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TTableSchemaTupleSchemeFactory implements SchemeFactory {
      private TTableSchemaTupleSchemeFactory() {
      }

      public TTableSchemaTupleScheme getScheme() {
         return new TTableSchemaTupleScheme();
      }
   }

   private static class TTableSchemaTupleScheme extends TupleScheme {
      private TTableSchemaTupleScheme() {
      }

      public void write(TProtocol prot, TTableSchema struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.columns.size());

         for(TColumnDesc _iter42 : struct.columns) {
            _iter42.write(oprot);
         }

      }

      public void read(TProtocol prot, TTableSchema struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list43 = iprot.readListBegin((byte)12);
         struct.columns = new ArrayList(_list43.size);

         for(int _i45 = 0; _i45 < _list43.size; ++_i45) {
            TColumnDesc _elem44 = new TColumnDesc();
            _elem44.read(iprot);
            struct.columns.add(_elem44);
         }

         struct.setColumnsIsSet(true);
      }
   }
}
