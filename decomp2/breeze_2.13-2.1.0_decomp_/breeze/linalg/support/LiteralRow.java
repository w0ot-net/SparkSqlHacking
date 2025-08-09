package breeze.linalg.support;

import scala.;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t]fa\u0002\u0011\"!\u0003\r\n\u0001\u000b\u0005\u0006a\u00011\t!\r\u0005\u0006+\u00021\tAV\u0004\u00061\u0006B\t!\u0017\u0004\u0006A\u0005B\ta\u0017\u0005\u00069\u0012!\t!\u0018\u0005\u0006=\u0012!\u0019a\u0018\u0005\u0006O\u0012!\u0019\u0001\u001b\u0005\u0006c\u0012!\u0019A\u001d\u0005\b\u0003/!A1AA\r\u0011\u001d\tY\u0003\u0002C\u0002\u0003[Aq!!\u0010\u0005\t\u0007\ty\u0004C\u0004\u0002P\u0011!\u0019!!\u0015\t\u000f\u0005\u0005D\u0001b\u0001\u0002d!9\u00111\u000f\u0003\u0005\u0004\u0005U\u0004bBAC\t\u0011\r\u0011q\u0011\u0005\b\u0003/#A1AAM\u0011\u001d\tI\u000b\u0002C\u0002\u0003WCq!a/\u0005\t\u0007\ti\fC\u0004\u0002N\u0012!\u0019!a4\t\u000f\u0005}G\u0001b\u0001\u0002b\"9\u0011\u0011\u001f\u0003\u0005\u0004\u0005M\bb\u0002B\u0002\t\u0011\r!Q\u0001\u0005\b\u0005+!A1\u0001B\f\u0011\u001d\u00119\u0003\u0002C\u0002\u0005SAqA!\u000f\u0005\t\u0007\u0011Y\u0004C\u0004\u0003L\u0011!\u0019A!\u0014\t\u000f\tuC\u0001b\u0001\u0003`!9!q\u000e\u0003\u0005\u0004\tE\u0004b\u0002BA\t\u0011\r!1\u0011\u0005\b\u0005'#A1\u0001BK\u0011\u001d\u0011)\u000b\u0002C\u0002\u0005O\u0013!\u0002T5uKJ\fGNU8x\u0015\t\u00113%A\u0004tkB\u0004xN\u001d;\u000b\u0005\u0011*\u0013A\u00027j]\u0006dwMC\u0001'\u0003\u0019\u0011'/Z3{K\u000e\u0001QcA\u0015;\u0019N\u0011\u0001A\u000b\t\u0003W9j\u0011\u0001\f\u0006\u0002[\u0005)1oY1mC&\u0011q\u0006\f\u0002\u0007\u0003:L(+\u001a4\u0002\u000f\u0019|'/Z1dQV\u0011!g\u0015\u000b\u0004gY\u001a\u0005CA\u00165\u0013\t)DF\u0001\u0003V]&$\b\"B\u001c\u0002\u0001\u0004A\u0014a\u0001:poB\u0011\u0011H\u000f\u0007\u0001\t\u0015Y\u0004A1\u0001=\u0005\u0005\u0011\u0016CA\u001fA!\tYc(\u0003\u0002@Y\t9aj\u001c;iS:<\u0007CA\u0016B\u0013\t\u0011EFA\u0002B]fDQ\u0001R\u0001A\u0002\u0015\u000b!A\u001a8\u0011\u000b-2\u0005j\u0013*\n\u0005\u001dc#!\u0003$v]\u000e$\u0018n\u001c83!\tY\u0013*\u0003\u0002KY\t\u0019\u0011J\u001c;\u0011\u0005ebE!C'\u0001A\u0003\u0005\tQ1\u0001=\u0005\u00051\u0006F\u0001'P!\tY\u0003+\u0003\u0002RY\tY1\u000f]3dS\u0006d\u0017N_3e!\tI4\u000bB\u0003U\u0003\t\u0007AHA\u0001Y\u0003\u0019aWM\\4uQR\u0011\u0001j\u0016\u0005\u0006o\t\u0001\r\u0001O\u0001\u000b\u0019&$XM]1m%><\bC\u0001.\u0005\u001b\u0005\t3C\u0001\u0003+\u0003\u0019a\u0014N\\5u}Q\t\u0011,A\u0003beJ\f\u00170\u0006\u0002aMV\t\u0011\r\u0005\u0003[\u0001\t,\u0007cA\u0016dK&\u0011A\r\f\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003s\u0019$Q!\u0014\u0004C\u0002q\n!\u0001\u001a<\u0016\u0005%\u0004X#\u00016\u0011\ti\u00031n\u001c\t\u0004Y6|W\"A\u0012\n\u00059\u001c#a\u0003#f]N,g+Z2u_J\u0004\"!\u000f9\u0005\u000b5;!\u0019\u0001\u001f\u0002\u0007M,\u0017/F\u0002tsZ$\"\u0001\u001e>\u0011\ti\u0003Q\u000f\u001f\t\u0003sY$Qa\u001e\u0005C\u0002q\u0012\u0011a\u0015\t\u0003se$Q!\u0014\u0005C\u0002qBQa\u001f\u0005A\u0004q\f!!\u001a<\u0011\t-jXo`\u0005\u0003}2\u0012\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\u000b\u0005\u0005\u0011\u0011\u0003=\u000f\t\u0005\r\u0011Q\u0002\b\u0005\u0003\u000b\tY!\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0013bAA\bY\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\n\u0003+\u00111aU3r\u0015\r\ty\u0001L\u0001\tm2KG/\u001a:bYV!\u00111DA\u0011+\t\ti\u0002\u0005\u0004[\u0001\u0005}\u0011q\u0004\t\u0004s\u0005\u0005BAB'\n\u0005\u0004\t\u0019#E\u0002>\u0003K\u00012aKA\u0014\u0013\r\tI\u0003\f\u0002\u0007\u0003:Lh+\u00197\u0002\rQ,\b\u000f\\32+\u0011\ty#a\u000f\u0016\u0005\u0005E\u0002C\u0002.\u0001\u0003g\tI\u0004E\u0003,\u0003k\tI$C\u0002\u000281\u0012a\u0001V;qY\u0016\f\u0004cA\u001d\u0002<\u0011)QJ\u0003b\u0001y\u00051A/\u001e9mKJ*B!!\u0011\u0002NU\u0011\u00111\t\t\u00075\u0002\t)%a\u0013\u0011\u000f-\n9%a\u0013\u0002L%\u0019\u0011\u0011\n\u0017\u0003\rQ+\b\u000f\\33!\rI\u0014Q\n\u0003\u0006\u001b.\u0011\r\u0001P\u0001\u0007iV\u0004H.Z\u001a\u0016\t\u0005M\u0013qL\u000b\u0003\u0003+\u0002bA\u0017\u0001\u0002X\u0005u\u0003#C\u0016\u0002Z\u0005u\u0013QLA/\u0013\r\tY\u0006\f\u0002\u0007)V\u0004H.Z\u001a\u0011\u0007e\ny\u0006B\u0003N\u0019\t\u0007A(\u0001\u0004ukBdW\rN\u000b\u0005\u0003K\n\t(\u0006\u0002\u0002hA1!\fAA5\u0003_\u00022bKA6\u0003_\ny'a\u001c\u0002p%\u0019\u0011Q\u000e\u0017\u0003\rQ+\b\u000f\\35!\rI\u0014\u0011\u000f\u0003\u0006\u001b6\u0011\r\u0001P\u0001\u0007iV\u0004H.Z\u001b\u0016\t\u0005]\u00141Q\u000b\u0003\u0003s\u0002bA\u0017\u0001\u0002|\u0005\u0005\u0005#D\u0016\u0002~\u0005\u0005\u0015\u0011QAA\u0003\u0003\u000b\t)C\u0002\u0002\u00001\u0012a\u0001V;qY\u0016,\u0004cA\u001d\u0002\u0004\u0012)QJ\u0004b\u0001y\u00051A/\u001e9mKZ*B!!#\u0002\u0016V\u0011\u00111\u0012\t\u00075\u0002\ti)a%\u0011\u001f-\ny)a%\u0002\u0014\u0006M\u00151SAJ\u0003'K1!!%-\u0005\u0019!V\u000f\u001d7fmA\u0019\u0011(!&\u0005\u000b5{!\u0019\u0001\u001f\u0002\rQ,\b\u000f\\38+\u0011\tY*a*\u0016\u0005\u0005u\u0005C\u0002.\u0001\u0003?\u000b)\u000bE\t,\u0003C\u000b)+!*\u0002&\u0006\u0015\u0016QUAS\u0003KK1!a)-\u0005\u0019!V\u000f\u001d7foA\u0019\u0011(a*\u0005\u000b5\u0003\"\u0019\u0001\u001f\u0002\rQ,\b\u000f\\39+\u0011\ti+!/\u0016\u0005\u0005=\u0006C\u0002.\u0001\u0003c\u000b9\fE\n,\u0003g\u000b9,a.\u00028\u0006]\u0016qWA\\\u0003o\u000b9,C\u0002\u000262\u0012a\u0001V;qY\u0016D\u0004cA\u001d\u0002:\u0012)Q*\u0005b\u0001y\u00051A/\u001e9mKf*B!a0\u0002LV\u0011\u0011\u0011\u0019\t\u00075\u0002\t\u0019-!3\u0011+-\n)-!3\u0002J\u0006%\u0017\u0011ZAe\u0003\u0013\fI-!3\u0002J&\u0019\u0011q\u0019\u0017\u0003\rQ+\b\u000f\\3:!\rI\u00141\u001a\u0003\u0006\u001bJ\u0011\r\u0001P\u0001\biV\u0004H.Z\u00191+\u0011\t\t.!8\u0016\u0005\u0005M\u0007C\u0002.\u0001\u0003+\fY\u000eE\f,\u0003/\fY.a7\u0002\\\u0006m\u00171\\An\u00037\fY.a7\u0002\\&\u0019\u0011\u0011\u001c\u0017\u0003\u000fQ+\b\u000f\\32aA\u0019\u0011(!8\u0005\u000b5\u001b\"\u0019\u0001\u001f\u0002\u000fQ,\b\u000f\\32cU!\u00111]Ax+\t\t)\u000f\u0005\u0004[\u0001\u0005\u001d\u0018Q\u001e\t\u001aW\u0005%\u0018Q^Aw\u0003[\fi/!<\u0002n\u00065\u0018Q^Aw\u0003[\fi/C\u0002\u0002l2\u0012q\u0001V;qY\u0016\f\u0014\u0007E\u0002:\u0003_$Q!\u0014\u000bC\u0002q\nq\u0001^;qY\u0016\f$'\u0006\u0003\u0002v\n\u0005QCAA|!\u0019Q\u0006!!?\u0002\u0000BY2&a?\u0002\u0000\u0006}\u0018q`A\u0000\u0003\u007f\fy0a@\u0002\u0000\u0006}\u0018q`A\u0000\u0003\u007fL1!!@-\u0005\u001d!V\u000f\u001d7fcI\u00022!\u000fB\u0001\t\u0015iUC1\u0001=\u0003\u001d!X\u000f\u001d7fcM*BAa\u0002\u0003\u0014U\u0011!\u0011\u0002\t\u00075\u0002\u0011YA!\u0005\u0011;-\u0012iA!\u0005\u0003\u0012\tE!\u0011\u0003B\t\u0005#\u0011\tB!\u0005\u0003\u0012\tE!\u0011\u0003B\t\u0005#I1Aa\u0004-\u0005\u001d!V\u000f\u001d7fcM\u00022!\u000fB\n\t\u0015ieC1\u0001=\u0003\u001d!X\u000f\u001d7fcQ*BA!\u0007\u0003&U\u0011!1\u0004\t\u00075\u0002\u0011iBa\t\u0011?-\u0012yBa\t\u0003$\t\r\"1\u0005B\u0012\u0005G\u0011\u0019Ca\t\u0003$\t\r\"1\u0005B\u0012\u0005G\u0011\u0019#C\u0002\u0003\"1\u0012q\u0001V;qY\u0016\fD\u0007E\u0002:\u0005K!Q!T\fC\u0002q\nq\u0001^;qY\u0016\fT'\u0006\u0003\u0003,\t]RC\u0001B\u0017!\u0019Q\u0006Aa\f\u00036A\t3F!\r\u00036\tU\"Q\u0007B\u001b\u0005k\u0011)D!\u000e\u00036\tU\"Q\u0007B\u001b\u0005k\u0011)D!\u000e\u00036%\u0019!1\u0007\u0017\u0003\u000fQ+\b\u000f\\32kA\u0019\u0011Ha\u000e\u0005\u000b5C\"\u0019\u0001\u001f\u0002\u000fQ,\b\u000f\\32mU!!Q\bB%+\t\u0011y\u0004\u0005\u0004[\u0001\t\u0005#q\t\t$W\t\r#q\tB$\u0005\u000f\u00129Ea\u0012\u0003H\t\u001d#q\tB$\u0005\u000f\u00129Ea\u0012\u0003H\t\u001d#q\tB$\u0013\r\u0011)\u0005\f\u0002\b)V\u0004H.Z\u00197!\rI$\u0011\n\u0003\u0006\u001bf\u0011\r\u0001P\u0001\biV\u0004H.Z\u00198+\u0011\u0011yEa\u0017\u0016\u0005\tE\u0003C\u0002.\u0001\u0005'\u0012I\u0006E\u0013,\u0005+\u0012IF!\u0017\u0003Z\te#\u0011\fB-\u00053\u0012IF!\u0017\u0003Z\te#\u0011\fB-\u00053\u0012IF!\u0017\u0003Z%\u0019!q\u000b\u0017\u0003\u000fQ+\b\u000f\\32oA\u0019\u0011Ha\u0017\u0005\u000b5S\"\u0019\u0001\u001f\u0002\u000fQ,\b\u000f\\32qU!!\u0011\rB7+\t\u0011\u0019\u0007\u0005\u0004[\u0001\t\u0015$1\u000e\t(W\t\u001d$1\u000eB6\u0005W\u0012YGa\u001b\u0003l\t-$1\u000eB6\u0005W\u0012YGa\u001b\u0003l\t-$1\u000eB6\u0005W\u0012Y'C\u0002\u0003j1\u0012q\u0001V;qY\u0016\f\u0004\bE\u0002:\u0005[\"Q!T\u000eC\u0002q\nq\u0001^;qY\u0016\f\u0014(\u0006\u0003\u0003t\t}TC\u0001B;!\u0019Q\u0006Aa\u001e\u0003~AI3F!\u001f\u0003~\tu$Q\u0010B?\u0005{\u0012iH! \u0003~\tu$Q\u0010B?\u0005{\u0012iH! \u0003~\tu$Q\u0010B?\u0005{J1Aa\u001f-\u0005\u001d!V\u000f\u001d7fce\u00022!\u000fB@\t\u0015iED1\u0001=\u0003\u001d!X\u000f\u001d7feA*BA!\"\u0003\u0012V\u0011!q\u0011\t\u00075\u0002\u0011IIa$\u0011W-\u0012YIa$\u0003\u0010\n=%q\u0012BH\u0005\u001f\u0013yIa$\u0003\u0010\n=%q\u0012BH\u0005\u001f\u0013yIa$\u0003\u0010\n=%q\u0012BH\u0005\u001fK1A!$-\u0005\u001d!V\u000f\u001d7feA\u00022!\u000fBI\t\u0015iUD1\u0001=\u0003\u001d!X\u000f\u001d7feE*BAa&\u0003$V\u0011!\u0011\u0014\t\u00075\u0002\u0011YJ!)\u0011[-\u0012iJ!)\u0003\"\n\u0005&\u0011\u0015BQ\u0005C\u0013\tK!)\u0003\"\n\u0005&\u0011\u0015BQ\u0005C\u0013\tK!)\u0003\"\n\u0005&\u0011\u0015BQ\u0005C\u0013\t+C\u0002\u0003 2\u0012q\u0001V;qY\u0016\u0014\u0014\u0007E\u0002:\u0005G#Q!\u0014\u0010C\u0002q\nq\u0001^;qY\u0016\u0014$'\u0006\u0003\u0003*\nUVC\u0001BV!\u0019Q\u0006A!,\u00034By3Fa,\u00034\nM&1\u0017BZ\u0005g\u0013\u0019La-\u00034\nM&1\u0017BZ\u0005g\u0013\u0019La-\u00034\nM&1\u0017BZ\u0005g\u0013\u0019La-\u00034&\u0019!\u0011\u0017\u0017\u0003\u000fQ+\b\u000f\\33eA\u0019\u0011H!.\u0005\u000b5{\"\u0019\u0001\u001f"
)
public interface LiteralRow {
   static LiteralRow tuple22() {
      return LiteralRow$.MODULE$.tuple22();
   }

   static LiteralRow tuple21() {
      return LiteralRow$.MODULE$.tuple21();
   }

   static LiteralRow tuple20() {
      return LiteralRow$.MODULE$.tuple20();
   }

   static LiteralRow tuple19() {
      return LiteralRow$.MODULE$.tuple19();
   }

   static LiteralRow tuple18() {
      return LiteralRow$.MODULE$.tuple18();
   }

   static LiteralRow tuple17() {
      return LiteralRow$.MODULE$.tuple17();
   }

   static LiteralRow tuple16() {
      return LiteralRow$.MODULE$.tuple16();
   }

   static LiteralRow tuple15() {
      return LiteralRow$.MODULE$.tuple15();
   }

   static LiteralRow tuple14() {
      return LiteralRow$.MODULE$.tuple14();
   }

   static LiteralRow tuple13() {
      return LiteralRow$.MODULE$.tuple13();
   }

   static LiteralRow tuple12() {
      return LiteralRow$.MODULE$.tuple12();
   }

   static LiteralRow tuple11() {
      return LiteralRow$.MODULE$.tuple11();
   }

   static LiteralRow tuple10() {
      return LiteralRow$.MODULE$.tuple10();
   }

   static LiteralRow tuple9() {
      return LiteralRow$.MODULE$.tuple9();
   }

   static LiteralRow tuple8() {
      return LiteralRow$.MODULE$.tuple8();
   }

   static LiteralRow tuple7() {
      return LiteralRow$.MODULE$.tuple7();
   }

   static LiteralRow tuple6() {
      return LiteralRow$.MODULE$.tuple6();
   }

   static LiteralRow tuple5() {
      return LiteralRow$.MODULE$.tuple5();
   }

   static LiteralRow tuple4() {
      return LiteralRow$.MODULE$.tuple4();
   }

   static LiteralRow tuple3() {
      return LiteralRow$.MODULE$.tuple3();
   }

   static LiteralRow tuple2() {
      return LiteralRow$.MODULE$.tuple2();
   }

   static LiteralRow tuple1() {
      return LiteralRow$.MODULE$.tuple1();
   }

   static LiteralRow vLiteral() {
      return LiteralRow$.MODULE$.vLiteral();
   }

   static LiteralRow seq(final .less.colon.less ev) {
      return LiteralRow$.MODULE$.seq(ev);
   }

   static LiteralRow dv() {
      return LiteralRow$.MODULE$.dv();
   }

   static LiteralRow array() {
      return LiteralRow$.MODULE$.array();
   }

   void foreach(final Object row, final Function2 fn);

   int length(final Object row);

   // $FF: synthetic method
   static void foreach$mcZ$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcZ$sp(row, fn);
   }

   default void foreach$mcZ$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcB$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcB$sp(row, fn);
   }

   default void foreach$mcB$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcC$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcC$sp(row, fn);
   }

   default void foreach$mcC$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcD$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcD$sp(row, fn);
   }

   default void foreach$mcD$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcF$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcF$sp(row, fn);
   }

   default void foreach$mcF$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcI$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcI$sp(row, fn);
   }

   default void foreach$mcI$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcJ$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcJ$sp(row, fn);
   }

   default void foreach$mcJ$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcS$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcS$sp(row, fn);
   }

   default void foreach$mcS$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }

   // $FF: synthetic method
   static void foreach$mcV$sp$(final LiteralRow $this, final Object row, final Function2 fn) {
      $this.foreach$mcV$sp(row, fn);
   }

   default void foreach$mcV$sp(final Object row, final Function2 fn) {
      this.foreach(row, fn);
   }
}
