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
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
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

public class ClientCapabilities implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ClientCapabilities");
   private static final TField VALUES_FIELD_DESC = new TField("values", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ClientCapabilitiesStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ClientCapabilitiesTupleSchemeFactory();
   @Nullable
   private List values;
   public static final Map metaDataMap;

   public ClientCapabilities() {
   }

   public ClientCapabilities(List values) {
      this();
      this.values = values;
   }

   public ClientCapabilities(ClientCapabilities other) {
      if (other.isSetValues()) {
         List<ClientCapability> __this__values = new ArrayList(other.values.size());

         for(ClientCapability other_element : other.values) {
            __this__values.add(other_element);
         }

         this.values = __this__values;
      }

   }

   public ClientCapabilities deepCopy() {
      return new ClientCapabilities(this);
   }

   public void clear() {
      this.values = null;
   }

   public int getValuesSize() {
      return this.values == null ? 0 : this.values.size();
   }

   @Nullable
   public Iterator getValuesIterator() {
      return this.values == null ? null : this.values.iterator();
   }

   public void addToValues(ClientCapability elem) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(elem);
   }

   @Nullable
   public List getValues() {
      return this.values;
   }

   public void setValues(@Nullable List values) {
      this.values = values;
   }

   public void unsetValues() {
      this.values = null;
   }

   public boolean isSetValues() {
      return this.values != null;
   }

   public void setValuesIsSet(boolean value) {
      if (!value) {
         this.values = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case VALUES:
            if (value == null) {
               this.unsetValues();
            } else {
               this.setValues((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case VALUES:
            return this.getValues();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case VALUES:
               return this.isSetValues();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ClientCapabilities ? this.equals((ClientCapabilities)that) : false;
   }

   public boolean equals(ClientCapabilities that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_values = this.isSetValues();
         boolean that_present_values = that.isSetValues();
         if (this_present_values || that_present_values) {
            if (!this_present_values || !that_present_values) {
               return false;
            }

            if (!this.values.equals(that.values)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetValues() ? 131071 : 524287);
      if (this.isSetValues()) {
         hashCode = hashCode * 8191 + this.values.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ClientCapabilities other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetValues(), other.isSetValues());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetValues()) {
               lastComparison = TBaseHelper.compareTo(this.values, other.values);
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
      return ClientCapabilities._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ClientCapabilities(");
      boolean first = true;
      sb.append("values:");
      if (this.values == null) {
         sb.append("null");
      } else {
         sb.append(this.values);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetValues()) {
         throw new TProtocolException("Required field 'values' is unset! Struct:" + this.toString());
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
      tmpMap.put(ClientCapabilities._Fields.VALUES, new FieldMetaData("values", (byte)1, new ListMetaData((byte)15, new EnumMetaData((byte)16, ClientCapability.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ClientCapabilities.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VALUES((short)1, "values");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VALUES;
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

   private static class ClientCapabilitiesStandardSchemeFactory implements SchemeFactory {
      private ClientCapabilitiesStandardSchemeFactory() {
      }

      public ClientCapabilitiesStandardScheme getScheme() {
         return new ClientCapabilitiesStandardScheme();
      }
   }

   private static class ClientCapabilitiesStandardScheme extends StandardScheme {
      private ClientCapabilitiesStandardScheme() {
      }

      public void read(TProtocol iprot, ClientCapabilities struct) throws TException {
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

                  TList _list682 = iprot.readListBegin();
                  struct.values = new ArrayList(_list682.size);
                  int _i684 = 0;

                  for(; _i684 < _list682.size; ++_i684) {
                     ClientCapability _elem683 = ClientCapability.findByValue(iprot.readI32());
                     if (_elem683 != null) {
                        struct.values.add(_elem683);
                     }
                  }

                  iprot.readListEnd();
                  struct.setValuesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ClientCapabilities struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ClientCapabilities.STRUCT_DESC);
         if (struct.values != null) {
            oprot.writeFieldBegin(ClientCapabilities.VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)8, struct.values.size()));

            for(ClientCapability _iter685 : struct.values) {
               oprot.writeI32(_iter685.getValue());
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ClientCapabilitiesTupleSchemeFactory implements SchemeFactory {
      private ClientCapabilitiesTupleSchemeFactory() {
      }

      public ClientCapabilitiesTupleScheme getScheme() {
         return new ClientCapabilitiesTupleScheme();
      }
   }

   private static class ClientCapabilitiesTupleScheme extends TupleScheme {
      private ClientCapabilitiesTupleScheme() {
      }

      public void write(TProtocol prot, ClientCapabilities struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.values.size());

         for(ClientCapability _iter686 : struct.values) {
            oprot.writeI32(_iter686.getValue());
         }

      }

      public void read(TProtocol prot, ClientCapabilities struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list687 = iprot.readListBegin((byte)8);
         struct.values = new ArrayList(_list687.size);

         for(int _i689 = 0; _i689 < _list687.size; ++_i689) {
            ClientCapability _elem688 = ClientCapability.findByValue(iprot.readI32());
            if (_elem688 != null) {
               struct.values.add(_elem688);
            }
         }

         struct.setValuesIsSet(true);
      }
   }
}
