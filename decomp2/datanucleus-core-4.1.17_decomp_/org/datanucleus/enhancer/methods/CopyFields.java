package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Opcodes;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;

public class CopyFields extends ClassMethod {
   public static CopyFields getInstance(ClassEnhancer enhancer) {
      return new CopyFields(enhancer, enhancer.getNamer().getCopyFieldsMethodName(), 1, (Object)null, new Class[]{Object.class, int[].class}, new String[]{"obj", "indices"});
   }

   public CopyFields(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label l0 = new Label();
      this.visitor.visitLabel(l0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l1 = new Label();
      this.visitor.visitJumpInsn(199, l1);
      this.visitor.visitTypeInsn(187, "java/lang/IllegalStateException");
      this.visitor.visitInsn(89);
      this.visitor.visitLdcInsn("state manager is null");
      this.visitor.visitMethodInsn(183, "java/lang/IllegalStateException", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 2);
      Label l3 = new Label();
      this.visitor.visitJumpInsn(199, l3);
      this.visitor.visitTypeInsn(187, "java/lang/IllegalStateException");
      this.visitor.visitInsn(89);
      this.visitor.visitLdcInsn("fieldNumbers is null");
      this.visitor.visitMethodInsn(183, "java/lang/IllegalStateException", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      this.visitor.visitLabel(l3);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitTypeInsn(193, this.getClassEnhancer().getASMClassName());
      Label l5 = new Label();
      this.visitor.visitJumpInsn(154, l5);
      this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
      this.visitor.visitInsn(89);
      this.visitor.visitLdcInsn("object is not an object of type " + this.getClassEnhancer().getASMClassName().replace('/', '.'));
      this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      this.visitor.visitLabel(l5);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitTypeInsn(192, this.getClassEnhancer().getASMClassName());
      this.visitor.visitVarInsn(58, 3);
      Label l9 = new Label();
      this.visitor.visitLabel(l9);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 3);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l10 = new Label();
      this.visitor.visitJumpInsn(165, l10);
      this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
      this.visitor.visitInsn(89);
      this.visitor.visitLdcInsn("state managers do not match");
      this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
      this.visitor.visitInsn(191);
      this.visitor.visitLabel(l10);
      this.visitor.visitFrame(1, 1, new Object[]{this.getClassEnhancer().getASMClassName()}, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 2);
      this.visitor.visitInsn(190);
      this.visitor.visitInsn(4);
      this.visitor.visitInsn(100);
      this.visitor.visitVarInsn(54, 4);
      Label l12 = new Label();
      this.visitor.visitLabel(l12);
      this.visitor.visitVarInsn(21, 4);
      Label l13 = new Label();
      this.visitor.visitJumpInsn(155, l13);
      Label l14 = new Label();
      this.visitor.visitLabel(l14);
      this.visitor.visitFrame(1, 1, new Object[]{Opcodes.INTEGER}, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 3);
      this.visitor.visitVarInsn(25, 2);
      this.visitor.visitVarInsn(21, 4);
      this.visitor.visitInsn(46);
      this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getCopyFieldMethodName(), "(" + this.getClassEnhancer().getClassDescriptor() + "I)V");
      this.visitor.visitIincInsn(4, -1);
      this.visitor.visitVarInsn(21, 4);
      this.visitor.visitJumpInsn(156, l14);
      this.visitor.visitLabel(l13);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitInsn(177);
      Label l16 = new Label();
      this.visitor.visitLabel(l16);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, l0, l16, 0);
      this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, l0, l16, 1);
      this.visitor.visitLocalVariable(this.argNames[1], "[I", (String)null, l0, l16, 2);
      this.visitor.visitLocalVariable("other", this.getClassEnhancer().getClassDescriptor(), (String)null, l9, l16, 3);
      this.visitor.visitLocalVariable("i", "I", (String)null, l12, l16, 4);
      this.visitor.visitMaxs(4, 5);
      this.visitor.visitEnd();
   }
}
