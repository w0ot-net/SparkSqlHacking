package org.apache.derby.iapi.services.compiler;

import org.apache.derby.iapi.services.loader.ClassFactory;

public interface JavaFactory {
   String JAVA_FACTORY_PROPERTY = "derby.module.JavaCompiler";

   ClassBuilder newClassBuilder(ClassFactory var1, String var2, int var3, String var4, String var5);
}
