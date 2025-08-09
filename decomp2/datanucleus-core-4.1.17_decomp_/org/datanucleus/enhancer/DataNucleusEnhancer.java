package org.datanucleus.enhancer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class DataNucleusEnhancer {
   public static final NucleusLogger LOGGER = NucleusLogger.getLoggerInstance("DataNucleus.Enhancer");
   private MetaDataManager metadataMgr;
   private ClassLoaderResolver clr;
   private String apiName = "JDO";
   private String enhancerVersion = null;
   private String outputDirectory = null;
   private boolean verbose = false;
   private boolean systemOut = false;
   private boolean generatePK = true;
   private boolean generateConstructor = true;
   private boolean detachListener = false;
   protected ClassLoader userClassLoader = null;
   private Collection componentsToEnhance = new ArrayList();
   private Map bytesForClassesToEnhanceByClassName = null;
   private Map enhancedBytesByClassName = null;
   private Map pkClassBytesByClassName = null;

   public DataNucleusEnhancer(String apiName, Properties props) {
      this.apiName = apiName;
      NucleusContext nucleusContext = new EnhancementNucleusContextImpl(apiName, props);
      if (props != null) {
         nucleusContext.getConfiguration().setPersistenceProperties(props);
      }

      this.metadataMgr = nucleusContext.getMetaDataManager();
      this.clr = nucleusContext.getClassLoaderResolver((ClassLoader)null);
      this.enhancerVersion = nucleusContext.getPluginManager().getVersionForBundle("org.datanucleus");
   }

   public MetaDataManager getMetaDataManager() {
      return this.metadataMgr;
   }

   public String getOutputDirectory() {
      return this.outputDirectory;
   }

   public DataNucleusEnhancer setOutputDirectory(String dir) {
      this.resetEnhancement();
      this.outputDirectory = dir;
      return this;
   }

   public ClassLoader getClassLoader() {
      return this.userClassLoader;
   }

   public DataNucleusEnhancer setClassLoader(ClassLoader loader) {
      this.resetEnhancement();
      this.userClassLoader = loader;
      if (this.userClassLoader != null) {
         this.clr.registerUserClassLoader(this.userClassLoader);
      }

      return this;
   }

   public boolean isVerbose() {
      return this.verbose;
   }

   public DataNucleusEnhancer setVerbose(boolean verbose) {
      this.resetEnhancement();
      this.verbose = verbose;
      return this;
   }

   public DataNucleusEnhancer setSystemOut(boolean sysout) {
      this.resetEnhancement();
      this.systemOut = sysout;
      return this;
   }

   public DataNucleusEnhancer setGeneratePK(boolean flag) {
      this.resetEnhancement();
      this.generatePK = flag;
      return this;
   }

   public DataNucleusEnhancer setGenerateConstructor(boolean flag) {
      this.resetEnhancement();
      this.generateConstructor = flag;
      return this;
   }

   public DataNucleusEnhancer setDetachListener(boolean flag) {
      this.resetEnhancement();
      this.detachListener = flag;
      return this;
   }

   public DataNucleusEnhancer addClass(String className, byte[] bytes) {
      if (className == null) {
         return this;
      } else {
         if (this.bytesForClassesToEnhanceByClassName == null) {
            this.bytesForClassesToEnhanceByClassName = new HashMap();
         }

         this.bytesForClassesToEnhanceByClassName.put(className, bytes);
         this.componentsToEnhance.add(new EnhanceComponent(0, className));
         return this;
      }
   }

   public DataNucleusEnhancer addClasses(String... classNames) {
      if (classNames == null) {
         return this;
      } else {
         Collection names = new HashSet();

         for(int i = 0; i < classNames.length; ++i) {
            if (classNames[i].endsWith(".class")) {
               String var10000 = classNames[i];
               String msg = null;
               String name;
               if (!StringUtils.getFileForFilename(classNames[i]).exists()) {
                  msg = Localiser.msg("005003", classNames[i]);
                  this.addMessage(msg, true);
                  name = null;
               } else {
                  name = ClassEnhancerImpl.getClassNameForFileName(classNames[i]);
               }

               if (name != null) {
                  names.add(name);
               }
            } else {
               try {
                  this.clr.classForName(classNames[i], false);
               } catch (ClassNotResolvedException cnre) {
                  this.addMessage("Class " + classNames[i] + " not found in CLASSPATH! : " + cnre.getMessage(), true);
               }

               names.add(classNames[i]);
            }
         }

         if (names.size() > 0) {
            this.componentsToEnhance.add(new EnhanceComponent(0, names.toArray(new String[names.size()])));
         }

         return this;
      }
   }

   public DataNucleusEnhancer addFiles(String... filenames) {
      if (filenames == null) {
         return this;
      } else {
         Collection<String> classFiles = new ArrayList();
         Collection<String> mappingFiles = new ArrayList();
         Collection<String> jarFiles = new HashSet();

         for(int i = 0; i < filenames.length; ++i) {
            if (filenames[i].endsWith(".class")) {
               classFiles.add(filenames[i]);
            } else if (filenames[i].endsWith(".jar")) {
               jarFiles.add(filenames[i]);
            } else {
               mappingFiles.add(filenames[i]);
            }
         }

         if (mappingFiles.size() > 0) {
            this.componentsToEnhance.add(new EnhanceComponent(2, mappingFiles.toArray(new String[mappingFiles.size()])));
         }

         if (jarFiles.size() > 0) {
            this.componentsToEnhance.add(new EnhanceComponent(3, jarFiles.toArray(new String[jarFiles.size()])));
         }

         if (classFiles.size() > 0) {
            this.componentsToEnhance.add(new EnhanceComponent(1, classFiles.toArray(new String[classFiles.size()])));
         }

         return this;
      }
   }

   public DataNucleusEnhancer addJar(String jarFileName) {
      if (jarFileName == null) {
         return this;
      } else {
         this.componentsToEnhance.add(new EnhanceComponent(3, jarFileName));
         return this;
      }
   }

   public DataNucleusEnhancer addPersistenceUnit(String persistenceUnitName) {
      if (persistenceUnitName == null) {
         return this;
      } else {
         this.componentsToEnhance.add(new EnhanceComponent(4, persistenceUnitName));
         return this;
      }
   }

   public DataNucleusEnhancer addPersistenceUnit(PersistenceUnitMetaData pumd) {
      if (pumd == null) {
         return this;
      } else {
         this.componentsToEnhance.add(new EnhanceComponent(4, pumd));
         return this;
      }
   }

   public int enhance() {
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("Enhancing classes");
      }

      if (this.componentsToEnhance.isEmpty()) {
         return 0;
      } else {
         long startTime = System.currentTimeMillis();
         Collection<FileMetaData> fileMetaData = this.getFileMetadataForInput();
         long inputTime = System.currentTimeMillis();
         Set<String> classNames = new HashSet();
         Iterator<FileMetaData> filemdIter = fileMetaData.iterator();
         boolean success = true;

         while(filemdIter.hasNext()) {
            FileMetaData filemd = (FileMetaData)filemdIter.next();

            for(int packagenum = 0; packagenum < filemd.getNoOfPackages(); ++packagenum) {
               PackageMetaData pmd = filemd.getPackage(packagenum);

               for(int classnum = 0; classnum < pmd.getNoOfClasses(); ++classnum) {
                  ClassMetaData cmd = pmd.getClass(classnum);
                  if (!classNames.contains(cmd.getFullClassName())) {
                     classNames.add(cmd.getFullClassName());
                     byte[] bytes = this.bytesForClassesToEnhanceByClassName != null ? (byte[])this.bytesForClassesToEnhanceByClassName.get(cmd.getFullClassName()) : null;
                     ClassEnhancer classEnhancer = this.getClassEnhancer(cmd, bytes);
                     boolean clsSuccess = this.enhanceClass(cmd, classEnhancer, bytes == null);
                     if (!clsSuccess) {
                        success = false;
                     }
                  }
               }
            }
         }

         if (!success) {
            throw new NucleusException("Failure during enhancement of classes - see the log for details");
         } else {
            long enhanceTime = System.currentTimeMillis();
            String msg = null;
            if (this.verbose) {
               msg = Localiser.msg("005004", classNames.size(), "" + (inputTime - startTime), "" + (enhanceTime - inputTime), "" + (enhanceTime - startTime));
            } else {
               msg = Localiser.msg("005005", (long)classNames.size());
            }

            this.addMessage(msg, false);
            if (this.bytesForClassesToEnhanceByClassName != null) {
               this.bytesForClassesToEnhanceByClassName.clear();
               this.bytesForClassesToEnhanceByClassName = null;
            }

            this.componentsToEnhance.clear();
            return classNames.size();
         }
      }
   }

   public int validate() {
      if (this.componentsToEnhance.isEmpty()) {
         return 0;
      } else {
         long startTime = System.currentTimeMillis();
         Collection<FileMetaData> fileMetaData = this.getFileMetadataForInput();
         long inputTime = System.currentTimeMillis();
         Set<String> classNames = new HashSet();

         for(FileMetaData filemd : fileMetaData) {
            for(int packagenum = 0; packagenum < filemd.getNoOfPackages(); ++packagenum) {
               PackageMetaData pmd = filemd.getPackage(packagenum);

               for(int classnum = 0; classnum < pmd.getNoOfClasses(); ++classnum) {
                  ClassMetaData cmd = pmd.getClass(classnum);
                  if (!classNames.contains(cmd.getFullClassName())) {
                     classNames.add(cmd.getFullClassName());
                     byte[] bytes = this.bytesForClassesToEnhanceByClassName != null ? (byte[])this.bytesForClassesToEnhanceByClassName.get(cmd.getFullClassName()) : null;
                     ClassEnhancer classEnhancer = this.getClassEnhancer(cmd, bytes);
                     this.validateClass(cmd, classEnhancer);
                  }
               }
            }
         }

         long enhanceTime = System.currentTimeMillis();
         String msg = null;
         if (this.verbose) {
            msg = Localiser.msg("005004", classNames.size(), "" + (inputTime - startTime), "" + (enhanceTime - inputTime), "" + (enhanceTime - startTime));
         } else {
            msg = Localiser.msg("005005", (long)classNames.size());
         }

         this.addMessage(msg, false);
         if (this.bytesForClassesToEnhanceByClassName != null) {
            this.bytesForClassesToEnhanceByClassName.clear();
            this.bytesForClassesToEnhanceByClassName = null;
         }

         this.componentsToEnhance.clear();
         return classNames.size();
      }
   }

   protected Collection getFileMetadataForInput() {
      Iterator<EnhanceComponent> iter = this.componentsToEnhance.iterator();
      Collection<FileMetaData> fileMetaData = new ArrayList();

      while(iter.hasNext()) {
         EnhanceComponent comp = (EnhanceComponent)iter.next();
         FileMetaData[] filemds = null;
         switch (comp.getType()) {
            case 0:
               if (comp.getValue() instanceof String) {
                  String className = (String)comp.getValue();
                  if (this.bytesForClassesToEnhanceByClassName != null && this.bytesForClassesToEnhanceByClassName.get(className) != null) {
                     AbstractClassMetaData cmd = this.metadataMgr.getMetaDataForClass(className, this.clr);
                     if (cmd != null) {
                        filemds = new FileMetaData[]{cmd.getPackageMetaData().getFileMetaData()};
                     }
                     break;
                  }

                  filemds = this.metadataMgr.loadClasses(new String[]{(String)comp.getValue()}, this.userClassLoader);
                  break;
               }

               filemds = this.metadataMgr.loadClasses((String[])comp.getValue(), this.userClassLoader);
               break;
            case 1:
               if (comp.getValue() instanceof String) {
                  String className = null;
                  String classFilename = (String)comp.getValue();
                  if (!StringUtils.getFileForFilename(classFilename).exists()) {
                     String msg = Localiser.msg("005003", classFilename);
                     this.addMessage(msg, true);
                  } else {
                     className = ClassEnhancerImpl.getClassNameForFileName(classFilename);
                  }

                  if (className != null) {
                     filemds = this.metadataMgr.loadClasses(new String[]{className}, this.userClassLoader);
                  }
                  break;
               }

               Collection<String> classNames = new ArrayList();
               String[] classFilenames = (String[])comp.getValue();
               int i = 0;

               for(; i < classFilenames.length; ++i) {
                  String className = null;
                  if (!StringUtils.getFileForFilename(classFilenames[i]).exists()) {
                     String msg = Localiser.msg("005003", classFilenames[i]);
                     this.addMessage(msg, true);
                  } else {
                     className = ClassEnhancerImpl.getClassNameForFileName(classFilenames[i]);
                  }

                  if (className != null) {
                     classNames.add(className);
                  }
               }

               filemds = this.metadataMgr.loadClasses((String[])classNames.toArray(new String[classNames.size()]), this.userClassLoader);
               break;
            case 2:
               if (comp.getValue() instanceof String) {
                  filemds = this.metadataMgr.loadMetadataFiles(new String[]{(String)comp.getValue()}, this.userClassLoader);
               } else {
                  filemds = this.metadataMgr.loadMetadataFiles((String[])comp.getValue(), this.userClassLoader);
               }
               break;
            case 3:
               if (comp.getValue() instanceof String) {
                  filemds = this.metadataMgr.loadJar((String)comp.getValue(), this.userClassLoader);
                  break;
               }

               String[] jarFilenames = (String[])comp.getValue();
               Collection<FileMetaData> filemdsColl = new HashSet();

               for(int i = 0; i < jarFilenames.length; ++i) {
                  FileMetaData[] fmds = this.metadataMgr.loadJar(jarFilenames[i], this.userClassLoader);

                  for(int j = 0; j < fmds.length; ++j) {
                     filemdsColl.add(fmds[j]);
                  }
               }

               filemds = (FileMetaData[])filemdsColl.toArray(new FileMetaData[filemdsColl.size()]);
               break;
            case 4:
               PersistenceUnitMetaData pumd = null;

               try {
                  Object puValue = comp.getValue();
                  if (puValue instanceof String) {
                     pumd = this.metadataMgr.getMetaDataForPersistenceUnit((String)comp.getValue());
                  } else {
                     pumd = (PersistenceUnitMetaData)puValue;
                  }
               } catch (NucleusException var10) {
                  throw new NucleusEnhanceException(Localiser.msg("005008", comp.getValue()));
               }

               if (pumd == null) {
                  throw new NucleusEnhanceException(Localiser.msg("005009", comp.getValue()));
               }

               filemds = this.metadataMgr.loadPersistenceUnit(pumd, this.userClassLoader);
         }

         if (filemds != null) {
            for(int i = 0; i < filemds.length; ++i) {
               fileMetaData.add(filemds[i]);
            }
         }
      }

      return fileMetaData;
   }

   public byte[] getEnhancedBytes(String className) {
      if (this.enhancedBytesByClassName != null) {
         byte[] bytes = (byte[])this.enhancedBytesByClassName.get(className);
         if (bytes != null) {
            return bytes;
         }
      }

      throw new NucleusException("No enhanced bytes available for " + className);
   }

   public byte[] getPkClassBytes(String className) {
      if (this.pkClassBytesByClassName != null) {
         byte[] bytes = (byte[])this.pkClassBytesByClassName.get(className);
         if (bytes != null) {
            return bytes;
         }
      }

      throw new NucleusException("No pk class bytes available for " + className);
   }

   protected void resetEnhancement() {
      if (this.enhancedBytesByClassName != null) {
         this.enhancedBytesByClassName.clear();
         this.enhancedBytesByClassName = null;
      }

      if (this.pkClassBytesByClassName != null) {
         this.pkClassBytesByClassName.clear();
         this.pkClassBytesByClassName = null;
      }

   }

   protected ClassEnhancer getClassEnhancer(ClassMetaData cmd, byte[] bytes) {
      ClassEnhancer classEnhancer = null;
      EnhancementNamer namer = (EnhancementNamer)(this.apiName.equalsIgnoreCase("jpa") ? JPAEnhancementNamer.getInstance() : JDOEnhancementNamer.getInstance());
      if (bytes != null) {
         classEnhancer = new ClassEnhancerImpl(cmd, this.clr, this.metadataMgr, namer, bytes);
      } else {
         classEnhancer = new ClassEnhancerImpl(cmd, this.clr, this.metadataMgr, namer);
      }

      Collection<String> options = new HashSet();
      if (this.generatePK) {
         options.add("generate-primary-key");
      }

      if (this.generateConstructor) {
         options.add("generate-default-constructor");
      }

      if (this.detachListener) {
         options.add("generate-detach-listener");
      }

      classEnhancer.setOptions(options);
      return classEnhancer;
   }

   protected void addMessage(String msg, boolean error) {
      if (error) {
         LOGGER.error(msg);
      } else {
         LOGGER.info(msg);
      }

      if (this.systemOut) {
         System.out.println(msg);
      }

   }

   protected boolean enhanceClass(ClassMetaData cmd, ClassEnhancer enhancer, boolean store) {
      boolean success = true;

      try {
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Localiser.msg("005010", cmd.getFullClassName()));
         }

         boolean enhanced = enhancer.enhance();
         if (enhanced) {
            if (this.enhancedBytesByClassName == null) {
               this.enhancedBytesByClassName = new HashMap();
            }

            this.enhancedBytesByClassName.put(cmd.getFullClassName(), enhancer.getClassBytes());
            byte[] pkClassBytes = enhancer.getPrimaryKeyClassBytes();
            if (pkClassBytes != null) {
               if (this.pkClassBytesByClassName == null) {
                  this.pkClassBytesByClassName = new HashMap();
               }

               this.pkClassBytesByClassName.put(cmd.getFullClassName(), pkClassBytes);
            }

            if (store) {
               enhancer.save(this.outputDirectory);
            }

            if (this.isVerbose()) {
               if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
                  this.addMessage("ENHANCED (Persistable) : " + cmd.getFullClassName(), false);
               } else if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_AWARE) {
                  this.addMessage("ENHANCED (PersistenceAware) : " + cmd.getFullClassName(), false);
               } else {
                  this.addMessage("NOT ENHANCED (NonPersistent) : " + cmd.getFullClassName(), false);
               }
            }
         } else {
            if (this.isVerbose()) {
               if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
                  this.addMessage("ERROR (Persistable) : " + cmd.getFullClassName(), false);
               } else if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_AWARE) {
                  this.addMessage("ERROR (PersistenceAware) : " + cmd.getFullClassName(), false);
               } else {
                  this.addMessage("NOT ENHANCED (NonPersistent) : " + cmd.getFullClassName(), false);
               }
            }

            if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE || cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_AWARE) {
               success = false;
            }
         }
      } catch (IOException ioe) {
         if (this.isVerbose()) {
            this.addMessage("ERROR (NonPersistent) : " + cmd.getFullClassName(), false);
         }

         String msg = Localiser.msg("005018", cmd.getFullClassName(), ioe.getMessage());
         LOGGER.error(msg, ioe);
         System.out.println(msg);
         success = false;
      }

      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug(Localiser.msg("005011", cmd.getFullClassName()));
      }

      return success;
   }

   protected boolean validateClass(ClassMetaData cmd, ClassEnhancer enhancer) {
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug(Localiser.msg("005012", cmd.getFullClassName()));
      }

      boolean enhanced = enhancer.validate();
      if (enhanced) {
         if (this.isVerbose()) {
            if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
               this.addMessage("ENHANCED (Persistable) : " + cmd.getFullClassName(), false);
            } else if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_AWARE) {
               this.addMessage("ENHANCED (PersistenceAware) : " + cmd.getFullClassName(), false);
            } else {
               this.addMessage("NOT ENHANCED (NonPersistent) : " + cmd.getFullClassName(), false);
            }
         }
      } else if (this.isVerbose()) {
         if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
            this.addMessage("NOT ENHANCED (Persistable) : " + cmd.getFullClassName(), false);
         } else if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_AWARE) {
            this.addMessage("NOT ENHANCED (PersistenceAware) : " + cmd.getFullClassName(), false);
         } else {
            this.addMessage("NOT ENHANCED (NonPersistent) : " + cmd.getFullClassName(), false);
         }
      }

      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug(Localiser.msg("005013", cmd.getFullClassName()));
      }

      return true;
   }

   public Properties getProperties() {
      Properties props = new Properties();
      props.setProperty("VendorName", "DataNucleus");
      props.setProperty("VersionNumber", this.enhancerVersion);
      props.setProperty("API", this.apiName);
      return props;
   }

   public String getEnhancerVersion() {
      return this.enhancerVersion;
   }

   public static void main(String[] args) throws Exception {
      CommandLineHelper clh = new CommandLineHelper(args);
      boolean quiet = clh.isQuiet();
      DataNucleusEnhancer enhancer = clh.createDataNucleusEnhancer();
      String persistenceUnitName = clh.getPersistenceUnitName();
      String directoryName = clh.getDirectory();
      String[] filenames = clh.getFiles();
      int numClasses = 0;

      try {
         if (persistenceUnitName != null) {
            enhancer.addPersistenceUnit(persistenceUnitName);
         } else if (directoryName == null) {
            enhancer.addFiles(filenames);
         } else {
            File dir = new File(directoryName);
            if (!dir.exists()) {
               System.out.println(directoryName + " is not a directory. please set this as a directory");
               System.exit(1);
            }

            Collection<File> files = ClassUtils.getFilesForDirectory(dir);
            int i = 0;
            String[] fileNames = new String[files.size()];

            for(File file : files) {
               fileNames[i++] = file.getPath();
            }

            enhancer.addFiles(fileNames);
         }

         if (clh.isValidating()) {
            numClasses = enhancer.validate();
         } else {
            numClasses = enhancer.enhance();
         }
      } catch (NucleusException ne) {
         System.out.println(ne.getMessage());
         String msg = Localiser.msg("005006");
         LOGGER.error(msg, ne);
         if (!quiet) {
            System.out.println(msg);
         }

         System.exit(1);
      }

      if (numClasses == 0) {
         String msg = Localiser.msg("005007");
         LOGGER.info(msg);
         if (!quiet) {
            System.out.println(msg);
         }
      }

   }

   static class EnhanceComponent {
      public static final int CLASS = 0;
      public static final int CLASS_FILE = 1;
      public static final int MAPPING_FILE = 2;
      public static final int JAR_FILE = 3;
      public static final int PERSISTENCE_UNIT = 4;
      int type;
      Object value;

      public EnhanceComponent(int type, Object value) {
         this.type = type;
         this.value = value;
      }

      public Object getValue() {
         return this.value;
      }

      public int getType() {
         return this.type;
      }
   }
}
