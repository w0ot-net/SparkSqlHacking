package org.apache.hadoop.hive.metastore.api;

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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class TxnOpenException extends TException implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TxnOpenException");
   private static final TField MESSAGE_FIELD_DESC = new TField("message", (byte)11, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TxnOpenExceptionStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TxnOpenExceptionTupleSchemeFactory();
   @Nullable
   private String message;
   public static final Map metaDataMap;

   public TxnOpenException() {
   }

   public TxnOpenException(String message) {
      this();
      this.message = message;
   }

   public TxnOpenException(TxnOpenException other) {
      if (other.isSetMessage()) {
         this.message = other.message;
      }

   }

   public TxnOpenException deepCopy() {
      return new TxnOpenException(this);
   }

   public void clear() {
      this.message = null;
   }

   @Nullable
   public String getMessage() {
      return this.message;
   }

   public void setMessage(@Nullable String message) {
      this.message = message;
   }

   public void unsetMessage() {
      this.message = null;
   }

   public boolean isSetMessage() {
      return this.message != null;
   }

   public void setMessageIsSet(boolean value) {
      if (!value) {
         this.message = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case MESSAGE:
            if (value == null) {
               this.unsetMessage();
            } else {
               this.setMessage((String)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case MESSAGE:
            return this.getMessage();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case MESSAGE:
               return this.isSetMessage();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TxnOpenException ? this.equals((TxnOpenException)that) : false;
   }

   public boolean equals(TxnOpenException that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_message = this.isSetMessage();
         boolean that_present_message = that.isSetMessage();
         if (this_present_message || that_present_message) {
            if (!this_present_message || !that_present_message) {
               return false;
            }

            if (!this.message.equals(that.message)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetMessage() ? 131071 : 524287);
      if (this.isSetMessage()) {
         hashCode = hashCode * 8191 + this.message.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TxnOpenException other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMessage(), other.isSetMessage());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMessage()) {
               lastComparison = TBaseHelper.compareTo(this.message, other.message);
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
      return TxnOpenException._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TxnOpenException(");
      boolean first = true;
      sb.append("message:");
      if (this.message == null) {
         sb.append("null");
      } else {
         sb.append(this.message);
      }

      first = false;
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
      tmpMap.put(TxnOpenException._Fields.MESSAGE, new FieldMetaData("message", (byte)3, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TxnOpenException.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MESSAGE((short)1, "message");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MESSAGE;
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

   private static class TxnOpenExceptionStandardSchemeFactory implements SchemeFactory {
      private TxnOpenExceptionStandardSchemeFactory() {
      }

      public TxnOpenExceptionStandardScheme getScheme() {
         return new TxnOpenExceptionStandardScheme();
      }
   }

   private static class TxnOpenExceptionStandardScheme extends StandardScheme {
      private TxnOpenExceptionStandardScheme() {
      }

      public void read(TProtocol iprot, TxnOpenException struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.message = iprot.readString();
                     struct.setMessageIsSet(true);
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

      public void write(TProtocol oprot, TxnOpenException struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TxnOpenException.STRUCT_DESC);
         if (struct.message != null) {
            oprot.writeFieldBegin(TxnOpenException.MESSAGE_FIELD_DESC);
            oprot.writeString(struct.message);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TxnOpenExceptionTupleSchemeFactory implements SchemeFactory {
      private TxnOpenExceptionTupleSchemeFactory() {
      }

      public TxnOpenExceptionTupleScheme getScheme() {
         return new TxnOpenExceptionTupleScheme();
      }
   }

   private static class TxnOpenExceptionTupleScheme extends TupleScheme {
      private TxnOpenExceptionTupleScheme() {
      }

      public void write(TProtocol prot, TxnOpenException struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetMessage()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetMessage()) {
            oprot.writeString(struct.message);
         }

      }

      public void read(TProtocol prot, TxnOpenException struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.message = iprot.readString();
            struct.setMessageIsSet(true);
         }

      }
   }
}
