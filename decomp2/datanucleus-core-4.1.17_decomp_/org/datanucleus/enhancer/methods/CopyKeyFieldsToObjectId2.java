package org.datanucleus.enhancer.methods;

import java.lang.reflect.Modifier;
import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.util.ClassUtils;

public class CopyKeyFieldsToObjectId2 extends ClassMethod {
   public static CopyKeyFieldsToObjectId2 getInstance(ClassEnhancer enhancer) {
      return new CopyKeyFieldsToObjectId2(enhancer, enhancer.getNamer().getCopyKeyFieldsToObjectIdMethodName(), 1, (Object)null, new Class[]{enhancer.getNamer().getObjectIdFieldSupplierClass(), Object.class}, new String[]{"fs", "oid"});
   }

   public CopyKeyFieldsToObjectId2(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      ClassMetaData cmd = this.enhancer.getClassMetaData();
      if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         if (!cmd.isInstantiable()) {
            Label startLabel = new Label();
            this.visitor.visitLabel(startLabel);
            this.visitor.visitInsn(177);
            Label endLabel = new Label();
            this.visitor.visitLabel(endLabel);
            this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
            this.visitor.visitLocalVariable(this.argNames[0], this.getNamer().getObjectIdFieldSupplierDescriptor(), (String)null, startLabel, endLabel, 1);
            this.visitor.visitLocalVariable(this.argNames[1], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 2);
            this.visitor.visitMaxs(0, 3);
         } else {
            String objectIdClass = cmd.getObjectidClass();
            String ACN_objectIdClass = objectIdClass.replace('.', '/');
            if (IdentityUtils.isSingleFieldIdentityClass(objectIdClass)) {
               Label startLabel = new Label();
               this.visitor.visitLabel(startLabel);
               this.visitor.visitTypeInsn(187, this.getNamer().getFatalInternalExceptionAsmClassName());
               this.visitor.visitInsn(89);
               this.visitor.visitLdcInsn("It's illegal to call dnCopyKeyFieldsToObjectId for a class with single-field identity.");
               this.visitor.visitMethodInsn(183, this.getNamer().getFatalInternalExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(191);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 1);
               this.visitor.visitLocalVariable("paramObject", "Ljava/lang/Object;", (String)null, startLabel, endLabel, 2);
               this.visitor.visitMaxs(3, 3);
            } else {
               Label l0 = new Label();
               Label l1 = new Label();
               Label l2 = new Label();
               this.visitor.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
               Label startLabel = new Label();
               this.visitor.visitLabel(startLabel);
               this.visitor.visitVarInsn(25, 1);
               Label l4 = new Label();
               this.visitor.visitJumpInsn(199, l4);
               this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
               this.visitor.visitInsn(89);
               this.visitor.visitLdcInsn("ObjectIdFieldSupplier is null");
               this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(191);
               this.visitor.visitLabel(l4);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitVarInsn(25, 2);
               this.visitor.visitTypeInsn(193, ACN_objectIdClass);
               Label l5 = new Label();
               this.visitor.visitJumpInsn(154, l5);
               this.visitor.visitTypeInsn(187, "java/lang/ClassCastException");
               this.visitor.visitInsn(89);
               this.visitor.visitLdcInsn("oid is not instanceof " + objectIdClass);
               this.visitor.visitMethodInsn(183, "java/lang/ClassCastException", "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(191);
               this.visitor.visitLabel(l5);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitVarInsn(25, 2);
               this.visitor.visitTypeInsn(192, ACN_objectIdClass);
               this.visitor.visitVarInsn(58, 3);
               this.visitor.visitLabel(l0);
               int[] pkFieldNums = this.enhancer.getClassMetaData().getPKMemberPositions();
               Label reflectionFieldStart = null;

               for(int i = 0; i < pkFieldNums.length; ++i) {
                  AbstractMemberMetaData fmd = this.enhancer.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[i]);
                  String fieldTypeDesc = Type.getDescriptor(fmd.getType());
                  String typeMethodName = EnhanceUtils.getTypeNameForPersistableMethod(fmd.getType());
                  int pkFieldModifiers = ClassUtils.getModifiersForFieldOfClass(this.enhancer.getClassLoaderResolver(), objectIdClass, fmd.getName());
                  AbstractClassMetaData acmd = this.enhancer.getMetaDataManager().getMetaDataForClass(fmd.getType(), this.enhancer.getClassLoaderResolver());
                  if (acmd != null && acmd.getIdentityType() != IdentityType.NONDURABLE) {
                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitVarInsn(25, 1);
                     EnhanceUtils.addBIPUSHToMethod(this.visitor, fmd.getFieldId());
                     this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetch" + typeMethodName + "Field", "(I)" + EnhanceUtils.getTypeDescriptorForEnhanceMethod(fmd.getType()));
                     if (typeMethodName.equals("Object")) {
                        this.visitor.visitTypeInsn(192, fmd.getTypeName().replace('.', '/'));
                     }

                     this.visitor.visitMethodInsn(185, this.getNamer().getPersistableAsmClassName(), this.getNamer().getGetObjectIdMethodName(), "()Ljava/lang/Object;", true);
                     this.visitor.visitTypeInsn(192, acmd.getObjectidClass().replace('.', '/'));
                     this.visitor.visitFieldInsn(181, ACN_objectIdClass, fmd.getName(), "L" + acmd.getObjectidClass().replace('.', '/') + ";");
                  } else if (fmd instanceof PropertyMetaData) {
                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitVarInsn(25, 1);
                     EnhanceUtils.addBIPUSHToMethod(this.visitor, fmd.getFieldId());
                     this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetch" + typeMethodName + "Field", "(I)" + EnhanceUtils.getTypeDescriptorForEnhanceMethod(fmd.getType()));
                     if (typeMethodName.equals("Object")) {
                        this.visitor.visitTypeInsn(192, fmd.getTypeName().replace('.', '/'));
                     }

                     this.visitor.visitMethodInsn(182, ACN_objectIdClass, ClassUtils.getJavaBeanSetterName(fmd.getName()), "(" + fieldTypeDesc + ")V");
                  } else if (Modifier.isPublic(pkFieldModifiers)) {
                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitVarInsn(25, 1);
                     EnhanceUtils.addBIPUSHToMethod(this.visitor, fmd.getFieldId());
                     this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetch" + typeMethodName + "Field", "(I)" + EnhanceUtils.getTypeDescriptorForEnhanceMethod(fmd.getType()));
                     if (typeMethodName.equals("Object")) {
                        this.visitor.visitTypeInsn(192, fmd.getTypeName().replace('.', '/'));
                     }

                     this.visitor.visitFieldInsn(181, ACN_objectIdClass, fmd.getName(), fieldTypeDesc);
                  } else {
                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                     this.visitor.visitLdcInsn(fmd.getName());
                     this.visitor.visitMethodInsn(182, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;");
                     this.visitor.visitVarInsn(58, 4);
                     if (reflectionFieldStart == null) {
                        reflectionFieldStart = new Label();
                        this.visitor.visitLabel(reflectionFieldStart);
                     }

                     this.visitor.visitVarInsn(25, 4);
                     this.visitor.visitInsn(4);
                     this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setAccessible", "(Z)V");
                     this.visitor.visitVarInsn(25, 4);
                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitVarInsn(25, 1);
                     EnhanceUtils.addBIPUSHToMethod(this.visitor, fmd.getFieldId());
                     if (fmd.getTypeName().equals("boolean")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchBooleanField", "(I)Z");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setBoolean", "(Ljava/lang/Object;Z)V");
                     } else if (fmd.getTypeName().equals("byte")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchByteField", "(I)B");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setByte", "(Ljava/lang/Object;B)V");
                     } else if (fmd.getTypeName().equals("char")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchCharField", "(I)C");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setChar", "(Ljava/lang/Object;C)V");
                     } else if (fmd.getTypeName().equals("double")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchDoubleField", "(I)D");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setDouble", "(Ljava/lang/Object;D)V");
                     } else if (fmd.getTypeName().equals("float")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchFloatField", "(I)F");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setFloat", "(Ljava/lang/Object;F)V");
                     } else if (fmd.getTypeName().equals("int")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchIntField", "(I)I");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setInt", "(Ljava/lang/Object;I)V");
                     } else if (fmd.getTypeName().equals("long")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchLongField", "(I)J");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setLong", "(Ljava/lang/Object;J)V");
                     } else if (fmd.getTypeName().equals("short")) {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchShortField", "(I)S");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setShort", "(Ljava/lang/Object;S)V");
                     } else {
                        this.visitor.visitMethodInsn(185, this.getNamer().getObjectIdFieldSupplierAsmClassName(), "fetchObjectField", "(I)Ljava/lang/Object;");
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                     }
                  }
               }

               this.visitor.visitLabel(l1);
               Label l20 = new Label();
               this.visitor.visitJumpInsn(167, l20);
               this.visitor.visitLabel(l2);
               this.visitor.visitFrame(0, 4, new Object[]{this.getClassEnhancer().getASMClassName(), this.getClassEnhancer().getNamer().getObjectIdFieldSupplierAsmClassName(), "java/lang/Object", ACN_objectIdClass}, 1, new Object[]{"java/lang/Exception"});
               this.visitor.visitVarInsn(58, 4);
               this.visitor.visitLabel(l20);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitInsn(177);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitLocalVariable(this.argNames[0], this.getNamer().getObjectIdFieldSupplierDescriptor(), (String)null, startLabel, endLabel, 1);
               this.visitor.visitLocalVariable(this.argNames[1], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 2);
               this.visitor.visitLocalVariable("o", "L" + ACN_objectIdClass + ";", (String)null, l0, endLabel, 3);
               if (reflectionFieldStart != null) {
                  this.visitor.visitLocalVariable("field", "Ljava/lang/reflect/Field;", (String)null, reflectionFieldStart, l2, 4);
                  this.visitor.visitMaxs(4, 5);
               } else {
                  this.visitor.visitMaxs(3, 4);
               }
            }
         }
      } else {
         Label startLabel = new Label();
         this.visitor.visitLabel(startLabel);
         this.visitor.visitInsn(177);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], "L" + this.getNamer().getObjectIdFieldSupplierAsmClassName() + ";", (String)null, startLabel, endLabel, 1);
         this.visitor.visitLocalVariable(this.argNames[1], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 2);
         this.visitor.visitMaxs(0, 3);
      }

      this.visitor.visitEnd();
   }
}
