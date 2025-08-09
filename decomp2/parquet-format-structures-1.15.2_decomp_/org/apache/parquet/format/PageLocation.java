package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.org.apache.thrift.EncodingUtils;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class PageLocation implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PageLocation");
   private static final TField OFFSET_FIELD_DESC = new TField("offset", (byte)10, (short)1);
   private static final TField COMPRESSED_PAGE_SIZE_FIELD_DESC = new TField("compressed_page_size", (byte)8, (short)2);
   private static final TField FIRST_ROW_INDEX_FIELD_DESC = new TField("first_row_index", (byte)10, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PageLocationStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PageLocationTupleSchemeFactory();
   public long offset;
   public int compressed_page_size;
   public long first_row_index;
   private static final int __OFFSET_ISSET_ID = 0;
   private static final int __COMPRESSED_PAGE_SIZE_ISSET_ID = 1;
   private static final int __FIRST_ROW_INDEX_ISSET_ID = 2;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public PageLocation() {
      this.__isset_bitfield = 0;
   }

   public PageLocation(long offset, int compressed_page_size, long first_row_index) {
      this();
      this.offset = offset;
      this.setOffsetIsSet(true);
      this.compressed_page_size = compressed_page_size;
      this.setCompressed_page_sizeIsSet(true);
      this.first_row_index = first_row_index;
      this.setFirst_row_indexIsSet(true);
   }

   public PageLocation(PageLocation other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.offset = other.offset;
      this.compressed_page_size = other.compressed_page_size;
      this.first_row_index = other.first_row_index;
   }

   public PageLocation deepCopy() {
      return new PageLocation(this);
   }

   public void clear() {
      this.setOffsetIsSet(false);
      this.offset = 0L;
      this.setCompressed_page_sizeIsSet(false);
      this.compressed_page_size = 0;
      this.setFirst_row_indexIsSet(false);
      this.first_row_index = 0L;
   }

   public long getOffset() {
      return this.offset;
   }

   public PageLocation setOffset(long offset) {
      this.offset = offset;
      this.setOffsetIsSet(true);
      return this;
   }

   public void unsetOffset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetOffset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setOffsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public int getCompressed_page_size() {
      return this.compressed_page_size;
   }

   public PageLocation setCompressed_page_size(int compressed_page_size) {
      this.compressed_page_size = compressed_page_size;
      this.setCompressed_page_sizeIsSet(true);
      return this;
   }

   public void unsetCompressed_page_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetCompressed_page_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setCompressed_page_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public long getFirst_row_index() {
      return this.first_row_index;
   }

   public PageLocation setFirst_row_index(long first_row_index) {
      this.first_row_index = first_row_index;
      this.setFirst_row_indexIsSet(true);
      return this;
   }

   public void unsetFirst_row_index() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetFirst_row_index() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setFirst_row_indexIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case OFFSET:
            if (value == null) {
               this.unsetOffset();
            } else {
               this.setOffset((Long)value);
            }
            break;
         case COMPRESSED_PAGE_SIZE:
            if (value == null) {
               this.unsetCompressed_page_size();
            } else {
               this.setCompressed_page_size((Integer)value);
            }
            break;
         case FIRST_ROW_INDEX:
            if (value == null) {
               this.unsetFirst_row_index();
            } else {
               this.setFirst_row_index((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OFFSET:
            return this.getOffset();
         case COMPRESSED_PAGE_SIZE:
            return this.getCompressed_page_size();
         case FIRST_ROW_INDEX:
            return this.getFirst_row_index();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case OFFSET:
               return this.isSetOffset();
            case COMPRESSED_PAGE_SIZE:
               return this.isSetCompressed_page_size();
            case FIRST_ROW_INDEX:
               return this.isSetFirst_row_index();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PageLocation ? this.equals((PageLocation)that) : false;
   }

   public boolean equals(PageLocation that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_offset = true;
         boolean that_present_offset = true;
         if (this_present_offset || that_present_offset) {
            if (!this_present_offset || !that_present_offset) {
               return false;
            }

            if (this.offset != that.offset) {
               return false;
            }
         }

         boolean this_present_compressed_page_size = true;
         boolean that_present_compressed_page_size = true;
         if (this_present_compressed_page_size || that_present_compressed_page_size) {
            if (!this_present_compressed_page_size || !that_present_compressed_page_size) {
               return false;
            }

            if (this.compressed_page_size != that.compressed_page_size) {
               return false;
            }
         }

         boolean this_present_first_row_index = true;
         boolean that_present_first_row_index = true;
         if (this_present_first_row_index || that_present_first_row_index) {
            if (!this_present_first_row_index || !that_present_first_row_index) {
               return false;
            }

            if (this.first_row_index != that.first_row_index) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.offset);
      hashCode = hashCode * 8191 + this.compressed_page_size;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.first_row_index);
      return hashCode;
   }

   public int compareTo(PageLocation other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetOffset(), other.isSetOffset());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetOffset()) {
               lastComparison = TBaseHelper.compareTo(this.offset, other.offset);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetCompressed_page_size(), other.isSetCompressed_page_size());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetCompressed_page_size()) {
                  lastComparison = TBaseHelper.compareTo(this.compressed_page_size, other.compressed_page_size);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetFirst_row_index(), other.isSetFirst_row_index());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetFirst_row_index()) {
                     lastComparison = TBaseHelper.compareTo(this.first_row_index, other.first_row_index);
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
      return PageLocation._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PageLocation(");
      boolean first = true;
      sb.append("offset:");
      sb.append(this.offset);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("compressed_page_size:");
      sb.append(this.compressed_page_size);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("first_row_index:");
      sb.append(this.first_row_index);
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PageLocation._Fields.OFFSET, new FieldMetaData("offset", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(PageLocation._Fields.COMPRESSED_PAGE_SIZE, new FieldMetaData("compressed_page_size", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(PageLocation._Fields.FIRST_ROW_INDEX, new FieldMetaData("first_row_index", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PageLocation.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OFFSET((short)1, "offset"),
      COMPRESSED_PAGE_SIZE((short)2, "compressed_page_size"),
      FIRST_ROW_INDEX((short)3, "first_row_index");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OFFSET;
            case 2:
               return COMPRESSED_PAGE_SIZE;
            case 3:
               return FIRST_ROW_INDEX;
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

   private static class PageLocationStandardSchemeFactory implements SchemeFactory {
      private PageLocationStandardSchemeFactory() {
      }

      public PageLocationStandardScheme getScheme() {
         return new PageLocationStandardScheme();
      }
   }

   private static class PageLocationStandardScheme extends StandardScheme {
      private PageLocationStandardScheme() {
      }

      public void read(TProtocol iprot, PageLocation struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetOffset()) {
                  throw new TProtocolException("Required field 'offset' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetCompressed_page_size()) {
                  throw new TProtocolException("Required field 'compressed_page_size' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetFirst_row_index()) {
                  throw new TProtocolException("Required field 'first_row_index' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 10) {
                     struct.offset = iprot.readI64();
                     struct.setOffsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.compressed_page_size = iprot.readI32();
                     struct.setCompressed_page_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.first_row_index = iprot.readI64();
                     struct.setFirst_row_indexIsSet(true);
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

      public void write(TProtocol oprot, PageLocation struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PageLocation.STRUCT_DESC);
         oprot.writeFieldBegin(PageLocation.OFFSET_FIELD_DESC);
         oprot.writeI64(struct.offset);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(PageLocation.COMPRESSED_PAGE_SIZE_FIELD_DESC);
         oprot.writeI32(struct.compressed_page_size);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(PageLocation.FIRST_ROW_INDEX_FIELD_DESC);
         oprot.writeI64(struct.first_row_index);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PageLocationTupleSchemeFactory implements SchemeFactory {
      private PageLocationTupleSchemeFactory() {
      }

      public PageLocationTupleScheme getScheme() {
         return new PageLocationTupleScheme();
      }
   }

   private static class PageLocationTupleScheme extends TupleScheme {
      private PageLocationTupleScheme() {
      }

      public void write(TProtocol prot, PageLocation struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.offset);
         oprot.writeI32(struct.compressed_page_size);
         oprot.writeI64(struct.first_row_index);
      }

      public void read(TProtocol prot, PageLocation struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.offset = iprot.readI64();
         struct.setOffsetIsSet(true);
         struct.compressed_page_size = iprot.readI32();
         struct.setCompressed_page_sizeIsSet(true);
         struct.first_row_index = iprot.readI64();
         struct.setFirst_row_indexIsSet(true);
      }
   }
}
