package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshallerHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.NamespaceContext;
import org.glassfish.jaxb.core.Utils;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public final class SAXConnector implements UnmarshallerHandler {
   private LocatorEx loc;
   private static final Logger logger = Utils.getClassLogger();
   private final StringBuilder buffer = new StringBuilder();
   private final XmlVisitor next;
   private final UnmarshallingContext context;
   private final XmlVisitor.TextPredictor predictor;
   private final TagNameImpl tagName = new TagNameImpl();

   public SAXConnector(XmlVisitor next, LocatorEx externalLocator) {
      this.next = next;
      this.context = next.getContext();
      this.predictor = next.getPredictor();
      this.loc = externalLocator;
   }

   public Object getResult() throws JAXBException, IllegalStateException {
      return this.context.getResult();
   }

   public UnmarshallingContext getContext() {
      return this.context;
   }

   public void setDocumentLocator(Locator locator) {
      if (this.loc == null) {
         this.loc = new LocatorExWrapper(locator);
      }
   }

   public void startDocument() throws SAXException {
      if (logger.isLoggable(Level.FINER)) {
         logger.log(Level.FINER, "SAXConnector.startDocument");
      }

      this.next.startDocument(this.loc, (NamespaceContext)null);
   }

   public void endDocument() throws SAXException {
      if (logger.isLoggable(Level.FINER)) {
         logger.log(Level.FINER, "SAXConnector.endDocument");
      }

      this.next.endDocument();
   }

   public void startPrefixMapping(String prefix, String uri) throws SAXException {
      if (logger.isLoggable(Level.FINER)) {
         logger.log(Level.FINER, "SAXConnector.startPrefixMapping: {0}:{1}", new Object[]{prefix, uri});
      }

      this.next.startPrefixMapping(prefix, uri);
   }

   public void endPrefixMapping(String prefix) throws SAXException {
      if (logger.isLoggable(Level.FINER)) {
         logger.log(Level.FINER, "SAXConnector.endPrefixMapping: {0}", new Object[]{prefix});
      }

      this.next.endPrefixMapping(prefix);
   }

   public void startElement(String uri, String local, String qname, Attributes atts) throws SAXException {
      if (logger.isLoggable(Level.FINER)) {
         logger.log(Level.FINER, "SAXConnector.startElement: {0}:{1}:{2}, attrs: {3}", new Object[]{uri, local, qname, atts});
      }

      if (uri == null || uri.length() == 0) {
         uri = "";
      }

      if (local == null || local.length() == 0) {
         local = qname;
      }

      if (qname == null || qname.length() == 0) {
         qname = local;
      }

      this.processText(!this.context.getCurrentState().isMixed());
      this.tagName.uri = uri;
      this.tagName.local = local;
      this.tagName.qname = qname;
      this.tagName.atts = atts;
      this.next.startElement(this.tagName);
   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (logger.isLoggable(Level.FINER)) {
         logger.log(Level.FINER, "SAXConnector.endElement: {0}:{1}:{2}", new Object[]{uri, localName, qName});
      }

      this.processText(false);
      this.tagName.uri = uri;
      this.tagName.local = localName;
      this.tagName.qname = qName;
      this.next.endElement(this.tagName);
   }

   public void characters(char[] buf, int start, int len) {
      if (logger.isLoggable(Level.FINEST)) {
         logger.log(Level.FINEST, "SAXConnector.characters: {0}", buf);
      }

      if (this.predictor.expectText()) {
         this.buffer.append(buf, start, len);
      }

   }

   public void ignorableWhitespace(char[] buf, int start, int len) {
      if (logger.isLoggable(Level.FINEST)) {
         logger.log(Level.FINEST, "SAXConnector.ignorableWhitespace: {0}", buf);
      }

      this.characters(buf, start, len);
   }

   public void processingInstruction(String target, String data) {
   }

   public void skippedEntity(String name) {
   }

   private void processText(boolean ignorable) throws SAXException {
      if (this.predictor.expectText() && (!ignorable || !WhiteSpaceProcessor.isWhiteSpace(this.buffer))) {
         this.next.text(this.buffer);
      }

      this.buffer.setLength(0);
   }

   private static final class TagNameImpl extends TagName {
      String qname;

      public String getQname() {
         return this.qname;
      }
   }
}
