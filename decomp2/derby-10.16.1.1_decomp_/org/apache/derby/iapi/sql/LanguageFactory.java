package org.apache.derby.iapi.sql;

import org.apache.derby.iapi.services.loader.ClassInspector;

public interface LanguageFactory {
   String MODULE = "org.apache.derby.iapi.sql.LanguageFactory";

   ParameterValueSet newParameterValueSet(ClassInspector var1, int var2, boolean var3);

   ResultDescription getResultDescription(ResultDescription var1, int[] var2);

   ResultDescription getResultDescription(ResultColumnDescriptor[] var1, String var2);
}
