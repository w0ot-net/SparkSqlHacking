package org.apache.parquet.glob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class GlobExpander {
   private GlobExpander() {
   }

   public static List expand(String globPattern) {
      return GlobExpander.GlobExpanderImpl.expand(GlobParser.parse(globPattern));
   }

   private static final class GlobExpanderImpl implements GlobNode.Visitor {
      private static final GlobExpanderImpl INSTANCE = new GlobExpanderImpl();

      public static List expand(GlobNode node) {
         return (List)node.accept(INSTANCE);
      }

      public List visit(GlobNode.Atom atom) {
         return Collections.singletonList(atom.get());
      }

      public List visit(GlobNode.OneOf oneOf) {
         List<String> results = new ArrayList();

         for(GlobNode n : oneOf.getChildren()) {
            results.addAll((Collection)n.accept(this));
         }

         return results;
      }

      public List visit(GlobNode.GlobNodeSequence seq) {
         List<String> results = new ArrayList();

         for(GlobNode n : seq.getChildren()) {
            results = crossOrTakeNonEmpty(results, (List)n.accept(this));
         }

         return results;
      }

      public static List crossOrTakeNonEmpty(List list1, List list2) {
         if (list1.isEmpty()) {
            ArrayList<String> result = new ArrayList(list2.size());
            result.addAll(list2);
            return result;
         } else if (list2.isEmpty()) {
            ArrayList<String> result = new ArrayList(list1.size());
            result.addAll(list1);
            return result;
         } else {
            List<String> result = new ArrayList(list1.size() * list2.size());

            for(String s1 : list1) {
               for(String s2 : list2) {
                  result.add(s1 + s2);
               }
            }

            return result;
         }
      }
   }
}
