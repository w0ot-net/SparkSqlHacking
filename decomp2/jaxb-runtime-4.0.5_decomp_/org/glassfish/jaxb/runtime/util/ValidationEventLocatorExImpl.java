package org.glassfish.jaxb.runtime.util;

import jakarta.xml.bind.helpers.ValidationEventLocatorImpl;
import org.glassfish.jaxb.runtime.ValidationEventLocatorEx;

public class ValidationEventLocatorExImpl extends ValidationEventLocatorImpl implements ValidationEventLocatorEx {
   private final String fieldName;

   public ValidationEventLocatorExImpl(Object target, String fieldName) {
      super(target);
      this.fieldName = fieldName;
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("[url=");
      buf.append(this.getURL());
      buf.append(",line=");
      buf.append(this.getLineNumber());
      buf.append(",column=");
      buf.append(this.getColumnNumber());
      buf.append(",node=");
      buf.append(this.getNode());
      buf.append(",object=");
      buf.append(this.getObject());
      buf.append(",field=");
      buf.append(this.getFieldName());
      buf.append("]");
      return buf.toString();
   }
}
