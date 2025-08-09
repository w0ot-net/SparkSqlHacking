package org.datanucleus.store.schema.table;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.types.converters.TypeConverter;

public interface MemberColumnMapping {
   AbstractMemberMetaData getMemberMetaData();

   Column getColumn(int var1);

   Column[] getColumns();

   int getNumberOfColumns();

   void setTypeConverter(TypeConverter var1);

   TypeConverter getTypeConverter();
}
