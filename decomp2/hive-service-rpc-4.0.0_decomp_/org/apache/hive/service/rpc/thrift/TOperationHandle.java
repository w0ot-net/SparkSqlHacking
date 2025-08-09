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
import org.apache.thrift.meta_data.EnumMetaData;
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
public class TOperationHandle implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TOperationHandle");
   private static final TField OPERATION_ID_FIELD_DESC = new TField("operationId", (byte)12, (short)1);
   private static final TField OPERATION_TYPE_FIELD_DESC = new TField("operationType", (byte)8, (short)2);
   private static final TField HAS_RESULT_SET_FIELD_DESC = new TField("hasResultSet", (byte)2, (short)3);
   private static final TField MODIFIED_ROW_COUNT_FIELD_DESC = new TField("modifiedRowCount", (byte)4, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TOperationHandleStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TOperationHandleTupleSchemeFactory();
   @Nullable
   private THandleIdentifier operationId;
   @Nullable
   private TOperationType operationType;
   private boolean hasResultSet;
   private double modifiedRowCount;
   private static final int __HASRESULTSET_ISSET_ID = 0;
   private static final int __MODIFIEDROWCOUNT_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TOperationHandle() {
      this.__isset_bitfield = 0;
   }

   public TOperationHandle(THandleIdentifier operationId, TOperationType operationType, boolean hasResultSet) {
      this();
      this.operationId = operationId;
      this.operationType = operationType;
      this.hasResultSet = hasResultSet;
      this.setHasResultSetIsSet(true);
   }

   public TOperationHandle(TOperationHandle other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetOperationId()) {
         this.operationId = new THandleIdentifier(other.operationId);
      }

      if (other.isSetOperationType()) {
         this.operationType = other.operationType;
      }

      this.hasResultSet = other.hasResultSet;
      this.modifiedRowCount = other.modifiedRowCount;
   }

   public TOperationHandle deepCopy() {
      return new TOperationHandle(this);
   }

   public void clear() {
      this.operationId = null;
      this.operationType = null;
      this.setHasResultSetIsSet(false);
      this.hasResultSet = false;
      this.setModifiedRowCountIsSet(false);
      this.modifiedRowCount = (double)0.0F;
   }

   @Nullable
   public THandleIdentifier getOperationId() {
      return this.operationId;
   }

   public void setOperationId(@Nullable THandleIdentifier operationId) {
      this.operationId = operationId;
   }

   public void unsetOperationId() {
      this.operationId = null;
   }

   public boolean isSetOperationId() {
      return this.operationId != null;
   }

   public void setOperationIdIsSet(boolean value) {
      if (!value) {
         this.operationId = null;
      }

   }

   @Nullable
   public TOperationType getOperationType() {
      return this.operationType;
   }

   public void setOperationType(@Nullable TOperationType operationType) {
      this.operationType = operationType;
   }

   public void unsetOperationType() {
      this.operationType = null;
   }

   public boolean isSetOperationType() {
      return this.operationType != null;
   }

   public void setOperationTypeIsSet(boolean value) {
      if (!value) {
         this.operationType = null;
      }

   }

   public boolean isHasResultSet() {
      return this.hasResultSet;
   }

   public void setHasResultSet(boolean hasResultSet) {
      this.hasResultSet = hasResultSet;
      this.setHasResultSetIsSet(true);
   }

   public void unsetHasResultSet() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetHasResultSet() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setHasResultSetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public double getModifiedRowCount() {
      return this.modifiedRowCount;
   }

   public void setModifiedRowCount(double modifiedRowCount) {
      this.modifiedRowCount = modifiedRowCount;
      this.setModifiedRowCountIsSet(true);
   }

   public void unsetModifiedRowCount() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetModifiedRowCount() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setModifiedRowCountIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case OPERATION_ID:
            if (value == null) {
               this.unsetOperationId();
            } else {
               this.setOperationId((THandleIdentifier)value);
            }
            break;
         case OPERATION_TYPE:
            if (value == null) {
               this.unsetOperationType();
            } else {
               this.setOperationType((TOperationType)value);
            }
            break;
         case HAS_RESULT_SET:
            if (value == null) {
               this.unsetHasResultSet();
            } else {
               this.setHasResultSet((Boolean)value);
            }
            break;
         case MODIFIED_ROW_COUNT:
            if (value == null) {
               this.unsetModifiedRowCount();
            } else {
               this.setModifiedRowCount((Double)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OPERATION_ID:
            return this.getOperationId();
         case OPERATION_TYPE:
            return this.getOperationType();
         case HAS_RESULT_SET:
            return this.isHasResultSet();
         case MODIFIED_ROW_COUNT:
            return this.getModifiedRowCount();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case OPERATION_ID:
               return this.isSetOperationId();
            case OPERATION_TYPE:
               return this.isSetOperationType();
            case HAS_RESULT_SET:
               return this.isSetHasResultSet();
            case MODIFIED_ROW_COUNT:
               return this.isSetModifiedRowCount();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TOperationHandle ? this.equals((TOperationHandle)that) : false;
   }

   public boolean equals(TOperationHandle that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_operationId = this.isSetOperationId();
         boolean that_present_operationId = that.isSetOperationId();
         if (this_present_operationId || that_present_operationId) {
            if (!this_present_operationId || !that_present_operationId) {
               return false;
            }

            if (!this.operationId.equals(that.operationId)) {
               return false;
            }
         }

         boolean this_present_operationType = this.isSetOperationType();
         boolean that_present_operationType = that.isSetOperationType();
         if (this_present_operationType || that_present_operationType) {
            if (!this_present_operationType || !that_present_operationType) {
               return false;
            }

            if (!this.operationType.equals(that.operationType)) {
               return false;
            }
         }

         boolean this_present_hasResultSet = true;
         boolean that_present_hasResultSet = true;
         if (this_present_hasResultSet || that_present_hasResultSet) {
            if (!this_present_hasResultSet || !that_present_hasResultSet) {
               return false;
            }

            if (this.hasResultSet != that.hasResultSet) {
               return false;
            }
         }

         boolean this_present_modifiedRowCount = this.isSetModifiedRowCount();
         boolean that_present_modifiedRowCount = that.isSetModifiedRowCount();
         if (this_present_modifiedRowCount || that_present_modifiedRowCount) {
            if (!this_present_modifiedRowCount || !that_present_modifiedRowCount) {
               return false;
            }

            if (this.modifiedRowCount != that.modifiedRowCount) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetOperationId() ? 131071 : 524287);
      if (this.isSetOperationId()) {
         hashCode = hashCode * 8191 + this.operationId.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOperationType() ? 131071 : 524287);
      if (this.isSetOperationType()) {
         hashCode = hashCode * 8191 + this.operationType.getValue();
      }

      hashCode = hashCode * 8191 + (this.hasResultSet ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.isSetModifiedRowCount() ? 131071 : 524287);
      if (this.isSetModifiedRowCount()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.modifiedRowCount);
      }

      return hashCode;
   }

   public int compareTo(TOperationHandle other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetOperationId(), other.isSetOperationId());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetOperationId()) {
               lastComparison = TBaseHelper.compareTo(this.operationId, other.operationId);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetOperationType(), other.isSetOperationType());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetOperationType()) {
                  lastComparison = TBaseHelper.compareTo(this.operationType, other.operationType);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetHasResultSet(), other.isSetHasResultSet());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetHasResultSet()) {
                     lastComparison = TBaseHelper.compareTo(this.hasResultSet, other.hasResultSet);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetModifiedRowCount(), other.isSetModifiedRowCount());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetModifiedRowCount()) {
                        lastComparison = TBaseHelper.compareTo(this.modifiedRowCount, other.modifiedRowCount);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     return 0;
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TOperationHandle._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TOperationHandle(");
      boolean first = true;
      sb.append("operationId:");
      if (this.operationId == null) {
         sb.append("null");
      } else {
         sb.append(this.operationId);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("operationType:");
      if (this.operationType == null) {
         sb.append("null");
      } else {
         sb.append(this.operationType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("hasResultSet:");
      sb.append(this.hasResultSet);
      first = false;
      if (this.isSetModifiedRowCount()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("modifiedRowCount:");
         sb.append(this.modifiedRowCount);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetOperationId()) {
         throw new TProtocolException("Required field 'operationId' is unset! Struct:" + this.toString());
      } else if (!this.isSetOperationType()) {
         throw new TProtocolException("Required field 'operationType' is unset! Struct:" + this.toString());
      } else if (!this.isSetHasResultSet()) {
         throw new TProtocolException("Required field 'hasResultSet' is unset! Struct:" + this.toString());
      } else {
         if (this.operationId != null) {
            this.operationId.validate();
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
      optionals = new _Fields[]{TOperationHandle._Fields.MODIFIED_ROW_COUNT};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TOperationHandle._Fields.OPERATION_ID, new FieldMetaData("operationId", (byte)1, new StructMetaData((byte)12, THandleIdentifier.class)));
      tmpMap.put(TOperationHandle._Fields.OPERATION_TYPE, new FieldMetaData("operationType", (byte)1, new EnumMetaData((byte)16, TOperationType.class)));
      tmpMap.put(TOperationHandle._Fields.HAS_RESULT_SET, new FieldMetaData("hasResultSet", (byte)1, new FieldValueMetaData((byte)2)));
      tmpMap.put(TOperationHandle._Fields.MODIFIED_ROW_COUNT, new FieldMetaData("modifiedRowCount", (byte)2, new FieldValueMetaData((byte)4)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TOperationHandle.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OPERATION_ID((short)1, "operationId"),
      OPERATION_TYPE((short)2, "operationType"),
      HAS_RESULT_SET((short)3, "hasResultSet"),
      MODIFIED_ROW_COUNT((short)4, "modifiedRowCount");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OPERATION_ID;
            case 2:
               return OPERATION_TYPE;
            case 3:
               return HAS_RESULT_SET;
            case 4:
               return MODIFIED_ROW_COUNT;
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

   private static class TOperationHandleStandardSchemeFactory implements SchemeFactory {
      private TOperationHandleStandardSchemeFactory() {
      }

      public TOperationHandleStandardScheme getScheme() {
         return new TOperationHandleStandardScheme();
      }
   }

   private static class TOperationHandleStandardScheme extends StandardScheme {
      private TOperationHandleStandardScheme() {
      }

      public void read(TProtocol iprot, TOperationHandle struct) throws TException {
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
                     struct.operationId = new THandleIdentifier();
                     struct.operationId.read(iprot);
                     struct.setOperationIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.operationType = TOperationType.findByValue(iprot.readI32());
                     struct.setOperationTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 2) {
                     struct.hasResultSet = iprot.readBool();
                     struct.setHasResultSetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 4) {
                     struct.modifiedRowCount = iprot.readDouble();
                     struct.setModifiedRowCountIsSet(true);
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

      public void write(TProtocol oprot, TOperationHandle struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TOperationHandle.STRUCT_DESC);
         if (struct.operationId != null) {
            oprot.writeFieldBegin(TOperationHandle.OPERATION_ID_FIELD_DESC);
            struct.operationId.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.operationType != null) {
            oprot.writeFieldBegin(TOperationHandle.OPERATION_TYPE_FIELD_DESC);
            oprot.writeI32(struct.operationType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(TOperationHandle.HAS_RESULT_SET_FIELD_DESC);
         oprot.writeBool(struct.hasResultSet);
         oprot.writeFieldEnd();
         if (struct.isSetModifiedRowCount()) {
            oprot.writeFieldBegin(TOperationHandle.MODIFIED_ROW_COUNT_FIELD_DESC);
            oprot.writeDouble(struct.modifiedRowCount);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TOperationHandleTupleSchemeFactory implements SchemeFactory {
      private TOperationHandleTupleSchemeFactory() {
      }

      public TOperationHandleTupleScheme getScheme() {
         return new TOperationHandleTupleScheme();
      }
   }

   private static class TOperationHandleTupleScheme extends TupleScheme {
      private TOperationHandleTupleScheme() {
      }

      public void write(TProtocol prot, TOperationHandle struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.operationId.write(oprot);
         oprot.writeI32(struct.operationType.getValue());
         oprot.writeBool(struct.hasResultSet);
         BitSet optionals = new BitSet();
         if (struct.isSetModifiedRowCount()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetModifiedRowCount()) {
            oprot.writeDouble(struct.modifiedRowCount);
         }

      }

      public void read(TProtocol prot, TOperationHandle struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.operationId = new THandleIdentifier();
         struct.operationId.read(iprot);
         struct.setOperationIdIsSet(true);
         struct.operationType = TOperationType.findByValue(iprot.readI32());
         struct.setOperationTypeIsSet(true);
         struct.hasResultSet = iprot.readBool();
         struct.setHasResultSetIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.modifiedRowCount = iprot.readDouble();
            struct.setModifiedRowCountIsSet(true);
         }

      }
   }
}
