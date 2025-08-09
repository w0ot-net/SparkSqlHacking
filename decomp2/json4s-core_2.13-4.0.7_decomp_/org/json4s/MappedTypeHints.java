package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a\u0001\u0002\u0011\"\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t%\u0002\u0011\t\u0012)A\u0005\u0005\"AQ\f\u0001BK\u0002\u0013\u0005c\f\u0003\u0005`\u0001\tE\t\u0015!\u0003[\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u001dQ\u0007A1A\u0005B-Da\u0001\u001e\u0001!\u0002\u0013a\u0007BB;\u0001A\u0003%a\u000fC\u0003}\u0001\u0011\u0005Q\u0010C\u0004\u0002\u0012\u0001!\t!a\u0005\t\u0013\u0005%\u0002!!A\u0005\u0002\u0005-\u0002\"CA\u0019\u0001E\u0005I\u0011AA\u001a\u0011%\tI\u0005AI\u0001\n\u0003\tY\u0005C\u0005\u0002P\u0001\t\t\u0011\"\u0011\u0002R!I\u0011\u0011\r\u0001\u0002\u0002\u0013\u0005\u00111\r\u0005\n\u0003W\u0002\u0011\u0011!C\u0001\u0003[B\u0011\"a\u001d\u0001\u0003\u0003%\t%!\u001e\t\u0013\u0005\r\u0005!!A\u0005\u0002\u0005\u0015\u0005\"CAH\u0001\u0005\u0005I\u0011IAI\u0011%\t)\nAA\u0001\n\u0003\n9\nC\u0005\u0002\u001a\u0002\t\t\u0011\"\u0011\u0002\u001c\"I\u0011Q\u0014\u0001\u0002\u0002\u0013\u0005\u0013qT\u0004\n\u0003G\u000b\u0013\u0011!E\u0001\u0003K3\u0001\u0002I\u0011\u0002\u0002#\u0005\u0011q\u0015\u0005\u0007Ab!\t!!3\t\u0013\u0005e\u0005$!A\u0005F\u0005m\u0005\"CAf1\u0005\u0005I\u0011QAg\u0011%\ti\u000eGI\u0001\n\u0003\tY\u0005C\u0005\u0002`b\t\t\u0011\"!\u0002b\"I\u0011\u0011 \r\u0012\u0002\u0013\u0005\u00111\n\u0005\n\u0003wD\u0012\u0011!C\u0005\u0003{\u0014q\"T1qa\u0016$G+\u001f9f\u0011&tGo\u001d\u0006\u0003E\r\naA[:p]R\u001a(\"\u0001\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u00019S&\r\u001b\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\r\u0005s\u0017PU3g!\tqs&D\u0001\"\u0013\t\u0001\u0014EA\u0005UsB,\u0007*\u001b8ugB\u0011\u0001FM\u0005\u0003g%\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00026{9\u0011ag\u000f\b\u0003oij\u0011\u0001\u000f\u0006\u0003s\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005qJ\u0013a\u00029bG.\fw-Z\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001P\u0015\u0002\u000f!Lg\u000e^'baV\t!\t\u0005\u0003D\u000f*SfB\u0001#F!\t9\u0014&\u0003\u0002GS\u00051\u0001K]3eK\u001aL!\u0001S%\u0003\u00075\u000b\u0007O\u0003\u0002GSA\u00121\n\u0015\t\u0004\u00072s\u0015BA'J\u0005\u0015\u0019E.Y:t!\ty\u0005\u000b\u0004\u0001\u0005\u0013E\u0013\u0011\u0011!A\u0001\u0006\u0003\u0019&aA0%c\u0005A\u0001.\u001b8u\u001b\u0006\u0004\b%\u0005\u0002U/B\u0011\u0001&V\u0005\u0003-&\u0012qAT8uQ&tw\r\u0005\u0002)1&\u0011\u0011,\u000b\u0002\u0004\u0003:L\bCA\"\\\u0013\ta\u0016J\u0001\u0004TiJLgnZ\u0001\u0012if\u0004X\rS5oi\u001aKW\r\u001c3OC6,W#\u0001.\u0002%QL\b/\u001a%j]R4\u0015.\u001a7e\u001d\u0006lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\t\u001c\u0017\u000e\u0005\u0002/\u0001!)\u0001)\u0002a\u0001IB!1iR3[a\t1\u0007\u000eE\u0002D\u0019\u001e\u0004\"a\u00145\u0005\u0013E\u001b\u0017\u0011!A\u0001\u0006\u0003\u0019\u0006bB/\u0006!\u0003\u0005\rAW\u0001\u0006Q&tGo]\u000b\u0002YB\u0019Q'\\8\n\u00059|$\u0001\u0002'jgR\u0004$\u0001\u001d:\u0011\u0007\rc\u0015\u000f\u0005\u0002Pe\u0012I1oBA\u0001\u0002\u0003\u0015\ta\u0015\u0002\u0004?\u0012\u0012\u0014A\u00025j]R\u001c\b%\u0001\u0004m_>\\W\u000f\u001d\t\u0005\u0007\u001eSv\u000f\r\u0002yuB\u00191\tT=\u0011\u0005=SH!C>\t\u0003\u0003\u0005\tQ!\u0001T\u0005\ryFeM\u0001\bQ&tGOR8s)\rq\u00181\u0001\t\u0004Q}T\u0016bAA\u0001S\t1q\n\u001d;j_:Dq!!\u0002\n\u0001\u0004\t9!A\u0003dY\u0006T(\u0010\r\u0003\u0002\n\u00055\u0001\u0003B\"M\u0003\u0017\u00012aTA\u0007\t-\ty!a\u0001\u0002\u0002\u0003\u0005)\u0011A*\u0003\u0007}#C'\u0001\u0005dY\u0006\u001c8OR8s)\u0019\t)\"a\u0006\u0002\u001cA\u0019\u0001f`<\t\r\u0005e!\u00021\u0001[\u0003\u0011A\u0017N\u001c;\t\u000f\u0005u!\u00021\u0001\u0002 \u00051\u0001/\u0019:f]R\u0004D!!\t\u0002&A!1\tTA\u0012!\ry\u0015Q\u0005\u0003\f\u0003O\tY\"!A\u0001\u0002\u000b\u00051KA\u0002`IU\nAaY8qsR)!-!\f\u00020!9\u0001i\u0003I\u0001\u0002\u0004!\u0007bB/\f!\u0003\u0005\rAW\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t)DK\u0002C\u0003oY#!!\u000f\u0011\t\u0005m\u0012QI\u0007\u0003\u0003{QA!a\u0010\u0002B\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u0007J\u0013AC1o]>$\u0018\r^5p]&!\u0011qIA\u001f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tiEK\u0002[\u0003o\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA*!\u0011\t)&a\u0018\u000e\u0005\u0005]#\u0002BA-\u00037\nA\u0001\\1oO*\u0011\u0011QL\u0001\u0005U\u00064\u0018-C\u0002]\u0003/\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u001a\u0011\u0007!\n9'C\u0002\u0002j%\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2aVA8\u0011%\t\t\bEA\u0001\u0002\u0004\t)'A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003o\u0002R!!\u001f\u0002\u0000]k!!a\u001f\u000b\u0007\u0005u\u0014&\u0001\u0006d_2dWm\u0019;j_:LA!!!\u0002|\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9)!$\u0011\u0007!\nI)C\u0002\u0002\f&\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002rI\t\t\u00111\u0001X\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005M\u00131\u0013\u0005\n\u0003c\u001a\u0012\u0011!a\u0001\u0003K\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003K\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003'\na!Z9vC2\u001cH\u0003BAD\u0003CC\u0001\"!\u001d\u0017\u0003\u0003\u0005\raV\u0001\u0010\u001b\u0006\u0004\b/\u001a3UsB,\u0007*\u001b8ugB\u0011a\u0006G\n\u00061\u0005%\u0016q\u0018\t\t\u0003W\u000b\t,!.[E6\u0011\u0011Q\u0016\u0006\u0004\u0003_K\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003g\u000biKA\tBEN$(/Y2u\rVt7\r^5p]J\u0002RaQ$\u00028j\u0003D!!/\u0002>B!1\tTA^!\ry\u0015Q\u0018\u0003\n#b\t\t\u0011!A\u0003\u0002M\u0003B!!1\u0002H6\u0011\u00111\u0019\u0006\u0005\u0003\u000b\fY&\u0001\u0002j_&\u0019a(a1\u0015\u0005\u0005\u0015\u0016!B1qa2LH#\u00022\u0002P\u0006m\u0007B\u0002!\u001c\u0001\u0004\t\t\u000eE\u0003D\u000f\u0006M'\f\r\u0003\u0002V\u0006e\u0007\u0003B\"M\u0003/\u00042aTAm\t)\t\u0016qZA\u0001\u0002\u0003\u0015\ta\u0015\u0005\b;n\u0001\n\u00111\u0001[\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003G\f)\u0010\u0005\u0003)\u007f\u0006\u0015\bC\u0002\u0015\u0002h\u0006-(,C\u0002\u0002j&\u0012a\u0001V;qY\u0016\u0014\u0004#B\"H\u0003[T\u0006\u0007BAx\u0003g\u0004Ba\u0011'\u0002rB\u0019q*a=\u0005\u0013Ek\u0012\u0011!A\u0001\u0006\u0003\u0019\u0006\u0002CA|;\u0005\u0005\t\u0019\u00012\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u007f\u0004B!!\u0016\u0003\u0002%!!1AA,\u0005\u0019y%M[3di\u0002"
)
public class MappedTypeHints implements TypeHints, Product, Serializable {
   private final Map hintMap;
   private final String typeHintFieldName;
   private final List hints;
   private final Map lookup;

   public static String $lessinit$greater$default$2() {
      return MappedTypeHints$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final MappedTypeHints x$0) {
      return MappedTypeHints$.MODULE$.unapply(x$0);
   }

   public static String apply$default$2() {
      return MappedTypeHints$.MODULE$.apply$default$2();
   }

   public static MappedTypeHints apply(final Map hintMap, final String typeHintFieldName) {
      return MappedTypeHints$.MODULE$.apply(hintMap, typeHintFieldName);
   }

   public static Function1 tupled() {
      return MappedTypeHints$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MappedTypeHints$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean isTypeHintField(final Tuple2 f, final Class parent) {
      return TypeHints.isTypeHintField$(this, f, parent);
   }

   public Option typeHintFieldNameForHint(final String hint, final Class parent) {
      return TypeHints.typeHintFieldNameForHint$(this, hint, parent);
   }

   public Option typeHintFieldNameForClass(final Class clazz) {
      return TypeHints.typeHintFieldNameForClass$(this, clazz);
   }

   public boolean containsHint(final Class clazz) {
      return TypeHints.containsHint$(this, clazz);
   }

   public boolean shouldExtractHints(final Class clazz) {
      return TypeHints.shouldExtractHints$(this, clazz);
   }

   public PartialFunction deserialize() {
      return TypeHints.deserialize$(this);
   }

   public PartialFunction serialize() {
      return TypeHints.serialize$(this);
   }

   public List components() {
      return TypeHints.components$(this);
   }

   public TypeHints $plus(final TypeHints hints) {
      return TypeHints.$plus$(this, hints);
   }

   public Map hintMap() {
      return this.hintMap;
   }

   public String typeHintFieldName() {
      return this.typeHintFieldName;
   }

   public List hints() {
      return this.hints;
   }

   public Option hintFor(final Class clazz) {
      return this.hintMap().get(clazz);
   }

   public Option classFor(final String hint, final Class parent) {
      return this.lookup.get(hint).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$classFor$1(parent, x$1)));
   }

   public MappedTypeHints copy(final Map hintMap, final String typeHintFieldName) {
      return new MappedTypeHints(hintMap, typeHintFieldName);
   }

   public Map copy$default$1() {
      return this.hintMap();
   }

   public String copy$default$2() {
      return this.typeHintFieldName();
   }

   public String productPrefix() {
      return "MappedTypeHints";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.hintMap();
            break;
         case 1:
            var10000 = this.typeHintFieldName();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof MappedTypeHints;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "hintMap";
            break;
         case 1:
            var10000 = "typeHintFieldName";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof MappedTypeHints) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     MappedTypeHints var4 = (MappedTypeHints)x$1;
                     Map var10000 = this.hintMap();
                     Map var5 = var4.hintMap();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     String var7 = this.typeHintFieldName();
                     String var6 = var4.typeHintFieldName();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$classFor$1(final Class parent$1, final Class x$1) {
      return parent$1.isAssignableFrom(x$1);
   }

   public MappedTypeHints(final Map hintMap, final String typeHintFieldName) {
      this.hintMap = hintMap;
      this.typeHintFieldName = typeHintFieldName;
      TypeHints.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(hintMap.size() == ((SeqOps)hintMap.values().toList().distinct()).size(), () -> "values in type hint mapping must be distinct");
      this.hints = hintMap.keys().toList();
      this.lookup = (Map)hintMap.map((x$1) -> x$1.swap());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
