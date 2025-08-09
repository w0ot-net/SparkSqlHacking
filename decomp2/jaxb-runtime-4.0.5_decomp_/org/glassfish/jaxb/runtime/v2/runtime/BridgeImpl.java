package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.glassfish.jaxb.core.marshaller.SAX2DOMEx;
import org.glassfish.jaxb.runtime.api.TypeReference;
import org.glassfish.jaxb.runtime.v2.runtime.output.SAXOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.XMLStreamWriterOutput;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallerImpl;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

final class BridgeImpl extends InternalBridge {
   private final Name tagName;
   private final JaxBeanInfo bi;
   private final TypeReference typeRef;

   public BridgeImpl(JAXBContextImpl context, Name tagName, JaxBeanInfo bi, TypeReference typeRef) {
      super(context);
      this.tagName = tagName;
      this.bi = bi;
      this.typeRef = typeRef;
   }

   public void marshal(Marshaller _m, Object t, XMLStreamWriter output) throws JAXBException {
      MarshallerImpl m = (MarshallerImpl)_m;
      m.write(this.tagName, this.bi, t, XMLStreamWriterOutput.create(output, this.context, m.getEscapeHandler()), new StAXPostInitAction(output, m.serializer));
   }

   public void marshal(Marshaller _m, Object t, OutputStream output, NamespaceContext nsContext) throws JAXBException {
      MarshallerImpl m = (MarshallerImpl)_m;
      Runnable pia = null;
      if (nsContext != null) {
         pia = new StAXPostInitAction(nsContext, m.serializer);
      }

      m.write(this.tagName, this.bi, t, m.createWriter(output), pia);
   }

   public void marshal(Marshaller _m, Object t, Node output) throws JAXBException {
      MarshallerImpl m = (MarshallerImpl)_m;
      m.write(this.tagName, this.bi, t, new SAXOutput(new SAX2DOMEx(output)), new DomPostInitAction(output, m.serializer));
   }

   public void marshal(Marshaller _m, Object t, ContentHandler contentHandler) throws JAXBException {
      MarshallerImpl m = (MarshallerImpl)_m;
      m.write(this.tagName, this.bi, t, new SAXOutput(contentHandler), (Runnable)null);
   }

   public void marshal(Marshaller _m, Object t, Result result) throws JAXBException {
      MarshallerImpl m = (MarshallerImpl)_m;
      m.write(this.tagName, this.bi, t, m.createXmlOutput(result), m.createPostInitAction(result));
   }

   @NotNull
   public Object unmarshal(Unmarshaller _u, XMLStreamReader in) throws JAXBException {
      UnmarshallerImpl u = (UnmarshallerImpl)_u;
      return ((JAXBElement)u.unmarshal0(in, this.bi)).getValue();
   }

   @NotNull
   public Object unmarshal(Unmarshaller _u, Source in) throws JAXBException {
      UnmarshallerImpl u = (UnmarshallerImpl)_u;
      return ((JAXBElement)u.unmarshal0(in, this.bi)).getValue();
   }

   @NotNull
   public Object unmarshal(Unmarshaller _u, InputStream in) throws JAXBException {
      UnmarshallerImpl u = (UnmarshallerImpl)_u;
      return ((JAXBElement)u.unmarshal0(in, this.bi)).getValue();
   }

   @NotNull
   public Object unmarshal(Unmarshaller _u, Node n) throws JAXBException {
      UnmarshallerImpl u = (UnmarshallerImpl)_u;
      return ((JAXBElement)u.unmarshal0(n, this.bi)).getValue();
   }

   public TypeReference getTypeReference() {
      return this.typeRef;
   }

   public void marshal(Object value, XMLSerializer out) throws IOException, SAXException, XMLStreamException {
      out.startElement(this.tagName, (Object)null);
      if (value == null) {
         out.writeXsiNilTrue();
      } else {
         out.childAsXsiType(value, (String)null, this.bi, false);
      }

      out.endElement();
   }
}
