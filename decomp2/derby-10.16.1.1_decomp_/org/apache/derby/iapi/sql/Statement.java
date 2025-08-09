package org.apache.derby.iapi.sql;

import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface Statement {
   PreparedStatement prepare(LanguageConnectionContext var1) throws StandardException;

   PreparedStatement prepare(LanguageConnectionContext var1, boolean var2) throws StandardException;

   PreparedStatement prepareStorable(LanguageConnectionContext var1, PreparedStatement var2, Object[] var3, SchemaDescriptor var4, boolean var5) throws StandardException;

   String getSource();
}
