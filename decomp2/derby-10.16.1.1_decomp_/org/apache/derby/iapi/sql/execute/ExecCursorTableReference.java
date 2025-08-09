package org.apache.derby.iapi.sql.execute;

public interface ExecCursorTableReference {
   String getBaseName();

   String getExposedName();

   String getSchemaName();
}
