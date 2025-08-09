package org.apache.curator.framework.schema;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;

public class SchemaBuilder {
   private final Pattern pathRegex;
   private final String path;
   private String name = UUID.randomUUID().toString();
   private String documentation = "";
   private SchemaValidator schemaValidator = new DefaultSchemaValidator();
   private Schema.Allowance ephemeral;
   private Schema.Allowance sequential;
   private Schema.Allowance watched;
   private boolean canBeDeleted;
   private Map metadata;

   public Schema build() {
      return new Schema(this.name, this.pathRegex, this.path, this.documentation, this.schemaValidator, this.ephemeral, this.sequential, this.watched, this.canBeDeleted, this.metadata);
   }

   public SchemaBuilder name(String name) {
      this.name = (String)Preconditions.checkNotNull(name, "name cannot be null");
      return this;
   }

   public SchemaBuilder documentation(String documentation) {
      this.documentation = (String)Preconditions.checkNotNull(documentation, "documentation cannot be null");
      return this;
   }

   public SchemaBuilder dataValidator(SchemaValidator schemaValidator) {
      this.schemaValidator = (SchemaValidator)Preconditions.checkNotNull(schemaValidator, "dataValidator cannot be null");
      return this;
   }

   public SchemaBuilder ephemeral(Schema.Allowance ephemeral) {
      this.ephemeral = (Schema.Allowance)Preconditions.checkNotNull(ephemeral, "ephemeral cannot be null");
      return this;
   }

   public SchemaBuilder sequential(Schema.Allowance sequential) {
      this.sequential = (Schema.Allowance)Preconditions.checkNotNull(sequential, "sequential cannot be null");
      return this;
   }

   public SchemaBuilder watched(Schema.Allowance watched) {
      this.watched = watched;
      return this;
   }

   public SchemaBuilder canBeDeleted(boolean canBeDeleted) {
      this.canBeDeleted = canBeDeleted;
      return this;
   }

   public SchemaBuilder metadata(Map metadata) {
      this.metadata = ImmutableMap.copyOf(metadata);
      return this;
   }

   SchemaBuilder(Pattern pathRegex, String path) {
      this.ephemeral = Schema.Allowance.CAN;
      this.sequential = Schema.Allowance.CAN;
      this.watched = Schema.Allowance.CAN;
      this.canBeDeleted = true;
      this.metadata = ImmutableMap.of();
      this.pathRegex = pathRegex;
      this.path = path;
   }
}
