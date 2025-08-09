package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.depend.Provider;

public final class FileInfoDescriptor extends UniqueSQLObjectDescriptor implements Provider {
   public static final int JAR_FILE_TYPE = 0;
   private final UUID id;
   private final SchemaDescriptor sd;
   private final String sqlName;
   private final long generationId;

   public FileInfoDescriptor(DataDictionary var1, UUID var2, SchemaDescriptor var3, String var4, long var5) {
      super(var1);
      this.id = var2;
      this.sd = var3;
      this.sqlName = var4;
      this.generationId = var5;
   }

   public SchemaDescriptor getSchemaDescriptor() {
      return this.sd;
   }

   public String getName() {
      return this.sqlName;
   }

   public UUID getUUID() {
      return this.id;
   }

   public long getGenerationId() {
      return this.generationId;
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(273);
   }

   public String getObjectName() {
      return this.sqlName;
   }

   public UUID getObjectID() {
      return this.id;
   }

   public String getClassType() {
      return "File";
   }

   public String getDescriptorType() {
      return "Jar file";
   }

   public String getDescriptorName() {
      return this.sqlName;
   }
}
