package org.datanucleus.enhancer;

import java.io.File;
import java.io.FileOutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassLoaderResolverImpl;
import org.datanucleus.asm.ClassWriter;
import org.datanucleus.asm.Label;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.util.ClassUtils;

public class ImplementationGenerator {
   protected final MetaDataManager metaDataMgr;
   protected final AbstractClassMetaData inputCmd;
   protected final String className;
   protected final String fullClassName;
   protected String fullSuperclassName = "java.lang.Object";
   protected byte[] bytes;
   ClassWriter writer;
   String asmClassName;
   String asmTypeDescriptor;
   EnhancementNamer namer = JDOEnhancementNamer.getInstance();

   public ImplementationGenerator(InterfaceMetaData interfaceMetaData, String implClassName, MetaDataManager mmgr) {
      this.className = implClassName;
      this.fullClassName = interfaceMetaData.getPackageName() + '.' + this.className;
      this.inputCmd = interfaceMetaData;
      this.metaDataMgr = mmgr;
      this.asmClassName = this.fullClassName.replace('.', '/');
      this.asmTypeDescriptor = "L" + this.asmClassName + ";";
      List<String> interfaces = new ArrayList();
      InterfaceMetaData imd = interfaceMetaData;

      do {
         String intfTypeName = imd.getFullClassName().replace('.', '/');
         interfaces.add(intfTypeName);
         imd = (InterfaceMetaData)imd.getSuperAbstractClassMetaData();
      } while(imd != null);

      this.writer = new ClassWriter(1);
      this.writer.visit(EnhanceUtils.getAsmVersionForJRE(), 33, this.fullClassName.replace('.', '/'), (String)null, this.fullSuperclassName.replace('.', '/'), (String[])interfaces.toArray(new String[interfaces.size()]));
      this.createPropertyFields();
      this.createDefaultConstructor();
      this.createPropertyMethods();
      this.writer.visitEnd();
      this.bytes = this.writer.toByteArray();
   }

   public ImplementationGenerator(ClassMetaData cmd, String implClassName, MetaDataManager mmgr) {
      this.className = implClassName;
      this.fullClassName = cmd.getPackageName() + '.' + this.className;
      this.inputCmd = cmd;
      this.metaDataMgr = mmgr;
      this.asmClassName = this.fullClassName.replace('.', '/');
      this.asmTypeDescriptor = "L" + this.asmClassName + ";";
      this.fullSuperclassName = cmd.getFullClassName();
      this.writer = new ClassWriter(1);
      this.writer.visit(EnhanceUtils.getAsmVersionForJRE(), 33, this.fullClassName.replace('.', '/'), (String)null, this.fullSuperclassName.replace('.', '/'), (String[])null);
      this.createPropertyFields();
      this.createDefaultConstructor();
      this.createPropertyMethods();
      this.writer.visitEnd();
      this.bytes = this.writer.toByteArray();
   }

   public byte[] getBytes() {
      return this.bytes;
   }

   protected void createPropertyFields() {
      AbstractClassMetaData acmd = this.inputCmd;

      do {
         this.createPropertyFields(acmd);
         acmd = acmd.getSuperAbstractClassMetaData();
      } while(acmd != null);

   }

   protected void createPropertyMethods() {
      AbstractClassMetaData acmd = this.inputCmd;

      do {
         this.createPropertyMethods(acmd);
         acmd = acmd.getSuperAbstractClassMetaData();
      } while(acmd != null);

   }

   protected void createPropertyMethods(AbstractClassMetaData acmd) {
      if (acmd != null) {
         AbstractMemberMetaData[] memberMetaData = acmd.getManagedMembers();

         for(int i = 0; i < memberMetaData.length; ++i) {
            this.createGetter(memberMetaData[i]);
            this.createSetter(memberMetaData[i]);
         }

      }
   }

   public void dumpToFile(String filename) {
      FileOutputStream out = null;

      try {
         out = new FileOutputStream(new File(filename));
         out.write(this.getBytes());
         DataNucleusEnhancer.LOGGER.info("Generated class for " + this.fullClassName + " dumped to " + filename);
      } catch (Exception e) {
         DataNucleusEnhancer.LOGGER.error("Failure to dump generated class to file", e);
      } finally {
         try {
            out.close();
            FileOutputStream var14 = null;
         } catch (Exception var11) {
         }

      }

   }

   public void enhance(ClassLoaderResolver clr) {
      EnhancerClassLoader loader = new EnhancerClassLoader();
      loader.defineClass(this.fullClassName, this.getBytes(), clr);
      final ClassLoaderResolver genclr = new ClassLoaderResolverImpl(loader);
      final ClassMetaData implementationCmd;
      if (this.inputCmd instanceof InterfaceMetaData) {
         implementationCmd = new ClassMetaData((InterfaceMetaData)this.inputCmd, this.className, true);
      } else {
         implementationCmd = new ClassMetaData((ClassMetaData)this.inputCmd, this.className);
      }

      AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            implementationCmd.populate(genclr, (ClassLoader)null, ImplementationGenerator.this.metaDataMgr);
            implementationCmd.initialise(genclr, ImplementationGenerator.this.metaDataMgr);
            return null;
         }
      });
      ClassEnhancer gen = new ClassEnhancerImpl(implementationCmd, genclr, this.metaDataMgr, JDOEnhancementNamer.getInstance(), this.getBytes());
      gen.enhance();
      this.bytes = gen.getClassBytes();
   }

   protected void createPropertyFields(AbstractClassMetaData acmd) {
      if (acmd != null) {
         AbstractMemberMetaData[] propertyMetaData = acmd.getManagedMembers();

         for(int i = 0; i < propertyMetaData.length; ++i) {
            this.writer.visitField(2, propertyMetaData[i].getName(), Type.getDescriptor(propertyMetaData[i].getType()), (String)null, (Object)null).visitEnd();
         }

      }
   }

   protected void createDefaultConstructor() {
      MethodVisitor visitor = this.writer.visitMethod(1, "<init>", "()V", (String)null, (String[])null);
      visitor.visitCode();
      Label l0 = new Label();
      visitor.visitLabel(l0);
      visitor.visitVarInsn(25, 0);
      visitor.visitMethodInsn(183, this.fullSuperclassName.replace('.', '/'), "<init>", "()V");
      visitor.visitInsn(177);
      Label l1 = new Label();
      visitor.visitLabel(l1);
      visitor.visitLocalVariable("this", this.asmTypeDescriptor, (String)null, l0, l1, 0);
      visitor.visitMaxs(1, 1);
      visitor.visitEnd();
   }

   protected void createGetter(AbstractMemberMetaData mmd) {
      boolean isBoolean = mmd.getTypeName().equals("boolean");
      String getterName = ClassUtils.getJavaBeanGetterName(mmd.getName(), isBoolean);
      String dnGetterName = this.namer.getGetMethodPrefixMethodName() + mmd.getName();
      if (this.inputCmd instanceof InterfaceMetaData) {
         String fieldDesc = Type.getDescriptor(mmd.getType());
         MethodVisitor visitor = this.writer.visitMethod(1, getterName, "()" + fieldDesc, (String)null, (String[])null);
         visitor.visitCode();
         Label l0 = new Label();
         visitor.visitLabel(l0);
         visitor.visitVarInsn(25, 0);
         visitor.visitFieldInsn(180, this.asmClassName, mmd.getName(), fieldDesc);
         EnhanceUtils.addReturnForType(visitor, mmd.getType());
         Label l1 = new Label();
         visitor.visitLabel(l1);
         visitor.visitLocalVariable("this", this.asmTypeDescriptor, (String)null, l0, l1, 0);
         visitor.visitMaxs(1, 1);
         visitor.visitEnd();
      } else {
         String fieldDesc = Type.getDescriptor(mmd.getType());
         int getAccess = (mmd.isPublic() ? 1 : 0) | (mmd.isProtected() ? 4 : 0) | (mmd.isPrivate() ? 2 : 0);
         MethodVisitor getVisitor = this.writer.visitMethod(getAccess, getterName, "()" + fieldDesc, (String)null, (String[])null);
         EnhancerPropertyGetterAdapter.generateGetXXXMethod(getVisitor, mmd, this.asmClassName, this.asmTypeDescriptor, false, this.namer);
         int access = (mmd.isPublic() ? 1 : 0) | (mmd.isProtected() ? 4 : 0) | (mmd.isPrivate() ? 2 : 0);
         MethodVisitor visitor = this.writer.visitMethod(access, dnGetterName, "()" + fieldDesc, (String)null, (String[])null);
         visitor.visitCode();
         Label l0 = new Label();
         visitor.visitLabel(l0);
         visitor.visitVarInsn(25, 0);
         visitor.visitFieldInsn(180, this.asmClassName, mmd.getName(), fieldDesc);
         EnhanceUtils.addReturnForType(visitor, mmd.getType());
         Label l1 = new Label();
         visitor.visitLabel(l1);
         visitor.visitLocalVariable("this", this.asmTypeDescriptor, (String)null, l0, l1, 0);
         visitor.visitMaxs(1, 1);
         visitor.visitEnd();
      }

   }

   protected void createSetter(AbstractMemberMetaData mmd) {
      String setterName = ClassUtils.getJavaBeanSetterName(mmd.getName());
      String dnSetterName = this.namer.getSetMethodPrefixMethodName() + mmd.getName();
      if (this.inputCmd instanceof InterfaceMetaData) {
         String fieldDesc = Type.getDescriptor(mmd.getType());
         MethodVisitor visitor = this.writer.visitMethod(1, setterName, "(" + fieldDesc + ")V", (String)null, (String[])null);
         visitor.visitCode();
         Label l0 = new Label();
         visitor.visitLabel(l0);
         visitor.visitVarInsn(25, 0);
         EnhanceUtils.addLoadForType(visitor, mmd.getType(), 1);
         visitor.visitFieldInsn(181, this.asmClassName, mmd.getName(), fieldDesc);
         visitor.visitInsn(177);
         Label l2 = new Label();
         visitor.visitLabel(l2);
         visitor.visitLocalVariable("this", this.asmTypeDescriptor, (String)null, l0, l2, 0);
         visitor.visitLocalVariable("val", fieldDesc, (String)null, l0, l2, 1);
         visitor.visitMaxs(2, 2);
         visitor.visitEnd();
      } else {
         String fieldDesc = Type.getDescriptor(mmd.getType());
         int setAccess = (mmd.isPublic() ? 1 : 0) | (mmd.isProtected() ? 4 : 0) | (mmd.isPrivate() ? 2 : 0);
         MethodVisitor setVisitor = this.writer.visitMethod(setAccess, setterName, "(" + fieldDesc + ")V", (String)null, (String[])null);
         EnhancerPropertySetterAdapter.generateSetXXXMethod(setVisitor, mmd, this.asmClassName, this.asmTypeDescriptor, this.namer);
         int access = (mmd.isPublic() ? 1 : 0) | (mmd.isProtected() ? 4 : 0) | (mmd.isPrivate() ? 2 : 0);
         MethodVisitor visitor = this.writer.visitMethod(access, dnSetterName, "(" + fieldDesc + ")V", (String)null, (String[])null);
         visitor.visitCode();
         Label l0 = new Label();
         visitor.visitLabel(l0);
         visitor.visitVarInsn(25, 0);
         EnhanceUtils.addLoadForType(visitor, mmd.getType(), 1);
         visitor.visitFieldInsn(181, this.asmClassName, mmd.getName(), fieldDesc);
         visitor.visitInsn(177);
         Label l2 = new Label();
         visitor.visitLabel(l2);
         visitor.visitLocalVariable("this", this.asmTypeDescriptor, (String)null, l0, l2, 0);
         visitor.visitLocalVariable("val", fieldDesc, (String)null, l0, l2, 1);
         visitor.visitMaxs(2, 2);
         visitor.visitEnd();
      }

   }
}
