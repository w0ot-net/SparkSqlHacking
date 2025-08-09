package py4j.reflection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MethodDescriptor {
   private String internalRepresentation;
   private String name;
   private Class container;
   private List parameters;
   private static final char DOT = '.';

   public MethodDescriptor(String name, Class container, Class[] parameters) {
      this.name = name;
      this.container = container;
      this.parameters = Collections.unmodifiableList(Arrays.asList(parameters));
      this.internalRepresentation = this.buildInternalRepresentation(container, name, this.parameters);
   }

   private String buildInternalRepresentation(Class container, String name, List params) {
      StringBuilder builder = new StringBuilder();
      builder.append(container.getName());
      builder.append('.');
      builder.append(name);
      builder.append('(');

      for(Class param : params) {
         String paramName = "null";
         if (param != null) {
            paramName = param.getName();
         }

         builder.append(paramName);
         builder.append('.');
      }

      builder.append(')');
      return builder.toString();
   }

   public boolean equals(Object obj) {
      return obj != null && obj instanceof MethodDescriptor ? this.internalRepresentation.equals(((MethodDescriptor)obj).internalRepresentation) : false;
   }

   public Class getContainer() {
      return this.container;
   }

   public String getInternalRepresentation() {
      return this.internalRepresentation;
   }

   public String getName() {
      return this.name;
   }

   public List getParameters() {
      return this.parameters;
   }

   public int hashCode() {
      return this.internalRepresentation.hashCode();
   }

   public String toString() {
      return this.internalRepresentation;
   }
}
