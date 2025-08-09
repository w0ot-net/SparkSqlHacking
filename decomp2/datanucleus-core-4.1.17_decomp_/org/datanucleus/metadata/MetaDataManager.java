package org.datanucleus.metadata;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.metadata.annotations.AnnotationManager;

public interface MetaDataManager {
   void close();

   NucleusContext getNucleusContext();

   ApiAdapter getApiAdapter();

   AnnotationManager getAnnotationManager();

   String getEnhancedMethodNamePrefix();

   boolean isEnhancerField(String var1);

   void registerListener(MetaDataListener var1);

   void deregisterListener(MetaDataListener var1);

   void setAllowMetaDataLoad(boolean var1);

   void setAllowXML(boolean var1);

   void setAllowAnnotations(boolean var1);

   void setValidate(boolean var1);

   void setXmlNamespaceAware(boolean var1);

   boolean supportsORM();

   boolean isEnhancing();

   FileMetaData[] loadMetadataFiles(String[] var1, ClassLoader var2);

   FileMetaData[] loadClasses(String[] var1, ClassLoader var2);

   FileMetaData[] loadJar(String var1, ClassLoader var2);

   FileMetaData[] loadPersistenceUnit(PersistenceUnitMetaData var1, ClassLoader var2);

   void loadUserMetaData(FileMetaData var1, ClassLoader var2);

   Collection loadFiles(String[] var1, ClassLoaderResolver var2);

   void unloadMetaDataForClass(String var1);

   boolean isClassPersistable(String var1);

   FileMetaData[] getFileMetaData();

   Collection getClassesWithMetaData();

   boolean hasMetaDataForClass(String var1);

   Collection getClassMetaDataWithApplicationId(String var1);

   AbstractClassMetaData getMetaDataForClass(String var1, ClassLoaderResolver var2);

   AbstractClassMetaData getMetaDataForClass(Class var1, ClassLoaderResolver var2);

   AbstractClassMetaData getMetaDataForEntityName(String var1);

   AbstractClassMetaData getMetaDataForDiscriminator(String var1);

   AbstractClassMetaData readMetaDataForClass(String var1);

   AbstractMemberMetaData readMetaDataForMember(String var1, String var2);

   AbstractClassMetaData getMetaDataForClassInternal(Class var1, ClassLoaderResolver var2);

   String[] getSubclassesForClass(String var1, boolean var2);

   String[] getConcreteSubclassesForClass(String var1);

   String[] getClassesImplementingInterface(String var1, ClassLoaderResolver var2);

   ClassMetaData getMetaDataForImplementationOfReference(Class var1, Object var2, ClassLoaderResolver var3);

   QueryMetaData getMetaDataForQuery(Class var1, ClassLoaderResolver var2, String var3);

   Set getNamedQueryNames();

   void registerNamedQuery(QueryMetaData var1);

   StoredProcQueryMetaData getMetaDataForStoredProcQuery(Class var1, ClassLoaderResolver var2, String var3);

   FetchPlanMetaData getMetaDataForFetchPlan(String var1);

   SequenceMetaData getMetaDataForSequence(ClassLoaderResolver var1, String var2);

   TableGeneratorMetaData getMetaDataForTableGenerator(ClassLoaderResolver var1, String var2);

   QueryResultMetaData getMetaDataForQueryResult(String var1);

   InterfaceMetaData getMetaDataForInterface(Class var1, ClassLoaderResolver var2);

   boolean isPersistentInterface(String var1);

   boolean isPersistentInterfaceImplementation(String var1, String var2);

   boolean isPersistentDefinitionImplementation(String var1);

   String getImplementationNameForPersistentInterface(String var1);

   ClassMetaData getClassMetaDataForImplementationOfPersistentInterface(String var1);

   PersistenceUnitMetaData getMetaDataForPersistenceUnit(String var1);

   void registerFile(String var1, FileMetaData var2, ClassLoaderResolver var3);

   String getClassNameForDiscriminatorValueWithRoot(AbstractClassMetaData var1, String var2);

   String getDiscriminatorValueForClass(AbstractClassMetaData var1, String var2);

   String getClassNameFromDiscriminatorValue(String var1, DiscriminatorMetaData var2);

   List getReferencedClasses(String[] var1, ClassLoaderResolver var2);

   boolean isFieldTypePersistable(Class var1);

   void registerPersistentInterface(InterfaceMetaData var1, Class var2, ClassLoaderResolver var3);

   void registerImplementationOfAbstractClass(ClassMetaData var1, Class var2, ClassLoaderResolver var3);

   void addORMDataToClass(Class var1, ClassLoaderResolver var2);

   void addAnnotationsDataToClass(Class var1, AbstractClassMetaData var2, ClassLoaderResolver var3);

   void abstractClassMetaDataInitialised(AbstractClassMetaData var1);

   void registerSequencesForFile(FileMetaData var1);

   void registerTableGeneratorsForFile(FileMetaData var1);

   void registerDiscriminatorValueForClass(AbstractClassMetaData var1, String var2);
}
