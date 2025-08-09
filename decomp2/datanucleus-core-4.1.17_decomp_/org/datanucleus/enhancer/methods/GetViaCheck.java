package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.util.Localiser;

public class GetViaCheck extends ClassMethod {
   protected AbstractMemberMetaData fmd;

   public GetViaCheck(ClassEnhancer enhancer, AbstractMemberMetaData fmd) {
      super(enhancer, enhancer.getNamer().getGetMethodPrefixMethodName() + fmd.getName(), (fmd.isPublic() ? 1 : 0) | (fmd.isProtected() ? 4 : 0) | (fmd.isPrivate() ? 2 : 0) | 8, fmd.getType(), (Object[])null, (String[])null);
      this.argTypes = new Class[]{this.getClassEnhancer().getClassBeingEnhanced()};
      this.argNames = new String[]{"objPC"};
      this.fmd = fmd;
   }

   public void execute() {
      this.visitor.visitCode();
      String fieldTypeDesc = Type.getDescriptor(this.fmd.getType());
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getFlagsFieldName(), "B");
      Label l1 = new Label();
      this.visitor.visitJumpInsn(158, l1);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), "L" + this.getNamer().getStateManagerAsmClassName() + ";");
      this.visitor.visitJumpInsn(198, l1);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), "L" + this.getNamer().getStateManagerAsmClassName() + ";");
      this.visitor.visitVarInsn(25, 0);
      EnhanceUtils.addBIPUSHToMethod(this.visitor, this.fmd.getFieldId());
      if (this.enhancer.getClassMetaData().getPersistableSuperclass() != null) {
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
         this.visitor.visitInsn(96);
      }

      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "isLoaded", "(L" + this.getNamer().getPersistableAsmClassName() + ";I)Z");
      this.visitor.visitJumpInsn(154, l1);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), "L" + this.getNamer().getStateManagerAsmClassName() + ";");
      this.visitor.visitVarInsn(25, 0);
      EnhanceUtils.addBIPUSHToMethod(this.visitor, this.fmd.getFieldId());
      if (this.enhancer.getClassMetaData().getPersistableSuperclass() != null) {
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
         this.visitor.visitInsn(96);
      }

      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.fmd.getName(), fieldTypeDesc);
      String dnMethodName = "get" + EnhanceUtils.getTypeNameForPersistableMethod(this.fmd.getType()) + "Field";
      String argTypeDesc = fieldTypeDesc;
      if (dnMethodName.equals("getObjectField")) {
         argTypeDesc = EnhanceUtils.CD_Object;
      }

      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), dnMethodName, "(L" + this.getNamer().getPersistableAsmClassName() + ";I" + argTypeDesc + ")" + argTypeDesc);
      if (dnMethodName.equals("getObjectField")) {
         this.visitor.visitTypeInsn(192, this.fmd.getTypeName().replace('.', '/'));
      }

      EnhanceUtils.addReturnForType(this.visitor, this.fmd.getType());
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      if (this.enhancer.getClassMetaData().isDetachable()) {
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getIsDetachedMethodName(), "()Z");
         Label l4 = new Label();
         this.visitor.visitJumpInsn(153, l4);
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[Ljava/lang/Object;");
         this.visitor.visitInsn(5);
         this.visitor.visitInsn(50);
         this.visitor.visitTypeInsn(192, "java/util/BitSet");
         EnhanceUtils.addBIPUSHToMethod(this.visitor, this.fmd.getFieldId());
         if (this.enhancer.getClassMetaData().getPersistableSuperclass() != null) {
            this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
            this.visitor.visitInsn(96);
         }

         this.visitor.visitMethodInsn(182, "java/util/BitSet", "get", "(I)Z");
         this.visitor.visitJumpInsn(154, l4);
         if (this.enhancer.hasOption("generate-detach-listener")) {
            this.visitor.visitMethodInsn(184, this.getNamer().getDetachListenerAsmClassName(), "getInstance", "()L" + this.getNamer().getDetachListenerAsmClassName() + ";");
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitLdcInsn(this.fmd.getName());
            this.visitor.visitMethodInsn(182, this.getNamer().getDetachListenerAsmClassName(), "undetachedFieldAccess", "(Ljava/lang/Object;Ljava/lang/String;)V");
         } else {
            this.visitor.visitTypeInsn(187, this.getNamer().getDetachedFieldAccessExceptionAsmClassName());
            this.visitor.visitInsn(89);
            this.visitor.visitLdcInsn(Localiser.msg("005025", this.fmd.getName()));
            this.visitor.visitMethodInsn(183, this.getNamer().getDetachedFieldAccessExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
            this.visitor.visitInsn(191);
         }

         this.visitor.visitLabel(l4);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }

      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.fmd.getName(), fieldTypeDesc);
      EnhanceUtils.addReturnForType(this.visitor, this.fmd.getType());
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable(this.argNames[0], this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitMaxs(4, 1);
      this.visitor.visitEnd();
   }
}
