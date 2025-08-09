package org.apache.ws.commons.schema.utils;

import java.util.Arrays;
import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaException;
import org.apache.ws.commons.schema.XmlSchemaForm;

public class XmlSchemaNamedWithFormImpl extends XmlSchemaNamedImpl implements XmlSchemaNamedWithForm {
   private XmlSchemaForm form;
   private boolean element;
   private QName wireName;

   public XmlSchemaNamedWithFormImpl(XmlSchema parent, boolean topLevel, boolean element) {
      super(parent, topLevel);
      this.form = XmlSchemaForm.NONE;
      this.element = element;
   }

   public boolean equals(Object what) {
      boolean parentCheck = super.equals(what);
      if (!parentCheck) {
         return false;
      } else if (!(what instanceof XmlSchemaNamedWithFormImpl)) {
         return false;
      } else {
         XmlSchemaNamedWithFormImpl xsn = (XmlSchemaNamedWithFormImpl)what;
         boolean isElementEq = this.element == xsn.element;
         boolean isFormEq = UtilObjects.equals(this.form, xsn.form);
         boolean isWireNameEq = UtilObjects.equals(this.wireName, xsn.wireName);
         return isElementEq && isFormEq && isWireNameEq;
      }
   }

   public int hashCode() {
      int hash = Arrays.hashCode(new Object[]{this.form, this.wireName});
      hash += this.element ? 47 : 13;
      hash ^= super.hashCode();
      return hash;
   }

   public XmlSchemaForm getForm() {
      if (this.form != XmlSchemaForm.NONE) {
         return this.form;
      } else {
         return this.element ? this.parentSchema.getElementFormDefault() : this.parentSchema.getAttributeFormDefault();
      }
   }

   public boolean isFormSpecified() {
      return this.form != XmlSchemaForm.NONE;
   }

   public void setForm(XmlSchemaForm form) {
      if (form == null) {
         throw new XmlSchemaException("form may not be null. Pass XmlSchemaForm.NONE to use schema default.");
      } else {
         this.form = form;
         this.setName(this.getName());
      }
   }

   public void setName(String name) {
      super.setName(name);
      if (this.getForm() == XmlSchemaForm.QUALIFIED) {
         this.wireName = this.getQName();
      } else {
         this.wireName = new QName("", this.getName());
      }

   }

   public QName getWireName() {
      return this.refTwin != null && this.refTwin.getTargetQName() != null ? this.refTwin.getTargetQName() : this.wireName;
   }
}
