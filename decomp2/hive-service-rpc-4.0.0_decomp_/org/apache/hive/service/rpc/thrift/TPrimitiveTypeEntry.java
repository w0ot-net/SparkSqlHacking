package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.StructMetaData;
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
public class TPrimitiveTypeEntry implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TPrimitiveTypeEntry");
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)1);
   private static final TField TYPE_QUALIFIERS_FIELD_DESC = new TField("typeQualifiers", (byte)12, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TPrimitiveTypeEntryStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TPrimitiveTypeEntryTupleSchemeFactory();
   @Nullable
   private TTypeId type;
   @Nullable
   private TTypeQualifiers typeQualifiers;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TPrimitiveTypeEntry() {
   }

   public TPrimitiveTypeEntry(TTypeId type) {
      this();
      this.type = type;
   }

   public TPrimitiveTypeEntry(TPrimitiveTypeEntry other) {
      if (other.isSetType()) {
         this.type = other.type;
      }

      if (other.isSetTypeQualifiers()) {
         this.typeQualifiers = new TTypeQualifiers(other.typeQualifiers);
      }

   }

   public TPrimitiveTypeEntry deepCopy() {
      return new TPrimitiveTypeEntry(this);
   }

   public void clear() {
      this.type = null;
      this.typeQualifiers = null;
   }

   @Nullable
   public TTypeId getType() {
      return this.type;
   }

   public void setType(@Nullable TTypeId type) {
      this.type = type;
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   @Nullable
   public TTypeQualifiers getTypeQualifiers() {
      return this.typeQualifiers;
   }

   public void setTypeQualifiers(@Nullable TTypeQualifiers typeQualifiers) {
      this.typeQualifiers = typeQualifiers;
   }

   public void unsetTypeQualifiers() {
      this.typeQualifiers = null;
   }

   public boolean isSetTypeQualifiers() {
      return this.typeQualifiers != null;
   }

   public void setTypeQualifiersIsSet(boolean value) {
      if (!value) {
         this.typeQualifiers = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((TTypeId)value);
            }
            break;
         case TYPE_QUALIFIERS:
            if (value == null) {
               this.unsetTypeQualifiers();
            } else {
               this.setTypeQualifiers((TTypeQualifiers)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TYPE:
            return this.getType();
         case TYPE_QUALIFIERS:
            return this.getTypeQualifiers();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TYPE:
               return this.isSetType();
            case TYPE_QUALIFIERS:
               return this.isSetTypeQualifiers();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TPrimitiveTypeEntry ? this.equals((TPrimitiveTypeEntry)that) : false;
   }

   public boolean equals(TPrimitiveTypeEntry that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         boolean this_present_typeQualifiers = this.isSetTypeQualifiers();
         boolean that_present_typeQualifiers = that.isSetTypeQualifiers();
         if (this_present_typeQualifiers || that_present_typeQualifiers) {
            if (!this_present_typeQualifiers || !that_present_typeQualifiers) {
               return false;
            }

            if (!this.typeQualifiers.equals(that.typeQualifiers)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetTypeQualifiers() ? 131071 : 524287);
      if (this.isSetTypeQualifiers()) {
         hashCode = hashCode * 8191 + this.typeQualifiers.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TPrimitiveTypeEntry other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetType()) {
               lastComparison = TBaseHelper.compareTo(this.type, other.type);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTypeQualifiers(), other.isSetTypeQualifiers());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTypeQualifiers()) {
                  lastComparison = TBaseHelper.compareTo(this.typeQualifiers, other.typeQualifiers);
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
      return TPrimitiveTypeEntry._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TPrimitiveTypeEntry(");
      boolean first = true;
      sb.append("type:");
      if (this.type == null) {
         sb.append("null");
      } else {
         sb.append(this.type);
      }

      first = false;
      if (this.isSetTypeQualifiers()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("typeQualifiers:");
         if (this.typeQualifiers == null) {
            sb.append("null");
         } else {
            sb.append(this.typeQualifiers);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetType()) {
         throw new TProtocolException("Required field 'type' is unset! Struct:" + this.toString());
      } else {
         if (this.typeQualifiers != null) {
            this.typeQualifiers.validate();
         }

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
      optionals = new _Fields[]{TPrimitiveTypeEntry._Fields.TYPE_QUALIFIERS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TPrimitiveTypeEntry._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)16, TTypeId.class)));
      tmpMap.put(TPrimitiveTypeEntry._Fields.TYPE_QUALIFIERS, new FieldMetaData("typeQualifiers", (byte)2, new StructMetaData((byte)12, TTypeQualifiers.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TPrimitiveTypeEntry.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPE((short)1, "type"),
      TYPE_QUALIFIERS((short)2, "typeQualifiers");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPE;
            case 2:
               return TYPE_QUALIFIERS;
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

   private static class TPrimitiveTypeEntryStandardSchemeFactory implements SchemeFactory {
      private TPrimitiveTypeEntryStandardSchemeFactory() {
      }

      public TPrimitiveTypeEntryStandardScheme getScheme() {
         return new TPrimitiveTypeEntryStandardScheme();
      }
   }

   private static class TPrimitiveTypeEntryStandardScheme extends StandardScheme {
      private TPrimitiveTypeEntryStandardScheme() {
      }

      public void read(TProtocol iprot, TPrimitiveTypeEntry struct) throws TException {
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
                     struct.type = TTypeId.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.typeQualifiers = new TTypeQualifiers();
                     struct.typeQualifiers.read(iprot);
                     struct.setTypeQualifiersIsSet(true);
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

      public void write(TProtocol oprot, TPrimitiveTypeEntry struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TPrimitiveTypeEntry.STRUCT_DESC);
         if (struct.type != null) {
            oprot.writeFieldBegin(TPrimitiveTypeEntry.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.typeQualifiers != null && struct.isSetTypeQualifiers()) {
            oprot.writeFieldBegin(TPrimitiveTypeEntry.TYPE_QUALIFIERS_FIELD_DESC);
            struct.typeQualifiers.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TPrimitiveTypeEntryTupleSchemeFactory implements SchemeFactory {
      private TPrimitiveTypeEntryTupleSchemeFactory() {
      }

      public TPrimitiveTypeEntryTupleScheme getScheme() {
         return new TPrimitiveTypeEntryTupleScheme();
      }
   }

   private static class TPrimitiveTypeEntryTupleScheme extends TupleScheme {
      private TPrimitiveTypeEntryTupleScheme() {
      }

      public void write(TProtocol prot, TPrimitiveTypeEntry struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.type.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetTypeQualifiers()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetTypeQualifiers()) {
            struct.typeQualifiers.write(oprot);
         }

      }

      public void read(TProtocol prot, TPrimitiveTypeEntry struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.type = TTypeId.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.typeQualifiers = new TTypeQualifiers();
            struct.typeQualifiers.read(iprot);
            struct.setTypeQualifiersIsSet(true);
         }

      }
   }
}
