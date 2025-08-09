package org.apache.commons.text.lookup;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractPathFencedLookup extends AbstractStringLookup {
   protected final List fences;

   AbstractPathFencedLookup(Path... fences) {
      this.fences = fences != null ? (List)Arrays.stream(fences).map(Path::toAbsolutePath).collect(Collectors.toList()) : Collections.emptyList();
   }

   protected Path getPath(String fileName) {
      Path path = Paths.get(fileName);
      if (this.fences.isEmpty()) {
         return path;
      } else {
         Path pathAbs = path.normalize().toAbsolutePath();
         Stream var10000 = this.fences.stream();
         Objects.requireNonNull(pathAbs);
         Optional<Path> first = var10000.filter(pathAbs::startsWith).findFirst();
         if (first.isPresent()) {
            return path;
         } else {
            throw IllegalArgumentExceptions.format("[%s] -> [%s] not in %s", fileName, pathAbs, this.fences);
         }
      }
   }
}
