package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.ListTransducedAccessorImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.ChildLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.DefaultValueLoaderDecorator;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.LeafPropertyLoader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Loader;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Receiver;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.SAXException;

final class ListElementProperty extends ArrayProperty {
   private final Name tagName;
   private final String defaultValue;
   private final TransducedAccessor xacc;

   public ListElementProperty(JAXBContextImpl grammar, RuntimeElementPropertyInfo prop) {
      super(grammar, prop);

      assert prop.isValueList();

      assert prop.getTypes().size() == 1;

      RuntimeTypeRef ref = (RuntimeTypeRef)prop.getTypes().get(0);
      this.tagName = grammar.nameBuilder.createElementName(ref.getTagName());
      this.defaultValue = ref.getDefaultValue();
      Transducer xducer = ref.getTransducer();
      this.xacc = new ListTransducedAccessorImpl(xducer, this.acc, this.lister);
   }

   public PropertyKind getKind() {
      return PropertyKind.ELEMENT;
   }

   public void buildChildElementUnmarshallers(UnmarshallerChain chain, QNameMap handlers) {
      Loader l = new LeafPropertyLoader(this.xacc);
      Loader var4 = new DefaultValueLoaderDecorator(l, this.defaultValue);
      handlers.put((Name)this.tagName, new ChildLoader(var4, (Receiver)null));
   }

   public void serializeBody(Object o, XMLSerializer w, Object outerPeer) throws SAXException, AccessorException, IOException, XMLStreamException {
      ListT list = (ListT)this.acc.get(o);
      if (list != null) {
         if (this.xacc.useNamespace()) {
            w.startElement(this.tagName, (Object)null);
            this.xacc.declareNamespace(o, w);
            w.endNamespaceDecls(list);
            w.endAttributes();
            this.xacc.writeText(w, o, this.fieldName);
            w.endElement();
         } else {
            this.xacc.writeLeafElement(w, this.tagName, o, this.fieldName);
         }
      }

   }

   public Accessor getElementPropertyAccessor(String nsUri, String localName) {
      return this.tagName != null && this.tagName.equals(nsUri, localName) ? this.acc : null;
   }
}
