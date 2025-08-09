package shaded.parquet.com.fasterxml.jackson.databind.util;

import java.util.stream.Collector;
import shaded.parquet.com.fasterxml.jackson.databind.node.ArrayNode;
import shaded.parquet.com.fasterxml.jackson.databind.node.JsonNodeCreator;
import shaded.parquet.com.fasterxml.jackson.databind.node.JsonNodeFactory;

public abstract class JacksonCollectors {
   public static Collector toArrayNode() {
      return toArrayNode(JsonNodeFactory.instance);
   }

   public static Collector toArrayNode(JsonNodeCreator nodeCreator) {
      return Collector.of(nodeCreator::arrayNode, ArrayNode::add, ArrayNode::addAll);
   }
}
