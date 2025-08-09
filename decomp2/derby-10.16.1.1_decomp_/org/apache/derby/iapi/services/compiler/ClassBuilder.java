package org.apache.derby.iapi.services.compiler;

import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public interface ClassBuilder {
   LocalField addField(String var1, String var2, int var3);

   GeneratedClass getGeneratedClass() throws StandardException;

   ByteArray getClassBytecode() throws StandardException;

   String getName();

   String getFullName();

   MethodBuilder newMethodBuilder(int var1, String var2, String var3);

   MethodBuilder newMethodBuilder(int var1, String var2, String var3, String[] var4);

   MethodBuilder newConstructorBuilder(int var1);
}
