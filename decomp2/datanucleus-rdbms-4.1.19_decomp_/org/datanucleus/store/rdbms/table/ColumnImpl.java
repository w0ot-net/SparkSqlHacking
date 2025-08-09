package org.datanucleus.store.rdbms.table;

import java.util.StringTokenizer;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.exceptions.ColumnDefinitionException;
import org.datanucleus.store.rdbms.exceptions.IncompatibleDataTypeException;
import org.datanucleus.store.rdbms.exceptions.WrongPrecisionException;
import org.datanucleus.store.rdbms.exceptions.WrongScaleException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.schema.RDBMSColumnInfo;
import org.datanucleus.store.rdbms.schema.SQLTypeInfo;
import org.datanucleus.store.schema.naming.ColumnType;
import org.datanucleus.store.schema.table.MemberColumnMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ColumnImpl implements Column {
   private static final byte PK = 1;
   private static final byte NULLABLE = 2;
   private static final byte UNIQUE = 4;
   private static final byte DEFAULTABLE = 8;
   private static final byte IDENTITY = 16;
   protected DatastoreIdentifier identifier;
   protected ColumnMetaData columnMetaData;
   protected final Table table;
   protected DatastoreMapping datastoreMapping = null;
   protected final String storedJavaType;
   protected SQLTypeInfo typeInfo;
   protected String constraints;
   protected byte flags;
   protected Object defaultValue;
   protected String[] wrapperFunction;

   public ColumnImpl(Table table, String javaType, DatastoreIdentifier identifier, ColumnMetaData colmd) {
      this.table = table;
      this.storedJavaType = javaType;
      this.typeInfo = null;
      this.constraints = null;
      this.flags = 0;
      this.setIdentifier(identifier);
      if (colmd == null) {
         this.columnMetaData = new ColumnMetaData();
      } else {
         this.columnMetaData = colmd;
      }

      if (this.columnMetaData.getAllowsNull() != null && this.columnMetaData.isAllowsNull()) {
         this.setNullable(true);
      }

      if (this.columnMetaData.getUnique()) {
         this.setUnique(true);
      }

      this.wrapperFunction = new String[3];
      this.wrapperFunction[0] = "?";
      this.wrapperFunction[1] = "?";
      this.wrapperFunction[2] = "?";
   }

   public String getName() {
      return this.identifier.toString();
   }

   public MemberColumnMapping getMemberColumnMapping() {
      throw new UnsupportedOperationException("Not supported on this Column");
   }

   public ColumnType getColumnType() {
      throw new UnsupportedOperationException("Not supported on this Column");
   }

   public Column setJdbcType(JdbcType jdbcType) {
      throw new UnsupportedOperationException("Not supported on this Column");
   }

   public JdbcType getJdbcType() {
      return JdbcType.getEnumByValue(this.typeInfo.getDataType());
   }

   public Column setTypeName(String type) {
      throw new UnsupportedOperationException("Not supported on this Column");
   }

   public String getTypeName() {
      return this.typeInfo.getTypeName();
   }

   public Column setPosition(int pos) {
      throw new UnsupportedOperationException("Not supported on this Column");
   }

   public int getPosition() {
      throw new UnsupportedOperationException("Not supported on this Column");
   }

   public boolean isUnlimitedLength() {
      if (this.columnMetaData.getJdbcType() == null || this.columnMetaData.getJdbcType() != JdbcType.BLOB && this.columnMetaData.getJdbcType() != JdbcType.CLOB) {
         return this.columnMetaData.getSqlType() != null && this.columnMetaData.getSqlType().toLowerCase().indexOf("lob") > 0;
      } else {
         return true;
      }
   }

   public DatastoreIdentifier getIdentifier() {
      return this.identifier;
   }

   public void setIdentifier(DatastoreIdentifier identifier) {
      this.identifier = identifier;
   }

   public Table getTable() {
      return this.table;
   }

   public DatastoreMapping getDatastoreMapping() {
      return this.datastoreMapping;
   }

   public void setDatastoreMapping(DatastoreMapping mapping) {
      this.datastoreMapping = mapping;
   }

   public JavaTypeMapping getJavaTypeMapping() {
      return this.datastoreMapping.getJavaTypeMapping();
   }

   public String getStoredJavaType() {
      return this.storedJavaType;
   }

   public final Column setTypeInfo(SQLTypeInfo typeInfo) {
      if (this.typeInfo == null) {
         this.typeInfo = typeInfo;
      }

      return this;
   }

   public final SQLTypeInfo getTypeInfo() {
      return this.typeInfo;
   }

   public RDBMSStoreManager getStoreManager() {
      return this.table.getStoreManager();
   }

   private int getSQLPrecision() {
      int sqlPrecision = -1;
      if (this.columnMetaData.getLength() != null && this.columnMetaData.getLength() > 0) {
         sqlPrecision = this.columnMetaData.getLength();
      } else if (this.isUnlimitedLength()) {
         int ulpv = this.getStoreManager().getDatastoreAdapter().getUnlimitedLengthPrecisionValue(this.typeInfo);
         if (ulpv > 0) {
            sqlPrecision = ulpv;
         }
      }

      return this.typeInfo.getTypeName().toLowerCase().startsWith("bit") ? sqlPrecision * 8 : sqlPrecision;
   }

   public String getSQLDefinition() {
      StringBuilder def = new StringBuilder(this.identifier.toString());
      if (!StringUtils.isWhitespace(this.columnMetaData.getColumnDdl())) {
         def.append(" ").append(this.columnMetaData.getColumnDdl());
         return def.toString();
      } else {
         StringBuilder typeSpec = new StringBuilder(this.typeInfo.getTypeName());
         DatastoreAdapter adapter = this.getStoreManager().getDatastoreAdapter();
         boolean specifyType = true;
         if (adapter.supportsOption("IdentityColumns") && this.isIdentity() && !adapter.supportsOption("AutoIncrementColumnTypeSpecification")) {
            specifyType = false;
         }

         if (specifyType) {
            if (this.typeInfo.getCreateParams() != null && this.typeInfo.getCreateParams().indexOf(40) >= 0 && this.typeInfo.getTypeName().indexOf(40) < 0) {
               StringTokenizer toks = new StringTokenizer(this.typeInfo.getCreateParams());

               while(toks.hasMoreTokens()) {
                  String tok = toks.nextToken();
                  if (!tok.startsWith("[") || !tok.endsWith("]")) {
                     typeSpec.append(" " + tok);
                  }
               }
            }

            StringBuilder precSpec = new StringBuilder();
            int sqlPrecision = this.getSQLPrecision();
            if (sqlPrecision > 0 && this.typeInfo.isAllowsPrecisionSpec()) {
               precSpec.append(sqlPrecision);
               if (this.columnMetaData.getScale() != null) {
                  precSpec.append("," + this.columnMetaData.getScale());
               }
            } else if (sqlPrecision > 0 && !this.typeInfo.isAllowsPrecisionSpec()) {
               NucleusLogger.DATASTORE_SCHEMA.warn(Localiser.msg("020183", new Object[]{this.toString()}));
            }

            int lParenIdx = typeSpec.toString().indexOf(40);
            int rParenIdx = typeSpec.toString().indexOf(41, lParenIdx);
            if (lParenIdx > 0 && rParenIdx > 0) {
               if (precSpec.length() > 0) {
                  typeSpec.replace(lParenIdx + 1, rParenIdx, precSpec.toString());
               } else if (rParenIdx == lParenIdx + 1) {
                  throw new ColumnDefinitionException(Localiser.msg("020184", new Object[]{this.toString()}));
               }
            } else if (precSpec.length() > 0) {
               typeSpec.append('(');
               typeSpec.append(precSpec.toString());
               typeSpec.append(')');
            }

            def.append(" " + typeSpec.toString());
         }

         if (adapter.supportsOption("ColumnOptions_DefaultBeforeNull") && adapter.supportsOption("ColumnOptions_DefaultKeyword") && this.columnMetaData.getDefaultValue() != null) {
            def.append(" ").append(this.getDefaultDefinition());
         }

         if (this.isIdentity() && this.isPrimaryKey() && adapter.supportsOption("AutoIncrementPkInCreateTableColumnDef")) {
            def.append(" PRIMARY KEY");
         }

         if (!adapter.supportsOption("IdentityColumns") || !this.isIdentity() || adapter.supportsOption("AutoIncrementNullSpecification")) {
            if (!this.isNullable()) {
               if (this.columnMetaData.getDefaultValue() == null || adapter.supportsOption("ColumnOptions_DefaultWithNotNull")) {
                  def.append(" NOT NULL");
               }
            } else if (this.typeInfo.getNullable() == 1 && adapter.supportsOption("ColumnOptions_NullsKeyword")) {
               def.append(" NULL");
            }
         }

         if (!adapter.supportsOption("ColumnOptions_DefaultBeforeNull") && adapter.supportsOption("ColumnOptions_DefaultKeyword") && this.columnMetaData.getDefaultValue() != null) {
            def.append(" ").append(this.getDefaultDefinition());
         }

         if (adapter.supportsOption("CheckInCreateStatements") && this.constraints != null) {
            def.append(" " + this.constraints.toString());
         }

         if (adapter.supportsOption("IdentityColumns") && this.isIdentity()) {
            def.append(" " + adapter.getAutoIncrementKeyword());
         }

         if (this.isUnique() && !adapter.supportsOption("UniqueInEndCreateStatements")) {
            def.append(" UNIQUE");
         }

         return def.toString();
      }
   }

   private String getDefaultDefinition() {
      if (this.columnMetaData.getDefaultValue().equalsIgnoreCase("#NULL")) {
         this.defaultValue = null;
         return "DEFAULT NULL";
      } else if (this.typeInfo.getTypeName().toUpperCase().indexOf("CHAR") < 0 && this.typeInfo.getTypeName().toUpperCase().indexOf("LOB") < 0) {
         if (this.typeInfo.getTypeName().toUpperCase().indexOf("BIT") != 0 || !this.columnMetaData.getDefaultValue().equalsIgnoreCase("true") && !this.columnMetaData.getDefaultValue().equalsIgnoreCase("false")) {
            this.defaultValue = this.columnMetaData.getDefaultValue();
            return "DEFAULT " + this.columnMetaData.getDefaultValue();
         } else {
            this.defaultValue = this.columnMetaData.getDefaultValue();
            return "DEFAULT '" + this.columnMetaData.getDefaultValue() + "'";
         }
      } else {
         this.defaultValue = this.columnMetaData.getDefaultValue();
         return "DEFAULT '" + this.columnMetaData.getDefaultValue() + "'";
      }
   }

   public void initializeColumnInfoFromDatastore(RDBMSColumnInfo ci) {
      String column_default = ci.getColumnDef();
      if (!StringUtils.isWhitespace(column_default)) {
         if (column_default.startsWith("'") && column_default.endsWith("'")) {
            String colDefString = column_default.replace("'", "");
            this.setDefaultable(colDefString);
         } else if (!column_default.equalsIgnoreCase("null")) {
            String columnDef = column_default.replace("'", "").replace("\"", "").replace(")", "").replace("(", "");
            if (!columnDef.equalsIgnoreCase("null")) {
               this.setDefaultable(columnDef);
            }
         }
      }

      try {
         this.setIdentity(this.getStoreManager().getDatastoreAdapter().isIdentityFieldDataType(ci.getColumnDef()));
      } catch (UnsupportedOperationException var4) {
      }

   }

   public void validate(RDBMSColumnInfo ci) {
      if (!this.typeInfo.isCompatibleWith(ci)) {
         throw new IncompatibleDataTypeException(this, this.typeInfo.getDataType(), ci.getDataType());
      } else if (ci.getDataType() != 1111) {
         if (this.table instanceof TableImpl) {
            if (this.typeInfo.isAllowsPrecisionSpec()) {
               int actualPrecision = ci.getColumnSize();
               int actualScale = ci.getDecimalDigits();
               int sqlPrecision = this.getSQLPrecision();
               if (sqlPrecision > 0 && actualPrecision > 0 && sqlPrecision != actualPrecision) {
                  if (this.columnMetaData != null && this.columnMetaData.getParent() != null && this.columnMetaData.getParent() instanceof AbstractMemberMetaData) {
                     throw new WrongPrecisionException(this.toString(), sqlPrecision, actualPrecision, ((AbstractMemberMetaData)this.columnMetaData.getParent()).getFullFieldName());
                  }

                  throw new WrongPrecisionException(this.toString(), sqlPrecision, actualPrecision);
               }

               if (this.columnMetaData.getScale() != null && actualScale >= 0 && this.columnMetaData.getScale() != actualScale) {
                  if (this.columnMetaData.getParent() != null && this.columnMetaData.getParent() instanceof AbstractMemberMetaData) {
                     throw new WrongScaleException(this.toString(), this.columnMetaData.getScale(), actualScale, ((AbstractMemberMetaData)this.columnMetaData.getParent()).getFullFieldName());
                  }

                  throw new WrongScaleException(this.toString(), this.columnMetaData.getScale(), actualScale);
               }
            }

            String actualIsNullable = ci.getIsNullable();
            if (actualIsNullable.length() > 0) {
               switch (Character.toUpperCase(actualIsNullable.charAt(0))) {
                  case 'N':
                  default:
                     break;
                  case 'Y':
                     if (!this.isNullable()) {
                        NucleusLogger.DATASTORE.warn(Localiser.msg("020025", new Object[]{this}));
                     }
               }
            }

            try {
               if (this.isIdentity() != this.getStoreManager().getDatastoreAdapter().isIdentityFieldDataType(ci.getColumnDef())) {
                  if (this.isIdentity()) {
                     throw (new NucleusException("Expected an auto increment column (" + this.getIdentifier() + ") in the database, but it is not")).setFatal();
                  }

                  throw (new NucleusException("According to the user metadata, the column (" + this.getIdentifier() + ") is not auto incremented, but the database says it is.")).setFatal();
               }
            } catch (UnsupportedOperationException var5) {
            }
         }

      }
   }

   public final Column setConstraints(String constraints) {
      this.constraints = constraints;
      return this;
   }

   public final Column setPrimaryKey() {
      this.flags = (byte)(this.flags | 1);
      this.flags &= -3;
      return this;
   }

   public final Column setNullable(boolean flag) {
      if (flag) {
         this.flags = (byte)(this.flags | 2);
      } else {
         this.flags &= -3;
      }

      return this;
   }

   public final Column setDefaultable(Object defaultValue) {
      if (!this.getStoreManager().getBooleanProperty("datanucleus.rdbms.useColumnDefaultWhenNull")) {
         return this;
      } else {
         this.flags = (byte)(this.flags | 8);
         this.defaultValue = defaultValue;
         return this;
      }
   }

   public final Column setUnique(boolean flag) {
      if (flag) {
         this.flags = (byte)(this.flags | 4);
      } else {
         this.flags &= -5;
      }

      return this;
   }

   public Column setIdentity(boolean identity) {
      if (identity) {
         this.flags = (byte)(this.flags | 16);
      } else {
         this.flags &= -17;
      }

      return this;
   }

   public final boolean isPrimaryKey() {
      return (this.flags & 1) != 0;
   }

   public final boolean isNullable() {
      return (this.flags & 2) != 0;
   }

   public final boolean isDefaultable() {
      return (this.flags & 8) != 0;
   }

   public final boolean isUnique() {
      return (this.flags & 4) != 0;
   }

   public boolean isIdentity() {
      return (this.flags & 16) != 0;
   }

   public String applySelectFunction(String replacementValue) {
      if (replacementValue == null) {
         return this.wrapperFunction[0];
      } else {
         return this.wrapperFunction[0] != null ? this.wrapperFunction[0].replace("?", replacementValue) : replacementValue;
      }
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public final ColumnMetaData getColumnMetaData() {
      return this.columnMetaData;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.columnMetaData != null && this.columnMetaData.getParent() instanceof AbstractMemberMetaData ? (AbstractMemberMetaData)this.columnMetaData.getParent() : null;
   }

   public Column setColumnMetaData(ColumnMetaData colmd) {
      if (colmd == null) {
         return this;
      } else {
         if (colmd.getJdbcType() != null) {
            this.columnMetaData.setJdbcType(colmd.getJdbcType());
         }

         if (colmd.getSqlType() != null) {
            this.columnMetaData.setSqlType(colmd.getSqlType());
         }

         if (colmd.getName() != null) {
            this.columnMetaData.setName(colmd.getName());
         }

         if (colmd.getAllowsNull() != null) {
            this.columnMetaData.setAllowsNull(colmd.isAllowsNull());
         }

         if (colmd.getLength() != null) {
            this.columnMetaData.setLength(colmd.getLength());
         }

         if (colmd.getScale() != null) {
            this.columnMetaData.setScale(colmd.getScale());
         }

         if (colmd.getAllowsNull() != null && colmd.isAllowsNull()) {
            this.setNullable(true);
         }

         if (colmd.getUnique()) {
            this.setUnique(true);
         }

         return this;
      }
   }

   public String getConstraints() {
      return this.constraints;
   }

   public final void checkPrimitive() throws ColumnDefinitionException {
   }

   public final void checkInteger() throws ColumnDefinitionException {
   }

   public final void checkDecimal() throws ColumnDefinitionException {
   }

   public final void checkString() throws ColumnDefinitionException {
      if (this.columnMetaData.getJdbcType() == null) {
         this.columnMetaData.setJdbcType(JdbcType.VARCHAR);
      }

      if (this.columnMetaData.getLength() == null) {
         this.columnMetaData.setLength(this.getStoreManager().getIntProperty("datanucleus.rdbms.stringDefaultLength"));
      }

   }

   public void copyConfigurationTo(Column colIn) {
      ColumnImpl col = (ColumnImpl)colIn;
      col.typeInfo = this.typeInfo;
      col.flags |= this.flags;
      col.flags &= -2;
      col.flags &= -5;
      col.flags &= -3;
      col.flags &= -17;
      col.flags &= -9;
      col.defaultValue = this.defaultValue;
      col.wrapperFunction = this.wrapperFunction;
      if (this.columnMetaData.getJdbcType() != null) {
         col.columnMetaData.setJdbcType(this.columnMetaData.getJdbcType());
      }

      if (this.columnMetaData.getSqlType() != null) {
         col.columnMetaData.setSqlType(this.columnMetaData.getSqlType());
      }

      if (this.columnMetaData.getLength() != null) {
         col.getColumnMetaData().setLength(this.columnMetaData.getLength());
      }

      if (this.columnMetaData.getScale() != null) {
         col.getColumnMetaData().setScale(this.getColumnMetaData().getScale());
      }

   }

   public void setWrapperFunction(String wrapperFunction, int wrapperMode) {
      if (wrapperFunction != null && wrapperMode == 0 && wrapperFunction.indexOf("?") < 0) {
         throw new NucleusUserException("Wrapping function must have one ? (question mark). e.g. SQRT(?)");
      } else {
         this.wrapperFunction[wrapperMode] = wrapperFunction;
      }
   }

   public String getWrapperFunction(int wrapperMode) {
      return this.wrapperFunction[wrapperMode];
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ColumnImpl)) {
         return false;
      } else {
         ColumnImpl col = (ColumnImpl)obj;
         return this.table.equals(col.table) && this.identifier.equals(col.identifier);
      }
   }

   public int hashCode() {
      return this.table.hashCode() ^ this.identifier.hashCode();
   }

   public String toString() {
      return this.table.toString() + "." + this.identifier;
   }
}
