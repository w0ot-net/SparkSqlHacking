package org.apache.jute.compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class CppGenerator {
   private String mName;
   private List mInclFiles;
   private List mRecList;
   private final File outputDirectory;

   CppGenerator(String name, List ilist, List rlist, File outputDirectory) {
      this.outputDirectory = outputDirectory;
      this.mName = (new File(name)).getName();
      this.mInclFiles = ilist;
      this.mRecList = rlist;
   }

   void genCode() throws IOException {
      if (!this.outputDirectory.exists() && !this.outputDirectory.mkdirs()) {
         throw new IOException("unable to create output directory " + this.outputDirectory);
      } else {
         FileWriter cc = new FileWriter(new File(this.outputDirectory, this.mName + ".cc"));

         try {
            FileWriter hh = new FileWriter(new File(this.outputDirectory, this.mName + ".hh"));

            try {
               hh.write("/**\n");
               hh.write("* Licensed to the Apache Software Foundation (ASF) under one\n");
               hh.write("* or more contributor license agreements.  See the NOTICE file\n");
               hh.write("* distributed with this work for additional information\n");
               hh.write("* regarding copyright ownership.  The ASF licenses this file\n");
               hh.write("* to you under the Apache License, Version 2.0 (the\n");
               hh.write("* \"License\"); you may not use this file except in compliance\n");
               hh.write("* with the License.  You may obtain a copy of the License at\n");
               hh.write("*\n");
               hh.write("*     http://www.apache.org/licenses/LICENSE-2.0\n");
               hh.write("*\n");
               hh.write("* Unless required by applicable law or agreed to in writing, software\n");
               hh.write("* distributed under the License is distributed on an \"AS IS\" BASIS,\n");
               hh.write("* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n");
               hh.write("* See the License for the specific language governing permissions and\n");
               hh.write("* limitations under the License.\n");
               hh.write("*/\n");
               hh.write("\n");
               cc.write("/**\n");
               cc.write("* Licensed to the Apache Software Foundation (ASF) under one\n");
               cc.write("* or more contributor license agreements.  See the NOTICE file\n");
               cc.write("* distributed with this work for additional information\n");
               cc.write("* regarding copyright ownership.  The ASF licenses this file\n");
               cc.write("* to you under the Apache License, Version 2.0 (the\n");
               cc.write("* \"License\"); you may not use this file except in compliance\n");
               cc.write("* with the License.  You may obtain a copy of the License at\n");
               cc.write("*\n");
               cc.write("*     http://www.apache.org/licenses/LICENSE-2.0\n");
               cc.write("*\n");
               cc.write("* Unless required by applicable law or agreed to in writing, software\n");
               cc.write("* distributed under the License is distributed on an \"AS IS\" BASIS,\n");
               cc.write("* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n");
               cc.write("* See the License for the specific language governing permissions and\n");
               cc.write("* limitations under the License.\n");
               cc.write("*/\n");
               cc.write("\n");
               hh.write("#ifndef __" + this.mName.toUpperCase().replace('.', '_') + "__\n");
               hh.write("#define __" + this.mName.toUpperCase().replace('.', '_') + "__\n");
               hh.write("#include \"recordio.hh\"\n");

               for(JFile f : this.mInclFiles) {
                  hh.write("#include \"" + f.getName() + ".hh\"\n");
               }

               cc.write("#include \"" + this.mName + ".hh\"\n");

               for(JRecord jr : this.mRecList) {
                  jr.genCppCode(hh, cc);
               }

               hh.write("#endif //" + this.mName.toUpperCase().replace('.', '_') + "__\n");
            } catch (Throwable var7) {
               try {
                  hh.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }

               throw var7;
            }

            hh.close();
         } catch (Throwable var8) {
            try {
               cc.close();
            } catch (Throwable var5) {
               var8.addSuppressed(var5);
            }

            throw var8;
         }

         cc.close();
      }
   }
}
