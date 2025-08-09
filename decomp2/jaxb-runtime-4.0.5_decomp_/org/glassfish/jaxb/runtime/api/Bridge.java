package org.glassfish.jaxb.runtime.api;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.attachment.AttachmentMarshaller;
import jakarta.xml.bind.attachment.AttachmentUnmarshaller;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

public abstract class Bridge {
   protected final JAXBContextImpl context;

   protected Bridge(JAXBContextImpl context) {
      this.context = context;
   }

   @NotNull
   public JAXBRIContext getContext() {
      return this.context;
   }

   public final void marshal(Object object, XMLStreamWriter output) throws JAXBException {
      this.marshal((Object)object, (XMLStreamWriter)output, (AttachmentMarshaller)null);
   }

   public final void marshal(Object object, XMLStreamWriter output, AttachmentMarshaller am) throws JAXBException {
      Marshaller m = (Marshaller)this.context.marshallerPool.take();
      m.setAttachmentMarshaller(am);
      this.marshal(m, object, output);
      m.setAttachmentMarshaller((AttachmentMarshaller)null);
      this.context.marshallerPool.recycle(m);
   }

   public abstract void marshal(@NotNull Marshaller var1, Object var2, XMLStreamWriter var3) throws JAXBException;

   public void marshal(Object object, OutputStream output, NamespaceContext nsContext) throws JAXBException {
      this.marshal((Object)object, (OutputStream)output, (NamespaceContext)nsContext, (AttachmentMarshaller)null);
   }

   public void marshal(Object object, OutputStream output, NamespaceContext nsContext, AttachmentMarshaller am) throws JAXBException {
      Marshaller m = (Marshaller)this.context.marshallerPool.take();
      m.setAttachmentMarshaller(am);
      this.marshal(m, object, output, nsContext);
      m.setAttachmentMarshaller((AttachmentMarshaller)null);
      this.context.marshallerPool.recycle(m);
   }

   public abstract void marshal(@NotNull Marshaller var1, Object var2, OutputStream var3, NamespaceContext var4) throws JAXBException;

   public final void marshal(Object object, Node output) throws JAXBException {
      Marshaller m = (Marshaller)this.context.marshallerPool.take();
      this.marshal(m, object, output);
      this.context.marshallerPool.recycle(m);
   }

   public abstract void marshal(@NotNull Marshaller var1, Object var2, Node var3) throws JAXBException;

   public final void marshal(Object object, ContentHandler contentHandler) throws JAXBException {
      this.marshal((Object)object, (ContentHandler)contentHandler, (AttachmentMarshaller)null);
   }

   public final void marshal(Object object, ContentHandler contentHandler, AttachmentMarshaller am) throws JAXBException {
      Marshaller m = (Marshaller)this.context.marshallerPool.take();
      m.setAttachmentMarshaller(am);
      this.marshal(m, object, contentHandler);
      m.setAttachmentMarshaller((AttachmentMarshaller)null);
      this.context.marshallerPool.recycle(m);
   }

   public abstract void marshal(@NotNull Marshaller var1, Object var2, ContentHandler var3) throws JAXBException;

   public final void marshal(Object object, Result result) throws JAXBException {
      Marshaller m = (Marshaller)this.context.marshallerPool.take();
      this.marshal(m, object, result);
      this.context.marshallerPool.recycle(m);
   }

   public abstract void marshal(@NotNull Marshaller var1, Object var2, Result var3) throws JAXBException;

   private Object exit(Object r, Unmarshaller u) {
      u.setAttachmentUnmarshaller((AttachmentUnmarshaller)null);
      this.context.unmarshallerPool.recycle(u);
      return r;
   }

   @NotNull
   public final Object unmarshal(@NotNull XMLStreamReader in) throws JAXBException {
      return this.unmarshal((XMLStreamReader)in, (AttachmentUnmarshaller)null);
   }

   @NotNull
   public final Object unmarshal(@NotNull XMLStreamReader in, @Nullable AttachmentUnmarshaller au) throws JAXBException {
      Unmarshaller u = (Unmarshaller)this.context.unmarshallerPool.take();
      u.setAttachmentUnmarshaller(au);
      return this.exit(this.unmarshal(u, in), u);
   }

   @NotNull
   public abstract Object unmarshal(@NotNull Unmarshaller var1, @NotNull XMLStreamReader var2) throws JAXBException;

   @NotNull
   public final Object unmarshal(@NotNull Source in) throws JAXBException {
      return this.unmarshal((Source)in, (AttachmentUnmarshaller)null);
   }

   @NotNull
   public final Object unmarshal(@NotNull Source in, @Nullable AttachmentUnmarshaller au) throws JAXBException {
      Unmarshaller u = (Unmarshaller)this.context.unmarshallerPool.take();
      u.setAttachmentUnmarshaller(au);
      return this.exit(this.unmarshal(u, in), u);
   }

   @NotNull
   public abstract Object unmarshal(@NotNull Unmarshaller var1, @NotNull Source var2) throws JAXBException;

   @NotNull
   public final Object unmarshal(@NotNull InputStream in) throws JAXBException {
      Unmarshaller u = (Unmarshaller)this.context.unmarshallerPool.take();
      return this.exit(this.unmarshal(u, in), u);
   }

   @NotNull
   public abstract Object unmarshal(@NotNull Unmarshaller var1, @NotNull InputStream var2) throws JAXBException;

   @NotNull
   public final Object unmarshal(@NotNull Node n) throws JAXBException {
      return this.unmarshal((Node)n, (AttachmentUnmarshaller)null);
   }

   @NotNull
   public final Object unmarshal(@NotNull Node n, @Nullable AttachmentUnmarshaller au) throws JAXBException {
      Unmarshaller u = (Unmarshaller)this.context.unmarshallerPool.take();
      u.setAttachmentUnmarshaller(au);
      return this.exit(this.unmarshal(u, n), u);
   }

   @NotNull
   public abstract Object unmarshal(@NotNull Unmarshaller var1, @NotNull Node var2) throws JAXBException;

   public abstract TypeReference getTypeReference();
}
