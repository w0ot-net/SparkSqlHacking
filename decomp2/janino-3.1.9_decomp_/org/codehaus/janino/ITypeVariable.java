package org.codehaus.janino;

import org.codehaus.commons.compiler.CompileException;

public interface ITypeVariable extends ITypeVariableOrIClass {
   String getName();

   ITypeVariableOrIClass[] getBounds() throws CompileException;
}
