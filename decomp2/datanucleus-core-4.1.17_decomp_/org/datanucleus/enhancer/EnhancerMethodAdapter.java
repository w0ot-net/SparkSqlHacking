package org.datanucleus.enhancer;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.enhancer.methods.InitClass;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.util.Localiser;

public class EnhancerMethodAdapter extends MethodVisitor {
   protected ClassEnhancer enhancer;
   protected String methodName;
   protected String methodDescriptor;
   boolean firstLabel = true;

   public EnhancerMethodAdapter(MethodVisitor mv, ClassEnhancer enhancer, String methodName, String methodDesc) {
      super(327680, mv);
      this.enhancer = enhancer;
      this.methodName = methodName;
      this.methodDescriptor = methodDesc;
   }

   public void visitLabel(Label label) {
      super.visitLabel(label);
      if (this.firstLabel) {
         if (this.methodName.equals("writeObject")) {
            this.mv.visitVarInsn(25, 0);
            this.mv.visitMethodInsn(182, this.enhancer.getASMClassName(), this.enhancer.getNamer().getPreSerializeMethodName(), "()V");
            if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
               DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005033", this.enhancer.getClassName() + "." + this.methodName));
            }
         } else if (this.methodName.equals("readObject")) {
         }
      }

      this.firstLabel = false;
   }

   public void visitFieldInsn(int opcode, String owner, String name, String desc) {
      String ownerName = owner.replace('/', '.');
      if (this.enhancer.isPersistable(ownerName)) {
         AbstractClassMetaData cmd = null;
         boolean fieldInThisClass = true;
         if (this.enhancer.getClassMetaData().getFullClassName().equals(ownerName)) {
            cmd = this.enhancer.getClassMetaData();
         } else {
            fieldInThisClass = false;
            cmd = this.enhancer.getMetaDataManager().getMetaDataForClass(ownerName, this.enhancer.getClassLoaderResolver());
         }

         if (fieldInThisClass && this.methodName.equals("<init>")) {
            DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005024", this.enhancer.getClassName() + "." + this.methodName, opcode == 180 ? "get" : "set", ownerName + "." + name));
         } else {
            AbstractMemberMetaData fmd = cmd.getMetaDataForMember(name);
            if (fmd != null && !fmd.isStatic() && !fmd.isFinal() && fmd.getPersistenceModifier() != FieldPersistenceModifier.NONE && fmd.getPersistenceFlags() != 0 && fmd instanceof FieldMetaData) {
               String fieldOwner = fmd.getClassName(true).replace('.', '/');
               if (opcode == 180) {
                  this.mv.visitMethodInsn(184, fieldOwner, this.enhancer.getNamer().getGetMethodPrefixMethodName() + name, "(L" + fieldOwner + ";)" + desc);
                  if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                     DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005023", this.enhancer.getClassName() + "." + this.methodName, fmd.getClassName(true) + "." + name, this.enhancer.getNamer().getGetMethodPrefixMethodName() + name + "()"));
                  }

                  return;
               }

               if (opcode == 181) {
                  this.mv.visitMethodInsn(184, fieldOwner, this.enhancer.getNamer().getSetMethodPrefixMethodName() + name, "(L" + fieldOwner + ";" + desc + ")V");
                  if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                     DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005023", this.enhancer.getClassName() + "." + this.methodName, fmd.getClassName(true) + "." + name, this.enhancer.getNamer().getSetMethodPrefixMethodName() + name + "()"));
                  }

                  return;
               }
            }
         }
      }

      super.visitFieldInsn(opcode, owner, name, desc);
   }

   public void visitMethodInsn(int opcode, String owner, String name, String desc) {
      if (this.methodName.equals("clone") && this.methodDescriptor.equals("()Ljava/lang/Object;") && this.enhancer.getClassMetaData().getPersistableSuperclass() == null && opcode == 183 && name.equals("clone") && desc.equals("()Ljava/lang/Object;")) {
         this.mv.visitMethodInsn(183, this.enhancer.getASMClassName(), this.enhancer.getNamer().getSuperCloneMethodName(), "()Ljava/lang/Object;");
      } else {
         super.visitMethodInsn(opcode, owner, name, desc);
      }
   }

   public void visitInsn(int opcode) {
      if (this.enhancer.getClassMetaData().getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE && this.methodName.equals("<clinit>") && this.methodDescriptor.equals("()V") && opcode == 177) {
         InitClass initMethod = InitClass.getInstance(this.enhancer);
         initMethod.addInitialiseInstructions(this.mv);
         this.mv.visitInsn(177);
      } else {
         super.visitInsn(opcode);
      }
   }
}
