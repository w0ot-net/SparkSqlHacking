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

public class IndexPageHeader implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("IndexPageHeader");
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new IndexPageHeaderStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new IndexPageHeaderTupleSchemeFactory();
   public static final Map metaDataMap;

   public IndexPageHeader() {
   }

   public IndexPageHeader(IndexPageHeader other) {
   }

   public IndexPageHeader deepCopy() {
      return new IndexPageHeader(this);
   }

   public void clear() {
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      int var10000 = null.$SwitchMap$org$apache$parquet$format$IndexPageHeader$_Fields[field.ordinal()];
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      int var10000 = null.$SwitchMap$org$apache$parquet$format$IndexPageHeader$_Fields[field.ordinal()];
      throw new IllegalStateException();
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         int var10000 = null.$SwitchMap$org$apache$parquet$format$IndexPageHeader$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }
   }

   public boolean equals(Object that) {
      return that instanceof IndexPageHeader ? this.equals((IndexPageHeader)that) : false;
   }

   public boolean equals(IndexPageHeader that) {
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

   public int compareTo(IndexPageHeader other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         return 0;
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return IndexPageHeader._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("IndexPageHeader(");
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
      FieldMetaData.addStructMetaDataMap(IndexPageHeader.class, metaDataMap);
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

   private static class IndexPageHeaderStandardSchemeFactory implements SchemeFactory {
      private IndexPageHeaderStandardSchemeFactory() {
      }

      public IndexPageHeaderStandardScheme getScheme() {
         return new IndexPageHeaderStandardScheme();
      }
   }

   private static class IndexPageHeaderStandardScheme extends StandardScheme {
      private IndexPageHeaderStandardScheme() {
      }

      public void read(TProtocol iprot, IndexPageHeader struct) throws TException {
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

      public void write(TProtocol oprot, IndexPageHeader struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(IndexPageHeader.STRUCT_DESC);
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class IndexPageHeaderTupleSchemeFactory implements SchemeFactory {
      private IndexPageHeaderTupleSchemeFactory() {
      }

      public IndexPageHeaderTupleScheme getScheme() {
         return new IndexPageHeaderTupleScheme();
      }
   }

   private static class IndexPageHeaderTupleScheme extends TupleScheme {
      private IndexPageHeaderTupleScheme() {
      }

      public void write(TProtocol prot, IndexPageHeader struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
      }

      public void read(TProtocol prot, IndexPageHeader struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
      }
   }
}
