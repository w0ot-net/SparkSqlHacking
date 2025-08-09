package org.datanucleus.metadata.xml;

import java.net.URI;
import java.net.URISyntaxException;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.PersistenceFileMetaData;
import org.datanucleus.metadata.PersistenceUnitMetaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

public class PersistenceFileMetaDataHandler extends AbstractMetaDataHandler {
   URI rootURI = null;

   public PersistenceFileMetaDataHandler(MetaDataManager mgr, String filename, EntityResolver resolver) {
      super(mgr, filename, resolver);
      this.metadata = new PersistenceFileMetaData(filename);
      this.pushStack(this.metadata);
      String rootFilename = null;
      if (filename.endsWith("/META-INF/persistence.xml")) {
         rootFilename = filename.substring(0, filename.length() - "/META-INF/persistence.xml".length());
      } else {
         rootFilename = filename.substring(0, filename.lastIndexOf("/"));
      }

      try {
         rootFilename = rootFilename.replace(" ", "%20");
         this.rootURI = new URI(rootFilename);
      } catch (URISyntaxException e) {
         NucleusLogger.METADATA.warn("Error deriving persistence-unit root URI from " + rootFilename, e);
      }

   }

   public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
      if (localName.length() < 1) {
         localName = qName;
      }

      try {
         if (!localName.equals("persistence")) {
            if (localName.equals("persistence-unit")) {
               PersistenceFileMetaData filemd = (PersistenceFileMetaData)this.getStack();
               PersistenceUnitMetaData pumd = new PersistenceUnitMetaData(this.getAttr(attrs, "name"), this.getAttr(attrs, "transaction-type"), this.rootURI);
               filemd.addPersistenceUnit(pumd);
               this.pushStack(pumd);
            } else if (!localName.equals("properties")) {
               if (localName.equals("property")) {
                  PersistenceUnitMetaData pumd = (PersistenceUnitMetaData)this.getStack();
                  pumd.addProperty(this.getAttr(attrs, "name"), this.getAttr(attrs, "value"));
               } else if (!localName.equals("mapping-file") && !localName.equals("class") && !localName.equals("jar-file") && !localName.equals("jta-data-source") && !localName.equals("non-jta-data-source") && !localName.equals("description") && !localName.equals("provider") && !localName.equals("shared-cache-mode") && !localName.equals("validation-mode") && !localName.equals("exclude-unlisted-classes")) {
                  String message = Localiser.msg("044037", qName);
                  NucleusLogger.METADATA.error(message);
                  throw new RuntimeException(message);
               }
            }
         }

      } catch (RuntimeException ex) {
         NucleusLogger.METADATA.error(Localiser.msg("044042", qName, this.getStack(), uri), ex);
         throw ex;
      }
   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      if (localName.length() < 1) {
         localName = qName;
      }

      String currentString = this.getString().trim();
      MetaData md = this.getStack();
      if (currentString.length() > 0) {
         if (localName.equals("description")) {
            ((PersistenceUnitMetaData)md).setDescription(currentString);
         } else if (localName.equals("provider")) {
            ((PersistenceUnitMetaData)md).setProvider(currentString);
         } else if (localName.equals("jta-data-source")) {
            ((PersistenceUnitMetaData)md).setJtaDataSource(currentString);
         } else if (localName.equals("non-jta-data-source")) {
            ((PersistenceUnitMetaData)md).setNonJtaDataSource(currentString);
         } else if (localName.equals("class")) {
            ((PersistenceUnitMetaData)md).addClassName(currentString);
         } else if (localName.equals("mapping-file")) {
            ((PersistenceUnitMetaData)md).addMappingFile(currentString);
         } else if (localName.equals("jar-file")) {
            ((PersistenceUnitMetaData)md).addJarFile(currentString);
         } else if (localName.equals("shared-cache-mode")) {
            ((PersistenceUnitMetaData)md).setCaching(currentString);
         } else if (localName.equals("validation-mode")) {
            ((PersistenceUnitMetaData)md).setValidationMode(currentString);
         }
      }

      if (localName.equals("exclude-unlisted-classes")) {
         if (StringUtils.isWhitespace(currentString)) {
            currentString = "true";
         }

         Boolean val = Boolean.valueOf(currentString);
         if (val != null) {
            ((PersistenceUnitMetaData)md).setExcludeUnlistedClasses(val);
         }
      }

      if (qName.equals("persistence-unit")) {
         this.popStack();
      }

   }
}
