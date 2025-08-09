package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.ClassMetadata;
import javax.jdo.metadata.FetchPlanMetadata;
import javax.jdo.metadata.InterfaceMetadata;
import javax.jdo.metadata.JDOMetadata;
import javax.jdo.metadata.PackageMetadata;
import javax.jdo.metadata.QueryMetadata;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.FetchPlanMetaData;
import org.datanucleus.metadata.FileMetaData;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.MetadataFileType;
import org.datanucleus.metadata.PackageMetaData;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.util.ClassUtils;

public class JDOMetadataImpl extends AbstractMetadataImpl implements JDOMetadata {
   public JDOMetadataImpl() {
      super(new FileMetaData());
      this.getInternal().setType(MetadataFileType.JDO_FILE);
   }

   public JDOMetadataImpl(FileMetaData filemd) {
      super(filemd);
   }

   public FileMetaData getInternal() {
      return (FileMetaData)this.internalMD;
   }

   public FetchPlanMetadata[] getFetchPlans() {
      FetchPlanMetaData[] baseFps = this.getInternal().getFetchPlans();
      if (baseFps == null) {
         return null;
      } else {
         FetchPlanMetadataImpl[] fps = new FetchPlanMetadataImpl[baseFps.length];

         for(int i = 0; i < fps.length; ++i) {
            fps[i] = new FetchPlanMetadataImpl(baseFps[i]);
            fps[i].parent = this;
         }

         return fps;
      }
   }

   public FetchPlanMetadata newFetchPlanMetadata(String name) {
      FetchPlanMetaData internalFpmd = this.getInternal().newFetchPlanMetadata(name);
      FetchPlanMetadataImpl fpmd = new FetchPlanMetadataImpl(internalFpmd);
      fpmd.parent = this;
      return fpmd;
   }

   public int getNumberOfFetchPlans() {
      return this.getInternal().getNoOfFetchPlans();
   }

   public QueryMetadata[] getQueries() {
      QueryMetaData[] baseQueries = this.getInternal().getQueries();
      if (baseQueries == null) {
         return null;
      } else {
         QueryMetadataImpl[] queries = new QueryMetadataImpl[this.getInternal().getNoOfQueries()];

         for(int i = 0; i < queries.length; ++i) {
            queries[i] = new QueryMetadataImpl(baseQueries[i]);
            queries[i].parent = this;
         }

         return queries;
      }
   }

   public int getNumberOfQueries() {
      return this.getInternal().getNoOfQueries();
   }

   public QueryMetadata newQueryMetadata(String name) {
      QueryMetaData internalQmd = this.getInternal().newQueryMetadata(name);
      QueryMetadataImpl qmd = new QueryMetadataImpl(internalQmd);
      qmd.parent = this;
      return qmd;
   }

   public PackageMetadata[] getPackages() {
      PackageMetadataImpl[] pmds = new PackageMetadataImpl[this.getInternal().getNoOfPackages()];

      for(int i = 0; i < pmds.length; ++i) {
         pmds[i] = new PackageMetadataImpl(this.getInternal().getPackage(i));
         pmds[i].parent = this;
      }

      return pmds;
   }

   public int getNumberOfPackages() {
      return this.getInternal().getNoOfPackages();
   }

   public PackageMetadata newPackageMetadata(String name) {
      PackageMetaData internalPmd = this.getInternal().newPackageMetadata(name);
      PackageMetadataImpl pmd = new PackageMetadataImpl(internalPmd);
      pmd.parent = this;
      return pmd;
   }

   public PackageMetadata newPackageMetadata(Package pkg) {
      PackageMetaData internalPmd = this.getInternal().newPackageMetadata(pkg.getName());
      PackageMetadataImpl pmd = new PackageMetadataImpl(internalPmd);
      pmd.parent = this;
      return pmd;
   }

   public ClassMetadata newClassMetadata(Class cls) {
      String packageName = ClassUtils.getPackageNameForClass(cls);
      PackageMetaData internalPmd = this.getInternal().newPackageMetadata(packageName);
      PackageMetadataImpl pmd = new PackageMetadataImpl(internalPmd);
      pmd.parent = this;
      String className = ClassUtils.getClassNameForClass(cls);
      ClassMetaData internalCmd = internalPmd.newClassMetadata(className);
      ClassMetadataImpl cmd = new ClassMetadataImpl(internalCmd);
      cmd.parent = pmd;
      return cmd;
   }

   public InterfaceMetadata newInterfaceMetadata(Class cls) {
      String packageName = ClassUtils.getPackageNameForClass(cls);
      PackageMetaData internalPmd = this.getInternal().newPackageMetadata(packageName);
      PackageMetadataImpl pmd = new PackageMetadataImpl(internalPmd);
      pmd.parent = this;
      String className = ClassUtils.getClassNameForClass(cls);
      InterfaceMetaData internalImd = internalPmd.newInterfaceMetadata(className);
      InterfaceMetadataImpl imd = new InterfaceMetadataImpl(internalImd);
      imd.parent = pmd;
      return imd;
   }

   public String getCatalog() {
      return this.getInternal().getCatalog();
   }

   public JDOMetadata setCatalog(String cat) {
      this.getInternal().setCatalog(cat);
      return this;
   }

   public String getSchema() {
      return this.getInternal().getSchema();
   }

   public JDOMetadata setSchema(String sch) {
      this.getInternal().setSchema(sch);
      return this;
   }
}
