package org.apache.derby.iapi.services.daemon;

import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface IndexStatisticsDaemon {
   void runExplicitly(LanguageConnectionContext var1, TableDescriptor var2, ConglomerateDescriptor[] var3, String var4) throws StandardException;

   void schedule(TableDescriptor var1);

   void stop();
}
