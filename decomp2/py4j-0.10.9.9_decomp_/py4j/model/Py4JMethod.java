package py4j.model;

import java.lang.reflect.Method;
import java.util.List;
import py4j.reflection.TypeUtil;

public class Py4JMethod extends Py4JMember {
   private final List parameterTypes;
   private final List parameterNames;
   private final String returnType;
   private final String container;

   public static final Py4JMethod buildMethod(Method method) {
      return new Py4JMethod(method.getName(), (String)null, TypeUtil.getNames(method.getParameterTypes()), (List)null, method.getReturnType().getCanonicalName(), method.getDeclaringClass().getCanonicalName());
   }

   public Py4JMethod(String name, String javadoc, List parameterTypes, List parameterNames, String returnType, String container) {
      super(name, javadoc);
      this.parameterNames = parameterNames;
      this.parameterTypes = parameterTypes;
      this.returnType = returnType;
      this.container = container;
   }

   public String getContainer() {
      return this.container;
   }

   public List getParameterNames() {
      return this.parameterNames;
   }

   public List getParameterTypes() {
      return this.parameterTypes;
   }

   public String getReturnType() {
      return this.returnType;
   }

   public String getSignature(boolean shortName) {
      StringBuilder builder = new StringBuilder();
      int length = this.parameterTypes.size();
      builder.append(this.getName());
      builder.append('(');

      for(int i = 0; i < length - 1; ++i) {
         builder.append(TypeUtil.getName((String)this.parameterTypes.get(i), shortName));
         builder.append(", ");
      }

      if (length > 0) {
         builder.append(TypeUtil.getName((String)this.parameterTypes.get(length - 1), shortName));
      }

      builder.append(") : ");
      builder.append(TypeUtil.getName(this.returnType, shortName));
      return builder.toString();
   }
}
