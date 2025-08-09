package org.apache.ivy.core.pack;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.util.FileUtil;

public class Pack200Packing extends StreamPacking {
   private static final String[] NAMES = new String[]{"pack200"};

   public String[] getNames() {
      return NAMES;
   }

   public String getUnpackedExtension(String ext) {
      if (ext.endsWith("pack.gz")) {
         ext = ext.substring(0, ext.length() - 7);
         if (ext.endsWith(".")) {
            ext = ext.substring(0, ext.length() - 1);
         }
      } else if (ext.endsWith("pack")) {
         ext = ext.substring(0, ext.length() - 4);
         if (ext.endsWith(".")) {
            ext = ext.substring(0, ext.length() - 1);
         }
      }

      return ext;
   }

   public InputStream unpack(InputStream packed) throws IOException {
      return FileUtil.unwrapPack200(packed);
   }
}
