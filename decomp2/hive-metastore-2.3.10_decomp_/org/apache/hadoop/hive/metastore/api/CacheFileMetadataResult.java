package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
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

public class CacheFileMetadataResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CacheFileMetadataResult");
   private static final TField IS_SUPPORTED_FIELD_DESC = new TField("isSupported", (byte)2, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CacheFileMetadataResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CacheFileMetadataResultTupleSchemeFactory();
   private boolean isSupported;
   private static final int __ISSUPPORTED_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public CacheFileMetadataResult() {
      this.__isset_bitfield = 0;
   }

   public CacheFileMetadataResult(boolean isSupported) {
      this();
      this.isSupported = isSupported;
      this.setIsSupportedIsSet(true);
   }

   public CacheFileMetadataResult(CacheFileMetadataResult other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.isSupported = other.isSupported;
   }

   public CacheFileMetadataResult deepCopy() {
      return new CacheFileMetadataResult(this);
   }

   public void clear() {
      this.setIsSupportedIsSet(false);
      this.isSupported = false;
   }

   public boolean isIsSupported() {
      return this.isSupported;
   }

   public void setIsSupported(boolean isSupported) {
      this.isSupported = isSupported;
      this.setIsSupportedIsSet(true);
   }

   public void unsetIsSupported() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIsSupported() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIsSupportedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case IS_SUPPORTED:
            if (value == null) {
               this.unsetIsSupported();
            } else {
               this.setIsSupported((Boolean)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case IS_SUPPORTED:
            return this.isIsSupported();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case IS_SUPPORTED:
               return this.isSetIsSupported();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CacheFileMetadataResult ? this.equals((CacheFileMetadataResult)that) : false;
   }

   public boolean equals(CacheFileMetadataResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_isSupported = true;
         boolean that_present_isSupported = true;
         if (this_present_isSupported || that_present_isSupported) {
            if (!this_present_isSupported || !that_present_isSupported) {
               return false;
            }

            if (this.isSupported != that.isSupported) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSupported ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(CacheFileMetadataResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetIsSupported(), other.isSetIsSupported());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetIsSupported()) {
               lastComparison = TBaseHelper.compareTo(this.isSupported, other.isSupported);
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
      return CacheFileMetadataResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CacheFileMetadataResult(");
      boolean first = true;
      sb.append("isSupported:");
      sb.append(this.isSupported);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetIsSupported()) {
         throw new TProtocolException("Required field 'isSupported' is unset! Struct:" + this.toString());
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
      tmpMap.put(CacheFileMetadataResult._Fields.IS_SUPPORTED, new FieldMetaData("isSupported", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CacheFileMetadataResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      IS_SUPPORTED((short)1, "isSupported");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return IS_SUPPORTED;
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

   private static class CacheFileMetadataResultStandardSchemeFactory implements SchemeFactory {
      private CacheFileMetadataResultStandardSchemeFactory() {
      }

      public CacheFileMetadataResultStandardScheme getScheme() {
         return new CacheFileMetadataResultStandardScheme();
      }
   }

   private static class CacheFileMetadataResultStandardScheme extends StandardScheme {
      private CacheFileMetadataResultStandardScheme() {
      }

      public void read(TProtocol iprot, CacheFileMetadataResult struct) throws TException {
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
                  if (schemeField.type == 2) {
                     struct.isSupported = iprot.readBool();
                     struct.setIsSupportedIsSet(true);
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

      public void write(TProtocol oprot, CacheFileMetadataResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CacheFileMetadataResult.STRUCT_DESC);
         oprot.writeFieldBegin(CacheFileMetadataResult.IS_SUPPORTED_FIELD_DESC);
         oprot.writeBool(struct.isSupported);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CacheFileMetadataResultTupleSchemeFactory implements SchemeFactory {
      private CacheFileMetadataResultTupleSchemeFactory() {
      }

      public CacheFileMetadataResultTupleScheme getScheme() {
         return new CacheFileMetadataResultTupleScheme();
      }
   }

   private static class CacheFileMetadataResultTupleScheme extends TupleScheme {
      private CacheFileMetadataResultTupleScheme() {
      }

      public void write(TProtocol prot, CacheFileMetadataResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBool(struct.isSupported);
      }

      public void read(TProtocol prot, CacheFileMetadataResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.isSupported = iprot.readBool();
         struct.setIsSupportedIsSet(true);
      }
   }
}
