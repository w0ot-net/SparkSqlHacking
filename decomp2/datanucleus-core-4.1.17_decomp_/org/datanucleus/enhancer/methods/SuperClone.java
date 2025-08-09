package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;

public class SuperClone extends ClassMethod {
   public static SuperClone getInstance(ClassEnhancer enhancer) {
      return new SuperClone(enhancer, enhancer.getNamer().getSuperCloneMethodName(), 2, Object.class, (Object[])null, (String[])null, new String[]{CloneNotSupportedException.class.getName().replace('.', '/')});
   }

   public SuperClone(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames, String[] exceptions) {
      super(enhancer, name, access, returnType, argTypes, argNames, exceptions);
   }

   public void execute() {
      this.visitor.visitCode();
      Label l0 = new Label();
      this.visitor.visitLabel(l0);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitMethodInsn(183, EnhanceUtils.ACN_Object, "clone", "()" + EnhanceUtils.CD_Object);
      this.visitor.visitTypeInsn(192, this.getClassEnhancer().getASMClassName());
      this.visitor.visitVarInsn(58, 1);
      Label l1 = new Label();
      this.visitor.visitLabel(l1);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitInsn(3);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getFlagsFieldName(), "B");
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitInsn(1);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitInsn(176);
      Label l4 = new Label();
      this.visitor.visitLabel(l4);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, l0, l4, 0);
      this.visitor.visitLocalVariable("o", this.getClassEnhancer().getClassDescriptor(), (String)null, l1, l4, 1);
      this.visitor.visitMaxs(2, 2);
      this.visitor.visitEnd();
   }
}
