package org.apache.derby.iapi.sql;

public interface StatementType {
   int UNKNOWN = 0;
   int INSERT = 1;
   int BULK_INSERT_REPLACE = 2;
   int UPDATE = 3;
   int DELETE = 4;
   int ENABLED = 5;
   int DISABLED = 6;
   int DROP_CASCADE = 0;
   int DROP_RESTRICT = 1;
   int DROP_DEFAULT = 2;
   int RENAME_TABLE = 1;
   int RENAME_COLUMN = 2;
   int RENAME_INDEX = 3;
   int RA_CASCADE = 0;
   int RA_RESTRICT = 1;
   int RA_NOACTION = 2;
   int RA_SETNULL = 3;
   int RA_SETDEFAULT = 4;
   int SET_SCHEMA_USER = 1;
   int SET_SCHEMA_DYNAMIC = 2;
   int SET_ROLE_DYNAMIC = 1;
}
