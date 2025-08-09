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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class SQLPrimaryKey implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SQLPrimaryKey");
   private static final TField TABLE_DB_FIELD_DESC = new TField("table_db", (byte)11, (short)1);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("table_name", (byte)11, (short)2);
   private static final TField COLUMN_NAME_FIELD_DESC = new TField("column_name", (byte)11, (short)3);
   private static final TField KEY_SEQ_FIELD_DESC = new TField("key_seq", (byte)8, (short)4);
   private static final TField PK_NAME_FIELD_DESC = new TField("pk_name", (byte)11, (short)5);
   private static final TField ENABLE_CSTR_FIELD_DESC = new TField("enable_cstr", (byte)2, (short)6);
   private static final TField VALIDATE_CSTR_FIELD_DESC = new TField("validate_cstr", (byte)2, (short)7);
   private static final TField RELY_CSTR_FIELD_DESC = new TField("rely_cstr", (byte)2, (short)8);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SQLPrimaryKeyStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SQLPrimaryKeyTupleSchemeFactory();
   @Nullable
   private String table_db;
   @Nullable
   private String table_name;
   @Nullable
   private String column_name;
   private int key_seq;
   @Nullable
   private String pk_name;
   private boolean enable_cstr;
   private boolean validate_cstr;
   private boolean rely_cstr;
   private static final int __KEY_SEQ_ISSET_ID = 0;
   private static final int __ENABLE_CSTR_ISSET_ID = 1;
   private static final int __VALIDATE_CSTR_ISSET_ID = 2;
   private static final int __RELY_CSTR_ISSET_ID = 3;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public SQLPrimaryKey() {
      this.__isset_bitfield = 0;
   }

   public SQLPrimaryKey(String table_db, String table_name, String column_name, int key_seq, String pk_name, boolean enable_cstr, boolean validate_cstr, boolean rely_cstr) {
      this();
      this.table_db = table_db;
      this.table_name = table_name;
      this.column_name = column_name;
      this.key_seq = key_seq;
      this.setKey_seqIsSet(true);
      this.pk_name = pk_name;
      this.enable_cstr = enable_cstr;
      this.setEnable_cstrIsSet(true);
      this.validate_cstr = validate_cstr;
      this.setValidate_cstrIsSet(true);
      this.rely_cstr = rely_cstr;
      this.setRely_cstrIsSet(true);
   }

   public SQLPrimaryKey(SQLPrimaryKey other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetTable_db()) {
         this.table_db = other.table_db;
      }

      if (other.isSetTable_name()) {
         this.table_name = other.table_name;
      }

      if (other.isSetColumn_name()) {
         this.column_name = other.column_name;
      }

      this.key_seq = other.key_seq;
      if (other.isSetPk_name()) {
         this.pk_name = other.pk_name;
      }

      this.enable_cstr = other.enable_cstr;
      this.validate_cstr = other.validate_cstr;
      this.rely_cstr = other.rely_cstr;
   }

   public SQLPrimaryKey deepCopy() {
      return new SQLPrimaryKey(this);
   }

   public void clear() {
      this.table_db = null;
      this.table_name = null;
      this.column_name = null;
      this.setKey_seqIsSet(false);
      this.key_seq = 0;
      this.pk_name = null;
      this.setEnable_cstrIsSet(false);
      this.enable_cstr = false;
      this.setValidate_cstrIsSet(false);
      this.validate_cstr = false;
      this.setRely_cstrIsSet(false);
      this.rely_cstr = false;
   }

   @Nullable
   public String getTable_db() {
      return this.table_db;
   }

   public void setTable_db(@Nullable String table_db) {
      this.table_db = table_db;
   }

   public void unsetTable_db() {
      this.table_db = null;
   }

   public boolean isSetTable_db() {
      return this.table_db != null;
   }

   public void setTable_dbIsSet(boolean value) {
      if (!value) {
         this.table_db = null;
      }

   }

   @Nullable
   public String getTable_name() {
      return this.table_name;
   }

   public void setTable_name(@Nullable String table_name) {
      this.table_name = table_name;
   }

   public void unsetTable_name() {
      this.table_name = null;
   }

   public boolean isSetTable_name() {
      return this.table_name != null;
   }

   public void setTable_nameIsSet(boolean value) {
      if (!value) {
         this.table_name = null;
      }

   }

   @Nullable
   public String getColumn_name() {
      return this.column_name;
   }

   public void setColumn_name(@Nullable String column_name) {
      this.column_name = column_name;
   }

   public void unsetColumn_name() {
      this.column_name = null;
   }

   public boolean isSetColumn_name() {
      return this.column_name != null;
   }

   public void setColumn_nameIsSet(boolean value) {
      if (!value) {
         this.column_name = null;
      }

   }

   public int getKey_seq() {
      return this.key_seq;
   }

   public void setKey_seq(int key_seq) {
      this.key_seq = key_seq;
      this.setKey_seqIsSet(true);
   }

   public void unsetKey_seq() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetKey_seq() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setKey_seqIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getPk_name() {
      return this.pk_name;
   }

   public void setPk_name(@Nullable String pk_name) {
      this.pk_name = pk_name;
   }

   public void unsetPk_name() {
      this.pk_name = null;
   }

   public boolean isSetPk_name() {
      return this.pk_name != null;
   }

   public void setPk_nameIsSet(boolean value) {
      if (!value) {
         this.pk_name = null;
      }

   }

   public boolean isEnable_cstr() {
      return this.enable_cstr;
   }

   public void setEnable_cstr(boolean enable_cstr) {
      this.enable_cstr = enable_cstr;
      this.setEnable_cstrIsSet(true);
   }

   public void unsetEnable_cstr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetEnable_cstr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setEnable_cstrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public boolean isValidate_cstr() {
      return this.validate_cstr;
   }

   public void setValidate_cstr(boolean validate_cstr) {
      this.validate_cstr = validate_cstr;
      this.setValidate_cstrIsSet(true);
   }

   public void unsetValidate_cstr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetValidate_cstr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setValidate_cstrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public boolean isRely_cstr() {
      return this.rely_cstr;
   }

   public void setRely_cstr(boolean rely_cstr) {
      this.rely_cstr = rely_cstr;
      this.setRely_cstrIsSet(true);
   }

   public void unsetRely_cstr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetRely_cstr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setRely_cstrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TABLE_DB:
            if (value == null) {
               this.unsetTable_db();
            } else {
               this.setTable_db((String)value);
            }
            break;
         case TABLE_NAME:
            if (value == null) {
               this.unsetTable_name();
            } else {
               this.setTable_name((String)value);
            }
            break;
         case COLUMN_NAME:
            if (value == null) {
               this.unsetColumn_name();
            } else {
               this.setColumn_name((String)value);
            }
            break;
         case KEY_SEQ:
            if (value == null) {
               this.unsetKey_seq();
            } else {
               this.setKey_seq((Integer)value);
            }
            break;
         case PK_NAME:
            if (value == null) {
               this.unsetPk_name();
            } else {
               this.setPk_name((String)value);
            }
            break;
         case ENABLE_CSTR:
            if (value == null) {
               this.unsetEnable_cstr();
            } else {
               this.setEnable_cstr((Boolean)value);
            }
            break;
         case VALIDATE_CSTR:
            if (value == null) {
               this.unsetValidate_cstr();
            } else {
               this.setValidate_cstr((Boolean)value);
            }
            break;
         case RELY_CSTR:
            if (value == null) {
               this.unsetRely_cstr();
            } else {
               this.setRely_cstr((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TABLE_DB:
            return this.getTable_db();
         case TABLE_NAME:
            return this.getTable_name();
         case COLUMN_NAME:
            return this.getColumn_name();
         case KEY_SEQ:
            return this.getKey_seq();
         case PK_NAME:
            return this.getPk_name();
         case ENABLE_CSTR:
            return this.isEnable_cstr();
         case VALIDATE_CSTR:
            return this.isValidate_cstr();
         case RELY_CSTR:
            return this.isRely_cstr();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TABLE_DB:
               return this.isSetTable_db();
            case TABLE_NAME:
               return this.isSetTable_name();
            case COLUMN_NAME:
               return this.isSetColumn_name();
            case KEY_SEQ:
               return this.isSetKey_seq();
            case PK_NAME:
               return this.isSetPk_name();
            case ENABLE_CSTR:
               return this.isSetEnable_cstr();
            case VALIDATE_CSTR:
               return this.isSetValidate_cstr();
            case RELY_CSTR:
               return this.isSetRely_cstr();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SQLPrimaryKey ? this.equals((SQLPrimaryKey)that) : false;
   }

   public boolean equals(SQLPrimaryKey that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_table_db = this.isSetTable_db();
         boolean that_present_table_db = that.isSetTable_db();
         if (this_present_table_db || that_present_table_db) {
            if (!this_present_table_db || !that_present_table_db) {
               return false;
            }

            if (!this.table_db.equals(that.table_db)) {
               return false;
            }
         }

         boolean this_present_table_name = this.isSetTable_name();
         boolean that_present_table_name = that.isSetTable_name();
         if (this_present_table_name || that_present_table_name) {
            if (!this_present_table_name || !that_present_table_name) {
               return false;
            }

            if (!this.table_name.equals(that.table_name)) {
               return false;
            }
         }

         boolean this_present_column_name = this.isSetColumn_name();
         boolean that_present_column_name = that.isSetColumn_name();
         if (this_present_column_name || that_present_column_name) {
            if (!this_present_column_name || !that_present_column_name) {
               return false;
            }

            if (!this.column_name.equals(that.column_name)) {
               return false;
            }
         }

         boolean this_present_key_seq = true;
         boolean that_present_key_seq = true;
         if (this_present_key_seq || that_present_key_seq) {
            if (!this_present_key_seq || !that_present_key_seq) {
               return false;
            }

            if (this.key_seq != that.key_seq) {
               return false;
            }
         }

         boolean this_present_pk_name = this.isSetPk_name();
         boolean that_present_pk_name = that.isSetPk_name();
         if (this_present_pk_name || that_present_pk_name) {
            if (!this_present_pk_name || !that_present_pk_name) {
               return false;
            }

            if (!this.pk_name.equals(that.pk_name)) {
               return false;
            }
         }

         boolean this_present_enable_cstr = true;
         boolean that_present_enable_cstr = true;
         if (this_present_enable_cstr || that_present_enable_cstr) {
            if (!this_present_enable_cstr || !that_present_enable_cstr) {
               return false;
            }

            if (this.enable_cstr != that.enable_cstr) {
               return false;
            }
         }

         boolean this_present_validate_cstr = true;
         boolean that_present_validate_cstr = true;
         if (this_present_validate_cstr || that_present_validate_cstr) {
            if (!this_present_validate_cstr || !that_present_validate_cstr) {
               return false;
            }

            if (this.validate_cstr != that.validate_cstr) {
               return false;
            }
         }

         boolean this_present_rely_cstr = true;
         boolean that_present_rely_cstr = true;
         if (this_present_rely_cstr || that_present_rely_cstr) {
            if (!this_present_rely_cstr || !that_present_rely_cstr) {
               return false;
            }

            if (this.rely_cstr != that.rely_cstr) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetTable_db() ? 131071 : 524287);
      if (this.isSetTable_db()) {
         hashCode = hashCode * 8191 + this.table_db.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTable_name() ? 131071 : 524287);
      if (this.isSetTable_name()) {
         hashCode = hashCode * 8191 + this.table_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColumn_name() ? 131071 : 524287);
      if (this.isSetColumn_name()) {
         hashCode = hashCode * 8191 + this.column_name.hashCode();
      }

      hashCode = hashCode * 8191 + this.key_seq;
      hashCode = hashCode * 8191 + (this.isSetPk_name() ? 131071 : 524287);
      if (this.isSetPk_name()) {
         hashCode = hashCode * 8191 + this.pk_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.enable_cstr ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.validate_cstr ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.rely_cstr ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(SQLPrimaryKey other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTable_db(), other.isSetTable_db());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTable_db()) {
               lastComparison = TBaseHelper.compareTo(this.table_db, other.table_db);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTable_name(), other.isSetTable_name());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTable_name()) {
                  lastComparison = TBaseHelper.compareTo(this.table_name, other.table_name);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetColumn_name(), other.isSetColumn_name());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetColumn_name()) {
                     lastComparison = TBaseHelper.compareTo(this.column_name, other.column_name);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetKey_seq(), other.isSetKey_seq());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetKey_seq()) {
                        lastComparison = TBaseHelper.compareTo(this.key_seq, other.key_seq);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetPk_name(), other.isSetPk_name());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetPk_name()) {
                           lastComparison = TBaseHelper.compareTo(this.pk_name, other.pk_name);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetEnable_cstr(), other.isSetEnable_cstr());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetEnable_cstr()) {
                              lastComparison = TBaseHelper.compareTo(this.enable_cstr, other.enable_cstr);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetValidate_cstr(), other.isSetValidate_cstr());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetValidate_cstr()) {
                                 lastComparison = TBaseHelper.compareTo(this.validate_cstr, other.validate_cstr);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetRely_cstr(), other.isSetRely_cstr());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetRely_cstr()) {
                                    lastComparison = TBaseHelper.compareTo(this.rely_cstr, other.rely_cstr);
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
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return SQLPrimaryKey._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SQLPrimaryKey(");
      boolean first = true;
      sb.append("table_db:");
      if (this.table_db == null) {
         sb.append("null");
      } else {
         sb.append(this.table_db);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("table_name:");
      if (this.table_name == null) {
         sb.append("null");
      } else {
         sb.append(this.table_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("column_name:");
      if (this.column_name == null) {
         sb.append("null");
      } else {
         sb.append(this.column_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("key_seq:");
      sb.append(this.key_seq);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("pk_name:");
      if (this.pk_name == null) {
         sb.append("null");
      } else {
         sb.append(this.pk_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("enable_cstr:");
      sb.append(this.enable_cstr);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("validate_cstr:");
      sb.append(this.validate_cstr);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("rely_cstr:");
      sb.append(this.rely_cstr);
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(SQLPrimaryKey._Fields.TABLE_DB, new FieldMetaData("table_db", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLPrimaryKey._Fields.TABLE_NAME, new FieldMetaData("table_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLPrimaryKey._Fields.COLUMN_NAME, new FieldMetaData("column_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLPrimaryKey._Fields.KEY_SEQ, new FieldMetaData("key_seq", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(SQLPrimaryKey._Fields.PK_NAME, new FieldMetaData("pk_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLPrimaryKey._Fields.ENABLE_CSTR, new FieldMetaData("enable_cstr", (byte)3, new FieldValueMetaData((byte)2)));
      tmpMap.put(SQLPrimaryKey._Fields.VALIDATE_CSTR, new FieldMetaData("validate_cstr", (byte)3, new FieldValueMetaData((byte)2)));
      tmpMap.put(SQLPrimaryKey._Fields.RELY_CSTR, new FieldMetaData("rely_cstr", (byte)3, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SQLPrimaryKey.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TABLE_DB((short)1, "table_db"),
      TABLE_NAME((short)2, "table_name"),
      COLUMN_NAME((short)3, "column_name"),
      KEY_SEQ((short)4, "key_seq"),
      PK_NAME((short)5, "pk_name"),
      ENABLE_CSTR((short)6, "enable_cstr"),
      VALIDATE_CSTR((short)7, "validate_cstr"),
      RELY_CSTR((short)8, "rely_cstr");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TABLE_DB;
            case 2:
               return TABLE_NAME;
            case 3:
               return COLUMN_NAME;
            case 4:
               return KEY_SEQ;
            case 5:
               return PK_NAME;
            case 6:
               return ENABLE_CSTR;
            case 7:
               return VALIDATE_CSTR;
            case 8:
               return RELY_CSTR;
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

   private static class SQLPrimaryKeyStandardSchemeFactory implements SchemeFactory {
      private SQLPrimaryKeyStandardSchemeFactory() {
      }

      public SQLPrimaryKeyStandardScheme getScheme() {
         return new SQLPrimaryKeyStandardScheme();
      }
   }

   private static class SQLPrimaryKeyStandardScheme extends StandardScheme {
      private SQLPrimaryKeyStandardScheme() {
      }

      public void read(TProtocol iprot, SQLPrimaryKey struct) throws TException {
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
                     struct.table_db = iprot.readString();
                     struct.setTable_dbIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.table_name = iprot.readString();
                     struct.setTable_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.column_name = iprot.readString();
                     struct.setColumn_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.key_seq = iprot.readI32();
                     struct.setKey_seqIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.pk_name = iprot.readString();
                     struct.setPk_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 2) {
                     struct.enable_cstr = iprot.readBool();
                     struct.setEnable_cstrIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 2) {
                     struct.validate_cstr = iprot.readBool();
                     struct.setValidate_cstrIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 2) {
                     struct.rely_cstr = iprot.readBool();
                     struct.setRely_cstrIsSet(true);
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

      public void write(TProtocol oprot, SQLPrimaryKey struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SQLPrimaryKey.STRUCT_DESC);
         if (struct.table_db != null) {
            oprot.writeFieldBegin(SQLPrimaryKey.TABLE_DB_FIELD_DESC);
            oprot.writeString(struct.table_db);
            oprot.writeFieldEnd();
         }

         if (struct.table_name != null) {
            oprot.writeFieldBegin(SQLPrimaryKey.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.table_name);
            oprot.writeFieldEnd();
         }

         if (struct.column_name != null) {
            oprot.writeFieldBegin(SQLPrimaryKey.COLUMN_NAME_FIELD_DESC);
            oprot.writeString(struct.column_name);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(SQLPrimaryKey.KEY_SEQ_FIELD_DESC);
         oprot.writeI32(struct.key_seq);
         oprot.writeFieldEnd();
         if (struct.pk_name != null) {
            oprot.writeFieldBegin(SQLPrimaryKey.PK_NAME_FIELD_DESC);
            oprot.writeString(struct.pk_name);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(SQLPrimaryKey.ENABLE_CSTR_FIELD_DESC);
         oprot.writeBool(struct.enable_cstr);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SQLPrimaryKey.VALIDATE_CSTR_FIELD_DESC);
         oprot.writeBool(struct.validate_cstr);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SQLPrimaryKey.RELY_CSTR_FIELD_DESC);
         oprot.writeBool(struct.rely_cstr);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SQLPrimaryKeyTupleSchemeFactory implements SchemeFactory {
      private SQLPrimaryKeyTupleSchemeFactory() {
      }

      public SQLPrimaryKeyTupleScheme getScheme() {
         return new SQLPrimaryKeyTupleScheme();
      }
   }

   private static class SQLPrimaryKeyTupleScheme extends TupleScheme {
      private SQLPrimaryKeyTupleScheme() {
      }

      public void write(TProtocol prot, SQLPrimaryKey struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetTable_db()) {
            optionals.set(0);
         }

         if (struct.isSetTable_name()) {
            optionals.set(1);
         }

         if (struct.isSetColumn_name()) {
            optionals.set(2);
         }

         if (struct.isSetKey_seq()) {
            optionals.set(3);
         }

         if (struct.isSetPk_name()) {
            optionals.set(4);
         }

         if (struct.isSetEnable_cstr()) {
            optionals.set(5);
         }

         if (struct.isSetValidate_cstr()) {
            optionals.set(6);
         }

         if (struct.isSetRely_cstr()) {
            optionals.set(7);
         }

         oprot.writeBitSet(optionals, 8);
         if (struct.isSetTable_db()) {
            oprot.writeString(struct.table_db);
         }

         if (struct.isSetTable_name()) {
            oprot.writeString(struct.table_name);
         }

         if (struct.isSetColumn_name()) {
            oprot.writeString(struct.column_name);
         }

         if (struct.isSetKey_seq()) {
            oprot.writeI32(struct.key_seq);
         }

         if (struct.isSetPk_name()) {
            oprot.writeString(struct.pk_name);
         }

         if (struct.isSetEnable_cstr()) {
            oprot.writeBool(struct.enable_cstr);
         }

         if (struct.isSetValidate_cstr()) {
            oprot.writeBool(struct.validate_cstr);
         }

         if (struct.isSetRely_cstr()) {
            oprot.writeBool(struct.rely_cstr);
         }

      }

      public void read(TProtocol prot, SQLPrimaryKey struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(8);
         if (incoming.get(0)) {
            struct.table_db = iprot.readString();
            struct.setTable_dbIsSet(true);
         }

         if (incoming.get(1)) {
            struct.table_name = iprot.readString();
            struct.setTable_nameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.column_name = iprot.readString();
            struct.setColumn_nameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.key_seq = iprot.readI32();
            struct.setKey_seqIsSet(true);
         }

         if (incoming.get(4)) {
            struct.pk_name = iprot.readString();
            struct.setPk_nameIsSet(true);
         }

         if (incoming.get(5)) {
            struct.enable_cstr = iprot.readBool();
            struct.setEnable_cstrIsSet(true);
         }

         if (incoming.get(6)) {
            struct.validate_cstr = iprot.readBool();
            struct.setValidate_cstrIsSet(true);
         }

         if (incoming.get(7)) {
            struct.rely_cstr = iprot.readBool();
            struct.setRely_cstrIsSet(true);
         }

      }
   }
}
