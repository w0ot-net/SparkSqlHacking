package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RequestConfigBuilder extends RequestConfigFluent implements VisitableBuilder {
   RequestConfigFluent fluent;

   public RequestConfigBuilder() {
      this(new RequestConfig());
   }

   public RequestConfigBuilder(RequestConfigFluent fluent) {
      this(fluent, new RequestConfig());
   }

   public RequestConfigBuilder(RequestConfigFluent fluent, RequestConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RequestConfigBuilder(RequestConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RequestConfig build() {
      RequestConfig buildable = new RequestConfig(this.fluent.getWatchReconnectLimit(), this.fluent.getWatchReconnectInterval(), this.fluent.getRequestTimeout(), this.fluent.getScaleTimeout(), this.fluent.getLoggingInterval(), this.fluent.getRequestRetryBackoffLimit(), this.fluent.getRequestRetryBackoffInterval(), this.fluent.getUploadRequestTimeout());
      buildable.setImpersonateUsername(this.fluent.getImpersonateUsername());
      buildable.setImpersonateGroups(this.fluent.getImpersonateGroups());
      buildable.setImpersonateExtras(this.fluent.getImpersonateExtras());
      return buildable;
   }
}
