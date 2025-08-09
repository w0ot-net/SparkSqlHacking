package com.esotericsoftware.reflectasm;

import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public abstract class ConstructorAccess {
   boolean isNonStaticMemberClass;

   public boolean isNonStaticMemberClass() {
      return this.isNonStaticMemberClass;
   }

   public abstract Object newInstance();

   public abstract Object newInstance(Object var1);

   public static ConstructorAccess get(Class type) {
      Class enclosingType = type.getEnclosingClass();
      boolean isNonStaticMemberClass = enclosingType != null && type.isMemberClass() && !Modifier.isStatic(type.getModifiers());
      String className = type.getName();
      String accessClassName = className + "ConstructorAccess";
      if (accessClassName.startsWith("java.")) {
         accessClassName = "reflectasm." + accessClassName;
      }

      AccessClassLoader loader = AccessClassLoader.get(type);

      Class accessClass;
      try {
         accessClass = loader.loadClass(accessClassName);
      } catch (ClassNotFoundException var23) {
         synchronized(loader) {
            try {
               accessClass = loader.loadClass(accessClassName);
            } catch (ClassNotFoundException var21) {
               String accessClassNameInternal = accessClassName.replace('.', '/');
               String classNameInternal = className.replace('.', '/');
               Constructor<T> constructor = null;
               int modifiers = 0;
               String enclosingClassNameInternal;
               if (!isNonStaticMemberClass) {
                  enclosingClassNameInternal = null;

                  try {
                     constructor = type.getDeclaredConstructor((Class[])null);
                     modifiers = constructor.getModifiers();
                  } catch (Exception ex) {
                     throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + type.getName(), ex);
                  }

                  if (Modifier.isPrivate(modifiers)) {
                     throw new RuntimeException("Class cannot be created (the no-arg constructor is private): " + type.getName());
                  }
               } else {
                  enclosingClassNameInternal = enclosingType.getName().replace('.', '/');

                  try {
                     constructor = type.getDeclaredConstructor(enclosingType);
                     modifiers = constructor.getModifiers();
                  } catch (Exception ex) {
                     throw new RuntimeException("Non-static member class cannot be created (missing enclosing class constructor): " + type.getName(), ex);
                  }

                  if (Modifier.isPrivate(modifiers)) {
                     throw new RuntimeException("Non-static member class cannot be created (the enclosing class constructor is private): " + type.getName());
                  }
               }

               String superclassNameInternal = Modifier.isPublic(modifiers) ? "com/esotericsoftware/reflectasm/PublicConstructorAccess" : "com/esotericsoftware/reflectasm/ConstructorAccess";
               ClassWriter cw = new ClassWriter(0);
               cw.visit(196653, 33, accessClassNameInternal, (String)null, superclassNameInternal, (String[])null);
               insertConstructor(cw, superclassNameInternal);
               insertNewInstance(cw, classNameInternal);
               insertNewInstanceInner(cw, classNameInternal, enclosingClassNameInternal);
               cw.visitEnd();
               accessClass = loader.defineClass(accessClassName, cw.toByteArray());
            }
         }
      }

      ConstructorAccess<T> access;
      try {
         access = (ConstructorAccess)accessClass.newInstance();
      } catch (Throwable t) {
         throw new RuntimeException("Exception constructing constructor access class: " + accessClassName, t);
      }

      if (!(access instanceof PublicConstructorAccess) && !AccessClassLoader.areInSameRuntimeClassLoader(type, accessClass)) {
         throw new RuntimeException((!isNonStaticMemberClass ? "Class cannot be created (the no-arg constructor is protected or package-protected, and its ConstructorAccess could not be defined in the same class loader): " : "Non-static member class cannot be created (the enclosing class constructor is protected or package-protected, and its ConstructorAccess could not be defined in the same class loader): ") + type.getName());
      } else {
         access.isNonStaticMemberClass = isNonStaticMemberClass;
         return access;
      }
   }

   private static void insertConstructor(ClassWriter cw, String superclassNameInternal) {
      MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", (String)null, (String[])null);
      mv.visitCode();
      mv.visitVarInsn(25, 0);
      mv.visitMethodInsn(183, superclassNameInternal, "<init>", "()V");
      mv.visitInsn(177);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
   }

   static void insertNewInstance(ClassWriter cw, String classNameInternal) {
      MethodVisitor mv = cw.visitMethod(1, "newInstance", "()Ljava/lang/Object;", (String)null, (String[])null);
      mv.visitCode();
      mv.visitTypeInsn(187, classNameInternal);
      mv.visitInsn(89);
      mv.visitMethodInsn(183, classNameInternal, "<init>", "()V");
      mv.visitInsn(176);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
   }

   static void insertNewInstanceInner(ClassWriter cw, String classNameInternal, String enclosingClassNameInternal) {
      MethodVisitor mv = cw.visitMethod(1, "newInstance", "(Ljava/lang/Object;)Ljava/lang/Object;", (String)null, (String[])null);
      mv.visitCode();
      if (enclosingClassNameInternal != null) {
         mv.visitTypeInsn(187, classNameInternal);
         mv.visitInsn(89);
         mv.visitVarInsn(25, 1);
         mv.visitTypeInsn(192, enclosingClassNameInternal);
         mv.visitInsn(89);
         mv.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
         mv.visitInsn(87);
         mv.visitMethodInsn(183, classNameInternal, "<init>", "(L" + enclosingClassNameInternal + ";)V");
         mv.visitInsn(176);
         mv.visitMaxs(4, 2);
      } else {
         mv.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
         mv.visitInsn(89);
         mv.visitLdcInsn("Not an inner class.");
         mv.visitMethodInsn(183, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V");
         mv.visitInsn(191);
         mv.visitMaxs(3, 2);
      }

      mv.visitEnd();
   }
}
