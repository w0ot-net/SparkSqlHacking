package org.glassfish.jaxb.runtime.v2.runtime.output;

import org.glassfish.jaxb.core.marshaller.SAX2DOMEx;
import org.glassfish.jaxb.runtime.v2.runtime.AssociationMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class DOMOutput extends SAXOutput {
   private final AssociationMap assoc;

   public DOMOutput(Node node, AssociationMap assoc) {
      super(new SAX2DOMEx(node));
      this.assoc = assoc;

      assert assoc != null;

   }

   private SAX2DOMEx getBuilder() {
      return (SAX2DOMEx)this.out;
   }

   public void endStartTag() throws SAXException {
      super.endStartTag();
      Object op = this.nsContext.getCurrent().getOuterPeer();
      if (op != null) {
         this.assoc.addOuter(this.getBuilder().getCurrentElement(), op);
      }

      Object ip = this.nsContext.getCurrent().getInnerPeer();
      if (ip != null) {
         this.assoc.addInner(this.getBuilder().getCurrentElement(), ip);
      }

   }
}
