package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.ListMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class EncryptionWithColumnKey implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("EncryptionWithColumnKey");
   private static final TField PATH_IN_SCHEMA_FIELD_DESC = new TField("path_in_schema", (byte)15, (short)1);
   private static final TField KEY_METADATA_FIELD_DESC = new TField("key_metadata", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new EncryptionWithColumnKeyStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new EncryptionWithColumnKeyTupleSchemeFactory();
   @Nullable
   public List path_in_schema;
   @Nullable
   public ByteBuffer key_metadata;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public EncryptionWithColumnKey() {
   }

   public EncryptionWithColumnKey(List path_in_schema) {
      this();
      this.path_in_schema = path_in_schema;
   }

   public EncryptionWithColumnKey(EncryptionWithColumnKey other) {
      if (other.isSetPath_in_schema()) {
         List<String> __this__path_in_schema = new ArrayList(other.path_in_schema);
         this.path_in_schema = __this__path_in_schema;
      }

      if (other.isSetKey_metadata()) {
         this.key_metadata = TBaseHelper.copyBinary(other.key_metadata);
      }

   }

   public EncryptionWithColumnKey deepCopy() {
      return new EncryptionWithColumnKey(this);
   }

   public void clear() {
      this.path_in_schema = null;
      this.key_metadata = null;
   }

   public int getPath_in_schemaSize() {
      return this.path_in_schema == null ? 0 : this.path_in_schema.size();
   }

   @Nullable
   public Iterator getPath_in_schemaIterator() {
      return this.path_in_schema == null ? null : this.path_in_schema.iterator();
   }

   public void addToPath_in_schema(String elem) {
      if (this.path_in_schema == null) {
         this.path_in_schema = new ArrayList();
      }

      this.path_in_schema.add(elem);
   }

   @Nullable
   public List getPath_in_schema() {
      return this.path_in_schema;
   }

   public EncryptionWithColumnKey setPath_in_schema(@Nullable List path_in_schema) {
      this.path_in_schema = path_in_schema;
      return this;
   }

   public void unsetPath_in_schema() {
      this.path_in_schema = null;
   }

   public boolean isSetPath_in_schema() {
      return this.path_in_schema != null;
   }

   public void setPath_in_schemaIsSet(boolean value) {
      if (!value) {
         this.path_in_schema = null;
      }

   }

   public byte[] getKey_metadata() {
      this.setKey_metadata(TBaseHelper.rightSize(this.key_metadata));
      return this.key_metadata == null ? null : this.key_metadata.array();
   }

   public ByteBuffer bufferForKey_metadata() {
      return TBaseHelper.copyBinary(this.key_metadata);
   }

   public EncryptionWithColumnKey setKey_metadata(byte[] key_metadata) {
      this.key_metadata = key_metadata == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)key_metadata).clone());
      return this;
   }

   public EncryptionWithColumnKey setKey_metadata(@Nullable ByteBuffer key_metadata) {
      this.key_metadata = TBaseHelper.copyBinary(key_metadata);
      return this;
   }

   public void unsetKey_metadata() {
      this.key_metadata = null;
   }

   public boolean isSetKey_metadata() {
      return this.key_metadata != null;
   }

   public void setKey_metadataIsSet(boolean value) {
      if (!value) {
         this.key_metadata = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PATH_IN_SCHEMA:
            if (value == null) {
               this.unsetPath_in_schema();
            } else {
               this.setPath_in_schema((List)value);
            }
            break;
         case KEY_METADATA:
            if (value == null) {
               this.unsetKey_metadata();
            } else if (value instanceof byte[]) {
               this.setKey_metadata((byte[])value);
            } else {
               this.setKey_metadata((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PATH_IN_SCHEMA:
            return this.getPath_in_schema();
         case KEY_METADATA:
            return this.getKey_metadata();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PATH_IN_SCHEMA:
               return this.isSetPath_in_schema();
            case KEY_METADATA:
               return this.isSetKey_metadata();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof EncryptionWithColumnKey ? this.equals((EncryptionWithColumnKey)that) : false;
   }

   public boolean equals(EncryptionWithColumnKey that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_path_in_schema = this.isSetPath_in_schema();
         boolean that_present_path_in_schema = that.isSetPath_in_schema();
         if (this_present_path_in_schema || that_present_path_in_schema) {
            if (!this_present_path_in_schema || !that_present_path_in_schema) {
               return false;
            }

            if (!this.path_in_schema.equals(that.path_in_schema)) {
               return false;
            }
         }

         boolean this_present_key_metadata = this.isSetKey_metadata();
         boolean that_present_key_metadata = that.isSetKey_metadata();
         if (this_present_key_metadata || that_present_key_metadata) {
            if (!this_present_key_metadata || !that_present_key_metadata) {
               return false;
            }

            if (!this.key_metadata.equals(that.key_metadata)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPath_in_schema() ? 131071 : 524287);
      if (this.isSetPath_in_schema()) {
         hashCode = hashCode * 8191 + this.path_in_schema.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetKey_metadata() ? 131071 : 524287);
      if (this.isSetKey_metadata()) {
         hashCode = hashCode * 8191 + this.key_metadata.hashCode();
      }

      return hashCode;
   }

   public int compareTo(EncryptionWithColumnKey other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPath_in_schema(), other.isSetPath_in_schema());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPath_in_schema()) {
               lastComparison = TBaseHelper.compareTo(this.path_in_schema, other.path_in_schema);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetKey_metadata(), other.isSetKey_metadata());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetKey_metadata()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.key_metadata, (Comparable)other.key_metadata);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return EncryptionWithColumnKey._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("EncryptionWithColumnKey(");
      boolean first = true;
      sb.append("path_in_schema:");
      if (this.path_in_schema == null) {
         sb.append("null");
      } else {
         sb.append(this.path_in_schema);
      }

      first = false;
      if (this.isSetKey_metadata()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("key_metadata:");
         if (this.key_metadata == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.key_metadata, sb);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.path_in_schema == null) {
         throw new TProtocolException("Required field 'path_in_schema' was not present! Struct: " + this.toString());
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
      optionals = new _Fields[]{EncryptionWithColumnKey._Fields.KEY_METADATA};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(EncryptionWithColumnKey._Fields.PATH_IN_SCHEMA, new FieldMetaData("path_in_schema", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(EncryptionWithColumnKey._Fields.KEY_METADATA, new FieldMetaData("key_metadata", (byte)2, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(EncryptionWithColumnKey.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PATH_IN_SCHEMA((short)1, "path_in_schema"),
      KEY_METADATA((short)2, "key_metadata");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PATH_IN_SCHEMA;
            case 2:
               return KEY_METADATA;
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

   private static class EncryptionWithColumnKeyStandardSchemeFactory implements SchemeFactory {
      private EncryptionWithColumnKeyStandardSchemeFactory() {
      }

      public EncryptionWithColumnKeyStandardScheme getScheme() {
         return new EncryptionWithColumnKeyStandardScheme();
      }
   }

   private static class EncryptionWithColumnKeyStandardScheme extends StandardScheme {
      private EncryptionWithColumnKeyStandardScheme() {
      }

      public void read(TProtocol iprot, EncryptionWithColumnKey struct) throws TException {
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

                  TList _list48 = iprot.readListBegin();
                  struct.path_in_schema = new ArrayList(_list48.size);

                  for(int _i50 = 0; _i50 < _list48.size; ++_i50) {
                     String _elem49 = iprot.readString();
                     struct.path_in_schema.add(_elem49);
                  }

                  iprot.readListEnd();
                  struct.setPath_in_schemaIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.key_metadata = iprot.readBinary();
                     struct.setKey_metadataIsSet(true);
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

      public void write(TProtocol oprot, EncryptionWithColumnKey struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(EncryptionWithColumnKey.STRUCT_DESC);
         if (struct.path_in_schema != null) {
            oprot.writeFieldBegin(EncryptionWithColumnKey.PATH_IN_SCHEMA_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.path_in_schema.size()));

            for(String _iter51 : struct.path_in_schema) {
               oprot.writeString(_iter51);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.key_metadata != null && struct.isSetKey_metadata()) {
            oprot.writeFieldBegin(EncryptionWithColumnKey.KEY_METADATA_FIELD_DESC);
            oprot.writeBinary(struct.key_metadata);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class EncryptionWithColumnKeyTupleSchemeFactory implements SchemeFactory {
      private EncryptionWithColumnKeyTupleSchemeFactory() {
      }

      public EncryptionWithColumnKeyTupleScheme getScheme() {
         return new EncryptionWithColumnKeyTupleScheme();
      }
   }

   private static class EncryptionWithColumnKeyTupleScheme extends TupleScheme {
      private EncryptionWithColumnKeyTupleScheme() {
      }

      public void write(TProtocol prot, EncryptionWithColumnKey struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.path_in_schema.size());

         for(String _iter52 : struct.path_in_schema) {
            oprot.writeString(_iter52);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetKey_metadata()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetKey_metadata()) {
            oprot.writeBinary(struct.key_metadata);
         }

      }

      public void read(TProtocol prot, EncryptionWithColumnKey struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list53 = iprot.readListBegin((byte)11);
         struct.path_in_schema = new ArrayList(_list53.size);

         for(int _i55 = 0; _i55 < _list53.size; ++_i55) {
            String _elem54 = iprot.readString();
            struct.path_in_schema.add(_elem54);
         }

         struct.setPath_in_schemaIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.key_metadata = iprot.readBinary();
            struct.setKey_metadataIsSet(true);
         }

      }
   }
}
