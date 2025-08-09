package org.datanucleus.api.jdo.metadata;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.NucleusContext;
import org.datanucleus.PersistenceNucleusContextImpl;
import org.datanucleus.enhancer.EnhancementHelper;
import org.datanucleus.enhancer.EnhancementNucleusContextImpl;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.ImplementsMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.MetaDataManagerImpl;
import org.datanucleus.metadata.MetaDataMerger;
import org.datanucleus.metadata.MetadataFileType;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.metadata.xml.MetaDataParser;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JDOMetaDataManager extends MetaDataManagerImpl {
   private static final long serialVersionUID = -2276240352978344222L;
   public static final int ALL_JDO_LOCATIONS = 1;
   public static final int JDO_1_0_0_LOCATIONS = 2;
   public static final int JDO_1_0_1_LOCATIONS = 3;
   protected int locationDefinition = 1;
   protected Map ormClassMetaDataByClass = new ConcurrentHashMap();
   protected Map classMetaDataByInterface = new ConcurrentHashMap();
   protected MetaDataRegisterClassListener registerListener;
   private static final char CLASS_SEPARATOR = '.';
   private static final char PATH_SEPARATOR = '/';
   private static final char EXTENSION_SEPARATOR = '.';
   private static final String METADATA_PACKAGE = "package";
   private static final String METADATA_LOCATION_METAINF = "/META-INF/package";
   private static final String METADATA_LOCATION_WEBINF = "/WEB-INF/package";

   public JDOMetaDataManager(NucleusContext ctxt) {
      super(ctxt);
      this.locationDefinition = 1;
      boolean useMetadataListener = false;
      Configuration conf = ctxt.getConfiguration();
      if (conf.getStringProperty("datanucleus.PersistenceUnitName") == null && ctxt instanceof PersistenceNucleusContextImpl && conf.getBooleanProperty("datanucleus.metadata.autoregistration")) {
         useMetadataListener = true;
      }

      if (NucleusLogger.METADATA.isDebugEnabled()) {
         if (this.allowXML && this.allowAnnotations) {
            if (this.allowORM) {
               String mappingName = this.getORMMappingName();
               NucleusLogger.METADATA.debug("MetaDataManager : Input=(XML,Annotations), XML-Validation=" + this.validateXML + ", XML-Suffices=(persistence=*." + this.getJDOFileSuffix() + ", orm=" + this.getORMFileSuffix() + ", query=*." + this.getJDOQueryFileSuffix() + ")" + (mappingName != null ? " mapping-name=" + mappingName : "") + ", JDO-listener=" + useMetadataListener);
            } else {
               NucleusLogger.METADATA.debug("MetaDataManager : Input=(XML,Annotations), XML-Validation=" + this.validateXML + ", XML-Suffices=(persistence=*." + this.getJDOFileSuffix() + ", query=*." + this.getJDOQueryFileSuffix() + "), JDO-listener=" + useMetadataListener);
            }
         } else if (this.allowXML && !this.allowAnnotations) {
            if (this.allowORM) {
               String mappingName = this.getORMMappingName();
               NucleusLogger.METADATA.debug("MetaDataManager : Input=(XML), XML-Validation=" + this.validateXML + ", XML-Suffices=(persistence=*." + this.getJDOFileSuffix() + ", orm=" + this.getORMFileSuffix() + ", query=*." + this.getJDOQueryFileSuffix() + ")" + (mappingName != null ? " mapping-name=" + mappingName : "") + ", JDO-listener=" + useMetadataListener);
            } else {
               NucleusLogger.METADATA.debug("MetaDataManager : Input=(XML), XML-Validation=" + this.validateXML + ", XML-Suffices=(persistence=*." + this.getJDOFileSuffix() + ", query=*." + this.getJDOQueryFileSuffix() + "), JDO-listener=" + useMetadataListener);
            }
         } else if (!this.allowXML && this.allowAnnotations) {
            NucleusLogger.METADATA.debug("MetaDataManager : Input=(Annotations), JDO-listener=" + useMetadataListener);
         } else {
            NucleusLogger.METADATA.debug("MetaDataManager : Input=(NONE), JDO-listener=" + useMetadataListener);
         }
      }

      if (useMetadataListener) {
         NucleusLogger.METADATA.debug("Registering listener for metadata initialisation");
         this.registerListener = new MetaDataRegisterClassListener();
         EnhancementHelper.getInstance().addRegisterClassListener(this.registerListener);
      }

   }

   public void close() {
      if (this.registerListener != null) {
         NucleusLogger.METADATA.debug("Deregistering listener for metadata initialisation");
         EnhancementHelper.getInstance().removeRegisterClassListener(this.registerListener);
      }

      super.close();
      this.ormClassMetaDataByClass.clear();
      this.ormClassMetaDataByClass = null;
   }

   public void unloadMetaDataForClass(String className) {
      super.unloadMetaDataForClass(className);
      this.ormClassMetaDataByClass.remove(className);
   }

   protected FileMetaData parseFile(URL fileURL) {
      if (this.metaDataParser == null) {
         this.metaDataParser = new MetaDataParser(this, this.nucleusContext.getPluginManager(), this.validateXML);
      }

      this.metaDataParser.setNamespaceAware(this.supportXMLNamespaces);
      return (FileMetaData)this.metaDataParser.parseMetaDataURL(fileURL, "jdo");
   }

   public void registerFile(String fileURLString, FileMetaData filemd, ClassLoaderResolver clr) {
      if (fileURLString != null) {
         if (this.fileMetaDataByURLString.get(fileURLString) == null) {
            this.fileMetaDataByURLString.put(fileURLString, filemd);
            this.registerQueriesForFile(filemd);
            this.registerFetchPlansForFile(filemd);
            this.registerSequencesForFile(filemd);
            this.registerTableGeneratorsForFile(filemd);
            if (filemd.getType() != MetadataFileType.JDO_QUERY_FILE) {
               for(int i = 0; i < filemd.getNoOfPackages(); ++i) {
                  PackageMetaData pmd = filemd.getPackage(i);

                  for(int j = 0; j < pmd.getNoOfClasses(); ++j) {
                     ClassMetaData cmd = pmd.getClass(j);
                     if (this.classesWithoutPersistenceInfo.contains(cmd.getFullClassName())) {
                        this.classesWithoutPersistenceInfo.remove(cmd.getFullClassName());
                     }

                     if (filemd.getType() != MetadataFileType.JDO_FILE && filemd.getType() != MetadataFileType.ANNOTATIONS) {
                        if (filemd.getType() == MetadataFileType.JDO_ORM_FILE) {
                           this.ormClassMetaDataByClass.put(cmd.getFullClassName(), cmd);
                        }
                     } else {
                        this.registerMetaDataForClass(cmd.getFullClassName(), cmd);
                     }

                     if (cmd.getEntityName() != null) {
                        this.classMetaDataByEntityName.put(cmd.getEntityName(), cmd);
                     }

                     if (cmd.getInheritanceMetaData() != null) {
                        DiscriminatorMetaData dismd = cmd.getInheritanceMetaData().getDiscriminatorMetaData();
                        if (dismd != null) {
                           if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
                              this.classMetaDataByDiscriminatorName.put(cmd.getFullClassName(), cmd);
                           } else if (dismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP && dismd.getValue() != null) {
                              this.classMetaDataByDiscriminatorName.put(dismd.getValue(), cmd);
                           }
                        }
                     }
                  }

                  for(int j = 0; j < pmd.getNoOfInterfaces(); ++j) {
                     InterfaceMetaData intfmd = pmd.getInterface(j);
                     if (filemd.getType() != MetadataFileType.JDO_FILE && filemd.getType() != MetadataFileType.ANNOTATIONS) {
                        if (filemd.getType() == MetadataFileType.JDO_ORM_FILE) {
                           this.ormClassMetaDataByClass.put(intfmd.getFullClassName(), intfmd);
                        }
                     } else {
                        this.registerMetaDataForClass(intfmd.getFullClassName(), intfmd);
                     }
                  }
               }
            }

         }
      }
   }

   public synchronized AbstractClassMetaData getMetaDataForClassInternal(Class c, ClassLoaderResolver clr) {
      if (c.isArray()) {
         return null;
      } else {
         String className = c.getName();
         if (this.isClassWithoutPersistenceInfo(className)) {
            return null;
         } else {
            AbstractClassMetaData the_md = (AbstractClassMetaData)this.classMetaDataByClass.get(className);
            if (the_md != null) {
               return the_md;
            } else if (!this.allowMetaDataLoad) {
               return null;
            } else {
               try {
                  this.updateLock.lock();
                  if (this.allowXML) {
                     FileMetaData filemd = this.loadXMLMetaDataForClass(c, clr, (String)null, this.getJDOFileSuffix(), MetadataFileType.JDO_FILE, true);
                     if (filemd != null) {
                        this.utilisedFileMetaData.add(filemd);
                        the_md = (AbstractClassMetaData)this.classMetaDataByClass.get(className);
                        AbstractClassMetaData var14 = the_md;
                        return var14;
                     }
                  }

                  if (this.allowAnnotations) {
                     FileMetaData annFilemd = this.loadAnnotationsForClass(c, clr, true, true);
                     if (annFilemd != null) {
                        if (c.isInterface()) {
                           InterfaceMetaData var13 = annFilemd.getPackage(0).getInterface(0);
                           return var13;
                        }

                        ClassMetaData var6 = annFilemd.getPackage(0).getClass(0);
                        return var6;
                     }
                  }

                  if (NucleusLogger.METADATA.isDebugEnabled()) {
                     NucleusLogger.METADATA.debug(Localiser.msg("044043", new Object[]{className}));
                  }

                  this.classesWithoutPersistenceInfo.add(className);
                  Object var12 = null;
                  return (AbstractClassMetaData)var12;
               } finally {
                  this.updateLock.unlock();
               }
            }
         }
      }
   }

   public QueryMetaData getMetaDataForQuery(Class cls, ClassLoaderResolver clr, String queryName) {
      QueryMetaData qmd = super.getMetaDataForQuery(cls, clr, queryName);
      if (qmd != null) {
         return qmd;
      } else {
         String query_key = queryName;
         if (cls != null) {
            query_key = cls.getName() + "_" + queryName;
         }

         if (cls != null) {
            AbstractClassMetaData cmd = this.getMetaDataForClass(cls, clr);
            if (cmd == null) {
               return null;
            } else {
               if (this.queryMetaDataByName != null) {
                  Object obj = this.queryMetaDataByName.get(query_key);
                  if (obj != null) {
                     return (QueryMetaData)obj;
                  }
               }

               if (this.allowXML) {
                  List locations = new ArrayList();
                  locations.addAll(this.getValidMetaDataLocationsForClass(this.getJDOQueryFileSuffix(), (String)null, cls.getName()));

                  for(int i = 0; i < locations.size(); ++i) {
                     String location = (String)locations.get(i);

                     Enumeration resources;
                     try {
                        resources = clr.getResources(location, cls.getClassLoader());
                     } catch (IOException e) {
                        throw (new NucleusException("Error loading resource", e)).setFatal();
                     }

                     while(resources.hasMoreElements()) {
                        URL fileURL = (URL)resources.nextElement();
                        if (this.fileMetaDataByURLString.get(fileURL.toString()) == null) {
                           FileMetaData filemd = this.parseFile(fileURL);
                           filemd.setType(MetadataFileType.JDO_QUERY_FILE);
                           this.registerFile(fileURL.toString(), filemd, clr);
                        }
                     }

                     this.getMetaDataForClass(cls, clr);
                     if (this.queryMetaDataByName != null) {
                        qmd = (QueryMetaData)this.queryMetaDataByName.get(query_key);
                        if (qmd != null) {
                           if (NucleusLogger.METADATA.isDebugEnabled()) {
                              NucleusLogger.METADATA.debug(Localiser.msg("044053", new Object[]{query_key, location}));
                           }

                           return qmd;
                        }

                        if (NucleusLogger.METADATA.isDebugEnabled()) {
                           NucleusLogger.METADATA.debug(Localiser.msg("044050", new Object[]{query_key, location}));
                        }
                     }
                  }
               }

               return null;
            }
         } else {
            List locations = new ArrayList();
            locations.addAll(this.getValidMetaDataLocationsForItem(this.getJDOFileSuffix(), (String)null, (String)null, false));
            locations.addAll(this.getValidMetaDataLocationsForItem(this.getORMFileSuffix(), this.getORMMappingName(), (String)null, false));
            locations.addAll(this.getValidMetaDataLocationsForItem(this.getJDOQueryFileSuffix(), (String)null, (String)null, false));

            for(int i = 0; i < locations.size(); ++i) {
               String location = (String)locations.get(i);

               Enumeration resources;
               try {
                  resources = clr.getResources(location, (ClassLoader)null);
               } catch (IOException e) {
                  throw (new NucleusException("Error loading resources", e)).setFatal();
               }

               while(resources.hasMoreElements()) {
                  URL fileURL = (URL)resources.nextElement();
                  if (this.fileMetaDataByURLString.get(fileURL.toString()) == null) {
                     FileMetaData filemd = this.parseFile(fileURL);
                     this.registerFile(fileURL.toString(), filemd, clr);
                  }
               }

               if (this.queryMetaDataByName != null) {
                  qmd = (QueryMetaData)this.queryMetaDataByName.get(query_key);
                  if (qmd != null) {
                     if (NucleusLogger.METADATA.isDebugEnabled()) {
                        NucleusLogger.METADATA.debug(Localiser.msg("044053", new Object[]{query_key, location}));
                     }

                     return qmd;
                  }
               }

               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044050", new Object[]{query_key, location}));
               }
            }

            return null;
         }
      }
   }

   public SequenceMetaData getMetaDataForSequence(ClassLoaderResolver clr, String packageSequenceName) {
      SequenceMetaData seqmd = super.getMetaDataForSequence(clr, packageSequenceName);
      if (seqmd != null) {
         return seqmd;
      } else {
         String packageName = packageSequenceName;
         if (packageSequenceName.lastIndexOf(46) >= 0) {
            packageName = packageSequenceName.substring(0, packageSequenceName.lastIndexOf(46));
         }

         List locations = new ArrayList();
         locations.addAll(this.getValidMetaDataLocationsForItem(this.getJDOFileSuffix(), (String)null, packageName, false));
         locations.addAll(this.getValidMetaDataLocationsForItem(this.getORMFileSuffix(), this.getORMMappingName(), packageName, false));

         for(int i = 0; i < locations.size(); ++i) {
            String location = (String)locations.get(i);

            Enumeration resources;
            try {
               resources = clr.getResources(location, (ClassLoader)null);
            } catch (IOException e) {
               throw (new NucleusException("Error loading resource", e)).setFatal();
            }

            while(resources.hasMoreElements()) {
               URL fileURL = (URL)resources.nextElement();
               if (this.fileMetaDataByURLString.get(fileURL.toString()) == null) {
                  FileMetaData filemd = this.parseFile(fileURL);
                  this.registerFile(fileURL.toString(), filemd, clr);
               }
            }

            if (this.sequenceMetaDataByPackageSequence != null) {
               seqmd = (SequenceMetaData)this.sequenceMetaDataByPackageSequence.get(packageSequenceName);
            }

            if (seqmd != null) {
               if (NucleusLogger.METADATA.isDebugEnabled()) {
                  NucleusLogger.METADATA.debug(Localiser.msg("044053", new Object[]{packageSequenceName, location}));
               }

               return seqmd;
            }

            if (NucleusLogger.METADATA.isDebugEnabled()) {
               NucleusLogger.METADATA.debug(Localiser.msg("044051", new Object[]{packageSequenceName, location}));
            }
         }

         return null;
      }
   }

   public void addORMDataToClass(Class c, ClassLoaderResolver clr) {
      if (!(this.getNucleusContext() instanceof EnhancementNucleusContextImpl)) {
         if (this.allowORM) {
            AbstractClassMetaData cmd = (AbstractClassMetaData)this.classMetaDataByClass.get(c.getName());
            AbstractClassMetaData ormCmd = (AbstractClassMetaData)this.ormClassMetaDataByClass.get(c.getName());
            if (ormCmd != null) {
               MetaDataMerger.mergeClassORMData(cmd, ormCmd, this);
               this.ormClassMetaDataByClass.remove(c.getName());
            } else {
               if (this.allowXML) {
                  FileMetaData filemdORM = this.loadXMLMetaDataForClass(c, clr, this.getORMMappingName(), this.getORMFileSuffix(), MetadataFileType.JDO_ORM_FILE, false);
                  if (filemdORM != null) {
                     ormCmd = (AbstractClassMetaData)this.ormClassMetaDataByClass.get(c.getName());
                     if (ormCmd != null) {
                        MetaDataMerger.mergeFileORMData((FileMetaData)cmd.getPackageMetaData().getParent(), (FileMetaData)ormCmd.getPackageMetaData().getParent());
                        MetaDataMerger.mergeClassORMData(cmd, ormCmd, this);
                        this.ormClassMetaDataByClass.remove(c.getName());
                     }
                  }
               }

            }
         }
      }
   }

   protected FileMetaData loadXMLMetaDataForClass(Class pc_class, ClassLoaderResolver clr, String mappingModifier, String metadataFileExtension, MetadataFileType metadataType, boolean populate) {
      for(String location : this.getValidMetaDataLocationsForClass(metadataFileExtension, mappingModifier, pc_class.getName())) {
         Enumeration resources;
         try {
            resources = clr.getResources(location, pc_class.getClassLoader());
         } catch (IOException e) {
            throw (new NucleusException("Error loading resource", e)).setFatal();
         }

         if (!resources.hasMoreElements() && NucleusLogger.METADATA.isDebugEnabled()) {
            NucleusLogger.METADATA.debug(Localiser.msg("044049", new Object[]{metadataFileExtension, pc_class.getName(), location}));
         }

         while(resources.hasMoreElements()) {
            URL url = (URL)resources.nextElement();
            if (url != null) {
               FileMetaData filemd = (FileMetaData)this.fileMetaDataByURLString.get(url.toString());
               if (filemd == null) {
                  filemd = this.parseFile(url);
                  if (filemd.getType() != metadataType) {
                     NucleusLogger.METADATA.warn(Localiser.msg("044045", new Object[]{url, filemd.getType(), metadataType}));
                     FileMetaData var14 = null;
                     break;
                  }

                  this.registerFile(url.toString(), filemd, clr);
                  if (populate) {
                     this.populateFileMetaData(filemd, clr, pc_class.getClassLoader());
                  }
               }

               if (filemd.getType() == MetadataFileType.JDO_FILE && this.classMetaDataByClass.get(pc_class.getName()) != null || filemd.getType() == MetadataFileType.JDO_ORM_FILE && this.ormClassMetaDataByClass.get(pc_class.getName()) != null) {
                  if (NucleusLogger.METADATA.isDebugEnabled()) {
                     NucleusLogger.METADATA.debug(Localiser.msg("044052", new Object[]{metadataFileExtension, pc_class.getName(), url}));
                  }

                  return filemd;
               }
            }
         }
      }

      if (NucleusLogger.METADATA.isDebugEnabled()) {
         NucleusLogger.METADATA.debug(Localiser.msg("044048", new Object[]{metadataFileExtension, pc_class.getName()}));
      }

      return null;
   }

   public List getValidMetaDataLocationsForPackage(String fileExtension, String fileModifier, String packageName) {
      return this.getValidMetaDataLocationsForItem(fileExtension, fileModifier, packageName, false);
   }

   public List getValidMetaDataLocationsForClass(String fileExtension, String fileModifier, String className) {
      return this.getValidMetaDataLocationsForItem(fileExtension, fileModifier, className, true);
   }

   List getValidMetaDataLocationsForItem(String fileExtension, String fileModifier, String itemName, boolean isClass) {
      List locations = new ArrayList();
      if (fileExtension == null) {
         fileExtension = "jdo";
      }

      StringTokenizer tokens = new StringTokenizer(fileExtension, ",");

      while(tokens.hasMoreTokens()) {
         locations.addAll(this.getValidMetaDataLocationsForSingleExtension(tokens.nextToken(), fileModifier, itemName, isClass));
      }

      return locations;
   }

   private List getValidMetaDataLocationsForSingleExtension(String fileExtension, String fileModifier, String itemName, boolean isClass) {
      List locations = new ArrayList();
      String suffix = null;
      if (fileExtension == null) {
         fileExtension = "jdo";
      }

      if (fileModifier != null) {
         suffix = "-" + fileModifier + '.' + fileExtension;
      } else {
         suffix = '.' + fileExtension;
      }

      if (this.locationDefinition == 1 || this.locationDefinition == 3) {
         locations.add("/META-INF/package" + suffix);
         locations.add("/WEB-INF/package" + suffix);
         locations.add("/package" + suffix);
      }

      if (itemName != null && itemName.length() > 0) {
         int separatorPosition = itemName.indexOf(46);
         if (separatorPosition < 0) {
            if (this.locationDefinition == 1 || this.locationDefinition == 3) {
               locations.add('/' + itemName + '/' + "package" + suffix);
            }

            if (this.locationDefinition == 1 || this.locationDefinition == 2) {
               locations.add('/' + itemName + suffix);
            }
         } else {
            while(separatorPosition >= 0) {
               String name = itemName.substring(0, separatorPosition);
               if (this.locationDefinition == 1 || this.locationDefinition == 3) {
                  locations.add('/' + name.replace('.', '/') + '/' + "package" + suffix);
               }

               if (this.locationDefinition == 1 || this.locationDefinition == 2) {
                  locations.add('/' + name.replace('.', '/') + suffix);
               }

               separatorPosition = itemName.indexOf(46, separatorPosition + 1);
               if (separatorPosition < 0) {
                  if (!isClass && (this.locationDefinition == 1 || this.locationDefinition == 3)) {
                     locations.add('/' + itemName.replace('.', '/') + '/' + "package" + suffix);
                  }

                  if (this.locationDefinition == 1 || this.locationDefinition == 2) {
                     locations.add('/' + itemName.replace('.', '/') + suffix);
                  }
               }
            }
         }
      }

      return locations;
   }

   private String getORMMappingName() {
      String mappingName = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.mapping");
      return StringUtils.isWhitespace(mappingName) ? null : mappingName;
   }

   private String getJDOFileSuffix() {
      String suffix = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.metadata.jdoFileExtension");
      return StringUtils.isWhitespace(suffix) ? "jdo" : suffix;
   }

   private String getORMFileSuffix() {
      String suffix = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.metadata.ormFileExtension");
      return StringUtils.isWhitespace(suffix) ? "orm" : suffix;
   }

   private String getJDOQueryFileSuffix() {
      String suffix = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.metadata.jdoqueryFileExtension");
      return StringUtils.isWhitespace(suffix) ? "jdoquery" : suffix;
   }

   public InterfaceMetaData getMetaDataForInterface(Class c, ClassLoaderResolver clr) {
      if (c != null && c.isInterface()) {
         InterfaceMetaData imd = (InterfaceMetaData)this.getMetaDataForClassInternal(c, clr);
         if (imd != null) {
            this.populateAbstractClassMetaData(imd, clr, c.getClassLoader());
            this.initialiseAbstractClassMetaData(imd, clr);
            if (this.utilisedFileMetaData.size() > 0) {
               for(FileMetaData filemd : this.utilisedFileMetaData) {
                  this.initialiseFileMetaData(filemd, clr, c.getClassLoader());
               }
            }
         }

         this.utilisedFileMetaData.clear();
         return imd;
      } else {
         return null;
      }
   }

   public boolean isPersistentInterface(String name) {
      AbstractClassMetaData acmd = (AbstractClassMetaData)this.classMetaDataByClass.get(name);
      return acmd != null && acmd instanceof InterfaceMetaData;
   }

   public boolean isPersistentInterfaceImplementation(String interfaceName, String implName) {
      ClassMetaData cmd = (ClassMetaData)this.classMetaDataByInterface.get(interfaceName);
      return cmd != null && cmd.getFullClassName().equals(implName);
   }

   public boolean isPersistentDefinitionImplementation(String implName) {
      ClassMetaData cmd = (ClassMetaData)this.classMetaDataByClass.get(implName);
      return cmd != null && cmd.isImplementationOfPersistentDefinition();
   }

   public String getImplementationNameForPersistentInterface(String interfaceName) {
      ClassMetaData cmd = (ClassMetaData)this.classMetaDataByInterface.get(interfaceName);
      return cmd != null ? cmd.getFullClassName() : null;
   }

   public ClassMetaData getClassMetaDataForImplementationOfPersistentInterface(String interfaceName) {
      return (ClassMetaData)this.classMetaDataByInterface.get(interfaceName);
   }

   public void registerPersistentInterface(InterfaceMetaData imd, Class implClass, ClassLoaderResolver clr) {
      ClassMetaData cmd = new ClassMetaData(imd, ClassUtils.getClassNameForClass(implClass), true);
      cmd.addImplements(new ImplementsMetaData(imd.getFullClassName()));
      this.registerMetaDataForClass(cmd.getFullClassName(), cmd);
      this.classMetaDataByInterface.put(imd.getFullClassName(), cmd);
      this.initialiseClassMetaData(cmd, implClass, clr);
      if (NucleusLogger.METADATA.isDebugEnabled()) {
         NucleusLogger.METADATA.debug(Localiser.msg("044044", new Object[]{implClass.getName()}));
      }

      this.classesWithoutPersistenceInfo.remove(implClass.getName());
   }

   public void registerImplementationOfAbstractClass(ClassMetaData cmd, Class implClass, ClassLoaderResolver clr) {
      ClassMetaData implCmd = new ClassMetaData(cmd, ClassUtils.getClassNameForClass(implClass));
      this.registerMetaDataForClass(implCmd.getFullClassName(), implCmd);
      this.initialiseClassMetaData(implCmd, implClass, clr);
      if (NucleusLogger.METADATA.isDebugEnabled()) {
         NucleusLogger.METADATA.debug(Localiser.msg("044044", new Object[]{implClass.getName()}));
      }

      this.classesWithoutPersistenceInfo.remove(implClass.getName());
   }

   private class MetaDataRegisterClassListener implements EnhancementHelper.RegisterClassListener {
      private MetaDataRegisterClassListener() {
      }

      public void registerClass(EnhancementHelper.RegisterClassEvent ev) {
         NucleusLogger.METADATA.debug("Listener found initialisation for persistable class " + ev.getRegisteredClass().getName());

         try {
            JDOMetaDataManager.this.getMetaDataForClass(ev.getRegisteredClass(), JDOMetaDataManager.this.nucleusContext.getClassLoaderResolver(ev.getRegisteredClass().getClassLoader()));
         } catch (Exception e) {
            NucleusLogger.METADATA.warn("Listener attempted to load metadata for " + ev.getRegisteredClass().getName() + " but an exception was thrown. Ignoring this class", e);
         }

      }
   }
}
