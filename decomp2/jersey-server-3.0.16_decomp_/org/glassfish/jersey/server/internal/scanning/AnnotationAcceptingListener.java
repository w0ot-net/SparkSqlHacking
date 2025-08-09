package org.glassfish.jersey.server.internal.scanning;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import jersey.repackaged.org.objectweb.asm.AnnotationVisitor;
import jersey.repackaged.org.objectweb.asm.Attribute;
import jersey.repackaged.org.objectweb.asm.ClassReader;
import jersey.repackaged.org.objectweb.asm.ClassVisitor;
import jersey.repackaged.org.objectweb.asm.FieldVisitor;
import jersey.repackaged.org.objectweb.asm.MethodVisitor;
import jersey.repackaged.org.objectweb.asm.ModuleVisitor;
import jersey.repackaged.org.objectweb.asm.RecordComponentVisitor;
import jersey.repackaged.org.objectweb.asm.TypePath;
import org.glassfish.jersey.internal.OsgiRegistry;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.server.internal.LocalizationMessages;

public final class AnnotationAcceptingListener implements ResourceProcessor {
   private final ClassLoader classloader;
   private final Set classes;
   private final Set annotations;
   private final AnnotatedClassVisitor classVisitor;

   public static AnnotationAcceptingListener newJaxrsResourceAndProviderListener() {
      return new AnnotationAcceptingListener(new Class[]{Path.class, Provider.class});
   }

   public static AnnotationAcceptingListener newJaxrsResourceAndProviderListener(ClassLoader classLoader) {
      return new AnnotationAcceptingListener(classLoader, new Class[]{Path.class, Provider.class});
   }

   public AnnotationAcceptingListener(Class... annotations) {
      this((ClassLoader)AccessController.doPrivileged(ReflectionHelper.getContextClassLoaderPA()), annotations);
   }

   public AnnotationAcceptingListener(ClassLoader classloader, Class... annotations) {
      this.classloader = classloader;
      this.classes = new LinkedHashSet();
      this.annotations = this.getAnnotationSet(annotations);
      this.classVisitor = new AnnotatedClassVisitor();
   }

   public Set getAnnotatedClasses() {
      return this.classes;
   }

   private Set getAnnotationSet(Class... annotations) {
      Set<String> a = new HashSet();

      for(Class c : annotations) {
         a.add("L" + c.getName().replaceAll("\\.", "/") + ";");
      }

      return a;
   }

   public boolean accept(String name) {
      return name != null && !name.isEmpty() && name.endsWith(".class");
   }

   public void process(String name, InputStream in) throws IOException {
      (new ClassReaderWrapper(in)).accept(this.classVisitor, 0);
   }

   private final class AnnotatedClassVisitor extends ClassVisitor {
      private String className;
      private boolean isScoped;
      private boolean isAnnotated;

      private AnnotatedClassVisitor() {
         super(589824);
      }

      public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
         this.className = name;
         this.isScoped = (access & 1) != 0;
         this.isAnnotated = false;
      }

      public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
         this.isAnnotated |= AnnotationAcceptingListener.this.annotations.contains(desc);
         return null;
      }

      public void visitInnerClass(String name, String outerName, String innerName, int access) {
         if (this.className.equals(name)) {
            this.isScoped = (access & 1) != 0;
            this.isScoped &= (access & 8) == 8;
         }

      }

      public void visitEnd() {
         if (this.isScoped && this.isAnnotated) {
            AnnotationAcceptingListener.this.classes.add(this.getClassForName(this.className.replaceAll("/", ".")));
         }

      }

      public void visitOuterClass(String string, String string0, String string1) {
      }

      public FieldVisitor visitField(int i, String string, String string0, String string1, Object object) {
         return null;
      }

      public void visitSource(String string, String string0) {
      }

      public void visitAttribute(Attribute attribute) {
      }

      public MethodVisitor visitMethod(int i, String string, String string0, String string1, String[] string2) {
         return null;
      }

      public ModuleVisitor visitModule(String name, int access, String version) {
         return null;
      }

      public void visitNestHost(String nestHost) {
      }

      public void visitNestMember(String nestMember) {
      }

      public void visitPermittedSubclass(String permittedSubclass) {
      }

      public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
         return null;
      }

      public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
         return null;
      }

      public ClassVisitor getDelegate() {
         return null;
      }

      private Class getClassForName(String className) {
         try {
            OsgiRegistry osgiRegistry = ReflectionHelper.getOsgiRegistryInstance();
            return osgiRegistry != null ? osgiRegistry.classForNameWithException(className) : (Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(className, AnnotationAcceptingListener.this.classloader));
         } catch (ClassNotFoundException ex) {
            throw new RuntimeException(LocalizationMessages.ERROR_SCANNING_CLASS_NOT_FOUND(className), ex);
         } catch (PrivilegedActionException pae) {
            Throwable cause = pae.getCause();
            if (cause instanceof ClassNotFoundException) {
               throw new RuntimeException(LocalizationMessages.ERROR_SCANNING_CLASS_NOT_FOUND(className), cause);
            } else if (cause instanceof RuntimeException) {
               throw (RuntimeException)cause;
            } else {
               throw new RuntimeException(cause);
            }
         }
      }
   }

   private static class ClassReaderWrapper {
      private static final Logger LOGGER = Logger.getLogger(ClassReader.class.getName());
      private static final int WARN_VERSION = 67;
      private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
      private final byte[] b;

      private ClassReaderWrapper(InputStream inputStream) throws IOException {
         this.b = readStream(inputStream);
      }

      private void accept(ClassVisitor classVisitor, int parsingOptions) {
         int originalVersion = getMajorVersion(this.b);
         if (originalVersion > 67) {
            setMajorVersion(67, this.b);
            LOGGER.warning("Unsupported class file major version " + originalVersion);
         }

         ClassReader classReader = new ClassReader(this.b);
         setMajorVersion(originalVersion, this.b);
         classReader.accept(classVisitor, parsingOptions);
      }

      private static void setMajorVersion(int majorVersion, byte[] b) {
         b[6] = (byte)(majorVersion >>> 8);
         b[7] = (byte)majorVersion;
      }

      private static int getMajorVersion(byte[] b) {
         return (b[6] & 255) << 8 | b[7] & 255;
      }

      private static byte[] readStream(InputStream inputStream) throws IOException {
         if (inputStream == null) {
            throw new IOException("Class not found");
         } else {
            byte[] var5;
            try {
               ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
               Throwable var2 = null;

               try {
                  byte[] data = new byte[4096];

                  int bytesRead;
                  while((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                     outputStream.write(data, 0, bytesRead);
                  }

                  outputStream.flush();
                  var5 = outputStream.toByteArray();
               } catch (Throwable var21) {
                  var2 = var21;
                  throw var21;
               } finally {
                  if (outputStream != null) {
                     if (var2 != null) {
                        try {
                           outputStream.close();
                        } catch (Throwable var20) {
                           var2.addSuppressed(var20);
                        }
                     } else {
                        outputStream.close();
                     }
                  }

               }
            } finally {
               inputStream.close();
            }

            return var5;
         }
      }
   }
}
