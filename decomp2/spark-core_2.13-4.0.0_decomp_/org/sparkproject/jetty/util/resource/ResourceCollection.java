package org.sparkproject.jetty.util.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.sparkproject.jetty.util.StringUtil;

public class ResourceCollection extends Resource {
   private List _resources;

   public ResourceCollection() {
      this._resources = new ArrayList();
   }

   public ResourceCollection(Resource... resources) {
      this((Collection)Arrays.asList(resources));
   }

   public ResourceCollection(Collection resources) {
      this._resources = new ArrayList();

      for(Resource r : resources) {
         if (r != null) {
            if (r instanceof ResourceCollection) {
               this._resources.addAll(((ResourceCollection)r).getResources());
            } else {
               this.assertResourceValid(r);
               this._resources.add(r);
            }
         }
      }

   }

   public ResourceCollection(String[] resources) {
      this._resources = new ArrayList();
      if (resources != null && resources.length != 0) {
         try {
            for(String strResource : resources) {
               if (strResource == null || strResource.length() == 0) {
                  throw new IllegalArgumentException("empty/null resource path not supported");
               }

               Resource resource = Resource.newResource(strResource);
               this.assertResourceValid(resource);
               this._resources.add(resource);
            }

            if (this._resources.isEmpty()) {
               throw new IllegalArgumentException("resources cannot be empty or null");
            }
         } catch (RuntimeException e) {
            throw e;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public ResourceCollection(String csvResources) throws IOException {
      this.setResources(csvResources);
   }

   public List getResources() {
      return this._resources;
   }

   public void setResources(List res) {
      this._resources = new ArrayList();
      if (!res.isEmpty()) {
         this._resources.addAll(res);
      }
   }

   public void setResources(Resource[] resources) {
      if (resources != null && resources.length != 0) {
         List<Resource> res = new ArrayList();

         for(Resource resource : resources) {
            this.assertResourceValid(resource);
            res.add(resource);
         }

         this.setResources(res);
      } else {
         this._resources = null;
      }
   }

   public void setResources(String resources) throws IOException {
      if (StringUtil.isBlank(resources)) {
         throw new IllegalArgumentException("String is blank");
      } else {
         List<Resource> list = Resource.fromList(resources, false);
         if (list.isEmpty()) {
            throw new IllegalArgumentException("String contains no entries");
         } else {
            List<Resource> ret = new ArrayList();

            for(Resource resource : list) {
               this.assertResourceValid(resource);
               ret.add(resource);
            }

            this.setResources(ret);
         }
      }
   }

   public Resource addPath(String path) throws IOException {
      this.assertResourcesSet();
      if (path == null) {
         throw new MalformedURLException("null path");
      } else if (path.length() != 0 && !"/".equals(path)) {
         ArrayList<Resource> resources = null;
         Resource addedResource = null;

         for(Resource res : this._resources) {
            addedResource = res.addPath(path);
            if (addedResource.exists()) {
               if (!addedResource.isDirectory()) {
                  return addedResource;
               }

               if (resources == null) {
                  resources = new ArrayList();
               }

               resources.add(addedResource);
            }
         }

         if (resources == null) {
            if (addedResource != null) {
               return addedResource;
            } else {
               return EmptyResource.INSTANCE;
            }
         } else if (resources.size() == 1) {
            return (Resource)resources.get(0);
         } else {
            return new ResourceCollection(resources);
         }
      } else {
         return this;
      }
   }

   public boolean delete() throws SecurityException {
      throw new UnsupportedOperationException();
   }

   public boolean exists() {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         if (r.exists()) {
            return true;
         }
      }

      return false;
   }

   public File getFile() throws IOException {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         File f = r.getFile();
         if (f != null) {
            return f;
         }
      }

      return null;
   }

   public InputStream getInputStream() throws IOException {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         if (r.exists()) {
            InputStream is = r.getInputStream();
            if (is != null) {
               return is;
            }
         }
      }

      throw new FileNotFoundException("Resource does not exist");
   }

   public ReadableByteChannel getReadableByteChannel() throws IOException {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         ReadableByteChannel channel = r.getReadableByteChannel();
         if (channel != null) {
            return channel;
         }
      }

      return null;
   }

   public String getName() {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         String name = r.getName();
         if (name != null) {
            return name;
         }
      }

      return null;
   }

   public URI getURI() {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         URI uri = r.getURI();
         if (uri != null) {
            return uri;
         }
      }

      return null;
   }

   public boolean isDirectory() {
      this.assertResourcesSet();
      return true;
   }

   public long lastModified() {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         long lm = r.lastModified();
         if (lm != -1L) {
            return lm;
         }
      }

      return -1L;
   }

   public long length() {
      return -1L;
   }

   public String[] list() {
      this.assertResourcesSet();
      HashSet<String> set = new HashSet();

      for(Resource r : this._resources) {
         String[] list = r.list();
         if (list != null) {
            Collections.addAll(set, list);
         }
      }

      String[] result = (String[])set.toArray(new String[0]);
      Arrays.sort(result);
      return result;
   }

   public void close() {
      this.assertResourcesSet();

      for(Resource r : this._resources) {
         r.close();
      }

   }

   public boolean renameTo(Resource dest) throws SecurityException {
      throw new UnsupportedOperationException();
   }

   public void copyTo(File destination) throws IOException {
      this.assertResourcesSet();
      int r = this._resources.size();

      while(r-- > 0) {
         ((Resource)this._resources.get(r)).copyTo(destination);
      }

   }

   public String toString() {
      return this._resources.isEmpty() ? "[]" : String.valueOf(this._resources);
   }

   public boolean isContainedIn(Resource r) {
      return false;
   }

   private void assertResourcesSet() {
      if (this._resources == null || this._resources.isEmpty()) {
         throw new IllegalStateException("*resources* not set.");
      }
   }

   private void assertResourceValid(Resource resource) {
      if (resource == null) {
         throw new IllegalStateException("Null resource not supported");
      } else if (!resource.exists() || !resource.isDirectory()) {
         throw new IllegalArgumentException(String.valueOf(resource) + " is not an existing directory.");
      }
   }
}
