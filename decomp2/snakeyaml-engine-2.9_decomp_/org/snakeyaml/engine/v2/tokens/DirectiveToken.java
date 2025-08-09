package org.snakeyaml.engine.v2.tokens;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;

public final class DirectiveToken extends Token {
   public static final String YAML_DIRECTIVE = "YAML";
   public static final String TAG_DIRECTIVE = "TAG";
   private final String name;
   private final Optional value;

   public DirectiveToken(String name, Optional value, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      Objects.requireNonNull(name);
      this.name = name;
      Objects.requireNonNull(value);
      if (value.isPresent() && ((List)value.get()).size() != 2) {
         throw new YamlEngineException("Two strings/integers must be provided instead of " + ((List)value.get()).size());
      } else {
         this.value = value;
      }
   }

   public String getName() {
      return this.name;
   }

   public Optional getValue() {
      return this.value;
   }

   public Token.ID getTokenId() {
      return Token.ID.Directive;
   }
}
