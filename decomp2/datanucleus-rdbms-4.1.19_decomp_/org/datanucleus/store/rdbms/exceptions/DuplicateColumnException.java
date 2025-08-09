package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class DuplicateColumnException extends NucleusException {
   private static final long serialVersionUID = -8400052870749668700L;
   private Column conflictingColumn;

   public DuplicateColumnException(String tableName, Column col1, Column col2) {
      super(Localiser.msg("020007", new Object[]{col1.getIdentifier(), tableName, col1.getMemberMetaData() == null ? Localiser.msg("020008") : (col1.getMemberMetaData() != null ? col1.getMemberMetaData().getFullFieldName() : null), col2.getMemberMetaData() == null ? Localiser.msg("020008") : (col2.getMemberMetaData() != null ? col2.getMemberMetaData().getFullFieldName() : null)}));
      this.conflictingColumn = col2;
      this.setFatal();
   }

   public Column getConflictingColumn() {
      return this.conflictingColumn;
   }
}
