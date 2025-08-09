package org.apache.ws.commons.schema;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class DocumentFragmentNodeList implements NodeList {
   private List nodes;
   private DocumentFragment fragment;

   DocumentFragmentNodeList(Node parentNode) {
      this.fragment = parentNode.getOwnerDocument().createDocumentFragment();
      this.nodes = new ArrayList();

      for(Node child = parentNode.getFirstChild(); child != null; child = child.getNextSibling()) {
         this.nodes.add(this.fragment.appendChild(child.cloneNode(true)));
      }

   }

   DocumentFragmentNodeList(Node parentNode, String filterUri, String filterLocal) {
      this.fragment = parentNode.getOwnerDocument().createDocumentFragment();
      this.nodes = new ArrayList();

      for(Node child = parentNode.getFirstChild(); child != null; child = child.getNextSibling()) {
         if (child.getNodeType() == 1 && child.getNamespaceURI().equals(filterUri) && child.getLocalName().equals(filterLocal)) {
            this.nodes.add(this.fragment.appendChild(child.cloneNode(true)));
         }
      }

   }

   public int getLength() {
      return this.nodes.size();
   }

   public Node item(int index) {
      return this.nodes == null ? null : (Node)this.nodes.get(index);
   }
}
