package org.datanucleus.store.schema.naming;

import java.util.List;
import java.util.Set;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ConstraintMetaData;
import org.datanucleus.metadata.SequenceMetaData;

public interface NamingFactory {
   NamingFactory setReservedKeywords(Set var1);

   NamingFactory setMaximumLength(SchemaComponent var1, int var2);

   NamingFactory setQuoteString(String var1);

   NamingFactory setWordSeparator(String var1);

   NamingFactory setNamingCase(NamingCase var1);

   String getTableName(AbstractClassMetaData var1);

   String getTableName(AbstractMemberMetaData var1);

   String getColumnName(AbstractClassMetaData var1, ColumnType var2);

   String getColumnName(AbstractMemberMetaData var1, ColumnType var2);

   String getColumnName(AbstractMemberMetaData var1, ColumnType var2, int var3);

   String getColumnName(List var1, int var2);

   String getConstraintName(AbstractClassMetaData var1, ConstraintMetaData var2, int var3);

   String getConstraintName(String var1, AbstractMemberMetaData var2, ConstraintMetaData var3);

   String getConstraintName(AbstractClassMetaData var1, ConstraintMetaData var2, ColumnType var3);

   String getSequenceName(SequenceMetaData var1);
}
