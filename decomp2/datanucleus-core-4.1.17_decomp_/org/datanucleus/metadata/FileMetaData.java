package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class FileMetaData extends MetaData {
   private static final long serialVersionUID = 6622356672262681288L;
   protected transient MetaDataManager metaDataManager;
   protected MetadataFileType type;
   protected String filename;
   protected String catalog;
   protected String schema;
   protected Collection queries = null;
   protected Collection storedProcs = null;
   protected Collection queryResultMetaData = null;
   protected Collection fetchPlans = null;
   protected List packages = null;
   protected List listeners = null;

   public void setMetaDataManager(MetaDataManager mmgr) {
      this.metaDataManager = mmgr;
   }

   public String getFilename() {
      return this.filename;
   }

   public FileMetaData setFilename(String filename) {
      this.filename = filename;
      return this;
   }

   public String getCatalog() {
      return this.catalog;
   }

   public FileMetaData setCatalog(String catalog) {
      this.catalog = catalog;
      return this;
   }

   public String getSchema() {
      return this.schema;
   }

   public FileMetaData setSchema(String schema) {
      this.schema = schema;
      return this;
   }

   public MetadataFileType getType() {
      return this.type;
   }

   public FileMetaData setType(MetadataFileType type) {
      this.type = type;
      return this;
   }

   public int getNoOfQueries() {
      return this.queries != null ? this.queries.size() : 0;
   }

   public QueryMetaData[] getQueries() {
      return this.queries == null ? null : (QueryMetaData[])((QueryMetaData[])this.queries.toArray(new QueryMetaData[this.queries.size()]));
   }

   public int getNoOfStoredProcQueries() {
      return this.storedProcs != null ? this.storedProcs.size() : 0;
   }

   public StoredProcQueryMetaData[] getStoredProcQueries() {
      return this.storedProcs == null ? null : (StoredProcQueryMetaData[])((StoredProcQueryMetaData[])this.storedProcs.toArray(new StoredProcQueryMetaData[this.storedProcs.size()]));
   }

   public int getNoOfFetchPlans() {
      return this.fetchPlans != null ? this.fetchPlans.size() : 0;
   }

   public FetchPlanMetaData[] getFetchPlans() {
      return this.fetchPlans == null ? null : (FetchPlanMetaData[])((FetchPlanMetaData[])this.fetchPlans.toArray(new FetchPlanMetaData[this.fetchPlans.size()]));
   }

   public int getNoOfPackages() {
      return this.packages != null ? this.packages.size() : 0;
   }

   public PackageMetaData getPackage(int i) {
      return this.packages == null ? null : (PackageMetaData)this.packages.get(i);
   }

   public PackageMetaData getPackage(String name) {
      if (this.packages == null) {
         return null;
      } else {
         for(PackageMetaData p : this.packages) {
            if (p.name.equals(name)) {
               return p;
            }
         }

         return null;
      }
   }

   public ClassMetaData getClass(String pkg_name, String class_name) {
      if (pkg_name != null && class_name != null) {
         PackageMetaData pmd = this.getPackage(pkg_name);
         return pmd != null ? pmd.getClass(class_name) : null;
      } else {
         return null;
      }
   }

   public QueryMetaData newQueryMetadata(String queryName) {
      QueryMetaData qmd = new QueryMetaData(queryName);
      if (this.queries == null) {
         this.queries = new HashSet();
      }

      this.queries.add(qmd);
      qmd.parent = this;
      return qmd;
   }

   public StoredProcQueryMetaData newStoredProcQueryMetaData(String queryName) {
      StoredProcQueryMetaData qmd = new StoredProcQueryMetaData(queryName);
      if (this.storedProcs == null) {
         this.storedProcs = new HashSet();
      }

      this.storedProcs.add(qmd);
      qmd.parent = this;
      return qmd;
   }

   public FetchPlanMetaData newFetchPlanMetadata(String name) {
      FetchPlanMetaData fpmd = new FetchPlanMetaData(name);
      if (this.fetchPlans == null) {
         this.fetchPlans = new HashSet();
      }

      this.fetchPlans.add(fpmd);
      fpmd.parent = this;
      return fpmd;
   }

   public PackageMetaData newPackageMetadata(String name) {
      PackageMetaData pmd = new PackageMetaData(name);
      if (this.packages == null) {
         this.packages = new ArrayList();
      } else {
         for(PackageMetaData p : this.packages) {
            if (pmd.getName().equals(p.getName())) {
               return p;
            }
         }
      }

      this.packages.add(pmd);
      pmd.parent = this;
      return pmd;
   }

   public void addListener(EventListenerMetaData listener) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      if (!this.listeners.contains(listener)) {
         this.listeners.add(listener);
         listener.parent = this;
      }

   }

   public List getListeners() {
      return this.listeners;
   }

   public void addQueryResultMetaData(QueryResultMetaData resultMetaData) {
      if (this.queryResultMetaData == null) {
         this.queryResultMetaData = new HashSet();
      }

      if (!this.queryResultMetaData.contains(resultMetaData)) {
         this.queryResultMetaData.add(resultMetaData);
         resultMetaData.parent = this;
      }

   }

   public QueryResultMetaData newQueryResultMetadata(String name) {
      QueryResultMetaData qrmd = new QueryResultMetaData(name);
      this.addQueryResultMetaData(qrmd);
      return qrmd;
   }

   public QueryResultMetaData[] getQueryResultMetaData() {
      return this.queryResultMetaData == null ? null : (QueryResultMetaData[])this.queryResultMetaData.toArray(new QueryResultMetaData[this.queryResultMetaData.size()]);
   }

   public String toString(String prefix, String indent) {
      if (indent == null) {
         indent = "";
      }

      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<jdo");
      if (this.catalog != null) {
         sb.append(" catalog=\"" + this.catalog + "\"");
      }

      if (this.schema != null) {
         sb.append(" schema=\"" + this.schema + "\"");
      }

      sb.append(">\n");
      if (this.packages != null) {
         Iterator<PackageMetaData> iter = this.packages.iterator();

         while(iter.hasNext()) {
            sb.append(((PackageMetaData)iter.next()).toString(indent, indent));
         }
      }

      if (this.queries != null) {
         Iterator iter = this.queries.iterator();

         while(iter.hasNext()) {
            sb.append(((QueryMetaData)iter.next()).toString(indent, indent));
         }
      }

      if (this.fetchPlans != null) {
         Iterator iter = this.fetchPlans.iterator();

         while(iter.hasNext()) {
            sb.append(((FetchPlanMetaData)iter.next()).toString(indent, indent));
         }
      }

      sb.append(super.toString(indent, indent));
      sb.append("</jdo>");
      return sb.toString();
   }
}
