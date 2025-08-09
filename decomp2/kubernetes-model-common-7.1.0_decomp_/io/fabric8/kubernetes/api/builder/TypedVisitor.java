package io.fabric8.kubernetes.api.builder;

public abstract class TypedVisitor implements Visitor {
   public Class getType() {
      return (Class)Visitors.getTypeArguments(TypedVisitor.class, this.getClass()).get(0);
   }
}
