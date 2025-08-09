package org.apache.spark.api.resource;

import java.util.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.resource.ResourceRequest;

@DeveloperApi
public interface ResourceDiscoveryPlugin {
   Optional discoverResource(ResourceRequest var1, SparkConf var2);
}
