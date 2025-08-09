package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.sun.istack.SAXException2;
import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.impl.RuntimeModelBuilder;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeNonElementRef;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.Patcher;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;
import org.xml.sax.SAXException;

public abstract class TransducedAccessor {
   protected TransducedAccessor() {
   }

   public boolean useNamespace() {
      return false;
   }

   public void declareNamespace(Object o, XMLSerializer w) throws AccessorException, SAXException {
   }

   @Nullable
   public abstract CharSequence print(@NotNull Object var1) throws AccessorException, SAXException;

   public abstract void parse(Object var1, CharSequence var2) throws AccessorException, SAXException;

   public abstract boolean hasValue(Object var1) throws AccessorException;

   public static TransducedAccessor get(JAXBContextImpl context, RuntimeNonElementRef ref) {
      Transducer xducer = RuntimeModelBuilder.createTransducer(ref);
      RuntimePropertyInfo prop = ref.getSource();
      if (prop.isCollection()) {
         return new ListTransducedAccessorImpl(xducer, prop.getAccessor(), Lister.create((Type)Utils.REFLECTION_NAVIGATOR.erasure(prop.getRawType()), prop.id(), prop.getAdapter()));
      } else if (prop.id() == ID.IDREF) {
         return new IDREFTransducedAccessorImpl(prop.getAccessor());
      } else {
         return (TransducedAccessor)(xducer.useNamespace() ? new CompositeContextDependentTransducedAccessorImpl(context, xducer, prop.getAccessor()) : new CompositeTransducedAccessorImpl(context, xducer, prop.getAccessor()));
      }
   }

   public abstract void writeLeafElement(XMLSerializer var1, Name var2, Object var3, String var4) throws SAXException, AccessorException, IOException, XMLStreamException;

   public abstract void writeText(XMLSerializer var1, Object var2, String var3) throws AccessorException, SAXException, IOException, XMLStreamException;

   static class CompositeContextDependentTransducedAccessorImpl extends CompositeTransducedAccessorImpl {
      public CompositeContextDependentTransducedAccessorImpl(JAXBContextImpl context, Transducer xducer, Accessor acc) {
         super(context, xducer, acc);

         assert xducer.useNamespace();

      }

      public boolean useNamespace() {
         return true;
      }

      public void declareNamespace(Object bean, XMLSerializer w) throws AccessorException {
         ValueT o = (ValueT)this.acc.get(bean);
         if (o != null) {
            this.xducer.declareNamespace(o, w);
         }

      }

      public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws SAXException, AccessorException, IOException, XMLStreamException {
         w.startElement(tagName, (Object)null);
         this.declareNamespace(o, w);
         w.endNamespaceDecls((Object)null);
         w.endAttributes();
         this.xducer.writeText(w, this.acc.get(o), fieldName);
         w.endElement();
      }
   }

   public static class CompositeTransducedAccessorImpl extends TransducedAccessor {
      protected final Transducer xducer;
      protected final Accessor acc;

      public CompositeTransducedAccessorImpl(JAXBContextImpl context, Transducer xducer, Accessor acc) {
         this.xducer = xducer;
         this.acc = acc.optimize(context);
      }

      public CharSequence print(Object bean) throws AccessorException {
         ValueT o = (ValueT)this.acc.get(bean);
         return o == null ? null : this.xducer.print(o);
      }

      public void parse(Object bean, CharSequence lexical) throws AccessorException, SAXException {
         this.acc.set(bean, this.xducer.parse(lexical));
      }

      public boolean hasValue(Object bean) throws AccessorException {
         return this.acc.getUnadapted(bean) != null;
      }

      public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws SAXException, AccessorException, IOException, XMLStreamException {
         this.xducer.writeLeafElement(w, tagName, this.acc.get(o), fieldName);
      }

      public void writeText(XMLSerializer w, Object o, String fieldName) throws AccessorException, SAXException, IOException, XMLStreamException {
         this.xducer.writeText(w, this.acc.get(o), fieldName);
      }
   }

   private static final class IDREFTransducedAccessorImpl extends DefaultTransducedAccessor {
      private final Accessor acc;
      private final Class targetType;

      public IDREFTransducedAccessorImpl(Accessor acc) {
         this.acc = acc;
         this.targetType = acc.getValueType();
      }

      public String print(Object bean) throws AccessorException, SAXException {
         TargetT target = (TargetT)this.acc.get(bean);
         if (target == null) {
            return null;
         } else {
            XMLSerializer w = XMLSerializer.getInstance();

            try {
               String id = w.grammar.getBeanInfo(target, true).getId(target, w);
               if (id == null) {
                  w.errorMissingId(target);
               }

               return id;
            } catch (JAXBException e) {
               w.reportError((String)null, e);
               return null;
            }
         }
      }

      private void assign(Object bean, Object t, UnmarshallingContext context) throws AccessorException {
         if (!this.targetType.isInstance(t)) {
            context.handleError(Messages.UNASSIGNABLE_TYPE.format(this.targetType, t.getClass()));
         } else {
            this.acc.set(bean, t);
         }

      }

      public void parse(final Object bean, CharSequence lexical) throws AccessorException, SAXException {
         final String idref = WhiteSpaceProcessor.trim(lexical).toString();
         final UnmarshallingContext context = UnmarshallingContext.getInstance();
         final Callable callable = context.getObjectFromId(idref, this.acc.valueType);
         if (callable == null) {
            context.errorUnresolvedIDREF(bean, idref, context.getLocator());
         } else {
            TargetT t;
            try {
               t = (TargetT)callable.call();
            } catch (RuntimeException | SAXException e) {
               throw e;
            } catch (Exception e) {
               throw new SAXException2(e);
            }

            if (t != null) {
               this.assign(bean, t, context);
            } else {
               final LocatorEx loc = new LocatorEx.Snapshot(context.getLocator());
               context.addPatcher(new Patcher() {
                  public void run() throws SAXException {
                     try {
                        TargetT t = (TargetT)callable.call();
                        if (t == null) {
                           context.errorUnresolvedIDREF(bean, idref, loc);
                        } else {
                           IDREFTransducedAccessorImpl.this.assign(bean, t, context);
                        }
                     } catch (AccessorException e) {
                        context.handleError((Exception)e);
                     } catch (RuntimeException | SAXException e) {
                        throw e;
                     } catch (Exception e) {
                        throw new SAXException2(e);
                     }

                  }
               });
            }

         }
      }

      public boolean hasValue(Object bean) throws AccessorException {
         return this.acc.get(bean) != null;
      }
   }
}
