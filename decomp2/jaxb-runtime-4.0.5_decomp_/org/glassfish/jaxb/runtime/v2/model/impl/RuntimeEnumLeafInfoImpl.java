package org.glassfish.jaxb.runtime.v2.model.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.annotation.FieldLocatable;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeEnumLeafInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElement;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.xml.sax.SAXException;

final class RuntimeEnumLeafInfoImpl extends EnumLeafInfoImpl implements RuntimeEnumLeafInfo, Transducer {
   private final Transducer baseXducer;
   private final Map parseMap = new HashMap();
   private final Map printMap;

   public Transducer getTransducer() {
      return this;
   }

   RuntimeEnumLeafInfoImpl(RuntimeModelBuilder builder, Locatable upstream, Class enumType) {
      super(builder, upstream, enumType, enumType);
      this.printMap = new EnumMap(enumType);
      this.baseXducer = ((RuntimeNonElement)this.baseType).getTransducer();
   }

   public RuntimeEnumConstantImpl createEnumConstant(String name, String literal, Field constant, EnumConstantImpl last) {
      T t;
      try {
         try {
            constant.setAccessible(true);
         } catch (SecurityException var9) {
         }

         t = (T)((Enum)constant.get((Object)null));
      } catch (IllegalAccessException e) {
         throw new IllegalAccessError(e.getMessage());
      }

      B b = (B)null;

      try {
         b = (B)this.baseXducer.parse(literal);
      } catch (Exception e) {
         this.builder.reportError(new IllegalAnnotationException(Messages.INVALID_XML_ENUM_VALUE.format(literal, ((Type)this.baseType.getType()).toString()), e, new FieldLocatable(this, constant, this.nav())));
      }

      this.parseMap.put(b, t);
      this.printMap.put(t, b);
      return new RuntimeEnumConstantImpl(this, name, literal, last);
   }

   public QName[] getTypeNames() {
      return new QName[]{this.getTypeName()};
   }

   public Class getClazz() {
      return (Class)this.clazz;
   }

   public boolean useNamespace() {
      return this.baseXducer.useNamespace();
   }

   public void declareNamespace(Enum t, XMLSerializer w) throws AccessorException {
      this.baseXducer.declareNamespace(this.printMap.get(t), w);
   }

   public CharSequence print(Enum t) throws AccessorException {
      return this.baseXducer.print(this.printMap.get(t));
   }

   public Enum parse(CharSequence lexical) throws AccessorException, SAXException {
      B b = (B)this.baseXducer.parse(lexical);
      if (this.tokenStringType) {
         b = (B)((String)b).trim();
      }

      return (Enum)this.parseMap.get(b);
   }

   public void writeText(XMLSerializer w, Enum t, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      this.baseXducer.writeText(w, this.printMap.get(t), fieldName);
   }

   public void writeLeafElement(XMLSerializer w, Name tagName, Enum o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      this.baseXducer.writeLeafElement(w, tagName, this.printMap.get(o), fieldName);
   }

   public QName getTypeName(Enum instance) {
      return null;
   }
}
