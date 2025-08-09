package org.apache.ivy.ant;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.report.XmlReportOutputter;
import org.apache.ivy.plugins.report.XmlReportParser;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.ivy.util.XMLHelper;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.XSLTProcess;
import org.apache.tools.ant.util.JAXPUtils;

public class IvyReport extends IvyTask {
   private File todir;
   private String organisation;
   private String module;
   private String conf;
   private boolean graph = true;
   private boolean dot = false;
   private boolean xml = false;
   private boolean xsl = true;
   private File xslFile;
   private String outputpattern;
   private String xslext = "html";
   private final List params = new ArrayList();
   private String resolveId;
   private ModuleRevisionId mRevId;

   public File getTodir() {
      return this.todir;
   }

   public void setTodir(File todir) {
      this.todir = todir;
   }

   public void setCache(File cache) {
      this.cacheAttributeNotSupported();
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public boolean isGraph() {
      return this.graph;
   }

   public void setGraph(boolean graph) {
      this.graph = graph;
   }

   public File getXslfile() {
      return this.xslFile;
   }

   public void setXslfile(File xslFile) {
      this.xslFile = xslFile;
   }

   public String getOutputpattern() {
      return this.outputpattern;
   }

   public void setOutputpattern(String outputpattern) {
      this.outputpattern = outputpattern;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public void setResolveId(String resolveId) {
      this.resolveId = resolveId;
   }

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();
      this.conf = this.getProperty(this.conf, settings, "ivy.resolved.configurations", this.resolveId);
      if ("*".equals(this.conf)) {
         this.conf = this.getProperty(settings, "ivy.resolved.configurations", this.resolveId);
      }

      if (this.conf == null) {
         throw new BuildException("no conf provided for ivy report task: It can either be set explicitly via the attribute 'conf' or via 'ivy.resolved.configurations' property or a prior call to <resolve/>");
      } else {
         if (this.todir == null) {
            String t = this.getProperty(settings, "ivy.report.todir");
            if (t != null) {
               this.todir = this.getProject().resolveFile(t);
            }
         }

         if (this.todir != null && this.todir.exists()) {
            this.todir.mkdirs();
         }

         this.outputpattern = this.getProperty(this.outputpattern, settings, "ivy.report.output.pattern");
         if (this.outputpattern == null) {
            this.outputpattern = "[organisation]-[module]-[conf].[ext]";
         }

         if (this.todir != null && this.todir.exists() && !this.todir.isDirectory()) {
            throw new BuildException("destination directory should be a directory !");
         } else {
            if (this.resolveId == null) {
               this.organisation = this.getProperty(this.organisation, settings, "ivy.organisation", this.resolveId);
               this.module = this.getProperty(this.module, settings, "ivy.module", this.resolveId);
               if (this.organisation == null) {
                  throw new BuildException("no organisation provided for ivy report task: It can either be set explicitly via the attribute 'organisation' or via 'ivy.organisation' property or a prior call to <resolve/>");
               }

               if (this.module == null) {
                  throw new BuildException("no module name provided for ivy report task: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property or a prior call to <resolve/>");
               }

               this.resolveId = ResolveOptions.getDefaultResolveId(new ModuleId(this.organisation, this.module));
            }

            try {
               String[] confs = StringUtils.splitToArray(this.conf);
               if (this.xsl) {
                  this.genreport(confs);
               }

               if (this.xml) {
                  this.genxml(confs);
               }

               if (this.graph) {
                  this.genStyled(confs, this.getStylePath("ivy-report-graph.xsl"), "graphml");
               }

               if (this.dot) {
                  this.genStyled(confs, this.getStylePath("ivy-report-dot.xsl"), "dot");
               }

            } catch (IOException e) {
               throw new BuildException("impossible to generate report: " + e, e);
            }
         }
      }
   }

   private void genxml(String[] confs) throws IOException {
      ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();

      for(String config : confs) {
         File xml = cacheMgr.getConfigurationResolveReportInCache(this.resolveId, config);
         File out;
         if (this.todir == null) {
            out = this.getProject().resolveFile(this.getOutputPattern(config, "xml"));
         } else {
            out = new File(this.todir, this.getOutputPattern(config, "xml"));
         }

         FileUtil.copy((File)xml, (File)out, (CopyProgressListener)null);
      }

   }

   private void genreport(String[] confs) throws IOException {
      this.genStyled(confs, this.getReportStylePath(), this.xslext);
      if (this.xslFile == null) {
         File css;
         if (this.todir == null) {
            css = this.getProject().resolveFile("ivy-report.css");
         } else {
            css = new File(this.todir, "ivy-report.css");
         }

         if (!css.exists()) {
            Message.debug("copying report css to " + css.getAbsolutePath());
            FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream("ivy-report.css"), (File)css, (CopyProgressListener)null);
         }
      }

   }

   private File getReportStylePath() throws IOException {
      if (this.xslFile != null) {
         return this.xslFile;
      } else {
         ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
         File style = new File(cacheMgr.getResolutionCacheRoot(), "ivy-report.xsl");
         if (!style.exists()) {
            Message.debug("copying ivy-report.xsl to " + style.getAbsolutePath());
            FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream("ivy-report.xsl"), (File)style, (CopyProgressListener)null);
         }

         return style;
      }
   }

   private String getOutputPattern(String conf, String ext) {
      if (this.mRevId == null) {
         ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
         XmlReportParser parser = new XmlReportParser();
         File reportFile = cacheMgr.getConfigurationResolveReportInCache(this.resolveId, conf);

         try {
            parser.parse(reportFile);
         } catch (ParseException e) {
            throw new BuildException("Error occurred while parsing reportfile '" + reportFile.getAbsolutePath() + "'", e);
         }

         this.mRevId = parser.getResolvedModule();
      }

      return IvyPatternHelper.substitute(this.outputpattern, this.mRevId.getOrganisation(), this.mRevId.getName(), this.mRevId.getRevision(), "", "", ext, conf, this.mRevId.getQualifiedExtraAttributes(), (Map)null);
   }

   private void genStyled(String[] confs, File style, String ext) throws IOException {
      ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
      File out;
      if (this.todir != null) {
         out = this.todir;
      } else {
         out = this.getProject().getBaseDir();
      }

      try {
         InputStream xsltStream = new BufferedInputStream(new FileInputStream(style));
         Throwable var7 = null;

         try {
            Source xsltSource = new StreamSource(xsltStream, JAXPUtils.getSystemId(style));
            Transformer transformer = XMLHelper.getTransformer(xsltSource);
            transformer.setParameter("confs", this.conf);
            transformer.setParameter("extension", this.xslext);

            for(XSLTProcess.Param param : this.params) {
               transformer.setParameter(param.getName(), param.getExpression());
            }

            for(String config : confs) {
               File reportFile = cacheMgr.getConfigurationResolveReportInCache(this.resolveId, config);
               File outFile = new File(out, this.getOutputPattern(config, ext));
               this.log("Processing " + reportFile + " to " + outFile);
               File outFileDir = outFile.getParentFile();
               if (!outFileDir.exists() && !outFileDir.mkdirs()) {
                  throw new BuildException("Unable to create directory: " + outFileDir.getAbsolutePath());
               }

               try {
                  InputStream inStream = new BufferedInputStream(new FileInputStream(reportFile));
                  Throwable var18 = null;

                  try {
                     OutputStream outStream = new BufferedOutputStream(new FileOutputStream(outFile));
                     Throwable var20 = null;

                     try {
                        StreamResult res = new StreamResult(outStream);
                        Source src = new StreamSource(inStream, JAXPUtils.getSystemId(style));
                        transformer.transform(src, res);
                     } catch (Throwable var71) {
                        var20 = var71;
                        throw var71;
                     } finally {
                        if (outStream != null) {
                           if (var20 != null) {
                              try {
                                 outStream.close();
                              } catch (Throwable var70) {
                                 var20.addSuppressed(var70);
                              }
                           } else {
                              outStream.close();
                           }
                        }

                     }
                  } catch (Throwable var73) {
                     var18 = var73;
                     throw var73;
                  } finally {
                     if (inStream != null) {
                        if (var18 != null) {
                           try {
                              inStream.close();
                           } catch (Throwable var69) {
                              var18.addSuppressed(var69);
                           }
                        } else {
                           inStream.close();
                        }
                     }

                  }
               } catch (TransformerException e) {
                  throw new BuildException(e);
               }
            }
         } catch (Throwable var76) {
            var7 = var76;
            throw var76;
         } finally {
            if (xsltStream != null) {
               if (var7 != null) {
                  try {
                     xsltStream.close();
                  } catch (Throwable var68) {
                     var7.addSuppressed(var68);
                  }
               } else {
                  xsltStream.close();
               }
            }

         }

      } catch (TransformerConfigurationException e) {
         throw new BuildException(e);
      }
   }

   private File getStylePath(String styleResourceName) throws IOException {
      ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
      File style = new File(cacheMgr.getResolutionCacheRoot(), styleResourceName);
      FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream(styleResourceName), (File)style, (CopyProgressListener)null);
      return style;
   }

   public boolean isXml() {
      return this.xml;
   }

   public void setXml(boolean xml) {
      this.xml = xml;
   }

   public boolean isXsl() {
      return this.xsl;
   }

   public void setXsl(boolean xsl) {
      this.xsl = xsl;
   }

   public String getXslext() {
      return this.xslext;
   }

   public void setXslext(String xslext) {
      this.xslext = xslext;
   }

   public XSLTProcess.Param createParam() {
      XSLTProcess.Param result = new XSLTProcess.Param();
      this.params.add(result);
      return result;
   }

   public boolean isDot() {
      return this.dot;
   }

   public void setDot(boolean dot) {
      this.dot = dot;
   }
}
