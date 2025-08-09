package org.codehaus.commons.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public abstract class MultiCookable extends Cookable implements IMultiCookable {
   public final void cook(InputStream... inputStreams) throws CompileException, IOException {
      this.cook(new String[inputStreams.length], inputStreams);
   }

   public final void cook(String[] strings) throws CompileException {
      this.cook(new String[strings.length], strings);
   }

   public final void cook(String[] fileNames, InputStream[] inputStreams) throws CompileException, IOException {
      this.cook(fileNames, inputStreams, new String[fileNames.length]);
   }

   public final void cook(InputStream[] inputStreams, String[] encodings) throws CompileException, IOException {
      this.cook(new String[inputStreams.length], inputStreams, encodings);
   }

   public final void cook(String[] fileNames, InputStream[] inputStreams, String[] encodings) throws CompileException, IOException {
      int count = fileNames.length;
      Reader[] readers = new Reader[count];

      for(int i = 0; i < count; ++i) {
         readers[i] = encodings[i] == null ? new InputStreamReader(inputStreams[i]) : new InputStreamReader(inputStreams[i], encodings[i]);
      }

      this.cook(fileNames, readers);
   }

   public final void cook(String[] fileNames, String[] strings) throws CompileException {
      int count = fileNames.length;
      Reader[] readers = new Reader[count];

      for(int i = 0; i < count; ++i) {
         readers[i] = new StringReader(strings[i]);
      }

      try {
         this.cook(fileNames, readers);
      } catch (IOException ioe) {
         throw new InternalCompilerException("SNO: IOException despite StringReader", ioe);
      }
   }

   public abstract void cook(String[] var1, Reader[] var2) throws CompileException, IOException;

   public final void cookFiles(File[] files) throws CompileException, IOException {
      this.cookFiles(files, new String[files.length]);
   }

   public final void cookFiles(File[] files, String[] encodings) throws CompileException, IOException {
      int count = files.length;
      String[] fileNames = new String[count];
      InputStream[] inputStreams = new InputStream[count];
      boolean var16 = false;

      try {
         var16 = true;

         for(int i = 0; i < count; ++i) {
            File file = files[i];
            fileNames[i] = file.getPath();
            inputStreams[i] = new FileInputStream(file);
         }

         this.cook(inputStreams, encodings);

         for(int i = 0; i < count; ++i) {
            inputStreams[i].close();
         }

         var16 = false;
      } finally {
         if (var16) {
            int i = 0;

            while(true) {
               if (i >= count) {
                  ;
               } else {
                  InputStream is = inputStreams[i];
                  if (is != null) {
                     try {
                        is.close();
                     } catch (Exception var17) {
                     }
                  }

                  ++i;
               }
            }
         }
      }

      for(int i = 0; i < count; ++i) {
         InputStream is = inputStreams[i];
         if (is != null) {
            try {
               is.close();
            } catch (Exception var18) {
            }
         }
      }

   }

   public final void cookFiles(String[] fileNames) throws CompileException, IOException {
      this.cook(fileNames, new String[fileNames.length]);
   }

   public final void cookFiles(String[] fileNames, String[] encodings) throws CompileException, IOException {
      int count = fileNames.length;
      File[] files = new File[count];

      for(int i = 0; i < count; ++i) {
         files[i] = new File(fileNames[i]);
      }

      this.cookFiles(files, encodings);
   }

   public final void cook(Reader... readers) throws CompileException, IOException {
      this.cook(new String[readers.length], readers);
   }
}
