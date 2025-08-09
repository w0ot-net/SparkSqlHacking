package org.apache.derby.iapi.sql.conn;

import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.shared.common.error.StandardException;

public interface Authorizer {
   int SQL_WRITE_OP = 0;
   int SQL_SELECT_OP = 1;
   int SQL_ARBITARY_OP = 2;
   int SQL_CALL_OP = 3;
   int SQL_DDL_OP = 4;
   int PROPERTY_WRITE_OP = 5;
   int JAR_WRITE_OP = 6;
   int NULL_PRIV = -1;
   int SELECT_PRIV = 0;
   int UPDATE_PRIV = 1;
   int REFERENCES_PRIV = 2;
   int INSERT_PRIV = 3;
   int DELETE_PRIV = 4;
   int TRIGGER_PRIV = 5;
   int EXECUTE_PRIV = 6;
   int USAGE_PRIV = 7;
   int MIN_SELECT_PRIV = 8;
   int PRIV_TYPE_COUNT = 9;
   int CREATE_SCHEMA_PRIV = 16;
   int MODIFY_SCHEMA_PRIV = 17;
   int DROP_SCHEMA_PRIV = 18;
   int CREATE_ROLE_PRIV = 19;
   int DROP_ROLE_PRIV = 20;
   String SYSTEM_AUTHORIZATION_ID = "_SYSTEM";
   String PUBLIC_AUTHORIZATION_ID = "PUBLIC";

   void authorize(int var1) throws StandardException;

   void authorize(Activation var1, int var2) throws StandardException;

   void authorize(List var1, Activation var2) throws StandardException;

   boolean isReadOnlyConnection();

   void setReadOnlyConnection(boolean var1, boolean var2) throws StandardException;

   void refresh() throws StandardException;
}
