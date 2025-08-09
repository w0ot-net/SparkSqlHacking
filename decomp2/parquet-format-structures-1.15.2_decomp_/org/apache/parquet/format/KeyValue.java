package org.apache.parquet.format;

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
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
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

public class KeyValue implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("KeyValue");
   private static final TField KEY_FIELD_DESC = new TField("key", (byte)11, (short)1);
   private static final TField VALUE_FIELD_DESC = new TField("value", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new KeyValueStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new KeyValueTupleSchemeFactory();
   @Nullable
   public String key;
   @Nullable
   public String value;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public KeyValue() {
   }

   public KeyValue(String key) {
      this();
      this.key = key;
   }

   public KeyValue(KeyValue other) {
      if (other.isSetKey()) {
         this.key = other.key;
      }

      if (other.isSetValue()) {
         this.value = other.value;
      }

   }

   public KeyValue deepCopy() {
      return new KeyValue(this);
   }

   public void clear() {
      this.key = null;
      this.value = null;
   }

   @Nullable
   public String getKey() {
      return this.key;
   }

   public KeyValue setKey(@Nullable String key) {
      this.key = key;
      return this;
   }

   public void unsetKey() {
      this.key = null;
   }

   public boolean isSetKey() {
      return this.key != null;
   }

   public void setKeyIsSet(boolean value) {
      if (!value) {
         this.key = null;
      }

   }

   @Nullable
   public String getValue() {
      return this.value;
   }

   public KeyValue setValue(@Nullable String value) {
      this.value = value;
      return this;
   }

   public void unsetValue() {
      this.value = null;
   }

   public boolean isSetValue() {
      return this.value != null;
   }

   public void setValueIsSet(boolean value) {
      if (!value) {
         this.value = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case KEY:
            if (value == null) {
               this.unsetKey();
            } else {
               this.setKey((String)value);
            }
            break;
         case VALUE:
            if (value == null) {
               this.unsetValue();
            } else {
               this.setValue((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case KEY:
            return this.getKey();
         case VALUE:
            return this.getValue();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case KEY:
               return this.isSetKey();
            case VALUE:
               return this.isSetValue();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof KeyValue ? this.equals((KeyValue)that) : false;
   }

   public boolean equals(KeyValue that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_key = this.isSetKey();
         boolean that_present_key = that.isSetKey();
         if (this_present_key || that_present_key) {
            if (!this_present_key || !that_present_key) {
               return false;
            }

            if (!this.key.equals(that.key)) {
               return false;
            }
         }

         boolean this_present_value = this.isSetValue();
         boolean that_present_value = that.isSetValue();
         if (this_present_value || that_present_value) {
            if (!this_present_value || !that_present_value) {
               return false;
            }

            if (!this.value.equals(that.value)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetKey() ? 131071 : 524287);
      if (this.isSetKey()) {
         hashCode = hashCode * 8191 + this.key.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetValue() ? 131071 : 524287);
      if (this.isSetValue()) {
         hashCode = hashCode * 8191 + this.value.hashCode();
      }

      return hashCode;
   }

   public int compareTo(KeyValue other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetKey(), other.isSetKey());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetKey()) {
               lastComparison = TBaseHelper.compareTo(this.key, other.key);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetValue(), other.isSetValue());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetValue()) {
                  lastComparison = TBaseHelper.compareTo(this.value, other.value);
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
      return KeyValue._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("KeyValue(");
      boolean first = true;
      sb.append("key:");
      if (this.key == null) {
         sb.append("null");
      } else {
         sb.append(this.key);
      }

      first = false;
      if (this.isSetValue()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("value:");
         if (this.value == null) {
            sb.append("null");
         } else {
            sb.append(this.value);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.key == null) {
         throw new TProtocolException("Required field 'key' was not present! Struct: " + this.toString());
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
      optionals = new _Fields[]{KeyValue._Fields.VALUE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(KeyValue._Fields.KEY, new FieldMetaData("key", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(KeyValue._Fields.VALUE, new FieldMetaData("value", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(KeyValue.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      KEY((short)1, "key"),
      VALUE((short)2, "value");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return KEY;
            case 2:
               return VALUE;
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

   private static class KeyValueStandardSchemeFactory implements SchemeFactory {
      private KeyValueStandardSchemeFactory() {
      }

      public KeyValueStandardScheme getScheme() {
         return new KeyValueStandardScheme();
      }
   }

   private static class KeyValueStandardScheme extends StandardScheme {
      private KeyValueStandardScheme() {
      }

      public void read(TProtocol iprot, KeyValue struct) throws TException {
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
                     struct.key = iprot.readString();
                     struct.setKeyIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.value = iprot.readString();
                     struct.setValueIsSet(true);
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

      public void write(TProtocol oprot, KeyValue struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(KeyValue.STRUCT_DESC);
         if (struct.key != null) {
            oprot.writeFieldBegin(KeyValue.KEY_FIELD_DESC);
            oprot.writeString(struct.key);
            oprot.writeFieldEnd();
         }

         if (struct.value != null && struct.isSetValue()) {
            oprot.writeFieldBegin(KeyValue.VALUE_FIELD_DESC);
            oprot.writeString(struct.value);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class KeyValueTupleSchemeFactory implements SchemeFactory {
      private KeyValueTupleSchemeFactory() {
      }

      public KeyValueTupleScheme getScheme() {
         return new KeyValueTupleScheme();
      }
   }

   private static class KeyValueTupleScheme extends TupleScheme {
      private KeyValueTupleScheme() {
      }

      public void write(TProtocol prot, KeyValue struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.key);
         BitSet optionals = new BitSet();
         if (struct.isSetValue()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetValue()) {
            oprot.writeString(struct.value);
         }

      }

      public void read(TProtocol prot, KeyValue struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.key = iprot.readString();
         struct.setKeyIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.value = iprot.readString();
            struct.setValueIsSet(true);
         }

      }
   }
}
