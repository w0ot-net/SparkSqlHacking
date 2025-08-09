package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Opcodes;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class ProvideFields extends ClassMethod {
   public static ProvideFields getInstance(ClassEnhancer enhancer) {
      return new ProvideFields(enhancer, enhancer.getNamer().getProvideFieldsMethodName(), 17, (Object)null, new Class[]{int[].class}, new String[]{"indices"});
   }

   public ProvideFields(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label l0 = new Label();
      this.visitor.visitLabel(l0);
      this.visitor.visitVarInsn(25, 1);
      Label l1 = new Label();
      this.visitor.visitJumpInsn(199, l1);
      this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
      this.visitor.visitInsn(89);
      this.visitor.visitLdcInsn("argment is null");
      this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitInsn(190);
      this.visitor.visitInsn(4);
      this.visitor.visitInsn(100);
      this.visitor.visitVarInsn(54, 2);
      Label l3 = new Label();
      this.visitor.visitLabel(l3);
      this.visitor.visitVarInsn(21, 2);
      Label l4 = new Label();
      this.visitor.visitJumpInsn(155, l4);
      Label l5 = new Label();
      this.visitor.visitLabel(l5);
      this.visitor.visitFrame(1, 1, new Object[]{Opcodes.INTEGER}, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitVarInsn(21, 2);
      this.visitor.visitInsn(46);
      this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getProvideFieldMethodName(), "(I)V");
      this.visitor.visitIincInsn(2, -1);
      this.visitor.visitVarInsn(21, 2);
      this.visitor.visitJumpInsn(156, l5);
      this.visitor.visitLabel(l4);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitInsn(177);
      Label l7 = new Label();
      this.visitor.visitLabel(l7);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, l0, l7, 0);
      this.visitor.visitLocalVariable(this.argNames[0], "[I", (String)null, l0, l7, 1);
      this.visitor.visitLocalVariable("i", "I", (String)null, l3, l7, 2);
      this.visitor.visitMaxs(3, 3);
      this.visitor.visitEnd();
   }
}
