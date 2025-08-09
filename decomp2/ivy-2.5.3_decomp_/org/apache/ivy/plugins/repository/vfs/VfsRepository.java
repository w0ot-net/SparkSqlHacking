package org.apache.ivy.plugins.repository.vfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.repository.AbstractRepository;
import org.apache.ivy.plugins.repository.RepositoryCopyProgressListener;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class VfsRepository extends AbstractRepository {
   private static final String IVY_VFS_CONFIG = "ivy_vfs.xml";
   private StandardFileSystemManager manager = null;
   private final CopyProgressListener progress = new RepositoryCopyProgressListener(this);

   public VfsRepository() {
   }

   public VfsRepository(TimeoutConstraint timeoutConstraint) {
      super(timeoutConstraint);
   }

   private FileSystemManager getVFSManager() throws IOException {
      synchronized(this) {
         if (this.manager == null) {
            this.manager = this.createVFSManager();
         }
      }

      return this.manager;
   }

   private StandardFileSystemManager createVFSManager() throws IOException {
      StandardFileSystemManager result = null;

      try {
         result = new StandardFileSystemManager() {
            protected void configurePlugins() throws FileSystemException {
            }
         };
         result.setConfiguration(this.getClass().getResource("ivy_vfs.xml"));
         result.init();
         Message.verbose("Available VFS schemes...");
         String[] schemes = result.getSchemes();
         Arrays.sort(schemes);

         for(String scheme : schemes) {
            Message.verbose("VFS Supported Scheme: " + scheme);
         }

         return result;
      } catch (FileSystemException e) {
         Message.error("Unable to initialize VFS repository manager!");
         Message.error(e.getLocalizedMessage());
         throw new IOException(e.getLocalizedMessage(), e);
      }
   }

   protected void finalize() {
      if (this.manager != null) {
         this.manager.close();
         this.manager = null;
      }

   }

   public Resource getResource(String vfsURI) throws IOException {
      return new VfsResource(vfsURI, this.getVFSManager());
   }

   public void get(String srcVfsURI, File destination) throws IOException {
      VfsResource src = new VfsResource(srcVfsURI, this.getVFSManager());
      this.fireTransferInitiated(src, 5);

      try {
         FileContent content = src.getContent();
         if (content == null) {
            throw new IllegalArgumentException("invalid vfs uri " + srcVfsURI + ": no content found");
         } else {
            FileUtil.copy(content.getInputStream(), destination, this.progress);
         }
      } catch (RuntimeException | IOException ex) {
         this.fireTransferError(ex);
         throw ex;
      }
   }

   public List list(String vfsURI) throws IOException {
      List<String> list = new ArrayList();
      Message.debug("list called for URI" + vfsURI);
      FileObject resourceImpl = this.getVFSManager().resolveFile(vfsURI);
      Message.debug("resourceImpl=" + resourceImpl.toString());
      Message.debug("resourceImpl.exists()" + resourceImpl.exists());
      Message.debug("resourceImpl.getType()" + resourceImpl.getType());
      Message.debug("FileType.FOLDER" + FileType.FOLDER);
      if (resourceImpl.exists() && resourceImpl.getType() == FileType.FOLDER) {
         List<FileObject> children = Arrays.asList(resourceImpl.getChildren());

         for(FileObject child : children) {
            Message.debug("child " + children.indexOf(child) + child.getName().getURI());
            list.add(VfsResource.normalize(child.getName().getURI()));
         }
      }

      return list;
   }

   public void put(File source, String vfsURI, boolean overwrite) throws IOException {
      VfsResource dest = new VfsResource(vfsURI, this.getVFSManager());
      this.fireTransferInitiated(dest, 6);
      if (dest.physicallyExists() && !overwrite) {
         throw new IOException("Cannot copy. Destination file: " + dest.getName() + " exists and overwrite not set.");
      } else if (dest.getContent() == null) {
         throw new IllegalArgumentException("invalid vfs uri " + vfsURI + " to put data to: resource has no content");
      } else {
         FileUtil.copy((InputStream)(new FileInputStream(source)), (OutputStream)dest.getContent().getOutputStream(), this.progress);
      }
   }
}
