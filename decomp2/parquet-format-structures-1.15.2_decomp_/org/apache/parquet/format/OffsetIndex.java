package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.ListMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
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

public class OffsetIndex implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("OffsetIndex");
   private static final TField PAGE_LOCATIONS_FIELD_DESC = new TField("page_locations", (byte)15, (short)1);
   private static final TField UNENCODED_BYTE_ARRAY_DATA_BYTES_FIELD_DESC = new TField("unencoded_byte_array_data_bytes", (byte)15, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new OffsetIndexStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new OffsetIndexTupleSchemeFactory();
   @Nullable
   public List page_locations;
   @Nullable
   public List unencoded_byte_array_data_bytes;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public OffsetIndex() {
   }

   public OffsetIndex(List page_locations) {
      this();
      this.page_locations = page_locations;
   }

   public OffsetIndex(OffsetIndex other) {
      if (other.isSetPage_locations()) {
         List<PageLocation> __this__page_locations = new ArrayList(other.page_locations.size());

         for(PageLocation other_element : other.page_locations) {
            __this__page_locations.add(new PageLocation(other_element));
         }

         this.page_locations = __this__page_locations;
      }

      if (other.isSetUnencoded_byte_array_data_bytes()) {
         List<Long> __this__unencoded_byte_array_data_bytes = new ArrayList(other.unencoded_byte_array_data_bytes);
         this.unencoded_byte_array_data_bytes = __this__unencoded_byte_array_data_bytes;
      }

   }

   public OffsetIndex deepCopy() {
      return new OffsetIndex(this);
   }

   public void clear() {
      this.page_locations = null;
      this.unencoded_byte_array_data_bytes = null;
   }

   public int getPage_locationsSize() {
      return this.page_locations == null ? 0 : this.page_locations.size();
   }

   @Nullable
   public Iterator getPage_locationsIterator() {
      return this.page_locations == null ? null : this.page_locations.iterator();
   }

   public void addToPage_locations(PageLocation elem) {
      if (this.page_locations == null) {
         this.page_locations = new ArrayList();
      }

      this.page_locations.add(elem);
   }

   @Nullable
   public List getPage_locations() {
      return this.page_locations;
   }

   public OffsetIndex setPage_locations(@Nullable List page_locations) {
      this.page_locations = page_locations;
      return this;
   }

   public void unsetPage_locations() {
      this.page_locations = null;
   }

   public boolean isSetPage_locations() {
      return this.page_locations != null;
   }

   public void setPage_locationsIsSet(boolean value) {
      if (!value) {
         this.page_locations = null;
      }

   }

   public int getUnencoded_byte_array_data_bytesSize() {
      return this.unencoded_byte_array_data_bytes == null ? 0 : this.unencoded_byte_array_data_bytes.size();
   }

   @Nullable
   public Iterator getUnencoded_byte_array_data_bytesIterator() {
      return this.unencoded_byte_array_data_bytes == null ? null : this.unencoded_byte_array_data_bytes.iterator();
   }

   public void addToUnencoded_byte_array_data_bytes(long elem) {
      if (this.unencoded_byte_array_data_bytes == null) {
         this.unencoded_byte_array_data_bytes = new ArrayList();
      }

      this.unencoded_byte_array_data_bytes.add(elem);
   }

   @Nullable
   public List getUnencoded_byte_array_data_bytes() {
      return this.unencoded_byte_array_data_bytes;
   }

   public OffsetIndex setUnencoded_byte_array_data_bytes(@Nullable List unencoded_byte_array_data_bytes) {
      this.unencoded_byte_array_data_bytes = unencoded_byte_array_data_bytes;
      return this;
   }

   public void unsetUnencoded_byte_array_data_bytes() {
      this.unencoded_byte_array_data_bytes = null;
   }

   public boolean isSetUnencoded_byte_array_data_bytes() {
      return this.unencoded_byte_array_data_bytes != null;
   }

   public void setUnencoded_byte_array_data_bytesIsSet(boolean value) {
      if (!value) {
         this.unencoded_byte_array_data_bytes = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PAGE_LOCATIONS:
            if (value == null) {
               this.unsetPage_locations();
            } else {
               this.setPage_locations((List)value);
            }
            break;
         case UNENCODED_BYTE_ARRAY_DATA_BYTES:
            if (value == null) {
               this.unsetUnencoded_byte_array_data_bytes();
            } else {
               this.setUnencoded_byte_array_data_bytes((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PAGE_LOCATIONS:
            return this.getPage_locations();
         case UNENCODED_BYTE_ARRAY_DATA_BYTES:
            return this.getUnencoded_byte_array_data_bytes();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PAGE_LOCATIONS:
               return this.isSetPage_locations();
            case UNENCODED_BYTE_ARRAY_DATA_BYTES:
               return this.isSetUnencoded_byte_array_data_bytes();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof OffsetIndex ? this.equals((OffsetIndex)that) : false;
   }

   public boolean equals(OffsetIndex that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_page_locations = this.isSetPage_locations();
         boolean that_present_page_locations = that.isSetPage_locations();
         if (this_present_page_locations || that_present_page_locations) {
            if (!this_present_page_locations || !that_present_page_locations) {
               return false;
            }

            if (!this.page_locations.equals(that.page_locations)) {
               return false;
            }
         }

         boolean this_present_unencoded_byte_array_data_bytes = this.isSetUnencoded_byte_array_data_bytes();
         boolean that_present_unencoded_byte_array_data_bytes = that.isSetUnencoded_byte_array_data_bytes();
         if (this_present_unencoded_byte_array_data_bytes || that_present_unencoded_byte_array_data_bytes) {
            if (!this_present_unencoded_byte_array_data_bytes || !that_present_unencoded_byte_array_data_bytes) {
               return false;
            }

            if (!this.unencoded_byte_array_data_bytes.equals(that.unencoded_byte_array_data_bytes)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPage_locations() ? 131071 : 524287);
      if (this.isSetPage_locations()) {
         hashCode = hashCode * 8191 + this.page_locations.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetUnencoded_byte_array_data_bytes() ? 131071 : 524287);
      if (this.isSetUnencoded_byte_array_data_bytes()) {
         hashCode = hashCode * 8191 + this.unencoded_byte_array_data_bytes.hashCode();
      }

      return hashCode;
   }

   public int compareTo(OffsetIndex other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPage_locations(), other.isSetPage_locations());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPage_locations()) {
               lastComparison = TBaseHelper.compareTo(this.page_locations, other.page_locations);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetUnencoded_byte_array_data_bytes(), other.isSetUnencoded_byte_array_data_bytes());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetUnencoded_byte_array_data_bytes()) {
                  lastComparison = TBaseHelper.compareTo(this.unencoded_byte_array_data_bytes, other.unencoded_byte_array_data_bytes);
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
      return OffsetIndex._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("OffsetIndex(");
      boolean first = true;
      sb.append("page_locations:");
      if (this.page_locations == null) {
         sb.append("null");
      } else {
         sb.append(this.page_locations);
      }

      first = false;
      if (this.isSetUnencoded_byte_array_data_bytes()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("unencoded_byte_array_data_bytes:");
         if (this.unencoded_byte_array_data_bytes == null) {
            sb.append("null");
         } else {
            sb.append(this.unencoded_byte_array_data_bytes);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.page_locations == null) {
         throw new TProtocolException("Required field 'page_locations' was not present! Struct: " + this.toString());
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
      optionals = new _Fields[]{OffsetIndex._Fields.UNENCODED_BYTE_ARRAY_DATA_BYTES};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(OffsetIndex._Fields.PAGE_LOCATIONS, new FieldMetaData("page_locations", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, PageLocation.class))));
      tmpMap.put(OffsetIndex._Fields.UNENCODED_BYTE_ARRAY_DATA_BYTES, new FieldMetaData("unencoded_byte_array_data_bytes", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(OffsetIndex.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PAGE_LOCATIONS((short)1, "page_locations"),
      UNENCODED_BYTE_ARRAY_DATA_BYTES((short)2, "unencoded_byte_array_data_bytes");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PAGE_LOCATIONS;
            case 2:
               return UNENCODED_BYTE_ARRAY_DATA_BYTES;
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

   private static class OffsetIndexStandardSchemeFactory implements SchemeFactory {
      private OffsetIndexStandardSchemeFactory() {
      }

      public OffsetIndexStandardScheme getScheme() {
         return new OffsetIndexStandardScheme();
      }
   }

   private static class OffsetIndexStandardScheme extends StandardScheme {
      private OffsetIndexStandardScheme() {
      }

      public void read(TProtocol iprot, OffsetIndex struct) throws TException {
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

                  TList _list72 = iprot.readListBegin();
                  struct.page_locations = new ArrayList(_list72.size);

                  for(int _i74 = 0; _i74 < _list72.size; ++_i74) {
                     PageLocation _elem73 = new PageLocation();
                     _elem73.read(iprot);
                     struct.page_locations.add(_elem73);
                  }

                  iprot.readListEnd();
                  struct.setPage_locationsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list75 = iprot.readListBegin();
                  struct.unencoded_byte_array_data_bytes = new ArrayList(_list75.size);

                  for(int _i77 = 0; _i77 < _list75.size; ++_i77) {
                     long _elem76 = iprot.readI64();
                     struct.unencoded_byte_array_data_bytes.add(_elem76);
                  }

                  iprot.readListEnd();
                  struct.setUnencoded_byte_array_data_bytesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, OffsetIndex struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(OffsetIndex.STRUCT_DESC);
         if (struct.page_locations != null) {
            oprot.writeFieldBegin(OffsetIndex.PAGE_LOCATIONS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.page_locations.size()));

            for(PageLocation _iter78 : struct.page_locations) {
               _iter78.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.unencoded_byte_array_data_bytes != null && struct.isSetUnencoded_byte_array_data_bytes()) {
            oprot.writeFieldBegin(OffsetIndex.UNENCODED_BYTE_ARRAY_DATA_BYTES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.unencoded_byte_array_data_bytes.size()));

            for(long _iter79 : struct.unencoded_byte_array_data_bytes) {
               oprot.writeI64(_iter79);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class OffsetIndexTupleSchemeFactory implements SchemeFactory {
      private OffsetIndexTupleSchemeFactory() {
      }

      public OffsetIndexTupleScheme getScheme() {
         return new OffsetIndexTupleScheme();
      }
   }

   private static class OffsetIndexTupleScheme extends TupleScheme {
      private OffsetIndexTupleScheme() {
      }

      public void write(TProtocol prot, OffsetIndex struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.page_locations.size());

         for(PageLocation _iter80 : struct.page_locations) {
            _iter80.write(oprot);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetUnencoded_byte_array_data_bytes()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetUnencoded_byte_array_data_bytes()) {
            oprot.writeI32(struct.unencoded_byte_array_data_bytes.size());

            for(long _iter81 : struct.unencoded_byte_array_data_bytes) {
               oprot.writeI64(_iter81);
            }
         }

      }

      public void read(TProtocol prot, OffsetIndex struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list82 = iprot.readListBegin((byte)12);
         struct.page_locations = new ArrayList(_list82.size);

         for(int _i84 = 0; _i84 < _list82.size; ++_i84) {
            PageLocation _elem83 = new PageLocation();
            _elem83.read(iprot);
            struct.page_locations.add(_elem83);
         }

         struct.setPage_locationsIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list85 = iprot.readListBegin((byte)10);
            struct.unencoded_byte_array_data_bytes = new ArrayList(_list85.size);

            for(int _i87 = 0; _i87 < _list85.size; ++_i87) {
               long _elem86 = iprot.readI64();
               struct.unencoded_byte_array_data_bytes.add(_elem86);
            }

            struct.setUnencoded_byte_array_data_bytesIsSet(true);
         }

      }
   }
}
