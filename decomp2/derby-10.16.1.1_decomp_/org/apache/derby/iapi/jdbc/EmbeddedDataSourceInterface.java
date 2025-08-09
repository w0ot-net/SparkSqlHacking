package org.apache.derby.iapi.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public interface EmbeddedDataSourceInterface extends DataSource {
   void setDatabaseName(String var1);

   String getDatabaseName();

   void setDataSourceName(String var1);

   String getDataSourceName();

   void setDescription(String var1);

   String getDescription();

   void setUser(String var1);

   String getUser();

   void setPassword(String var1);

   String getPassword();

   void setCreateDatabase(String var1);

   String getCreateDatabase();

   void setConnectionAttributes(String var1);

   String getConnectionAttributes();

   void setShutdownDatabase(String var1);

   String getShutdownDatabase();

   void setAttributesAsPassword(boolean var1);

   boolean getAttributesAsPassword();

   Connection getConnection(String var1, String var2, boolean var3) throws SQLException;
}
