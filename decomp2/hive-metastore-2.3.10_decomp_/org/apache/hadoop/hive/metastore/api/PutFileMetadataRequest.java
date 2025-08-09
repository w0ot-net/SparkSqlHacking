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

public class PutFileMetadataRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PutFileMetadataRequest");
   private static final TField FILE_IDS_FIELD_DESC = new TField("fileIds", (byte)15, (short)1);
   private static final TField METADATA_FIELD_DESC = new TField("metadata", (byte)15, (short)2);
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PutFileMetadataRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PutFileMetadataRequestTupleSchemeFactory();
   @Nullable
   private List fileIds;
   @Nullable
   private List metadata;
   @Nullable
   private FileMetadataExprType type;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PutFileMetadataRequest() {
   }

   public PutFileMetadataRequest(List fileIds, List metadata) {
      this();
      this.fileIds = fileIds;
      this.metadata = metadata;
   }

   public PutFileMetadataRequest(PutFileMetadataRequest other) {
      if (other.isSetFileIds()) {
         List<Long> __this__fileIds = new ArrayList(other.fileIds);
         this.fileIds = __this__fileIds;
      }

      if (other.isSetMetadata()) {
         List<ByteBuffer> __this__metadata = new ArrayList(other.metadata);
         this.metadata = __this__metadata;
      }

      if (other.isSetType()) {
         this.type = other.type;
      }

   }

   public PutFileMetadataRequest deepCopy() {
      return new PutFileMetadataRequest(this);
   }

   public void clear() {
      this.fileIds = null;
      this.metadata = null;
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

   public int getMetadataSize() {
      return this.metadata == null ? 0 : this.metadata.size();
   }

   @Nullable
   public Iterator getMetadataIterator() {
      return this.metadata == null ? null : this.metadata.iterator();
   }

   public void addToMetadata(ByteBuffer elem) {
      if (this.metadata == null) {
         this.metadata = new ArrayList();
      }

      this.metadata.add(elem);
   }

   @Nullable
   public List getMetadata() {
      return this.metadata;
   }

   public void setMetadata(@Nullable List metadata) {
      this.metadata = metadata;
   }

   public void unsetMetadata() {
      this.metadata = null;
   }

   public boolean isSetMetadata() {
      return this.metadata != null;
   }

   public void setMetadataIsSet(boolean value) {
      if (!value) {
         this.metadata = null;
      }

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
         case METADATA:
            if (value == null) {
               this.unsetMetadata();
            } else {
               this.setMetadata((List)value);
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
         case METADATA:
            return this.getMetadata();
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
            case METADATA:
               return this.isSetMetadata();
            case TYPE:
               return this.isSetType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PutFileMetadataRequest ? this.equals((PutFileMetadataRequest)that) : false;
   }

   public boolean equals(PutFileMetadataRequest that) {
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

         boolean this_present_metadata = this.isSetMetadata();
         boolean that_present_metadata = that.isSetMetadata();
         if (this_present_metadata || that_present_metadata) {
            if (!this_present_metadata || !that_present_metadata) {
               return false;
            }

            if (!this.metadata.equals(that.metadata)) {
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

      hashCode = hashCode * 8191 + (this.isSetMetadata() ? 131071 : 524287);
      if (this.isSetMetadata()) {
         hashCode = hashCode * 8191 + this.metadata.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      return hashCode;
   }

   public int compareTo(PutFileMetadataRequest other) {
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

            lastComparison = Boolean.compare(this.isSetMetadata(), other.isSetMetadata());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMetadata()) {
                  lastComparison = TBaseHelper.compareTo(this.metadata, other.metadata);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PutFileMetadataRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PutFileMetadataRequest(");
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

      sb.append("metadata:");
      if (this.metadata == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.metadata, sb);
      }

      first = false;
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
      } else if (!this.isSetMetadata()) {
         throw new TProtocolException("Required field 'metadata' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{PutFileMetadataRequest._Fields.TYPE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PutFileMetadataRequest._Fields.FILE_IDS, new FieldMetaData("fileIds", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      tmpMap.put(PutFileMetadataRequest._Fields.METADATA, new FieldMetaData("metadata", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11, true))));
      tmpMap.put(PutFileMetadataRequest._Fields.TYPE, new FieldMetaData("type", (byte)2, new EnumMetaData((byte)16, FileMetadataExprType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PutFileMetadataRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FILE_IDS((short)1, "fileIds"),
      METADATA((short)2, "metadata"),
      TYPE((short)3, "type");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FILE_IDS;
            case 2:
               return METADATA;
            case 3:
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

   private static class PutFileMetadataRequestStandardSchemeFactory implements SchemeFactory {
      private PutFileMetadataRequestStandardSchemeFactory() {
      }

      public PutFileMetadataRequestStandardScheme getScheme() {
         return new PutFileMetadataRequestStandardScheme();
      }
   }

   private static class PutFileMetadataRequestStandardScheme extends StandardScheme {
      private PutFileMetadataRequestStandardScheme() {
      }

      public void read(TProtocol iprot, PutFileMetadataRequest struct) throws TException {
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

                  TList _list650 = iprot.readListBegin();
                  struct.fileIds = new ArrayList(_list650.size);

                  for(int _i652 = 0; _i652 < _list650.size; ++_i652) {
                     long _elem651 = iprot.readI64();
                     struct.fileIds.add(_elem651);
                  }

                  iprot.readListEnd();
                  struct.setFileIdsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list653 = iprot.readListBegin();
                  struct.metadata = new ArrayList(_list653.size);

                  for(int _i655 = 0; _i655 < _list653.size; ++_i655) {
                     ByteBuffer _elem654 = iprot.readBinary();
                     struct.metadata.add(_elem654);
                  }

                  iprot.readListEnd();
                  struct.setMetadataIsSet(true);
                  break;
               case 3:
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

      public void write(TProtocol oprot, PutFileMetadataRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PutFileMetadataRequest.STRUCT_DESC);
         if (struct.fileIds != null) {
            oprot.writeFieldBegin(PutFileMetadataRequest.FILE_IDS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.fileIds.size()));

            for(long _iter656 : struct.fileIds) {
               oprot.writeI64(_iter656);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.metadata != null) {
            oprot.writeFieldBegin(PutFileMetadataRequest.METADATA_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.metadata.size()));

            for(ByteBuffer _iter657 : struct.metadata) {
               oprot.writeBinary(_iter657);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.type != null && struct.isSetType()) {
            oprot.writeFieldBegin(PutFileMetadataRequest.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PutFileMetadataRequestTupleSchemeFactory implements SchemeFactory {
      private PutFileMetadataRequestTupleSchemeFactory() {
      }

      public PutFileMetadataRequestTupleScheme getScheme() {
         return new PutFileMetadataRequestTupleScheme();
      }
   }

   private static class PutFileMetadataRequestTupleScheme extends TupleScheme {
      private PutFileMetadataRequestTupleScheme() {
      }

      public void write(TProtocol prot, PutFileMetadataRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.fileIds.size());

         for(long _iter658 : struct.fileIds) {
            oprot.writeI64(_iter658);
         }

         oprot.writeI32(struct.metadata.size());

         for(ByteBuffer _iter659 : struct.metadata) {
            oprot.writeBinary(_iter659);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetType()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetType()) {
            oprot.writeI32(struct.type.getValue());
         }

      }

      public void read(TProtocol prot, PutFileMetadataRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list660 = iprot.readListBegin((byte)10);
         struct.fileIds = new ArrayList(_list660.size);

         for(int _i662 = 0; _i662 < _list660.size; ++_i662) {
            long _elem661 = iprot.readI64();
            struct.fileIds.add(_elem661);
         }

         struct.setFileIdsIsSet(true);
         _list660 = iprot.readListBegin((byte)11);
         struct.metadata = new ArrayList(_list660.size);

         for(int _i665 = 0; _i665 < _list660.size; ++_i665) {
            ByteBuffer _elem664 = iprot.readBinary();
            struct.metadata.add(_elem664);
         }

         struct.setMetadataIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.type = FileMetadataExprType.findByValue(iprot.readI32());
            struct.setTypeIsSet(true);
         }

      }
   }
}
