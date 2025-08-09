package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.gymnastics.NotGiven;
import breeze.linalg.support.ScalarOf;
import scala.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\teca\u0002\u0006\f!\u0003\r\tA\u0005\u0005\u0006;\u0001!\tA\b\u0005\u0006E\u0001!\u0019a\t\u0005\u0006_\u0002!\u0019\u0001\u001d\u0005\b\u00033\u0001A1AA\u000e\u0011\u001d\t\u0019\u0006\u0001C\u0002\u0003+Bq!! \u0001\t\u0007\ty\bC\u0004\u00028\u0002!\u0019!!/\t\u000f\u0005%\b\u0001b\u0001\u0002l\"9!1\u0005\u0001\u0005\u0004\t\u0015\"aB\"bgR|\u0005o\u001d\u0006\u0003\u00195\t\u0011b\u001c9fe\u0006$xN]:\u000b\u00059y\u0011A\u00027j]\u0006dwMC\u0001\u0011\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u00143A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u0004\"AG\u000e\u000e\u0003-I!\u0001H\u0006\u0003%\u001d+g.\u001a:jG>\u00038\u000fT8x!JLwnM\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0006\u0011\n\u0005\u0005*\"\u0001B+oSR\f1bY1ti>\u00038o\u0018,`-V1A%\u0011)JoM#R!J+[;2\u0004bA\n\u001a6\u0001>\u0013fBA\u00140\u001d\tASF\u0004\u0002*Y5\t!F\u0003\u0002,#\u00051AH]8pizJ\u0011\u0001E\u0005\u0003]=\tqaZ3oKJL7-\u0003\u00021c\u0005)QKR;oG*\u0011afD\u0005\u0003gQ\u0012a!V%na2\u0014$B\u0001\u00192!\t1t\u0007\u0004\u0001\u0005\u000ba\u0012!\u0019A\u001d\u0003\u0005=\u0003\u0018C\u0001\u001e>!\t!2(\u0003\u0002=+\t9aj\u001c;iS:<\u0007C\u0001\u000e?\u0013\ty4B\u0001\u0004PaRK\b/\u001a\t\u0003m\u0005#QA\u0011\u0002C\u0002\r\u0013!!T\u0019\u0012\u0005i\"\u0005cA#G\u00116\tQ\"\u0003\u0002H\u001b\t1a+Z2u_J\u0004\"AN%\u0005\u000b)\u0013!\u0019A&\u0003\u0003Q\u000b\"A\u000f'\u0011\u0005Qi\u0015B\u0001(\u0016\u0005\r\te.\u001f\t\u0003mA#Q!\u0015\u0002C\u0002\r\u0013!!\u0014\u001a\u0011\u0005Y\u001aF!\u0002+\u0003\u0005\u0004Y%AA'S\u0011\u00151&\u0001q\u0001X\u0003\u00111\u0018\u0007\u001c;\u0011\tQA\u0006\tR\u0005\u00033V\u0011\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\t\u000bm\u0013\u00019\u0001/\u0002\tY\u0014D\u000e\u001e\t\u0005)a{E\tC\u0003_\u0005\u0001\u000fq,\u0001\u0003wc9,\u0007c\u00011dK6\t\u0011M\u0003\u0002c\u001f\u0005Qq-_7oCN$\u0018nY:\n\u0005\u0011\f'\u0001\u0003(pi\u001eKg/\u001a8\u0011\t\u00014\u0007n[\u0005\u0003O\u0006\u0014a\u0002J1na\u0012\u001aw\u000e\\8oI\u0005l\u0007\u000f\u0005\u0003\u0015S\u0002#\u0015B\u00016\u0016\u00051!S-\u001d\u0013d_2|g\u000eJ3r!\u0011!\u0012n\u0014#\t\u000b5\u0014\u00019\u00018\u0002\u0005=\u0004\bC\u0002\u00143k\u0011#%+A\tdCN$X\u000b\u001d3bi\u0016|\u0005o]0W?Z+b!\u001d>\u0002\u0002yDH#\u0003:\u0002\u0004\u0005\u001d\u00111BA\u000b!\u0015\u0019Xo^=\u0000\u001d\t!x&D\u00012\u0013\t1HG\u0001\u0007J]Bc\u0017mY3J[Bd'\u0007\u0005\u00027q\u0012)\u0001h\u0001b\u0001sA\u0011aG\u001f\u0003\u0006\u0005\u000e\u0011\ra_\t\u0003uq\u00042!\u0012$~!\t1d\u0010B\u0003K\u0007\t\u00071\nE\u00027\u0003\u0003!Q!U\u0002C\u0002mDaAV\u0002A\u0004\u0005\u0015\u0001\u0003\u0002\u000bYsrDaaW\u0002A\u0004\u0005%\u0001\u0003\u0002\u000bY\u007frDaAX\u0002A\u0004\u00055\u0001\u0003\u00021d\u0003\u001f\u0001b\u0001\u00194\u0002\u0012\u0005M\u0001\u0003\u0002\u000bjsr\u0004B\u0001F5\u0000y\"1Qn\u0001a\u0002\u0003/\u0001Ra];xyr\f1bY1ti>\u00038o\u0018,`'VQ\u0011QDA\u0014\u0003_\t\u0019#a\r\u0015\u0015\u0005}\u0011QGA#\u0003\u0013\ny\u0005\u0005\u0006'e\u0005\u0005\u0012QEA\u0017\u0003c\u00012ANA\u0012\t\u0015ADA1\u0001:!\r1\u0014q\u0005\u0003\u0007\u0005\u0012\u0011\r!!\u000b\u0012\u0007i\nY\u0003\u0005\u0003F\r\u00065\u0002c\u0001\u001c\u00020\u0011)!\n\u0002b\u0001\u0017B\u0019a'a\r\u0005\u000bQ#!\u0019A&\t\u000f\u0005]B\u0001q\u0001\u0002:\u0005\u0011aO\r\t\t\u0003w\t\t%!\n\u0002.5\u0011\u0011Q\b\u0006\u0004\u0003\u007fi\u0011aB:vaB|'\u000f^\u0005\u0005\u0003\u0007\niD\u0001\u0005TG\u0006d\u0017M](g\u0011\u00191F\u0001q\u0001\u0002HA1A\u0003WA\u0013\u0003WAaA\u0018\u0003A\u0004\u0005-\u0003\u0003\u00021d\u0003\u001b\u0002b\u0001F5\u0002&\u0005-\u0002BB7\u0005\u0001\b\t\t\u0006\u0005\u0006'e\u0005\u0005\u00121FA\u0017\u0003c\t\u0011cY1tiV\u0003H-\u0019;f\u001fB\u001cxLV0T+!\t9&!\u0019\u0002j\u0005uCCCA-\u0003W\ny'a\u001d\u0002zAA1/^A.\u0003?\n9\u0007E\u00027\u0003;\"Q\u0001O\u0003C\u0002e\u00022ANA1\t\u0019\u0011UA1\u0001\u0002dE\u0019!(!\u001a\u0011\t\u00153\u0015q\r\t\u0004m\u0005%D!\u0002&\u0006\u0005\u0004Y\u0005bBA\u001c\u000b\u0001\u000f\u0011Q\u000e\t\t\u0003w\t\t%a\u0018\u0002h!1a+\u0002a\u0002\u0003c\u0002b\u0001\u0006-\u0002`\u0005\u0015\u0004B\u00020\u0006\u0001\b\t)\b\u0005\u0003aG\u0006]\u0004C\u0002\u000bj\u0003?\n)\u0007\u0003\u0004n\u000b\u0001\u000f\u00111\u0010\t\tgV\fY&!\u001a\u0002h\u0005Y1-Y:u\u001fB\u001cx,T0N+1\t\t)a#\u0002\u001c\u0006]\u0015qQAP))\t\u0019)!)\u0002&\u0006%\u00161\u0017\t\u000bMI\n))!#\u0002\u001a\u0006u\u0005c\u0001\u001c\u0002\b\u0012)\u0001H\u0002b\u0001sA\u0019a'a#\u0005\r\t3!\u0019AAG#\rQ\u0014q\u0012\t\u0006\u000b\u0006E\u0015QS\u0005\u0004\u0003'k!AB'biJL\u0007\u0010E\u00027\u0003/#QA\u0013\u0004C\u0002-\u00032ANAN\t\u0019\tfA1\u0001\u0002\u000eB\u0019a'a(\u0005\u000bQ3!\u0019A&\t\rY3\u00019AAR!\u0019!\u0002,!#\u0002\u0010\"11L\u0002a\u0002\u0003O\u0003b\u0001\u0006-\u0002\u001a\u0006=\u0005B\u00020\u0007\u0001\b\tY\u000b\u0005\u0003aG\u00065\u0006C\u00021g\u0003_\u000b\t\f\u0005\u0004\u0015S\u0006%\u0015q\u0012\t\u0007)%\fI*a$\t\r54\u00019AA[!)1#'!\"\u0002\u0010\u0006=\u0015QT\u0001\u0012G\u0006\u001cH/\u00169eCR,w\n]:`\u001b~kUCCA^\u0003\u000b\f\t.!4\u0002BRQ\u0011QXAj\u0003/\fY.!:\u0011\u0011M,\u0018qXAb\u0003\u001f\u00042ANAa\t\u0015AtA1\u0001:!\r1\u0014Q\u0019\u0003\u0007\u0005\u001e\u0011\r!a2\u0012\u0007i\nI\rE\u0003F\u0003#\u000bY\rE\u00027\u0003\u001b$QAS\u0004C\u0002-\u00032ANAi\t\u0019\tvA1\u0001\u0002H\"1ak\u0002a\u0002\u0003+\u0004b\u0001\u0006-\u0002D\u0006%\u0007BB.\b\u0001\b\tI\u000e\u0005\u0004\u00151\u0006=\u0017\u0011\u001a\u0005\u0007=\u001e\u0001\u001d!!8\u0011\t\u0001\u001c\u0017q\u001c\t\u0007A\u001a\f\t/a9\u0011\rQI\u00171YAe!\u0019!\u0012.a4\u0002J\"1Qn\u0002a\u0002\u0003O\u0004\u0002b];\u0002@\u0006%\u0017\u0011Z\u0001\fG\u0006\u001cHo\u00149t?6{f+\u0006\u0007\u0002n\u0006](1AA\u0000\u0003g\u0014Y\u0001\u0006\u0006\u0002p\n5!\u0011\u0003B\u000b\u0005?\u0001\"B\n\u001a\u0002r\u0006U(\u0011\u0001B\u0005!\r1\u00141\u001f\u0003\u0006q!\u0011\r!\u000f\t\u0004m\u0005]HA\u0002\"\t\u0005\u0004\tI0E\u0002;\u0003w\u0004R!RAI\u0003{\u00042ANA\u0000\t\u0015Q\u0005B1\u0001L!\r1$1\u0001\u0003\u0007#\"\u0011\rA!\u0002\u0012\u0007i\u00129\u0001\u0005\u0003F\r\u0006u\bc\u0001\u001c\u0003\f\u0011)A\u000b\u0003b\u0001\u0017\"1a\u000b\u0003a\u0002\u0005\u001f\u0001b\u0001\u0006-\u0002v\u0006m\bBB.\t\u0001\b\u0011\u0019\u0002\u0005\u0004\u00151\n\u0005!q\u0001\u0005\u0007=\"\u0001\u001dAa\u0006\u0011\t\u0001\u001c'\u0011\u0004\t\u0007A\u001a\u0014YB!\b\u0011\rQI\u0017Q_A~!\u0019!\u0012N!\u0001\u0003\b!1Q\u000e\u0003a\u0002\u0005C\u0001\"B\n\u001a\u0002r\u0006m(q\u0001B\u0005\u0003E\u0019\u0017m\u001d;Va\u0012\fG/Z(qg~kuLV\u000b\u000b\u0005O\u0011\tD!\u0010\u0003:\t5BC\u0003B\u0015\u0005\u0007\u00129Ea\u0013\u0003VAA1/\u001eB\u0016\u0005_\u0011Y\u0004E\u00027\u0005[!Q\u0001O\u0005C\u0002e\u00022A\u000eB\u0019\t\u0019\u0011\u0015B1\u0001\u00034E\u0019!H!\u000e\u0011\u000b\u0015\u000b\tJa\u000e\u0011\u0007Y\u0012I\u0004B\u0003K\u0013\t\u00071\nE\u00027\u0005{!a!U\u0005C\u0002\t}\u0012c\u0001\u001e\u0003BA!QI\u0012B\u001c\u0011\u00191\u0016\u0002q\u0001\u0003FA1A\u0003\u0017B\u0018\u0005kAaaW\u0005A\u0004\t%\u0003C\u0002\u000bY\u0005w\u0011\t\u0005\u0003\u0004_\u0013\u0001\u000f!Q\n\t\u0005A\u000e\u0014y\u0005\u0005\u0004aM\nE#1\u000b\t\u0007)%\u0014yC!\u000e\u0011\rQI'1\bB!\u0011\u0019i\u0017\u0002q\u0001\u0003XAA1/\u001eB\u0016\u0005k\u0011\t\u0005"
)
public interface CastOps extends GenericOpsLowPrio3 {
   // $FF: synthetic method
   static UFunc.UImpl2 castOps_V_V$(final CastOps $this, final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return $this.castOps_V_V(v1lt, v2lt, v1ne, op);
   }

   default UFunc.UImpl2 castOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 castUpdateOps_V_V$(final CastOps $this, final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return $this.castUpdateOps_V_V(v1lt, v2lt, v1ne, op);
   }

   default UFunc.InPlaceImpl2 castUpdateOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.UImpl2 castOps_V_S$(final CastOps $this, final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return $this.castOps_V_S(v2, v1lt, v1ne, op);
   }

   default UFunc.UImpl2 castOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 castUpdateOps_V_S$(final CastOps $this, final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return $this.castUpdateOps_V_S(v2, v1lt, v1ne, op);
   }

   default UFunc.InPlaceImpl2 castUpdateOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.UImpl2 castOps_M_M$(final CastOps $this, final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return $this.castOps_M_M(v1lt, v2lt, v1ne, op);
   }

   default UFunc.UImpl2 castOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 castUpdateOps_M_M$(final CastOps $this, final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return $this.castUpdateOps_M_M(v1lt, v2lt, v1ne, op);
   }

   default UFunc.InPlaceImpl2 castUpdateOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.UImpl2 castOps_M_V$(final CastOps $this, final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return $this.castOps_M_V(v1lt, v2lt, v1ne, op);
   }

   default UFunc.UImpl2 castOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return op;
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 castUpdateOps_M_V$(final CastOps $this, final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return $this.castUpdateOps_M_V(v1lt, v2lt, v1ne, op);
   }

   default UFunc.InPlaceImpl2 castUpdateOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return op;
   }

   static void $init$(final CastOps $this) {
   }
}
