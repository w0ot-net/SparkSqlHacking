package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.activation.MimeType;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.xml.sax.SAXException;

public final class MimeTypedTransducer extends FilterTransducer {
   private final MimeType expectedMimeType;

   public MimeTypedTransducer(Transducer core, MimeType expectedMimeType) {
      super(core);
      this.expectedMimeType = expectedMimeType;
   }

   public CharSequence print(Object o) throws AccessorException {
      XMLSerializer w = XMLSerializer.getInstance();
      MimeType old = w.setExpectedMimeType(this.expectedMimeType);

      CharSequence var4;
      try {
         var4 = this.core.print(o);
      } finally {
         w.setExpectedMimeType(old);
      }

      return var4;
   }

   public void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      MimeType old = w.setExpectedMimeType(this.expectedMimeType);

      try {
         this.core.writeText(w, o, fieldName);
      } finally {
         w.setExpectedMimeType(old);
      }

   }

   public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      MimeType old = w.setExpectedMimeType(this.expectedMimeType);

      try {
         this.core.writeLeafElement(w, tagName, o, fieldName);
      } finally {
         w.setExpectedMimeType(old);
      }

   }
}
