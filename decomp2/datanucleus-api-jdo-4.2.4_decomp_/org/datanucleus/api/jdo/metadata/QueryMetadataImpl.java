package org.datanucleus.api.jdo.metadata;

import javax.jdo.metadata.QueryMetadata;
import org.datanucleus.metadata.QueryLanguage;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.util.StringUtils;

public class QueryMetadataImpl extends AbstractMetadataImpl implements QueryMetadata {
   public QueryMetadataImpl(QueryMetaData querymd) {
      super(querymd);
   }

   public QueryMetaData getInternal() {
      return (QueryMetaData)this.internalMD;
   }

   public String getFetchPlan() {
      return this.getInternal().getFetchPlanName();
   }

   public String getLanguage() {
      return this.getInternal().getLanguage();
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public String getQuery() {
      return this.getInternal().getQuery();
   }

   public String getResultClass() {
      return this.getInternal().getResultClass();
   }

   public Boolean getUnique() {
      return this.getInternal().isUnique();
   }

   public boolean getUnmodifiable() {
      return this.getInternal().isUnmodifiable();
   }

   public QueryMetadata setFetchPlan(String fpName) {
      this.getInternal().setFetchPlanName(fpName);
      return this;
   }

   public QueryMetadata setLanguage(String lang) {
      if (!StringUtils.isWhitespace(lang)) {
         if (lang.equals("javax.jdo.query.JDOQL")) {
            lang = QueryLanguage.JDOQL.toString();
         } else if (lang.equals("javax.jdo.query.SQL")) {
            lang = QueryLanguage.SQL.toString();
         } else if (lang.equals("javax.jdo.query.JPQL")) {
            lang = QueryLanguage.JPQL.toString();
         }
      }

      this.getInternal().setLanguage(lang);
      return this;
   }

   public QueryMetadata setQuery(String query) {
      this.getInternal().setQuery(query);
      return this;
   }

   public QueryMetadata setResultClass(String resultClass) {
      this.getInternal().setResultClass(resultClass);
      return this;
   }

   public QueryMetadata setUnique(boolean unique) {
      this.getInternal().setUnique(unique);
      return this;
   }

   public QueryMetadata setUnmodifiable() {
      this.getInternal().setUnmodifiable(true);
      return this;
   }
}
