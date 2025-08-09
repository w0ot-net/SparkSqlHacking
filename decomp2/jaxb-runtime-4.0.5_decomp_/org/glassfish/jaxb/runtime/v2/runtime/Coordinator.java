package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import java.util.HashMap;
import org.glassfish.jaxb.core.v2.ClassFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class Coordinator implements ErrorHandler, ValidationEventHandler {
   private final HashMap adapters = new HashMap();
   private static final ThreadLocal activeTable = new ThreadLocal();
   private Coordinator old;

   protected Coordinator() {
   }

   public final XmlAdapter putAdapter(Class c, XmlAdapter a) {
      return a == null ? (XmlAdapter)this.adapters.remove(c) : (XmlAdapter)this.adapters.put(c, a);
   }

   public final XmlAdapter getAdapter(Class key) {
      T v = (T)((XmlAdapter)key.cast(this.adapters.get(key)));
      if (v == null) {
         v = (T)((XmlAdapter)ClassFactory.create(key));
         this.putAdapter(key, v);
      }

      return v;
   }

   public boolean containsAdapter(Class type) {
      return this.adapters.containsKey(type);
   }

   protected final void pushCoordinator() {
      this.old = (Coordinator)activeTable.get();
      activeTable.set(this);
   }

   protected final void popCoordinator() {
      if (this.old != null) {
         activeTable.set(this.old);
      } else {
         activeTable.remove();
      }

      this.old = null;
   }

   public static Coordinator _getInstance() {
      return (Coordinator)activeTable.get();
   }

   protected abstract ValidationEventLocator getLocation();

   public final void error(SAXParseException exception) throws SAXException {
      this.propagateEvent(1, exception);
   }

   public final void warning(SAXParseException exception) throws SAXException {
      this.propagateEvent(0, exception);
   }

   public final void fatalError(SAXParseException exception) throws SAXException {
      this.propagateEvent(2, exception);
   }

   private void propagateEvent(int severity, SAXParseException saxException) throws SAXException {
      ValidationEventImpl ve = new ValidationEventImpl(severity, saxException.getMessage(), this.getLocation());
      Exception e = saxException.getException();
      if (e != null) {
         ve.setLinkedException(e);
      } else {
         ve.setLinkedException(saxException);
      }

      boolean result = this.handleEvent(ve);
      if (!result) {
         throw saxException;
      }
   }
}
