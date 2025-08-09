package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.ReferencePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.WildcardMode;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;

class ReferencePropertyInfoImpl extends ERPropertyInfoImpl implements ReferencePropertyInfo, DummyPropertyInfo {
   private Set types;
   private Set subTypes = new LinkedHashSet();
   private final boolean isMixed;
   private final WildcardMode wildcard;
   private final Object domHandler;
   private Boolean isRequired;
   private static boolean is2_2 = true;

   public ReferencePropertyInfoImpl(ClassInfoImpl classInfo, PropertySeed seed) {
      super(classInfo, seed);
      this.isMixed = seed.readAnnotation(XmlMixed.class) != null;
      XmlAnyElement xae = (XmlAnyElement)seed.readAnnotation(XmlAnyElement.class);
      if (xae == null) {
         this.wildcard = null;
         this.domHandler = null;
      } else {
         this.wildcard = xae.lax() ? WildcardMode.LAX : WildcardMode.SKIP;
         this.domHandler = this.nav().asDecl(this.reader().getClassValue(xae, "value"));
      }

   }

   public Set ref() {
      return this.getElements();
   }

   public PropertyKind kind() {
      return PropertyKind.REFERENCE;
   }

   public Set getElements() {
      if (this.types == null) {
         this.calcTypes(false);
      }

      assert this.types != null;

      return this.types;
   }

   private void calcTypes(boolean last) {
      this.types = new LinkedHashSet();
      XmlElementRefs refs = (XmlElementRefs)this.seed.readAnnotation(XmlElementRefs.class);
      XmlElementRef ref = (XmlElementRef)this.seed.readAnnotation(XmlElementRef.class);
      if (refs != null && ref != null) {
         ModelBuilder var10000 = this.parent.builder;
         Messages var10003 = Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS;
         Object[] var10004 = new Object[3];
         String var10007 = this.nav().getClassName(this.parent.getClazz());
         var10004[0] = var10007 + "#" + this.seed.getName();
         var10004[1] = ref.annotationType().getName();
         var10004[2] = refs.annotationType().getName();
         var10000.reportError(new IllegalAnnotationException(var10003.format(var10004), ref, refs));
      }

      XmlElementRef[] ann;
      if (refs != null) {
         ann = refs.value();
      } else if (ref != null) {
         ann = new XmlElementRef[]{ref};
      } else {
         ann = null;
      }

      this.isRequired = !this.isCollection();
      if (ann != null) {
         Navigator<T, C, F, M> nav = this.nav();
         AnnotationReader<T, C, F, M> reader = this.reader();
         T defaultType = (T)nav.ref(XmlElementRef.DEFAULT.class);
         C je = (C)nav.asDecl(JAXBElement.class);

         for(XmlElementRef r : ann) {
            T type = (T)reader.getClassValue(r, "type");
            if (this.nav().isSameType(type, defaultType)) {
               type = (T)nav.erasure(this.getIndividualType());
            }

            boolean _yield;
            if (nav.getBaseClass(type, je) != null) {
               _yield = this.addGenericElement(r);
            } else {
               _yield = this.addAllSubtypes(type);
            }

            if (this.isRequired && !this.isRequired(r)) {
               this.isRequired = false;
            }

            if (last && !_yield) {
               if (this.nav().isSameType(type, nav.ref(JAXBElement.class))) {
                  this.parent.builder.reportError(new IllegalAnnotationException(Messages.NO_XML_ELEMENT_DECL.format(this.getEffectiveNamespaceFor(r), r.name()), this));
               } else {
                  this.parent.builder.reportError(new IllegalAnnotationException(Messages.INVALID_XML_ELEMENT_REF.format(type), this));
               }

               return;
            }
         }
      }

      for(ReferencePropertyInfoImpl info : this.subTypes) {
         PropertySeed<T, C, F, M> sd = info.seed;
         refs = (XmlElementRefs)sd.readAnnotation(XmlElementRefs.class);
         ref = (XmlElementRef)sd.readAnnotation(XmlElementRef.class);
         if (refs != null && ref != null) {
            ModelBuilder var31 = this.parent.builder;
            Messages var32 = Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS;
            Object[] var33 = new Object[3];
            String var34 = this.nav().getClassName(this.parent.getClazz());
            var33[0] = var34 + "#" + this.seed.getName();
            var33[1] = ref.annotationType().getName();
            var33[2] = refs.annotationType().getName();
            var31.reportError(new IllegalAnnotationException(var32.format(var33), ref, refs));
         }

         if (refs != null) {
            ann = refs.value();
         } else if (ref != null) {
            ann = new XmlElementRef[]{ref};
         } else {
            ann = null;
         }

         if (ann != null) {
            Navigator<T, C, F, M> nav = this.nav();
            AnnotationReader<T, C, F, M> reader = this.reader();
            T defaultType = (T)nav.ref(XmlElementRef.DEFAULT.class);
            C je = (C)nav.asDecl(JAXBElement.class);

            for(XmlElementRef r : ann) {
               T type = (T)reader.getClassValue(r, "type");
               if (this.nav().isSameType(type, defaultType)) {
                  type = (T)nav.erasure(this.getIndividualType());
               }

               boolean _yield;
               if (nav.getBaseClass(type, je) != null) {
                  _yield = this.addGenericElement(r, info);
               } else {
                  _yield = this.addAllSubtypes(type);
               }

               if (last && !_yield) {
                  if (this.nav().isSameType(type, nav.ref(JAXBElement.class))) {
                     this.parent.builder.reportError(new IllegalAnnotationException(Messages.NO_XML_ELEMENT_DECL.format(this.getEffectiveNamespaceFor(r), r.name()), this));
                  } else {
                     this.parent.builder.reportError(new IllegalAnnotationException(Messages.INVALID_XML_ELEMENT_REF.format(), this));
                  }

                  return;
               }
            }
         }
      }

      this.types = Collections.unmodifiableSet(this.types);
   }

   public boolean isRequired() {
      if (this.isRequired == null) {
         this.calcTypes(false);
      }

      return this.isRequired;
   }

   private boolean isRequired(XmlElementRef ref) {
      if (!is2_2) {
         return true;
      } else {
         try {
            return ref.required();
         } catch (LinkageError var3) {
            is2_2 = false;
            return true;
         }
      }
   }

   private boolean addGenericElement(XmlElementRef r) {
      String nsUri = this.getEffectiveNamespaceFor(r);
      return this.addGenericElement((ElementInfo)this.parent.owner.getElementInfo(this.parent.getClazz(), new QName(nsUri, r.name())));
   }

   private boolean addGenericElement(XmlElementRef r, ReferencePropertyInfoImpl info) {
      String nsUri = info.getEffectiveNamespaceFor(r);
      ElementInfo ei = this.parent.owner.getElementInfo(info.parent.getClazz(), new QName(nsUri, r.name()));
      this.types.add(ei);
      return true;
   }

   private String getEffectiveNamespaceFor(XmlElementRef r) {
      String nsUri = r.namespace();
      XmlSchema xs = (XmlSchema)this.reader().getPackageAnnotation(XmlSchema.class, this.parent.getClazz(), this);
      if (xs != null && xs.attributeFormDefault() == XmlNsForm.QUALIFIED && nsUri.length() == 0) {
         nsUri = this.parent.builder.defaultNsUri;
      }

      return nsUri;
   }

   private boolean addGenericElement(ElementInfo ei) {
      if (ei == null) {
         return false;
      } else {
         this.types.add(ei);

         for(ElementInfo subst : ei.getSubstitutionMembers()) {
            this.addGenericElement(subst);
         }

         return true;
      }
   }

   private boolean addAllSubtypes(Object type) {
      Navigator<T, C, F, M> nav = this.nav();
      NonElement<T, C> t = this.parent.builder.getClassInfo(nav.asDecl(type), this);
      if (!(t instanceof ClassInfo)) {
         return false;
      } else {
         boolean result = false;
         ClassInfo<T, C> c = (ClassInfo)t;
         if (c.isElement()) {
            this.types.add(c.asElement());
            result = true;
         }

         for(ClassInfo ci : this.parent.owner.beans().values()) {
            if (ci.isElement() && nav.isSubClassOf(ci.getType(), type)) {
               this.types.add(ci.asElement());
               result = true;
            }
         }

         for(ElementInfo ei : this.parent.owner.getElementMappings((Object)null).values()) {
            if (nav.isSubClassOf(ei.getType(), type)) {
               this.types.add(ei);
               result = true;
            }
         }

         return result;
      }
   }

   protected void link() {
      super.link();
      this.calcTypes(true);
   }

   public void addType(PropertyInfo info) {
      this.subTypes.add((ReferencePropertyInfoImpl)info);
   }

   public final boolean isMixed() {
      return this.isMixed;
   }

   public final WildcardMode getWildcard() {
      return this.wildcard;
   }

   public final Object getDOMHandler() {
      return this.domHandler;
   }
}
