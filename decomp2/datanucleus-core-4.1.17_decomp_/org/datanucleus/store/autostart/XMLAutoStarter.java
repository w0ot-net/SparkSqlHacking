package org.datanucleus.store.autostart;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.store.StoreData;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.exceptions.DatastoreInitialisationException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLAutoStarter extends AbstractAutoStartMechanism {
   protected final URL fileUrl;
   protected Document doc;
   protected Element rootElement;
   String version = null;
   Set autoStartClasses = new HashSet();

   public XMLAutoStarter(StoreManager storeMgr, ClassLoaderResolver clr) throws MalformedURLException {
      this.fileUrl = new URL("file:" + storeMgr.getStringProperty("datanucleus.autoStartMechanismXmlFile"));
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder db = factory.newDocumentBuilder();

         try {
            db.setEntityResolver(new XMLAutoStarterEntityResolver());
            this.rootElement = db.parse(new InputSource(new InputStreamReader(this.fileUrl.openStream()))).getDocumentElement();
            this.doc = this.rootElement.getOwnerDocument();
         } catch (Exception var6) {
            NucleusLogger.PERSISTENCE.info(Localiser.msg("034201", this.fileUrl.getFile()));
            this.doc = db.newDocument();
            this.rootElement = this.doc.createElement("datanucleus_autostart");
            this.doc.appendChild(this.rootElement);
            this.writeToFile();
         }
      } catch (ParserConfigurationException e1) {
         NucleusLogger.PERSISTENCE.error(Localiser.msg("034202", this.fileUrl.getFile(), e1.getMessage()));
      }

      this.version = storeMgr.getNucleusContext().getPluginManager().getVersionForBundle("org.datanucleus");
   }

   public Collection getAllClassData() throws DatastoreInitialisationException {
      Collection classes = new HashSet();
      NodeList classElements = this.rootElement.getElementsByTagName("class");

      for(int i = 0; i < classElements.getLength(); ++i) {
         Element element = (Element)classElements.item(i);
         StoreData data = new StoreData(element.getAttribute("name"), element.getAttribute("type").equals("FCO") ? 1 : 2);
         this.autoStartClasses.add(data.getName());
         NamedNodeMap attributeMap = element.getAttributes();

         for(int j = 0; j < attributeMap.getLength(); ++j) {
            Node attr = attributeMap.item(j);
            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();
            if (!attrName.equals("name") && !attrName.equals("type")) {
               data.addProperty(attrName, attrValue);
            }
         }

         classes.add(data);
      }

      return classes;
   }

   public boolean isOpen() {
      return true;
   }

   public void close() {
      this.writeToFile();
      super.close();
   }

   public void addClass(StoreData data) {
      if (!this.autoStartClasses.contains(data.getName())) {
         Element classElement = this.doc.createElement("class");
         classElement.setAttribute("name", data.getName());
         classElement.setAttribute("type", data.isFCO() ? "FCO" : "SCO");
         classElement.setAttribute("version", this.version);
         Map dataProps = data.getProperties();

         for(Map.Entry entry : dataProps.entrySet()) {
            String key = (String)entry.getKey();
            Object val = entry.getValue();
            if (val instanceof String) {
               classElement.setAttribute(key, (String)val);
            }
         }

         this.rootElement.appendChild(classElement);
      }
   }

   public void deleteClass(String className) {
      this.autoStartClasses.remove(className);
      NodeList classElements = this.rootElement.getElementsByTagName("class");

      for(int i = 0; i < classElements.getLength(); ++i) {
         Element element = (Element)classElements.item(i);
         String attr = element.getAttribute("name");
         if (attr != null && attr.equals(className)) {
            this.rootElement.removeChild(element);
         }
      }

   }

   public void deleteAllClasses() {
      this.autoStartClasses.clear();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder db = factory.newDocumentBuilder();
         this.doc = db.newDocument();
         this.rootElement = this.doc.createElement("datanucleus_autostart");
         this.doc.appendChild(this.rootElement);
      } catch (ParserConfigurationException e) {
         NucleusLogger.PERSISTENCE.error(Localiser.msg("034203", this.fileUrl.getFile(), e.getMessage()));
      }

   }

   public String getStorageDescription() {
      return Localiser.msg("034200");
   }

   private synchronized void writeToFile() {
      FileOutputStream os = null;

      try {
         os = new FileOutputStream(this.fileUrl.getFile());
         StreamResult result = new StreamResult(os);
         Transformer m = TransformerFactory.newInstance().newTransformer();
         m.setOutputProperty("indent", "yes");
         m.setOutputProperty("doctype-public", "-//DataNucleus//DTD DataNucleus AutoStarter Metadata 1.0//EN");
         m.transform(new DOMSource(this.doc), result);
         os.close();
      } catch (Exception e) {
         NucleusLogger.PERSISTENCE.error(Localiser.msg("034203", this.fileUrl.getFile(), e.getMessage()));
      } finally {
         try {
            os.close();
         } catch (IOException var11) {
         }

      }

   }
}
