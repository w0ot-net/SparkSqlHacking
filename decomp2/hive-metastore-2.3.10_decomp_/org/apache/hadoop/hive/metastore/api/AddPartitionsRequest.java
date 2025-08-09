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

public class AddPartitionsRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("AddPartitionsRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField PARTS_FIELD_DESC = new TField("parts", (byte)15, (short)3);
   private static final TField IF_NOT_EXISTS_FIELD_DESC = new TField("ifNotExists", (byte)2, (short)4);
   private static final TField NEED_RESULT_FIELD_DESC = new TField("needResult", (byte)2, (short)5);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new AddPartitionsRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new AddPartitionsRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private List parts;
   private boolean ifNotExists;
   private boolean needResult;
   @Nullable
   private String catName;
   private static final int __IFNOTEXISTS_ISSET_ID = 0;
   private static final int __NEEDRESULT_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public AddPartitionsRequest() {
      this.__isset_bitfield = 0;
      this.needResult = true;
   }

   public AddPartitionsRequest(String dbName, String tblName, List parts, boolean ifNotExists) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
      this.parts = parts;
      this.ifNotExists = ifNotExists;
      this.setIfNotExistsIsSet(true);
   }

   public AddPartitionsRequest(AddPartitionsRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetParts()) {
         List<Partition> __this__parts = new ArrayList(other.parts.size());

         for(Partition other_element : other.parts) {
            __this__parts.add(new Partition(other_element));
         }

         this.parts = __this__parts;
      }

      this.ifNotExists = other.ifNotExists;
      this.needResult = other.needResult;
      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public AddPartitionsRequest deepCopy() {
      return new AddPartitionsRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.parts = null;
      this.setIfNotExistsIsSet(false);
      this.ifNotExists = false;
      this.needResult = true;
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

   public int getPartsSize() {
      return this.parts == null ? 0 : this.parts.size();
   }

   @Nullable
   public Iterator getPartsIterator() {
      return this.parts == null ? null : this.parts.iterator();
   }

   public void addToParts(Partition elem) {
      if (this.parts == null) {
         this.parts = new ArrayList();
      }

      this.parts.add(elem);
   }

   @Nullable
   public List getParts() {
      return this.parts;
   }

   public void setParts(@Nullable List parts) {
      this.parts = parts;
   }

   public void unsetParts() {
      this.parts = null;
   }

   public boolean isSetParts() {
      return this.parts != null;
   }

   public void setPartsIsSet(boolean value) {
      if (!value) {
         this.parts = null;
      }

   }

   public boolean isIfNotExists() {
      return this.ifNotExists;
   }

   public void setIfNotExists(boolean ifNotExists) {
      this.ifNotExists = ifNotExists;
      this.setIfNotExistsIsSet(true);
   }

   public void unsetIfNotExists() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIfNotExists() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIfNotExistsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public boolean isNeedResult() {
      return this.needResult;
   }

   public void setNeedResult(boolean needResult) {
      this.needResult = needResult;
      this.setNeedResultIsSet(true);
   }

   public void unsetNeedResult() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetNeedResult() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setNeedResultIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
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
         case PARTS:
            if (value == null) {
               this.unsetParts();
            } else {
               this.setParts((List)value);
            }
            break;
         case IF_NOT_EXISTS:
            if (value == null) {
               this.unsetIfNotExists();
            } else {
               this.setIfNotExists((Boolean)value);
            }
            break;
         case NEED_RESULT:
            if (value == null) {
               this.unsetNeedResult();
            } else {
               this.setNeedResult((Boolean)value);
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
         case PARTS:
            return this.getParts();
         case IF_NOT_EXISTS:
            return this.isIfNotExists();
         case NEED_RESULT:
            return this.isNeedResult();
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
            case PARTS:
               return this.isSetParts();
            case IF_NOT_EXISTS:
               return this.isSetIfNotExists();
            case NEED_RESULT:
               return this.isSetNeedResult();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof AddPartitionsRequest ? this.equals((AddPartitionsRequest)that) : false;
   }

   public boolean equals(AddPartitionsRequest that) {
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

         boolean this_present_parts = this.isSetParts();
         boolean that_present_parts = that.isSetParts();
         if (this_present_parts || that_present_parts) {
            if (!this_present_parts || !that_present_parts) {
               return false;
            }

            if (!this.parts.equals(that.parts)) {
               return false;
            }
         }

         boolean this_present_ifNotExists = true;
         boolean that_present_ifNotExists = true;
         if (this_present_ifNotExists || that_present_ifNotExists) {
            if (!this_present_ifNotExists || !that_present_ifNotExists) {
               return false;
            }

            if (this.ifNotExists != that.ifNotExists) {
               return false;
            }
         }

         boolean this_present_needResult = this.isSetNeedResult();
         boolean that_present_needResult = that.isSetNeedResult();
         if (this_present_needResult || that_present_needResult) {
            if (!this_present_needResult || !that_present_needResult) {
               return false;
            }

            if (this.needResult != that.needResult) {
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

      hashCode = hashCode * 8191 + (this.isSetParts() ? 131071 : 524287);
      if (this.isSetParts()) {
         hashCode = hashCode * 8191 + this.parts.hashCode();
      }

      hashCode = hashCode * 8191 + (this.ifNotExists ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.isSetNeedResult() ? 131071 : 524287);
      if (this.isSetNeedResult()) {
         hashCode = hashCode * 8191 + (this.needResult ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(AddPartitionsRequest other) {
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

               lastComparison = Boolean.compare(this.isSetParts(), other.isSetParts());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetParts()) {
                     lastComparison = TBaseHelper.compareTo(this.parts, other.parts);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetIfNotExists(), other.isSetIfNotExists());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetIfNotExists()) {
                        lastComparison = TBaseHelper.compareTo(this.ifNotExists, other.ifNotExists);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetNeedResult(), other.isSetNeedResult());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetNeedResult()) {
                           lastComparison = TBaseHelper.compareTo(this.needResult, other.needResult);
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
      return AddPartitionsRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AddPartitionsRequest(");
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

      sb.append("parts:");
      if (this.parts == null) {
         sb.append("null");
      } else {
         sb.append(this.parts);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("ifNotExists:");
      sb.append(this.ifNotExists);
      first = false;
      if (this.isSetNeedResult()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("needResult:");
         sb.append(this.needResult);
         first = false;
      }

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
      } else if (!this.isSetParts()) {
         throw new TProtocolException("Required field 'parts' is unset! Struct:" + this.toString());
      } else if (!this.isSetIfNotExists()) {
         throw new TProtocolException("Required field 'ifNotExists' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{AddPartitionsRequest._Fields.NEED_RESULT, AddPartitionsRequest._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(AddPartitionsRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(AddPartitionsRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(AddPartitionsRequest._Fields.PARTS, new FieldMetaData("parts", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, Partition.class))));
      tmpMap.put(AddPartitionsRequest._Fields.IF_NOT_EXISTS, new FieldMetaData("ifNotExists", (byte)1, new FieldValueMetaData((byte)2)));
      tmpMap.put(AddPartitionsRequest._Fields.NEED_RESULT, new FieldMetaData("needResult", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(AddPartitionsRequest._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(AddPartitionsRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      PARTS((short)3, "parts"),
      IF_NOT_EXISTS((short)4, "ifNotExists"),
      NEED_RESULT((short)5, "needResult"),
      CAT_NAME((short)6, "catName");

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
               return PARTS;
            case 4:
               return IF_NOT_EXISTS;
            case 5:
               return NEED_RESULT;
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

   private static class AddPartitionsRequestStandardSchemeFactory implements SchemeFactory {
      private AddPartitionsRequestStandardSchemeFactory() {
      }

      public AddPartitionsRequestStandardScheme getScheme() {
         return new AddPartitionsRequestStandardScheme();
      }
   }

   private static class AddPartitionsRequestStandardScheme extends StandardScheme {
      private AddPartitionsRequestStandardScheme() {
      }

      public void read(TProtocol iprot, AddPartitionsRequest struct) throws TException {
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

                  TList _list420 = iprot.readListBegin();
                  struct.parts = new ArrayList(_list420.size);

                  for(int _i422 = 0; _i422 < _list420.size; ++_i422) {
                     Partition _elem421 = new Partition();
                     _elem421.read(iprot);
                     struct.parts.add(_elem421);
                  }

                  iprot.readListEnd();
                  struct.setPartsIsSet(true);
                  break;
               case 4:
                  if (schemeField.type == 2) {
                     struct.ifNotExists = iprot.readBool();
                     struct.setIfNotExistsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 2) {
                     struct.needResult = iprot.readBool();
                     struct.setNeedResultIsSet(true);
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

      public void write(TProtocol oprot, AddPartitionsRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(AddPartitionsRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(AddPartitionsRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(AddPartitionsRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.parts != null) {
            oprot.writeFieldBegin(AddPartitionsRequest.PARTS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.parts.size()));

            for(Partition _iter423 : struct.parts) {
               _iter423.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(AddPartitionsRequest.IF_NOT_EXISTS_FIELD_DESC);
         oprot.writeBool(struct.ifNotExists);
         oprot.writeFieldEnd();
         if (struct.isSetNeedResult()) {
            oprot.writeFieldBegin(AddPartitionsRequest.NEED_RESULT_FIELD_DESC);
            oprot.writeBool(struct.needResult);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(AddPartitionsRequest.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class AddPartitionsRequestTupleSchemeFactory implements SchemeFactory {
      private AddPartitionsRequestTupleSchemeFactory() {
      }

      public AddPartitionsRequestTupleScheme getScheme() {
         return new AddPartitionsRequestTupleScheme();
      }
   }

   private static class AddPartitionsRequestTupleScheme extends TupleScheme {
      private AddPartitionsRequestTupleScheme() {
      }

      public void write(TProtocol prot, AddPartitionsRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         oprot.writeI32(struct.parts.size());

         for(Partition _iter424 : struct.parts) {
            _iter424.write(oprot);
         }

         oprot.writeBool(struct.ifNotExists);
         BitSet optionals = new BitSet();
         if (struct.isSetNeedResult()) {
            optionals.set(0);
         }

         if (struct.isSetCatName()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetNeedResult()) {
            oprot.writeBool(struct.needResult);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, AddPartitionsRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         TList _list425 = iprot.readListBegin((byte)12);
         struct.parts = new ArrayList(_list425.size);

         for(int _i427 = 0; _i427 < _list425.size; ++_i427) {
            Partition _elem426 = new Partition();
            _elem426.read(iprot);
            struct.parts.add(_elem426);
         }

         struct.setPartsIsSet(true);
         struct.ifNotExists = iprot.readBool();
         struct.setIfNotExistsIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.needResult = iprot.readBool();
            struct.setNeedResultIsSet(true);
         }

         if (incoming.get(1)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
