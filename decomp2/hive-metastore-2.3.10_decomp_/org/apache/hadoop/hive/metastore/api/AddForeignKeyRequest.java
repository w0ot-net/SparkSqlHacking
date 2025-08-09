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

public class AddForeignKeyRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("AddForeignKeyRequest");
   private static final TField FOREIGN_KEY_COLS_FIELD_DESC = new TField("foreignKeyCols", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new AddForeignKeyRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new AddForeignKeyRequestTupleSchemeFactory();
   @Nullable
   private List foreignKeyCols;
   public static final Map metaDataMap;

   public AddForeignKeyRequest() {
   }

   public AddForeignKeyRequest(List foreignKeyCols) {
      this();
      this.foreignKeyCols = foreignKeyCols;
   }

   public AddForeignKeyRequest(AddForeignKeyRequest other) {
      if (other.isSetForeignKeyCols()) {
         List<SQLForeignKey> __this__foreignKeyCols = new ArrayList(other.foreignKeyCols.size());

         for(SQLForeignKey other_element : other.foreignKeyCols) {
            __this__foreignKeyCols.add(new SQLForeignKey(other_element));
         }

         this.foreignKeyCols = __this__foreignKeyCols;
      }

   }

   public AddForeignKeyRequest deepCopy() {
      return new AddForeignKeyRequest(this);
   }

   public void clear() {
      this.foreignKeyCols = null;
   }

   public int getForeignKeyColsSize() {
      return this.foreignKeyCols == null ? 0 : this.foreignKeyCols.size();
   }

   @Nullable
   public Iterator getForeignKeyColsIterator() {
      return this.foreignKeyCols == null ? null : this.foreignKeyCols.iterator();
   }

   public void addToForeignKeyCols(SQLForeignKey elem) {
      if (this.foreignKeyCols == null) {
         this.foreignKeyCols = new ArrayList();
      }

      this.foreignKeyCols.add(elem);
   }

   @Nullable
   public List getForeignKeyCols() {
      return this.foreignKeyCols;
   }

   public void setForeignKeyCols(@Nullable List foreignKeyCols) {
      this.foreignKeyCols = foreignKeyCols;
   }

   public void unsetForeignKeyCols() {
      this.foreignKeyCols = null;
   }

   public boolean isSetForeignKeyCols() {
      return this.foreignKeyCols != null;
   }

   public void setForeignKeyColsIsSet(boolean value) {
      if (!value) {
         this.foreignKeyCols = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FOREIGN_KEY_COLS:
            if (value == null) {
               this.unsetForeignKeyCols();
            } else {
               this.setForeignKeyCols((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FOREIGN_KEY_COLS:
            return this.getForeignKeyCols();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FOREIGN_KEY_COLS:
               return this.isSetForeignKeyCols();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof AddForeignKeyRequest ? this.equals((AddForeignKeyRequest)that) : false;
   }

   public boolean equals(AddForeignKeyRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_foreignKeyCols = this.isSetForeignKeyCols();
         boolean that_present_foreignKeyCols = that.isSetForeignKeyCols();
         if (this_present_foreignKeyCols || that_present_foreignKeyCols) {
            if (!this_present_foreignKeyCols || !that_present_foreignKeyCols) {
               return false;
            }

            if (!this.foreignKeyCols.equals(that.foreignKeyCols)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetForeignKeyCols() ? 131071 : 524287);
      if (this.isSetForeignKeyCols()) {
         hashCode = hashCode * 8191 + this.foreignKeyCols.hashCode();
      }

      return hashCode;
   }

   public int compareTo(AddForeignKeyRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetForeignKeyCols(), other.isSetForeignKeyCols());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetForeignKeyCols()) {
               lastComparison = TBaseHelper.compareTo(this.foreignKeyCols, other.foreignKeyCols);
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
      return AddForeignKeyRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AddForeignKeyRequest(");
      boolean first = true;
      sb.append("foreignKeyCols:");
      if (this.foreignKeyCols == null) {
         sb.append("null");
      } else {
         sb.append(this.foreignKeyCols);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetForeignKeyCols()) {
         throw new TProtocolException("Required field 'foreignKeyCols' is unset! Struct:" + this.toString());
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
      tmpMap.put(AddForeignKeyRequest._Fields.FOREIGN_KEY_COLS, new FieldMetaData("foreignKeyCols", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, SQLForeignKey.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(AddForeignKeyRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FOREIGN_KEY_COLS((short)1, "foreignKeyCols");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FOREIGN_KEY_COLS;
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

   private static class AddForeignKeyRequestStandardSchemeFactory implements SchemeFactory {
      private AddForeignKeyRequestStandardSchemeFactory() {
      }

      public AddForeignKeyRequestStandardScheme getScheme() {
         return new AddForeignKeyRequestStandardScheme();
      }
   }

   private static class AddForeignKeyRequestStandardScheme extends StandardScheme {
      private AddForeignKeyRequestStandardScheme() {
      }

      public void read(TProtocol iprot, AddForeignKeyRequest struct) throws TException {
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

                  TList _list346 = iprot.readListBegin();
                  struct.foreignKeyCols = new ArrayList(_list346.size);

                  for(int _i348 = 0; _i348 < _list346.size; ++_i348) {
                     SQLForeignKey _elem347 = new SQLForeignKey();
                     _elem347.read(iprot);
                     struct.foreignKeyCols.add(_elem347);
                  }

                  iprot.readListEnd();
                  struct.setForeignKeyColsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, AddForeignKeyRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(AddForeignKeyRequest.STRUCT_DESC);
         if (struct.foreignKeyCols != null) {
            oprot.writeFieldBegin(AddForeignKeyRequest.FOREIGN_KEY_COLS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.foreignKeyCols.size()));

            for(SQLForeignKey _iter349 : struct.foreignKeyCols) {
               _iter349.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class AddForeignKeyRequestTupleSchemeFactory implements SchemeFactory {
      private AddForeignKeyRequestTupleSchemeFactory() {
      }

      public AddForeignKeyRequestTupleScheme getScheme() {
         return new AddForeignKeyRequestTupleScheme();
      }
   }

   private static class AddForeignKeyRequestTupleScheme extends TupleScheme {
      private AddForeignKeyRequestTupleScheme() {
      }

      public void write(TProtocol prot, AddForeignKeyRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.foreignKeyCols.size());

         for(SQLForeignKey _iter350 : struct.foreignKeyCols) {
            _iter350.write(oprot);
         }

      }

      public void read(TProtocol prot, AddForeignKeyRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list351 = iprot.readListBegin((byte)12);
         struct.foreignKeyCols = new ArrayList(_list351.size);

         for(int _i353 = 0; _i353 < _list351.size; ++_i353) {
            SQLForeignKey _elem352 = new SQLForeignKey();
            _elem352.read(iprot);
            struct.foreignKeyCols.add(_elem352);
         }

         struct.setForeignKeyColsIsSet(true);
      }
   }
}
