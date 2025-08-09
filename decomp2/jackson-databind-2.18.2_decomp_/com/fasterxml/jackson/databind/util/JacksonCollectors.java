package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.util.stream.Collector;

public abstract class JacksonCollectors {
   public static Collector toArrayNode() {
      return toArrayNode(JsonNodeFactory.instance);
   }

   public static Collector toArrayNode(JsonNodeCreator nodeCreator) {
      return Collector.of(nodeCreator::arrayNode, ArrayNode::add, ArrayNode::addAll);
   }
}
