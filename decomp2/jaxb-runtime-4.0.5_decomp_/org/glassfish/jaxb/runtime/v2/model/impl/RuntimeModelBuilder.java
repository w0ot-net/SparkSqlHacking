package org.glassfish.jaxb.runtime.v2.model.impl;

import com.sun.istack.Nullable;
import jakarta.activation.MimeType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.ArrayInfo;
import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.RegistryInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.annotation.RuntimeAnnotationReader;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElementRef;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeInfoSet;
import org.glassfish.jaxb.runtime.v2.runtime.FilterTransducer;
import org.glassfish.jaxb.runtime.v2.runtime.InlineBinaryTransducer;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.MimeTypedTransducer;
import org.glassfish.jaxb.runtime.v2.runtime.SchemaTypeTransducer;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

public class RuntimeModelBuilder extends ModelBuilder {
   @Nullable
   public final JAXBContextImpl context;

   public RuntimeModelBuilder(JAXBContextImpl context, RuntimeAnnotationReader annotationReader, Map subclassReplacements, String defaultNamespaceRemap) {
      super(annotationReader, Utils.REFLECTION_NAVIGATOR, subclassReplacements, defaultNamespaceRemap);
      this.context = context;
   }

   public RuntimeNonElement getClassInfo(Class clazz, Locatable upstream) {
      return (RuntimeNonElement)super.getClassInfo(clazz, upstream);
   }

   public RuntimeNonElement getClassInfo(Class clazz, boolean searchForSuperClass, Locatable upstream) {
      return (RuntimeNonElement)super.getClassInfo(clazz, searchForSuperClass, upstream);
   }

   protected EnumLeafInfo createEnumLeafInfo(Class clazz, Locatable upstream) {
      return new RuntimeEnumLeafInfoImpl(this, upstream, clazz);
   }

   protected ClassInfo createClassInfo(Class clazz, Locatable upstream) {
      return new RuntimeClassInfoImpl(this, upstream, clazz);
   }

   public ElementInfo createElementInfo(RegistryInfo registryInfo, Method method) throws IllegalAnnotationException {
      return new RuntimeElementInfoImpl(this, registryInfo, method);
   }

   public ArrayInfo createArrayInfo(Locatable upstream, Type arrayType) {
      return new RuntimeArrayInfoImpl(this, upstream, (Class)arrayType);
   }

   protected TypeInfoSet createTypeInfoSet() {
      return new RuntimeTypeInfoSetImpl(this.reader);
   }

   public RuntimeTypeInfoSet link() {
      return (RuntimeTypeInfoSet)super.link();
   }

   public static Transducer createTransducer(RuntimeNonElementRef ref) {
      Transducer<V> t = ref.getTarget().getTransducer();
      RuntimePropertyInfo src = ref.getSource();
      ID id = src.id();
      if (id == ID.IDREF) {
         return RuntimeBuiltinLeafInfoImpl.STRING;
      } else {
         if (id == ID.ID) {
            t = new IDTransducerImpl(t);
         }

         MimeType emt = src.getExpectedMimeType();
         if (emt != null) {
            t = new MimeTypedTransducer(t, emt);
         }

         if (src.inlineBinaryData()) {
            t = new InlineBinaryTransducer(t);
         }

         if (src.getSchemaType() != null) {
            if (src.getSchemaType().equals(createXSSimpleType())) {
               return RuntimeBuiltinLeafInfoImpl.STRING;
            }

            t = new SchemaTypeTransducer(t, src.getSchemaType());
         }

         return t;
      }
   }

   private static QName createXSSimpleType() {
      return new QName("http://www.w3.org/2001/XMLSchema", "anySimpleType");
   }

   private static final class IDTransducerImpl extends FilterTransducer {
      public IDTransducerImpl(Transducer core) {
         super(core);
      }

      public Object parse(CharSequence lexical) throws AccessorException, SAXException {
         String value = WhiteSpaceProcessor.trim(lexical).toString();
         UnmarshallingContext.getInstance().addToIdTable(value);
         return this.core.parse(value);
      }
   }
}
