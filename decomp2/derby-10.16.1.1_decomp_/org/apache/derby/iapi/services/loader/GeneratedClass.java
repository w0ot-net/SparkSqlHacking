package org.apache.derby.iapi.services.loader;

import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.shared.common.error.StandardException;

public interface GeneratedClass {
   String getName();

   Object newInstance(Context var1) throws StandardException;

   GeneratedMethod getMethod(String var1) throws StandardException;

   int getClassLoaderVersion();
}
