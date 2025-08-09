package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.Utils;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.ClassBeanInfoImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.property.AttributeProperty;
import org.glassfish.jaxb.runtime.v2.runtime.property.Property;
import org.glassfish.jaxb.runtime.v2.runtime.property.StructureLoaderBuilder;
import org.glassfish.jaxb.runtime.v2.runtime.property.UnmarshallerChain;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor;
import org.glassfish.jaxb.runtime.v2.util.QNameMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public final class StructureLoader extends Loader {
   private final QNameMap childUnmarshallers = new QNameMap();
   private ChildLoader catchAll;
   private ChildLoader textHandler;
   private QNameMap attUnmarshallers;
   private Accessor attCatchAll;
   private final JaxBeanInfo beanInfo;
   private int frameSize;
   private static final QNameMap EMPTY = new QNameMap();

   public StructureLoader(ClassBeanInfoImpl beanInfo) {
      super(true);
      this.beanInfo = beanInfo;
   }

   public void init(JAXBContextImpl context, ClassBeanInfoImpl beanInfo, Accessor attWildcard) {
      UnmarshallerChain chain = new UnmarshallerChain(context);

      for(ClassBeanInfoImpl bi = beanInfo; bi != null; bi = bi.superClazz) {
         for(int i = bi.properties.length - 1; i >= 0; --i) {
            Property p = bi.properties[i];
            switch (p.getKind()) {
               case ATTRIBUTE:
                  if (this.attUnmarshallers == null) {
                     this.attUnmarshallers = new QNameMap();
                  }

                  AttributeProperty ap = (AttributeProperty)p;
                  this.attUnmarshallers.put((QName)ap.attName.toQName(), ap.xacc);
                  break;
               case ELEMENT:
               case REFERENCE:
               case MAP:
               case VALUE:
                  p.buildChildElementUnmarshallers(chain, this.childUnmarshallers);
            }
         }
      }

      this.frameSize = chain.getScopeSize();
      this.textHandler = (ChildLoader)this.childUnmarshallers.get(StructureLoaderBuilder.TEXT_HANDLER);
      this.catchAll = (ChildLoader)this.childUnmarshallers.get(StructureLoaderBuilder.CATCH_ALL);
      if (attWildcard != null) {
         this.attCatchAll = attWildcard;
         if (this.attUnmarshallers == null) {
            this.attUnmarshallers = EMPTY;
         }
      } else {
         this.attCatchAll = null;
      }

   }

   public void startElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
      UnmarshallingContext context = state.getContext();

      assert !this.beanInfo.isImmutable();

      Object child = context.getInnerPeer();
      if (child != null && this.beanInfo.jaxbType != child.getClass()) {
         child = null;
      }

      if (child != null) {
         this.beanInfo.reset(child, context);
      }

      if (child == null) {
         child = context.createInstance(this.beanInfo);
      }

      context.recordInnerPeer(child);
      state.setTarget(child);
      this.fireBeforeUnmarshal(this.beanInfo, child, state);
      context.startScope(this.frameSize);
      if (this.attUnmarshallers != null) {
         Attributes atts = ea.atts;

         for(int i = 0; i < atts.getLength(); ++i) {
            String auri = atts.getURI(i);
            String alocal = atts.getLocalName(i);
            if ("".equals(alocal)) {
               alocal = atts.getQName(i);
            }

            String avalue = atts.getValue(i);
            TransducedAccessor xacc = (TransducedAccessor)this.attUnmarshallers.get(auri, alocal);

            try {
               if (xacc != null) {
                  xacc.parse(child, avalue);
               } else if (this.attCatchAll != null) {
                  String qname = atts.getQName(i);
                  if (!"http://www.w3.org/2001/XMLSchema-instance".equals(atts.getURI(i))) {
                     Object o = state.getTarget();
                     Map<QName, String> map = (Map)this.attCatchAll.get(o);
                     if (map == null) {
                        if (!this.attCatchAll.valueType.isAssignableFrom(HashMap.class)) {
                           context.handleError(Messages.UNABLE_TO_CREATE_MAP.format(this.attCatchAll.valueType));
                           return;
                        }

                        map = new HashMap();
                        this.attCatchAll.set(o, map);
                     }

                     int idx = qname.indexOf(58);
                     String prefix;
                     if (idx < 0) {
                        prefix = "";
                     } else {
                        prefix = qname.substring(0, idx);
                     }

                     map.put(new QName(auri, alocal, prefix), avalue);
                  }
               }
            } catch (AccessorException e) {
               handleGenericException(e, true);
            }
         }
      }

   }

   public void childElement(UnmarshallingContext.State state, TagName arg) throws SAXException {
      ChildLoader child = (ChildLoader)this.childUnmarshallers.get(arg.uri, arg.local);
      if (child == null) {
         Boolean backupWithParentNamespace = state.getContext().getJAXBContext().backupWithParentNamespace;
         backupWithParentNamespace = backupWithParentNamespace != null ? backupWithParentNamespace : Boolean.parseBoolean(Utils.getSystemProperty("org.glassfish.jaxb.backupWithParentNamespace"));
         if (this.beanInfo != null && this.beanInfo.getTypeNames() != null && backupWithParentNamespace) {
            Iterator<?> typeNamesIt = this.beanInfo.getTypeNames().iterator();
            QName parentQName = null;
            if (typeNamesIt != null && typeNamesIt.hasNext() && this.catchAll == null) {
               parentQName = (QName)typeNamesIt.next();
               String parentUri = parentQName.getNamespaceURI();
               child = (ChildLoader)this.childUnmarshallers.get(parentUri, arg.local);
            }
         }

         if (child == null) {
            child = this.catchAll;
            if (child == null) {
               super.childElement(state, arg);
               return;
            }
         }
      }

      state.setLoader(child.loader);
      state.setReceiver(child.receiver);
   }

   public Collection getExpectedChildElements() {
      return this.childUnmarshallers.keySet();
   }

   public Collection getExpectedAttributes() {
      return this.attUnmarshallers.keySet();
   }

   public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
      if (this.textHandler != null) {
         this.textHandler.loader.text(state, text);
      }

   }

   public void leaveElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
      state.getContext().endScope(this.frameSize);
      this.fireAfterUnmarshal(this.beanInfo, state.getTarget(), state.getPrev());
   }

   public JaxBeanInfo getBeanInfo() {
      return this.beanInfo;
   }
}
