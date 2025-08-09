package org.apache.derby.database;

import java.sql.SQLException;
import java.util.Locale;
import org.apache.derby.catalog.UUID;

public interface Database {
   String LUCENE_DIR = "LUCENE";

   boolean isReadOnly();

   void backup(String var1, boolean var2) throws SQLException;

   void backupAndEnableLogArchiveMode(String var1, boolean var2, boolean var3) throws SQLException;

   void disableLogArchiveMode(boolean var1) throws SQLException;

   void freeze() throws SQLException;

   void unfreeze() throws SQLException;

   void checkpoint() throws SQLException;

   Locale getLocale();

   /** @deprecated */
   UUID getId();
}
