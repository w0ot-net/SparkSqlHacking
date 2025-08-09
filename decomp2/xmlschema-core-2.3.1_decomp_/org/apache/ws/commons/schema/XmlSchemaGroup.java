package org.apache.ws.commons.schema;

import java.util.Arrays;
import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.CollectionFactory;
import org.apache.ws.commons.schema.utils.UtilObjects;
import org.apache.ws.commons.schema.utils.XmlSchemaNamed;
import org.apache.ws.commons.schema.utils.XmlSchemaNamedImpl;

public class XmlSchemaGroup extends XmlSchemaAnnotated implements XmlSchemaNamed, XmlSchemaChoiceMember, XmlSchemaSequenceMember, XmlSchemaAllMember {
   private XmlSchemaGroupParticle particle;
   private XmlSchemaNamedImpl namedDelegate;

   public XmlSchemaGroup(final XmlSchema parent) {
      this.namedDelegate = new XmlSchemaNamedImpl(parent, true);
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            parent.getItems().add(XmlSchemaGroup.this);
         }
      });
   }

   public boolean equals(Object what) {
      boolean parentCheck = super.equals(what);
      if (!parentCheck) {
         return false;
      } else if (!(what instanceof XmlSchemaGroup)) {
         return false;
      } else {
         XmlSchemaGroup xsg = (XmlSchemaGroup)what;
         boolean isParticleEq = UtilObjects.equals(this.particle, xsg.particle);
         boolean isNamedDelegateEq = UtilObjects.equals(this.namedDelegate, xsg.namedDelegate);
         return isParticleEq && isNamedDelegateEq;
      }
   }

   public int hashCode() {
      int hash = Arrays.hashCode(new Object[]{this.particle, this.namedDelegate});
      hash ^= super.hashCode();
      return hash;
   }

   public XmlSchemaGroupParticle getParticle() {
      return this.particle;
   }

   public void setParticle(XmlSchemaGroupParticle particle) {
      this.particle = particle;
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
            if (XmlSchemaGroup.this.namedDelegate.getQName() != null) {
               XmlSchemaGroup.this.namedDelegate.getParent().getGroups().remove(XmlSchemaGroup.this.namedDelegate.getQName());
            }

            XmlSchemaGroup.this.namedDelegate.setName(name);
            if (name != null) {
               XmlSchemaGroup.this.namedDelegate.getParent().getGroups().put(XmlSchemaGroup.this.namedDelegate.getQName(), XmlSchemaGroup.this);
            }

         }
      });
   }
}
