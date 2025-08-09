package org.apache.ws.commons.schema;

import java.util.Arrays;
import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.CollectionFactory;
import org.apache.ws.commons.schema.utils.UtilObjects;
import org.apache.ws.commons.schema.utils.XmlSchemaNamed;
import org.apache.ws.commons.schema.utils.XmlSchemaNamedImpl;

public abstract class XmlSchemaType extends XmlSchemaAnnotated implements XmlSchemaNamed {
   private XmlSchemaDerivationMethod deriveBy;
   private XmlSchemaDerivationMethod finalDerivation;
   private XmlSchemaDerivationMethod finalResolved;
   private boolean isMixed;
   private XmlSchemaNamedImpl namedDelegate;

   protected XmlSchemaType(final XmlSchema schema, boolean topLevel) {
      this.namedDelegate = new XmlSchemaNamedImpl(schema, topLevel);
      this.finalDerivation = XmlSchemaDerivationMethod.NONE;
      if (topLevel) {
         CollectionFactory.withSchemaModifiable(new Runnable() {
            public void run() {
               schema.getItems().add(XmlSchemaType.this);
            }
         });
      }

   }

   public boolean equals(Object what) {
      boolean parentCheck = super.equals(what);
      if (!parentCheck) {
         return false;
      } else if (!(what instanceof XmlSchemaType)) {
         return false;
      } else {
         XmlSchemaType xst = (XmlSchemaType)what;
         boolean isIsMixedEq = this.isMixed == xst.isMixed;
         boolean isDeriveByEq = UtilObjects.equals(this.deriveBy, xst.deriveBy);
         boolean isFinalDerivationEq = UtilObjects.equals(this.finalDerivation, xst.finalDerivation);
         boolean isFinalResolvedEq = UtilObjects.equals(this.finalResolved, xst.finalResolved);
         boolean isNamedDelegateEq = UtilObjects.equals(this.namedDelegate, xst.namedDelegate);
         return isIsMixedEq && isDeriveByEq && isFinalDerivationEq && isFinalResolvedEq && isNamedDelegateEq;
      }
   }

   public int hashCode() {
      int hash = Arrays.hashCode(new Object[]{this.deriveBy, this.finalDerivation, this.finalResolved, this.namedDelegate});
      hash += this.isMixed ? 29 : 83;
      hash ^= super.hashCode();
      return hash;
   }

   public XmlSchemaDerivationMethod getDeriveBy() {
      return this.deriveBy;
   }

   public XmlSchemaDerivationMethod getFinal() {
      return this.finalDerivation;
   }

   public void setFinal(XmlSchemaDerivationMethod finalDerivationValue) {
      this.finalDerivation = finalDerivationValue;
   }

   public XmlSchemaDerivationMethod getFinalResolved() {
      return this.finalResolved;
   }

   public boolean isMixed() {
      return this.isMixed;
   }

   public void setMixed(boolean isMixedValue) {
      this.isMixed = isMixedValue;
   }

   public String toString() {
      if (this.getName() == null) {
         return super.toString() + "[anonymous]";
      } else {
         return this.namedDelegate.getParent().getLogicalTargetNamespace() == null ? super.toString() + "[{}" + this.getName() + "]" : super.toString() + "[{" + this.namedDelegate.getParent().getLogicalTargetNamespace() + "}" + this.getName() + "]";
      }
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

   public void setName(String name) {
      if (this.isTopLevel() && name == null) {
         throw new XmlSchemaException("A non-top-level type may not be anonyous.");
      } else {
         if (this.namedDelegate.isTopLevel() && this.namedDelegate.getName() != null) {
            this.namedDelegate.getParent().getSchemaTypes().remove(this.getQName());
         }

         this.namedDelegate.setName(name);
         if (this.namedDelegate.isTopLevel()) {
            this.namedDelegate.getParent().getSchemaTypes().put(this.getQName(), this);
         }

      }
   }

   void setFinalResolved(XmlSchemaDerivationMethod finalResolved) {
      this.finalResolved = finalResolved;
   }

   public void setFinalDerivation(XmlSchemaDerivationMethod finalDerivation) {
      this.finalDerivation = finalDerivation;
   }

   public XmlSchemaDerivationMethod getFinalDerivation() {
      return this.finalDerivation;
   }

   public void setDeriveBy(XmlSchemaDerivationMethod deriveBy) {
      this.deriveBy = deriveBy;
   }
}
