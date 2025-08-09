package org.codehaus.commons.compiler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import org.codehaus.commons.compiler.util.resource.ListableResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;

public interface ICompiler {
   @Nullable
   File NO_DESTINATION_DIRECTORY = null;
   ResourceFinder FIND_NEXT_TO_SOURCE_FILE = new ListableResourceFinder() {
      @Nullable
      public Resource findResource(String resourceName) {
         throw new UnsupportedOperationException("FIND_NEXT_TO_SOUJRCE_FILE");
      }

      @Nullable
      public Iterable list(String resourceNamePrefix, boolean recurse) {
         return Collections.emptyList();
      }

      public String toString() {
         return "FIND_NEXT_TO_SOUJRCE_FILE";
      }
   };
   ResourceCreator CREATE_NEXT_TO_SOURCE_FILE = new ResourceCreator() {
      public boolean deleteResource(String resourceName) {
         throw new UnsupportedOperationException("CREATE_NEXT_TO_SOURCE_FILE");
      }

      public OutputStream createResource(String resourceName) {
         throw new UnsupportedOperationException("CREATE_NEXT_TO_SOURCE_FILE");
      }

      public String toString() {
         return "CREATE_NEXT_TO_SOURCE_FILE";
      }
   };

   void setEncoding(Charset var1);

   void setSourceCharset(Charset var1);

   /** @deprecated */
   @Deprecated
   void setCharacterEncoding(@Nullable String var1);

   void setDebugLines(boolean var1);

   void setDebugVars(boolean var1);

   void setDebugSource(boolean var1);

   void setSourceVersion(int var1);

   void setTargetVersion(int var1);

   void setSourceFinder(ResourceFinder var1);

   void setSourcePath(File[] var1);

   void setBootClassPath(File[] var1);

   void setExtensionDirectories(File[] var1);

   void setClassPath(File[] var1);

   void setDestinationDirectory(@Nullable File var1, boolean var2);

   void setClassFileFinder(ResourceFinder var1, boolean var2);

   void setClassFileFinder(ResourceFinder var1);

   void setClassFileCreator(ResourceCreator var1);

   void setVerbose(boolean var1);

   boolean compile(File[] var1) throws CompileException, IOException;

   void compile(Resource[] var1) throws CompileException, IOException;

   void setCompileErrorHandler(@Nullable ErrorHandler var1);

   void setWarningHandler(WarningHandler var1);
}
