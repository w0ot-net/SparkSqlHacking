package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.DatatypeConverterImpl;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.DefaultTransducedAccessor;
import org.xml.sax.SAXException;

public final class TransducedAccessor_field_Integer extends DefaultTransducedAccessor {
   public String print(Object o) {
      return DatatypeConverterImpl._printInt(((Bean)o).f_int);
   }

   public void parse(Object o, CharSequence lexical) {
      ((Bean)o).f_int = DatatypeConverterImpl._parseInt(lexical);
   }

   public boolean hasValue(Object o) {
      return true;
   }

   public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws SAXException, AccessorException, IOException, XMLStreamException {
      w.leafElement(tagName, ((Bean)o).f_int, fieldName);
   }
}
