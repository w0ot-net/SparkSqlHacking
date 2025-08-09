package org.codehaus.commons.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class Cookable implements ICookable {
   public abstract void cook(@Nullable String var1, Reader var2) throws CompileException, IOException;

   public final void cook(Reader r) throws CompileException, IOException {
      this.cook((String)null, (Reader)r);
   }

   public final void cook(InputStream is) throws CompileException, IOException {
      this.cook((String)null, (InputStream)is);
   }

   public final void cook(@Nullable String fileName, InputStream is) throws CompileException, IOException {
      this.cook(fileName, is, (String)null);
   }

   public final void cook(InputStream is, @Nullable String encoding) throws CompileException, IOException {
      this.cook((Reader)(encoding == null ? new InputStreamReader(is) : new InputStreamReader(is, encoding)));
   }

   public final void cook(@Nullable String fileName, InputStream is, @Nullable String encoding) throws CompileException, IOException {
      this.cook((String)fileName, (Reader)(encoding == null ? new InputStreamReader(is) : new InputStreamReader(is, encoding)));
   }

   public final void cook(String s) throws CompileException {
      this.cook((String)null, s);
   }

   public final void cook(@Nullable String fileName, String s) throws CompileException {
      try {
         this.cook((String)fileName, (Reader)(new StringReader(s)));
      } catch (IOException ioe) {
         ioe.printStackTrace();
         throw new RuntimeException("SNO: StringReader throws IOException");
      }
   }

   public final void cookFile(File file) throws CompileException, IOException {
      this.cookFile((File)file, (String)null);
   }

   public final void cookFile(File file, @Nullable String encoding) throws CompileException, IOException {
      InputStream is = new FileInputStream(file);

      try {
         this.cook((String)file.getAbsolutePath(), (Reader)(encoding == null ? new InputStreamReader(is) : new InputStreamReader(is, encoding)));
         is.close();
         is = null;
      } finally {
         if (is != null) {
            try {
               is.close();
            } catch (IOException var10) {
            }
         }

      }

   }

   public final void cookFile(String fileName) throws CompileException, IOException {
      this.cookFile((String)fileName, (String)null);
   }

   public final void cookFile(String fileName, @Nullable String encoding) throws CompileException, IOException {
      this.cookFile(new File(fileName), encoding);
   }
}
