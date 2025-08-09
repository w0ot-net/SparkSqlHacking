package org.datanucleus.enhancer;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import org.datanucleus.asm.ClassWriter;
import org.datanucleus.asm.FieldVisitor;
import org.datanucleus.asm.Label;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.util.Localiser;

public class PrimaryKeyGenerator {
   final AbstractClassMetaData cmd;
   final String pkClassName;
   final String className_ASM;
   final String className_DescName;
   final ClassEnhancer classEnhancer;
   String stringSeparator = ":";

   public PrimaryKeyGenerator(AbstractClassMetaData cmd, ClassEnhancer enhancer) {
      this.cmd = cmd;
      this.classEnhancer = enhancer;
      this.pkClassName = cmd.getFullClassName() + "_PK";
      this.className_ASM = this.pkClassName.replace('.', '/');
      this.className_DescName = "L" + this.className_ASM + ";";
   }

   public byte[] generate() {
      ClassWriter cw = new ClassWriter(1);
      cw.visit(EnhanceUtils.getAsmVersionForJRE(), 33, this.className_ASM, (String)null, "java/lang/Object", new String[]{"java/io/Serializable"});
      this.addFields(cw);
      this.addDefaultConstructor(cw);
      this.addStringConstructor(cw);
      this.addMethodToString(cw);
      this.addMethodEquals(cw);
      this.addMethodHashCode(cw);
      cw.visitEnd();
      return cw.toByteArray();
   }

   protected void addFields(ClassWriter cw) {
      int[] pkPositions = this.cmd.getPKMemberPositions();

      for(int i = 0; i < pkPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(pkPositions[i]);
         String fieldTypeName = this.getTypeNameForField(mmd);
         if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
            DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005021", fieldTypeName + " " + this.pkClassName + " " + mmd.getName()));
         }

         FieldVisitor fv = cw.visitField(1, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(fieldTypeName), (String)null, (Object)null);
         fv.visitEnd();
      }

   }

   protected String getTypeNameForField(AbstractMemberMetaData mmd) {
      AbstractClassMetaData fieldCmd = this.classEnhancer.getMetaDataManager().getMetaDataForClass(mmd.getType(), this.classEnhancer.getClassLoaderResolver());
      String fieldTypeName = mmd.getTypeName();
      if (fieldCmd != null && fieldCmd.getIdentityType() == IdentityType.APPLICATION) {
         fieldTypeName = fieldCmd.getObjectidClass();
      }

      return fieldTypeName;
   }

   protected void addDefaultConstructor(ClassWriter cw) {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005020", this.pkClassName + "()"));
      }

      MethodVisitor mv = cw.visitMethod(1, "<init>", "()V", (String)null, (String[])null);
      mv.visitCode();
      Label startLabel = new Label();
      mv.visitLabel(startLabel);
      mv.visitVarInsn(25, 0);
      mv.visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
      mv.visitInsn(177);
      Label endLabel = new Label();
      mv.visitLabel(endLabel);
      mv.visitLocalVariable("this", this.className_DescName, (String)null, startLabel, endLabel, 0);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
   }

   protected void addStringConstructor(ClassWriter cw) {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005020", this.pkClassName + "(String str)"));
      }

      MethodVisitor mv = cw.visitMethod(1, "<init>", "(Ljava/lang/String;)V", (String)null, (String[])null);
      mv.visitCode();
      int[] pkPositions = this.cmd.getPKMemberPositions();
      Label[] fieldLabels = new Label[pkPositions.length];
      Label startLabel = new Label();
      mv.visitLabel(startLabel);
      mv.visitVarInsn(25, 0);
      mv.visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
      mv.visitTypeInsn(187, "java/util/StringTokenizer");
      mv.visitInsn(89);
      mv.visitVarInsn(25, 1);
      mv.visitLdcInsn(this.stringSeparator);
      mv.visitMethodInsn(183, "java/util/StringTokenizer", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V");
      mv.visitVarInsn(58, 2);
      Label l5 = new Label();
      mv.visitLabel(l5);
      int astorePosition = 2;

      for(int i = 0; i < pkPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(pkPositions[i]);
         String typeName_ASM = mmd.getTypeName().replace('.', '/');
         ++astorePosition;
         mv.visitVarInsn(25, 2);
         mv.visitMethodInsn(182, "java/util/StringTokenizer", "nextToken", "()Ljava/lang/String;");
         mv.visitVarInsn(58, astorePosition);
         fieldLabels[i] = new Label();
         mv.visitLabel(fieldLabels[i]);
         mv.visitVarInsn(25, 0);
         if (mmd.getType() != Long.TYPE && mmd.getType() != Integer.TYPE && mmd.getType() != Short.TYPE) {
            if (mmd.getType() == Character.TYPE) {
               mv.visitVarInsn(25, astorePosition);
               mv.visitInsn(3);
               mv.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
               mv.visitFieldInsn(181, this.className_ASM, "field1", "C");
            } else if (mmd.getType() == String.class) {
               mv.visitVarInsn(25, astorePosition);
               mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
            } else if (mmd.getType() != Long.class && mmd.getType() != Integer.class && mmd.getType() != Short.class && mmd.getType() != BigInteger.class) {
               if (mmd.getType() == Currency.class) {
                  mv.visitVarInsn(25, astorePosition);
                  mv.visitMethodInsn(184, "java/util/Currency", "getInstance", "(Ljava/lang/String;)Ljava/util/Currency;");
                  mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), "Ljava/util/Currency;");
               } else if (mmd.getType() == TimeZone.class) {
                  mv.visitVarInsn(25, astorePosition);
                  mv.visitMethodInsn(184, "java/util/TimeZone", "getTimeZone", "(Ljava/lang/String;)Ljava/util/TimeZone;");
                  mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), "Ljava/util/TimeZone;");
               } else if (mmd.getType() == UUID.class) {
                  mv.visitVarInsn(25, astorePosition);
                  mv.visitMethodInsn(184, "java/util/UUID", "fromString", "(Ljava/lang/String;)Ljava/util/UUID;");
                  mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), "Ljava/util/UUID;");
               } else if (Date.class.isAssignableFrom(mmd.getType())) {
                  mv.visitTypeInsn(187, typeName_ASM);
                  mv.visitInsn(89);
                  mv.visitTypeInsn(187, "java/lang/Long");
                  mv.visitInsn(89);
                  mv.visitVarInsn(25, astorePosition);
                  mv.visitMethodInsn(183, "java/lang/Long", "<init>", "(Ljava/lang/String;)V");
                  mv.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
                  mv.visitMethodInsn(183, typeName_ASM, "<init>", "(J)V");
                  mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
               } else if (Calendar.class.isAssignableFrom(mmd.getType())) {
                  mv.visitMethodInsn(184, "java/util/Calendar", "getInstance", "()Ljava/util/Calendar;");
                  mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), "Ljava/util/Calendar;");
                  mv.visitVarInsn(25, 0);
                  mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), "Ljava/util/Calendar;");
                  mv.visitVarInsn(25, astorePosition);
                  mv.visitMethodInsn(184, "java/lang/Long", "valueOf", "(Ljava/lang/String;)Ljava/lang/Long;");
                  mv.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
                  mv.visitMethodInsn(182, "java/util/Calendar", "setTimeInMillis", "(J)V");
               } else {
                  String fieldTypeName = this.getTypeNameForField(mmd);
                  String fieldTypeName_ASM = fieldTypeName.replace('.', '/');
                  mv.visitTypeInsn(187, fieldTypeName_ASM);
                  mv.visitInsn(89);
                  mv.visitVarInsn(25, astorePosition);
                  mv.visitMethodInsn(183, fieldTypeName_ASM, "<init>", "(Ljava/lang/String;)V");
                  mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(fieldTypeName));
               }
            } else {
               mv.visitVarInsn(25, astorePosition);
               mv.visitMethodInsn(184, typeName_ASM, "valueOf", "(Ljava/lang/String;)L" + typeName_ASM + ";");
               mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), "L" + typeName_ASM + ";");
            }
         } else {
            String type_desc = EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName());
            String wrapperClassName_ASM = "java/lang/Long";
            String wrapperConverterMethod = "longValue";
            if (mmd.getType() == Integer.TYPE) {
               wrapperClassName_ASM = "java/lang/Integer";
               wrapperConverterMethod = "intValue";
            } else if (mmd.getType() == Short.TYPE) {
               wrapperClassName_ASM = "java/lang/Short";
               wrapperConverterMethod = "shortValue";
            }

            mv.visitVarInsn(25, astorePosition);
            mv.visitMethodInsn(184, wrapperClassName_ASM, "valueOf", "(Ljava/lang/String;)L" + wrapperClassName_ASM + ";");
            mv.visitMethodInsn(182, wrapperClassName_ASM, wrapperConverterMethod, "()" + type_desc);
            mv.visitFieldInsn(181, this.className_ASM, mmd.getName(), type_desc);
         }
      }

      mv.visitInsn(177);
      Label endLabel = new Label();
      mv.visitLabel(endLabel);
      int variableNum = 0;
      mv.visitLocalVariable("this", this.className_DescName, (String)null, startLabel, endLabel, variableNum++);
      mv.visitLocalVariable("str", "Ljava/lang/String;", (String)null, startLabel, endLabel, variableNum++);
      mv.visitLocalVariable("tokeniser", "Ljava/util/StringTokenizer;", (String)null, l5, endLabel, variableNum++);

      for(int i = 0; i < pkPositions.length; ++i) {
         mv.visitLocalVariable("token" + i, "Ljava/lang/String;", (String)null, fieldLabels[i], endLabel, variableNum++);
      }

      mv.visitMaxs(6, variableNum);
      mv.visitEnd();
   }

   protected void addMethodToString(ClassWriter cw) {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005019", "String " + this.pkClassName + ".toString()"));
      }

      MethodVisitor mv = cw.visitMethod(1, "toString", "()Ljava/lang/String;", (String)null, (String[])null);
      mv.visitCode();
      Label startLabel = new Label();
      mv.visitLabel(startLabel);
      mv.visitTypeInsn(187, "java/lang/StringBuilder");
      mv.visitInsn(89);
      mv.visitMethodInsn(183, "java/lang/StringBuilder", "<init>", "()V");
      mv.visitVarInsn(58, 1);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitVarInsn(25, 1);
      int[] pkPositions = this.cmd.getPKMemberPositions();

      for(int i = 0; i < pkPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(pkPositions[i]);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
         if (mmd.getType() != Integer.TYPE && mmd.getType() != Long.TYPE && mmd.getType() != Float.TYPE && mmd.getType() != Double.TYPE) {
            if (mmd.getType() == Character.TYPE) {
               mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(" + EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()) + ")Ljava/lang/StringBuilder;");
            } else if (mmd.getType() == String.class) {
               mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            } else if (Date.class.isAssignableFrom(mmd.getType())) {
               mv.visitMethodInsn(182, mmd.getTypeName().replace('.', '/'), "getTime", "()J");
               mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;");
            } else if (Calendar.class.isAssignableFrom(mmd.getType())) {
               mv.visitMethodInsn(182, "java/util/Calendar", "getTime", "()Ljava/util/Date;");
               mv.visitMethodInsn(182, "java/util/Date", "getTime", "()J");
               mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;");
            } else if (mmd.getType() == TimeZone.class) {
               mv.visitMethodInsn(182, "java/util/TimeZone", "getID", "()Ljava/lang/String;");
               mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            } else {
               mv.visitMethodInsn(182, mmd.getTypeName().replace('.', '/'), "toString", "()Ljava/lang/String;");
               mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            }
         } else {
            mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(" + EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()) + ")Ljava/lang/StringBuilder;");
         }

         if (i < pkPositions.length - 1) {
            mv.visitLdcInsn(this.stringSeparator);
            mv.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
         }
      }

      mv.visitInsn(87);
      mv.visitVarInsn(25, 1);
      mv.visitMethodInsn(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
      mv.visitInsn(176);
      Label endLabel = new Label();
      mv.visitLabel(endLabel);
      mv.visitLocalVariable("this", this.className_DescName, (String)null, startLabel, endLabel, 0);
      mv.visitLocalVariable("str", "Ljava/lang/StringBuilder;", (String)null, l1, endLabel, 1);
      mv.visitMaxs(pkPositions.length, 2);
      mv.visitEnd();
   }

   protected void addMethodEquals(ClassWriter cw) {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005019", "boolean " + this.pkClassName + ".equals(Object obj)"));
      }

      MethodVisitor mv = cw.visitMethod(1, "equals", "(Ljava/lang/Object;)Z", (String)null, (String[])null);
      mv.visitCode();
      Label startLabel = new Label();
      mv.visitLabel(startLabel);
      mv.visitVarInsn(25, 1);
      mv.visitVarInsn(25, 0);
      Label l1 = new Label();
      mv.visitJumpInsn(166, l1);
      mv.visitInsn(4);
      mv.visitInsn(172);
      mv.visitLabel(l1);
      mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      mv.visitVarInsn(25, 1);
      mv.visitTypeInsn(193, this.className_ASM);
      Label l3 = new Label();
      mv.visitJumpInsn(154, l3);
      mv.visitInsn(3);
      mv.visitInsn(172);
      mv.visitLabel(l3);
      mv.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      mv.visitVarInsn(25, 1);
      mv.visitTypeInsn(192, this.className_ASM);
      mv.visitVarInsn(58, 2);
      Label compareStartLabel = new Label();
      mv.visitLabel(compareStartLabel);
      int[] pkPositions = this.cmd.getPKMemberPositions();
      Label compareSepLabel = null;

      for(int i = 0; i < pkPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(pkPositions[i]);
         if (mmd.getType() == Long.TYPE) {
            mv.visitVarInsn(25, 0);
            mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
            mv.visitVarInsn(25, 2);
            mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
            mv.visitInsn(148);
            if (i == 0) {
               compareSepLabel = new Label();
            }

            mv.visitJumpInsn(154, compareSepLabel);
         } else if (mmd.getType() != Integer.TYPE && mmd.getType() != Short.TYPE && mmd.getType() != Character.TYPE) {
            String typeName = this.getTypeNameForField(mmd);
            String typeName_ASM = typeName.replace('.', '/');
            String typeNameDesc = "L" + typeName_ASM + ";";
            mv.visitVarInsn(25, 0);
            mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), typeNameDesc);
            mv.visitVarInsn(25, 2);
            mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), typeNameDesc);
            mv.visitMethodInsn(182, typeName_ASM, "equals", "(Ljava/lang/Object;)Z");
            if (i == 0) {
               compareSepLabel = new Label();
            }

            mv.visitJumpInsn(153, compareSepLabel);
         } else {
            mv.visitVarInsn(25, 0);
            mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
            mv.visitVarInsn(25, 2);
            mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
            if (i == 0) {
               compareSepLabel = new Label();
            }

            mv.visitJumpInsn(160, compareSepLabel);
         }
      }

      mv.visitInsn(4);
      mv.visitInsn(172);
      mv.visitLabel(compareSepLabel);
      mv.visitFrame(1, 1, new Object[]{this.className_ASM}, 0, (Object[])null);
      mv.visitInsn(3);
      mv.visitInsn(172);
      Label endLabel = new Label();
      mv.visitLabel(endLabel);
      mv.visitLocalVariable("this", this.className_DescName, (String)null, startLabel, endLabel, 0);
      mv.visitLocalVariable("obj", "Ljava/lang/Object;", (String)null, startLabel, endLabel, 1);
      mv.visitLocalVariable("other", this.className_DescName, (String)null, compareStartLabel, endLabel, 2);
      mv.visitMaxs(4, 3);
      mv.visitEnd();
   }

   protected void addMethodHashCode(ClassWriter cw) {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005019", "int " + this.pkClassName + ".hashCode()"));
      }

      MethodVisitor mv = cw.visitMethod(1, "hashCode", "()I", (String)null, (String[])null);
      mv.visitCode();
      Label startLabel = new Label();
      mv.visitLabel(startLabel);
      int[] pkPositions = this.cmd.getPKMemberPositions();

      for(int i = 0; i < pkPositions.length; ++i) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(pkPositions[i]);
         mv.visitVarInsn(25, 0);
         mv.visitFieldInsn(180, this.className_ASM, mmd.getName(), EnhanceUtils.getTypeDescriptorForType(mmd.getTypeName()));
         if (mmd.getType() == Long.TYPE) {
            mv.visitInsn(136);
         } else if (mmd.getType() != Integer.TYPE && mmd.getType() != Short.TYPE && mmd.getType() != Character.TYPE) {
            mv.visitMethodInsn(182, mmd.getTypeName().replace('.', '/'), "hashCode", "()I");
         }

         if (i > 0) {
            mv.visitInsn(130);
         }
      }

      mv.visitInsn(172);
      Label endLabel = new Label();
      mv.visitLabel(endLabel);
      mv.visitLocalVariable("this", this.className_DescName, (String)null, startLabel, endLabel, 0);
      mv.visitMaxs(3, 1);
      mv.visitEnd();
   }
}
