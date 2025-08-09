package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class GetExecutionContext extends ClassMethod {
   public static GetExecutionContext getInstance(ClassEnhancer enhancer) {
      return new GetExecutionContext(enhancer, enhancer.getNamer().getGetExecutionContextMethodName(), 17, enhancer.getNamer().getExecutionContextClass(), (Object[])null, (String[])null);
   }

   public GetExecutionContext(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label l0 = new Label();
      this.visitor.visitLabel(l0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l1 = new Label();
      this.visitor.visitJumpInsn(198, l1);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "getExecutionContext", "(" + this.getNamer().getPersistableDescriptor() + ")" + this.getNamer().getExecutionContextDescriptor());
      Label l2 = new Label();
      this.visitor.visitJumpInsn(167, l2);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitInsn(1);
      this.visitor.visitLabel(l2);
      this.visitor.visitFrame(4, 0, (Object[])null, 1, new Object[]{this.getClassEnhancer().getNamer().getExecutionContextAsmClassName()});
      this.visitor.visitInsn(176);
      Label l3 = new Label();
      this.visitor.visitLabel(l3);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, l0, l3, 0);
      this.visitor.visitMaxs(2, 1);
      this.visitor.visitEnd();
   }
}
