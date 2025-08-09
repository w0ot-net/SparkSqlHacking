package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class UUIDType implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("UUIDType");
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new UUIDTypeStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new UUIDTypeTupleSchemeFactory();
   public static final Map metaDataMap;

   public UUIDType() {
   }

   public UUIDType(UUIDType other) {
   }

   public UUIDType deepCopy() {
      return new UUIDType(this);
   }

   public void clear() {
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      int var10000 = null.$SwitchMap$org$apache$parquet$format$UUIDType$_Fields[field.ordinal()];
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      int var10000 = null.$SwitchMap$org$apache$parquet$format$UUIDType$_Fields[field.ordinal()];
      throw new IllegalStateException();
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         int var10000 = null.$SwitchMap$org$apache$parquet$format$UUIDType$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }
   }

   public boolean equals(Object that) {
      return that instanceof UUIDType ? this.equals((UUIDType)that) : false;
   }

   public boolean equals(UUIDType that) {
      if (that == null) {
         return false;
      } else {
         return this == that ? true : true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      return hashCode;
   }

   public int compareTo(UUIDType other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         return 0;
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return UUIDType._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("UUIDType(");
      boolean first = true;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
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
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(UUIDType.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
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

   private static class UUIDTypeStandardSchemeFactory implements SchemeFactory {
      private UUIDTypeStandardSchemeFactory() {
      }

      public UUIDTypeStandardScheme getScheme() {
         return new UUIDTypeStandardScheme();
      }
   }

   private static class UUIDTypeStandardScheme extends StandardScheme {
      private UUIDTypeStandardScheme() {
      }

      public void read(TProtocol iprot, UUIDType struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
                  iprot.readFieldEnd();
            }
         }
      }

      public void write(TProtocol oprot, UUIDType struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(UUIDType.STRUCT_DESC);
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class UUIDTypeTupleSchemeFactory implements SchemeFactory {
      private UUIDTypeTupleSchemeFactory() {
      }

      public UUIDTypeTupleScheme getScheme() {
         return new UUIDTypeTupleScheme();
      }
   }

   private static class UUIDTypeTupleScheme extends TupleScheme {
      private UUIDTypeTupleScheme() {
      }

      public void write(TProtocol prot, UUIDType struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
      }

      public void read(TProtocol prot, UUIDType struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
      }
   }
}
