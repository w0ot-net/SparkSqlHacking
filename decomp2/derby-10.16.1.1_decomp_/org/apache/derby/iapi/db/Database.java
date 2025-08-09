package org.apache.derby.iapi.db;

import java.sql.SQLException;
import java.util.Locale;
import org.apache.derby.iapi.jdbc.AuthenticationService;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.LocaleFinder;

public interface Database extends org.apache.derby.database.Database, LocaleFinder {
   LanguageConnectionContext setupConnection(ContextManager var1, String var2, String var3, String var4) throws StandardException;

   void pushDbContext(ContextManager var1);

   boolean isActive();

   int getEngineType();

   AuthenticationService getAuthenticationService() throws StandardException;

   Object getResourceAdapter();

   void setLocale(Locale var1);

   DataDictionary getDataDictionary();

   void failover(String var1) throws StandardException;

   boolean isInSlaveMode();

   void stopReplicationSlave() throws SQLException;

   void startReplicationMaster(String var1, String var2, int var3, String var4) throws SQLException;

   void stopReplicationMaster() throws SQLException;
}
