package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;

public class KubernetesClientException extends RuntimeException {
   private final int code;
   private final Status status;
   private final RequestMetadata requestMetadata;

   public KubernetesClientException(String message) {
      this(message, (Throwable)null);
   }

   public KubernetesClientException(String message, Throwable t) {
      this(message, t, -1, (Status)null, (RequestMetadata)((RequestMetadata)null));
   }

   public KubernetesClientException(Status status) {
      this(status.getMessage(), status.getCode(), status);
   }

   public KubernetesClientException(String message, int code, Status status) {
      this(message, (Throwable)null, code, status, (RequestMetadata)((RequestMetadata)null));
   }

   public KubernetesClientException(String message, Throwable t, int code, Status status, HttpRequest httpRequest) {
      this(message, t, code, status, KubernetesClientException.RequestMetadata.from(httpRequest));
   }

   private KubernetesClientException(String message, Throwable t, int code, Status status, RequestMetadata requestMetadata) {
      super(message, t);
      this.code = code;
      this.status = status;
      this.requestMetadata = requestMetadata == null ? KubernetesClientException.RequestMetadata.EMPTY : requestMetadata;
   }

   public Status getStatus() {
      return this.status;
   }

   public int getCode() {
      return this.code;
   }

   public String getGroup() {
      return this.requestMetadata.group;
   }

   public String getVersion() {
      return this.requestMetadata.version;
   }

   public String getResourcePlural() {
      return this.requestMetadata.plural;
   }

   public String getNamespace() {
      return this.requestMetadata.namespace;
   }

   public String getName() {
      return this.requestMetadata.name;
   }

   public String getFullResourceName() {
      return this.requestMetadata.plural != null && this.requestMetadata.group != null ? HasMetadata.getFullResourceName(this.requestMetadata.plural, this.requestMetadata.group) : null;
   }

   public static RuntimeException launderThrowable(Throwable cause) {
      return launderThrowable("An error has occurred.", cause);
   }

   public static RuntimeException launderThrowable(String message, Throwable cause) {
      RuntimeException processed = processCause(cause);
      if (processed != null) {
         return processed;
      } else {
         throw new KubernetesClientException(message, cause);
      }
   }

   private static RuntimeException processCause(Throwable cause) {
      if (cause instanceof RuntimeException) {
         return (RuntimeException)cause;
      } else if (cause instanceof Error) {
         throw (Error)cause;
      } else {
         if (cause instanceof InterruptedException) {
            Thread.currentThread().interrupt();
         }

         return null;
      }
   }

   public static RuntimeException launderThrowable(OperationInfo spec, Throwable cause) {
      RuntimeException processed = processCause(cause);
      if (processed != null) {
         return processed;
      } else {
         StringBuilder sb = new StringBuilder();
         sb.append(describeOperation(spec)).append(" failed.");
         if (cause instanceof KubernetesClientException) {
            Status status = ((KubernetesClientException)cause).getStatus();
            if (status != null && Utils.isNotNullOrEmpty(status.getMessage())) {
               sb.append("Reason: ").append(status.getMessage());
            }
         }

         throw new KubernetesClientException(sb.toString(), cause, -1, (Status)null, KubernetesClientException.RequestMetadata.builder().group(spec.getGroup()).version(spec.getVersion()).plural(spec.getPlural()).namespace(spec.getNamespace()).name(spec.getName()).build());
      }
   }

   private static String describeOperation(OperationInfo operation) {
      StringBuilder sb = new StringBuilder();
      sb.append("Operation");
      if (Utils.isNotNullOrEmpty(operation.getOperationType())) {
         sb.append(": [").append(operation.getOperationType()).append("]");
      }

      sb.append(" ");
      sb.append(" for kind: [").append(operation.getKind()).append("] ");
      sb.append(" with name: [").append(operation.getName()).append("] ");
      sb.append(" in namespace: [").append(operation.getNamespace()).append("] ");
      return sb.toString();
   }

   public KubernetesClientException copyAsCause() {
      return new KubernetesClientException(this.getMessage(), this, this.getCode(), this.getStatus(), this.requestMetadata);
   }

   private static class RequestMetadata implements Serializable {
      private static final RequestMetadata EMPTY = new RequestMetadata((String)null, (String)null, (String)null, (String)null, (String)null);
      final String group;
      final String version;
      final String plural;
      final String namespace;
      final String name;

      static RequestMetadata from(HttpRequest request) {
         return from(request.uri());
      }

      static RequestMetadata from(URI request) {
         List<String> segments = (List)Arrays.stream(request.getRawPath().split("/")).filter((s) -> !s.isEmpty()).collect(Collectors.toList());
         switch (segments.size()) {
            case 3:
               return builder().group("").version((String)segments.get(1)).plural((String)segments.get(2)).build();
            case 4:
               return builder().group((String)segments.get(1)).version((String)segments.get(2)).plural((String)segments.get(3)).build();
            case 5:
            default:
               return EMPTY;
            case 6:
               String root = (String)segments.get(0);
               if ("api".equals(root)) {
                  return builder().group("").version((String)segments.get(1)).plural((String)segments.get(4)).namespace((String)segments.get(3)).name((String)segments.get(5)).build();
               }

               return builder().group((String)segments.get(1)).version((String)segments.get(2)).plural((String)segments.get(5)).namespace((String)segments.get(4)).build();
            case 7:
               return builder().group((String)segments.get(1)).version((String)segments.get(2)).plural((String)segments.get(5)).namespace((String)segments.get(4)).name((String)segments.get(6)).build();
         }
      }

      @Generated
      public static RequestMetadataBuilder builder() {
         return new RequestMetadataBuilder();
      }

      @Generated
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof RequestMetadata)) {
            return false;
         } else {
            RequestMetadata other = (RequestMetadata)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$group = this.group;
               Object other$group = other.group;
               if (this$group == null) {
                  if (other$group != null) {
                     return false;
                  }
               } else if (!this$group.equals(other$group)) {
                  return false;
               }

               Object this$version = this.version;
               Object other$version = other.version;
               if (this$version == null) {
                  if (other$version != null) {
                     return false;
                  }
               } else if (!this$version.equals(other$version)) {
                  return false;
               }

               Object this$plural = this.plural;
               Object other$plural = other.plural;
               if (this$plural == null) {
                  if (other$plural != null) {
                     return false;
                  }
               } else if (!this$plural.equals(other$plural)) {
                  return false;
               }

               Object this$namespace = this.namespace;
               Object other$namespace = other.namespace;
               if (this$namespace == null) {
                  if (other$namespace != null) {
                     return false;
                  }
               } else if (!this$namespace.equals(other$namespace)) {
                  return false;
               }

               Object this$name = this.name;
               Object other$name = other.name;
               if (this$name == null) {
                  if (other$name != null) {
                     return false;
                  }
               } else if (!this$name.equals(other$name)) {
                  return false;
               }

               return true;
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof RequestMetadata;
      }

      @Generated
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $group = this.group;
         result = result * 59 + ($group == null ? 43 : $group.hashCode());
         Object $version = this.version;
         result = result * 59 + ($version == null ? 43 : $version.hashCode());
         Object $plural = this.plural;
         result = result * 59 + ($plural == null ? 43 : $plural.hashCode());
         Object $namespace = this.namespace;
         result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
         Object $name = this.name;
         result = result * 59 + ($name == null ? 43 : $name.hashCode());
         return result;
      }

      @Generated
      private RequestMetadata(String group, String version, String plural, String namespace, String name) {
         this.group = group;
         this.version = version;
         this.plural = plural;
         this.namespace = namespace;
         this.name = name;
      }

      @Generated
      public static class RequestMetadataBuilder {
         @Generated
         private String group;
         @Generated
         private String version;
         @Generated
         private String plural;
         @Generated
         private String namespace;
         @Generated
         private String name;

         @Generated
         RequestMetadataBuilder() {
         }

         @Generated
         public RequestMetadataBuilder group(String group) {
            this.group = group;
            return this;
         }

         @Generated
         public RequestMetadataBuilder version(String version) {
            this.version = version;
            return this;
         }

         @Generated
         public RequestMetadataBuilder plural(String plural) {
            this.plural = plural;
            return this;
         }

         @Generated
         public RequestMetadataBuilder namespace(String namespace) {
            this.namespace = namespace;
            return this;
         }

         @Generated
         public RequestMetadataBuilder name(String name) {
            this.name = name;
            return this;
         }

         @Generated
         public RequestMetadata build() {
            return new RequestMetadata(this.group, this.version, this.plural, this.namespace, this.name);
         }

         @Generated
         public String toString() {
            return "KubernetesClientException.RequestMetadata.RequestMetadataBuilder(group=" + this.group + ", version=" + this.version + ", plural=" + this.plural + ", namespace=" + this.namespace + ", name=" + this.name + ")";
         }
      }
   }
}
