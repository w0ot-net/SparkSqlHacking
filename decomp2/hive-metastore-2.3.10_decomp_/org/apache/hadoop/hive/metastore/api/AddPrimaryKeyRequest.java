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

public class AddPrimaryKeyRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("AddPrimaryKeyRequest");
   private static final TField PRIMARY_KEY_COLS_FIELD_DESC = new TField("primaryKeyCols", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new AddPrimaryKeyRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new AddPrimaryKeyRequestTupleSchemeFactory();
   @Nullable
   private List primaryKeyCols;
   public static final Map metaDataMap;

   public AddPrimaryKeyRequest() {
   }

   public AddPrimaryKeyRequest(List primaryKeyCols) {
      this();
      this.primaryKeyCols = primaryKeyCols;
   }

   public AddPrimaryKeyRequest(AddPrimaryKeyRequest other) {
      if (other.isSetPrimaryKeyCols()) {
         List<SQLPrimaryKey> __this__primaryKeyCols = new ArrayList(other.primaryKeyCols.size());

         for(SQLPrimaryKey other_element : other.primaryKeyCols) {
            __this__primaryKeyCols.add(new SQLPrimaryKey(other_element));
         }

         this.primaryKeyCols = __this__primaryKeyCols;
      }

   }

   public AddPrimaryKeyRequest deepCopy() {
      return new AddPrimaryKeyRequest(this);
   }

   public void clear() {
      this.primaryKeyCols = null;
   }

   public int getPrimaryKeyColsSize() {
      return this.primaryKeyCols == null ? 0 : this.primaryKeyCols.size();
   }

   @Nullable
   public Iterator getPrimaryKeyColsIterator() {
      return this.primaryKeyCols == null ? null : this.primaryKeyCols.iterator();
   }

   public void addToPrimaryKeyCols(SQLPrimaryKey elem) {
      if (this.primaryKeyCols == null) {
         this.primaryKeyCols = new ArrayList();
      }

      this.primaryKeyCols.add(elem);
   }

   @Nullable
   public List getPrimaryKeyCols() {
      return this.primaryKeyCols;
   }

   public void setPrimaryKeyCols(@Nullable List primaryKeyCols) {
      this.primaryKeyCols = primaryKeyCols;
   }

   public void unsetPrimaryKeyCols() {
      this.primaryKeyCols = null;
   }

   public boolean isSetPrimaryKeyCols() {
      return this.primaryKeyCols != null;
   }

   public void setPrimaryKeyColsIsSet(boolean value) {
      if (!value) {
         this.primaryKeyCols = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PRIMARY_KEY_COLS:
            if (value == null) {
               this.unsetPrimaryKeyCols();
            } else {
               this.setPrimaryKeyCols((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PRIMARY_KEY_COLS:
            return this.getPrimaryKeyCols();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PRIMARY_KEY_COLS:
               return this.isSetPrimaryKeyCols();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof AddPrimaryKeyRequest ? this.equals((AddPrimaryKeyRequest)that) : false;
   }

   public boolean equals(AddPrimaryKeyRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_primaryKeyCols = this.isSetPrimaryKeyCols();
         boolean that_present_primaryKeyCols = that.isSetPrimaryKeyCols();
         if (this_present_primaryKeyCols || that_present_primaryKeyCols) {
            if (!this_present_primaryKeyCols || !that_present_primaryKeyCols) {
               return false;
            }

            if (!this.primaryKeyCols.equals(that.primaryKeyCols)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPrimaryKeyCols() ? 131071 : 524287);
      if (this.isSetPrimaryKeyCols()) {
         hashCode = hashCode * 8191 + this.primaryKeyCols.hashCode();
      }

      return hashCode;
   }

   public int compareTo(AddPrimaryKeyRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPrimaryKeyCols(), other.isSetPrimaryKeyCols());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPrimaryKeyCols()) {
               lastComparison = TBaseHelper.compareTo(this.primaryKeyCols, other.primaryKeyCols);
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
      return AddPrimaryKeyRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AddPrimaryKeyRequest(");
      boolean first = true;
      sb.append("primaryKeyCols:");
      if (this.primaryKeyCols == null) {
         sb.append("null");
      } else {
         sb.append(this.primaryKeyCols);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPrimaryKeyCols()) {
         throw new TProtocolException("Required field 'primaryKeyCols' is unset! Struct:" + this.toString());
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
      tmpMap.put(AddPrimaryKeyRequest._Fields.PRIMARY_KEY_COLS, new FieldMetaData("primaryKeyCols", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, SQLPrimaryKey.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(AddPrimaryKeyRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRIMARY_KEY_COLS((short)1, "primaryKeyCols");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRIMARY_KEY_COLS;
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

   private static class AddPrimaryKeyRequestStandardSchemeFactory implements SchemeFactory {
      private AddPrimaryKeyRequestStandardSchemeFactory() {
      }

      public AddPrimaryKeyRequestStandardScheme getScheme() {
         return new AddPrimaryKeyRequestStandardScheme();
      }
   }

   private static class AddPrimaryKeyRequestStandardScheme extends StandardScheme {
      private AddPrimaryKeyRequestStandardScheme() {
      }

      public void read(TProtocol iprot, AddPrimaryKeyRequest struct) throws TException {
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

                  TList _list338 = iprot.readListBegin();
                  struct.primaryKeyCols = new ArrayList(_list338.size);

                  for(int _i340 = 0; _i340 < _list338.size; ++_i340) {
                     SQLPrimaryKey _elem339 = new SQLPrimaryKey();
                     _elem339.read(iprot);
                     struct.primaryKeyCols.add(_elem339);
                  }

                  iprot.readListEnd();
                  struct.setPrimaryKeyColsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, AddPrimaryKeyRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(AddPrimaryKeyRequest.STRUCT_DESC);
         if (struct.primaryKeyCols != null) {
            oprot.writeFieldBegin(AddPrimaryKeyRequest.PRIMARY_KEY_COLS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.primaryKeyCols.size()));

            for(SQLPrimaryKey _iter341 : struct.primaryKeyCols) {
               _iter341.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class AddPrimaryKeyRequestTupleSchemeFactory implements SchemeFactory {
      private AddPrimaryKeyRequestTupleSchemeFactory() {
      }

      public AddPrimaryKeyRequestTupleScheme getScheme() {
         return new AddPrimaryKeyRequestTupleScheme();
      }
   }

   private static class AddPrimaryKeyRequestTupleScheme extends TupleScheme {
      private AddPrimaryKeyRequestTupleScheme() {
      }

      public void write(TProtocol prot, AddPrimaryKeyRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.primaryKeyCols.size());

         for(SQLPrimaryKey _iter342 : struct.primaryKeyCols) {
            _iter342.write(oprot);
         }

      }

      public void read(TProtocol prot, AddPrimaryKeyRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list343 = iprot.readListBegin((byte)12);
         struct.primaryKeyCols = new ArrayList(_list343.size);

         for(int _i345 = 0; _i345 < _list343.size; ++_i345) {
            SQLPrimaryKey _elem344 = new SQLPrimaryKey();
            _elem344.read(iprot);
            struct.primaryKeyCols.add(_elem344);
         }

         struct.setPrimaryKeyColsIsSet(true);
      }
   }
}
