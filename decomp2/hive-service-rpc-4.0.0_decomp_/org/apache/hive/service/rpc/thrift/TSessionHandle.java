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
public class TSessionHandle implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TSessionHandle");
   private static final TField SESSION_ID_FIELD_DESC = new TField("sessionId", (byte)12, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TSessionHandleStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TSessionHandleTupleSchemeFactory();
   @Nullable
   private THandleIdentifier sessionId;
   public static final Map metaDataMap;

   public TSessionHandle() {
   }

   public TSessionHandle(THandleIdentifier sessionId) {
      this();
      this.sessionId = sessionId;
   }

   public TSessionHandle(TSessionHandle other) {
      if (other.isSetSessionId()) {
         this.sessionId = new THandleIdentifier(other.sessionId);
      }

   }

   public TSessionHandle deepCopy() {
      return new TSessionHandle(this);
   }

   public void clear() {
      this.sessionId = null;
   }

   @Nullable
   public THandleIdentifier getSessionId() {
      return this.sessionId;
   }

   public void setSessionId(@Nullable THandleIdentifier sessionId) {
      this.sessionId = sessionId;
   }

   public void unsetSessionId() {
      this.sessionId = null;
   }

   public boolean isSetSessionId() {
      return this.sessionId != null;
   }

   public void setSessionIdIsSet(boolean value) {
      if (!value) {
         this.sessionId = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SESSION_ID:
            if (value == null) {
               this.unsetSessionId();
            } else {
               this.setSessionId((THandleIdentifier)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_ID:
            return this.getSessionId();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SESSION_ID:
               return this.isSetSessionId();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TSessionHandle ? this.equals((TSessionHandle)that) : false;
   }

   public boolean equals(TSessionHandle that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_sessionId = this.isSetSessionId();
         boolean that_present_sessionId = that.isSetSessionId();
         if (this_present_sessionId || that_present_sessionId) {
            if (!this_present_sessionId || !that_present_sessionId) {
               return false;
            }

            if (!this.sessionId.equals(that.sessionId)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetSessionId() ? 131071 : 524287);
      if (this.isSetSessionId()) {
         hashCode = hashCode * 8191 + this.sessionId.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TSessionHandle other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSessionId(), other.isSetSessionId());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSessionId()) {
               lastComparison = TBaseHelper.compareTo(this.sessionId, other.sessionId);
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
      return TSessionHandle._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TSessionHandle(");
      boolean first = true;
      sb.append("sessionId:");
      if (this.sessionId == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionId);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionId()) {
         throw new TProtocolException("Required field 'sessionId' is unset! Struct:" + this.toString());
      } else {
         if (this.sessionId != null) {
            this.sessionId.validate();
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TSessionHandle._Fields.SESSION_ID, new FieldMetaData("sessionId", (byte)1, new StructMetaData((byte)12, THandleIdentifier.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TSessionHandle.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_ID((short)1, "sessionId");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_ID;
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

   private static class TSessionHandleStandardSchemeFactory implements SchemeFactory {
      private TSessionHandleStandardSchemeFactory() {
      }

      public TSessionHandleStandardScheme getScheme() {
         return new TSessionHandleStandardScheme();
      }
   }

   private static class TSessionHandleStandardScheme extends StandardScheme {
      private TSessionHandleStandardScheme() {
      }

      public void read(TProtocol iprot, TSessionHandle struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.sessionId = new THandleIdentifier();
                     struct.sessionId.read(iprot);
                     struct.setSessionIdIsSet(true);
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

      public void write(TProtocol oprot, TSessionHandle struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TSessionHandle.STRUCT_DESC);
         if (struct.sessionId != null) {
            oprot.writeFieldBegin(TSessionHandle.SESSION_ID_FIELD_DESC);
            struct.sessionId.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TSessionHandleTupleSchemeFactory implements SchemeFactory {
      private TSessionHandleTupleSchemeFactory() {
      }

      public TSessionHandleTupleScheme getScheme() {
         return new TSessionHandleTupleScheme();
      }
   }

   private static class TSessionHandleTupleScheme extends TupleScheme {
      private TSessionHandleTupleScheme() {
      }

      public void write(TProtocol prot, TSessionHandle struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionId.write(oprot);
      }

      public void read(TProtocol prot, TSessionHandle struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionId = new THandleIdentifier();
         struct.sessionId.read(iprot);
         struct.setSessionIdIsSet(true);
      }
   }
}
