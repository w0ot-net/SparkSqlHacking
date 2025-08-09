package jakarta.activation;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

public abstract class FileTypeMap {
   private static FileTypeMap defaultMap = null;
   private static Map map = new WeakHashMap();

   public abstract String getContentType(File var1);

   public abstract String getContentType(String var1);

   public static synchronized void setDefaultFileTypeMap(FileTypeMap fileTypeMap) {
      SecurityManager security = System.getSecurityManager();
      if (security != null) {
         try {
            security.checkSetFactory();
         } catch (SecurityException ex) {
            ClassLoader cl = FileTypeMap.class.getClassLoader();
            if (cl == null || cl.getParent() == null || cl != fileTypeMap.getClass().getClassLoader()) {
               throw ex;
            }
         }
      }

      map.remove(SecuritySupport.getContextClassLoader());
      defaultMap = fileTypeMap;
   }

   public static synchronized FileTypeMap getDefaultFileTypeMap() {
      if (defaultMap != null) {
         return defaultMap;
      } else {
         ClassLoader tccl = SecuritySupport.getContextClassLoader();
         FileTypeMap def = (FileTypeMap)map.get(tccl);
         if (def == null) {
            def = new MimetypesFileTypeMap();
            map.put(tccl, def);
         }

         return def;
      }
   }
}
