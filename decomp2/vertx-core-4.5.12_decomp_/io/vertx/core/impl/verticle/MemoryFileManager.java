package io.vertx.core.impl.verticle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.JavaFileObject.Kind;

public class MemoryFileManager extends ForwardingJavaFileManager {
   private final Map compiledClasses = new HashMap();
   private final PackageHelper helper;

   public MemoryFileManager(ClassLoader classLoader, JavaFileManager fileManager) {
      super(fileManager);
      this.helper = new PackageHelper(classLoader);
   }

   public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, final String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
      try {
         return new SimpleJavaFileObject(new URI(""), kind) {
            public OutputStream openOutputStream() throws IOException {
               ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
               MemoryFileManager.this.compiledClasses.put(className, outputStream);
               return outputStream;
            }
         };
      } catch (URISyntaxException e) {
         throw new RuntimeException(e);
      }
   }

   public byte[] getCompiledClass(String name) {
      ByteArrayOutputStream bytes = (ByteArrayOutputStream)this.compiledClasses.get(name);
      return bytes == null ? null : bytes.toByteArray();
   }

   public String inferBinaryName(JavaFileManager.Location location, JavaFileObject file) {
      return file instanceof CustomJavaFileObject ? ((CustomJavaFileObject)file).binaryName() : super.inferBinaryName(location, file);
   }

   public Iterable list(JavaFileManager.Location location, String packageName, Set kinds, boolean recurse) throws IOException {
      return (Iterable)(location == StandardLocation.CLASS_PATH && kinds.contains(Kind.CLASS) ? this.helper.find(packageName) : super.list(location, packageName, kinds, recurse));
   }
}
