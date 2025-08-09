package org.apache.derby.catalog;

public interface Dependable {
   String ALIAS = "Alias";
   String CONGLOMERATE = "Conglomerate";
   String CONSTRAINT = "Constraint";
   String DEFAULT = "Default";
   String HEAP = "Heap";
   String INDEX = "Index";
   String PREPARED_STATEMENT = "PreparedStatement";
   String ACTIVATION = "Activation";
   String FILE = "File";
   String STORED_PREPARED_STATEMENT = "StoredPreparedStatement";
   String TABLE = "Table";
   String COLUMNS_IN_TABLE = "ColumnsInTable";
   String TRIGGER = "Trigger";
   String VIEW = "View";
   String SCHEMA = "Schema";
   String TABLE_PERMISSION = "TablePrivilege";
   String COLUMNS_PERMISSION = "ColumnsPrivilege";
   String ROUTINE_PERMISSION = "RoutinePrivilege";
   String ROLE_GRANT = "RoleGrant";
   String SEQUENCE = "Sequence";
   String PERM = "Perm";

   DependableFinder getDependableFinder();

   String getObjectName();

   UUID getObjectID();

   boolean isPersistent();

   String getClassType();
}
