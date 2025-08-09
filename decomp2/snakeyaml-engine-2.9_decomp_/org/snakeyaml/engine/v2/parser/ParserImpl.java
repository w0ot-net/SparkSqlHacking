package org.snakeyaml.engine.v2.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.comments.CommentType;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.common.ArrayStack;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.common.SpecVersion;
import org.snakeyaml.engine.v2.events.AliasEvent;
import org.snakeyaml.engine.v2.events.CommentEvent;
import org.snakeyaml.engine.v2.events.DocumentEndEvent;
import org.snakeyaml.engine.v2.events.DocumentStartEvent;
import org.snakeyaml.engine.v2.events.Event;
import org.snakeyaml.engine.v2.events.ImplicitTuple;
import org.snakeyaml.engine.v2.events.MappingEndEvent;
import org.snakeyaml.engine.v2.events.MappingStartEvent;
import org.snakeyaml.engine.v2.events.ScalarEvent;
import org.snakeyaml.engine.v2.events.SequenceEndEvent;
import org.snakeyaml.engine.v2.events.SequenceStartEvent;
import org.snakeyaml.engine.v2.events.StreamEndEvent;
import org.snakeyaml.engine.v2.events.StreamStartEvent;
import org.snakeyaml.engine.v2.exceptions.Mark;
import org.snakeyaml.engine.v2.exceptions.ParserException;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.scanner.Scanner;
import org.snakeyaml.engine.v2.scanner.ScannerImpl;
import org.snakeyaml.engine.v2.scanner.StreamReader;
import org.snakeyaml.engine.v2.tokens.AliasToken;
import org.snakeyaml.engine.v2.tokens.AnchorToken;
import org.snakeyaml.engine.v2.tokens.BlockEntryToken;
import org.snakeyaml.engine.v2.tokens.CommentToken;
import org.snakeyaml.engine.v2.tokens.DirectiveToken;
import org.snakeyaml.engine.v2.tokens.ScalarToken;
import org.snakeyaml.engine.v2.tokens.StreamEndToken;
import org.snakeyaml.engine.v2.tokens.StreamStartToken;
import org.snakeyaml.engine.v2.tokens.TagToken;
import org.snakeyaml.engine.v2.tokens.TagTuple;
import org.snakeyaml.engine.v2.tokens.Token;

public class ParserImpl implements Parser {
   private static final Map DEFAULT_TAGS = new HashMap();
   protected final Scanner scanner;
   private final LoadSettings settings;
   private final ArrayStack states;
   private final ArrayStack marksStack;
   private Optional currentEvent;
   private Optional state;
   private Map directiveTags;

   /** @deprecated */
   @Deprecated
   public ParserImpl(StreamReader reader, LoadSettings settings) {
      this(settings, reader);
   }

   public ParserImpl(LoadSettings settings, StreamReader reader) {
      this((LoadSettings)settings, (Scanner)(new ScannerImpl(settings, reader)));
   }

   /** @deprecated */
   @Deprecated
   public ParserImpl(Scanner scanner, LoadSettings settings) {
      this(settings, scanner);
   }

   public ParserImpl(LoadSettings settings, Scanner scanner) {
      this.scanner = scanner;
      this.settings = settings;
      this.currentEvent = Optional.empty();
      this.directiveTags = new HashMap(DEFAULT_TAGS);
      this.states = new ArrayStack(100);
      this.marksStack = new ArrayStack(10);
      this.state = Optional.of(new ParseStreamStart());
   }

   public boolean checkEvent(Event.ID id) {
      this.peekEvent();
      return this.currentEvent.isPresent() && ((Event)this.currentEvent.get()).getEventId() == id;
   }

   public Event peekEvent() {
      this.produce();
      return (Event)this.currentEvent.orElseThrow(() -> new NoSuchElementException("No more Events found."));
   }

   public Event next() {
      Event value = this.peekEvent();
      this.currentEvent = Optional.empty();
      return value;
   }

   public boolean hasNext() {
      this.produce();
      return this.currentEvent.isPresent();
   }

   private void produce() {
      if (!this.currentEvent.isPresent()) {
         this.state.ifPresent((production) -> this.currentEvent = Optional.of(production.produce()));
      }

   }

   private CommentEvent produceCommentEvent(CommentToken token) {
      String value = token.getValue();
      CommentType type = token.getCommentType();
      return new CommentEvent(type, value, token.getStartMark(), token.getEndMark());
   }

   private VersionTagsTuple processDirectives() {
      Optional<SpecVersion> yamlSpecVersion = Optional.empty();
      HashMap<String, String> tagHandles = new HashMap();

      while(this.scanner.checkToken(Token.ID.Directive)) {
         DirectiveToken token = (DirectiveToken)this.scanner.next();
         Optional<List<?>> dirOption = token.getValue();
         if (dirOption.isPresent()) {
            List<?> directiveValue = (List)dirOption.get();
            if (token.getName().equals("YAML")) {
               if (yamlSpecVersion.isPresent()) {
                  throw new ParserException("found duplicate YAML directive", token.getStartMark());
               }

               Integer major = (Integer)directiveValue.get(0);
               Integer minor = (Integer)directiveValue.get(1);
               yamlSpecVersion = Optional.of((SpecVersion)this.settings.getVersionFunction().apply(new SpecVersion(major, minor)));
            } else if (token.getName().equals("TAG")) {
               String handle = (String)directiveValue.get(0);
               String prefix = (String)directiveValue.get(1);
               if (tagHandles.containsKey(handle)) {
                  throw new ParserException("duplicate tag handle " + handle, token.getStartMark());
               }

               tagHandles.put(handle, prefix);
            }
         }
      }

      HashMap<String, String> detectedTagHandles = new HashMap();
      if (!tagHandles.isEmpty()) {
         detectedTagHandles.putAll(tagHandles);
      }

      for(Map.Entry entry : DEFAULT_TAGS.entrySet()) {
         if (!tagHandles.containsKey(entry.getKey())) {
            tagHandles.put((String)entry.getKey(), (String)entry.getValue());
         }
      }

      this.directiveTags = tagHandles;
      return new VersionTagsTuple(yamlSpecVersion, detectedTagHandles);
   }

   private Event parseFlowNode() {
      return this.parseNode(false, false);
   }

   private Event parseBlockNodeOrIndentlessSequence() {
      return this.parseNode(true, true);
   }

   private Event parseNode(boolean block, boolean indentlessSequence) {
      Optional<Mark> startMark = Optional.empty();
      Optional<Mark> endMark = Optional.empty();
      Optional<Mark> tagMark = Optional.empty();
      Event event;
      if (this.scanner.checkToken(Token.ID.Alias)) {
         AliasToken token = (AliasToken)this.scanner.next();
         event = new AliasEvent(Optional.of(token.getValue()), token.getStartMark(), token.getEndMark());
         this.state = Optional.of((Production)this.states.pop());
      } else {
         Optional<Anchor> anchor = Optional.empty();
         TagTuple tagTupleValue = null;
         if (this.scanner.checkToken(Token.ID.Anchor)) {
            AnchorToken token = (AnchorToken)this.scanner.next();
            startMark = token.getStartMark();
            endMark = token.getEndMark();
            anchor = Optional.of(token.getValue());
            if (this.scanner.checkToken(Token.ID.Tag)) {
               TagToken tagToken = (TagToken)this.scanner.next();
               tagMark = tagToken.getStartMark();
               endMark = tagToken.getEndMark();
               tagTupleValue = tagToken.getValue();
            }
         } else if (this.scanner.checkToken(Token.ID.Tag)) {
            TagToken tagToken = (TagToken)this.scanner.next();
            startMark = tagToken.getStartMark();
            tagMark = startMark;
            endMark = tagToken.getEndMark();
            tagTupleValue = tagToken.getValue();
            if (this.scanner.checkToken(Token.ID.Anchor)) {
               AnchorToken token = (AnchorToken)this.scanner.next();
               endMark = token.getEndMark();
               anchor = Optional.of(token.getValue());
            }
         }

         Optional<String> tag = Optional.empty();
         if (tagTupleValue != null) {
            Optional<String> handleOpt = tagTupleValue.getHandle();
            String suffix = tagTupleValue.getSuffix();
            if (handleOpt.isPresent()) {
               String handle = (String)handleOpt.get();
               if (!this.directiveTags.containsKey(handle)) {
                  throw new ParserException("while parsing a node", startMark, "found undefined tag handle " + handle, tagMark);
               }

               tag = Optional.of((String)this.directiveTags.get(handle) + suffix);
            } else {
               tag = Optional.of(suffix);
            }
         }

         if (!startMark.isPresent()) {
            startMark = this.scanner.peekToken().getStartMark();
            endMark = startMark;
         }

         boolean implicit = !tag.isPresent();
         if (indentlessSequence && this.scanner.checkToken(Token.ID.BlockEntry)) {
            endMark = this.scanner.peekToken().getEndMark();
            event = new SequenceStartEvent(anchor, tag, implicit, FlowStyle.BLOCK, startMark, endMark);
            this.state = Optional.of(new ParseIndentlessSequenceEntryKey());
         } else if (this.scanner.checkToken(Token.ID.Scalar)) {
            ScalarToken token = (ScalarToken)this.scanner.next();
            endMark = token.getEndMark();
            ImplicitTuple implicitValues;
            if (token.isPlain() && !tag.isPresent()) {
               implicitValues = new ImplicitTuple(true, false);
            } else if (!tag.isPresent()) {
               implicitValues = new ImplicitTuple(false, true);
            } else {
               implicitValues = new ImplicitTuple(false, false);
            }

            event = new ScalarEvent(anchor, tag, implicitValues, token.getValue(), token.getStyle(), startMark, endMark);
            this.state = Optional.of((Production)this.states.pop());
         } else if (this.scanner.checkToken(Token.ID.FlowSequenceStart)) {
            endMark = this.scanner.peekToken().getEndMark();
            event = new SequenceStartEvent(anchor, tag, implicit, FlowStyle.FLOW, startMark, endMark);
            this.state = Optional.of(new ParseFlowSequenceFirstEntry());
         } else if (this.scanner.checkToken(Token.ID.FlowMappingStart)) {
            endMark = this.scanner.peekToken().getEndMark();
            event = new MappingStartEvent(anchor, tag, implicit, FlowStyle.FLOW, startMark, endMark);
            this.state = Optional.of(new ParseFlowMappingFirstKey());
         } else if (block && this.scanner.checkToken(Token.ID.BlockSequenceStart)) {
            endMark = this.scanner.peekToken().getStartMark();
            event = new SequenceStartEvent(anchor, tag, implicit, FlowStyle.BLOCK, startMark, endMark);
            this.state = Optional.of(new ParseBlockSequenceFirstEntry());
         } else if (block && this.scanner.checkToken(Token.ID.BlockMappingStart)) {
            endMark = this.scanner.peekToken().getStartMark();
            event = new MappingStartEvent(anchor, tag, implicit, FlowStyle.BLOCK, startMark, endMark);
            this.state = Optional.of(new ParseBlockMappingFirstKey());
         } else {
            if (!anchor.isPresent() && !tag.isPresent()) {
               Token token = this.scanner.peekToken();
               throw new ParserException("while parsing a " + (block ? "block" : "flow") + " node", startMark, "expected the node content, but found '" + token.getTokenId() + "'", token.getStartMark());
            }

            event = new ScalarEvent(anchor, tag, new ImplicitTuple(implicit, false), "", ScalarStyle.PLAIN, startMark, endMark);
            this.state = Optional.of((Production)this.states.pop());
         }
      }

      return event;
   }

   private Event processEmptyScalar(Optional mark) {
      return new ScalarEvent(Optional.empty(), Optional.empty(), new ImplicitTuple(true, false), "", ScalarStyle.PLAIN, mark, mark);
   }

   private Optional markPop() {
      return (Optional)this.marksStack.pop();
   }

   private void markPush(Optional mark) {
      this.marksStack.push(mark);
   }

   static {
      DEFAULT_TAGS.put("!", "!");
      DEFAULT_TAGS.put("!!", "tag:yaml.org,2002:");
   }

   private class ParseStreamStart implements Production {
      private ParseStreamStart() {
      }

      public Event produce() {
         StreamStartToken token = (StreamStartToken)ParserImpl.this.scanner.next();
         Event event = new StreamStartEvent(token.getStartMark(), token.getEndMark());
         ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseImplicitDocumentStart());
         return event;
      }
   }

   private class ParseImplicitDocumentStart implements Production {
      private ParseImplicitDocumentStart() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseImplicitDocumentStart());
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (!ParserImpl.this.scanner.checkToken(Token.ID.Directive, Token.ID.DocumentStart, Token.ID.StreamEnd)) {
            Token token = ParserImpl.this.scanner.peekToken();
            Optional<Mark> startMark = token.getStartMark();
            Event event = new DocumentStartEvent(false, Optional.empty(), Collections.emptyMap(), startMark, startMark);
            ParserImpl.this.states.push(ParserImpl.this.new ParseDocumentEnd());
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockNode());
            return event;
         } else {
            return (ParserImpl.this.new ParseDocumentStart()).produce();
         }
      }
   }

   private class ParseDocumentStart implements Production {
      private ParseDocumentStart() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseDocumentStart());
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else {
            while(ParserImpl.this.scanner.checkToken(Token.ID.DocumentEnd)) {
               ParserImpl.this.scanner.next();
            }

            if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseDocumentStart());
               return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
            } else if (ParserImpl.this.scanner.checkToken(Token.ID.StreamEnd)) {
               StreamEndToken token = (StreamEndToken)ParserImpl.this.scanner.next();
               Event event = new StreamEndEvent(token.getStartMark(), token.getEndMark());
               if (!ParserImpl.this.states.isEmpty()) {
                  throw new YamlEngineException("Unexpected end of stream. States left: " + ParserImpl.this.states);
               } else if (!this.markEmpty()) {
                  throw new YamlEngineException("Unexpected end of stream. Marks left: " + ParserImpl.this.marksStack);
               } else {
                  ParserImpl.this.state = Optional.empty();
                  return event;
               }
            } else {
               ParserImpl.this.scanner.resetDocumentIndex();
               Token token = ParserImpl.this.scanner.peekToken();
               Optional<Mark> startMark = token.getStartMark();
               VersionTagsTuple tuple = ParserImpl.this.processDirectives();

               while(ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
                  ParserImpl.this.scanner.next();
               }

               if (!ParserImpl.this.scanner.checkToken(Token.ID.StreamEnd)) {
                  if (!ParserImpl.this.scanner.checkToken(Token.ID.DocumentStart)) {
                     throw new ParserException("expected '<document start>', but found '" + ParserImpl.this.scanner.peekToken().getTokenId() + "'", ParserImpl.this.scanner.peekToken().getStartMark());
                  } else {
                     token = ParserImpl.this.scanner.next();
                     Optional<Mark> endMark = token.getEndMark();
                     Event event = new DocumentStartEvent(true, tuple.getSpecVersion(), tuple.getTags(), startMark, endMark);
                     ParserImpl.this.states.push(ParserImpl.this.new ParseDocumentEnd());
                     ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseDocumentContent());
                     return event;
                  }
               } else {
                  throw new ParserException("expected '<document start>', but found '" + ParserImpl.this.scanner.peekToken().getTokenId() + "'", ParserImpl.this.scanner.peekToken().getStartMark());
               }
            }
         }
      }

      private boolean markEmpty() {
         return ParserImpl.this.marksStack.isEmpty();
      }
   }

   private class ParseDocumentEnd implements Production {
      private ParseDocumentEnd() {
      }

      public Event produce() {
         Token token = ParserImpl.this.scanner.peekToken();
         Optional<Mark> startMark = token.getStartMark();
         Optional<Mark> endMark = startMark;
         boolean explicit = false;
         if (ParserImpl.this.scanner.checkToken(Token.ID.DocumentEnd)) {
            token = ParserImpl.this.scanner.next();
            endMark = token.getEndMark();
            explicit = true;
         } else if (ParserImpl.this.scanner.checkToken(Token.ID.Directive)) {
            throw new ParserException("expected '<document end>' before directives, but found '" + ParserImpl.this.scanner.peekToken().getTokenId() + "'", ParserImpl.this.scanner.peekToken().getStartMark());
         }

         ParserImpl.this.directiveTags.clear();
         Event event = new DocumentEndEvent(explicit, startMark, endMark);
         ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseDocumentStart());
         return event;
      }
   }

   private class ParseDocumentContent implements Production {
      private ParseDocumentContent() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseDocumentContent());
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (ParserImpl.this.scanner.checkToken(Token.ID.Directive, Token.ID.DocumentStart, Token.ID.DocumentEnd, Token.ID.StreamEnd)) {
            Event event = ParserImpl.this.processEmptyScalar(ParserImpl.this.scanner.peekToken().getStartMark());
            ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
            return event;
         } else {
            return (ParserImpl.this.new ParseBlockNode()).produce();
         }
      }
   }

   private class ParseBlockNode implements Production {
      private ParseBlockNode() {
      }

      public Event produce() {
         return ParserImpl.this.parseNode(true, false);
      }
   }

   private class ParseBlockSequenceFirstEntry implements Production {
      private ParseBlockSequenceFirstEntry() {
      }

      public Event produce() {
         Token token = ParserImpl.this.scanner.next();
         ParserImpl.this.markPush(token.getStartMark());
         return (ParserImpl.this.new ParseBlockSequenceEntryKey()).produce();
      }
   }

   private class ParseBlockSequenceEntryKey implements Production {
      private ParseBlockSequenceEntryKey() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockSequenceEntryKey());
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (ParserImpl.this.scanner.checkToken(Token.ID.BlockEntry)) {
            BlockEntryToken token = (BlockEntryToken)ParserImpl.this.scanner.next();
            return (ParserImpl.this.new ParseBlockSequenceEntryValue(token)).produce();
         } else if (!ParserImpl.this.scanner.checkToken(Token.ID.BlockEnd)) {
            Token token = ParserImpl.this.scanner.peekToken();
            throw new ParserException("while parsing a block collection", ParserImpl.this.markPop(), "expected <block end>, but found '" + token.getTokenId() + "'", token.getStartMark());
         } else {
            Token token = ParserImpl.this.scanner.next();
            Event event = new SequenceEndEvent(token.getStartMark(), token.getEndMark());
            ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
            ParserImpl.this.markPop();
            return event;
         }
      }
   }

   private class ParseBlockSequenceEntryValue implements Production {
      BlockEntryToken token;

      public ParseBlockSequenceEntryValue(BlockEntryToken token) {
         this.token = token;
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockSequenceEntryValue(this.token));
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (!ParserImpl.this.scanner.checkToken(Token.ID.BlockEntry, Token.ID.BlockEnd)) {
            ParserImpl.this.states.push(ParserImpl.this.new ParseBlockSequenceEntryKey());
            return (ParserImpl.this.new ParseBlockNode()).produce();
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockSequenceEntryKey());
            return ParserImpl.this.processEmptyScalar(this.token.getEndMark());
         }
      }
   }

   private class ParseIndentlessSequenceEntryKey implements Production {
      private ParseIndentlessSequenceEntryKey() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseIndentlessSequenceEntryKey());
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (ParserImpl.this.scanner.checkToken(Token.ID.BlockEntry)) {
            BlockEntryToken token = (BlockEntryToken)ParserImpl.this.scanner.next();
            return (ParserImpl.this.new ParseIndentlessSequenceEntryValue(token)).produce();
         } else {
            Token token = ParserImpl.this.scanner.peekToken();
            Event event = new SequenceEndEvent(token.getStartMark(), token.getEndMark());
            ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
            return event;
         }
      }
   }

   private class ParseIndentlessSequenceEntryValue implements Production {
      BlockEntryToken token;

      public ParseIndentlessSequenceEntryValue(BlockEntryToken token) {
         this.token = token;
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseIndentlessSequenceEntryValue(this.token));
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (!ParserImpl.this.scanner.checkToken(Token.ID.BlockEntry, Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
            ParserImpl.this.states.push(ParserImpl.this.new ParseIndentlessSequenceEntryKey());
            return (ParserImpl.this.new ParseBlockNode()).produce();
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseIndentlessSequenceEntryKey());
            return ParserImpl.this.processEmptyScalar(this.token.getEndMark());
         }
      }
   }

   private class ParseBlockMappingFirstKey implements Production {
      private ParseBlockMappingFirstKey() {
      }

      public Event produce() {
         Token token = ParserImpl.this.scanner.next();
         ParserImpl.this.markPush(token.getStartMark());
         return (ParserImpl.this.new ParseBlockMappingKey()).produce();
      }
   }

   private class ParseBlockMappingKey implements Production {
      private ParseBlockMappingKey() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockMappingKey());
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else if (ParserImpl.this.scanner.checkToken(Token.ID.Key)) {
            Token token = ParserImpl.this.scanner.next();
            if (!ParserImpl.this.scanner.checkToken(Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
               ParserImpl.this.states.push(ParserImpl.this.new ParseBlockMappingValue());
               return ParserImpl.this.parseBlockNodeOrIndentlessSequence();
            } else {
               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockMappingValue());
               return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
         } else if (!ParserImpl.this.scanner.checkToken(Token.ID.BlockEnd)) {
            Token token = ParserImpl.this.scanner.peekToken();
            throw new ParserException("while parsing a block mapping", ParserImpl.this.markPop(), "expected <block end>, but found '" + token.getTokenId() + "'", token.getStartMark());
         } else {
            Token token = ParserImpl.this.scanner.next();
            Event event = new MappingEndEvent(token.getStartMark(), token.getEndMark());
            ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
            ParserImpl.this.markPop();
            return event;
         }
      }
   }

   private class ParseBlockMappingValue implements Production {
      private ParseBlockMappingValue() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Value)) {
            Token token = ParserImpl.this.scanner.next();
            if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
               Production p = ParserImpl.this.new ParseBlockMappingValueComment();
               ParserImpl.this.state = Optional.of(p);
               return p.produce();
            } else if (!ParserImpl.this.scanner.checkToken(Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
               ParserImpl.this.states.push(ParserImpl.this.new ParseBlockMappingKey());
               return ParserImpl.this.parseBlockNodeOrIndentlessSequence();
            } else {
               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockMappingKey());
               return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
         } else if (ParserImpl.this.scanner.checkToken(Token.ID.Scalar)) {
            ParserImpl.this.states.push(ParserImpl.this.new ParseBlockMappingKey());
            return ParserImpl.this.parseBlockNodeOrIndentlessSequence();
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockMappingKey());
            Token token = ParserImpl.this.scanner.peekToken();
            return ParserImpl.this.processEmptyScalar(token.getStartMark());
         }
      }
   }

   private class ParseBlockMappingValueComment implements Production {
      List tokens;

      private ParseBlockMappingValueComment() {
         this.tokens = new LinkedList();
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            this.tokens.add((CommentToken)ParserImpl.this.scanner.next());
            return this.produce();
         } else if (!ParserImpl.this.scanner.checkToken(Token.ID.Key, Token.ID.Value, Token.ID.BlockEnd)) {
            if (!this.tokens.isEmpty()) {
               return ParserImpl.this.produceCommentEvent((CommentToken)this.tokens.remove(0));
            } else {
               ParserImpl.this.states.push(ParserImpl.this.new ParseBlockMappingKey());
               return ParserImpl.this.parseBlockNodeOrIndentlessSequence();
            }
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseBlockMappingValueCommentList(this.tokens));
            return ParserImpl.this.processEmptyScalar(ParserImpl.this.scanner.peekToken().getStartMark());
         }
      }
   }

   private class ParseBlockMappingValueCommentList implements Production {
      List tokens;

      public ParseBlockMappingValueCommentList(List tokens) {
         this.tokens = tokens;
      }

      public Event produce() {
         return (Event)(!this.tokens.isEmpty() ? ParserImpl.this.produceCommentEvent((CommentToken)this.tokens.remove(0)) : (ParserImpl.this.new ParseBlockMappingKey()).produce());
      }
   }

   private class ParseFlowSequenceFirstEntry implements Production {
      private ParseFlowSequenceFirstEntry() {
      }

      public Event produce() {
         Token token = ParserImpl.this.scanner.next();
         ParserImpl.this.markPush(token.getStartMark());
         return (ParserImpl.this.new ParseFlowSequenceEntry(true)).produce();
      }
   }

   private class ParseFlowSequenceEntry implements Production {
      private final boolean first;

      public ParseFlowSequenceEntry(boolean first) {
         this.first = first;
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntry(this.first));
            return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         } else {
            if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowSequenceEnd)) {
               if (!this.first) {
                  if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowEntry)) {
                     Token token = ParserImpl.this.scanner.peekToken();
                     throw new ParserException("while parsing a flow sequence", ParserImpl.this.markPop(), "expected ',' or ']', but got " + token.getTokenId(), token.getStartMark());
                  }

                  ParserImpl.this.scanner.next();
                  if (ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
                     ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntry(true));
                     return ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
                  }
               }

               if (ParserImpl.this.scanner.checkToken(Token.ID.Key)) {
                  Token token = ParserImpl.this.scanner.peekToken();
                  Event event = new MappingStartEvent(Optional.empty(), Optional.empty(), true, FlowStyle.FLOW, token.getStartMark(), token.getEndMark());
                  ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntryMappingKey());
                  return event;
               }

               if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowSequenceEnd)) {
                  ParserImpl.this.states.push(ParserImpl.this.new ParseFlowSequenceEntry(false));
                  return ParserImpl.this.parseFlowNode();
               }
            }

            Token token = ParserImpl.this.scanner.next();
            Event event = new SequenceEndEvent(token.getStartMark(), token.getEndMark());
            if (!ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
               ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
            } else {
               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowEndComment());
            }

            ParserImpl.this.markPop();
            return event;
         }
      }
   }

   private class ParseFlowEndComment implements Production {
      private ParseFlowEndComment() {
      }

      public Event produce() {
         Event event = ParserImpl.this.produceCommentEvent((CommentToken)ParserImpl.this.scanner.next());
         if (!ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
         }

         return event;
      }
   }

   private class ParseFlowSequenceEntryMappingKey implements Production {
      private ParseFlowSequenceEntryMappingKey() {
      }

      public Event produce() {
         Token token = ParserImpl.this.scanner.next();
         if (!ParserImpl.this.scanner.checkToken(Token.ID.Value, Token.ID.FlowEntry, Token.ID.FlowSequenceEnd)) {
            ParserImpl.this.states.push(ParserImpl.this.new ParseFlowSequenceEntryMappingValue());
            return ParserImpl.this.parseFlowNode();
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntryMappingValue());
            return ParserImpl.this.processEmptyScalar(token.getEndMark());
         }
      }
   }

   private class ParseFlowSequenceEntryMappingValue implements Production {
      private ParseFlowSequenceEntryMappingValue() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Value)) {
            Token token = ParserImpl.this.scanner.next();
            if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowEntry, Token.ID.FlowSequenceEnd)) {
               ParserImpl.this.states.push(ParserImpl.this.new ParseFlowSequenceEntryMappingEnd());
               return ParserImpl.this.parseFlowNode();
            } else {
               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntryMappingEnd());
               return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntryMappingEnd());
            Token token = ParserImpl.this.scanner.peekToken();
            return ParserImpl.this.processEmptyScalar(token.getStartMark());
         }
      }
   }

   private class ParseFlowSequenceEntryMappingEnd implements Production {
      private ParseFlowSequenceEntryMappingEnd() {
      }

      public Event produce() {
         ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowSequenceEntry(false));
         Token token = ParserImpl.this.scanner.peekToken();
         return new MappingEndEvent(token.getStartMark(), token.getEndMark());
      }
   }

   private class ParseFlowMappingFirstKey implements Production {
      private ParseFlowMappingFirstKey() {
      }

      public Event produce() {
         Token token = ParserImpl.this.scanner.next();
         ParserImpl.this.markPush(token.getStartMark());
         return (ParserImpl.this.new ParseFlowMappingKey(true)).produce();
      }
   }

   private class ParseFlowMappingKey implements Production {
      private final boolean first;

      public ParseFlowMappingKey(boolean first) {
         this.first = first;
      }

      public Event produce() {
         if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowMappingEnd)) {
            if (!this.first) {
               if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowEntry)) {
                  Token token = ParserImpl.this.scanner.peekToken();
                  throw new ParserException("while parsing a flow mapping", ParserImpl.this.markPop(), "expected ',' or '}', but got " + token.getTokenId(), token.getStartMark());
               }

               ParserImpl.this.scanner.next();
            }

            if (ParserImpl.this.scanner.checkToken(Token.ID.Key)) {
               Token token = ParserImpl.this.scanner.next();
               if (!ParserImpl.this.scanner.checkToken(Token.ID.Value, Token.ID.FlowEntry, Token.ID.FlowMappingEnd)) {
                  ParserImpl.this.states.push(ParserImpl.this.new ParseFlowMappingValue());
                  return ParserImpl.this.parseFlowNode();
               }

               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowMappingValue());
               return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }

            if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowMappingEnd)) {
               ParserImpl.this.states.push(ParserImpl.this.new ParseFlowMappingEmptyValue());
               return ParserImpl.this.parseFlowNode();
            }
         }

         Token token = ParserImpl.this.scanner.next();
         Event event = new MappingEndEvent(token.getStartMark(), token.getEndMark());
         ParserImpl.this.markPop();
         if (!ParserImpl.this.scanner.checkToken(Token.ID.Comment)) {
            ParserImpl.this.state = Optional.of((Production)ParserImpl.this.states.pop());
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowEndComment());
         }

         return event;
      }
   }

   private class ParseFlowMappingValue implements Production {
      private ParseFlowMappingValue() {
      }

      public Event produce() {
         if (ParserImpl.this.scanner.checkToken(Token.ID.Value)) {
            Token token = ParserImpl.this.scanner.next();
            if (!ParserImpl.this.scanner.checkToken(Token.ID.FlowEntry, Token.ID.FlowMappingEnd)) {
               ParserImpl.this.states.push(ParserImpl.this.new ParseFlowMappingKey(false));
               return ParserImpl.this.parseFlowNode();
            } else {
               ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowMappingKey(false));
               return ParserImpl.this.processEmptyScalar(token.getEndMark());
            }
         } else {
            ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowMappingKey(false));
            Token token = ParserImpl.this.scanner.peekToken();
            return ParserImpl.this.processEmptyScalar(token.getStartMark());
         }
      }
   }

   private class ParseFlowMappingEmptyValue implements Production {
      private ParseFlowMappingEmptyValue() {
      }

      public Event produce() {
         ParserImpl.this.state = Optional.of(ParserImpl.this.new ParseFlowMappingKey(false));
         return ParserImpl.this.processEmptyScalar(ParserImpl.this.scanner.peekToken().getStartMark());
      }
   }
}
