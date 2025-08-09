package org.snakeyaml.engine.v2.exceptions;

import java.util.Objects;
import java.util.Optional;

public class MarkedYamlEngineException extends YamlEngineException {
   private final String context;
   private final Optional contextMark;
   private final String problem;
   private final Optional problemMark;

   protected MarkedYamlEngineException(String context, Optional contextMark, String problem, Optional problemMark, Throwable cause) {
      super(context + "; " + problem + "; " + problemMark, cause);
      Objects.requireNonNull(contextMark, "contextMark must be provided");
      Objects.requireNonNull(problemMark, "problemMark must be provided");
      this.context = context;
      this.contextMark = contextMark;
      this.problem = problem;
      this.problemMark = problemMark;
   }

   protected MarkedYamlEngineException(String context, Optional contextMark, String problem, Optional problemMark) {
      this(context, contextMark, problem, problemMark, (Throwable)null);
   }

   public String getMessage() {
      return this.toString();
   }

   public String toString() {
      StringBuilder lines = new StringBuilder();
      if (this.context != null) {
         lines.append(this.context);
         lines.append("\n");
      }

      if (this.contextMark.isPresent() && (this.problem == null || !this.problemMark.isPresent() || ((Mark)this.contextMark.get()).getName().equals(((Mark)this.problemMark.get()).getName()) || ((Mark)this.contextMark.get()).getLine() != ((Mark)this.problemMark.get()).getLine() || ((Mark)this.contextMark.get()).getColumn() != ((Mark)this.problemMark.get()).getColumn())) {
         lines.append(this.contextMark.get());
         lines.append("\n");
      }

      if (this.problem != null) {
         lines.append(this.problem);
         lines.append("\n");
      }

      if (this.problemMark.isPresent()) {
         lines.append(this.problemMark.get());
         lines.append("\n");
      }

      return lines.toString();
   }

   public String getContext() {
      return this.context;
   }

   public Optional getContextMark() {
      return this.contextMark;
   }

   public String getProblem() {
      return this.problem;
   }

   public Optional getProblemMark() {
      return this.problemMark;
   }
}
