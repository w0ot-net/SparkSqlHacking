package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import javax.xml.stream.XMLStreamException;
import org.jvnet.staxex.XMLStreamReaderEx;
import org.xml.sax.SAXException;

final class StAXExConnector extends StAXStreamConnector {
   private final XMLStreamReaderEx in;

   public StAXExConnector(XMLStreamReaderEx in, XmlVisitor visitor) {
      super(in, visitor);
      this.in = in;
   }

   protected void handleCharacters() throws XMLStreamException, SAXException {
      if (this.predictor.expectText()) {
         CharSequence pcdata = this.in.getPCDATA();
         if (pcdata instanceof org.jvnet.staxex.Base64Data) {
            org.jvnet.staxex.Base64Data bd = (org.jvnet.staxex.Base64Data)pcdata;
            Base64Data binary = new Base64Data();
            if (!bd.hasData()) {
               binary.set(bd.getDataHandler());
            } else {
               binary.set(bd.get(), bd.getDataLen(), bd.getMimeType());
            }

            this.visitor.text(binary);
            this.textReported = true;
         } else {
            this.buffer.append(pcdata);
         }
      }

   }
}
