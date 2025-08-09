package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.activation.MimeType;
import jakarta.xml.bind.annotation.XmlAttachmentRef;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlInlineBinaryData;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.lang.annotation.Annotation;
import java.util.Collection;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.TODO;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.Adapter;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfo;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.Location;
import org.glassfish.jaxb.runtime.v2.runtime.SwaRefAdapter;

abstract class PropertyInfoImpl implements PropertyInfo, Locatable, Comparable {
   protected final PropertySeed seed;
   private final boolean isCollection;
   private final ID id;
   private final MimeType expectedMimeType;
   private final boolean inlineBinary;
   private final QName schemaType;
   protected final ClassInfoImpl parent;
   private final Adapter adapter;

   protected PropertyInfoImpl(ClassInfoImpl parent, PropertySeed spi) {
      this.seed = spi;
      this.parent = parent;
      if (parent == null) {
         throw new AssertionError();
      } else {
         MimeType mt = Util.calcExpectedMediaType(this.seed, parent.builder);
         if (mt != null && !this.kind().canHaveXmlMimeType) {
            parent.builder.reportError(new IllegalAnnotationException(Messages.ILLEGAL_ANNOTATION.format(XmlMimeType.class.getName()), this.seed.readAnnotation(XmlMimeType.class)));
            mt = null;
         }

         this.expectedMimeType = mt;
         this.inlineBinary = this.seed.hasAnnotation(XmlInlineBinaryData.class);
         T t = (T)this.seed.getRawType();
         XmlJavaTypeAdapter xjta = this.getApplicableAdapter(t);
         if (xjta != null) {
            this.isCollection = false;
            this.adapter = new Adapter(xjta, this.reader(), this.nav());
         } else {
            this.isCollection = this.nav().isSubClassOf(t, this.nav().ref(Collection.class)) || this.nav().isArrayButNotByteArray(t);
            xjta = this.getApplicableAdapter(this.getIndividualType());
            if (xjta == null) {
               XmlAttachmentRef xsa = (XmlAttachmentRef)this.seed.readAnnotation(XmlAttachmentRef.class);
               if (xsa != null) {
                  parent.builder.hasSwaRef = true;
                  this.adapter = new Adapter(this.nav().asDecl(SwaRefAdapter.class), this.nav());
               } else {
                  this.adapter = null;
                  xjta = (XmlJavaTypeAdapter)this.seed.readAnnotation(XmlJavaTypeAdapter.class);
                  if (xjta != null) {
                     T ad = (T)this.reader().getClassValue(xjta, "value");
                     parent.builder.reportError(new IllegalAnnotationException(Messages.UNMATCHABLE_ADAPTER.format(this.nav().getTypeName(ad), this.nav().getTypeName(t)), xjta));
                  }
               }
            } else {
               this.adapter = new Adapter(xjta, this.reader(), this.nav());
            }
         }

         this.id = this.calcId();
         this.schemaType = Util.calcSchemaType(this.reader(), this.seed, parent.clazz, this.getIndividualType(), this);
      }
   }

   public ClassInfoImpl parent() {
      return this.parent;
   }

   protected final Navigator nav() {
      return this.parent.nav();
   }

   protected final AnnotationReader reader() {
      return this.parent.reader();
   }

   public Object getRawType() {
      return this.seed.getRawType();
   }

   public Object getIndividualType() {
      if (this.adapter != null) {
         return this.adapter.defaultType;
      } else {
         T raw = (T)this.getRawType();
         if (!this.isCollection()) {
            return raw;
         } else if (this.nav().isArrayButNotByteArray(raw)) {
            return this.nav().getComponentType(raw);
         } else {
            T bt = (T)this.nav().getBaseClass(raw, this.nav().asDecl(Collection.class));
            return this.nav().isParameterizedType(bt) ? this.nav().getTypeArgument(bt, 0) : this.nav().ref(Object.class);
         }
      }
   }

   public final String getName() {
      return this.seed.getName();
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

   private XmlJavaTypeAdapter getApplicableAdapter(Object type) {
      XmlJavaTypeAdapter jta = (XmlJavaTypeAdapter)this.seed.readAnnotation(XmlJavaTypeAdapter.class);
      if (jta != null && this.isApplicable(jta, type)) {
         return jta;
      } else {
         XmlJavaTypeAdapters jtas = (XmlJavaTypeAdapters)this.reader().getPackageAnnotation(XmlJavaTypeAdapters.class, this.parent.clazz, this.seed);
         if (jtas != null) {
            for(XmlJavaTypeAdapter xjta : jtas.value()) {
               if (this.isApplicable(xjta, type)) {
                  return xjta;
               }
            }
         }

         jta = (XmlJavaTypeAdapter)this.reader().getPackageAnnotation(XmlJavaTypeAdapter.class, this.parent.clazz, this.seed);
         if (this.isApplicable(jta, type)) {
            return jta;
         } else {
            C refType = (C)this.nav().asDecl(type);
            if (refType != null) {
               jta = (XmlJavaTypeAdapter)this.reader().getClassAnnotation(XmlJavaTypeAdapter.class, refType, this.seed);
               if (jta != null && this.isApplicable(jta, type)) {
                  return jta;
               }
            }

            return null;
         }
      }
   }

   public Adapter getAdapter() {
      return this.adapter;
   }

   public final String displayName() {
      String var10000 = this.nav().getClassName(this.parent.getClazz());
      return var10000 + "#" + this.getName();
   }

   public final ID id() {
      return this.id;
   }

   private ID calcId() {
      if (this.seed.hasAnnotation(XmlID.class)) {
         if (!this.nav().isSameType(this.getIndividualType(), this.nav().ref(String.class))) {
            this.parent.builder.reportError(new IllegalAnnotationException(Messages.ID_MUST_BE_STRING.format(this.getName()), this.seed));
         }

         return ID.ID;
      } else {
         return this.seed.hasAnnotation(XmlIDREF.class) ? ID.IDREF : ID.NONE;
      }
   }

   public final MimeType getExpectedMimeType() {
      return this.expectedMimeType;
   }

   public final boolean inlineBinaryData() {
      return this.inlineBinary;
   }

   public final QName getSchemaType() {
      return this.schemaType;
   }

   public final boolean isCollection() {
      return this.isCollection;
   }

   protected void link() {
      if (this.id == ID.IDREF) {
         for(TypeInfo ti : this.ref()) {
            if (!ti.canBeReferencedByIDREF()) {
               this.parent.builder.reportError(new IllegalAnnotationException(Messages.INVALID_IDREF.format(this.parent.builder.nav.getTypeName(ti.getType())), this));
            }
         }
      }

   }

   public Locatable getUpstream() {
      return this.parent;
   }

   public Location getLocation() {
      return this.seed.getLocation();
   }

   protected final QName calcXmlName(XmlElement e) {
      return e != null ? this.calcXmlName(e.namespace(), e.name()) : this.calcXmlName("##default", "##default");
   }

   protected final QName calcXmlName(XmlElementWrapper e) {
      return e != null ? this.calcXmlName(e.namespace(), e.name()) : this.calcXmlName("##default", "##default");
   }

   private QName calcXmlName(String uri, String local) {
      TODO.checkSpec();
      if (local.length() == 0 || local.equals("##default")) {
         local = this.seed.getName();
      }

      if (uri.equals("##default")) {
         XmlSchema xs = (XmlSchema)this.reader().getPackageAnnotation(XmlSchema.class, this.parent.getClazz(), this);
         if (xs != null) {
            switch (xs.elementFormDefault()) {
               case QUALIFIED:
                  QName typeName = this.parent.getTypeName();
                  if (typeName != null) {
                     uri = typeName.getNamespaceURI();
                  } else {
                     uri = xs.namespace();
                  }

                  if (uri.length() == 0) {
                     uri = this.parent.builder.defaultNsUri;
                  }
                  break;
               case UNQUALIFIED:
               case UNSET:
                  uri = "";
            }
         } else {
            uri = "";
         }
      }

      return new QName(uri.intern(), local.intern());
   }

   public int compareTo(PropertyInfoImpl that) {
      return this.getName().compareTo(that.getName());
   }

   public final Annotation readAnnotation(Class annotationType) {
      return this.seed.readAnnotation(annotationType);
   }

   public final boolean hasAnnotation(Class annotationType) {
      return this.seed.hasAnnotation(annotationType);
   }
}
