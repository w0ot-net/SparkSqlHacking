package org.datanucleus.store.rdbms.identifier;

class SequenceIdentifier extends DatastoreIdentifierImpl {
   public SequenceIdentifier(IdentifierFactory factory, String sqlIdentifier) {
      super(factory, sqlIdentifier);
   }
}
