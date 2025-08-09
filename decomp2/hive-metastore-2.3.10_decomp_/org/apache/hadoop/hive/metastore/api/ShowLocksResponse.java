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
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class ShowLocksResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ShowLocksResponse");
   private static final TField LOCKS_FIELD_DESC = new TField("locks", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ShowLocksResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ShowLocksResponseTupleSchemeFactory();
   @Nullable
   private List locks;
   public static final Map metaDataMap;

   public ShowLocksResponse() {
   }

   public ShowLocksResponse(List locks) {
      this();
      this.locks = locks;
   }

   public ShowLocksResponse(ShowLocksResponse other) {
      if (other.isSetLocks()) {
         List<ShowLocksResponseElement> __this__locks = new ArrayList(other.locks.size());

         for(ShowLocksResponseElement other_element : other.locks) {
            __this__locks.add(new ShowLocksResponseElement(other_element));
         }

         this.locks = __this__locks;
      }

   }

   public ShowLocksResponse deepCopy() {
      return new ShowLocksResponse(this);
   }

   public void clear() {
      this.locks = null;
   }

   public int getLocksSize() {
      return this.locks == null ? 0 : this.locks.size();
   }

   @Nullable
   public Iterator getLocksIterator() {
      return this.locks == null ? null : this.locks.iterator();
   }

   public void addToLocks(ShowLocksResponseElement elem) {
      if (this.locks == null) {
         this.locks = new ArrayList();
      }

      this.locks.add(elem);
   }

   @Nullable
   public List getLocks() {
      return this.locks;
   }

   public void setLocks(@Nullable List locks) {
      this.locks = locks;
   }

   public void unsetLocks() {
      this.locks = null;
   }

   public boolean isSetLocks() {
      return this.locks != null;
   }

   public void setLocksIsSet(boolean value) {
      if (!value) {
         this.locks = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LOCKS:
            if (value == null) {
               this.unsetLocks();
            } else {
               this.setLocks((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LOCKS:
            return this.getLocks();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case LOCKS:
               return this.isSetLocks();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ShowLocksResponse ? this.equals((ShowLocksResponse)that) : false;
   }

   public boolean equals(ShowLocksResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_locks = this.isSetLocks();
         boolean that_present_locks = that.isSetLocks();
         if (this_present_locks || that_present_locks) {
            if (!this_present_locks || !that_present_locks) {
               return false;
            }

            if (!this.locks.equals(that.locks)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetLocks() ? 131071 : 524287);
      if (this.isSetLocks()) {
         hashCode = hashCode * 8191 + this.locks.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ShowLocksResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetLocks(), other.isSetLocks());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetLocks()) {
               lastComparison = TBaseHelper.compareTo(this.locks, other.locks);
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
      return ShowLocksResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ShowLocksResponse(");
      boolean first = true;
      sb.append("locks:");
      if (this.locks == null) {
         sb.append("null");
      } else {
         sb.append(this.locks);
      }

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
      tmpMap.put(ShowLocksResponse._Fields.LOCKS, new FieldMetaData("locks", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, ShowLocksResponseElement.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ShowLocksResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LOCKS((short)1, "locks");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LOCKS;
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

   private static class ShowLocksResponseStandardSchemeFactory implements SchemeFactory {
      private ShowLocksResponseStandardSchemeFactory() {
      }

      public ShowLocksResponseStandardScheme getScheme() {
         return new ShowLocksResponseStandardScheme();
      }
   }

   private static class ShowLocksResponseStandardScheme extends StandardScheme {
      private ShowLocksResponseStandardScheme() {
      }

      public void read(TProtocol iprot, ShowLocksResponse struct) throws TException {
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

                  TList _list532 = iprot.readListBegin();
                  struct.locks = new ArrayList(_list532.size);

                  for(int _i534 = 0; _i534 < _list532.size; ++_i534) {
                     ShowLocksResponseElement _elem533 = new ShowLocksResponseElement();
                     _elem533.read(iprot);
                     struct.locks.add(_elem533);
                  }

                  iprot.readListEnd();
                  struct.setLocksIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ShowLocksResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ShowLocksResponse.STRUCT_DESC);
         if (struct.locks != null) {
            oprot.writeFieldBegin(ShowLocksResponse.LOCKS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.locks.size()));

            for(ShowLocksResponseElement _iter535 : struct.locks) {
               _iter535.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ShowLocksResponseTupleSchemeFactory implements SchemeFactory {
      private ShowLocksResponseTupleSchemeFactory() {
      }

      public ShowLocksResponseTupleScheme getScheme() {
         return new ShowLocksResponseTupleScheme();
      }
   }

   private static class ShowLocksResponseTupleScheme extends TupleScheme {
      private ShowLocksResponseTupleScheme() {
      }

      public void write(TProtocol prot, ShowLocksResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetLocks()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetLocks()) {
            oprot.writeI32(struct.locks.size());

            for(ShowLocksResponseElement _iter536 : struct.locks) {
               _iter536.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, ShowLocksResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TList _list537 = iprot.readListBegin((byte)12);
            struct.locks = new ArrayList(_list537.size);

            for(int _i539 = 0; _i539 < _list537.size; ++_i539) {
               ShowLocksResponseElement _elem538 = new ShowLocksResponseElement();
               _elem538.read(iprot);
               struct.locks.add(_elem538);
            }

            struct.setLocksIsSet(true);
         }

      }
   }
}
