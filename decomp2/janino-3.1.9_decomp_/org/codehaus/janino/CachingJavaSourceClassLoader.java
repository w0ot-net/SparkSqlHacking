package org.codehaus.janino;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceCreator;
import org.codehaus.commons.compiler.util.resource.DirectoryResourceFinder;
import org.codehaus.commons.compiler.util.resource.PathResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class CachingJavaSourceClassLoader extends JavaSourceClassLoader {
   private final ResourceFinder classFileCacheResourceFinder;
   private final ResourceCreator classFileCacheResourceCreator;
   private final ResourceFinder sourceFinder;

   public CachingJavaSourceClassLoader(ClassLoader parentClassLoader, @Nullable File[] sourcePath, @Nullable String characterEncoding, File cacheDirectory) {
      this(parentClassLoader, (ResourceFinder)(sourcePath == null ? new DirectoryResourceFinder(new File(".")) : new PathResourceFinder(sourcePath)), characterEncoding, new DirectoryResourceFinder(cacheDirectory), new DirectoryResourceCreator(cacheDirectory));
   }

   public CachingJavaSourceClassLoader(ClassLoader parentClassLoader, ResourceFinder sourceFinder, @Nullable String characterEncoding, ResourceFinder classFileCacheResourceFinder, ResourceCreator classFileCacheResourceCreator) {
      super(parentClassLoader, sourceFinder, characterEncoding);
      this.classFileCacheResourceFinder = classFileCacheResourceFinder;
      this.classFileCacheResourceCreator = classFileCacheResourceCreator;
      this.sourceFinder = sourceFinder;
   }

   @Nullable
   protected Map generateBytecodes(String className) throws ClassNotFoundException {
      Resource classFileResource = this.classFileCacheResourceFinder.findResource(ClassFile.getClassFileResourceName(className));
      if (classFileResource != null) {
         Resource sourceResource = this.sourceFinder.findResource(ClassFile.getSourceResourceName(className));
         if (sourceResource == null) {
            return null;
         }

         if (sourceResource.lastModified() < classFileResource.lastModified()) {
            byte[] bytecode;
            try {
               bytecode = readResource(classFileResource);
            } catch (IOException ex) {
               throw new ClassNotFoundException("Reading class file from \"" + classFileResource + "\"", ex);
            }

            Map<String, byte[]> m = new HashMap();
            m.put(className, bytecode);
            return m;
         }
      }

      Map<String, byte[]> bytecodes = super.generateBytecodes(className);
      if (bytecodes == null) {
         return null;
      } else {
         for(Map.Entry me : bytecodes.entrySet()) {
            String className2 = (String)me.getKey();
            byte[] bytecode = (byte[])me.getValue();

            try {
               writeResource(this.classFileCacheResourceCreator, ClassFile.getClassFileResourceName(className2), bytecode);
            } catch (IOException ex) {
               throw new ClassNotFoundException("Writing class file to \"" + ClassFile.getClassFileResourceName(className2) + "\"", ex);
            }
         }

         return bytecodes;
      }
   }

   private static byte[] readResource(Resource r) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[4096];
      InputStream is = r.open();

      try {
         while(true) {
            int cnt = is.read(buffer);
            if (cnt == -1) {
               return baos.toByteArray();
            }

            baos.write(buffer, 0, cnt);
         }
      } finally {
         try {
            is.close();
         } catch (IOException var10) {
         }

      }
   }

   private static void writeResource(ResourceCreator resourceCreator, String resourceName, byte[] data) throws IOException {
      OutputStream os = resourceCreator.createResource(resourceName);

      try {
         os.write(data);
      } finally {
         try {
            os.close();
         } catch (IOException var10) {
         }

      }

   }
}
