package org.datanucleus.store.rdbms.sql;

public interface StatementGenerator {
   String OPTION_ALLOW_NULLS = "allowNulls";
   String OPTION_SELECT_NUCLEUS_TYPE = "selectNucleusType";
   String OPTION_RESTRICT_DISCRIM = "restrictDiscriminator";

   SQLStatement getStatement();

   void setParentStatement(SQLStatement var1);

   StatementGenerator setOption(String var1);

   StatementGenerator unsetOption(String var1);

   boolean hasOption(String var1);
}
