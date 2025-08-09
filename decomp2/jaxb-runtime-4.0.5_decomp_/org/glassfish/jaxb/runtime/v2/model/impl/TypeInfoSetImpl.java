package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlRegistry;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.BuiltinLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.LeafInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.Ref;
import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.RuntimeUtil;
import org.glassfish.jaxb.core.v2.util.FlattenIterator;

class TypeInfoSetImpl implements TypeInfoSet {
   @XmlTransient
   public final Navigator nav;
   @XmlTransient
   public final AnnotationReader reader;
   private final Map builtins = new LinkedHashMap();
   private final Map enums = new LinkedHashMap();
   private final Map arrays = new LinkedHashMap();
   @XmlJavaTypeAdapter(RuntimeUtil.ToStringAdapter.class)
   private final Map beans = new LinkedHashMap();
   @XmlTransient
   private final Map beansView;
   private final Map elementMappings;
   private final Iterable allElements;
   private final NonElement anyType;
   private Map xmlNsCache;

   public TypeInfoSetImpl(Navigator nav, AnnotationReader reader, Map leaves) {
      this.beansView = Collections.unmodifiableMap(this.beans);
      this.elementMappings = new LinkedHashMap();
      this.allElements = new Iterable() {
         public Iterator iterator() {
            return new FlattenIterator(TypeInfoSetImpl.this.elementMappings.values());
         }
      };
      this.nav = nav;
      this.reader = reader;
      this.builtins.putAll(leaves);
      this.anyType = this.createAnyType();

      for(Map.Entry e : RuntimeUtil.primitiveToBox.entrySet()) {
         this.builtins.put(nav.getPrimitive((Class)e.getKey()), (BuiltinLeafInfo)leaves.get(nav.ref((Class)e.getValue())));
      }

      this.elementMappings.put((Object)null, new LinkedHashMap());
   }

   protected NonElement createAnyType() {
      return new AnyTypeImpl(this.nav);
   }

   public Navigator getNavigator() {
      return this.nav;
   }

   public void add(ClassInfoImpl ci) {
      this.beans.put(ci.getClazz(), ci);
   }

   public void add(EnumLeafInfoImpl li) {
      this.enums.put(li.clazz, li);
   }

   public void add(ArrayInfoImpl ai) {
      this.arrays.put(ai.getType(), ai);
   }

   public NonElement getTypeInfo(Object type) {
      type = (T)this.nav.erasure(type);
      LeafInfo<T, C> l = (LeafInfo)this.builtins.get(type);
      if (l != null) {
         return l;
      } else if (this.nav.isArray(type)) {
         return (NonElement)this.arrays.get(type);
      } else {
         C d = (C)this.nav.asDecl(type);
         return d == null ? null : this.getClassInfo(d);
      }
   }

   public NonElement getAnyTypeInfo() {
      return this.anyType;
   }

   public NonElement getTypeInfo(Ref ref) {
      assert !ref.valueList;

      C c = (C)this.nav.asDecl(ref.type);
      return c != null && this.reader.getClassAnnotation(XmlRegistry.class, c, (Locatable)null) != null ? null : this.getTypeInfo(ref.type);
   }

   public Map beans() {
      return this.beansView;
   }

   public Map builtins() {
      return this.builtins;
   }

   public Map enums() {
      return this.enums;
   }

   public Map arrays() {
      return this.arrays;
   }

   public NonElement getClassInfo(Object type) {
      LeafInfo<T, C> l = (LeafInfo)this.builtins.get(this.nav.use(type));
      if (l != null) {
         return l;
      } else {
         l = (LeafInfo)this.enums.get(type);
         if (l != null) {
            return l;
         } else {
            return this.nav.asDecl(Object.class).equals(type) ? this.anyType : (NonElement)this.beans.get(type);
         }
      }
   }

   public ElementInfoImpl getElementInfo(Object scope, QName name) {
      for(; scope != null; scope = (C)this.nav.getSuperClass(scope)) {
         Map<QName, ElementInfoImpl<T, C, F, M>> m = (Map)this.elementMappings.get(scope);
         if (m != null) {
            ElementInfoImpl<T, C, F, M> r = (ElementInfoImpl)m.get(name);
            if (r != null) {
               return r;
            }
         }
      }

      return (ElementInfoImpl)((Map)this.elementMappings.get((Object)null)).get(name);
   }

   public final void add(ElementInfoImpl ei, ModelBuilder builder) {
      C scope = (C)null;
      if (ei.getScope() != null) {
         scope = (C)ei.getScope().getClazz();
      }

      Map<QName, ElementInfoImpl<T, C, F, M>> m = (Map)this.elementMappings.computeIfAbsent(scope, (k) -> new LinkedHashMap());
      ElementInfoImpl<T, C, F, M> existing = (ElementInfoImpl)m.put(ei.getElementName(), ei);
      if (existing != null) {
         QName en = ei.getElementName();
         builder.reportError(new IllegalAnnotationException(Messages.CONFLICTING_XML_ELEMENT_MAPPING.format(en.getNamespaceURI(), en.getLocalPart()), ei, existing));
      }

   }

   public Map getElementMappings(Object scope) {
      return (Map)this.elementMappings.get(scope);
   }

   public Iterable getAllElements() {
      return this.allElements;
   }

   public Map getXmlNs(String namespaceUri) {
      if (this.xmlNsCache == null) {
         this.xmlNsCache = new HashMap();

         for(ClassInfoImpl ci : this.beans().values()) {
            XmlSchema xs = (XmlSchema)this.reader.getPackageAnnotation(XmlSchema.class, ci.getClazz(), (Locatable)null);
            if (xs != null) {
               String uri = xs.namespace();
               Map<String, String> m = (Map)this.xmlNsCache.computeIfAbsent(uri, (k) -> new HashMap());

               for(XmlNs xns : xs.xmlns()) {
                  m.put(xns.prefix(), xns.namespaceURI());
               }
            }
         }
      }

      Map<String, String> r = (Map)this.xmlNsCache.get(namespaceUri);
      return r != null ? r : Collections.emptyMap();
   }

   public Map getSchemaLocations() {
      Map<String, String> r = new HashMap();

      for(ClassInfoImpl ci : this.beans().values()) {
         XmlSchema xs = (XmlSchema)this.reader.getPackageAnnotation(XmlSchema.class, ci.getClazz(), (Locatable)null);
         if (xs != null) {
            String loc = xs.location();
            if (!loc.equals("##generate")) {
               r.put(xs.namespace(), loc);
            }
         }
      }

      return r;
   }

   public final XmlNsForm getElementFormDefault(String nsUri) {
      for(ClassInfoImpl ci : this.beans().values()) {
         XmlSchema xs = (XmlSchema)this.reader.getPackageAnnotation(XmlSchema.class, ci.getClazz(), (Locatable)null);
         if (xs != null && xs.namespace().equals(nsUri)) {
            XmlNsForm xnf = xs.elementFormDefault();
            if (xnf != XmlNsForm.UNSET) {
               return xnf;
            }
         }
      }

      return XmlNsForm.UNSET;
   }

   public final XmlNsForm getAttributeFormDefault(String nsUri) {
      for(ClassInfoImpl ci : this.beans().values()) {
         XmlSchema xs = (XmlSchema)this.reader.getPackageAnnotation(XmlSchema.class, ci.getClazz(), (Locatable)null);
         if (xs != null && xs.namespace().equals(nsUri)) {
            XmlNsForm xnf = xs.attributeFormDefault();
            if (xnf != XmlNsForm.UNSET) {
               return xnf;
            }
         }
      }

      return XmlNsForm.UNSET;
   }

   public void dump(Result out) throws JAXBException {
      JAXBContext context = JAXBContext.newInstance(new Class[]{this.getClass()});
      Marshaller m = context.createMarshaller();
      m.marshal(this, out);
   }
}
