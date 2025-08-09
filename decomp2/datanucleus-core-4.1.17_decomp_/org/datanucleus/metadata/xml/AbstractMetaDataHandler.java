package org.datanucleus.metadata.xml;

import java.io.IOException;
import java.util.Stack;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class AbstractMetaDataHandler extends DefaultHandler {
   protected final MetaDataManager mgr;
   protected final String filename;
   protected MetaData metadata;
   protected final EntityResolver entityResolver;
   protected StringBuilder charactersBuffer = new StringBuilder();
   protected boolean validate = true;
   protected Stack stack = new Stack();

   public AbstractMetaDataHandler(MetaDataManager mgr, String filename, EntityResolver resolver) {
      this.mgr = mgr;
      this.filename = filename;
      this.entityResolver = resolver;
   }

   public void setValidate(boolean validate) {
      this.validate = validate;
   }

   public MetaData getMetaData() {
      return this.metadata;
   }

   public void error(SAXParseException e) throws SAXException {
      if (this.validate) {
         if (e.getColumnNumber() >= 0) {
            NucleusLogger.METADATA.warn(Localiser.msg("044039", this.filename, "" + e.getLineNumber(), "" + e.getColumnNumber(), e.getMessage()));
         } else {
            NucleusLogger.METADATA.warn(Localiser.msg("044038", this.filename, "" + e.getLineNumber(), e.getMessage()));
         }
      }

   }

   protected String getAttr(Attributes attrs, String key, String defaultValue) {
      String result = attrs.getValue(key);
      if (result == null) {
         return defaultValue;
      } else {
         return result.length() == 0 ? defaultValue : result;
      }
   }

   protected String getAttr(Attributes attrs, String key) {
      return this.getAttr(attrs, key, (String)null);
   }

   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
      InputSource source = null;
      if (this.entityResolver != null) {
         try {
            source = this.entityResolver.resolveEntity(publicId, systemId);
         } catch (IOException var5) {
         }
      }

      if (source == null) {
         try {
            return super.resolveEntity(publicId, systemId);
         } catch (IOException var6) {
         }
      }

      return source;
   }

   public void characters(char[] ch, int start, int length) throws SAXException {
      this.charactersBuffer.append(ch, start, length);
   }

   public String getString() {
      String result = this.charactersBuffer.toString();
      this.charactersBuffer = new StringBuilder();
      return result;
   }

   protected MetaData getStack() {
      return (MetaData)this.stack.lastElement();
   }

   protected MetaData popStack() {
      return (MetaData)this.stack.pop();
   }

   protected void pushStack(MetaData md) {
      this.stack.push(md);
   }
}
