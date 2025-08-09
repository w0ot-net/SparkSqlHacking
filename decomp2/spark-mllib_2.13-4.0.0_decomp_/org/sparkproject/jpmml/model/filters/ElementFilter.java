package org.sparkproject.jpmml.model.filters;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public abstract class ElementFilter extends XMLFilterImpl {
   private String namespaceURI;
   private String localName;

   public ElementFilter(String localName) {
      this(Version.PMML_4_4.getNamespaceURI(), localName);
   }

   public ElementFilter(String namespaceURI, String localName) {
      this.namespaceURI = null;
      this.localName = null;
      this.setNamespaceURI(namespaceURI);
      this.setLocalName(localName);
   }

   public ElementFilter(Class clazz) {
      this.namespaceURI = null;
      this.localName = null;
      XmlRootElement element = getElement(clazz);
      this.setNamespaceURI(element.namespace());
      this.setLocalName(element.name());
   }

   public ElementFilter(XMLReader reader, String localName) {
      this(reader, Version.PMML_4_4.getNamespaceURI(), localName);
   }

   public ElementFilter(XMLReader reader, String namespaceURI, String localName) {
      super(reader);
      this.namespaceURI = null;
      this.localName = null;
      this.setNamespaceURI(namespaceURI);
      this.setLocalName(localName);
   }

   public ElementFilter(XMLReader reader, Class clazz) {
      super(reader);
      this.namespaceURI = null;
      this.localName = null;
      XmlRootElement element = getElement(clazz);
      this.setNamespaceURI(element.namespace());
      this.setLocalName(element.name());
   }

   public boolean matches(String namespaceURI, String localName) {
      return equals(this.getNamespaceURI(), namespaceURI) && equals(this.getLocalName(), localName);
   }

   public String getQualifiedName() {
      String namespaceURI = this.getNamespaceURI();
      String localName = this.getLocalName();
      return !"*".equals(namespaceURI) && !"*".equals(localName) ? namespaceURI + ":" + localName : localName;
   }

   public String getNamespaceURI() {
      return this.namespaceURI;
   }

   private void setNamespaceURI(String namespaceURI) {
      this.namespaceURI = (String)Objects.requireNonNull(namespaceURI);
   }

   public String getLocalName() {
      return this.localName;
   }

   private void setLocalName(String localName) {
      this.localName = (String)Objects.requireNonNull(localName);
   }

   private static XmlRootElement getElement(Class clazz) {
      XmlRootElement result = (XmlRootElement)clazz.getAnnotation(XmlRootElement.class);
      if (result == null) {
         throw new IllegalArgumentException();
      } else {
         return result;
      }
   }

   private static boolean equals(String left, String right) {
      return Objects.equals(left, right) || Objects.equals(left, "*");
   }
}
