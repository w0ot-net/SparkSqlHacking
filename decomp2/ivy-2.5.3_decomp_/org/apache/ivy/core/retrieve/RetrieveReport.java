package org.apache.ivy.core.retrieve;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.ivy.core.report.ArtifactDownloadReport;

public class RetrieveReport {
   private Collection upToDateFiles = new HashSet();
   private Collection copiedFiles = new HashSet();
   private Map downloadReport = new HashMap();
   private File retrieveRoot;

   public File getRetrieveRoot() {
      return this.retrieveRoot;
   }

   public void setRetrieveRoot(File retrieveRoot) {
      this.retrieveRoot = retrieveRoot;
   }

   public int getNbrArtifactsCopied() {
      return this.copiedFiles.size();
   }

   public int getNbrArtifactsUpToDate() {
      return this.upToDateFiles.size();
   }

   public void addCopiedFile(File file, ArtifactDownloadReport report) {
      this.copiedFiles.add(file);
      this.downloadReport.put(file, report);
   }

   public void addUpToDateFile(File file, ArtifactDownloadReport report) {
      this.upToDateFiles.add(file);
      this.downloadReport.put(file, report);
   }

   public Collection getCopiedFiles() {
      return new ArrayList(this.copiedFiles);
   }

   public Collection getUpToDateFiles() {
      return new ArrayList(this.upToDateFiles);
   }

   public Collection getRetrievedFiles() {
      Collection<File> result = new ArrayList(this.upToDateFiles.size() + this.copiedFiles.size());
      result.addAll(this.upToDateFiles);
      result.addAll(this.copiedFiles);
      return result;
   }

   public Map getDownloadReport() {
      return this.downloadReport;
   }
}
