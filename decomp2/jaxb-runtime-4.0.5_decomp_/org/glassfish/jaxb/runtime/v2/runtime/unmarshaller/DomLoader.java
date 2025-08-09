package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import jakarta.xml.bind.annotation.DomHandler;
import javax.xml.transform.Result;
import javax.xml.transform.sax.TransformerHandler;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.xml.sax.SAXException;

public class DomLoader extends Loader {
   private final DomHandler dom;

   public DomLoader(DomHandler dom) {
      super(true);
      this.dom = dom;
   }

   public void startElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
      UnmarshallingContext context = state.getContext();
      if (state.getTarget() == null) {
         state.setTarget(new State(context));
      }

      DomLoader<ResultT>.State s = (State)state.getTarget();

      try {
         s.declarePrefixes(context, context.getNewlyDeclaredPrefixes());
         s.handler.startElement(ea.uri, ea.local, ea.getQname(), ea.atts);
      } catch (SAXException e) {
         context.handleError((Exception)e);
         throw e;
      }
   }

   public void childElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
      state.setLoader(this);
      DomLoader<ResultT>.State s = (State)state.getPrev().getTarget();
      ++s.depth;
      state.setTarget(s);
   }

   public void text(UnmarshallingContext.State state, CharSequence text) throws SAXException {
      if (text.length() != 0) {
         try {
            DomLoader<ResultT>.State s = (State)state.getTarget();
            s.handler.characters(text.toString().toCharArray(), 0, text.length());
         } catch (SAXException e) {
            state.getContext().handleError((Exception)e);
            throw e;
         }
      }
   }

   public void leaveElement(UnmarshallingContext.State state, TagName ea) throws SAXException {
      DomLoader<ResultT>.State s = (State)state.getTarget();
      UnmarshallingContext context = state.getContext();

      try {
         s.handler.endElement(ea.uri, ea.local, ea.getQname());
         s.undeclarePrefixes(context.getNewlyDeclaredPrefixes());
      } catch (SAXException e) {
         context.handleError((Exception)e);
         throw e;
      }

      if (--s.depth == 0) {
         try {
            s.undeclarePrefixes(context.getAllDeclaredPrefixes());
            s.handler.endDocument();
         } catch (SAXException e) {
            context.handleError((Exception)e);
            throw e;
         }

         state.setTarget(s.getElement());
      }

   }

   private final class State {
      private TransformerHandler handler = null;
      private final Result result;
      int depth = 1;

      public State(UnmarshallingContext context) throws SAXException {
         this.handler = JAXBContextImpl.createTransformerHandler(context.getJAXBContext().disableSecurityProcessing);
         this.result = DomLoader.this.dom.createUnmarshaller(context);
         this.handler.setResult(this.result);

         try {
            this.handler.setDocumentLocator(context.getLocator());
            this.handler.startDocument();
            this.declarePrefixes(context, context.getAllDeclaredPrefixes());
         } catch (SAXException e) {
            context.handleError((Exception)e);
            throw e;
         }
      }

      public Object getElement() {
         return DomLoader.this.dom.getElement(this.result);
      }

      private void declarePrefixes(UnmarshallingContext context, String[] prefixes) throws SAXException {
         for(int i = prefixes.length - 1; i >= 0; --i) {
            String nsUri = context.getNamespaceURI(prefixes[i]);
            if (nsUri == null) {
               throw new IllegalStateException("prefix '" + prefixes[i] + "' isn't bound");
            }

            this.handler.startPrefixMapping(prefixes[i], nsUri);
         }

      }

      private void undeclarePrefixes(String[] prefixes) throws SAXException {
         for(int i = prefixes.length - 1; i >= 0; --i) {
            this.handler.endPrefixMapping(prefixes[i]);
         }

      }
   }
}
