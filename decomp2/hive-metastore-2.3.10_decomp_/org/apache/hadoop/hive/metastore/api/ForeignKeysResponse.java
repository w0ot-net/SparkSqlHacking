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

public class ForeignKeysResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ForeignKeysResponse");
   private static final TField FOREIGN_KEYS_FIELD_DESC = new TField("foreignKeys", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ForeignKeysResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ForeignKeysResponseTupleSchemeFactory();
   @Nullable
   private List foreignKeys;
   public static final Map metaDataMap;

   public ForeignKeysResponse() {
   }

   public ForeignKeysResponse(List foreignKeys) {
      this();
      this.foreignKeys = foreignKeys;
   }

   public ForeignKeysResponse(ForeignKeysResponse other) {
      if (other.isSetForeignKeys()) {
         List<SQLForeignKey> __this__foreignKeys = new ArrayList(other.foreignKeys.size());

         for(SQLForeignKey other_element : other.foreignKeys) {
            __this__foreignKeys.add(new SQLForeignKey(other_element));
         }

         this.foreignKeys = __this__foreignKeys;
      }

   }

   public ForeignKeysResponse deepCopy() {
      return new ForeignKeysResponse(this);
   }

   public void clear() {
      this.foreignKeys = null;
   }

   public int getForeignKeysSize() {
      return this.foreignKeys == null ? 0 : this.foreignKeys.size();
   }

   @Nullable
   public Iterator getForeignKeysIterator() {
      return this.foreignKeys == null ? null : this.foreignKeys.iterator();
   }

   public void addToForeignKeys(SQLForeignKey elem) {
      if (this.foreignKeys == null) {
         this.foreignKeys = new ArrayList();
      }

      this.foreignKeys.add(elem);
   }

   @Nullable
   public List getForeignKeys() {
      return this.foreignKeys;
   }

   public void setForeignKeys(@Nullable List foreignKeys) {
      this.foreignKeys = foreignKeys;
   }

   public void unsetForeignKeys() {
      this.foreignKeys = null;
   }

   public boolean isSetForeignKeys() {
      return this.foreignKeys != null;
   }

   public void setForeignKeysIsSet(boolean value) {
      if (!value) {
         this.foreignKeys = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FOREIGN_KEYS:
            if (value == null) {
               this.unsetForeignKeys();
            } else {
               this.setForeignKeys((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FOREIGN_KEYS:
            return this.getForeignKeys();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FOREIGN_KEYS:
               return this.isSetForeignKeys();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ForeignKeysResponse ? this.equals((ForeignKeysResponse)that) : false;
   }

   public boolean equals(ForeignKeysResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_foreignKeys = this.isSetForeignKeys();
         boolean that_present_foreignKeys = that.isSetForeignKeys();
         if (this_present_foreignKeys || that_present_foreignKeys) {
            if (!this_present_foreignKeys || !that_present_foreignKeys) {
               return false;
            }

            if (!this.foreignKeys.equals(that.foreignKeys)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetForeignKeys() ? 131071 : 524287);
      if (this.isSetForeignKeys()) {
         hashCode = hashCode * 8191 + this.foreignKeys.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ForeignKeysResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetForeignKeys(), other.isSetForeignKeys());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetForeignKeys()) {
               lastComparison = TBaseHelper.compareTo(this.foreignKeys, other.foreignKeys);
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
      return ForeignKeysResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ForeignKeysResponse(");
      boolean first = true;
      sb.append("foreignKeys:");
      if (this.foreignKeys == null) {
         sb.append("null");
      } else {
         sb.append(this.foreignKeys);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetForeignKeys()) {
         throw new TProtocolException("Required field 'foreignKeys' is unset! Struct:" + this.toString());
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
      tmpMap.put(ForeignKeysResponse._Fields.FOREIGN_KEYS, new FieldMetaData("foreignKeys", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, SQLForeignKey.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ForeignKeysResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FOREIGN_KEYS((short)1, "foreignKeys");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FOREIGN_KEYS;
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

   private static class ForeignKeysResponseStandardSchemeFactory implements SchemeFactory {
      private ForeignKeysResponseStandardSchemeFactory() {
      }

      public ForeignKeysResponseStandardScheme getScheme() {
         return new ForeignKeysResponseStandardScheme();
      }
   }

   private static class ForeignKeysResponseStandardScheme extends StandardScheme {
      private ForeignKeysResponseStandardScheme() {
      }

      public void read(TProtocol iprot, ForeignKeysResponse struct) throws TException {
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

                  TList _list330 = iprot.readListBegin();
                  struct.foreignKeys = new ArrayList(_list330.size);

                  for(int _i332 = 0; _i332 < _list330.size; ++_i332) {
                     SQLForeignKey _elem331 = new SQLForeignKey();
                     _elem331.read(iprot);
                     struct.foreignKeys.add(_elem331);
                  }

                  iprot.readListEnd();
                  struct.setForeignKeysIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ForeignKeysResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ForeignKeysResponse.STRUCT_DESC);
         if (struct.foreignKeys != null) {
            oprot.writeFieldBegin(ForeignKeysResponse.FOREIGN_KEYS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.foreignKeys.size()));

            for(SQLForeignKey _iter333 : struct.foreignKeys) {
               _iter333.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ForeignKeysResponseTupleSchemeFactory implements SchemeFactory {
      private ForeignKeysResponseTupleSchemeFactory() {
      }

      public ForeignKeysResponseTupleScheme getScheme() {
         return new ForeignKeysResponseTupleScheme();
      }
   }

   private static class ForeignKeysResponseTupleScheme extends TupleScheme {
      private ForeignKeysResponseTupleScheme() {
      }

      public void write(TProtocol prot, ForeignKeysResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.foreignKeys.size());

         for(SQLForeignKey _iter334 : struct.foreignKeys) {
            _iter334.write(oprot);
         }

      }

      public void read(TProtocol prot, ForeignKeysResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list335 = iprot.readListBegin((byte)12);
         struct.foreignKeys = new ArrayList(_list335.size);

         for(int _i337 = 0; _i337 < _list335.size; ++_i337) {
            SQLForeignKey _elem336 = new SQLForeignKey();
            _elem336.read(iprot);
            struct.foreignKeys.add(_elem336);
         }

         struct.setForeignKeysIsSet(true);
      }
   }
}
