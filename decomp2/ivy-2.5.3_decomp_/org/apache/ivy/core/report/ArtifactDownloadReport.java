package org.apache.ivy.core.report;

import java.io.File;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;

public class ArtifactDownloadReport {
   public static final String MISSING_ARTIFACT = "missing artifact";
   private Artifact artifact;
   private ArtifactOrigin origin;
   private File localFile;
   private DownloadStatus downloadStatus;
   private long size;
   private String downloadDetails = "";
   private long downloadTimeMillis;
   private File unpackedLocalFile;
   private Artifact unpackedArtifact;

   public ArtifactDownloadReport(Artifact artifact) {
      this.artifact = artifact;
   }

   public DownloadStatus getDownloadStatus() {
      return this.downloadStatus;
   }

   public void setDownloadStatus(DownloadStatus downloadStatus) {
      this.downloadStatus = downloadStatus;
   }

   public String getName() {
      return this.artifact.getName();
   }

   public String getType() {
      return this.artifact.getType();
   }

   public Artifact getArtifact() {
      return this.artifact;
   }

   public String getExt() {
      return this.artifact.getExt();
   }

   public long getSize() {
      return this.size;
   }

   public void setSize(long size) {
      this.size = size;
   }

   public void setArtifactOrigin(ArtifactOrigin origin) {
      this.origin = origin;
   }

   public ArtifactOrigin getArtifactOrigin() {
      return this.origin;
   }

   public void setDownloadDetails(String message) {
      this.downloadDetails = message;
   }

   public String getDownloadDetails() {
      return this.downloadDetails;
   }

   public void setDownloadTimeMillis(long l) {
      this.downloadTimeMillis = l;
   }

   public long getDownloadTimeMillis() {
      return this.downloadTimeMillis;
   }

   public String toString() {
      if (this.downloadStatus == DownloadStatus.SUCCESSFUL) {
         return "[SUCCESSFUL ] " + this.artifact + " (" + this.downloadTimeMillis + "ms)";
      } else if (this.downloadStatus == DownloadStatus.FAILED) {
         return "missing artifact".equals(this.downloadDetails) ? "[NOT FOUND  ] " + this.artifact + " (" + this.downloadTimeMillis + "ms)" : "[FAILED     ] " + this.artifact + ": " + this.downloadDetails + " (" + this.downloadTimeMillis + "ms)";
      } else {
         return this.downloadStatus == DownloadStatus.NO ? "[NOT REQUIRED] " + this.artifact : super.toString();
      }
   }

   public File getLocalFile() {
      return this.localFile;
   }

   public void setLocalFile(File localFile) {
      this.localFile = localFile;
   }

   public boolean isDownloaded() {
      return DownloadStatus.SUCCESSFUL == this.downloadStatus;
   }

   public void setUnpackedLocalFile(File unpackedLocalFile) {
      this.unpackedLocalFile = unpackedLocalFile;
   }

   public File getUnpackedLocalFile() {
      return this.unpackedLocalFile;
   }

   public void setUnpackedArtifact(Artifact unpackedArtifact) {
      this.unpackedArtifact = unpackedArtifact;
   }

   public Artifact getUnpackedArtifact() {
      return this.unpackedArtifact;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.artifact == null ? 0 : this.artifact.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ArtifactDownloadReport)) {
         return false;
      } else if (this == obj) {
         return true;
      } else {
         ArtifactDownloadReport other = (ArtifactDownloadReport)obj;
         return this.artifact == null ? other.artifact == null : this.artifact.equals(other.artifact);
      }
   }
}
