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
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
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
public class TUnionTypeEntry implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TUnionTypeEntry");
   private static final TField NAME_TO_TYPE_PTR_FIELD_DESC = new TField("nameToTypePtr", (byte)13, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TUnionTypeEntryStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TUnionTypeEntryTupleSchemeFactory();
   @Nullable
   private Map nameToTypePtr;
   public static final Map metaDataMap;

   public TUnionTypeEntry() {
   }

   public TUnionTypeEntry(Map nameToTypePtr) {
      this();
      this.nameToTypePtr = nameToTypePtr;
   }

   public TUnionTypeEntry(TUnionTypeEntry other) {
      if (other.isSetNameToTypePtr()) {
         Map<String, Integer> __this__nameToTypePtr = new HashMap(other.nameToTypePtr.size());

         for(Map.Entry other_element : other.nameToTypePtr.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            Integer other_element_value = (Integer)other_element.getValue();
            __this__nameToTypePtr.put(other_element_key, other_element_value);
         }

         this.nameToTypePtr = __this__nameToTypePtr;
      }

   }

   public TUnionTypeEntry deepCopy() {
      return new TUnionTypeEntry(this);
   }

   public void clear() {
      this.nameToTypePtr = null;
   }

   public int getNameToTypePtrSize() {
      return this.nameToTypePtr == null ? 0 : this.nameToTypePtr.size();
   }

   public void putToNameToTypePtr(String key, int val) {
      if (this.nameToTypePtr == null) {
         this.nameToTypePtr = new HashMap();
      }

      this.nameToTypePtr.put(key, val);
   }

   @Nullable
   public Map getNameToTypePtr() {
      return this.nameToTypePtr;
   }

   public void setNameToTypePtr(@Nullable Map nameToTypePtr) {
      this.nameToTypePtr = nameToTypePtr;
   }

   public void unsetNameToTypePtr() {
      this.nameToTypePtr = null;
   }

   public boolean isSetNameToTypePtr() {
      return this.nameToTypePtr != null;
   }

   public void setNameToTypePtrIsSet(boolean value) {
      if (!value) {
         this.nameToTypePtr = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NAME_TO_TYPE_PTR:
            if (value == null) {
               this.unsetNameToTypePtr();
            } else {
               this.setNameToTypePtr((Map)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NAME_TO_TYPE_PTR:
            return this.getNameToTypePtr();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NAME_TO_TYPE_PTR:
               return this.isSetNameToTypePtr();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TUnionTypeEntry ? this.equals((TUnionTypeEntry)that) : false;
   }

   public boolean equals(TUnionTypeEntry that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_nameToTypePtr = this.isSetNameToTypePtr();
         boolean that_present_nameToTypePtr = that.isSetNameToTypePtr();
         if (this_present_nameToTypePtr || that_present_nameToTypePtr) {
            if (!this_present_nameToTypePtr || !that_present_nameToTypePtr) {
               return false;
            }

            if (!this.nameToTypePtr.equals(that.nameToTypePtr)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetNameToTypePtr() ? 131071 : 524287);
      if (this.isSetNameToTypePtr()) {
         hashCode = hashCode * 8191 + this.nameToTypePtr.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TUnionTypeEntry other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetNameToTypePtr(), other.isSetNameToTypePtr());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetNameToTypePtr()) {
               lastComparison = TBaseHelper.compareTo(this.nameToTypePtr, other.nameToTypePtr);
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
      return TUnionTypeEntry._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TUnionTypeEntry(");
      boolean first = true;
      sb.append("nameToTypePtr:");
      if (this.nameToTypePtr == null) {
         sb.append("null");
      } else {
         sb.append(this.nameToTypePtr);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetNameToTypePtr()) {
         throw new TProtocolException("Required field 'nameToTypePtr' is unset! Struct:" + this.toString());
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
      tmpMap.put(TUnionTypeEntry._Fields.NAME_TO_TYPE_PTR, new FieldMetaData("nameToTypePtr", (byte)1, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)8, "TTypeEntryPtr"))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TUnionTypeEntry.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NAME_TO_TYPE_PTR((short)1, "nameToTypePtr");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NAME_TO_TYPE_PTR;
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

   private static class TUnionTypeEntryStandardSchemeFactory implements SchemeFactory {
      private TUnionTypeEntryStandardSchemeFactory() {
      }

      public TUnionTypeEntryStandardScheme getScheme() {
         return new TUnionTypeEntryStandardScheme();
      }
   }

   private static class TUnionTypeEntryStandardScheme extends StandardScheme {
      private TUnionTypeEntryStandardScheme() {
      }

      public void read(TProtocol iprot, TUnionTypeEntry struct) throws TException {
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
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map20 = iprot.readMapBegin();
                  struct.nameToTypePtr = new HashMap(2 * _map20.size);

                  for(int _i23 = 0; _i23 < _map20.size; ++_i23) {
                     String _key21 = iprot.readString();
                     int _val22 = iprot.readI32();
                     struct.nameToTypePtr.put(_key21, _val22);
                  }

                  iprot.readMapEnd();
                  struct.setNameToTypePtrIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TUnionTypeEntry struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TUnionTypeEntry.STRUCT_DESC);
         if (struct.nameToTypePtr != null) {
            oprot.writeFieldBegin(TUnionTypeEntry.NAME_TO_TYPE_PTR_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)8, struct.nameToTypePtr.size()));

            for(Map.Entry _iter24 : struct.nameToTypePtr.entrySet()) {
               oprot.writeString((String)_iter24.getKey());
               oprot.writeI32((Integer)_iter24.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TUnionTypeEntryTupleSchemeFactory implements SchemeFactory {
      private TUnionTypeEntryTupleSchemeFactory() {
      }

      public TUnionTypeEntryTupleScheme getScheme() {
         return new TUnionTypeEntryTupleScheme();
      }
   }

   private static class TUnionTypeEntryTupleScheme extends TupleScheme {
      private TUnionTypeEntryTupleScheme() {
      }

      public void write(TProtocol prot, TUnionTypeEntry struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.nameToTypePtr.size());

         for(Map.Entry _iter25 : struct.nameToTypePtr.entrySet()) {
            oprot.writeString((String)_iter25.getKey());
            oprot.writeI32((Integer)_iter25.getValue());
         }

      }

      public void read(TProtocol prot, TUnionTypeEntry struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TMap _map26 = iprot.readMapBegin((byte)11, (byte)8);
         struct.nameToTypePtr = new HashMap(2 * _map26.size);

         for(int _i29 = 0; _i29 < _map26.size; ++_i29) {
            String _key27 = iprot.readString();
            int _val28 = iprot.readI32();
            struct.nameToTypePtr.put(_key27, _val28);
         }

         struct.setNameToTypePtrIsSet(true);
      }
   }
}
