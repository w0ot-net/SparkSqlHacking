package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class IsDetached extends ClassMethod {
   public static IsDetached getInstance(ClassEnhancer enhancer) {
      return new IsDetached(enhancer, enhancer.getNamer().getIsDetachedMethodName(), 1, Boolean.TYPE, (Object[])null, (String[])null);
   }

   public IsDetached(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      if (this.getClassEnhancer().getClassMetaData().isDetachable()) {
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), "L" + this.getNamer().getStateManagerAsmClassName() + ";");
         Label l1 = new Label();
         this.visitor.visitJumpInsn(199, l1);
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[Ljava/lang/Object;");
         this.visitor.visitJumpInsn(198, l1);
         this.visitor.visitInsn(4);
         this.visitor.visitInsn(172);
         this.visitor.visitLabel(l1);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }

      this.visitor.visitInsn(3);
      this.visitor.visitInsn(172);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitMaxs(1, 1);
      this.visitor.visitEnd();
   }
}
