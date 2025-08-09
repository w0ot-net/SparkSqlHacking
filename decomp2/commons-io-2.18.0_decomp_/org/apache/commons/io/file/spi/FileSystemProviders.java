package org.apache.commons.io.file.spi;

import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileSystemProviders {
   private static final String SCHEME_FILE = "file";
   private static final FileSystemProviders INSTALLED = new FileSystemProviders(FileSystemProvider.installedProviders());
   private final List providers;

   public static FileSystemProvider getFileSystemProvider(Path path) {
      return ((Path)Objects.requireNonNull(path, "path")).getFileSystem().provider();
   }

   public static FileSystemProviders installed() {
      return INSTALLED;
   }

   private FileSystemProviders(List providers) {
      this.providers = providers != null ? providers : Collections.emptyList();
   }

   public FileSystemProvider getFileSystemProvider(String scheme) {
      Objects.requireNonNull(scheme, "scheme");
      return scheme.equalsIgnoreCase("file") ? FileSystems.getDefault().provider() : (FileSystemProvider)this.providers.stream().filter((provider) -> provider.getScheme().equalsIgnoreCase(scheme)).findFirst().orElse((Object)null);
   }

   public FileSystemProvider getFileSystemProvider(URI uri) {
      return this.getFileSystemProvider(((URI)Objects.requireNonNull(uri, "uri")).getScheme());
   }

   public FileSystemProvider getFileSystemProvider(URL url) {
      return this.getFileSystemProvider(((URL)Objects.requireNonNull(url, "url")).getProtocol());
   }
}
