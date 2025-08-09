package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class PartitionsByExprRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionsByExprRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tblName", (byte)11, (short)2);
   private static final TField EXPR_FIELD_DESC = new TField("expr", (byte)11, (short)3);
   private static final TField DEFAULT_PARTITION_NAME_FIELD_DESC = new TField("defaultPartitionName", (byte)11, (short)4);
   private static final TField MAX_PARTS_FIELD_DESC = new TField("maxParts", (byte)6, (short)5);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionsByExprRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionsByExprRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tblName;
   @Nullable
   private ByteBuffer expr;
   @Nullable
   private String defaultPartitionName;
   private short maxParts;
   @Nullable
   private String catName;
   private static final int __MAXPARTS_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PartitionsByExprRequest() {
      this.__isset_bitfield = 0;
      this.maxParts = -1;
   }

   public PartitionsByExprRequest(String dbName, String tblName, ByteBuffer expr) {
      this();
      this.dbName = dbName;
      this.tblName = tblName;
      this.expr = TBaseHelper.copyBinary(expr);
   }

   public PartitionsByExprRequest(PartitionsByExprRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblName()) {
         this.tblName = other.tblName;
      }

      if (other.isSetExpr()) {
         this.expr = TBaseHelper.copyBinary(other.expr);
      }

      if (other.isSetDefaultPartitionName()) {
         this.defaultPartitionName = other.defaultPartitionName;
      }

      this.maxParts = other.maxParts;
      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public PartitionsByExprRequest deepCopy() {
      return new PartitionsByExprRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblName = null;
      this.expr = null;
      this.defaultPartitionName = null;
      this.maxParts = -1;
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

   public byte[] getExpr() {
      this.setExpr(TBaseHelper.rightSize(this.expr));
      return this.expr == null ? null : this.expr.array();
   }

   public ByteBuffer bufferForExpr() {
      return TBaseHelper.copyBinary(this.expr);
   }

   public void setExpr(byte[] expr) {
      this.expr = expr == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)expr).clone());
   }

   public void setExpr(@Nullable ByteBuffer expr) {
      this.expr = TBaseHelper.copyBinary(expr);
   }

   public void unsetExpr() {
      this.expr = null;
   }

   public boolean isSetExpr() {
      return this.expr != null;
   }

   public void setExprIsSet(boolean value) {
      if (!value) {
         this.expr = null;
      }

   }

   @Nullable
   public String getDefaultPartitionName() {
      return this.defaultPartitionName;
   }

   public void setDefaultPartitionName(@Nullable String defaultPartitionName) {
      this.defaultPartitionName = defaultPartitionName;
   }

   public void unsetDefaultPartitionName() {
      this.defaultPartitionName = null;
   }

   public boolean isSetDefaultPartitionName() {
      return this.defaultPartitionName != null;
   }

   public void setDefaultPartitionNameIsSet(boolean value) {
      if (!value) {
         this.defaultPartitionName = null;
      }

   }

   public short getMaxParts() {
      return this.maxParts;
   }

   public void setMaxParts(short maxParts) {
      this.maxParts = maxParts;
      this.setMaxPartsIsSet(true);
   }

   public void unsetMaxParts() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetMaxParts() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setMaxPartsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
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
         case EXPR:
            if (value == null) {
               this.unsetExpr();
            } else if (value instanceof byte[]) {
               this.setExpr((byte[])value);
            } else {
               this.setExpr((ByteBuffer)value);
            }
            break;
         case DEFAULT_PARTITION_NAME:
            if (value == null) {
               this.unsetDefaultPartitionName();
            } else {
               this.setDefaultPartitionName((String)value);
            }
            break;
         case MAX_PARTS:
            if (value == null) {
               this.unsetMaxParts();
            } else {
               this.setMaxParts((Short)value);
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
         case EXPR:
            return this.getExpr();
         case DEFAULT_PARTITION_NAME:
            return this.getDefaultPartitionName();
         case MAX_PARTS:
            return this.getMaxParts();
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
            case EXPR:
               return this.isSetExpr();
            case DEFAULT_PARTITION_NAME:
               return this.isSetDefaultPartitionName();
            case MAX_PARTS:
               return this.isSetMaxParts();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionsByExprRequest ? this.equals((PartitionsByExprRequest)that) : false;
   }

   public boolean equals(PartitionsByExprRequest that) {
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

         boolean this_present_expr = this.isSetExpr();
         boolean that_present_expr = that.isSetExpr();
         if (this_present_expr || that_present_expr) {
            if (!this_present_expr || !that_present_expr) {
               return false;
            }

            if (!this.expr.equals(that.expr)) {
               return false;
            }
         }

         boolean this_present_defaultPartitionName = this.isSetDefaultPartitionName();
         boolean that_present_defaultPartitionName = that.isSetDefaultPartitionName();
         if (this_present_defaultPartitionName || that_present_defaultPartitionName) {
            if (!this_present_defaultPartitionName || !that_present_defaultPartitionName) {
               return false;
            }

            if (!this.defaultPartitionName.equals(that.defaultPartitionName)) {
               return false;
            }
         }

         boolean this_present_maxParts = this.isSetMaxParts();
         boolean that_present_maxParts = that.isSetMaxParts();
         if (this_present_maxParts || that_present_maxParts) {
            if (!this_present_maxParts || !that_present_maxParts) {
               return false;
            }

            if (this.maxParts != that.maxParts) {
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

      hashCode = hashCode * 8191 + (this.isSetExpr() ? 131071 : 524287);
      if (this.isSetExpr()) {
         hashCode = hashCode * 8191 + this.expr.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDefaultPartitionName() ? 131071 : 524287);
      if (this.isSetDefaultPartitionName()) {
         hashCode = hashCode * 8191 + this.defaultPartitionName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMaxParts() ? 131071 : 524287);
      if (this.isSetMaxParts()) {
         hashCode = hashCode * 8191 + this.maxParts;
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionsByExprRequest other) {
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

               lastComparison = Boolean.compare(this.isSetExpr(), other.isSetExpr());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetExpr()) {
                     lastComparison = TBaseHelper.compareTo(this.expr, other.expr);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetDefaultPartitionName(), other.isSetDefaultPartitionName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetDefaultPartitionName()) {
                        lastComparison = TBaseHelper.compareTo(this.defaultPartitionName, other.defaultPartitionName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetMaxParts(), other.isSetMaxParts());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetMaxParts()) {
                           lastComparison = TBaseHelper.compareTo(this.maxParts, other.maxParts);
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
      return PartitionsByExprRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionsByExprRequest(");
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

      sb.append("expr:");
      if (this.expr == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.expr, sb);
      }

      first = false;
      if (this.isSetDefaultPartitionName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("defaultPartitionName:");
         if (this.defaultPartitionName == null) {
            sb.append("null");
         } else {
            sb.append(this.defaultPartitionName);
         }

         first = false;
      }

      if (this.isSetMaxParts()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("maxParts:");
         sb.append(this.maxParts);
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
      } else if (!this.isSetExpr()) {
         throw new TProtocolException("Required field 'expr' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{PartitionsByExprRequest._Fields.DEFAULT_PARTITION_NAME, PartitionsByExprRequest._Fields.MAX_PARTS, PartitionsByExprRequest._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PartitionsByExprRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionsByExprRequest._Fields.TBL_NAME, new FieldMetaData("tblName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionsByExprRequest._Fields.EXPR, new FieldMetaData("expr", (byte)1, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(PartitionsByExprRequest._Fields.DEFAULT_PARTITION_NAME, new FieldMetaData("defaultPartitionName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionsByExprRequest._Fields.MAX_PARTS, new FieldMetaData("maxParts", (byte)2, new FieldValueMetaData((byte)6)));
      tmpMap.put(PartitionsByExprRequest._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionsByExprRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAME((short)2, "tblName"),
      EXPR((short)3, "expr"),
      DEFAULT_PARTITION_NAME((short)4, "defaultPartitionName"),
      MAX_PARTS((short)5, "maxParts"),
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
               return EXPR;
            case 4:
               return DEFAULT_PARTITION_NAME;
            case 5:
               return MAX_PARTS;
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

   private static class PartitionsByExprRequestStandardSchemeFactory implements SchemeFactory {
      private PartitionsByExprRequestStandardSchemeFactory() {
      }

      public PartitionsByExprRequestStandardScheme getScheme() {
         return new PartitionsByExprRequestStandardScheme();
      }
   }

   private static class PartitionsByExprRequestStandardScheme extends StandardScheme {
      private PartitionsByExprRequestStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionsByExprRequest struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.expr = iprot.readBinary();
                     struct.setExprIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.defaultPartitionName = iprot.readString();
                     struct.setDefaultPartitionNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 6) {
                     struct.maxParts = iprot.readI16();
                     struct.setMaxPartsIsSet(true);
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

      public void write(TProtocol oprot, PartitionsByExprRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionsByExprRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(PartitionsByExprRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblName != null) {
            oprot.writeFieldBegin(PartitionsByExprRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tblName);
            oprot.writeFieldEnd();
         }

         if (struct.expr != null) {
            oprot.writeFieldBegin(PartitionsByExprRequest.EXPR_FIELD_DESC);
            oprot.writeBinary(struct.expr);
            oprot.writeFieldEnd();
         }

         if (struct.defaultPartitionName != null && struct.isSetDefaultPartitionName()) {
            oprot.writeFieldBegin(PartitionsByExprRequest.DEFAULT_PARTITION_NAME_FIELD_DESC);
            oprot.writeString(struct.defaultPartitionName);
            oprot.writeFieldEnd();
         }

         if (struct.isSetMaxParts()) {
            oprot.writeFieldBegin(PartitionsByExprRequest.MAX_PARTS_FIELD_DESC);
            oprot.writeI16(struct.maxParts);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(PartitionsByExprRequest.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionsByExprRequestTupleSchemeFactory implements SchemeFactory {
      private PartitionsByExprRequestTupleSchemeFactory() {
      }

      public PartitionsByExprRequestTupleScheme getScheme() {
         return new PartitionsByExprRequestTupleScheme();
      }
   }

   private static class PartitionsByExprRequestTupleScheme extends TupleScheme {
      private PartitionsByExprRequestTupleScheme() {
      }

      public void write(TProtocol prot, PartitionsByExprRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tblName);
         oprot.writeBinary(struct.expr);
         BitSet optionals = new BitSet();
         if (struct.isSetDefaultPartitionName()) {
            optionals.set(0);
         }

         if (struct.isSetMaxParts()) {
            optionals.set(1);
         }

         if (struct.isSetCatName()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetDefaultPartitionName()) {
            oprot.writeString(struct.defaultPartitionName);
         }

         if (struct.isSetMaxParts()) {
            oprot.writeI16(struct.maxParts);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, PartitionsByExprRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tblName = iprot.readString();
         struct.setTblNameIsSet(true);
         struct.expr = iprot.readBinary();
         struct.setExprIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.defaultPartitionName = iprot.readString();
            struct.setDefaultPartitionNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.maxParts = iprot.readI16();
            struct.setMaxPartsIsSet(true);
         }

         if (incoming.get(2)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
