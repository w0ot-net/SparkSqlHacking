package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.KeyOperation;
import io.jsonwebtoken.security.KeyOperationBuilder;
import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultKeyOperationBuilder implements KeyOperationBuilder {
   private String id;
   private String description;
   private final Set related = new LinkedHashSet();

   public KeyOperationBuilder id(String id) {
      this.id = id;
      return this;
   }

   public KeyOperationBuilder description(String description) {
      this.description = description;
      return this;
   }

   public KeyOperationBuilder related(String related) {
      if (Strings.hasText(related)) {
         this.related.add(related);
      }

      return this;
   }

   public KeyOperation build() {
      return new DefaultKeyOperation(this.id, this.description, this.related);
   }
}
