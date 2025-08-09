package org.apache.avro.util;

import org.apache.avro.Schema;

public interface SchemaVisitor {
   SchemaVisitorAction visitTerminal(Schema terminal);

   SchemaVisitorAction visitNonTerminal(Schema nonTerminal);

   SchemaVisitorAction afterVisitNonTerminal(Schema nonTerminal);

   Object get();

   public static enum SchemaVisitorAction {
      CONTINUE,
      TERMINATE,
      SKIP_SUBTREE,
      SKIP_SIBLINGS;

      // $FF: synthetic method
      private static SchemaVisitorAction[] $values() {
         return new SchemaVisitorAction[]{CONTINUE, TERMINATE, SKIP_SUBTREE, SKIP_SIBLINGS};
      }
   }
}
