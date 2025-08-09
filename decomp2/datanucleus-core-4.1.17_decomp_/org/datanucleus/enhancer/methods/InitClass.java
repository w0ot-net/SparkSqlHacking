package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class InitClass extends ClassMethod {
   public static InitClass getInstance(ClassEnhancer enhancer) {
      return new InitClass(enhancer, "<clinit>", 8, (Object)null, (Object[])null, (String[])null);
   }

   public InitClass(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      this.addInitialiseInstructions(this.visitor);
      this.visitor.visitInsn(177);
      this.visitor.visitMaxs(7, 0);
      this.visitor.visitEnd();
   }

   public void addInitialiseInstructions(MethodVisitor mv) {
      mv.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldNamesInitMethodName(), "()[Ljava/lang/String;");
      mv.visitFieldInsn(179, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldNamesFieldName(), "[Ljava/lang/String;");
      mv.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldTypesInitMethodName(), "()[Ljava/lang/Class;");
      mv.visitFieldInsn(179, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldTypesFieldName(), "[Ljava/lang/Class;");
      mv.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldFlagsInitMethodName(), "()[B");
      mv.visitFieldInsn(179, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldFlagsFieldName(), "[B");
      mv.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetInheritedFieldCountMethodName(), "()I");
      mv.visitFieldInsn(179, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
      mv.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getPersistableSuperclassInitMethodName(), "()Ljava/lang/Class;");
      mv.visitFieldInsn(179, this.getClassEnhancer().getASMClassName(), this.getNamer().getPersistableSuperclassFieldName(), "Ljava/lang/Class;");
      mv.visitLdcInsn(this.getClassEnhancer().getClassName());
      mv.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getLoadClassMethodName(), "(Ljava/lang/String;)Ljava/lang/Class;");
      mv.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldNamesFieldName(), "[Ljava/lang/String;");
      mv.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldTypesFieldName(), "[Ljava/lang/Class;");
      mv.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldFlagsFieldName(), "[B");
      mv.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getPersistableSuperclassFieldName(), "Ljava/lang/Class;");
      if (this.enhancer.getClassMetaData().isAbstract()) {
         mv.visitInsn(1);
      } else {
         mv.visitTypeInsn(187, this.getClassEnhancer().getASMClassName());
         mv.visitInsn(89);
         mv.visitMethodInsn(183, this.getClassEnhancer().getASMClassName(), "<init>", "()V");
      }

      mv.visitMethodInsn(184, this.getNamer().getImplHelperAsmClassName(), "registerClass", "(Ljava/lang/Class;[Ljava/lang/String;[Ljava/lang/Class;[BLjava/lang/Class;L" + this.getNamer().getPersistableAsmClassName() + ";)V");
   }
}
