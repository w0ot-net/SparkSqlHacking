package org.datanucleus.metadata;

import java.util.ArrayList;
import java.util.List;

public class StoredProcQueryMetaData extends MetaData {
   private static final long serialVersionUID = 7372988908718827901L;
   String name;
   String procedureName;
   List parameters;
   List resultClasses;
   List resultSetMappings;

   public StoredProcQueryMetaData(String name) {
      this.name = name;
   }

   public StoredProcQueryMetaData setName(String name) {
      this.name = name;
      return this;
   }

   public StoredProcQueryMetaData setProcedureName(String name) {
      this.procedureName = name;
      return this;
   }

   public StoredProcQueryMetaData addParameter(StoredProcQueryParameterMetaData param) {
      if (this.parameters == null) {
         this.parameters = new ArrayList(1);
      }

      this.parameters.add(param);
      return this;
   }

   public StoredProcQueryMetaData addResultClass(String resultClass) {
      if (this.resultClasses == null) {
         this.resultClasses = new ArrayList(1);
      }

      this.resultClasses.add(resultClass);
      return this;
   }

   public StoredProcQueryMetaData addResultSetMapping(String mapping) {
      if (this.resultSetMappings == null) {
         this.resultSetMappings = new ArrayList(1);
      }

      this.resultSetMappings.add(mapping);
      return this;
   }

   public String getName() {
      return this.name;
   }

   public String getProcedureName() {
      return this.procedureName;
   }

   public List getParameters() {
      return this.parameters;
   }

   public List getResultClasses() {
      return this.resultClasses;
   }

   public List getResultSetMappings() {
      return this.resultSetMappings;
   }
}
