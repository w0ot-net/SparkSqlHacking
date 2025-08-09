package scala.xml;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.collection.BuildFrom;
import scala.collection.Seq;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.runtime.ModuleSerializationProxy;

public final class NodeSeq$ implements Serializable {
   public static final NodeSeq$ MODULE$ = new NodeSeq$();
   private static final NodeSeq Empty;

   static {
      Empty = MODULE$.fromSeq(.MODULE$);
   }

   public final NodeSeq Empty() {
      return Empty;
   }

   public NodeSeq fromSeq(final Seq s) {
      return new NodeSeq(s) {
         private final Seq s$1;

         public Seq theSeq() {
            return this.s$1;
         }

         public {
            this.s$1 = s$1;
         }
      };
   }

   public BuildFrom canBuildFrom() {
      return ScalaVersionSpecific.NodeSeqCBF$.MODULE$;
   }

   public Builder newBuilder() {
      return (new ListBuffer()).mapResult((s) -> MODULE$.fromSeq(s));
   }

   public NodeSeq seqToNodeSeq(final Seq s) {
      return this.fromSeq(s);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NodeSeq$.class);
   }

   private NodeSeq$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
