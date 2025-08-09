package org.apache.spark.streaming;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005Q4QAG\u000e\u00017\rBQ\u0001\u000f\u0001\u0005\u0002eBqa\u000f\u0001A\u0002\u0013%A\bC\u0004>\u0001\u0001\u0007I\u0011\u0002 \t\r\u0011\u0003\u0001\u0015)\u0003*\u0011\u001d)\u0005\u00011A\u0005\n\u0019CqA\u0013\u0001A\u0002\u0013%1\n\u0003\u0004N\u0001\u0001\u0006Ka\u0012\u0005\b\u001d\u0002\u0001\r\u0011\"\u0003G\u0011\u001dy\u0005\u00011A\u0005\nACaA\u0015\u0001!B\u00139\u0005bB*\u0001\u0001\u0004%IA\u0012\u0005\b)\u0002\u0001\r\u0011\"\u0003V\u0011\u00199\u0006\u0001)Q\u0005\u000f\"9\u0001\f\u0001a\u0001\n\u00131\u0005bB-\u0001\u0001\u0004%IA\u0017\u0005\u00079\u0002\u0001\u000b\u0015B$\t\u000bu\u0003A\u0011\t0\t\u000b}\u0003A\u0011\t1\t\u000b\u0005\u0004A\u0011\t2\t\u000b\u0015\u0004A\u0011\t0\t\u000b\u0019\u0004A\u0011I4\t\u000b!\u0004A\u0011\u00010\t\u000b%\u0004A\u0011\u00010\t\u000b)\u0004A\u0011A6\t\u000bE\u0004A\u0011\u0001:\u0003\u0013M#\u0018\r^3J[Bd'B\u0001\u000f\u001e\u0003%\u0019HO]3b[&twM\u0003\u0002\u001f?\u0005)1\u000f]1sW*\u0011\u0001%I\u0001\u0007CB\f7\r[3\u000b\u0003\t\n1a\u001c:h+\t!3f\u0005\u0002\u0001KA\u0019aeJ\u0015\u000e\u0003mI!\u0001K\u000e\u0003\u000bM#\u0018\r^3\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\u0001\u0011\rA\f\u0002\u0002'\u000e\u0001\u0011CA\u00186!\t\u00014'D\u00012\u0015\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b2\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\r\u001c\n\u0005]\n$aA!os\u00061A(\u001b8jiz\"\u0012A\u000f\t\u0004M\u0001I\u0013!B:uCR,W#A\u0015\u0002\u0013M$\u0018\r^3`I\u0015\fHCA C!\t\u0001\u0004)\u0003\u0002Bc\t!QK\\5u\u0011\u001d\u00195!!AA\u0002%\n1\u0001\u001f\u00132\u0003\u0019\u0019H/\u0019;fA\u00059A-\u001a4j]\u0016$W#A$\u0011\u0005AB\u0015BA%2\u0005\u001d\u0011un\u001c7fC:\f1\u0002Z3gS:,Gm\u0018\u0013fcR\u0011q\b\u0014\u0005\b\u0007\u001a\t\t\u00111\u0001H\u0003!!WMZ5oK\u0012\u0004\u0013!\u0003;j[&twmT;u\u00035!\u0018.\\5oO>+Ho\u0018\u0013fcR\u0011q(\u0015\u0005\b\u0007&\t\t\u00111\u0001H\u0003)!\u0018.\\5oO>+H\u000fI\u0001\bkB$\u0017\r^3e\u0003-)\b\u000fZ1uK\u0012|F%Z9\u0015\u0005}2\u0006bB\"\r\u0003\u0003\u0005\raR\u0001\tkB$\u0017\r^3eA\u00059!/Z7pm\u0016$\u0017a\u0003:f[>4X\rZ0%KF$\"aP.\t\u000f\r{\u0011\u0011!a\u0001\u000f\u0006A!/Z7pm\u0016$\u0007%\u0001\u0004fq&\u001cHo\u001d\u000b\u0002\u000f\u0006\u0019q-\u001a;\u0015\u0003%\na!\u001e9eCR,GCA d\u0011\u0015!7\u00031\u0001*\u0003!qWm^*uCR,\u0017aC5t)&l\u0017N\\4PkR\faA]3n_Z,G#A \u0002\u0013%\u001c(+Z7pm\u0016$\u0017!C5t+B$\u0017\r^3e\u0003\u00119(/\u00199\u0015\u0005}b\u0007\"B7\u0019\u0001\u0004q\u0017!D8qi&|g.\u00197Ti\u0006$X\rE\u00021_&J!\u0001]\u0019\u0003\r=\u0003H/[8o\u0003I9(/\u00199US6LgnZ(viN#\u0018\r^3\u0015\u0005}\u001a\b\"\u00023\u001a\u0001\u0004I\u0003"
)
public class StateImpl extends State {
   private Object state = null;
   private boolean defined = false;
   private boolean timingOut = false;
   private boolean updated = false;
   private boolean removed = false;

   private Object state() {
      return this.state;
   }

   private void state_$eq(final Object x$1) {
      this.state = x$1;
   }

   private boolean defined() {
      return this.defined;
   }

   private void defined_$eq(final boolean x$1) {
      this.defined = x$1;
   }

   private boolean timingOut() {
      return this.timingOut;
   }

   private void timingOut_$eq(final boolean x$1) {
      this.timingOut = x$1;
   }

   private boolean updated() {
      return this.updated;
   }

   private void updated_$eq(final boolean x$1) {
      this.updated = x$1;
   }

   private boolean removed() {
      return this.removed;
   }

   private void removed_$eq(final boolean x$1) {
      this.removed = x$1;
   }

   public boolean exists() {
      return this.defined();
   }

   public Object get() {
      if (this.defined()) {
         return this.state();
      } else {
         throw new NoSuchElementException("State is not set");
      }
   }

   public void update(final Object newState) {
      .MODULE$.require(!this.removed(), () -> "Cannot update the state after it has been removed");
      .MODULE$.require(!this.timingOut(), () -> "Cannot update the state that is timing out");
      this.state_$eq(newState);
      this.defined_$eq(true);
      this.updated_$eq(true);
   }

   public boolean isTimingOut() {
      return this.timingOut();
   }

   public void remove() {
      .MODULE$.require(!this.timingOut(), () -> "Cannot remove the state that is timing out");
      .MODULE$.require(!this.removed(), () -> "Cannot remove the state that has already been removed");
      this.defined_$eq(false);
      this.updated_$eq(false);
      this.removed_$eq(true);
   }

   public boolean isRemoved() {
      return this.removed();
   }

   public boolean isUpdated() {
      return this.updated();
   }

   public void wrap(final Option optionalState) {
      if (optionalState instanceof Some var4) {
         Object newState = var4.value();
         this.state_$eq(newState);
         this.defined_$eq(true);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!scala.None..MODULE$.equals(optionalState)) {
            throw new MatchError(optionalState);
         }

         this.state_$eq((Object)null);
         this.defined_$eq(false);
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      this.timingOut_$eq(false);
      this.removed_$eq(false);
      this.updated_$eq(false);
   }

   public void wrapTimingOutState(final Object newState) {
      this.state_$eq(newState);
      this.defined_$eq(true);
      this.timingOut_$eq(true);
      this.removed_$eq(false);
      this.updated_$eq(false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
