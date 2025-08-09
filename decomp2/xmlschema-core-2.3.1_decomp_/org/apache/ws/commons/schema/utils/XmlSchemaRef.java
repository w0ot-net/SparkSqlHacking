package org.apache.ws.commons.schema.utils;

import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaAttribute;
import org.apache.ws.commons.schema.XmlSchemaAttributeGroup;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaGroup;
import org.apache.ws.commons.schema.XmlSchemaNotation;
import org.apache.ws.commons.schema.XmlSchemaType;

public class XmlSchemaRef extends XmlSchemaRefBase {
   private Class targetClass;
   private XmlSchemaNamed targetObject;

   public XmlSchemaRef(XmlSchema parent, Class targetClass) {
      this.parent = parent;
      this.targetClass = targetClass;
   }

   protected void forgetTargetObject() {
      this.targetObject = null;
   }

   public XmlSchemaNamed getTarget() {
      if (this.targetObject == null && this.targetQName != null) {
         Class<?> cls = this.targetClass;
         XmlSchemaCollection parentCollection = this.parent.getParent();
         if (cls == XmlSchemaElement.class) {
            this.targetObject = (XmlSchemaNamed)this.targetClass.cast(parentCollection.getElementByQName(this.targetQName));
         } else if (cls == XmlSchemaAttribute.class) {
            this.targetObject = (XmlSchemaNamed)this.targetClass.cast(parentCollection.getAttributeByQName(this.targetQName));
         } else if (cls == XmlSchemaType.class) {
            this.targetObject = (XmlSchemaNamed)this.targetClass.cast(parentCollection.getTypeByQName(this.targetQName));
         } else if (cls == XmlSchemaAttributeGroup.class) {
            this.targetObject = (XmlSchemaNamed)this.targetClass.cast(parentCollection.getAttributeGroupByQName(this.targetQName));
         } else if (cls == XmlSchemaGroup.class) {
            this.targetObject = (XmlSchemaNamed)this.targetClass.cast(parentCollection.getGroupByQName(this.targetQName));
         } else if (cls == XmlSchemaNotation.class) {
            this.targetObject = (XmlSchemaNamed)this.targetClass.cast(parentCollection.getNotationByQName(this.targetQName));
         }
      }

      return this.targetObject;
   }

   public String toString() {
      return "XmlSchemaRef: " + this.targetClass.getName() + " " + this.targetQName;
   }
}
