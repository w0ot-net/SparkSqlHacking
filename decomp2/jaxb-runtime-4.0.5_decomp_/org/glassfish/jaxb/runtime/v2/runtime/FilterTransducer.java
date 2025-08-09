package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.xml.sax.SAXException;

public abstract class FilterTransducer implements Transducer {
   protected final Transducer core;

   protected FilterTransducer(Transducer core) {
      this.core = core;
   }

   public boolean useNamespace() {
      return this.core.useNamespace();
   }

   public void declareNamespace(Object o, XMLSerializer w) throws AccessorException {
      this.core.declareNamespace(o, w);
   }

   @NotNull
   public CharSequence print(@NotNull Object o) throws AccessorException {
      return this.core.print(o);
   }

   public Object parse(CharSequence lexical) throws AccessorException, SAXException {
      return this.core.parse(lexical);
   }

   public void writeText(XMLSerializer w, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      this.core.writeText(w, o, fieldName);
   }

   public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
      this.core.writeLeafElement(w, tagName, o, fieldName);
   }

   public QName getTypeName(Object instance) {
      return null;
   }
}
