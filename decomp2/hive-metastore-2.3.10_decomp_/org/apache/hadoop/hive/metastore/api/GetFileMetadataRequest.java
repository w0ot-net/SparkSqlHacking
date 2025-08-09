package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
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

public class GetFileMetadataRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetFileMetadataRequest");
   private static final TField FILE_IDS_FIELD_DESC = new TField("fileIds", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetFileMetadataRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetFileMetadataRequestTupleSchemeFactory();
   @Nullable
   private List fileIds;
   public static final Map metaDataMap;

   public GetFileMetadataRequest() {
   }

   public GetFileMetadataRequest(List fileIds) {
      this();
      this.fileIds = fileIds;
   }

   public GetFileMetadataRequest(GetFileMetadataRequest other) {
      if (other.isSetFileIds()) {
         List<Long> __this__fileIds = new ArrayList(other.fileIds);
         this.fileIds = __this__fileIds;
      }

   }

   public GetFileMetadataRequest deepCopy() {
      return new GetFileMetadataRequest(this);
   }

   public void clear() {
      this.fileIds = null;
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

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FILE_IDS:
            if (value == null) {
               this.unsetFileIds();
            } else {
               this.setFileIds((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FILE_IDS:
            return this.getFileIds();
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
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetFileMetadataRequest ? this.equals((GetFileMetadataRequest)that) : false;
   }

   public boolean equals(GetFileMetadataRequest that) {
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

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetFileIds() ? 131071 : 524287);
      if (this.isSetFileIds()) {
         hashCode = hashCode * 8191 + this.fileIds.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetFileMetadataRequest other) {
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

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GetFileMetadataRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetFileMetadataRequest(");
      boolean first = true;
      sb.append("fileIds:");
      if (this.fileIds == null) {
         sb.append("null");
      } else {
         sb.append(this.fileIds);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetFileIds()) {
         throw new TProtocolException("Required field 'fileIds' is unset! Struct:" + this.toString());
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
      tmpMap.put(GetFileMetadataRequest._Fields.FILE_IDS, new FieldMetaData("fileIds", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetFileMetadataRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FILE_IDS((short)1, "fileIds");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FILE_IDS;
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

   private static class GetFileMetadataRequestStandardSchemeFactory implements SchemeFactory {
      private GetFileMetadataRequestStandardSchemeFactory() {
      }

      public GetFileMetadataRequestStandardScheme getScheme() {
         return new GetFileMetadataRequestStandardScheme();
      }
   }

   private static class GetFileMetadataRequestStandardScheme extends StandardScheme {
      private GetFileMetadataRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GetFileMetadataRequest struct) throws TException {
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

                  TList _list642 = iprot.readListBegin();
                  struct.fileIds = new ArrayList(_list642.size);

                  for(int _i644 = 0; _i644 < _list642.size; ++_i644) {
                     long _elem643 = iprot.readI64();
                     struct.fileIds.add(_elem643);
                  }

                  iprot.readListEnd();
                  struct.setFileIdsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, GetFileMetadataRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetFileMetadataRequest.STRUCT_DESC);
         if (struct.fileIds != null) {
            oprot.writeFieldBegin(GetFileMetadataRequest.FILE_IDS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.fileIds.size()));

            for(long _iter645 : struct.fileIds) {
               oprot.writeI64(_iter645);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetFileMetadataRequestTupleSchemeFactory implements SchemeFactory {
      private GetFileMetadataRequestTupleSchemeFactory() {
      }

      public GetFileMetadataRequestTupleScheme getScheme() {
         return new GetFileMetadataRequestTupleScheme();
      }
   }

   private static class GetFileMetadataRequestTupleScheme extends TupleScheme {
      private GetFileMetadataRequestTupleScheme() {
      }

      public void write(TProtocol prot, GetFileMetadataRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.fileIds.size());

         for(long _iter646 : struct.fileIds) {
            oprot.writeI64(_iter646);
         }

      }

      public void read(TProtocol prot, GetFileMetadataRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list647 = iprot.readListBegin((byte)10);
         struct.fileIds = new ArrayList(_list647.size);

         for(int _i649 = 0; _i649 < _list647.size; ++_i649) {
            long _elem648 = iprot.readI64();
            struct.fileIds.add(_elem648);
         }

         struct.setFileIdsIsSet(true);
      }
   }
}
