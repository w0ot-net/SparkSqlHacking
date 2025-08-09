package org.datanucleus.enhancer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.asm.AnnotationVisitor;
import org.datanucleus.asm.Attribute;
import org.datanucleus.asm.ClassReader;
import org.datanucleus.asm.ClassVisitor;
import org.datanucleus.asm.ClassWriter;
import org.datanucleus.asm.FieldVisitor;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.methods.CopyField;
import org.datanucleus.enhancer.methods.CopyFields;
import org.datanucleus.enhancer.methods.CopyKeyFieldsFromObjectId;
import org.datanucleus.enhancer.methods.CopyKeyFieldsFromObjectId2;
import org.datanucleus.enhancer.methods.CopyKeyFieldsToObjectId;
import org.datanucleus.enhancer.methods.CopyKeyFieldsToObjectId2;
import org.datanucleus.enhancer.methods.GetExecutionContext;
import org.datanucleus.enhancer.methods.GetInheritedFieldCount;
import org.datanucleus.enhancer.methods.GetManagedFieldCount;
import org.datanucleus.enhancer.methods.GetObjectId;
import org.datanucleus.enhancer.methods.GetTransactionalObjectId;
import org.datanucleus.enhancer.methods.GetVersion;
import org.datanucleus.enhancer.methods.InitFieldFlags;
import org.datanucleus.enhancer.methods.InitFieldNames;
import org.datanucleus.enhancer.methods.InitFieldTypes;
import org.datanucleus.enhancer.methods.InitPersistableSuperclass;
import org.datanucleus.enhancer.methods.IsDeleted;
import org.datanucleus.enhancer.methods.IsDetached;
import org.datanucleus.enhancer.methods.IsDirty;
import org.datanucleus.enhancer.methods.IsNew;
import org.datanucleus.enhancer.methods.IsPersistent;
import org.datanucleus.enhancer.methods.IsTransactional;
import org.datanucleus.enhancer.methods.LoadClass;
import org.datanucleus.enhancer.methods.MakeDirty;
import org.datanucleus.enhancer.methods.NewInstance1;
import org.datanucleus.enhancer.methods.NewInstance2;
import org.datanucleus.enhancer.methods.NewObjectIdInstance1;
import org.datanucleus.enhancer.methods.NewObjectIdInstance2;
import org.datanucleus.enhancer.methods.PreSerialize;
import org.datanucleus.enhancer.methods.ProvideField;
import org.datanucleus.enhancer.methods.ProvideFields;
import org.datanucleus.enhancer.methods.ReplaceDetachedState;
import org.datanucleus.enhancer.methods.ReplaceField;
import org.datanucleus.enhancer.methods.ReplaceFields;
import org.datanucleus.enhancer.methods.ReplaceFlags;
import org.datanucleus.enhancer.methods.ReplaceStateManager;
import org.datanucleus.enhancer.methods.SuperClone;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InvalidMetaDataException;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class ClassEnhancerImpl implements ClassEnhancer {
   protected final ClassLoaderResolver clr;
   protected final MetaDataManager metaDataMgr;
   protected final ClassMetaData cmd;
   public final String className;
   protected boolean update = false;
   protected List fieldsToAdd = new ArrayList();
   protected List methodsToAdd = new ArrayList();
   protected boolean initialised = false;
   protected Collection options = new HashSet();
   protected String inputResourceName;
   protected byte[] inputBytes;
   protected final Class cls;
   protected byte[] classBytes = null;
   protected byte[] pkClassBytes = null;
   protected String asmClassName = null;
   protected String classDescriptor = null;
   protected EnhancementNamer namer = null;

   public ClassEnhancerImpl(ClassMetaData cmd, ClassLoaderResolver clr, MetaDataManager mmgr, EnhancementNamer namer) {
      this.clr = clr;
      this.cmd = cmd;
      this.className = cmd.getFullClassName();
      this.metaDataMgr = mmgr;
      this.namer = namer;
      this.cls = clr.classForName(cmd.getFullClassName());
      this.asmClassName = cmd.getFullClassName().replace('.', '/');
      this.classDescriptor = Type.getDescriptor(this.cls);
      this.inputResourceName = "/" + this.className.replace('.', '/') + ".class";
   }

   public ClassEnhancerImpl(ClassMetaData cmd, ClassLoaderResolver clr, MetaDataManager mmgr, EnhancementNamer namer, byte[] classBytes) {
      this.clr = clr;
      this.cmd = cmd;
      this.className = cmd.getFullClassName();
      this.metaDataMgr = mmgr;
      this.namer = namer;
      this.cls = clr.classForName(cmd.getFullClassName());
      this.asmClassName = cmd.getFullClassName().replace('.', '/');
      this.classDescriptor = Type.getDescriptor(this.cls);
      this.inputBytes = classBytes;
   }

   protected void initialise() {
      if (!this.initialised) {
         this.initialiseFieldsList();
         this.initialiseMethodsList();
         this.initialised = true;
      }
   }

   public String getClassName() {
      return this.className;
   }

   public List getMethodsList() {
      return this.methodsToAdd;
   }

   public List getFieldsList() {
      return this.fieldsToAdd;
   }

   public ClassLoaderResolver getClassLoaderResolver() {
      return this.clr;
   }

   public MetaDataManager getMetaDataManager() {
      return this.metaDataMgr;
   }

   public ClassMetaData getClassMetaData() {
      return this.cmd;
   }

   protected boolean requiresDetachable() {
      boolean isDetachable = this.cmd.isDetachable();
      boolean hasPcsc = this.cmd.getPersistableSuperclass() != null;
      if (!hasPcsc && isDetachable) {
         return true;
      } else {
         return hasPcsc && !this.cmd.getSuperAbstractClassMetaData().isDetachable() && isDetachable;
      }
   }

   public boolean isPersistable(String className) {
      if (className.equals(this.className) && this.cmd.getPersistenceModifier() != ClassPersistenceModifier.PERSISTENCE_AWARE) {
         return true;
      } else {
         NucleusContext nucleusCtx = this.metaDataMgr.getNucleusContext();
         Class cls = this.clr.classForName(className, new EnhancerClassLoader(this.clr));
         if (nucleusCtx.getApiAdapter().isPersistable(cls)) {
            return true;
         } else {
            AbstractClassMetaData cmd = this.metaDataMgr.getMetaDataForClass(cls, this.clr);
            return cmd != null && cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE;
         }
      }
   }

   public void setOptions(Collection options) {
      if (options != null && !options.isEmpty()) {
         this.options.addAll(options);
      }
   }

   public boolean hasOption(String name) {
      return this.options.contains(name);
   }

   public void save(String directoryName) throws IOException {
      if (this.update) {
         File classFile = null;
         File pkClassFile = null;
         if (directoryName != null) {
            File baseDir = new File(directoryName);
            if (!baseDir.exists()) {
               baseDir.mkdirs();
            } else if (!baseDir.isDirectory()) {
               throw new RuntimeException("Target directory " + directoryName + " is not a directory");
            }

            String sep = System.getProperty("file.separator");
            String baseName = this.cmd.getFullClassName().replace('.', sep.charAt(0));
            classFile = new File(directoryName, baseName + ".class");
            classFile.getParentFile().mkdirs();
            if (this.getPrimaryKeyClassBytes() != null) {
               pkClassFile = new File(directoryName, baseName + "_PK" + ".class");
            }
         } else {
            String baseName = this.className.replace('.', '/');
            URL classURL = this.clr.getResource(baseName + ".class", (ClassLoader)null);
            URL convertedPath = this.metaDataMgr.getNucleusContext().getPluginManager().resolveURLAsFileURL(classURL);
            String classFilename = convertedPath.getFile();
            classFile = StringUtils.getFileForFilename(classFilename);
            String pkClassFilename = classFilename.substring(0, classFilename.length() - 6) + "_PK" + ".class";
            pkClassFile = StringUtils.getFileForFilename(pkClassFilename);
         }

         FileOutputStream out = null;

         try {
            DataNucleusEnhancer.LOGGER.info(Localiser.msg("005015", classFile));
            out = new FileOutputStream(classFile);
            out.write(this.getClassBytes());
         } finally {
            try {
               out.close();
               out = null;
            } catch (Exception var25) {
            }

         }

         byte[] pkClassBytes = this.getPrimaryKeyClassBytes();
         if (pkClassBytes != null) {
            try {
               DataNucleusEnhancer.LOGGER.info(Localiser.msg("005017", pkClassFile));
               out = new FileOutputStream(pkClassFile);
               out.write(pkClassBytes);
            } finally {
               try {
                  out.close();
                  FileOutputStream var31 = null;
               } catch (Exception var24) {
               }

            }
         }

      }
   }

   public void setNamer(EnhancementNamer namer) {
      this.namer = namer;
   }

   public static String getClassNameForFileName(String filename) {
      MyClassVisitor vis = new MyClassVisitor();

      try {
         (new ClassReader(new FileInputStream(filename))).accept(vis, 0);
         return vis.getClassName();
      } catch (IOException var3) {
         return null;
      }
   }

   public Class getClassBeingEnhanced() {
      return this.cls;
   }

   public String getASMClassName() {
      return this.asmClassName;
   }

   public String getClassDescriptor() {
      return this.classDescriptor;
   }

   protected void initialiseMethodsList() {
      if (this.cmd.getPersistableSuperclass() == null) {
         this.methodsToAdd.add(CopyKeyFieldsFromObjectId.getInstance(this));
         this.methodsToAdd.add(CopyKeyFieldsFromObjectId2.getInstance(this));
         this.methodsToAdd.add(CopyKeyFieldsToObjectId.getInstance(this));
         this.methodsToAdd.add(CopyKeyFieldsToObjectId2.getInstance(this));
         this.methodsToAdd.add(GetObjectId.getInstance(this));
         this.methodsToAdd.add(GetVersion.getInstance(this));
         this.methodsToAdd.add(PreSerialize.getInstance(this));
         this.methodsToAdd.add(GetExecutionContext.getInstance(this));
         this.methodsToAdd.add(GetTransactionalObjectId.getInstance(this));
         this.methodsToAdd.add(IsDeleted.getInstance(this));
         this.methodsToAdd.add(IsDirty.getInstance(this));
         this.methodsToAdd.add(IsNew.getInstance(this));
         this.methodsToAdd.add(IsPersistent.getInstance(this));
         this.methodsToAdd.add(IsTransactional.getInstance(this));
         this.methodsToAdd.add(MakeDirty.getInstance(this));
         this.methodsToAdd.add(NewObjectIdInstance1.getInstance(this));
         this.methodsToAdd.add(NewObjectIdInstance2.getInstance(this));
         this.methodsToAdd.add(ProvideFields.getInstance(this));
         this.methodsToAdd.add(ReplaceFields.getInstance(this));
         this.methodsToAdd.add(ReplaceFlags.getInstance(this));
         this.methodsToAdd.add(ReplaceStateManager.getInstance(this));
      }

      if (this.cmd.getPersistableSuperclass() != null && this.cmd.isRootInstantiableClass()) {
         this.methodsToAdd.add(CopyKeyFieldsFromObjectId.getInstance(this));
         this.methodsToAdd.add(CopyKeyFieldsFromObjectId2.getInstance(this));
         this.methodsToAdd.add(CopyKeyFieldsToObjectId.getInstance(this));
         this.methodsToAdd.add(CopyKeyFieldsToObjectId2.getInstance(this));
         this.methodsToAdd.add(NewObjectIdInstance1.getInstance(this));
         this.methodsToAdd.add(NewObjectIdInstance2.getInstance(this));
      }

      if (this.requiresDetachable()) {
         this.methodsToAdd.add(ReplaceDetachedState.getInstance(this));
      }

      if (this.cmd.isDetachable() && this.cmd.getPersistableSuperclass() != null) {
         this.methodsToAdd.add(MakeDirty.getInstance(this));
      }

      this.methodsToAdd.add(IsDetached.getInstance(this));
      this.methodsToAdd.add(NewInstance1.getInstance(this));
      this.methodsToAdd.add(NewInstance2.getInstance(this));
      this.methodsToAdd.add(ReplaceField.getInstance(this));
      this.methodsToAdd.add(ProvideField.getInstance(this));
      this.methodsToAdd.add(CopyField.getInstance(this));
      this.methodsToAdd.add(CopyFields.getInstance(this));
      this.methodsToAdd.add(InitFieldNames.getInstance(this));
      this.methodsToAdd.add(InitFieldTypes.getInstance(this));
      this.methodsToAdd.add(InitFieldFlags.getInstance(this));
      this.methodsToAdd.add(GetInheritedFieldCount.getInstance(this));
      this.methodsToAdd.add(GetManagedFieldCount.getInstance(this));
      this.methodsToAdd.add(InitPersistableSuperclass.getInstance(this));
      this.methodsToAdd.add(LoadClass.getInstance(this));
      this.methodsToAdd.add(SuperClone.getInstance(this));
   }

   protected void initialiseFieldsList() {
      if (this.cmd.getPersistableSuperclass() == null) {
         this.fieldsToAdd.add(new ClassField(this, this.namer.getStateManagerFieldName(), 132, this.namer.getStateManagerClass()));
         this.fieldsToAdd.add(new ClassField(this, this.namer.getFlagsFieldName(), 132, Byte.TYPE));
      }

      if (this.requiresDetachable()) {
         this.fieldsToAdd.add(new ClassField(this, this.namer.getDetachedStateFieldName(), 4, Object[].class));
      }

      this.fieldsToAdd.add(new ClassField(this, this.namer.getFieldFlagsFieldName(), 26, byte[].class));
      this.fieldsToAdd.add(new ClassField(this, this.namer.getPersistableSuperclassFieldName(), 26, Class.class));
      this.fieldsToAdd.add(new ClassField(this, this.namer.getFieldTypesFieldName(), 26, Class[].class));
      this.fieldsToAdd.add(new ClassField(this, this.namer.getFieldNamesFieldName(), 26, String[].class));
      this.fieldsToAdd.add(new ClassField(this, this.namer.getInheritedFieldCountFieldName(), 26, Integer.TYPE));
   }

   public boolean enhance() {
      if (this.cmd.getPersistenceModifier() != ClassPersistenceModifier.PERSISTENCE_CAPABLE && this.cmd.getPersistenceModifier() != ClassPersistenceModifier.PERSISTENCE_AWARE) {
         return false;
      } else {
         this.initialise();
         if (this.checkClassIsEnhanced(false)) {
            DataNucleusEnhancer.LOGGER.info(Localiser.msg("005014", this.className));
            return true;
         } else {
            try {
               if (this.cmd.getIdentityType() == IdentityType.APPLICATION && this.cmd.getObjectidClass() == null && this.cmd.getNoOfPrimaryKeyMembers() > 1) {
                  if (!this.hasOption("generate-primary-key")) {
                     throw new InvalidMetaDataException("044065", new Object[]{this.cmd.getFullClassName(), this.cmd.getNoOfPrimaryKeyMembers()});
                  }

                  String pkClassName = this.cmd.getFullClassName() + "_PK";
                  if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                     DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005016", this.cmd.getFullClassName(), pkClassName));
                  }

                  this.cmd.setObjectIdClass(pkClassName);
                  PrimaryKeyGenerator pkGen = new PrimaryKeyGenerator(this.cmd, this);
                  this.pkClassBytes = pkGen.generate();
               }

               ClassWriter cw = new ClassWriter(1);
               EnhancerClassAdapter cv = new EnhancerClassAdapter(cw, this);
               ClassReader cr = null;
               InputStream classReaderInputStream = null;

               try {
                  if (this.inputBytes != null) {
                     cr = new ClassReader(this.inputBytes);
                  } else {
                     classReaderInputStream = this.clr.getResource(this.inputResourceName, (ClassLoader)null).openStream();
                     cr = new ClassReader(classReaderInputStream);
                  }

                  cr.accept(cv, 0);
                  this.classBytes = cw.toByteArray();
               } finally {
                  if (classReaderInputStream != null) {
                     classReaderInputStream.close();
                  }

               }
            } catch (Exception e) {
               DataNucleusEnhancer.LOGGER.error("Error thrown enhancing with ASMClassEnhancer", e);
               return false;
            }

            this.update = true;
            return true;
         }
      }
   }

   public byte[] getClassBytes() {
      return this.classBytes;
   }

   public byte[] getPrimaryKeyClassBytes() {
      return this.pkClassBytes;
   }

   public boolean validate() {
      if (this.cmd.getPersistenceModifier() != ClassPersistenceModifier.PERSISTENCE_CAPABLE && this.cmd.getPersistenceModifier() != ClassPersistenceModifier.PERSISTENCE_AWARE) {
         return false;
      } else {
         this.initialise();
         return this.checkClassIsEnhanced(true);
      }
   }

   protected boolean checkClassIsEnhanced(boolean logErrors) {
      try {
         EnhancerClassChecker checker = new EnhancerClassChecker(this, logErrors);
         InputStream classReaderInputStream = null;

         try {
            ClassReader cr = null;
            if (this.inputBytes != null) {
               cr = new ClassReader(this.inputBytes);
            } else {
               classReaderInputStream = this.clr.getResource(this.inputResourceName, (ClassLoader)null).openStream();
               cr = new ClassReader(classReaderInputStream);
            }

            cr.accept(checker, 0);
         } finally {
            if (classReaderInputStream != null) {
               classReaderInputStream.close();
            }

         }

         return checker.isEnhanced();
      } catch (Exception e) {
         DataNucleusEnhancer.LOGGER.error("Error thrown enhancing with ASMClassEnhancer", e);
         return false;
      }
   }

   public EnhancementNamer getNamer() {
      return this.namer;
   }

   public static class MyClassVisitor extends ClassVisitor {
      String className = null;

      public MyClassVisitor() {
         super(327680);
      }

      public String getClassName() {
         return this.className;
      }

      public void visitInnerClass(String name, String outerName, String innerName, int access) {
      }

      public void visit(int version, int access, String name, String sig, String supername, String[] intfs) {
         this.className = name.replace('/', '.');
      }

      public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
         return null;
      }

      public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
         return null;
      }

      public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] excpts) {
         return null;
      }

      public void visitAttribute(Attribute attr) {
      }

      public void visitOuterClass(String owner, String name, String desc) {
      }

      public void visitSource(String source, String debug) {
      }

      public void visitEnd() {
      }
   }
}
