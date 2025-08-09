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
import org.apache.thrift.EncodingUtils;
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

public class PartitionWithoutSD implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionWithoutSD");
   private static final TField VALUES_FIELD_DESC = new TField("values", (byte)15, (short)1);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)2);
   private static final TField LAST_ACCESS_TIME_FIELD_DESC = new TField("lastAccessTime", (byte)8, (short)3);
   private static final TField RELATIVE_PATH_FIELD_DESC = new TField("relativePath", (byte)11, (short)4);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)5);
   private static final TField PRIVILEGES_FIELD_DESC = new TField("privileges", (byte)12, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionWithoutSDStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionWithoutSDTupleSchemeFactory();
   @Nullable
   private List values;
   private int createTime;
   private int lastAccessTime;
   @Nullable
   private String relativePath;
   @Nullable
   private Map parameters;
   @Nullable
   private PrincipalPrivilegeSet privileges;
   private static final int __CREATETIME_ISSET_ID = 0;
   private static final int __LASTACCESSTIME_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PartitionWithoutSD() {
      this.__isset_bitfield = 0;
   }

   public PartitionWithoutSD(List values, int createTime, int lastAccessTime, String relativePath, Map parameters) {
      this();
      this.values = values;
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.lastAccessTime = lastAccessTime;
      this.setLastAccessTimeIsSet(true);
      this.relativePath = relativePath;
      this.parameters = parameters;
   }

   public PartitionWithoutSD(PartitionWithoutSD other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetValues()) {
         List<String> __this__values = new ArrayList(other.values);
         this.values = __this__values;
      }

      this.createTime = other.createTime;
      this.lastAccessTime = other.lastAccessTime;
      if (other.isSetRelativePath()) {
         this.relativePath = other.relativePath;
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

      if (other.isSetPrivileges()) {
         this.privileges = new PrincipalPrivilegeSet(other.privileges);
      }

   }

   public PartitionWithoutSD deepCopy() {
      return new PartitionWithoutSD(this);
   }

   public void clear() {
      this.values = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.setLastAccessTimeIsSet(false);
      this.lastAccessTime = 0;
      this.relativePath = null;
      this.parameters = null;
      this.privileges = null;
   }

   public int getValuesSize() {
      return this.values == null ? 0 : this.values.size();
   }

   @Nullable
   public Iterator getValuesIterator() {
      return this.values == null ? null : this.values.iterator();
   }

   public void addToValues(String elem) {
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

   public int getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(int createTime) {
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
   }

   public void unsetCreateTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetCreateTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setCreateTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getLastAccessTime() {
      return this.lastAccessTime;
   }

   public void setLastAccessTime(int lastAccessTime) {
      this.lastAccessTime = lastAccessTime;
      this.setLastAccessTimeIsSet(true);
   }

   public void unsetLastAccessTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetLastAccessTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setLastAccessTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   @Nullable
   public String getRelativePath() {
      return this.relativePath;
   }

   public void setRelativePath(@Nullable String relativePath) {
      this.relativePath = relativePath;
   }

   public void unsetRelativePath() {
      this.relativePath = null;
   }

   public boolean isSetRelativePath() {
      return this.relativePath != null;
   }

   public void setRelativePathIsSet(boolean value) {
      if (!value) {
         this.relativePath = null;
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
      this.parameters = parameters;
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

   @Nullable
   public PrincipalPrivilegeSet getPrivileges() {
      return this.privileges;
   }

   public void setPrivileges(@Nullable PrincipalPrivilegeSet privileges) {
      this.privileges = privileges;
   }

   public void unsetPrivileges() {
      this.privileges = null;
   }

   public boolean isSetPrivileges() {
      return this.privileges != null;
   }

   public void setPrivilegesIsSet(boolean value) {
      if (!value) {
         this.privileges = null;
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
            break;
         case CREATE_TIME:
            if (value == null) {
               this.unsetCreateTime();
            } else {
               this.setCreateTime((Integer)value);
            }
            break;
         case LAST_ACCESS_TIME:
            if (value == null) {
               this.unsetLastAccessTime();
            } else {
               this.setLastAccessTime((Integer)value);
            }
            break;
         case RELATIVE_PATH:
            if (value == null) {
               this.unsetRelativePath();
            } else {
               this.setRelativePath((String)value);
            }
            break;
         case PARAMETERS:
            if (value == null) {
               this.unsetParameters();
            } else {
               this.setParameters((Map)value);
            }
            break;
         case PRIVILEGES:
            if (value == null) {
               this.unsetPrivileges();
            } else {
               this.setPrivileges((PrincipalPrivilegeSet)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case VALUES:
            return this.getValues();
         case CREATE_TIME:
            return this.getCreateTime();
         case LAST_ACCESS_TIME:
            return this.getLastAccessTime();
         case RELATIVE_PATH:
            return this.getRelativePath();
         case PARAMETERS:
            return this.getParameters();
         case PRIVILEGES:
            return this.getPrivileges();
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
            case CREATE_TIME:
               return this.isSetCreateTime();
            case LAST_ACCESS_TIME:
               return this.isSetLastAccessTime();
            case RELATIVE_PATH:
               return this.isSetRelativePath();
            case PARAMETERS:
               return this.isSetParameters();
            case PRIVILEGES:
               return this.isSetPrivileges();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionWithoutSD ? this.equals((PartitionWithoutSD)that) : false;
   }

   public boolean equals(PartitionWithoutSD that) {
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

         boolean this_present_createTime = true;
         boolean that_present_createTime = true;
         if (this_present_createTime || that_present_createTime) {
            if (!this_present_createTime || !that_present_createTime) {
               return false;
            }

            if (this.createTime != that.createTime) {
               return false;
            }
         }

         boolean this_present_lastAccessTime = true;
         boolean that_present_lastAccessTime = true;
         if (this_present_lastAccessTime || that_present_lastAccessTime) {
            if (!this_present_lastAccessTime || !that_present_lastAccessTime) {
               return false;
            }

            if (this.lastAccessTime != that.lastAccessTime) {
               return false;
            }
         }

         boolean this_present_relativePath = this.isSetRelativePath();
         boolean that_present_relativePath = that.isSetRelativePath();
         if (this_present_relativePath || that_present_relativePath) {
            if (!this_present_relativePath || !that_present_relativePath) {
               return false;
            }

            if (!this.relativePath.equals(that.relativePath)) {
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

         boolean this_present_privileges = this.isSetPrivileges();
         boolean that_present_privileges = that.isSetPrivileges();
         if (this_present_privileges || that_present_privileges) {
            if (!this_present_privileges || !that_present_privileges) {
               return false;
            }

            if (!this.privileges.equals(that.privileges)) {
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

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + this.lastAccessTime;
      hashCode = hashCode * 8191 + (this.isSetRelativePath() ? 131071 : 524287);
      if (this.isSetRelativePath()) {
         hashCode = hashCode * 8191 + this.relativePath.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrivileges() ? 131071 : 524287);
      if (this.isSetPrivileges()) {
         hashCode = hashCode * 8191 + this.privileges.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionWithoutSD other) {
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

            lastComparison = Boolean.compare(this.isSetCreateTime(), other.isSetCreateTime());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetCreateTime()) {
                  lastComparison = TBaseHelper.compareTo(this.createTime, other.createTime);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetLastAccessTime(), other.isSetLastAccessTime());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetLastAccessTime()) {
                     lastComparison = TBaseHelper.compareTo(this.lastAccessTime, other.lastAccessTime);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetRelativePath(), other.isSetRelativePath());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetRelativePath()) {
                        lastComparison = TBaseHelper.compareTo(this.relativePath, other.relativePath);
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

                        lastComparison = Boolean.compare(this.isSetPrivileges(), other.isSetPrivileges());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetPrivileges()) {
                              lastComparison = TBaseHelper.compareTo(this.privileges, other.privileges);
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
      return PartitionWithoutSD._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionWithoutSD(");
      boolean first = true;
      sb.append("values:");
      if (this.values == null) {
         sb.append("null");
      } else {
         sb.append(this.values);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("createTime:");
      sb.append(this.createTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("lastAccessTime:");
      sb.append(this.lastAccessTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("relativePath:");
      if (this.relativePath == null) {
         sb.append("null");
      } else {
         sb.append(this.relativePath);
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
      if (this.isSetPrivileges()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("privileges:");
         if (this.privileges == null) {
            sb.append("null");
         } else {
            sb.append(this.privileges);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.privileges != null) {
         this.privileges.validate();
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
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{PartitionWithoutSD._Fields.PRIVILEGES};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PartitionWithoutSD._Fields.VALUES, new FieldMetaData("values", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(PartitionWithoutSD._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(PartitionWithoutSD._Fields.LAST_ACCESS_TIME, new FieldMetaData("lastAccessTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(PartitionWithoutSD._Fields.RELATIVE_PATH, new FieldMetaData("relativePath", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionWithoutSD._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(PartitionWithoutSD._Fields.PRIVILEGES, new FieldMetaData("privileges", (byte)2, new StructMetaData((byte)12, PrincipalPrivilegeSet.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionWithoutSD.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VALUES((short)1, "values"),
      CREATE_TIME((short)2, "createTime"),
      LAST_ACCESS_TIME((short)3, "lastAccessTime"),
      RELATIVE_PATH((short)4, "relativePath"),
      PARAMETERS((short)5, "parameters"),
      PRIVILEGES((short)6, "privileges");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VALUES;
            case 2:
               return CREATE_TIME;
            case 3:
               return LAST_ACCESS_TIME;
            case 4:
               return RELATIVE_PATH;
            case 5:
               return PARAMETERS;
            case 6:
               return PRIVILEGES;
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

   private static class PartitionWithoutSDStandardSchemeFactory implements SchemeFactory {
      private PartitionWithoutSDStandardSchemeFactory() {
      }

      public PartitionWithoutSDStandardScheme getScheme() {
         return new PartitionWithoutSDStandardScheme();
      }
   }

   private static class PartitionWithoutSDStandardScheme extends StandardScheme {
      private PartitionWithoutSDStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionWithoutSD struct) throws TException {
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

                  TList _list226 = iprot.readListBegin();
                  struct.values = new ArrayList(_list226.size);

                  for(int _i228 = 0; _i228 < _list226.size; ++_i228) {
                     String _elem227 = iprot.readString();
                     struct.values.add(_elem227);
                  }

                  iprot.readListEnd();
                  struct.setValuesIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.createTime = iprot.readI32();
                     struct.setCreateTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.lastAccessTime = iprot.readI32();
                     struct.setLastAccessTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.relativePath = iprot.readString();
                     struct.setRelativePathIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map229 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map229.size);

                  for(int _i232 = 0; _i232 < _map229.size; ++_i232) {
                     String _key230 = iprot.readString();
                     String _val231 = iprot.readString();
                     struct.parameters.put(_key230, _val231);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               case 6:
                  if (schemeField.type == 12) {
                     struct.privileges = new PrincipalPrivilegeSet();
                     struct.privileges.read(iprot);
                     struct.setPrivilegesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PartitionWithoutSD struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionWithoutSD.STRUCT_DESC);
         if (struct.values != null) {
            oprot.writeFieldBegin(PartitionWithoutSD.VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.values.size()));

            for(String _iter233 : struct.values) {
               oprot.writeString(_iter233);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(PartitionWithoutSD.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(PartitionWithoutSD.LAST_ACCESS_TIME_FIELD_DESC);
         oprot.writeI32(struct.lastAccessTime);
         oprot.writeFieldEnd();
         if (struct.relativePath != null) {
            oprot.writeFieldBegin(PartitionWithoutSD.RELATIVE_PATH_FIELD_DESC);
            oprot.writeString(struct.relativePath);
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(PartitionWithoutSD.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter234 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter234.getKey());
               oprot.writeString((String)_iter234.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.privileges != null && struct.isSetPrivileges()) {
            oprot.writeFieldBegin(PartitionWithoutSD.PRIVILEGES_FIELD_DESC);
            struct.privileges.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionWithoutSDTupleSchemeFactory implements SchemeFactory {
      private PartitionWithoutSDTupleSchemeFactory() {
      }

      public PartitionWithoutSDTupleScheme getScheme() {
         return new PartitionWithoutSDTupleScheme();
      }
   }

   private static class PartitionWithoutSDTupleScheme extends TupleScheme {
      private PartitionWithoutSDTupleScheme() {
      }

      public void write(TProtocol prot, PartitionWithoutSD struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetValues()) {
            optionals.set(0);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(1);
         }

         if (struct.isSetLastAccessTime()) {
            optionals.set(2);
         }

         if (struct.isSetRelativePath()) {
            optionals.set(3);
         }

         if (struct.isSetParameters()) {
            optionals.set(4);
         }

         if (struct.isSetPrivileges()) {
            optionals.set(5);
         }

         oprot.writeBitSet(optionals, 6);
         if (struct.isSetValues()) {
            oprot.writeI32(struct.values.size());

            for(String _iter235 : struct.values) {
               oprot.writeString(_iter235);
            }
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetLastAccessTime()) {
            oprot.writeI32(struct.lastAccessTime);
         }

         if (struct.isSetRelativePath()) {
            oprot.writeString(struct.relativePath);
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter236 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter236.getKey());
               oprot.writeString((String)_iter236.getValue());
            }
         }

         if (struct.isSetPrivileges()) {
            struct.privileges.write(oprot);
         }

      }

      public void read(TProtocol prot, PartitionWithoutSD struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(6);
         if (incoming.get(0)) {
            TList _list237 = iprot.readListBegin((byte)11);
            struct.values = new ArrayList(_list237.size);

            for(int _i239 = 0; _i239 < _list237.size; ++_i239) {
               String _elem238 = iprot.readString();
               struct.values.add(_elem238);
            }

            struct.setValuesIsSet(true);
         }

         if (incoming.get(1)) {
            struct.createTime = iprot.readI32();
            struct.setCreateTimeIsSet(true);
         }

         if (incoming.get(2)) {
            struct.lastAccessTime = iprot.readI32();
            struct.setLastAccessTimeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.relativePath = iprot.readString();
            struct.setRelativePathIsSet(true);
         }

         if (incoming.get(4)) {
            TMap _map240 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map240.size);

            for(int _i243 = 0; _i243 < _map240.size; ++_i243) {
               String _key241 = iprot.readString();
               String _val242 = iprot.readString();
               struct.parameters.put(_key241, _val242);
            }

            struct.setParametersIsSet(true);
         }

         if (incoming.get(5)) {
            struct.privileges = new PrincipalPrivilegeSet();
            struct.privileges.read(iprot);
            struct.setPrivilegesIsSet(true);
         }

      }
   }
}
