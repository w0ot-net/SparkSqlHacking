package scala.concurrent.impl;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.concurrent.AwaitPermission$;
import scala.concurrent.BlockContext$;
import scala.concurrent.Future;
import scala.concurrent.package$;
import scala.reflect.ScalaSignature;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\r=qA\u0002\u0012$\u0011\u00039\u0013F\u0002\u0004,G!\u0005q\u0005\f\u0005\u0006c\u0005!\ta\r\u0004\u0005i\u0005\u0011Q\u0007\u0003\u0005W\u0007\t\u0015\r\u0011\"\u0001X\u0011!a6A!A!\u0002\u0013A\u0006\"B\u0019\u0004\t\u0003i\u0006\"B1\u0004\t\u0003\u0012\u0007\"B3\u0004\t\u00032\u0007\"B?\u0004\t\u0003r\bbBA\u000f\u0007\u0011\u0005\u0013q\u0004\u0005\b\u0003S\u0019A\u0011IA\u0016\u0011\u001d\tyg\u0001C!\u0003cBq!a(\u0004\t\u0003\n\t\u000bC\u0004\u00022\u000e!\t%a-\t\u000f\u0005]7\u0001\"\u0011\u0002Z\"9\u00111_\u0002\u0005B\u0005U\bb\u0002B\u0003\u0007\u0011\u0005#q\u0001\u0005\b\u0005O\u0019A\u0011\tB\u0015\u0011\u001d\u00119f\u0001C!\u00053BqAa\u001e\u0004\t\u0003\u0012I\bC\u0004\u0003\b\u000e!\tE!#\t\u000f\t-5\u0001\"\u0011\u0003\u000e\"9!1S\u0002\u0005B\tU\u0005b\u0002BN\u0007\u0011\u0005#Q\u0014\u0005\b\u00057\u001bA\u0011\tBP\u0011\u001d\u0011)l\u0001C!\u0005oCaB!3\u0004!\u0003\r\t\u0011!C\u0005\u0005;\u0013Y\r\u0003\b\u0003J\u000e\u0001\n1!A\u0001\n\u0013\u0011iMa6\u0007\r\te\u0017A\u0001Bn\u0011%1VD!b\u0001\n\u0003\u0011Y\u0010C\u0005];\t\u0005\t\u0015!\u0003\u0003~\"1\u0011'\bC\u0001\u0005\u007fDa!Y\u000f\u0005B\r\u0015\u0011\u0001\u0006$viV\u0014XmQ8om\u0016\u0014H/\u001a:t\u00136\u0004HN\u0003\u0002%K\u0005!\u0011.\u001c9m\u0015\t1s%\u0001\u0006d_:\u001cWO\u001d:f]RT\u0011\u0001K\u0001\u0006g\u000e\fG.\u0019\t\u0003U\u0005i\u0011a\t\u0002\u0015\rV$XO]3D_:4XM\u001d;feNLU\u000e\u001d7\u0014\u0005\u0005i\u0003C\u0001\u00180\u001b\u00059\u0013B\u0001\u0019(\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001*\u0005\t\u0019e)\u0006\u00027\u0005N\u00191aN&\u0011\u0007ar\u0004)D\u0001:\u0015\t1#H\u0003\u0002<y\u0005!Q\u000f^5m\u0015\u0005i\u0014\u0001\u00026bm\u0006L!aP\u001d\u0003#\r{W\u000e\u001d7fi\u0006\u0014G.\u001a$viV\u0014X\r\u0005\u0002B\u00052\u0001A!B\"\u0004\u0005\u0004!%!\u0001+\u0012\u0005\u0015C\u0005C\u0001\u0018G\u0013\t9uEA\u0004O_RD\u0017N\\4\u0011\u00059J\u0015B\u0001&(\u0005\r\te.\u001f\t\u0005]1s5+\u0003\u0002NO\tIa)\u001e8di&|g.\r\t\u0004\u001fF\u0003U\"\u0001)\u000b\u0005m:\u0013B\u0001*Q\u0005\r!&/\u001f\t\u0003]QK!!V\u0014\u0003\tUs\u0017\u000e^\u0001\boJ\f\u0007\u000f]3e+\u0005A\u0006cA-[\u00016\tQ%\u0003\u0002\\K\t1a)\u001e;ve\u0016\f\u0001b\u001e:baB,G\r\t\u000b\u0003=\u0002\u00042aX\u0002A\u001b\u0005\t\u0001\"\u0002,\u0007\u0001\u0004A\u0016!B1qa2LHCA*d\u0011\u0015!w\u00011\u0001O\u0003\u0005!\u0018!\u0003;iK:\f\u0005\u000f\u001d7z+\t9'\u000e\u0006\u0002iYB\u0019\u0001HP5\u0011\u0005\u0005SG!B6\t\u0005\u0004!%!A+\t\u000b5D\u0001\u0019\u00018\u0002\u0005\u0019t\u0007gA8wuB!\u0001o];z\u001b\u0005\t(B\u0001:;\u0003!1WO\\2uS>t\u0017B\u0001;r\u0005!1UO\\2uS>t\u0007CA!w\t%9H.!A\u0001\u0002\u000b\u0005\u0001PA\u0002`IE\n\"\u0001\u0011%\u0011\u0005\u0005SH!C>m\u0003\u0003\u0005\tQ!\u0001}\u0005\ryFEM\t\u0003\u000b&\f!\u0002\u001e5f]\u0006\u001b7-\u001a9u)\ry\u0018Q\u0002\t\u0005qy\n\t\u0001\u0005\u0003\u0002\u0004\u0005%QBAA\u0003\u0015\r\t9\u0001P\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\f\u0005\u0015!\u0001\u0002,pS\u0012Da!\\\u0005A\u0002\u0005=\u0001\u0007BA\t\u00033\u0001R\u0001]A\n\u0003/I1!!\u0006r\u0005!\u0019uN\\:v[\u0016\u0014\bcA!\u0002\u001a\u0011Y\u00111DA\u0007\u0003\u0003\u0005\tQ!\u0001y\u0005\ryFeM\u0001\bi\",gNU;o)\ry\u0018\u0011\u0005\u0005\u0007[*\u0001\r!a\t\u0011\t\u0005\r\u0011QE\u0005\u0005\u0003O\t)A\u0001\u0005Sk:t\u0017M\u00197f\u0003-!\b.\u001a8D_6\u0014\u0017N\\3\u0016\r\u00055\u0012QJA\u001a)\u0019\ty#a\u000e\u0002PA!\u0001HPA\u0019!\r\t\u00151\u0007\u0003\u0007\u0003kY!\u0019\u0001#\u0003\u0003YCq!!\u000f\f\u0001\u0004\tY$\u0001\u0002dgB\"\u0011QHA#!\u0015A\u0014qHA\"\u0013\r\t\t%\u000f\u0002\u0010\u0007>l\u0007\u000f\\3uS>t7\u000b^1hKB\u0019\u0011)!\u0012\u0005\u0019\u0005\u001d\u0013qGA\u0001\u0002\u0003\u0015\t!!\u0013\u0003\u0007}#C'E\u0002F\u0003\u0017\u00022!QA'\t\u0015Y7B1\u0001E\u0011\u0019i7\u00021\u0001\u0002RAB\u00111KA.\u0003C\nI\u0007E\u0005q\u0003+\nI&a\u0018\u0002h%\u0019\u0011qK9\u0003\u0015\tKg)\u001e8di&|g\u000eE\u0002B\u00037\"1\"!\u0018\u0002P\u0005\u0005\t\u0011!B\u0001q\n\u0019q\fJ\u001b\u0011\u0007\u0005\u000b\t\u0007\u0002\u0007\u0002d\u0005=\u0013\u0011!A\u0001\u0006\u0003\t)GA\u0002`IY\n2!a\u0013I!\r\t\u0015\u0011\u000e\u0003\r\u0003W\ny%!A\u0001\u0002\u000b\u0005\u0011Q\u000e\u0002\u0004?\u0012:\u0014cA#\u00022\u0005qA\u000f[3o\u0003\u000e\u001cW\r\u001d;C_RDW\u0003BA:\u0003\u000b#Ra`A;\u0003\u000fCq!!\u000f\r\u0001\u0004\t9\b\r\u0003\u0002z\u0005u\u0004#\u0002\u001d\u0002@\u0005m\u0004cA!\u0002~\u0011a\u0011qPA;\u0003\u0003\u0005\tQ!\u0001\u0002\u0002\n\u0019q\f\n\u001d\u0012\u0007\u0015\u000b\u0019\tE\u0002B\u0003\u000b#Qa\u001b\u0007C\u0002\u0011Ca!\u001c\u0007A\u0002\u0005%\u0005GBAF\u0003'\u000bI\nE\u0004q\u0003\u001b\u000b\t*a&\n\u0007\u0005=\u0015O\u0001\u0006CS\u000e{gn];nKJ\u00042!QAJ\t-\t)*a\"\u0002\u0002\u0003\u0005)\u0011\u0001=\u0003\u0007}#\u0013\bE\u0002B\u00033#A\"a'\u0002\b\u0006\u0005\t\u0011!B\u0001\u0003;\u0013Aa\u0018\u00132aE\u0019\u00111\u0011%\u0002\u0019I,h.\u00114uKJ\u0014u\u000e\u001e5\u0015\u000b}\f\u0019+a,\t\u000f\u0005eR\u00021\u0001\u0002&B\"\u0011qUAV!\u0015A\u0014qHAU!\r\t\u00151\u0016\u0003\f\u0003[\u000b\u0019+!A\u0001\u0002\u000b\u0005AI\u0001\u0003`IE\n\u0004BB7\u000e\u0001\u0004\t\u0019#A\u0007baBd\u0017\u0010V8FSRDWM]\u000b\u0005\u0003k\u000bY\f\u0006\u0004\u00028\u0006u\u00161\u001a\t\u0005qy\nI\fE\u0002B\u0003w#Qa\u001b\bC\u0002\u0011Cq!!\u000f\u000f\u0001\u0004\ty\f\r\u0003\u0002B\u0006\u0015\u0007#\u0002\u001d\u0002@\u0005\r\u0007cA!\u0002F\u0012a\u0011qYA_\u0003\u0003\u0005\tQ!\u0001\u0002J\n!q\fJ\u00193#\t)\u0005\t\u0003\u0004n\u001d\u0001\u0007\u0011Q\u001a\u0019\u0005\u0003\u001f\f\u0019\u000e\u0005\u0004qg\u0006E\u0017\u0011\u0018\t\u0004\u0003\u0006MGaCAk\u0003\u0017\f\t\u0011!A\u0003\u0002a\u0014Aa\u0018\u00132g\u0005a\u0011mY2faR,\u0015\u000e\u001e5feR)q0a7\u0002h\"9\u0011\u0011H\bA\u0002\u0005u\u0007\u0007BAp\u0003G\u0004R\u0001OA \u0003C\u00042!QAr\t1\t)/a7\u0002\u0002\u0003\u0005)\u0011AAe\u0005\u0011yF%\r\u001b\t\r5|\u0001\u0019AAua\u0011\tY/a<\u0011\u000bA\f\u0019\"!<\u0011\u0007\u0005\u000by\u000fB\u0006\u0002r\u0006\u001d\u0018\u0011!A\u0001\u0006\u0003A(\u0001B0%cU\naB];o\u0003\u001a$XM]#ji\",'\u000fF\u0003\u0000\u0003o\u0014\u0019\u0001C\u0004\u0002:A\u0001\r!!?1\t\u0005m\u0018q \t\u0006q\u0005}\u0012Q \t\u0004\u0003\u0006}Ha\u0003B\u0001\u0003o\f\t\u0011!A\u0003\u0002\u0011\u0013Aa\u0018\u00132m!1Q\u000e\u0005a\u0001\u0003G\t1\u0002\u001e5f]\u000e{W\u000e]8tKV!!\u0011\u0002B\b)\u0011\u0011YA!\u0005\u0011\tar$Q\u0002\t\u0004\u0003\n=A!B6\u0012\u0005\u0004!\u0005BB7\u0012\u0001\u0004\u0011\u0019\u0002\r\u0004\u0003\u0016\te!q\u0004\t\u0007aN\u00149B!\b\u0011\u0007\u0005\u0013I\u0002B\u0006\u0003\u001c\tE\u0011\u0011!A\u0001\u0006\u0003A(\u0001B0%c]\u00022!\u0011B\u0010\t1\u0011\tC!\u0005\u0002\u0002\u0003\u0005)\u0011\u0001B\u0012\u0005\u0011yF%\r\u001d\u0012\u0007\u0015\u0013)\u0003E\u00039\u0003\u007f\u0011i!\u0001\u0007xQ\u0016t7i\\7qY\u0016$X\rF\u00028\u0005WAa!\u001c\nA\u0002\t5\u0002G\u0002B\u0018\u0005g\u0011I\u0004E\u0004q\u0003\u001b\u0013\tDa\u000e\u0011\u0007\u0005\u0013\u0019\u0004B\u0006\u00036\t-\u0012\u0011!A\u0001\u0006\u0003A(\u0001B0%ce\u00022!\u0011B\u001d\t1\u0011YDa\u000b\u0002\u0002\u0003\u0005)\u0011\u0001B\u001f\u0005\u0011yFE\r\u0019\u0012\u0007\t}\u0002\n\u0005\u0003\u0003B\tEc\u0002\u0002B\"\u0005\u001brAA!\u0012\u0003L5\u0011!q\t\u0006\u0004\u0005\u0013\u0012\u0014A\u0002\u001fs_>$h(C\u0001)\u0013\r\u0011yeJ\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0011\u0019F!\u0016\u0003\u0013QC'o\\<bE2,'b\u0001B(O\u00051\u0001.\u00198eY\u0016,BAa\u0017\u0003bQ!!Q\fB2!\u0011AdHa\u0018\u0011\u0007\u0005\u0013\t\u0007B\u0003l'\t\u0007A\t\u0003\u0004n'\u0001\u0007!Q\r\u0019\u0007\u0005O\u0012YG!\u001d\u0011\u0013A\f)F!\u001b\u0003@\t=\u0004cA!\u0003l\u0011Y!Q\u000eB2\u0003\u0003\u0005\tQ!\u0001y\u0005\u0011yFEM\u0019\u0011\u0007\u0005\u0013\t\b\u0002\u0007\u0003t\t\r\u0014\u0011!A\u0001\u0006\u0003\u0011)H\u0001\u0003`II\u0012\u0014cA#\u0003`\u0005iQ\r_2faRLwN\\1mYf$2a\u000eB>\u0011\u0019iG\u00031\u0001\u0003~A\"!q\u0010BB!\u0019\u00018Oa\u0010\u0003\u0002B\u0019\u0011Ia!\u0005\u0019\t\u0015%1PA\u0001\u0002\u0003\u0015\t!!3\u0003\t}##gM\u0001\u0014i>\u001cu.\u001c9mKR\f'\r\\3GkR,(/\u001a\u000b\u0002o\u0005aqN\u0019;sk\u0012,g+\u00197vKR\u00191Ka$\t\r\tEe\u00031\u0001A\u0003\u00151\u0018\r\\;f\u0003Ay'\r\u001e:vI\u0016,\u0005pY3qi&|g\u000eF\u0002T\u0005/CqA!'\u0018\u0001\u0004\u0011y$\u0001\u0002fq\u0006\u0019q-\u001a;\u0015\u0003\u0001#R\u0001\u0011BQ\u0005WCqAa)\u001a\u0001\u0004\u0011)+A\u0004uS6,w.\u001e;\u0011\u00079\u00129+C\u0002\u0003*\u001e\u0012A\u0001T8oO\"9!QV\rA\u0002\t=\u0016\u0001B;oSR\u00042\u0001\u000fBY\u0013\r\u0011\u0019,\u000f\u0002\t)&lW-\u00168ji\u0006AAo\\*ue&tw\r\u0006\u0002\u0003:B!!1\u0018Bb\u001d\u0011\u0011iLa0\u0011\u0007\t\u0015s%C\u0002\u0003B\u001e\na\u0001\u0015:fI\u00164\u0017\u0002\u0002Bc\u0005\u000f\u0014aa\u0015;sS:<'b\u0001BaO\u0005I1/\u001e9fe\u0012:W\r^\u0005\u0004\u00057sD#\u0002!\u0003P\nM\u0007\"\u0003Bi9\u0005\u0005\t\u0019\u0001BS\u0003\rAH%\r\u0005\n\u0005+d\u0012\u0011!a\u0001\u0005_\u000b1\u0001\u001f\u00133\u0013\r\u0011YJ\u0010\u0002\u0002!V!!Q\u001cB|'\u0015i\"q\u001cB}!\u0019\u0011\tOa<\u0003v:!!1\u001dBv\u001d\u0011\u0011)O!;\u000f\t\t\r#q]\u0005\u0003M\u001dJ!\u0001J\u0013\n\u0007\t58%A\u0004Qe>l\u0017n]3\n\t\tE(1\u001f\u0002\u000f\t\u00164\u0017-\u001e7u!J|W.[:f\u0015\r\u0011io\t\t\u0004\u0003\n]H!B\"\u001e\u0005\u0004!\u0005\u0003\u00039\u0002V\tU(qH*\u0016\u0005\tu\b#\u0002\u001d\u0002@\tUH\u0003BB\u0001\u0007\u0007\u0001BaX\u000f\u0003v\"1a\u000b\ta\u0001\u0005{$RaUB\u0004\u0007\u0017Aqa!\u0003\"\u0001\u0004\u0011)0A\u0001w\u0011\u001d\u0019i!\ta\u0001\u0005\u007f\t\u0011!\u001a"
)
public final class FutureConvertersImpl {
   public static final class CF extends CompletableFuture implements Function1 {
      private final Future wrapped;

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      // $FF: synthetic method
      private Object super$get() {
         return super.get();
      }

      // $FF: synthetic method
      private Object super$get(final long x$1, final TimeUnit x$2) {
         return super.get(x$1, x$2);
      }

      public Future wrapped() {
         return this.wrapped;
      }

      public void apply(final Try t) {
         if (t instanceof Success) {
            Object v = ((Success)t).value();
            this.complete(v);
         } else if (t instanceof Failure) {
            Throwable e = ((Failure)t).exception();
            this.completeExceptionally(e);
         } else {
            throw new MatchError(t);
         }
      }

      public CompletableFuture thenApply(final Function fn) {
         return this.thenApplyAsync(fn);
      }

      public CompletableFuture thenAccept(final Consumer fn) {
         return this.thenAcceptAsync(fn);
      }

      public CompletableFuture thenRun(final Runnable fn) {
         return this.thenRunAsync(fn);
      }

      public CompletableFuture thenCombine(final CompletionStage cs, final BiFunction fn) {
         return this.thenCombineAsync(cs, fn);
      }

      public CompletableFuture thenAcceptBoth(final CompletionStage cs, final BiConsumer fn) {
         return this.thenAcceptBothAsync(cs, fn);
      }

      public CompletableFuture runAfterBoth(final CompletionStage cs, final Runnable fn) {
         return this.runAfterBothAsync(cs, fn);
      }

      public CompletableFuture applyToEither(final CompletionStage cs, final Function fn) {
         return this.applyToEitherAsync(cs, fn);
      }

      public CompletableFuture acceptEither(final CompletionStage cs, final Consumer fn) {
         return this.acceptEitherAsync(cs, fn);
      }

      public CompletableFuture runAfterEither(final CompletionStage cs, final Runnable fn) {
         return this.runAfterEitherAsync(cs, fn);
      }

      public CompletableFuture thenCompose(final Function fn) {
         return this.thenComposeAsync(fn);
      }

      public CompletableFuture whenComplete(final BiConsumer fn) {
         return this.whenCompleteAsync(fn);
      }

      public CompletableFuture handle(final BiFunction fn) {
         return this.handleAsync(fn);
      }

      public CompletableFuture exceptionally(final Function fn) {
         CompletableFuture cf = new CompletableFuture();
         this.whenCompleteAsync((t, e) -> {
            if (e == null) {
               cf.complete(t);
            } else {
               Object var10000;
               try {
                  var10000 = fn.apply(e);
               } catch (Throwable var7) {
                  cf.completeExceptionally(var7);
                  var10000 = this;
               }

               Object n = var10000;
               if (n != this) {
                  cf.complete(n);
               }
            }
         });
         return cf;
      }

      public CompletableFuture toCompletableFuture() {
         return this;
      }

      public void obtrudeValue(final Object value) {
         throw new UnsupportedOperationException("obtrudeValue may not be used on the result of toJava(scalaFuture)");
      }

      public void obtrudeException(final Throwable ex) {
         throw new UnsupportedOperationException("obtrudeException may not be used on the result of toJava(scalaFuture)");
      }

      public Object get() {
         package$ var10000 = package$.MODULE$;
         Function0 blocking_body = () -> this.super$get();
         return BlockContext$.MODULE$.current().blockOn(blocking_body, AwaitPermission$.MODULE$);
      }

      public Object get(final long timeout, final TimeUnit unit) {
         package$ var10000 = package$.MODULE$;
         Function0 blocking_body = () -> this.super$get(timeout, unit);
         return BlockContext$.MODULE$.current().blockOn(blocking_body, AwaitPermission$.MODULE$);
      }

      public String toString() {
         return super.toString();
      }

      public CF(final Future wrapped) {
         this.wrapped = wrapped;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static final class P extends Promise.DefaultPromise implements BiFunction {
      private final CompletionStage wrapped;

      public BiFunction andThen(final Function x$1) {
         return super.andThen(x$1);
      }

      public CompletionStage wrapped() {
         return this.wrapped;
      }

      public void apply(final Object v, final Throwable e) {
         if (e == null) {
            scala.concurrent.Promise.success$(this, v);
         } else {
            scala.concurrent.Promise.failure$(this, e);
         }
      }

      public P(final CompletionStage wrapped) {
         this.wrapped = wrapped;
      }
   }
}
