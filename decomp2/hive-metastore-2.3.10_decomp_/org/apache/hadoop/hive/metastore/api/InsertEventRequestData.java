package org.apache.hadoop.hive.metastore.api;

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

public class InsertEventRequestData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("InsertEventRequestData");
   private static final TField FILES_ADDED_FIELD_DESC = new TField("filesAdded", (byte)15, (short)1);
   private static final TField FILES_ADDED_CHECKSUM_FIELD_DESC = new TField("filesAddedChecksum", (byte)15, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new InsertEventRequestDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new InsertEventRequestDataTupleSchemeFactory();
   @Nullable
   private List filesAdded;
   @Nullable
   private List filesAddedChecksum;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public InsertEventRequestData() {
   }

   public InsertEventRequestData(List filesAdded) {
      this();
      this.filesAdded = filesAdded;
   }

   public InsertEventRequestData(InsertEventRequestData other) {
      if (other.isSetFilesAdded()) {
         List<String> __this__filesAdded = new ArrayList(other.filesAdded);
         this.filesAdded = __this__filesAdded;
      }

      if (other.isSetFilesAddedChecksum()) {
         List<String> __this__filesAddedChecksum = new ArrayList(other.filesAddedChecksum);
         this.filesAddedChecksum = __this__filesAddedChecksum;
      }

   }

   public InsertEventRequestData deepCopy() {
      return new InsertEventRequestData(this);
   }

   public void clear() {
      this.filesAdded = null;
      this.filesAddedChecksum = null;
   }

   public int getFilesAddedSize() {
      return this.filesAdded == null ? 0 : this.filesAdded.size();
   }

   @Nullable
   public Iterator getFilesAddedIterator() {
      return this.filesAdded == null ? null : this.filesAdded.iterator();
   }

   public void addToFilesAdded(String elem) {
      if (this.filesAdded == null) {
         this.filesAdded = new ArrayList();
      }

      this.filesAdded.add(elem);
   }

   @Nullable
   public List getFilesAdded() {
      return this.filesAdded;
   }

   public void setFilesAdded(@Nullable List filesAdded) {
      this.filesAdded = filesAdded;
   }

   public void unsetFilesAdded() {
      this.filesAdded = null;
   }

   public boolean isSetFilesAdded() {
      return this.filesAdded != null;
   }

   public void setFilesAddedIsSet(boolean value) {
      if (!value) {
         this.filesAdded = null;
      }

   }

   public int getFilesAddedChecksumSize() {
      return this.filesAddedChecksum == null ? 0 : this.filesAddedChecksum.size();
   }

   @Nullable
   public Iterator getFilesAddedChecksumIterator() {
      return this.filesAddedChecksum == null ? null : this.filesAddedChecksum.iterator();
   }

   public void addToFilesAddedChecksum(String elem) {
      if (this.filesAddedChecksum == null) {
         this.filesAddedChecksum = new ArrayList();
      }

      this.filesAddedChecksum.add(elem);
   }

   @Nullable
   public List getFilesAddedChecksum() {
      return this.filesAddedChecksum;
   }

   public void setFilesAddedChecksum(@Nullable List filesAddedChecksum) {
      this.filesAddedChecksum = filesAddedChecksum;
   }

   public void unsetFilesAddedChecksum() {
      this.filesAddedChecksum = null;
   }

   public boolean isSetFilesAddedChecksum() {
      return this.filesAddedChecksum != null;
   }

   public void setFilesAddedChecksumIsSet(boolean value) {
      if (!value) {
         this.filesAddedChecksum = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FILES_ADDED:
            if (value == null) {
               this.unsetFilesAdded();
            } else {
               this.setFilesAdded((List)value);
            }
            break;
         case FILES_ADDED_CHECKSUM:
            if (value == null) {
               this.unsetFilesAddedChecksum();
            } else {
               this.setFilesAddedChecksum((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FILES_ADDED:
            return this.getFilesAdded();
         case FILES_ADDED_CHECKSUM:
            return this.getFilesAddedChecksum();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FILES_ADDED:
               return this.isSetFilesAdded();
            case FILES_ADDED_CHECKSUM:
               return this.isSetFilesAddedChecksum();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof InsertEventRequestData ? this.equals((InsertEventRequestData)that) : false;
   }

   public boolean equals(InsertEventRequestData that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_filesAdded = this.isSetFilesAdded();
         boolean that_present_filesAdded = that.isSetFilesAdded();
         if (this_present_filesAdded || that_present_filesAdded) {
            if (!this_present_filesAdded || !that_present_filesAdded) {
               return false;
            }

            if (!this.filesAdded.equals(that.filesAdded)) {
               return false;
            }
         }

         boolean this_present_filesAddedChecksum = this.isSetFilesAddedChecksum();
         boolean that_present_filesAddedChecksum = that.isSetFilesAddedChecksum();
         if (this_present_filesAddedChecksum || that_present_filesAddedChecksum) {
            if (!this_present_filesAddedChecksum || !that_present_filesAddedChecksum) {
               return false;
            }

            if (!this.filesAddedChecksum.equals(that.filesAddedChecksum)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetFilesAdded() ? 131071 : 524287);
      if (this.isSetFilesAdded()) {
         hashCode = hashCode * 8191 + this.filesAdded.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFilesAddedChecksum() ? 131071 : 524287);
      if (this.isSetFilesAddedChecksum()) {
         hashCode = hashCode * 8191 + this.filesAddedChecksum.hashCode();
      }

      return hashCode;
   }

   public int compareTo(InsertEventRequestData other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetFilesAdded(), other.isSetFilesAdded());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetFilesAdded()) {
               lastComparison = TBaseHelper.compareTo(this.filesAdded, other.filesAdded);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetFilesAddedChecksum(), other.isSetFilesAddedChecksum());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetFilesAddedChecksum()) {
                  lastComparison = TBaseHelper.compareTo(this.filesAddedChecksum, other.filesAddedChecksum);
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
      return InsertEventRequestData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("InsertEventRequestData(");
      boolean first = true;
      sb.append("filesAdded:");
      if (this.filesAdded == null) {
         sb.append("null");
      } else {
         sb.append(this.filesAdded);
      }

      first = false;
      if (this.isSetFilesAddedChecksum()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("filesAddedChecksum:");
         if (this.filesAddedChecksum == null) {
            sb.append("null");
         } else {
            sb.append(this.filesAddedChecksum);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetFilesAdded()) {
         throw new TProtocolException("Required field 'filesAdded' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{InsertEventRequestData._Fields.FILES_ADDED_CHECKSUM};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(InsertEventRequestData._Fields.FILES_ADDED, new FieldMetaData("filesAdded", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(InsertEventRequestData._Fields.FILES_ADDED_CHECKSUM, new FieldMetaData("filesAddedChecksum", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(InsertEventRequestData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FILES_ADDED((short)1, "filesAdded"),
      FILES_ADDED_CHECKSUM((short)2, "filesAddedChecksum");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FILES_ADDED;
            case 2:
               return FILES_ADDED_CHECKSUM;
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

   private static class InsertEventRequestDataStandardSchemeFactory implements SchemeFactory {
      private InsertEventRequestDataStandardSchemeFactory() {
      }

      public InsertEventRequestDataStandardScheme getScheme() {
         return new InsertEventRequestDataStandardScheme();
      }
   }

   private static class InsertEventRequestDataStandardScheme extends StandardScheme {
      private InsertEventRequestDataStandardScheme() {
      }

      public void read(TProtocol iprot, InsertEventRequestData struct) throws TException {
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

                  TList _list590 = iprot.readListBegin();
                  struct.filesAdded = new ArrayList(_list590.size);

                  for(int _i592 = 0; _i592 < _list590.size; ++_i592) {
                     String _elem591 = iprot.readString();
                     struct.filesAdded.add(_elem591);
                  }

                  iprot.readListEnd();
                  struct.setFilesAddedIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list593 = iprot.readListBegin();
                  struct.filesAddedChecksum = new ArrayList(_list593.size);

                  for(int _i595 = 0; _i595 < _list593.size; ++_i595) {
                     String _elem594 = iprot.readString();
                     struct.filesAddedChecksum.add(_elem594);
                  }

                  iprot.readListEnd();
                  struct.setFilesAddedChecksumIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, InsertEventRequestData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(InsertEventRequestData.STRUCT_DESC);
         if (struct.filesAdded != null) {
            oprot.writeFieldBegin(InsertEventRequestData.FILES_ADDED_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.filesAdded.size()));

            for(String _iter596 : struct.filesAdded) {
               oprot.writeString(_iter596);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.filesAddedChecksum != null && struct.isSetFilesAddedChecksum()) {
            oprot.writeFieldBegin(InsertEventRequestData.FILES_ADDED_CHECKSUM_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.filesAddedChecksum.size()));

            for(String _iter597 : struct.filesAddedChecksum) {
               oprot.writeString(_iter597);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class InsertEventRequestDataTupleSchemeFactory implements SchemeFactory {
      private InsertEventRequestDataTupleSchemeFactory() {
      }

      public InsertEventRequestDataTupleScheme getScheme() {
         return new InsertEventRequestDataTupleScheme();
      }
   }

   private static class InsertEventRequestDataTupleScheme extends TupleScheme {
      private InsertEventRequestDataTupleScheme() {
      }

      public void write(TProtocol prot, InsertEventRequestData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.filesAdded.size());

         for(String _iter598 : struct.filesAdded) {
            oprot.writeString(_iter598);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetFilesAddedChecksum()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetFilesAddedChecksum()) {
            oprot.writeI32(struct.filesAddedChecksum.size());

            for(String _iter599 : struct.filesAddedChecksum) {
               oprot.writeString(_iter599);
            }
         }

      }

      public void read(TProtocol prot, InsertEventRequestData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list600 = iprot.readListBegin((byte)11);
         struct.filesAdded = new ArrayList(_list600.size);

         for(int _i602 = 0; _i602 < _list600.size; ++_i602) {
            String _elem601 = iprot.readString();
            struct.filesAdded.add(_elem601);
         }

         struct.setFilesAddedIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list603 = iprot.readListBegin((byte)11);
            struct.filesAddedChecksum = new ArrayList(_list603.size);

            for(int _i605 = 0; _i605 < _list603.size; ++_i605) {
               String _elem604 = iprot.readString();
               struct.filesAddedChecksum.add(_elem604);
            }

            struct.setFilesAddedChecksumIsSet(true);
         }

      }
   }
}
