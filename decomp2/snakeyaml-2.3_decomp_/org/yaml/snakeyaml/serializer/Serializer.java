package org.yaml.snakeyaml.serializer;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.comments.CommentLine;
import org.yaml.snakeyaml.emitter.Emitable;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.CommentEvent;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.yaml.snakeyaml.events.ImplicitTuple;
import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.events.StreamEndEvent;
import org.yaml.snakeyaml.events.StreamStartEvent;
import org.yaml.snakeyaml.nodes.AnchorNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.resolver.Resolver;
import org.yaml.snakeyaml.util.MergeUtils;

public final class Serializer {
   private final Emitable emitter;
   private final Resolver resolver;
   private final boolean explicitStart;
   private final boolean explicitEnd;
   private DumperOptions.Version useVersion;
   private final Map useTags;
   private final Set serializedNodes;
   private final Map anchors;
   private final AnchorGenerator anchorGenerator;
   private Boolean closed;
   private final Tag explicitRoot;
   private final boolean dereferenceAliases;
   private final Set recursive;
   private final MergeUtils mergeUtils;

   public Serializer(Emitable emitter, Resolver resolver, DumperOptions opts, Tag rootTag) {
      if (emitter == null) {
         throw new NullPointerException("Emitter must  be provided");
      } else if (resolver == null) {
         throw new NullPointerException("Resolver must  be provided");
      } else if (opts == null) {
         throw new NullPointerException("DumperOptions must  be provided");
      } else {
         this.emitter = emitter;
         this.resolver = resolver;
         this.explicitStart = opts.isExplicitStart();
         this.explicitEnd = opts.isExplicitEnd();
         if (opts.getVersion() != null) {
            this.useVersion = opts.getVersion();
         }

         this.useTags = opts.getTags();
         this.serializedNodes = new HashSet();
         this.anchors = new HashMap();
         this.anchorGenerator = opts.getAnchorGenerator();
         this.dereferenceAliases = opts.isDereferenceAliases();
         this.recursive = Collections.newSetFromMap(new IdentityHashMap());
         this.closed = null;
         this.explicitRoot = rootTag;
         this.mergeUtils = new MergeUtils() {
            public MappingNode asMappingNode(Node node) {
               if (node instanceof MappingNode) {
                  return (MappingNode)node;
               } else {
                  throw new SerializerException("expecting MappingNode while processing merge.");
               }
            }
         };
      }
   }

   public void open() throws IOException {
      if (this.closed == null) {
         this.emitter.emit(new StreamStartEvent((Mark)null, (Mark)null));
         this.closed = Boolean.FALSE;
      } else if (Boolean.TRUE.equals(this.closed)) {
         throw new SerializerException("serializer is closed");
      } else {
         throw new SerializerException("serializer is already opened");
      }
   }

   public void close() throws IOException {
      if (this.closed == null) {
         throw new SerializerException("serializer is not opened");
      } else {
         if (!Boolean.TRUE.equals(this.closed)) {
            this.emitter.emit(new StreamEndEvent((Mark)null, (Mark)null));
            this.closed = Boolean.TRUE;
            this.serializedNodes.clear();
            this.anchors.clear();
            this.recursive.clear();
         }

      }
   }

   public void serialize(Node node) throws IOException {
      if (this.closed == null) {
         throw new SerializerException("serializer is not opened");
      } else if (this.closed) {
         throw new SerializerException("serializer is closed");
      } else {
         this.emitter.emit(new DocumentStartEvent((Mark)null, (Mark)null, this.explicitStart, this.useVersion, this.useTags));
         this.anchorNode(node);
         if (this.explicitRoot != null) {
            node.setTag(this.explicitRoot);
         }

         this.serializeNode(node, (Node)null);
         this.emitter.emit(new DocumentEndEvent((Mark)null, (Mark)null, this.explicitEnd));
         this.serializedNodes.clear();
         this.anchors.clear();
         this.recursive.clear();
      }
   }

   private void anchorNode(Node node) {
      if (node.getNodeId() == NodeId.anchor) {
         node = ((AnchorNode)node).getRealNode();
      }

      if (this.anchors.containsKey(node)) {
         String anchor = (String)this.anchors.get(node);
         if (null == anchor) {
            anchor = this.anchorGenerator.nextAnchor(node);
            this.anchors.put(node, anchor);
         }
      } else {
         this.anchors.put(node, node.getAnchor() != null ? this.anchorGenerator.nextAnchor(node) : null);
         switch (node.getNodeId()) {
            case sequence:
               SequenceNode seqNode = (SequenceNode)node;

               for(Node item : seqNode.getValue()) {
                  this.anchorNode(item);
               }
               break;
            case mapping:
               MappingNode mnode = (MappingNode)node;

               for(NodeTuple object : mnode.getValue()) {
                  Node key = object.getKeyNode();
                  Node value = object.getValueNode();
                  this.anchorNode(key);
                  this.anchorNode(value);
               }
         }
      }

   }

   private void serializeNode(Node node, Node parent) throws IOException {
      if (node.getNodeId() == NodeId.anchor) {
         node = ((AnchorNode)node).getRealNode();
      }

      if (this.dereferenceAliases && this.recursive.contains(node)) {
         throw new SerializerException("Cannot dereferenceAliases for recursive structures.");
      } else {
         this.recursive.add(node);
         String tAlias = !this.dereferenceAliases ? (String)this.anchors.get(node) : null;
         if (!this.dereferenceAliases && this.serializedNodes.contains(node)) {
            this.emitter.emit(new AliasEvent(tAlias, (Mark)null, (Mark)null));
         } else {
            this.serializedNodes.add(node);
            switch (node.getNodeId()) {
               case sequence:
                  SequenceNode seqNode = (SequenceNode)node;
                  this.serializeComments(node.getBlockComments());
                  boolean implicitS = node.getTag().equals(this.resolver.resolve(NodeId.sequence, (String)null, true));
                  this.emitter.emit(new SequenceStartEvent(tAlias, node.getTag().getValue(), implicitS, (Mark)null, (Mark)null, seqNode.getFlowStyle()));

                  for(Node item : seqNode.getValue()) {
                     this.serializeNode(item, node);
                  }

                  this.emitter.emit(new SequenceEndEvent((Mark)null, (Mark)null));
                  this.serializeComments(node.getInLineComments());
                  this.serializeComments(node.getEndComments());
                  break;
               case scalar:
                  ScalarNode scalarNode = (ScalarNode)node;
                  this.serializeComments(node.getBlockComments());
                  Tag detectedTag = this.resolver.resolve(NodeId.scalar, scalarNode.getValue(), true);
                  Tag defaultTag = this.resolver.resolve(NodeId.scalar, scalarNode.getValue(), false);
                  ImplicitTuple tuple = new ImplicitTuple(node.getTag().equals(detectedTag), node.getTag().equals(defaultTag));
                  ScalarEvent event = new ScalarEvent(tAlias, node.getTag().getValue(), tuple, scalarNode.getValue(), (Mark)null, (Mark)null, scalarNode.getScalarStyle());
                  this.emitter.emit(event);
                  this.serializeComments(node.getInLineComments());
                  this.serializeComments(node.getEndComments());
                  break;
               default:
                  this.serializeComments(node.getBlockComments());
                  if (node.getTag() != Tag.COMMENT) {
                     Tag implicitTag = this.resolver.resolve(NodeId.mapping, (String)null, true);
                     boolean implicitM = node.getTag().equals(implicitTag);
                     MappingNode mnode = (MappingNode)node;
                     List<NodeTuple> map = mnode.getValue();
                     if (this.dereferenceAliases && mnode.isMerged()) {
                        map = this.mergeUtils.flatten(mnode);
                     }

                     this.emitter.emit(new MappingStartEvent(tAlias, mnode.getTag().getValue(), implicitM, (Mark)null, (Mark)null, mnode.getFlowStyle()));

                     for(NodeTuple row : map) {
                        Node key = row.getKeyNode();
                        Node value = row.getValueNode();
                        this.serializeNode(key, mnode);
                        this.serializeNode(value, mnode);
                     }

                     this.emitter.emit(new MappingEndEvent((Mark)null, (Mark)null));
                     this.serializeComments(node.getInLineComments());
                     this.serializeComments(node.getEndComments());
                  }
            }
         }

         this.recursive.remove(node);
      }
   }

   private void serializeComments(List comments) throws IOException {
      if (comments != null) {
         for(CommentLine line : comments) {
            CommentEvent commentEvent = new CommentEvent(line.getCommentType(), line.getValue(), line.getStartMark(), line.getEndMark());
            this.emitter.emit(commentEvent);
         }

      }
   }
}
