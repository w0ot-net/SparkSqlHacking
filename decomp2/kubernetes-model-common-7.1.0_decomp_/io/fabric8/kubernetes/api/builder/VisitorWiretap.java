package io.fabric8.kubernetes.api.builder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class VisitorWiretap implements Visitor {
   private final Collection listeners;
   private final Visitor delegate;

   private VisitorWiretap(Visitor delegate, Collection listeners) {
      this.delegate = delegate;
      this.listeners = listeners;
   }

   public static VisitorWiretap create(Visitor visitor, Collection listeners) {
      return visitor instanceof VisitorWiretap ? (VisitorWiretap)visitor : new VisitorWiretap(visitor, listeners);
   }

   public Class getType() {
      return this.delegate.getType();
   }

   public void visit(Object target) {
      for(VisitorListener l : this.listeners) {
         l.beforeVisit(this.delegate, Collections.emptyList(), target);
      }

      this.delegate.visit(target);

      for(VisitorListener l : this.listeners) {
         l.afterVisit(this.delegate, Collections.emptyList(), target);
      }

   }

   public int order() {
      return this.delegate.order();
   }

   public void visit(List path, Object target) {
      for(VisitorListener l : this.listeners) {
         l.beforeVisit(this.delegate, path, target);
      }

      this.delegate.visit(path, target);

      for(VisitorListener l : this.listeners) {
         l.afterVisit(this.delegate, path, target);
      }

   }

   public Boolean canVisit(List path, Object target) {
      boolean canVisit = this.delegate.canVisit(path, target);

      for(VisitorListener l : this.listeners) {
         l.onCheck(this.delegate, canVisit, target);
      }

      return canVisit;
   }
}
