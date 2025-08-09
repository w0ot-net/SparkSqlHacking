package org.datanucleus.metadata;

public class StoredProcQueryParameterMetaData extends MetaData {
   private static final long serialVersionUID = 7363911357565223250L;
   String name;
   String type;
   StoredProcQueryParameterMode mode;

   public String getName() {
      return this.name;
   }

   public StoredProcQueryParameterMetaData setName(String name) {
      this.name = name;
      return this;
   }

   public String getType() {
      return this.type;
   }

   public StoredProcQueryParameterMetaData setType(String type) {
      this.type = type;
      return this;
   }

   public StoredProcQueryParameterMode getMode() {
      return this.mode;
   }

   public StoredProcQueryParameterMetaData setMode(StoredProcQueryParameterMode mode) {
      this.mode = mode;
      return this;
   }
}
