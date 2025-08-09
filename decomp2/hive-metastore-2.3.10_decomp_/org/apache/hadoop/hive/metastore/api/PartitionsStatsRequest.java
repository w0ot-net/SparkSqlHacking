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

public class PartitionsStatsRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionsStatsRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField COL_NAMES_FIELD_DESC = new TField("colNames", (byte)15, (short)3);
   private static final TField PART_NAMES_FIELD_DESC = new TField("partNames", (byte)15, (short)4);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionsStatsRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionsStatsRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private List colNames;
   @Nullable
   private List partNames;
   @Nullable
   private String catName;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PartitionsStatsRequest() {
   }

   public PartitionsStatsRequest(String dbName, String tblName, List colNames, List partNames) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
      this.colNames = colNames;
      this.partNames = partNames;
   }

   public PartitionsStatsRequest(PartitionsStatsRequest other) {
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetColNames()) {
         List<String> __this__colNames = new ArrayList(other.colNames);
         this.colNames = __this__colNames;
      }

      if (other.isSetPartNames()) {
         List<String> __this__partNames = new ArrayList(other.partNames);
         this.partNames = __this__partNames;
      }

      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public PartitionsStatsRequest deepCopy() {
      return new PartitionsStatsRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.colNames = null;
      this.partNames = null;
      this.catName = null;
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
   public String getTblName() {
      return this.tblName;
   }

   public void setTblName(@Nullable String tblName) {
      this.tblName = tblName;
   }

   public void unsetTblName() {
      this.tblName = null;
   }

   public boolean isSetTblName() {
      return this.tblName != null;
   }

   public void setTblNameIsSet(boolean value) {
      if (!value) {
         this.tblName = null;
      }

   }

   public int getColNamesSize() {
      return this.colNames == null ? 0 : this.colNames.size();
   }

   @Nullable
   public Iterator getColNamesIterator() {
      return this.colNames == null ? null : this.colNames.iterator();
   }

   public void addToColNames(String elem) {
      if (this.colNames == null) {
         this.colNames = new ArrayList();
      }

      this.colNames.add(elem);
   }

   @Nullable
   public List getColNames() {
      return this.colNames;
   }

   public void setColNames(@Nullable List colNames) {
      this.colNames = colNames;
   }

   public void unsetColNames() {
      this.colNames = null;
   }

   public boolean isSetColNames() {
      return this.colNames != null;
   }

   public void setColNamesIsSet(boolean value) {
      if (!value) {
         this.colNames = null;
      }

   }

   public int getPartNamesSize() {
      return this.partNames == null ? 0 : this.partNames.size();
   }

   @Nullable
   public Iterator getPartNamesIterator() {
      return this.partNames == null ? null : this.partNames.iterator();
   }

   public void addToPartNames(String elem) {
      if (this.partNames == null) {
         this.partNames = new ArrayList();
      }

      this.partNames.add(elem);
   }

   @Nullable
   public List getPartNames() {
      return this.partNames;
   }

   public void setPartNames(@Nullable List partNames) {
      this.partNames = partNames;
   }

   public void unsetPartNames() {
      this.partNames = null;
   }

   public boolean isSetPartNames() {
      return this.partNames != null;
   }

   public void setPartNamesIsSet(boolean value) {
      if (!value) {
         this.partNames = null;
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
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TBL_NAME:
            if (value == null) {
               this.unsetTblName();
            } else {
               this.setTblName((String)value);
            }
            break;
         case COL_NAMES:
            if (value == null) {
               this.unsetColNames();
            } else {
               this.setColNames((List)value);
            }
            break;
         case PART_NAMES:
            if (value == null) {
               this.unsetPartNames();
            } else {
               this.setPartNames((List)value);
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
         case DB_NAME:
            return this.getDbName();
         case TBL_NAME:
            return this.getTblName();
         case COL_NAMES:
            return this.getColNames();
         case PART_NAMES:
            return this.getPartNames();
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
            case DB_NAME:
               return this.isSetDbName();
            case TBL_NAME:
               return this.isSetTblName();
            case COL_NAMES:
               return this.isSetColNames();
            case PART_NAMES:
               return this.isSetPartNames();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionsStatsRequest ? this.equals((PartitionsStatsRequest)that) : false;
   }

   public boolean equals(PartitionsStatsRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_tblName = this.isSetTblName();
         boolean that_present_tblName = that.isSetTblName();
         if (this_present_tblName || that_present_tblName) {
            if (!this_present_tblName || !that_present_tblName) {
               return false;
            }

            if (!this.tblName.equals(that.tblName)) {
               return false;
            }
         }

         boolean this_present_colNames = this.isSetColNames();
         boolean that_present_colNames = that.isSetColNames();
         if (this_present_colNames || that_present_colNames) {
            if (!this_present_colNames || !that_present_colNames) {
               return false;
            }

            if (!this.colNames.equals(that.colNames)) {
               return false;
            }
         }

         boolean this_present_partNames = this.isSetPartNames();
         boolean that_present_partNames = that.isSetPartNames();
         if (this_present_partNames || that_present_partNames) {
            if (!this_present_partNames || !that_present_partNames) {
               return false;
            }

            if (!this.partNames.equals(that.partNames)) {
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
      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTblName() ? 131071 : 524287);
      if (this.isSetTblName()) {
         hashCode = hashCode * 8191 + this.tblName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColNames() ? 131071 : 524287);
      if (this.isSetColNames()) {
         hashCode = hashCode * 8191 + this.colNames.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartNames() ? 131071 : 524287);
      if (this.isSetPartNames()) {
         hashCode = hashCode * 8191 + this.partNames.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionsStatsRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

            lastComparison = Boolean.compare(this.isSetTblName(), other.isSetTblName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTblName()) {
                  lastComparison = TBaseHelper.compareTo(this.tblName, other.tblName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetColNames(), other.isSetColNames());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetColNames()) {
                     lastComparison = TBaseHelper.compareTo(this.colNames, other.colNames);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetPartNames(), other.isSetPartNames());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetPartNames()) {
                        lastComparison = TBaseHelper.compareTo(this.partNames, other.partNames);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PartitionsStatsRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionsStatsRequest(");
      boolean first = true;
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

      sb.append("tblName:");
      if (this.tblName == null) {
         sb.append("null");
      } else {
         sb.append(this.tblName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("colNames:");
      if (this.colNames == null) {
         sb.append("null");
      } else {
         sb.append(this.colNames);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("partNames:");
      if (this.partNames == null) {
         sb.append("null");
      } else {
         sb.append(this.partNames);
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
      if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTblName()) {
         throw new TProtocolException("Required field 'tblName' is unset! Struct:" + this.toString());
      } else if (!this.isSetColNames()) {
         throw new TProtocolException("Required field 'colNames' is unset! Struct:" + this.toString());
      } else if (!this.isSetPartNames()) {
         throw new TProtocolException("Required field 'partNames' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{PartitionsStatsRequest._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PartitionsStatsRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionsStatsRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionsStatsRequest._Fields.COL_NAMES, new FieldMetaData("colNames", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(PartitionsStatsRequest._Fields.PART_NAMES, new FieldMetaData("partNames", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(PartitionsStatsRequest._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionsStatsRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      COL_NAMES((short)3, "colNames"),
      PART_NAMES((short)4, "partNames"),
      CAT_NAME((short)5, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TBL_NAME;
            case 3:
               return COL_NAMES;
            case 4:
               return PART_NAMES;
            case 5:
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

   private static class PartitionsStatsRequestStandardSchemeFactory implements SchemeFactory {
      private PartitionsStatsRequestStandardSchemeFactory() {
      }

      public PartitionsStatsRequestStandardScheme getScheme() {
         return new PartitionsStatsRequestStandardScheme();
      }
   }

   private static class PartitionsStatsRequestStandardScheme extends StandardScheme {
      private PartitionsStatsRequestStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionsStatsRequest struct) throws TException {
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
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tblName = iprot.readString();
                     struct.setTblNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list396 = iprot.readListBegin();
                  struct.colNames = new ArrayList(_list396.size);

                  for(int _i398 = 0; _i398 < _list396.size; ++_i398) {
                     String _elem397 = iprot.readString();
                     struct.colNames.add(_elem397);
                  }

                  iprot.readListEnd();
                  struct.setColNamesIsSet(true);
                  break;
               case 4:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list399 = iprot.readListBegin();
                  struct.partNames = new ArrayList(_list399.size);

                  for(int _i401 = 0; _i401 < _list399.size; ++_i401) {
                     String _elem400 = iprot.readString();
                     struct.partNames.add(_elem400);
                  }

                  iprot.readListEnd();
                  struct.setPartNamesIsSet(true);
                  break;
               case 5:
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

      public void write(TProtocol oprot, PartitionsStatsRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionsStatsRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(PartitionsStatsRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(PartitionsStatsRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.colNames != null) {
            oprot.writeFieldBegin(PartitionsStatsRequest.COL_NAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.colNames.size()));

            for(String _iter402 : struct.colNames) {
               oprot.writeString(_iter402);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.partNames != null) {
            oprot.writeFieldBegin(PartitionsStatsRequest.PART_NAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.partNames.size()));

            for(String _iter403 : struct.partNames) {
               oprot.writeString(_iter403);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(PartitionsStatsRequest.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionsStatsRequestTupleSchemeFactory implements SchemeFactory {
      private PartitionsStatsRequestTupleSchemeFactory() {
      }

      public PartitionsStatsRequestTupleScheme getScheme() {
         return new PartitionsStatsRequestTupleScheme();
      }
   }

   private static class PartitionsStatsRequestTupleScheme extends TupleScheme {
      private PartitionsStatsRequestTupleScheme() {
      }

      public void write(TProtocol prot, PartitionsStatsRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         oprot.writeI32(struct.colNames.size());

         for(String _iter404 : struct.colNames) {
            oprot.writeString(_iter404);
         }

         oprot.writeI32(struct.partNames.size());

         for(String _iter405 : struct.partNames) {
            oprot.writeString(_iter405);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetCatName()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, PartitionsStatsRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         TList _list406 = iprot.readListBegin((byte)11);
         struct.colNames = new ArrayList(_list406.size);

         for(int _i408 = 0; _i408 < _list406.size; ++_i408) {
            String _elem407 = iprot.readString();
            struct.colNames.add(_elem407);
         }

         struct.setColNamesIsSet(true);
         _list406 = iprot.readListBegin((byte)11);
         struct.partNames = new ArrayList(_list406.size);

         for(int _i411 = 0; _i411 < _list406.size; ++_i411) {
            String _elem410 = iprot.readString();
            struct.partNames.add(_elem410);
         }

         struct.setPartNamesIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
