package org.glassfish.jaxb.runtime.v2.model.impl;

import com.sun.istack.FinalArrayList;
import jakarta.activation.MimeType;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAttachmentRef;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlInlineBinaryData;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.TODO;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationSource;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.TypeRef;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.Location;
import org.glassfish.jaxb.runtime.v2.runtime.SwaRefAdapter;

class ElementInfoImpl extends TypeInfoImpl implements ElementInfo {
   private final QName tagName;
   private final NonElement contentType;
   private final Object tOfJAXBElementT;
   private final Object elementType;
   private final ClassInfo scope;
   private final XmlElementDecl anno;
   private ElementInfoImpl substitutionHead;
   private FinalArrayList substitutionMembers;
   private final Object method;
   private final Adapter adapter;
   private final boolean isCollection;
   private final ID id;
   private final PropertyImpl property;
   private final MimeType expectedMimeType;
   private final boolean inlineBinary;
   private final QName schemaType;

   public ElementInfoImpl(ModelBuilder builder, RegistryInfoImpl registry, Object m) throws IllegalAnnotationException {
      super(builder, registry);
      this.method = m;
      this.anno = (XmlElementDecl)this.reader().getMethodAnnotation(XmlElementDecl.class, m, this);

      assert this.anno != null;

      assert this.anno instanceof Locatable;

      this.elementType = this.nav().getReturnType(m);
      T baseClass = (T)this.nav().getBaseClass(this.elementType, this.nav().asDecl(JAXBElement.class));
      if (baseClass == null) {
         throw new IllegalAnnotationException(Messages.XML_ELEMENT_MAPPING_ON_NON_IXMLELEMENT_METHOD.format(this.nav().getMethodName(m)), this.anno);
      } else {
         this.tagName = this.parseElementName(this.anno);
         T[] methodParams = (T[])this.nav().getMethodParameters(m);
         Adapter<T, C> a = null;
         if (methodParams.length > 0) {
            XmlJavaTypeAdapter adapter = (XmlJavaTypeAdapter)this.reader().getMethodAnnotation(XmlJavaTypeAdapter.class, m, this);
            if (adapter != null) {
               a = new Adapter(adapter, this.reader(), this.nav());
            } else {
               XmlAttachmentRef xsa = (XmlAttachmentRef)this.reader().getMethodAnnotation(XmlAttachmentRef.class, m, this);
               if (xsa != null) {
                  TODO.prototype("in Annotation Processing swaRefAdapter isn't avaialble, so this returns null");
                  a = new Adapter(this.owner.nav.asDecl(SwaRefAdapter.class), this.owner.nav);
               }
            }
         }

         this.adapter = a;
         this.tOfJAXBElementT = methodParams.length > 0 ? methodParams[0] : this.nav().getTypeArgument(baseClass, 0);
         if (this.adapter == null) {
            T list = (T)this.nav().getBaseClass(this.tOfJAXBElementT, this.nav().asDecl(List.class));
            if (list == null) {
               this.isCollection = false;
               this.contentType = builder.getTypeInfo(this.tOfJAXBElementT, this);
            } else {
               this.isCollection = true;
               this.contentType = builder.getTypeInfo(this.nav().getTypeArgument(list, 0), this);
            }
         } else {
            this.contentType = builder.getTypeInfo(this.adapter.defaultType, this);
            this.isCollection = false;
         }

         T s = (T)this.reader().getClassValue(this.anno, "scope");
         if (this.nav().isSameType(s, this.nav().ref(XmlElementDecl.GLOBAL.class))) {
            this.scope = null;
         } else {
            NonElement<T, C> scp = builder.getClassInfo(this.nav().asDecl(s), this);
            if (!(scp instanceof ClassInfo)) {
               throw new IllegalAnnotationException(Messages.SCOPE_IS_NOT_COMPLEXTYPE.format(this.nav().getTypeName(s)), this.anno);
            }

            this.scope = (ClassInfo)scp;
         }

         this.id = this.calcId();
         this.property = this.createPropertyImpl();
         this.expectedMimeType = Util.calcExpectedMediaType(this.property, builder);
         this.inlineBinary = this.reader().hasMethodAnnotation(XmlInlineBinaryData.class, this.method);
         this.schemaType = Util.calcSchemaType(this.reader(), this.property, registry.registryClass, this.getContentInMemoryType(), this);
      }
   }

   final QName parseElementName(XmlElementDecl e) {
      String local = e.name();
      String nsUri = e.namespace();
      if (nsUri.equals("##default")) {
         XmlSchema xs = (XmlSchema)this.reader().getPackageAnnotation(XmlSchema.class, this.nav().getDeclaringClassForMethod(this.method), this);
         if (xs != null) {
            nsUri = xs.namespace();
         } else {
            nsUri = this.builder.defaultNsUri;
         }
      }

      return new QName(nsUri.intern(), local.intern());
   }

   protected PropertyImpl createPropertyImpl() {
      return new PropertyImpl();
   }

   public ElementPropertyInfo getProperty() {
      return this.property;
   }

   public NonElement getContentType() {
      return this.contentType;
   }

   public Object getContentInMemoryType() {
      return this.adapter == null ? this.tOfJAXBElementT : this.adapter.customType;
   }

   public QName getElementName() {
      return this.tagName;
   }

   public Object getType() {
      return this.elementType;
   }

   /** @deprecated */
   @Deprecated
   public final boolean canBeReferencedByIDREF() {
      return false;
   }

   private ID calcId() {
      if (this.reader().hasMethodAnnotation(XmlID.class, this.method)) {
         return ID.ID;
      } else {
         return this.reader().hasMethodAnnotation(XmlIDREF.class, this.method) ? ID.IDREF : ID.NONE;
      }
   }

   public ClassInfo getScope() {
      return this.scope;
   }

   public ElementInfo getSubstitutionHead() {
      return this.substitutionHead;
   }

   public Collection getSubstitutionMembers() {
      return (Collection)(this.substitutionMembers == null ? Collections.emptyList() : this.substitutionMembers);
   }

   void link() {
      if (this.anno.substitutionHeadName().length() != 0) {
         QName name = new QName(this.anno.substitutionHeadNamespace(), this.anno.substitutionHeadName());
         this.substitutionHead = this.owner.getElementInfo((Object)null, name);
         if (this.substitutionHead == null) {
            this.builder.reportError(new IllegalAnnotationException(Messages.NON_EXISTENT_ELEMENT_MAPPING.format(name.getNamespaceURI(), name.getLocalPart()), this.anno));
         } else {
            this.substitutionHead.addSubstitutionMember(this);
         }
      } else {
         this.substitutionHead = null;
      }

      super.link();
   }

   private void addSubstitutionMember(ElementInfoImpl child) {
      if (this.substitutionMembers == null) {
         this.substitutionMembers = new FinalArrayList();
      }

      this.substitutionMembers.add(child);
   }

   public Location getLocation() {
      return this.nav().getMethodLocation(this.method);
   }

   protected class PropertyImpl implements ElementPropertyInfo, TypeRef, AnnotationSource {
      public NonElement getTarget() {
         return ElementInfoImpl.this.contentType;
      }

      public QName getTagName() {
         return ElementInfoImpl.this.tagName;
      }

      public List getTypes() {
         return Collections.singletonList(this);
      }

      public List ref() {
         return Collections.singletonList(ElementInfoImpl.this.contentType);
      }

      public QName getXmlName() {
         return ElementInfoImpl.this.tagName;
      }

      public boolean isCollectionRequired() {
         return false;
      }

      public boolean isCollectionNillable() {
         return true;
      }

      public boolean isNillable() {
         return true;
      }

      public String getDefaultValue() {
         String v = ElementInfoImpl.this.anno.defaultValue();
         return v.equals("\u0000") ? null : v;
      }

      public ElementInfoImpl parent() {
         return ElementInfoImpl.this;
      }

      public String getName() {
         return "value";
      }

      public String displayName() {
         return "JAXBElement#value";
      }

      public boolean isCollection() {
         return ElementInfoImpl.this.isCollection;
      }

      public boolean isValueList() {
         return ElementInfoImpl.this.isCollection;
      }

      public boolean isRequired() {
         return true;
      }

      public PropertyKind kind() {
         return PropertyKind.ELEMENT;
      }

      public Adapter getAdapter() {
         return ElementInfoImpl.this.adapter;
      }

      public ID id() {
         return ElementInfoImpl.this.id;
      }

      public MimeType getExpectedMimeType() {
         return ElementInfoImpl.this.expectedMimeType;
      }

      public QName getSchemaType() {
         return ElementInfoImpl.this.schemaType;
      }

      public boolean inlineBinaryData() {
         return ElementInfoImpl.this.inlineBinary;
      }

      public PropertyInfo getSource() {
         return this;
      }

      public Annotation readAnnotation(Class annotationType) {
         return ElementInfoImpl.this.reader().getMethodAnnotation(annotationType, ElementInfoImpl.this.method, ElementInfoImpl.this);
      }

      public boolean hasAnnotation(Class annotationType) {
         return ElementInfoImpl.this.reader().hasMethodAnnotation(annotationType, ElementInfoImpl.this.method);
      }
   }
}
