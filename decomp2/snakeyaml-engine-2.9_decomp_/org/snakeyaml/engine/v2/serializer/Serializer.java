package org.snakeyaml.engine.v2.serializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.comments.CommentLine;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.emitter.Emitable;
import org.snakeyaml.engine.v2.events.AliasEvent;
import org.snakeyaml.engine.v2.events.CommentEvent;
import org.snakeyaml.engine.v2.events.DocumentEndEvent;
import org.snakeyaml.engine.v2.events.DocumentStartEvent;
import org.snakeyaml.engine.v2.events.ImplicitTuple;
import org.snakeyaml.engine.v2.events.MappingEndEvent;
import org.snakeyaml.engine.v2.events.MappingStartEvent;
import org.snakeyaml.engine.v2.events.ScalarEvent;
import org.snakeyaml.engine.v2.events.SequenceEndEvent;
import org.snakeyaml.engine.v2.events.SequenceStartEvent;
import org.snakeyaml.engine.v2.events.StreamEndEvent;
import org.snakeyaml.engine.v2.events.StreamStartEvent;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.AnchorNode;
import org.snakeyaml.engine.v2.nodes.MappingNode;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.NodeTuple;
import org.snakeyaml.engine.v2.nodes.NodeType;
import org.snakeyaml.engine.v2.nodes.ScalarNode;
import org.snakeyaml.engine.v2.nodes.SequenceNode;
import org.snakeyaml.engine.v2.nodes.Tag;

public class Serializer {
   private final DumpSettings settings;
   private final Emitable emitable;
   private final Set serializedNodes;
   private final Map anchors;
   private final boolean dereferenceAliases;
   private final Set recursive;

   public Serializer(DumpSettings settings, Emitable emitable) {
      this.settings = settings;
      this.emitable = emitable;
      this.serializedNodes = new HashSet();
      this.anchors = new HashMap();
      this.dereferenceAliases = settings.isDereferenceAliases();
      this.recursive = Collections.newSetFromMap(new IdentityHashMap());
   }

   public void serializeDocument(Node node) {
      this.emitable.emit(new DocumentStartEvent(this.settings.isExplicitStart(), this.settings.getYamlDirective(), this.settings.getTagDirective()));
      this.anchorNode(node);
      Optional var10000 = this.settings.getExplicitRootTag();
      Objects.requireNonNull(node);
      var10000.ifPresent(node::setTag);
      this.serializeNode(node);
      this.emitable.emit(new DocumentEndEvent(this.settings.isExplicitEnd()));
      this.serializedNodes.clear();
      this.anchors.clear();
      this.recursive.clear();
   }

   public void emitStreamStart() {
      this.emitable.emit(new StreamStartEvent());
   }

   public void emitStreamEnd() {
      this.emitable.emit(new StreamEndEvent());
   }

   private void anchorNode(Node node) {
      Node realNode;
      if (node.getNodeType() == NodeType.ANCHOR) {
         realNode = ((AnchorNode)node).getRealNode();
      } else {
         realNode = node;
      }

      if (this.anchors.containsKey(realNode)) {
         this.anchors.computeIfAbsent(realNode, (a) -> this.settings.getAnchorGenerator().nextAnchor(realNode));
      } else {
         this.anchors.put(realNode, realNode.getAnchor().isPresent() ? this.settings.getAnchorGenerator().nextAnchor(realNode) : null);
         switch (realNode.getNodeType()) {
            case SEQUENCE:
               SequenceNode seqNode = (SequenceNode)realNode;

               for(Node item : seqNode.getValue()) {
                  this.anchorNode(item);
               }
               break;
            case MAPPING:
               MappingNode mappingNode = (MappingNode)realNode;

               for(NodeTuple object : mappingNode.getValue()) {
                  Node key = object.getKeyNode();
                  Node value = object.getValueNode();
                  this.anchorNode(key);
                  this.anchorNode(value);
               }
         }
      }

   }

   private void serializeNode(Node node) {
      if (node.getNodeType() == NodeType.ANCHOR) {
         node = ((AnchorNode)node).getRealNode();
      }

      if (this.dereferenceAliases && this.recursive.contains(node)) {
         throw new YamlEngineException("Cannot dereferenceAliases for recursive structures.");
      } else {
         this.recursive.add(node);
         Optional<Anchor> tAlias;
         if (!this.dereferenceAliases) {
            tAlias = Optional.ofNullable((Anchor)this.anchors.get(node));
         } else {
            tAlias = Optional.empty();
         }

         if (!this.dereferenceAliases && this.serializedNodes.contains(node)) {
            this.emitable.emit(new AliasEvent(tAlias));
         } else {
            this.serializedNodes.add(node);
            switch (node.getNodeType()) {
               case SEQUENCE:
                  SequenceNode seqNode = (SequenceNode)node;
                  this.serializeComments(node.getBlockComments());
                  boolean implicitS = node.getTag().equals(Tag.SEQ);
                  this.emitable.emit(new SequenceStartEvent(tAlias, Optional.of(node.getTag().getValue()), implicitS, seqNode.getFlowStyle()));

                  for(Node item : seqNode.getValue()) {
                     this.serializeNode(item);
                  }

                  this.emitable.emit(new SequenceEndEvent());
                  this.serializeComments(node.getInLineComments());
                  this.serializeComments(node.getEndComments());
                  break;
               case SCALAR:
                  ScalarNode scalarNode = (ScalarNode)node;
                  this.serializeComments(node.getBlockComments());
                  Tag detectedTag = this.settings.getSchema().getScalarResolver().resolve(scalarNode.getValue(), true);
                  Tag defaultTag = this.settings.getSchema().getScalarResolver().resolve(scalarNode.getValue(), false);
                  ImplicitTuple tuple = new ImplicitTuple(node.getTag().equals(detectedTag), node.getTag().equals(defaultTag));
                  ScalarEvent event = new ScalarEvent(tAlias, Optional.of(node.getTag().getValue()), tuple, scalarNode.getValue(), scalarNode.getScalarStyle());
                  this.emitable.emit(event);
                  this.serializeComments(node.getInLineComments());
                  this.serializeComments(node.getEndComments());
                  break;
               default:
                  this.serializeComments(node.getBlockComments());
                  boolean implicitM = node.getTag().equals(Tag.MAP);
                  MappingNode mappingNode = (MappingNode)node;
                  List<NodeTuple> map = mappingNode.getValue();
                  if (mappingNode.getTag() != Tag.COMMENT) {
                     this.emitable.emit(new MappingStartEvent(tAlias, Optional.of(mappingNode.getTag().getValue()), implicitM, mappingNode.getFlowStyle(), Optional.empty(), Optional.empty()));

                     for(NodeTuple entry : map) {
                        Node key = entry.getKeyNode();
                        Node value = entry.getValueNode();
                        this.serializeNode(key);
                        this.serializeNode(value);
                     }

                     this.emitable.emit(new MappingEndEvent());
                     this.serializeComments(node.getInLineComments());
                     this.serializeComments(node.getEndComments());
                  }
            }
         }

         this.recursive.remove(node);
      }
   }

   private void serializeComments(List comments) {
      if (this.settings.getDumpComments() && comments != null) {
         for(CommentLine line : comments) {
            CommentEvent commentEvent = new CommentEvent(line.getCommentType(), line.getValue(), line.getStartMark(), line.getEndMark());
            this.emitable.emit(commentEvent);
         }
      }

   }
}
