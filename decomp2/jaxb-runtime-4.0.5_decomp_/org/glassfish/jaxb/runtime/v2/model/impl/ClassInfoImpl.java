package org.glassfish.jaxb.runtime.v2.model.impl;

import com.sun.istack.FinalArrayList;
import jakarta.xml.bind.annotation.XmlAccessOrder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorOrder;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttachmentRef;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlInlineBinaryData;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.annotation.OverrideAnnotationOf;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.AttributePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.Element;
import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.MapPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.ReferencePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ValuePropertyInfo;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.Location;
import org.glassfish.jaxb.core.v2.util.EditDistance;
import org.glassfish.jaxb.runtime.v2.model.annotation.MethodLocatable;

public class ClassInfoImpl extends TypeInfoImpl implements ClassInfo, Element {
   protected final Object clazz;
   private final QName elementName;
   private final QName typeName;
   private FinalArrayList properties;
   private String[] propOrder;
   private ClassInfoImpl baseClass;
   private boolean baseClassComputed = false;
   private boolean hasSubClasses = false;
   protected PropertySeed attributeWildcard;
   private Object factoryMethod = null;
   private static final SecondaryAnnotation[] SECONDARY_ANNOTATIONS = ClassInfoImpl.SecondaryAnnotation.values();
   private static final Annotation[] EMPTY_ANNOTATIONS = new Annotation[0];
   private static final HashMap ANNOTATION_NUMBER_MAP = new HashMap();
   private static final String[] DEFAULT_ORDER;

   ClassInfoImpl(ModelBuilder builder, Locatable upstream, Object clazz) {
      super(builder, upstream);
      this.clazz = clazz;

      assert clazz != null;

      this.elementName = this.parseElementName(clazz);
      XmlType t = (XmlType)this.reader().getClassAnnotation(XmlType.class, clazz, this);
      this.typeName = this.parseTypeName(clazz, t);
      if (t != null) {
         String[] propOrder = t.propOrder();
         if (propOrder.length == 0) {
            this.propOrder = null;
         } else if (propOrder[0].length() == 0) {
            this.propOrder = DEFAULT_ORDER;
         } else {
            this.propOrder = propOrder;
         }
      } else {
         this.propOrder = DEFAULT_ORDER;
      }

      XmlAccessorOrder xao = (XmlAccessorOrder)this.reader().getPackageAnnotation(XmlAccessorOrder.class, clazz, this);
      if (xao != null && xao.value() == XmlAccessOrder.UNDEFINED) {
         this.propOrder = null;
      }

      xao = (XmlAccessorOrder)this.reader().getClassAnnotation(XmlAccessorOrder.class, clazz, this);
      if (xao != null && xao.value() == XmlAccessOrder.UNDEFINED) {
         this.propOrder = null;
      }

      if (this.nav().isInterface(clazz)) {
         builder.reportError(new IllegalAnnotationException(Messages.CANT_HANDLE_INTERFACE.format(this.nav().getClassName(clazz)), this));
      }

      if (!this.hasFactoryConstructor(t) && !this.nav().hasDefaultConstructor(clazz)) {
         if (this.nav().isInnerClass(clazz)) {
            builder.reportError(new IllegalAnnotationException(Messages.CANT_HANDLE_INNER_CLASS.format(this.nav().getClassName(clazz)), this));
         } else if (this.elementName != null) {
            builder.reportError(new IllegalAnnotationException(Messages.NO_DEFAULT_CONSTRUCTOR.format(this.nav().getClassName(clazz)), this));
         }
      }

   }

   public ClassInfoImpl getBaseClass() {
      if (!this.baseClassComputed) {
         C s = (C)this.nav().getSuperClass(this.clazz);
         if (s != null && s != this.nav().asDecl(Object.class)) {
            NonElement<T, C> b = this.builder.getClassInfo(s, true, this);
            if (b instanceof ClassInfoImpl) {
               this.baseClass = (ClassInfoImpl)b;
               this.baseClass.hasSubClasses = true;
            } else {
               this.baseClass = null;
            }
         } else {
            this.baseClass = null;
         }

         this.baseClassComputed = true;
      }

      return this.baseClass;
   }

   public final Element getSubstitutionHead() {
      ClassInfoImpl<T, C, F, M> c;
      for(c = this.getBaseClass(); c != null && !c.isElement(); c = c.getBaseClass()) {
      }

      return c;
   }

   public final Object getClazz() {
      return this.clazz;
   }

   /** @deprecated */
   @Deprecated
   public ClassInfoImpl getScope() {
      return null;
   }

   public final Object getType() {
      return this.nav().use(this.clazz);
   }

   public boolean canBeReferencedByIDREF() {
      for(PropertyInfo p : this.getProperties()) {
         if (p.id() == ID.ID) {
            return true;
         }
      }

      ClassInfoImpl<T, C, F, M> base = this.getBaseClass();
      if (base != null) {
         return base.canBeReferencedByIDREF();
      } else {
         return false;
      }
   }

   public final String getName() {
      return this.nav().getClassName(this.clazz);
   }

   public Annotation readAnnotation(Class a) {
      return this.reader().getClassAnnotation(a, this.clazz, this);
   }

   public Element asElement() {
      return this.isElement() ? this : null;
   }

   public List getProperties() {
      if (this.properties != null) {
         return this.properties;
      } else {
         XmlAccessType at = this.getAccessType();
         this.properties = new FinalArrayList();
         this.findFieldProperties(this.clazz, at);
         this.findGetterSetterProperties(at);
         if (this.propOrder != DEFAULT_ORDER && this.propOrder != null) {
            ClassInfoImpl<T, C, F, M>.PropertySorter sorter = new PropertySorter();

            for(PropertyInfoImpl p : this.properties) {
               sorter.checkedGet(p);
            }

            Collections.sort(this.properties, sorter);
            sorter.checkUnusedProperties();
         } else {
            XmlAccessOrder ao = this.getAccessorOrder();
            if (ao == XmlAccessOrder.ALPHABETICAL) {
               Collections.sort(this.properties);
            }
         }

         PropertyInfoImpl vp = null;
         PropertyInfoImpl ep = null;

         for(PropertyInfoImpl p : this.properties) {
            switch (p.kind()) {
               case ELEMENT:
               case REFERENCE:
               case MAP:
                  ep = p;
                  break;
               case VALUE:
                  if (vp != null) {
                     this.builder.reportError(new IllegalAnnotationException(Messages.MULTIPLE_VALUE_PROPERTY.format(), vp, p));
                  }

                  if (this.getBaseClass() != null) {
                     this.builder.reportError(new IllegalAnnotationException(Messages.XMLVALUE_IN_DERIVED_TYPE.format(), p));
                  }

                  vp = p;
               case ATTRIBUTE:
                  break;
               default:
                  assert false;
            }
         }

         if (ep != null && vp != null) {
            this.builder.reportError(new IllegalAnnotationException(Messages.ELEMENT_AND_VALUE_PROPERTY.format(), vp, ep));
         }

         return this.properties;
      }
   }

   private void findFieldProperties(Object c, XmlAccessType at) {
      C sc = (C)this.nav().getSuperClass(c);
      if (this.shouldRecurseSuperClass(sc)) {
         this.findFieldProperties(sc, at);
      }

      for(Object f : this.nav().getDeclaredFields(c)) {
         Annotation[] annotations = this.reader().getAllFieldAnnotations(f, this);
         boolean isDummy = this.reader().hasFieldAnnotation(OverrideAnnotationOf.class, f);
         if (this.nav().isTransient(f)) {
            if (hasJAXBAnnotation(annotations)) {
               this.builder.reportError(new IllegalAnnotationException(Messages.TRANSIENT_FIELD_NOT_BINDABLE.format(this.nav().getFieldName(f)), getSomeJAXBAnnotation(annotations)));
            }
         } else if (this.nav().isStaticField(f)) {
            if (hasJAXBAnnotation(annotations)) {
               this.addProperty(this.createFieldSeed(f), annotations, false);
            }
         } else {
            if (at == XmlAccessType.FIELD || at == XmlAccessType.PUBLIC_MEMBER && this.nav().isPublicField(f) || hasJAXBAnnotation(annotations)) {
               if (isDummy) {
                  ClassInfo<T, C> top;
                  for(top = this.getBaseClass(); top != null && top.getProperty("content") == null; top = top.getBaseClass()) {
                  }

                  DummyPropertyInfo prop = (DummyPropertyInfo)top.getProperty("content");
                  PropertySeed seed = this.createFieldSeed(f);
                  prop.addType(this.createReferenceProperty(seed));
               } else {
                  this.addProperty(this.createFieldSeed(f), annotations, false);
               }
            }

            this.checkFieldXmlLocation(f);
         }
      }

   }

   public final boolean hasValueProperty() {
      ClassInfoImpl<T, C, F, M> bc = this.getBaseClass();
      if (bc != null && bc.hasValueProperty()) {
         return true;
      } else {
         for(PropertyInfo p : this.getProperties()) {
            if (p instanceof ValuePropertyInfo) {
               return true;
            }
         }

         return false;
      }
   }

   public PropertyInfo getProperty(String name) {
      for(PropertyInfo p : this.getProperties()) {
         if (p.getName().equals(name)) {
            return p;
         }
      }

      return null;
   }

   protected void checkFieldXmlLocation(Object f) {
   }

   private Annotation getClassOrPackageAnnotation(Class type) {
      T t = (T)this.reader().getClassAnnotation(type, this.clazz, this);
      return t != null ? t : this.reader().getPackageAnnotation(type, this.clazz, this);
   }

   private XmlAccessType getAccessType() {
      XmlAccessorType xat = (XmlAccessorType)this.getClassOrPackageAnnotation(XmlAccessorType.class);
      return xat != null ? xat.value() : XmlAccessType.PUBLIC_MEMBER;
   }

   private XmlAccessOrder getAccessorOrder() {
      XmlAccessorOrder xao = (XmlAccessorOrder)this.getClassOrPackageAnnotation(XmlAccessorOrder.class);
      return xao != null ? xao.value() : XmlAccessOrder.UNDEFINED;
   }

   public boolean hasProperties() {
      return !this.properties.isEmpty();
   }

   @SafeVarargs
   private static Object pickOne(Object... args) {
      for(Object arg : args) {
         if (arg != null) {
            return arg;
         }
      }

      return null;
   }

   @SafeVarargs
   private static List makeSet(Object... args) {
      List<T> l = new FinalArrayList();

      for(Object arg : args) {
         if (arg != null) {
            l.add(arg);
         }
      }

      return l;
   }

   private void checkConflict(Annotation a, Annotation b) throws DuplicateException {
      assert b != null;

      if (a != null) {
         throw new DuplicateException(a, b);
      }
   }

   private void addProperty(PropertySeed seed, Annotation[] annotations, boolean dummy) {
      XmlTransient t = null;
      XmlAnyAttribute aa = null;
      XmlAttribute a = null;
      XmlValue v = null;
      XmlElement e1 = null;
      XmlElements e2 = null;
      XmlElementRef r1 = null;
      XmlElementRefs r2 = null;
      XmlAnyElement xae = null;
      XmlMixed mx = null;
      OverrideAnnotationOf ov = null;
      int secondaryAnnotations = 0;

      try {
         for(Annotation ann : annotations) {
            Integer index = (Integer)ANNOTATION_NUMBER_MAP.get(ann.annotationType());
            if (index != null) {
               switch (index) {
                  case 0:
                     this.checkConflict(t, ann);
                     t = (XmlTransient)ann;
                     break;
                  case 1:
                     this.checkConflict(aa, ann);
                     aa = (XmlAnyAttribute)ann;
                     break;
                  case 2:
                     this.checkConflict(a, ann);
                     a = (XmlAttribute)ann;
                     break;
                  case 3:
                     this.checkConflict(v, ann);
                     v = (XmlValue)ann;
                     break;
                  case 4:
                     this.checkConflict(e1, ann);
                     e1 = (XmlElement)ann;
                     break;
                  case 5:
                     this.checkConflict(e2, ann);
                     e2 = (XmlElements)ann;
                     break;
                  case 6:
                     this.checkConflict(r1, ann);
                     r1 = (XmlElementRef)ann;
                     break;
                  case 7:
                     this.checkConflict(r2, ann);
                     r2 = (XmlElementRefs)ann;
                     break;
                  case 8:
                     this.checkConflict(xae, ann);
                     xae = (XmlAnyElement)ann;
                     break;
                  case 9:
                     this.checkConflict(mx, ann);
                     mx = (XmlMixed)ann;
                     break;
                  case 10:
                     this.checkConflict(ov, ann);
                     ov = (OverrideAnnotationOf)ann;
                     break;
                  default:
                     secondaryAnnotations |= 1 << index - 20;
               }
            }
         }

         PropertyGroup group = null;
         int groupCount = 0;
         if (t != null) {
            group = ClassInfoImpl.PropertyGroup.TRANSIENT;
            ++groupCount;
         }

         if (aa != null) {
            group = ClassInfoImpl.PropertyGroup.ANY_ATTRIBUTE;
            ++groupCount;
         }

         if (a != null) {
            group = ClassInfoImpl.PropertyGroup.ATTRIBUTE;
            ++groupCount;
         }

         if (v != null) {
            group = ClassInfoImpl.PropertyGroup.VALUE;
            ++groupCount;
         }

         if (e1 != null || e2 != null) {
            group = ClassInfoImpl.PropertyGroup.ELEMENT;
            ++groupCount;
         }

         if (r1 != null || r2 != null || xae != null || mx != null || ov != null) {
            group = ClassInfoImpl.PropertyGroup.ELEMENT_REF;
            ++groupCount;
         }

         if (groupCount > 1) {
            List<Annotation> err = makeSet(t, aa, a, v, (Annotation)pickOne(e1, e2), (Annotation)pickOne(r1, r2, xae));
            throw new ConflictException(err);
         }

         if (group == null) {
            assert groupCount == 0;

            if (this.nav().isSubClassOf(seed.getRawType(), this.nav().ref(Map.class)) && !this.hasApplicableAdapter(seed, seed.getRawType())) {
               group = ClassInfoImpl.PropertyGroup.MAP;
            } else {
               group = ClassInfoImpl.PropertyGroup.ELEMENT;
            }
         } else if (group.equals(ClassInfoImpl.PropertyGroup.ELEMENT) && this.nav().isSubClassOf(seed.getRawType(), this.nav().ref(Map.class)) && !this.hasApplicableAdapter(seed, seed.getRawType())) {
            group = ClassInfoImpl.PropertyGroup.MAP;
         }

         if ((secondaryAnnotations & group.allowedsecondaryAnnotations) != 0) {
            for(SecondaryAnnotation sa : SECONDARY_ANNOTATIONS) {
               if (!group.allows(sa)) {
                  for(Class m : sa.members) {
                     Annotation offender = seed.readAnnotation(m);
                     if (offender != null) {
                        this.builder.reportError(new IllegalAnnotationException(Messages.ANNOTATION_NOT_ALLOWED.format(m.getSimpleName()), offender));
                        return;
                     }
                  }
               }
            }

            assert false;
         }

         switch (group) {
            case TRANSIENT:
               return;
            case ANY_ATTRIBUTE:
               if (this.attributeWildcard != null) {
                  this.builder.reportError(new IllegalAnnotationException(Messages.TWO_ATTRIBUTE_WILDCARDS.format(this.nav().getClassName(this.getClazz())), aa, this.attributeWildcard));
                  return;
               }

               this.attributeWildcard = seed;
               if (this.inheritsAttributeWildcard()) {
                  this.builder.reportError(new IllegalAnnotationException(Messages.SUPER_CLASS_HAS_WILDCARD.format(), aa, this.getInheritedAttributeWildcard()));
                  return;
               }

               if (!this.nav().isSubClassOf(seed.getRawType(), this.nav().ref(Map.class))) {
                  this.builder.reportError(new IllegalAnnotationException(Messages.INVALID_ATTRIBUTE_WILDCARD_TYPE.format(this.nav().getTypeName(seed.getRawType())), aa, this.getInheritedAttributeWildcard()));
                  return;
               }

               return;
            case ATTRIBUTE:
               this.properties.add((PropertyInfoImpl)this.createAttributeProperty(seed));
               return;
            case VALUE:
               this.properties.add((PropertyInfoImpl)this.createValueProperty(seed));
               return;
            case ELEMENT:
               this.properties.add((PropertyInfoImpl)this.createElementProperty(seed));
               return;
            case ELEMENT_REF:
               this.properties.add((PropertyInfoImpl)this.createReferenceProperty(seed));
               return;
            case MAP:
               this.properties.add((PropertyInfoImpl)this.createMapProperty(seed));
               return;
            default:
               assert false;
         }
      } catch (ConflictException x) {
         List<Annotation> err = x.annotations;
         ModelBuilder var10000 = this.builder;
         Messages var10003 = Messages.MUTUALLY_EXCLUSIVE_ANNOTATIONS;
         Object[] var10004 = new Object[3];
         String var10007 = this.nav().getClassName(this.getClazz());
         var10004[0] = var10007 + "#" + seed.getName();
         var10004[1] = ((Annotation)err.get(0)).annotationType().getName();
         var10004[2] = ((Annotation)err.get(1)).annotationType().getName();
         var10000.reportError(new IllegalAnnotationException(var10003.format(var10004), (Annotation)err.get(0), (Annotation)err.get(1)));
      } catch (DuplicateException e) {
         this.builder.reportError(new IllegalAnnotationException(Messages.DUPLICATE_ANNOTATIONS.format(e.a1.annotationType().getName()), e.a1, e.a2));
      }

   }

   protected ReferencePropertyInfo createReferenceProperty(PropertySeed seed) {
      return new ReferencePropertyInfoImpl(this, seed);
   }

   protected AttributePropertyInfo createAttributeProperty(PropertySeed seed) {
      return new AttributePropertyInfoImpl(this, seed);
   }

   protected ValuePropertyInfo createValueProperty(PropertySeed seed) {
      return new ValuePropertyInfoImpl(this, seed);
   }

   protected ElementPropertyInfo createElementProperty(PropertySeed seed) {
      return new ElementPropertyInfoImpl(this, seed);
   }

   protected MapPropertyInfo createMapProperty(PropertySeed seed) {
      return new MapPropertyInfoImpl(this, seed);
   }

   private void findGetterSetterProperties(XmlAccessType at) {
      Map<String, M> getters = new LinkedHashMap();
      Map<String, M> setters = new LinkedHashMap();
      C c = (C)this.clazz;

      do {
         this.collectGetterSetters(this.clazz, getters, setters);
         c = (C)this.nav().getSuperClass(c);
      } while(this.shouldRecurseSuperClass(c));

      Set<String> complete = new TreeSet(getters.keySet());
      complete.retainAll(setters.keySet());
      this.resurrect(getters, complete);
      this.resurrect(setters, complete);

      for(String name : complete) {
         M getter = (M)getters.get(name);
         M setter = (M)setters.get(name);
         Annotation[] ga = getter != null ? this.reader().getAllMethodAnnotations(getter, new MethodLocatable(this, getter, this.nav())) : EMPTY_ANNOTATIONS;
         Annotation[] sa = setter != null ? this.reader().getAllMethodAnnotations(setter, new MethodLocatable(this, setter, this.nav())) : EMPTY_ANNOTATIONS;
         boolean hasAnnotation = hasJAXBAnnotation(ga) || hasJAXBAnnotation(sa);
         boolean isOverriding = false;
         if (!hasAnnotation) {
            isOverriding = getter != null && this.nav().isOverriding(getter, c) && setter != null && this.nav().isOverriding(setter, c);
         }

         if (at == XmlAccessType.PROPERTY && !isOverriding || at == XmlAccessType.PUBLIC_MEMBER && this.isConsideredPublic(getter) && this.isConsideredPublic(setter) && !isOverriding || hasAnnotation) {
            if (getter != null && setter != null && !this.nav().isSameType(this.nav().getReturnType(getter), this.nav().getMethodParameters(setter)[0])) {
               this.builder.reportError(new IllegalAnnotationException(Messages.GETTER_SETTER_INCOMPATIBLE_TYPE.format(this.nav().getTypeName(this.nav().getReturnType(getter)), this.nav().getTypeName(this.nav().getMethodParameters(setter)[0])), new MethodLocatable(this, getter, this.nav()), new MethodLocatable(this, setter, this.nav())));
            } else {
               Annotation[] r;
               if (ga.length == 0) {
                  r = sa;
               } else if (sa.length == 0) {
                  r = ga;
               } else {
                  r = new Annotation[ga.length + sa.length];
                  System.arraycopy(ga, 0, r, 0, ga.length);
                  System.arraycopy(sa, 0, r, ga.length, sa.length);
               }

               this.addProperty(this.createAccessorSeed(getter, setter), r, false);
            }
         }
      }

      getters.keySet().removeAll(complete);
      setters.keySet().removeAll(complete);
   }

   private void collectGetterSetters(Object c, Map getters, Map setters) {
      C sc = (C)this.nav().getSuperClass(c);
      if (this.shouldRecurseSuperClass(sc)) {
         this.collectGetterSetters(sc, getters, setters);
      }

      Collection<? extends M> methods = this.nav().getDeclaredMethods(c);
      Map<String, List<M>> allSetters = new LinkedHashMap();

      for(Object method : methods) {
         boolean used = false;
         if (!this.nav().isBridgeMethod(method)) {
            String name = this.nav().getMethodName(method);
            int arity = this.nav().getMethodParameters(method).length;
            if (this.nav().isStaticMethod(method)) {
               this.ensureNoAnnotation(method);
            } else {
               String propName = getPropertyNameFromGetMethod(name);
               if (propName != null && arity == 0) {
                  getters.put(propName, method);
                  used = true;
               }

               propName = getPropertyNameFromSetMethod(name);
               if (propName != null && arity == 1) {
                  List<M> propSetters = (List)allSetters.computeIfAbsent(propName, (k) -> new ArrayList());
                  propSetters.add(method);
                  used = true;
               }

               if (!used) {
                  this.ensureNoAnnotation(method);
               }
            }
         }
      }

      for(Map.Entry entry : getters.entrySet()) {
         String propName = (String)entry.getKey();
         M getter = (M)entry.getValue();
         List<M> propSetters = (List)allSetters.remove(propName);
         if (null != propSetters) {
            T getterType = (T)this.nav().getReturnType(getter);

            for(Object setter : propSetters) {
               T setterType = (T)this.nav().getMethodParameters(setter)[0];
               if (this.nav().isSameType(setterType, getterType)) {
                  setters.put(propName, setter);
                  break;
               }
            }
         }
      }

      for(Map.Entry e : allSetters.entrySet()) {
         setters.put((String)e.getKey(), ((List)e.getValue()).get(0));
      }

   }

   private boolean shouldRecurseSuperClass(Object sc) {
      return sc != null && (this.builder.isReplaced(sc) || this.reader().hasClassAnnotation(sc, XmlTransient.class));
   }

   private boolean isConsideredPublic(Object m) {
      return m == null || this.nav().isPublicMethod(m);
   }

   private void resurrect(Map methods, Set complete) {
      for(Map.Entry e : methods.entrySet()) {
         if (!complete.contains(e.getKey()) && hasJAXBAnnotation(this.reader().getAllMethodAnnotations(e.getValue(), this))) {
            complete.add((String)e.getKey());
         }
      }

   }

   private void ensureNoAnnotation(Object method) {
      Annotation[] annotations = this.reader().getAllMethodAnnotations(method, this);

      for(Annotation a : annotations) {
         if (isJAXBAnnotation(a)) {
            this.builder.reportError(new IllegalAnnotationException(Messages.ANNOTATION_ON_WRONG_METHOD.format(), a));
            return;
         }
      }

   }

   private static boolean isJAXBAnnotation(Annotation a) {
      return ANNOTATION_NUMBER_MAP.containsKey(a.annotationType());
   }

   private static boolean hasJAXBAnnotation(Annotation[] annotations) {
      return getSomeJAXBAnnotation(annotations) != null;
   }

   private static Annotation getSomeJAXBAnnotation(Annotation[] annotations) {
      for(Annotation a : annotations) {
         if (isJAXBAnnotation(a)) {
            return a;
         }
      }

      return null;
   }

   private static String getPropertyNameFromGetMethod(String name) {
      if (name.startsWith("get") && name.length() > 3) {
         return name.substring(3);
      } else {
         return name.startsWith("is") && name.length() > 2 ? name.substring(2) : null;
      }
   }

   private static String getPropertyNameFromSetMethod(String name) {
      return name.startsWith("set") && name.length() > 3 ? name.substring(3) : null;
   }

   protected PropertySeed createFieldSeed(Object f) {
      return new FieldPropertySeed(this, f);
   }

   protected PropertySeed createAccessorSeed(Object getter, Object setter) {
      return new GetterSetterPropertySeed(this, getter, setter);
   }

   public final boolean isElement() {
      return this.elementName != null;
   }

   public boolean isAbstract() {
      return this.nav().isAbstract(this.clazz);
   }

   public boolean isOrdered() {
      return this.propOrder != null;
   }

   public final boolean isFinal() {
      return this.nav().isFinal(this.clazz);
   }

   public final boolean hasSubClasses() {
      return this.hasSubClasses;
   }

   public final boolean hasAttributeWildcard() {
      return this.declaresAttributeWildcard() || this.inheritsAttributeWildcard();
   }

   public final boolean inheritsAttributeWildcard() {
      return this.getInheritedAttributeWildcard() != null;
   }

   public final boolean declaresAttributeWildcard() {
      return this.attributeWildcard != null;
   }

   private PropertySeed getInheritedAttributeWildcard() {
      for(ClassInfoImpl<T, C, F, M> c = this.getBaseClass(); c != null; c = c.getBaseClass()) {
         if (c.attributeWildcard != null) {
            return c.attributeWildcard;
         }
      }

      return null;
   }

   public final QName getElementName() {
      return this.elementName;
   }

   public final QName getTypeName() {
      return this.typeName;
   }

   public final boolean isSimpleType() {
      List<? extends PropertyInfo> props = this.getProperties();
      if (props.size() != 1) {
         return false;
      } else {
         return ((PropertyInfo)props.get(0)).kind() == PropertyKind.VALUE;
      }
   }

   void link() {
      this.getProperties();
      Map<String, PropertyInfoImpl> names = new HashMap();

      for(PropertyInfoImpl p : this.properties) {
         p.link();
         PropertyInfoImpl old = (PropertyInfoImpl)names.put(p.getName(), p);
         if (old != null) {
            this.builder.reportError(new IllegalAnnotationException(Messages.PROPERTY_COLLISION.format(p.getName()), p, old));
         }
      }

      super.link();
   }

   public Location getLocation() {
      return this.nav().getClassLocation(this.clazz);
   }

   private boolean hasFactoryConstructor(XmlType t) {
      if (t == null) {
         return false;
      } else {
         String method = t.factoryMethod();
         T fClass = (T)this.reader().getClassValue(t, "factoryClass");
         if (method.length() > 0) {
            if (this.nav().isSameType(fClass, this.nav().ref(XmlType.DEFAULT.class))) {
               fClass = (T)this.nav().use(this.clazz);
            }

            for(Object m : this.nav().getDeclaredMethods(this.nav().asDecl(fClass))) {
               if (this.nav().getMethodName(m).equals(method) && this.nav().isSameType(this.nav().getReturnType(m), this.nav().use(this.clazz)) && this.nav().getMethodParameters(m).length == 0 && this.nav().isStaticMethod(m)) {
                  this.factoryMethod = m;
                  break;
               }
            }

            if (this.factoryMethod == null) {
               this.builder.reportError(new IllegalAnnotationException(Messages.NO_FACTORY_METHOD.format(this.nav().getClassName(this.nav().asDecl(fClass)), method), this));
            }
         } else if (!this.nav().isSameType(fClass, this.nav().ref(XmlType.DEFAULT.class))) {
            this.builder.reportError(new IllegalAnnotationException(Messages.FACTORY_CLASS_NEEDS_FACTORY_METHOD.format(this.nav().getClassName(this.nav().asDecl(fClass))), this));
         }

         return this.factoryMethod != null;
      }
   }

   public Method getFactoryMethod() {
      return (Method)this.factoryMethod;
   }

   private boolean hasApplicableAdapter(PropertySeed seed, Object type) {
      XmlJavaTypeAdapter jta = (XmlJavaTypeAdapter)seed.readAnnotation(XmlJavaTypeAdapter.class);
      if (jta != null && this.isApplicable(jta, type)) {
         return true;
      } else {
         XmlJavaTypeAdapters jtas = (XmlJavaTypeAdapters)this.reader().getPackageAnnotation(XmlJavaTypeAdapters.class, this.clazz, seed);
         if (jtas != null) {
            for(XmlJavaTypeAdapter xjta : jtas.value()) {
               if (this.isApplicable(xjta, type)) {
                  return true;
               }
            }
         }

         jta = (XmlJavaTypeAdapter)this.reader().getPackageAnnotation(XmlJavaTypeAdapter.class, this.clazz, seed);
         if (this.isApplicable(jta, type)) {
            return true;
         } else {
            C refType = (C)this.nav().asDecl(type);
            if (refType != null) {
               jta = (XmlJavaTypeAdapter)this.reader().getClassAnnotation(XmlJavaTypeAdapter.class, refType, seed);
               if (jta != null && this.isApplicable(jta, type)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private boolean isApplicable(XmlJavaTypeAdapter jta, Object declaredType) {
      if (jta == null) {
         return false;
      } else {
         T type = (T)this.reader().getClassValue(jta, "type");
         if (this.nav().isSameType(declaredType, type)) {
            return true;
         } else {
            T ad = (T)this.reader().getClassValue(jta, "value");
            T ba = (T)this.nav().getBaseClass(ad, this.nav().asDecl(XmlAdapter.class));
            if (!this.nav().isParameterizedType(ba)) {
               return true;
            } else {
               T inMemType = (T)this.nav().getTypeArgument(ba, 1);
               return this.nav().isSubClassOf(declaredType, inMemType);
            }
         }
      }
   }

   public String toString() {
      return "ClassInfo(" + this.clazz + ")";
   }

   static {
      Class[] annotations = new Class[]{XmlTransient.class, XmlAnyAttribute.class, XmlAttribute.class, XmlValue.class, XmlElement.class, XmlElements.class, XmlElementRef.class, XmlElementRefs.class, XmlAnyElement.class, XmlMixed.class, OverrideAnnotationOf.class};
      HashMap<Class, Integer> m = ANNOTATION_NUMBER_MAP;

      for(Class c : annotations) {
         m.put(c, m.size());
      }

      int index = 20;

      for(SecondaryAnnotation sa : SECONDARY_ANNOTATIONS) {
         for(Class member : sa.members) {
            m.put(member, index);
         }

         ++index;
      }

      DEFAULT_ORDER = new String[0];
   }

   private final class PropertySorter extends HashMap implements Comparator {
      private static final long serialVersionUID = 8074459976041391290L;
      PropertyInfoImpl[] used;
      private Set collidedNames;

      PropertySorter() {
         super(ClassInfoImpl.this.propOrder.length);
         this.used = new PropertyInfoImpl[ClassInfoImpl.this.propOrder.length];

         for(String name : ClassInfoImpl.this.propOrder) {
            if (this.put(name, this.size()) != null) {
               ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.DUPLICATE_ENTRY_IN_PROP_ORDER.format(name), ClassInfoImpl.this));
            }
         }

      }

      public int compare(PropertyInfoImpl o1, PropertyInfoImpl o2) {
         int lhs = this.checkedGet(o1);
         int rhs = this.checkedGet(o2);
         return lhs - rhs;
      }

      private int checkedGet(PropertyInfoImpl p) {
         Integer i = (Integer)this.get(p.getName());
         if (i == null) {
            if (p.kind().isOrdered) {
               ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.PROPERTY_MISSING_FROM_ORDER.format(p.getName()), p));
            }

            i = this.size();
            this.put(p.getName(), i);
         }

         int ii = i;
         if (ii < this.used.length) {
            if (this.used[ii] != null && this.used[ii] != p) {
               if (this.collidedNames == null) {
                  this.collidedNames = new HashSet();
               }

               if (this.collidedNames.add(p.getName())) {
                  ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.DUPLICATE_PROPERTIES.format(p.getName()), p, this.used[ii]));
               }
            }

            this.used[ii] = p;
         }

         return i;
      }

      public void checkUnusedProperties() {
         for(int i = 0; i < this.used.length; ++i) {
            if (this.used[i] == null) {
               String unusedName = ClassInfoImpl.this.propOrder[i];
               String nearest = EditDistance.findNearest(unusedName, new AbstractList() {
                  public String get(int index) {
                     return ((PropertyInfoImpl)ClassInfoImpl.this.properties.get(index)).getName();
                  }

                  public int size() {
                     return ClassInfoImpl.this.properties.size();
                  }
               });
               boolean isOverriding = i <= ClassInfoImpl.this.properties.size() - 1 && ((PropertyInfoImpl)ClassInfoImpl.this.properties.get(i)).hasAnnotation(OverrideAnnotationOf.class);
               if (!isOverriding) {
                  ClassInfoImpl.this.builder.reportError(new IllegalAnnotationException(Messages.PROPERTY_ORDER_CONTAINS_UNUSED_ENTRY.format(unusedName, nearest), ClassInfoImpl.this));
               }
            }
         }

      }
   }

   private static final class ConflictException extends Exception {
      private static final long serialVersionUID = -8261248191127673032L;
      final List annotations;

      public ConflictException(List one) {
         this.annotations = one;
      }
   }

   private static final class DuplicateException extends Exception {
      private static final long serialVersionUID = -2996855754364938240L;
      final Annotation a1;
      final Annotation a2;

      public DuplicateException(Annotation a1, Annotation a2) {
         this.a1 = a1;
         this.a2 = a2;
      }
   }

   private static enum SecondaryAnnotation {
      JAVA_TYPE(1, new Class[]{XmlJavaTypeAdapter.class}),
      ID_IDREF(2, new Class[]{XmlID.class, XmlIDREF.class}),
      BINARY(4, new Class[]{XmlInlineBinaryData.class, XmlMimeType.class, XmlAttachmentRef.class}),
      ELEMENT_WRAPPER(8, new Class[]{XmlElementWrapper.class}),
      LIST(16, new Class[]{XmlList.class}),
      SCHEMA_TYPE(32, new Class[]{XmlSchemaType.class});

      final int bitMask;
      final Class[] members;

      private SecondaryAnnotation(int bitMask, Class[] members) {
         this.bitMask = bitMask;
         this.members = members;
      }
   }

   private static enum PropertyGroup {
      TRANSIENT(new boolean[]{false, false, false, false, false, false}),
      ANY_ATTRIBUTE(new boolean[]{true, false, false, false, false, false}),
      ATTRIBUTE(new boolean[]{true, true, true, false, true, true}),
      VALUE(new boolean[]{true, true, true, false, true, true}),
      ELEMENT(new boolean[]{true, true, true, true, true, true}),
      ELEMENT_REF(new boolean[]{true, false, false, true, false, false}),
      MAP(new boolean[]{false, false, false, true, false, false});

      final int allowedsecondaryAnnotations;

      private PropertyGroup(boolean... bits) {
         int mask = 0;

         assert bits.length == ClassInfoImpl.SECONDARY_ANNOTATIONS.length;

         for(int i = 0; i < bits.length; ++i) {
            if (bits[i]) {
               mask |= ClassInfoImpl.SECONDARY_ANNOTATIONS[i].bitMask;
            }
         }

         this.allowedsecondaryAnnotations = ~mask;
      }

      boolean allows(SecondaryAnnotation a) {
         return (this.allowedsecondaryAnnotations & a.bitMask) == 0;
      }
   }
}
