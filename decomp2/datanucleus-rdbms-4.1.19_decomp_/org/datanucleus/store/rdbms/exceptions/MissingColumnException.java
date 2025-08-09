package org.datanucleus.store.rdbms.exceptions;

import java.util.Collection;
import java.util.Iterator;
import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class MissingColumnException extends DatastoreValidationException {
   private static final long serialVersionUID = 4195458244790681864L;

   public MissingColumnException(Table table, Collection columns) {
      super(Localiser.msg("020010", new Object[]{table.toString(), getColumnNameList(columns)}));
   }

   private static String getColumnNameList(Collection columns) {
      StringBuilder list = new StringBuilder();

      for(Iterator<Column> i = columns.iterator(); i.hasNext(); list.append(((Column)i.next()).getIdentifier())) {
         if (list.length() > 0) {
            list.append(", ");
         }
      }

      return list.toString();
   }
}
