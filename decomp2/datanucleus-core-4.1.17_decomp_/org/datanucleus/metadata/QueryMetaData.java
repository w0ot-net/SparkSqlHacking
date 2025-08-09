package org.datanucleus.metadata;

import org.datanucleus.util.StringUtils;

public class QueryMetaData extends MetaData {
   private static final long serialVersionUID = -4592528440929968977L;
   protected String scope;
   protected String name;
   protected String language;
   protected boolean unmodifiable = false;
   protected String query;
   protected String resultClass = null;
   protected String resultMetaDataName = null;
   protected boolean unique = false;
   protected String fetchPlanName = null;

   public QueryMetaData(String name) {
      this.name = name;
   }

   public String getScope() {
      return this.scope;
   }

   public QueryMetaData setScope(String scope) {
      this.scope = StringUtils.isWhitespace(scope) ? null : scope;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public String getLanguage() {
      if (this.language == null) {
         this.language = QueryLanguage.JDOQL.toString();
      }

      return this.language;
   }

   public QueryMetaData setLanguage(String language) {
      if (!StringUtils.isWhitespace(language)) {
         this.language = language;
      }

      return this;
   }

   public boolean isUnmodifiable() {
      return this.unmodifiable;
   }

   public QueryMetaData setUnmodifiable(boolean unmodifiable) {
      this.unmodifiable = unmodifiable;
      return this;
   }

   public QueryMetaData setUnmodifiable(String unmodifiable) {
      if (!StringUtils.isWhitespace(unmodifiable)) {
         this.unmodifiable = Boolean.parseBoolean(unmodifiable);
      }

      return this;
   }

   public String getQuery() {
      return this.query;
   }

   public QueryMetaData setQuery(String query) {
      this.query = query;
      return this;
   }

   public String getResultClass() {
      return this.resultClass;
   }

   public QueryMetaData setResultClass(String resultClass) {
      this.resultClass = StringUtils.isWhitespace(resultClass) ? null : resultClass;
      return this;
   }

   public String getResultMetaDataName() {
      return this.resultMetaDataName;
   }

   public QueryMetaData setResultMetaDataName(String mdName) {
      this.resultMetaDataName = StringUtils.isWhitespace(mdName) ? null : mdName;
      return this;
   }

   public boolean isUnique() {
      return this.unique;
   }

   public QueryMetaData setUnique(boolean unique) {
      this.unique = unique;
      return this;
   }

   public QueryMetaData setUnique(String unique) {
      if (!StringUtils.isWhitespace(unique)) {
         this.unique = Boolean.parseBoolean(unique);
      }

      return this;
   }

   public String getFetchPlanName() {
      return this.fetchPlanName;
   }

   public QueryMetaData setFetchPlanName(String fpName) {
      this.fetchPlanName = StringUtils.isWhitespace(fpName) ? null : fpName;
      return this;
   }

   public String toString(String prefix, String indent) {
      StringBuilder sb = new StringBuilder();
      sb.append(prefix).append("<query name=\"" + this.name + "\"\n");
      sb.append(prefix).append("       language=\"" + this.language + "\"\n");
      if (this.unique) {
         sb.append(prefix).append("       unique=\"true\"\n");
      }

      if (this.resultClass != null) {
         sb.append(prefix).append("       result-class=\"" + this.resultClass + "\"\n");
      }

      if (this.fetchPlanName != null) {
         sb.append(prefix).append("       fetch-plan=\"" + this.fetchPlanName + "\"\n");
      }

      sb.append(prefix).append("       unmodifiable=\"" + this.unmodifiable + "\">\n");
      sb.append(prefix).append(this.query).append("\n");
      sb.append(super.toString(prefix + indent, indent));
      sb.append(prefix + "</query>\n");
      return sb.toString();
   }
}
