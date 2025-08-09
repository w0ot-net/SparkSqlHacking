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
import org.apache.thrift.meta_data.StructMetaData;
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
public class TTypeQualifiers implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TTypeQualifiers");
   private static final TField QUALIFIERS_FIELD_DESC = new TField("qualifiers", (byte)13, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TTypeQualifiersStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TTypeQualifiersTupleSchemeFactory();
   @Nullable
   private Map qualifiers;
   public static final Map metaDataMap;

   public TTypeQualifiers() {
   }

   public TTypeQualifiers(Map qualifiers) {
      this();
      this.qualifiers = qualifiers;
   }

   public TTypeQualifiers(TTypeQualifiers other) {
      if (other.isSetQualifiers()) {
         Map<String, TTypeQualifierValue> __this__qualifiers = new HashMap(other.qualifiers.size());

         for(Map.Entry other_element : other.qualifiers.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            TTypeQualifierValue other_element_value = (TTypeQualifierValue)other_element.getValue();
            TTypeQualifierValue __this__qualifiers_copy_value = new TTypeQualifierValue(other_element_value);
            __this__qualifiers.put(other_element_key, __this__qualifiers_copy_value);
         }

         this.qualifiers = __this__qualifiers;
      }

   }

   public TTypeQualifiers deepCopy() {
      return new TTypeQualifiers(this);
   }

   public void clear() {
      this.qualifiers = null;
   }

   public int getQualifiersSize() {
      return this.qualifiers == null ? 0 : this.qualifiers.size();
   }

   public void putToQualifiers(String key, TTypeQualifierValue val) {
      if (this.qualifiers == null) {
         this.qualifiers = new HashMap();
      }

      this.qualifiers.put(key, val);
   }

   @Nullable
   public Map getQualifiers() {
      return this.qualifiers;
   }

   public void setQualifiers(@Nullable Map qualifiers) {
      this.qualifiers = qualifiers;
   }

   public void unsetQualifiers() {
      this.qualifiers = null;
   }

   public boolean isSetQualifiers() {
      return this.qualifiers != null;
   }

   public void setQualifiersIsSet(boolean value) {
      if (!value) {
         this.qualifiers = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case QUALIFIERS:
            if (value == null) {
               this.unsetQualifiers();
            } else {
               this.setQualifiers((Map)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case QUALIFIERS:
            return this.getQualifiers();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case QUALIFIERS:
               return this.isSetQualifiers();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TTypeQualifiers ? this.equals((TTypeQualifiers)that) : false;
   }

   public boolean equals(TTypeQualifiers that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_qualifiers = this.isSetQualifiers();
         boolean that_present_qualifiers = that.isSetQualifiers();
         if (this_present_qualifiers || that_present_qualifiers) {
            if (!this_present_qualifiers || !that_present_qualifiers) {
               return false;
            }

            if (!this.qualifiers.equals(that.qualifiers)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetQualifiers() ? 131071 : 524287);
      if (this.isSetQualifiers()) {
         hashCode = hashCode * 8191 + this.qualifiers.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TTypeQualifiers other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetQualifiers(), other.isSetQualifiers());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetQualifiers()) {
               lastComparison = TBaseHelper.compareTo(this.qualifiers, other.qualifiers);
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
      return TTypeQualifiers._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TTypeQualifiers(");
      boolean first = true;
      sb.append("qualifiers:");
      if (this.qualifiers == null) {
         sb.append("null");
      } else {
         sb.append(this.qualifiers);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetQualifiers()) {
         throw new TProtocolException("Required field 'qualifiers' is unset! Struct:" + this.toString());
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
      tmpMap.put(TTypeQualifiers._Fields.QUALIFIERS, new FieldMetaData("qualifiers", (byte)1, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new StructMetaData((byte)12, TTypeQualifierValue.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TTypeQualifiers.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      QUALIFIERS((short)1, "qualifiers");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return QUALIFIERS;
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

   private static class TTypeQualifiersStandardSchemeFactory implements SchemeFactory {
      private TTypeQualifiersStandardSchemeFactory() {
      }

      public TTypeQualifiersStandardScheme getScheme() {
         return new TTypeQualifiersStandardScheme();
      }
   }

   private static class TTypeQualifiersStandardScheme extends StandardScheme {
      private TTypeQualifiersStandardScheme() {
      }

      public void read(TProtocol iprot, TTypeQualifiers struct) throws TException {
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

                  TMap _map0 = iprot.readMapBegin();
                  struct.qualifiers = new HashMap(2 * _map0.size);

                  for(int _i3 = 0; _i3 < _map0.size; ++_i3) {
                     String _key1 = iprot.readString();
                     TTypeQualifierValue _val2 = new TTypeQualifierValue();
                     _val2.read(iprot);
                     struct.qualifiers.put(_key1, _val2);
                  }

                  iprot.readMapEnd();
                  struct.setQualifiersIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TTypeQualifiers struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TTypeQualifiers.STRUCT_DESC);
         if (struct.qualifiers != null) {
            oprot.writeFieldBegin(TTypeQualifiers.QUALIFIERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)12, struct.qualifiers.size()));

            for(Map.Entry _iter4 : struct.qualifiers.entrySet()) {
               oprot.writeString((String)_iter4.getKey());
               ((TTypeQualifierValue)_iter4.getValue()).write(oprot);
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TTypeQualifiersTupleSchemeFactory implements SchemeFactory {
      private TTypeQualifiersTupleSchemeFactory() {
      }

      public TTypeQualifiersTupleScheme getScheme() {
         return new TTypeQualifiersTupleScheme();
      }
   }

   private static class TTypeQualifiersTupleScheme extends TupleScheme {
      private TTypeQualifiersTupleScheme() {
      }

      public void write(TProtocol prot, TTypeQualifiers struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.qualifiers.size());

         for(Map.Entry _iter5 : struct.qualifiers.entrySet()) {
            oprot.writeString((String)_iter5.getKey());
            ((TTypeQualifierValue)_iter5.getValue()).write(oprot);
         }

      }

      public void read(TProtocol prot, TTypeQualifiers struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TMap _map6 = iprot.readMapBegin((byte)11, (byte)12);
         struct.qualifiers = new HashMap(2 * _map6.size);

         for(int _i9 = 0; _i9 < _map6.size; ++_i9) {
            String _key7 = iprot.readString();
            TTypeQualifierValue _val8 = new TTypeQualifierValue();
            _val8.read(iprot);
            struct.qualifiers.put(_key7, _val8);
         }

         struct.setQualifiersIsSet(true);
      }
   }
}
