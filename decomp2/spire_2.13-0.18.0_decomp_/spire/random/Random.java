package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Some;
import scala.Tuple2;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.random.rng.Cmwc5;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}u!B\f\u0019\u0011\u0003ib!B\u0010\u0019\u0011\u0003\u0001\u0003\"\u0002\u0019\u0002\t\u0003\t\u0004\"\u0002\u001a\u0002\t\u0003\u0019\u0004\"\u0002\u001b\u0002\t\u0003)d!B\u0010\u0019\u0003\u0003Q\u0005\u0002\u0003$\u0006\u0005\u000b\u0007I\u0011\u0001'\t\u0011E+!\u0011!Q\u0001\n5CQ\u0001M\u0003\u0005\u0002ICQ\u0001X\u0003\u0007\u0002uCQaX\u0003\u0005\u0002\u0001DQA[\u0003\u0005\u0002-DQA]\u0003\u0005\u0002MDQA]\u0003\u0005\u0002QDQA_\u0003\u0005\u0002mDq!!\u0001\u0006\t\u0003\t\u0019\u0001C\u0004\u0002 \u0015!\t!!\t\t\u000f\u0005-R\u0001\"\u0001\u0002.!9\u0011qG\u0003\u0005\u0002\u0005e\u0002bBA(\u000b\u0011\u0005\u0011\u0011\u000b\u0005\b\u0003K*A\u0011AA4\u0011\u001d\tY(\u0002C\u0001\u0003{Bq!!%\u0006\t\u0003\t\u0019*\u0001\u0004SC:$w.\u001c\u0006\u00033i\taA]1oI>l'\"A\u000e\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011a$A\u0007\u00021\t1!+\u00198e_6\u001c2!A\u0011(!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0019a\u0004\u000b\u0016\n\u0005%B\"a\u0004*b]\u0012|WnQ8na\u0006t\u0017n\u001c8\u0011\u0005-rS\"\u0001\u0017\u000b\u00055B\u0012a\u0001:oO&\u0011q\u0006\f\u0002\u0006\u00076<8-N\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003u\tQ\"\u001b8ji\u001e+g.\u001a:bi>\u0014X#\u0001\u0016\u0002\u000bM\u0004\u0018m\u001e8\u0016\u0005YbDCA\u001cF!\rq\u0002HO\u0005\u0003sa\u00111BU1oI>l7)\\<dkA\u00111\b\u0010\u0007\u0001\t\u0015iDA1\u0001?\u0005\u0005\u0011\u0015CA C!\t\u0011\u0003)\u0003\u0002BG\t9aj\u001c;iS:<\u0007C\u0001\u0012D\u0013\t!5EA\u0002B]fDQA\u0012\u0003A\u0002\u001d\u000b!a\u001c9\u0011\u0007yA%(\u0003\u0002J1\t\u0011q\n]\u000b\u0004\u0017>+6CA\u0003\"+\u0005i\u0005c\u0001\u0010I\u001dB\u00111h\u0014\u0003\u0007!\u0016!)\u0019\u0001 \u0003\u0003\u0005\u000b1a\u001c9!)\t\u00196\f\u0005\u0003\u001f\u000b9#\u0006CA\u001eV\t\u00151VA1\u0001X\u0005\u00059\u0015CA Y!\tq\u0012,\u0003\u0002[1\tIq)\u001a8fe\u0006$xN\u001d\u0005\u0006\r\"\u0001\r!T\u0001\nG>l\u0007/\u00198j_:,\u0012A\u0018\t\u0004=!\"\u0016aA7baV\u0011\u0011\r\u001a\u000b\u0003E\u0016\u0004BAH\u0003d)B\u00111\b\u001a\u0003\u0006{)\u0011\rA\u0010\u0005\u0006M*\u0001\raZ\u0001\u0002MB!!\u0005\u001b(d\u0013\tI7EA\u0005Gk:\u001cG/[8oc\u00059a\r\\1u\u001b\u0006\u0004XC\u00017p)\ti\u0007\u000f\u0005\u0003\u001f\u000b9$\u0006CA\u001ep\t\u0015i4B1\u0001?\u0011\u001517\u00021\u0001r!\u0011\u0011\u0003NT7\u0002\u0007I,h.F\u0001O)\tqU\u000fC\u0003w\u001b\u0001\u0007q/\u0001\u0003tK\u0016$\u0007C\u0001\u0010y\u0013\tI\bD\u0001\u0003TK\u0016$\u0017\u0001B:p[\u0016,\u0012\u0001 \t\u0005=\u0015iH\u000bE\u0002#}:K!a`\u0012\u0003\tM{W.Z\u0001\u0005Y\u00164G/\u0006\u0002\u0002\u0006A)a$BA\u0004)B1\u0011\u0011BA\r\u001d~rA!a\u0003\u0002\u00169!\u0011QBA\n\u001b\t\tyAC\u0002\u0002\u0012q\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0013\n\u0007\u0005]1%A\u0004qC\u000e\\\u0017mZ3\n\t\u0005m\u0011Q\u0004\u0002\u0005\u0019\u00164GOC\u0002\u0002\u0018\r\nQA]5hQR,\"!a\t\u0011\u000by)\u0011Q\u0005+\u0011\r\u0005%\u0011qE O\u0013\u0011\tI#!\b\u0003\u000bIKw\r\u001b;\u0002\r=\u0004H/[8o+\t\ty\u0003E\u0003\u001f\u000b\u0005EB\u000b\u0005\u0003#\u0003gq\u0015bAA\u001bG\t1q\n\u001d;j_:\f!a\u001c:\u0016\t\u0005m\u0012q\t\u000b\u0005\u0003{\tI\u0005E\u0003\u001f\u000b\u0005}B\u000bE\u0004\u0002\n\u0005\u0005c*!\u0012\n\t\u0005\r\u0013Q\u0004\u0002\u0007\u000b&$\b.\u001a:\u0011\u0007m\n9\u0005B\u0003>%\t\u0007a\bC\u0004\u0002LI\u0001\r!!\u0014\u0002\tQD\u0017\r\u001e\t\u0006=\u0015\t)\u0005V\u0001\u0004C:$W\u0003BA*\u0003?\"B!!\u0016\u0002bA)a$BA,)B1!%!\u0017O\u0003;J1!a\u0017$\u0005\u0019!V\u000f\u001d7feA\u00191(a\u0018\u0005\u000bu\u001a\"\u0019\u0001 \t\u000f\u0005-3\u00031\u0001\u0002dA)a$BA/)\u00069!/Z2veN,W\u0003BA5\u0003_\"B!a\u001b\u0002rA)a$BA7)B\u00191(a\u001c\u0005\u000bu\"\"\u0019\u0001 \t\u0011\u0005MD\u0003\"a\u0001\u0003k\nAAY8esB)!%a\u001e\u0002l%\u0019\u0011\u0011P\u0012\u0003\u0011q\u0012\u0017P\\1nKz\nA\u0001\\5tiR!\u0011qPAD!\u0015qR!!!U!\u0015\tI!a!O\u0013\u0011\t))!\b\u0003\t1K7\u000f\u001e\u0005\b\u0003\u0013+\u0002\u0019AAF\u0003\u0011\u0019\u0018N_3\u0011\u0007y\ti)C\u0002\u0002\u0010b\u0011AaU5{K\u0006QA.[:u\u001f\u001a\u001c\u0016N_3\u0015\t\u0005}\u0014Q\u0013\u0005\b\u0003/3\u0002\u0019AAM\u0003\u0005q\u0007c\u0001\u0012\u0002\u001c&\u0019\u0011QT\u0012\u0003\u0007%sG\u000f"
)
public abstract class Random {
   private final Op op;

   public static RandomCmwc5 spawn(final Op op) {
      return Random$.MODULE$.spawn(op);
   }

   public static Cmwc5 initGenerator() {
      return Random$.MODULE$.initGenerator();
   }

   public static Random tuple4(final Random r1, final Random r2, final Random r3, final Random r4) {
      return Random$.MODULE$.tuple4(r1, r2, r3, r4);
   }

   public static Random tuple3(final Random r1, final Random r2, final Random r3) {
      return Random$.MODULE$.tuple3(r1, r2, r3);
   }

   public static Random tuple2(final Random r1, final Random r2) {
      return Random$.MODULE$.tuple2(r1, r2);
   }

   public static RandomCompanion.RandomOps RandomOps(final Random lhs) {
      return Random$.MODULE$.RandomOps(lhs);
   }

   public static Random stringOfSize(final int n) {
      return Random$.MODULE$.stringOfSize(n);
   }

   public static Random string(final Size size) {
      return Random$.MODULE$.string(size);
   }

   public static Random double() {
      return Random$.MODULE$.double();
   }

   public static Random long() {
      return Random$.MODULE$.long();
   }

   public static Random float() {
      return Random$.MODULE$.float();
   }

   public static Random int(final int n1, final int n2) {
      return Random$.MODULE$.int(n1, n2);
   }

   public static Random int(final int n) {
      return Random$.MODULE$.int(n);
   }

   public static Random int() {
      return Random$.MODULE$.int();
   }

   public static Random char() {
      return Random$.MODULE$.char();
   }

   public static Random short() {
      return Random$.MODULE$.short();
   }

   public static Random byte() {
      return Random$.MODULE$.byte();
   }

   public static Random boolean() {
      return Random$.MODULE$.boolean();
   }

   public static Random unit() {
      return Random$.MODULE$.unit();
   }

   public static Random constant(final Object b) {
      return Random$.MODULE$.constant(b);
   }

   public static Random fromDist(final Dist dist) {
      return Random$.MODULE$.fromDist(dist);
   }

   public static Random next(final Function1 f) {
      return Random$.MODULE$.next(f);
   }

   public static Generator generatorFromSeed(final Seed seed) {
      return Random$.MODULE$.generatorFromSeed(seed);
   }

   public Op op() {
      return this.op;
   }

   public abstract RandomCompanion companion();

   public Random map(final Function1 f) {
      return this.companion().spawn(this.op().map(f));
   }

   public Random flatMap(final Function1 f) {
      return this.companion().spawn(this.op().flatMap((x$14) -> ((Random)f.apply(x$14)).op()));
   }

   public Object run() {
      return this.op().run(this.companion().initGenerator());
   }

   public Object run(final Seed seed) {
      Generator gen = this.companion().initGenerator();
      gen.setSeedBytes(seed.bytes());
      return this.op().run(gen);
   }

   public Random some() {
      return this.map((x$15) -> new Some(x$15));
   }

   public Random left() {
      return this.map((x$16) -> .MODULE$.Left().apply(x$16));
   }

   public Random right() {
      return this.map((x$17) -> .MODULE$.Right().apply(x$17));
   }

   public Random option() {
      return this.companion().boolean().flatMap((b) -> $anonfun$option$1(this, BoxesRunTime.unboxToBoolean(b)));
   }

   public Random or(final Random that) {
      return this.companion().boolean().flatMap((b) -> $anonfun$or$1(this, that, BoxesRunTime.unboxToBoolean(b)));
   }

   public Random and(final Random that) {
      return this.flatMap((a) -> that.map((b) -> new Tuple2(a, b)));
   }

   public Random recurse(final Function0 body) {
      return this.companion().spawn(new More(() -> ((Random)body.apply()).op()));
   }

   public Random list(final Size size) {
      return size.random(this.companion()).flatMap((n) -> $anonfun$list$1(this, BoxesRunTime.unboxToInt(n)));
   }

   public Random listOfSize(final int n) {
      return this.companion().RandomOps(this).foldLeftOfSize(n, () -> .MODULE$.List().empty(), (as, a) -> as.$colon$colon(a));
   }

   // $FF: synthetic method
   public static final Random $anonfun$option$1(final Random $this, final boolean b) {
      return b ? $this.some() : $this.companion().constant(scala.None..MODULE$);
   }

   // $FF: synthetic method
   public static final Random $anonfun$or$1(final Random $this, final Random that$1, final boolean b) {
      return b ? $this.left() : that$1.right();
   }

   // $FF: synthetic method
   public static final Random $anonfun$list$1(final Random $this, final int n) {
      return $this.listOfSize(n);
   }

   public Random(final Op op) {
      this.op = op;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
