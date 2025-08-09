package org.datanucleus.store.rdbms.adapter;

import java.sql.DatabaseMetaData;
import org.datanucleus.store.rdbms.identifier.IdentifierType;

public class DB2AS400Adapter extends DB2Adapter {
   public DB2AS400Adapter(DatabaseMetaData metadata) {
      super(metadata);
   }

   public int getTransactionIsolationForSchemaCreation() {
      return 4;
   }

   public int getDatastoreIdentifierMaxLength(IdentifierType identifierType) {
      if (identifierType == IdentifierType.CANDIDATE_KEY) {
         return this.maxConstraintNameLength;
      } else if (identifierType == IdentifierType.FOREIGN_KEY) {
         return this.maxConstraintNameLength;
      } else if (identifierType == IdentifierType.INDEX) {
         return this.maxIndexNameLength;
      } else {
         return identifierType == IdentifierType.PRIMARY_KEY ? this.maxConstraintNameLength : super.getDatastoreIdentifierMaxLength(identifierType);
      }
   }
}
