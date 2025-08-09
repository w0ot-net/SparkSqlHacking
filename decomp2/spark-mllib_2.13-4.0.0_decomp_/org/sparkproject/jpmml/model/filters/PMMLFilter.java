package org.sparkproject.jpmml.model.filters;

import java.util.Objects;
import org.sparkproject.dmg.pmml.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

public abstract class PMMLFilter extends XMLFilterImpl {
   private String sourceNamespaceURI = null;
   private Version source = null;
   private Version target = null;

   public PMMLFilter(Version target) {
      this.setTarget(target);
   }

   public PMMLFilter(XMLReader reader, Version target) {
      super(reader);
      this.setTarget(target);
   }

   public abstract String filterLocalName(String var1);

   public abstract Attributes filterAttributes(String var1, Attributes var2);

   public void startPrefixMapping(String prefix, String namespaceURI) throws SAXException {
      if ("".equals(prefix)) {
         this.updateSource(namespaceURI);
         super.startPrefixMapping("", this.getNamespaceURI());
      } else {
         super.startPrefixMapping(prefix, namespaceURI);
      }
   }

   public void endPrefixMapping(String prefix) throws SAXException {
      super.endPrefixMapping(prefix);
   }

   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
      if (this.isFilterable(namespaceURI)) {
         this.updateSource(namespaceURI);
         String filteredLocalName = this.filterLocalName(localName);
         String filteredQualifiedName = "".equals(qualifiedName) ? "" : filteredLocalName;
         Attributes filteredAttributes = this.filterAttributes(localName, attributes);
         super.startElement(this.getNamespaceURI(), filteredLocalName, filteredQualifiedName, filteredAttributes);
      } else {
         super.startElement(namespaceURI, localName, qualifiedName, attributes);
      }
   }

   public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
      if (this.isFilterable(namespaceURI)) {
         String filteredLocalName = this.filterLocalName(localName);
         String filteredQualifiedName = "".equals(qualifiedName) ? "" : filteredLocalName;
         super.endElement(this.getNamespaceURI(), filteredLocalName, filteredQualifiedName);
      } else {
         super.endElement(namespaceURI, localName, qualifiedName);
      }
   }

   private boolean isFilterable(String namespaceURI) {
      if ("".equals(namespaceURI)) {
         return true;
      } else {
         return this.sourceNamespaceURI != null && this.sourceNamespaceURI.equals(namespaceURI) ? true : namespaceURI.startsWith("http://www.dmg.org/PMML-");
      }
   }

   private String getNamespaceURI() {
      Version target = this.getTarget();
      return target.getNamespaceURI();
   }

   private void updateSource(String namespaceURI) {
      if (!"".equals(namespaceURI)) {
         if (this.sourceNamespaceURI == null || !this.sourceNamespaceURI.equals(namespaceURI)) {
            Version version = Version.forNamespaceURI(namespaceURI);
            Version source = this.getSource();
            if (source != null && !source.equals(version)) {
               throw new IllegalStateException();
            } else {
               this.sourceNamespaceURI = namespaceURI;
               this.setSource(version);
            }
         }
      }
   }

   public Version getSource() {
      return this.source;
   }

   private void setSource(Version source) {
      this.source = source;
   }

   public Version getTarget() {
      return this.target;
   }

   private void setTarget(Version target) {
      this.target = (Version)Objects.requireNonNull(target);
   }

   protected static boolean hasAttribute(Attributes attributes, String localName) {
      int index = attributes.getIndex("", localName);
      return index > -1;
   }

   protected static String getAttribute(Attributes attributes, String localName) {
      return attributes.getValue("", localName);
   }

   protected static Attributes setAttribute(Attributes attributes, String localName, String value) {
      int index = attributes.getIndex("", localName);
      AttributesImpl result = new AttributesImpl(attributes);
      if (index < 0) {
         result.addAttribute("", localName, "", "CDATA", value);
      } else {
         result.setValue(index, value);
      }

      return result;
   }

   protected static Attributes renameAttribute(Attributes attributes, String oldLocalName, String localName) {
      int index = attributes.getIndex("", oldLocalName);
      if (index < 0) {
         return attributes;
      } else {
         AttributesImpl result = new AttributesImpl(attributes);
         result.setLocalName(index, localName);
         result.setQName(index, localName);
         return result;
      }
   }

   protected static Attributes removeAttribute(Attributes attributes, String localName) {
      int index = attributes.getIndex("", localName);
      if (index < 0) {
         return attributes;
      } else {
         AttributesImpl result = new AttributesImpl(attributes);
         result.removeAttribute(index);
         return result;
      }
   }
}
