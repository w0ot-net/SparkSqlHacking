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

public class SQLForeignKey implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SQLForeignKey");
   private static final TField PKTABLE_DB_FIELD_DESC = new TField("pktable_db", (byte)11, (short)1);
   private static final TField PKTABLE_NAME_FIELD_DESC = new TField("pktable_name", (byte)11, (short)2);
   private static final TField PKCOLUMN_NAME_FIELD_DESC = new TField("pkcolumn_name", (byte)11, (short)3);
   private static final TField FKTABLE_DB_FIELD_DESC = new TField("fktable_db", (byte)11, (short)4);
   private static final TField FKTABLE_NAME_FIELD_DESC = new TField("fktable_name", (byte)11, (short)5);
   private static final TField FKCOLUMN_NAME_FIELD_DESC = new TField("fkcolumn_name", (byte)11, (short)6);
   private static final TField KEY_SEQ_FIELD_DESC = new TField("key_seq", (byte)8, (short)7);
   private static final TField UPDATE_RULE_FIELD_DESC = new TField("update_rule", (byte)8, (short)8);
   private static final TField DELETE_RULE_FIELD_DESC = new TField("delete_rule", (byte)8, (short)9);
   private static final TField FK_NAME_FIELD_DESC = new TField("fk_name", (byte)11, (short)10);
   private static final TField PK_NAME_FIELD_DESC = new TField("pk_name", (byte)11, (short)11);
   private static final TField ENABLE_CSTR_FIELD_DESC = new TField("enable_cstr", (byte)2, (short)12);
   private static final TField VALIDATE_CSTR_FIELD_DESC = new TField("validate_cstr", (byte)2, (short)13);
   private static final TField RELY_CSTR_FIELD_DESC = new TField("rely_cstr", (byte)2, (short)14);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SQLForeignKeyStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SQLForeignKeyTupleSchemeFactory();
   @Nullable
   private String pktable_db;
   @Nullable
   private String pktable_name;
   @Nullable
   private String pkcolumn_name;
   @Nullable
   private String fktable_db;
   @Nullable
   private String fktable_name;
   @Nullable
   private String fkcolumn_name;
   private int key_seq;
   private int update_rule;
   private int delete_rule;
   @Nullable
   private String fk_name;
   @Nullable
   private String pk_name;
   private boolean enable_cstr;
   private boolean validate_cstr;
   private boolean rely_cstr;
   private static final int __KEY_SEQ_ISSET_ID = 0;
   private static final int __UPDATE_RULE_ISSET_ID = 1;
   private static final int __DELETE_RULE_ISSET_ID = 2;
   private static final int __ENABLE_CSTR_ISSET_ID = 3;
   private static final int __VALIDATE_CSTR_ISSET_ID = 4;
   private static final int __RELY_CSTR_ISSET_ID = 5;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public SQLForeignKey() {
      this.__isset_bitfield = 0;
   }

   public SQLForeignKey(String pktable_db, String pktable_name, String pkcolumn_name, String fktable_db, String fktable_name, String fkcolumn_name, int key_seq, int update_rule, int delete_rule, String fk_name, String pk_name, boolean enable_cstr, boolean validate_cstr, boolean rely_cstr) {
      this();
      this.pktable_db = pktable_db;
      this.pktable_name = pktable_name;
      this.pkcolumn_name = pkcolumn_name;
      this.fktable_db = fktable_db;
      this.fktable_name = fktable_name;
      this.fkcolumn_name = fkcolumn_name;
      this.key_seq = key_seq;
      this.setKey_seqIsSet(true);
      this.update_rule = update_rule;
      this.setUpdate_ruleIsSet(true);
      this.delete_rule = delete_rule;
      this.setDelete_ruleIsSet(true);
      this.fk_name = fk_name;
      this.pk_name = pk_name;
      this.enable_cstr = enable_cstr;
      this.setEnable_cstrIsSet(true);
      this.validate_cstr = validate_cstr;
      this.setValidate_cstrIsSet(true);
      this.rely_cstr = rely_cstr;
      this.setRely_cstrIsSet(true);
   }

   public SQLForeignKey(SQLForeignKey other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetPktable_db()) {
         this.pktable_db = other.pktable_db;
      }

      if (other.isSetPktable_name()) {
         this.pktable_name = other.pktable_name;
      }

      if (other.isSetPkcolumn_name()) {
         this.pkcolumn_name = other.pkcolumn_name;
      }

      if (other.isSetFktable_db()) {
         this.fktable_db = other.fktable_db;
      }

      if (other.isSetFktable_name()) {
         this.fktable_name = other.fktable_name;
      }

      if (other.isSetFkcolumn_name()) {
         this.fkcolumn_name = other.fkcolumn_name;
      }

      this.key_seq = other.key_seq;
      this.update_rule = other.update_rule;
      this.delete_rule = other.delete_rule;
      if (other.isSetFk_name()) {
         this.fk_name = other.fk_name;
      }

      if (other.isSetPk_name()) {
         this.pk_name = other.pk_name;
      }

      this.enable_cstr = other.enable_cstr;
      this.validate_cstr = other.validate_cstr;
      this.rely_cstr = other.rely_cstr;
   }

   public SQLForeignKey deepCopy() {
      return new SQLForeignKey(this);
   }

   public void clear() {
      this.pktable_db = null;
      this.pktable_name = null;
      this.pkcolumn_name = null;
      this.fktable_db = null;
      this.fktable_name = null;
      this.fkcolumn_name = null;
      this.setKey_seqIsSet(false);
      this.key_seq = 0;
      this.setUpdate_ruleIsSet(false);
      this.update_rule = 0;
      this.setDelete_ruleIsSet(false);
      this.delete_rule = 0;
      this.fk_name = null;
      this.pk_name = null;
      this.setEnable_cstrIsSet(false);
      this.enable_cstr = false;
      this.setValidate_cstrIsSet(false);
      this.validate_cstr = false;
      this.setRely_cstrIsSet(false);
      this.rely_cstr = false;
   }

   @Nullable
   public String getPktable_db() {
      return this.pktable_db;
   }

   public void setPktable_db(@Nullable String pktable_db) {
      this.pktable_db = pktable_db;
   }

   public void unsetPktable_db() {
      this.pktable_db = null;
   }

   public boolean isSetPktable_db() {
      return this.pktable_db != null;
   }

   public void setPktable_dbIsSet(boolean value) {
      if (!value) {
         this.pktable_db = null;
      }

   }

   @Nullable
   public String getPktable_name() {
      return this.pktable_name;
   }

   public void setPktable_name(@Nullable String pktable_name) {
      this.pktable_name = pktable_name;
   }

   public void unsetPktable_name() {
      this.pktable_name = null;
   }

   public boolean isSetPktable_name() {
      return this.pktable_name != null;
   }

   public void setPktable_nameIsSet(boolean value) {
      if (!value) {
         this.pktable_name = null;
      }

   }

   @Nullable
   public String getPkcolumn_name() {
      return this.pkcolumn_name;
   }

   public void setPkcolumn_name(@Nullable String pkcolumn_name) {
      this.pkcolumn_name = pkcolumn_name;
   }

   public void unsetPkcolumn_name() {
      this.pkcolumn_name = null;
   }

   public boolean isSetPkcolumn_name() {
      return this.pkcolumn_name != null;
   }

   public void setPkcolumn_nameIsSet(boolean value) {
      if (!value) {
         this.pkcolumn_name = null;
      }

   }

   @Nullable
   public String getFktable_db() {
      return this.fktable_db;
   }

   public void setFktable_db(@Nullable String fktable_db) {
      this.fktable_db = fktable_db;
   }

   public void unsetFktable_db() {
      this.fktable_db = null;
   }

   public boolean isSetFktable_db() {
      return this.fktable_db != null;
   }

   public void setFktable_dbIsSet(boolean value) {
      if (!value) {
         this.fktable_db = null;
      }

   }

   @Nullable
   public String getFktable_name() {
      return this.fktable_name;
   }

   public void setFktable_name(@Nullable String fktable_name) {
      this.fktable_name = fktable_name;
   }

   public void unsetFktable_name() {
      this.fktable_name = null;
   }

   public boolean isSetFktable_name() {
      return this.fktable_name != null;
   }

   public void setFktable_nameIsSet(boolean value) {
      if (!value) {
         this.fktable_name = null;
      }

   }

   @Nullable
   public String getFkcolumn_name() {
      return this.fkcolumn_name;
   }

   public void setFkcolumn_name(@Nullable String fkcolumn_name) {
      this.fkcolumn_name = fkcolumn_name;
   }

   public void unsetFkcolumn_name() {
      this.fkcolumn_name = null;
   }

   public boolean isSetFkcolumn_name() {
      return this.fkcolumn_name != null;
   }

   public void setFkcolumn_nameIsSet(boolean value) {
      if (!value) {
         this.fkcolumn_name = null;
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

   public int getUpdate_rule() {
      return this.update_rule;
   }

   public void setUpdate_rule(int update_rule) {
      this.update_rule = update_rule;
      this.setUpdate_ruleIsSet(true);
   }

   public void unsetUpdate_rule() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetUpdate_rule() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setUpdate_ruleIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public int getDelete_rule() {
      return this.delete_rule;
   }

   public void setDelete_rule(int delete_rule) {
      this.delete_rule = delete_rule;
      this.setDelete_ruleIsSet(true);
   }

   public void unsetDelete_rule() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetDelete_rule() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setDelete_ruleIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   @Nullable
   public String getFk_name() {
      return this.fk_name;
   }

   public void setFk_name(@Nullable String fk_name) {
      this.fk_name = fk_name;
   }

   public void unsetFk_name() {
      this.fk_name = null;
   }

   public boolean isSetFk_name() {
      return this.fk_name != null;
   }

   public void setFk_nameIsSet(boolean value) {
      if (!value) {
         this.fk_name = null;
      }

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
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetEnable_cstr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setEnable_cstrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   public boolean isValidate_cstr() {
      return this.validate_cstr;
   }

   public void setValidate_cstr(boolean validate_cstr) {
      this.validate_cstr = validate_cstr;
      this.setValidate_cstrIsSet(true);
   }

   public void unsetValidate_cstr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 4);
   }

   public boolean isSetValidate_cstr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 4);
   }

   public void setValidate_cstrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 4, value);
   }

   public boolean isRely_cstr() {
      return this.rely_cstr;
   }

   public void setRely_cstr(boolean rely_cstr) {
      this.rely_cstr = rely_cstr;
      this.setRely_cstrIsSet(true);
   }

   public void unsetRely_cstr() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 5);
   }

   public boolean isSetRely_cstr() {
      return EncodingUtils.testBit(this.__isset_bitfield, 5);
   }

   public void setRely_cstrIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 5, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PKTABLE_DB:
            if (value == null) {
               this.unsetPktable_db();
            } else {
               this.setPktable_db((String)value);
            }
            break;
         case PKTABLE_NAME:
            if (value == null) {
               this.unsetPktable_name();
            } else {
               this.setPktable_name((String)value);
            }
            break;
         case PKCOLUMN_NAME:
            if (value == null) {
               this.unsetPkcolumn_name();
            } else {
               this.setPkcolumn_name((String)value);
            }
            break;
         case FKTABLE_DB:
            if (value == null) {
               this.unsetFktable_db();
            } else {
               this.setFktable_db((String)value);
            }
            break;
         case FKTABLE_NAME:
            if (value == null) {
               this.unsetFktable_name();
            } else {
               this.setFktable_name((String)value);
            }
            break;
         case FKCOLUMN_NAME:
            if (value == null) {
               this.unsetFkcolumn_name();
            } else {
               this.setFkcolumn_name((String)value);
            }
            break;
         case KEY_SEQ:
            if (value == null) {
               this.unsetKey_seq();
            } else {
               this.setKey_seq((Integer)value);
            }
            break;
         case UPDATE_RULE:
            if (value == null) {
               this.unsetUpdate_rule();
            } else {
               this.setUpdate_rule((Integer)value);
            }
            break;
         case DELETE_RULE:
            if (value == null) {
               this.unsetDelete_rule();
            } else {
               this.setDelete_rule((Integer)value);
            }
            break;
         case FK_NAME:
            if (value == null) {
               this.unsetFk_name();
            } else {
               this.setFk_name((String)value);
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
         case PKTABLE_DB:
            return this.getPktable_db();
         case PKTABLE_NAME:
            return this.getPktable_name();
         case PKCOLUMN_NAME:
            return this.getPkcolumn_name();
         case FKTABLE_DB:
            return this.getFktable_db();
         case FKTABLE_NAME:
            return this.getFktable_name();
         case FKCOLUMN_NAME:
            return this.getFkcolumn_name();
         case KEY_SEQ:
            return this.getKey_seq();
         case UPDATE_RULE:
            return this.getUpdate_rule();
         case DELETE_RULE:
            return this.getDelete_rule();
         case FK_NAME:
            return this.getFk_name();
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
            case PKTABLE_DB:
               return this.isSetPktable_db();
            case PKTABLE_NAME:
               return this.isSetPktable_name();
            case PKCOLUMN_NAME:
               return this.isSetPkcolumn_name();
            case FKTABLE_DB:
               return this.isSetFktable_db();
            case FKTABLE_NAME:
               return this.isSetFktable_name();
            case FKCOLUMN_NAME:
               return this.isSetFkcolumn_name();
            case KEY_SEQ:
               return this.isSetKey_seq();
            case UPDATE_RULE:
               return this.isSetUpdate_rule();
            case DELETE_RULE:
               return this.isSetDelete_rule();
            case FK_NAME:
               return this.isSetFk_name();
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
      return that instanceof SQLForeignKey ? this.equals((SQLForeignKey)that) : false;
   }

   public boolean equals(SQLForeignKey that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_pktable_db = this.isSetPktable_db();
         boolean that_present_pktable_db = that.isSetPktable_db();
         if (this_present_pktable_db || that_present_pktable_db) {
            if (!this_present_pktable_db || !that_present_pktable_db) {
               return false;
            }

            if (!this.pktable_db.equals(that.pktable_db)) {
               return false;
            }
         }

         boolean this_present_pktable_name = this.isSetPktable_name();
         boolean that_present_pktable_name = that.isSetPktable_name();
         if (this_present_pktable_name || that_present_pktable_name) {
            if (!this_present_pktable_name || !that_present_pktable_name) {
               return false;
            }

            if (!this.pktable_name.equals(that.pktable_name)) {
               return false;
            }
         }

         boolean this_present_pkcolumn_name = this.isSetPkcolumn_name();
         boolean that_present_pkcolumn_name = that.isSetPkcolumn_name();
         if (this_present_pkcolumn_name || that_present_pkcolumn_name) {
            if (!this_present_pkcolumn_name || !that_present_pkcolumn_name) {
               return false;
            }

            if (!this.pkcolumn_name.equals(that.pkcolumn_name)) {
               return false;
            }
         }

         boolean this_present_fktable_db = this.isSetFktable_db();
         boolean that_present_fktable_db = that.isSetFktable_db();
         if (this_present_fktable_db || that_present_fktable_db) {
            if (!this_present_fktable_db || !that_present_fktable_db) {
               return false;
            }

            if (!this.fktable_db.equals(that.fktable_db)) {
               return false;
            }
         }

         boolean this_present_fktable_name = this.isSetFktable_name();
         boolean that_present_fktable_name = that.isSetFktable_name();
         if (this_present_fktable_name || that_present_fktable_name) {
            if (!this_present_fktable_name || !that_present_fktable_name) {
               return false;
            }

            if (!this.fktable_name.equals(that.fktable_name)) {
               return false;
            }
         }

         boolean this_present_fkcolumn_name = this.isSetFkcolumn_name();
         boolean that_present_fkcolumn_name = that.isSetFkcolumn_name();
         if (this_present_fkcolumn_name || that_present_fkcolumn_name) {
            if (!this_present_fkcolumn_name || !that_present_fkcolumn_name) {
               return false;
            }

            if (!this.fkcolumn_name.equals(that.fkcolumn_name)) {
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

         boolean this_present_update_rule = true;
         boolean that_present_update_rule = true;
         if (this_present_update_rule || that_present_update_rule) {
            if (!this_present_update_rule || !that_present_update_rule) {
               return false;
            }

            if (this.update_rule != that.update_rule) {
               return false;
            }
         }

         boolean this_present_delete_rule = true;
         boolean that_present_delete_rule = true;
         if (this_present_delete_rule || that_present_delete_rule) {
            if (!this_present_delete_rule || !that_present_delete_rule) {
               return false;
            }

            if (this.delete_rule != that.delete_rule) {
               return false;
            }
         }

         boolean this_present_fk_name = this.isSetFk_name();
         boolean that_present_fk_name = that.isSetFk_name();
         if (this_present_fk_name || that_present_fk_name) {
            if (!this_present_fk_name || !that_present_fk_name) {
               return false;
            }

            if (!this.fk_name.equals(that.fk_name)) {
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
      hashCode = hashCode * 8191 + (this.isSetPktable_db() ? 131071 : 524287);
      if (this.isSetPktable_db()) {
         hashCode = hashCode * 8191 + this.pktable_db.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPktable_name() ? 131071 : 524287);
      if (this.isSetPktable_name()) {
         hashCode = hashCode * 8191 + this.pktable_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPkcolumn_name() ? 131071 : 524287);
      if (this.isSetPkcolumn_name()) {
         hashCode = hashCode * 8191 + this.pkcolumn_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFktable_db() ? 131071 : 524287);
      if (this.isSetFktable_db()) {
         hashCode = hashCode * 8191 + this.fktable_db.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFktable_name() ? 131071 : 524287);
      if (this.isSetFktable_name()) {
         hashCode = hashCode * 8191 + this.fktable_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFkcolumn_name() ? 131071 : 524287);
      if (this.isSetFkcolumn_name()) {
         hashCode = hashCode * 8191 + this.fkcolumn_name.hashCode();
      }

      hashCode = hashCode * 8191 + this.key_seq;
      hashCode = hashCode * 8191 + this.update_rule;
      hashCode = hashCode * 8191 + this.delete_rule;
      hashCode = hashCode * 8191 + (this.isSetFk_name() ? 131071 : 524287);
      if (this.isSetFk_name()) {
         hashCode = hashCode * 8191 + this.fk_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPk_name() ? 131071 : 524287);
      if (this.isSetPk_name()) {
         hashCode = hashCode * 8191 + this.pk_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.enable_cstr ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.validate_cstr ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.rely_cstr ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(SQLForeignKey other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPktable_db(), other.isSetPktable_db());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPktable_db()) {
               lastComparison = TBaseHelper.compareTo(this.pktable_db, other.pktable_db);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetPktable_name(), other.isSetPktable_name());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetPktable_name()) {
                  lastComparison = TBaseHelper.compareTo(this.pktable_name, other.pktable_name);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPkcolumn_name(), other.isSetPkcolumn_name());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPkcolumn_name()) {
                     lastComparison = TBaseHelper.compareTo(this.pkcolumn_name, other.pkcolumn_name);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetFktable_db(), other.isSetFktable_db());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetFktable_db()) {
                        lastComparison = TBaseHelper.compareTo(this.fktable_db, other.fktable_db);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetFktable_name(), other.isSetFktable_name());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetFktable_name()) {
                           lastComparison = TBaseHelper.compareTo(this.fktable_name, other.fktable_name);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetFkcolumn_name(), other.isSetFkcolumn_name());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetFkcolumn_name()) {
                              lastComparison = TBaseHelper.compareTo(this.fkcolumn_name, other.fkcolumn_name);
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

                              lastComparison = Boolean.compare(this.isSetUpdate_rule(), other.isSetUpdate_rule());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetUpdate_rule()) {
                                    lastComparison = TBaseHelper.compareTo(this.update_rule, other.update_rule);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetDelete_rule(), other.isSetDelete_rule());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetDelete_rule()) {
                                       lastComparison = TBaseHelper.compareTo(this.delete_rule, other.delete_rule);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetFk_name(), other.isSetFk_name());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetFk_name()) {
                                          lastComparison = TBaseHelper.compareTo(this.fk_name, other.fk_name);
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
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return SQLForeignKey._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SQLForeignKey(");
      boolean first = true;
      sb.append("pktable_db:");
      if (this.pktable_db == null) {
         sb.append("null");
      } else {
         sb.append(this.pktable_db);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("pktable_name:");
      if (this.pktable_name == null) {
         sb.append("null");
      } else {
         sb.append(this.pktable_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("pkcolumn_name:");
      if (this.pkcolumn_name == null) {
         sb.append("null");
      } else {
         sb.append(this.pkcolumn_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("fktable_db:");
      if (this.fktable_db == null) {
         sb.append("null");
      } else {
         sb.append(this.fktable_db);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("fktable_name:");
      if (this.fktable_name == null) {
         sb.append("null");
      } else {
         sb.append(this.fktable_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("fkcolumn_name:");
      if (this.fkcolumn_name == null) {
         sb.append("null");
      } else {
         sb.append(this.fkcolumn_name);
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

      sb.append("update_rule:");
      sb.append(this.update_rule);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("delete_rule:");
      sb.append(this.delete_rule);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("fk_name:");
      if (this.fk_name == null) {
         sb.append("null");
      } else {
         sb.append(this.fk_name);
      }

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
      tmpMap.put(SQLForeignKey._Fields.PKTABLE_DB, new FieldMetaData("pktable_db", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.PKTABLE_NAME, new FieldMetaData("pktable_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.PKCOLUMN_NAME, new FieldMetaData("pkcolumn_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.FKTABLE_DB, new FieldMetaData("fktable_db", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.FKTABLE_NAME, new FieldMetaData("fktable_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.FKCOLUMN_NAME, new FieldMetaData("fkcolumn_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.KEY_SEQ, new FieldMetaData("key_seq", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(SQLForeignKey._Fields.UPDATE_RULE, new FieldMetaData("update_rule", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(SQLForeignKey._Fields.DELETE_RULE, new FieldMetaData("delete_rule", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(SQLForeignKey._Fields.FK_NAME, new FieldMetaData("fk_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.PK_NAME, new FieldMetaData("pk_name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(SQLForeignKey._Fields.ENABLE_CSTR, new FieldMetaData("enable_cstr", (byte)3, new FieldValueMetaData((byte)2)));
      tmpMap.put(SQLForeignKey._Fields.VALIDATE_CSTR, new FieldMetaData("validate_cstr", (byte)3, new FieldValueMetaData((byte)2)));
      tmpMap.put(SQLForeignKey._Fields.RELY_CSTR, new FieldMetaData("rely_cstr", (byte)3, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SQLForeignKey.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PKTABLE_DB((short)1, "pktable_db"),
      PKTABLE_NAME((short)2, "pktable_name"),
      PKCOLUMN_NAME((short)3, "pkcolumn_name"),
      FKTABLE_DB((short)4, "fktable_db"),
      FKTABLE_NAME((short)5, "fktable_name"),
      FKCOLUMN_NAME((short)6, "fkcolumn_name"),
      KEY_SEQ((short)7, "key_seq"),
      UPDATE_RULE((short)8, "update_rule"),
      DELETE_RULE((short)9, "delete_rule"),
      FK_NAME((short)10, "fk_name"),
      PK_NAME((short)11, "pk_name"),
      ENABLE_CSTR((short)12, "enable_cstr"),
      VALIDATE_CSTR((short)13, "validate_cstr"),
      RELY_CSTR((short)14, "rely_cstr");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PKTABLE_DB;
            case 2:
               return PKTABLE_NAME;
            case 3:
               return PKCOLUMN_NAME;
            case 4:
               return FKTABLE_DB;
            case 5:
               return FKTABLE_NAME;
            case 6:
               return FKCOLUMN_NAME;
            case 7:
               return KEY_SEQ;
            case 8:
               return UPDATE_RULE;
            case 9:
               return DELETE_RULE;
            case 10:
               return FK_NAME;
            case 11:
               return PK_NAME;
            case 12:
               return ENABLE_CSTR;
            case 13:
               return VALIDATE_CSTR;
            case 14:
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

   private static class SQLForeignKeyStandardSchemeFactory implements SchemeFactory {
      private SQLForeignKeyStandardSchemeFactory() {
      }

      public SQLForeignKeyStandardScheme getScheme() {
         return new SQLForeignKeyStandardScheme();
      }
   }

   private static class SQLForeignKeyStandardScheme extends StandardScheme {
      private SQLForeignKeyStandardScheme() {
      }

      public void read(TProtocol iprot, SQLForeignKey struct) throws TException {
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
                     struct.pktable_db = iprot.readString();
                     struct.setPktable_dbIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.pktable_name = iprot.readString();
                     struct.setPktable_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.pkcolumn_name = iprot.readString();
                     struct.setPkcolumn_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.fktable_db = iprot.readString();
                     struct.setFktable_dbIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.fktable_name = iprot.readString();
                     struct.setFktable_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.fkcolumn_name = iprot.readString();
                     struct.setFkcolumn_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 8) {
                     struct.key_seq = iprot.readI32();
                     struct.setKey_seqIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 8) {
                     struct.update_rule = iprot.readI32();
                     struct.setUpdate_ruleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 8) {
                     struct.delete_rule = iprot.readI32();
                     struct.setDelete_ruleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 11) {
                     struct.fk_name = iprot.readString();
                     struct.setFk_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 11:
                  if (schemeField.type == 11) {
                     struct.pk_name = iprot.readString();
                     struct.setPk_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 12:
                  if (schemeField.type == 2) {
                     struct.enable_cstr = iprot.readBool();
                     struct.setEnable_cstrIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 13:
                  if (schemeField.type == 2) {
                     struct.validate_cstr = iprot.readBool();
                     struct.setValidate_cstrIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 14:
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

      public void write(TProtocol oprot, SQLForeignKey struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SQLForeignKey.STRUCT_DESC);
         if (struct.pktable_db != null) {
            oprot.writeFieldBegin(SQLForeignKey.PKTABLE_DB_FIELD_DESC);
            oprot.writeString(struct.pktable_db);
            oprot.writeFieldEnd();
         }

         if (struct.pktable_name != null) {
            oprot.writeFieldBegin(SQLForeignKey.PKTABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.pktable_name);
            oprot.writeFieldEnd();
         }

         if (struct.pkcolumn_name != null) {
            oprot.writeFieldBegin(SQLForeignKey.PKCOLUMN_NAME_FIELD_DESC);
            oprot.writeString(struct.pkcolumn_name);
            oprot.writeFieldEnd();
         }

         if (struct.fktable_db != null) {
            oprot.writeFieldBegin(SQLForeignKey.FKTABLE_DB_FIELD_DESC);
            oprot.writeString(struct.fktable_db);
            oprot.writeFieldEnd();
         }

         if (struct.fktable_name != null) {
            oprot.writeFieldBegin(SQLForeignKey.FKTABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.fktable_name);
            oprot.writeFieldEnd();
         }

         if (struct.fkcolumn_name != null) {
            oprot.writeFieldBegin(SQLForeignKey.FKCOLUMN_NAME_FIELD_DESC);
            oprot.writeString(struct.fkcolumn_name);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(SQLForeignKey.KEY_SEQ_FIELD_DESC);
         oprot.writeI32(struct.key_seq);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SQLForeignKey.UPDATE_RULE_FIELD_DESC);
         oprot.writeI32(struct.update_rule);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SQLForeignKey.DELETE_RULE_FIELD_DESC);
         oprot.writeI32(struct.delete_rule);
         oprot.writeFieldEnd();
         if (struct.fk_name != null) {
            oprot.writeFieldBegin(SQLForeignKey.FK_NAME_FIELD_DESC);
            oprot.writeString(struct.fk_name);
            oprot.writeFieldEnd();
         }

         if (struct.pk_name != null) {
            oprot.writeFieldBegin(SQLForeignKey.PK_NAME_FIELD_DESC);
            oprot.writeString(struct.pk_name);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(SQLForeignKey.ENABLE_CSTR_FIELD_DESC);
         oprot.writeBool(struct.enable_cstr);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SQLForeignKey.VALIDATE_CSTR_FIELD_DESC);
         oprot.writeBool(struct.validate_cstr);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(SQLForeignKey.RELY_CSTR_FIELD_DESC);
         oprot.writeBool(struct.rely_cstr);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SQLForeignKeyTupleSchemeFactory implements SchemeFactory {
      private SQLForeignKeyTupleSchemeFactory() {
      }

      public SQLForeignKeyTupleScheme getScheme() {
         return new SQLForeignKeyTupleScheme();
      }
   }

   private static class SQLForeignKeyTupleScheme extends TupleScheme {
      private SQLForeignKeyTupleScheme() {
      }

      public void write(TProtocol prot, SQLForeignKey struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetPktable_db()) {
            optionals.set(0);
         }

         if (struct.isSetPktable_name()) {
            optionals.set(1);
         }

         if (struct.isSetPkcolumn_name()) {
            optionals.set(2);
         }

         if (struct.isSetFktable_db()) {
            optionals.set(3);
         }

         if (struct.isSetFktable_name()) {
            optionals.set(4);
         }

         if (struct.isSetFkcolumn_name()) {
            optionals.set(5);
         }

         if (struct.isSetKey_seq()) {
            optionals.set(6);
         }

         if (struct.isSetUpdate_rule()) {
            optionals.set(7);
         }

         if (struct.isSetDelete_rule()) {
            optionals.set(8);
         }

         if (struct.isSetFk_name()) {
            optionals.set(9);
         }

         if (struct.isSetPk_name()) {
            optionals.set(10);
         }

         if (struct.isSetEnable_cstr()) {
            optionals.set(11);
         }

         if (struct.isSetValidate_cstr()) {
            optionals.set(12);
         }

         if (struct.isSetRely_cstr()) {
            optionals.set(13);
         }

         oprot.writeBitSet(optionals, 14);
         if (struct.isSetPktable_db()) {
            oprot.writeString(struct.pktable_db);
         }

         if (struct.isSetPktable_name()) {
            oprot.writeString(struct.pktable_name);
         }

         if (struct.isSetPkcolumn_name()) {
            oprot.writeString(struct.pkcolumn_name);
         }

         if (struct.isSetFktable_db()) {
            oprot.writeString(struct.fktable_db);
         }

         if (struct.isSetFktable_name()) {
            oprot.writeString(struct.fktable_name);
         }

         if (struct.isSetFkcolumn_name()) {
            oprot.writeString(struct.fkcolumn_name);
         }

         if (struct.isSetKey_seq()) {
            oprot.writeI32(struct.key_seq);
         }

         if (struct.isSetUpdate_rule()) {
            oprot.writeI32(struct.update_rule);
         }

         if (struct.isSetDelete_rule()) {
            oprot.writeI32(struct.delete_rule);
         }

         if (struct.isSetFk_name()) {
            oprot.writeString(struct.fk_name);
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

      public void read(TProtocol prot, SQLForeignKey struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(14);
         if (incoming.get(0)) {
            struct.pktable_db = iprot.readString();
            struct.setPktable_dbIsSet(true);
         }

         if (incoming.get(1)) {
            struct.pktable_name = iprot.readString();
            struct.setPktable_nameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.pkcolumn_name = iprot.readString();
            struct.setPkcolumn_nameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.fktable_db = iprot.readString();
            struct.setFktable_dbIsSet(true);
         }

         if (incoming.get(4)) {
            struct.fktable_name = iprot.readString();
            struct.setFktable_nameIsSet(true);
         }

         if (incoming.get(5)) {
            struct.fkcolumn_name = iprot.readString();
            struct.setFkcolumn_nameIsSet(true);
         }

         if (incoming.get(6)) {
            struct.key_seq = iprot.readI32();
            struct.setKey_seqIsSet(true);
         }

         if (incoming.get(7)) {
            struct.update_rule = iprot.readI32();
            struct.setUpdate_ruleIsSet(true);
         }

         if (incoming.get(8)) {
            struct.delete_rule = iprot.readI32();
            struct.setDelete_ruleIsSet(true);
         }

         if (incoming.get(9)) {
            struct.fk_name = iprot.readString();
            struct.setFk_nameIsSet(true);
         }

         if (incoming.get(10)) {
            struct.pk_name = iprot.readString();
            struct.setPk_nameIsSet(true);
         }

         if (incoming.get(11)) {
            struct.enable_cstr = iprot.readBool();
            struct.setEnable_cstrIsSet(true);
         }

         if (incoming.get(12)) {
            struct.validate_cstr = iprot.readBool();
            struct.setValidate_cstrIsSet(true);
         }

         if (incoming.get(13)) {
            struct.rely_cstr = iprot.readBool();
            struct.setRely_cstrIsSet(true);
         }

      }
   }
}
