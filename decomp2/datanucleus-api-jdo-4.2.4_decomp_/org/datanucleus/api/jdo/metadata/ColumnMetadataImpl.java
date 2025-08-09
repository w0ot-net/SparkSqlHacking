package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ColumnMetadata;
import org.datanucleus.metadata.ColumnMetaData;

public class ColumnMetadataImpl extends AbstractMetadataImpl implements ColumnMetadata {
   public ColumnMetadataImpl(ColumnMetaData internal) {
      super(internal);
   }

   public ColumnMetaData getInternal() {
      return (ColumnMetaData)this.internalMD;
   }

   public Boolean getAllowsNull() {
      return this.getInternal().getAllowsNull();
   }

   public String getDefaultValue() {
      return this.getInternal().getDefaultValue();
   }

   public String getInsertValue() {
      return this.getInternal().getInsertValue();
   }

   public String getJDBCType() {
      return this.getInternal().getJdbcTypeName();
   }

   public Integer getLength() {
      return this.getInternal().getLength();
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public Integer getPosition() {
      return this.getInternal().getPosition();
   }

   public String getSQLType() {
      return this.getInternal().getSqlType();
   }

   public Integer getScale() {
      return this.getInternal().getScale();
   }

   public String getTarget() {
      return this.getInternal().getTarget();
   }

   public String getTargetField() {
      return this.getInternal().getTargetMember();
   }

   public ColumnMetadata setAllowsNull(boolean flag) {
      this.getInternal().setAllowsNull(flag);
      return this;
   }

   public ColumnMetadata setDefaultValue(String val) {
      this.getInternal().setDefaultValue(val);
      return this;
   }

   public ColumnMetadata setInsertValue(String val) {
      this.getInternal().setInsertValue(val);
      return this;
   }

   public ColumnMetadata setJDBCType(String type) {
      this.getInternal().setJdbcType(type);
      return this;
   }

   public ColumnMetadata setLength(int len) {
      this.getInternal().setLength(len);
      return this;
   }

   public ColumnMetadata setName(String name) {
      this.getInternal().setName(name);
      return this;
   }

   public ColumnMetadata setPosition(int pos) {
      this.getInternal().setPosition(pos);
      return this;
   }

   public ColumnMetadata setSQLType(String type) {
      this.getInternal().setSqlType(type);
      return this;
   }

   public ColumnMetadata setScale(int scale) {
      this.getInternal().setScale(scale);
      return this;
   }

   public ColumnMetadata setTarget(String tgt) {
      this.getInternal().setTarget(tgt);
      return this;
   }

   public ColumnMetadata setTargetField(String tgt) {
      this.getInternal().setTargetMember(tgt);
      return this;
   }
}
