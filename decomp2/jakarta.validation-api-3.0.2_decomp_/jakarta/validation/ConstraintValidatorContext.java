package jakarta.validation;

public interface ConstraintValidatorContext {
   void disableDefaultConstraintViolation();

   String getDefaultConstraintMessageTemplate();

   ClockProvider getClockProvider();

   ConstraintViolationBuilder buildConstraintViolationWithTemplate(String var1);

   Object unwrap(Class var1);

   public interface ConstraintViolationBuilder {
      /** @deprecated */
      NodeBuilderDefinedContext addNode(String var1);

      NodeBuilderCustomizableContext addPropertyNode(String var1);

      LeafNodeBuilderCustomizableContext addBeanNode();

      ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

      NodeBuilderDefinedContext addParameterNode(int var1);

      ConstraintValidatorContext addConstraintViolation();

      public interface ContainerElementNodeBuilderCustomizableContext {
         ContainerElementNodeContextBuilder inIterable();

         NodeBuilderCustomizableContext addPropertyNode(String var1);

         LeafNodeBuilderCustomizableContext addBeanNode();

         ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface ContainerElementNodeBuilderDefinedContext {
         NodeBuilderCustomizableContext addPropertyNode(String var1);

         LeafNodeBuilderCustomizableContext addBeanNode();

         ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface ContainerElementNodeContextBuilder {
         ContainerElementNodeBuilderDefinedContext atKey(Object var1);

         ContainerElementNodeBuilderDefinedContext atIndex(Integer var1);

         NodeBuilderCustomizableContext addPropertyNode(String var1);

         LeafNodeBuilderCustomizableContext addBeanNode();

         ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface LeafNodeBuilderCustomizableContext {
         LeafNodeContextBuilder inIterable();

         LeafNodeBuilderCustomizableContext inContainer(Class var1, Integer var2);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface LeafNodeBuilderDefinedContext {
         ConstraintValidatorContext addConstraintViolation();
      }

      public interface LeafNodeContextBuilder {
         LeafNodeBuilderDefinedContext atKey(Object var1);

         LeafNodeBuilderDefinedContext atIndex(Integer var1);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface NodeBuilderCustomizableContext {
         NodeContextBuilder inIterable();

         NodeBuilderCustomizableContext inContainer(Class var1, Integer var2);

         /** @deprecated */
         NodeBuilderCustomizableContext addNode(String var1);

         NodeBuilderCustomizableContext addPropertyNode(String var1);

         LeafNodeBuilderCustomizableContext addBeanNode();

         ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface NodeBuilderDefinedContext {
         /** @deprecated */
         NodeBuilderCustomizableContext addNode(String var1);

         NodeBuilderCustomizableContext addPropertyNode(String var1);

         LeafNodeBuilderCustomizableContext addBeanNode();

         ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

         ConstraintValidatorContext addConstraintViolation();
      }

      public interface NodeContextBuilder {
         NodeBuilderDefinedContext atKey(Object var1);

         NodeBuilderDefinedContext atIndex(Integer var1);

         /** @deprecated */
         NodeBuilderCustomizableContext addNode(String var1);

         NodeBuilderCustomizableContext addPropertyNode(String var1);

         LeafNodeBuilderCustomizableContext addBeanNode();

         ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String var1, Class var2, Integer var3);

         ConstraintValidatorContext addConstraintViolation();
      }
   }
}
