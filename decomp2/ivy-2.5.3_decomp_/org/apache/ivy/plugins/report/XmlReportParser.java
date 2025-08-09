package org.apache.ivy.plugins.report;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.DownloadStatus;
import org.apache.ivy.core.report.MetadataArtifactDownloadReport;
import org.apache.ivy.util.DateUtil;
import org.apache.ivy.util.XMLHelper;
import org.apache.ivy.util.extendable.ExtendableItemHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlReportParser {
   private SaxXmlReportParser parser = null;

   public void parse(File report) throws ParseException {
      if (!report.exists()) {
         throw new IllegalStateException("Report file '" + report.getAbsolutePath() + "' does not exist.");
      } else {
         this.parser = new SaxXmlReportParser(report);

         try {
            this.parser.parse();
         } catch (Exception e) {
            ParseException pe = new ParseException("failed to parse report: " + report + ": " + e.getMessage(), 0);
            pe.initCause(e);
            throw pe;
         }
      }
   }

   public Artifact[] getArtifacts() {
      return (Artifact[])this.parser.getArtifacts().toArray(new Artifact[this.parser.getArtifacts().size()]);
   }

   public ArtifactDownloadReport[] getArtifactReports() {
      return (ArtifactDownloadReport[])this.parser.getArtifactReports().toArray(new ArtifactDownloadReport[this.parser.getArtifactReports().size()]);
   }

   public ModuleRevisionId[] getDependencyRevisionIds() {
      return (ModuleRevisionId[])this.parser.getModuleRevisionIds().toArray(new ModuleRevisionId[this.parser.getModuleRevisionIds().size()]);
   }

   public ModuleRevisionId[] getRealDependencyRevisionIds() {
      return (ModuleRevisionId[])this.parser.getRealModuleRevisionIds().toArray(new ModuleRevisionId[this.parser.getRealModuleRevisionIds().size()]);
   }

   public MetadataArtifactDownloadReport getMetadataArtifactReport(ModuleRevisionId id) {
      return this.parser.getMetadataArtifactReport(id);
   }

   public ModuleRevisionId getResolvedModule() {
      return this.parser.getResolvedModule();
   }

   public boolean hasError() {
      return this.parser.hasError;
   }

   private static class SaxXmlReportParser {
      private List mrids = new ArrayList();
      private List defaultMrids = new ArrayList();
      private List realMrids = new ArrayList();
      private List artifacts = new ArrayList();
      private List artifactReports = new ArrayList();
      private Map metadataReports = new HashMap();
      private ModuleRevisionId mRevisionId;
      private File report;
      private boolean hasError = false;

      SaxXmlReportParser(File report) {
         this.report = report;
      }

      public void parse() throws Exception {
         XMLHelper.parse(this.report.toURI().toURL(), (URL)null, new XmlReportParserHandler());
      }

      private static boolean parseBoolean(String str) {
         return str != null && str.equalsIgnoreCase("true");
      }

      public List getArtifacts() {
         return this.artifacts;
      }

      public List getArtifactReports() {
         return this.artifactReports;
      }

      public List getModuleRevisionIds() {
         return this.mrids;
      }

      public List getRealModuleRevisionIds() {
         return this.realMrids;
      }

      public ModuleRevisionId getResolvedModule() {
         return this.mRevisionId;
      }

      public MetadataArtifactDownloadReport getMetadataArtifactReport(ModuleRevisionId id) {
         return (MetadataArtifactDownloadReport)this.metadataReports.get(id);
      }

      private final class XmlReportParserHandler extends DefaultHandler {
         private String organisation;
         private String module;
         private String branch;
         private String revision;
         private int position;
         private Date pubdate;
         private boolean skip;
         private ModuleRevisionId mrid;
         private boolean isDefault;
         private SortedMap revisionsMap;
         private List revisionArtifacts;

         private XmlReportParserHandler() {
            this.revisionsMap = new TreeMap();
            this.revisionArtifacts = null;
         }

         public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
               case "module":
                  this.organisation = attributes.getValue("organisation");
                  this.module = attributes.getValue("name");
                  break;
               case "revision":
                  this.revisionArtifacts = new ArrayList();
                  this.branch = attributes.getValue("branch");
                  this.revision = attributes.getValue("name");
                  this.isDefault = Boolean.valueOf(attributes.getValue("default"));
                  String pos = attributes.getValue("position");
                  this.position = pos == null ? this.getMaxPos() + 1 : Integer.valueOf(pos);
                  if (attributes.getValue("error") != null) {
                     SaxXmlReportParser.this.hasError = true;
                     this.skip = true;
                  } else if (attributes.getValue("evicted") != null) {
                     this.skip = true;
                  } else {
                     this.revisionsMap.put(this.position, this.revisionArtifacts);
                     this.mrid = ModuleRevisionId.newInstance(this.organisation, this.module, this.branch, this.revision, ExtendableItemHelper.getExtraAttributes(attributes, "extra-"));
                     SaxXmlReportParser.this.mrids.add(this.mrid);
                     if (this.isDefault) {
                        SaxXmlReportParser.this.defaultMrids.add(this.mrid);
                     } else {
                        Artifact metadataArtifact = DefaultArtifact.newIvyArtifact(this.mrid, this.pubdate);
                        MetadataArtifactDownloadReport madr = new MetadataArtifactDownloadReport(metadataArtifact);
                        SaxXmlReportParser.this.metadataReports.put(this.mrid, madr);
                        SaxXmlReportParser.this.realMrids.add(this.mrid);
                     }

                     try {
                        String pubDateAttr = attributes.getValue("pubdate");
                        if (pubDateAttr != null) {
                           this.pubdate = DateUtil.parse(pubDateAttr);
                        }

                        this.skip = false;
                     } catch (ParseException var20) {
                        throw new IllegalArgumentException("invalid publication date for " + this.organisation + " " + this.module + " " + this.revision + ": " + attributes.getValue("pubdate"));
                     }
                  }
                  break;
               case "metadata-artifact":
                  if (this.skip) {
                     return;
                  }

                  MetadataArtifactDownloadReport madr = (MetadataArtifactDownloadReport)SaxXmlReportParser.this.metadataReports.get(this.mrid);
                  if (madr != null) {
                     madr.setDownloadStatus(DownloadStatus.fromString(attributes.getValue("status")));
                     madr.setDownloadDetails(attributes.getValue("details"));
                     madr.setSize(Long.parseLong(attributes.getValue("size")));
                     madr.setDownloadTimeMillis(Long.parseLong(attributes.getValue("time")));
                     madr.setSearched(XmlReportParser.SaxXmlReportParser.parseBoolean(attributes.getValue("searched")));
                     if (attributes.getValue("location") != null) {
                        madr.setLocalFile(new File(attributes.getValue("location")));
                     }

                     if (attributes.getValue("original-local-location") != null) {
                        madr.setOriginalLocalFile(new File(attributes.getValue("original-local-location")));
                     }

                     if (attributes.getValue("origin-location") != null) {
                        if (ArtifactOrigin.isUnknown(attributes.getValue("origin-location"))) {
                           madr.setArtifactOrigin(ArtifactOrigin.unknown(madr.getArtifact()));
                        } else {
                           madr.setArtifactOrigin(new ArtifactOrigin(madr.getArtifact(), XmlReportParser.SaxXmlReportParser.parseBoolean(attributes.getValue("origin-is-local")), attributes.getValue("origin-location")));
                        }
                     }
                  }
                  break;
               case "artifact":
                  if (this.skip) {
                     return;
                  }

                  String status = attributes.getValue("status");
                  String artifactName = attributes.getValue("name");
                  String type = attributes.getValue("type");
                  String ext = attributes.getValue("ext");
                  Artifact artifact = new DefaultArtifact(this.mrid, this.pubdate, artifactName, type, ext, ExtendableItemHelper.getExtraAttributes(attributes, "extra-"));
                  ArtifactDownloadReport aReport = new ArtifactDownloadReport(artifact);
                  aReport.setDownloadStatus(DownloadStatus.fromString(status));
                  aReport.setDownloadDetails(attributes.getValue("details"));
                  aReport.setSize(Long.parseLong(attributes.getValue("size")));
                  aReport.setDownloadTimeMillis(Long.parseLong(attributes.getValue("time")));
                  if (attributes.getValue("location") != null) {
                     aReport.setLocalFile(new File(attributes.getValue("location")));
                  }

                  if (attributes.getValue("unpackedFile") != null) {
                     aReport.setUnpackedLocalFile(new File(attributes.getValue("unpackedFile")));
                  }

                  this.revisionArtifacts.add(aReport);
                  break;
               case "origin-location":
                  if (this.skip) {
                     return;
                  }

                  ArtifactDownloadReport adr = (ArtifactDownloadReport)this.revisionArtifacts.get(this.revisionArtifacts.size() - 1);
                  if (ArtifactOrigin.isUnknown(attributes.getValue("location"))) {
                     adr.setArtifactOrigin(ArtifactOrigin.unknown(adr.getArtifact()));
                  } else {
                     adr.setArtifactOrigin(new ArtifactOrigin(adr.getArtifact(), XmlReportParser.SaxXmlReportParser.parseBoolean(attributes.getValue("is-local")), attributes.getValue("location")));
                  }
                  break;
               case "info":
                  String organisation = attributes.getValue("organisation");
                  String name = attributes.getValue("module");
                  String branch = attributes.getValue("branch");
                  String revision = attributes.getValue("revision");
                  SaxXmlReportParser.this.mRevisionId = ModuleRevisionId.newInstance(organisation, name, branch, revision, ExtendableItemHelper.getExtraAttributes(attributes, "extra-"));
            }

         }

         public void endElement(String uri, String localName, String qname) throws SAXException {
            if ("dependencies".equals(qname)) {
               for(List artifactReports : this.revisionsMap.values()) {
                  SaxXmlReportParser.this.artifactReports.addAll(artifactReports);

                  for(ArtifactDownloadReport artifactReport : artifactReports) {
                     if (artifactReport.getDownloadStatus() != DownloadStatus.FAILED) {
                        SaxXmlReportParser.this.artifacts.add(artifactReport.getArtifact());
                     }
                  }
               }
            }

         }

         private int getMaxPos() {
            return this.revisionsMap.isEmpty() ? -1 : (Integer)this.revisionsMap.keySet().toArray()[this.revisionsMap.size() - 1];
         }
      }
   }
}
