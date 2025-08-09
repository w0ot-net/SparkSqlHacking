package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;

public class NewInstance2 extends ClassMethod {
   public static NewInstance2 getInstance(ClassEnhancer enhancer) {
      return new NewInstance2(enhancer, enhancer.getNamer().getNewInstanceMethodName(), 1, enhancer.getNamer().getPersistableClass(), new Class[]{enhancer.getNamer().getStateManagerClass(), Object.class}, new String[]{"sm", "obj"});
   }

   public NewInstance2(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      if (this.enhancer.getClassMetaData().isAbstract()) {
         this.visitor.visitTypeInsn(187, this.getNamer().getFatalInternalExceptionAsmClassName());
         this.visitor.visitInsn(89);
         this.visitor.visitLdcInsn("Cannot instantiate abstract class.");
         this.visitor.visitMethodInsn(183, this.getNamer().getFatalInternalExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
         this.visitor.visitInsn(191);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], this.getNamer().getStateManagerDescriptor(), (String)null, startLabel, endLabel, 1);
         this.visitor.visitLocalVariable(this.argNames[1], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 2);
         this.visitor.visitMaxs(3, 3);
      } else {
         this.visitor.visitTypeInsn(187, this.getClassEnhancer().getASMClassName());
         this.visitor.visitInsn(89);
         this.visitor.visitMethodInsn(183, this.getClassEnhancer().getASMClassName(), "<init>", "()V");
         this.visitor.visitVarInsn(58, 3);
         Label l1 = new Label();
         this.visitor.visitLabel(l1);
         this.visitor.visitVarInsn(25, 3);
         this.visitor.visitInsn(4);
         this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getFlagsFieldName(), "B");
         this.visitor.visitVarInsn(25, 3);
         this.visitor.visitVarInsn(25, 1);
         this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
         this.visitor.visitVarInsn(25, 3);
         this.visitor.visitVarInsn(25, 2);
         this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getCopyKeyFieldsFromObjectIdMethodName(), "(" + EnhanceUtils.CD_Object + ")V");
         this.visitor.visitVarInsn(25, 3);
         this.visitor.visitInsn(176);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], this.getNamer().getStateManagerDescriptor(), (String)null, startLabel, endLabel, 1);
         this.visitor.visitLocalVariable(this.argNames[1], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 2);
         this.visitor.visitLocalVariable("result", this.getClassEnhancer().getClassDescriptor(), (String)null, l1, endLabel, 3);
         this.visitor.visitMaxs(2, 4);
      }

      this.visitor.visitEnd();
   }
}
