package org.apache.derby.iapi.sql.depend;

import org.apache.derby.catalog.Dependable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

public interface Dependent extends Dependable {
   boolean isValid();

   void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException;

   void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException;
}
