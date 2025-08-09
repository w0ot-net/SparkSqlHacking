package org.datanucleus.metadata.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.datanucleus.ClassConstants;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.InvalidMetaDataException;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MetaDataParser extends DefaultHandler {
   protected final MetaDataManager mgr;
   protected final PluginManager pluginMgr;
   protected final boolean validate;
   protected boolean namespaceAware = true;
   SAXParser parser = null;

   public MetaDataParser(MetaDataManager mgr, PluginManager pluginMgr, boolean validate) {
      this.mgr = mgr;
      this.pluginMgr = pluginMgr;
      this.validate = validate;
   }

   public void setNamespaceAware(boolean aware) {
      if (this.namespaceAware != aware) {
         this.parser = null;
      }

      this.namespaceAware = aware;
   }

   public MetaData parseMetaDataURL(URL url, String handlerName) {
      if (url == null) {
         String msg = Localiser.msg("044031");
         NucleusLogger.METADATA.error(msg);
         throw new NucleusException(msg);
      } else {
         InputStream in = null;

         try {
            in = url.openStream();
         } catch (Exception var6) {
         }

         if (in == null) {
            try {
               in = new FileInputStream(StringUtils.getFileForFilename(url.getFile()));
            } catch (Exception var5) {
            }
         }

         if (in == null) {
            NucleusLogger.METADATA.error(Localiser.msg("044032", url.toString()));
            throw new NucleusException(Localiser.msg("044032", url.toString()));
         } else {
            return this.parseMetaDataStream(in, url.toString(), handlerName);
         }
      }
   }

   public MetaData parseMetaDataFile(String fileName, String handlerName) {
      InputStream in = null;

      try {
         in = (new URL(fileName)).openStream();
      } catch (Exception var6) {
      }

      if (in == null) {
         try {
            in = new FileInputStream(StringUtils.getFileForFilename(fileName));
         } catch (Exception var5) {
         }
      }

      if (in == null) {
         NucleusLogger.METADATA.error(Localiser.msg("044032", fileName));
         throw new NucleusException(Localiser.msg("044032", fileName));
      } else {
         return this.parseMetaDataStream(in, fileName, handlerName);
      }
   }

   public synchronized MetaData parseMetaDataStream(InputStream in, String filename, String handlerName) {
      if (in == null) {
         throw new NullPointerException("input stream is null");
      } else {
         if (NucleusLogger.METADATA.isDebugEnabled()) {
            NucleusLogger.METADATA.debug(Localiser.msg("044030", filename, handlerName, this.validate ? "true" : "false"));
         }

         MetaData e;
         try {
            if (this.parser == null) {
               SAXParserFactory factory = SAXParserFactory.newInstance();
               factory.setValidating(this.validate);
               factory.setNamespaceAware(this.namespaceAware);
               if (this.validate) {
                  try {
                     Schema schema = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(this.getRegisteredSchemas(this.pluginMgr));
                     if (schema != null) {
                        try {
                           factory.setSchema(schema);
                        } catch (UnsupportedOperationException e) {
                           NucleusLogger.METADATA.info(e.getMessage());
                        }
                     }
                  } catch (Exception e) {
                     NucleusLogger.METADATA.info(e.getMessage());
                  }

                  try {
                     factory.setFeature("http://apache.org/xml/features/validation/schema", true);
                  } catch (Exception e) {
                     NucleusLogger.METADATA.info(e.getMessage());
                  }
               }

               this.parser = factory.newSAXParser();
            }

            DefaultHandler handler = null;
            EntityResolver entityResolver = null;

            try {
               entityResolver = EntityResolverFactory.getInstance(this.pluginMgr, handlerName);
               if (entityResolver != null) {
                  this.parser.getXMLReader().setEntityResolver(entityResolver);
               }

               Class[] argTypes = new Class[]{ClassConstants.METADATA_MANAGER, String.class, EntityResolver.class};
               Object[] argValues = new Object[]{this.mgr, filename, entityResolver};
               handler = (DefaultHandler)this.pluginMgr.createExecutableExtension("org.datanucleus.metadata_handler", "name", handlerName, "class-name", argTypes, argValues);
               if (handler == null) {
                  throw (new NucleusUserException(Localiser.msg("044028", handlerName))).setFatal();
               }
            } catch (Exception e) {
               String msg = Localiser.msg("044029", handlerName, e.getMessage());
               throw new NucleusException(msg, e);
            }

            ((AbstractMetaDataHandler)handler).setValidate(this.validate);
            this.parser.parse(in, handler);
            e = ((AbstractMetaDataHandler)handler).getMetaData();
         } catch (NucleusException e) {
            throw e;
         } catch (Exception var25) {
            Throwable cause = var25;
            if (var25 instanceof SAXException) {
               SAXException se = (SAXException)var25;
               cause = se.getException();
            }

            cause = var25.getCause() == null ? cause : var25.getCause();
            NucleusLogger.METADATA.error(Localiser.msg("044040", filename, cause));
            if (cause instanceof InvalidMetaDataException) {
               throw (InvalidMetaDataException)cause;
            }

            throw new NucleusException(Localiser.msg("044033", var25), cause);
         } finally {
            try {
               in.close();
            } catch (Exception var19) {
            }

         }

         return e;
      }
   }

   private Source[] getRegisteredSchemas(PluginManager pm) {
      ConfigurationElement[] elems = pm.getConfigurationElementsForExtension("org.datanucleus.metadata_entityresolver", (String)null, (String)null);
      Set<Source> sources = new HashSet();

      for(int i = 0; i < elems.length; ++i) {
         if (elems[i].getAttribute("type") == null) {
            InputStream in = MetaDataParser.class.getResourceAsStream(elems[i].getAttribute("url"));
            if (in == null) {
               NucleusLogger.METADATA.warn("local resource \"" + elems[i].getAttribute("url") + "\" does not exist!!!");
            }

            sources.add(new StreamSource(in));
         }
      }

      return (Source[])sources.toArray(new Source[sources.size()]);
   }
}
