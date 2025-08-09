package org.apache.spark.sql.connector.catalog;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import org.apache.arrow.util.Preconditions;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.catalyst.util.QuotingUtils;

@Evolving
class IdentifierImpl implements Identifier {
   private String[] namespace;
   private String name;

   IdentifierImpl(String[] namespace, String name) {
      Preconditions.checkNotNull(namespace, "Identifier namespace cannot be null");
      Preconditions.checkNotNull(name, "Identifier name cannot be null");
      this.namespace = namespace;
      this.name = name;
   }

   public String[] namespace() {
      return this.namespace;
   }

   public String name() {
      return this.name;
   }

   public String toString() {
      StringJoiner joiner = new StringJoiner(".");

      for(String p : this.namespace) {
         joiner.add(QuotingUtils.quoteIfNeeded(p));
      }

      joiner.add(QuotingUtils.quoteIfNeeded(this.name));
      return joiner.toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof IdentifierImpl)) {
         return false;
      } else {
         IdentifierImpl that = (IdentifierImpl)o;
         return Arrays.equals(this.namespace, that.namespace) && this.name.equals(that.name);
      }
   }

   public int hashCode() {
      int result = Objects.hash(new Object[]{this.name});
      result = 31 * result + Arrays.hashCode(this.namespace);
      return result;
   }
}
