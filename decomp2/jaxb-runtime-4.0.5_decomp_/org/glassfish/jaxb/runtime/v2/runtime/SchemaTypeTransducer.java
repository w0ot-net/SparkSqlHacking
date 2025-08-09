package org.glassfish.jaxb.runtime.v2.runtime;

import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.xml.sax.SAXException;

public class SchemaTypeTransducer extends FilterTransducer {
   private final QName schemaType;

   public SchemaTypeTransducer(Transducer core, QName schemaType) {
      super(core);
      this.schemaType = schemaType;
   }

   public CharSequence print(Object o) throws AccessorException {
      XMLSerializer w = XMLSerializer.getInstance();
      QName old = w.setSchemaType(this.schemaType);

      CharSequence var4;
      try {
         var4 = this.core.print(o);
      } finally {
         w.setSchemaType(old);
      }

      return var4;
   }

   public void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      QName old = w.setSchemaType(this.schemaType);

      try {
         this.core.writeText(w, o, fieldName);
      } finally {
         w.setSchemaType(old);
      }

   }

   public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      QName old = w.setSchemaType(this.schemaType);

      try {
         this.core.writeLeafElement(w, tagName, o, fieldName);
      } finally {
         w.setSchemaType(old);
      }

   }
}
