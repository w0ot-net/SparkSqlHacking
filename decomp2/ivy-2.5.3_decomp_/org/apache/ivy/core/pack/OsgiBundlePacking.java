package org.apache.ivy.core.pack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.util.FileUtil;

public class OsgiBundlePacking extends ZipPacking {
   private static final String[] NAMES = new String[]{"bundle"};

   public String[] getNames() {
      return NAMES;
   }

   protected void writeFile(InputStream zip, File f) throws IOException {
      if (f.getName().endsWith(".jar.pack.gz")) {
         zip = FileUtil.unwrapPack200(zip);
         f = new File(f.getParentFile(), f.getName().substring(0, f.getName().length() - 8));
      }

      super.writeFile(zip, f);
   }
}
