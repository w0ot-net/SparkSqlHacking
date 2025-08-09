package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class EnvironmentContext implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("EnvironmentContext");
   private static final TField PROPERTIES_FIELD_DESC = new TField("properties", (byte)13, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new EnvironmentContextStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new EnvironmentContextTupleSchemeFactory();
   @Nullable
   private Map properties;
   public static final Map metaDataMap;

   public EnvironmentContext() {
   }

   public EnvironmentContext(Map properties) {
      this();
      this.properties = properties;
   }

   public EnvironmentContext(EnvironmentContext other) {
      if (other.isSetProperties()) {
         Map<String, String> __this__properties = new HashMap(other.properties);
         this.properties = __this__properties;
      }

   }

   public EnvironmentContext deepCopy() {
      return new EnvironmentContext(this);
   }

   public void clear() {
      this.properties = null;
   }

   public int getPropertiesSize() {
      return this.properties == null ? 0 : this.properties.size();
   }

   public void putToProperties(String key, String val) {
      if (this.properties == null) {
         this.properties = new HashMap();
      }

      this.properties.put(key, val);
   }

   @Nullable
   public Map getProperties() {
      return this.properties;
   }

   public void setProperties(@Nullable Map properties) {
      this.properties = properties;
   }

   public void unsetProperties() {
      this.properties = null;
   }

   public boolean isSetProperties() {
      return this.properties != null;
   }

   public void setPropertiesIsSet(boolean value) {
      if (!value) {
         this.properties = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PROPERTIES:
            if (value == null) {
               this.unsetProperties();
            } else {
               this.setProperties((Map)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PROPERTIES:
            return this.getProperties();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PROPERTIES:
               return this.isSetProperties();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof EnvironmentContext ? this.equals((EnvironmentContext)that) : false;
   }

   public boolean equals(EnvironmentContext that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_properties = this.isSetProperties();
         boolean that_present_properties = that.isSetProperties();
         if (this_present_properties || that_present_properties) {
            if (!this_present_properties || !that_present_properties) {
               return false;
            }

            if (!this.properties.equals(that.properties)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetProperties() ? 131071 : 524287);
      if (this.isSetProperties()) {
         hashCode = hashCode * 8191 + this.properties.hashCode();
      }

      return hashCode;
   }

   public int compareTo(EnvironmentContext other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetProperties(), other.isSetProperties());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetProperties()) {
               lastComparison = TBaseHelper.compareTo(this.properties, other.properties);
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
      return EnvironmentContext._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("EnvironmentContext(");
      boolean first = true;
      sb.append("properties:");
      if (this.properties == null) {
         sb.append("null");
      } else {
         sb.append(this.properties);
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
      tmpMap.put(EnvironmentContext._Fields.PROPERTIES, new FieldMetaData("properties", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(EnvironmentContext.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PROPERTIES((short)1, "properties");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PROPERTIES;
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

   private static class EnvironmentContextStandardSchemeFactory implements SchemeFactory {
      private EnvironmentContextStandardSchemeFactory() {
      }

      public EnvironmentContextStandardScheme getScheme() {
         return new EnvironmentContextStandardScheme();
      }
   }

   private static class EnvironmentContextStandardScheme extends StandardScheme {
      private EnvironmentContextStandardScheme() {
      }

      public void read(TProtocol iprot, EnvironmentContext struct) throws TException {
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
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map312 = iprot.readMapBegin();
                  struct.properties = new HashMap(2 * _map312.size);

                  for(int _i315 = 0; _i315 < _map312.size; ++_i315) {
                     String _key313 = iprot.readString();
                     String _val314 = iprot.readString();
                     struct.properties.put(_key313, _val314);
                  }

                  iprot.readMapEnd();
                  struct.setPropertiesIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, EnvironmentContext struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(EnvironmentContext.STRUCT_DESC);
         if (struct.properties != null) {
            oprot.writeFieldBegin(EnvironmentContext.PROPERTIES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.properties.size()));

            for(Map.Entry _iter316 : struct.properties.entrySet()) {
               oprot.writeString((String)_iter316.getKey());
               oprot.writeString((String)_iter316.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class EnvironmentContextTupleSchemeFactory implements SchemeFactory {
      private EnvironmentContextTupleSchemeFactory() {
      }

      public EnvironmentContextTupleScheme getScheme() {
         return new EnvironmentContextTupleScheme();
      }
   }

   private static class EnvironmentContextTupleScheme extends TupleScheme {
      private EnvironmentContextTupleScheme() {
      }

      public void write(TProtocol prot, EnvironmentContext struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetProperties()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetProperties()) {
            oprot.writeI32(struct.properties.size());

            for(Map.Entry _iter317 : struct.properties.entrySet()) {
               oprot.writeString((String)_iter317.getKey());
               oprot.writeString((String)_iter317.getValue());
            }
         }

      }

      public void read(TProtocol prot, EnvironmentContext struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            TMap _map318 = iprot.readMapBegin((byte)11, (byte)11);
            struct.properties = new HashMap(2 * _map318.size);

            for(int _i321 = 0; _i321 < _map318.size; ++_i321) {
               String _key319 = iprot.readString();
               String _val320 = iprot.readString();
               struct.properties.put(_key319, _val320);
            }

            struct.setPropertiesIsSet(true);
         }

      }
   }
}
