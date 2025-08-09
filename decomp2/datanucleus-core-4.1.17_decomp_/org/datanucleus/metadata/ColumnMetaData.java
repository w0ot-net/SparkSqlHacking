package org.datanucleus.metadata;

import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ColumnMetaData extends MetaData {
   private static final long serialVersionUID = -751430163728764079L;
   protected String name;
   protected String target;
   protected String targetMember;
   protected JdbcType jdbcType;
   protected String sqlType;
   protected Integer length;
   protected Integer scale;
   protected Boolean allowsNull;
   protected String defaultValue;
   protected String insertValue;
   protected boolean insertable = true;
   protected boolean updateable = true;
   protected boolean unique = false;
   protected String columnDdl = null;
   protected Integer position = null;

   public ColumnMetaData(ColumnMetaData colmd) {
      super((MetaData)null, colmd);
      this.name = colmd.getName();
      this.target = colmd.getTarget();
      this.targetMember = colmd.getTargetMember();
      this.jdbcType = colmd.getJdbcType();
      this.sqlType = colmd.getSqlType();
      this.length = colmd.getLength();
      this.scale = colmd.getScale();
      this.allowsNull = colmd.allowsNull;
      this.defaultValue = colmd.getDefaultValue();
      this.insertValue = colmd.getInsertValue();
      this.insertable = colmd.getInsertable();
      this.updateable = colmd.getUpdateable();
      this.unique = colmd.getUnique();
      this.position = colmd.getPosition();
   }

   public ColumnMetaData() {
   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public ColumnMetaData setDefaultValue(String defaultValue) {
      this.defaultValue = StringUtils.isWhitespace(defaultValue) ? null : defaultValue;
      return this;
   }

   public String getColumnDdl() {
      return this.columnDdl;
   }

   public void setColumnDdl(String columnDdl) {
      this.columnDdl = columnDdl;
   }

   public boolean getInsertable() {
      return this.insertable;
   }

   public ColumnMetaData setInsertable(boolean insertable) {
      this.insertable = insertable;
      return this;
   }

   public ColumnMetaData setInsertable(String insertable) {
      if (!StringUtils.isWhitespace(insertable)) {
         this.insertable = Boolean.parseBoolean(insertable);
      }

      return this;
   }

   public String getInsertValue() {
      return this.insertValue;
   }

   public ColumnMetaData setInsertValue(String insertValue) {
      this.insertValue = StringUtils.isWhitespace(insertValue) ? null : insertValue;
      return this;
   }

   public JdbcType getJdbcType() {
      return this.jdbcType;
   }

   public String getJdbcTypeName() {
      return this.jdbcType != null ? this.jdbcType.toString() : null;
   }

   public ColumnMetaData setJdbcType(JdbcType type) {
      this.jdbcType = type;
      return this;
   }

   public ColumnMetaData setJdbcType(String jdbcTypeName) {
      if (StringUtils.isWhitespace(jdbcTypeName)) {
         this.jdbcType = null;
      } else {
         try {
            this.jdbcType = (JdbcType)Enum.valueOf(JdbcType.class, jdbcTypeName.toUpperCase());
         } catch (IllegalArgumentException var3) {
            NucleusLogger.METADATA.warn("Metadata has jdbc-type of " + this.jdbcType + " yet this is not valid. Ignored");
         }
      }

      return this;
   }

   public Integer getLength() {
      return this.length;
   }

   public ColumnMetaData setLength(Integer length) {
      if (length != null && length > 0) {
         this.length = length;
      }

      return this;
   }

   public ColumnMetaData setLength(String length) {
      if (!StringUtils.isWhitespace(length)) {
         try {
            int val = Integer.parseInt(length);
            if (val > 0) {
               this.length = val;
            }
         } catch (NumberFormatException var3) {
         }
      }

      return this;
   }

   public String getName() {
      return this.name;
   }

   public ColumnMetaData setName(String name) {
      this.name = StringUtils.isWhitespace(name) ? null : name;
      return this;
   }

   public Integer getScale() {
      return this.scale;
   }

   public ColumnMetaData setScale(Integer scale) {
      if (scale != null && scale > 0) {
         this.scale = scale;
      }

      return this;
   }

   public ColumnMetaData setScale(String scale) {
      if (!StringUtils.isWhitespace(scale)) {
         try {
            int val = Integer.parseInt(scale);
            if (val > 0) {
               this.scale = val;
            }
         } catch (NumberFormatException var3) {
         }
      }

      return this;
   }

   public String getSqlType() {
      return this.sqlType;
   }

   public ColumnMetaData setSqlType(String sqlType) {
      this.sqlType = StringUtils.isWhitespace(sqlType) ? null : sqlType;
      return this;
   }

   public String getTarget() {
      return this.target;
   }

   public ColumnMetaData setTarget(String target) {
      this.target = StringUtils.isWhitespace(target) ? null : target;
      return this;
   }

   public String getTargetMember() {
      return this.targetMember;
   }

   public ColumnMetaData setTargetMember(String targetMember) {
      this.targetMember = StringUtils.isWhitespace(targetMember) ? null : targetMember;
      return this;
   }

   public Integer getPosition() {
      if (this.hasExtension("index")) {
         try {
            return Integer.valueOf(this.getValueForExtension("index"));
         } catch (NumberFormatException var2) {
         }
      }

      return this.position;
   }

   public ColumnMetaData setPosition(int pos) {
      if (pos >= 0) {
         this.position = pos;
      } else {
         this.position = null;
      }

      return this;
   }

   public ColumnMetaData setPosition(String pos) {
      if (!StringUtils.isWhitespace(pos)) {
         try {
            int val = Integer.parseInt(pos);
            if (val >= 0) {
               this.position = val;
            }
         } catch (NumberFormatException var3) {
         }
      }

      return this;
   }

   public boolean getUnique() {
      return this.unique;
   }

   public ColumnMetaData setUnique(boolean unique) {
      this.unique = unique;
      return this;
   }

   public ColumnMetaData setUnique(String unique) {
      if (!StringUtils.isWhitespace(unique)) {
         this.unique = Boolean.parseBoolean(unique);
      }

      return this;
   }

   public boolean getUpdateable() {
      return this.updateable;
   }

   public ColumnMetaData setUpdateable(boolean updateable) {
      this.updateable = updateable;
      return this;
   }

   public ColumnMetaData setUpdateable(String updateable) {
      if (!StringUtils.isWhitespace(updateable)) {
         this.updateable = Boolean.parseBoolean(updateable);
      }

      return this;
   }

   public boolean isAllowsNull() {
      return this.allowsNull == null ? false : this.allowsNull;
   }

   public Boolean getAllowsNull() {
      return this.allowsNull;
   }

   public ColumnMetaData setAllowsNull(Boolean allowsNull) {
      this.allowsNull = allowsNull;
      return this;
   }

   public ColumnMetaData setAllowsNull(String allowsNull) {
      if (!StringUtils.isWhitespace(allowsNull)) {
         this.allowsNull = Boolean.parseBoolean(allowsNull);
      }

      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<column");
      if (this.name != null) {
         sb.append(" name=\"" + this.name + "\"");
      }

      if (this.target != null) {
         sb.append(" target=\"" + this.target + "\"");
      }

      if (this.targetMember != null) {
         sb.append(" target-field=\"" + this.targetMember + "\"");
      }

      if (this.jdbcType != null) {
         sb.append(" jdbc-type=\"" + this.jdbcType + "\"");
      }

      if (this.sqlType != null) {
         sb.append(" sql-type=\"" + this.sqlType + "\"");
      }

      if (this.allowsNull != null) {
         sb.append(" allows-null=\"" + this.allowsNull + "\"");
      }

      if (this.length != null) {
         sb.append(" length=\"" + this.length + "\"");
      }

      if (this.scale != null) {
         sb.append(" scale=\"" + this.scale + "\"");
      }

      if (this.defaultValue != null) {
         sb.append(" default-value=\"" + this.defaultValue + "\"");
      }

      if (this.insertValue != null) {
         sb.append(" insert-value=\"" + this.insertValue + "\"");
      }

      if (this.position != null) {
         sb.append(" position=\"" + this.position + "\"");
      }

      if (this.extensions != null && this.extensions.size() > 0) {
         sb.append(">\n");
         sb.append(super.toString(prefix + indent, indent));
         sb.append(prefix).append("</column>\n");
      } else {
         sb.append("/>\n");
      }

      return sb.toString();
   }
}
