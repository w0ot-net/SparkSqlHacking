package io.fabric8.kubernetes.api.builder;

import java.util.List;
import java.util.Map;

public class PathAwareTypedVisitor extends TypedVisitor {
   private final Class type;
   private final Class parentType;

   PathAwareTypedVisitor() {
      List<Class> args = Visitors.getTypeArguments(PathAwareTypedVisitor.class, this.getClass());
      if (args != null && !args.isEmpty()) {
         this.type = (Class)args.get(0);
         this.parentType = (Class)args.get(1);
      } else {
         throw new IllegalStateException("Could not determine type arguments for path aware typed visitor.");
      }
   }

   public void visit(Object element) {
   }

   public void visit(List path, Object element) {
      this.visit(element);
   }

   public Object getParent(List path) {
      return path.size() - 1 >= 0 ? path.get(path.size() - 1) : null;
   }

   public Class getParentType() {
      return this.parentType;
   }
}
