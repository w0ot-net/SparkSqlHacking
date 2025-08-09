package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.MarshalException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.UnmarshalException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.glassfish.jaxb.runtime.api.TypeReference;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallerImpl;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

final class BridgeAdapter extends InternalBridge {
   private final InternalBridge core;
   private final Class adapter;

   public BridgeAdapter(InternalBridge core, Class adapter) {
      super(core.getContext());
      this.core = core;
      this.adapter = adapter;
   }

   public void marshal(Marshaller m, Object inMemory, XMLStreamWriter output) throws JAXBException {
      this.core.marshal(m, this.adaptM(m, inMemory), output);
   }

   public void marshal(Marshaller m, Object inMemory, OutputStream output, NamespaceContext nsc) throws JAXBException {
      this.core.marshal(m, this.adaptM(m, inMemory), output, nsc);
   }

   public void marshal(Marshaller m, Object inMemory, Node output) throws JAXBException {
      this.core.marshal(m, this.adaptM(m, inMemory), output);
   }

   public void marshal(Marshaller context, Object inMemory, ContentHandler contentHandler) throws JAXBException {
      this.core.marshal(context, this.adaptM(context, inMemory), contentHandler);
   }

   public void marshal(Marshaller context, Object inMemory, Result result) throws JAXBException {
      this.core.marshal(context, this.adaptM(context, inMemory), result);
   }

   private Object adaptM(Marshaller m, Object v) throws JAXBException {
      XMLSerializer serializer = ((MarshallerImpl)m).serializer;
      serializer.pushCoordinator();

      Object var4;
      try {
         var4 = this._adaptM(serializer, v);
      } finally {
         serializer.popCoordinator();
      }

      return var4;
   }

   private Object _adaptM(XMLSerializer serializer, Object v) throws MarshalException {
      XmlAdapter<OnWire, InMemory> a = serializer.getAdapter(this.adapter);

      try {
         return a.marshal(v);
      } catch (Exception e) {
         serializer.handleError(e, v, (String)null);
         throw new MarshalException(e);
      }
   }

   @NotNull
   public Object unmarshal(Unmarshaller u, XMLStreamReader in) throws JAXBException {
      return this.adaptU(u, this.core.unmarshal(u, in));
   }

   @NotNull
   public Object unmarshal(Unmarshaller u, Source in) throws JAXBException {
      return this.adaptU(u, this.core.unmarshal(u, in));
   }

   @NotNull
   public Object unmarshal(Unmarshaller u, InputStream in) throws JAXBException {
      return this.adaptU(u, this.core.unmarshal(u, in));
   }

   @NotNull
   public Object unmarshal(Unmarshaller u, Node n) throws JAXBException {
      return this.adaptU(u, this.core.unmarshal(u, n));
   }

   public TypeReference getTypeReference() {
      return this.core.getTypeReference();
   }

   @NotNull
   private Object adaptU(Unmarshaller _u, Object v) throws JAXBException {
      UnmarshallerImpl u = (UnmarshallerImpl)_u;
      XmlAdapter<OnWire, InMemory> a = u.coordinator.getAdapter(this.adapter);
      u.coordinator.pushCoordinator();

      Object var5;
      try {
         var5 = a.unmarshal(v);
      } catch (Exception e) {
         throw new UnmarshalException(e);
      } finally {
         u.coordinator.popCoordinator();
      }

      return var5;
   }

   void marshal(Object o, XMLSerializer out) throws IOException, SAXException, XMLStreamException {
      try {
         this.core.marshal(this._adaptM(XMLSerializer.getInstance(), o), out);
      } catch (MarshalException var4) {
      }

   }
}
