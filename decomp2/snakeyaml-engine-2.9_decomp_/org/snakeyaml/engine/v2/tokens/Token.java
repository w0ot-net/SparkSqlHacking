package org.snakeyaml.engine.v2.tokens;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class Token {
   private final Optional startMark;
   private final Optional endMark;

   public Token(Optional startMark, Optional endMark) {
      Objects.requireNonNull(startMark);
      Objects.requireNonNull(endMark);
      this.startMark = startMark;
      this.endMark = endMark;
   }

   public Optional getStartMark() {
      return this.startMark;
   }

   public Optional getEndMark() {
      return this.endMark;
   }

   public abstract ID getTokenId();

   public String toString() {
      return this.getTokenId().toString();
   }

   public static enum ID {
      Alias("<alias>"),
      Anchor("<anchor>"),
      BlockEnd("<block end>"),
      BlockEntry("-"),
      BlockMappingStart("<block mapping start>"),
      BlockSequenceStart("<block sequence start>"),
      Directive("<directive>"),
      DocumentEnd("<document end>"),
      DocumentStart("<document start>"),
      FlowEntry(","),
      FlowMappingEnd("}"),
      FlowMappingStart("{"),
      FlowSequenceEnd("]"),
      FlowSequenceStart("["),
      Key("?"),
      Scalar("<scalar>"),
      StreamEnd("<stream end>"),
      StreamStart("<stream start>"),
      Tag("<tag>"),
      Comment("#"),
      Value(":");

      private final String description;

      private ID(String s) {
         this.description = s;
      }

      public String toString() {
         return this.description;
      }
   }
}
