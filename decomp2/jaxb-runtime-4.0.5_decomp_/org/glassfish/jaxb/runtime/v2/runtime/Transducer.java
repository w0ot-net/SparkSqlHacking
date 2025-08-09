package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.xml.sax.SAXException;

public interface Transducer {
   boolean useNamespace();

   void declareNamespace(Object var1, XMLSerializer var2) throws AccessorException;

   @NotNull
   CharSequence print(@NotNull Object var1) throws AccessorException;

   Object parse(CharSequence var1) throws AccessorException, SAXException;

   void writeText(XMLSerializer var1, Object var2, String var3) throws IOException, SAXException, XMLStreamException, AccessorException;

   void writeLeafElement(XMLSerializer var1, Name var2, @NotNull Object var3, String var4) throws IOException, SAXException, XMLStreamException, AccessorException;

   QName getTypeName(@NotNull Object var1);
}
