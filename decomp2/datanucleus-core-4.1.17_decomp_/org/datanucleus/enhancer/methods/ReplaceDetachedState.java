package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;

public class ReplaceDetachedState extends ClassMethod {
   public static ReplaceDetachedState getInstance(ClassEnhancer enhancer) {
      return new ReplaceDetachedState(enhancer, enhancer.getNamer().getReplaceDetachedStateMethodName(), 49, (Object)null, (Object[])null, (String[])null);
   }

   public ReplaceDetachedState(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
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
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[" + EnhanceUtils.CD_Object);
      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "replacingDetachedState", "(L" + this.getNamer().getDetachableAsmClassName() + ";[" + EnhanceUtils.CD_Object + ")[" + EnhanceUtils.CD_Object);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[" + EnhanceUtils.CD_Object);
      this.visitor.visitInsn(177);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitMaxs(4, 1);
      this.visitor.visitEnd();
   }
}
