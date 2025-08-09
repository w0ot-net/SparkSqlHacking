package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import org.apache.xbean.asm9.ClassWriter;
import org.apache.xbean.asm9.MethodVisitor;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class StubClassLoader$ {
   public static final StubClassLoader$ MODULE$ = new StubClassLoader$();

   public StubClassLoader apply(final ClassLoader parent, final Seq binaryName) {
      return new StubClassLoader(parent, (name) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(binaryName, name)));
   }

   public byte[] generateStub(final String binaryName) {
      String name = binaryName.replace('.', '/');
      ClassWriter classWriter = new ClassWriter(0);
      classWriter.visit(49, 33, name, (String)null, "java/lang/Object", (String[])null);
      classWriter.visitSource(name + ".java", (String)null);
      MethodVisitor ctorWriter = classWriter.visitMethod(1, "<init>", "()V", (String)null, (String[])null);
      ctorWriter.visitVarInsn(25, 0);
      ctorWriter.visitMethodInsn(183, "java/lang/Object", "<init>", "()V", false);
      String internalException = "java/lang/ClassNotFoundException";
      ctorWriter.visitTypeInsn(187, internalException);
      ctorWriter.visitInsn(89);
      ctorWriter.visitLdcInsn("Fail to initiate the class " + binaryName + " because it is stubbed. Please install the artifact of the missing class by calling session.addArtifact.");
      ctorWriter.visitMethodInsn(183, internalException, "<init>", "(Ljava/lang/String;)V", false);
      ctorWriter.visitInsn(191);
      ctorWriter.visitMaxs(3, 3);
      ctorWriter.visitEnd();
      classWriter.visitEnd();
      return classWriter.toByteArray();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$2(final String name$2, final String p) {
      return name$2.startsWith(p);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final Seq binaryName$1, final String name) {
      return binaryName$1.exists((p) -> BoxesRunTime.boxToBoolean($anonfun$apply$2(name, p)));
   }

   private StubClassLoader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
