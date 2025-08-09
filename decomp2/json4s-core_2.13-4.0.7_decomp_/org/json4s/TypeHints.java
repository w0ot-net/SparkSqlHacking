package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tMgaB\u00193!\u0003\r\ta\u000e\u0005\u0006}\u0001!\ta\u0010\u0005\b\u0007\u0002\u0011\rQ\"\u0001E\u0011\u0015)\u0007A\"\u0001g\u0011\u0015!\bA\"\u0001v\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!a\u0004\u0001\t\u0003\t\t\u0002C\u0004\u00028\u0001!\t!!\u000f\t\u000f\u0005%\u0003\u0001\"\u0001\u0002L!9\u0011\u0011\f\u0001\u0005\u0002\u0005m\u0003bBA5\u0001\u0011\u0005\u00111\u000e\u0005\b\u0003s\u0002A\u0011AA>\u0011\u001d\ty\t\u0001C\u0001\u0003#Cq!!&\u0001\t\u0003\t9\nC\u0004\u0002\u001e\u0002!\t!a(\b\u0011\u0005\r&\u0007#\u00013\u0003K3q!\r\u001a\t\u0002I\n9\u000bC\u0004\u0002*B!\t!a+\u0007\r\u00055\u0006\u0003RAX\u0011)\t)J\u0005BK\u0002\u0013\u0005\u0013q\u0013\u0005\u000b\u0003{\u0013\"\u0011#Q\u0001\n\u0005e\u0005bBAU%\u0011\u0005\u0011q\u0018\u0005\t\u0007J\u0011\r\u0011\"\u0001\u0002H\"A\u0011Q\u001b\n!\u0002\u0013\tI\r\u0003\u0004f%\u0011\u0005\u0011q\u001b\u0005\u0007iJ!\t!!:\t\u000f\u0005=!\u0003\"\u0011\u0003\u0002!9\u0011q\u0007\n\u0005B\tE\u0001bBA%%\u0011\u0005#\u0011\u0005\u0005\b\u0003s\u0012B\u0011IA>\u0011\u001d\tyI\u0005C!\u0003#C\u0011Ba\f\u0013\u0003\u0003%\tA!\r\t\u0013\tU\"#%A\u0005\u0002\t]\u0002\"\u0003B'%\u0005\u0005I\u0011\tB(\u0011%\u0011yFEA\u0001\n\u0003\u0011\t\u0007C\u0005\u0003jI\t\t\u0011\"\u0001\u0003l!I!\u0011\u000f\n\u0002\u0002\u0013\u0005#1\u000f\u0005\n\u0005\u0003\u0013\u0012\u0011!C\u0001\u0005\u0007C\u0011Ba\"\u0013\u0003\u0003%\tE!#\t\u0013\t5%#!A\u0005B\t=\u0005\"\u0003BI%\u0005\u0005I\u0011\tBJ\u0011%\u0011)JEA\u0001\n\u0003\u00129jB\u0005\u0003\u001cB\t\t\u0011#\u0003\u0003\u001e\u001aI\u0011Q\u0016\t\u0002\u0002#%!q\u0014\u0005\b\u0003S[C\u0011\u0001B\\\u0011%\u0011\tjKA\u0001\n\u000b\u0012\u0019\nC\u0005\u0003:.\n\t\u0011\"!\u0003<\"I!qX\u0016\u0002\u0002\u0013\u0005%\u0011\u0019\u0005\n\u0005\u0013\\\u0013\u0011!C\u0005\u0005\u0017\u0014\u0011\u0002V=qK\"Kg\u000e^:\u000b\u0005M\"\u0014A\u00026t_:$4OC\u00016\u0003\ry'oZ\u0002\u0001'\t\u0001\u0001\b\u0005\u0002:y5\t!HC\u0001<\u0003\u0015\u00198-\u00197b\u0013\ti$H\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0001\u0003\"!O!\n\u0005\tS$\u0001B+oSR\fQ\u0001[5oiN,\u0012!\u0012\t\u0004\r:\u000bfBA$M\u001d\tA5*D\u0001J\u0015\tQe'\u0001\u0004=e>|GOP\u0005\u0002w%\u0011QJO\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0003MSN$(BA';a\t\u0011F\fE\u0002T/js!\u0001V+\u0011\u0005!S\u0014B\u0001,;\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001,\u0017\u0002\u0006\u00072\f7o\u001d\u0006\u0003-j\u0002\"a\u0017/\r\u0001\u0011IQLAA\u0001\u0002\u0003\u0015\tA\u0018\u0002\u0004?\u0012\n\u0014CA0c!\tI\u0004-\u0003\u0002bu\t9aj\u001c;iS:<\u0007CA\u001dd\u0013\t!'HA\u0002B]f\fq\u0001[5oi\u001a{'\u000f\u0006\u0002h[B\u0019\u0011\b\u001b6\n\u0005%T$AB(qi&|g\u000e\u0005\u0002TW&\u0011A.\u0017\u0002\u0007'R\u0014\u0018N\\4\t\u000b9\u001c\u0001\u0019A8\u0002\u000b\rd\u0017M\u001f>1\u0005A\u0014\bcA*XcB\u00111L\u001d\u0003\ng6\f\t\u0011!A\u0003\u0002y\u00131a\u0018\u00133\u0003!\u0019G.Y:t\r>\u0014Hc\u0001<}}B\u0019\u0011\b[<1\u0005aT\bcA*XsB\u00111L\u001f\u0003\nw\u0012\t\t\u0011!A\u0003\u0002y\u00131a\u0018\u00135\u0011\u0015iH\u00011\u0001k\u0003\u0011A\u0017N\u001c;\t\r}$\u0001\u0019AA\u0001\u0003\u0019\u0001\u0018M]3oiB\"\u00111AA\u0004!\u0011\u0019v+!\u0002\u0011\u0007m\u000b9\u0001\u0002\u0006\u0002\ny\f\t\u0011!A\u0003\u0002y\u00131a\u0018\u00134\u0003E!\u0018\u0010]3IS:$h)[3mI:\u000bW.Z\u000b\u0002U\u0006y\u0011n\u001d+za\u0016D\u0015N\u001c;GS\u0016dG\r\u0006\u0004\u0002\u0014\u0005e\u00111\u0006\t\u0004s\u0005U\u0011bAA\fu\t9!i\\8mK\u0006t\u0007bBA\u000e\r\u0001\u0007\u0011QD\u0001\u0002MB!\u0011qDA\u0013\u001d\u0011\t\t#a\t\u000e\u0003IJ!!\u0014\u001a\n\t\u0005\u001d\u0012\u0011\u0006\u0002\u0007\u0015\u001aKW\r\u001c3\u000b\u00055\u0013\u0004BB@\u0007\u0001\u0004\ti\u0003\r\u0003\u00020\u0005M\u0002\u0003B*X\u0003c\u00012aWA\u001a\t-\t)$a\u000b\u0002\u0002\u0003\u0005)\u0011\u00010\u0003\u0007}#S'\u0001\rusB,\u0007*\u001b8u\r&,G\u000e\u001a(b[\u00164uN\u001d%j]R$RaZA\u001e\u0003{AQ!`\u0004A\u0002)Daa`\u0004A\u0002\u0005}\u0002\u0007BA!\u0003\u000b\u0002BaU,\u0002DA\u00191,!\u0012\u0005\u0017\u0005\u001d\u0013QHA\u0001\u0002\u0003\u0015\tA\u0018\u0002\u0004?\u00122\u0014!\u0007;za\u0016D\u0015N\u001c;GS\u0016dGMT1nK\u001a{'o\u00117bgN$2aZA'\u0011\u0019q\u0007\u00021\u0001\u0002PA\"\u0011\u0011KA+!\u0011\u0019v+a\u0015\u0011\u0007m\u000b)\u0006B\u0006\u0002X\u00055\u0013\u0011!A\u0001\u0006\u0003q&aA0%o\u0005a1m\u001c8uC&t7\u000fS5oiR!\u00111CA/\u0011\u0019q\u0017\u00021\u0001\u0002`A\"\u0011\u0011MA3!\u0011\u0019v+a\u0019\u0011\u0007m\u000b)\u0007B\u0006\u0002h\u0005u\u0013\u0011!A\u0001\u0006\u0003q&aA0%q\u0005\u00112\u000f[8vY\u0012,\u0005\u0010\u001e:bGRD\u0015N\u001c;t)\u0011\t\u0019\"!\u001c\t\r9T\u0001\u0019AA8a\u0011\t\t(!\u001e\u0011\tM;\u00161\u000f\t\u00047\u0006UDaCA<\u0003[\n\t\u0011!A\u0003\u0002y\u00131a\u0018\u0013:\u0003-!Wm]3sS\u0006d\u0017N_3\u0016\u0005\u0005u\u0004CB\u001d\u0002\u0000\u0005\r%-C\u0002\u0002\u0002j\u0012q\u0002U1si&\fGNR;oGRLwN\u001c\t\u0007s\u0005\u0015%.!#\n\u0007\u0005\u001d%H\u0001\u0004UkBdWM\r\t\u0005\u0003C\tY)C\u0002\u0002\u000eJ\u0012qAS(cU\u0016\u001cG/A\u0005tKJL\u0017\r\\5{KV\u0011\u00111\u0013\t\u0007s\u0005}$-!#\u0002\u0015\r|W\u000e]8oK:$8/\u0006\u0002\u0002\u001aB!aITAN!\r\t\t\u0003A\u0001\u0006IAdWo\u001d\u000b\u0005\u00037\u000b\t\u000b\u0003\u0004D\u001d\u0001\u0007\u00111T\u0001\n)f\u0004X\rS5oiN\u00042!!\t\u0011'\t\u0001\u0002(\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003K\u0013!cQ8na>\u001c\u0018\u000e^3UsB,\u0007*\u001b8ugNA!\u0003OAN\u0003c\u000b9\fE\u0002:\u0003gK1!!.;\u0005\u001d\u0001&o\u001c3vGR\u00042ARA]\u0013\r\tY\f\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\fG>l\u0007o\u001c8f]R\u001c\b\u0005\u0006\u0003\u0002B\u0006\u0015\u0007cAAb%5\t\u0001\u0003C\u0004\u0002\u0016V\u0001\r!!'\u0016\u0005\u0005%\u0007\u0003\u0002$O\u0003\u0017\u0004D!!4\u0002RB!1kVAh!\rY\u0016\u0011\u001b\u0003\u000b\u0003'<\u0012\u0011!A\u0001\u0006\u0003q&\u0001B0%cA\na\u0001[5oiN\u0004CcA4\u0002Z\"1a\u000e\u0007a\u0001\u00037\u0004D!!8\u0002bB!1kVAp!\rY\u0016\u0011\u001d\u0003\f\u0003G\fI.!A\u0001\u0002\u000b\u0005aL\u0001\u0003`IE\nDCBAt\u0003g\f)\u0010\u0005\u0003:Q\u0006%\b\u0007BAv\u0003_\u0004BaU,\u0002nB\u00191,a<\u0005\u0015\u0005E\u0018$!A\u0001\u0002\u000b\u0005aL\u0001\u0003`IE\u001a\u0004\"B?\u001a\u0001\u0004Q\u0007BB@\u001a\u0001\u0004\t9\u0010\r\u0003\u0002z\u0006u\b\u0003B*X\u0003w\u00042aWA\u007f\t-\ty0!>\u0002\u0002\u0003\u0005)\u0011\u00010\u0003\t}#\u0013G\r\u000b\u0007\u0003'\u0011\u0019A!\u0002\t\u000f\u0005m!\u00041\u0001\u0002\u001e!1qP\u0007a\u0001\u0005\u000f\u0001DA!\u0003\u0003\u000eA!1k\u0016B\u0006!\rY&Q\u0002\u0003\f\u0005\u001f\u0011)!!A\u0001\u0002\u000b\u0005aL\u0001\u0003`IE\"D#B4\u0003\u0014\tU\u0001\"B?\u001c\u0001\u0004Q\u0007BB@\u001c\u0001\u0004\u00119\u0002\r\u0003\u0003\u001a\tu\u0001\u0003B*X\u00057\u00012a\u0017B\u000f\t-\u0011yB!\u0006\u0002\u0002\u0003\u0005)\u0011\u00010\u0003\t}#\u0013'\u000e\u000b\u0004O\n\r\u0002B\u00028\u001d\u0001\u0004\u0011)\u0003\r\u0003\u0003(\t-\u0002\u0003B*X\u0005S\u00012a\u0017B\u0016\t-\u0011iCa\t\u0002\u0002\u0003\u0005)\u0011\u00010\u0003\t}#\u0013GN\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003\u0002B\nM\u0002\"CAK?A\u0005\t\u0019AAM\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"A!\u000f+\t\u0005e%1H\u0016\u0003\u0005{\u0001BAa\u0010\u0003J5\u0011!\u0011\t\u0006\u0005\u0005\u0007\u0012)%A\u0005v]\u000eDWmY6fI*\u0019!q\t\u001e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003L\t\u0005#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"A!\u0015\u0011\t\tM#QL\u0007\u0003\u0005+RAAa\u0016\u0003Z\u0005!A.\u00198h\u0015\t\u0011Y&\u0001\u0003kCZ\f\u0017b\u00017\u0003V\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011!1\r\t\u0004s\t\u0015\u0014b\u0001B4u\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019!M!\u001c\t\u0013\t=4%!AA\u0002\t\r\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003vA)!q\u000fB?E6\u0011!\u0011\u0010\u0006\u0004\u0005wR\u0014AC2pY2,7\r^5p]&!!q\u0010B=\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005M!Q\u0011\u0005\t\u0005_*\u0013\u0011!a\u0001E\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011\tFa#\t\u0013\t=d%!AA\u0002\t\r\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\t\r\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\tE\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0014\te\u0005\u0002\u0003B8S\u0005\u0005\t\u0019\u00012\u0002%\r{W\u000e]8tSR,G+\u001f9f\u0011&tGo\u001d\t\u0004\u0003\u0007\\3#B\u0016\u0003\"\n5\u0006\u0003\u0003BR\u0005S\u000bI*!1\u000e\u0005\t\u0015&b\u0001BTu\u00059!/\u001e8uS6,\u0017\u0002\u0002BV\u0005K\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\u0011yK!.\u000e\u0005\tE&\u0002\u0002BZ\u00053\n!![8\n\t\u0005m&\u0011\u0017\u000b\u0003\u0005;\u000bQ!\u00199qYf$B!!1\u0003>\"9\u0011Q\u0013\u0018A\u0002\u0005e\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0005\u0007\u0014)\r\u0005\u0003:Q\u0006e\u0005\"\u0003Bd_\u0005\u0005\t\u0019AAa\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u001b\u0004BAa\u0015\u0003P&!!\u0011\u001bB+\u0005\u0019y%M[3di\u0002"
)
public interface TypeHints {
   List hints();

   Option hintFor(final Class clazz);

   Option classFor(final String hint, final Class parent);

   default String typeHintFieldName() {
      return "jsonClass";
   }

   // $FF: synthetic method
   static boolean isTypeHintField$(final TypeHints $this, final Tuple2 f, final Class parent) {
      return $this.isTypeHintField(f, parent);
   }

   default boolean isTypeHintField(final Tuple2 f, final Class parent) {
      boolean var3;
      if (f != null) {
         String key = (String)f._1();
         JValue var6 = (JValue)f._2();
         if (var6 instanceof JString) {
            boolean var10000;
            label25: {
               label24: {
                  JString var7 = (JString)var6;
                  String value = var7.s();
                  Option hint = this.typeHintFieldNameForHint(value, parent);
                  String var10 = this.typeHintFieldName();
                  if (key == null) {
                     if (var10 != null) {
                        break label24;
                     }
                  } else if (!key.equals(var10)) {
                     break label24;
                  }

                  if (hint.isDefined()) {
                     var10000 = true;
                     break label25;
                  }
               }

               var10000 = false;
            }

            var3 = var10000;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   // $FF: synthetic method
   static Option typeHintFieldNameForHint$(final TypeHints $this, final String hint, final Class parent) {
      return $this.typeHintFieldNameForHint(hint, parent);
   }

   default Option typeHintFieldNameForHint(final String hint, final Class parent) {
      return this.classFor(hint, parent).map((x$1) -> this.typeHintFieldName());
   }

   // $FF: synthetic method
   static Option typeHintFieldNameForClass$(final TypeHints $this, final Class clazz) {
      return $this.typeHintFieldNameForClass(clazz);
   }

   default Option typeHintFieldNameForClass(final Class clazz) {
      return this.hintFor(clazz).flatMap((x$2) -> this.typeHintFieldNameForHint(x$2, clazz));
   }

   default boolean containsHint(final Class clazz) {
      return this.hints().exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$containsHint$1(clazz, x$3)));
   }

   default boolean shouldExtractHints(final Class clazz) {
      return this.hints().exists((x$4) -> BoxesRunTime.boxToBoolean($anonfun$shouldExtractHints$1(clazz, x$4)));
   }

   // $FF: synthetic method
   static PartialFunction deserialize$(final TypeHints $this) {
      return $this.deserialize();
   }

   default PartialFunction deserialize() {
      return (PartialFunction).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   static PartialFunction serialize$(final TypeHints $this) {
      return $this.serialize();
   }

   default PartialFunction serialize() {
      return (PartialFunction).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   static List components$(final TypeHints $this) {
      return $this.components();
   }

   default List components() {
      return (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new TypeHints[]{this}));
   }

   default TypeHints $plus(final TypeHints hints) {
      List var2 = hints.components();
      return new CompositeTypeHints(this.components().$colon$colon$colon(var2));
   }

   // $FF: synthetic method
   static boolean $anonfun$containsHint$1(final Class clazz$2, final Class x$3) {
      return x$3.isAssignableFrom(clazz$2);
   }

   // $FF: synthetic method
   static boolean $anonfun$shouldExtractHints$1(final Class clazz$3, final Class x$4) {
      return clazz$3.isAssignableFrom(x$4);
   }

   static void $init$(final TypeHints $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class CompositeTypeHints implements TypeHints, Product, Serializable {
      private final List components;
      private final List hints;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String typeHintFieldName() {
         return TypeHints.super.typeHintFieldName();
      }

      public boolean containsHint(final Class clazz) {
         return TypeHints.super.containsHint(clazz);
      }

      public boolean shouldExtractHints(final Class clazz) {
         return TypeHints.super.shouldExtractHints(clazz);
      }

      public TypeHints $plus(final TypeHints hints) {
         return TypeHints.super.$plus(hints);
      }

      public List components() {
         return this.components;
      }

      public List hints() {
         return this.hints;
      }

      public Option hintFor(final Class clazz) {
         return ((LinearSeqOps)this.components().reverse().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$hintFor$1(clazz, x$6))).map((th) -> {
            Option hint = th.hintFor(clazz);
            return new Tuple2(hint, hint.flatMap((x$7) -> th.classFor(x$7, clazz)).getOrElse(() -> scala.sys.package..MODULE$.error((new StringBuilder(36)).append("hintFor/classFor not invertible for ").append(th).toString())));
         }).sortWith((x, y) -> BoxesRunTime.boxToBoolean($anonfun$hintFor$5(clazz, x, y)))).headOption().flatMap((x$8) -> (Option)x$8._1());
      }

      public Option classFor(final String hint, final Class parent) {
         return this.components().find((h) -> BoxesRunTime.boxToBoolean($anonfun$classFor$3(hint, parent, h))).flatMap((x$10) -> x$10.classFor(hint, parent));
      }

      public boolean isTypeHintField(final Tuple2 f, final Class parent) {
         return this.components().exists((x$11) -> BoxesRunTime.boxToBoolean($anonfun$isTypeHintField$1(f, parent, x$11)));
      }

      public Option typeHintFieldNameForHint(final String hint, final Class parent) {
         return this.components().flatMap((x$12) -> x$12.typeHintFieldNameForHint(hint, parent)).headOption();
      }

      public Option typeHintFieldNameForClass(final Class clazz) {
         return this.components().flatMap((x$13) -> x$13.typeHintFieldNameForClass(clazz)).headOption();
      }

      public PartialFunction deserialize() {
         return (PartialFunction)this.components().foldLeft(.MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (result, cur) -> result.orElse(cur.deserialize()));
      }

      public PartialFunction serialize() {
         return (PartialFunction)this.components().foldLeft(.MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (result, cur) -> result.orElse(cur.serialize()));
      }

      public CompositeTypeHints copy(final List components) {
         return new CompositeTypeHints(components);
      }

      public List copy$default$1() {
         return this.components();
      }

      public String productPrefix() {
         return "CompositeTypeHints";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.components();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof CompositeTypeHints;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "components";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof CompositeTypeHints) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        CompositeTypeHints var4 = (CompositeTypeHints)x$1;
                        List var10000 = this.components();
                        List var5 = var4.components();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hintFor$1(final Class clazz$4, final TypeHints x$6) {
         return x$6.containsHint(clazz$4);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hintFor$5(final Class clazz$4, final Tuple2 x, final Tuple2 y) {
         return ClassDelta$.MODULE$.delta((Class)x._2(), clazz$4) - ClassDelta$.MODULE$.delta((Class)y._2(), clazz$4) <= 0;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$classFor$2(final Option x$9) {
         return x$9.isDefined();
      }

      private static final boolean hasClass$1(final TypeHints h, final String hint$1, final Class parent$1) {
         return scala.util.control.Exception..MODULE$.allCatch().opt(() -> h.classFor(hint$1, parent$1)).exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$classFor$2(x$9)));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$classFor$3(final String hint$1, final Class parent$1, final TypeHints h) {
         return hasClass$1(h, hint$1, parent$1);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isTypeHintField$1(final Tuple2 f$1, final Class parent$2, final TypeHints x$11) {
         return x$11.isTypeHintField(f$1, parent$2);
      }

      public CompositeTypeHints(final List components) {
         this.components = components;
         TypeHints.$init$(this);
         Product.$init$(this);
         this.hints = components.flatMap((x$5) -> x$5.hints());
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class CompositeTypeHints$ extends AbstractFunction1 implements Serializable {
      public static final CompositeTypeHints$ MODULE$ = new CompositeTypeHints$();

      public final String toString() {
         return "CompositeTypeHints";
      }

      public CompositeTypeHints apply(final List components) {
         return new CompositeTypeHints(components);
      }

      public Option unapply(final CompositeTypeHints x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.components()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CompositeTypeHints$.class);
      }

      public CompositeTypeHints$() {
      }
   }
}
