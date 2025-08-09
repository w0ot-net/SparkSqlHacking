package jakarta.validation;

import java.util.List;

public interface Path extends Iterable {
   String toString();

   public interface BeanNode extends Node {
      Class getContainerClass();

      Integer getTypeArgumentIndex();
   }

   public interface ConstructorNode extends Node {
      List getParameterTypes();
   }

   public interface ContainerElementNode extends Node {
      Class getContainerClass();

      Integer getTypeArgumentIndex();
   }

   public interface CrossParameterNode extends Node {
   }

   public interface MethodNode extends Node {
      List getParameterTypes();
   }

   public interface Node {
      String getName();

      boolean isInIterable();

      Integer getIndex();

      Object getKey();

      ElementKind getKind();

      Node as(Class var1);

      String toString();
   }

   public interface ParameterNode extends Node {
      int getParameterIndex();
   }

   public interface PropertyNode extends Node {
      Class getContainerClass();

      Integer getTypeArgumentIndex();
   }

   public interface ReturnValueNode extends Node {
   }
}
