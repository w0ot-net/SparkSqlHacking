package jakarta.xml.bind.annotation;

import jakarta.xml.bind.ValidationEventHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class W3CDomHandler implements DomHandler {
   private DocumentBuilder builder;

   public W3CDomHandler() {
      this.builder = null;
   }

   public W3CDomHandler(DocumentBuilder builder) {
      if (builder == null) {
         throw new IllegalArgumentException();
      } else {
         this.builder = builder;
      }
   }

   public DocumentBuilder getBuilder() {
      return this.builder;
   }

   public void setBuilder(DocumentBuilder builder) {
      this.builder = builder;
   }

   public DOMResult createUnmarshaller(ValidationEventHandler errorHandler) {
      return this.builder == null ? new DOMResult() : new DOMResult(this.builder.newDocument());
   }

   public Element getElement(DOMResult r) {
      Node n = r.getNode();
      if (n instanceof Document) {
         return ((Document)n).getDocumentElement();
      } else if (n instanceof Element) {
         return (Element)n;
      } else if (n instanceof DocumentFragment) {
         return (Element)n.getChildNodes().item(0);
      } else {
         throw new IllegalStateException(n.toString());
      }
   }

   public Source marshal(Element element, ValidationEventHandler errorHandler) {
      return new DOMSource(element);
   }
}
