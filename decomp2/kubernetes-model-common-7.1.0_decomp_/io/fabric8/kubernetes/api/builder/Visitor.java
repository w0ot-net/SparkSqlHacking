package io.fabric8.kubernetes.api.builder;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@FunctionalInterface
public interface Visitor {
   default Class getType() {
      List<Class> args = Visitors.getTypeArguments(Visitor.class, this.getClass());
      return args != null && !args.isEmpty() ? (Class)args.get(0) : null;
   }

   void visit(Object var1);

   default int order() {
      return 0;
   }

   default void visit(List path, Object element) {
      this.visit(element);
   }

   default Boolean canVisit(List path, Object target) {
      if (target == null) {
         return false;
      } else if (this.getType() == null) {
         return this.hasVisitMethodMatching(target);
      } else if (!this.getType().isAssignableFrom(target.getClass())) {
         return false;
      } else {
         try {
            return this.getRequirement().test(path);
         } catch (ClassCastException var4) {
            return false;
         }
      }
   }

   default Boolean hasVisitMethodMatching(Object target) {
      for(Method method : this.getClass().getMethods()) {
         if (method.getName().equals("visit") && method.getParameterTypes().length == 1) {
            Class<?> visitorType = method.getParameterTypes()[0];
            if (visitorType.isAssignableFrom(target.getClass())) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   default Predicate getRequirement() {
      return (p) -> true;
   }

   default Predicate hasItem(Class type, Predicate predicate) {
      Predicate<List<Map.Entry<String, Object>>> result = (l) -> l.stream().map(Map.Entry::getValue).filter((i) -> type.isInstance(i)).map((i) -> type.cast(i)).anyMatch(predicate);
      return result;
   }

   default Visitor addRequirement(Class type, Predicate predicate) {
      return this.addRequirement(predicate);
   }

   default Visitor addRequirement(final Predicate predicate) {
      return new DelegatingVisitor(this.getType(), this) {
         public Predicate getRequirement() {
            return Visitor.this.getRequirement().and(predicate);
         }
      };
   }
}
