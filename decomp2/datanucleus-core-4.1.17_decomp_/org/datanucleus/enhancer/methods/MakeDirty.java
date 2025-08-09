package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Opcodes;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.metadata.AbstractClassMetaData;

public class MakeDirty extends ClassMethod {
   public static MakeDirty getInstance(ClassEnhancer enhancer) {
      return new MakeDirty(enhancer, enhancer.getNamer().getMakeDirtyMethodName(), 1, (Object)null, new Class[]{String.class}, new String[]{"fieldName"});
   }

   public MakeDirty(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      AbstractClassMetaData cmd = this.getClassEnhancer().getClassMetaData();
      String pcSuperclassName = cmd.getPersistableSuperclass();
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      Label l1 = new Label();
      this.visitor.visitJumpInsn(198, l1);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitVarInsn(25, 1);
      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "makeDirty", "(" + this.getNamer().getPersistableDescriptor() + "Ljava/lang/String;)V");
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      if (cmd.isDetachable()) {
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getIsDetachedMethodName(), "()Z");
         Label l3 = new Label();
         this.visitor.visitJumpInsn(153, l3);
         this.visitor.visitVarInsn(25, 1);
         this.visitor.visitJumpInsn(198, l3);
         this.visitor.visitInsn(1);
         this.visitor.visitVarInsn(58, 2);
         Label l5 = new Label();
         this.visitor.visitLabel(l5);
         this.visitor.visitVarInsn(25, 1);
         this.visitor.visitIntInsn(16, 46);
         this.visitor.visitMethodInsn(182, "java/lang/String", "indexOf", "(I)I");
         Label l6 = new Label();
         this.visitor.visitJumpInsn(155, l6);
         this.visitor.visitVarInsn(25, 1);
         this.visitor.visitVarInsn(25, 1);
         this.visitor.visitIntInsn(16, 46);
         this.visitor.visitMethodInsn(182, "java/lang/String", "lastIndexOf", "(I)I");
         this.visitor.visitInsn(4);
         this.visitor.visitInsn(96);
         this.visitor.visitMethodInsn(182, "java/lang/String", "substring", "(I)Ljava/lang/String;");
         this.visitor.visitVarInsn(58, 2);
         Label l8 = new Label();
         this.visitor.visitJumpInsn(167, l8);
         this.visitor.visitLabel(l6);
         this.visitor.visitFrame(1, 1, new Object[]{"java/lang/String"}, 0, (Object[])null);
         this.visitor.visitVarInsn(25, 1);
         this.visitor.visitVarInsn(58, 2);
         this.visitor.visitLabel(l8);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitInsn(3);
         this.visitor.visitVarInsn(54, 3);
         Label l9 = new Label();
         this.visitor.visitLabel(l9);
         Label l10 = new Label();
         this.visitor.visitJumpInsn(167, l10);
         Label l11 = new Label();
         this.visitor.visitLabel(l11);
         this.visitor.visitFrame(1, 1, new Object[]{Opcodes.INTEGER}, 0, (Object[])null);
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldNamesFieldName(), "[Ljava/lang/String;");
         this.visitor.visitVarInsn(21, 3);
         this.visitor.visitInsn(50);
         this.visitor.visitVarInsn(25, 2);
         this.visitor.visitMethodInsn(182, "java/lang/String", "equals", "(Ljava/lang/Object;)Z");
         Label l12 = new Label();
         this.visitor.visitJumpInsn(153, l12);
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[Ljava/lang/Object;");
         this.visitor.visitInsn(5);
         this.visitor.visitInsn(50);
         this.visitor.visitTypeInsn(192, "java/util/BitSet");
         this.visitor.visitVarInsn(21, 3);
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
         this.visitor.visitInsn(96);
         this.visitor.visitMethodInsn(182, "java/util/BitSet", "get", "(I)Z");
         Label l14 = new Label();
         this.visitor.visitJumpInsn(153, l14);
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[Ljava/lang/Object;");
         this.visitor.visitInsn(6);
         this.visitor.visitInsn(50);
         this.visitor.visitTypeInsn(192, "java/util/BitSet");
         this.visitor.visitVarInsn(21, 3);
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
         this.visitor.visitInsn(96);
         this.visitor.visitMethodInsn(182, "java/util/BitSet", "set", "(I)V");
         this.visitor.visitInsn(177);
         this.visitor.visitLabel(l14);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         if (this.enhancer.hasOption("generate-detach-listener")) {
            this.visitor.visitMethodInsn(184, this.getNamer().getDetachListenerAsmClassName(), "getInstance", "()L" + this.getNamer().getDetachListenerAsmClassName() + ";");
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitLdcInsn("field/property");
            this.visitor.visitMethodInsn(182, this.getNamer().getDetachListenerAsmClassName(), "undetachedFieldAccess", "(Ljava/lang/Object;Ljava/lang/String;)V");
         } else {
            this.visitor.visitTypeInsn(187, this.getNamer().getDetachedFieldAccessExceptionAsmClassName());
            this.visitor.visitInsn(89);
            this.visitor.visitLdcInsn("You have just attempted to access a field/property that hasn't been detached. Please detach it first before performing this operation");
            this.visitor.visitMethodInsn(183, this.getNamer().getDetachedFieldAccessExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
            this.visitor.visitInsn(191);
         }

         this.visitor.visitLabel(l12);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitIincInsn(3, 1);
         this.visitor.visitLabel(l10);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitVarInsn(21, 3);
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getFieldNamesFieldName(), "[Ljava/lang/String;");
         this.visitor.visitInsn(190);
         this.visitor.visitJumpInsn(161, l11);
         this.visitor.visitLabel(l3);
         this.visitor.visitFrame(2, 2, (Object[])null, 0, (Object[])null);
         if (pcSuperclassName != null) {
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitVarInsn(25, 1);
            this.visitor.visitMethodInsn(183, pcSuperclassName.replace('.', '/'), this.getNamer().getMakeDirtyMethodName(), "(Ljava/lang/String;)V");
         }

         this.visitor.visitInsn(177);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], "Ljava/lang/String;", (String)null, startLabel, endLabel, 1);
         this.visitor.visitLocalVariable("fldName", "Ljava/lang/String;", (String)null, l5, l3, 2);
         this.visitor.visitLocalVariable("i", "I", (String)null, l9, l3, 3);
         this.visitor.visitMaxs(3, 4);
      } else {
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitInsn(177);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], "Ljava/lang/String;", (String)null, startLabel, endLabel, 1);
         this.visitor.visitMaxs(3, 2);
      }

      this.visitor.visitEnd();
   }
}
