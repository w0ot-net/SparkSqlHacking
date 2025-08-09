package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.UnmarshalException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.UnmarshallerHandler;
import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.attachment.AttachmentUnmarshaller;
import jakarta.xml.bind.helpers.AbstractUnmarshallerImpl;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import org.glassfish.jaxb.core.unmarshaller.DOMScanner;
import org.glassfish.jaxb.core.unmarshaller.InfosetScanner;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.glassfish.jaxb.core.v2.util.XmlFactory;
import org.glassfish.jaxb.runtime.IDResolver;
import org.glassfish.jaxb.runtime.api.ClassResolver;
import org.glassfish.jaxb.runtime.v2.runtime.AssociationMap;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public final class UnmarshallerImpl extends AbstractUnmarshallerImpl implements ValidationEventHandler, Closeable {
   protected final JAXBContextImpl context;
   private Schema schema;
   public final UnmarshallingContext coordinator;
   private Unmarshaller.Listener externalListener;
   private AttachmentUnmarshaller attachmentUnmarshaller;
   private IDResolver idResolver = new DefaultIDResolver();
   private XMLReader reader = null;
   private static final DefaultHandler dummyHandler = new DefaultHandler();
   public static final String FACTORY = "org.glassfish.jaxb.core.ObjectFactory";

   public UnmarshallerImpl(JAXBContextImpl context, AssociationMap assoc) {
      this.context = context;
      this.coordinator = new UnmarshallingContext(this, assoc);

      try {
         this.setEventHandler(this);
      } catch (JAXBException e) {
         throw new AssertionError(e);
      }
   }

   public UnmarshallerHandler getUnmarshallerHandler() {
      return this.getUnmarshallerHandler(true, (JaxBeanInfo)null);
   }

   protected XMLReader getXMLReader() throws JAXBException {
      if (this.reader == null) {
         try {
            SAXParserFactory parserFactory = XmlFactory.createParserFactory(this.context.disableSecurityProcessing);
            parserFactory.setValidating(false);
            this.reader = parserFactory.newSAXParser().getXMLReader();
         } catch (SAXException | ParserConfigurationException e) {
            throw new JAXBException(e);
         }
      }

      return this.reader;
   }

   private SAXConnector getUnmarshallerHandler(boolean intern, JaxBeanInfo expectedType) {
      XmlVisitor h = this.createUnmarshallerHandler((InfosetScanner)null, false, expectedType);
      if (intern) {
         h = new InterningXmlVisitor(h);
      }

      return new SAXConnector(h, (LocatorEx)null);
   }

   public XmlVisitor createUnmarshallerHandler(InfosetScanner scanner, boolean inplace, JaxBeanInfo expectedType) {
      this.coordinator.reset(scanner, inplace, expectedType, this.idResolver);
      XmlVisitor unmarshaller = this.coordinator;
      if (this.schema != null) {
         unmarshaller = new ValidatingUnmarshaller(this.schema, unmarshaller);
      }

      if (this.attachmentUnmarshaller != null && this.attachmentUnmarshaller.isXOPPackage()) {
         unmarshaller = new MTOMDecorator(this, unmarshaller, this.attachmentUnmarshaller);
      }

      return unmarshaller;
   }

   public static boolean needsInterning(XMLReader reader) {
      try {
         reader.setFeature("http://xml.org/sax/features/string-interning", true);
      } catch (SAXException var3) {
      }

      try {
         if (reader.getFeature("http://xml.org/sax/features/string-interning")) {
            return false;
         }
      } catch (SAXException var2) {
      }

      return true;
   }

   protected Object unmarshal(XMLReader reader, InputSource source) throws JAXBException {
      return this.unmarshal0(reader, source, (JaxBeanInfo)null);
   }

   protected JAXBElement unmarshal(XMLReader reader, InputSource source, Class expectedType) throws JAXBException {
      if (expectedType == null) {
         throw new IllegalArgumentException();
      } else {
         return (JAXBElement)this.unmarshal0(reader, source, this.getBeanInfo(expectedType));
      }
   }

   private Object unmarshal0(XMLReader reader, InputSource source, JaxBeanInfo expectedType) throws JAXBException {
      SAXConnector connector = this.getUnmarshallerHandler(needsInterning(reader), expectedType);
      reader.setContentHandler(connector);
      reader.setErrorHandler(this.coordinator);

      try {
         reader.parse(source);
      } catch (IOException e) {
         this.coordinator.clearStates();
         throw new UnmarshalException(e);
      } catch (SAXException e) {
         this.coordinator.clearStates();
         throw this.createUnmarshalException(e);
      }

      Object result = connector.getResult();
      reader.setContentHandler(dummyHandler);
      reader.setErrorHandler(dummyHandler);
      return result;
   }

   public JAXBElement unmarshal(Source source, Class expectedType) throws JAXBException {
      if (source instanceof SAXSource) {
         SAXSource ss = (SAXSource)source;
         XMLReader locReader = ss.getXMLReader();
         if (locReader == null) {
            locReader = this.getXMLReader();
         }

         return this.unmarshal(locReader, ss.getInputSource(), expectedType);
      } else if (source instanceof StreamSource) {
         return this.unmarshal(this.getXMLReader(), streamSourceToInputSource((StreamSource)source), expectedType);
      } else if (source instanceof DOMSource) {
         return this.unmarshal(((DOMSource)source).getNode(), expectedType);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Object unmarshal0(Source source, JaxBeanInfo expectedType) throws JAXBException {
      if (source instanceof SAXSource) {
         SAXSource ss = (SAXSource)source;
         XMLReader locReader = ss.getXMLReader();
         if (locReader == null) {
            locReader = this.getXMLReader();
         }

         return this.unmarshal0(locReader, ss.getInputSource(), expectedType);
      } else if (source instanceof StreamSource) {
         return this.unmarshal0(this.getXMLReader(), streamSourceToInputSource((StreamSource)source), expectedType);
      } else if (source instanceof DOMSource) {
         return this.unmarshal0(((DOMSource)source).getNode(), expectedType);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public ValidationEventHandler getEventHandler() {
      try {
         return super.getEventHandler();
      } catch (JAXBException var2) {
         throw new AssertionError();
      }
   }

   public boolean hasEventHandler() {
      return this.getEventHandler() != this;
   }

   public JAXBElement unmarshal(Node node, Class expectedType) throws JAXBException {
      if (expectedType == null) {
         throw new IllegalArgumentException();
      } else {
         return (JAXBElement)this.unmarshal0(node, this.getBeanInfo(expectedType));
      }
   }

   public Object unmarshal(Node node) throws JAXBException {
      return this.unmarshal0((Node)node, (JaxBeanInfo)null);
   }

   /** @deprecated */
   @Deprecated
   public Object unmarshal(SAXSource source) throws JAXBException {
      return super.unmarshal(source);
   }

   public Object unmarshal0(Node node, JaxBeanInfo expectedType) throws JAXBException {
      try {
         DOMScanner scanner = new DOMScanner();
         InterningXmlVisitor handler = new InterningXmlVisitor(this.createUnmarshallerHandler((InfosetScanner)null, false, expectedType));
         scanner.setContentHandler(new SAXConnector(handler, scanner));
         if (node.getNodeType() == 1) {
            scanner.scan((Element)node);
         } else {
            if (node.getNodeType() != 9) {
               throw new IllegalArgumentException("Unexpected node type: " + node);
            }

            scanner.scan((Document)node);
         }

         Object retVal = handler.getContext().getResult();
         handler.getContext().clearResult();
         return retVal;
      } catch (SAXException e) {
         throw this.createUnmarshalException(e);
      }
   }

   public Object unmarshal(XMLStreamReader reader) throws JAXBException {
      return this.unmarshal0((XMLStreamReader)reader, (JaxBeanInfo)null);
   }

   public JAXBElement unmarshal(XMLStreamReader reader, Class expectedType) throws JAXBException {
      if (expectedType == null) {
         throw new IllegalArgumentException();
      } else {
         return (JAXBElement)this.unmarshal0(reader, this.getBeanInfo(expectedType));
      }
   }

   public Object unmarshal0(XMLStreamReader reader, JaxBeanInfo expectedType) throws JAXBException {
      if (reader == null) {
         throw new IllegalArgumentException(org.glassfish.jaxb.runtime.unmarshaller.Messages.format("Unmarshaller.NullReader"));
      } else {
         int eventType = reader.getEventType();
         if (eventType != 1 && eventType != 7) {
            throw new IllegalStateException(org.glassfish.jaxb.runtime.unmarshaller.Messages.format("Unmarshaller.IllegalReaderState", (Object)eventType));
         } else {
            XmlVisitor h = this.createUnmarshallerHandler((InfosetScanner)null, false, expectedType);
            StAXConnector connector = StAXStreamConnector.create(reader, h);

            try {
               connector.bridge();
            } catch (XMLStreamException e) {
               throw handleStreamException(e);
            }

            Object retVal = h.getContext().getResult();
            h.getContext().clearResult();
            return retVal;
         }
      }
   }

   public JAXBElement unmarshal(XMLEventReader reader, Class expectedType) throws JAXBException {
      if (expectedType == null) {
         throw new IllegalArgumentException();
      } else {
         return (JAXBElement)this.unmarshal0(reader, this.getBeanInfo(expectedType));
      }
   }

   public Object unmarshal(XMLEventReader reader) throws JAXBException {
      return this.unmarshal0((XMLEventReader)reader, (JaxBeanInfo)null);
   }

   private Object unmarshal0(XMLEventReader reader, JaxBeanInfo expectedType) throws JAXBException {
      if (reader == null) {
         throw new IllegalArgumentException(org.glassfish.jaxb.runtime.unmarshaller.Messages.format("Unmarshaller.NullReader"));
      } else {
         try {
            XMLEvent event = reader.peek();
            if (!event.isStartElement() && !event.isStartDocument()) {
               throw new IllegalStateException(org.glassfish.jaxb.runtime.unmarshaller.Messages.format("Unmarshaller.IllegalReaderState", (Object)event.getEventType()));
            } else {
               boolean isZephyr = reader.getClass().getName().equals("com.sun.xml.stream.XMLReaderImpl");
               XmlVisitor h = this.createUnmarshallerHandler((InfosetScanner)null, false, expectedType);
               if (!isZephyr) {
                  h = new InterningXmlVisitor(h);
               }

               (new StAXEventConnector(reader, h)).bridge();
               return h.getContext().getResult();
            }
         } catch (XMLStreamException e) {
            throw handleStreamException(e);
         }
      }
   }

   public Object unmarshal0(InputStream input, JaxBeanInfo expectedType) throws JAXBException {
      return this.unmarshal0(this.getXMLReader(), new InputSource(input), expectedType);
   }

   private static JAXBException handleStreamException(XMLStreamException e) {
      Throwable ne = e.getNestedException();
      if (ne instanceof JAXBException) {
         return (JAXBException)ne;
      } else {
         return ne instanceof SAXException ? new UnmarshalException(ne) : new UnmarshalException(e);
      }
   }

   public Object getProperty(String name) throws PropertyException {
      return name.equals(IDResolver.class.getName()) ? this.idResolver : super.getProperty(name);
   }

   public void setProperty(String name, Object value) throws PropertyException {
      if (name.equals("org.glassfish.jaxb.core.ObjectFactory")) {
         this.coordinator.setFactories(value);
      } else if (name.equals(IDResolver.class.getName())) {
         this.idResolver = (IDResolver)value;
      } else if (name.equals(ClassResolver.class.getName())) {
         this.coordinator.classResolver = (ClassResolver)value;
      } else if (name.equals(ClassLoader.class.getName())) {
         this.coordinator.classLoader = (ClassLoader)value;
      } else {
         super.setProperty(name, value);
      }
   }

   public void setSchema(Schema schema) {
      this.schema = schema;
   }

   public Schema getSchema() {
      return this.schema;
   }

   public AttachmentUnmarshaller getAttachmentUnmarshaller() {
      return this.attachmentUnmarshaller;
   }

   public void setAttachmentUnmarshaller(AttachmentUnmarshaller au) {
      this.attachmentUnmarshaller = au;
   }

   /** @deprecated */
   @Deprecated
   public boolean isValidating() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   public void setValidating(boolean validating) {
      throw new UnsupportedOperationException();
   }

   public void setAdapter(Class type, XmlAdapter adapter) {
      if (type == null) {
         throw new IllegalArgumentException();
      } else {
         this.coordinator.putAdapter(type, adapter);
      }
   }

   public XmlAdapter getAdapter(Class type) {
      if (type == null) {
         throw new IllegalArgumentException();
      } else {
         return this.coordinator.containsAdapter(type) ? this.coordinator.getAdapter(type) : null;
      }
   }

   public UnmarshalException createUnmarshalException(SAXException e) {
      return super.createUnmarshalException(e);
   }

   public boolean handleEvent(ValidationEvent event) {
      return event.getSeverity() != 2;
   }

   private static InputSource streamSourceToInputSource(StreamSource ss) {
      InputSource is = new InputSource();
      is.setSystemId(ss.getSystemId());
      is.setByteStream(ss.getInputStream());
      is.setCharacterStream(ss.getReader());
      return is;
   }

   public JaxBeanInfo getBeanInfo(Class clazz) throws JAXBException {
      return this.context.getBeanInfo(clazz, true);
   }

   public Unmarshaller.Listener getListener() {
      return this.externalListener;
   }

   public void setListener(Unmarshaller.Listener listener) {
      this.externalListener = listener;
   }

   public UnmarshallingContext getContext() {
      return this.coordinator;
   }

   protected void finalize() throws Throwable {
      try {
         ClassFactory.cleanCache();
      } finally {
         super.finalize();
      }

   }

   public void close() throws IOException {
      ClassFactory.cleanCache();
   }
}
