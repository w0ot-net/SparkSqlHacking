package org.apache.ivy.osgi.obr.xml;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.Set;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.osgi.core.BundleArtifact;
import org.apache.ivy.osgi.core.BundleCapability;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.BundleRequirement;
import org.apache.ivy.osgi.core.ExportPackage;
import org.apache.ivy.osgi.core.ManifestParser;
import org.apache.ivy.osgi.repo.ManifestAndLocation;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.osgi.util.VersionRange;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class OBRXMLWriter {
   public static ContentHandler newHandler(OutputStream out, String encoding, boolean indent) throws TransformerConfigurationException {
      TransformerHandler hd = XMLHelper.getTransformerHandler();
      hd.getTransformer().setOutputProperty("encoding", encoding);
      hd.getTransformer().setOutputProperty("indent", indent ? "yes" : "no");
      StreamResult stream = new StreamResult(out);
      hd.setResult(stream);
      return hd;
   }

   public static void writeManifests(Iterable manifestAndLocations, ContentHandler handler, boolean quiet) throws SAXException {
      int level = quiet ? 4 : 1;
      handler.startDocument();
      AttributesImpl atts = new AttributesImpl();
      handler.startElement("", "repository", "repository", atts);
      int nbOk = 0;
      int nbRejected = 0;

      for(ManifestAndLocation manifestAndLocation : manifestAndLocations) {
         BundleInfo bundleInfo;
         try {
            bundleInfo = ManifestParser.parseManifest(manifestAndLocation.getManifest());
            bundleInfo.addArtifact(new BundleArtifact(false, manifestAndLocation.getUri(), (String)null));
            if (manifestAndLocation.getSourceURI() != null) {
               bundleInfo.addArtifact(new BundleArtifact(true, manifestAndLocation.getSourceURI(), (String)null));
            }

            ++nbOk;
         } catch (ParseException e) {
            ++nbRejected;
            IvyContext.getContext().getMessageLogger().log("Rejected " + manifestAndLocation.getUri() + ": " + e.getMessage(), level);
            continue;
         }

         saxBundleInfo(bundleInfo, handler);
      }

      handler.endElement("", "repository", "repository");
      handler.endDocument();
      Message.info(nbOk + " bundle" + (nbOk > 1 ? "s" : "") + " added, " + nbRejected + " bundle" + (nbRejected > 1 ? "s" : "") + " rejected.");
   }

   public static void writeBundles(Iterable bundleInfos, ContentHandler handler) throws SAXException {
      handler.startDocument();
      AttributesImpl atts = new AttributesImpl();
      handler.startElement("", "repository", "repository", atts);

      for(BundleInfo bundleInfo : bundleInfos) {
         saxBundleInfo(bundleInfo, handler);
      }

      handler.endElement("", "repository", "repository");
      handler.endDocument();
   }

   private static void saxBundleInfo(BundleInfo bundleInfo, ContentHandler handler) throws SAXException {
      AttributesImpl atts = new AttributesImpl();
      addAttr(atts, "symbolicname", bundleInfo.getSymbolicName());
      addAttr(atts, "version", (Object)bundleInfo.getRawVersion());

      for(BundleArtifact artifact : bundleInfo.getArtifacts()) {
         if (!artifact.isSource()) {
            addAttr(atts, "uri", ((BundleArtifact)bundleInfo.getArtifacts().get(0)).getUri().toString());
            break;
         }
      }

      handler.startElement("", "resource", "resource", atts);

      for(BundleArtifact artifact : bundleInfo.getArtifacts()) {
         if (artifact.isSource()) {
            startElement(handler, "source");
            characters(handler, artifact.getUri().toString());
            endElement(handler, "source");
            break;
         }
      }

      for(BundleCapability capability : bundleInfo.getCapabilities()) {
         saxCapability(capability, handler);
      }

      for(BundleRequirement requirement : bundleInfo.getRequirements()) {
         saxRequirement(requirement, handler);
      }

      handler.endElement("", "resource", "resource");
      handler.characters("\n".toCharArray(), 0, 1);
   }

   private static void saxCapability(BundleCapability capability, ContentHandler handler) throws SAXException {
      AttributesImpl atts = new AttributesImpl();
      String type = capability.getType();
      addAttr(atts, "name", type);
      handler.startElement("", "capability", "capability", atts);
      switch (type) {
         case "bundle":
         default:
            break;
         case "package":
            saxCapabilityProperty("package", capability.getName(), handler);
            Version v = capability.getRawVersion();
            if (v != null) {
               saxCapabilityProperty("version", v.toString(), handler);
            }

            Set<String> uses = ((ExportPackage)capability).getUses();
            if (uses != null && !uses.isEmpty()) {
               StringBuilder builder = new StringBuilder();

               for(String use : uses) {
                  if (builder.length() != 0) {
                     builder.append(',');
                  }

                  builder.append(use);
               }

               saxCapabilityProperty("uses", builder.toString(), handler);
            }
            break;
         case "service":
            saxCapabilityProperty("service", capability.getName(), handler);
            Version v = capability.getRawVersion();
            if (v != null) {
               saxCapabilityProperty("version", v.toString(), handler);
            }
      }

      handler.endElement("", "capability", "capability");
      handler.characters("\n".toCharArray(), 0, 1);
   }

   private static void saxCapabilityProperty(String n, String v, ContentHandler handler) throws SAXException {
      saxCapabilityProperty(n, (String)null, v, handler);
   }

   private static void saxCapabilityProperty(String n, String t, String v, ContentHandler handler) throws SAXException {
      AttributesImpl atts = new AttributesImpl();
      addAttr(atts, "n", n);
      if (t != null) {
         addAttr(atts, "t", t);
      }

      addAttr(atts, "v", v);
      handler.startElement("", "p", "p", atts);
      handler.endElement("", "p", "p");
      handler.characters("\n".toCharArray(), 0, 1);
   }

   private static void saxRequirement(BundleRequirement requirement, ContentHandler handler) throws SAXException {
      AttributesImpl atts = new AttributesImpl();
      addAttr(atts, "name", requirement.getType());
      if ("optional".equals(requirement.getResolution())) {
         addAttr(atts, "optional", Boolean.TRUE.toString());
      }

      addAttr(atts, "filter", buildFilter(requirement));
      handler.startElement("", "require", "require", atts);
      handler.endElement("", "require", "require");
      handler.characters("\n".toCharArray(), 0, 1);
   }

   private static String buildFilter(BundleRequirement requirement) {
      StringBuilder filter = new StringBuilder();
      VersionRange v = requirement.getVersion();
      if (v != null) {
         appendVersion(filter, v);
      }

      filter.append('(');
      filter.append(requirement.getType());
      filter.append("=");
      filter.append(requirement.getName());
      filter.append(')');
      if (v != null) {
         filter.append(')');
      }

      return filter.toString();
   }

   private static void appendVersion(StringBuilder filter, VersionRange v) {
      filter.append("(&");
      Version start = v.getStartVersion();
      if (start != null) {
         if (!v.isStartExclusive()) {
            filter.append("(version>=");
            filter.append(start.toString());
            filter.append(')');
         } else {
            filter.append("(!");
            filter.append("(version<=");
            filter.append(start.toString());
            filter.append("))");
         }
      }

      Version end = v.getEndVersion();
      if (end != null) {
         if (!v.isEndExclusive()) {
            filter.append("(version<=");
            filter.append(end.toString());
            filter.append(')');
         } else {
            filter.append("(!");
            filter.append("(version>=");
            filter.append(end.toString());
            filter.append("))");
         }
      }

   }

   private static void addAttr(AttributesImpl atts, String name, String value) {
      if (value != null) {
         atts.addAttribute("", name, name, "CDATA", value);
      }

   }

   private static void addAttr(AttributesImpl atts, String name, Object value) {
      if (value != null) {
         atts.addAttribute("", name, name, "CDATA", value.toString());
      }

   }

   private static void startElement(ContentHandler handler, String name) throws SAXException {
      handler.startElement("", name, name, new AttributesImpl());
   }

   private static void endElement(ContentHandler handler, String name) throws SAXException {
      handler.endElement("", name, name);
   }

   private static void characters(ContentHandler handler, String value) throws SAXException {
      char[] chars = value.toCharArray();
      handler.characters(chars, 0, chars.length);
   }
}
