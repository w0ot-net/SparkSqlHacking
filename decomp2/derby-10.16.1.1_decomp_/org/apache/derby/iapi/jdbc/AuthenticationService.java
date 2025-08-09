package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;
import java.util.Properties;

public interface AuthenticationService {
   String MODULE = "org.apache.derby.iapi.jdbc.AuthenticationService";

   boolean authenticate(String var1, Properties var2) throws SQLException;

   String getSystemCredentialsDatabaseName();
}
