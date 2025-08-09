package org.apache.ws.commons.schema;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.CollectionFactory;
import org.apache.ws.commons.schema.utils.XmlSchemaNamed;
import org.apache.ws.commons.schema.utils.XmlSchemaNamedImpl;

public class XmlSchemaAttributeGroup extends XmlSchemaAnnotated implements XmlSchemaNamed, XmlSchemaAttributeGroupMember {
   private XmlSchemaAnyAttribute anyAttribute;
   private List attributes;
   private XmlSchemaNamedImpl namedDelegate;

   public XmlSchemaAttributeGroup(final XmlSchema parent) {
      this.namedDelegate = new XmlSchemaNamedImpl(parent, true);
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            parent.getItems().add(XmlSchemaAttributeGroup.this);
         }
      });
      this.attributes = CollectionFactory.getList(XmlSchemaAttributeGroupMember.class);
   }

   public XmlSchemaAnyAttribute getAnyAttribute() {
      return this.anyAttribute;
   }

   public void setAnyAttribute(XmlSchemaAnyAttribute anyAttribute) {
      this.anyAttribute = anyAttribute;
   }

   public List getAttributes() {
      return this.attributes;
   }

   public String getName() {
      return this.namedDelegate.getName();
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

   public void setName(final String name) {
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            if (name != null) {
               XmlSchemaAttributeGroup.this.namedDelegate.getParent().getAttributeGroups().remove(XmlSchemaAttributeGroup.this.getQName());
            }

            XmlSchemaAttributeGroup.this.namedDelegate.setName(name);
            XmlSchemaAttributeGroup.this.namedDelegate.getParent().getAttributeGroups().put(XmlSchemaAttributeGroup.this.getQName(), XmlSchemaAttributeGroup.this);
         }
      });
   }
}
