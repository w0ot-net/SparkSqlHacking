package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
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

public class GetFileMetadataByExprRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetFileMetadataByExprRequest");
   private static final TField FILE_IDS_FIELD_DESC = new TField("fileIds", (byte)15, (short)1);
   private static final TField EXPR_FIELD_DESC = new TField("expr", (byte)11, (short)2);
   private static final TField DO_GET_FOOTERS_FIELD_DESC = new TField("doGetFooters", (byte)2, (short)3);
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetFileMetadataByExprRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetFileMetadataByExprRequestTupleSchemeFactory();
   @Nullable
   private List fileIds;
   @Nullable
   private ByteBuffer expr;
   private boolean doGetFooters;
   @Nullable
   private FileMetadataExprType type;
   private static final int __DOGETFOOTERS_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GetFileMetadataByExprRequest() {
      this.__isset_bitfield = 0;
   }

   public GetFileMetadataByExprRequest(List fileIds, ByteBuffer expr) {
      this();
      this.fileIds = fileIds;
      this.expr = TBaseHelper.copyBinary(expr);
   }

   public GetFileMetadataByExprRequest(GetFileMetadataByExprRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetFileIds()) {
         List<Long> __this__fileIds = new ArrayList(other.fileIds);
         this.fileIds = __this__fileIds;
      }

      if (other.isSetExpr()) {
         this.expr = TBaseHelper.copyBinary(other.expr);
      }

      this.doGetFooters = other.doGetFooters;
      if (other.isSetType()) {
         this.type = other.type;
      }

   }

   public GetFileMetadataByExprRequest deepCopy() {
      return new GetFileMetadataByExprRequest(this);
   }

   public void clear() {
      this.fileIds = null;
      this.expr = null;
      this.setDoGetFootersIsSet(false);
      this.doGetFooters = false;
      this.type = null;
   }

   public int getFileIdsSize() {
      return this.fileIds == null ? 0 : this.fileIds.size();
   }

   @Nullable
   public Iterator getFileIdsIterator() {
      return this.fileIds == null ? null : this.fileIds.iterator();
   }

   public void addToFileIds(long elem) {
      if (this.fileIds == null) {
         this.fileIds = new ArrayList();
      }

      this.fileIds.add(elem);
   }

   @Nullable
   public List getFileIds() {
      return this.fileIds;
   }

   public void setFileIds(@Nullable List fileIds) {
      this.fileIds = fileIds;
   }

   public void unsetFileIds() {
      this.fileIds = null;
   }

   public boolean isSetFileIds() {
      return this.fileIds != null;
   }

   public void setFileIdsIsSet(boolean value) {
      if (!value) {
         this.fileIds = null;
      }

   }

   public byte[] getExpr() {
      this.setExpr(TBaseHelper.rightSize(this.expr));
      return this.expr == null ? null : this.expr.array();
   }

   public ByteBuffer bufferForExpr() {
      return TBaseHelper.copyBinary(this.expr);
   }

   public void setExpr(byte[] expr) {
      this.expr = expr == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)expr).clone());
   }

   public void setExpr(@Nullable ByteBuffer expr) {
      this.expr = TBaseHelper.copyBinary(expr);
   }

   public void unsetExpr() {
      this.expr = null;
   }

   public boolean isSetExpr() {
      return this.expr != null;
   }

   public void setExprIsSet(boolean value) {
      if (!value) {
         this.expr = null;
      }

   }

   public boolean isDoGetFooters() {
      return this.doGetFooters;
   }

   public void setDoGetFooters(boolean doGetFooters) {
      this.doGetFooters = doGetFooters;
      this.setDoGetFootersIsSet(true);
   }

   public void unsetDoGetFooters() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetDoGetFooters() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setDoGetFootersIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public FileMetadataExprType getType() {
      return this.type;
   }

   public void setType(@Nullable FileMetadataExprType type) {
      this.type = type;
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FILE_IDS:
            if (value == null) {
               this.unsetFileIds();
            } else {
               this.setFileIds((List)value);
            }
            break;
         case EXPR:
            if (value == null) {
               this.unsetExpr();
            } else if (value instanceof byte[]) {
               this.setExpr((byte[])value);
            } else {
               this.setExpr((ByteBuffer)value);
            }
            break;
         case DO_GET_FOOTERS:
            if (value == null) {
               this.unsetDoGetFooters();
            } else {
               this.setDoGetFooters((Boolean)value);
            }
            break;
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((FileMetadataExprType)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FILE_IDS:
            return this.getFileIds();
         case EXPR:
            return this.getExpr();
         case DO_GET_FOOTERS:
            return this.isDoGetFooters();
         case TYPE:
            return this.getType();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FILE_IDS:
               return this.isSetFileIds();
            case EXPR:
               return this.isSetExpr();
            case DO_GET_FOOTERS:
               return this.isSetDoGetFooters();
            case TYPE:
               return this.isSetType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetFileMetadataByExprRequest ? this.equals((GetFileMetadataByExprRequest)that) : false;
   }

   public boolean equals(GetFileMetadataByExprRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_fileIds = this.isSetFileIds();
         boolean that_present_fileIds = that.isSetFileIds();
         if (this_present_fileIds || that_present_fileIds) {
            if (!this_present_fileIds || !that_present_fileIds) {
               return false;
            }

            if (!this.fileIds.equals(that.fileIds)) {
               return false;
            }
         }

         boolean this_present_expr = this.isSetExpr();
         boolean that_present_expr = that.isSetExpr();
         if (this_present_expr || that_present_expr) {
            if (!this_present_expr || !that_present_expr) {
               return false;
            }

            if (!this.expr.equals(that.expr)) {
               return false;
            }
         }

         boolean this_present_doGetFooters = this.isSetDoGetFooters();
         boolean that_present_doGetFooters = that.isSetDoGetFooters();
         if (this_present_doGetFooters || that_present_doGetFooters) {
            if (!this_present_doGetFooters || !that_present_doGetFooters) {
               return false;
            }

            if (this.doGetFooters != that.doGetFooters) {
               return false;
            }
         }

         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetFileIds() ? 131071 : 524287);
      if (this.isSetFileIds()) {
         hashCode = hashCode * 8191 + this.fileIds.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetExpr() ? 131071 : 524287);
      if (this.isSetExpr()) {
         hashCode = hashCode * 8191 + this.expr.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDoGetFooters() ? 131071 : 524287);
      if (this.isSetDoGetFooters()) {
         hashCode = hashCode * 8191 + (this.doGetFooters ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      return hashCode;
   }

   public int compareTo(GetFileMetadataByExprRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetFileIds(), other.isSetFileIds());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetFileIds()) {
               lastComparison = TBaseHelper.compareTo(this.fileIds, other.fileIds);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetExpr(), other.isSetExpr());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetExpr()) {
                  lastComparison = TBaseHelper.compareTo(this.expr, other.expr);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetDoGetFooters(), other.isSetDoGetFooters());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetDoGetFooters()) {
                     lastComparison = TBaseHelper.compareTo(this.doGetFooters, other.doGetFooters);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetType()) {
                        lastComparison = TBaseHelper.compareTo(this.type, other.type);
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
      return GetFileMetadataByExprRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetFileMetadataByExprRequest(");
      boolean first = true;
      sb.append("fileIds:");
      if (this.fileIds == null) {
         sb.append("null");
      } else {
         sb.append(this.fileIds);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("expr:");
      if (this.expr == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.expr, sb);
      }

      first = false;
      if (this.isSetDoGetFooters()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("doGetFooters:");
         sb.append(this.doGetFooters);
         first = false;
      }

      if (this.isSetType()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("type:");
         if (this.type == null) {
            sb.append("null");
         } else {
            sb.append(this.type);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetFileIds()) {
         throw new TProtocolException("Required field 'fileIds' is unset! Struct:" + this.toString());
      } else if (!this.isSetExpr()) {
         throw new TProtocolException("Required field 'expr' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{GetFileMetadataByExprRequest._Fields.DO_GET_FOOTERS, GetFileMetadataByExprRequest._Fields.TYPE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetFileMetadataByExprRequest._Fields.FILE_IDS, new FieldMetaData("fileIds", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      tmpMap.put(GetFileMetadataByExprRequest._Fields.EXPR, new FieldMetaData("expr", (byte)1, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(GetFileMetadataByExprRequest._Fields.DO_GET_FOOTERS, new FieldMetaData("doGetFooters", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(GetFileMetadataByExprRequest._Fields.TYPE, new FieldMetaData("type", (byte)2, new EnumMetaData((byte)16, FileMetadataExprType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetFileMetadataByExprRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FILE_IDS((short)1, "fileIds"),
      EXPR((short)2, "expr"),
      DO_GET_FOOTERS((short)3, "doGetFooters"),
      TYPE((short)4, "type");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FILE_IDS;
            case 2:
               return EXPR;
            case 3:
               return DO_GET_FOOTERS;
            case 4:
               return TYPE;
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

   private static class GetFileMetadataByExprRequestStandardSchemeFactory implements SchemeFactory {
      private GetFileMetadataByExprRequestStandardSchemeFactory() {
      }

      public GetFileMetadataByExprRequestStandardScheme getScheme() {
         return new GetFileMetadataByExprRequestStandardScheme();
      }
   }

   private static class GetFileMetadataByExprRequestStandardScheme extends StandardScheme {
      private GetFileMetadataByExprRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GetFileMetadataByExprRequest struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list624 = iprot.readListBegin();
                  struct.fileIds = new ArrayList(_list624.size);

                  for(int _i626 = 0; _i626 < _list624.size; ++_i626) {
                     long _elem625 = iprot.readI64();
                     struct.fileIds.add(_elem625);
                  }

                  iprot.readListEnd();
                  struct.setFileIdsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.expr = iprot.readBinary();
                     struct.setExprIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 2) {
                     struct.doGetFooters = iprot.readBool();
                     struct.setDoGetFootersIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.type = FileMetadataExprType.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
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

      public void write(TProtocol oprot, GetFileMetadataByExprRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetFileMetadataByExprRequest.STRUCT_DESC);
         if (struct.fileIds != null) {
            oprot.writeFieldBegin(GetFileMetadataByExprRequest.FILE_IDS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.fileIds.size()));

            for(long _iter627 : struct.fileIds) {
               oprot.writeI64(_iter627);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.expr != null) {
            oprot.writeFieldBegin(GetFileMetadataByExprRequest.EXPR_FIELD_DESC);
            oprot.writeBinary(struct.expr);
            oprot.writeFieldEnd();
         }

         if (struct.isSetDoGetFooters()) {
            oprot.writeFieldBegin(GetFileMetadataByExprRequest.DO_GET_FOOTERS_FIELD_DESC);
            oprot.writeBool(struct.doGetFooters);
            oprot.writeFieldEnd();
         }

         if (struct.type != null && struct.isSetType()) {
            oprot.writeFieldBegin(GetFileMetadataByExprRequest.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetFileMetadataByExprRequestTupleSchemeFactory implements SchemeFactory {
      private GetFileMetadataByExprRequestTupleSchemeFactory() {
      }

      public GetFileMetadataByExprRequestTupleScheme getScheme() {
         return new GetFileMetadataByExprRequestTupleScheme();
      }
   }

   private static class GetFileMetadataByExprRequestTupleScheme extends TupleScheme {
      private GetFileMetadataByExprRequestTupleScheme() {
      }

      public void write(TProtocol prot, GetFileMetadataByExprRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.fileIds.size());

         for(long _iter628 : struct.fileIds) {
            oprot.writeI64(_iter628);
         }

         oprot.writeBinary(struct.expr);
         BitSet optionals = new BitSet();
         if (struct.isSetDoGetFooters()) {
            optionals.set(0);
         }

         if (struct.isSetType()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetDoGetFooters()) {
            oprot.writeBool(struct.doGetFooters);
         }

         if (struct.isSetType()) {
            oprot.writeI32(struct.type.getValue());
         }

      }

      public void read(TProtocol prot, GetFileMetadataByExprRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list629 = iprot.readListBegin((byte)10);
         struct.fileIds = new ArrayList(_list629.size);

         for(int _i631 = 0; _i631 < _list629.size; ++_i631) {
            long _elem630 = iprot.readI64();
            struct.fileIds.add(_elem630);
         }

         struct.setFileIdsIsSet(true);
         struct.expr = iprot.readBinary();
         struct.setExprIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.doGetFooters = iprot.readBool();
            struct.setDoGetFootersIsSet(true);
         }

         if (incoming.get(1)) {
            struct.type = FileMetadataExprType.findByValue(iprot.readI32());
            struct.setTypeIsSet(true);
         }

      }
   }
}
