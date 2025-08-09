package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.DataNucleusEnhancer;
import org.datanucleus.util.Localiser;

public class DefaultConstructor extends ClassMethod {
   public static DefaultConstructor getInstance(ClassEnhancer enhancer) {
      return new DefaultConstructor(enhancer, "<init>", 4, (Object)null, (Object[])null, (String[])null);
   }

   public DefaultConstructor(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label l0 = new Label();
      this.visitor.visitLabel(l0);
      this.visitor.visitVarInsn(25, 0);
      Class superclass = this.enhancer.getClassBeingEnhanced().getSuperclass();
      this.visitor.visitMethodInsn(183, superclass.getName().replace('.', '/'), "<init>", "()V");
      this.visitor.visitInsn(177);
      Label l1 = new Label();
      this.visitor.visitLabel(l1);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, l0, l1, 0);
      this.visitor.visitMaxs(1, 1);
      this.visitor.visitEnd();
   }

   public void close() {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005020", this.getClassEnhancer().getClassName() + "()"));
      }

   }
}
