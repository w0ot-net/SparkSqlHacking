package org.glassfish.jersey.process.internal;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.process.Inflector;

public final class Stages {
   private static final ChainableStage IDENTITY = new AbstractChainableStage() {
      public Stage.Continuation apply(Object o) {
         return Stage.Continuation.of(o, this.getDefaultNext());
      }
   };

   private Stages() {
   }

   public static ChainableStage identity() {
      return IDENTITY;
   }

   public static Stage asStage(Inflector inflector) {
      return new InflectingStage(inflector);
   }

   public static Inflector extractInflector(Object stage) {
      return stage instanceof Inflecting ? ((Inflecting)stage).inflector() : null;
   }

   public static Stage.Builder chain(Function transformation) {
      return new StageChainBuilder(transformation);
   }

   public static Stage.Builder chain(ChainableStage rootStage) {
      return new StageChainBuilder(rootStage);
   }

   public static Object process(Object data, Stage rootStage) {
      Stage.Continuation<DATA> continuation;
      Stage<DATA> currentStage;
      for(continuation = Stage.Continuation.of(data, rootStage); (currentStage = continuation.next()) != null; continuation = currentStage.apply(continuation.result())) {
      }

      return continuation.result();
   }

   public static Object process(Object data, Stage rootStage, Ref inflectorRef) {
      Stage<DATA> lastStage = rootStage;

      Stage.Continuation<DATA> continuation;
      for(continuation = Stage.Continuation.of(data, rootStage); continuation.next() != null; continuation = lastStage.apply(continuation.result())) {
         lastStage = continuation.next();
      }

      inflectorRef.set(extractInflector(lastStage));
      return continuation.result();
   }

   private static class InflectingStage implements Stage, Inflecting {
      private final Inflector inflector;

      public InflectingStage(Inflector inflector) {
         this.inflector = inflector;
      }

      public Inflector inflector() {
         return this.inflector;
      }

      public Stage.Continuation apply(Object request) {
         return Stage.Continuation.of(request);
      }
   }

   private static class StageChainBuilder implements Stage.Builder {
      private final Deque transformations;
      private Stage rootStage;
      private ChainableStage lastStage;

      private StageChainBuilder(Function transformation) {
         this.transformations = new LinkedList();
         this.transformations.push(transformation);
      }

      private StageChainBuilder(ChainableStage rootStage) {
         this.transformations = new LinkedList();
         this.rootStage = rootStage;
         this.lastStage = rootStage;
      }

      public Stage.Builder to(Function transformation) {
         this.transformations.push(transformation);
         return this;
      }

      public Stage.Builder to(ChainableStage stage) {
         this.addTailStage(stage);
         this.lastStage = stage;
         return this;
      }

      private void addTailStage(Stage lastStage) {
         Stage<DATA> tail = lastStage;
         if (!this.transformations.isEmpty()) {
            tail = this.convertTransformations(lastStage);
         }

         if (this.rootStage != null) {
            this.lastStage.setDefaultNext(tail);
         } else {
            this.rootStage = tail;
         }

      }

      public Stage build(Stage stage) {
         this.addTailStage(stage);
         return this.rootStage;
      }

      public Stage build() {
         return this.build((Stage)null);
      }

      private Stage convertTransformations(Stage successor) {
         Stage<DATA> stage;
         if (successor == null) {
            stage = new LinkedStage((Function)this.transformations.poll());
         } else {
            stage = new LinkedStage((Function)this.transformations.poll(), successor);
         }

         Function<DATA, DATA> t;
         while((t = (Function)this.transformations.poll()) != null) {
            stage = new LinkedStage(t, stage);
         }

         return stage;
      }
   }

   public static class LinkedStage implements Stage {
      private final Stage nextStage;
      private final Function transformation;

      public LinkedStage(Function transformation, Stage nextStage) {
         this.nextStage = nextStage;
         this.transformation = transformation;
      }

      public LinkedStage(Function transformation) {
         this(transformation, (Stage)null);
      }

      public Stage.Continuation apply(Object data) {
         return Stage.Continuation.of(this.transformation.apply(data), this.nextStage);
      }
   }
}
