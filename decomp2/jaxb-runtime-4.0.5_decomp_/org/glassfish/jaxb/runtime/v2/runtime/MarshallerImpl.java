package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.MarshalException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.attachment.AttachmentMarshaller;
import jakarta.xml.bind.helpers.AbstractMarshallerImpl;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.ValidatorHandler;
import org.glassfish.jaxb.core.marshaller.CharacterEscapeHandler;
import org.glassfish.jaxb.core.marshaller.DataWriter;
import org.glassfish.jaxb.core.marshaller.DumbEscapeHandler;
import org.glassfish.jaxb.core.marshaller.MinimumEscapeHandler;
import org.glassfish.jaxb.core.marshaller.SAX2DOMEx;
import org.glassfish.jaxb.core.marshaller.XMLWriter;
import org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper;
import org.glassfish.jaxb.runtime.marshaller.NioEscapeHandler;
import org.glassfish.jaxb.runtime.v2.runtime.output.C14nXmlOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.Encoded;
import org.glassfish.jaxb.runtime.v2.runtime.output.ForkXmlOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.IndentingUTF8XmlOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.NamespaceContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.output.SAXOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.UTF8XmlOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.XMLEventWriterOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.XMLStreamWriterOutput;
import org.glassfish.jaxb.runtime.v2.runtime.output.XmlOutput;
import org.glassfish.jaxb.runtime.v2.util.FatalAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public final class MarshallerImpl extends AbstractMarshallerImpl implements ValidationEventHandler {
   private static final Logger LOGGER = Logger.getLogger(MarshallerImpl.class.getName());
   private String indent = "    ";
   private NamespacePrefixMapper prefixMapper = null;
   private CharacterEscapeHandler escapeHandler = null;
   private String header = null;
   final JAXBContextImpl context;
   protected final XMLSerializer serializer;
   private Schema schema;
   private Marshaller.Listener externalListener = null;
   private boolean c14nSupport;
   private Flushable toBeFlushed;
   private Closeable toBeClosed;
   protected static final String INDENT_STRING = "org.glassfish.jaxb.indentString";
   protected static final String PREFIX_MAPPER = "org.glassfish.jaxb.namespacePrefixMapper";
   protected static final String ENCODING_HANDLER = "org.glassfish.jaxb.characterEscapeHandler";
   protected static final String ENCODING_HANDLER2 = "org.glassfish.jaxb.core.marshaller.CharacterEscapeHandler";
   protected static final String XMLDECLARATION = "org.glassfish.jaxb.xmlDeclaration";
   protected static final String XML_HEADERS = "org.glassfish.jaxb.xmlHeaders";
   protected static final String C14N = "org.glassfish.jaxb.c14n";
   protected static final String OBJECT_IDENTITY_CYCLE_DETECTION = "org.glassfish.jaxb.objectIdentitityCycleDetection";

   public MarshallerImpl(JAXBContextImpl c, AssociationMap assoc) {
      this.context = c;
      this.serializer = new XMLSerializer(this);
      this.c14nSupport = this.context.c14nSupport;

      try {
         this.setEventHandler(this);
      } catch (JAXBException e) {
         throw new AssertionError(e);
      }
   }

   public JAXBContextImpl getContext() {
      return this.context;
   }

   public void marshal(Object obj, OutputStream out, NamespaceContext inscopeNamespace) throws JAXBException {
      this.write(obj, this.createWriter(out), new StAXPostInitAction(inscopeNamespace, this.serializer));
   }

   public void marshal(Object obj, XMLStreamWriter writer) throws JAXBException {
      this.write(obj, XMLStreamWriterOutput.create(writer, this.context, this.escapeHandler), new StAXPostInitAction(writer, this.serializer));
   }

   public void marshal(Object obj, XMLEventWriter writer) throws JAXBException {
      this.write(obj, new XMLEventWriterOutput(writer), new StAXPostInitAction(writer, this.serializer));
   }

   public void marshal(Object obj, XmlOutput output) throws JAXBException {
      this.write(obj, output, (Runnable)null);
   }

   XmlOutput createXmlOutput(Result result) throws JAXBException {
      if (result instanceof SAXResult) {
         return new SAXOutput(((SAXResult)result).getHandler());
      } else if (result instanceof DOMResult) {
         Node node = ((DOMResult)result).getNode();
         if (node == null) {
            Document doc = JAXBContextImpl.createDom(this.getContext().disableSecurityProcessing);
            ((DOMResult)result).setNode(doc);
            return new SAXOutput(new SAX2DOMEx(doc));
         } else {
            return new SAXOutput(new SAX2DOMEx(node));
         }
      } else {
         if (result instanceof StreamResult) {
            StreamResult sr = (StreamResult)result;
            if (sr.getWriter() != null) {
               return this.createWriter(sr.getWriter());
            }

            if (sr.getOutputStream() != null) {
               return this.createWriter(sr.getOutputStream());
            }

            if (sr.getSystemId() != null) {
               String fileURL = sr.getSystemId();

               try {
                  fileURL = (new URI(fileURL)).getPath();
               } catch (URISyntaxException var6) {
               }

               try {
                  FileOutputStream fos = new FileOutputStream(fileURL);

                  assert this.toBeClosed == null;

                  this.toBeClosed = fos;
                  return this.createWriter((OutputStream)fos);
               } catch (IOException e) {
                  throw new MarshalException(e);
               }
            }
         }

         throw new MarshalException(Messages.UNSUPPORTED_RESULT.format());
      }
   }

   Runnable createPostInitAction(Result result) {
      if (result instanceof DOMResult) {
         Node node = ((DOMResult)result).getNode();
         return new DomPostInitAction(node, this.serializer);
      } else {
         return null;
      }
   }

   public void marshal(Object target, Result result) throws JAXBException {
      this.write(target, this.createXmlOutput(result), this.createPostInitAction(result));
   }

   protected void write(Name rootTagName, JaxBeanInfo bi, Object obj, XmlOutput out, Runnable postInitAction) throws JAXBException {
      try {
         try {
            this.prewrite(out, true, postInitAction);
            this.serializer.startElement(rootTagName, (Object)null);
            if (bi.jaxbType != Void.class && bi.jaxbType != Void.TYPE) {
               if (obj == null) {
                  this.serializer.writeXsiNilTrue();
               } else {
                  this.serializer.childAsXsiType(obj, "root", bi, false);
               }
            } else {
               this.serializer.endNamespaceDecls((Object)null);
               this.serializer.endAttributes();
            }

            this.serializer.endElement();
            this.postwrite();
         } catch (XMLStreamException | IOException | SAXException e) {
            throw new MarshalException(e);
         } finally {
            this.serializer.close();
         }
      } finally {
         this.cleanUp();
      }

   }

   private void write(Object obj, XmlOutput out, Runnable postInitAction) throws JAXBException {
      try {
         if (obj == null) {
            throw new IllegalArgumentException(Messages.NOT_MARSHALLABLE.format());
         }

         if (this.schema != null) {
            ValidatorHandler validator = this.schema.newValidatorHandler();
            validator.setErrorHandler(new FatalAdapter(this.serializer));
            XMLFilterImpl f = new XMLFilterImpl() {
               public void startPrefixMapping(String prefix, String uri) throws SAXException {
                  super.startPrefixMapping(prefix.intern(), uri.intern());
               }
            };
            f.setContentHandler(validator);
            out = new ForkXmlOutput(new SAXOutput(f) {
               public void startDocument(XMLSerializer serializer, boolean fragment, int[] nsUriIndex2prefixIndex, NamespaceContextImpl nsContext) throws SAXException, IOException, XMLStreamException {
                  super.startDocument(serializer, false, nsUriIndex2prefixIndex, nsContext);
               }

               public void endDocument(boolean fragment) throws SAXException, IOException, XMLStreamException {
                  super.endDocument(false);
               }
            }, out);
         }

         try {
            this.prewrite(out, this.isFragment(), postInitAction);
            this.serializer.childAsRoot(obj);
            this.postwrite();
         } catch (XMLStreamException | IOException | SAXException e) {
            throw new MarshalException(e);
         } finally {
            this.serializer.close();
         }
      } finally {
         this.cleanUp();
      }

   }

   private void cleanUp() {
      if (this.toBeFlushed != null) {
         try {
            this.toBeFlushed.flush();
         } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
         }
      }

      if (this.toBeClosed != null) {
         try {
            this.toBeClosed.close();
         } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
         }
      }

      this.toBeFlushed = null;
      this.toBeClosed = null;
   }

   private void prewrite(XmlOutput out, boolean fragment, Runnable postInitAction) throws IOException, SAXException, XMLStreamException {
      this.serializer.startDocument(out, fragment, this.getSchemaLocation(), this.getNoNSSchemaLocation());
      if (postInitAction != null) {
         postInitAction.run();
      }

      if (this.prefixMapper != null) {
         String[] decls = this.prefixMapper.getContextualNamespaceDecls();
         if (decls != null) {
            for(int i = 0; i < decls.length; i += 2) {
               String prefix = decls[i];
               String nsUri = decls[i + 1];
               if (nsUri != null && prefix != null) {
                  this.serializer.addInscopeBinding(nsUri, prefix);
               }
            }
         }
      }

      this.serializer.setPrefixMapper(this.prefixMapper);
   }

   private void postwrite() throws IOException, SAXException, XMLStreamException {
      this.serializer.endDocument();
      this.serializer.reconcileID();
   }

   CharacterEscapeHandler getEscapeHandler() {
      return this.escapeHandler;
   }

   protected CharacterEscapeHandler createEscapeHandler(String encoding) {
      if (this.escapeHandler != null) {
         return this.escapeHandler;
      } else if (encoding.startsWith("UTF")) {
         return MinimumEscapeHandler.theInstance;
      } else {
         try {
            return new NioEscapeHandler(this.getJavaEncoding(encoding));
         } catch (Throwable var3) {
            return DumbEscapeHandler.theInstance;
         }
      }
   }

   public XmlOutput createWriter(Writer w, String encoding) {
      if (!(w instanceof BufferedWriter)) {
         w = new BufferedWriter(w);
      }

      assert this.toBeFlushed == null;

      this.toBeFlushed = w;
      CharacterEscapeHandler ceh = this.createEscapeHandler(encoding);
      XMLWriter xw;
      if (this.isFormattedOutput()) {
         DataWriter d = new DataWriter(w, encoding, ceh);
         d.setIndentStep(this.indent);
         xw = d;
      } else {
         xw = new XMLWriter(w, encoding, ceh);
      }

      xw.setXmlDecl(!this.isFragment());
      xw.setHeader(this.header);
      return new SAXOutput(xw);
   }

   public XmlOutput createWriter(Writer w) {
      return this.createWriter(w, this.getEncoding());
   }

   public XmlOutput createWriter(OutputStream os) throws JAXBException {
      return this.createWriter(os, this.getEncoding());
   }

   public XmlOutput createWriter(OutputStream os, String encoding) throws JAXBException {
      if (encoding.equals("UTF-8")) {
         Encoded[] table = this.context.getUTF8NameTable();
         CharacterEscapeHandler ceh = this.createEscapeHandler(encoding);
         UTF8XmlOutput out;
         if (this.isFormattedOutput()) {
            out = new IndentingUTF8XmlOutput(os, this.indent, table, ceh);
         } else if (this.c14nSupport) {
            out = new C14nXmlOutput(os, table, this.context.c14nSupport, ceh);
         } else {
            out = new UTF8XmlOutput(os, table, ceh);
         }

         if (this.header != null) {
            out.setHeader(this.header);
         }

         return out;
      } else {
         try {
            return this.createWriter((Writer)(new OutputStreamWriter(os, this.getJavaEncoding(encoding))), encoding);
         } catch (UnsupportedEncodingException e) {
            throw new MarshalException(Messages.UNSUPPORTED_ENCODING.format(encoding), e);
         }
      }
   }

   public Object getProperty(String name) throws PropertyException {
      if ("org.glassfish.jaxb.indentString".equals(name)) {
         return this.indent;
      } else if (!"org.glassfish.jaxb.characterEscapeHandler".equals(name) && !"org.glassfish.jaxb.core.marshaller.CharacterEscapeHandler".equals(name)) {
         if ("org.glassfish.jaxb.namespacePrefixMapper".equals(name)) {
            return this.prefixMapper;
         } else if ("org.glassfish.jaxb.xmlDeclaration".equals(name)) {
            return !this.isFragment();
         } else if ("org.glassfish.jaxb.xmlHeaders".equals(name)) {
            return this.header;
         } else if ("org.glassfish.jaxb.c14n".equals(name)) {
            return this.c14nSupport;
         } else {
            return "org.glassfish.jaxb.objectIdentitityCycleDetection".equals(name) ? this.serializer.getObjectIdentityCycleDetection() : super.getProperty(name);
         }
      } else {
         return this.escapeHandler;
      }
   }

   public void setProperty(String name, Object value) throws PropertyException {
      if ("org.glassfish.jaxb.indentString".equals(name)) {
         this.checkString(name, value);
         this.indent = (String)value;
      } else if (!"org.glassfish.jaxb.characterEscapeHandler".equals(name) && !"org.glassfish.jaxb.core.marshaller.CharacterEscapeHandler".equals(name)) {
         if ("org.glassfish.jaxb.namespacePrefixMapper".equals(name)) {
            if (!(value instanceof NamespacePrefixMapper)) {
               throw new PropertyException(Messages.MUST_BE_X.format(name, NamespacePrefixMapper.class.getName(), value.getClass().getName()));
            } else {
               this.prefixMapper = (NamespacePrefixMapper)value;
            }
         } else if ("org.glassfish.jaxb.xmlDeclaration".equals(name)) {
            this.checkBoolean(name, value);
            super.setProperty("jaxb.fragment", !(Boolean)value);
         } else if ("org.glassfish.jaxb.xmlHeaders".equals(name)) {
            this.checkString(name, value);
            this.header = (String)value;
         } else if ("org.glassfish.jaxb.c14n".equals(name)) {
            this.checkBoolean(name, value);
            this.c14nSupport = (Boolean)value;
         } else if ("org.glassfish.jaxb.objectIdentitityCycleDetection".equals(name)) {
            this.checkBoolean(name, value);
            this.serializer.setObjectIdentityCycleDetection((Boolean)value);
         } else {
            super.setProperty(name, value);
         }
      } else if (!(value instanceof CharacterEscapeHandler)) {
         throw new PropertyException(Messages.MUST_BE_X.format(name, CharacterEscapeHandler.class.getName(), value.getClass().getName()));
      } else {
         this.escapeHandler = (CharacterEscapeHandler)value;
      }
   }

   private void checkBoolean(String name, Object value) throws PropertyException {
      if (!(value instanceof Boolean)) {
         throw new PropertyException(Messages.MUST_BE_X.format(name, Boolean.class.getName(), value.getClass().getName()));
      }
   }

   private void checkString(String name, Object value) throws PropertyException {
      if (!(value instanceof String)) {
         throw new PropertyException(Messages.MUST_BE_X.format(name, String.class.getName(), value.getClass().getName()));
      }
   }

   public void setAdapter(Class type, XmlAdapter adapter) {
      if (type == null) {
         throw new IllegalArgumentException();
      } else {
         this.serializer.putAdapter(type, adapter);
      }
   }

   public XmlAdapter getAdapter(Class type) {
      if (type == null) {
         throw new IllegalArgumentException();
      } else {
         return this.serializer.containsAdapter(type) ? this.serializer.getAdapter(type) : null;
      }
   }

   public void setAttachmentMarshaller(AttachmentMarshaller am) {
      this.serializer.attachmentMarshaller = am;
   }

   public AttachmentMarshaller getAttachmentMarshaller() {
      return this.serializer.attachmentMarshaller;
   }

   public Schema getSchema() {
      return this.schema;
   }

   public void setSchema(Schema s) {
      this.schema = s;
   }

   public boolean handleEvent(ValidationEvent event) {
      return false;
   }

   public Marshaller.Listener getListener() {
      return this.externalListener;
   }

   public void setListener(Marshaller.Listener listener) {
      this.externalListener = listener;
   }
}
