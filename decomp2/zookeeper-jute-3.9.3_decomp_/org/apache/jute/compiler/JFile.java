package org.apache.jute.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JFile {
   private String mName;
   private List mInclFiles;
   private List mRecords;

   public JFile(String name, ArrayList inclFiles, ArrayList recList) {
      this.mName = name;
      this.mInclFiles = inclFiles;
      this.mRecords = recList;
   }

   String getName() {
      int idx = this.mName.lastIndexOf(47);
      return idx > 0 ? this.mName.substring(idx) : this.mName;
   }

   public void genCode(String language, File outputDirectory) throws IOException {
      if ("c++".equals(language)) {
         CppGenerator gen = new CppGenerator(this.mName, this.mInclFiles, this.mRecords, outputDirectory);
         gen.genCode();
      } else if ("java".equals(language)) {
         JavaGenerator gen = new JavaGenerator(this.mName, this.mInclFiles, this.mRecords, outputDirectory);
         gen.genCode();
      } else if ("c".equals(language)) {
         CGenerator gen = new CGenerator(this.mName, this.mInclFiles, this.mRecords, outputDirectory);
         gen.genCode();
      } else {
         if (!"csharp".equals(language)) {
            throw new IOException("Cannnot recognize language:" + language);
         }

         CSharpGenerator gen = new CSharpGenerator(this.mName, this.mInclFiles, this.mRecords, outputDirectory);
         gen.genCode();
      }

   }
}
