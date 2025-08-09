package org.sparkproject.jetty.plus.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Objects;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IntrospectionUtil;

public class Injection {
   private static final Logger LOG = LoggerFactory.getLogger(Injection.class);
   private final Class _targetClass;
   private final String _jndiName;
   private final String _mappingName;
   private final Member _target;
   private final Class _paramClass;
   private final Class _resourceClass;

   public Injection(Class clazz, Field field, Class resourceType, String jndiName, String mappingName) {
      this._targetClass = (Class)Objects.requireNonNull(clazz);
      this._target = (Member)Objects.requireNonNull(field);
      this._resourceClass = resourceType;
      this._paramClass = null;
      this._jndiName = jndiName;
      this._mappingName = mappingName;
   }

   public Injection(Class clazz, Method method, Class arg, Class resourceType, String jndiName, String mappingName) {
      this._targetClass = (Class)Objects.requireNonNull(clazz);
      this._target = (Member)Objects.requireNonNull(method);
      this._resourceClass = resourceType;
      this._paramClass = arg;
      this._jndiName = jndiName;
      this._mappingName = mappingName;
   }

   public Injection(Class clazz, String target, Class resourceType, String jndiName, String mappingName) {
      this._targetClass = (Class)Objects.requireNonNull(clazz);
      Objects.requireNonNull(target);
      this._resourceClass = resourceType;
      this._jndiName = jndiName;
      this._mappingName = mappingName;
      Member tmpTarget = null;
      Class<?> tmpParamClass = null;
      String var10000 = target.substring(0, 1).toUpperCase(Locale.ENGLISH);
      String setter = "set" + var10000 + target.substring(1);

      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Looking for method for setter: {} with arg {}", setter, this._resourceClass);
         }

         var13 = IntrospectionUtil.findMethod(clazz, setter, new Class[]{this._resourceClass}, true, false);
         tmpParamClass = this._resourceClass;
      } catch (NoSuchMethodException nsme) {
         try {
            var13 = IntrospectionUtil.findField(clazz, target, resourceType, true, false);
            tmpParamClass = null;
         } catch (NoSuchFieldException nsfe) {
            nsme.addSuppressed(nsfe);
            throw new IllegalArgumentException("No such field or method " + target + " on class " + String.valueOf(this._targetClass), nsme);
         }
      }

      this._target = (Member)var13;
      this._paramClass = tmpParamClass;
   }

   public Class getTargetClass() {
      return this._targetClass;
   }

   public Class getParamClass() {
      return this._paramClass;
   }

   public Class getResourceClass() {
      return this._resourceClass;
   }

   public boolean isField() {
      return Field.class.isInstance(this._target);
   }

   public boolean isMethod() {
      return Method.class.isInstance(this._target);
   }

   public String getJndiName() {
      return this._jndiName;
   }

   public String getMappingName() {
      return this._mappingName;
   }

   public Member getTarget() {
      return this._target;
   }

   public void inject(Object injectable) {
      if (this.isField()) {
         this.injectField((Field)this._target, injectable);
      } else {
         if (!this.isMethod()) {
            throw new IllegalStateException("Neither field nor method injection");
         }

         this.injectMethod((Method)this._target, injectable);
      }

   }

   public Object lookupInjectedValue() throws NamingException {
      InitialContext context = new InitialContext();
      return context.lookup("java:comp/env/" + this.getJndiName());
   }

   protected void injectField(Field field, Object injectable) {
      try {
         boolean accessibility = field.isAccessible();
         field.setAccessible(true);
         field.set(injectable, this.lookupInjectedValue());
         field.setAccessible(accessibility);
      } catch (Exception e) {
         LOG.warn("Unable to inject field {} with {}", new Object[]{field, injectable, e});
         throw new IllegalStateException("Inject failed for field " + field.getName());
      }
   }

   protected void injectMethod(Method method, Object injectable) {
      try {
         boolean accessibility = method.isAccessible();
         method.setAccessible(true);
         method.invoke(injectable, this.lookupInjectedValue());
         method.setAccessible(accessibility);
      } catch (Exception e) {
         LOG.warn("Unable to inject method {} with {}", new Object[]{method, injectable, e});
         throw new IllegalStateException("Inject failed for method " + method.getName());
      }
   }
}
