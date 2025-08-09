package org.datanucleus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class AbstractXMLEntityResolver implements EntityResolver {
   protected Map publicIdEntities = new HashMap();
   protected Map systemIdEntities = new HashMap();

   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
      try {
         if (publicId != null) {
            String internalEntity = (String)this.publicIdEntities.get(publicId);
            if (internalEntity != null) {
               return this.getLocalInputSource(publicId, systemId, internalEntity);
            }
         }

         if (systemId != null) {
            String internalEntity = (String)this.systemIdEntities.get(systemId);
            if (internalEntity != null) {
               return this.getLocalInputSource(publicId, systemId, internalEntity);
            }

            if (systemId.startsWith("file://")) {
               String localPath = systemId.substring(7);
               File file = new File(localPath);
               if (file.exists()) {
                  if (NucleusLogger.METADATA.isDebugEnabled()) {
                     NucleusLogger.METADATA.debug(Localiser.msg("028001", publicId, systemId));
                  }

                  FileInputStream in = new FileInputStream(file);
                  return new InputSource(in);
               }

               return null;
            }

            if (systemId.startsWith("file:")) {
               return this.getLocalInputSource(publicId, systemId, systemId.substring(5));
            }

            if (systemId.startsWith("http:")) {
               try {
                  if (NucleusLogger.METADATA.isDebugEnabled()) {
                     NucleusLogger.METADATA.debug(Localiser.msg("028001", publicId, systemId));
                  }

                  URL url = new URL(systemId);
                  InputStream url_stream = url.openStream();
                  return new InputSource(url_stream);
               } catch (Exception e) {
                  NucleusLogger.METADATA.error(e);
               }
            }
         }

         NucleusLogger.METADATA.error(Localiser.msg("028002", publicId, systemId));
         return null;
      } catch (Exception e) {
         NucleusLogger.METADATA.error(Localiser.msg("028003", publicId, systemId), e);
         throw new SAXException(e.getMessage(), e);
      }
   }

   protected InputSource getLocalInputSource(String publicId, String systemId, String localPath) throws FileNotFoundException {
      if (NucleusLogger.METADATA.isDebugEnabled()) {
         NucleusLogger.METADATA.debug(Localiser.msg("028000", publicId, systemId, localPath));
      }

      InputStream in = AbstractXMLEntityResolver.class.getResourceAsStream(localPath);
      if (in == null) {
         NucleusLogger.METADATA.fatal("local resource \"" + localPath + "\" does not exist!!!");
         throw new FileNotFoundException("Unable to load resource: " + localPath);
      } else {
         return new InputSource(new InputStreamReader(in));
      }
   }
}
