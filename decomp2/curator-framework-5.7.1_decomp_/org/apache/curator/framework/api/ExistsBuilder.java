package org.apache.curator.framework.api;

public interface ExistsBuilder extends ExistsBuilderMain {
   ACLableExistBuilderMain creatingParentsIfNeeded();

   ACLableExistBuilderMain creatingParentContainersIfNeeded();
}
