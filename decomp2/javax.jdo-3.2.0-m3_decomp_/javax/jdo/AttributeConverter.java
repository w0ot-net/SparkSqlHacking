package javax.jdo;

public interface AttributeConverter {
   Object convertToDatastore(Object var1);

   Object convertToAttribute(Object var1);

   public static class UseDefault implements AttributeConverter {
      public Object convertToDatastore(Object attributeValue) {
         throw new JDOUserException("This converter is not usable.");
      }

      public Object convertToAttribute(Object datastoreValue) {
         throw new JDOUserException("This converter is not usable.");
      }
   }
}
