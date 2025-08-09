package spire.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.whitebox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005x!\u0002\u0007\u000e\u0011\u0003\u0011b!\u0002\u000b\u000e\u0011\u0003)\u0002\"\u0002\u000f\u0002\t\u0003i\u0002\"\u0002\u0010\u0002\t\u0003y\u0002\"\u0002,\u0002\t\u00039\u0006\"B6\u0002\t\u0003a\u0007\"B?\u0002\t\u0003q\bbBA\u0011\u0003\u0011\u0005\u00111\u0005\u0005\b\u0003+\nA\u0011AA,\u0011\u001d\t\u0019)\u0001C\u0001\u0003\u000bCq!!(\u0002\t\u0003\ty\nC\u0004\u0002<\u0006!\t!!0\u0002\u001d)\u000bg/Y!vi>l\u0015m\u0019:pg*\u0011abD\u0001\u0007[\u0006\u001c'o\\:\u000b\u0003A\tQa\u001d9je\u0016\u001c\u0001\u0001\u0005\u0002\u0014\u00035\tQB\u0001\bKCZ\f\u0017)\u001e;p\u001b\u0006\u001c'o\\:\u0014\u0005\u00051\u0002CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002%\u0005a1/Z7je&tw-S7qYV\u0011\u0001\u0005\u0013\u000b\u0003C\u0015\"\"AI)\u0011\u0007\r*DH\u0004\u0002%K1\u0001\u0001\"\u0002\u0014\u0004\u0001\u00049\u0013!A2\u0011\u0005!\u0012dBA\u00151\u001d\tQsF\u0004\u0002,]5\tAF\u0003\u0002.#\u00051AH]8pizJ\u0011\u0001E\u0005\u0003\u001d=I!!M\u0007\u0002\r\r|W\u000e]1u\u0013\t\u0019DGA\u0004D_:$X\r\u001f;\u000b\u0005Ej\u0011B\u0001\u001c8\u0005\u0011)\u0005\u0010\u001d:\n\u0005aJ$aB!mS\u0006\u001cXm\u001d\u0006\u0003\u001diR!a\u000f\r\u0002\u000fI,g\r\\3diB\u0019Q\bR$\u000f\u0005y\neB\u0001\u0016@\u0013\t\u0001u\"A\u0004bY\u001e,'M]1\n\u0005\t\u001b\u0015a\u00029bG.\fw-\u001a\u0006\u0003\u0001>I!!\u0012$\u0003\u0011M+W.\u001b:j]\u001eT!AQ\"\u0011\u0005\u0011BE!B%\u0004\u0005\u0004Q%!A!\u0012\u0005-s\u0005CA\fM\u0013\ti\u0005DA\u0004O_RD\u0017N\\4\u0011\u0005]y\u0015B\u0001)\u0019\u0005\r\te.\u001f\u0005\b%\u000e\t\t\u0011q\u0001T\u0003-)g/\u001b3f]\u000e,G%\u000e\u0019\u0011\u0007\r\"v)\u0003\u0002Vo\tYq+Z1l)f\u0004X\rV1h\u0003\u001d\u0011\u0018nZ%na2,\"\u0001\u00172\u0015\u0005ekFc\u0001.gSR\u00111l\u0019\t\u00049VrfB\u0001\u0013^\u0011\u00151C\u00011\u0001(!\rit,Y\u0005\u0003A\u001a\u00131AU5h!\t!#\rB\u0003J\t\t\u0007!\nC\u0004e\t\u0005\u0005\t9A3\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$S'\r\t\u00049R\u000b\u0007\"B4\u0005\u0001\u0004A\u0017!\u0001>\u0011\u0007q+\u0014\rC\u0003k\t\u0001\u0007\u0001.A\u0001p\u0003\u001d\u0011hnZ%na2,\"!\\<\u0015\u00059\u0014HCA8|)\t\u0001\b\u0010E\u0002rkMt!\u0001\n:\t\u000b\u0019*\u0001\u0019A\u0014\u0011\u0007u\"h/\u0003\u0002v\r\n\u0019!K\\4\u0011\u0005\u0011:H!B%\u0006\u0005\u0004Q\u0005bB=\u0006\u0003\u0003\u0005\u001dA_\u0001\fKZLG-\u001a8dK\u0012*$\u0007E\u0002r)ZDQaZ\u0003A\u0002q\u00042!]\u001bw\u0003!\u0011\u0018N\\4J[BdWcA@\u0002\u0014Q!\u0011\u0011AA\u0005)\u0019\t\u0019!a\u0007\u0002 Q!\u0011QAA\u000b!\u0015\t9!NA\u0006\u001d\r!\u0013\u0011\u0002\u0005\u0006M\u0019\u0001\ra\n\t\u0006{\u00055\u0011\u0011C\u0005\u0004\u0003\u001f1%\u0001\u0002*j]\u001e\u00042\u0001JA\n\t\u0015IeA1\u0001K\u0011%\t9BBA\u0001\u0002\b\tI\"A\u0006fm&$WM\\2fIU\u001a\u0004#BA\u0004)\u0006E\u0001BB4\u0007\u0001\u0004\ti\u0002E\u0003\u0002\bU\n\t\u0002\u0003\u0004k\r\u0001\u0007\u0011QD\u0001\u0012KV\u001cG.\u001b3fC:\u0014\u0016N\\4J[BdW\u0003BA\u0013\u0003w!B!a\n\u00022Q1\u0011\u0011FA(\u0003'\"B!a\u000b\u0002DQ!\u0011QFA\u001f!\u0015\ty#NA\u001a\u001d\r!\u0013\u0011\u0007\u0005\u0006M\u001d\u0001\ra\n\t\u0006{\u0005U\u0012\u0011H\u0005\u0004\u0003o1%!D#vG2LG-Z1o%&tw\rE\u0002%\u0003w!Q!S\u0004C\u0002)C\u0011\"a\u0010\b\u0003\u0003\u0005\u001d!!\u0011\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$S\u0007\u000e\t\u0006\u0003_!\u0016\u0011\b\u0005\b\u0003\u000b:\u0001\u0019AA$\u0003\t)g\u000fE\u0003\u00020U\nI\u0005E\u0003>\u0003\u0017\nI$C\u0002\u0002N\u0019\u0013!!R9\t\r\u001d<\u0001\u0019AA)!\u0015\ty#NA\u001d\u0011\u0019Qw\u00011\u0001\u0002R\u0005Ia-[3mI&k\u0007\u000f\\\u000b\u0005\u00033\ny\u0007\u0006\u0003\u0002\\\u0005\u0015DCBA/\u0003{\n\t\t\u0006\u0003\u0002`\u0005]D\u0003BA1\u0003c\u0002R!a\u00196\u0003Or1\u0001JA3\u0011\u00151\u0003\u00021\u0001(!\u0015i\u0014\u0011NA7\u0013\r\tYG\u0012\u0002\u0006\r&,G\u000e\u001a\t\u0004I\u0005=D!B%\t\u0005\u0004Q\u0005\"CA:\u0011\u0005\u0005\t9AA;\u0003-)g/\u001b3f]\u000e,G%N\u001b\u0011\u000b\u0005\rD+!\u001c\t\u000f\u0005\u0015\u0003\u00021\u0001\u0002zA)\u00111M\u001b\u0002|A)Q(a\u0013\u0002n!1q\r\u0003a\u0001\u0003\u007f\u0002R!a\u00196\u0003[BaA\u001b\u0005A\u0002\u0005}\u0014AB3r\u00136\u0004H.\u0006\u0003\u0002\b\u0006UE\u0003BAE\u0003\u001f#B!a#\u0002\u0018B)\u0011QR\u001b\u0002\u0012:\u0019A%a$\t\u000b\u0019J\u0001\u0019A\u0014\u0011\u000bu\nY%a%\u0011\u0007\u0011\n)\nB\u0003J\u0013\t\u0007!\nC\u0005\u0002\u001a&\t\t\u0011q\u0001\u0002\u001c\u0006YQM^5eK:\u001cW\rJ\u001b7!\u0015\ti\tVAJ\u0003%y'\u000fZ3s\u00136\u0004H.\u0006\u0003\u0002\"\u0006MF\u0003BAR\u0003S#B!!*\u00026B)\u0011qU\u001b\u0002,:\u0019A%!+\t\u000b\u0019R\u0001\u0019A\u0014\u0011\u000bu\ni+!-\n\u0007\u0005=fIA\u0003Pe\u0012,'\u000fE\u0002%\u0003g#Q!\u0013\u0006C\u0002)C\u0011\"a.\u000b\u0003\u0003\u0005\u001d!!/\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$Sg\u000e\t\u0006\u0003O#\u0016\u0011W\u0001\u0015G>dG.Z2uS>tWj\u001c8pS\u0012LU\u000e\u001d7\u0016\t\u0005}\u00161\u001b\u000b\u0005\u0003\u0003\fI\r\u0006\u0003\u0002D\u0006mG\u0003BAc\u0003+\u0004R!a26\u0003\u0017t1\u0001JAe\u0011\u001513\u00021\u0001(!\u0015i\u0014QZAi\u0013\r\tyM\u0012\u0002\u0007\u001b>tw.\u001b3\u0011\u0007\u0011\n\u0019\u000eB\u0003J\u0017\t\u0007!\nC\u0005\u0002X.\t\t\u0011q\u0001\u0002Z\u0006YQM^5eK:\u001cW\rJ\u001b9!\u0015\t9\rVAi\u0011\u001d\tin\u0003a\u0001\u0003?\fQ!Z7qif\u0004R!a26\u0003#\u0004"
)
public final class JavaAutoMacros {
   public static Exprs.Expr collectionMonoidImpl(final Context c, final Exprs.Expr empty, final TypeTags.WeakTypeTag evidence$58) {
      return JavaAutoMacros$.MODULE$.collectionMonoidImpl(c, empty, evidence$58);
   }

   public static Exprs.Expr orderImpl(final Context c, final TypeTags.WeakTypeTag evidence$57) {
      return JavaAutoMacros$.MODULE$.orderImpl(c, evidence$57);
   }

   public static Exprs.Expr eqImpl(final Context c, final TypeTags.WeakTypeTag evidence$56) {
      return JavaAutoMacros$.MODULE$.eqImpl(c, evidence$56);
   }

   public static Exprs.Expr fieldImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$55) {
      return JavaAutoMacros$.MODULE$.fieldImpl(c, z, o, ev, evidence$55);
   }

   public static Exprs.Expr euclideanRingImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$54) {
      return JavaAutoMacros$.MODULE$.euclideanRingImpl(c, z, o, ev, evidence$54);
   }

   public static Exprs.Expr ringImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$53) {
      return JavaAutoMacros$.MODULE$.ringImpl(c, z, o, evidence$53);
   }

   public static Exprs.Expr rngImpl(final Context c, final Exprs.Expr z, final TypeTags.WeakTypeTag evidence$52) {
      return JavaAutoMacros$.MODULE$.rngImpl(c, z, evidence$52);
   }

   public static Exprs.Expr rigImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$51) {
      return JavaAutoMacros$.MODULE$.rigImpl(c, z, o, evidence$51);
   }

   public static Exprs.Expr semiringImpl(final Context c, final TypeTags.WeakTypeTag evidence$50) {
      return JavaAutoMacros$.MODULE$.semiringImpl(c, evidence$50);
   }
}
