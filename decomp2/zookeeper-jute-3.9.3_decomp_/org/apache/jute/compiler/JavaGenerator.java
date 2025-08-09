package org.apache.jute.compiler;

import java.io.File;
import java.io.IOException;
import java.util.List;

class JavaGenerator {
   private List mRecList;
   private final File outputDirectory;

   JavaGenerator(String name, List incl, List records, File outputDirectory) {
      this.mRecList = records;
      this.outputDirectory = outputDirectory;
   }

   void genCode() throws IOException {
      for(JRecord rec : this.mRecList) {
         rec.genJavaCode(this.outputDirectory);
      }

   }
}
