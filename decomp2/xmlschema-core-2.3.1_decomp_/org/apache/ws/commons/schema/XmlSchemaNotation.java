package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.CollectionFactory;
import org.apache.ws.commons.schema.utils.XmlSchemaNamed;
import org.apache.ws.commons.schema.utils.XmlSchemaNamedImpl;

public class XmlSchemaNotation extends XmlSchemaAnnotated implements XmlSchemaNamed {
   private String system;
   private String publicNotation;
   private XmlSchemaNamedImpl namedDelegate;

   public XmlSchemaNotation(final XmlSchema parent) {
      this.namedDelegate = new XmlSchemaNamedImpl(parent, true);
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            parent.getItems().add(XmlSchemaNotation.this);
         }
      });
   }

   public String getPublic() {
      return this.publicNotation;
   }

   public void setPublic(String isPublic) {
      this.publicNotation = isPublic;
   }

   public String getSystem() {
      return this.system;
   }

   public void setSystem(String system) {
      this.system = system;
   }

   public XmlSchema getParent() {
      return this.namedDelegate.getParent();
   }

   public QName getQName() {
      return this.namedDelegate.getQName();
   }

   public boolean isAnonymous() {
      return this.namedDelegate.isAnonymous();
   }

   public boolean isTopLevel() {
      return this.namedDelegate.isTopLevel();
   }

   public String toString() {
      return this.namedDelegate.toString();
   }

   void setPublicNotation(String publicNotation) {
      this.publicNotation = publicNotation;
   }

   String getPublicNotation() {
      return this.publicNotation;
   }

   public String getName() {
      return this.namedDelegate.getName();
   }

   public void setName(final String name) {
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            if (XmlSchemaNotation.this.namedDelegate.getName() != null) {
               XmlSchemaNotation.this.namedDelegate.getParent().getNotations().remove(XmlSchemaNotation.this.getQName());
            }

            XmlSchemaNotation.this.namedDelegate.setName(name);
            XmlSchemaNotation.this.namedDelegate.getParent().getNotations().put(XmlSchemaNotation.this.getQName(), XmlSchemaNotation.this);
         }
      });
   }
}
