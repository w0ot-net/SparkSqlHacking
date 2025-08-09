package io.fabric8.kubernetes.client.dsl.base;

public enum PatchType {
   JSON("application/json-patch+json"),
   JSON_MERGE("application/merge-patch+json"),
   STRATEGIC_MERGE("application/strategic-merge-patch+json"),
   SERVER_SIDE_APPLY("application/apply-patch+yaml");

   private final String contentType;

   private PatchType(String mediaType) {
      this.contentType = mediaType;
   }

   public String getContentType() {
      return this.contentType;
   }
}
