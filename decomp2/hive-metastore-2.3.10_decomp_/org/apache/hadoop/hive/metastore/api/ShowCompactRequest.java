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
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class ShowCompactRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ShowCompactRequest");
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ShowCompactRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ShowCompactRequestTupleSchemeFactory();
   public static final Map metaDataMap;

   public ShowCompactRequest() {
   }

   public ShowCompactRequest(ShowCompactRequest other) {
   }

   public ShowCompactRequest deepCopy() {
      return new ShowCompactRequest(this);
   }

   public void clear() {
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      int var10000 = null.$SwitchMap$org$apache$hadoop$hive$metastore$api$ShowCompactRequest$_Fields[field.ordinal()];
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      int var10000 = null.$SwitchMap$org$apache$hadoop$hive$metastore$api$ShowCompactRequest$_Fields[field.ordinal()];
      throw new IllegalStateException();
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         int var10000 = null.$SwitchMap$org$apache$hadoop$hive$metastore$api$ShowCompactRequest$_Fields[field.ordinal()];
         throw new IllegalStateException();
      }
   }

   public boolean equals(Object that) {
      return that instanceof ShowCompactRequest ? this.equals((ShowCompactRequest)that) : false;
   }

   public boolean equals(ShowCompactRequest that) {
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

   public int compareTo(ShowCompactRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         return 0;
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ShowCompactRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ShowCompactRequest(");
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
      FieldMetaData.addStructMetaDataMap(ShowCompactRequest.class, metaDataMap);
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

   private static class ShowCompactRequestStandardSchemeFactory implements SchemeFactory {
      private ShowCompactRequestStandardSchemeFactory() {
      }

      public ShowCompactRequestStandardScheme getScheme() {
         return new ShowCompactRequestStandardScheme();
      }
   }

   private static class ShowCompactRequestStandardScheme extends StandardScheme {
      private ShowCompactRequestStandardScheme() {
      }

      public void read(TProtocol iprot, ShowCompactRequest struct) throws TException {
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

      public void write(TProtocol oprot, ShowCompactRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ShowCompactRequest.STRUCT_DESC);
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ShowCompactRequestTupleSchemeFactory implements SchemeFactory {
      private ShowCompactRequestTupleSchemeFactory() {
      }

      public ShowCompactRequestTupleScheme getScheme() {
         return new ShowCompactRequestTupleScheme();
      }
   }

   private static class ShowCompactRequestTupleScheme extends TupleScheme {
      private ShowCompactRequestTupleScheme() {
      }

      public void write(TProtocol prot, ShowCompactRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
      }

      public void read(TProtocol prot, ShowCompactRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
      }
   }
}
