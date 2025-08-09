package org.datanucleus.store.rdbms.identifier;

class PrimaryKeyIdentifier extends DatastoreIdentifierImpl {
   public PrimaryKeyIdentifier(IdentifierFactory factory, String sqlIdentifier) {
      super(factory, sqlIdentifier);
   }
}
