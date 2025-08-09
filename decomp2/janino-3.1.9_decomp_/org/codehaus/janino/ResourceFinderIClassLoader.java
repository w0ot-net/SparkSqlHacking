package org.codehaus.janino;

import java.io.IOException;
import java.io.InputStream;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceFinder;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;

public class ResourceFinderIClassLoader extends IClassLoader {
   private final ResourceFinder resourceFinder;

   public ResourceFinderIClassLoader(ResourceFinder resourceFinder, @Nullable IClassLoader parentIClassLoader) {
      super(parentIClassLoader);
      this.resourceFinder = resourceFinder;
      this.postConstruct();
   }

   @Nullable
   protected IClass findIClass(String descriptor) throws ClassNotFoundException {
      String className = Descriptor.toClassName(descriptor);
      Resource classFileResource = this.resourceFinder.findResource(ClassFile.getClassFileResourceName(className));
      if (classFileResource == null) {
         return null;
      } else {
         InputStream is;
         try {
            is = classFileResource.open();
         } catch (IOException ex) {
            throw new ClassNotFoundException("Opening resource \"" + classFileResource.getFileName() + "\"", ex);
         }

         ClassFile cf;
         try {
            cf = new ClassFile(is);
         } catch (IOException e) {
            throw new ClassNotFoundException("Reading resource \"" + classFileResource.getFileName() + "\"", e);
         } finally {
            try {
               is.close();
            } catch (IOException var14) {
            }

         }

         IClass iClass = new ClassFileIClass(cf, this);
         this.defineIClass(iClass);
         return iClass;
      }
   }
}
