package org.apache.ws.commons.schema.utils;

import javax.xml.namespace.QName;
import org.apache.ws.commons.schema.XmlSchemaForm;

public interface XmlSchemaNamedWithForm extends XmlSchemaNamed {
   XmlSchemaForm getForm();

   void setForm(XmlSchemaForm var1);

   boolean isFormSpecified();

   QName getWireName();
}
