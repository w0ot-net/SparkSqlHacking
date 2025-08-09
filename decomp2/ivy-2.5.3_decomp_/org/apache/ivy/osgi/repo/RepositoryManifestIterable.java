package org.apache.ivy.osgi.repo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.resolver.util.ResolverHelper;

public class RepositoryManifestIterable extends AbstractFSManifestIterable {
   private final Repository repo;

   public RepositoryManifestIterable(Repository repo) {
      super("");
      this.repo = repo;
   }

   protected URI buildBundleURI(String location) throws IOException {
      Resource resource = this.repo.getResource(location);

      try {
         return new URI(resource.getName());
      } catch (URISyntaxException var4) {
         return (new File(resource.getName())).toURI();
      }
   }

   protected InputStream getInputStream(String f) throws IOException {
      return this.repo.getResource(f).openStream();
   }

   protected List listBundleFiles(String dir) throws IOException {
      return this.asList(ResolverHelper.listAll(this.repo, dir));
   }

   protected List listDirs(String dir) throws IOException {
      return this.asList(ResolverHelper.listAll(this.repo, dir));
   }

   private List asList(String[] array) {
      return array == null ? Collections.emptyList() : Arrays.asList(array);
   }
}
