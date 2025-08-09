package org.apache.avro.util;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.avro.Schema;

public final class Schemas {
   private Schemas() {
   }

   public static Object visit(final Schema start, final SchemaVisitor visitor) {
      IdentityHashMap<Schema, Schema> visited = new IdentityHashMap();
      Deque<Object> dq = new ArrayDeque();
      dq.push(start);

      Object current;
      while((current = dq.poll()) != null) {
         if (current instanceof Supplier) {
            SchemaVisitor.SchemaVisitorAction action = (SchemaVisitor.SchemaVisitorAction)((Supplier)current).get();
            switch (action) {
               case CONTINUE:
                  break;
               case SKIP_SIBLINGS:
                  while(dq.peek() instanceof Schema) {
                     dq.remove();
                  }
                  break label1;
               case TERMINATE:
                  return visitor.get();
               case SKIP_SUBTREE:
               default:
                  throw new UnsupportedOperationException("Invalid action " + String.valueOf(action));
            }
         } else {
            Schema schema = (Schema)current;
            boolean terminate;
            if (visited.containsKey(schema)) {
               terminate = visitTerminal(visitor, schema, dq);
            } else {
               Schema.Type type = schema.getType();
               switch (type) {
                  case ARRAY:
                     terminate = visitNonTerminal(visitor, schema, dq, Collections.singleton(schema.getElementType()));
                     visited.put(schema, schema);
                     break;
                  case RECORD:
                     terminate = visitNonTerminal(visitor, schema, dq, () -> ((ArrayDeque)schema.getFields().stream().map(Schema.Field::schema).collect(Collectors.toCollection(ArrayDeque::new))).descendingIterator());
                     visited.put(schema, schema);
                     break;
                  case UNION:
                     terminate = visitNonTerminal(visitor, schema, dq, schema.getTypes());
                     visited.put(schema, schema);
                     break;
                  case MAP:
                     terminate = visitNonTerminal(visitor, schema, dq, Collections.singleton(schema.getValueType()));
                     visited.put(schema, schema);
                     break;
                  default:
                     terminate = visitTerminal(visitor, schema, dq);
               }
            }

            if (terminate) {
               return visitor.get();
            }
         }
      }

      return visitor.get();
   }

   private static boolean visitNonTerminal(final SchemaVisitor visitor, final Schema schema, final Deque dq, final Iterable itSupp) {
      SchemaVisitor.SchemaVisitorAction action = visitor.visitNonTerminal(schema);
      switch (action) {
         case CONTINUE:
            dq.push((Supplier)() -> visitor.afterVisitNonTerminal(schema));
            Objects.requireNonNull(dq);
            itSupp.forEach(dq::push);
            break;
         case SKIP_SIBLINGS:
            while(dq.peek() instanceof Schema) {
               dq.remove();
            }
            break;
         case TERMINATE:
            return true;
         case SKIP_SUBTREE:
            dq.push((Supplier)() -> visitor.afterVisitNonTerminal(schema));
            break;
         default:
            String var10002 = String.valueOf(action);
            throw new UnsupportedOperationException("Invalid action " + var10002 + " for " + String.valueOf(schema));
      }

      return false;
   }

   private static boolean visitTerminal(final SchemaVisitor visitor, final Schema schema, final Deque dq) {
      SchemaVisitor.SchemaVisitorAction action = visitor.visitTerminal(schema);
      switch (action) {
         case CONTINUE:
            break;
         case SKIP_SIBLINGS:
            while(dq.peek() instanceof Schema) {
               dq.remove();
            }
            break;
         case TERMINATE:
            return true;
         case SKIP_SUBTREE:
         default:
            String var10002 = String.valueOf(action);
            throw new UnsupportedOperationException("Invalid action " + var10002 + " for " + String.valueOf(schema));
      }

      return false;
   }
}
