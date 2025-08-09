package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Opcodes;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class ReplaceFields extends ClassMethod {
   public static ReplaceFields getInstance(ClassEnhancer enhancer) {
      return new ReplaceFields(enhancer, enhancer.getNamer().getReplaceFieldsMethodName(), 17, (Object)null, new Class[]{int[].class}, new String[]{"indices"});
   }

   public ReplaceFields(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
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
      this.visitor.visitLdcInsn("argument is null");
      this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitInsn(190);
      this.visitor.visitVarInsn(54, 2);
      Label l3 = new Label();
      this.visitor.visitLabel(l3);
      this.visitor.visitVarInsn(21, 2);
      Label l4 = new Label();
      this.visitor.visitJumpInsn(158, l4);
      this.visitor.visitInsn(3);
      this.visitor.visitVarInsn(54, 3);
      Label l6 = new Label();
      this.visitor.visitLabel(l6);
      this.visitor.visitFrame(1, 2, new Object[]{Opcodes.INTEGER, Opcodes.INTEGER}, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitVarInsn(21, 3);
      this.visitor.visitInsn(46);
      this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getReplaceFieldMethodName(), "(I)V");
      this.visitor.visitIincInsn(3, 1);
      this.visitor.visitVarInsn(21, 3);
      this.visitor.visitVarInsn(21, 2);
      this.visitor.visitJumpInsn(161, l6);
      this.visitor.visitLabel(l4);
      this.visitor.visitFrame(2, 1, (Object[])null, 0, (Object[])null);
      this.visitor.visitInsn(177);
      Label l8 = new Label();
      this.visitor.visitLabel(l8);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, l0, l8, 0);
      this.visitor.visitLocalVariable(this.argNames[0], "[I", (String)null, l0, l8, 1);
      this.visitor.visitLocalVariable("i", "I", (String)null, l3, l8, 2);
      this.visitor.visitLocalVariable("j", "I", (String)null, l6, l4, 3);
      this.visitor.visitMaxs(3, 4);
      this.visitor.visitEnd();
   }
}
