package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class ReplaceStateManager extends ClassMethod {
   public static ReplaceStateManager getInstance(ClassEnhancer enhancer) {
      return new ReplaceStateManager(enhancer, enhancer.getNamer().getReplaceStateManagerMethodName(), 49, (Object)null, new Class[]{enhancer.getNamer().getStateManagerClass()}, new String[]{"sm"});
   }

   public ReplaceStateManager(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l1 = new Label();
      this.visitor.visitJumpInsn(198, l1);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "replacingStateManager", "(" + this.getNamer().getPersistableDescriptor() + this.getNamer().getStateManagerDescriptor() + ")" + this.getNamer().getStateManagerDescriptor());
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l3 = new Label();
      this.visitor.visitJumpInsn(167, l3);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitMethodInsn(184, this.getNamer().getImplHelperAsmClassName(), "checkAuthorizedStateManager", "(L" + this.getNamer().getStateManagerAsmClassName() + ";)V");
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitInsn(4);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getFlagsFieldName(), "B");
      this.visitor.visitLabel(l3);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitInsn(177);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitLocalVariable(this.argNames[0], this.getNamer().getStateManagerDescriptor(), (String)null, startLabel, endLabel, 1);
      this.visitor.visitMaxs(4, 2);
      this.visitor.visitEnd();
   }
}
