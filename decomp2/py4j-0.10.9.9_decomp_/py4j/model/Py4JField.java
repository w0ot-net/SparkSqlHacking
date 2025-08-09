package py4j.model;

import java.lang.reflect.Field;
import py4j.reflection.TypeUtil;

public class Py4JField extends Py4JMember {
   private final String type;
   private final String container;

   public static final Py4JField buildField(Field field) {
      return new Py4JField(field.getName(), (String)null, field.getType().getCanonicalName(), field.getDeclaringClass().getCanonicalName());
   }

   public Py4JField(String name, String javadoc, String type, String container) {
      super(name, javadoc);
      this.type = type;
      this.container = container;
   }

   public String getContainer() {
      return this.container;
   }

   public String getSignature(boolean shortName) {
      StringBuilder builder = new StringBuilder();
      builder.append(this.getName());
      builder.append(" : ");
      builder.append(TypeUtil.getName(this.type, shortName));
      return builder.toString();
   }

   public String getType() {
      return this.type;
   }
}
