package org.datanucleus.enhancer.methods;

import java.io.IOException;
import java.io.ObjectOutputStream;
import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class WriteObject extends ClassMethod {
   public static WriteObject getInstance(ClassEnhancer enhancer) {
      return new WriteObject(enhancer, "writeObject", 2, (Object)null, new Class[]{ObjectOutputStream.class}, new String[]{"out"}, new String[]{IOException.class.getName().replace('.', '/')});
   }

   public WriteObject(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public WriteObject(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames, String[] exceptions) {
      super(enhancer, name, access, returnType, argTypes, argNames, exceptions);
   }

   public void execute() {
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getPreSerializeMethodName(), "()V");
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitMethodInsn(182, "java/io/ObjectOutputStream", "defaultWriteObject", "()V");
      this.visitor.visitInsn(177);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitLocalVariable(this.argNames[0], "Ljava/io/ObjectOutputStream;", (String)null, startLabel, endLabel, 1);
      this.visitor.visitMaxs(1, 2);
      this.visitor.visitEnd();
   }
}
