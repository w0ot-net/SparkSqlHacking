package org.codehaus.commons.compiler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import org.codehaus.commons.nullanalysis.Nullable;

public interface ICookable {
   void cook(@Nullable String var1, Reader var2) throws CompileException, IOException;

   void cook(Reader var1) throws CompileException, IOException;

   void cook(InputStream var1) throws CompileException, IOException;

   void cook(@Nullable String var1, InputStream var2) throws CompileException, IOException;

   void cook(InputStream var1, @Nullable String var2) throws CompileException, IOException;

   void cook(@Nullable String var1, InputStream var2, @Nullable String var3) throws CompileException, IOException;

   void cook(String var1) throws CompileException;

   void cook(@Nullable String var1, String var2) throws CompileException;

   void cookFile(File var1) throws CompileException, IOException;

   void cookFile(File var1, @Nullable String var2) throws CompileException, IOException;

   void cookFile(String var1) throws CompileException, IOException;

   void cookFile(String var1, @Nullable String var2) throws CompileException, IOException;

   void setSourceVersion(int var1);

   void setTargetVersion(int var1);

   Map getBytecodes();
}
