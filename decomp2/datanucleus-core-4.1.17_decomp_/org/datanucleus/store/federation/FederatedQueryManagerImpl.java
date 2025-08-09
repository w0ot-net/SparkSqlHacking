package org.datanucleus.store.federation;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.query.QueryManagerImpl;

public class FederatedQueryManagerImpl extends QueryManagerImpl {
   public FederatedQueryManagerImpl(NucleusContext nucleusContext, StoreManager storeMgr) {
      super(nucleusContext, storeMgr);
   }

   protected void initialiseQueryCaches() {
   }

   public Query newQuery(String language, ExecutionContext ec, Object query) {
      if (language == null) {
         return null;
      } else if (query == null) {
         throw new NucleusException("Not yet supported for queries with unknown candidate");
      } else if (query instanceof String) {
         String queryString = (String)query;
         String candidateName = null;
         if (language.equalsIgnoreCase("JDOQL")) {
            int candidateStart = queryString.toUpperCase().indexOf(" FROM ") + 6;
            int candidateEnd = queryString.indexOf(" ", candidateStart + 1);
            candidateName = queryString.substring(candidateStart, candidateEnd);
         }

         if (candidateName != null) {
            ClassLoaderResolver clr = this.nucleusCtx.getClassLoaderResolver((ClassLoader)null);
            AbstractClassMetaData cmd = this.nucleusCtx.getMetaDataManager().getMetaDataForClass(candidateName, clr);
            StoreManager classStoreMgr = ((FederatedStoreManager)this.storeMgr).getStoreManagerForClass(cmd);
            return classStoreMgr.getQueryManager().newQuery(language, ec, query);
         } else {
            throw new NucleusException("Not yet supported for single-string queries");
         }
      } else if (query instanceof Query) {
         StoreManager storeMgr = ((Query)query).getStoreManager();
         return storeMgr.getQueryManager().newQuery(language, ec, query);
      } else if (query instanceof Class) {
         Class cls = (Class)query;
         ClassLoaderResolver clr = this.nucleusCtx.getClassLoaderResolver(cls.getClassLoader());
         AbstractClassMetaData cmd = this.nucleusCtx.getMetaDataManager().getMetaDataForClass(cls, clr);
         StoreManager classStoreMgr = ((FederatedStoreManager)this.storeMgr).getStoreManagerForClass(cmd);
         return classStoreMgr.getQueryManager().newQuery(language, ec, query);
      } else {
         throw new NucleusException("Not yet supported for queries taking in object of type " + query.getClass());
      }
   }
}
