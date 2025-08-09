package io.fabric8.kubernetes.client.dsl.internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.zjsonpatch.JsonDiff;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PatchUtils {
   private PatchUtils() {
   }

   public static String withoutRuntimeState(Object object, Format format, boolean omitStatus, KubernetesSerialization serialization) {
      Function var10000;
      if (format == PatchUtils.Format.JSON) {
         Objects.requireNonNull(serialization);
         var10000 = serialization::asJson;
      } else {
         Objects.requireNonNull(serialization);
         var10000 = serialization::asYaml;
      }

      Function<Object, String> mapper = var10000;
      return (String)mapper.apply(withoutRuntimeState(object, omitStatus, serialization));
   }

   static JsonNode withoutRuntimeState(Object object, boolean omitStatus, KubernetesSerialization serialization) {
      ObjectNode raw = (ObjectNode)serialization.convertValue(object, ObjectNode.class);
      removeEmptyArrays(raw);
      Optional var10000 = Optional.ofNullable(raw.get("metadata"));
      Objects.requireNonNull(ObjectNode.class);
      var10000 = var10000.filter(ObjectNode.class::isInstance);
      Objects.requireNonNull(ObjectNode.class);
      var10000.map(ObjectNode.class::cast).ifPresent((m) -> {
         m.remove("creationTimestamp");
         m.remove("deletionTimestamp");
         m.remove("generation");
         m.remove("selfLink");
         m.remove("uid");
      });
      if (omitStatus) {
         raw.remove("status");
      }

      return raw;
   }

   private static void removeEmptyArrays(ObjectNode raw) {
      List<String> toRemove = new ArrayList();
      Iterator<String> names = raw.fieldNames();

      while(names.hasNext()) {
         String name = (String)names.next();
         JsonNode node = raw.get(name);
         if (node.isArray() && node.size() == 0) {
            toRemove.add(name);
         }

         if (node.isObject()) {
            removeEmptyArrays((ObjectNode)node);
         }
      }

      raw.remove(toRemove);
   }

   public static String jsonDiff(Object current, Object updated, boolean omitStatus, KubernetesSerialization serialization) {
      return serialization.asJson(JsonDiff.asJson(withoutRuntimeState(current, omitStatus, serialization), withoutRuntimeState(updated, omitStatus, serialization)));
   }

   public static enum Format {
      YAML,
      JSON;
   }
}
