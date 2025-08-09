package io.fabric8.kubernetes.client.informers.cache;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface Cache extends Indexer {
   String NAMESPACE_INDEX = "namespace";

   static String metaNamespaceKeyFunc(HasMetadata obj) {
      if (obj == null) {
         return "";
      } else {
         ObjectMeta metadata = obj.getMetadata();
         if (metadata == null) {
            throw new RuntimeException("Object is bad :" + obj);
         } else {
            return namespaceKeyFunc(metadata.getNamespace(), metadata.getName());
         }
      }
   }

   static String metaUidKeyFunc(HasMetadata obj) {
      if (obj != null && obj.getMetadata() != null) {
         String result = obj.getMetadata().getUid();
         return (String)Utils.getNonNullOrElse(result, "");
      } else {
         return "";
      }
   }

   static String namespaceKeyFunc(String objectNamespace, String objectName) {
      return Utils.isNullOrEmpty(objectNamespace) ? objectName : objectNamespace + "/" + objectName;
   }

   static List metaNamespaceIndexFunc(HasMetadata obj) {
      return (List)Optional.ofNullable(obj).map(HasMetadata::getMetadata).map((metadata) -> Collections.singletonList(metadata.getNamespace())).orElse(Collections.emptyList());
   }
}
