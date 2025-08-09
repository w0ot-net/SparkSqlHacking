package org.apache.hadoop.hive.conf.valcoersion;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;

public class JavaIOTmpdirVariableCoercion extends VariableCoercion {
   private static final Log LOG = LogFactory.getLog(JavaIOTmpdirVariableCoercion.class);
   private static final String NAME = "system:java.io.tmpdir";
   private static final FileSystem LOCAL_FILE_SYSTEM = new LocalFileSystem();
   public static final JavaIOTmpdirVariableCoercion INSTANCE = new JavaIOTmpdirVariableCoercion();

   private JavaIOTmpdirVariableCoercion() {
      super("system:java.io.tmpdir");
   }

   private String coerce(String originalValue) {
      if (originalValue != null && !originalValue.isEmpty()) {
         try {
            Path originalPath = new Path(originalValue);
            Path absolutePath = FileUtils.makeAbsolute(LOCAL_FILE_SYSTEM, originalPath);
            return absolutePath.toString();
         } catch (IOException var4) {
            LOG.warn(String.format("Unable to resolve 'java.io.tmpdir' for absolute path '%s'", originalValue));
            return originalValue;
         }
      } else {
         return originalValue;
      }
   }

   public String getCoerced(String originalValue) {
      return this.coerce(originalValue);
   }
}
