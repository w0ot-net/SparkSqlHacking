package py4j.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import py4j.reflection.TypeUtil;

public class Py4JClass extends Py4JMember {
   private final String extendType;
   private final List implementTypes;
   private final List methods;
   private final List fields;
   private final List classes;

   public static final Py4JClass buildClass(Class clazz) {
      return buildClass(clazz, true);
   }

   public static final Py4JClass buildClass(Class clazz, boolean sort) {
      List<Py4JClass> classes = new ArrayList();
      List<Py4JMethod> methods = new ArrayList();
      List<Py4JField> fields = new ArrayList();

      for(Class memberClass : clazz.getDeclaredClasses()) {
         if (Modifier.isPublic(memberClass.getModifiers())) {
            classes.add(buildClass(memberClass, sort));
         }
      }

      for(Method method : clazz.getDeclaredMethods()) {
         if (Modifier.isPublic(method.getModifiers())) {
            methods.add(Py4JMethod.buildMethod(method));
         }
      }

      for(Field field : clazz.getDeclaredFields()) {
         if (Modifier.isPublic(field.getModifiers())) {
            fields.add(Py4JField.buildField(field));
         }
      }

      Class<?> superClass = clazz.getSuperclass();
      String extend = null;
      if (superClass != null && superClass != Object.class) {
         extend = superClass.getCanonicalName();
      }

      Class<?>[] interfaces = clazz.getInterfaces();
      List<String> implementTypes = interfaces != null && interfaces.length > 0 ? TypeUtil.getNames(interfaces) : null;
      if (sort) {
         Collections.sort(classes);
         Collections.sort(methods);
         Collections.sort(fields);
      }

      return new Py4JClass(clazz.getCanonicalName(), (String)null, extend, implementTypes, Collections.unmodifiableList(methods), Collections.unmodifiableList(fields), Collections.unmodifiableList(classes));
   }

   public Py4JClass(String name, String javadoc, String extendType, List implementTypes, List methods, List fields, List classes) {
      super(name, javadoc);
      this.extendType = extendType;
      this.implementTypes = implementTypes;
      this.methods = methods;
      this.fields = fields;
      this.classes = classes;
   }

   public List getClasses() {
      return this.classes;
   }

   public String getExtendType() {
      return this.extendType;
   }

   public List getFields() {
      return this.fields;
   }

   public List getImplementTypes() {
      return this.implementTypes;
   }

   public List getMethods() {
      return this.methods;
   }

   public String getSignature(boolean shortName) {
      StringBuilder builder = new StringBuilder();
      builder.append(TypeUtil.getName(this.getName(), shortName));
      if (this.extendType != null) {
         builder.append(" extends ");
         builder.append(this.extendType);
      }

      if (this.implementTypes != null) {
         builder.append(" implements ");
         int length = this.implementTypes.size();

         for(int i = 0; i < length - 1; ++i) {
            builder.append((String)this.implementTypes.get(i));
            builder.append(", ");
         }

         builder.append((String)this.implementTypes.get(length - 1));
      }

      return builder.toString();
   }
}
