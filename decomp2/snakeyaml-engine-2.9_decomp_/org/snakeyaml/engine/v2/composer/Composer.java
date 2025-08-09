package org.snakeyaml.engine.v2.composer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.comments.CommentEventsCollector;
import org.snakeyaml.engine.v2.comments.CommentLine;
import org.snakeyaml.engine.v2.comments.CommentType;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.events.AliasEvent;
import org.snakeyaml.engine.v2.events.Event;
import org.snakeyaml.engine.v2.events.MappingStartEvent;
import org.snakeyaml.engine.v2.events.NodeEvent;
import org.snakeyaml.engine.v2.events.ScalarEvent;
import org.snakeyaml.engine.v2.events.SequenceStartEvent;
import org.snakeyaml.engine.v2.exceptions.ComposerException;
import org.snakeyaml.engine.v2.exceptions.Mark;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.MappingNode;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.NodeTuple;
import org.snakeyaml.engine.v2.nodes.NodeType;
import org.snakeyaml.engine.v2.nodes.ScalarNode;
import org.snakeyaml.engine.v2.nodes.SequenceNode;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.parser.Parser;
import org.snakeyaml.engine.v2.resolver.ScalarResolver;

public class Composer implements Iterator {
   protected final Parser parser;
   private final ScalarResolver scalarResolver;
   private final Map anchors;
   private final Set recursiveNodes;
   private final LoadSettings settings;
   private final CommentEventsCollector blockCommentsCollector;
   private final CommentEventsCollector inlineCommentsCollector;
   private int nonScalarAliasesCount;

   /** @deprecated */
   @Deprecated
   public Composer(Parser parser, LoadSettings settings) {
      this(settings, parser);
   }

   public Composer(LoadSettings settings, Parser parser) {
      this.nonScalarAliasesCount = 0;
      this.parser = parser;
      this.scalarResolver = settings.getSchema().getScalarResolver();
      this.settings = settings;
      this.anchors = new HashMap();
      this.recursiveNodes = new HashSet();
      this.blockCommentsCollector = new CommentEventsCollector(parser, new CommentType[]{CommentType.BLANK_LINE, CommentType.BLOCK});
      this.inlineCommentsCollector = new CommentEventsCollector(parser, new CommentType[]{CommentType.IN_LINE});
   }

   public boolean hasNext() {
      if (this.parser.checkEvent(Event.ID.StreamStart)) {
         this.parser.next();
      }

      return !this.parser.checkEvent(Event.ID.StreamEnd);
   }

   public Optional getSingleNode() {
      this.parser.next();
      Optional<Node> document = Optional.empty();
      if (!this.parser.checkEvent(Event.ID.StreamEnd)) {
         document = Optional.of(this.next());
      }

      if (!this.parser.checkEvent(Event.ID.StreamEnd)) {
         Event event = this.parser.next();
         Optional<Mark> previousDocMark = document.flatMap(Node::getStartMark);
         throw new ComposerException("expected a single document in the stream", previousDocMark, "but found another document", event.getStartMark());
      } else {
         this.parser.next();
         return document;
      }
   }

   public Node next() {
      this.blockCommentsCollector.collectEvents();
      if (this.parser.checkEvent(Event.ID.StreamEnd)) {
         List<CommentLine> commentLines = this.blockCommentsCollector.consume();
         Optional<Mark> startMark = ((CommentLine)commentLines.get(0)).getStartMark();
         List<NodeTuple> children = Collections.emptyList();
         Node node = new MappingNode(Tag.COMMENT, false, children, FlowStyle.BLOCK, startMark, Optional.empty());
         node.setBlockComments(commentLines);
         return node;
      } else {
         this.parser.next();
         Node node = this.composeNode(Optional.empty());
         this.blockCommentsCollector.collectEvents();
         if (!this.blockCommentsCollector.isEmpty()) {
            node.setEndComments(this.blockCommentsCollector.consume());
         }

         this.parser.next();
         this.anchors.clear();
         this.recursiveNodes.clear();
         this.nonScalarAliasesCount = 0;
         return node;
      }
   }

   private Node composeNode(Optional parent) {
      this.blockCommentsCollector.collectEvents();
      Set var10001 = this.recursiveNodes;
      Objects.requireNonNull(var10001);
      parent.ifPresent(var10001::add);
      Node node;
      if (this.parser.checkEvent(Event.ID.Alias)) {
         AliasEvent event = (AliasEvent)this.parser.next();
         Anchor anchor = event.getAlias();
         if (!this.anchors.containsKey(anchor)) {
            throw new ComposerException("found undefined alias " + anchor, event.getStartMark());
         }

         node = (Node)this.anchors.get(anchor);
         if (node.getNodeType() != NodeType.SCALAR) {
            ++this.nonScalarAliasesCount;
            if (this.nonScalarAliasesCount > this.settings.getMaxAliasesForCollections()) {
               throw new YamlEngineException("Number of aliases for non-scalar nodes exceeds the specified max=" + this.settings.getMaxAliasesForCollections());
            }
         }

         if (this.recursiveNodes.remove(node)) {
            node.setRecursive(true);
         }

         this.blockCommentsCollector.consume();
         this.inlineCommentsCollector.collectEvents().consume();
      } else {
         NodeEvent event = (NodeEvent)this.parser.peekEvent();
         Optional<Anchor> anchor = event.getAnchor();
         if (this.parser.checkEvent(Event.ID.Scalar)) {
            node = this.composeScalarNode(anchor, this.blockCommentsCollector.consume());
         } else if (this.parser.checkEvent(Event.ID.SequenceStart)) {
            node = this.composeSequenceNode(anchor);
         } else {
            node = this.composeMappingNode(anchor);
         }
      }

      var10001 = this.recursiveNodes;
      Objects.requireNonNull(var10001);
      parent.ifPresent(var10001::remove);
      return node;
   }

   private void registerAnchor(Anchor anchor, Node node) {
      this.anchors.put(anchor, node);
      node.setAnchor(Optional.of(anchor));
   }

   protected Node composeScalarNode(Optional anchor, List blockComments) {
      ScalarEvent ev = (ScalarEvent)this.parser.next();
      Optional<String> tag = ev.getTag();
      boolean resolved = false;
      Tag nodeTag;
      if (tag.isPresent() && !((String)tag.get()).equals("!")) {
         nodeTag = new Tag((String)tag.get());
      } else {
         nodeTag = this.scalarResolver.resolve(ev.getValue(), ev.getImplicit().canOmitTagInPlainScalar());
         resolved = true;
      }

      Node node = new ScalarNode(nodeTag, resolved, ev.getValue(), ev.getScalarStyle(), ev.getStartMark(), ev.getEndMark());
      anchor.ifPresent((a) -> this.registerAnchor(a, node));
      node.setBlockComments(blockComments);
      node.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
      return node;
   }

   protected SequenceNode composeSequenceNode(Optional anchor) {
      SequenceStartEvent startEvent = (SequenceStartEvent)this.parser.next();
      Optional<String> tag = startEvent.getTag();
      boolean resolved = false;
      Tag nodeTag;
      if (tag.isPresent() && !((String)tag.get()).equals("!")) {
         nodeTag = new Tag((String)tag.get());
      } else {
         nodeTag = Tag.SEQ;
         resolved = true;
      }

      ArrayList<Node> children = new ArrayList();
      SequenceNode node = new SequenceNode(nodeTag, resolved, children, startEvent.getFlowStyle(), startEvent.getStartMark(), Optional.empty());
      if (startEvent.isFlow()) {
         node.setBlockComments(this.blockCommentsCollector.consume());
      }

      anchor.ifPresent((a) -> this.registerAnchor(a, node));

      while(!this.parser.checkEvent(Event.ID.SequenceEnd)) {
         this.blockCommentsCollector.collectEvents();
         if (this.parser.checkEvent(Event.ID.SequenceEnd)) {
            break;
         }

         children.add(this.composeNode(Optional.of(node)));
      }

      if (startEvent.isFlow()) {
         node.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
      }

      Event endEvent = this.parser.next();
      node.setEndMark(endEvent.getEndMark());
      this.inlineCommentsCollector.collectEvents();
      if (!this.inlineCommentsCollector.isEmpty()) {
         node.setInLineComments(this.inlineCommentsCollector.consume());
      }

      return node;
   }

   protected Node composeMappingNode(Optional anchor) {
      MappingStartEvent startEvent = (MappingStartEvent)this.parser.next();
      Optional<String> tag = startEvent.getTag();
      boolean resolved = false;
      Tag nodeTag;
      if (tag.isPresent() && !((String)tag.get()).equals("!")) {
         nodeTag = new Tag((String)tag.get());
      } else {
         nodeTag = Tag.MAP;
         resolved = true;
      }

      List<NodeTuple> children = new ArrayList();
      MappingNode node = new MappingNode(nodeTag, resolved, children, startEvent.getFlowStyle(), startEvent.getStartMark(), Optional.empty());
      if (startEvent.isFlow()) {
         node.setBlockComments(this.blockCommentsCollector.consume());
      }

      anchor.ifPresent((a) -> this.registerAnchor(a, node));

      while(!this.parser.checkEvent(Event.ID.MappingEnd)) {
         this.blockCommentsCollector.collectEvents();
         if (this.parser.checkEvent(Event.ID.MappingEnd)) {
            break;
         }

         this.composeMappingChildren(children, node);
      }

      if (startEvent.isFlow()) {
         node.setInLineComments(this.inlineCommentsCollector.collectEvents().consume());
      }

      Event endEvent = this.parser.next();
      node.setEndMark(endEvent.getEndMark());
      this.inlineCommentsCollector.collectEvents();
      if (!this.inlineCommentsCollector.isEmpty()) {
         node.setInLineComments(this.inlineCommentsCollector.consume());
      }

      return node;
   }

   protected void composeMappingChildren(List children, MappingNode node) {
      Node itemKey = this.composeKeyNode(node);
      Node itemValue = this.composeValueNode(node);
      children.add(new NodeTuple(itemKey, itemValue));
   }

   protected Node composeKeyNode(MappingNode node) {
      return this.composeNode(Optional.of(node));
   }

   protected Node composeValueNode(MappingNode node) {
      return this.composeNode(Optional.of(node));
   }
}
