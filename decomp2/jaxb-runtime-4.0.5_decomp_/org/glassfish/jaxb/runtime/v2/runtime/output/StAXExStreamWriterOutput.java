package org.glassfish.jaxb.runtime.v2.runtime.output;

import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.marshaller.NoEscapeHandler;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Base64Data;
import org.jvnet.staxex.XMLStreamWriterEx;

public final class StAXExStreamWriterOutput extends XMLStreamWriterOutput {
   private final XMLStreamWriterEx out;

   public StAXExStreamWriterOutput(XMLStreamWriterEx out) {
      super(out, NoEscapeHandler.theInstance);
      this.out = out;
   }

   public void text(Pcdata value, boolean needsSeparatingWhitespace) throws XMLStreamException {
      if (needsSeparatingWhitespace) {
         this.out.writeCharacters(" ");
      }

      if (!(value instanceof Base64Data)) {
         this.out.writeCharacters(value.toString());
      } else {
         Base64Data v = (Base64Data)value;
         this.out.writeBinary(v.getDataHandler());
      }

   }
}
