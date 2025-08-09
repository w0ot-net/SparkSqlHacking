package org.datanucleus.store.rdbms.identifier;

import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.schema.naming.NamingCase;

public class DatastoreIdentifierImpl implements DatastoreIdentifier {
   protected final DatastoreAdapter dba;
   protected final IdentifierFactory factory;
   protected String name;
   protected String catalogName;
   protected String schemaName;
   private String toString;

   protected DatastoreIdentifierImpl(IdentifierFactory factory, String sqlIdentifier) {
      this.dba = factory.getDatastoreAdapter();
      this.factory = factory;
      this.name = this.toCase(sqlIdentifier);
   }

   protected String toCase(String identifierName) {
      if (this.factory.getNamingCase() != NamingCase.LOWER_CASE && this.factory.getNamingCase() != NamingCase.LOWER_CASE_QUOTED) {
         return this.factory.getNamingCase() != NamingCase.UPPER_CASE && this.factory.getNamingCase() != NamingCase.UPPER_CASE_QUOTED ? identifierName : identifierName.toUpperCase();
      } else {
         return identifierName.toLowerCase();
      }
   }

   public String getName() {
      return this.name;
   }

   public void setCatalogName(String catalogName) {
      this.catalogName = catalogName;
   }

   public void setSchemaName(String schemaName) {
      this.schemaName = schemaName;
   }

   public String getCatalogName() {
      return this.catalogName;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public int hashCode() {
      return this.name.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof DatastoreIdentifierImpl)) {
         return false;
      } else {
         DatastoreIdentifierImpl id = (DatastoreIdentifierImpl)obj;
         return this.name.equals(id.name) && (this.schemaName == null || id.schemaName == null || this.schemaName.equals(id.schemaName)) && (this.catalogName == null || id.catalogName == null || this.catalogName.equals(id.catalogName));
      }
   }

   public String toString() {
      if (this.toString == null) {
         String identifierQuoteString = this.dba.getIdentifierQuoteString();
         if (this.dba.isReservedKeyword(this.name)) {
            this.toString = identifierQuoteString + this.name + identifierQuoteString;
         } else if (this.factory.getNamingCase() != NamingCase.LOWER_CASE_QUOTED && this.factory.getNamingCase() != NamingCase.MIXED_CASE_QUOTED && this.factory.getNamingCase() != NamingCase.UPPER_CASE_QUOTED) {
            this.toString = this.name;
         } else {
            this.toString = identifierQuoteString + this.name + identifierQuoteString;
         }
      }

      return this.toString;
   }

   public final String getFullyQualifiedName(boolean adapterCase) {
      boolean supportsCatalogName = this.dba.supportsOption("CatalogInTableDefinition");
      boolean supportsSchemaName = this.dba.supportsOption("SchemaInTableDefinition");
      String separator = this.dba.getCatalogSeparator();
      StringBuilder name = new StringBuilder();
      if (supportsCatalogName && this.catalogName != null) {
         if (adapterCase) {
            name.append(this.factory.getIdentifierInAdapterCase(this.catalogName));
         } else {
            name.append(this.catalogName);
         }

         name.append(separator);
      }

      if (supportsSchemaName && this.schemaName != null) {
         if (adapterCase) {
            name.append(this.factory.getIdentifierInAdapterCase(this.schemaName));
         } else {
            name.append(this.schemaName);
         }

         name.append(separator);
      }

      if (adapterCase) {
         name.append(this.factory.getIdentifierInAdapterCase(this.toString()));
      } else {
         name.append(this.toString());
      }

      return name.toString();
   }
}
