package org.apache.derby.iapi.services.loader;

import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.shared.common.error.StandardException;

public interface GeneratedByteCode {
   void initFromContext(Context var1) throws StandardException;

   void setGC(GeneratedClass var1);

   void postConstructor() throws StandardException;

   GeneratedClass getGC();

   GeneratedMethod getMethod(String var1) throws StandardException;

   Object e0() throws StandardException;

   Object e1() throws StandardException;

   Object e2() throws StandardException;

   Object e3() throws StandardException;

   Object e4() throws StandardException;

   Object e5() throws StandardException;

   Object e6() throws StandardException;

   Object e7() throws StandardException;

   Object e8() throws StandardException;

   Object e9() throws StandardException;
}
