package org.sparkproject.jetty.http.pathmap;

import java.util.Map;

public class MatchedResource {
   private final Object resource;
   private final PathSpec pathSpec;
   private final MatchedPath matchedPath;

   public MatchedResource(Object resource, PathSpec pathSpec, MatchedPath matchedPath) {
      this.resource = resource;
      this.pathSpec = pathSpec;
      this.matchedPath = matchedPath;
   }

   public static MatchedResource of(Map.Entry mapping, MatchedPath matchedPath) {
      return new MatchedResource(mapping.getValue(), (PathSpec)mapping.getKey(), matchedPath);
   }

   public MatchedPath getMatchedPath() {
      return this.matchedPath;
   }

   public PathSpec getPathSpec() {
      return this.pathSpec;
   }

   public Object getResource() {
      return this.resource;
   }

   public String getPathMatch() {
      return this.matchedPath.getPathMatch();
   }

   public String getPathInfo() {
      return this.matchedPath.getPathInfo();
   }
}
