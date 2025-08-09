package org.glassfish.jaxb.core.unmarshaller;

import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.helpers.ValidationEventLocatorImpl;
import java.util.Enumeration;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;

public class DOMScanner implements LocatorEx, InfosetScanner {
   private Node currentNode = null;
   private final AttributesImpl atts = new AttributesImpl();
   private ContentHandler receiver = null;
   private Locator locator = this;

   public void setLocator(Locator loc) {
      this.locator = loc;
   }

   public void scan(Node node) throws SAXException {
      if (node instanceof Document) {
         this.scan((Document)node);
      } else {
         this.scan((Element)node);
      }

   }

   public void scan(Document doc) throws SAXException {
      this.scan(doc.getDocumentElement());
   }

   public void scan(Element e) throws SAXException {
      this.setCurrentLocation(e);
      this.receiver.setDocumentLocator(this.locator);
      this.receiver.startDocument();
      NamespaceSupport nss = new NamespaceSupport();
      this.buildNamespaceSupport(nss, e.getParentNode());
      Enumeration<String> en = nss.getPrefixes();

      while(en.hasMoreElements()) {
         String prefix = (String)en.nextElement();
         this.receiver.startPrefixMapping(prefix, nss.getURI(prefix));
      }

      String defaultUri = nss.getURI("");
      if (defaultUri != null && !defaultUri.isEmpty()) {
         this.receiver.startPrefixMapping("", defaultUri);
      }

      this.visit(e);
      Enumeration<String> en = nss.getPrefixes();

      while(en.hasMoreElements()) {
         String prefix = (String)en.nextElement();
         this.receiver.endPrefixMapping(prefix);
      }

      if (defaultUri != null && !defaultUri.isEmpty()) {
         this.receiver.endPrefixMapping("");
      }

      this.setCurrentLocation(e);
      this.receiver.endDocument();
   }

   /** @deprecated */
   @Deprecated(
      since = "2.0",
      forRemoval = true
   )
   public void parse(Element e, ContentHandler handler) throws SAXException {
      this.receiver = handler;
      this.setCurrentLocation(e);
      this.receiver.startDocument();
      this.receiver.setDocumentLocator(this.locator);
      this.visit(e);
      this.setCurrentLocation(e);
      this.receiver.endDocument();
   }

   /** @deprecated */
   @Deprecated(
      since = "2.0",
      forRemoval = true
   )
   public void parseWithContext(Element e, ContentHandler handler) throws SAXException {
      this.setContentHandler(handler);
      this.scan(e);
   }

   private void buildNamespaceSupport(NamespaceSupport nss, Node node) {
      if (node != null && node.getNodeType() == 1) {
         this.buildNamespaceSupport(nss, node.getParentNode());
         nss.pushContext();
         NamedNodeMap atts = node.getAttributes();

         for(int i = 0; i < atts.getLength(); ++i) {
            Attr a = (Attr)atts.item(i);
            if ("xmlns".equals(a.getPrefix())) {
               nss.declarePrefix(a.getLocalName(), a.getValue());
            } else if ("xmlns".equals(a.getName())) {
               nss.declarePrefix("", a.getValue());
            }
         }

      }
   }

   public void visit(Element e) throws SAXException {
      this.setCurrentLocation(e);
      NamedNodeMap attributes = e.getAttributes();
      this.atts.clear();
      int len = attributes == null ? 0 : attributes.getLength();

      for(int i = len - 1; i >= 0; --i) {
         Attr a = (Attr)attributes.item(i);
         String name = a.getName();
         if (name.startsWith("xmlns")) {
            if (name.length() == 5) {
               this.receiver.startPrefixMapping("", a.getValue());
            } else {
               String localName = a.getLocalName();
               if (localName == null) {
                  localName = name.substring(6);
               }

               this.receiver.startPrefixMapping(localName, a.getValue());
            }
         } else {
            String uri = a.getNamespaceURI();
            if (uri == null) {
               uri = "";
            }

            String local = a.getLocalName();
            if (local == null) {
               local = a.getName();
            }

            this.atts.addAttribute(uri, local, a.getName(), "CDATA", a.getValue());
         }
      }

      String uri = e.getNamespaceURI();
      if (uri == null) {
         uri = "";
      }

      String local = e.getLocalName();
      String qname = e.getTagName();
      if (local == null) {
         local = qname;
      }

      this.receiver.startElement(uri, local, qname, this.atts);
      NodeList children = e.getChildNodes();
      int clen = children.getLength();

      for(int i = 0; i < clen; ++i) {
         this.visit(children.item(i));
      }

      this.setCurrentLocation(e);
      this.receiver.endElement(uri, local, qname);

      for(int i = len - 1; i >= 0; --i) {
         Attr a = (Attr)attributes.item(i);
         String name = a.getName();
         if (name.startsWith("xmlns")) {
            if (name.length() == 5) {
               this.receiver.endPrefixMapping("");
            } else {
               this.receiver.endPrefixMapping(a.getLocalName());
            }
         }
      }

   }

   private void visit(Node n) throws SAXException {
      this.setCurrentLocation(n);
      switch (n.getNodeType()) {
         case 1:
            this.visit((Element)n);
         case 2:
         case 6:
         default:
            break;
         case 3:
         case 4:
            String value = n.getNodeValue();
            this.receiver.characters(value.toCharArray(), 0, value.length());
            break;
         case 5:
            this.receiver.skippedEntity(n.getNodeName());
            break;
         case 7:
            ProcessingInstruction pi = (ProcessingInstruction)n;
            this.receiver.processingInstruction(pi.getTarget(), pi.getData());
      }

   }

   private void setCurrentLocation(Node currNode) {
      this.currentNode = currNode;
   }

   public Node getCurrentLocation() {
      return this.currentNode;
   }

   public Node getCurrentElement() {
      return this.currentNode;
   }

   public LocatorEx getLocator() {
      return this;
   }

   public void setContentHandler(ContentHandler handler) {
      this.receiver = handler;
   }

   public ContentHandler getContentHandler() {
      return this.receiver;
   }

   public String getPublicId() {
      return null;
   }

   public String getSystemId() {
      return null;
   }

   public int getLineNumber() {
      return -1;
   }

   public int getColumnNumber() {
      return -1;
   }

   public ValidationEventLocator getLocation() {
      return new ValidationEventLocatorImpl(this.getCurrentLocation());
   }
}
