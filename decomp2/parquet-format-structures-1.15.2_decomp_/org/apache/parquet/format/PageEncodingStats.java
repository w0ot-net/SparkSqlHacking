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
import shaded.parquet.org.apache.thrift.meta_data.EnumMetaData;
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

public class PageEncodingStats implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PageEncodingStats");
   private static final TField PAGE_TYPE_FIELD_DESC = new TField("page_type", (byte)8, (short)1);
   private static final TField ENCODING_FIELD_DESC = new TField("encoding", (byte)8, (short)2);
   private static final TField COUNT_FIELD_DESC = new TField("count", (byte)8, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PageEncodingStatsStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PageEncodingStatsTupleSchemeFactory();
   @Nullable
   public PageType page_type;
   @Nullable
   public Encoding encoding;
   public int count;
   private static final int __COUNT_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public PageEncodingStats() {
      this.__isset_bitfield = 0;
   }

   public PageEncodingStats(PageType page_type, Encoding encoding, int count) {
      this();
      this.page_type = page_type;
      this.encoding = encoding;
      this.count = count;
      this.setCountIsSet(true);
   }

   public PageEncodingStats(PageEncodingStats other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetPage_type()) {
         this.page_type = other.page_type;
      }

      if (other.isSetEncoding()) {
         this.encoding = other.encoding;
      }

      this.count = other.count;
   }

   public PageEncodingStats deepCopy() {
      return new PageEncodingStats(this);
   }

   public void clear() {
      this.page_type = null;
      this.encoding = null;
      this.setCountIsSet(false);
      this.count = 0;
   }

   @Nullable
   public PageType getPage_type() {
      return this.page_type;
   }

   public PageEncodingStats setPage_type(@Nullable PageType page_type) {
      this.page_type = page_type;
      return this;
   }

   public void unsetPage_type() {
      this.page_type = null;
   }

   public boolean isSetPage_type() {
      return this.page_type != null;
   }

   public void setPage_typeIsSet(boolean value) {
      if (!value) {
         this.page_type = null;
      }

   }

   @Nullable
   public Encoding getEncoding() {
      return this.encoding;
   }

   public PageEncodingStats setEncoding(@Nullable Encoding encoding) {
      this.encoding = encoding;
      return this;
   }

   public void unsetEncoding() {
      this.encoding = null;
   }

   public boolean isSetEncoding() {
      return this.encoding != null;
   }

   public void setEncodingIsSet(boolean value) {
      if (!value) {
         this.encoding = null;
      }

   }

   public int getCount() {
      return this.count;
   }

   public PageEncodingStats setCount(int count) {
      this.count = count;
      this.setCountIsSet(true);
      return this;
   }

   public void unsetCount() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetCount() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setCountIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PAGE_TYPE:
            if (value == null) {
               this.unsetPage_type();
            } else {
               this.setPage_type((PageType)value);
            }
            break;
         case ENCODING:
            if (value == null) {
               this.unsetEncoding();
            } else {
               this.setEncoding((Encoding)value);
            }
            break;
         case COUNT:
            if (value == null) {
               this.unsetCount();
            } else {
               this.setCount((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PAGE_TYPE:
            return this.getPage_type();
         case ENCODING:
            return this.getEncoding();
         case COUNT:
            return this.getCount();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PAGE_TYPE:
               return this.isSetPage_type();
            case ENCODING:
               return this.isSetEncoding();
            case COUNT:
               return this.isSetCount();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PageEncodingStats ? this.equals((PageEncodingStats)that) : false;
   }

   public boolean equals(PageEncodingStats that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_page_type = this.isSetPage_type();
         boolean that_present_page_type = that.isSetPage_type();
         if (this_present_page_type || that_present_page_type) {
            if (!this_present_page_type || !that_present_page_type) {
               return false;
            }

            if (!this.page_type.equals(that.page_type)) {
               return false;
            }
         }

         boolean this_present_encoding = this.isSetEncoding();
         boolean that_present_encoding = that.isSetEncoding();
         if (this_present_encoding || that_present_encoding) {
            if (!this_present_encoding || !that_present_encoding) {
               return false;
            }

            if (!this.encoding.equals(that.encoding)) {
               return false;
            }
         }

         boolean this_present_count = true;
         boolean that_present_count = true;
         if (this_present_count || that_present_count) {
            if (!this_present_count || !that_present_count) {
               return false;
            }

            if (this.count != that.count) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPage_type() ? 131071 : 524287);
      if (this.isSetPage_type()) {
         hashCode = hashCode * 8191 + this.page_type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetEncoding() ? 131071 : 524287);
      if (this.isSetEncoding()) {
         hashCode = hashCode * 8191 + this.encoding.getValue();
      }

      hashCode = hashCode * 8191 + this.count;
      return hashCode;
   }

   public int compareTo(PageEncodingStats other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPage_type(), other.isSetPage_type());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPage_type()) {
               lastComparison = TBaseHelper.compareTo((Comparable)this.page_type, (Comparable)other.page_type);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetEncoding(), other.isSetEncoding());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetEncoding()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.encoding, (Comparable)other.encoding);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetCount(), other.isSetCount());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetCount()) {
                     lastComparison = TBaseHelper.compareTo(this.count, other.count);
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
      return PageEncodingStats._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PageEncodingStats(");
      boolean first = true;
      sb.append("page_type:");
      if (this.page_type == null) {
         sb.append("null");
      } else {
         sb.append(this.page_type);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("encoding:");
      if (this.encoding == null) {
         sb.append("null");
      } else {
         sb.append(this.encoding);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("count:");
      sb.append(this.count);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.page_type == null) {
         throw new TProtocolException("Required field 'page_type' was not present! Struct: " + this.toString());
      } else if (this.encoding == null) {
         throw new TProtocolException("Required field 'encoding' was not present! Struct: " + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PageEncodingStats._Fields.PAGE_TYPE, new FieldMetaData("page_type", (byte)1, new EnumMetaData((byte)-1, PageType.class)));
      tmpMap.put(PageEncodingStats._Fields.ENCODING, new FieldMetaData("encoding", (byte)1, new EnumMetaData((byte)-1, Encoding.class)));
      tmpMap.put(PageEncodingStats._Fields.COUNT, new FieldMetaData("count", (byte)1, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PageEncodingStats.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PAGE_TYPE((short)1, "page_type"),
      ENCODING((short)2, "encoding"),
      COUNT((short)3, "count");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PAGE_TYPE;
            case 2:
               return ENCODING;
            case 3:
               return COUNT;
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

   private static class PageEncodingStatsStandardSchemeFactory implements SchemeFactory {
      private PageEncodingStatsStandardSchemeFactory() {
      }

      public PageEncodingStatsStandardScheme getScheme() {
         return new PageEncodingStatsStandardScheme();
      }
   }

   private static class PageEncodingStatsStandardScheme extends StandardScheme {
      private PageEncodingStatsStandardScheme() {
      }

      public void read(TProtocol iprot, PageEncodingStats struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetCount()) {
                  throw new TProtocolException("Required field 'count' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.page_type = PageType.findByValue(iprot.readI32());
                     struct.setPage_typeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.encoding = Encoding.findByValue(iprot.readI32());
                     struct.setEncodingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.count = iprot.readI32();
                     struct.setCountIsSet(true);
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

      public void write(TProtocol oprot, PageEncodingStats struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PageEncodingStats.STRUCT_DESC);
         if (struct.page_type != null) {
            oprot.writeFieldBegin(PageEncodingStats.PAGE_TYPE_FIELD_DESC);
            oprot.writeI32(struct.page_type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.encoding != null) {
            oprot.writeFieldBegin(PageEncodingStats.ENCODING_FIELD_DESC);
            oprot.writeI32(struct.encoding.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(PageEncodingStats.COUNT_FIELD_DESC);
         oprot.writeI32(struct.count);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PageEncodingStatsTupleSchemeFactory implements SchemeFactory {
      private PageEncodingStatsTupleSchemeFactory() {
      }

      public PageEncodingStatsTupleScheme getScheme() {
         return new PageEncodingStatsTupleScheme();
      }
   }

   private static class PageEncodingStatsTupleScheme extends TupleScheme {
      private PageEncodingStatsTupleScheme() {
      }

      public void write(TProtocol prot, PageEncodingStats struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.page_type.getValue());
         oprot.writeI32(struct.encoding.getValue());
         oprot.writeI32(struct.count);
      }

      public void read(TProtocol prot, PageEncodingStats struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.page_type = PageType.findByValue(iprot.readI32());
         struct.setPage_typeIsSet(true);
         struct.encoding = Encoding.findByValue(iprot.readI32());
         struct.setEncodingIsSet(true);
         struct.count = iprot.readI32();
         struct.setCountIsSet(true);
      }
   }
}
