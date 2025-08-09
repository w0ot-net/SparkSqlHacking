package jakarta.validation.metadata;

import java.lang.annotation.ElementType;
import java.util.Set;

public interface ElementDescriptor {
   boolean hasConstraints();

   Class getElementClass();

   Set getConstraintDescriptors();

   ConstraintFinder findConstraints();

   public interface ConstraintFinder {
      ConstraintFinder unorderedAndMatchingGroups(Class... var1);

      ConstraintFinder lookingAt(Scope var1);

      ConstraintFinder declaredOn(ElementType... var1);

      Set getConstraintDescriptors();

      boolean hasConstraints();
   }
}
