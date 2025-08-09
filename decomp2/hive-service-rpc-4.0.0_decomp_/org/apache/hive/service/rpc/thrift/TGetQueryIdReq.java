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
public class TGetQueryIdReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetQueryIdReq");
   private static final TField OPERATION_HANDLE_FIELD_DESC = new TField("operationHandle", (byte)12, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetQueryIdReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetQueryIdReqTupleSchemeFactory();
   @Nullable
   private TOperationHandle operationHandle;
   public static final Map metaDataMap;

   public TGetQueryIdReq() {
   }

   public TGetQueryIdReq(TOperationHandle operationHandle) {
      this();
      this.operationHandle = operationHandle;
   }

   public TGetQueryIdReq(TGetQueryIdReq other) {
      if (other.isSetOperationHandle()) {
         this.operationHandle = new TOperationHandle(other.operationHandle);
      }

   }

   public TGetQueryIdReq deepCopy() {
      return new TGetQueryIdReq(this);
   }

   public void clear() {
      this.operationHandle = null;
   }

   @Nullable
   public TOperationHandle getOperationHandle() {
      return this.operationHandle;
   }

   public void setOperationHandle(@Nullable TOperationHandle operationHandle) {
      this.operationHandle = operationHandle;
   }

   public void unsetOperationHandle() {
      this.operationHandle = null;
   }

   public boolean isSetOperationHandle() {
      return this.operationHandle != null;
   }

   public void setOperationHandleIsSet(boolean value) {
      if (!value) {
         this.operationHandle = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case OPERATION_HANDLE:
            if (value == null) {
               this.unsetOperationHandle();
            } else {
               this.setOperationHandle((TOperationHandle)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OPERATION_HANDLE:
            return this.getOperationHandle();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case OPERATION_HANDLE:
               return this.isSetOperationHandle();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetQueryIdReq ? this.equals((TGetQueryIdReq)that) : false;
   }

   public boolean equals(TGetQueryIdReq that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_operationHandle = this.isSetOperationHandle();
         boolean that_present_operationHandle = that.isSetOperationHandle();
         if (this_present_operationHandle || that_present_operationHandle) {
            if (!this_present_operationHandle || !that_present_operationHandle) {
               return false;
            }

            if (!this.operationHandle.equals(that.operationHandle)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetOperationHandle() ? 131071 : 524287);
      if (this.isSetOperationHandle()) {
         hashCode = hashCode * 8191 + this.operationHandle.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TGetQueryIdReq other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetOperationHandle(), other.isSetOperationHandle());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetOperationHandle()) {
               lastComparison = TBaseHelper.compareTo(this.operationHandle, other.operationHandle);
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
      return TGetQueryIdReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetQueryIdReq(");
      boolean first = true;
      sb.append("operationHandle:");
      if (this.operationHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.operationHandle);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetOperationHandle()) {
         throw new TProtocolException("Required field 'operationHandle' is unset! Struct:" + this.toString());
      } else {
         if (this.operationHandle != null) {
            this.operationHandle.validate();
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
      tmpMap.put(TGetQueryIdReq._Fields.OPERATION_HANDLE, new FieldMetaData("operationHandle", (byte)1, new StructMetaData((byte)12, TOperationHandle.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetQueryIdReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OPERATION_HANDLE((short)1, "operationHandle");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OPERATION_HANDLE;
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

   private static class TGetQueryIdReqStandardSchemeFactory implements SchemeFactory {
      private TGetQueryIdReqStandardSchemeFactory() {
      }

      public TGetQueryIdReqStandardScheme getScheme() {
         return new TGetQueryIdReqStandardScheme();
      }
   }

   private static class TGetQueryIdReqStandardScheme extends StandardScheme {
      private TGetQueryIdReqStandardScheme() {
      }

      public void read(TProtocol iprot, TGetQueryIdReq struct) throws TException {
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
                     struct.operationHandle = new TOperationHandle();
                     struct.operationHandle.read(iprot);
                     struct.setOperationHandleIsSet(true);
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

      public void write(TProtocol oprot, TGetQueryIdReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetQueryIdReq.STRUCT_DESC);
         if (struct.operationHandle != null) {
            oprot.writeFieldBegin(TGetQueryIdReq.OPERATION_HANDLE_FIELD_DESC);
            struct.operationHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetQueryIdReqTupleSchemeFactory implements SchemeFactory {
      private TGetQueryIdReqTupleSchemeFactory() {
      }

      public TGetQueryIdReqTupleScheme getScheme() {
         return new TGetQueryIdReqTupleScheme();
      }
   }

   private static class TGetQueryIdReqTupleScheme extends TupleScheme {
      private TGetQueryIdReqTupleScheme() {
      }

      public void write(TProtocol prot, TGetQueryIdReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.operationHandle.write(oprot);
      }

      public void read(TProtocol prot, TGetQueryIdReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.operationHandle = new TOperationHandle();
         struct.operationHandle.read(iprot);
         struct.setOperationHandleIsSet(true);
      }
   }
}
