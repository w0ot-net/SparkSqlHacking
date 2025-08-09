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
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
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

public class ShowCompactResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ShowCompactResponse");
   private static final TField COMPACTS_FIELD_DESC = new TField("compacts", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ShowCompactResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ShowCompactResponseTupleSchemeFactory();
   @Nullable
   private List compacts;
   public static final Map metaDataMap;

   public ShowCompactResponse() {
   }

   public ShowCompactResponse(List compacts) {
      this();
      this.compacts = compacts;
   }

   public ShowCompactResponse(ShowCompactResponse other) {
      if (other.isSetCompacts()) {
         List<ShowCompactResponseElement> __this__compacts = new ArrayList(other.compacts.size());

         for(ShowCompactResponseElement other_element : other.compacts) {
            __this__compacts.add(new ShowCompactResponseElement(other_element));
         }

         this.compacts = __this__compacts;
      }

   }

   public ShowCompactResponse deepCopy() {
      return new ShowCompactResponse(this);
   }

   public void clear() {
      this.compacts = null;
   }

   public int getCompactsSize() {
      return this.compacts == null ? 0 : this.compacts.size();
   }

   @Nullable
   public Iterator getCompactsIterator() {
      return this.compacts == null ? null : this.compacts.iterator();
   }

   public void addToCompacts(ShowCompactResponseElement elem) {
      if (this.compacts == null) {
         this.compacts = new ArrayList();
      }

      this.compacts.add(elem);
   }

   @Nullable
   public List getCompacts() {
      return this.compacts;
   }

   public void setCompacts(@Nullable List compacts) {
      this.compacts = compacts;
   }

   public void unsetCompacts() {
      this.compacts = null;
   }

   public boolean isSetCompacts() {
      return this.compacts != null;
   }

   public void setCompactsIsSet(boolean value) {
      if (!value) {
         this.compacts = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COMPACTS:
            if (value == null) {
               this.unsetCompacts();
            } else {
               this.setCompacts((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COMPACTS:
            return this.getCompacts();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COMPACTS:
               return this.isSetCompacts();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ShowCompactResponse ? this.equals((ShowCompactResponse)that) : false;
   }

   public boolean equals(ShowCompactResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_compacts = this.isSetCompacts();
         boolean that_present_compacts = that.isSetCompacts();
         if (this_present_compacts || that_present_compacts) {
            if (!this_present_compacts || !that_present_compacts) {
               return false;
            }

            if (!this.compacts.equals(that.compacts)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetCompacts() ? 131071 : 524287);
      if (this.isSetCompacts()) {
         hashCode = hashCode * 8191 + this.compacts.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ShowCompactResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetCompacts(), other.isSetCompacts());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetCompacts()) {
               lastComparison = TBaseHelper.compareTo(this.compacts, other.compacts);
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
      return ShowCompactResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ShowCompactResponse(");
      boolean first = true;
      sb.append("compacts:");
      if (this.compacts == null) {
         sb.append("null");
      } else {
         sb.append(this.compacts);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetCompacts()) {
         throw new TProtocolException("Required field 'compacts' is unset! Struct:" + this.toString());
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
      tmpMap.put(ShowCompactResponse._Fields.COMPACTS, new FieldMetaData("compacts", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, ShowCompactResponseElement.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ShowCompactResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COMPACTS((short)1, "compacts");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COMPACTS;
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

   private static class ShowCompactResponseStandardSchemeFactory implements SchemeFactory {
      private ShowCompactResponseStandardSchemeFactory() {
      }

      public ShowCompactResponseStandardScheme getScheme() {
         return new ShowCompactResponseStandardScheme();
      }
   }

   private static class ShowCompactResponseStandardScheme extends StandardScheme {
      private ShowCompactResponseStandardScheme() {
      }

      public void read(TProtocol iprot, ShowCompactResponse struct) throws TException {
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

                  TList _list566 = iprot.readListBegin();
                  struct.compacts = new ArrayList(_list566.size);

                  for(int _i568 = 0; _i568 < _list566.size; ++_i568) {
                     ShowCompactResponseElement _elem567 = new ShowCompactResponseElement();
                     _elem567.read(iprot);
                     struct.compacts.add(_elem567);
                  }

                  iprot.readListEnd();
                  struct.setCompactsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ShowCompactResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ShowCompactResponse.STRUCT_DESC);
         if (struct.compacts != null) {
            oprot.writeFieldBegin(ShowCompactResponse.COMPACTS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.compacts.size()));

            for(ShowCompactResponseElement _iter569 : struct.compacts) {
               _iter569.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ShowCompactResponseTupleSchemeFactory implements SchemeFactory {
      private ShowCompactResponseTupleSchemeFactory() {
      }

      public ShowCompactResponseTupleScheme getScheme() {
         return new ShowCompactResponseTupleScheme();
      }
   }

   private static class ShowCompactResponseTupleScheme extends TupleScheme {
      private ShowCompactResponseTupleScheme() {
      }

      public void write(TProtocol prot, ShowCompactResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.compacts.size());

         for(ShowCompactResponseElement _iter570 : struct.compacts) {
            _iter570.write(oprot);
         }

      }

      public void read(TProtocol prot, ShowCompactResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list571 = iprot.readListBegin((byte)12);
         struct.compacts = new ArrayList(_list571.size);

         for(int _i573 = 0; _i573 < _list571.size; ++_i573) {
            ShowCompactResponseElement _elem572 = new ShowCompactResponseElement();
            _elem572.read(iprot);
            struct.compacts.add(_elem572);
         }

         struct.setCompactsIsSet(true);
      }
   }
}
