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

public class CopyKeyFieldsFromObjectId2 extends ClassMethod {
   public static CopyKeyFieldsFromObjectId2 getInstance(ClassEnhancer enhancer) {
      return new CopyKeyFieldsFromObjectId2(enhancer, enhancer.getNamer().getCopyKeyFieldsFromObjectIdMethodName(), 4, (Object)null, new Class[]{Object.class}, new String[]{"oid"});
   }

   public CopyKeyFieldsFromObjectId2(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
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
            this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 1);
            this.visitor.visitMaxs(0, 2);
         } else {
            String objectIdClass = cmd.getObjectidClass();
            String ACN_objectIdClass = objectIdClass.replace('.', '/');
            int[] pkFieldNums = this.enhancer.getClassMetaData().getPKMemberPositions();
            if (IdentityUtils.isSingleFieldIdentityClass(objectIdClass)) {
               Label startLabel = new Label();
               this.visitor.visitLabel(startLabel);
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(193, ACN_objectIdClass);
               Label l1 = new Label();
               this.visitor.visitJumpInsn(154, l1);
               this.visitor.visitTypeInsn(187, "java/lang/ClassCastException");
               this.visitor.visitInsn(89);
               this.visitor.visitLdcInsn("key class is not " + objectIdClass + " or null");
               this.visitor.visitMethodInsn(183, "java/lang/ClassCastException", "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(191);
               this.visitor.visitLabel(l1);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(192, ACN_objectIdClass);
               this.visitor.visitVarInsn(58, 2);
               Label l5 = new Label();
               this.visitor.visitLabel(l5);
               this.visitor.visitVarInsn(25, 0);
               AbstractMemberMetaData fmd = this.enhancer.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[0]);
               Class primitiveType = ClassUtils.getPrimitiveTypeForType(fmd.getType());
               if (primitiveType != null) {
                  String ACN_fieldType = fmd.getTypeName().replace('.', '/');
                  String getKeyReturnDesc = Type.getDescriptor(primitiveType);
                  this.visitor.visitVarInsn(25, 2);
                  this.visitor.visitMethodInsn(182, ACN_objectIdClass, "getKey", "()" + getKeyReturnDesc);
                  this.visitor.visitMethodInsn(184, ACN_fieldType, "valueOf", "(" + getKeyReturnDesc + ")L" + ACN_fieldType + ";");
               } else {
                  this.visitor.visitVarInsn(25, 2);
                  this.visitor.visitMethodInsn(182, ACN_objectIdClass, "getKey", "()" + this.getNamer().getTypeDescriptorForSingleFieldIdentityGetKey(objectIdClass));
                  if (objectIdClass.equals(this.getNamer().getObjectIdentityClass().getName())) {
                     this.visitor.visitTypeInsn(192, fmd.getTypeName().replace('.', '/'));
                  }
               }

               if (fmd instanceof PropertyMetaData) {
                  this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getSetMethodPrefixMethodName() + fmd.getName(), "(" + Type.getDescriptor(fmd.getType()) + ")V");
               } else {
                  this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fmd.getName(), Type.getDescriptor(fmd.getType()));
               }

               this.visitor.visitInsn(177);
               Label l7 = new Label();
               this.visitor.visitLabel(l7);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, l7, 0);
               this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, startLabel, l7, 1);
               this.visitor.visitLocalVariable("o", this.getNamer().getSingleFieldIdentityDescriptor(objectIdClass), (String)null, l5, l7, 2);
               this.visitor.visitMaxs(3, 3);
            } else {
               Label l0 = new Label();
               Label l1 = new Label();
               Label l2 = new Label();
               this.visitor.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
               Label startLabel = new Label();
               this.visitor.visitLabel(startLabel);
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(193, ACN_objectIdClass);
               Label l4 = new Label();
               this.visitor.visitJumpInsn(154, l4);
               this.visitor.visitTypeInsn(187, "java/lang/ClassCastException");
               this.visitor.visitInsn(89);
               this.visitor.visitLdcInsn("key class is not " + objectIdClass + " or null");
               this.visitor.visitMethodInsn(183, "java/lang/ClassCastException", "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(191);
               this.visitor.visitLabel(l4);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(192, ACN_objectIdClass);
               this.visitor.visitVarInsn(58, 2);
               this.visitor.visitLabel(l0);
               Label reflectionFieldStart = null;

               for(int i = 0; i < pkFieldNums.length; ++i) {
                  AbstractMemberMetaData fmd = this.enhancer.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[i]);
                  String fieldTypeDesc = Type.getDescriptor(fmd.getType());
                  String fieldTypeName = fmd.getTypeName().replace('.', '/');
                  int pkFieldModifiers = ClassUtils.getModifiersForFieldOfClass(this.enhancer.getClassLoaderResolver(), objectIdClass, fmd.getName());
                  AbstractClassMetaData acmd = this.enhancer.getMetaDataManager().getMetaDataForClass(fmd.getType(), this.enhancer.getClassLoaderResolver());
                  if (acmd != null && acmd.getIdentityType() != IdentityType.NONDURABLE) {
                     this.visitor.visitVarInsn(25, 0);
                     this.visitor.visitVarInsn(25, 0);
                     this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetExecutionContextMethodName(), "()L" + this.getNamer().getExecutionContextAsmClassName() + ";");
                     this.visitor.visitVarInsn(25, 2);
                     this.visitor.visitFieldInsn(180, ACN_objectIdClass, fmd.getName(), "L" + acmd.getObjectidClass().replace('.', '/') + ";");
                     this.visitor.visitInsn(3);
                     this.visitor.visitMethodInsn(185, this.getNamer().getExecutionContextAsmClassName(), "findObject", "(Ljava/lang/Object;Z)Ljava/lang/Object;");
                     this.visitor.visitTypeInsn(192, fieldTypeName);
                     if (fmd instanceof PropertyMetaData) {
                        this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getSetMethodPrefixMethodName() + fmd.getName(), "(" + Type.getDescriptor(fmd.getType()) + ")V");
                     } else if (Modifier.isPublic(pkFieldModifiers)) {
                        this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fmd.getName(), Type.getDescriptor(fmd.getType()));
                     } else {
                        this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fmd.getName(), Type.getDescriptor(fmd.getType()));
                     }
                  } else if (fmd instanceof PropertyMetaData) {
                     this.visitor.visitVarInsn(25, 0);
                     this.visitor.visitVarInsn(25, 2);
                     this.visitor.visitMethodInsn(182, ACN_objectIdClass, ClassUtils.getJavaBeanGetterName(fmd.getName(), fmd.getTypeName().equals("boolean")), "()" + Type.getDescriptor(fmd.getType()));
                     this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getSetMethodPrefixMethodName() + fmd.getName(), "(" + Type.getDescriptor(fmd.getType()) + ")V");
                  } else if (Modifier.isPublic(pkFieldModifiers)) {
                     this.visitor.visitVarInsn(25, 0);
                     this.visitor.visitVarInsn(25, 2);
                     this.visitor.visitFieldInsn(180, ACN_objectIdClass, fmd.getName(), fieldTypeDesc);
                     this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fmd.getName(), fieldTypeDesc);
                  } else {
                     this.visitor.visitVarInsn(25, 2);
                     this.visitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                     this.visitor.visitLdcInsn(fmd.getName());
                     this.visitor.visitMethodInsn(182, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;");
                     this.visitor.visitVarInsn(58, 3);
                     if (reflectionFieldStart == null) {
                        reflectionFieldStart = new Label();
                        this.visitor.visitLabel(reflectionFieldStart);
                     }

                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitInsn(4);
                     this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "setAccessible", "(Z)V");
                     this.visitor.visitVarInsn(25, 0);
                     this.visitor.visitVarInsn(25, 3);
                     this.visitor.visitVarInsn(25, 2);
                     if (fmd.getTypeName().equals("boolean")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getBoolean", "(Ljava/lang/Object;)Z");
                     } else if (fmd.getTypeName().equals("byte")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getByte", "(Ljava/lang/Object;)B");
                     } else if (fmd.getTypeName().equals("char")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getChar", "(Ljava/lang/Object;)C");
                     } else if (fmd.getTypeName().equals("double")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getDouble", "(Ljava/lang/Object;)D");
                     } else if (fmd.getTypeName().equals("float")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getFloat", "(Ljava/lang/Object;)F");
                     } else if (fmd.getTypeName().equals("int")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getInt", "(Ljava/lang/Object;)I");
                     } else if (fmd.getTypeName().equals("long")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getLong", "(Ljava/lang/Object;)L");
                     } else if (fmd.getTypeName().equals("short")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "getShort", "(Ljava/lang/Object;)S");
                     } else if (fmd.getTypeName().equals("java.lang.String")) {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
                        this.visitor.visitTypeInsn(192, "java/lang/String");
                     } else {
                        this.visitor.visitMethodInsn(182, "java/lang/reflect/Field", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
                        this.visitor.visitTypeInsn(192, fieldTypeName);
                     }

                     this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fmd.getName(), fieldTypeDesc);
                  }
               }

               this.visitor.visitLabel(l1);
               Label l16 = new Label();
               this.visitor.visitJumpInsn(167, l16);
               this.visitor.visitLabel(l2);
               this.visitor.visitFrame(0, 3, new Object[]{this.getClassEnhancer().getASMClassName(), "java/lang/Object", ACN_objectIdClass}, 1, new Object[]{"java/lang/Exception"});
               this.visitor.visitVarInsn(58, 3);
               this.visitor.visitLabel(l16);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitInsn(177);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 1);
               this.visitor.visitLocalVariable("o", "L" + ACN_objectIdClass + ";", (String)null, l0, endLabel, 2);
               if (reflectionFieldStart != null) {
                  this.visitor.visitLocalVariable("field", "Ljava/lang/reflect/Field;", (String)null, reflectionFieldStart, l2, 3);
                  this.visitor.visitMaxs(3, 4);
               } else {
                  this.visitor.visitMaxs(3, 3);
               }
            }
         }
      } else {
         Label startLabel = new Label();
         this.visitor.visitLabel(startLabel);
         this.visitor.visitInsn(177);
         Label l1 = new Label();
         this.visitor.visitLabel(l1);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, l1, 0);
         this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, startLabel, l1, 1);
         this.visitor.visitMaxs(0, 2);
      }

      this.visitor.visitEnd();
   }
}
