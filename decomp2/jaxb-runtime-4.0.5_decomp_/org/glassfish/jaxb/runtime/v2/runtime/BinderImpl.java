package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.xml.bind.Binder;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.ValidationEventHandler;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import org.glassfish.jaxb.core.unmarshaller.InfosetScanner;
import org.glassfish.jaxb.runtime.v2.runtime.output.DOMOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.XmlOutput;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.InterningXmlVisitor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.SAXConnector;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallerImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class BinderImpl extends Binder {
   private final JAXBContextImpl context;
   private UnmarshallerImpl unmarshaller;
   private MarshallerImpl marshaller;
   private final InfosetScanner scanner;
   private final AssociationMap assoc = new AssociationMap();

   BinderImpl(JAXBContextImpl _context, InfosetScanner scanner) {
      this.context = _context;
      this.scanner = scanner;
   }

   private UnmarshallerImpl getUnmarshaller() {
      if (this.unmarshaller == null) {
         this.unmarshaller = new UnmarshallerImpl(this.context, this.assoc);
      }

      return this.unmarshaller;
   }

   private MarshallerImpl getMarshaller() {
      if (this.marshaller == null) {
         this.marshaller = new MarshallerImpl(this.context, this.assoc);
      }

      return this.marshaller;
   }

   public void marshal(Object jaxbObject, Object xmlNode) throws JAXBException {
      if (xmlNode != null && jaxbObject != null) {
         this.getMarshaller().marshal(jaxbObject, (XmlOutput)this.createOutput(xmlNode));
      } else {
         throw new IllegalArgumentException();
      }
   }

   private DOMOutput createOutput(Object xmlNode) {
      return new DOMOutput((Node)xmlNode, this.assoc);
   }

   public Object updateJAXB(Object xmlNode) throws JAXBException {
      return this.associativeUnmarshal(xmlNode, true, (Class)null);
   }

   public Object unmarshal(Object xmlNode) throws JAXBException {
      return this.associativeUnmarshal(xmlNode, false, (Class)null);
   }

   public JAXBElement unmarshal(Object xmlNode, Class expectedType) throws JAXBException {
      if (expectedType == null) {
         throw new IllegalArgumentException();
      } else {
         return (JAXBElement)this.associativeUnmarshal(xmlNode, true, expectedType);
      }
   }

   public void setSchema(Schema schema) {
      this.getMarshaller().setSchema(schema);
      this.getUnmarshaller().setSchema(schema);
   }

   public Schema getSchema() {
      return this.getUnmarshaller().getSchema();
   }

   private Object associativeUnmarshal(Object xmlNode, boolean inplace, Class expectedType) throws JAXBException {
      if (xmlNode == null) {
         throw new IllegalArgumentException();
      } else {
         JaxBeanInfo bi = null;
         if (expectedType != null) {
            bi = this.context.getBeanInfo(expectedType, true);
         }

         InterningXmlVisitor handler = new InterningXmlVisitor(this.getUnmarshaller().createUnmarshallerHandler(this.scanner, inplace, bi));
         this.scanner.setContentHandler(new SAXConnector(handler, this.scanner.getLocator()));

         try {
            this.scanner.scan(xmlNode);
         } catch (SAXException e) {
            throw this.unmarshaller.createUnmarshalException(e);
         }

         return handler.getContext().getResult();
      }
   }

   public Object getXMLNode(Object jaxbObject) {
      if (jaxbObject == null) {
         throw new IllegalArgumentException();
      } else {
         AssociationMap.Entry<XmlNode> e = this.assoc.byPeer(jaxbObject);
         return e == null ? null : e.element();
      }
   }

   public Object getJAXBNode(Object xmlNode) {
      if (xmlNode == null) {
         throw new IllegalArgumentException();
      } else {
         AssociationMap.Entry e = this.assoc.byElement(xmlNode);
         if (e == null) {
            return null;
         } else {
            return e.outer() != null ? e.outer() : e.inner();
         }
      }
   }

   public Object updateXML(Object jaxbObject) throws JAXBException {
      return this.updateXML(jaxbObject, this.getXMLNode(jaxbObject));
   }

   public Object updateXML(Object jaxbObject, Object xmlNode) throws JAXBException {
      if (jaxbObject != null && xmlNode != null) {
         Element e = (Element)xmlNode;
         Node ns = e.getNextSibling();
         Node p = e.getParentNode();
         p.removeChild(e);
         JaxBeanInfo bi = this.context.getBeanInfo(jaxbObject, true);
         if (!bi.isElement()) {
            jaxbObject = new JAXBElement(new QName(e.getNamespaceURI(), e.getLocalName()), bi.jaxbType, jaxbObject);
         }

         this.getMarshaller().marshal(jaxbObject, (Node)p);
         Node newNode = p.getLastChild();
         p.removeChild(newNode);
         p.insertBefore(newNode, ns);
         return newNode;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void setEventHandler(ValidationEventHandler handler) throws JAXBException {
      this.getUnmarshaller().setEventHandler(handler);
      this.getMarshaller().setEventHandler(handler);
   }

   public ValidationEventHandler getEventHandler() {
      return this.getUnmarshaller().getEventHandler();
   }

   public void setMarshallerListener(Marshaller.Listener listener) {
      this.getMarshaller().setListener(listener);
   }

   public Marshaller.Listener getMarshallerListener() {
      return this.getMarshaller().getListener();
   }

   public void setUnmarshallerListener(Unmarshaller.Listener listener) {
      this.getUnmarshaller().setListener(listener);
   }

   public Unmarshaller.Listener getUnmarshallerListener() {
      return this.getUnmarshaller().getListener();
   }

   public Object getProperty(String name) throws PropertyException {
      if (name == null) {
         throw new IllegalArgumentException(Messages.NULL_PROPERTY_NAME.format());
      } else if (this.excludeProperty(name)) {
         throw new PropertyException(name);
      } else {
         Object prop = null;
         PropertyException pe = null;

         try {
            prop = this.getMarshaller().getProperty(name);
            return prop;
         } catch (PropertyException var6) {
            try {
               prop = this.getUnmarshaller().getProperty(name);
               return prop;
            } catch (PropertyException p) {
               p.setStackTrace(Thread.currentThread().getStackTrace());
               throw p;
            }
         }
      }
   }

   public void setProperty(String name, Object value) throws PropertyException {
      if (name == null) {
         throw new IllegalArgumentException(Messages.NULL_PROPERTY_NAME.format());
      } else if (this.excludeProperty(name)) {
         throw new PropertyException(name, value);
      } else {
         PropertyException pe = null;

         try {
            this.getMarshaller().setProperty(name, value);
         } catch (PropertyException var6) {
            try {
               this.getUnmarshaller().setProperty(name, value);
            } catch (PropertyException p) {
               p.setStackTrace(Thread.currentThread().getStackTrace());
               throw p;
            }
         }
      }
   }

   private boolean excludeProperty(String name) {
      return name.equals("org.glassfish.jaxb.characterEscapeHandler") || name.equals("org.glassfish.jaxb.xmlDeclaration") || name.equals("org.glassfish.jaxb.xmlHeaders");
   }
}
