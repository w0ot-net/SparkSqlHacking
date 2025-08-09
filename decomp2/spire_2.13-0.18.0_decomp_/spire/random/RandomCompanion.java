package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.Tuple4;
import scala.None.;
import scala.collection.Factory;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t%gaB\u0011#!\u0003\r\ta\n\u0005\u0006_\u0001!\t\u0001M\u0003\u0005i\u0001\u0001Q\u0007C\u0003M\u0001\u0019\u0005Q\nC\u0003O\u0001\u0011\u0005q\nC\u0003V\u0001\u0019\u0005a\u000bC\u0003c\u0001\u0011\u00051\rC\u0003n\u0001\u0011\u0005a\u000eC\u0003y\u0001\u0011\u0005\u0011\u0010C\u0004\u0002\u0002\u0001!\t!a\u0001\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n!9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001bBA\u0010\u0001\u0011\u0005\u0011\u0011\u0005\u0005\b\u0003W\u0001A\u0011AA\u0017\u0011\u001d\t9\u0004\u0001C\u0001\u0003sAq!a\u000e\u0001\t\u0003\t\u0019\u0005C\u0004\u00028\u0001!\t!!\u0013\t\u000f\u0005M\u0003\u0001\"\u0001\u0002V!9\u0011q\f\u0001\u0005\u0002\u0005\u0005\u0004bBA6\u0001\u0011\u0005\u0011Q\u000e\u0005\b\u0003o\u0002A\u0011AA=\u0011\u001d\ti\n\u0001C\u0001\u0003?3a!!*\u0001\u0003\u0005\u001d\u0006BCAV-\t\u0005\t\u0015!\u0003\u0002.\"9\u0011Q\u0017\f\u0005\u0002\u0005]\u0006bBA_-\u0011\u0005\u0011q\u0018\u0005\b\u0003G4B\u0011AAs\u0011\u001d\tiP\u0006C\u0001\u0003\u007fDqA!\t\u0017\t\u0003\u0011\u0019\u0003C\u0005\u0003<\u0001\t\t\u0011b\u0001\u0003>!9!1\n\u0001\u0005\u0002\t5\u0003b\u0002B7\u0001\u0011\u0005!q\u000e\u0005\b\u0005/\u0003A\u0011\u0001BM\u0005=\u0011\u0016M\u001c3p[\u000e{W\u000e]1oS>t'BA\u0012%\u0003\u0019\u0011\u0018M\u001c3p[*\tQ%A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005!25C\u0001\u0001*!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\r\t\u0003UIJ!aM\u0016\u0003\tUs\u0017\u000e\u001e\u0002\u0002%V\u0011a\u0007\u0010\t\u0005oaRT)D\u0001#\u0013\tI$E\u0001\u0004SC:$w.\u001c\t\u0003wqb\u0001\u0001B\u0003>\u0005\t\u0007aHA\u0001Y#\ty$\t\u0005\u0002+\u0001&\u0011\u0011i\u000b\u0002\b\u001d>$\b.\u001b8h!\tQ3)\u0003\u0002EW\t\u0019\u0011I\\=\u0011\u0005m2E!B$\u0001\u0005\u0004A%!A$\u0012\u0005}J\u0005CA\u001cK\u0013\tY%EA\u0005HK:,'/\u0019;pe\u0006i\u0011N\\5u\u000f\u0016tWM]1u_J,\u0012!R\u0001\u0012O\u0016tWM]1u_J4%o\\7TK\u0016$GCA#Q\u0011\u0015\tF\u00011\u0001S\u0003\u0011\u0019X-\u001a3\u0011\u0005]\u001a\u0016B\u0001+#\u0005\u0011\u0019V-\u001a3\u0002\u000bM\u0004\u0018m\u001e8\u0016\u0005][FC\u0001-^!\rI&AW\u0007\u0002\u0001A\u00111h\u0017\u0003\u00069\u0016\u0011\rA\u0010\u0002\u0002\u0005\")a,\u0002a\u0001?\u0006\u0011q\u000e\u001d\t\u0004o\u0001T\u0016BA1#\u0005\ty\u0005/\u0001\u0003oKb$XC\u00013h)\t)\u0007\u000eE\u0002Z\u0005\u0019\u0004\"aO4\u0005\u000bq3!\u0019\u0001 \t\u000b%4\u0001\u0019\u00016\u0002\u0003\u0019\u0004BAK6JM&\u0011An\u000b\u0002\n\rVt7\r^5p]F\n\u0001B\u001a:p[\u0012K7\u000f^\u000b\u0003_J$\"\u0001]:\u0011\u0007e\u0013\u0011\u000f\u0005\u0002<e\u0012)Al\u0002b\u0001}!)Ao\u0002a\u0001k\u0006!A-[:u!\r9d/]\u0005\u0003o\n\u0012A\u0001R5ti\u0006A1m\u001c8ti\u0006tG/\u0006\u0002{{R\u00111P \t\u00043\na\bCA\u001e~\t\u0015a\u0006B1\u0001?\u0011\u0015y\b\u00021\u0001}\u0003\u0005\u0011\u0017\u0001B;oSR,\"!!\u0002\u0011\u0007e\u0013\u0011'A\u0004c_>dW-\u00198\u0016\u0005\u0005-\u0001\u0003B-\u0003\u0003\u001b\u00012AKA\b\u0013\r\t\tb\u000b\u0002\b\u0005>|G.Z1o\u0003\u0011\u0011\u0017\u0010^3\u0016\u0005\u0005]\u0001\u0003B-\u0003\u00033\u00012AKA\u000e\u0013\r\tib\u000b\u0002\u0005\u0005f$X-A\u0003tQ>\u0014H/\u0006\u0002\u0002$A!\u0011LAA\u0013!\rQ\u0013qE\u0005\u0004\u0003SY#!B*i_J$\u0018\u0001B2iCJ,\"!a\f\u0011\te\u0013\u0011\u0011\u0007\t\u0004U\u0005M\u0012bAA\u001bW\t!1\t[1s\u0003\rIg\u000e^\u000b\u0003\u0003w\u0001B!\u0017\u0002\u0002>A\u0019!&a\u0010\n\u0007\u0005\u00053FA\u0002J]R$B!a\u000f\u0002F!9\u0011qI\bA\u0002\u0005u\u0012!\u00018\u0015\r\u0005m\u00121JA(\u0011\u001d\ti\u0005\u0005a\u0001\u0003{\t!A\\\u0019\t\u000f\u0005E\u0003\u00031\u0001\u0002>\u0005\u0011aNM\u0001\u0006M2|\u0017\r^\u000b\u0003\u0003/\u0002B!\u0017\u0002\u0002ZA\u0019!&a\u0017\n\u0007\u0005u3FA\u0003GY>\fG/\u0001\u0003m_:<WCAA2!\u0011I&!!\u001a\u0011\u0007)\n9'C\u0002\u0002j-\u0012A\u0001T8oO\u00061Am\\;cY\u0016,\"!a\u001c\u0011\te\u0013\u0011\u0011\u000f\t\u0004U\u0005M\u0014bAA;W\t1Ai\\;cY\u0016\faa\u001d;sS:<G\u0003BA>\u0003'\u0003B!\u0017\u0002\u0002~A!\u0011qPAG\u001d\u0011\t\t)!#\u0011\u0007\u0005\r5&\u0004\u0002\u0002\u0006*\u0019\u0011q\u0011\u0014\u0002\rq\u0012xn\u001c;?\u0013\r\tYiK\u0001\u0007!J,G-\u001a4\n\t\u0005=\u0015\u0011\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005-5\u0006C\u0004\u0002\u0016R\u0001\r!a&\u0002\tML'0\u001a\t\u0004o\u0005e\u0015bAANE\t!1+\u001b>f\u00031\u0019HO]5oO>37+\u001b>f)\u0011\t\t+a)\u0011\u000b]B\u0014QP#\t\u000f\u0005\u001dS\u00031\u0001\u0002>\tI!+\u00198e_6|\u0005o]\u000b\u0005\u0003S\u000b\tl\u0005\u0002\u0017S\u0005\u0019A\u000e[:\u0011\te\u0013\u0011q\u0016\t\u0004w\u0005EFABAZ-\t\u0007aHA\u0001B\u0003\u0019a\u0014N\\5u}Q!\u0011\u0011XA^!\u0011If#a,\t\u000f\u0005-\u0006\u00041\u0001\u0002.\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u0016\t\u0005\u0005\u0017\u0011\u001a\u000b\u0005\u0003\u0007\f\t\u000f\u0006\u0003\u0002F\u0006M\u0007#B\u001c9\u0003\u000f,\u0005#B\u001e\u0002J\u0006=FaBAf3\t\u0007\u0011Q\u001a\u0002\u0003\u0007\u000e+2APAh\t\u001d\t\t.!3C\u0002y\u0012Aa\u0018\u0013%c!9\u0011Q[\rA\u0004\u0005]\u0017aA2cMBA\u0011\u0011\\Ao\u0003_\u000b9-\u0004\u0002\u0002\\*\u0019\u0011QX\u0016\n\t\u0005}\u00171\u001c\u0002\b\r\u0006\u001cGo\u001c:z\u0011\u001d\t)*\u0007a\u0001\u0003/\u000b\u0001cY8mY\u0016\u001cG/[8o\u001f\u001a\u001c\u0016N_3\u0016\t\u0005\u001d\u0018q\u001e\u000b\u0005\u0003S\fY\u0010\u0006\u0003\u0002l\u0006]\b#B\u001c9\u0003[,\u0005#B\u001e\u0002p\u0006=FaBAf5\t\u0007\u0011\u0011_\u000b\u0004}\u0005MHaBA{\u0003_\u0014\rA\u0010\u0002\u0005?\u0012\"#\u0007C\u0004\u0002Vj\u0001\u001d!!?\u0011\u0011\u0005e\u0017Q\\AX\u0003[Dq!a\u0012\u001b\u0001\u0004\ti$\u0001\bg_2$G*\u001a4u\u001f\u001a\u001c\u0016N_3\u0016\t\t\u0005!1\u0002\u000b\u0005\u0005\u0007\u0011y\u0002\u0006\u0003\u0003\u0006\tUA\u0003\u0002B\u0004\u0005\u001b\u0001Ra\u000e\u001d\u0003\n\u0015\u00032a\u000fB\u0006\t\u0015a6D1\u0001?\u0011\u0019I7\u00041\u0001\u0003\u0010AI!F!\u0005\u0003\n\u0005=&\u0011B\u0005\u0004\u0005'Y#!\u0003$v]\u000e$\u0018n\u001c83\u0011!\u00119b\u0007CA\u0002\te\u0011\u0001B5oSR\u0004RA\u000bB\u000e\u0005\u0013I1A!\b,\u0005!a$-\u001f8b[\u0016t\u0004bBA$7\u0001\u0007\u0011QH\u0001\u0007k:4w\u000e\u001c3\u0016\t\t\u0015\"Q\u0006\u000b\u0005\u0005O\u0011I\u0004\u0006\u0003\u0003*\t=\u0002#B\u001c9\u0005W)\u0005cA\u001e\u0003.\u0011)A\f\bb\u0001}!1\u0011\u000e\ba\u0001\u0005c\u0001\u0012B\u000bB\t\u0005W\tyKa\r\u0011\u000b)\u0012)Da\u000b\n\u0007\t]2F\u0001\u0004PaRLwN\u001c\u0005\b\u0005/a\u0002\u0019\u0001B\u0016\u0003%\u0011\u0016M\u001c3p[>\u00038/\u0006\u0003\u0003@\t\u0015C\u0003\u0002B!\u0005\u000f\u0002B!\u0017\f\u0003DA\u00191H!\u0012\u0005\r\u0005MVD1\u0001?\u0011\u001d\tY+\ba\u0001\u0005\u0013\u0002B!\u0017\u0002\u0003D\u00051A/\u001e9mKJ*bAa\u0014\u0003\\\t}CC\u0002B)\u0005C\u00129\u0007\u0005\u0003Z\u0005\tM\u0003c\u0002\u0016\u0003V\te#QL\u0005\u0004\u0005/Z#A\u0002+va2,'\u0007E\u0002<\u00057\"a!a-\u001f\u0005\u0004q\u0004cA\u001e\u0003`\u0011)AL\bb\u0001}!9!1\r\u0010A\u0002\t\u0015\u0014A\u0001:2!\u0011I&A!\u0017\t\u000f\t%d\u00041\u0001\u0003l\u0005\u0011!O\r\t\u00053\n\u0011i&\u0001\u0004ukBdWmM\u000b\t\u0005c\u0012iH!!\u0003\u0006RA!1\u000fBE\u0005\u001b\u0013\t\n\u0005\u0003Z\u0005\tU\u0004#\u0003\u0016\u0003x\tm$q\u0010BB\u0013\r\u0011Ih\u000b\u0002\u0007)V\u0004H.Z\u001a\u0011\u0007m\u0012i\b\u0002\u0004\u00024~\u0011\rA\u0010\t\u0004w\t\u0005E!\u0002/ \u0005\u0004q\u0004cA\u001e\u0003\u0006\u00121!qQ\u0010C\u0002y\u0012\u0011a\u0011\u0005\b\u0005Gz\u0002\u0019\u0001BF!\u0011I&Aa\u001f\t\u000f\t%t\u00041\u0001\u0003\u0010B!\u0011L\u0001B@\u0011\u001d\u0011\u0019j\ba\u0001\u0005+\u000b!A]\u001a\u0011\te\u0013!1Q\u0001\u0007iV\u0004H.\u001a\u001b\u0016\u0015\tm%q\u0015BV\u0005_\u0013\u0019\f\u0006\u0006\u0003\u001e\n]&1\u0018B`\u0005\u0007\u0004B!\u0017\u0002\u0003 BY!F!)\u0003&\n%&Q\u0016BY\u0013\r\u0011\u0019k\u000b\u0002\u0007)V\u0004H.\u001a\u001b\u0011\u0007m\u00129\u000b\u0002\u0004\u00024\u0002\u0012\rA\u0010\t\u0004w\t-F!\u0002/!\u0005\u0004q\u0004cA\u001e\u00030\u00121!q\u0011\u0011C\u0002y\u00022a\u000fBZ\t\u0019\u0011)\f\tb\u0001}\t\tA\tC\u0004\u0003d\u0001\u0002\rA!/\u0011\te\u0013!Q\u0015\u0005\b\u0005S\u0002\u0003\u0019\u0001B_!\u0011I&A!+\t\u000f\tM\u0005\u00051\u0001\u0003BB!\u0011L\u0001BW\u0011\u001d\u0011)\r\ta\u0001\u0005\u000f\f!A\u001d\u001b\u0011\te\u0013!\u0011\u0017"
)
public interface RandomCompanion {
   Generator initGenerator();

   // $FF: synthetic method
   static Generator generatorFromSeed$(final RandomCompanion $this, final Seed seed) {
      return $this.generatorFromSeed(seed);
   }

   default Generator generatorFromSeed(final Seed seed) {
      Generator gen = this.initGenerator();
      gen.setSeedBytes(seed.bytes());
      return gen;
   }

   Random spawn(final Op op);

   // $FF: synthetic method
   static Random next$(final RandomCompanion $this, final Function1 f) {
      return $this.next(f);
   }

   default Random next(final Function1 f) {
      return this.spawn(new Next(f));
   }

   // $FF: synthetic method
   static Random fromDist$(final RandomCompanion $this, final Dist dist) {
      return $this.fromDist(dist);
   }

   default Random fromDist(final Dist dist) {
      return this.spawn(new Next((g) -> dist.apply(g)));
   }

   // $FF: synthetic method
   static Random constant$(final RandomCompanion $this, final Object b) {
      return $this.constant(b);
   }

   default Random constant(final Object b) {
      return this.spawn(new Const(b));
   }

   // $FF: synthetic method
   static Random unit$(final RandomCompanion $this) {
      return $this.unit();
   }

   default Random unit() {
      return this.constant(BoxedUnit.UNIT);
   }

   // $FF: synthetic method
   static Random boolean$(final RandomCompanion $this) {
      return $this.boolean();
   }

   default Random boolean() {
      return this.next((x$1) -> BoxesRunTime.boxToBoolean($anonfun$boolean$1(x$1)));
   }

   // $FF: synthetic method
   static Random byte$(final RandomCompanion $this) {
      return $this.byte();
   }

   default Random byte() {
      return this.next((x$2) -> BoxesRunTime.boxToByte($anonfun$byte$1(x$2)));
   }

   // $FF: synthetic method
   static Random short$(final RandomCompanion $this) {
      return $this.short();
   }

   default Random short() {
      return this.next((x$3) -> BoxesRunTime.boxToShort($anonfun$short$1(x$3)));
   }

   // $FF: synthetic method
   static Random char$(final RandomCompanion $this) {
      return $this.char();
   }

   default Random char() {
      return this.next((x$4) -> BoxesRunTime.boxToCharacter($anonfun$char$1(x$4)));
   }

   // $FF: synthetic method
   static Random int$(final RandomCompanion $this) {
      return $this.int();
   }

   default Random int() {
      return this.next((x$5) -> BoxesRunTime.boxToInteger($anonfun$int$1(x$5)));
   }

   // $FF: synthetic method
   static Random int$(final RandomCompanion $this, final int n) {
      return $this.int(n);
   }

   default Random int(final int n) {
      return this.next((x$6) -> BoxesRunTime.boxToInteger($anonfun$int$2(n, x$6)));
   }

   // $FF: synthetic method
   static Random int$(final RandomCompanion $this, final int n1, final int n2) {
      return $this.int(n1, n2);
   }

   default Random int(final int n1, final int n2) {
      return this.next((x$7) -> BoxesRunTime.boxToInteger($anonfun$int$3(n1, n2, x$7)));
   }

   // $FF: synthetic method
   static Random float$(final RandomCompanion $this) {
      return $this.float();
   }

   default Random float() {
      return this.next((x$8) -> BoxesRunTime.boxToFloat($anonfun$float$1(x$8)));
   }

   // $FF: synthetic method
   static Random long$(final RandomCompanion $this) {
      return $this.long();
   }

   default Random long() {
      return this.next((x$9) -> BoxesRunTime.boxToLong($anonfun$long$1(x$9)));
   }

   // $FF: synthetic method
   static Random double$(final RandomCompanion $this) {
      return $this.double();
   }

   default Random double() {
      return this.next((x$10) -> BoxesRunTime.boxToDouble($anonfun$double$1(x$10)));
   }

   // $FF: synthetic method
   static Random string$(final RandomCompanion $this, final Size size) {
      return $this.string(size);
   }

   default Random string(final Size size) {
      return size.random(this).flatMap((n) -> $anonfun$string$1(this, BoxesRunTime.unboxToInt(n)));
   }

   // $FF: synthetic method
   static Random stringOfSize$(final RandomCompanion $this, final int n) {
      return $this.stringOfSize(n);
   }

   default Random stringOfSize(final int n) {
      return this.RandomOps(this.char()).foldLeftOfSize(n, () -> new StringBuilder(), (sb, c) -> $anonfun$stringOfSize$2(sb, BoxesRunTime.unboxToChar(c))).map((x$11) -> x$11.toString());
   }

   // $FF: synthetic method
   static RandomOps RandomOps$(final RandomCompanion $this, final Random lhs) {
      return $this.RandomOps(lhs);
   }

   default RandomOps RandomOps(final Random lhs) {
      return new RandomOps(lhs);
   }

   // $FF: synthetic method
   static Random tuple2$(final RandomCompanion $this, final Random r1, final Random r2) {
      return $this.tuple2(r1, r2);
   }

   default Random tuple2(final Random r1, final Random r2) {
      return r1.and(r2);
   }

   // $FF: synthetic method
   static Random tuple3$(final RandomCompanion $this, final Random r1, final Random r2, final Random r3) {
      return $this.tuple3(r1, r2, r3);
   }

   default Random tuple3(final Random r1, final Random r2, final Random r3) {
      return r1.flatMap((a) -> r2.flatMap((b) -> r3.map((c) -> new Tuple3(a, b, c))));
   }

   // $FF: synthetic method
   static Random tuple4$(final RandomCompanion $this, final Random r1, final Random r2, final Random r3, final Random r4) {
      return $this.tuple4(r1, r2, r3, r4);
   }

   default Random tuple4(final Random r1, final Random r2, final Random r3, final Random r4) {
      return r1.flatMap((a) -> r2.flatMap((b) -> r3.flatMap((c) -> r4.map((d) -> new Tuple4(a, b, c, d)))));
   }

   // $FF: synthetic method
   static boolean $anonfun$boolean$1(final Generator x$1) {
      return x$1.nextBoolean();
   }

   // $FF: synthetic method
   static byte $anonfun$byte$1(final Generator x$2) {
      return (byte)x$2.nextInt();
   }

   // $FF: synthetic method
   static short $anonfun$short$1(final Generator x$3) {
      return (short)x$3.nextInt();
   }

   // $FF: synthetic method
   static char $anonfun$char$1(final Generator x$4) {
      return (char)x$4.nextInt();
   }

   // $FF: synthetic method
   static int $anonfun$int$1(final Generator x$5) {
      return x$5.nextInt();
   }

   // $FF: synthetic method
   static int $anonfun$int$2(final int n$1, final Generator x$6) {
      return x$6.nextInt(n$1);
   }

   // $FF: synthetic method
   static int $anonfun$int$3(final int n1$1, final int n2$1, final Generator x$7) {
      return x$7.nextInt(n1$1, n2$1);
   }

   // $FF: synthetic method
   static float $anonfun$float$1(final Generator x$8) {
      return x$8.nextFloat();
   }

   // $FF: synthetic method
   static long $anonfun$long$1(final Generator x$9) {
      return x$9.nextLong();
   }

   // $FF: synthetic method
   static double $anonfun$double$1(final Generator x$10) {
      return x$10.nextDouble();
   }

   // $FF: synthetic method
   static Random $anonfun$string$1(final RandomCompanion $this, final int n) {
      return $this.stringOfSize(n);
   }

   // $FF: synthetic method
   static StringBuilder $anonfun$stringOfSize$2(final StringBuilder sb, final char c) {
      sb.append(c);
      return sb;
   }

   static void $init$(final RandomCompanion $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class RandomOps {
      private final Random lhs;
      // $FF: synthetic field
      public final RandomCompanion $outer;

      public Random collection(final Size size, final Factory cbf) {
         return size.random(this.spire$random$RandomCompanion$RandomOps$$$outer()).flatMap((x$12) -> $anonfun$collection$1(this, cbf, BoxesRunTime.unboxToInt(x$12)));
      }

      public Random collectionOfSize(final int n, final Factory cbf) {
         return this.foldLeftOfSize(n, () -> cbf.newBuilder(), (b, a) -> {
            b.$plus$eq(a);
            return b;
         }).map((x$13) -> x$13.result());
      }

      public Random foldLeftOfSize(final int n, final Function0 init, final Function2 f) {
         return this.spire$random$RandomCompanion$RandomOps$$$outer().spawn(loop$2(n, new More(() -> this.lhs.op()), init, f));
      }

      public Random unfold(final Object init, final Function2 f) {
         return this.spire$random$RandomCompanion$RandomOps$$$outer().spawn(loop$3(new Const(init), new More(() -> this.lhs.op()), f));
      }

      // $FF: synthetic method
      public RandomCompanion spire$random$RandomCompanion$RandomOps$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Random $anonfun$collection$1(final RandomOps $this, final Factory cbf$1, final int x$12) {
         return $this.collectionOfSize(x$12, cbf$1);
      }

      private static final Op loop$2(final int n, final Op ma, final Function0 init$1, final Function2 f$4) {
         return (Op)(n <= 0 ? new Const(init$1.apply()) : (new More(() -> loop$2(n - 1, ma, init$1, f$4))).flatMap((b) -> ma.map((a) -> f$4.apply(b, a))));
      }

      private static final Op loop$3(final Op mb, final Op ma, final Function2 f$5) {
         return mb.flatMap((b) -> ma.flatMap((a) -> {
               Option var5 = (Option)f$5.apply(b, a);
               Object var4;
               if (var5 instanceof Some) {
                  Some var6 = (Some)var5;
                  Object b2 = var6.value();
                  var4 = new More(() -> loop$3(new Const(b2), ma, f$5));
               } else {
                  if (!.MODULE$.equals(var5)) {
                     throw new MatchError(var5);
                  }

                  var4 = new Const(b);
               }

               return (Op)var4;
            }));
      }

      public RandomOps(final Random lhs) {
         this.lhs = lhs;
         if (RandomCompanion.this == null) {
            throw null;
         } else {
            this.$outer = RandomCompanion.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
