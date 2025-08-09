package jakarta.xml.bind;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.attachment.AttachmentUnmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public interface Unmarshaller {
   Object unmarshal(File var1) throws JAXBException;

   Object unmarshal(InputStream var1) throws JAXBException;

   Object unmarshal(Reader var1) throws JAXBException;

   Object unmarshal(URL var1) throws JAXBException;

   Object unmarshal(InputSource var1) throws JAXBException;

   Object unmarshal(Node var1) throws JAXBException;

   JAXBElement unmarshal(Node var1, Class var2) throws JAXBException;

   Object unmarshal(Source var1) throws JAXBException;

   JAXBElement unmarshal(Source var1, Class var2) throws JAXBException;

   Object unmarshal(XMLStreamReader var1) throws JAXBException;

   JAXBElement unmarshal(XMLStreamReader var1, Class var2) throws JAXBException;

   Object unmarshal(XMLEventReader var1) throws JAXBException;

   JAXBElement unmarshal(XMLEventReader var1, Class var2) throws JAXBException;

   UnmarshallerHandler getUnmarshallerHandler();

   void setEventHandler(ValidationEventHandler var1) throws JAXBException;

   ValidationEventHandler getEventHandler() throws JAXBException;

   void setProperty(String var1, Object var2) throws PropertyException;

   Object getProperty(String var1) throws PropertyException;

   void setSchema(Schema var1);

   Schema getSchema();

   void setAdapter(XmlAdapter var1);

   void setAdapter(Class var1, XmlAdapter var2);

   XmlAdapter getAdapter(Class var1);

   void setAttachmentUnmarshaller(AttachmentUnmarshaller var1);

   AttachmentUnmarshaller getAttachmentUnmarshaller();

   void setListener(Listener var1);

   Listener getListener();

   public abstract static class Listener {
      protected Listener() {
      }

      public void beforeUnmarshal(Object target, Object parent) {
      }

      public void afterUnmarshal(Object target, Object parent) {
      }
   }
}
