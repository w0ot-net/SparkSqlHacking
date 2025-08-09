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
public class TArrayTypeEntry implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TArrayTypeEntry");
   private static final TField OBJECT_TYPE_PTR_FIELD_DESC = new TField("objectTypePtr", (byte)8, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TArrayTypeEntryStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TArrayTypeEntryTupleSchemeFactory();
   private int objectTypePtr;
   private static final int __OBJECTTYPEPTR_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public TArrayTypeEntry() {
      this.__isset_bitfield = 0;
   }

   public TArrayTypeEntry(int objectTypePtr) {
      this();
      this.objectTypePtr = objectTypePtr;
      this.setObjectTypePtrIsSet(true);
   }

   public TArrayTypeEntry(TArrayTypeEntry other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.objectTypePtr = other.objectTypePtr;
   }

   public TArrayTypeEntry deepCopy() {
      return new TArrayTypeEntry(this);
   }

   public void clear() {
      this.setObjectTypePtrIsSet(false);
      this.objectTypePtr = 0;
   }

   public int getObjectTypePtr() {
      return this.objectTypePtr;
   }

   public void setObjectTypePtr(int objectTypePtr) {
      this.objectTypePtr = objectTypePtr;
      this.setObjectTypePtrIsSet(true);
   }

   public void unsetObjectTypePtr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetObjectTypePtr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setObjectTypePtrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case OBJECT_TYPE_PTR:
            if (value == null) {
               this.unsetObjectTypePtr();
            } else {
               this.setObjectTypePtr((Integer)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OBJECT_TYPE_PTR:
            return this.getObjectTypePtr();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case OBJECT_TYPE_PTR:
               return this.isSetObjectTypePtr();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TArrayTypeEntry ? this.equals((TArrayTypeEntry)that) : false;
   }

   public boolean equals(TArrayTypeEntry that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_objectTypePtr = true;
         boolean that_present_objectTypePtr = true;
         if (this_present_objectTypePtr || that_present_objectTypePtr) {
            if (!this_present_objectTypePtr || !that_present_objectTypePtr) {
               return false;
            }

            if (this.objectTypePtr != that.objectTypePtr) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.objectTypePtr;
      return hashCode;
   }

   public int compareTo(TArrayTypeEntry other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetObjectTypePtr(), other.isSetObjectTypePtr());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetObjectTypePtr()) {
               lastComparison = TBaseHelper.compareTo(this.objectTypePtr, other.objectTypePtr);
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
      return TArrayTypeEntry._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TArrayTypeEntry(");
      boolean first = true;
      sb.append("objectTypePtr:");
      sb.append(this.objectTypePtr);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetObjectTypePtr()) {
         throw new TProtocolException("Required field 'objectTypePtr' is unset! Struct:" + this.toString());
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
      tmpMap.put(TArrayTypeEntry._Fields.OBJECT_TYPE_PTR, new FieldMetaData("objectTypePtr", (byte)1, new FieldValueMetaData((byte)8, "TTypeEntryPtr")));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TArrayTypeEntry.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OBJECT_TYPE_PTR((short)1, "objectTypePtr");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OBJECT_TYPE_PTR;
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

   private static class TArrayTypeEntryStandardSchemeFactory implements SchemeFactory {
      private TArrayTypeEntryStandardSchemeFactory() {
      }

      public TArrayTypeEntryStandardScheme getScheme() {
         return new TArrayTypeEntryStandardScheme();
      }
   }

   private static class TArrayTypeEntryStandardScheme extends StandardScheme {
      private TArrayTypeEntryStandardScheme() {
      }

      public void read(TProtocol iprot, TArrayTypeEntry struct) throws TException {
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
                     struct.objectTypePtr = iprot.readI32();
                     struct.setObjectTypePtrIsSet(true);
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

      public void write(TProtocol oprot, TArrayTypeEntry struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TArrayTypeEntry.STRUCT_DESC);
         oprot.writeFieldBegin(TArrayTypeEntry.OBJECT_TYPE_PTR_FIELD_DESC);
         oprot.writeI32(struct.objectTypePtr);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TArrayTypeEntryTupleSchemeFactory implements SchemeFactory {
      private TArrayTypeEntryTupleSchemeFactory() {
      }

      public TArrayTypeEntryTupleScheme getScheme() {
         return new TArrayTypeEntryTupleScheme();
      }
   }

   private static class TArrayTypeEntryTupleScheme extends TupleScheme {
      private TArrayTypeEntryTupleScheme() {
      }

      public void write(TProtocol prot, TArrayTypeEntry struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.objectTypePtr);
      }

      public void read(TProtocol prot, TArrayTypeEntry struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.objectTypePtr = iprot.readI32();
         struct.setObjectTypePtrIsSet(true);
      }
   }
}
