package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class LoadClass extends ClassMethod {
   public static LoadClass getInstance(ClassEnhancer enhancer) {
      return new LoadClass(enhancer, enhancer.getNamer().getLoadClassMethodName(), 9, Class.class, new Class[]{String.class}, new String[]{"className"});
   }

   public LoadClass(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label l0 = new Label();
      Label l1 = new Label();
      Label l2 = new Label();
      this.visitor.visitTryCatchBlock(l0, l1, l2, "java/lang/ClassNotFoundException");
      this.visitor.visitLabel(l0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitMethodInsn(184, "java/lang/Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
      this.visitor.visitLabel(l1);
      this.visitor.visitInsn(176);
      this.visitor.visitLabel(l2);
      this.visitor.visitFrame(4, 0, (Object[])null, 1, new Object[]{"java/lang/ClassNotFoundException"});
      this.visitor.visitVarInsn(58, 1);
      Label l3 = new Label();
      this.visitor.visitLabel(l3);
      this.visitor.visitTypeInsn(187, "java/lang/NoClassDefFoundError");
      this.visitor.visitInsn(89);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitMethodInsn(182, "java/lang/ClassNotFoundException", "getMessage", "()Ljava/lang/String;");
      this.visitor.visitMethodInsn(183, "java/lang/NoClassDefFoundError", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      Label l4 = new Label();
      this.visitor.visitLabel(l4);
      this.visitor.visitLocalVariable(this.argNames[0], "Ljava/lang/String;", (String)null, l0, l4, 0);
      this.visitor.visitLocalVariable("e", "Ljava/lang/ClassNotFoundException;", (String)null, l3, l4, 1);
      this.visitor.visitMaxs(3, 2);
      this.visitor.visitEnd();
   }
}
