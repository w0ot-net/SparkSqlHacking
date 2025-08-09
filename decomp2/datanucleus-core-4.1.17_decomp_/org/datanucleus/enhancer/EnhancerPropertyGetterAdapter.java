package org.datanucleus.enhancer;

import org.datanucleus.asm.AnnotationVisitor;
import org.datanucleus.asm.Attribute;
import org.datanucleus.asm.ClassVisitor;
import org.datanucleus.asm.Handle;
import org.datanucleus.asm.Label;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;
import org.datanucleus.asm.TypePath;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;

public class EnhancerPropertyGetterAdapter extends MethodVisitor {
   protected ClassEnhancer enhancer;
   protected String methodName;
   protected String methodDescriptor;
   protected AbstractMemberMetaData mmd;
   protected MethodVisitor visitor = null;

   public EnhancerPropertyGetterAdapter(MethodVisitor mv, ClassEnhancer enhancer, String methodName, String methodDesc, AbstractMemberMetaData mmd, ClassVisitor cv) {
      super(327680, mv);
      this.enhancer = enhancer;
      this.methodName = methodName;
      this.methodDescriptor = methodDesc;
      this.mmd = mmd;
      int access = (mmd.isPublic() ? 1 : 0) | (mmd.isProtected() ? 4 : 0) | (mmd.isPrivate() ? 2 : 0) | (mmd.isAbstract() ? 1024 : 0);
      this.visitor = cv.visitMethod(access, enhancer.getNamer().getGetMethodPrefixMethodName() + mmd.getName(), methodDesc, (String)null, (String[])null);
   }

   public void visitEnd() {
      this.visitor.visitEnd();
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         String msg = ClassMethod.getMethodAdditionMessage(this.enhancer.getNamer().getGetMethodPrefixMethodName() + this.mmd.getName(), this.mmd.getType(), (Object[])null, (String[])null);
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005019", msg));
      }

      if (!this.mmd.isAbstract()) {
         generateGetXXXMethod(this.mv, this.mmd, this.enhancer.getASMClassName(), this.enhancer.getClassDescriptor(), false, this.enhancer.getNamer());
      }

   }

   public static void generateGetXXXMethod(MethodVisitor mv, AbstractMemberMetaData mmd, String asmClassName, String asmClassDesc, boolean detachListener, EnhancementNamer namer) {
      String[] argNames = new String[]{"this"};
      String fieldTypeDesc = Type.getDescriptor(mmd.getType());
      mv.visitCode();
      AbstractClassMetaData cmd = mmd.getAbstractClassMetaData();
      if ((mmd.getPersistenceFlags() & 2) == 2) {
         Label startLabel = new Label();
         mv.visitLabel(startLabel);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getStateManagerFieldName(), "L" + namer.getStateManagerAsmClassName() + ";");
         Label l1 = new Label();
         mv.visitJumpInsn(198, l1);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getStateManagerFieldName(), "L" + namer.getStateManagerAsmClassName() + ";");
         mv.visitVarInsn(25, 0);
         EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
         if (cmd.getPersistableSuperclass() != null) {
            mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
            mv.visitInsn(96);
         }

         mv.visitMethodInsn(185, namer.getStateManagerAsmClassName(), "isLoaded", "(L" + namer.getPersistableAsmClassName() + ";I)Z");
         mv.visitJumpInsn(154, l1);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getStateManagerFieldName(), "L" + namer.getStateManagerAsmClassName() + ";");
         mv.visitVarInsn(25, 0);
         EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
         if (cmd.getPersistableSuperclass() != null) {
            mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
            mv.visitInsn(96);
         }

         mv.visitVarInsn(25, 0);
         mv.visitMethodInsn(182, asmClassName, namer.getGetMethodPrefixMethodName() + mmd.getName(), "()" + fieldTypeDesc);
         String methodName = "get" + EnhanceUtils.getTypeNameForPersistableMethod(mmd.getType()) + "Field";
         String argTypeDesc = fieldTypeDesc;
         if (methodName.equals("getObjectField")) {
            argTypeDesc = EnhanceUtils.CD_Object;
         }

         mv.visitMethodInsn(185, namer.getStateManagerAsmClassName(), methodName, "(L" + namer.getPersistableAsmClassName() + ";I" + argTypeDesc + ")" + argTypeDesc);
         if (methodName.equals("getObjectField")) {
            mv.visitTypeInsn(192, mmd.getTypeName().replace('.', '/'));
         }

         EnhanceUtils.addReturnForType(mv, mmd.getType());
         mv.visitLabel(l1);
         mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         if (cmd.isDetachable()) {
            mv.visitVarInsn(25, 0);
            mv.visitMethodInsn(182, asmClassName, namer.getIsDetachedMethodName(), "()Z");
            Label l4 = new Label();
            mv.visitJumpInsn(153, l4);
            mv.visitVarInsn(25, 0);
            mv.visitFieldInsn(180, asmClassName, namer.getDetachedStateFieldName(), "[Ljava/lang/Object;");
            mv.visitInsn(5);
            mv.visitInsn(50);
            mv.visitTypeInsn(192, "java/util/BitSet");
            EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
            if (cmd.getPersistableSuperclass() != null) {
               mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
               mv.visitInsn(96);
            }

            mv.visitMethodInsn(182, "java/util/BitSet", "get", "(I)Z");
            mv.visitJumpInsn(154, l4);
            mv.visitVarInsn(25, 0);
            mv.visitFieldInsn(180, asmClassName, namer.getDetachedStateFieldName(), "[Ljava/lang/Object;");
            mv.visitInsn(6);
            mv.visitInsn(50);
            mv.visitTypeInsn(192, "java/util/BitSet");
            EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
            if (cmd.getPersistableSuperclass() != null) {
               mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
               mv.visitInsn(96);
            }

            mv.visitMethodInsn(182, "java/util/BitSet", "get", "(I)Z");
            mv.visitJumpInsn(154, l4);
            if (detachListener) {
               mv.visitMethodInsn(184, namer.getDetachListenerAsmClassName(), "getInstance", "()L" + namer.getDetachListenerAsmClassName() + ";");
               mv.visitVarInsn(25, 0);
               mv.visitLdcInsn(mmd.getName());
               mv.visitMethodInsn(182, namer.getDetachListenerAsmClassName(), "undetachedFieldAccess", "(Ljava/lang/Object;Ljava/lang/String;)V");
            } else {
               mv.visitTypeInsn(187, namer.getDetachedFieldAccessExceptionAsmClassName());
               mv.visitInsn(89);
               mv.visitLdcInsn(Localiser.msg("005026", mmd.getName()));
               mv.visitMethodInsn(183, namer.getDetachedFieldAccessExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
               mv.visitInsn(191);
            }

            mv.visitLabel(l4);
            mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         }

         mv.visitVarInsn(25, 0);
         mv.visitMethodInsn(182, asmClassName, namer.getGetMethodPrefixMethodName() + mmd.getName(), "()" + fieldTypeDesc);
         EnhanceUtils.addReturnForType(mv, mmd.getType());
         Label endLabel = new Label();
         mv.visitLabel(endLabel);
         mv.visitLocalVariable(argNames[0], asmClassDesc, (String)null, startLabel, endLabel, 0);
         mv.visitMaxs(4, 1);
      } else if ((mmd.getPersistenceFlags() & 1) == 1) {
         Label startLabel = new Label();
         mv.visitLabel(startLabel);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getFlagsFieldName(), "B");
         Label l1 = new Label();
         mv.visitJumpInsn(158, l1);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getStateManagerFieldName(), "L" + namer.getStateManagerAsmClassName() + ";");
         mv.visitJumpInsn(198, l1);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getStateManagerFieldName(), "L" + namer.getStateManagerAsmClassName() + ";");
         mv.visitVarInsn(25, 0);
         EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
         if (cmd.getPersistableSuperclass() != null) {
            mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
            mv.visitInsn(96);
         }

         mv.visitMethodInsn(185, namer.getStateManagerAsmClassName(), "isLoaded", "(L" + namer.getPersistableAsmClassName() + ";I)Z");
         mv.visitJumpInsn(154, l1);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, asmClassName, namer.getStateManagerFieldName(), "L" + namer.getStateManagerAsmClassName() + ";");
         mv.visitVarInsn(25, 0);
         EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
         if (cmd.getPersistableSuperclass() != null) {
            mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
            mv.visitInsn(96);
         }

         mv.visitVarInsn(25, 0);
         mv.visitMethodInsn(182, asmClassName, namer.getGetMethodPrefixMethodName() + mmd.getName(), "()" + fieldTypeDesc);
         String methodName = "get" + EnhanceUtils.getTypeNameForPersistableMethod(mmd.getType()) + "Field";
         String argTypeDesc = fieldTypeDesc;
         if (methodName.equals("getObjectField")) {
            argTypeDesc = EnhanceUtils.CD_Object;
         }

         mv.visitMethodInsn(185, namer.getStateManagerAsmClassName(), methodName, "(L" + namer.getPersistableAsmClassName() + ";I" + argTypeDesc + ")" + argTypeDesc);
         if (methodName.equals("getObjectField")) {
            mv.visitTypeInsn(192, mmd.getTypeName().replace('.', '/'));
         }

         EnhanceUtils.addReturnForType(mv, mmd.getType());
         mv.visitLabel(l1);
         mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         if (cmd.isDetachable()) {
            mv.visitVarInsn(25, 0);
            mv.visitMethodInsn(182, asmClassName, namer.getIsDetachedMethodName(), "()Z");
            Label l4 = new Label();
            mv.visitJumpInsn(153, l4);
            mv.visitVarInsn(25, 0);
            mv.visitFieldInsn(180, asmClassName, namer.getDetachedStateFieldName(), "[Ljava/lang/Object;");
            mv.visitInsn(5);
            mv.visitInsn(50);
            mv.visitTypeInsn(192, "java/util/BitSet");
            EnhanceUtils.addBIPUSHToMethod(mv, mmd.getFieldId());
            if (cmd.getPersistableSuperclass() != null) {
               mv.visitFieldInsn(178, asmClassName, namer.getInheritedFieldCountFieldName(), "I");
               mv.visitInsn(96);
            }

            mv.visitMethodInsn(182, "java/util/BitSet", "get", "(I)Z");
            mv.visitJumpInsn(154, l4);
            if (detachListener) {
               mv.visitMethodInsn(184, namer.getDetachListenerAsmClassName(), "getInstance", "()L" + namer.getDetachListenerAsmClassName() + ";");
               mv.visitVarInsn(25, 0);
               mv.visitLdcInsn(mmd.getName());
               mv.visitMethodInsn(182, namer.getDetachListenerAsmClassName(), "undetachedFieldAccess", "(Ljava/lang/Object;Ljava/lang/String;)V");
            } else {
               mv.visitTypeInsn(187, namer.getDetachedFieldAccessExceptionAsmClassName());
               mv.visitInsn(89);
               mv.visitLdcInsn(Localiser.msg("005026", mmd.getName()));
               mv.visitMethodInsn(183, namer.getDetachedFieldAccessExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
               mv.visitInsn(191);
            }

            mv.visitLabel(l4);
            mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         }

         mv.visitVarInsn(25, 0);
         mv.visitMethodInsn(182, asmClassName, namer.getGetMethodPrefixMethodName() + mmd.getName(), "()" + fieldTypeDesc);
         EnhanceUtils.addReturnForType(mv, mmd.getType());
         Label endLabel = new Label();
         mv.visitLabel(endLabel);
         mv.visitLocalVariable(argNames[0], asmClassDesc, (String)null, startLabel, endLabel, 0);
         mv.visitMaxs(4, 1);
      } else {
         Label startLabel = new Label();
         mv.visitLabel(startLabel);
         mv.visitVarInsn(25, 0);
         mv.visitMethodInsn(182, asmClassName, namer.getGetMethodPrefixMethodName() + mmd.getName(), "()" + fieldTypeDesc);
         EnhanceUtils.addReturnForType(mv, mmd.getType());
         Label endLabel = new Label();
         mv.visitLabel(endLabel);
         mv.visitLocalVariable(argNames[0], asmClassDesc, (String)null, startLabel, endLabel, 0);
         mv.visitMaxs(1, 1);
      }

      mv.visitEnd();
   }

   public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
      return this.mv.visitAnnotation(arg0, arg1);
   }

   public AnnotationVisitor visitAnnotationDefault() {
      return this.visitor.visitAnnotationDefault();
   }

   public void visitParameter(String name, int access) {
      this.visitor.visitParameter(name, access);
   }

   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
      return this.visitor.visitTypeAnnotation(typeRef, typePath, desc, visible);
   }

   public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
      if (this.mmd instanceof PropertyMetaData) {
         String ownerClassName = owner.replace('/', '.');
         AbstractClassMetaData cmd = this.enhancer.getClassMetaData();
         if (!this.enhancer.getASMClassName().equals(owner)) {
            String propName = ClassUtils.getFieldNameForJavaBeanGetter(name);
            if (propName != null) {
               boolean callingOverriddenSuperclassMethod = false;

               while(cmd.getSuperAbstractClassMetaData() != null) {
                  cmd = cmd.getSuperAbstractClassMetaData();
                  if (cmd.getFullClassName().equals(ownerClassName)) {
                     AbstractMemberMetaData theMmd = cmd.getMetaDataForMember(this.mmd.getName());
                     if (theMmd != null) {
                        callingOverriddenSuperclassMethod = true;
                        break;
                     }
                  }
               }

               if (callingOverriddenSuperclassMethod) {
                  String redirectMethodName = this.enhancer.getNamer().getGetMethodPrefixMethodName() + propName;
                  this.visitor.visitMethodInsn(opcode, owner, redirectMethodName, desc, itf);
                  return;
               }
            }
         }
      }

      this.visitor.visitMethodInsn(opcode, owner, name, desc, itf);
   }

   public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
      this.visitor.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
   }

   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
      return this.visitor.visitInsnAnnotation(typeRef, typePath, desc, visible);
   }

   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
      return this.visitor.visitTryCatchAnnotation(typeRef, typePath, desc, visible);
   }

   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
      return this.visitor.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, desc, visible);
   }

   public void visitAttribute(Attribute arg0) {
      this.visitor.visitAttribute(arg0);
   }

   public void visitCode() {
      this.visitor.visitCode();
   }

   public void visitFieldInsn(int opcode, String owner, String name, String desc) {
      this.visitor.visitFieldInsn(opcode, owner, name, desc);
   }

   public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3, Object[] arg4) {
      this.visitor.visitFrame(arg0, arg1, arg2, arg3, arg4);
   }

   public void visitIincInsn(int arg0, int arg1) {
      this.visitor.visitIincInsn(arg0, arg1);
   }

   public void visitInsn(int opcode) {
      this.visitor.visitInsn(opcode);
   }

   public void visitIntInsn(int arg0, int arg1) {
      this.visitor.visitIntInsn(arg0, arg1);
   }

   public void visitJumpInsn(int arg0, Label arg1) {
      this.visitor.visitJumpInsn(arg0, arg1);
   }

   public void visitLabel(Label arg0) {
      this.visitor.visitLabel(arg0);
   }

   public void visitLdcInsn(Object arg0) {
      this.visitor.visitLdcInsn(arg0);
   }

   public void visitLineNumber(int arg0, Label arg1) {
      this.visitor.visitLineNumber(arg0, arg1);
   }

   public void visitLocalVariable(String arg0, String arg1, String arg2, Label arg3, Label arg4, int arg5) {
      this.visitor.visitLocalVariable(arg0, arg1, arg2, arg3, arg4, arg5);
   }

   public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
      this.visitor.visitLookupSwitchInsn(arg0, arg1, arg2);
   }

   public void visitMaxs(int arg0, int arg1) {
      this.visitor.visitMaxs(arg0, arg1);
   }

   public void visitMethodInsn(int opcode, String owner, String name, String desc) {
      this.visitor.visitMethodInsn(opcode, owner, name, desc);
   }

   public void visitMultiANewArrayInsn(String arg0, int arg1) {
      this.visitor.visitMultiANewArrayInsn(arg0, arg1);
   }

   public AnnotationVisitor visitParameterAnnotation(int arg0, String arg1, boolean arg2) {
      return this.visitor.visitParameterAnnotation(arg0, arg1, arg2);
   }

   public void visitTableSwitchInsn(int arg0, int arg1, Label arg2, Label... arg3) {
      this.visitor.visitTableSwitchInsn(arg0, arg1, arg2, arg3);
   }

   public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2, String arg3) {
      this.visitor.visitTryCatchBlock(arg0, arg1, arg2, arg3);
   }

   public void visitTypeInsn(int arg0, String arg1) {
      this.visitor.visitTypeInsn(arg0, arg1);
   }

   public void visitVarInsn(int arg0, int arg1) {
      this.visitor.visitVarInsn(arg0, arg1);
   }
}
