package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.xml.sax.SAXException;

public class InlineBinaryTransducer extends FilterTransducer {
   public InlineBinaryTransducer(Transducer core) {
      super(core);
   }

   @NotNull
   public CharSequence print(@NotNull Object o) throws AccessorException {
      XMLSerializer w = XMLSerializer.getInstance();
      boolean old = w.setInlineBinaryFlag(true);

      CharSequence var4;
      try {
         var4 = this.core.print(o);
      } finally {
         w.setInlineBinaryFlag(old);
      }

      return var4;
   }

   public void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      boolean old = w.setInlineBinaryFlag(true);

      try {
         this.core.writeText(w, o, fieldName);
      } finally {
         w.setInlineBinaryFlag(old);
      }

   }

   public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      boolean old = w.setInlineBinaryFlag(true);

      try {
         this.core.writeLeafElement(w, tagName, o, fieldName);
      } finally {
         w.setInlineBinaryFlag(old);
      }

   }
}
