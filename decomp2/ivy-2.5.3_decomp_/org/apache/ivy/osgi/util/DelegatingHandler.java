package org.apache.ivy.osgi.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.ivy.util.Message;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DelegatingHandler extends DefaultHandler implements DTDHandler, ContentHandler, ErrorHandler {
   private DelegatingHandler delegate = null;
   DelegatingHandler parent;
   private final Map saxHandlerMapping = new HashMap();
   private final Map childHandlerMapping = new HashMap();
   private final String tagName;
   private boolean started = false;
   private boolean skip = false;
   private boolean skipOnError = false;
   private StringBuilder charBuffer = new StringBuilder();
   private boolean bufferingChar = false;
   private Locator locator;
   static final String TRUE;
   static final String FALSE;

   public DelegatingHandler(String name) {
      this.tagName = name;
      this.charBuffer.setLength(0);
   }

   protected void addChild(DelegatingHandler saxHandler, ChildElementHandler elementHandler) {
      this.saxHandlerMapping.put(saxHandler.getName(), saxHandler);
      this.childHandlerMapping.put(saxHandler.getName(), elementHandler);
      saxHandler.parent = this;
   }

   public String getName() {
      return this.tagName;
   }

   public DelegatingHandler getParent() {
      return this.parent;
   }

   public void setBufferingChar(boolean bufferingChar) {
      this.bufferingChar = bufferingChar;
   }

   public void setSkipOnError(boolean skipOnError) {
      this.skipOnError = skipOnError;
   }

   public boolean isBufferingChar() {
      return this.bufferingChar;
   }

   public String getBufferedChars() {
      return this.charBuffer.toString();
   }

   public void setDocumentLocator(Locator locator) {
      this.locator = locator;

      for(DelegatingHandler subHandler : this.saxHandlerMapping.values()) {
         subHandler.setDocumentLocator(locator);
      }

   }

   public Locator getLocator() {
      return this.locator;
   }

   protected String getCurrentElementIdentifier() {
      return "";
   }

   public void skip() {
      this.skip = true;

      for(DelegatingHandler subHandler : this.saxHandlerMapping.values()) {
         subHandler.stopDelegating();
      }

   }

   protected void stopDelegating() {
      this.parent.delegate = null;
      this.skip = false;
      this.started = false;

      for(DelegatingHandler subHandler : this.saxHandlerMapping.values()) {
         subHandler.stopDelegating();
      }

   }

   private void skipOnError(SkipOnErrorCallback callback) throws SAXException {
      try {
         callback.call();
      } catch (SAXException e) {
         if (!this.skipOnError) {
            throw e;
         }

         this.skip();
         this.log(0, e.getMessage(), e);
      }

   }

   public final void startDocument() throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.startDocument();
         } else {
            this.doStartDocument();
         }

      }
   }

   protected void doStartDocument() throws SAXException {
   }

   public final void endDocument() throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.endDocument();
         } else {
            this.doEndDocument();
         }

      }
   }

   protected void doEndDocument() throws SAXException {
   }

   public final void startElement(final String uri, final String localName, final String n, final Attributes atts) throws SAXException {
      this.charBuffer.setLength(0);
      if (this.delegate != null) {
         this.skipOnError(new SkipOnErrorCallback() {
            public void call() throws SAXException {
               DelegatingHandler.this.delegate.startElement(uri, localName, n, atts);
            }
         });
      } else if (!this.started) {
         if (this.parent == null && !localName.equals(this.tagName)) {
            throw new SAXException("The root element of the parsed document '" + localName + "' didn't matched the expected one: '" + this.tagName + "'");
         }

         this.skipOnError(new SkipOnErrorCallback() {
            public void call() throws SAXException {
               DelegatingHandler.this.handleAttributes(atts);
            }
         });
         this.started = true;
      } else {
         if (this.skip) {
            return;
         }

         this.delegate = (DelegatingHandler)this.saxHandlerMapping.get(localName);
         if (this.delegate != null) {
            this.skipOnError(new SkipOnErrorCallback() {
               public void call() throws SAXException {
                  DelegatingHandler.this.delegate.startElement(uri, localName, n, atts);
               }
            });
         }
      }

   }

   protected void handleAttributes(Attributes atts) throws SAXException {
   }

   protected void doStartElement(String uri, String localName, String name, Attributes atts) throws SAXException {
   }

   public final void endElement(final String uri, final String localName, final String n) throws SAXException {
      if (this.delegate != null) {
         final DelegatingHandler savedDelegate = this.delegate;
         this.skipOnError(new SkipOnErrorCallback() {
            public void call() throws SAXException {
               DelegatingHandler.this.delegate.endElement(uri, localName, n);
            }
         });
         if (this.delegate == null) {
            final ChildElementHandler<?> childHandler = (ChildElementHandler)this.childHandlerMapping.get(localName);
            if (childHandler != null) {
               this.skipOnError(new SkipOnErrorCallback() {
                  public void call() throws SAXException {
                     childHandler._childHandled(savedDelegate);
                  }
               });
            }
         }
      } else {
         if (!this.skip) {
            this.doEndElement(uri, localName, n);
         }

         if (this.parent != null && this.tagName.equals(localName)) {
            this.stopDelegating();
         }
      }

   }

   protected void doEndElement(String uri, String localName, String name) throws SAXException {
   }

   public final void characters(char[] ch, int start, int length) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.characters(ch, start, length);
         } else {
            this.doCharacters(ch, start, length);
         }

      }
   }

   protected void doCharacters(char[] ch, int start, int length) throws SAXException {
      if (this.bufferingChar) {
         this.charBuffer.append(ch, start, length);
      }

   }

   public final void startPrefixMapping(String prefix, String uri) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.startPrefixMapping(prefix, uri);
         } else {
            this.doStartPrefixMapping(prefix, uri);
         }

      }
   }

   protected void doStartPrefixMapping(String prefix, String uri) throws SAXException {
   }

   public final void endPrefixMapping(String prefix) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.endPrefixMapping(prefix);
         } else {
            this.doEndPrefixMapping(prefix);
         }

      }
   }

   protected void doEndPrefixMapping(String prefix) throws SAXException {
   }

   public final void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.ignorableWhitespace(ch, start, length);
         } else {
            this.doIgnorableWhitespace(ch, start, length);
         }

      }
   }

   protected void doIgnorableWhitespace(char[] ch, int start, int length) throws SAXException {
   }

   public final void notationDecl(String name, String publicId, String systemId) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.notationDecl(name, publicId, systemId);
         } else {
            this.doNotationDecl(name, publicId, systemId);
         }

      }
   }

   protected void doNotationDecl(String name, String publicId, String systemId) throws SAXException {
   }

   public final void processingInstruction(String target, String data) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.processingInstruction(target, data);
         } else {
            this.doProcessingInstruction(target, data);
         }

      }
   }

   protected void doProcessingInstruction(String target, String data) throws SAXException {
   }

   public final void skippedEntity(String name) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.skippedEntity(name);
         } else {
            this.doSkippedEntity(name);
         }

      }
   }

   protected void doSkippedEntity(String name) throws SAXException {
   }

   public final void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.unparsedEntityDecl(name, publicId, systemId, notationName);
         } else {
            this.doUnparsedEntityDecl(name, publicId, systemId, notationName);
         }

      }
   }

   protected void doUnparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
   }

   public final void warning(SAXParseException exception) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.warning(exception);
         } else {
            this.doWarning(exception);
         }

      }
   }

   protected void doWarning(SAXParseException exception) throws SAXException {
   }

   public final void error(SAXParseException exception) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.error(exception);
         } else {
            this.doError(exception);
         }

      }
   }

   protected void doError(SAXParseException exception) throws SAXException {
   }

   public final void fatalError(SAXParseException exception) throws SAXException {
      if (!this.skip) {
         if (this.delegate != null) {
            this.delegate.fatalError(exception);
         } else {
            this.doFatalError(exception);
         }

      }
   }

   protected void doFatalError(SAXParseException exception) throws SAXException {
   }

   protected void log(int logLevel, String message, Throwable t) {
      Message.debug(t);
      this.log(logLevel, message);
   }

   protected void log(int logLevel, String message) {
      Message.log(logLevel, getLocation(this.getLocator()) + message);
   }

   protected static String getLocation(Locator locator) {
      return locator == null ? "" : "[line " + locator.getLineNumber() + " col. " + locator.getColumnNumber() + "] ";
   }

   private void skipOnError(DelegatingHandler currentHandler, Class handlerClassToSkip, String message) {
      DelegatingHandler handlerToSkip;
      for(handlerToSkip = currentHandler; !handlerClassToSkip.isAssignableFrom(handlerToSkip.getClass()); handlerToSkip = handlerToSkip.getParent()) {
      }

      this.log(0, message + ". The '" + handlerToSkip.getName() + "' element " + this.getCurrentElementIdentifier() + " is then ignored.");
      handlerToSkip.skip();
   }

   protected String getRequiredAttribute(Attributes atts, String name) throws SAXParseException {
      String value = atts.getValue(name);
      if (value == null) {
         throw new SAXParseException("Required attribute '" + name + "' not found", this.getLocator());
      } else {
         return value;
      }
   }

   protected String getOptionalAttribute(Attributes atts, String name, String defaultValue) {
      String value = atts.getValue(name);
      return value == null ? defaultValue : value;
   }

   protected int getRequiredIntAttribute(Attributes atts, String name, Integer logLevel) throws SAXParseException {
      return this.parseInt(name, this.getRequiredAttribute(atts, name));
   }

   protected Integer getOptionalIntAttribute(Attributes atts, String name, Integer defaultValue) throws SAXParseException {
      String value = atts.getValue(name);
      return value == null ? defaultValue : this.parseInt(name, value);
   }

   private int parseInt(String name, String value) throws SAXParseException {
      try {
         return Integer.parseInt(value);
      } catch (NumberFormatException e) {
         throw new SAXParseException("Attribute '" + name + "' is expected to be an integer but was '" + value + "' (" + e.getMessage() + ")", this.getLocator());
      }
   }

   protected long getRequiredLongAttribute(Attributes atts, String name) throws SAXParseException {
      return this.parseLong(name, this.getRequiredAttribute(atts, name));
   }

   protected Long getOptionalLongAttribute(Attributes atts, String name, Long defaultValue) throws SAXParseException {
      String value = atts.getValue(name);
      return value == null ? defaultValue : this.parseLong(name, value);
   }

   private long parseLong(String name, String value) throws SAXParseException {
      try {
         return Long.parseLong(value);
      } catch (NumberFormatException e) {
         throw new SAXParseException("Attribute '" + name + "' is expected to be an long but was '" + value + "' (" + e.getMessage() + ")", this.getLocator());
      }
   }

   protected boolean getRequiredBooleanAttribute(Attributes atts, String name) throws SAXParseException {
      return this.parseBoolean(name, this.getRequiredAttribute(atts, name));
   }

   protected Boolean getOptionalBooleanAttribute(Attributes atts, String name, Boolean defaultValue) throws SAXParseException {
      String value = atts.getValue(name);
      return value == null ? defaultValue : this.parseBoolean(name, value);
   }

   private boolean parseBoolean(String name, String value) throws SAXParseException {
      String lowerValue = value.toLowerCase(Locale.US);
      if (lowerValue.equals(TRUE)) {
         return true;
      } else if (lowerValue.equals(FALSE)) {
         return false;
      } else {
         throw new SAXParseException("Attribute '" + name + "' is expected to be a boolean but was '" + value + "'", this.getLocator());
      }
   }

   static {
      TRUE = Boolean.TRUE.toString().toLowerCase(Locale.US);
      FALSE = Boolean.FALSE.toString().toLowerCase(Locale.US);
   }

   public abstract static class ChildElementHandler {
      /** @deprecated */
      @Deprecated
      public void childHanlded(DelegatingHandler child) throws SAXParseException {
         this.childHandled(child);
      }

      public abstract void childHandled(DelegatingHandler var1) throws SAXParseException;

      private void _childHandled(DelegatingHandler delegate) throws SAXParseException {
         this.childHandled(delegate);
      }
   }

   private interface SkipOnErrorCallback {
      void call() throws SAXException;
   }
}
