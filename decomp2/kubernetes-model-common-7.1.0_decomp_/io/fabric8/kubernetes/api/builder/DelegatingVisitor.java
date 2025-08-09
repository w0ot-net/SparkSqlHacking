package io.fabric8.kubernetes.api.builder;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DelegatingVisitor implements Visitor {
   private final Class type;
   private final Visitor delegate;

   DelegatingVisitor(Class type, Visitor delegate) {
      this.type = type;
      this.delegate = delegate;
   }

   public Class getType() {
      return this.type;
   }

   public void visit(Object target) {
      this.delegate.visit(target);
   }

   public int order() {
      return this.delegate.order();
   }

   public void visit(List path, Object target) {
      this.delegate.visit(path, target);
   }

   public Predicate getRequirement() {
      return this.delegate.getRequirement();
   }
}
