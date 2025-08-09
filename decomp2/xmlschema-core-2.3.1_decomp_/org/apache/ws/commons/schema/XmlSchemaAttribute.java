package org.apache.ws.commons.schema;

import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.utils.CollectionFactory;
import org.apache.ws.commons.schema.utils.XmlSchemaNamedWithForm;
import org.apache.ws.commons.schema.utils.XmlSchemaNamedWithFormImpl;
import org.apache.ws.commons.schema.utils.XmlSchemaRef;
import org.apache.ws.commons.schema.utils.XmlSchemaRefBase;

public class XmlSchemaAttribute extends XmlSchemaAttributeOrGroupRef implements XmlSchemaNamedWithForm, XmlSchemaAttributeGroupMember, XmlSchemaItemWithRef {
   private String defaultValue;
   private String fixedValue;
   private XmlSchemaSimpleType schemaType;
   private QName schemaTypeName;
   private XmlSchemaUse use;
   private XmlSchemaNamedWithFormImpl namedDelegate;
   private XmlSchemaRef ref;

   public XmlSchemaAttribute(final XmlSchema schema, boolean topLevel) {
      this.namedDelegate = new XmlSchemaNamedWithFormImpl(schema, topLevel, false);
      this.ref = new XmlSchemaRef(schema, XmlSchemaAttribute.class);
      this.namedDelegate.setRefObject(this.ref);
      this.ref.setNamedObject(this.namedDelegate);
      this.use = XmlSchemaUse.NONE;
      if (topLevel) {
         CollectionFactory.withSchemaModifiable(new Runnable() {
            public void run() {
               schema.getItems().add(XmlSchemaAttribute.this);
            }
         });
      }

   }

   public String getDefaultValue() {
      return this.defaultValue;
   }

   public void setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
   }

   public String getFixedValue() {
      return this.fixedValue;
   }

   public void setFixedValue(String fixedValue) {
      this.fixedValue = fixedValue;
   }

   public XmlSchemaRef getRef() {
      return this.ref;
   }

   public XmlSchemaSimpleType getSchemaType() {
      return this.schemaType;
   }

   public void setSchemaType(XmlSchemaSimpleType schemaType) {
      this.schemaType = schemaType;
   }

   public QName getSchemaTypeName() {
      return this.schemaTypeName;
   }

   public void setSchemaTypeName(QName schemaTypeName) {
      this.schemaTypeName = schemaTypeName;
   }

   public XmlSchemaUse getUse() {
      return this.use;
   }

   public void setUse(XmlSchemaUse use) {
      if (this.namedDelegate.isTopLevel() && use != null) {
         throw new XmlSchemaException("Top-level attributes may not have a 'use'");
      } else {
         this.use = use;
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

   public void setName(final String name) {
      CollectionFactory.withSchemaModifiable(new Runnable() {
         public void run() {
            if (XmlSchemaAttribute.this.namedDelegate.isTopLevel() && XmlSchemaAttribute.this.namedDelegate.getName() != null) {
               XmlSchemaAttribute.this.namedDelegate.getParent().getAttributes().remove(XmlSchemaAttribute.this.getQName());
            }

            XmlSchemaAttribute.this.namedDelegate.setName(name);
            if (XmlSchemaAttribute.this.namedDelegate.isTopLevel()) {
               if (name == null) {
                  throw new XmlSchemaException("Top-level attributes may not be anonymous");
               }

               XmlSchemaAttribute.this.namedDelegate.getParent().getAttributes().put(XmlSchemaAttribute.this.getQName(), XmlSchemaAttribute.this);
            }

         }
      });
   }

   public boolean isFormSpecified() {
      return this.namedDelegate.isFormSpecified();
   }

   public XmlSchemaForm getForm() {
      return this.namedDelegate.getForm();
   }

   public void setForm(XmlSchemaForm form) {
      if (this.namedDelegate.isTopLevel() && form != XmlSchemaForm.NONE) {
         throw new XmlSchemaException("Top-level attributes may not have a 'form'");
      } else {
         this.namedDelegate.setForm(form);
      }
   }

   public QName getWireName() {
      return this.namedDelegate.getWireName();
   }

   public boolean isRef() {
      return this.ref.getTargetQName() != null;
   }

   public QName getTargetQName() {
      return this.ref.getTargetQName();
   }

   public XmlSchemaRefBase getRefBase() {
      return this.ref;
   }
}
