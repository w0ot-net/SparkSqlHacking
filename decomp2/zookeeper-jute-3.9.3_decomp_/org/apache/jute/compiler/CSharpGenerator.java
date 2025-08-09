package org.apache.jute.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSharpGenerator {
   private List mRecList;
   private final File outputDirectory;

   CSharpGenerator(String name, List ilist, List rlist, File outputDirectory) {
      this.outputDirectory = outputDirectory;
      this.mRecList = rlist;
   }

   void genCode() throws IOException {
      for(JRecord rec : this.mRecList) {
         rec.genCsharpCode(this.outputDirectory);
      }

   }
}
