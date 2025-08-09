package org.sparkproject.jetty.plus.annotation;

import jakarta.servlet.ServletContainerInitializer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.webapp.WebAppContext;

/** @deprecated */
@Deprecated
public class ContainerInitializer {
   private static final Logger LOG = LoggerFactory.getLogger(ContainerInitializer.class);
   protected final ServletContainerInitializer _target;
   protected final Class[] _interestedTypes;
   protected final Set _applicableTypeNames = ConcurrentHashMap.newKeySet();
   protected final Set _annotatedTypeNames = ConcurrentHashMap.newKeySet();

   public ContainerInitializer(ServletContainerInitializer target, Class[] classes) {
      this._target = target;
      this._interestedTypes = classes;
   }

   public ContainerInitializer(ClassLoader loader, String toString) {
      Matcher m = Pattern.compile("ContainerInitializer\\{(.*),interested=(.*),applicable=(.*),annotated=(.*)\\}").matcher(toString);
      if (!m.matches()) {
         throw new IllegalArgumentException(toString);
      } else {
         try {
            this._target = (ServletContainerInitializer)loader.loadClass(m.group(1)).getDeclaredConstructor().newInstance();
            String[] interested = StringUtil.arrayFromString(m.group(2));
            this._interestedTypes = new Class[interested.length];

            for(int i = 0; i < interested.length; ++i) {
               this._interestedTypes[i] = loader.loadClass(interested[i]);
            }

            for(String s : StringUtil.arrayFromString(m.group(3))) {
               this._applicableTypeNames.add(s);
            }

            for(String s : StringUtil.arrayFromString(m.group(4))) {
               this._annotatedTypeNames.add(s);
            }

         } catch (Exception e) {
            throw new IllegalArgumentException(toString, e);
         }
      }
   }

   public ServletContainerInitializer getTarget() {
      return this._target;
   }

   public Class[] getInterestedTypes() {
      return this._interestedTypes;
   }

   public void addAnnotatedTypeName(String className) {
      this._annotatedTypeNames.add(className);
   }

   public Set getAnnotatedTypeNames() {
      return Collections.unmodifiableSet(this._annotatedTypeNames);
   }

   public void addApplicableTypeName(String className) {
      this._applicableTypeNames.add(className);
   }

   public Set getApplicableTypeNames() {
      return Collections.unmodifiableSet(this._applicableTypeNames);
   }

   public void callStartup(WebAppContext context) throws Exception {
      if (this._target != null) {
         Set<Class<?>> classes = new HashSet();

         try {
            for(String s : this._applicableTypeNames) {
               classes.add(Loader.loadClass(s));
            }

            context.getServletContext().setExtendedListenerTypes(true);
            if (LOG.isDebugEnabled()) {
               long start = NanoTime.now();
               this._target.onStartup(classes, context.getServletContext());
               LOG.debug("ContainerInitializer {} called in {}ms", this._target.getClass().getName(), NanoTime.millisSince(start));
            } else {
               this._target.onStartup(classes, context.getServletContext());
            }
         } finally {
            context.getServletContext().setExtendedListenerTypes(false);
         }
      }

   }

   public String toString() {
      List<String> interested = Collections.emptyList();
      if (this._interestedTypes != null) {
         interested = new ArrayList(this._interestedTypes.length);

         for(Class c : this._interestedTypes) {
            interested.add(c.getName());
         }
      }

      return String.format("ContainerInitializer{%s,interested=%s,applicable=%s,annotated=%s}", this._target.getClass().getName(), interested, this._applicableTypeNames, this._annotatedTypeNames);
   }

   public void resolveClasses(WebAppContext context, Map classMap) {
      Set<String> annotatedClassNames = this.getAnnotatedTypeNames();
      if (annotatedClassNames != null && !annotatedClassNames.isEmpty()) {
         for(String name : annotatedClassNames) {
            this.addApplicableTypeName(name);
            this.addInheritedTypes(classMap, (Set)classMap.get(name));
         }
      }

      if (this.getInterestedTypes() != null) {
         for(Class c : this.getInterestedTypes()) {
            if (!c.isAnnotation()) {
               this.addInheritedTypes(classMap, (Set)classMap.get(c.getName()));
            }
         }
      }

   }

   private void addInheritedTypes(Map classMap, Set names) {
      if (names != null && !names.isEmpty()) {
         for(String s : names) {
            this.addApplicableTypeName(s);
            this.addInheritedTypes(classMap, (Set)classMap.get(s));
         }

      }
   }
}
