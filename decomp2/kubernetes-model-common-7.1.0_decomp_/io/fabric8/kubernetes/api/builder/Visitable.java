package io.fabric8.kubernetes.api.builder;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Visitable {
   default Object accept(final Class type, final Visitor visitor) {
      return this.accept(Collections.emptyList(), new Visitor() {
         public Class getType() {
            return type;
         }

         public void visit(List path, Object element) {
            visitor.visit(path, element);
         }

         public void visit(Object element) {
            visitor.visit(element);
         }
      });
   }

   default Object accept(Visitor... visitors) {
      return this.accept(Collections.emptyList(), visitors);
   }

   default Object accept(List path, Visitor... visitors) {
      return this.accept(path, "", visitors);
   }

   default Object accept(List path, String currentKey, Visitor... visitors) {
      List<Visitor> sortedVisitor = new ArrayList();

      for(Visitor visitor : visitors) {
         visitor = VisitorListener.wrap(visitor);
         if (visitor.canVisit(path, this)) {
            sortedVisitor.add(visitor);
         }
      }

      sortedVisitor.sort((l, r) -> r.order() - l.order());

      for(Visitor visitor : sortedVisitor) {
         visitor.visit(path, this);
      }

      List<Map.Entry<String, Object>> copyOfPath = path != null ? new ArrayList(path) : new ArrayList();
      copyOfPath.add(new AbstractMap.SimpleEntry(currentKey, this));
      this.getVisitableMap().ifPresent((vm) -> {
         for(Map.Entry entry : vm.entrySet()) {
            List<Map.Entry<String, Object>> newPath = Collections.unmodifiableList(copyOfPath);

            for(Visitable visitable : new ArrayList((List)entry.getValue())) {
               for(Visitor visitor : visitors) {
                  if (visitor.getType() != null && visitor.getType().isAssignableFrom(visitable.getClass())) {
                     visitable.accept(newPath, (String)entry.getKey(), visitor);
                  }
               }

               for(Visitor visitor : visitors) {
                  if (visitor.getType() == null || !visitor.getType().isAssignableFrom(visitable.getClass())) {
                     visitable.accept(newPath, (String)entry.getKey(), visitor);
                  }
               }
            }
         }

      });
      return this;
   }

   default Object getTarget(Visitable visitable) {
      return visitable;
   }

   default Optional getVisitableMap() {
      return Optional.empty();
   }
}
