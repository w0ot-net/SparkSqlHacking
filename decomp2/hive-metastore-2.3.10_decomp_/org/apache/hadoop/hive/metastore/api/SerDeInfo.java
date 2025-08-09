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
import org.apache.hive.common.util.HiveStringUtils;
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

public class SerDeInfo implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SerDeInfo");
   private static final TField NAME_FIELD_DESC = new TField("name", (byte)11, (short)1);
   private static final TField SERIALIZATION_LIB_FIELD_DESC = new TField("serializationLib", (byte)11, (short)2);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SerDeInfoStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SerDeInfoTupleSchemeFactory();
   @Nullable
   private String name;
   @Nullable
   private String serializationLib;
   @Nullable
   private Map parameters;
   public static final Map metaDataMap;

   public SerDeInfo() {
   }

   public SerDeInfo(String name, String serializationLib, Map parameters) {
      this();
      this.name = HiveStringUtils.intern(name);
      this.serializationLib = HiveStringUtils.intern(serializationLib);
      this.parameters = HiveStringUtils.intern(parameters);
   }

   public SerDeInfo(SerDeInfo other) {
      if (other.isSetName()) {
         this.name = HiveStringUtils.intern(other.name);
      }

      if (other.isSetSerializationLib()) {
         this.serializationLib = HiveStringUtils.intern(other.serializationLib);
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

   }

   public SerDeInfo deepCopy() {
      return new SerDeInfo(this);
   }

   public void clear() {
      this.name = null;
      this.serializationLib = null;
      this.parameters = null;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(@Nullable String name) {
      this.name = HiveStringUtils.intern(name);
   }

   public void unsetName() {
      this.name = null;
   }

   public boolean isSetName() {
      return this.name != null;
   }

   public void setNameIsSet(boolean value) {
      if (!value) {
         this.name = null;
      }

   }

   @Nullable
   public String getSerializationLib() {
      return this.serializationLib;
   }

   public void setSerializationLib(@Nullable String serializationLib) {
      this.serializationLib = HiveStringUtils.intern(serializationLib);
   }

   public void unsetSerializationLib() {
      this.serializationLib = null;
   }

   public boolean isSetSerializationLib() {
      return this.serializationLib != null;
   }

   public void setSerializationLibIsSet(boolean value) {
      if (!value) {
         this.serializationLib = null;
      }

   }

   public int getParametersSize() {
      return this.parameters == null ? 0 : this.parameters.size();
   }

   public void putToParameters(String key, String val) {
      if (this.parameters == null) {
         this.parameters = new HashMap();
      }

      this.parameters.put(key, val);
   }

   @Nullable
   public Map getParameters() {
      return this.parameters;
   }

   public void setParameters(@Nullable Map parameters) {
      this.parameters = HiveStringUtils.intern(parameters);
   }

   public void unsetParameters() {
      this.parameters = null;
   }

   public boolean isSetParameters() {
      return this.parameters != null;
   }

   public void setParametersIsSet(boolean value) {
      if (!value) {
         this.parameters = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NAME:
            if (value == null) {
               this.unsetName();
            } else {
               this.setName((String)value);
            }
            break;
         case SERIALIZATION_LIB:
            if (value == null) {
               this.unsetSerializationLib();
            } else {
               this.setSerializationLib((String)value);
            }
            break;
         case PARAMETERS:
            if (value == null) {
               this.unsetParameters();
            } else {
               this.setParameters((Map)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NAME:
            return this.getName();
         case SERIALIZATION_LIB:
            return this.getSerializationLib();
         case PARAMETERS:
            return this.getParameters();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NAME:
               return this.isSetName();
            case SERIALIZATION_LIB:
               return this.isSetSerializationLib();
            case PARAMETERS:
               return this.isSetParameters();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SerDeInfo ? this.equals((SerDeInfo)that) : false;
   }

   public boolean equals(SerDeInfo that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_name = this.isSetName();
         boolean that_present_name = that.isSetName();
         if (this_present_name || that_present_name) {
            if (!this_present_name || !that_present_name) {
               return false;
            }

            if (!this.name.equals(that.name)) {
               return false;
            }
         }

         boolean this_present_serializationLib = this.isSetSerializationLib();
         boolean that_present_serializationLib = that.isSetSerializationLib();
         if (this_present_serializationLib || that_present_serializationLib) {
            if (!this_present_serializationLib || !that_present_serializationLib) {
               return false;
            }

            if (!this.serializationLib.equals(that.serializationLib)) {
               return false;
            }
         }

         boolean this_present_parameters = this.isSetParameters();
         boolean that_present_parameters = that.isSetParameters();
         if (this_present_parameters || that_present_parameters) {
            if (!this_present_parameters || !that_present_parameters) {
               return false;
            }

            if (!this.parameters.equals(that.parameters)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetName() ? 131071 : 524287);
      if (this.isSetName()) {
         hashCode = hashCode * 8191 + this.name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSerializationLib() ? 131071 : 524287);
      if (this.isSetSerializationLib()) {
         hashCode = hashCode * 8191 + this.serializationLib.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      return hashCode;
   }

   public int compareTo(SerDeInfo other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetName(), other.isSetName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetName()) {
               lastComparison = TBaseHelper.compareTo(this.name, other.name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetSerializationLib(), other.isSetSerializationLib());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSerializationLib()) {
                  lastComparison = TBaseHelper.compareTo(this.serializationLib, other.serializationLib);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetParameters(), other.isSetParameters());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetParameters()) {
                     lastComparison = TBaseHelper.compareTo(this.parameters, other.parameters);
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
      return SerDeInfo._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SerDeInfo(");
      boolean first = true;
      sb.append("name:");
      if (this.name == null) {
         sb.append("null");
      } else {
         sb.append(this.name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("serializationLib:");
      if (this.serializationLib == null) {
         sb.append("null");
      } else {
         sb.append(this.serializationLib);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("parameters:");
      if (this.parameters == null) {
         sb.append("null");
      } else {
         sb.append(this.parameters);
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
      tmpMap.put(SerDeInfo._Fields.NAME, new FieldMetaData("name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SerDeInfo._Fields.SERIALIZATION_LIB, new FieldMetaData("serializationLib", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SerDeInfo._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SerDeInfo.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NAME((short)1, "name"),
      SERIALIZATION_LIB((short)2, "serializationLib"),
      PARAMETERS((short)3, "parameters");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NAME;
            case 2:
               return SERIALIZATION_LIB;
            case 3:
               return PARAMETERS;
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

   private static class SerDeInfoStandardSchemeFactory implements SchemeFactory {
      private SerDeInfoStandardSchemeFactory() {
      }

      public SerDeInfoStandardScheme getScheme() {
         return new SerDeInfoStandardScheme();
      }
   }

   private static class SerDeInfoStandardScheme extends StandardScheme {
      private SerDeInfoStandardScheme() {
      }

      public void read(TProtocol iprot, SerDeInfo struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.name = iprot.readString();
                     struct.setNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.serializationLib = iprot.readString();
                     struct.setSerializationLibIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map104 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map104.size);

                  for(int _i107 = 0; _i107 < _map104.size; ++_i107) {
                     String _key105 = iprot.readString();
                     String _val106 = iprot.readString();
                     struct.parameters.put(_key105, _val106);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, SerDeInfo struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SerDeInfo.STRUCT_DESC);
         if (struct.name != null) {
            oprot.writeFieldBegin(SerDeInfo.NAME_FIELD_DESC);
            oprot.writeString(struct.name);
            oprot.writeFieldEnd();
         }

         if (struct.serializationLib != null) {
            oprot.writeFieldBegin(SerDeInfo.SERIALIZATION_LIB_FIELD_DESC);
            oprot.writeString(struct.serializationLib);
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(SerDeInfo.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter108 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter108.getKey());
               oprot.writeString((String)_iter108.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SerDeInfoTupleSchemeFactory implements SchemeFactory {
      private SerDeInfoTupleSchemeFactory() {
      }

      public SerDeInfoTupleScheme getScheme() {
         return new SerDeInfoTupleScheme();
      }
   }

   private static class SerDeInfoTupleScheme extends TupleScheme {
      private SerDeInfoTupleScheme() {
      }

      public void write(TProtocol prot, SerDeInfo struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetName()) {
            optionals.set(0);
         }

         if (struct.isSetSerializationLib()) {
            optionals.set(1);
         }

         if (struct.isSetParameters()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetName()) {
            oprot.writeString(struct.name);
         }

         if (struct.isSetSerializationLib()) {
            oprot.writeString(struct.serializationLib);
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter109 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter109.getKey());
               oprot.writeString((String)_iter109.getValue());
            }
         }

      }

      public void read(TProtocol prot, SerDeInfo struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.name = iprot.readString();
            struct.setNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.serializationLib = iprot.readString();
            struct.setSerializationLibIsSet(true);
         }

         if (incoming.get(2)) {
            TMap _map110 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map110.size);

            for(int _i113 = 0; _i113 < _map110.size; ++_i113) {
               String _key111 = iprot.readString();
               String _val112 = iprot.readString();
               struct.parameters.put(_key111, _val112);
            }

            struct.setParametersIsSet(true);
         }

      }
   }
}
