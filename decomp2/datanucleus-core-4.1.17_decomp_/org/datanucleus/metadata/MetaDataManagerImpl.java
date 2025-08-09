package org.datanucleus.metadata;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.PersistenceNucleusContextImpl;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.enhancer.EnhancementNucleusContextImpl;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NoPersistenceInformationException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.annotations.AnnotationManager;
import org.datanucleus.metadata.annotations.AnnotationManagerImpl;
import org.datanucleus.metadata.xml.MetaDataParser;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.MultiMap;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class MetaDataManagerImpl implements Serializable, MetaDataManager {
   private static final long serialVersionUID = 5223949693488111123L;
   protected final NucleusContext nucleusContext;
   protected final AnnotationManager annotationManager;
   protected MetaDataParser metaDataParser = null;
   protected boolean validateXML = true;
   protected boolean supportXMLNamespaces = true;
   protected boolean allowMetaDataLoad = true;
   protected boolean allowXML = true;
   protected boolean allowAnnotations = true;
   protected boolean allowORM = true;
   protected Lock updateLock = null;
   protected Collection classesWithoutPersistenceInfo = new HashSet();
   protected Map classMetaDataByClass = new ConcurrentHashMap();
   protected Map usableClassMetaDataByClass = new ConcurrentHashMap();
   protected Map fileMetaDataByURLString = new ConcurrentHashMap();
   protected Map classMetaDataByEntityName = new ConcurrentHashMap();
   protected Map classMetaDataByDiscriminatorName = new ConcurrentHashMap();
   protected Map directSubclassesByClass = new ConcurrentHashMap();
   protected Map concreteSubclassNamesByClassName = new ConcurrentHashMap();
   protected Map queryMetaDataByName = null;
   protected Map storedProcQueryMetaDataByName = null;
   protected Map fetchPlanMetaDataByName = null;
   protected Map sequenceMetaDataByPackageSequence = null;
   protected Map tableGeneratorMetaDataByPackageSequence = null;
   protected Map queryResultMetaDataByName = null;
   protected MultiMap classMetaDataByAppIdClassName = new MultiMap();
   protected Set listeners = null;
   private List listenersLoadedMetaData = null;
   protected int userMetaDataNumber = 0;
   protected Map discriminatorLookupByRootClassName = new ConcurrentHashMap();
   protected ArrayList utilisedFileMetaData = new ArrayList();

   public MetaDataManagerImpl(NucleusContext ctx) {
      this.nucleusContext = ctx;
      this.updateLock = new ReentrantLock();
      this.validateXML = this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.xml.validate");
      this.supportXMLNamespaces = this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.xml.namespaceAware");
      this.allowXML = this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.allowXML");
      this.allowAnnotations = this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.allowAnnotations");
      this.annotationManager = new AnnotationManagerImpl(this);
      Set supportedClasses = this.nucleusContext.getTypeManager().getSupportedSecondClassTypes();
      Iterator<String> iter = supportedClasses.iterator();

      while(iter.hasNext()) {
         this.classesWithoutPersistenceInfo.add(iter.next());
      }

      this.allowORM = this.nucleusContext.supportsORMMetaData();
      if (this.allowORM) {
         Boolean configOrm = this.nucleusContext.getConfiguration().getBooleanObjectProperty("datanucleus.metadata.supportORM");
         if (configOrm != null && !configOrm) {
            this.allowORM = false;
         }
      }

   }

   public void close() {
      this.classMetaDataByClass.clear();
      this.classMetaDataByClass = null;
      this.usableClassMetaDataByClass.clear();
      this.usableClassMetaDataByClass = null;
      this.fileMetaDataByURLString.clear();
      this.fileMetaDataByURLString = null;
      this.classesWithoutPersistenceInfo.clear();
      this.classesWithoutPersistenceInfo = null;
      this.directSubclassesByClass.clear();
      this.directSubclassesByClass = null;
      this.concreteSubclassNamesByClassName.clear();
      this.concreteSubclassNamesByClassName = null;
      if (this.classMetaDataByEntityName != null) {
         this.classMetaDataByEntityName.clear();
         this.classMetaDataByEntityName = null;
      }

      if (this.classMetaDataByDiscriminatorName != null) {
         this.classMetaDataByDiscriminatorName.clear();
         this.classMetaDataByDiscriminatorName = null;
      }

      if (this.queryMetaDataByName != null) {
         this.queryMetaDataByName.clear();
         this.queryMetaDataByName = null;
      }

      if (this.storedProcQueryMetaDataByName != null) {
         this.storedProcQueryMetaDataByName.clear();
         this.storedProcQueryMetaDataByName = null;
      }

      if (this.fetchPlanMetaDataByName != null) {
         this.fetchPlanMetaDataByName.clear();
         this.fetchPlanMetaDataByName = null;
      }

      if (this.sequenceMetaDataByPackageSequence != null) {
         this.sequenceMetaDataByPackageSequence.clear();
         this.sequenceMetaDataByPackageSequence = null;
      }

      if (this.tableGeneratorMetaDataByPackageSequence != null) {
         this.tableGeneratorMetaDataByPackageSequence.clear();
         this.tableGeneratorMetaDataByPackageSequence = null;
      }

      if (this.queryResultMetaDataByName != null) {
         this.queryResultMetaDataByName.clear();
         this.queryResultMetaDataByName = null;
      }

      if (this.classMetaDataByAppIdClassName != null) {
         this.classMetaDataByAppIdClassName.clear();
         this.classMetaDataByAppIdClassName = null;
      }

      if (this.listeners != null) {
         this.listeners.clear();
         this.listeners = null;
      }

   }

   public void registerListener(MetaDataListener listener) {
      if (this.listeners == null) {
         this.listeners = new HashSet();
      }

      this.listeners.add(listener);
   }

   public void deregisterListener(MetaDataListener listener) {
      if (this.listeners != null) {
         this.listeners.remove(listener);
         if (this.listeners.isEmpty()) {
            this.listeners = null;
         }

      }
   }

   public String getEnhancedMethodNamePrefix() {
      return "dn";
   }

   public boolean isEnhancerField(String fieldName) {
      String prefix = "dn";
      if (!fieldName.startsWith(prefix)) {
         return false;
      } else {
         return fieldName.equals("dnStateManager") || fieldName.equals("dnFlags") || fieldName.equals("dnDetachedState");
      }
   }

   public void setAllowMetaDataLoad(boolean allow) {
      this.allowMetaDataLoad = allow;
   }

   public void setAllowXML(boolean allow) {
      this.allowXML = allow;
   }

   public void setAllowAnnotations(boolean allow) {
      this.allowAnnotations = allow;
   }

   public boolean supportsORM() {
      return this.allowORM;
   }

   public boolean isEnhancing() {
      return this.getNucleusContext() instanceof EnhancementNucleusContextImpl;
   }

   public void setValidate(boolean validate) {
      this.validateXML = validate;
   }

   public void setXmlNamespaceAware(boolean aware) {
      this.supportXMLNamespaces = aware;
   }

   public NucleusContext getNucleusContext() {
      return this.nucleusContext;
   }

   public ApiAdapter getApiAdapter() {
      return this.nucleusContext.getApiAdapter();
   }

   public AnnotationManager getAnnotationManager() {
      return this.annotationManager;
   }

   public FileMetaData[] loadMetadataFiles(String[] metadataFiles, ClassLoader loader) {
      if (!this.allowMetaDataLoad) {
         return null;
      } else {
         boolean originatingLoadCall = false;
         if (this.listenersLoadedMetaData == null && this.listeners != null) {
            originatingLoadCall = true;
            this.listenersLoadedMetaData = new ArrayList();
         }

         FileMetaData[] var6;
         try {
            if (originatingLoadCall) {
               this.updateLock.lock();
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044005", StringUtils.objectArrayToString(metadataFiles)));
            }

            ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver(loader);
            Collection fileMetaData = this.loadFiles(metadataFiles, clr);
            if (!fileMetaData.isEmpty()) {
               this.initialiseFileMetaDataForUse(fileMetaData, clr);
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044010"));
            }

            if (originatingLoadCall) {
               this.processListenerLoadingCall();
            }

            var6 = (FileMetaData[])fileMetaData.toArray(new FileMetaData[fileMetaData.size()]);
         } finally {
            if (originatingLoadCall) {
               this.updateLock.unlock();
            }

         }

         return var6;
      }
   }

   public FileMetaData[] loadClasses(String[] classNames, ClassLoader loader) {
      if (!this.allowMetaDataLoad) {
         return null;
      } else {
         boolean originatingLoadCall = false;
         if (this.listenersLoadedMetaData == null && this.listeners != null) {
            originatingLoadCall = true;
            this.listenersLoadedMetaData = new ArrayList();
         }

         FileMetaData[] var18;
         try {
            if (originatingLoadCall) {
               this.updateLock.lock();
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044006", StringUtils.objectArrayToString(classNames)));
            }

            ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver(loader);
            Collection fileMetaData = new ArrayList();
            Set<Exception> exceptions = new HashSet();

            for(int i = 0; i < classNames.length; ++i) {
               try {
                  Class cls = clr.classForName(classNames[i]);
                  AbstractClassMetaData cmd = (AbstractClassMetaData)this.classMetaDataByClass.get(classNames[i]);
                  if (cmd == null) {
                     FileMetaData filemd = this.loadAnnotationsForClass(cls, clr, true, false);
                     if (filemd != null) {
                        this.registerFile("annotations:" + classNames[i], filemd, clr);
                        fileMetaData.add(filemd);
                     } else {
                        cmd = this.getMetaDataForClass(cls, clr);
                        if (cmd == null) {
                           NucleusLogger.METADATA.debug(Localiser.msg("044017", classNames[i]));
                        } else {
                           fileMetaData.add(cmd.getPackageMetaData().getFileMetaData());
                        }
                     }
                  } else {
                     fileMetaData.add(cmd.getPackageMetaData().getFileMetaData());
                  }
               } catch (ClassNotResolvedException e) {
                  NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(e));
               } catch (Exception e) {
                  exceptions.add(e);
               }
            }

            if (!exceptions.isEmpty()) {
               throw new NucleusUserException(Localiser.msg("044016"), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]), (Object)null);
            }

            if (!fileMetaData.isEmpty()) {
               this.initialiseFileMetaDataForUse(fileMetaData, clr);
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044010"));
            }

            if (originatingLoadCall) {
               this.processListenerLoadingCall();
            }

            var18 = (FileMetaData[])fileMetaData.toArray(new FileMetaData[fileMetaData.size()]);
         } finally {
            if (originatingLoadCall) {
               this.updateLock.unlock();
            }

         }

         return var18;
      }
   }

   public FileMetaData[] loadJar(String jarFileName, ClassLoader loader) {
      if (!this.allowMetaDataLoad) {
         return null;
      } else {
         boolean originatingLoadCall = false;
         if (this.listenersLoadedMetaData == null && this.listeners != null) {
            originatingLoadCall = true;
            this.listenersLoadedMetaData = new ArrayList();
         }

         FileMetaData[] var30;
         try {
            if (originatingLoadCall) {
               this.updateLock.lock();
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044009", jarFileName));
            }

            ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver(loader);
            List<FileMetaData> fileMetaData = new ArrayList();
            Set mappingFiles = new HashSet();
            if (this.allowXML) {
               String[] packageJdoFiles = ClassUtils.getPackageJdoFilesForJarFile(jarFileName);
               if (packageJdoFiles != null) {
                  for(int i = 0; i < packageJdoFiles.length; ++i) {
                     mappingFiles.add(packageJdoFiles[i]);
                  }
               }
            }

            Set classNames = new HashSet();
            if (this.allowAnnotations) {
               String[] jarClassNames = ClassUtils.getClassNamesForJarFile(jarFileName);
               if (jarClassNames != null) {
                  for(int i = 0; i < jarClassNames.length; ++i) {
                     classNames.add(jarClassNames[i]);
                  }
               }
            }

            Set<Throwable> exceptions = new HashSet();
            if (this.allowXML && !mappingFiles.isEmpty()) {
               for(String mappingFileName : mappingFiles) {
                  try {
                     Enumeration files = clr.getResources(mappingFileName, Thread.currentThread().getContextClassLoader());

                     while(files.hasMoreElements()) {
                        URL url = (URL)files.nextElement();
                        if (url != null && this.fileMetaDataByURLString.get(url.toString()) == null) {
                           FileMetaData filemd = this.parseFile(url);
                           if (filemd != null) {
                              this.registerFile(url.toString(), filemd, clr);
                              fileMetaData.add(filemd);
                           }
                        }
                     }
                  } catch (InvalidMetaDataException imde) {
                     NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(imde));
                     exceptions.add(imde);
                  } catch (IOException ioe) {
                     NucleusLogger.METADATA.error(Localiser.msg("044027", jarFileName, mappingFileName, ioe.getMessage()), ioe);
                  }
               }
            }

            if (this.allowAnnotations && !classNames.isEmpty()) {
               for(String className : classNames) {
                  AbstractClassMetaData cmd = (AbstractClassMetaData)this.classMetaDataByClass.get(className);
                  if (cmd == null) {
                     try {
                        Class cls = clr.classForName(className);
                        FileMetaData filemd = this.loadAnnotationsForClass(cls, clr, true, false);
                        if (filemd != null) {
                           fileMetaData.add(filemd);
                        }
                     } catch (ClassNotResolvedException e) {
                        NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(e));
                     } catch (Throwable e) {
                        exceptions.add(e);
                     }
                  }
               }
            }

            if (!exceptions.isEmpty()) {
               throw new NucleusUserException(Localiser.msg("044024", jarFileName), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
            }

            if (!fileMetaData.isEmpty()) {
               this.initialiseFileMetaDataForUse(fileMetaData, clr);
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044010"));
            }

            if (originatingLoadCall) {
               this.processListenerLoadingCall();
            }

            var30 = (FileMetaData[])fileMetaData.toArray(new FileMetaData[fileMetaData.size()]);
         } finally {
            if (originatingLoadCall) {
               this.updateLock.unlock();
            }

         }

         return var30;
      }
   }

   public FileMetaData[] loadPersistenceUnit(PersistenceUnitMetaData pumd, ClassLoader loader) {
      if (!this.allowMetaDataLoad) {
         return null;
      } else {
         boolean originatingLoadCall = false;
         if (this.listenersLoadedMetaData == null && this.listeners != null) {
            originatingLoadCall = true;
            this.listenersLoadedMetaData = new ArrayList();
         }

         FileMetaData[] var36;
         try {
            if (originatingLoadCall) {
               this.updateLock.lock();
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044007", pumd.getName()));
            }

            Properties puProps = pumd.getProperties();
            if (puProps != null) {
               if (puProps.containsKey("datanucleus.metadata.xml.validate")) {
                  Boolean val = Boolean.valueOf((String)puProps.get("datanucleus.metadata.xml.validate"));
                  if (val != null) {
                     this.validateXML = val;
                  }
               }

               if (puProps.containsKey("datanucleus.metadata.xml.namespaceAware")) {
                  Boolean val = Boolean.valueOf((String)puProps.get("datanucleus.metadata.xml.namespaceAware"));
                  if (val != null) {
                     this.supportXMLNamespaces = val;
                  }
               }
            }

            ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver(loader);
            Set<Throwable> exceptions = new HashSet();
            List<FileMetaData> fileMetaData = new ArrayList();
            Set mappingFiles = new HashSet();
            if (this.allowXML) {
               if (this.nucleusContext.getApiName().equalsIgnoreCase("JPA")) {
                  mappingFiles.add("META-INF/orm.xml");
               }

               if (pumd.getMappingFiles() != null) {
                  mappingFiles.addAll(pumd.getMappingFiles());
               }

               if (this.nucleusContext.getApiName().equalsIgnoreCase("JDO")) {
                  Set jarFileNames = pumd.getJarFiles();
                  if (jarFileNames != null) {
                     for(Object jarFile : jarFileNames) {
                        if (jarFile instanceof String) {
                           String[] packageJdoFiles = ClassUtils.getPackageJdoFilesForJarFile((String)jarFile);
                           if (packageJdoFiles != null) {
                              for(int i = 0; i < packageJdoFiles.length; ++i) {
                                 mappingFiles.add(packageJdoFiles[i]);
                              }
                           }
                        } else if (jarFile instanceof URL) {
                           String[] packageJdoFiles = ClassUtils.getPackageJdoFilesForJarFile((URL)jarFile);
                           if (packageJdoFiles != null) {
                              for(int i = 0; i < packageJdoFiles.length; ++i) {
                                 mappingFiles.add(packageJdoFiles[i]);
                              }
                           }
                        } else if (jarFile instanceof URI) {
                           String[] packageJdoFiles = ClassUtils.getPackageJdoFilesForJarFile((URI)jarFile);
                           if (packageJdoFiles != null) {
                              for(int i = 0; i < packageJdoFiles.length; ++i) {
                                 mappingFiles.add(packageJdoFiles[i]);
                              }
                           }
                        }
                     }
                  }
               }
            }

            Set classNames = new HashSet();
            if (this.allowAnnotations) {
               if (pumd.getClassNames() != null) {
                  classNames.addAll(pumd.getClassNames());
               }

               if (this.getNucleusContext() instanceof PersistenceNucleusContextImpl) {
                  Set jarFileNames = pumd.getJarFiles();
                  if (jarFileNames != null) {
                     for(Object jarFile : jarFileNames) {
                        if (jarFile instanceof String) {
                           String[] jarClassNames = ClassUtils.getClassNamesForJarFile((String)jarFile);
                           if (jarClassNames != null) {
                              for(int i = 0; i < jarClassNames.length; ++i) {
                                 classNames.add(jarClassNames[i]);
                              }
                           }
                        } else if (jarFile instanceof URL) {
                           String[] jarClassNames = ClassUtils.getClassNamesForJarFile((URL)jarFile);
                           if (jarClassNames != null) {
                              for(int i = 0; i < jarClassNames.length; ++i) {
                                 classNames.add(jarClassNames[i]);
                              }
                           }
                        } else if (jarFile instanceof URI) {
                           String[] jarClassNames = ClassUtils.getClassNamesForJarFile((URI)jarFile);
                           if (jarClassNames != null) {
                              for(int i = 0; i < jarClassNames.length; ++i) {
                                 classNames.add(jarClassNames[i]);
                              }
                           }
                        }
                     }
                  }
               }

               if (!pumd.getExcludeUnlistedClasses()) {
                  MetaDataScanner scanner = this.getScanner(clr);
                  if (scanner != null) {
                     Set<String> scannedClassNames = scanner.scanForPersistableClasses(pumd);
                     if (scannedClassNames != null) {
                        classNames.addAll(scannedClassNames);
                     }
                  } else {
                     try {
                        if (pumd.getRootURI() != null && pumd.getRootURI().getScheme().equals("file")) {
                           File rootDir = new File(pumd.getRootURI());
                           String[] scannedClassNames = ClassUtils.getClassNamesForDirectoryAndBelow(rootDir);
                           if (scannedClassNames != null) {
                              for(int i = 0; i < scannedClassNames.length; ++i) {
                                 NucleusLogger.METADATA.debug(Localiser.msg("044026", scannedClassNames[i], pumd.getName()));
                                 classNames.add(scannedClassNames[i]);
                              }
                           }
                        }
                     } catch (IllegalArgumentException var24) {
                        NucleusLogger.METADATA.debug("Ignoring scan of classes for this persistence-unit since the URI root is " + pumd.getRootURI() + " and is not hierarchical");
                     }
                  }
               }
            }

            if (this.allowXML && !mappingFiles.isEmpty()) {
               for(String mappingFileName : mappingFiles) {
                  try {
                     Enumeration files = clr.getResources(mappingFileName, Thread.currentThread().getContextClassLoader());
                     if (!files.hasMoreElements()) {
                        NucleusLogger.METADATA.debug("Not found any metadata mapping files for resource name " + mappingFileName + " in CLASSPATH");
                     } else {
                        while(files.hasMoreElements()) {
                           URL url = (URL)files.nextElement();
                           if (url != null && this.fileMetaDataByURLString.get(url.toString()) == null) {
                              FileMetaData filemd = this.parseFile(url);
                              if (filemd != null) {
                                 this.registerFile(url.toString(), filemd, clr);
                                 fileMetaData.add(filemd);
                              }
                           }
                        }
                     }
                  } catch (InvalidMetaDataException imde) {
                     NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(imde));
                     exceptions.add(imde);
                  } catch (IOException ioe) {
                     NucleusLogger.METADATA.error(Localiser.msg("044027", pumd.getName(), mappingFileName, ioe.getMessage()), ioe);
                  }
               }
            }

            if (this.allowAnnotations && !classNames.isEmpty()) {
               for(String className : classNames) {
                  AbstractClassMetaData cmd = (AbstractClassMetaData)this.classMetaDataByClass.get(className);
                  if (cmd == null) {
                     try {
                        Class cls = clr.classForName(className);
                        FileMetaData filemd = this.loadAnnotationsForClass(cls, clr, true, false);
                        if (filemd != null) {
                           fileMetaData.add(filemd);
                        } else {
                           NucleusLogger.METADATA.debug("Class " + className + " was specified in persistence-unit (maybe by not putting exclude-unlisted-classes) " + pumd.getName() + " but not annotated, so ignoring");
                        }
                     } catch (ClassNotResolvedException e) {
                        NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(e));
                     } catch (Throwable e) {
                        exceptions.add(e);
                     }
                  }
               }
            }

            if (!exceptions.isEmpty()) {
               throw new NucleusUserException(Localiser.msg("044023", pumd.getName()), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
            }

            if (!fileMetaData.isEmpty()) {
               this.initialiseFileMetaDataForUse(fileMetaData, clr);
            }

            for(AbstractClassMetaData cmd : this.classMetaDataByClass.values()) {
               boolean populated = cmd.isPopulated();
               if (!populated) {
                  populated = this.populateAbstractClassMetaData(cmd, clr, loader);
               }

               if (populated && !cmd.isInitialised()) {
                  this.initialiseAbstractClassMetaData(cmd, clr);
               }
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044010"));
            }

            if (originatingLoadCall) {
               this.processListenerLoadingCall();
            }

            var36 = (FileMetaData[])fileMetaData.toArray(new FileMetaData[fileMetaData.size()]);
         } finally {
            if (originatingLoadCall) {
               this.updateLock.unlock();
            }

         }

         return var36;
      }
   }

   public void loadUserMetaData(FileMetaData fileMetaData, ClassLoader loader) {
      if (fileMetaData != null) {
         if (this.allowMetaDataLoad) {
            boolean originatingLoadCall = false;
            if (this.listenersLoadedMetaData == null && this.listeners != null) {
               originatingLoadCall = true;
               this.listenersLoadedMetaData = new ArrayList();
            }

            try {
               if (originatingLoadCall) {
                  this.updateLock.lock();
               }

               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044008"));
               }

               ClassLoaderResolver clr = this.nucleusContext.getClassLoaderResolver(loader);
               fileMetaData.setFilename("User_Metadata_" + this.userMetaDataNumber);
               ++this.userMetaDataNumber;
               this.registerFile(fileMetaData.getFilename(), fileMetaData, clr);
               Collection filemds = new ArrayList();
               filemds.add(fileMetaData);
               this.initialiseFileMetaDataForUse(filemds, clr);
               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044010"));
               }

               if (originatingLoadCall) {
                  this.processListenerLoadingCall();
               }
            } finally {
               if (originatingLoadCall) {
                  this.updateLock.unlock();
               }

            }

         }
      }
   }

   public void unloadMetaDataForClass(String className) {
      try {
         this.updateLock.lock();
         this.usableClassMetaDataByClass.remove(className);
         AbstractClassMetaData cmd = (AbstractClassMetaData)this.classMetaDataByClass.remove(className);
         Iterator<Map.Entry<String, AbstractClassMetaData>> iter = this.classMetaDataByDiscriminatorName.entrySet().iterator();

         while(iter.hasNext()) {
            Map.Entry<String, AbstractClassMetaData> entry = (Map.Entry)iter.next();
            if (entry.getValue() == cmd) {
               iter.remove();
            }
         }

         iter = this.classMetaDataByEntityName.entrySet().iterator();

         while(iter.hasNext()) {
            Map.Entry<String, AbstractClassMetaData> entry = (Map.Entry)iter.next();
            if (entry.getValue() == cmd) {
               iter.remove();
            }
         }

         for(Map.Entry entry : this.classMetaDataByAppIdClassName.entrySet()) {
            Collection<AbstractClassMetaData> collCmds = (Collection)entry.getValue();
            if (!collCmds.isEmpty()) {
               collCmds.remove(cmd);
            }
         }

         this.concreteSubclassNamesByClassName.remove(className);
         this.directSubclassesByClass.remove(className);
         this.discriminatorLookupByRootClassName.remove(className);
         this.classesWithoutPersistenceInfo.remove(className);
      } finally {
         this.updateLock.unlock();
      }

   }

   protected MetaDataScanner getScanner(ClassLoaderResolver clr) {
      Object so = this.nucleusContext.getConfiguration().getProperty("datanucleus.metadata.scanner");
      if (so == null) {
         return null;
      } else if (so instanceof MetaDataScanner) {
         return (MetaDataScanner)so;
      } else if (so instanceof String) {
         try {
            Class clazz = clr.classForName((String)so);
            return (MetaDataScanner)clazz.newInstance();
         } catch (Throwable t) {
            throw new NucleusUserException(Localiser.msg("044012", so), t);
         }
      } else {
         if (NucleusLogger.METADATA.isDebugEnabled()) {
            NucleusLogger.METADATA.debug(Localiser.msg("044011", so));
         }

         return null;
      }
   }

   protected void initialiseFileMetaDataForUse(Collection fileMetaData, ClassLoaderResolver clr) {
      Set<Exception> exceptions = new HashSet();
      if (NucleusLogger.METADATA.isDebugEnabled()) {
         NucleusLogger.METADATA.debug(Localiser.msg("044018"));
      }

      for(FileMetaData filemd : fileMetaData) {
         if (!filemd.isInitialised()) {
            this.populateFileMetaData(filemd, clr, (ClassLoader)null);
         }
      }

      if (NucleusLogger.METADATA.isDebugEnabled()) {
         NucleusLogger.METADATA.debug(Localiser.msg("044019"));
      }

      for(FileMetaData filemd : fileMetaData) {
         if (!filemd.isInitialised()) {
            try {
               this.initialiseFileMetaData(filemd, clr, (ClassLoader)null);
            } catch (Exception e) {
               NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(e));
               exceptions.add(e);
            }
         }
      }

      if (!exceptions.isEmpty()) {
         throw new NucleusUserException(Localiser.msg("044020"), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
      }
   }

   public Collection loadFiles(String[] metadataFiles, ClassLoaderResolver clr) {
      List<FileMetaData> fileMetaData = new ArrayList();
      Set<Throwable> exceptions = new HashSet();
      if (this.allowXML) {
         for(int i = 0; i < metadataFiles.length; ++i) {
            try {
               URL fileURL = null;

               try {
                  File file = new File(metadataFiles[i]);
                  fileURL = file.toURI().toURL();
                  if (!file.exists()) {
                     fileURL = clr.getResource(metadataFiles[i], (ClassLoader)null);
                  }
               } catch (Exception var8) {
                  fileURL = clr.getResource(metadataFiles[i], (ClassLoader)null);
               }

               if (fileURL == null) {
                  NucleusLogger.METADATA.warn("Metadata file " + metadataFiles[i] + " not found in CLASSPATH");
               } else {
                  FileMetaData filemd = (FileMetaData)this.fileMetaDataByURLString.get(fileURL.toString());
                  if (filemd == null) {
                     filemd = this.parseFile(fileURL);
                     if (filemd == null) {
                        throw new NucleusUserException(Localiser.msg("044015", metadataFiles[i]));
                     }

                     this.registerFile(fileURL.toString(), filemd, clr);
                     fileMetaData.add(filemd);
                  } else {
                     fileMetaData.add(filemd);
                  }
               }
            } catch (Exception e) {
               NucleusLogger.METADATA.error(StringUtils.getStringFromStackTrace(e));
               exceptions.add(e);
            }
         }
      }

      if (!exceptions.isEmpty()) {
         throw new NucleusUserException(Localiser.msg("044016"), (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]), (Object)null);
      } else {
         return fileMetaData;
      }
   }

   public boolean isClassPersistable(String className) {
      AbstractClassMetaData acmd = this.readMetaDataForClass(className);
      if (acmd == null) {
         return false;
      } else {
         return acmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE;
      }
   }

   public FileMetaData[] getFileMetaData() {
      Collection filemds = this.fileMetaDataByURLString.values();
      return (FileMetaData[])filemds.toArray(new FileMetaData[filemds.size()]);
   }

   public Collection getClassesWithMetaData() {
      return Collections.unmodifiableCollection(this.classMetaDataByClass.keySet());
   }

   public boolean hasMetaDataForClass(String className) {
      if (className == null) {
         return false;
      } else if (this.isClassWithoutPersistenceInfo(className)) {
         return false;
      } else {
         return this.classMetaDataByClass.get(className) != null;
      }
   }

   protected boolean isClassWithoutPersistenceInfo(String className) {
      if (className == null) {
         return true;
      } else {
         return !className.startsWith("java.") && !className.startsWith("javax.") ? this.classesWithoutPersistenceInfo.contains(className) : true;
      }
   }

   public Collection getClassMetaDataWithApplicationId(String objectIdClassName) {
      return (Collection)this.classMetaDataByAppIdClassName.get(objectIdClassName);
   }

   public AbstractClassMetaData getMetaDataForClass(String className, ClassLoaderResolver clr) {
      if (className == null) {
         return null;
      } else {
         AbstractClassMetaData cmd = (AbstractClassMetaData)this.usableClassMetaDataByClass.get(className);
         if (cmd != null) {
            return cmd;
         } else if (this.isClassWithoutPersistenceInfo(className)) {
            return null;
         } else {
            synchronized(this) {
               cmd = (AbstractClassMetaData)this.classMetaDataByClass.get(className);
               if (cmd != null && cmd.isPopulated() && cmd.isInitialised() && cmd instanceof ClassMetaData) {
                  return cmd;
               } else {
                  Class c = null;

                  try {
                     if (clr == null) {
                        c = Class.forName(className);
                     } else {
                        c = clr.classForName(className, (ClassLoader)null, false);
                     }
                  } catch (ClassNotFoundException var8) {
                  } catch (ClassNotResolvedException var9) {
                  }

                  if (c == null) {
                     return cmd != null && cmd.isPopulated() && cmd.isInitialised() ? cmd : null;
                  } else {
                     cmd = this.getMetaDataForClass(c, clr);
                     if (cmd != null) {
                        this.usableClassMetaDataByClass.put(className, cmd);
                     }

                     return cmd;
                  }
               }
            }
         }
      }
   }

   public AbstractClassMetaData getMetaDataForClass(Class c, ClassLoaderResolver clr) {
      if (c == null) {
         return null;
      } else {
         AbstractClassMetaData cmd = (AbstractClassMetaData)this.usableClassMetaDataByClass.get(c.getName());
         if (cmd != null) {
            return cmd;
         } else if (this.isClassWithoutPersistenceInfo(c.getName())) {
            return null;
         } else {
            synchronized(this) {
               boolean originatingLoadCall = false;
               if (this.listenersLoadedMetaData == null && this.listeners != null) {
                  originatingLoadCall = true;
                  this.listenersLoadedMetaData = new ArrayList();
               }

               AbstractClassMetaData var14 = null;
               if (c.isInterface()) {
                  var14 = this.getClassMetaDataForImplementationOfPersistentInterface(c.getName());
               } else {
                  var14 = this.getMetaDataForClassInternal(c, clr);
               }

               if (var14 != null) {
                  boolean populated = this.populateAbstractClassMetaData((AbstractClassMetaData)var14, clr, c.getClassLoader());
                  if (populated) {
                     this.initialiseAbstractClassMetaData((AbstractClassMetaData)var14, clr);
                  }

                  if (!this.utilisedFileMetaData.isEmpty()) {
                     ArrayList utilisedFileMetaData1 = (ArrayList)this.utilisedFileMetaData.clone();
                     this.utilisedFileMetaData.clear();

                     for(FileMetaData filemd : utilisedFileMetaData1) {
                        this.initialiseFileMetaData(filemd, clr, c.getClassLoader());
                     }

                     if (!this.utilisedFileMetaData.isEmpty()) {
                        ArrayList utilisedFileMetaData2 = (ArrayList)this.utilisedFileMetaData.clone();
                        this.utilisedFileMetaData.clear();

                        for(FileMetaData filemd : utilisedFileMetaData2) {
                           this.initialiseFileMetaData(filemd, clr, c.getClassLoader());
                        }
                     }
                  }
               } else if (!c.isInterface()) {
                  this.classesWithoutPersistenceInfo.add(c.getName());
               }

               this.utilisedFileMetaData.clear();
               if (originatingLoadCall) {
                  this.processListenerLoadingCall();
               }

               if (var14 != null) {
                  this.usableClassMetaDataByClass.put(c.getName(), var14);
               }

               return (AbstractClassMetaData)var14;
            }
         }
      }
   }

   protected void processListenerLoadingCall() {
      if (!this.listenersLoadedMetaData.isEmpty() && this.listeners != null) {
         for(AbstractClassMetaData acmd : new ArrayList(this.listenersLoadedMetaData)) {
            for(MetaDataListener listener : this.listeners) {
               listener.loaded(acmd);
            }
         }
      }

      this.listenersLoadedMetaData = null;
   }

   public AbstractClassMetaData getMetaDataForEntityName(String entityName) {
      return (AbstractClassMetaData)this.classMetaDataByEntityName.get(entityName);
   }

   public AbstractClassMetaData getMetaDataForDiscriminator(String discriminator) {
      return (AbstractClassMetaData)this.classMetaDataByDiscriminatorName.get(discriminator);
   }

   public AbstractClassMetaData readMetaDataForClass(String className) {
      return (AbstractClassMetaData)this.classMetaDataByClass.get(className);
   }

   public AbstractMemberMetaData readMetaDataForMember(String className, String memberName) {
      AbstractClassMetaData cmd = this.readMetaDataForClass(className);
      return cmd != null ? cmd.getMetaDataForMember(memberName) : null;
   }

   public abstract AbstractClassMetaData getMetaDataForClassInternal(Class var1, ClassLoaderResolver var2);

   protected void registerMetaDataForClass(String fullClassName, AbstractClassMetaData cmd) {
      this.classMetaDataByClass.put(fullClassName, cmd);
   }

   public String[] getClassesImplementingInterface(String interfaceName, ClassLoaderResolver clr) {
      Collection classes = new HashSet();
      Class intfClass = clr.classForName(interfaceName);
      Collection generatedClassNames = new HashSet();
      Collection cmds = this.classMetaDataByClass.values();
      Iterator cmdIter = cmds.iterator();
      boolean isPersistentInterface = false;

      while(cmdIter.hasNext()) {
         AbstractClassMetaData acmd = (AbstractClassMetaData)cmdIter.next();
         Class implClass = null;

         try {
            implClass = clr.classForName(acmd.getFullClassName());
         } catch (ClassNotResolvedException var14) {
         }

         if (implClass != null) {
            if (acmd instanceof ClassMetaData) {
               this.initialiseAbstractClassMetaData(acmd, clr);
               if (intfClass.isAssignableFrom(implClass) && !((ClassMetaData)acmd).isAbstract()) {
                  classes.add(implClass);
               }
            } else if (acmd instanceof InterfaceMetaData && intfClass.isAssignableFrom(implClass)) {
               isPersistentInterface = true;
            }
         } else if (this.isPersistentInterfaceImplementation(interfaceName, acmd.getFullClassName())) {
            isPersistentInterface = true;
            generatedClassNames.add(acmd.getFullClassName());
         }
      }

      if (isPersistentInterface && this.nucleusContext instanceof PersistenceNucleusContext && ((PersistenceNucleusContext)this.nucleusContext).getImplementationCreator() != null) {
         classes.add(((PersistenceNucleusContext)this.nucleusContext).getImplementationCreator().newInstance(intfClass, clr).getClass());
         int numClasses = classes.size() + generatedClassNames.size();
         String[] classNames = new String[numClasses];
         Iterator iter = classes.iterator();

         int i;
         for(i = 0; iter.hasNext(); classNames[i++] = ((Class)iter.next()).getName()) {
         }

         for(Iterator var20 = generatedClassNames.iterator(); var20.hasNext(); classNames[i++] = (String)var20.next()) {
         }

         return classNames;
      } else if (classes.isEmpty()) {
         return null;
      } else {
         Collection classesSorted = new TreeSet(new InterfaceClassComparator());
         Iterator classesIter = classes.iterator();

         while(classesIter.hasNext()) {
            classesSorted.add(classesIter.next());
         }

         String[] classNames = new String[classesSorted.size()];
         Iterator iter = classesSorted.iterator();

         for(int i = 0; iter.hasNext(); classNames[i++] = ((Class)iter.next()).getName()) {
         }

         return classNames;
      }
   }

   public void addORMDataToClass(Class c, ClassLoaderResolver clr) {
   }

   public void addAnnotationsDataToClass(Class c, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      if (this.allowAnnotations) {
         if (cmd.getPackageMetaData() != null && cmd.getPackageMetaData().getFileMetaData() != null && cmd.getPackageMetaData().getFileMetaData().getType() == MetadataFileType.ANNOTATIONS) {
            return;
         }

         FileMetaData filemd = this.loadAnnotationsForClass(c, clr, false, false);
         if (filemd != null) {
            AbstractClassMetaData annotCmd = filemd.getPackage(0).getClass(0);
            if (annotCmd != null) {
               this.postProcessClassMetaData(annotCmd, clr);
               MetaDataMerger.mergeClassAnnotationsData(cmd, annotCmd, this);
            }
         }
      }

   }

   public ClassMetaData getMetaDataForImplementationOfReference(Class referenceClass, Object implValue, ClassLoaderResolver clr) {
      if (referenceClass != null && (referenceClass.isInterface() || referenceClass == Object.class)) {
         Object intfMetaData = this.getClassMetaDataForImplementationOfPersistentInterface(referenceClass.getName());
         if (intfMetaData != null) {
            return (ClassMetaData)intfMetaData;
         } else {
            ClassMetaData cmd = null;

            for(String class_name : this.classMetaDataByClass.keySet()) {
               AbstractClassMetaData cmd_cls = (AbstractClassMetaData)this.classMetaDataByClass.get(class_name);
               if (cmd_cls instanceof ClassMetaData) {
                  try {
                     if (referenceClass == Object.class || clr.isAssignableFrom(referenceClass, class_name)) {
                        cmd = (ClassMetaData)cmd_cls;
                        if (implValue != null && cmd.getFullClassName().equals(implValue.getClass().getName())) {
                           return cmd;
                        }

                        cmd_cls = cmd.getSuperAbstractClassMetaData();

                        while(cmd_cls != null && (referenceClass == Object.class || clr.isAssignableFrom(referenceClass, ((ClassMetaData)cmd_cls).getFullClassName()))) {
                           cmd = (ClassMetaData)cmd_cls;
                           if (implValue != null && cmd.getFullClassName().equals(implValue.getClass().getName())) {
                              break;
                           }

                           cmd_cls = cmd_cls.getSuperAbstractClassMetaData();
                           if (cmd_cls == null) {
                              break;
                           }
                        }
                     }
                  } catch (Exception var11) {
                  }
               }
            }

            return cmd;
         }
      } else {
         return null;
      }
   }

   public QueryMetaData getMetaDataForQuery(Class cls, ClassLoaderResolver clr, String queryName) {
      if (queryName != null && this.queryMetaDataByName != null) {
         String query_key = queryName;
         if (cls != null) {
            query_key = cls.getName() + "_" + queryName;
         }

         return (QueryMetaData)this.queryMetaDataByName.get(query_key);
      } else {
         return null;
      }
   }

   public Set getNamedQueryNames() {
      return this.queryMetaDataByName != null && !this.queryMetaDataByName.isEmpty() ? this.queryMetaDataByName.keySet() : null;
   }

   public StoredProcQueryMetaData getMetaDataForStoredProcQuery(Class cls, ClassLoaderResolver clr, String queryName) {
      if (queryName != null && this.storedProcQueryMetaDataByName != null) {
         String query_key = queryName;
         if (cls != null) {
            query_key = cls.getName() + "_" + queryName;
         }

         return (StoredProcQueryMetaData)this.storedProcQueryMetaDataByName.get(query_key);
      } else {
         return null;
      }
   }

   public FetchPlanMetaData getMetaDataForFetchPlan(String name) {
      return name != null && this.fetchPlanMetaDataByName != null ? (FetchPlanMetaData)this.fetchPlanMetaDataByName.get(name) : null;
   }

   public SequenceMetaData getMetaDataForSequence(ClassLoaderResolver clr, String seqName) {
      return seqName != null && this.sequenceMetaDataByPackageSequence != null ? (SequenceMetaData)this.sequenceMetaDataByPackageSequence.get(seqName) : null;
   }

   public TableGeneratorMetaData getMetaDataForTableGenerator(ClassLoaderResolver clr, String genName) {
      return genName != null && this.tableGeneratorMetaDataByPackageSequence != null ? (TableGeneratorMetaData)this.tableGeneratorMetaDataByPackageSequence.get(genName) : null;
   }

   public QueryResultMetaData getMetaDataForQueryResult(String name) {
      return name != null && this.queryResultMetaDataByName != null ? (QueryResultMetaData)this.queryResultMetaDataByName.get(name) : null;
   }

   public InterfaceMetaData getMetaDataForInterface(Class c, ClassLoaderResolver clr) {
      return null;
   }

   public boolean isPersistentInterface(String name) {
      return false;
   }

   public boolean isPersistentInterfaceImplementation(String interfaceName, String implName) {
      return false;
   }

   public boolean isPersistentDefinitionImplementation(String implName) {
      return false;
   }

   public String getImplementationNameForPersistentInterface(String interfaceName) {
      return null;
   }

   public ClassMetaData getClassMetaDataForImplementationOfPersistentInterface(String interfaceName) {
      return null;
   }

   public void registerPersistentInterface(InterfaceMetaData imd, Class implClass, ClassLoaderResolver clr) {
   }

   public void registerImplementationOfAbstractClass(ClassMetaData cmd, Class implClass, ClassLoaderResolver clr) {
   }

   public PersistenceUnitMetaData getMetaDataForPersistenceUnit(String unitName) {
      String filename = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.persistenceXmlFilename");
      PersistenceFileMetaData[] files = MetaDataUtils.parsePersistenceFiles(this.nucleusContext.getPluginManager(), filename, this.validateXML, this.nucleusContext.getClassLoaderResolver((ClassLoader)null));
      if (files == null) {
         throw new NucleusUserException(Localiser.msg("044046"));
      } else {
         for(int i = 0; i < files.length; ++i) {
            PersistenceUnitMetaData[] unitmds = files[i].getPersistenceUnits();
            if (unitmds != null) {
               for(int j = 0; j < unitmds.length; ++j) {
                  if (unitmds[j].getName().equals(unitName)) {
                     return unitmds[j];
                  }
               }
            }
         }

         return null;
      }
   }

   protected abstract FileMetaData parseFile(URL var1);

   public abstract void registerFile(String var1, FileMetaData var2, ClassLoaderResolver var3);

   public void registerDiscriminatorValueForClass(AbstractClassMetaData cmd, String discrimValue) {
      AbstractClassMetaData rootCmd = cmd.getBaseAbstractClassMetaData();
      DiscriminatorLookup lookup = (DiscriminatorLookup)this.discriminatorLookupByRootClassName.get(rootCmd.getFullClassName());
      if (lookup == null) {
         lookup = new DiscriminatorLookup();
         this.discriminatorLookupByRootClassName.put(rootCmd.getFullClassName(), lookup);
      }

      lookup.addValue(cmd.getFullClassName(), discrimValue);
   }

   public String getClassNameForDiscriminatorValueWithRoot(AbstractClassMetaData rootCmd, String discrimValue) {
      DiscriminatorLookup lookup = (DiscriminatorLookup)this.discriminatorLookupByRootClassName.get(rootCmd.getFullClassName());
      return lookup != null ? lookup.getClassForValue(discrimValue) : null;
   }

   public String getDiscriminatorValueForClass(AbstractClassMetaData cmd, String discrimValue) {
      AbstractClassMetaData rootCmd = cmd.getBaseAbstractClassMetaData();
      DiscriminatorLookup lookup = (DiscriminatorLookup)this.discriminatorLookupByRootClassName.get(rootCmd.getFullClassName());
      return lookup != null ? lookup.getValueForClass(cmd.getFullClassName()) : null;
   }

   public String getClassNameFromDiscriminatorValue(String discrimValue, DiscriminatorMetaData dismd) {
      if (discrimValue == null) {
         return null;
      } else if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
         return discrimValue;
      } else if (dismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP) {
         AbstractClassMetaData baseCmd = (AbstractClassMetaData)((InheritanceMetaData)dismd.getParent()).getParent();
         AbstractClassMetaData rootCmd = baseCmd.getBaseAbstractClassMetaData();
         return this.getClassNameForDiscriminatorValueWithRoot(rootCmd, discrimValue);
      } else {
         return null;
      }
   }

   public void registerSequencesForFile(FileMetaData filemd) {
      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);
         SequenceMetaData[] seqmds = pmd.getSequences();
         if (seqmds != null) {
            if (this.sequenceMetaDataByPackageSequence == null) {
               this.sequenceMetaDataByPackageSequence = new ConcurrentHashMap();
            }

            for(int j = 0; j < seqmds.length; ++j) {
               this.sequenceMetaDataByPackageSequence.put(seqmds[j].getFullyQualifiedName(), seqmds[j]);
               this.sequenceMetaDataByPackageSequence.put(seqmds[j].getName(), seqmds[j]);
            }
         }
      }

   }

   public void registerTableGeneratorsForFile(FileMetaData filemd) {
      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);
         TableGeneratorMetaData[] tgmds = pmd.getTableGenerators();
         if (tgmds != null) {
            if (this.tableGeneratorMetaDataByPackageSequence == null) {
               this.tableGeneratorMetaDataByPackageSequence = new ConcurrentHashMap();
            }

            for(int j = 0; j < tgmds.length; ++j) {
               this.tableGeneratorMetaDataByPackageSequence.put(tgmds[j].getFullyQualifiedName(), tgmds[j]);
               this.tableGeneratorMetaDataByPackageSequence.put(tgmds[j].getName(), tgmds[j]);
            }
         }
      }

   }

   protected void registerQueryResultMetaDataForFile(FileMetaData filemd) {
      QueryResultMetaData[] fqrmds = filemd.getQueryResultMetaData();
      if (fqrmds != null) {
         if (this.queryResultMetaDataByName == null) {
            this.queryResultMetaDataByName = new ConcurrentHashMap();
         }

         for(int i = 0; i < fqrmds.length; ++i) {
            this.queryResultMetaDataByName.put(fqrmds[i].getName(), fqrmds[i]);
         }
      }

      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);

         for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
            AbstractClassMetaData cmd = pmd.getClass(j);
            QueryResultMetaData[] qrmds = cmd.getQueryResultMetaData();
            if (qrmds != null) {
               if (this.queryResultMetaDataByName == null) {
                  this.queryResultMetaDataByName = new ConcurrentHashMap();
               }

               for(int k = 0; k < qrmds.length; ++k) {
                  this.queryResultMetaDataByName.put(qrmds[k].getName(), qrmds[k]);
               }
            }
         }
      }

   }

   public void registerNamedQuery(QueryMetaData qmd) {
      if (this.queryMetaDataByName == null) {
         this.queryMetaDataByName = new ConcurrentHashMap();
      }

      String scope = qmd.getScope();
      String key = qmd.getName();
      if (scope != null) {
         key = scope + "_" + key;
      }

      this.queryMetaDataByName.put(key, qmd);
   }

   protected void registerQueriesForFile(FileMetaData filemd) {
      QueryMetaData[] queries = filemd.getQueries();
      if (queries != null) {
         if (this.queryMetaDataByName == null) {
            this.queryMetaDataByName = new ConcurrentHashMap();
         }

         for(int i = 0; i < queries.length; ++i) {
            String scope = queries[i].getScope();
            String key = queries[i].getName();
            if (scope != null) {
               key = scope + "_" + key;
            }

            this.queryMetaDataByName.put(key, queries[i]);
         }
      }

      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);

         for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
            ClassMetaData cmd = pmd.getClass(j);
            QueryMetaData[] classQueries = cmd.getQueries();
            if (classQueries != null) {
               if (this.queryMetaDataByName == null) {
                  this.queryMetaDataByName = new ConcurrentHashMap();
               }

               for(int k = 0; k < classQueries.length; ++k) {
                  String scope = classQueries[k].getScope();
                  String key = classQueries[k].getName();
                  if (scope != null) {
                     key = scope + "_" + key;
                  }

                  this.queryMetaDataByName.put(key, classQueries[k]);
               }
            }
         }

         for(int j = 0; j < pmd.getNoOfInterfaces(); ++j) {
            InterfaceMetaData intfmd = pmd.getInterface(j);
            QueryMetaData[] interfaceQueries = intfmd.getQueries();
            if (interfaceQueries != null) {
               if (this.queryMetaDataByName == null) {
                  this.queryMetaDataByName = new ConcurrentHashMap();
               }

               for(int k = 0; k < interfaceQueries.length; ++k) {
                  String scope = interfaceQueries[k].getScope();
                  String key = interfaceQueries[k].getName();
                  if (scope != null) {
                     key = scope + "_" + key;
                  }

                  this.queryMetaDataByName.put(key, interfaceQueries[k]);
               }
            }
         }
      }

   }

   protected void registerStoredProcQueriesForFile(FileMetaData filemd) {
      StoredProcQueryMetaData[] queries = filemd.getStoredProcQueries();
      if (queries != null) {
         if (this.storedProcQueryMetaDataByName == null) {
            this.storedProcQueryMetaDataByName = new ConcurrentHashMap();
         }

         for(int i = 0; i < queries.length; ++i) {
            String key = queries[i].getName();
            this.storedProcQueryMetaDataByName.put(key, queries[i]);
         }
      }

      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);

         for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
            ClassMetaData cmd = pmd.getClass(j);
            StoredProcQueryMetaData[] classStoredProcQueries = cmd.getStoredProcQueries();
            if (classStoredProcQueries != null) {
               if (this.storedProcQueryMetaDataByName == null) {
                  this.storedProcQueryMetaDataByName = new ConcurrentHashMap();
               }

               for(int k = 0; k < classStoredProcQueries.length; ++k) {
                  String key = classStoredProcQueries[k].getName();
                  this.storedProcQueryMetaDataByName.put(key, classStoredProcQueries[k]);
               }
            }
         }

         for(int j = 0; j < pmd.getNoOfInterfaces(); ++j) {
            InterfaceMetaData intfmd = pmd.getInterface(j);
            StoredProcQueryMetaData[] interfaceStoredProcQueries = intfmd.getStoredProcQueries();
            if (interfaceStoredProcQueries != null) {
               if (this.storedProcQueryMetaDataByName == null) {
                  this.storedProcQueryMetaDataByName = new ConcurrentHashMap();
               }

               for(int k = 0; k < interfaceStoredProcQueries.length; ++k) {
                  String key = interfaceStoredProcQueries[k].getName();
                  this.storedProcQueryMetaDataByName.put(key, interfaceStoredProcQueries[k]);
               }
            }
         }
      }

   }

   protected void registerFetchPlansForFile(FileMetaData filemd) {
      FetchPlanMetaData[] fetchPlans = filemd.getFetchPlans();
      if (fetchPlans != null) {
         if (this.fetchPlanMetaDataByName == null) {
            this.fetchPlanMetaDataByName = new ConcurrentHashMap();
         }

         for(int i = 0; i < fetchPlans.length; ++i) {
            this.fetchPlanMetaDataByName.put(fetchPlans[i].getName(), fetchPlans[i]);
         }
      }

   }

   protected void populateFileMetaData(FileMetaData filemd, ClassLoaderResolver clr, ClassLoader primary) {
      filemd.setMetaDataManager(this);

      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);

         for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
            AbstractClassMetaData cmd = pmd.getClass(j);
            this.populateAbstractClassMetaData(cmd, clr, primary);
         }

         for(int j = 0; j < pmd.getNoOfInterfaces(); ++j) {
            AbstractClassMetaData cmd = pmd.getInterface(j);
            this.populateAbstractClassMetaData(cmd, clr, primary);
         }
      }

   }

   protected void initialiseFileMetaData(FileMetaData filemd, ClassLoaderResolver clr, ClassLoader primary) {
      for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
         PackageMetaData pmd = filemd.getPackage(i);
         pmd.initialise(clr, this);

         for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
            ClassMetaData cmd = pmd.getClass(j);

            try {
               this.initialiseClassMetaData(cmd, clr.classForName(cmd.getFullClassName(), primary), clr);
            } catch (NucleusException ne) {
               if (!this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.ignoreMetaDataForMissingClasses", false)) {
                  throw ne;
               }

               cmd.getPackageMetaData().removeClass(cmd);
               this.classMetaDataByClass.remove(cmd.getFullClassName());
               NucleusLogger.METADATA.warn("Attempt to load metadata for class=" + cmd.getFullClassName() + " but an exception was thrown : " + ne.getMessage());
            } catch (RuntimeException var10) {
            }
         }

         for(int j = 0; j < pmd.getNoOfInterfaces(); ++j) {
            InterfaceMetaData imd = pmd.getInterface(j);

            try {
               this.initialiseInterfaceMetaData(imd, clr, primary);
            } catch (NucleusException ne) {
               if (!this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.ignoreMetaDataForMissingClasses", false)) {
                  throw ne;
               }

               imd.getPackageMetaData().removeClass(imd);
               this.classMetaDataByClass.remove(imd.getFullClassName());
               NucleusLogger.METADATA.warn("Attempt to load metadata for class=" + imd.getFullClassName() + " but an exception was thrown : " + ne.getMessage());
            } catch (RuntimeException var12) {
            }
         }
      }

   }

   protected void initialiseClassMetaData(ClassMetaData cmd, Class cls, ClassLoaderResolver clr) {
      synchronized(cmd) {
         if (this.getNucleusContext() instanceof PersistenceNucleusContextImpl && cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE && !this.getNucleusContext().getApiAdapter().isPersistable(cls)) {
            throw new NucleusUserException(Localiser.msg("044059", cls.getName()));
         } else {
            boolean populated = this.populateAbstractClassMetaData(cmd, clr, cls.getClassLoader());
            if (populated) {
               this.initialiseAbstractClassMetaData(cmd, clr);
            }

         }
      }
   }

   protected void initialiseInterfaceMetaData(InterfaceMetaData imd, ClassLoaderResolver clr, ClassLoader primary) {
      synchronized(imd) {
         boolean populated = this.populateAbstractClassMetaData(imd, clr, primary);
         if (populated) {
            this.initialiseAbstractClassMetaData(imd, clr);
         }

      }
   }

   protected FileMetaData loadAnnotationsForClass(Class cls, ClassLoaderResolver clr, boolean register, boolean populate) {
      if (!this.allowAnnotations) {
         return null;
      } else if (this.isClassWithoutPersistenceInfo(cls.getName())) {
         return null;
      } else {
         String clsPackageName = ClassUtils.getPackageNameForClass(cls);
         if (clsPackageName == null) {
            clsPackageName = "";
         }

         FileMetaData filemd = new FileMetaData();
         filemd.setType(MetadataFileType.ANNOTATIONS);
         filemd.setMetaDataManager(this);
         PackageMetaData pmd = filemd.newPackageMetadata(clsPackageName);
         AbstractClassMetaData cmd = this.annotationManager.getMetaDataForClass(cls, pmd, clr);
         if (cmd != null) {
            if (register) {
               this.registerFile("annotations:" + cls.getName(), filemd, clr);
               if (populate) {
                  this.populateFileMetaData(filemd, clr, cls.getClassLoader());
               }
            }

            return filemd;
         } else {
            return null;
         }
      }
   }

   protected void postProcessClassMetaData(AbstractClassMetaData cmd, ClassLoaderResolver clr) {
   }

   protected boolean populateAbstractClassMetaData(final AbstractClassMetaData cmd, final ClassLoaderResolver clr, final ClassLoader loader) {
      if (!cmd.isPopulated() && !cmd.isInitialised()) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  cmd.populate(clr, loader, MetaDataManagerImpl.this);
                  return null;
               } catch (NucleusException ne) {
                  if (MetaDataManagerImpl.this.nucleusContext.getConfiguration().getBooleanProperty("datanucleus.metadata.ignoreMetaDataForMissingClasses", false)) {
                     cmd.getPackageMetaData().removeClass(cmd);
                     MetaDataManagerImpl.this.classMetaDataByClass.remove(cmd.getFullClassName());
                     NucleusLogger.METADATA.warn("Attempt to load metadata for class=" + cmd.getFullClassName() + " but an exception was thrown : " + ne.getMessage());
                     return false;
                  } else {
                     throw ne;
                  }
               } catch (Exception e) {
                  throw new NucleusUserException("Exception during population of metadata for " + cmd.getFullClassName(), e);
               }
            }
         });
      }

      return true;
   }

   protected void initialiseAbstractClassMetaData(final AbstractClassMetaData cmd, final ClassLoaderResolver clr) {
      if (!cmd.isInitialised()) {
         AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  cmd.initialise(clr, MetaDataManagerImpl.this);
                  return null;
               } catch (NucleusException ne) {
                  throw ne;
               } catch (Exception e) {
                  throw new NucleusUserException("Exception during initialisation of metadata for " + cmd.getFullClassName(), e);
               }
            }
         });
      }

   }

   public void abstractClassMetaDataInitialised(AbstractClassMetaData cmd) {
      if (cmd.getIdentityType() == IdentityType.APPLICATION && !cmd.usesSingleFieldIdentityClass()) {
         this.classMetaDataByAppIdClassName.put(cmd.getObjectidClass(), cmd);
      }

      if (cmd instanceof ClassMetaData && cmd.getPersistableSuperclass() != null) {
         Set<String> directSubclasses = (Set)this.directSubclassesByClass.get(cmd.getPersistableSuperclass());
         if (directSubclasses == null) {
            directSubclasses = new HashSet();
            this.directSubclassesByClass.put(cmd.getPersistableSuperclass(), directSubclasses);
         }

         directSubclasses.add(cmd.getFullClassName());
         Set<String> subclassNames;
         if (!((ClassMetaData)cmd).isAbstract()) {
            for(AbstractClassMetaData theCmd = cmd; theCmd.getPersistableSuperclass() != null; subclassNames.add(cmd.getFullClassName())) {
               theCmd = theCmd.getSuperAbstractClassMetaData();
               subclassNames = (Set)this.concreteSubclassNamesByClassName.get(theCmd.getFullClassName());
               if (subclassNames == null) {
                  subclassNames = new HashSet();
                  this.concreteSubclassNamesByClassName.put(theCmd.getFullClassName(), subclassNames);
               }
            }
         }
      }

      if (this.listeners != null && this.listenersLoadedMetaData != null) {
         this.listenersLoadedMetaData.add(cmd);
      }

   }

   public String[] getConcreteSubclassesForClass(String className) {
      Set<String> concreteSubclasses = (Set)this.concreteSubclassNamesByClassName.get(className);
      return concreteSubclasses == null ? null : (String[])concreteSubclasses.toArray(new String[concreteSubclasses.size()]);
   }

   public String[] getSubclassesForClass(String className, boolean includeDescendents) {
      Collection subclassNames2 = new HashSet();
      this.provideSubclassesForClass(className, includeDescendents, subclassNames2);
      return !subclassNames2.isEmpty() ? (String[])((String[])subclassNames2.toArray(new String[subclassNames2.size()])) : null;
   }

   private void provideSubclassesForClass(String className, boolean includeDescendents, Collection consumer) {
      Set<String> subclasses = (Set)this.directSubclassesByClass.get(className);
      if (subclasses != null) {
         consumer.addAll(subclasses);
         if (includeDescendents) {
            Iterator subClassNameIter = subclasses.iterator();

            while(subClassNameIter.hasNext()) {
               this.provideSubclassesForClass((String)subClassNameIter.next(), includeDescendents, consumer);
            }
         }
      }

   }

   public List getReferencedClasses(String[] classNames, ClassLoaderResolver clr) {
      List<AbstractClassMetaData> cmds = new ArrayList();

      for(int i = 0; i < classNames.length; ++i) {
         Class cls = null;

         try {
            cls = clr.classForName(classNames[i]);
            if (!cls.isInterface()) {
               AbstractClassMetaData cmd = this.getMetaDataForClass(classNames[i], clr);
               if (cmd == null) {
                  NucleusLogger.DATASTORE.warn("Class Invalid " + classNames[i]);
                  throw new NoPersistenceInformationException(classNames[i]);
               }

               cmds.addAll(this.getReferencedClassMetaData(cmd, clr));
            }
         } catch (ClassNotResolvedException var7) {
            NucleusLogger.DATASTORE.warn("Class " + classNames[i] + " not found so being ignored");
         }
      }

      return cmds;
   }

   protected List getReferencedClassMetaData(AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      if (cmd == null) {
         return null;
      } else {
         List<AbstractClassMetaData> orderedCMDs = new ArrayList();
         Set referencedCMDs = new HashSet();
         cmd.getReferencedClassMetaData(orderedCMDs, referencedCMDs, clr, this);
         return orderedCMDs;
      }
   }

   public boolean isFieldTypePersistable(Class type) {
      if (this.isEnhancing()) {
         AbstractClassMetaData cmd = this.readMetaDataForClass(type.getName());
         if (cmd != null && cmd instanceof ClassMetaData && cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
            return true;
         }
      }

      return this.getApiAdapter().isPersistable(type);
   }

   private class DiscriminatorLookup {
      Map discrimValueByClass;
      Map discrimClassByValue;

      private DiscriminatorLookup() {
         this.discrimValueByClass = new HashMap();
         this.discrimClassByValue = new HashMap();
      }

      public void addValue(String className, String value) {
         this.discrimValueByClass.put(className, value);
         this.discrimClassByValue.put(value, className);
      }

      public String getValueForClass(String className) {
         return (String)this.discrimValueByClass.get(className);
      }

      public String getClassForValue(String value) {
         return (String)this.discrimClassByValue.get(value);
      }

      public String toString() {
         return StringUtils.mapToString(this.discrimValueByClass);
      }
   }

   private static class InterfaceClassComparator implements Comparator, Serializable {
      private static final long serialVersionUID = -8114305773358090763L;

      public InterfaceClassComparator() {
      }

      public int compare(Object o1, Object o2) {
         if (o1 == null && o2 == null) {
            return 0;
         } else if (o1 != null && o2 != null) {
            Class cls1 = (Class)o1;
            Class cls2 = (Class)o2;
            return cls1.hashCode() - cls2.hashCode();
         } else {
            return Integer.MIN_VALUE;
         }
      }
   }
}
