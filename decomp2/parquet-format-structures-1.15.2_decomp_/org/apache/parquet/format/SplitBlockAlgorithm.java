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

public class SplitBlockAlgorithm implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SplitBlockAlgorithm");
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SplitBlockAlgorithmStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SplitBlockAlgorithmTupleSchemeFactory();
   public static final Map metaDataMap;

   public SplitBlockAlgorithm() {
   }

   public SplitBlockAlgorithm(SplitBlockAlgorithm other) {
   }

   public SplitBlockAlgorithm deepCopy() {
      return new SplitBlockAlgorithm(this);
   }

   public void clear() {
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      int var10000 = null.$SwitchMap$org$apache$parquet$format$SplitBlockAlgorithm$_Fields[field.ordinal()];
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      int var10000 = null.$SwitchMap$org$apache$parquet$format$SplitBlockAlgorithm$_Fields[field.ordinal()];
      throw new IllegalStateException();
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         int var10000 = null.$SwitchMap$org$apache$parquet$format$SplitBlockAlgorithm$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }
   }

   public boolean equals(Object that) {
      return that instanceof SplitBlockAlgorithm ? this.equals((SplitBlockAlgorithm)that) : false;
   }

   public boolean equals(SplitBlockAlgorithm that) {
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

   public int compareTo(SplitBlockAlgorithm other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         return 0;
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return SplitBlockAlgorithm._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SplitBlockAlgorithm(");
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
      FieldMetaData.addStructMetaDataMap(SplitBlockAlgorithm.class, metaDataMap);
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

   private static class SplitBlockAlgorithmStandardSchemeFactory implements SchemeFactory {
      private SplitBlockAlgorithmStandardSchemeFactory() {
      }

      public SplitBlockAlgorithmStandardScheme getScheme() {
         return new SplitBlockAlgorithmStandardScheme();
      }
   }

   private static class SplitBlockAlgorithmStandardScheme extends StandardScheme {
      private SplitBlockAlgorithmStandardScheme() {
      }

      public void read(TProtocol iprot, SplitBlockAlgorithm struct) throws TException {
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

      public void write(TProtocol oprot, SplitBlockAlgorithm struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SplitBlockAlgorithm.STRUCT_DESC);
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SplitBlockAlgorithmTupleSchemeFactory implements SchemeFactory {
      private SplitBlockAlgorithmTupleSchemeFactory() {
      }

      public SplitBlockAlgorithmTupleScheme getScheme() {
         return new SplitBlockAlgorithmTupleScheme();
      }
   }

   private static class SplitBlockAlgorithmTupleScheme extends TupleScheme {
      private SplitBlockAlgorithmTupleScheme() {
      }

      public void write(TProtocol prot, SplitBlockAlgorithm struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
      }

      public void read(TProtocol prot, SplitBlockAlgorithm struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
      }
   }
}
