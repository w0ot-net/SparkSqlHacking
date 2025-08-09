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
public class TFetchResultsReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TFetchResultsReq");
   private static final TField OPERATION_HANDLE_FIELD_DESC = new TField("operationHandle", (byte)12, (short)1);
   private static final TField ORIENTATION_FIELD_DESC = new TField("orientation", (byte)8, (short)2);
   private static final TField MAX_ROWS_FIELD_DESC = new TField("maxRows", (byte)10, (short)3);
   private static final TField FETCH_TYPE_FIELD_DESC = new TField("fetchType", (byte)6, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TFetchResultsReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TFetchResultsReqTupleSchemeFactory();
   @Nullable
   private TOperationHandle operationHandle;
   @Nullable
   private TFetchOrientation orientation;
   private long maxRows;
   private short fetchType;
   private static final int __MAXROWS_ISSET_ID = 0;
   private static final int __FETCHTYPE_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TFetchResultsReq() {
      this.__isset_bitfield = 0;
      this.orientation = TFetchOrientation.FETCH_NEXT;
      this.fetchType = 0;
   }

   public TFetchResultsReq(TOperationHandle operationHandle, TFetchOrientation orientation, long maxRows) {
      this();
      this.operationHandle = operationHandle;
      this.orientation = orientation;
      this.maxRows = maxRows;
      this.setMaxRowsIsSet(true);
   }

   public TFetchResultsReq(TFetchResultsReq other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetOperationHandle()) {
         this.operationHandle = new TOperationHandle(other.operationHandle);
      }

      if (other.isSetOrientation()) {
         this.orientation = other.orientation;
      }

      this.maxRows = other.maxRows;
      this.fetchType = other.fetchType;
   }

   public TFetchResultsReq deepCopy() {
      return new TFetchResultsReq(this);
   }

   public void clear() {
      this.operationHandle = null;
      this.orientation = TFetchOrientation.FETCH_NEXT;
      this.setMaxRowsIsSet(false);
      this.maxRows = 0L;
      this.fetchType = 0;
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

   @Nullable
   public TFetchOrientation getOrientation() {
      return this.orientation;
   }

   public void setOrientation(@Nullable TFetchOrientation orientation) {
      this.orientation = orientation;
   }

   public void unsetOrientation() {
      this.orientation = null;
   }

   public boolean isSetOrientation() {
      return this.orientation != null;
   }

   public void setOrientationIsSet(boolean value) {
      if (!value) {
         this.orientation = null;
      }

   }

   public long getMaxRows() {
      return this.maxRows;
   }

   public void setMaxRows(long maxRows) {
      this.maxRows = maxRows;
      this.setMaxRowsIsSet(true);
   }

   public void unsetMaxRows() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetMaxRows() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setMaxRowsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public short getFetchType() {
      return this.fetchType;
   }

   public void setFetchType(short fetchType) {
      this.fetchType = fetchType;
      this.setFetchTypeIsSet(true);
   }

   public void unsetFetchType() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetFetchType() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setFetchTypeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
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
         case ORIENTATION:
            if (value == null) {
               this.unsetOrientation();
            } else {
               this.setOrientation((TFetchOrientation)value);
            }
            break;
         case MAX_ROWS:
            if (value == null) {
               this.unsetMaxRows();
            } else {
               this.setMaxRows((Long)value);
            }
            break;
         case FETCH_TYPE:
            if (value == null) {
               this.unsetFetchType();
            } else {
               this.setFetchType((Short)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OPERATION_HANDLE:
            return this.getOperationHandle();
         case ORIENTATION:
            return this.getOrientation();
         case MAX_ROWS:
            return this.getMaxRows();
         case FETCH_TYPE:
            return this.getFetchType();
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
            case ORIENTATION:
               return this.isSetOrientation();
            case MAX_ROWS:
               return this.isSetMaxRows();
            case FETCH_TYPE:
               return this.isSetFetchType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TFetchResultsReq ? this.equals((TFetchResultsReq)that) : false;
   }

   public boolean equals(TFetchResultsReq that) {
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

         boolean this_present_orientation = this.isSetOrientation();
         boolean that_present_orientation = that.isSetOrientation();
         if (this_present_orientation || that_present_orientation) {
            if (!this_present_orientation || !that_present_orientation) {
               return false;
            }

            if (!this.orientation.equals(that.orientation)) {
               return false;
            }
         }

         boolean this_present_maxRows = true;
         boolean that_present_maxRows = true;
         if (this_present_maxRows || that_present_maxRows) {
            if (!this_present_maxRows || !that_present_maxRows) {
               return false;
            }

            if (this.maxRows != that.maxRows) {
               return false;
            }
         }

         boolean this_present_fetchType = this.isSetFetchType();
         boolean that_present_fetchType = that.isSetFetchType();
         if (this_present_fetchType || that_present_fetchType) {
            if (!this_present_fetchType || !that_present_fetchType) {
               return false;
            }

            if (this.fetchType != that.fetchType) {
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

      hashCode = hashCode * 8191 + (this.isSetOrientation() ? 131071 : 524287);
      if (this.isSetOrientation()) {
         hashCode = hashCode * 8191 + this.orientation.getValue();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.maxRows);
      hashCode = hashCode * 8191 + (this.isSetFetchType() ? 131071 : 524287);
      if (this.isSetFetchType()) {
         hashCode = hashCode * 8191 + this.fetchType;
      }

      return hashCode;
   }

   public int compareTo(TFetchResultsReq other) {
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

            lastComparison = Boolean.compare(this.isSetOrientation(), other.isSetOrientation());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetOrientation()) {
                  lastComparison = TBaseHelper.compareTo(this.orientation, other.orientation);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetMaxRows(), other.isSetMaxRows());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetMaxRows()) {
                     lastComparison = TBaseHelper.compareTo(this.maxRows, other.maxRows);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetFetchType(), other.isSetFetchType());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetFetchType()) {
                        lastComparison = TBaseHelper.compareTo(this.fetchType, other.fetchType);
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
      return TFetchResultsReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TFetchResultsReq(");
      boolean first = true;
      sb.append("operationHandle:");
      if (this.operationHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.operationHandle);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("orientation:");
      if (this.orientation == null) {
         sb.append("null");
      } else {
         sb.append(this.orientation);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("maxRows:");
      sb.append(this.maxRows);
      first = false;
      if (this.isSetFetchType()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("fetchType:");
         sb.append(this.fetchType);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetOperationHandle()) {
         throw new TProtocolException("Required field 'operationHandle' is unset! Struct:" + this.toString());
      } else if (!this.isSetOrientation()) {
         throw new TProtocolException("Required field 'orientation' is unset! Struct:" + this.toString());
      } else if (!this.isSetMaxRows()) {
         throw new TProtocolException("Required field 'maxRows' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TFetchResultsReq._Fields.FETCH_TYPE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TFetchResultsReq._Fields.OPERATION_HANDLE, new FieldMetaData("operationHandle", (byte)1, new StructMetaData((byte)12, TOperationHandle.class)));
      tmpMap.put(TFetchResultsReq._Fields.ORIENTATION, new FieldMetaData("orientation", (byte)1, new EnumMetaData((byte)16, TFetchOrientation.class)));
      tmpMap.put(TFetchResultsReq._Fields.MAX_ROWS, new FieldMetaData("maxRows", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(TFetchResultsReq._Fields.FETCH_TYPE, new FieldMetaData("fetchType", (byte)2, new FieldValueMetaData((byte)6)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TFetchResultsReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OPERATION_HANDLE((short)1, "operationHandle"),
      ORIENTATION((short)2, "orientation"),
      MAX_ROWS((short)3, "maxRows"),
      FETCH_TYPE((short)4, "fetchType");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OPERATION_HANDLE;
            case 2:
               return ORIENTATION;
            case 3:
               return MAX_ROWS;
            case 4:
               return FETCH_TYPE;
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

   private static class TFetchResultsReqStandardSchemeFactory implements SchemeFactory {
      private TFetchResultsReqStandardSchemeFactory() {
      }

      public TFetchResultsReqStandardScheme getScheme() {
         return new TFetchResultsReqStandardScheme();
      }
   }

   private static class TFetchResultsReqStandardScheme extends StandardScheme {
      private TFetchResultsReqStandardScheme() {
      }

      public void read(TProtocol iprot, TFetchResultsReq struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.orientation = TFetchOrientation.findByValue(iprot.readI32());
                     struct.setOrientationIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.maxRows = iprot.readI64();
                     struct.setMaxRowsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 6) {
                     struct.fetchType = iprot.readI16();
                     struct.setFetchTypeIsSet(true);
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

      public void write(TProtocol oprot, TFetchResultsReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TFetchResultsReq.STRUCT_DESC);
         if (struct.operationHandle != null) {
            oprot.writeFieldBegin(TFetchResultsReq.OPERATION_HANDLE_FIELD_DESC);
            struct.operationHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.orientation != null) {
            oprot.writeFieldBegin(TFetchResultsReq.ORIENTATION_FIELD_DESC);
            oprot.writeI32(struct.orientation.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(TFetchResultsReq.MAX_ROWS_FIELD_DESC);
         oprot.writeI64(struct.maxRows);
         oprot.writeFieldEnd();
         if (struct.isSetFetchType()) {
            oprot.writeFieldBegin(TFetchResultsReq.FETCH_TYPE_FIELD_DESC);
            oprot.writeI16(struct.fetchType);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TFetchResultsReqTupleSchemeFactory implements SchemeFactory {
      private TFetchResultsReqTupleSchemeFactory() {
      }

      public TFetchResultsReqTupleScheme getScheme() {
         return new TFetchResultsReqTupleScheme();
      }
   }

   private static class TFetchResultsReqTupleScheme extends TupleScheme {
      private TFetchResultsReqTupleScheme() {
      }

      public void write(TProtocol prot, TFetchResultsReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.operationHandle.write(oprot);
         oprot.writeI32(struct.orientation.getValue());
         oprot.writeI64(struct.maxRows);
         BitSet optionals = new BitSet();
         if (struct.isSetFetchType()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetFetchType()) {
            oprot.writeI16(struct.fetchType);
         }

      }

      public void read(TProtocol prot, TFetchResultsReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.operationHandle = new TOperationHandle();
         struct.operationHandle.read(iprot);
         struct.setOperationHandleIsSet(true);
         struct.orientation = TFetchOrientation.findByValue(iprot.readI32());
         struct.setOrientationIsSet(true);
         struct.maxRows = iprot.readI64();
         struct.setMaxRowsIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.fetchType = iprot.readI16();
            struct.setFetchTypeIsSet(true);
         }

      }
   }
}
