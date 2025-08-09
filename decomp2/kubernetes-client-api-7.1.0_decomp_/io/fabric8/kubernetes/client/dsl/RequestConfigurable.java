package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.RequestConfig;

public interface RequestConfigurable {
   FunctionCallable withRequestConfig(RequestConfig var1);
}
