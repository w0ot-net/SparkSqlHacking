package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps$;
import scala.collection.BitSetOps;
import scala.collection.BitSetOps$;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SortedIterableFactory;
import scala.collection.SortedOps;
import scala.collection.SortedSetFactoryDefaults;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005b!\u0002!B\u0003CA\u0005\"B5\u0001\t\u0003Q\u0007\"B6\u0001\t\u0003b\u0007\"\u00029\u0001\t#\n\b\"B<\u0001\t#B\bBB@\u0001\t\u0003\n\t\u0001C\u0004\u0002\u0004\u0001!\t!!\u0002\t\u0011\u0005U\u0003\u0001\"\u0005D\u0005/BqAa\u0017\u0001\t\u0003\u0011i\u0006C\u0004\u0003d\u0001!\tA!\u001a\t\u000f\u0005]\u0004A\"\u0005\u0003j!9!q\u000e\u0001\u0005B\tE\u0004b\u0002B8\u0001\u0011\u0005#\u0011\u0010\u0005\b\u0005g\u0003A\u0011\tB[\u0011\u001d\u0011\u0019\f\u0001C!\u0005wCqAa5\u0001\t\u0003\u0012)\u000eC\u0004\u0003T\u0002!\tE!9\t\u000f\t]\b\u0001\"\u0011\u0003z\"A!1\t\u0001!\n#\u0019ibB\u0004\u0002\n\u0005C\t!a\u0003\u0007\r\u0001\u000b\u0005\u0012AA\u0007\u0011\u0019IG\u0003\"\u0001\u0002*!1\u0001\u000f\u0006C\u0001\u0003WA\u0001b \u000bC\u0002\u0013\u0015\u0011\u0011\u0001\u0005\b\u0003c!\u0002\u0015!\u0004Y\u0011\u0019\t\u0019\u0004\u0006C\u0001q\"9\u0011Q\u0007\u000b\u0005\n\u0005]\u0002bBA$)\u0011\u0005\u0011\u0011\n\u0005\b\u0003+\"B\u0011AA,\r\u0019\tY\u0006\u0006\u0001\u0002^!Q\u0011QJ\u000f\u0003\u0006\u0004%\t!a\u0018\t\u0015\u0005\u0005TD!A!\u0002\u0013\ti\u0004\u0003\u0004j;\u0011\u0005\u00111\r\u0005\t\u0003WjB\u0011C\"\u0002n!A\u0011qN\u000f\u0005\u0012\r\u000b\t\b\u0003\u0005\u0002xu!\tbQA=\u0011\u001d\t\t)\bC!\u0003\u0007Cq!!$\u001e\t\u0003\nyI\u0002\u0004\u0002:R\u0001\u00111\u0018\u0005\u000b\u0003{3#Q1A\u0005\u0002\u0005}\u0003BCA`M\t\u0005\t\u0015!\u0003\u0002>!Q\u0011\u0011\u0019\u0014\u0003\u0006\u0004%\t!a\u0018\t\u0015\u0005\rgE!A!\u0002\u0013\ti\u0004\u0003\u0004jM\u0011\u0005\u0011Q\u0019\u0005\t\u0003W2C\u0011C\"\u0002n!A\u0011q\u000e\u0014\u0005\u0012\r\u000bi\r\u0003\u0005\u0002x\u0019\"\tbQAi\u0011\u001d\t\tI\nC!\u0003/Dq!!$'\t\u0003\nYN\u0002\u0004\u0002dR\u0001\u0011Q\u001d\u0005\u000b\u0003\u001b\n$Q1A\u0005\u0002\u0005\u001d\bBCA1c\t\u0005\t\u0015!\u0003\u0002P!1\u0011.\rC\u0001\u0003SD\u0001\"a\u001b2\t#\u0019\u0015Q\u000e\u0005\t\u0003_\nD\u0011C\"\u0002p\"A\u0011qO\u0019\u0005\u0012\r\u000b\u0019\u0010C\u0004\u0002\u0002F\"\t%!?\t\u000f\u00055\u0015\u0007\"\u0011\u0002\u0000\"9!QA\u0019\u0005B\u0005\u001dhA\u0002B\u0005)\u0019\u0011Y\u0001\u0003\u0006tw\t\u0005\t\u0015!\u0003Y\u0005KAa![\u001e\u0005\u0002\t\u001d\u0002\u0002\u0003B\u0017w\u0001&\tBa\f\t\u0013\t\rC#!A\u0005\n\t\u0015#A\u0002\"jiN+GO\u0003\u0002C\u0007\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003\t\u0016\u000b!bY8mY\u0016\u001cG/[8o\u0015\u00051\u0015!B:dC2\f7\u0001A\n\t\u0001%\u000bF+\u0017/`EB\u0019!jS'\u000e\u0003\u0005K!\u0001T!\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV\r\u001e\t\u0003\u001d>k\u0011!R\u0005\u0003!\u0016\u00131!\u00138u!\rQ%+T\u0005\u0003'\u0006\u0013\u0011bU8si\u0016$7+\u001a;\u0011\u000b)+Vj\u0016-\n\u0005Y\u000b%\u0001D*peR,GmU3u\u001fB\u001c\bC\u0001&S!\tQ\u0005\u0001E\u0003K56;\u0006,\u0003\u0002\\\u0003\nY2\u000b\u001e:jGR|\u0005\u000f^5nSj,GmU8si\u0016$7+\u001a;PaN\u0004\"!\u00180\u000e\u0003\rK!\u0001Q\"\u0011\u0007u\u0003\u0007,\u0003\u0002b\u0007\nI!)\u001b;TKR|\u0005o\u001d\t\u0003G\u001at!A\u00143\n\u0005\u0015,\u0015a\u00029bG.\fw-Z\u0005\u0003O\"\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!!Z#\u0002\rqJg.\u001b;?)\u0005A\u0016\u0001C;og>\u0014H/\u001a3\u0016\u00035\u00042A\u00138N\u0013\ty\u0017IA\u0002TKR\fAB\u001a:p[N\u0003XmY5gS\u000e$\"\u0001\u0017:\t\u000bM\u001c\u0001\u0019\u0001;\u0002\t\r|G\u000e\u001c\t\u0004;Vl\u0015B\u0001<D\u00051IE/\u001a:bE2,wJ\\2f\u0003IqWm^*qK\u000eLg-[2Ck&dG-\u001a:\u0016\u0003e\u0004BA_?N16\t1P\u0003\u0002}\u0007\u00069Q.\u001e;bE2,\u0017B\u0001@|\u0005\u001d\u0011U/\u001b7eKJ\fQ!Z7qif,\u0012\u0001W\u0001\u000eE&$8+\u001a;GC\u000e$xN]=\u0016\u0005\u0005\u001daB\u0001&\u0014\u0003\u0019\u0011\u0015\u000e^*fiB\u0011!\nF\n\b)\u0005=\u0011QCA\u000e!\rq\u0015\u0011C\u0005\u0004\u0003')%AB!osJ+g\rE\u0003^\u0003/i\u0005,C\u0002\u0002\u001a\r\u0013qc\u00159fG&4\u0017nY%uKJ\f'\r\\3GC\u000e$xN]=\u0011\t\u0005u\u0011qE\u0007\u0003\u0003?QA!!\t\u0002$\u0005\u0011\u0011n\u001c\u0006\u0003\u0003K\tAA[1wC&\u0019q-a\b\u0015\u0005\u0005-Ac\u0001-\u0002.!1\u0011q\u0006\fA\u0002Q\f!!\u001b;\u0002\r\u0015l\u0007\u000f^=!\u0003)qWm\u001e\"vS2$WM]\u0001\fGJ,\u0017\r^3T[\u0006dG\u000eF\u0003Y\u0003s\t\u0019\u0005C\u0004\u0002<i\u0001\r!!\u0010\u0002\u0003\u0005\u00042ATA \u0013\r\t\t%\u0012\u0002\u0005\u0019>tw\rC\u0004\u0002Fi\u0001\r!!\u0010\u0002\u0003\t\f1B\u001a:p[\nKG/T1tWR\u0019\u0001,a\u0013\t\u000f\u000553\u00041\u0001\u0002P\u0005)Q\r\\3ngB)a*!\u0015\u0002>%\u0019\u00111K#\u0003\u000b\u0005\u0013(/Y=\u0002#\u0019\u0014x.\u001c\"ji6\u000b7o\u001b(p\u0007>\u0004\u0018\u0010F\u0002Y\u00033Bq!!\u0014\u001d\u0001\u0004\tyEA\u0004CSR\u001cV\r^\u0019\u0014\u0005uAVCAA\u001f\u0003\u0019)G.Z7tAQ!\u0011QMA5!\r\t9'H\u0007\u0002)!9\u0011Q\n\u0011A\u0002\u0005u\u0012A\u00028x_J$7/F\u0001N\u0003\u00119xN\u001d3\u0015\t\u0005u\u00121\u000f\u0005\u0007\u0003k\u0012\u0003\u0019A'\u0002\u0007%$\u00070\u0001\u0006va\u0012\fG/Z,pe\u0012$R\u0001WA>\u0003{Ba!!\u001e$\u0001\u0004i\u0005bBA@G\u0001\u0007\u0011QH\u0001\u0002o\u0006!A-\u001b4g)\rA\u0016Q\u0011\u0005\b\u0003\u000f#\u0003\u0019AAE\u0003\u0015yG\u000f[3s!\u0011i\u00161R'\n\u0005=\u001c\u0015A\u00034jYR,'/S7qYR)\u0001,!%\u0002\"\"9\u00111S\u0013A\u0002\u0005U\u0015\u0001\u00029sK\u0012\u0004bATAL\u001b\u0006m\u0015bAAM\u000b\nIa)\u001e8di&|g.\r\t\u0004\u001d\u0006u\u0015bAAP\u000b\n9!i\\8mK\u0006t\u0007bBARK\u0001\u0007\u00111T\u0001\nSN4E.\u001b9qK\u0012D3\"HAT\u0003[\u000by+a-\u00026B\u0019a*!+\n\u0007\u0005-VI\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u00022\u0006\u0001\u0015*\u001c9mK6,g\u000e^1uS>t\u0007e\u00197bgN,7\u000fI8gA\tKGoU3uAMDw.\u001e7eA9|G\u000f\t2fA\u0005\u001c7-Z:tK\u0012\u0004C-\u001b:fGRd\u00170A\u0003tS:\u001cW-\t\u0002\u00028\u00061!GL\u00194]A\u0012qAQ5u'\u0016$(g\u0005\u0002'1\u00061Q\r\\3ngB\nq!\u001a7f[N\u0004\u0004%\u0001\u0004fY\u0016l7/M\u0001\bK2,Wn]\u0019!)\u0019\t9-!3\u0002LB\u0019\u0011q\r\u0014\t\u000f\u0005u6\u00061\u0001\u0002>!9\u0011\u0011Y\u0016A\u0002\u0005uB\u0003BA\u001f\u0003\u001fDa!!\u001e.\u0001\u0004iE#\u0002-\u0002T\u0006U\u0007BBA;]\u0001\u0007Q\nC\u0004\u0002\u00009\u0002\r!!\u0010\u0015\u0007a\u000bI\u000eC\u0004\u0002\b>\u0002\r!!#\u0015\u000ba\u000bi.a8\t\u000f\u0005M\u0005\u00071\u0001\u0002\u0016\"9\u00111\u0015\u0019A\u0002\u0005m\u0005f\u0003\u0014\u0002(\u00065\u0016qVAZ\u0003k\u0013qAQ5u'\u0016$hj\u0005\u000221V\u0011\u0011q\n\u000b\u0005\u0003W\fi\u000fE\u0002\u0002hEBq!!\u00145\u0001\u0004\ty\u0005\u0006\u0003\u0002>\u0005E\bBBA;m\u0001\u0007Q\nF\u0003Y\u0003k\f9\u0010\u0003\u0004\u0002v]\u0002\r!\u0014\u0005\b\u0003\u007f:\u0004\u0019AA\u001f)\rA\u00161 \u0005\b\u0003{D\u0004\u0019AAE\u0003\u0011!\b.\u0019;\u0015\u000ba\u0013\tAa\u0001\t\u000f\u0005M\u0015\b1\u0001\u0002\u0016\"9\u00111U\u001dA\u0002\u0005m\u0015!\u0003;p\u0005&$X*Y:lQ-\t\u0014qUAW\u0003_\u000b\u0019,!.\u0003%M+'/[1mSj\fG/[8o!J|\u00070_\n\u0004w\t5\u0001\u0003\u0002B\b\u0005CqAA!\u0005\u0003 9!!1\u0003B\u000f\u001d\u0011\u0011)Ba\u0007\u000e\u0005\t]!b\u0001B\r\u000f\u00061AH]8pizJ\u0011AR\u0005\u0003\t\u0016K1!!\u0003D\u0013\u0011\u0011IAa\t\u000b\u0007\u0005%1)C\u0002t\u0005C!BA!\u000b\u0003,A\u0019\u0011qM\u001e\t\u000bMl\u0004\u0019\u0001-\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0005c\u00012A\u0014B\u001a\u0013\r\u0011)$\u0012\u0002\u0004\u0003:L\bfB\u001e\u0003:\t}\"\u0011\t\t\u0004\u001d\nm\u0012b\u0001B\u001f\u000b\n\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\t\t\u0005\u0005\u0013\u0012y%\u0004\u0002\u0003L)!!QJA\u0012\u0003\u0011a\u0017M\\4\n\t\tE#1\n\u0002\u0007\u001f\nTWm\u0019;)\u000fQ\u0011IDa\u0010\u0003B!:1C!\u000f\u0003@\t\u0005Cc\u0001-\u0003Z!9\u0011QJ\u0004A\u0002\u0005=\u0013\u0001B5oG2$2\u0001\u0017B0\u0011\u0019\u0011\t\u0007\u0003a\u0001\u001b\u0006!Q\r\\3n\u0003\u0011)\u0007p\u00197\u0015\u0007a\u00139\u0007\u0003\u0004\u0003b%\u0001\r!\u0014\u000b\u00061\n-$Q\u000e\u0005\u0007\u0003kR\u0001\u0019A'\t\u000f\u0005}$\u00021\u0001\u0002>\u0005\u0019Q.\u00199\u0015\u0007a\u0013\u0019\bC\u0004\u0003v-\u0001\rAa\u001e\u0002\u0003\u0019\u0004RATAL\u001b6+BAa\u001f\u0003\u0006R!!Q\u0010BX)\u0011\u0011yH!%\u0011\t)\u0013&\u0011\u0011\t\u0005\u0005\u0007\u0013)\t\u0004\u0001\u0005\u000f\t\u001dEB1\u0001\u0003\n\n\t!)\u0005\u0003\u0003\f\nE\u0002c\u0001(\u0003\u000e&\u0019!qR#\u0003\u000f9{G\u000f[5oO\"9!1\u0013\u0007A\u0004\tU\u0015AA3w!\u0015\u0019'q\u0013BA\u0013\r\u0011I\n\u001b\u0002\t\u001fJ$WM]5oO\"B!\u0011\u0013BO\u0005S\u0013Y\u000b\u0005\u0003\u0003 \n\u0015VB\u0001BQ\u0015\r\u0011\u0019+R\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002BT\u0005C\u0013\u0001#[7qY&\u001c\u0017\u000e\u001e(pi\u001a{WO\u001c3\u0002\u00075\u001cx-\t\u0002\u0003.\u0006qhj\u001c\u0011j[Bd\u0017nY5uA=\u0013H-\u001a:j]\u001e\\Fe\u001f\"~;\u00022w.\u001e8eAQ|\u0007EY;jY\u0012\u0004\u0013\rI*peR,GmU3u7\u0012Z()`//Ae{W\u000fI7bs\u0002:\u0018M\u001c;!i>\u0004S\u000f]2bgR\u0004Co\u001c\u0011bAM+GoW%oiv\u0003c-\u001b:ti\u0002\u0012\u0017\u0010I2bY2Lgn\u001a\u0011ak:\u001cxN\u001d;fI\u0002t\u0003b\u0002B;\u0019\u0001\u0007!\u0011\u0017\t\u0007\u001d\u0006]UJ!!\u0002\u000f\u0019d\u0017\r^'baR\u0019\u0001La.\t\u000f\tUT\u00021\u0001\u0003:B)a*a&NiV!!Q\u0018Bc)\u0011\u0011yL!4\u0015\t\t\u0005'q\u0019\t\u0005\u0015J\u0013\u0019\r\u0005\u0003\u0003\u0004\n\u0015Ga\u0002BD\u001d\t\u0007!\u0011\u0012\u0005\b\u0005's\u00019\u0001Be!\u0015\u0019'q\u0013BbQ!\u00119M!(\u0003*\n-\u0006b\u0002B;\u001d\u0001\u0007!q\u001a\t\u0007\u001d\u0006]UJ!5\u0011\tu+(1Y\u0001\bG>dG.Z2u)\rA&q\u001b\u0005\b\u00053|\u0001\u0019\u0001Bn\u0003\t\u0001h\rE\u0003O\u0005;lU*C\u0002\u0003`\u0016\u0013q\u0002U1si&\fGNR;oGRLwN\\\u000b\u0005\u0005G\u0014Y\u000f\u0006\u0003\u0003f\nMH\u0003\u0002Bt\u0005[\u0004BA\u0013*\u0003jB!!1\u0011Bv\t\u001d\u00119\t\u0005b\u0001\u0005\u0013CqAa%\u0011\u0001\b\u0011y\u000fE\u0003d\u0005/\u0013I\u000f\u000b\u0005\u0003n\nu%\u0011\u0016BV\u0011\u001d\u0011I\u000e\u0005a\u0001\u0005k\u0004bA\u0014Bo\u001b\n%\u0018a\u0001>jaV!!1`B\u0005)\u0011\u0011ip!\u0006\u0015\t\t}81\u0002\t\u0005\u0015J\u001b\t\u0001\u0005\u0004O\u0007\u0007i5qA\u0005\u0004\u0007\u000b)%A\u0002+va2,'\u0007\u0005\u0003\u0003\u0004\u000e%Aa\u0002BD#\t\u0007!\u0011\u0012\u0005\b\u0005'\u000b\u00029AB\u0007!\u0015\u0019'qSB\u0001Q!\u0019YA!(\u0003*\u000eE\u0011EAB\n\u0003\u0005-aj\u001c\u0011j[Bd\u0017nY5uA=\u0013H-\u001a:j]\u001e\\Fe\u001f\"~;\u00022w.\u001e8eAQ|\u0007EY;jY\u0012\u0004\u0013\rI*peR,GmU3u7\"Je\u000e\u001e\u0017!Im\u0014U0K//Ae{W\u000fI7bs\u0002:\u0018M\u001c;!i>\u0004S\u000f]2bgR\u0004Co\u001c\u0011bAM+GoW%oiv\u0003c-\u001b:ti\u0002\u0012\u0017\u0010I2bY2Lgn\u001a\u0011ak:\u001cxN\u001d;fI\u0002t\u0003bBA\u007f#\u0001\u00071q\u0003\t\u0007\u00073\u0019Yba\u0002\u000f\u0007\tMA-\u0003\u0002wQR\u0011\u0011qB\u0015\u0005\u0001u1\u0013\u0007"
)
public abstract class BitSet extends AbstractSet implements SortedSet, StrictOptimizedSortedSetOps, scala.collection.BitSet, Serializable {
   public static BitSet fromBitMask(final long[] elems) {
      return BitSet$.MODULE$.fromBitMask(elems);
   }

   public static Builder newBuilder() {
      return BitSet$.MODULE$.newBuilder();
   }

   public static Factory specificIterableFactory() {
      return BitSet$.MODULE$;
   }

   public static Object fill(final int n, final Function0 elem) {
      BitSet$ fill_this = BitSet$.MODULE$;
      IterableOnce fromSpecific_it = new View.Fill(n, elem);
      return fill_this.fromSpecific(fromSpecific_it);
   }

   public String stringPrefix() {
      return scala.collection.BitSet.stringPrefix$(this);
   }

   // $FF: synthetic method
   public int scala$collection$BitSetOps$$super$max(final Ordering ord) {
      return BoxesRunTime.unboxToInt(scala.collection.SortedSetOps.max$(this, ord));
   }

   // $FF: synthetic method
   public int scala$collection$BitSetOps$$super$min(final Ordering ord) {
      return BoxesRunTime.unboxToInt(scala.collection.SortedSetOps.min$(this, ord));
   }

   // $FF: synthetic method
   public scala.collection.BitSet scala$collection$BitSetOps$$super$concat(final IterableOnce that) {
      return (scala.collection.BitSet)StrictOptimizedSetOps.concat$(this, that);
   }

   // $FF: synthetic method
   public scala.collection.BitSet scala$collection$BitSetOps$$super$intersect(final scala.collection.Set that) {
      return (scala.collection.BitSet)scala.collection.SetOps.intersect$(this, that);
   }

   // $FF: synthetic method
   public scala.collection.BitSet scala$collection$BitSetOps$$super$diff(final scala.collection.Set that) {
      return (scala.collection.BitSet)SetOps.diff$(this, that);
   }

   public final Ordering ordering() {
      return BitSetOps.ordering$(this);
   }

   public boolean contains(final int elem) {
      return BitSetOps.contains$(this, elem);
   }

   public Iterator iterator() {
      return BitSetOps.iterator$(this);
   }

   public Iterator iteratorFrom(final int start) {
      return BitSetOps.iteratorFrom$(this, start);
   }

   public Stepper stepper(final StepperShape shape) {
      return BitSetOps.stepper$(this, shape);
   }

   public int size() {
      return BitSetOps.size$(this);
   }

   public boolean isEmpty() {
      return BitSetOps.isEmpty$(this);
   }

   public int max(final Ordering ord) {
      return BitSetOps.max$(this, ord);
   }

   public int min(final Ordering ord) {
      return BitSetOps.min$(this, ord);
   }

   public void foreach(final Function1 f) {
      BitSetOps.foreach$(this, f);
   }

   public long[] toBitMask() {
      return BitSetOps.toBitMask$(this);
   }

   public scala.collection.BitSet rangeImpl(final Option from, final Option until) {
      return BitSetOps.rangeImpl$(this, from, until);
   }

   public scala.collection.BitSet concat(final IterableOnce other) {
      return BitSetOps.concat$(this, other);
   }

   public scala.collection.BitSet intersect(final scala.collection.Set other) {
      return BitSetOps.intersect$(this, other);
   }

   public scala.collection.BitSet diff(final scala.collection.Set other) {
      return BitSetOps.diff$(this, other);
   }

   public scala.collection.BitSet xor(final scala.collection.BitSet other) {
      return BitSetOps.xor$(this, other);
   }

   public final scala.collection.BitSet $up(final scala.collection.BitSet other) {
      return BitSetOps.$up$(this, other);
   }

   public Tuple2 partition(final Function1 p) {
      return BitSetOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public SortedIterableFactory sortedIterableFactory() {
      return SortedSet.sortedIterableFactory$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$SortedSet$$super$equals(final Object that) {
      return scala.collection.Set.equals$(this, that);
   }

   public boolean equals(final Object that) {
      return scala.collection.SortedSet.equals$(this, that);
   }

   public scala.collection.SortedSetOps.WithFilter withFilter(final Function1 p) {
      return SortedSetFactoryDefaults.withFilter$(this, p);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   /** @deprecated */
   public Iterator keysIteratorFrom(final Object start) {
      return scala.collection.SortedSetOps.keysIteratorFrom$(this, start);
   }

   public Object firstKey() {
      return scala.collection.SortedSetOps.firstKey$(this);
   }

   public Object lastKey() {
      return scala.collection.SortedSetOps.lastKey$(this);
   }

   public Option minAfter(final Object key) {
      return scala.collection.SortedSetOps.minAfter$(this, key);
   }

   public Option maxBefore(final Object key) {
      return scala.collection.SortedSetOps.maxBefore$(this, key);
   }

   public scala.collection.SortedSetOps rangeTo(final Object to) {
      return scala.collection.SortedSetOps.rangeTo$(this, to);
   }

   /** @deprecated */
   public int compare(final Object k0, final Object k1) {
      return SortedOps.compare$(this, k0, k1);
   }

   public Object range(final Object from, final Object until) {
      return SortedOps.range$(this, from, until);
   }

   /** @deprecated */
   public final Object from(final Object from) {
      return SortedOps.from$(this, from);
   }

   public Object rangeFrom(final Object from) {
      return SortedOps.rangeFrom$(this, from);
   }

   /** @deprecated */
   public final Object until(final Object until) {
      return SortedOps.until$(this, until);
   }

   public Object rangeUntil(final Object until) {
      return SortedOps.rangeUntil$(this, until);
   }

   /** @deprecated */
   public final Object to(final Object to) {
      return SortedOps.to$(this, to);
   }

   public Set unsorted() {
      return this;
   }

   public BitSet fromSpecific(final IterableOnce coll) {
      return this.bitSetFactory().fromSpecific(coll);
   }

   public Builder newSpecificBuilder() {
      return this.bitSetFactory().newBuilder();
   }

   public BitSet empty() {
      return this.bitSetFactory().empty();
   }

   public BitSet$ bitSetFactory() {
      return BitSet$.MODULE$;
   }

   public BitSet fromBitMaskNoCopy(final long[] elems) {
      return BitSet$.MODULE$.fromBitMaskNoCopy(elems);
   }

   public BitSet incl(final int elem) {
      if (elem < 0) {
         throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append("bitset element must be >= 0").toString());
      } else if (this.contains(elem)) {
         return this;
      } else {
         int idx = elem >> 6;
         return this.updateWord(idx, this.word(idx) | 1L << elem);
      }
   }

   public BitSet excl(final int elem) {
      if (elem < 0) {
         throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append("bitset element must be >= 0").toString());
      } else if (this.contains(elem)) {
         int idx = elem >> 6;
         return this.updateWord(idx, this.word(idx) & ~(1L << elem));
      } else {
         return this;
      }
   }

   public abstract BitSet updateWord(final int idx, final long w);

   public BitSet map(final Function1 f) {
      Builder strictOptimizedMap_b = this.newSpecificBuilder();

      Object var5;
      for(Iterator strictOptimizedMap_it = this.iterator(); strictOptimizedMap_it.hasNext(); var5 = null) {
         var5 = f.apply(strictOptimizedMap_it.next());
         if (strictOptimizedMap_b == null) {
            throw null;
         }

         strictOptimizedMap_b.addOne(var5);
      }

      return (BitSet)strictOptimizedMap_b.result();
   }

   public SortedSet map(final Function1 f, final Ordering ev) {
      return (SortedSet)scala.collection.StrictOptimizedSortedSetOps.map$(this, f, ev);
   }

   public BitSet flatMap(final Function1 f) {
      Builder strictOptimizedFlatMap_b = this.newSpecificBuilder();

      Object var5;
      for(Iterator strictOptimizedFlatMap_it = this.iterator(); strictOptimizedFlatMap_it.hasNext(); var5 = null) {
         IterableOnce strictOptimizedFlatMap_$plus$plus$eq_elems = (IterableOnce)f.apply(strictOptimizedFlatMap_it.next());
         if (strictOptimizedFlatMap_b == null) {
            throw null;
         }

         strictOptimizedFlatMap_b.addAll(strictOptimizedFlatMap_$plus$plus$eq_elems);
      }

      return (BitSet)strictOptimizedFlatMap_b.result();
   }

   public SortedSet flatMap(final Function1 f, final Ordering ev) {
      return (SortedSet)scala.collection.StrictOptimizedSortedSetOps.flatMap$(this, f, ev);
   }

   public BitSet collect(final PartialFunction pf) {
      Builder strictOptimizedCollect_b = this.newSpecificBuilder();
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = this.iterator();

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = strictOptimizedCollect_it.next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, StrictOptimizedIterableOps::$anonfun$strictOptimizedCollect$1);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            if (strictOptimizedCollect_b == null) {
               throw null;
            }

            strictOptimizedCollect_b.addOne(strictOptimizedCollect_v);
         }
      }

      return (BitSet)strictOptimizedCollect_b.result();
   }

   public SortedSet collect(final PartialFunction pf, final Ordering ev) {
      return (SortedSet)scala.collection.StrictOptimizedSortedSetOps.collect$(this, pf, ev);
   }

   public SortedSet zip(final IterableOnce that, final Ordering ev) {
      return (SortedSet)scala.collection.StrictOptimizedSortedSetOps.zip$(this, that, ev);
   }

   public Object writeReplace() {
      return new SerializationProxy(this);
   }

   // $FF: synthetic method
   public static final String $anonfun$incl$1() {
      return "bitset element must be >= 0";
   }

   // $FF: synthetic method
   public static final String $anonfun$excl$1() {
      return "bitset element must be >= 0";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   /** @deprecated */
   public static class BitSet1 extends BitSet {
      private final long elems;

      public long elems() {
         return this.elems;
      }

      public int nwords() {
         return 1;
      }

      public long word(final int idx) {
         return idx == 0 ? this.elems() : 0L;
      }

      public BitSet updateWord(final int idx, final long w) {
         if (idx == 0) {
            return new BitSet1(w);
         } else {
            return idx == 1 ? BitSet$.MODULE$.scala$collection$immutable$BitSet$$createSmall(this.elems(), w) : this.fromBitMaskNoCopy(BitSetOps$.MODULE$.updateArray(new long[]{this.elems()}, idx, w));
         }
      }

      public BitSet diff(final scala.collection.Set other) {
         if (other instanceof scala.collection.BitSet) {
            scala.collection.BitSet var2 = (scala.collection.BitSet)other;
            switch (var2.nwords()) {
               case 0:
                  return this;
               default:
                  long newElems = this.elems() & ~var2.word(0);
                  return (BitSet)(newElems == 0L ? this.empty() : new BitSet1(newElems));
            }
         } else {
            return (BitSet)BitSetOps.diff$(this, other);
         }
      }

      public BitSet filterImpl(final Function1 pred, final boolean isFlipped) {
         BitSetOps$ var10000 = BitSetOps$.MODULE$;
         long var15 = this.elems();
         byte computeWordForFilter_wordIndex = 0;
         long computeWordForFilter_oldWord = var15;
         if (computeWordForFilter_oldWord == 0L) {
            var15 = 0L;
         } else {
            long computeWordForFilter_w = computeWordForFilter_oldWord;
            int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(computeWordForFilter_oldWord);
            long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
            int computeWordForFilter_j = computeWordForFilter_wordIndex * 64 + computeWordForFilter_trailingZeroes;

            for(int computeWordForFilter_maxJ = (computeWordForFilter_wordIndex + 1) * 64 - Long.numberOfLeadingZeros(computeWordForFilter_oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
               if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                  computeWordForFilter_w &= ~computeWordForFilter_jmask;
               }

               computeWordForFilter_jmask <<= 1;
            }

            var15 = computeWordForFilter_w;
         }

         long _elems = var15;
         return (BitSet)(_elems == 0L ? this.empty() : new BitSet1(_elems));
      }

      public BitSet1(final long elems) {
         this.elems = elems;
      }
   }

   /** @deprecated */
   public static class BitSet2 extends BitSet {
      private final long elems0;
      private final long elems1;

      public long elems0() {
         return this.elems0;
      }

      public long elems1() {
         return this.elems1;
      }

      public int nwords() {
         return 2;
      }

      public long word(final int idx) {
         if (idx == 0) {
            return this.elems0();
         } else {
            return idx == 1 ? this.elems1() : 0L;
         }
      }

      public BitSet updateWord(final int idx, final long w) {
         if (idx == 0) {
            return new BitSet2(w, this.elems1());
         } else {
            return idx == 1 ? BitSet$.MODULE$.scala$collection$immutable$BitSet$$createSmall(this.elems0(), w) : this.fromBitMaskNoCopy(BitSetOps$.MODULE$.updateArray(new long[]{this.elems0(), this.elems1()}, idx, w));
         }
      }

      public BitSet diff(final scala.collection.Set other) {
         if (other instanceof scala.collection.BitSet) {
            scala.collection.BitSet var2 = (scala.collection.BitSet)other;
            switch (var2.nwords()) {
               case 0:
                  return this;
               case 1:
                  return new BitSet2(this.elems0() & ~var2.word(0), this.elems1());
               default:
                  long _elems0 = this.elems0() & ~var2.word(0);
                  long _elems1 = this.elems1() & ~var2.word(1);
                  if (_elems1 == 0L) {
                     return (BitSet)(_elems0 == 0L ? this.empty() : new BitSet1(_elems0));
                  } else {
                     return new BitSet2(_elems0, _elems1);
                  }
            }
         } else {
            return (BitSet)BitSetOps.diff$(this, other);
         }
      }

      public BitSet filterImpl(final Function1 pred, final boolean isFlipped) {
         BitSetOps$ var10000 = BitSetOps$.MODULE$;
         long var27 = this.elems0();
         byte computeWordForFilter_wordIndex = 0;
         long computeWordForFilter_oldWord = var27;
         if (computeWordForFilter_oldWord == 0L) {
            var27 = 0L;
         } else {
            long computeWordForFilter_w = computeWordForFilter_oldWord;
            int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(computeWordForFilter_oldWord);
            long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
            int computeWordForFilter_j = computeWordForFilter_wordIndex * 64 + computeWordForFilter_trailingZeroes;

            for(int computeWordForFilter_maxJ = (computeWordForFilter_wordIndex + 1) * 64 - Long.numberOfLeadingZeros(computeWordForFilter_oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
               if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                  computeWordForFilter_w &= ~computeWordForFilter_jmask;
               }

               computeWordForFilter_jmask <<= 1;
            }

            var27 = computeWordForFilter_w;
         }

         long _elems0 = var27;
         BitSetOps$ var29 = BitSetOps$.MODULE$;
         long var30 = this.elems1();
         byte computeWordForFilter_wordIndex = 1;
         long computeWordForFilter_oldWord = var30;
         if (computeWordForFilter_oldWord == 0L) {
            var30 = 0L;
         } else {
            long computeWordForFilter_w = computeWordForFilter_oldWord;
            int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(computeWordForFilter_oldWord);
            long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
            int computeWordForFilter_j = computeWordForFilter_wordIndex * 64 + computeWordForFilter_trailingZeroes;

            for(int computeWordForFilter_maxJ = (computeWordForFilter_wordIndex + 1) * 64 - Long.numberOfLeadingZeros(computeWordForFilter_oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
               if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                  computeWordForFilter_w &= ~computeWordForFilter_jmask;
               }

               computeWordForFilter_jmask <<= 1;
            }

            var30 = computeWordForFilter_w;
         }

         long _elems1 = var30;
         if (_elems1 == 0L) {
            return (BitSet)(_elems0 == 0L ? this.empty() : new BitSet1(_elems0));
         } else {
            return new BitSet2(_elems0, _elems1);
         }
      }

      public BitSet2(final long elems0, final long elems1) {
         this.elems0 = elems0;
         this.elems1 = elems1;
      }
   }

   /** @deprecated */
   public static class BitSetN extends BitSet {
      private final long[] elems;

      public long[] elems() {
         return this.elems;
      }

      public int nwords() {
         return this.elems().length;
      }

      public long word(final int idx) {
         return idx < this.nwords() ? this.elems()[idx] : 0L;
      }

      public BitSet updateWord(final int idx, final long w) {
         return this.fromBitMaskNoCopy(BitSetOps$.MODULE$.updateArray(this.elems(), idx, w));
      }

      public BitSet diff(final scala.collection.Set that) {
         if (!(that instanceof scala.collection.BitSet)) {
            return (BitSet)BitSetOps.diff$(this, that);
         } else {
            scala.collection.BitSet var2 = (scala.collection.BitSet)that;
            int bsnwords = var2.nwords();
            int thisnwords = this.nwords();
            if (bsnwords >= thisnwords) {
               int i = thisnwords - 1;
               long currentWord = 0L;

               boolean anyChanges;
               for(anyChanges = false; i >= 0 && currentWord == 0L; --i) {
                  long oldWord = this.word(i);
                  currentWord = oldWord & ~var2.word(i);
                  anyChanges = anyChanges || currentWord != oldWord;
               }

               switch (i) {
                  case -1:
                     if (anyChanges) {
                        if (currentWord == 0L) {
                           return this.empty();
                        }

                        return new BitSet1(currentWord);
                     }

                     return this;
                  case 0:
                     long oldFirstWord = this.word(0);
                     long firstWord = oldFirstWord & ~var2.word(0);
                     anyChanges = anyChanges || firstWord != oldFirstWord;
                     if (anyChanges) {
                        return new BitSet2(firstWord, currentWord);
                     }

                     return this;
                  default:
                     int minimumNonZeroIndex;
                     for(minimumNonZeroIndex = i + 1; !anyChanges && i >= 0; --i) {
                        long oldWord = this.word(i);
                        currentWord = oldWord & ~var2.word(i);
                        anyChanges = anyChanges || currentWord != oldWord;
                     }

                     if (!anyChanges) {
                        return this;
                     } else {
                        ArrayOps$ var10000 = ArrayOps$.MODULE$;
                        long[] var10001 = this.elems();
                        int take$extension_n = minimumNonZeroIndex + 1;
                        long[] newArray = (long[])var10000.slice$extension(var10001, 0, take$extension_n);

                        for(newArray[i + 1] = currentWord; i >= 0; --i) {
                           newArray[i] = this.word(i) & ~var2.word(i);
                        }

                        return new BitSetN(newArray);
                     }
               }
            } else {
               int i = bsnwords - 1;
               boolean anyChanges = false;

               long currentWord;
               for(currentWord = 0L; i >= 0 && !anyChanges; --i) {
                  long oldWord = this.word(i);
                  currentWord = oldWord & ~var2.word(i);
                  anyChanges = anyChanges || currentWord != oldWord;
               }

               if (!anyChanges) {
                  return this;
               } else {
                  long[] newElems = (long[])this.elems().clone();

                  for(newElems[i + 1] = currentWord; i >= 0; --i) {
                     newElems[i] = this.word(i) & ~var2.word(i);
                  }

                  return this.fromBitMaskNoCopy(newElems);
               }
            }
         }
      }

      public BitSet filterImpl(final Function1 pred, final boolean isFlipped) {
         int i = this.nwords() - 1;
         long currentWord = 0L;

         boolean anyChanges;
         for(anyChanges = false; i >= 0 && currentWord == 0L; --i) {
            long oldWord = this.word(i);
            BitSetOps$ var10000 = BitSetOps$.MODULE$;
            long var50;
            if (oldWord == 0L) {
               var50 = 0L;
            } else {
               long computeWordForFilter_w = oldWord;
               int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(oldWord);
               long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
               int computeWordForFilter_j = i * 64 + computeWordForFilter_trailingZeroes;

               for(int computeWordForFilter_maxJ = (i + 1) * 64 - Long.numberOfLeadingZeros(oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
                  if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                     computeWordForFilter_w &= ~computeWordForFilter_jmask;
                  }

                  computeWordForFilter_jmask <<= 1;
               }

               var50 = computeWordForFilter_w;
            }

            currentWord = var50;
            anyChanges = anyChanges || currentWord != oldWord;
         }

         switch (i) {
            case -1:
               if (anyChanges) {
                  if (currentWord == 0L) {
                     return this.empty();
                  }

                  return new BitSet1(currentWord);
               }

               return this;
            case 0:
               long oldFirstWord = this.word(0);
               BitSetOps$ var51 = BitSetOps$.MODULE$;
               int computeWordForFilter_wordIndex = 0;
               long var52;
               if (oldFirstWord == 0L) {
                  var52 = 0L;
               } else {
                  long computeWordForFilter_w = oldFirstWord;
                  int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(oldFirstWord);
                  long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
                  int computeWordForFilter_j = computeWordForFilter_wordIndex * 64 + computeWordForFilter_trailingZeroes;

                  for(int computeWordForFilter_maxJ = (computeWordForFilter_wordIndex + 1) * 64 - Long.numberOfLeadingZeros(oldFirstWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
                     if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                        computeWordForFilter_w &= ~computeWordForFilter_jmask;
                     }

                     computeWordForFilter_jmask <<= 1;
                  }

                  var52 = computeWordForFilter_w;
               }

               long firstWord = var52;
               anyChanges = anyChanges || firstWord != oldFirstWord;
               if (anyChanges) {
                  return new BitSet2(firstWord, currentWord);
               }

               return this;
            default:
               int minimumNonZeroIndex;
               for(minimumNonZeroIndex = i + 1; !anyChanges && i >= 0; --i) {
                  long oldWord = this.word(i);
                  BitSetOps$ var53 = BitSetOps$.MODULE$;
                  long var54;
                  if (oldWord == 0L) {
                     var54 = 0L;
                  } else {
                     long computeWordForFilter_w = oldWord;
                     int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(oldWord);
                     long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
                     int computeWordForFilter_j = i * 64 + computeWordForFilter_trailingZeroes;

                     for(int computeWordForFilter_maxJ = (i + 1) * 64 - Long.numberOfLeadingZeros(oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
                        if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                           computeWordForFilter_w &= ~computeWordForFilter_jmask;
                        }

                        computeWordForFilter_jmask <<= 1;
                     }

                     var54 = computeWordForFilter_w;
                  }

                  currentWord = var54;
                  anyChanges = anyChanges || currentWord != oldWord;
               }

               if (!anyChanges) {
                  return this;
               } else {
                  ArrayOps$ var55 = ArrayOps$.MODULE$;
                  long[] var10001 = this.elems();
                  int take$extension_n = minimumNonZeroIndex + 1;
                  long[] newArray = (long[])var55.slice$extension(var10001, 0, take$extension_n);

                  for(newArray[i + 1] = currentWord; i >= 0; --i) {
                     BitSetOps$ var10002 = BitSetOps$.MODULE$;
                     long computeWordForFilter_oldWord = this.word(i);
                     long var56;
                     if (computeWordForFilter_oldWord == 0L) {
                        var56 = 0L;
                     } else {
                        long computeWordForFilter_w = computeWordForFilter_oldWord;
                        int computeWordForFilter_trailingZeroes = Long.numberOfTrailingZeros(computeWordForFilter_oldWord);
                        long computeWordForFilter_jmask = 1L << computeWordForFilter_trailingZeroes;
                        int computeWordForFilter_j = i * 64 + computeWordForFilter_trailingZeroes;

                        for(int computeWordForFilter_maxJ = (i + 1) * 64 - Long.numberOfLeadingZeros(computeWordForFilter_oldWord); computeWordForFilter_j != computeWordForFilter_maxJ; ++computeWordForFilter_j) {
                           if ((computeWordForFilter_w & computeWordForFilter_jmask) != 0L && pred.apply$mcZI$sp(computeWordForFilter_j) == isFlipped) {
                              computeWordForFilter_w &= ~computeWordForFilter_jmask;
                           }

                           computeWordForFilter_jmask <<= 1;
                        }

                        var56 = computeWordForFilter_w;
                     }

                     newArray[i] = var56;
                  }

                  return new BitSetN(newArray);
               }
         }
      }

      public long[] toBitMask() {
         return (long[])this.elems().clone();
      }

      public BitSetN(final long[] elems) {
         this.elems = elems;
      }
   }

   private static final class SerializationProxy extends scala.collection.BitSet.SerializationProxy {
      private static final long serialVersionUID = 3L;

      public Object readResolve() {
         return BitSet$.MODULE$.fromBitMaskNoCopy(this.elems());
      }

      public SerializationProxy(final BitSet coll) {
         super(coll);
      }
   }
}
