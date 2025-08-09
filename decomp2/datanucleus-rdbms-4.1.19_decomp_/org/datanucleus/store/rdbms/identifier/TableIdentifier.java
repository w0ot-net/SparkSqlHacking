package org.datanucleus.store.rdbms.identifier;

class TableIdentifier extends DatastoreIdentifierImpl {
   public TableIdentifier(IdentifierFactory factory, String sqlIdentifier) {
      super(factory, sqlIdentifier);
   }
}
