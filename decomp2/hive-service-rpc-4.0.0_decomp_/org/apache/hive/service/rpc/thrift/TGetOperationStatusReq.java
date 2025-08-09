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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
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
public class TGetOperationStatusReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetOperationStatusReq");
   private static final TField OPERATION_HANDLE_FIELD_DESC = new TField("operationHandle", (byte)12, (short)1);
   private static final TField GET_PROGRESS_UPDATE_FIELD_DESC = new TField("getProgressUpdate", (byte)2, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetOperationStatusReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetOperationStatusReqTupleSchemeFactory();
   @Nullable
   private TOperationHandle operationHandle;
   private boolean getProgressUpdate;
   private static final int __GETPROGRESSUPDATE_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TGetOperationStatusReq() {
      this.__isset_bitfield = 0;
   }

   public TGetOperationStatusReq(TOperationHandle operationHandle) {
      this();
      this.operationHandle = operationHandle;
   }

   public TGetOperationStatusReq(TGetOperationStatusReq other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetOperationHandle()) {
         this.operationHandle = new TOperationHandle(other.operationHandle);
      }

      this.getProgressUpdate = other.getProgressUpdate;
   }

   public TGetOperationStatusReq deepCopy() {
      return new TGetOperationStatusReq(this);
   }

   public void clear() {
      this.operationHandle = null;
      this.setGetProgressUpdateIsSet(false);
      this.getProgressUpdate = false;
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

   public boolean isGetProgressUpdate() {
      return this.getProgressUpdate;
   }

   public void setGetProgressUpdate(boolean getProgressUpdate) {
      this.getProgressUpdate = getProgressUpdate;
      this.setGetProgressUpdateIsSet(true);
   }

   public void unsetGetProgressUpdate() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetGetProgressUpdate() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setGetProgressUpdateIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case OPERATION_HANDLE:
            if (value == null) {
               this.unsetOperationHandle();
            } else {
               this.setOperationHandle((TOperationHandle)value);
            }
            break;
         case GET_PROGRESS_UPDATE:
            if (value == null) {
               this.unsetGetProgressUpdate();
            } else {
               this.setGetProgressUpdate((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OPERATION_HANDLE:
            return this.getOperationHandle();
         case GET_PROGRESS_UPDATE:
            return this.isGetProgressUpdate();
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
            case GET_PROGRESS_UPDATE:
               return this.isSetGetProgressUpdate();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetOperationStatusReq ? this.equals((TGetOperationStatusReq)that) : false;
   }

   public boolean equals(TGetOperationStatusReq that) {
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

         boolean this_present_getProgressUpdate = this.isSetGetProgressUpdate();
         boolean that_present_getProgressUpdate = that.isSetGetProgressUpdate();
         if (this_present_getProgressUpdate || that_present_getProgressUpdate) {
            if (!this_present_getProgressUpdate || !that_present_getProgressUpdate) {
               return false;
            }

            if (this.getProgressUpdate != that.getProgressUpdate) {
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

      hashCode = hashCode * 8191 + (this.isSetGetProgressUpdate() ? 131071 : 524287);
      if (this.isSetGetProgressUpdate()) {
         hashCode = hashCode * 8191 + (this.getProgressUpdate ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(TGetOperationStatusReq other) {
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

            lastComparison = Boolean.compare(this.isSetGetProgressUpdate(), other.isSetGetProgressUpdate());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetGetProgressUpdate()) {
                  lastComparison = TBaseHelper.compareTo(this.getProgressUpdate, other.getProgressUpdate);
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
      return TGetOperationStatusReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetOperationStatusReq(");
      boolean first = true;
      sb.append("operationHandle:");
      if (this.operationHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.operationHandle);
      }

      first = false;
      if (this.isSetGetProgressUpdate()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("getProgressUpdate:");
         sb.append(this.getProgressUpdate);
         first = false;
      }

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
      optionals = new _Fields[]{TGetOperationStatusReq._Fields.GET_PROGRESS_UPDATE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetOperationStatusReq._Fields.OPERATION_HANDLE, new FieldMetaData("operationHandle", (byte)1, new StructMetaData((byte)12, TOperationHandle.class)));
      tmpMap.put(TGetOperationStatusReq._Fields.GET_PROGRESS_UPDATE, new FieldMetaData("getProgressUpdate", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetOperationStatusReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OPERATION_HANDLE((short)1, "operationHandle"),
      GET_PROGRESS_UPDATE((short)2, "getProgressUpdate");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OPERATION_HANDLE;
            case 2:
               return GET_PROGRESS_UPDATE;
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

   private static class TGetOperationStatusReqStandardSchemeFactory implements SchemeFactory {
      private TGetOperationStatusReqStandardSchemeFactory() {
      }

      public TGetOperationStatusReqStandardScheme getScheme() {
         return new TGetOperationStatusReqStandardScheme();
      }
   }

   private static class TGetOperationStatusReqStandardScheme extends StandardScheme {
      private TGetOperationStatusReqStandardScheme() {
      }

      public void read(TProtocol iprot, TGetOperationStatusReq struct) throws TException {
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
               case 2:
                  if (schemeField.type == 2) {
                     struct.getProgressUpdate = iprot.readBool();
                     struct.setGetProgressUpdateIsSet(true);
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

      public void write(TProtocol oprot, TGetOperationStatusReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetOperationStatusReq.STRUCT_DESC);
         if (struct.operationHandle != null) {
            oprot.writeFieldBegin(TGetOperationStatusReq.OPERATION_HANDLE_FIELD_DESC);
            struct.operationHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetGetProgressUpdate()) {
            oprot.writeFieldBegin(TGetOperationStatusReq.GET_PROGRESS_UPDATE_FIELD_DESC);
            oprot.writeBool(struct.getProgressUpdate);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetOperationStatusReqTupleSchemeFactory implements SchemeFactory {
      private TGetOperationStatusReqTupleSchemeFactory() {
      }

      public TGetOperationStatusReqTupleScheme getScheme() {
         return new TGetOperationStatusReqTupleScheme();
      }
   }

   private static class TGetOperationStatusReqTupleScheme extends TupleScheme {
      private TGetOperationStatusReqTupleScheme() {
      }

      public void write(TProtocol prot, TGetOperationStatusReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.operationHandle.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetGetProgressUpdate()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetGetProgressUpdate()) {
            oprot.writeBool(struct.getProgressUpdate);
         }

      }

      public void read(TProtocol prot, TGetOperationStatusReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.operationHandle = new TOperationHandle();
         struct.operationHandle.read(iprot);
         struct.setOperationHandleIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.getProgressUpdate = iprot.readBool();
            struct.setGetProgressUpdateIsSet(true);
         }

      }
   }
}
