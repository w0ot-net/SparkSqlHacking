package org.apache.ws.commons.schema;

import org.w3c.dom.Element;

public abstract class XmlSchemaFacet extends XmlSchemaAnnotated {
   boolean fixed;
   Object value;

   protected XmlSchemaFacet() {
   }

   protected XmlSchemaFacet(Object value, boolean fixed) {
      this.value = value;
      this.fixed = fixed;
   }

   public static XmlSchemaFacet construct(Element el) {
      String name = el.getLocalName();
      boolean fixed = false;
      if (el.getAttribute("fixed").equals("true")) {
         fixed = true;
      }

      XmlSchemaFacet facet;
      if ("enumeration".equals(name)) {
         facet = new XmlSchemaEnumerationFacet();
      } else if ("fractionDigits".equals(name)) {
         facet = new XmlSchemaFractionDigitsFacet();
      } else if ("length".equals(name)) {
         facet = new XmlSchemaLengthFacet();
      } else if ("maxExclusive".equals(name)) {
         facet = new XmlSchemaMaxExclusiveFacet();
      } else if ("maxInclusive".equals(name)) {
         facet = new XmlSchemaMaxInclusiveFacet();
      } else if ("maxLength".equals(name)) {
         facet = new XmlSchemaMaxLengthFacet();
      } else if ("minLength".equals(name)) {
         facet = new XmlSchemaMinLengthFacet();
      } else if ("minExclusive".equals(name)) {
         facet = new XmlSchemaMinExclusiveFacet();
      } else if ("minInclusive".equals(name)) {
         facet = new XmlSchemaMinInclusiveFacet();
      } else if ("pattern".equals(name)) {
         facet = new XmlSchemaPatternFacet();
      } else if ("totalDigits".equals(name)) {
         facet = new XmlSchemaTotalDigitsFacet();
      } else {
         if (!"whiteSpace".equals(name)) {
            throw new XmlSchemaException("Incorrect facet with name \"" + name + "\" found.");
         }

         facet = new XmlSchemaWhiteSpaceFacet();
      }

      if (el.hasAttribute("id")) {
         facet.setId(el.getAttribute("id"));
      }

      facet.setFixed(fixed);
      facet.setValue(el.getAttribute("value"));
      return facet;
   }

   public Object getValue() {
      return this.value;
   }

   public boolean isFixed() {
      return this.fixed;
   }

   public void setFixed(boolean fixed) {
      this.fixed = fixed;
   }

   public void setValue(Object value) {
      this.value = value;
   }
}
