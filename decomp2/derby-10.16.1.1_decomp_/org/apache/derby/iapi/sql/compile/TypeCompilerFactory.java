package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.types.TypeId;

public interface TypeCompilerFactory {
   String MODULE = "org.apache.derby.iapi.sql.compile.TypeCompilerFactory";

   TypeCompiler getTypeCompiler(TypeId var1);
}
