package scala.jdk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.collection.AnyStepper$;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.LongStepper;
import scala.collection.SeqFactory;
import scala.collection.SpecificIterableFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.View;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Growable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r]b\u0001\u0002#F\u0005)CQa\u001b\u0001\u0005\u00021D\u0001\"\u001c\u0001A\u0002\u0013\u0005QI\u001c\u0005\te\u0002\u0001\r\u0011\"\u0001Fg\"1\u0011\u0010\u0001Q!\n=D\u0001B\u001f\u0001A\u0002\u0013\u0005Qi\u001f\u0005\t{\u0002\u0001\r\u0011\"\u0001F}\"9\u0011\u0011\u0001\u0001!B\u0013a\b\u0002CA\u0002\u0001\u0011\u0005Q)!\u0002\t\u0011\u0005E\u0001\u0001)C)\u0003'Aq!!\n\u0001\t\u0003\t9\u0003C\u0004\u0002v\u0001!I!a\u001e\t\u000f\u0005e\u0004\u0001\"\u0003\u0002x!9\u00111\u0010\u0001\u0005\u0002\u0005u\u0004BBAC\u0001\u0011\u0005C\u000eC\u0004\u0002\b\u0002!\t!!#\t\u000f\u0005=\u0005\u0001\"\u0011\u0002x!9\u0011\u0011\u0013\u0001\u0005\u0002\u0005M\u0005bBAI\u0001\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003;\u0003A\u0011AAP\u0011\u001d\ti\n\u0001C\u0001\u0003SCq!a,\u0001\t\u0003\t\t\fC\u0004\u0002:\u0002!\t%a/\t\u000f\u0005=\u0007\u0001\"\u0001\u0002R\"9\u0011q\u001b\u0001\u0005\u0002\u0005e\u0007bBAs\u0001\u0011\u0005\u0011q\u001d\u0005\b\u0003g\u0004A\u0011BA{\u0011\u001d\u00119\u0001\u0001C!\u0005\u0013AqA!\u0004\u0001\t\u0003\u0012y\u0001C\u0004\u0003\u0014\u0001!\tE!\u0006\t\u000f\tm\u0001\u0001\"\u0011\u0003\u001e!9!\u0011\u0005\u0001\u0005B\t\r\u0002b\u0002B\u0014\u0001\u0011\u0005!\u0011\u0006\u0005\u0007\u0005[\u0001A\u0011\u00018\t\u000f\t=\u0002\u0001\"\u0011\u00032!9!\u0011\b\u0001\u0005B\tm\u0002b\u0002B(\u0001\u0011E#\u0011\u000b\u0005\b\u0005/\u0002A\u0011\u000bB-\u0011\u001d\u0011Y\u0006\u0001C!\u0005;BqA!\u001a\u0001\t\u0003\u0012I\u0006C\u0004\u0003h\u0001!IA!\u001b\b\u000f\tET\t#\u0001\u0003t\u00191A)\u0012E\u0001\u0005kBaa\u001b\u0016\u0005\u0002\t-\u0005\u0002\u0003BGU\t\u0007I\u0011\u00028\t\u000f\t=%\u0006)A\u0005_\"A!\u0011\u0013\u0016C\u0002\u0013%1\u0010C\u0004\u0003\u0014*\u0002\u000b\u0011\u0002?\t\u000f\tU%\u0006b\u0001\u0003\u0018\"9!1\u0016\u0016\u0005\u0002\t5\u0006b\u0002B`U\u0011\u0005!\u0011\u0019\u0005\b\u0005\u0013TC\u0011\u0001Bf\u0011\u001d\u0011\u0019N\u000bC\u0001\u0005+DqA!7+\t\u0013\u0011Y\u000eC\u0004\u0003P)\"\tEa8\t\u000f\t\u0015$\u0006\"\u0011\u0003Z!9!Q\u001d\u0016\u0005B\tecA\u0002BtU\u0001\u0011I\u000f\u0003\u0006\u0003nf\u0012)\u0019!C\u0005\u00053B\u0011Ba<:\u0005\u0003\u0005\u000b\u0011\u0002,\t\r-LD\u0011\u0001B}\u0011-\t))\u000fa\u0001\u0002\u0004%IA!\u0017\t\u0017\r\u001d\u0011\b1AA\u0002\u0013%1\u0011\u0002\u0005\u000b\u0007\u001bI\u0004\u0019!A!B\u00131\u0006bBB\ts\u0011%11\u0003\u0005\b\u0007?ID\u0011BB\u0011\u0011\u001d\u0019i#\u000fC\u0005\u0005SB\u0011Ba\u001a+\u0003\u0003%Iaa\f\u0003\u001f1{gnZ!dGVlW\u000f\\1u_JT!AR$\u0002\u0007)$7NC\u0001I\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019B\u0001A&X?B)A*T(T-6\tQ)\u0003\u0002O\u000b\nY\u0011iY2v[Vd\u0017\r^8s!\t\u0001\u0016+D\u0001H\u0013\t\u0011vI\u0001\u0003M_:<\u0007C\u0001'U\u0013\t)VI\u0001\bB]f\f5mY;nk2\fGo\u001c:\u0011\u00051\u0003\u0001#\u0002-^\u001fN3V\"A-\u000b\u0005i[\u0016aB7vi\u0006\u0014G.\u001a\u0006\u00039\u001e\u000b!bY8mY\u0016\u001cG/[8o\u0013\tq\u0016L\u0001\u0004TKF|\u0005o\u001d\t\u0003A\"t!!\u00194\u000f\u0005\t,W\"A2\u000b\u0005\u0011L\u0015A\u0002\u001fs_>$h(C\u0001I\u0013\t9w)A\u0004qC\u000e\\\u0017mZ3\n\u0005%T'\u0001D*fe&\fG.\u001b>bE2,'BA4H\u0003\u0019a\u0014N\\5u}Q\ta+A\u0004dkJ\u0014XM\u001c;\u0016\u0003=\u00042\u0001\u00159P\u0013\t\txIA\u0003BeJ\f\u00170A\u0006dkJ\u0014XM\u001c;`I\u0015\fHC\u0001;x!\t\u0001V/\u0003\u0002w\u000f\n!QK\\5u\u0011\u001dA8!!AA\u0002=\f1\u0001\u001f\u00132\u0003!\u0019WO\u001d:f]R\u0004\u0013a\u00025jgR|'/_\u000b\u0002yB\u0019\u0001\u000b]8\u0002\u0017!L7\u000f^8ss~#S-\u001d\u000b\u0003i~Dq\u0001\u001f\u0004\u0002\u0002\u0003\u0007A0\u0001\u0005iSN$xN]=!\u0003)\u0019W/\\;mCRLg/\u001a\u000b\u0004\u001f\u0006\u001d\u0001bBA\u0005\u0011\u0001\u0007\u00111B\u0001\u0002SB\u0019\u0001+!\u0004\n\u0007\u0005=qIA\u0002J]R\f\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\u0005U\u0001\u0003BA\f\u0003?qA!!\u0007\u0002\u001cA\u0011!mR\u0005\u0004\u0003;9\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002\"\u0005\r\"AB*ue&twMC\u0002\u0002\u001e\u001d\u000b\u0001#\u001a4gS\u000eLWM\u001c;Ti\u0016\u0004\b/\u001a:\u0016\t\u0005%\u0012Q\u0007\u000b\u0005\u0003W\tYG\u0005\u0004\u0002.\u0005E\u0012\u0011\f\u0004\u0007\u0003_\u0001\u0001!a\u000b\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\t\u0005M\u0012Q\u0007\u0007\u0001\t\u001d\t9D\u0003b\u0001\u0003s\u0011\u0011aU\t\u0005\u0003w\t\t\u0005E\u0002Q\u0003{I1!a\u0010H\u0005\u001dqu\u000e\u001e5j]\u001e\u0004D!a\u0011\u0002NA1\u0011QIA$\u0003\u0017j\u0011aW\u0005\u0004\u0003\u0013Z&aB*uKB\u0004XM\u001d\t\u0005\u0003g\ti\u0005\u0002\u0007\u0002P\u0005U\u0012\u0011!A\u0001\u0006\u0003\t\tFA\u0002`IE\nB!a\u000f\u0002TA\u0019\u0001+!\u0016\n\u0007\u0005]sIA\u0002B]f\u0004B!a\u0017\u0002f9!\u0011QLA1\u001d\r\t\u0017qL\u0005\u00039\u001eK1!a\u0019\\\u0003\u001d\u0019F/\u001a9qKJLA!a\u001a\u0002j\tqQI\u001a4jG&,g\u000e^*qY&$(bAA27\"9\u0011Q\u000e\u0006A\u0004\u0005=\u0014!B:iCB,\u0007cBA#\u0003cz\u0015\u0011G\u0005\u0004\u0003gZ&\u0001D*uKB\u0004XM]*iCB,\u0017AB3ya\u0006tG\rF\u0001u\u0003\u001dAW\t\u001f9b]\u0012\fa!\u00193e\u001f:,G\u0003BA@\u0003\u0003k\u0011\u0001\u0001\u0005\u0007\u0003\u0007k\u0001\u0019A(\u0002\u0003\u0005\faA]3tk2$\u0018!\u00023sC&tGc\u0001;\u0002\f\"1\u0011QR\bA\u0002Y\u000bA\u0001\u001e5bi\u0006)1\r\\3be\u0006)\u0011\r\u001d9msR\u0019q*!&\t\r\u0005]\u0015\u00031\u0001P\u0003\tI\u0007\u0010F\u0002P\u00037Cq!!\u0003\u0013\u0001\u0004\tY!\u0001\u0004va\u0012\fG/\u001a\u000b\u0006i\u0006\u0005\u0016Q\u0015\u0005\u0007\u0003G\u001b\u0002\u0019A(\u0002\u0007%$\u0007\u0010\u0003\u0004\u0002(N\u0001\raT\u0001\u0005K2,W\u000eF\u0003u\u0003W\u000bi\u000bC\u0004\u0002$R\u0001\r!a\u0003\t\r\u0005\u001dF\u00031\u0001P\u0003!IG/\u001a:bi>\u0014XCAAZ!\u0011\u0001\u0017QW(\n\u0007\u0005]&N\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003\u001d1wN]3bG\",B!!0\u0002LR\u0019A/a0\t\u000f\u0005\u0005g\u00031\u0001\u0002D\u0006\ta\r\u0005\u0004Q\u0003\u000b|\u0015\u0011Z\u0005\u0004\u0003\u000f<%!\u0003$v]\u000e$\u0018n\u001c82!\u0011\t\u0019$a3\u0005\u000f\u00055gC1\u0001\u0002R\t\tQ+A\u0002nCB$2AVAj\u0011\u001d\t\tm\u0006a\u0001\u0003+\u0004R\u0001UAc\u001f>\u000bqA\u001a7bi6\u000b\u0007\u000fF\u0002W\u00037Dq!!1\u0019\u0001\u0004\ti\u000e\u0005\u0004Q\u0003\u000b|\u0015q\u001c\t\u0005A\u0006\u0005x*C\u0002\u0002d*\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\fqaY8mY\u0016\u001cG\u000fF\u0002W\u0003SDq!a;\u001a\u0001\u0004\ti/\u0001\u0002qMB)\u0001+a<P\u001f&\u0019\u0011\u0011_$\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\fQBZ5mi\u0016\u0014\u0018iY2J[BdG#\u0002,\u0002x\n\r\u0001bBA}5\u0001\u0007\u00111`\u0001\u0005aJ,G\r\u0005\u0004Q\u0003\u000b|\u0015Q \t\u0004!\u0006}\u0018b\u0001B\u0001\u000f\n9!i\\8mK\u0006t\u0007b\u0002B\u00035\u0001\u0007\u0011Q`\u0001\u0004]>$\u0018A\u00024jYR,'\u000fF\u0002W\u0005\u0017Aq!!?\u001c\u0001\u0004\tY0A\u0005gS2$XM\u001d(piR\u0019aK!\u0005\t\u000f\u0005eH\u00041\u0001\u0002|\u00061am\u001c:bY2$B!!@\u0003\u0018!9!\u0011D\u000fA\u0002\u0005m\u0018!\u00019\u0002\r\u0015D\u0018n\u001d;t)\u0011\tiPa\b\t\u000f\tea\u00041\u0001\u0002|\u0006)1m\\;oiR!\u00111\u0002B\u0013\u0011\u001d\u0011Ib\ba\u0001\u0003w\f\u0011bY8v]RduN\\4\u0015\u0007=\u0013Y\u0003C\u0004\u0003\u001a\u0001\u0002\r!a?\u0002\u000fQ|\u0017I\u001d:bs\u00061Ao\u001c'jgR,\"Aa\r\u0011\t\u0001\u0014)dT\u0005\u0004\u0005oQ'\u0001\u0002'jgR\f!\u0001^8\u0016\t\tu\"\u0011\t\u000b\u0005\u0005\u007f\u0011)\u0005\u0005\u0003\u00024\t\u0005Ca\u0002B\"G\t\u0007\u0011\u0011\u000b\u0002\u0003\u0007FBqAa\u0012$\u0001\u0004\u0011I%A\u0004gC\u000e$xN]=\u0011\u000f\u0005\u0015#1J(\u0003@%\u0019!QJ.\u0003\u000f\u0019\u000b7\r^8ss\u0006aaM]8n'B,7-\u001b4jGR\u0019aKa\u0015\t\u000f\tUC\u00051\u0001\u0002`\u0006!1m\u001c7m\u0003IqWm^*qK\u000eLg-[2Ck&dG-\u001a:\u0016\u0003Y\u000bq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0003\u0005?\u0002R!!\u0012\u0003bMK1Aa\u0019\\\u0005)\u0019V-\u001d$bGR|'/_\u0001\u0006K6\u0004H/_\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005W\u00022\u0001\u0015B7\u0013\r\u0011yg\u0012\u0002\u0007\u0003:L(+\u001a4\u0002\u001f1{gnZ!dGVlW\u000f\\1u_J\u0004\"\u0001\u0014\u0016\u0014\u000f)\u0012YGa\u001e\u0003~A1\u0011Q\tB=\u001fZK1Aa\u001f\\\u0005]\u0019\u0006/Z2jM&\u001c\u0017\n^3sC\ndWMR1di>\u0014\u0018\u0010\u0005\u0003\u0003\u0000\t%UB\u0001BA\u0015\u0011\u0011\u0019I!\"\u0002\u0005%|'B\u0001BD\u0003\u0011Q\u0017M^1\n\u0007%\u0014\t\t\u0006\u0002\u0003t\u0005qQ-\u001c9us2{gnZ!se\u0006L\u0018aD3naRLHj\u001c8h\u0003J\u0014\u0018-\u001f\u0011\u0002'\u0015l\u0007\u000f^=M_:<\u0017I\u001d:bs\u0006\u0013(/Y=\u0002)\u0015l\u0007\u000f^=M_:<\u0017I\u001d:bs\u0006\u0013(/Y=!\u0003U!xNS1wC2{gnZ!dGVlW\u000f\\1u_J$BA!'\u0003&B9\u0011Q\tB=\u000573\u0006\u0003\u0002BO\u0005Gk!Aa(\u000b\t\t\u0005&QQ\u0001\u0005Y\u0006tw-C\u0002S\u0005?CqAa*1\u0001\u0004\u0011I+\u0001\u0002jC:\u0011A*K\u0001\tgV\u0004\b\u000f\\5feV\u0011!q\u0016\t\u0006\u0005c\u0013YLV\u0007\u0003\u0005gSAA!.\u00038\u0006Aa-\u001e8di&|gN\u0003\u0003\u0003:\n\u0015\u0015\u0001B;uS2LAA!0\u00034\nA1+\u001e9qY&,'/A\u0003bI\u0012,'/\u0006\u0002\u0003DB)!\u0011\u0017Bc-&!!q\u0019BZ\u0005=y%M\u001b'p]\u001e\u001cuN\\:v[\u0016\u0014\u0018A\u00032pq\u0016$\u0017\t\u001a3feV\u0011!Q\u001a\t\u0007\u0005c\u0013yMV(\n\t\tE'1\u0017\u0002\u000b\u0005&\u001cuN\\:v[\u0016\u0014\u0018AB7fe\u001e,'/\u0006\u0002\u0003XB1!\u0011\u0017Bh-Z\u000b\u0011B\u001a:p[\u0006\u0013(/Y=\u0015\u0007Y\u0013i\u000e\u0003\u0004\u0002\u0004V\u0002\ra\u001c\u000b\u0004-\n\u0005\bb\u0002Brm\u0001\u0007\u0011q\\\u0001\u0003SR\f!B\\3x\u0005VLG\u000eZ3s\u0005I\u0019VM]5bY&T\u0018\r^5p]B\u0013x\u000e_=\u0016\t\t-8\u0011A\n\u0005s\t-t,A\u0002bG\u000e\fA!Y2dA!\u001a1Ha=\u0011\u0007A\u0013)0C\u0002\u0003x\u001e\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0015\t\tm8Q\u0001\t\u0006\u0005{L$q`\u0007\u0002UA!\u00111GB\u0001\t\u001d\u0019\u0019!\u000fb\u0001\u0003#\u0012\u0011!\u0011\u0005\u0007\u0005[d\u0004\u0019\u0001,\u0002\u0015I,7/\u001e7u?\u0012*\u0017\u000fF\u0002u\u0007\u0017Aq\u0001\u001f \u0002\u0002\u0003\u0007a+A\u0004sKN,H\u000e\u001e\u0011)\u0007}\u0012\u00190A\u0006xe&$Xm\u00142kK\u000e$Hc\u0001;\u0004\u0016!91q\u0003!A\u0002\re\u0011aA8viB!!qPB\u000e\u0013\u0011\u0019iB!!\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u000be\u0016\fGm\u00142kK\u000e$Hc\u0001;\u0004$!91QE!A\u0002\r\u001d\u0012AA5o!\u0011\u0011yh!\u000b\n\t\r-\"\u0011\u0011\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0017a\u0003:fC\u0012\u0014Vm]8mm\u0016$\"a!\r\u0011\t\tu51G\u0005\u0005\u0007k\u0011yJ\u0001\u0004PE*,7\r\u001e"
)
public final class LongAccumulator extends Accumulator$mcJ$sp implements Serializable {
   private long[] current;
   private long[][] history;

   public static LongAccumulator newBuilder() {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      return new LongAccumulator();
   }

   public static BiConsumer merger() {
      return LongAccumulator$.MODULE$.merger();
   }

   public static BiConsumer boxedAdder() {
      return LongAccumulator$.MODULE$.boxedAdder();
   }

   public static ObjLongConsumer adder() {
      return LongAccumulator$.MODULE$.adder();
   }

   public static Supplier supplier() {
      return LongAccumulator$.MODULE$.supplier();
   }

   public static SpecificIterableFactory toJavaLongAccumulator(final LongAccumulator$ ia) {
      return LongAccumulator$.MODULE$;
   }

   public static Factory specificIterableFactory() {
      return LongAccumulator$.MODULE$;
   }

   public static Object fill(final int n, final Function0 elem) {
      LongAccumulator$ fill_this = LongAccumulator$.MODULE$;
      IterableOnce fromSpecific_it = new View.Fill(n, elem);
      return fill_this.fromSpecific(fromSpecific_it);
   }

   public long[] current() {
      return this.current;
   }

   public void current_$eq(final long[] x$1) {
      this.current = x$1;
   }

   public long[][] history() {
      return this.history;
   }

   public void history_$eq(final long[][] x$1) {
      this.history = x$1;
   }

   public long cumulative(final int i) {
      long[] x = this.history()[i];
      return x[x.length - 1];
   }

   public String className() {
      return "LongAccumulator";
   }

   public Stepper efficientStepper(final StepperShape shape) {
      LongAccumulatorStepper st = new LongAccumulatorStepper(this);
      if (shape.shape() == StepperShape$.MODULE$.LongShape()) {
         return st;
      } else if (shape.shape() != StepperShape$.MODULE$.ReferenceShape()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$efficientStepper$1(shape)).toString());
      } else {
         AnyStepper$ var10000 = AnyStepper$.MODULE$;
         return new Stepper.EfficientSplit(st) {
         };
      }
   }

   private void expand() {
      if (this.index() > 0) {
         long[] var10000 = this.current();
         int var10001 = this.current().length - 1;
         long var10002;
         if (this.hIndex() > 0) {
            long[] x = this.history()[this.hIndex() - 1];
            var10002 = x[x.length - 1];
         } else {
            var10002 = 0L;
         }

         var10000[var10001] = var10002 + (long)this.index();
         if (this.hIndex() >= this.history().length) {
            this.hExpand();
         }

         this.history()[this.hIndex()] = this.current();
         this.hIndex_$eq(this.hIndex() + 1);
      }

      this.current_$eq(new long[this.nextBlockSize() + 1]);
      this.index_$eq(0);
   }

   private void hExpand() {
      if (this.hIndex() == 0) {
         this.history_$eq(new long[4][]);
      } else {
         this.history_$eq((long[][])Arrays.copyOf(this.history(), this.history().length << 1));
      }
   }

   public LongAccumulator addOne(final long a) {
      this.totalSize_$eq(this.totalSize() + 1L);
      if (this.index() + 1 >= this.current().length) {
         this.expand();
      }

      this.current()[this.index()] = a;
      this.index_$eq(this.index() + 1);
      return this;
   }

   public LongAccumulator result() {
      return this;
   }

   public void drain(final LongAccumulator that) {
      int h = 0;
      long prev = 0L;
      boolean more = true;

      while(more && h < that.hIndex()) {
         long cuml = that.cumulative(h);
         int n = (int)(cuml - prev);
         if (this.current().length - this.index() - 1 >= n) {
            System.arraycopy(that.history()[h], 0, this.current(), this.index(), n);
            prev = cuml;
            this.index_$eq(this.index() + n);
            ++h;
         } else {
            more = false;
         }
      }

      if (h >= that.hIndex() && this.current().length - this.index() - 1 >= that.index()) {
         if (that.index() > 0) {
            System.arraycopy(that.current(), 0, this.current(), this.index(), that.index());
         }

         this.index_$eq(this.index() + that.index());
      } else {
         int slots = (this.index() > 0 ? 1 : 0) + that.hIndex() - h;
         if (this.hIndex() + slots > this.history().length) {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            int n = Math.max(4, 1 << 32 - Integer.numberOfLeadingZeros(1 + this.hIndex() + slots));
            this.history_$eq((long[][])Arrays.copyOf(this.history(), n));
         }

         long pv = this.hIndex() > 0 ? this.cumulative(this.hIndex() - 1) : 0L;
         if (this.index() > 0) {
            long[] var18;
            if (this.index() < this.current().length >>> 3 && this.current().length - 1 > 32) {
               long[] ans = Arrays.copyOf(this.current(), this.index() + 1);
               ans[ans.length - 1] = this.current()[this.current().length - 1];
               var18 = ans;
            } else {
               var18 = this.current();
            }

            long[] x = var18;
            pv += (long)this.index();
            x[x.length - 1] = pv;
            this.history()[this.hIndex()] = x;
            this.hIndex_$eq(this.hIndex() + 1);
         }

         while(h < that.hIndex()) {
            long cuml = that.cumulative(h);
            pv = pv + cuml - prev;
            prev = cuml;
            long[] x = that.history()[h];
            x[x.length - 1] = pv;
            this.history()[this.hIndex()] = x;
            ++h;
            this.hIndex_$eq(this.hIndex() + 1);
         }

         this.index_$eq(that.index());
         this.current_$eq(that.current());
      }

      this.totalSize_$eq(this.totalSize() + that.totalSize());
      that.clear();
   }

   public void clear() {
      super.clear();
      this.current_$eq(LongAccumulator$.MODULE$.scala$jdk$LongAccumulator$$emptyLongArray());
      this.history_$eq(LongAccumulator$.MODULE$.scala$jdk$LongAccumulator$$emptyLongArrayArray());
   }

   public long apply(final long ix) {
      if (this.totalSize() - ix > (long)this.index() && this.hIndex() != 0) {
         long w = this.seekSlot(ix);
         return this.history()[(int)(w >>> 32)][(int)(w & 4294967295L)];
      } else {
         return this.current()[(int)(ix - (this.totalSize() - (long)this.index()))];
      }
   }

   public long apply(final int i) {
      return this.apply((long)i);
   }

   public void update(final long idx, final long elem) {
      if (this.totalSize() - idx > (long)this.index() && this.hIndex() != 0) {
         long w = this.seekSlot(idx);
         this.history()[(int)(w >>> 32)][(int)(w & 4294967295L)] = elem;
      } else {
         this.current()[(int)(idx - (this.totalSize() - (long)this.index()))] = elem;
      }
   }

   public void update(final int idx, final long elem) {
      this.update((long)idx, elem);
   }

   public Iterator iterator() {
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var10000 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      return var10000.iterator();
   }

   public void foreach(final Function1 f) {
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var10000 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var10000;

      while(s.hasStep()) {
         f.apply(s.nextStep$mcJ$sp());
      }

   }

   public LongAccumulator map(final Function1 f) {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      LongAccumulator b = new LongAccumulator();
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var6 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var6;

      while(s.hasStep()) {
         b.addOne(f.apply$mcJJ$sp(s.nextStep$mcJ$sp()));
      }

      return b;
   }

   public LongAccumulator flatMap(final Function1 f) {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      LongAccumulator b = new LongAccumulator();
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var8 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;

      Object var6;
      for(LongStepper s = (LongStepper)var8; s.hasStep(); var6 = null) {
         IterableOnce addAll_elems = (IterableOnce)f.apply(s.nextStep$mcJ$sp());
         Growable.addAll$(b, addAll_elems);
      }

      return b;
   }

   public LongAccumulator collect(final PartialFunction pf) {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      LongAccumulator b = new LongAccumulator();
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var8 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var8;

      while(s.hasStep()) {
         long n = s.nextStep$mcJ$sp();
         pf.runWith((a) -> $anonfun$collect$1(b, BoxesRunTime.unboxToLong(a))).apply$mcZJ$sp(n);
      }

      return b;
   }

   private LongAccumulator filterAccImpl(final Function1 pred, final boolean not) {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      LongAccumulator b = new LongAccumulator();
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var9 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var9;

      while(s.hasStep()) {
         long n = s.nextStep$mcJ$sp();
         if (pred.apply$mcZJ$sp(n) != not) {
            b.addOne(n);
         }
      }

      return b;
   }

   public LongAccumulator filter(final Function1 pred) {
      boolean filterAccImpl_not = false;
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      LongAccumulator filterAccImpl_b = new LongAccumulator();
      StepperShape filterAccImpl_stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var9 = ((Accumulator)this).efficientStepper(filterAccImpl_stepper_shape);
      filterAccImpl_stepper_shape = null;
      LongStepper filterAccImpl_s = (LongStepper)var9;

      while(filterAccImpl_s.hasStep()) {
         long filterAccImpl_n = filterAccImpl_s.nextStep$mcJ$sp();
         if (pred.apply$mcZJ$sp(filterAccImpl_n) != filterAccImpl_not) {
            filterAccImpl_b.addOne(filterAccImpl_n);
         }
      }

      return filterAccImpl_b;
   }

   public LongAccumulator filterNot(final Function1 pred) {
      boolean filterAccImpl_not = true;
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      LongAccumulator filterAccImpl_b = new LongAccumulator();
      StepperShape filterAccImpl_stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var9 = ((Accumulator)this).efficientStepper(filterAccImpl_stepper_shape);
      filterAccImpl_stepper_shape = null;
      LongStepper filterAccImpl_s = (LongStepper)var9;

      while(filterAccImpl_s.hasStep()) {
         long filterAccImpl_n = filterAccImpl_s.nextStep$mcJ$sp();
         if (pred.apply$mcZJ$sp(filterAccImpl_n) != filterAccImpl_not) {
            filterAccImpl_b.addOne(filterAccImpl_n);
         }
      }

      return filterAccImpl_b;
   }

   public boolean forall(final Function1 p) {
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var10000 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var10000;

      while(s.hasStep()) {
         if (!p.apply$mcZJ$sp(s.nextStep$mcJ$sp())) {
            return false;
         }
      }

      return true;
   }

   public boolean exists(final Function1 p) {
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var10000 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var10000;

      while(s.hasStep()) {
         if (p.apply$mcZJ$sp(s.nextStep$mcJ$sp())) {
            return true;
         }
      }

      return false;
   }

   public int count(final Function1 p) {
      int r = 0;
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var10000 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var10000;

      while(s.hasStep()) {
         if (p.apply$mcZJ$sp(s.nextStep$mcJ$sp())) {
            ++r;
         }
      }

      return r;
   }

   public long countLong(final Function1 p) {
      long r = 0L;
      StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
      Stepper var10000 = ((Accumulator)this).efficientStepper(stepper_shape);
      stepper_shape = null;
      LongStepper s = (LongStepper)var10000;

      while(s.hasStep()) {
         if (p.apply$mcZJ$sp(s.nextStep$mcJ$sp())) {
            ++r;
         }
      }

      return r;
   }

   public long[] toArray() {
      if (this.totalSize() > 2147483647L) {
         throw new IllegalArgumentException((new StringBuilder(44)).append("Too many elements accumulated for an array: ").append(Long.toString(this.totalSize())).toString());
      } else {
         long[] a = new long[(int)this.totalSize()];
         int j = 0;
         int h = 0;

         for(long pv = 0L; h < this.hIndex(); ++h) {
            long[] x = this.history()[h];
            long cuml = x[x.length - 1];
            int n = (int)(cuml - pv);
            pv = cuml;
            System.arraycopy(x, 0, a, j, n);
            j += n;
         }

         System.arraycopy(this.current(), 0, a, j, this.index());
         this.index();
         return a;
      }
   }

   public List toList() {
      List ans = Nil$.MODULE$;

      for(int i = this.index() - 1; i >= 0; --i) {
         long var3 = this.current()[i];
         ans = ans.$colon$colon(var3);
      }

      for(int h = this.hIndex() - 1; h >= 0; --h) {
         long[] a = this.history()[h];

         for(int var9 = (int)(this.cumulative(h) - (h == 0 ? 0L : this.cumulative(h - 1))) - 1; var9 >= 0; --var9) {
            long var7 = a[var9];
            ans = ans.$colon$colon(var7);
         }
      }

      return ans;
   }

   public Object to(final Factory factory) {
      if (this.totalSize() > 2147483647L) {
         throw new IllegalArgumentException((new StringBuilder(54)).append("Too many elements accumulated for a Scala collection: ").append(Long.toString(this.totalSize())).toString());
      } else {
         return factory.fromSpecific(this.iterator());
      }
   }

   public LongAccumulator fromSpecific(final IterableOnce coll) {
      return LongAccumulator$.MODULE$.fromSpecific(coll);
   }

   public LongAccumulator newSpecificBuilder() {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      return new LongAccumulator();
   }

   public SeqFactory iterableFactory() {
      return AnyAccumulator$.MODULE$;
   }

   public LongAccumulator empty() {
      LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
      return new LongAccumulator();
   }

   private Object writeReplace() {
      return new SerializationProxy(this);
   }

   public long apply$mcJI$sp(final int i) {
      return this.apply((long)i);
   }

   // $FF: synthetic method
   public static final String $anonfun$efficientStepper$1(final StepperShape shape$1) {
      return (new StringBuilder(25)).append("unexpected StepperShape: ").append(shape$1).toString();
   }

   // $FF: synthetic method
   public static final LongAccumulator $anonfun$collect$1(final LongAccumulator b$1, final long a) {
      return b$1.addOne(a);
   }

   public LongAccumulator() {
      this.current = LongAccumulator$.MODULE$.scala$jdk$LongAccumulator$$emptyLongArray();
      this.history = LongAccumulator$.MODULE$.scala$jdk$LongAccumulator$$emptyLongArrayArray();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SerializationProxy implements Serializable {
      private final transient LongAccumulator acc;
      private transient LongAccumulator result;

      private LongAccumulator acc() {
         return this.acc;
      }

      private LongAccumulator result() {
         return this.result;
      }

      private void result_$eq(final LongAccumulator x$1) {
         this.result = x$1;
      }

      private void writeObject(final ObjectOutputStream out) {
         out.defaultWriteObject();
         LongAccumulator var10000 = this.acc();
         if (var10000 == null) {
            throw null;
         } else {
            Accumulator sizeLong_this = var10000;
            long var9 = sizeLong_this.totalSize();
            sizeLong_this = null;
            long size = var9;
            out.writeLong(size);
            LongAccumulator var10 = this.acc();
            StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
            if (var10 == null) {
               throw null;
            } else {
               Stepper var11 = ((Accumulator)var10).efficientStepper(stepper_shape);
               stepper_shape = null;
               LongStepper st = (LongStepper)var11;

               while(st.hasStep()) {
                  out.writeLong(st.nextStep$mcJ$sp());
               }

            }
         }
      }

      private void readObject(final ObjectInputStream in) {
         in.defaultReadObject();
         LongAccumulator res = new LongAccumulator();

         for(long elems = in.readLong(); elems > 0L; --elems) {
            long boxToLong_l = in.readLong();
            res.addOne(boxToLong_l);
         }

         this.result_$eq(res);
      }

      private Object readResolve() {
         return this.result();
      }

      public SerializationProxy(final LongAccumulator acc) {
         this.acc = acc;
      }
   }
}
