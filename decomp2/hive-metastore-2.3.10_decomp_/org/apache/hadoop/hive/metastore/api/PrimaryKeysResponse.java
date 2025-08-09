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

public class PrimaryKeysResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PrimaryKeysResponse");
   private static final TField PRIMARY_KEYS_FIELD_DESC = new TField("primaryKeys", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PrimaryKeysResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PrimaryKeysResponseTupleSchemeFactory();
   @Nullable
   private List primaryKeys;
   public static final Map metaDataMap;

   public PrimaryKeysResponse() {
   }

   public PrimaryKeysResponse(List primaryKeys) {
      this();
      this.primaryKeys = primaryKeys;
   }

   public PrimaryKeysResponse(PrimaryKeysResponse other) {
      if (other.isSetPrimaryKeys()) {
         List<SQLPrimaryKey> __this__primaryKeys = new ArrayList(other.primaryKeys.size());

         for(SQLPrimaryKey other_element : other.primaryKeys) {
            __this__primaryKeys.add(new SQLPrimaryKey(other_element));
         }

         this.primaryKeys = __this__primaryKeys;
      }

   }

   public PrimaryKeysResponse deepCopy() {
      return new PrimaryKeysResponse(this);
   }

   public void clear() {
      this.primaryKeys = null;
   }

   public int getPrimaryKeysSize() {
      return this.primaryKeys == null ? 0 : this.primaryKeys.size();
   }

   @Nullable
   public Iterator getPrimaryKeysIterator() {
      return this.primaryKeys == null ? null : this.primaryKeys.iterator();
   }

   public void addToPrimaryKeys(SQLPrimaryKey elem) {
      if (this.primaryKeys == null) {
         this.primaryKeys = new ArrayList();
      }

      this.primaryKeys.add(elem);
   }

   @Nullable
   public List getPrimaryKeys() {
      return this.primaryKeys;
   }

   public void setPrimaryKeys(@Nullable List primaryKeys) {
      this.primaryKeys = primaryKeys;
   }

   public void unsetPrimaryKeys() {
      this.primaryKeys = null;
   }

   public boolean isSetPrimaryKeys() {
      return this.primaryKeys != null;
   }

   public void setPrimaryKeysIsSet(boolean value) {
      if (!value) {
         this.primaryKeys = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PRIMARY_KEYS:
            if (value == null) {
               this.unsetPrimaryKeys();
            } else {
               this.setPrimaryKeys((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PRIMARY_KEYS:
            return this.getPrimaryKeys();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PRIMARY_KEYS:
               return this.isSetPrimaryKeys();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PrimaryKeysResponse ? this.equals((PrimaryKeysResponse)that) : false;
   }

   public boolean equals(PrimaryKeysResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_primaryKeys = this.isSetPrimaryKeys();
         boolean that_present_primaryKeys = that.isSetPrimaryKeys();
         if (this_present_primaryKeys || that_present_primaryKeys) {
            if (!this_present_primaryKeys || !that_present_primaryKeys) {
               return false;
            }

            if (!this.primaryKeys.equals(that.primaryKeys)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPrimaryKeys() ? 131071 : 524287);
      if (this.isSetPrimaryKeys()) {
         hashCode = hashCode * 8191 + this.primaryKeys.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PrimaryKeysResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPrimaryKeys(), other.isSetPrimaryKeys());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPrimaryKeys()) {
               lastComparison = TBaseHelper.compareTo(this.primaryKeys, other.primaryKeys);
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
      return PrimaryKeysResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PrimaryKeysResponse(");
      boolean first = true;
      sb.append("primaryKeys:");
      if (this.primaryKeys == null) {
         sb.append("null");
      } else {
         sb.append(this.primaryKeys);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPrimaryKeys()) {
         throw new TProtocolException("Required field 'primaryKeys' is unset! Struct:" + this.toString());
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
      tmpMap.put(PrimaryKeysResponse._Fields.PRIMARY_KEYS, new FieldMetaData("primaryKeys", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, SQLPrimaryKey.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PrimaryKeysResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRIMARY_KEYS((short)1, "primaryKeys");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRIMARY_KEYS;
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

   private static class PrimaryKeysResponseStandardSchemeFactory implements SchemeFactory {
      private PrimaryKeysResponseStandardSchemeFactory() {
      }

      public PrimaryKeysResponseStandardScheme getScheme() {
         return new PrimaryKeysResponseStandardScheme();
      }
   }

   private static class PrimaryKeysResponseStandardScheme extends StandardScheme {
      private PrimaryKeysResponseStandardScheme() {
      }

      public void read(TProtocol iprot, PrimaryKeysResponse struct) throws TException {
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

                  TList _list322 = iprot.readListBegin();
                  struct.primaryKeys = new ArrayList(_list322.size);

                  for(int _i324 = 0; _i324 < _list322.size; ++_i324) {
                     SQLPrimaryKey _elem323 = new SQLPrimaryKey();
                     _elem323.read(iprot);
                     struct.primaryKeys.add(_elem323);
                  }

                  iprot.readListEnd();
                  struct.setPrimaryKeysIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PrimaryKeysResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PrimaryKeysResponse.STRUCT_DESC);
         if (struct.primaryKeys != null) {
            oprot.writeFieldBegin(PrimaryKeysResponse.PRIMARY_KEYS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.primaryKeys.size()));

            for(SQLPrimaryKey _iter325 : struct.primaryKeys) {
               _iter325.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PrimaryKeysResponseTupleSchemeFactory implements SchemeFactory {
      private PrimaryKeysResponseTupleSchemeFactory() {
      }

      public PrimaryKeysResponseTupleScheme getScheme() {
         return new PrimaryKeysResponseTupleScheme();
      }
   }

   private static class PrimaryKeysResponseTupleScheme extends TupleScheme {
      private PrimaryKeysResponseTupleScheme() {
      }

      public void write(TProtocol prot, PrimaryKeysResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.primaryKeys.size());

         for(SQLPrimaryKey _iter326 : struct.primaryKeys) {
            _iter326.write(oprot);
         }

      }

      public void read(TProtocol prot, PrimaryKeysResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list327 = iprot.readListBegin((byte)12);
         struct.primaryKeys = new ArrayList(_list327.size);

         for(int _i329 = 0; _i329 < _list327.size; ++_i329) {
            SQLPrimaryKey _elem328 = new SQLPrimaryKey();
            _elem328.read(iprot);
            struct.primaryKeys.add(_elem328);
         }

         struct.setPrimaryKeysIsSet(true);
      }
   }
}
