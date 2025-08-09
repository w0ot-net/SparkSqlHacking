package org.apache.commons.cli;

import java.util.function.Supplier;

public final class DeprecatedAttributes {
   static final DeprecatedAttributes DEFAULT = new DeprecatedAttributes("", "", false);
   private static final String EMPTY_STRING = "";
   private final String description;
   private final boolean forRemoval;
   private final String since;

   public static Builder builder() {
      return new Builder();
   }

   private DeprecatedAttributes(String description, String since, boolean forRemoval) {
      this.description = this.toEmpty(description);
      this.since = this.toEmpty(since);
      this.forRemoval = forRemoval;
   }

   public String getDescription() {
      return this.description;
   }

   public String getSince() {
      return this.since;
   }

   public boolean isForRemoval() {
      return this.forRemoval;
   }

   private String toEmpty(String since) {
      return since != null ? since : "";
   }

   public String toString() {
      StringBuilder builder = new StringBuilder("Deprecated");
      if (this.forRemoval) {
         builder.append(" for removal");
      }

      if (!this.since.isEmpty()) {
         builder.append(" since ");
         builder.append(this.since);
      }

      if (!this.description.isEmpty()) {
         builder.append(": ");
         builder.append(this.description);
      }

      return builder.toString();
   }

   public static class Builder implements Supplier {
      private String description;
      private boolean forRemoval;
      private String since;

      public DeprecatedAttributes get() {
         return new DeprecatedAttributes(this.description, this.since, this.forRemoval);
      }

      public Builder setDescription(String description) {
         this.description = description;
         return this;
      }

      public Builder setForRemoval(boolean forRemoval) {
         this.forRemoval = forRemoval;
         return this;
      }

      public Builder setSince(String since) {
         this.since = since;
         return this;
      }
   }
}
