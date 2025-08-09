package org.apache.curator.framework.schema;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;

public class Schema {
   private final String name;
   private final Pattern pathRegex;
   private final String fixedPath;
   private final String documentation;
   private final SchemaValidator schemaValidator;
   private final Allowance ephemeral;
   private final Allowance sequential;
   private final Allowance watched;
   private final boolean canBeDeleted;
   private final Map metadata;

   public static SchemaBuilder builder(String path) {
      return new SchemaBuilder((Pattern)null, path);
   }

   public static SchemaBuilder builder(Pattern pathRegex) {
      return new SchemaBuilder(pathRegex, (String)null);
   }

   public static SchemaBuilder builderForRecipeParent(String parentPath) {
      return (new SchemaBuilder((Pattern)null, parentPath)).sequential(Schema.Allowance.CANNOT).ephemeral(Schema.Allowance.CANNOT);
   }

   public static SchemaBuilder builderForRecipe(String parentPath) {
      return (new SchemaBuilder(Pattern.compile(ZKPaths.makePath(parentPath, ".*")), (String)null)).sequential(Schema.Allowance.MUST).ephemeral(Schema.Allowance.MUST).watched(Schema.Allowance.MUST).canBeDeleted(true);
   }

   Schema(String name, Pattern pathRegex, String path, String documentation, SchemaValidator schemaValidator, Allowance ephemeral, Allowance sequential, Allowance watched, boolean canBeDeleted, Map metadata) {
      Preconditions.checkArgument(pathRegex != null || path != null, "pathRegex and path cannot both be null");
      this.pathRegex = pathRegex;
      this.fixedPath = this.fixPath(path);
      this.metadata = ImmutableMap.copyOf((Map)Preconditions.checkNotNull(metadata, "metadata cannot be null"));
      this.name = (String)Preconditions.checkNotNull(name, "name cannot be null");
      this.documentation = (String)Preconditions.checkNotNull(documentation, "documentation cannot be null");
      this.schemaValidator = (SchemaValidator)Preconditions.checkNotNull(schemaValidator, "dataValidator cannot be null");
      this.ephemeral = (Allowance)Preconditions.checkNotNull(ephemeral, "ephemeral cannot be null");
      this.sequential = (Allowance)Preconditions.checkNotNull(sequential, "sequential cannot be null");
      this.watched = (Allowance)Preconditions.checkNotNull(watched, "watched cannot be null");
      this.canBeDeleted = canBeDeleted;
   }

   private String fixPath(String path) {
      if (path != null) {
         if (path.endsWith("/")) {
            return path.length() > 1 ? path.substring(0, path.length() - 1) : "";
         } else {
            return path;
         }
      } else {
         return null;
      }
   }

   public void validateDelete(String path) {
      if (!this.canBeDeleted) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, (byte[])null, (List)null), "Cannot be deleted");
      }
   }

   public void validateWatch(String path, boolean isWatching) {
      if (isWatching && this.watched == Schema.Allowance.CANNOT) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, (byte[])null, (List)null), "Cannot be watched");
      } else if (!isWatching && this.watched == Schema.Allowance.MUST) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, (byte[])null, (List)null), "Must be watched");
      }
   }

   public void validateCreate(CreateMode mode, String path, byte[] data, List acl) {
      if (mode.isEphemeral() && this.ephemeral == Schema.Allowance.CANNOT) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, data, acl), "Cannot be ephemeral");
      } else if (!mode.isEphemeral() && this.ephemeral == Schema.Allowance.MUST) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, data, acl), "Must be ephemeral");
      } else if (mode.isSequential() && this.sequential == Schema.Allowance.CANNOT) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, data, acl), "Cannot be sequential");
      } else if (!mode.isSequential() && this.sequential == Schema.Allowance.MUST) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, data, acl), "Must be sequential");
      } else {
         this.validateGeneral(path, data, acl);
      }
   }

   public void validateGeneral(String path, byte[] data, List acl) {
      if (!this.schemaValidator.isValid(this, path, data, acl)) {
         throw new SchemaViolation(this, new SchemaViolation.ViolatorData(path, data, acl), "Data is not valid");
      }
   }

   public String getName() {
      return this.name;
   }

   public String getRawPath() {
      return this.fixedPath != null ? this.fixedPath : this.pathRegex.pattern();
   }

   public Map getMetadata() {
      return this.metadata;
   }

   public Pattern getPathRegex() {
      return this.pathRegex;
   }

   public String getPath() {
      return this.fixedPath;
   }

   public String getDocumentation() {
      return this.documentation;
   }

   public SchemaValidator getSchemaValidator() {
      return this.schemaValidator;
   }

   public Allowance getEphemeral() {
      return this.ephemeral;
   }

   public Allowance getSequential() {
      return this.sequential;
   }

   public Allowance getWatched() {
      return this.watched;
   }

   public boolean canBeDeleted() {
      return this.canBeDeleted;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Schema schema = (Schema)o;
         return !this.pathRegex.equals(schema.pathRegex) ? false : this.fixedPath.equals(schema.fixedPath);
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.pathRegex.hashCode();
      result = 31 * result + this.fixedPath.hashCode();
      return result;
   }

   public String toString() {
      return "Schema{name='" + this.name + '\'' + ", pathRegex=" + this.pathRegex + ", path='" + this.fixedPath + '\'' + ", documentation='" + this.documentation + '\'' + ", dataValidator=" + this.schemaValidator.getClass() + ", ephemeral=" + this.ephemeral + ", sequential=" + this.sequential + ", watched=" + this.watched + ", canBeDeleted=" + this.canBeDeleted + ", metadata=" + this.metadata + '}';
   }

   public String toDocumentation() {
      String pathLabel = this.pathRegex != null ? "Path Regex: " : "Path: ";
      return "Name: " + this.name + '\n' + pathLabel + this.getRawPath() + '\n' + "Doc: " + this.documentation + '\n' + "Validator: " + this.schemaValidator.getClass().getSimpleName() + '\n' + "Meta: " + this.metadata + '\n' + String.format("ephemeral: %s | sequential: %s | watched: %s | canBeDeleted: %s", this.ephemeral, this.sequential, this.watched, this.canBeDeleted) + '\n';
   }

   public static enum Allowance {
      CAN,
      MUST,
      CANNOT;
   }
}
