package org.apache.ivy.core.module.descriptor;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.id.ArtifactRevisionId;
import org.apache.ivy.core.module.id.ModuleRevisionId;

public class MDArtifact extends AbstractArtifact {
   private ModuleDescriptor md;
   private String name;
   private String type;
   private String ext;
   private final List confs;
   private Map extraAttributes;
   private URL url;
   private boolean isMetadata;

   public static Artifact newIvyArtifact(ModuleDescriptor md) {
      return new MDArtifact(md, "ivy", "ivy", "xml", true);
   }

   public MDArtifact(ModuleDescriptor md, String name, String type, String ext) {
      this(md, name, type, ext, (URL)null, (Map)null);
   }

   public MDArtifact(ModuleDescriptor md, String name, String type, String ext, boolean isMetadata) {
      this(md, name, type, ext, (URL)null, (Map)null);
      this.isMetadata = isMetadata;
   }

   public MDArtifact(ModuleDescriptor md, String name, String type, String ext, URL url, Map extraAttributes) {
      this.confs = new ArrayList();
      this.extraAttributes = null;
      this.isMetadata = false;
      if (md == null) {
         throw new NullPointerException("null module descriptor not allowed");
      } else if (name == null) {
         throw new NullPointerException("null name not allowed");
      } else if (type == null) {
         throw new NullPointerException("null type not allowed");
      } else if (ext == null) {
         throw new NullPointerException("null ext not allowed");
      } else {
         this.md = md;
         this.name = name;
         this.type = type;
         this.ext = ext;
         this.url = url;
         this.extraAttributes = extraAttributes;
      }
   }

   public ModuleRevisionId getModuleRevisionId() {
      return this.md.getResolvedModuleRevisionId();
   }

   public Date getPublicationDate() {
      return this.md.getResolvedPublicationDate();
   }

   public ArtifactRevisionId getId() {
      return ArtifactRevisionId.newInstance(this.md.getResolvedModuleRevisionId(), this.name, this.type, this.ext, this.extraAttributes);
   }

   public String getName() {
      return this.name;
   }

   public String getType() {
      return this.type;
   }

   public String getExt() {
      return this.ext;
   }

   public String[] getConfigurations() {
      return (String[])this.confs.toArray(new String[this.confs.size()]);
   }

   public void addConfiguration(String conf) {
      this.confs.add(conf);
   }

   public URL getUrl() {
      return this.url;
   }

   public boolean isMetadata() {
      return this.isMetadata;
   }
}
