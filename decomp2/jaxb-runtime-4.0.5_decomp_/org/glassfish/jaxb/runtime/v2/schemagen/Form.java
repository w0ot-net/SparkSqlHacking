package org.glassfish.jaxb.runtime.v2.schemagen;

import com.sun.xml.txw2.TypedXmlWriter;
import jakarta.xml.bind.annotation.XmlNsForm;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.LocalAttribute;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.LocalElement;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Schema;

enum Form {
   QUALIFIED(XmlNsForm.QUALIFIED, true) {
      void declare(String attName, Schema schema) {
         schema._attribute(attName, "qualified");
      }
   },
   UNQUALIFIED(XmlNsForm.UNQUALIFIED, false) {
      void declare(String attName, Schema schema) {
         schema._attribute(attName, "unqualified");
      }
   },
   UNSET(XmlNsForm.UNSET, false) {
      void declare(String attName, Schema schema) {
      }
   };

   private final XmlNsForm xnf;
   public final boolean isEffectivelyQualified;

   private Form(XmlNsForm xnf, boolean effectivelyQualified) {
      this.xnf = xnf;
      this.isEffectivelyQualified = effectivelyQualified;
   }

   abstract void declare(String var1, Schema var2);

   public void writeForm(LocalElement e, QName tagName) {
      this._writeForm(e, tagName);
   }

   public void writeForm(LocalAttribute a, QName tagName) {
      this._writeForm(a, tagName);
   }

   private void _writeForm(TypedXmlWriter e, QName tagName) {
      boolean qualified = tagName.getNamespaceURI().length() > 0;
      if (qualified && this != QUALIFIED) {
         e._attribute("form", "qualified");
      } else if (!qualified && this == QUALIFIED) {
         e._attribute("form", "unqualified");
      }

   }

   public static Form get(XmlNsForm xnf) {
      for(Form v : values()) {
         if (v.xnf == xnf) {
            return v;
         }
      }

      throw new IllegalArgumentException();
   }
}
