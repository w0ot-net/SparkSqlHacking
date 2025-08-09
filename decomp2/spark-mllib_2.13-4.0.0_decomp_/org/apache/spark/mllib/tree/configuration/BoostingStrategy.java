package org.apache.spark.mllib.tree.configuration;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.tree.loss.Loss;
import scala.Enumeration;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015a\u0001B\u001d;\u0001\u001eC\u0001\"\u0018\u0001\u0003\u0012\u0004%\tA\u0018\u0005\tY\u0002\u0011\t\u0019!C\u0001[\"AA\u000f\u0001B\tB\u0003&q\fC\u0003~\u0001\u0011\u0005a\u0010C\u0004\u0002\u0004\u0001!\t!!\u0002\t\u0015\u00055\u0001A!e\u0001\n\u0003\ty\u0001\u0003\u0006\u0002\u001e\u0001\u0011\t\u0019!C\u0001\u0003?A!\"!\n\u0001\u0005#\u0005\u000b\u0015BA\t\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!a\r\u0001\t\u0003\t)\u0004\u0003\u0006\u0002>\u0001\u0011\t\u001a!C\u0001\u0003\u007fA!\"!\u0013\u0001\u0005\u0003\u0007I\u0011AA&\u0011)\t\t\u0006\u0001B\tB\u0003&\u0011\u0011\t\u0005\b\u0003/\u0002A\u0011AA-\u0011\u001d\ty\u0006\u0001C\u0001\u0003CB!\"!\u001b\u0001\u0005#\u0007I\u0011AA6\u0011)\t)\b\u0001BA\u0002\u0013\u0005\u0011q\u000f\u0005\u000b\u0003{\u0002!\u0011#Q!\n\u00055\u0004bBAB\u0001\u0011\u0005\u0011Q\u0011\u0005\b\u0003\u0017\u0003A\u0011AAG\u0011)\t)\n\u0001BI\u0002\u0013\u0005\u00111\u000e\u0005\u000b\u0003;\u0003!\u00111A\u0005\u0002\u0005}\u0005BCAS\u0001\tE\t\u0015)\u0003\u0002n!9\u00111\u0016\u0001\u0005\u0002\u0005\u0015\u0005bBAY\u0001\u0011\u0005\u00111\u0017\u0005\b\u0003w\u0003A\u0011AA_\u0011!\t9\u000e\u0001C\u0001\u0001\u0006e\u0007\"CAn\u0001\u0005\u0005I\u0011AAo\u0011%\tI\u000fAI\u0001\n\u0003\tY\u000fC\u0005\u0002\u0000\u0002\t\n\u0011\"\u0001\u0003\u0002!I!Q\u0001\u0001\u0012\u0002\u0013\u0005!q\u0001\u0005\n\u0005\u0017\u0001\u0011\u0013!C\u0001\u0005\u001bA\u0011B!\u0005\u0001#\u0003%\tA!\u0004\t\u0013\tM\u0001!!A\u0005B\tU\u0001\"\u0003B\u0014\u0001\u0005\u0005I\u0011AA \u0011%\u0011I\u0003AA\u0001\n\u0003\u0011Y\u0003C\u0005\u00036\u0001\t\t\u0011\"\u0011\u00038!I!Q\t\u0001\u0002\u0002\u0013\u0005!q\t\u0005\n\u0005#\u0002\u0011\u0011!C!\u0005'B\u0011Ba\u0016\u0001\u0003\u0003%\t%!\u0017\t\u0013\te\u0003!!A\u0005B\tm\u0003\"\u0003B/\u0001\u0005\u0005I\u0011\tB0\u000f\u001d\u0011)G\u000fE\u0001\u0005O2a!\u000f\u001e\t\u0002\t%\u0004bBA^Y\u0011\u0005!Q\u000f\u0005\b\u0005obC\u0011\u0001B=\u0011\u001d\u00119\b\fC\u0001\u0005\u001fC\u0011Ba0-\u0003\u0003%\tI!1\t\u0013\t]G&%A\u0005\u0002\t\u001d\u0001\"\u0003BmYE\u0005I\u0011\u0001B\u0007\u0011%\u0011Y\u000eLI\u0001\n\u0003\u0011i\u0001C\u0005\u0003^2\n\t\u0011\"!\u0003`\"I!\u0011\u001f\u0017\u0012\u0002\u0013\u0005!q\u0001\u0005\n\u0005gd\u0013\u0013!C\u0001\u0005\u001bA\u0011B!>-#\u0003%\tA!\u0004\t\u0013\t]H&!A\u0005\n\te(\u0001\u0005\"p_N$\u0018N\\4TiJ\fG/Z4z\u0015\tYD(A\u0007d_:4\u0017nZ;sCRLwN\u001c\u0006\u0003{y\nA\u0001\u001e:fK*\u0011q\bQ\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u0003\n\u000bQa\u001d9be.T!a\u0011#\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0015aA8sO\u000e\u00011\u0003\u0002\u0001I\u001dj\u0003\"!\u0013'\u000e\u0003)S\u0011aS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001b*\u0013a!\u00118z%\u00164\u0007CA(X\u001d\t\u0001VK\u0004\u0002R)6\t!K\u0003\u0002T\r\u00061AH]8pizJ\u0011aS\u0005\u0003-*\u000bq\u0001]1dW\u0006<W-\u0003\u0002Y3\na1+\u001a:jC2L'0\u00192mK*\u0011aK\u0013\t\u0003\u0013nK!\u0001\u0018&\u0003\u000fA\u0013x\u000eZ;di\u0006aAO]3f'R\u0014\u0018\r^3hsV\tq\f\u0005\u0002aC6\t!(\u0003\u0002cu\tA1\u000b\u001e:bi\u0016<\u0017\u0010K\u0002\u0002I*\u0004\"!\u001a5\u000e\u0003\u0019T!a\u001a!\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002jM\n)1+\u001b8dK\u0006\n1.A\u00032]Ir\u0003'\u0001\tue\u0016,7\u000b\u001e:bi\u0016<\u0017p\u0018\u0013fcR\u0011a.\u001d\t\u0003\u0013>L!\u0001\u001d&\u0003\tUs\u0017\u000e\u001e\u0005\be\n\t\t\u00111\u0001`\u0003\rAH%\r\u0015\u0004\u0005\u0011T\u0017!\u0004;sK\u0016\u001cFO]1uK\u001eL\b\u0005K\u0002\u0004I*D#aA<\u0011\u0005a\\X\"A=\u000b\u0005iT\u0015!\u00022fC:\u001c\u0018B\u0001?z\u00051\u0011U-\u00198Qe>\u0004XM\u001d;z\u0003=9W\r\u001e+sK\u0016\u001cFO]1uK\u001eLH#A0)\u0007\u0011!'\u000e\u000b\u0002\u0005o\u0006y1/\u001a;Ue\u0016,7\u000b\u001e:bi\u0016<\u0017\u0010F\u0002o\u0003\u000fAqA]\u0003\u0002\u0002\u0003\u0007q\fK\u0002\u0006I*D#!B<\u0002\t1|7o]\u000b\u0003\u0003#\u0001B!a\u0005\u0002\u00185\u0011\u0011Q\u0003\u0006\u0004\u0003\u001ba\u0014\u0002BA\r\u0003+\u0011A\u0001T8tg\"\u001aa\u0001\u001a6\u0002\u00111|7o]0%KF$2A\\A\u0011\u0011!\u0011x!!AA\u0002\u0005E\u0001fA\u0004eU\u0006)An\\:tA!\u001a\u0001\u0002\u001a6)\u0005!9\u0018aB4fi2{7o\u001d\u000b\u0003\u0003#A3!\u00033kQ\tIq/A\u0004tKRdun]:\u0015\u00079\f9\u0004\u0003\u0005s\u0015\u0005\u0005\t\u0019AA\tQ\rQAM\u001b\u0015\u0003\u0015]\fQB\\;n\u0013R,'/\u0019;j_:\u001cXCAA!!\rI\u00151I\u0005\u0004\u0003\u000bR%aA%oi\"\u001a1\u0002\u001a6\u0002#9,X.\u0013;fe\u0006$\u0018n\u001c8t?\u0012*\u0017\u000fF\u0002o\u0003\u001bB\u0001B\u001d\u0007\u0002\u0002\u0003\u0007\u0011\u0011\t\u0015\u0004\u0019\u0011T\u0017A\u00048v[&#XM]1uS>t7\u000f\t\u0015\u0004\u001b\u0011T\u0007FA\u0007x\u0003A9W\r\u001e(v[&#XM]1uS>t7\u000f\u0006\u0002\u0002B!\u001aa\u0002\u001a6)\u000599\u0018\u0001E:fi:+X.\u0013;fe\u0006$\u0018n\u001c8t)\rq\u00171\r\u0005\te>\t\t\u00111\u0001\u0002B!\u001aq\u0002\u001a6)\u0005=9\u0018\u0001\u00047fCJt\u0017N\\4SCR,WCAA7!\rI\u0015qN\u0005\u0004\u0003cR%A\u0002#pk\ndW\rK\u0002\u0011I*\f\u0001\u0003\\3be:Lgn\u001a*bi\u0016|F%Z9\u0015\u00079\fI\b\u0003\u0005s#\u0005\u0005\t\u0019AA7Q\r\tBM[\u0001\u000eY\u0016\f'O\\5oOJ\u000bG/\u001a\u0011)\u0007I!'\u000e\u000b\u0002\u0013o\u0006yq-\u001a;MK\u0006\u0014h.\u001b8h%\u0006$X\r\u0006\u0002\u0002n!\u001a1\u0003\u001a6)\u0005M9\u0018aD:fi2+\u0017M\u001d8j]\u001e\u0014\u0016\r^3\u0015\u00079\fy\t\u0003\u0005s)\u0005\u0005\t\u0019AA7Q\r!BM\u001b\u0015\u0003)]\fQB^1mS\u0012\fG/[8o)>d\u0007\u0006B\u000be\u00033\u000b#!a'\u0002\u000bErCG\f\u0019\u0002#Y\fG.\u001b3bi&|g\u000eV8m?\u0012*\u0017\u000fF\u0002o\u0003CC\u0001B\u001d\f\u0002\u0002\u0003\u0007\u0011Q\u000e\u0015\u0005-\u0011\fI*\u0001\bwC2LG-\u0019;j_:$v\u000e\u001c\u0011)\t]!\u0017\u0011\u0014\u0015\u0003/]\f\u0001cZ3u-\u0006d\u0017\u000eZ1uS>tGk\u001c7)\ta!\u0017\u0011\u0014\u0015\u00031]\f\u0001c]3u-\u0006d\u0017\u000eZ1uS>tGk\u001c7\u0015\u00079\f)\f\u0003\u0005s3\u0005\u0005\t\u0019AA7Q\u0011IB-!')\u0005e9\u0018A\u0002\u001fj]&$h\b\u0006\u0007\u0002@\u0006\u0005\u0017QYAe\u0003\u001b\f\t\u000e\u0005\u0002a\u0001!)QL\u0007a\u0001?\"\"\u0011\u0011\u00193k\u0011\u001d\tiA\u0007a\u0001\u0003#AC!!2eU\"I\u0011Q\b\u000e\u0011\u0002\u0003\u0007\u0011\u0011\t\u0015\u0005\u0003\u0013$'\u000eC\u0005\u0002ji\u0001\n\u00111\u0001\u0002n!\"\u0011Q\u001a3k\u0011%\t)J\u0007I\u0001\u0002\u0004\ti\u0007K\u0003\u0002R\u0012\fI\n\u000b\u0003\u001bI\u0006e\u0015aC1tg\u0016\u0014HOV1mS\u0012$\u0012A\\\u0001\u0005G>\u0004\u0018\u0010\u0006\u0007\u0002@\u0006}\u0017\u0011]Ar\u0003K\f9\u000fC\u0004^9A\u0005\t\u0019A0\t\u0013\u00055A\u0004%AA\u0002\u0005E\u0001\"CA\u001f9A\u0005\t\u0019AA!\u0011%\tI\u0007\bI\u0001\u0002\u0004\ti\u0007C\u0005\u0002\u0016r\u0001\n\u00111\u0001\u0002n\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAAwU\ry\u0016q^\u0016\u0003\u0003c\u0004B!a=\u0002|6\u0011\u0011Q\u001f\u0006\u0005\u0003o\fI0A\u0005v]\u000eDWmY6fI*\u0011qMS\u0005\u0005\u0003{\f)PA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u0004)\"\u0011\u0011CAx\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"A!\u0003+\t\u0005\u0005\u0013q^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\u0011yA\u000b\u0003\u0002n\u0005=\u0018AD2paf$C-\u001a4bk2$H%N\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t]\u0001\u0003\u0002B\r\u0005Gi!Aa\u0007\u000b\t\tu!qD\u0001\u0005Y\u0006twM\u0003\u0002\u0003\"\u0005!!.\u0019<b\u0013\u0011\u0011)Ca\u0007\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$BA!\f\u00034A\u0019\u0011Ja\f\n\u0007\tE\"JA\u0002B]fD\u0001B\u001d\u0013\u0002\u0002\u0003\u0007\u0011\u0011I\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!\u0011\b\t\u0007\u0005w\u0011\tE!\f\u000e\u0005\tu\"b\u0001B \u0015\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t\r#Q\b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003J\t=\u0003cA%\u0003L%\u0019!Q\n&\u0003\u000f\t{w\u000e\\3b]\"A!OJA\u0001\u0002\u0004\u0011i#\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B\f\u0005+B\u0001B]\u0014\u0002\u0002\u0003\u0007\u0011\u0011I\u0001\tQ\u0006\u001c\bnQ8eK\u0006AAo\\*ue&tw\r\u0006\u0002\u0003\u0018\u00051Q-];bYN$BA!\u0013\u0003b!A!OKA\u0001\u0002\u0004\u0011i\u0003K\u0002\u0001I*\f\u0001CQ8pgRLgnZ*ue\u0006$XmZ=\u0011\u0005\u0001d3\u0003\u0002\u0017I\u0005W\u0002BA!\u001c\u0003t5\u0011!q\u000e\u0006\u0005\u0005c\u0012y\"\u0001\u0002j_&\u0019\u0001La\u001c\u0015\u0005\t\u001d\u0014!\u00043fM\u0006,H\u000e\u001e)be\u0006l7\u000f\u0006\u0003\u0002@\nm\u0004b\u0002B?]\u0001\u0007!qP\u0001\u0005C2<w\u000e\u0005\u0003\u0003\u0002\n%e\u0002\u0002BB\u0005\u000b\u0003\"!\u0015&\n\u0007\t\u001d%*\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005K\u0011YIC\u0002\u0003\b*C3A\f3k)\u0011\tyL!%\t\u000f\tut\u00061\u0001\u0003\u0014B!!Q\u0013BZ\u001d\u0011\u00119Ja,\u000f\t\te%Q\u0016\b\u0005\u00057\u0013YK\u0004\u0003\u0003\u001e\n%f\u0002\u0002BP\u0005OsAA!)\u0003&:\u0019\u0011Ka)\n\u0003\u0015K!a\u0011#\n\u0005\u0005\u0013\u0015BA A\u0013\tid(\u0003\u0002<y%\u0019!\u0011\u0017\u001e\u0002\t\u0005cwm\\\u0005\u0005\u0005k\u00139L\u0001\u0003BY\u001e|'b\u0001BYu!\"q\u0006\u001aB^C\t\u0011i,A\u00032]Mr\u0003'A\u0003baBd\u0017\u0010\u0006\u0007\u0002@\n\r'q\u0019Bf\u0005\u001f\u0014\u0019\u000eC\u0003^a\u0001\u0007q\f\u000b\u0003\u0003D\u0012T\u0007bBA\u0007a\u0001\u0007\u0011\u0011\u0003\u0015\u0005\u0005\u000f$'\u000eC\u0005\u0002>A\u0002\n\u00111\u0001\u0002B!\"!1\u001a3k\u0011%\tI\u0007\rI\u0001\u0002\u0004\ti\u0007\u000b\u0003\u0003P\u0012T\u0007\"CAKaA\u0005\t\u0019AA7Q\u0015\u0011\u0019\u000eZAM\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u001a\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIU\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003b\n5\b#B%\u0003d\n\u001d\u0018b\u0001Bs\u0015\n1q\n\u001d;j_:\u0004B\"\u0013Bu?\u0006E\u0011\u0011IA7\u0003[J1Aa;K\u0005\u0019!V\u000f\u001d7fk!I!q\u001e\u001b\u0002\u0002\u0003\u0007\u0011qX\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tm\b\u0003\u0002B\r\u0005{LAAa@\u0003\u001c\t1qJ\u00196fGRD3\u0001\f3kQ\rYCM\u001b"
)
public class BoostingStrategy implements Serializable, Product {
   private Strategy treeStrategy;
   private Loss loss;
   private int numIterations;
   private double learningRate;
   private double validationTol;

   public static double $lessinit$greater$default$5() {
      return BoostingStrategy$.MODULE$.$lessinit$greater$default$5();
   }

   public static double $lessinit$greater$default$4() {
      return BoostingStrategy$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return BoostingStrategy$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final BoostingStrategy x$0) {
      return BoostingStrategy$.MODULE$.unapply(x$0);
   }

   public static double apply$default$5() {
      return BoostingStrategy$.MODULE$.apply$default$5();
   }

   public static double apply$default$4() {
      return BoostingStrategy$.MODULE$.apply$default$4();
   }

   public static int apply$default$3() {
      return BoostingStrategy$.MODULE$.apply$default$3();
   }

   public static BoostingStrategy apply(final Strategy treeStrategy, final Loss loss, final int numIterations, final double learningRate, final double validationTol) {
      return BoostingStrategy$.MODULE$.apply(treeStrategy, loss, numIterations, learningRate, validationTol);
   }

   public static BoostingStrategy defaultParams(final Enumeration.Value algo) {
      return BoostingStrategy$.MODULE$.defaultParams(algo);
   }

   public static BoostingStrategy defaultParams(final String algo) {
      return BoostingStrategy$.MODULE$.defaultParams(algo);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Strategy treeStrategy() {
      return this.treeStrategy;
   }

   public void treeStrategy_$eq(final Strategy x$1) {
      this.treeStrategy = x$1;
   }

   public Loss loss() {
      return this.loss;
   }

   public void loss_$eq(final Loss x$1) {
      this.loss = x$1;
   }

   public int numIterations() {
      return this.numIterations;
   }

   public void numIterations_$eq(final int x$1) {
      this.numIterations = x$1;
   }

   public double learningRate() {
      return this.learningRate;
   }

   public void learningRate_$eq(final double x$1) {
      this.learningRate = x$1;
   }

   public double validationTol() {
      return this.validationTol;
   }

   public void validationTol_$eq(final double x$1) {
      this.validationTol = x$1;
   }

   public void assertValid() {
      label47: {
         label48: {
            Enumeration.Value var2 = this.treeStrategy().algo();
            Enumeration.Value var10000 = Algo$.MODULE$.Classification();
            if (var10000 == null) {
               if (var2 == null) {
                  break label48;
               }
            } else if (var10000.equals(var2)) {
               break label48;
            }

            var10000 = Algo$.MODULE$.Regression();
            if (var10000 == null) {
               if (var2 != null) {
                  throw new IllegalArgumentException("BoostingStrategy given invalid algo parameter: " + this.treeStrategy().algo() + ".  Valid settings are: Classification, Regression.");
               }
            } else if (!var10000.equals(var2)) {
               throw new IllegalArgumentException("BoostingStrategy given invalid algo parameter: " + this.treeStrategy().algo() + ".  Valid settings are: Classification, Regression.");
            }

            BoxedUnit var6 = BoxedUnit.UNIT;
            break label47;
         }

         .MODULE$.require(this.treeStrategy().numClasses() == 2, () -> "Only binary classification is supported for boosting.");
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      .MODULE$.require(this.learningRate() > (double)0 && this.learningRate() <= (double)1, () -> "Learning rate should be in range (0, 1]. Provided learning rate is " + this.learningRate() + ".");
   }

   public BoostingStrategy copy(final Strategy treeStrategy, final Loss loss, final int numIterations, final double learningRate, final double validationTol) {
      return new BoostingStrategy(treeStrategy, loss, numIterations, learningRate, validationTol);
   }

   public Strategy copy$default$1() {
      return this.treeStrategy();
   }

   public Loss copy$default$2() {
      return this.loss();
   }

   public int copy$default$3() {
      return this.numIterations();
   }

   public double copy$default$4() {
      return this.learningRate();
   }

   public double copy$default$5() {
      return this.validationTol();
   }

   public double getLearningRate() {
      return this.learningRate();
   }

   public Loss getLoss() {
      return this.loss();
   }

   public int getNumIterations() {
      return this.numIterations();
   }

   public Strategy getTreeStrategy() {
      return this.treeStrategy();
   }

   public double getValidationTol() {
      return this.validationTol();
   }

   public void setLearningRate(final double x$1) {
      this.learningRate_$eq(x$1);
   }

   public void setLoss(final Loss x$1) {
      this.loss_$eq(x$1);
   }

   public void setNumIterations(final int x$1) {
      this.numIterations_$eq(x$1);
   }

   public void setTreeStrategy(final Strategy x$1) {
      this.treeStrategy_$eq(x$1);
   }

   public void setValidationTol(final double x$1) {
      this.validationTol_$eq(x$1);
   }

   public String productPrefix() {
      return "BoostingStrategy";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.treeStrategy();
         }
         case 1 -> {
            return this.loss();
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.numIterations());
         }
         case 3 -> {
            return BoxesRunTime.boxToDouble(this.learningRate());
         }
         case 4 -> {
            return BoxesRunTime.boxToDouble(this.validationTol());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof BoostingStrategy;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "treeStrategy";
         }
         case 1 -> {
            return "loss";
         }
         case 2 -> {
            return "numIterations";
         }
         case 3 -> {
            return "learningRate";
         }
         case 4 -> {
            return "validationTol";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.treeStrategy()));
      var1 = Statics.mix(var1, Statics.anyHash(this.loss()));
      var1 = Statics.mix(var1, this.numIterations());
      var1 = Statics.mix(var1, Statics.doubleHash(this.learningRate()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.validationTol()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof BoostingStrategy) {
               BoostingStrategy var4 = (BoostingStrategy)x$1;
               if (this.numIterations() == var4.numIterations() && this.learningRate() == var4.learningRate() && this.validationTol() == var4.validationTol()) {
                  label60: {
                     Strategy var10000 = this.treeStrategy();
                     Strategy var5 = var4.treeStrategy();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     Loss var7 = this.loss();
                     Loss var6 = var4.loss();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var7.equals(var6)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public BoostingStrategy(final Strategy treeStrategy, final Loss loss, final int numIterations, final double learningRate, final double validationTol) {
      this.treeStrategy = treeStrategy;
      this.loss = loss;
      this.numIterations = numIterations;
      this.learningRate = learningRate;
      this.validationTol = validationTol;
      super();
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
