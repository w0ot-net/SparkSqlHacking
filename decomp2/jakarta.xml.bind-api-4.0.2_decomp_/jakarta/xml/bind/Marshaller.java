package jakarta.xml.bind;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.attachment.AttachmentMarshaller;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.validation.Schema;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

public interface Marshaller {
   String JAXB_ENCODING = "jaxb.encoding";
   String JAXB_FORMATTED_OUTPUT = "jaxb.formatted.output";
   String JAXB_SCHEMA_LOCATION = "jaxb.schemaLocation";
   String JAXB_NO_NAMESPACE_SCHEMA_LOCATION = "jaxb.noNamespaceSchemaLocation";
   String JAXB_FRAGMENT = "jaxb.fragment";

   void marshal(Object var1, Result var2) throws JAXBException;

   void marshal(Object var1, OutputStream var2) throws JAXBException;

   void marshal(Object var1, File var2) throws JAXBException;

   void marshal(Object var1, Writer var2) throws JAXBException;

   void marshal(Object var1, ContentHandler var2) throws JAXBException;

   void marshal(Object var1, Node var2) throws JAXBException;

   void marshal(Object var1, XMLStreamWriter var2) throws JAXBException;

   void marshal(Object var1, XMLEventWriter var2) throws JAXBException;

   Node getNode(Object var1) throws JAXBException;

   void setProperty(String var1, Object var2) throws PropertyException;

   Object getProperty(String var1) throws PropertyException;

   void setEventHandler(ValidationEventHandler var1) throws JAXBException;

   ValidationEventHandler getEventHandler() throws JAXBException;

   void setAdapter(XmlAdapter var1);

   void setAdapter(Class var1, XmlAdapter var2);

   XmlAdapter getAdapter(Class var1);

   void setAttachmentMarshaller(AttachmentMarshaller var1);

   AttachmentMarshaller getAttachmentMarshaller();

   void setSchema(Schema var1);

   Schema getSchema();

   void setListener(Listener var1);

   Listener getListener();

   public abstract static class Listener {
      protected Listener() {
      }

      public void beforeMarshal(Object source) {
      }

      public void afterMarshal(Object source) {
      }
   }
}
