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
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
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

public class Schema implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Schema");
   private static final TField FIELD_SCHEMAS_FIELD_DESC = new TField("fieldSchemas", (byte)15, (short)1);
   private static final TField PROPERTIES_FIELD_DESC = new TField("properties", (byte)13, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SchemaStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SchemaTupleSchemeFactory();
   @Nullable
   private List fieldSchemas;
   @Nullable
   private Map properties;
   public static final Map metaDataMap;

   public Schema() {
   }

   public Schema(List fieldSchemas, Map properties) {
      this();
      this.fieldSchemas = fieldSchemas;
      this.properties = properties;
   }

   public Schema(Schema other) {
      if (other.isSetFieldSchemas()) {
         List<FieldSchema> __this__fieldSchemas = new ArrayList(other.fieldSchemas.size());

         for(FieldSchema other_element : other.fieldSchemas) {
            __this__fieldSchemas.add(new FieldSchema(other_element));
         }

         this.fieldSchemas = __this__fieldSchemas;
      }

      if (other.isSetProperties()) {
         Map<String, String> __this__properties = new HashMap(other.properties);
         this.properties = __this__properties;
      }

   }

   public Schema deepCopy() {
      return new Schema(this);
   }

   public void clear() {
      this.fieldSchemas = null;
      this.properties = null;
   }

   public int getFieldSchemasSize() {
      return this.fieldSchemas == null ? 0 : this.fieldSchemas.size();
   }

   @Nullable
   public Iterator getFieldSchemasIterator() {
      return this.fieldSchemas == null ? null : this.fieldSchemas.iterator();
   }

   public void addToFieldSchemas(FieldSchema elem) {
      if (this.fieldSchemas == null) {
         this.fieldSchemas = new ArrayList();
      }

      this.fieldSchemas.add(elem);
   }

   @Nullable
   public List getFieldSchemas() {
      return this.fieldSchemas;
   }

   public void setFieldSchemas(@Nullable List fieldSchemas) {
      this.fieldSchemas = fieldSchemas;
   }

   public void unsetFieldSchemas() {
      this.fieldSchemas = null;
   }

   public boolean isSetFieldSchemas() {
      return this.fieldSchemas != null;
   }

   public void setFieldSchemasIsSet(boolean value) {
      if (!value) {
         this.fieldSchemas = null;
      }

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
         case FIELD_SCHEMAS:
            if (value == null) {
               this.unsetFieldSchemas();
            } else {
               this.setFieldSchemas((List)value);
            }
            break;
         case PROPERTIES:
            if (value == null) {
               this.unsetProperties();
            } else {
               this.setProperties((Map)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FIELD_SCHEMAS:
            return this.getFieldSchemas();
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
            case FIELD_SCHEMAS:
               return this.isSetFieldSchemas();
            case PROPERTIES:
               return this.isSetProperties();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Schema ? this.equals((Schema)that) : false;
   }

   public boolean equals(Schema that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_fieldSchemas = this.isSetFieldSchemas();
         boolean that_present_fieldSchemas = that.isSetFieldSchemas();
         if (this_present_fieldSchemas || that_present_fieldSchemas) {
            if (!this_present_fieldSchemas || !that_present_fieldSchemas) {
               return false;
            }

            if (!this.fieldSchemas.equals(that.fieldSchemas)) {
               return false;
            }
         }

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
      hashCode = hashCode * 8191 + (this.isSetFieldSchemas() ? 131071 : 524287);
      if (this.isSetFieldSchemas()) {
         hashCode = hashCode * 8191 + this.fieldSchemas.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetProperties() ? 131071 : 524287);
      if (this.isSetProperties()) {
         hashCode = hashCode * 8191 + this.properties.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Schema other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetFieldSchemas(), other.isSetFieldSchemas());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetFieldSchemas()) {
               lastComparison = TBaseHelper.compareTo(this.fieldSchemas, other.fieldSchemas);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

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
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Schema._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Schema(");
      boolean first = true;
      sb.append("fieldSchemas:");
      if (this.fieldSchemas == null) {
         sb.append("null");
      } else {
         sb.append(this.fieldSchemas);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

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
      tmpMap.put(Schema._Fields.FIELD_SCHEMAS, new FieldMetaData("fieldSchemas", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, FieldSchema.class))));
      tmpMap.put(Schema._Fields.PROPERTIES, new FieldMetaData("properties", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Schema.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FIELD_SCHEMAS((short)1, "fieldSchemas"),
      PROPERTIES((short)2, "properties");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FIELD_SCHEMAS;
            case 2:
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

   private static class SchemaStandardSchemeFactory implements SchemeFactory {
      private SchemaStandardSchemeFactory() {
      }

      public SchemaStandardScheme getScheme() {
         return new SchemaStandardScheme();
      }
   }

   private static class SchemaStandardScheme extends StandardScheme {
      private SchemaStandardScheme() {
      }

      public void read(TProtocol iprot, Schema struct) throws TException {
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

                  TList _list294 = iprot.readListBegin();
                  struct.fieldSchemas = new ArrayList(_list294.size);

                  for(int _i296 = 0; _i296 < _list294.size; ++_i296) {
                     FieldSchema _elem295 = new FieldSchema();
                     _elem295.read(iprot);
                     struct.fieldSchemas.add(_elem295);
                  }

                  iprot.readListEnd();
                  struct.setFieldSchemasIsSet(true);
                  break;
               case 2:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map297 = iprot.readMapBegin();
                  struct.properties = new HashMap(2 * _map297.size);

                  for(int _i300 = 0; _i300 < _map297.size; ++_i300) {
                     String _key298 = iprot.readString();
                     String _val299 = iprot.readString();
                     struct.properties.put(_key298, _val299);
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

      public void write(TProtocol oprot, Schema struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Schema.STRUCT_DESC);
         if (struct.fieldSchemas != null) {
            oprot.writeFieldBegin(Schema.FIELD_SCHEMAS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.fieldSchemas.size()));

            for(FieldSchema _iter301 : struct.fieldSchemas) {
               _iter301.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.properties != null) {
            oprot.writeFieldBegin(Schema.PROPERTIES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.properties.size()));

            for(Map.Entry _iter302 : struct.properties.entrySet()) {
               oprot.writeString((String)_iter302.getKey());
               oprot.writeString((String)_iter302.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SchemaTupleSchemeFactory implements SchemeFactory {
      private SchemaTupleSchemeFactory() {
      }

      public SchemaTupleScheme getScheme() {
         return new SchemaTupleScheme();
      }
   }

   private static class SchemaTupleScheme extends TupleScheme {
      private SchemaTupleScheme() {
      }

      public void write(TProtocol prot, Schema struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetFieldSchemas()) {
            optionals.set(0);
         }

         if (struct.isSetProperties()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetFieldSchemas()) {
            oprot.writeI32(struct.fieldSchemas.size());

            for(FieldSchema _iter303 : struct.fieldSchemas) {
               _iter303.write(oprot);
            }
         }

         if (struct.isSetProperties()) {
            oprot.writeI32(struct.properties.size());

            for(Map.Entry _iter304 : struct.properties.entrySet()) {
               oprot.writeString((String)_iter304.getKey());
               oprot.writeString((String)_iter304.getValue());
            }
         }

      }

      public void read(TProtocol prot, Schema struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            TList _list305 = iprot.readListBegin((byte)12);
            struct.fieldSchemas = new ArrayList(_list305.size);

            for(int _i307 = 0; _i307 < _list305.size; ++_i307) {
               FieldSchema _elem306 = new FieldSchema();
               _elem306.read(iprot);
               struct.fieldSchemas.add(_elem306);
            }

            struct.setFieldSchemasIsSet(true);
         }

         if (incoming.get(1)) {
            TMap _map308 = iprot.readMapBegin((byte)11, (byte)11);
            struct.properties = new HashMap(2 * _map308.size);

            for(int _i311 = 0; _i311 < _map308.size; ++_i311) {
               String _key309 = iprot.readString();
               String _val310 = iprot.readString();
               struct.properties.put(_key309, _val310);
            }

            struct.setPropertiesIsSet(true);
         }

      }
   }
}
