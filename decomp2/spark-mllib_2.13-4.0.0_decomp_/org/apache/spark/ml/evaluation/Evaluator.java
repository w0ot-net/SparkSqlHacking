package org.apache.spark.ml.evaluation;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.Identifiable;
import org.apache.spark.sql.Dataset;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014QAB\u0004\u0002\u0002IAQa\b\u0001\u0005\u0002\u0001BQa\t\u0001\u0005\u0002\u0011BQa\t\u0001\u0007\u0002)CQA\u0015\u0001\u0005\u0002MCQA\u0017\u0001\u0007Bm\u0013\u0011\"\u0012<bYV\fGo\u001c:\u000b\u0005!I\u0011AC3wC2,\u0018\r^5p]*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iiR\"A\u000e\u000b\u0005qI\u0011!\u00029be\u0006l\u0017B\u0001\u0010\u001c\u0005\u0019\u0001\u0016M]1ng\u00061A(\u001b8jiz\"\u0012!\t\t\u0003E\u0001i\u0011aB\u0001\tKZ\fG.^1uKR\u0019Q\u0005\u000b\u001f\u0011\u0005Q1\u0013BA\u0014\u0016\u0005\u0019!u.\u001e2mK\")\u0011F\u0001a\u0001U\u00059A-\u0019;bg\u0016$\bGA\u00164!\ras&M\u0007\u0002[)\u0011afC\u0001\u0004gFd\u0017B\u0001\u0019.\u0005\u001d!\u0015\r^1tKR\u0004\"AM\u001a\r\u0001\u0011IA\u0007KA\u0001\u0002\u0003\u0015\t!\u000e\u0002\u0004?\u0012\n\u0014C\u0001\u001c:!\t!r'\u0003\u00029+\t9aj\u001c;iS:<\u0007C\u0001\u000b;\u0013\tYTCA\u0002B]fDQ!\u0010\u0002A\u0002y\n\u0001\u0002]1sC6l\u0015\r\u001d\t\u00035}J!\u0001Q\u000e\u0003\u0011A\u000b'/Y7NCBD3A\u0001\"I!\t\u0019e)D\u0001E\u0015\t)5\"\u0001\u0006b]:|G/\u0019;j_:L!a\u0012#\u0003\u000bMKgnY3\"\u0003%\u000bQA\r\u00181]A\"\"!J&\t\u000b%\u001a\u0001\u0019\u0001'1\u00055{\u0005c\u0001\u00170\u001dB\u0011!g\u0014\u0003\n!.\u000b\t\u0011!A\u0003\u0002U\u00121a\u0018\u00133Q\r\u0019!\tS\u0001\u000fSNd\u0015M]4fe\n+G\u000f^3s+\u0005!\u0006C\u0001\u000bV\u0013\t1VCA\u0004C_>dW-\u00198)\u0007\u0011\u0011\u0005,I\u0001Z\u0003\u0015\td&\u000e\u00181\u0003\u0011\u0019w\u000e]=\u0015\u0005\u0005b\u0006\"B/\u0006\u0001\u0004q\u0014!B3yiJ\f\u0007fA\u0003C1\"\u001a\u0001A\u0011-"
)
public abstract class Evaluator implements Params {
   private Param[] params;
   private ParamMap paramMap;
   private ParamMap defaultParamMap;
   private volatile boolean bitmap$0;

   public String explainParam(final Param param) {
      return Params.explainParam$(this, param);
   }

   public String explainParams() {
      return Params.explainParams$(this);
   }

   public final boolean isSet(final Param param) {
      return Params.isSet$(this, param);
   }

   public final boolean isDefined(final Param param) {
      return Params.isDefined$(this, param);
   }

   public boolean hasParam(final String paramName) {
      return Params.hasParam$(this, paramName);
   }

   public Param getParam(final String paramName) {
      return Params.getParam$(this, paramName);
   }

   public final Params set(final Param param, final Object value) {
      return Params.set$(this, (Param)param, value);
   }

   public final Params set(final String param, final Object value) {
      return Params.set$(this, (String)param, value);
   }

   public final Params set(final ParamPair paramPair) {
      return Params.set$(this, paramPair);
   }

   public final Option get(final Param param) {
      return Params.get$(this, param);
   }

   public final Params clear(final Param param) {
      return Params.clear$(this, param);
   }

   public final Object getOrDefault(final Param param) {
      return Params.getOrDefault$(this, param);
   }

   public final Object $(final Param param) {
      return Params.$$(this, param);
   }

   public final Params setDefault(final Param param, final Object value) {
      return Params.setDefault$(this, param, value);
   }

   public final Params setDefault(final Seq paramPairs) {
      return Params.setDefault$(this, paramPairs);
   }

   public final Option getDefault(final Param param) {
      return Params.getDefault$(this, param);
   }

   public final boolean hasDefault(final Param param) {
      return Params.hasDefault$(this, param);
   }

   public final Params defaultCopy(final ParamMap extra) {
      return Params.defaultCopy$(this, extra);
   }

   public final ParamMap extractParamMap(final ParamMap extra) {
      return Params.extractParamMap$(this, extra);
   }

   public final ParamMap extractParamMap() {
      return Params.extractParamMap$(this);
   }

   public Params copyValues(final Params to, final ParamMap extra) {
      return Params.copyValues$(this, to, extra);
   }

   public ParamMap copyValues$default$2() {
      return Params.copyValues$default$2$(this);
   }

   public void onParamChange(final Param param) {
      Params.onParamChange$(this, param);
   }

   public String toString() {
      return Identifiable.toString$(this);
   }

   private Param[] params$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.params = Params.params$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.params;
   }

   public Param[] params() {
      return !this.bitmap$0 ? this.params$lzycompute() : this.params;
   }

   public ParamMap paramMap() {
      return this.paramMap;
   }

   public ParamMap defaultParamMap() {
      return this.defaultParamMap;
   }

   public void org$apache$spark$ml$param$Params$_setter_$paramMap_$eq(final ParamMap x$1) {
      this.paramMap = x$1;
   }

   public void org$apache$spark$ml$param$Params$_setter_$defaultParamMap_$eq(final ParamMap x$1) {
      this.defaultParamMap = x$1;
   }

   public double evaluate(final Dataset dataset, final ParamMap paramMap) {
      return this.copy(paramMap).evaluate(dataset);
   }

   public abstract double evaluate(final Dataset dataset);

   public boolean isLargerBetter() {
      return true;
   }

   public abstract Evaluator copy(final ParamMap extra);

   public Evaluator() {
      Identifiable.$init$(this);
      Params.$init$(this);
      Statics.releaseFence();
   }
}
