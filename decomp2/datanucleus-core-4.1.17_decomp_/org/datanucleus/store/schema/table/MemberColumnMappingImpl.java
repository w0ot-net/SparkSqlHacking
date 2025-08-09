package org.datanucleus.store.schema.table;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.util.StringUtils;

public class MemberColumnMappingImpl implements MemberColumnMapping {
   protected AbstractMemberMetaData mmd;
   protected TypeConverter typeConverter;
   protected Column[] columns;

   public MemberColumnMappingImpl(AbstractMemberMetaData mmd, Column col) {
      this.mmd = mmd;
      this.columns = new Column[]{col};
   }

   public MemberColumnMappingImpl(AbstractMemberMetaData mmd, Column[] cols, TypeConverter typeConv) {
      this.mmd = mmd;
      this.columns = cols;
      this.typeConverter = typeConv;
   }

   public void setTypeConverter(TypeConverter typeConv) {
      this.typeConverter = typeConv;
   }

   public AbstractMemberMetaData getMemberMetaData() {
      return this.mmd;
   }

   public Column getColumn(int position) {
      return position >= this.getNumberOfColumns() ? null : this.columns[position];
   }

   public Column[] getColumns() {
      return this.columns;
   }

   public int getNumberOfColumns() {
      return this.columns != null ? this.columns.length : 0;
   }

   public TypeConverter getTypeConverter() {
      return this.typeConverter;
   }

   public String toString() {
      return "Member: " + this.mmd.getFullFieldName() + " converter=" + this.typeConverter + " columns=" + StringUtils.objectArrayToString(this.columns);
   }
}
