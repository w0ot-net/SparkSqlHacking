package org.sparkproject.jetty.http.pathmap;

public enum PathSpecGroup {
   ROOT,
   EXACT,
   MIDDLE_GLOB,
   PREFIX_GLOB,
   SUFFIX_GLOB,
   DEFAULT;

   // $FF: synthetic method
   private static PathSpecGroup[] $values() {
      return new PathSpecGroup[]{ROOT, EXACT, MIDDLE_GLOB, PREFIX_GLOB, SUFFIX_GLOB, DEFAULT};
   }
}
