package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.shared.common.error.StandardException;

public interface ConsInfo extends Formatable {
   SchemaDescriptor getReferencedTableSchemaDescriptor(DataDictionary var1) throws StandardException;

   TableDescriptor getReferencedTableDescriptor(DataDictionary var1) throws StandardException;

   String[] getReferencedColumnNames();

   String getReferencedTableName();

   int getReferentialActionUpdateRule();

   int getReferentialActionDeleteRule();
}
