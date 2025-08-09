package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
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

@Public
@Stable
public class TMapTypeEntry implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TMapTypeEntry");
   private static final TField KEY_TYPE_PTR_FIELD_DESC = new TField("keyTypePtr", (byte)8, (short)1);
   private static final TField VALUE_TYPE_PTR_FIELD_DESC = new TField("valueTypePtr", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TMapTypeEntryStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TMapTypeEntryTupleSchemeFactory();
   private int keyTypePtr;
   private int valueTypePtr;
   private static final int __KEYTYPEPTR_ISSET_ID = 0;
   private static final int __VALUETYPEPTR_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public TMapTypeEntry() {
      this.__isset_bitfield = 0;
   }

   public TMapTypeEntry(int keyTypePtr, int valueTypePtr) {
      this();
      this.keyTypePtr = keyTypePtr;
      this.setKeyTypePtrIsSet(true);
      this.valueTypePtr = valueTypePtr;
      this.setValueTypePtrIsSet(true);
   }

   public TMapTypeEntry(TMapTypeEntry other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.keyTypePtr = other.keyTypePtr;
      this.valueTypePtr = other.valueTypePtr;
   }

   public TMapTypeEntry deepCopy() {
      return new TMapTypeEntry(this);
   }

   public void clear() {
      this.setKeyTypePtrIsSet(false);
      this.keyTypePtr = 0;
      this.setValueTypePtrIsSet(false);
      this.valueTypePtr = 0;
   }

   public int getKeyTypePtr() {
      return this.keyTypePtr;
   }

   public void setKeyTypePtr(int keyTypePtr) {
      this.keyTypePtr = keyTypePtr;
      this.setKeyTypePtrIsSet(true);
   }

   public void unsetKeyTypePtr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetKeyTypePtr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setKeyTypePtrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getValueTypePtr() {
      return this.valueTypePtr;
   }

   public void setValueTypePtr(int valueTypePtr) {
      this.valueTypePtr = valueTypePtr;
      this.setValueTypePtrIsSet(true);
   }

   public void unsetValueTypePtr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetValueTypePtr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setValueTypePtrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case KEY_TYPE_PTR:
            if (value == null) {
               this.unsetKeyTypePtr();
            } else {
               this.setKeyTypePtr((Integer)value);
            }
            break;
         case VALUE_TYPE_PTR:
            if (value == null) {
               this.unsetValueTypePtr();
            } else {
               this.setValueTypePtr((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case KEY_TYPE_PTR:
            return this.getKeyTypePtr();
         case VALUE_TYPE_PTR:
            return this.getValueTypePtr();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case KEY_TYPE_PTR:
               return this.isSetKeyTypePtr();
            case VALUE_TYPE_PTR:
               return this.isSetValueTypePtr();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TMapTypeEntry ? this.equals((TMapTypeEntry)that) : false;
   }

   public boolean equals(TMapTypeEntry that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_keyTypePtr = true;
         boolean that_present_keyTypePtr = true;
         if (this_present_keyTypePtr || that_present_keyTypePtr) {
            if (!this_present_keyTypePtr || !that_present_keyTypePtr) {
               return false;
            }

            if (this.keyTypePtr != that.keyTypePtr) {
               return false;
            }
         }

         boolean this_present_valueTypePtr = true;
         boolean that_present_valueTypePtr = true;
         if (this_present_valueTypePtr || that_present_valueTypePtr) {
            if (!this_present_valueTypePtr || !that_present_valueTypePtr) {
               return false;
            }

            if (this.valueTypePtr != that.valueTypePtr) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.keyTypePtr;
      hashCode = hashCode * 8191 + this.valueTypePtr;
      return hashCode;
   }

   public int compareTo(TMapTypeEntry other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetKeyTypePtr(), other.isSetKeyTypePtr());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetKeyTypePtr()) {
               lastComparison = TBaseHelper.compareTo(this.keyTypePtr, other.keyTypePtr);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetValueTypePtr(), other.isSetValueTypePtr());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetValueTypePtr()) {
                  lastComparison = TBaseHelper.compareTo(this.valueTypePtr, other.valueTypePtr);
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
      return TMapTypeEntry._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TMapTypeEntry(");
      boolean first = true;
      sb.append("keyTypePtr:");
      sb.append(this.keyTypePtr);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("valueTypePtr:");
      sb.append(this.valueTypePtr);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetKeyTypePtr()) {
         throw new TProtocolException("Required field 'keyTypePtr' is unset! Struct:" + this.toString());
      } else if (!this.isSetValueTypePtr()) {
         throw new TProtocolException("Required field 'valueTypePtr' is unset! Struct:" + this.toString());
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
         this.__isset_bitfield = 0;
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
      tmpMap.put(TMapTypeEntry._Fields.KEY_TYPE_PTR, new FieldMetaData("keyTypePtr", (byte)1, new FieldValueMetaData((byte)8, "TTypeEntryPtr")));
      tmpMap.put(TMapTypeEntry._Fields.VALUE_TYPE_PTR, new FieldMetaData("valueTypePtr", (byte)1, new FieldValueMetaData((byte)8, "TTypeEntryPtr")));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TMapTypeEntry.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      KEY_TYPE_PTR((short)1, "keyTypePtr"),
      VALUE_TYPE_PTR((short)2, "valueTypePtr");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return KEY_TYPE_PTR;
            case 2:
               return VALUE_TYPE_PTR;
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

   private static class TMapTypeEntryStandardSchemeFactory implements SchemeFactory {
      private TMapTypeEntryStandardSchemeFactory() {
      }

      public TMapTypeEntryStandardScheme getScheme() {
         return new TMapTypeEntryStandardScheme();
      }
   }

   private static class TMapTypeEntryStandardScheme extends StandardScheme {
      private TMapTypeEntryStandardScheme() {
      }

      public void read(TProtocol iprot, TMapTypeEntry struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.keyTypePtr = iprot.readI32();
                     struct.setKeyTypePtrIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.valueTypePtr = iprot.readI32();
                     struct.setValueTypePtrIsSet(true);
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

      public void write(TProtocol oprot, TMapTypeEntry struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TMapTypeEntry.STRUCT_DESC);
         oprot.writeFieldBegin(TMapTypeEntry.KEY_TYPE_PTR_FIELD_DESC);
         oprot.writeI32(struct.keyTypePtr);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(TMapTypeEntry.VALUE_TYPE_PTR_FIELD_DESC);
         oprot.writeI32(struct.valueTypePtr);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TMapTypeEntryTupleSchemeFactory implements SchemeFactory {
      private TMapTypeEntryTupleSchemeFactory() {
      }

      public TMapTypeEntryTupleScheme getScheme() {
         return new TMapTypeEntryTupleScheme();
      }
   }

   private static class TMapTypeEntryTupleScheme extends TupleScheme {
      private TMapTypeEntryTupleScheme() {
      }

      public void write(TProtocol prot, TMapTypeEntry struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.keyTypePtr);
         oprot.writeI32(struct.valueTypePtr);
      }

      public void read(TProtocol prot, TMapTypeEntry struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.keyTypePtr = iprot.readI32();
         struct.setKeyTypePtrIsSet(true);
         struct.valueTypePtr = iprot.readI32();
         struct.setValueTypePtrIsSet(true);
      }
   }
}
