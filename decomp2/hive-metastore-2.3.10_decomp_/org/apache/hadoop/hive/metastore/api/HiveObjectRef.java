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
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
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

public class HiveObjectRef implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("HiveObjectRef");
   private static final TField OBJECT_TYPE_FIELD_DESC = new TField("objectType", (byte)8, (short)1);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)2);
   private static final TField OBJECT_NAME_FIELD_DESC = new TField("objectName", (byte)11, (short)3);
   private static final TField PART_VALUES_FIELD_DESC = new TField("partValues", (byte)15, (short)4);
   private static final TField COLUMN_NAME_FIELD_DESC = new TField("columnName", (byte)11, (short)5);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new HiveObjectRefStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new HiveObjectRefTupleSchemeFactory();
   @Nullable
   private HiveObjectType objectType;
   @Nullable
   private String dbName;
   @Nullable
   private String objectName;
   @Nullable
   private List partValues;
   @Nullable
   private String columnName;
   @Nullable
   private String catName;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public HiveObjectRef() {
   }

   public HiveObjectRef(HiveObjectType objectType, String dbName, String objectName, List partValues, String columnName) {
      this();
      this.objectType = objectType;
      this.dbName = dbName;
      this.objectName = objectName;
      this.partValues = partValues;
      this.columnName = columnName;
   }

   public HiveObjectRef(HiveObjectRef other) {
      if (other.isSetObjectType()) {
         this.objectType = other.objectType;
      }

      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetObjectName()) {
         this.objectName = other.objectName;
      }

      if (other.isSetPartValues()) {
         List<String> __this__partValues = new ArrayList(other.partValues);
         this.partValues = __this__partValues;
      }

      if (other.isSetColumnName()) {
         this.columnName = other.columnName;
      }

      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public HiveObjectRef deepCopy() {
      return new HiveObjectRef(this);
   }

   public void clear() {
      this.objectType = null;
      this.dbName = null;
      this.objectName = null;
      this.partValues = null;
      this.columnName = null;
      this.catName = null;
   }

   @Nullable
   public HiveObjectType getObjectType() {
      return this.objectType;
   }

   public void setObjectType(@Nullable HiveObjectType objectType) {
      this.objectType = objectType;
   }

   public void unsetObjectType() {
      this.objectType = null;
   }

   public boolean isSetObjectType() {
      return this.objectType != null;
   }

   public void setObjectTypeIsSet(boolean value) {
      if (!value) {
         this.objectType = null;
      }

   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = dbName;
   }

   public void unsetDbName() {
      this.dbName = null;
   }

   public boolean isSetDbName() {
      return this.dbName != null;
   }

   public void setDbNameIsSet(boolean value) {
      if (!value) {
         this.dbName = null;
      }

   }

   @Nullable
   public String getObjectName() {
      return this.objectName;
   }

   public void setObjectName(@Nullable String objectName) {
      this.objectName = objectName;
   }

   public void unsetObjectName() {
      this.objectName = null;
   }

   public boolean isSetObjectName() {
      return this.objectName != null;
   }

   public void setObjectNameIsSet(boolean value) {
      if (!value) {
         this.objectName = null;
      }

   }

   public int getPartValuesSize() {
      return this.partValues == null ? 0 : this.partValues.size();
   }

   @Nullable
   public Iterator getPartValuesIterator() {
      return this.partValues == null ? null : this.partValues.iterator();
   }

   public void addToPartValues(String elem) {
      if (this.partValues == null) {
         this.partValues = new ArrayList();
      }

      this.partValues.add(elem);
   }

   @Nullable
   public List getPartValues() {
      return this.partValues;
   }

   public void setPartValues(@Nullable List partValues) {
      this.partValues = partValues;
   }

   public void unsetPartValues() {
      this.partValues = null;
   }

   public boolean isSetPartValues() {
      return this.partValues != null;
   }

   public void setPartValuesIsSet(boolean value) {
      if (!value) {
         this.partValues = null;
      }

   }

   @Nullable
   public String getColumnName() {
      return this.columnName;
   }

   public void setColumnName(@Nullable String columnName) {
      this.columnName = columnName;
   }

   public void unsetColumnName() {
      this.columnName = null;
   }

   public boolean isSetColumnName() {
      return this.columnName != null;
   }

   public void setColumnNameIsSet(boolean value) {
      if (!value) {
         this.columnName = null;
      }

   }

   @Nullable
   public String getCatName() {
      return this.catName;
   }

   public void setCatName(@Nullable String catName) {
      this.catName = catName;
   }

   public void unsetCatName() {
      this.catName = null;
   }

   public boolean isSetCatName() {
      return this.catName != null;
   }

   public void setCatNameIsSet(boolean value) {
      if (!value) {
         this.catName = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case OBJECT_TYPE:
            if (value == null) {
               this.unsetObjectType();
            } else {
               this.setObjectType((HiveObjectType)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case OBJECT_NAME:
            if (value == null) {
               this.unsetObjectName();
            } else {
               this.setObjectName((String)value);
            }
            break;
         case PART_VALUES:
            if (value == null) {
               this.unsetPartValues();
            } else {
               this.setPartValues((List)value);
            }
            break;
         case COLUMN_NAME:
            if (value == null) {
               this.unsetColumnName();
            } else {
               this.setColumnName((String)value);
            }
            break;
         case CAT_NAME:
            if (value == null) {
               this.unsetCatName();
            } else {
               this.setCatName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case OBJECT_TYPE:
            return this.getObjectType();
         case DB_NAME:
            return this.getDbName();
         case OBJECT_NAME:
            return this.getObjectName();
         case PART_VALUES:
            return this.getPartValues();
         case COLUMN_NAME:
            return this.getColumnName();
         case CAT_NAME:
            return this.getCatName();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case OBJECT_TYPE:
               return this.isSetObjectType();
            case DB_NAME:
               return this.isSetDbName();
            case OBJECT_NAME:
               return this.isSetObjectName();
            case PART_VALUES:
               return this.isSetPartValues();
            case COLUMN_NAME:
               return this.isSetColumnName();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof HiveObjectRef ? this.equals((HiveObjectRef)that) : false;
   }

   public boolean equals(HiveObjectRef that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_objectType = this.isSetObjectType();
         boolean that_present_objectType = that.isSetObjectType();
         if (this_present_objectType || that_present_objectType) {
            if (!this_present_objectType || !that_present_objectType) {
               return false;
            }

            if (!this.objectType.equals(that.objectType)) {
               return false;
            }
         }

         boolean this_present_dbName = this.isSetDbName();
         boolean that_present_dbName = that.isSetDbName();
         if (this_present_dbName || that_present_dbName) {
            if (!this_present_dbName || !that_present_dbName) {
               return false;
            }

            if (!this.dbName.equals(that.dbName)) {
               return false;
            }
         }

         boolean this_present_objectName = this.isSetObjectName();
         boolean that_present_objectName = that.isSetObjectName();
         if (this_present_objectName || that_present_objectName) {
            if (!this_present_objectName || !that_present_objectName) {
               return false;
            }

            if (!this.objectName.equals(that.objectName)) {
               return false;
            }
         }

         boolean this_present_partValues = this.isSetPartValues();
         boolean that_present_partValues = that.isSetPartValues();
         if (this_present_partValues || that_present_partValues) {
            if (!this_present_partValues || !that_present_partValues) {
               return false;
            }

            if (!this.partValues.equals(that.partValues)) {
               return false;
            }
         }

         boolean this_present_columnName = this.isSetColumnName();
         boolean that_present_columnName = that.isSetColumnName();
         if (this_present_columnName || that_present_columnName) {
            if (!this_present_columnName || !that_present_columnName) {
               return false;
            }

            if (!this.columnName.equals(that.columnName)) {
               return false;
            }
         }

         boolean this_present_catName = this.isSetCatName();
         boolean that_present_catName = that.isSetCatName();
         if (this_present_catName || that_present_catName) {
            if (!this_present_catName || !that_present_catName) {
               return false;
            }

            if (!this.catName.equals(that.catName)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetObjectType() ? 131071 : 524287);
      if (this.isSetObjectType()) {
         hashCode = hashCode * 8191 + this.objectType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetObjectName() ? 131071 : 524287);
      if (this.isSetObjectName()) {
         hashCode = hashCode * 8191 + this.objectName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartValues() ? 131071 : 524287);
      if (this.isSetPartValues()) {
         hashCode = hashCode * 8191 + this.partValues.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColumnName() ? 131071 : 524287);
      if (this.isSetColumnName()) {
         hashCode = hashCode * 8191 + this.columnName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(HiveObjectRef other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetObjectType(), other.isSetObjectType());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetObjectType()) {
               lastComparison = TBaseHelper.compareTo(this.objectType, other.objectType);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetDbName(), other.isSetDbName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetDbName()) {
                  lastComparison = TBaseHelper.compareTo(this.dbName, other.dbName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetObjectName(), other.isSetObjectName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetObjectName()) {
                     lastComparison = TBaseHelper.compareTo(this.objectName, other.objectName);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetPartValues(), other.isSetPartValues());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetPartValues()) {
                        lastComparison = TBaseHelper.compareTo(this.partValues, other.partValues);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetColumnName(), other.isSetColumnName());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetColumnName()) {
                           lastComparison = TBaseHelper.compareTo(this.columnName, other.columnName);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetCatName(), other.isSetCatName());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetCatName()) {
                              lastComparison = TBaseHelper.compareTo(this.catName, other.catName);
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
      return HiveObjectRef._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("HiveObjectRef(");
      boolean first = true;
      sb.append("objectType:");
      if (this.objectType == null) {
         sb.append("null");
      } else {
         sb.append(this.objectType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("dbName:");
      if (this.dbName == null) {
         sb.append("null");
      } else {
         sb.append(this.dbName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("objectName:");
      if (this.objectName == null) {
         sb.append("null");
      } else {
         sb.append(this.objectName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("partValues:");
      if (this.partValues == null) {
         sb.append("null");
      } else {
         sb.append(this.partValues);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("columnName:");
      if (this.columnName == null) {
         sb.append("null");
      } else {
         sb.append(this.columnName);
      }

      first = false;
      if (this.isSetCatName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("catName:");
         if (this.catName == null) {
            sb.append("null");
         } else {
            sb.append(this.catName);
         }

         first = false;
      }

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
      optionals = new _Fields[]{HiveObjectRef._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(HiveObjectRef._Fields.OBJECT_TYPE, new FieldMetaData("objectType", (byte)3, new EnumMetaData((byte)16, HiveObjectType.class)));
      tmpMap.put(HiveObjectRef._Fields.DB_NAME, new FieldMetaData("dbName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(HiveObjectRef._Fields.OBJECT_NAME, new FieldMetaData("objectName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(HiveObjectRef._Fields.PART_VALUES, new FieldMetaData("partValues", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(HiveObjectRef._Fields.COLUMN_NAME, new FieldMetaData("columnName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(HiveObjectRef._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(HiveObjectRef.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      OBJECT_TYPE((short)1, "objectType"),
      DB_NAME((short)2, "dbName"),
      OBJECT_NAME((short)3, "objectName"),
      PART_VALUES((short)4, "partValues"),
      COLUMN_NAME((short)5, "columnName"),
      CAT_NAME((short)6, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return OBJECT_TYPE;
            case 2:
               return DB_NAME;
            case 3:
               return OBJECT_NAME;
            case 4:
               return PART_VALUES;
            case 5:
               return COLUMN_NAME;
            case 6:
               return CAT_NAME;
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

   private static class HiveObjectRefStandardSchemeFactory implements SchemeFactory {
      private HiveObjectRefStandardSchemeFactory() {
      }

      public HiveObjectRefStandardScheme getScheme() {
         return new HiveObjectRefStandardScheme();
      }
   }

   private static class HiveObjectRefStandardScheme extends StandardScheme {
      private HiveObjectRefStandardScheme() {
      }

      public void read(TProtocol iprot, HiveObjectRef struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.objectType = HiveObjectType.findByValue(iprot.readI32());
                     struct.setObjectTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.objectName = iprot.readString();
                     struct.setObjectNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list8 = iprot.readListBegin();
                  struct.partValues = new ArrayList(_list8.size);

                  for(int _i10 = 0; _i10 < _list8.size; ++_i10) {
                     String _elem9 = iprot.readString();
                     struct.partValues.add(_elem9);
                  }

                  iprot.readListEnd();
                  struct.setPartValuesIsSet(true);
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.columnName = iprot.readString();
                     struct.setColumnNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.catName = iprot.readString();
                     struct.setCatNameIsSet(true);
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

      public void write(TProtocol oprot, HiveObjectRef struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(HiveObjectRef.STRUCT_DESC);
         if (struct.objectType != null) {
            oprot.writeFieldBegin(HiveObjectRef.OBJECT_TYPE_FIELD_DESC);
            oprot.writeI32(struct.objectType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null) {
            oprot.writeFieldBegin(HiveObjectRef.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.objectName != null) {
            oprot.writeFieldBegin(HiveObjectRef.OBJECT_NAME_FIELD_DESC);
            oprot.writeString(struct.objectName);
            oprot.writeFieldEnd();
         }

         if (struct.partValues != null) {
            oprot.writeFieldBegin(HiveObjectRef.PART_VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.partValues.size()));

            for(String _iter11 : struct.partValues) {
               oprot.writeString(_iter11);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.columnName != null) {
            oprot.writeFieldBegin(HiveObjectRef.COLUMN_NAME_FIELD_DESC);
            oprot.writeString(struct.columnName);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(HiveObjectRef.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class HiveObjectRefTupleSchemeFactory implements SchemeFactory {
      private HiveObjectRefTupleSchemeFactory() {
      }

      public HiveObjectRefTupleScheme getScheme() {
         return new HiveObjectRefTupleScheme();
      }
   }

   private static class HiveObjectRefTupleScheme extends TupleScheme {
      private HiveObjectRefTupleScheme() {
      }

      public void write(TProtocol prot, HiveObjectRef struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetObjectType()) {
            optionals.set(0);
         }

         if (struct.isSetDbName()) {
            optionals.set(1);
         }

         if (struct.isSetObjectName()) {
            optionals.set(2);
         }

         if (struct.isSetPartValues()) {
            optionals.set(3);
         }

         if (struct.isSetColumnName()) {
            optionals.set(4);
         }

         if (struct.isSetCatName()) {
            optionals.set(5);
         }

         oprot.writeBitSet(optionals, 6);
         if (struct.isSetObjectType()) {
            oprot.writeI32(struct.objectType.getValue());
         }

         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetObjectName()) {
            oprot.writeString(struct.objectName);
         }

         if (struct.isSetPartValues()) {
            oprot.writeI32(struct.partValues.size());

            for(String _iter12 : struct.partValues) {
               oprot.writeString(_iter12);
            }
         }

         if (struct.isSetColumnName()) {
            oprot.writeString(struct.columnName);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, HiveObjectRef struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(6);
         if (incoming.get(0)) {
            struct.objectType = HiveObjectType.findByValue(iprot.readI32());
            struct.setObjectTypeIsSet(true);
         }

         if (incoming.get(1)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.objectName = iprot.readString();
            struct.setObjectNameIsSet(true);
         }

         if (incoming.get(3)) {
            TList _list13 = iprot.readListBegin((byte)11);
            struct.partValues = new ArrayList(_list13.size);

            for(int _i15 = 0; _i15 < _list13.size; ++_i15) {
               String _elem14 = iprot.readString();
               struct.partValues.add(_elem14);
            }

            struct.setPartValuesIsSet(true);
         }

         if (incoming.get(4)) {
            struct.columnName = iprot.readString();
            struct.setColumnNameIsSet(true);
         }

         if (incoming.get(5)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
