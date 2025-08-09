package org.apache.spark.ml.param;

import org.apache.spark.ml.util.Identifiable;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005q1QAA\u0002\u0002\u00029AQ!\u0007\u0001\u0005\u0002i\u0011!BS1wCB\u000b'/Y7t\u0015\t!Q!A\u0003qCJ\fWN\u0003\u0002\u0007\u000f\u0005\u0011Q\u000e\u001c\u0006\u0003\u0011%\tQa\u001d9be.T!AC\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0011aA8sO\u000e\u00011c\u0001\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"AF\f\u000e\u0003\rI!\u0001G\u0002\u0003\rA\u000b'/Y7t\u0003\u0019a\u0014N\\5u}Q\t1\u0004\u0005\u0002\u0017\u0001\u0001"
)
public abstract class JavaParams implements Params {
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

   public JavaParams() {
      Identifiable.$init$(this);
      Params.$init$(this);
      Statics.releaseFence();
   }
}
