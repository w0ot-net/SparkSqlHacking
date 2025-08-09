package org.apache.derby.vti;

import java.sql.SQLException;

public interface DeferModification {
   int INSERT_STATEMENT = 1;
   int UPDATE_STATEMENT = 2;
   int DELETE_STATEMENT = 3;

   boolean alwaysDefer(int var1) throws SQLException;

   boolean columnRequiresDefer(int var1, String var2, boolean var3) throws SQLException;

   boolean subselectRequiresDefer(int var1, String var2, String var3) throws SQLException;

   boolean subselectRequiresDefer(int var1, String var2) throws SQLException;

   void modificationNotify(int var1, boolean var2) throws SQLException;
}
