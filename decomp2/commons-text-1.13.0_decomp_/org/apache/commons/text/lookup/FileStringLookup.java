package org.apache.commons.text.lookup;

import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.lang3.StringUtils;

final class FileStringLookup extends AbstractPathFencedLookup {
   static final AbstractStringLookup INSTANCE = new FileStringLookup((Path[])null);

   FileStringLookup(Path... fences) {
      super(fences);
   }

   public String lookup(String key) {
      if (key == null) {
         return null;
      } else {
         String[] keys = key.split(String.valueOf(':'));
         int keyLen = keys.length;
         if (keyLen < 2) {
            throw IllegalArgumentExceptions.format("Bad file key format [%s], expected format is CharsetName:DocumentPath.", key);
         } else {
            String charsetName = keys[0];
            String fileName = StringUtils.substringAfter(key, 58);

            try {
               return new String(Files.readAllBytes(this.getPath(fileName)), charsetName);
            } catch (Exception e) {
               throw IllegalArgumentExceptions.format(e, "Error looking up file [%s] with charset [%s].", fileName, charsetName);
            }
         }
      }
   }
}
