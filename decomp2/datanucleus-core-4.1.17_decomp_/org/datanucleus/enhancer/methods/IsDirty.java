package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;

public class IsDirty extends ClassMethod {
   public static IsDirty getInstance(ClassEnhancer enhancer) {
      return new IsDirty(enhancer, enhancer.getNamer().getIsDirtyMethodName(), 17, Boolean.TYPE, (Object[])null, (String[])null);
   }

   public IsDirty(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      boolean detachable = this.enhancer.getClassMetaData().isDetachable();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l1 = new Label();
      this.visitor.visitJumpInsn(198, l1);
      Label l2 = new Label();
      this.visitor.visitLabel(l2);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "isDirty", "(" + this.getNamer().getPersistableDescriptor() + ")Z");
      this.visitor.visitInsn(172);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      if (!detachable) {
         this.visitor.visitInsn(3);
         this.visitor.visitInsn(172);
      } else {
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getIsDetachedMethodName(), "()Z");
         Label l3 = new Label();
         this.visitor.visitJumpInsn(154, l3);
         this.visitor.visitInsn(3);
         this.visitor.visitInsn(172);
         this.visitor.visitLabel(l3);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[" + EnhanceUtils.CD_Object);
         this.visitor.visitInsn(6);
         this.visitor.visitInsn(50);
         this.visitor.visitTypeInsn(192, "java/util/BitSet");
         this.visitor.visitMethodInsn(182, "java/util/BitSet", "length", "()I");
         Label l5 = new Label();
         this.visitor.visitJumpInsn(157, l5);
         this.visitor.visitInsn(3);
         this.visitor.visitInsn(172);
         this.visitor.visitLabel(l5);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitInsn(4);
         this.visitor.visitInsn(172);
      }

      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitMaxs(2, 1);
      this.visitor.visitEnd();
   }
}
