package org.apache.derby.authentication;

import java.sql.SQLException;
import java.util.Properties;

public interface UserAuthenticator {
   boolean authenticateUser(String var1, String var2, String var3, Properties var4) throws SQLException;
}
