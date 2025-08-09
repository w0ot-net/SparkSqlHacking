package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.ArrayInfo;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.ErrorHandler;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.Ref;
import org.glassfish.jaxb.core.v2.model.core.RegistryInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;
import org.glassfish.jaxb.core.v2.model.impl.ModelBuilderI;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.runtime.v2.model.annotation.ClassLocatable;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;

public class ModelBuilder implements ModelBuilderI {
   private static final Logger logger = Logger.getLogger(ModelBuilder.class.getName());
   final TypeInfoSetImpl typeInfoSet;
   public final AnnotationReader reader;
   public final Navigator nav;
   private final Map typeNames = new HashMap();
   public final String defaultNsUri;
   final Map registries = new HashMap();
   private final Map subclassReplacements;
   private ErrorHandler errorHandler;
   private boolean hadError;
   public boolean hasSwaRef;
   private final ErrorHandler proxyErrorHandler = new ErrorHandler() {
      public void error(IllegalAnnotationException e) {
         ModelBuilder.this.reportError(e);
      }
   };
   private boolean linked;

   public ModelBuilder(AnnotationReader reader, Navigator navigator, Map subclassReplacements, String defaultNamespaceRemap) {
      this.reader = reader;
      this.nav = navigator;
      this.subclassReplacements = subclassReplacements;
      if (defaultNamespaceRemap == null) {
         defaultNamespaceRemap = "";
      }

      this.defaultNsUri = defaultNamespaceRemap;
      reader.setErrorHandler(this.proxyErrorHandler);
      this.typeInfoSet = (TypeInfoSetImpl)this.createTypeInfoSet();
   }

   protected TypeInfoSet createTypeInfoSet() {
      return new TypeInfoSetImpl(this.nav, this.reader, BuiltinLeafInfoImpl.createLeaves(this.nav));
   }

   public NonElement getClassInfo(Object clazz, Locatable upstream) {
      return this.getClassInfo(clazz, false, upstream);
   }

   public NonElement getClassInfo(Object clazz, boolean searchForSuperClass, Locatable upstream) {
      assert clazz != null;

      NonElement<T, C> r = this.typeInfoSet.getClassInfo(clazz);
      if (r != null) {
         return r;
      } else {
         if (this.nav.isEnum(clazz)) {
            EnumLeafInfoImpl<T, C, F, M> li = (EnumLeafInfoImpl)this.createEnumLeafInfo(clazz, upstream);
            this.typeInfoSet.add(li);
            r = li;
            this.addTypeName(li);
         } else {
            boolean isReplaced = this.subclassReplacements.containsKey(clazz);
            if (isReplaced && !searchForSuperClass) {
               r = this.getClassInfo(this.subclassReplacements.get(clazz), upstream);
            } else if (!this.reader.hasClassAnnotation(clazz, XmlTransient.class) && !isReplaced) {
               ClassInfoImpl<T, C, F, M> ci = (ClassInfoImpl)this.createClassInfo(clazz, upstream);
               this.typeInfoSet.add(ci);

               for(PropertyInfo p : ci.getProperties()) {
                  if (p.kind() == PropertyKind.REFERENCE) {
                     this.addToRegistry(clazz, (Locatable)p);
                     Class[] prmzdClasses = this.getParametrizedTypes(p);
                     if (prmzdClasses != null) {
                        for(Class prmzdClass : prmzdClasses) {
                           if (prmzdClass != clazz) {
                              this.addToRegistry(prmzdClass, (Locatable)p);
                           }
                        }
                     }
                  }

                  for(TypeInfo var22 : p.ref()) {
                     ;
                  }
               }

               ci.getBaseClass();
               r = ci;
               this.addTypeName(ci);
            } else {
               r = this.getClassInfo(this.nav.getSuperClass(clazz), searchForSuperClass, new ClassLocatable(upstream, clazz, this.nav));
            }
         }

         XmlSeeAlso sa = (XmlSeeAlso)this.reader.getClassAnnotation(XmlSeeAlso.class, clazz, upstream);
         if (sa != null) {
            for(Object t : this.reader.getClassArrayValue(sa, "value")) {
               this.getTypeInfo(t, (Locatable)sa);
            }
         }

         return r;
      }
   }

   private void addToRegistry(Object clazz, Locatable p) {
      String pkg = this.nav.getPackageName(clazz);
      if (!this.registries.containsKey(pkg)) {
         C c = (C)this.nav.loadObjectFactory(clazz, pkg);
         if (c != null) {
            this.addRegistry(c, p);
         }
      }

   }

   private Class[] getParametrizedTypes(PropertyInfo p) {
      try {
         Type pType = ((RuntimePropertyInfo)p).getIndividualType();
         if (pType instanceof ParameterizedType) {
            ParameterizedType prmzdType = (ParameterizedType)pType;
            if (prmzdType.getRawType() == JAXBElement.class) {
               Type[] actualTypes = prmzdType.getActualTypeArguments();
               Class[] result = new Class[actualTypes.length];

               for(int i = 0; i < actualTypes.length; ++i) {
                  result[i] = (Class)actualTypes[i];
               }

               return result;
            }
         }
      } catch (Exception e) {
         logger.log(Level.FINE, "Error in ModelBuilder.getParametrizedTypes. " + e.getMessage());
      }

      return null;
   }

   private void addTypeName(NonElement r) {
      QName t = r.getTypeName();
      if (t != null) {
         TypeInfo old = (TypeInfo)this.typeNames.put(t, r);
         if (old != null) {
            this.reportError(new IllegalAnnotationException(Messages.CONFLICTING_XML_TYPE_MAPPING.format(r.getTypeName()), old, r));
         }

      }
   }

   public NonElement getTypeInfo(Object t, Locatable upstream) {
      NonElement<T, C> r = this.typeInfoSet.getTypeInfo(t);
      if (r != null) {
         return r;
      } else if (this.nav.isArray(t)) {
         ArrayInfoImpl<T, C, F, M> ai = (ArrayInfoImpl)this.createArrayInfo(upstream, t);
         this.addTypeName(ai);
         this.typeInfoSet.add(ai);
         return ai;
      } else {
         C c = (C)this.nav.asDecl(t);

         assert c != null : t.toString() + " must be a leaf, but we failed to recognize it.";

         return this.getClassInfo(c, upstream);
      }
   }

   public NonElement getTypeInfo(Ref ref) {
      assert !ref.valueList;

      C c = (C)this.nav.asDecl(ref.type);
      if (c != null && this.reader.getClassAnnotation(XmlRegistry.class, c, (Locatable)null) != null) {
         if (!this.registries.containsKey(this.nav.getPackageName(c))) {
            this.addRegistry(c, (Locatable)null);
         }

         return null;
      } else {
         return this.getTypeInfo(ref.type, (Locatable)null);
      }
   }

   protected EnumLeafInfo createEnumLeafInfo(Object clazz, Locatable upstream) {
      return new EnumLeafInfoImpl(this, upstream, clazz, this.nav.use(clazz));
   }

   protected ClassInfo createClassInfo(Object clazz, Locatable upstream) {
      return new ClassInfoImpl(this, upstream, clazz);
   }

   protected ElementInfo createElementInfo(RegistryInfo registryInfo, Object m) throws IllegalAnnotationException {
      return new ElementInfoImpl(this, (RegistryInfoImpl)registryInfo, m);
   }

   protected ArrayInfo createArrayInfo(Locatable upstream, Object arrayType) {
      return new ArrayInfoImpl(this, upstream, arrayType);
   }

   public RegistryInfo addRegistry(Object registryClass, Locatable upstream) {
      return new RegistryInfoImpl(this, upstream, registryClass);
   }

   public RegistryInfo getRegistry(String packageName) {
      return (RegistryInfo)this.registries.get(packageName);
   }

   public TypeInfoSet link() {
      assert !this.linked;

      this.linked = true;

      for(ElementInfoImpl ei : this.typeInfoSet.getAllElements()) {
         ei.link();
      }

      for(ClassInfoImpl ci : this.typeInfoSet.beans().values()) {
         ci.link();
      }

      for(EnumLeafInfoImpl li : this.typeInfoSet.enums().values()) {
         li.link();
      }

      return this.hadError ? null : this.typeInfoSet;
   }

   public void setErrorHandler(ErrorHandler errorHandler) {
      this.errorHandler = errorHandler;
   }

   public final void reportError(IllegalAnnotationException e) {
      this.hadError = true;
      if (this.errorHandler != null) {
         this.errorHandler.error(e);
      }

   }

   public boolean isReplaced(Object sc) {
      return this.subclassReplacements.containsKey(sc);
   }

   public Navigator getNavigator() {
      return this.nav;
   }

   public AnnotationReader getReader() {
      return this.reader;
   }
}
