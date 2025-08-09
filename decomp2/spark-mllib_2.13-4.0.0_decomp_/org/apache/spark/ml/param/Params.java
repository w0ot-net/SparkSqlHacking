package org.apache.spark.ml.param;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.NoSuchElementException;
import org.apache.spark.ml.util.Identifiable;
import scala.Option;
import scala.Predef;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005ca\u0002\u0010 !\u0003\r\tA\u000b\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\t\u0011\u0002A)\u0019!C\u0001\u0013\")Q\f\u0001C\u0001=\")Q\u000e\u0001C\u0001]\")q\u000e\u0001C\u0003a\")!\u0010\u0001C\u0003w\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003+\u0001AQAA\f\u0011\u001d\t)\u0002\u0001C\u000b\u0003WAq!!\u0006\u0001\t+\t\t\u0004C\u0004\u0002F\u0001!)!a\u0012\t\u000f\u0005e\u0003\u0001\"\u0002\u0002\\!9\u0011\u0011\u000e\u0001\u0005\u0006\u0005-\u0004bBA<\u0001\u0011U\u0011\u0011\u0010\u0005\t\u0003\u000b\u0003AQC\u0011\u0002\b\"9\u0011Q\u0011\u0001\u0005\u0016\u0005U\u0005bBAV\u0001\u0011\u0015\u0011Q\u0016\u0005\b\u0003w\u0003AQAA_\u0011\u001d\tI\r\u0001D\u0001\u0003\u0017Dq!!7\u0001\t+\tY\u000eC\u0004\u0002h\u0002!)!!;\t\u000f\u0005\u001d\b\u0001\"\u0002\u0002n\"Q\u0011q\u001e\u0001C\u0002\u0013\u0005\u0011%!=\t\u0015\u0005M\bA1A\u0005\u0002\u0005\n\t\u0010C\u0004\u0002v\u0002!I!a>\t\u000f\t\u0015\u0001\u0001\"\u0005\u0003\b!I!Q\u0003\u0001\u0012\u0002\u0013E!q\u0003\u0005\t\u0005c\u0001A\u0011A\u0011\u00034\t1\u0001+\u0019:b[NT!\u0001I\u0011\u0002\u000bA\f'/Y7\u000b\u0005\t\u001a\u0013AA7m\u0015\t!S%A\u0003ta\u0006\u00148N\u0003\u0002'O\u00051\u0011\r]1dQ\u0016T\u0011\u0001K\u0001\u0004_J<7\u0001A\n\u0005\u0001-\nt\u0007\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VM\u001a\t\u0003eUj\u0011a\r\u0006\u0003i\u0005\nA!\u001e;jY&\u0011ag\r\u0002\r\u0013\u0012,g\u000e^5gS\u0006\u0014G.\u001a\t\u0003q\u0001s!!\u000f \u000f\u0005ijT\"A\u001e\u000b\u0005qJ\u0013A\u0002\u001fs_>$h(C\u0001/\u0013\tyT&A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0005\u0013%\u0001D*fe&\fG.\u001b>bE2,'BA .\u0003\u0019!\u0013N\\5uIQ\tQ\t\u0005\u0002-\r&\u0011q)\f\u0002\u0005+:LG/\u0001\u0004qCJ\fWn]\u000b\u0002\u0015B\u0019AfS'\n\u00051k#!B!se\u0006L\bG\u0001(U!\ry\u0005KU\u0007\u0002?%\u0011\u0011k\b\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003'Rc\u0001\u0001B\u0005V\u0005\u0005\u0005\t\u0011!B\u0001-\n\u0019q\fJ\u001b\u0012\u0005]S\u0006C\u0001\u0017Y\u0013\tIVFA\u0004O_RD\u0017N\\4\u0011\u00051Z\u0016B\u0001/.\u0005\r\te._\u0001\rKb\u0004H.Y5o!\u0006\u0014\u0018-\u001c\u000b\u0003?\u001e\u0004\"\u0001\u00193\u000f\u0005\u0005\u0014\u0007C\u0001\u001e.\u0013\t\u0019W&\u0001\u0004Qe\u0016$WMZ\u0005\u0003K\u001a\u0014aa\u0015;sS:<'BA2.\u0011\u0015\u00013\u00011\u0001ia\tI7\u000eE\u0002P!*\u0004\"aU6\u0005\u00131<\u0017\u0011!A\u0001\u0006\u00031&aA0%q\u0005iQ\r\u001f9mC&t\u0007+\u0019:b[N$\u0012aX\u0001\u0006SN\u001cV\r\u001e\u000b\u0003cR\u0004\"\u0001\f:\n\u0005Ml#a\u0002\"p_2,\u0017M\u001c\u0005\u0006A\u0015\u0001\r!\u001e\u0019\u0003mb\u00042a\u0014)x!\t\u0019\u0006\u0010B\u0005zi\u0006\u0005\t\u0011!B\u0001-\n\u0019q\fJ\u001d\u0002\u0013%\u001cH)\u001a4j]\u0016$GCA9}\u0011\u0015\u0001c\u00011\u0001~a\rq\u0018\u0011\u0001\t\u0004\u001fB{\bcA*\u0002\u0002\u0011Q\u00111\u0001?\u0002\u0002\u0003\u0005)\u0011\u0001,\u0003\t}#\u0013\u0007M\u0001\tQ\u0006\u001c\b+\u0019:b[R\u0019\u0011/!\u0003\t\r\u0005-q\u00011\u0001`\u0003%\u0001\u0018M]1n\u001d\u0006lW-\u0001\u0005hKR\u0004\u0016M]1n)\u0011\t\t\"a\u0005\u0011\u0007=\u0003&\f\u0003\u0004\u0002\f!\u0001\raX\u0001\u0004g\u0016$X\u0003BA\r\u0003G!b!a\u0007\u0002\u001e\u0005\u001dR\"\u0001\u0001\t\r\u0001J\u0001\u0019AA\u0010!\u0011y\u0005+!\t\u0011\u0007M\u000b\u0019\u0003\u0002\u0004\u0002&%\u0011\rA\u0016\u0002\u0002)\"9\u0011\u0011F\u0005A\u0002\u0005\u0005\u0012!\u0002<bYV,GCBA\u000e\u0003[\ty\u0003C\u0003!\u0015\u0001\u0007q\f\u0003\u0004\u0002*)\u0001\rA\u0017\u000b\u0005\u00037\t\u0019\u0004C\u0004\u00026-\u0001\r!a\u000e\u0002\u0013A\f'/Y7QC&\u0014\b\u0007BA\u001d\u0003\u0003\u0002RaTA\u001e\u0003\u007fI1!!\u0010 \u0005%\u0001\u0016M]1n!\u0006L'\u000fE\u0002T\u0003\u0003\"1\"a\u0011\u00024\u0005\u0005\t\u0011!B\u0001-\n!q\fJ\u00192\u0003\r9W\r^\u000b\u0005\u0003\u0013\n\u0019\u0006\u0006\u0003\u0002L\u0005U\u0003#\u0002\u0017\u0002N\u0005E\u0013bAA([\t1q\n\u001d;j_:\u00042aUA*\t\u0019\t)\u0003\u0004b\u0001-\"1\u0001\u0005\u0004a\u0001\u0003/\u0002Ba\u0014)\u0002R\u0005)1\r\\3beR!\u00111DA/\u0011\u0019\u0001S\u00021\u0001\u0002`A\"\u0011\u0011MA3!\u0011y\u0005+a\u0019\u0011\u0007M\u000b)\u0007B\u0006\u0002h\u0005u\u0013\u0011!A\u0001\u0006\u00031&\u0001B0%cI\nAbZ3u\u001fJ$UMZ1vYR,B!!\u001c\u0002rQ!\u0011qNA:!\r\u0019\u0016\u0011\u000f\u0003\u0007\u0003Kq!\u0019\u0001,\t\r\u0001r\u0001\u0019AA;!\u0011y\u0005+a\u001c\u0002\u0003\u0011*B!a\u001f\u0002\u0000Q!\u0011QPAA!\r\u0019\u0016q\u0010\u0003\u0007\u0003Ky!\u0019\u0001,\t\r\u0001z\u0001\u0019AAB!\u0011y\u0005+! \u0002\u0015M,G\u000fR3gCVdG/\u0006\u0003\u0002\n\u0006EECBA\u000e\u0003\u0017\u000b\u0019\n\u0003\u0004!!\u0001\u0007\u0011Q\u0012\t\u0005\u001fB\u000by\tE\u0002T\u0003##a!!\n\u0011\u0005\u00041\u0006bBA\u0015!\u0001\u0007\u0011q\u0012\u000b\u0005\u00037\t9\nC\u0004\u0002\u001aF\u0001\r!a'\u0002\u0015A\f'/Y7QC&\u00148\u000fE\u0003-\u0003;\u000b\t+C\u0002\u0002 6\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?a\u0011\t\u0019+a*\u0011\u000b=\u000bY$!*\u0011\u0007M\u000b9\u000bB\u0006\u0002*\u0006]\u0015\u0011!A\u0001\u0006\u00031&\u0001B0%cM\n!bZ3u\t\u00164\u0017-\u001e7u+\u0011\ty+!.\u0015\t\u0005E\u0016q\u0017\t\u0006Y\u00055\u00131\u0017\t\u0004'\u0006UFABA\u0013%\t\u0007a\u000b\u0003\u0004!%\u0001\u0007\u0011\u0011\u0018\t\u0005\u001fB\u000b\u0019,\u0001\u0006iCN$UMZ1vYR,B!a0\u0002HR\u0019\u0011/!1\t\r\u0001\u001a\u0002\u0019AAb!\u0011y\u0005+!2\u0011\u0007M\u000b9\r\u0002\u0004\u0002&M\u0011\rAV\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003\u0002N\u0006=\u0007CA(\u0001\u0011\u001d\t\t\u000e\u0006a\u0001\u0003'\fQ!\u001a=ue\u0006\u00042aTAk\u0013\r\t9n\b\u0002\t!\u0006\u0014\u0018-\\'ba\u0006YA-\u001a4bk2$8i\u001c9z+\u0011\ti.!9\u0015\t\u0005}\u0017Q\u001d\t\u0004'\u0006\u0005HaBA\u0013+\t\u0007\u00111]\t\u0004/\u00065\u0007bBAi+\u0001\u0007\u00111[\u0001\u0010Kb$(/Y2u!\u0006\u0014\u0018-\\'baR!\u00111[Av\u0011\u001d\t\tN\u0006a\u0001\u0003'$\"!a5\u0002\u0011A\f'/Y7NCB,\"!a5\u0002\u001f\u0011,g-Y;miB\u000b'/Y7NCB\f\u0011b\u001d5pk2$wj\u001e8\u0015\u0007\u0015\u000bI\u0010\u0003\u0004!5\u0001\u0007\u00111 \u0019\u0005\u0003{\u0014\t\u0001\u0005\u0003P!\u0006}\bcA*\u0003\u0002\u0011Y!1AA}\u0003\u0003\u0005\tQ!\u0001W\u0005\u0011yF%\r\u001b\u0002\u0015\r|\u0007/\u001f,bYV,7/\u0006\u0003\u0003\n\t5AC\u0002B\u0006\u0005\u001f\u0011\u0019\u0002E\u0002T\u0005\u001b!q!!\n\u001c\u0005\u0004\t\u0019\u000fC\u0004\u0003\u0012m\u0001\rAa\u0003\u0002\u0005Q|\u0007\"CAi7A\u0005\t\u0019AAj\u0003Q\u0019w\u000e]=WC2,Xm\u001d\u0013eK\u001a\fW\u000f\u001c;%eU!!\u0011\u0004B\u0018+\t\u0011YB\u000b\u0003\u0002T\nu1F\u0001B\u0010!\u0011\u0011\tCa\u000b\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0005O\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t%R&\u0001\u0006b]:|G/\u0019;j_:LAA!\f\u0003$\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000f\u0005\u0015BD1\u0001\u0002d\u0006iqN\u001c)be\u0006l7\t[1oO\u0016$2!\u0012B\u001b\u0011\u0019\u0001S\u00041\u0001\u00038A\"!\u0011\bB\u001f!\u0011y\u0005Ka\u000f\u0011\u0007M\u0013i\u0004B\u0006\u0003@\tU\u0012\u0011!A\u0001\u0006\u00031&\u0001B0%cU\u0002"
)
public interface Params extends Identifiable, Serializable {
   void org$apache$spark$ml$param$Params$_setter_$paramMap_$eq(final ParamMap x$1);

   void org$apache$spark$ml$param$Params$_setter_$defaultParamMap_$eq(final ParamMap x$1);

   // $FF: synthetic method
   static Param[] params$(final Params $this) {
      return $this.params();
   }

   default Param[] params() {
      Method[] methods = this.getClass().getMethods();
      return (Param[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])methods), (m) -> BoxesRunTime.boxToBoolean($anonfun$params$1(m)))), (x$10) -> x$10.getName(), scala.math.Ordering.String..MODULE$)), (m) -> (Param)m.invoke(this), scala.reflect.ClassTag..MODULE$.apply(Param.class));
   }

   // $FF: synthetic method
   static String explainParam$(final Params $this, final Param param) {
      return $this.explainParam(param);
   }

   default String explainParam(final Param param) {
      this.shouldOwn(param);
      String var10000;
      if (this.isDefined(param)) {
         Option defaultValueStr = this.getDefault(param).map((x$11) -> "default: " + x$11);
         Option currentValueStr = this.get(param).map((x$12) -> "current: " + x$12);
         var10000 = ((IterableOnceOps)scala.Option..MODULE$.option2Iterable(defaultValueStr).$plus$plus(currentValueStr)).mkString("(", ", ", ")");
      } else {
         var10000 = "(undefined)";
      }

      String valueStr = var10000;
      var10000 = param.name();
      return var10000 + ": " + param.doc() + " " + valueStr;
   }

   // $FF: synthetic method
   static String explainParams$(final Params $this) {
      return $this.explainParams();
   }

   default String explainParams() {
      return scala.Predef..MODULE$.wrapRefArray(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.params()), (param) -> this.explainParam(param), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("\n");
   }

   // $FF: synthetic method
   static boolean isSet$(final Params $this, final Param param) {
      return $this.isSet(param);
   }

   default boolean isSet(final Param param) {
      this.shouldOwn(param);
      return this.paramMap().contains(param);
   }

   // $FF: synthetic method
   static boolean isDefined$(final Params $this, final Param param) {
      return $this.isDefined(param);
   }

   default boolean isDefined(final Param param) {
      this.shouldOwn(param);
      return this.defaultParamMap().contains(param) || this.paramMap().contains(param);
   }

   // $FF: synthetic method
   static boolean hasParam$(final Params $this, final String paramName) {
      return $this.hasParam(paramName);
   }

   default boolean hasParam(final String paramName) {
      return .MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps(this.params()), (x$13) -> BoxesRunTime.boxToBoolean($anonfun$hasParam$1(paramName, x$13)));
   }

   // $FF: synthetic method
   static Param getParam$(final Params $this, final String paramName) {
      return $this.getParam(paramName);
   }

   default Param getParam(final String paramName) {
      return (Param).MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.params()), (x$14) -> BoxesRunTime.boxToBoolean($anonfun$getParam$1(paramName, x$14))).getOrElse(() -> {
         throw new NoSuchElementException("Param " + paramName + " does not exist.");
      });
   }

   // $FF: synthetic method
   static Params set$(final Params $this, final Param param, final Object value) {
      return $this.set(param, value);
   }

   default Params set(final Param param, final Object value) {
      return this.set(param.$minus$greater(value));
   }

   // $FF: synthetic method
   static Params set$(final Params $this, final String param, final Object value) {
      return $this.set(param, value);
   }

   default Params set(final String param, final Object value) {
      return this.set(this.getParam(param), value);
   }

   // $FF: synthetic method
   static Params set$(final Params $this, final ParamPair paramPair) {
      return $this.set(paramPair);
   }

   default Params set(final ParamPair paramPair) {
      this.shouldOwn(paramPair.param());
      this.paramMap().put((Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{paramPair}));
      this.onParamChange(paramPair.param());
      return this;
   }

   // $FF: synthetic method
   static Option get$(final Params $this, final Param param) {
      return $this.get(param);
   }

   default Option get(final Param param) {
      this.shouldOwn(param);
      return this.paramMap().get(param);
   }

   // $FF: synthetic method
   static Params clear$(final Params $this, final Param param) {
      return $this.clear(param);
   }

   default Params clear(final Param param) {
      this.shouldOwn(param);
      this.paramMap().remove(param);
      this.onParamChange(param);
      return this;
   }

   // $FF: synthetic method
   static Object getOrDefault$(final Params $this, final Param param) {
      return $this.getOrDefault(param);
   }

   default Object getOrDefault(final Param param) {
      this.shouldOwn(param);
      return this.get(param).orElse(() -> this.getDefault(param)).getOrElse(() -> {
         throw new NoSuchElementException("Failed to find a default value for " + param.name());
      });
   }

   // $FF: synthetic method
   static Object $$(final Params $this, final Param param) {
      return $this.$(param);
   }

   default Object $(final Param param) {
      return this.getOrDefault(param);
   }

   // $FF: synthetic method
   static Params setDefault$(final Params $this, final Param param, final Object value) {
      return $this.setDefault(param, value);
   }

   default Params setDefault(final Param param, final Object value) {
      this.defaultParamMap().put((Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{param.$minus$greater(value)}));
      this.onParamChange(param);
      return this;
   }

   // $FF: synthetic method
   static Params setDefault$(final Params $this, final Seq paramPairs) {
      return $this.setDefault(paramPairs);
   }

   default Params setDefault(final Seq paramPairs) {
      paramPairs.foreach((p) -> this.setDefault(p.param(), p.value()));
      return this;
   }

   // $FF: synthetic method
   static Option getDefault$(final Params $this, final Param param) {
      return $this.getDefault(param);
   }

   default Option getDefault(final Param param) {
      this.shouldOwn(param);
      return this.defaultParamMap().get(param);
   }

   // $FF: synthetic method
   static boolean hasDefault$(final Params $this, final Param param) {
      return $this.hasDefault(param);
   }

   default boolean hasDefault(final Param param) {
      this.shouldOwn(param);
      return this.defaultParamMap().contains(param);
   }

   Params copy(final ParamMap extra);

   // $FF: synthetic method
   static Params defaultCopy$(final Params $this, final ParamMap extra) {
      return $this.defaultCopy(extra);
   }

   default Params defaultCopy(final ParamMap extra) {
      Params that = (Params)this.getClass().getConstructor(String.class).newInstance(this.uid());
      return this.copyValues(that, extra);
   }

   // $FF: synthetic method
   static ParamMap extractParamMap$(final Params $this, final ParamMap extra) {
      return $this.extractParamMap(extra);
   }

   default ParamMap extractParamMap(final ParamMap extra) {
      return this.defaultParamMap().$plus$plus(this.paramMap()).$plus$plus(extra);
   }

   // $FF: synthetic method
   static ParamMap extractParamMap$(final Params $this) {
      return $this.extractParamMap();
   }

   default ParamMap extractParamMap() {
      return this.extractParamMap(ParamMap$.MODULE$.empty());
   }

   ParamMap paramMap();

   ParamMap defaultParamMap();

   private void shouldOwn(final Param param) {
      boolean var3;
      Predef var10000;
      label19: {
         label18: {
            var10000 = scala.Predef..MODULE$;
            String var10001 = param.parent();
            String var2 = this.uid();
            if (var10001 == null) {
               if (var2 != null) {
                  break label18;
               }
            } else if (!var10001.equals(var2)) {
               break label18;
            }

            if (this.hasParam(param.name())) {
               var3 = true;
               break label19;
            }
         }

         var3 = false;
      }

      var10000.require(var3, () -> "Param " + param + " does not belong to " + this + ".");
   }

   // $FF: synthetic method
   static Params copyValues$(final Params $this, final Params to, final ParamMap extra) {
      return $this.copyValues(to, extra);
   }

   default Params copyValues(final Params to, final ParamMap extra) {
      ParamMap map = this.paramMap().$plus$plus(extra);
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.params()), (param) -> {
         if (this.defaultParamMap().contains(param) && to.hasParam(param.name())) {
            to.setDefault(to.getParam(param.name()), this.defaultParamMap().apply(param));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return map.contains(param) && to.hasParam(param.name()) ? to.set(param.name(), map.apply(param)) : BoxedUnit.UNIT;
      });
      return to;
   }

   // $FF: synthetic method
   static ParamMap copyValues$default$2$(final Params $this) {
      return $this.copyValues$default$2();
   }

   default ParamMap copyValues$default$2() {
      return ParamMap$.MODULE$.empty();
   }

   // $FF: synthetic method
   static void onParamChange$(final Params $this, final Param param) {
      $this.onParamChange(param);
   }

   default void onParamChange(final Param param) {
   }

   // $FF: synthetic method
   static boolean $anonfun$params$1(final Method m) {
      return Modifier.isPublic(m.getModifiers()) && Param.class.isAssignableFrom(m.getReturnType()) && m.getParameterCount() == 0;
   }

   // $FF: synthetic method
   static boolean $anonfun$hasParam$1(final String paramName$1, final Param x$13) {
      boolean var3;
      label23: {
         String var10000 = x$13.name();
         if (var10000 == null) {
            if (paramName$1 == null) {
               break label23;
            }
         } else if (var10000.equals(paramName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   static boolean $anonfun$getParam$1(final String paramName$2, final Param x$14) {
      boolean var3;
      label23: {
         String var10000 = x$14.name();
         if (var10000 == null) {
            if (paramName$2 == null) {
               break label23;
            }
         } else if (var10000.equals(paramName$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   static void $init$(final Params $this) {
      $this.org$apache$spark$ml$param$Params$_setter_$paramMap_$eq(ParamMap$.MODULE$.empty());
      $this.org$apache$spark$ml$param$Params$_setter_$defaultParamMap_$eq(ParamMap$.MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
