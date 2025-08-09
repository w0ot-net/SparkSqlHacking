package org.snakeyaml.engine.v2.representer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.snakeyaml.engine.v2.api.RepresentToNode;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.AnchorNode;
import org.snakeyaml.engine.v2.nodes.MappingNode;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.NodeTuple;
import org.snakeyaml.engine.v2.nodes.ScalarNode;
import org.snakeyaml.engine.v2.nodes.SequenceNode;
import org.snakeyaml.engine.v2.nodes.Tag;

public abstract class BaseRepresenter {
   protected final Map representers = new HashMap();
   protected final Map parentClassRepresenters = new LinkedHashMap();
   protected final Map representedObjects = new IdentityHashMap() {
      public Node put(Object key, Node value) {
         return (Node)super.put(key, new AnchorNode(value));
      }
   };
   protected RepresentToNode nullRepresenter;
   protected ScalarStyle defaultScalarStyle;
   protected FlowStyle defaultFlowStyle;
   protected Object objectToRepresent;

   public BaseRepresenter() {
      this.defaultScalarStyle = ScalarStyle.PLAIN;
      this.defaultFlowStyle = FlowStyle.AUTO;
   }

   public Node represent(Object data) {
      Node node = this.representData(data);
      this.representedObjects.clear();
      this.objectToRepresent = null;
      return node;
   }

   protected Optional findRepresenterFor(Object data) {
      Class<?> clazz = data.getClass();
      if (this.representers.containsKey(clazz)) {
         return Optional.of((RepresentToNode)this.representers.get(clazz));
      } else {
         for(Map.Entry parentRepresenterEntry : this.parentClassRepresenters.entrySet()) {
            if (((Class)parentRepresenterEntry.getKey()).isInstance(data)) {
               return Optional.of((RepresentToNode)parentRepresenterEntry.getValue());
            }
         }

         return Optional.empty();
      }
   }

   protected final Node representData(Object data) {
      this.objectToRepresent = data;
      if (this.representedObjects.containsKey(this.objectToRepresent)) {
         return (Node)this.representedObjects.get(this.objectToRepresent);
      } else if (data == null) {
         return this.nullRepresenter.representData((Object)null);
      } else {
         RepresentToNode representer = (RepresentToNode)this.findRepresenterFor(data).orElseThrow(() -> new YamlEngineException("Representer is not defined for " + data.getClass()));
         return representer.representData(data);
      }
   }

   protected Node representScalar(Tag tag, String value, ScalarStyle style) {
      if (style == ScalarStyle.PLAIN) {
         style = this.defaultScalarStyle;
      }

      return new ScalarNode(tag, value, style);
   }

   protected Node representScalar(Tag tag, String value) {
      return this.representScalar(tag, value, ScalarStyle.PLAIN);
   }

   protected Node representSequence(Tag tag, Iterable sequence, FlowStyle flowStyle) {
      int size = 10;
      if (sequence instanceof List) {
         size = ((List)sequence).size();
      }

      List<Node> value = new ArrayList(size);
      SequenceNode node = new SequenceNode(tag, value, flowStyle);
      this.representedObjects.put(this.objectToRepresent, node);
      FlowStyle bestStyle = FlowStyle.FLOW;

      for(Object item : sequence) {
         Node nodeItem = this.representData(item);
         if (!(nodeItem instanceof ScalarNode) || !((ScalarNode)nodeItem).isPlain()) {
            bestStyle = FlowStyle.BLOCK;
         }

         value.add(nodeItem);
      }

      if (flowStyle == FlowStyle.AUTO) {
         if (this.defaultFlowStyle != FlowStyle.AUTO) {
            node.setFlowStyle(this.defaultFlowStyle);
         } else {
            node.setFlowStyle(bestStyle);
         }
      }

      return node;
   }

   protected NodeTuple representMappingEntry(Map.Entry entry) {
      return new NodeTuple(this.representData(entry.getKey()), this.representData(entry.getValue()));
   }

   protected Node representMapping(Tag tag, Map mapping, FlowStyle flowStyle) {
      List<NodeTuple> value = new ArrayList(mapping.size());
      MappingNode node = new MappingNode(tag, value, flowStyle);
      this.representedObjects.put(this.objectToRepresent, node);
      FlowStyle bestStyle = FlowStyle.FLOW;

      for(Map.Entry entry : mapping.entrySet()) {
         NodeTuple tuple = this.representMappingEntry(entry);
         if (!(tuple.getKeyNode() instanceof ScalarNode) || !((ScalarNode)tuple.getKeyNode()).isPlain()) {
            bestStyle = FlowStyle.BLOCK;
         }

         if (!(tuple.getValueNode() instanceof ScalarNode) || !((ScalarNode)tuple.getValueNode()).isPlain()) {
            bestStyle = FlowStyle.BLOCK;
         }

         value.add(tuple);
      }

      if (flowStyle == FlowStyle.AUTO) {
         if (this.defaultFlowStyle != FlowStyle.AUTO) {
            node.setFlowStyle(this.defaultFlowStyle);
         } else {
            node.setFlowStyle(bestStyle);
         }
      }

      return node;
   }
}
