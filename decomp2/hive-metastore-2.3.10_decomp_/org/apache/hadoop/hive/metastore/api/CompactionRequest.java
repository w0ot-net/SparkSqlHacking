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
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
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

public class CompactionRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CompactionRequest");
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)1);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)2);
   private static final TField PARTITIONNAME_FIELD_DESC = new TField("partitionname", (byte)11, (short)3);
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)4);
   private static final TField RUNAS_FIELD_DESC = new TField("runas", (byte)11, (short)5);
   private static final TField PROPERTIES_FIELD_DESC = new TField("properties", (byte)13, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CompactionRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CompactionRequestTupleSchemeFactory();
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private String partitionname;
   @Nullable
   private CompactionType type;
   @Nullable
   private String runas;
   @Nullable
   private Map properties;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public CompactionRequest() {
   }

   public CompactionRequest(String dbname, String tablename, CompactionType type) {
      this();
      this.dbname = dbname;
      this.tablename = tablename;
      this.type = type;
   }

   public CompactionRequest(CompactionRequest other) {
      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetPartitionname()) {
         this.partitionname = other.partitionname;
      }

      if (other.isSetType()) {
         this.type = other.type;
      }

      if (other.isSetRunas()) {
         this.runas = other.runas;
      }

      if (other.isSetProperties()) {
         Map<String, String> __this__properties = new HashMap(other.properties);
         this.properties = __this__properties;
      }

   }

   public CompactionRequest deepCopy() {
      return new CompactionRequest(this);
   }

   public void clear() {
      this.dbname = null;
      this.tablename = null;
      this.partitionname = null;
      this.type = null;
      this.runas = null;
      this.properties = null;
   }

   @Nullable
   public String getDbname() {
      return this.dbname;
   }

   public void setDbname(@Nullable String dbname) {
      this.dbname = dbname;
   }

   public void unsetDbname() {
      this.dbname = null;
   }

   public boolean isSetDbname() {
      return this.dbname != null;
   }

   public void setDbnameIsSet(boolean value) {
      if (!value) {
         this.dbname = null;
      }

   }

   @Nullable
   public String getTablename() {
      return this.tablename;
   }

   public void setTablename(@Nullable String tablename) {
      this.tablename = tablename;
   }

   public void unsetTablename() {
      this.tablename = null;
   }

   public boolean isSetTablename() {
      return this.tablename != null;
   }

   public void setTablenameIsSet(boolean value) {
      if (!value) {
         this.tablename = null;
      }

   }

   @Nullable
   public String getPartitionname() {
      return this.partitionname;
   }

   public void setPartitionname(@Nullable String partitionname) {
      this.partitionname = partitionname;
   }

   public void unsetPartitionname() {
      this.partitionname = null;
   }

   public boolean isSetPartitionname() {
      return this.partitionname != null;
   }

   public void setPartitionnameIsSet(boolean value) {
      if (!value) {
         this.partitionname = null;
      }

   }

   @Nullable
   public CompactionType getType() {
      return this.type;
   }

   public void setType(@Nullable CompactionType type) {
      this.type = type;
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   @Nullable
   public String getRunas() {
      return this.runas;
   }

   public void setRunas(@Nullable String runas) {
      this.runas = runas;
   }

   public void unsetRunas() {
      this.runas = null;
   }

   public boolean isSetRunas() {
      return this.runas != null;
   }

   public void setRunasIsSet(boolean value) {
      if (!value) {
         this.runas = null;
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
         case DBNAME:
            if (value == null) {
               this.unsetDbname();
            } else {
               this.setDbname((String)value);
            }
            break;
         case TABLENAME:
            if (value == null) {
               this.unsetTablename();
            } else {
               this.setTablename((String)value);
            }
            break;
         case PARTITIONNAME:
            if (value == null) {
               this.unsetPartitionname();
            } else {
               this.setPartitionname((String)value);
            }
            break;
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((CompactionType)value);
            }
            break;
         case RUNAS:
            if (value == null) {
               this.unsetRunas();
            } else {
               this.setRunas((String)value);
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
         case DBNAME:
            return this.getDbname();
         case TABLENAME:
            return this.getTablename();
         case PARTITIONNAME:
            return this.getPartitionname();
         case TYPE:
            return this.getType();
         case RUNAS:
            return this.getRunas();
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
            case DBNAME:
               return this.isSetDbname();
            case TABLENAME:
               return this.isSetTablename();
            case PARTITIONNAME:
               return this.isSetPartitionname();
            case TYPE:
               return this.isSetType();
            case RUNAS:
               return this.isSetRunas();
            case PROPERTIES:
               return this.isSetProperties();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CompactionRequest ? this.equals((CompactionRequest)that) : false;
   }

   public boolean equals(CompactionRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_dbname = this.isSetDbname();
         boolean that_present_dbname = that.isSetDbname();
         if (this_present_dbname || that_present_dbname) {
            if (!this_present_dbname || !that_present_dbname) {
               return false;
            }

            if (!this.dbname.equals(that.dbname)) {
               return false;
            }
         }

         boolean this_present_tablename = this.isSetTablename();
         boolean that_present_tablename = that.isSetTablename();
         if (this_present_tablename || that_present_tablename) {
            if (!this_present_tablename || !that_present_tablename) {
               return false;
            }

            if (!this.tablename.equals(that.tablename)) {
               return false;
            }
         }

         boolean this_present_partitionname = this.isSetPartitionname();
         boolean that_present_partitionname = that.isSetPartitionname();
         if (this_present_partitionname || that_present_partitionname) {
            if (!this_present_partitionname || !that_present_partitionname) {
               return false;
            }

            if (!this.partitionname.equals(that.partitionname)) {
               return false;
            }
         }

         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         boolean this_present_runas = this.isSetRunas();
         boolean that_present_runas = that.isSetRunas();
         if (this_present_runas || that_present_runas) {
            if (!this_present_runas || !that_present_runas) {
               return false;
            }

            if (!this.runas.equals(that.runas)) {
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
      hashCode = hashCode * 8191 + (this.isSetDbname() ? 131071 : 524287);
      if (this.isSetDbname()) {
         hashCode = hashCode * 8191 + this.dbname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTablename() ? 131071 : 524287);
      if (this.isSetTablename()) {
         hashCode = hashCode * 8191 + this.tablename.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionname() ? 131071 : 524287);
      if (this.isSetPartitionname()) {
         hashCode = hashCode * 8191 + this.partitionname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetRunas() ? 131071 : 524287);
      if (this.isSetRunas()) {
         hashCode = hashCode * 8191 + this.runas.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetProperties() ? 131071 : 524287);
      if (this.isSetProperties()) {
         hashCode = hashCode * 8191 + this.properties.hashCode();
      }

      return hashCode;
   }

   public int compareTo(CompactionRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetDbname(), other.isSetDbname());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetDbname()) {
               lastComparison = TBaseHelper.compareTo(this.dbname, other.dbname);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTablename(), other.isSetTablename());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTablename()) {
                  lastComparison = TBaseHelper.compareTo(this.tablename, other.tablename);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPartitionname(), other.isSetPartitionname());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPartitionname()) {
                     lastComparison = TBaseHelper.compareTo(this.partitionname, other.partitionname);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetType()) {
                        lastComparison = TBaseHelper.compareTo(this.type, other.type);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetRunas(), other.isSetRunas());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetRunas()) {
                           lastComparison = TBaseHelper.compareTo(this.runas, other.runas);
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
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return CompactionRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CompactionRequest(");
      boolean first = true;
      sb.append("dbname:");
      if (this.dbname == null) {
         sb.append("null");
      } else {
         sb.append(this.dbname);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tablename:");
      if (this.tablename == null) {
         sb.append("null");
      } else {
         sb.append(this.tablename);
      }

      first = false;
      if (this.isSetPartitionname()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partitionname:");
         if (this.partitionname == null) {
            sb.append("null");
         } else {
            sb.append(this.partitionname);
         }

         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("type:");
      if (this.type == null) {
         sb.append("null");
      } else {
         sb.append(this.type);
      }

      first = false;
      if (this.isSetRunas()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("runas:");
         if (this.runas == null) {
            sb.append("null");
         } else {
            sb.append(this.runas);
         }

         first = false;
      }

      if (this.isSetProperties()) {
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
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbname()) {
         throw new TProtocolException("Required field 'dbname' is unset! Struct:" + this.toString());
      } else if (!this.isSetTablename()) {
         throw new TProtocolException("Required field 'tablename' is unset! Struct:" + this.toString());
      } else if (!this.isSetType()) {
         throw new TProtocolException("Required field 'type' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{CompactionRequest._Fields.PARTITIONNAME, CompactionRequest._Fields.RUNAS, CompactionRequest._Fields.PROPERTIES};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(CompactionRequest._Fields.DBNAME, new FieldMetaData("dbname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(CompactionRequest._Fields.TABLENAME, new FieldMetaData("tablename", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(CompactionRequest._Fields.PARTITIONNAME, new FieldMetaData("partitionname", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(CompactionRequest._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)16, CompactionType.class)));
      tmpMap.put(CompactionRequest._Fields.RUNAS, new FieldMetaData("runas", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(CompactionRequest._Fields.PROPERTIES, new FieldMetaData("properties", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CompactionRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DBNAME((short)1, "dbname"),
      TABLENAME((short)2, "tablename"),
      PARTITIONNAME((short)3, "partitionname"),
      TYPE((short)4, "type"),
      RUNAS((short)5, "runas"),
      PROPERTIES((short)6, "properties");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DBNAME;
            case 2:
               return TABLENAME;
            case 3:
               return PARTITIONNAME;
            case 4:
               return TYPE;
            case 5:
               return RUNAS;
            case 6:
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

   private static class CompactionRequestStandardSchemeFactory implements SchemeFactory {
      private CompactionRequestStandardSchemeFactory() {
      }

      public CompactionRequestStandardScheme getScheme() {
         return new CompactionRequestStandardScheme();
      }
   }

   private static class CompactionRequestStandardScheme extends StandardScheme {
      private CompactionRequestStandardScheme() {
      }

      public void read(TProtocol iprot, CompactionRequest struct) throws TException {
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
                     struct.dbname = iprot.readString();
                     struct.setDbnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tablename = iprot.readString();
                     struct.setTablenameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.partitionname = iprot.readString();
                     struct.setPartitionnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.type = CompactionType.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.runas = iprot.readString();
                     struct.setRunasIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map556 = iprot.readMapBegin();
                  struct.properties = new HashMap(2 * _map556.size);

                  for(int _i559 = 0; _i559 < _map556.size; ++_i559) {
                     String _key557 = iprot.readString();
                     String _val558 = iprot.readString();
                     struct.properties.put(_key557, _val558);
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

      public void write(TProtocol oprot, CompactionRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CompactionRequest.STRUCT_DESC);
         if (struct.dbname != null) {
            oprot.writeFieldBegin(CompactionRequest.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null) {
            oprot.writeFieldBegin(CompactionRequest.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.partitionname != null && struct.isSetPartitionname()) {
            oprot.writeFieldBegin(CompactionRequest.PARTITIONNAME_FIELD_DESC);
            oprot.writeString(struct.partitionname);
            oprot.writeFieldEnd();
         }

         if (struct.type != null) {
            oprot.writeFieldBegin(CompactionRequest.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.runas != null && struct.isSetRunas()) {
            oprot.writeFieldBegin(CompactionRequest.RUNAS_FIELD_DESC);
            oprot.writeString(struct.runas);
            oprot.writeFieldEnd();
         }

         if (struct.properties != null && struct.isSetProperties()) {
            oprot.writeFieldBegin(CompactionRequest.PROPERTIES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.properties.size()));

            for(Map.Entry _iter560 : struct.properties.entrySet()) {
               oprot.writeString((String)_iter560.getKey());
               oprot.writeString((String)_iter560.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CompactionRequestTupleSchemeFactory implements SchemeFactory {
      private CompactionRequestTupleSchemeFactory() {
      }

      public CompactionRequestTupleScheme getScheme() {
         return new CompactionRequestTupleScheme();
      }
   }

   private static class CompactionRequestTupleScheme extends TupleScheme {
      private CompactionRequestTupleScheme() {
      }

      public void write(TProtocol prot, CompactionRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbname);
         oprot.writeString(struct.tablename);
         oprot.writeI32(struct.type.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetPartitionname()) {
            optionals.set(0);
         }

         if (struct.isSetRunas()) {
            optionals.set(1);
         }

         if (struct.isSetProperties()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetPartitionname()) {
            oprot.writeString(struct.partitionname);
         }

         if (struct.isSetRunas()) {
            oprot.writeString(struct.runas);
         }

         if (struct.isSetProperties()) {
            oprot.writeI32(struct.properties.size());

            for(Map.Entry _iter561 : struct.properties.entrySet()) {
               oprot.writeString((String)_iter561.getKey());
               oprot.writeString((String)_iter561.getValue());
            }
         }

      }

      public void read(TProtocol prot, CompactionRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbname = iprot.readString();
         struct.setDbnameIsSet(true);
         struct.tablename = iprot.readString();
         struct.setTablenameIsSet(true);
         struct.type = CompactionType.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.partitionname = iprot.readString();
            struct.setPartitionnameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.runas = iprot.readString();
            struct.setRunasIsSet(true);
         }

         if (incoming.get(2)) {
            TMap _map562 = iprot.readMapBegin((byte)11, (byte)11);
            struct.properties = new HashMap(2 * _map562.size);

            for(int _i565 = 0; _i565 < _map562.size; ++_i565) {
               String _key563 = iprot.readString();
               String _val564 = iprot.readString();
               struct.properties.put(_key563, _val564);
            }

            struct.setPropertiesIsSet(true);
         }

      }
   }
}
