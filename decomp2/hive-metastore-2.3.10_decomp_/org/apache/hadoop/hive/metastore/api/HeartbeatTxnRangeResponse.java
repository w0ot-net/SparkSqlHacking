package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class HeartbeatTxnRangeResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("HeartbeatTxnRangeResponse");
   private static final TField ABORTED_FIELD_DESC = new TField("aborted", (byte)14, (short)1);
   private static final TField NOSUCH_FIELD_DESC = new TField("nosuch", (byte)14, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new HeartbeatTxnRangeResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new HeartbeatTxnRangeResponseTupleSchemeFactory();
   @Nullable
   private Set aborted;
   @Nullable
   private Set nosuch;
   public static final Map metaDataMap;

   public HeartbeatTxnRangeResponse() {
   }

   public HeartbeatTxnRangeResponse(Set aborted, Set nosuch) {
      this();
      this.aborted = aborted;
      this.nosuch = nosuch;
   }

   public HeartbeatTxnRangeResponse(HeartbeatTxnRangeResponse other) {
      if (other.isSetAborted()) {
         Set<Long> __this__aborted = new HashSet(other.aborted);
         this.aborted = __this__aborted;
      }

      if (other.isSetNosuch()) {
         Set<Long> __this__nosuch = new HashSet(other.nosuch);
         this.nosuch = __this__nosuch;
      }

   }

   public HeartbeatTxnRangeResponse deepCopy() {
      return new HeartbeatTxnRangeResponse(this);
   }

   public void clear() {
      this.aborted = null;
      this.nosuch = null;
   }

   public int getAbortedSize() {
      return this.aborted == null ? 0 : this.aborted.size();
   }

   @Nullable
   public Iterator getAbortedIterator() {
      return this.aborted == null ? null : this.aborted.iterator();
   }

   public void addToAborted(long elem) {
      if (this.aborted == null) {
         this.aborted = new HashSet();
      }

      this.aborted.add(elem);
   }

   @Nullable
   public Set getAborted() {
      return this.aborted;
   }

   public void setAborted(@Nullable Set aborted) {
      this.aborted = aborted;
   }

   public void unsetAborted() {
      this.aborted = null;
   }

   public boolean isSetAborted() {
      return this.aborted != null;
   }

   public void setAbortedIsSet(boolean value) {
      if (!value) {
         this.aborted = null;
      }

   }

   public int getNosuchSize() {
      return this.nosuch == null ? 0 : this.nosuch.size();
   }

   @Nullable
   public Iterator getNosuchIterator() {
      return this.nosuch == null ? null : this.nosuch.iterator();
   }

   public void addToNosuch(long elem) {
      if (this.nosuch == null) {
         this.nosuch = new HashSet();
      }

      this.nosuch.add(elem);
   }

   @Nullable
   public Set getNosuch() {
      return this.nosuch;
   }

   public void setNosuch(@Nullable Set nosuch) {
      this.nosuch = nosuch;
   }

   public void unsetNosuch() {
      this.nosuch = null;
   }

   public boolean isSetNosuch() {
      return this.nosuch != null;
   }

   public void setNosuchIsSet(boolean value) {
      if (!value) {
         this.nosuch = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case ABORTED:
            if (value == null) {
               this.unsetAborted();
            } else {
               this.setAborted((Set)value);
            }
            break;
         case NOSUCH:
            if (value == null) {
               this.unsetNosuch();
            } else {
               this.setNosuch((Set)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ABORTED:
            return this.getAborted();
         case NOSUCH:
            return this.getNosuch();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case ABORTED:
               return this.isSetAborted();
            case NOSUCH:
               return this.isSetNosuch();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof HeartbeatTxnRangeResponse ? this.equals((HeartbeatTxnRangeResponse)that) : false;
   }

   public boolean equals(HeartbeatTxnRangeResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_aborted = this.isSetAborted();
         boolean that_present_aborted = that.isSetAborted();
         if (this_present_aborted || that_present_aborted) {
            if (!this_present_aborted || !that_present_aborted) {
               return false;
            }

            if (!this.aborted.equals(that.aborted)) {
               return false;
            }
         }

         boolean this_present_nosuch = this.isSetNosuch();
         boolean that_present_nosuch = that.isSetNosuch();
         if (this_present_nosuch || that_present_nosuch) {
            if (!this_present_nosuch || !that_present_nosuch) {
               return false;
            }

            if (!this.nosuch.equals(that.nosuch)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetAborted() ? 131071 : 524287);
      if (this.isSetAborted()) {
         hashCode = hashCode * 8191 + this.aborted.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetNosuch() ? 131071 : 524287);
      if (this.isSetNosuch()) {
         hashCode = hashCode * 8191 + this.nosuch.hashCode();
      }

      return hashCode;
   }

   public int compareTo(HeartbeatTxnRangeResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetAborted(), other.isSetAborted());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetAborted()) {
               lastComparison = TBaseHelper.compareTo(this.aborted, other.aborted);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetNosuch(), other.isSetNosuch());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetNosuch()) {
                  lastComparison = TBaseHelper.compareTo(this.nosuch, other.nosuch);
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
      return HeartbeatTxnRangeResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("HeartbeatTxnRangeResponse(");
      boolean first = true;
      sb.append("aborted:");
      if (this.aborted == null) {
         sb.append("null");
      } else {
         sb.append(this.aborted);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("nosuch:");
      if (this.nosuch == null) {
         sb.append("null");
      } else {
         sb.append(this.nosuch);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetAborted()) {
         throw new TProtocolException("Required field 'aborted' is unset! Struct:" + this.toString());
      } else if (!this.isSetNosuch()) {
         throw new TProtocolException("Required field 'nosuch' is unset! Struct:" + this.toString());
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
      tmpMap.put(HeartbeatTxnRangeResponse._Fields.ABORTED, new FieldMetaData("aborted", (byte)1, new SetMetaData((byte)14, new FieldValueMetaData((byte)10))));
      tmpMap.put(HeartbeatTxnRangeResponse._Fields.NOSUCH, new FieldMetaData("nosuch", (byte)1, new SetMetaData((byte)14, new FieldValueMetaData((byte)10))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(HeartbeatTxnRangeResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ABORTED((short)1, "aborted"),
      NOSUCH((short)2, "nosuch");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ABORTED;
            case 2:
               return NOSUCH;
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

   private static class HeartbeatTxnRangeResponseStandardSchemeFactory implements SchemeFactory {
      private HeartbeatTxnRangeResponseStandardSchemeFactory() {
      }

      public HeartbeatTxnRangeResponseStandardScheme getScheme() {
         return new HeartbeatTxnRangeResponseStandardScheme();
      }
   }

   private static class HeartbeatTxnRangeResponseStandardScheme extends StandardScheme {
      private HeartbeatTxnRangeResponseStandardScheme() {
      }

      public void read(TProtocol iprot, HeartbeatTxnRangeResponse struct) throws TException {
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
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set540 = iprot.readSetBegin();
                  struct.aborted = new HashSet(2 * _set540.size);

                  for(int _i542 = 0; _i542 < _set540.size; ++_i542) {
                     long _elem541 = iprot.readI64();
                     struct.aborted.add(_elem541);
                  }

                  iprot.readSetEnd();
                  struct.setAbortedIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set543 = iprot.readSetBegin();
                  struct.nosuch = new HashSet(2 * _set543.size);

                  for(int _i545 = 0; _i545 < _set543.size; ++_i545) {
                     long _elem544 = iprot.readI64();
                     struct.nosuch.add(_elem544);
                  }

                  iprot.readSetEnd();
                  struct.setNosuchIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, HeartbeatTxnRangeResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(HeartbeatTxnRangeResponse.STRUCT_DESC);
         if (struct.aborted != null) {
            oprot.writeFieldBegin(HeartbeatTxnRangeResponse.ABORTED_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)10, struct.aborted.size()));

            for(long _iter546 : struct.aborted) {
               oprot.writeI64(_iter546);
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         if (struct.nosuch != null) {
            oprot.writeFieldBegin(HeartbeatTxnRangeResponse.NOSUCH_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)10, struct.nosuch.size()));

            for(long _iter547 : struct.nosuch) {
               oprot.writeI64(_iter547);
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class HeartbeatTxnRangeResponseTupleSchemeFactory implements SchemeFactory {
      private HeartbeatTxnRangeResponseTupleSchemeFactory() {
      }

      public HeartbeatTxnRangeResponseTupleScheme getScheme() {
         return new HeartbeatTxnRangeResponseTupleScheme();
      }
   }

   private static class HeartbeatTxnRangeResponseTupleScheme extends TupleScheme {
      private HeartbeatTxnRangeResponseTupleScheme() {
      }

      public void write(TProtocol prot, HeartbeatTxnRangeResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.aborted.size());

         for(long _iter548 : struct.aborted) {
            oprot.writeI64(_iter548);
         }

         oprot.writeI32(struct.nosuch.size());

         for(long _iter549 : struct.nosuch) {
            oprot.writeI64(_iter549);
         }

      }

      public void read(TProtocol prot, HeartbeatTxnRangeResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TSet _set550 = iprot.readSetBegin((byte)10);
         struct.aborted = new HashSet(2 * _set550.size);

         for(int _i552 = 0; _i552 < _set550.size; ++_i552) {
            long _elem551 = iprot.readI64();
            struct.aborted.add(_elem551);
         }

         struct.setAbortedIsSet(true);
         _set550 = iprot.readSetBegin((byte)10);
         struct.nosuch = new HashSet(2 * _set550.size);

         for(int _i555 = 0; _i555 < _set550.size; ++_i555) {
            long _elem554 = iprot.readI64();
            struct.nosuch.add(_elem554);
         }

         struct.setNosuchIsSet(true);
      }
   }
}
