package org.apache.ivy.core.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.IvySettingsAware;

public class PackagingManager implements IvySettingsAware {
   private IvySettings settings;

   public void setSettings(IvySettings settings) {
      this.settings = settings;
   }

   public Artifact getUnpackedArtifact(Artifact artifact) {
      String packaging = artifact.getExtraAttribute("packaging");
      if (packaging == null) {
         return null;
      } else {
         String ext = artifact.getExt();
         String[] packings = packaging.split(",");

         for(int i = packings.length - 1; i >= 1; --i) {
            ArchivePacking packing = this.settings.getPackingRegistry().get(packings[i]);
            if (packing == null) {
               throw new IllegalStateException("Unknown packing type '" + packings[i] + "' in the packing chain: " + packaging);
            }

            if (!(packing instanceof StreamPacking)) {
               throw new IllegalStateException("Unsupported archive only packing type '" + packings[i] + "' in the streamed chain: " + packaging);
            }

            ext = packing.getUnpackedExtension(ext);
         }

         ArchivePacking packing = this.settings.getPackingRegistry().get(packings[0]);
         if (packing == null) {
            throw new IllegalStateException("Unknown packing type '" + packings[0] + "' in the packing chain: " + packaging);
         } else {
            ext = packing.getUnpackedExtension(ext);
            return new DefaultArtifact(artifact.getModuleRevisionId(), artifact.getPublicationDate(), artifact.getName(), artifact.getType() + "_unpacked", ext);
         }
      }
   }

   public Artifact unpackArtifact(Artifact artifact, File localFile, File archiveFile) throws IOException {
      String packaging = artifact.getExtraAttribute("packaging");
      if (packaging == null) {
         return null;
      } else {
         String ext = artifact.getExt();
         String[] packings = packaging.split(",");
         InputStream in = null;

         try {
            in = new FileInputStream(localFile);

            for(int i = packings.length - 1; i >= 1; --i) {
               ArchivePacking packing = this.settings.getPackingRegistry().get(packings[i]);
               if (packing == null) {
                  throw new IllegalStateException("Unknown packing type '" + packings[i] + "' in the packing chain: " + packaging);
               }

               if (!(packing instanceof StreamPacking)) {
                  throw new IllegalStateException("Unsupported archive only packing type '" + packings[i] + "' in the streamed chain: " + packaging);
               }

               in = ((StreamPacking)packing).unpack(in);
               ext = packing.getUnpackedExtension(ext);
            }

            ArchivePacking packing = this.settings.getPackingRegistry().get(packings[0]);
            if (packing == null) {
               throw new IllegalStateException("Unknown packing type '" + packings[0] + "' in the packing chain: " + packaging);
            }

            packing.unpack(in, archiveFile);
            ext = packing.getUnpackedExtension(ext);
         } finally {
            if (in != null) {
               try {
                  in.close();
               } catch (IOException var15) {
               }
            }

         }

         return new DefaultArtifact(artifact.getModuleRevisionId(), artifact.getPublicationDate(), artifact.getName(), artifact.getType() + "_unpacked", ext);
      }
   }
}
