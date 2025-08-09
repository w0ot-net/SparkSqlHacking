package org.apache.ivy.plugins.repository.vfs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.resolver.VfsResolver;
import org.apache.ivy.util.Message;

public class VfsResource implements Resource {
   private String vfsURI;
   private FileSystemManager fsManager;
   private transient boolean init = false;
   private transient boolean exists;
   private transient long lastModified;
   private transient long contentLength;
   private transient FileContent content = null;
   private transient FileObject resourceImpl;

   public VfsResource(String vfsURI, FileSystemManager fsManager) {
      this.vfsURI = vfsURI;
      this.fsManager = fsManager;
      this.init = false;
   }

   private void init() {
      if (!this.init) {
         try {
            this.resourceImpl = this.fsManager.resolveFile(this.vfsURI);
            this.content = this.resourceImpl.getContent();
            this.exists = this.resourceImpl.exists();
            this.lastModified = this.content.getLastModifiedTime();
            this.contentLength = this.content.getSize();
         } catch (FileSystemException e) {
            Message.debug((Throwable)e);
            Message.verbose(e.getLocalizedMessage());
            this.exists = false;
            this.lastModified = 0L;
            this.contentLength = 0L;
         }

         this.init = true;
      }

   }

   public List getChildren() {
      this.init();
      List<String> list = new ArrayList();

      try {
         if (this.resourceImpl != null && this.resourceImpl.exists() && this.resourceImpl.getType() == FileType.FOLDER) {
            for(FileObject child : this.resourceImpl.getChildren()) {
               list.add(normalize(child.getName().getURI()));
            }
         }
      } catch (IOException e) {
         Message.debug((Throwable)e);
         Message.verbose(e.getLocalizedMessage());
      }

      return list;
   }

   public FileContent getContent() {
      this.init();
      return this.content;
   }

   public String getName() {
      return normalize(this.vfsURI);
   }

   public Resource clone(String cloneName) {
      return new VfsResource(cloneName, this.fsManager);
   }

   public static String normalize(String vfsURI) {
      if (vfsURI == null) {
         return "";
      } else {
         if (vfsURI.startsWith("file:////")) {
            vfsURI = vfsURI.replaceFirst("////", "///");
         }

         return vfsURI;
      }
   }

   public long getLastModified() {
      this.init();
      return this.lastModified;
   }

   public long getContentLength() {
      this.init();
      return this.contentLength;
   }

   public boolean exists() {
      this.init();
      return this.exists;
   }

   public boolean physicallyExists() {
      this.init();

      try {
         return this.resourceImpl.exists();
      } catch (Exception e) {
         Message.verbose("Fail to check the existence of the resource " + this.getName(), e);
         return false;
      }
   }

   public String toString() {
      return VfsResolver.prepareForDisplay(this.getName());
   }

   public boolean isLocal() {
      return this.getName().startsWith("file:");
   }

   public InputStream openStream() throws IOException {
      return this.getContent().getInputStream();
   }
}
