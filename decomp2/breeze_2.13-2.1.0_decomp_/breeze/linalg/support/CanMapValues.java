package breeze.linalg.support;

import breeze.math.Complex;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUga\u0002\u001b6!\u0003\r\n\u0001\u0010\u0005\u0006\t\u00021\t!\u0012\u0005\b\u0003\u001f\u0001a\u0011AA\t\u000f\u001d\t9\"\u000eE\u0001\u000331a\u0001N\u001b\t\u0002\u0005u\u0001bBA\u0013\t\u0011\u0005\u0011q\u0005\u0004\n\u0003S!\u0001\u0013aA\u0001\u0003WAq!!\u0011\u0007\t\u0003\t\u0019\u0005C\u0004\u0002\u0010\u0019!)!a\u0013\t\u000f\u0005MC\u0001b\u0001\u0002V!9\u0011Q\r\u0003\u0005\u0004\u0005\u001d\u0004bBA<\t\u0011\r\u0011\u0011\u0010\u0005\b\u0003\u0013#A1AAF\u0011\u001d\tY\n\u0002C\u0002\u0003;Cq!!,\u0005\t\u0007\ty\u000bC\u0004\u0002@\u0012!\u0019!!1\u0007\r\u0005EG\u0001AAj\u0011)\u0011\t\u0002\u0005B\u0002B\u0003-!1\u0003\u0005\b\u0003K\u0001B\u0011\u0001B\u0010\u0011\u0019!\u0005\u0003\"\u0001\u0003*!9\u0011q\u0002\t\u0005B\tE\u0002b\u0002B\u001c\t\u0011\r!\u0011H\u0004\b\u0005#\"\u00012\u0001B*\r\u001d\u0011)\u0006\u0002E\u0001\u0005/Bq!!\n\u0018\t\u0003\u0011YfB\u0004\u0003^\u0011A\u0019Aa\u0018\u0007\u000f\t\u0005D\u0001#\u0001\u0003d!9\u0011Q\u0005\u000e\u0005\u0002\t\u001dta\u0002B5\t!\r!1\u000e\u0004\b\u0005[\"\u0001\u0012\u0001B8\u0011\u001d\t)#\bC\u0001\u0005g:qA!\u001e\u0005\u0011\u0007\u00119HB\u0004\u0003z\u0011A\tAa\u001f\t\u000f\u0005\u0015\u0002\u0005\"\u0001\u0003\u0000\u001d9!\u0011\u0011\u0003\t\u0004\t\rea\u0002BC\t!\u0005!q\u0011\u0005\b\u0003K\u0019C\u0011\u0001BF\u000f\u001d\u0011i\t\u0002E\u0002\u0005\u001f3qA!%\u0005\u0011\u0003\u0011\u0019\nC\u0004\u0002&\u0019\"\tAa)\b\u000f\t\u0015F\u0001c\u0001\u0003(\u001a9!\u0011\u0016\u0003\t\u0002\t-\u0006bBA\u0013S\u0011\u0005!qV\u0004\b\u0005c#\u00012\u0001BZ\r\u001d\u0011)\f\u0002E\u0001\u0005oCq!!\n-\t\u0003\u0011YlB\u0004\u0003>\u0012A\u0019Aa0\u0007\u000f\t\u0005G\u0001#\u0001\u0003D\"9\u0011QE\u0018\u0005\u0002\t\u001dwa\u0002Be\t!\r!1\u001a\u0004\b\u0005\u001b$\u0001\u0012\u0001Bh\u0011\u001d\t)C\rC\u0001\u0005'\u0014AbQ1o\u001b\u0006\u0004h+\u00197vKNT!AN\u001c\u0002\u000fM,\b\u000f]8si*\u0011\u0001(O\u0001\u0007Y&t\u0017\r\\4\u000b\u0003i\naA\u0019:fKj,7\u0001A\u000b\u0006{QcF\u0010S\n\u0003\u0001y\u0002\"a\u0010\"\u000e\u0003\u0001S\u0011!Q\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0007\u0002\u0013a!\u00118z%\u00164\u0017aA7baR\u0019a)\u0015,\u0011\u0005\u001dCE\u0002\u0001\u0003\u0007\u0013\u0002!)\u0019\u0001&\u0003\u0005Q{\u0017CA&O!\tyD*\u0003\u0002N\u0001\n9aj\u001c;iS:<\u0007CA P\u0013\t\u0001\u0006IA\u0002B]fDQAU\u0001A\u0002M\u000bAA\u001a:p[B\u0011q\t\u0016\u0003\u0006+\u0002\u0011\rA\u0013\u0002\u0005\rJ|W\u000eC\u0003X\u0003\u0001\u0007\u0001,\u0001\u0002g]B!q(W.|\u0013\tQ\u0006IA\u0005Gk:\u001cG/[8ocA\u0011q\t\u0018\u0003\n;\u0002\u0001\u000b\u0011!AC\u0002)\u0013\u0011A\u0016\u0015\u00079~\u0013G.\u001d<\u0011\u0005}\u0002\u0017BA1A\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u001aGMZ3\u000f\u0005}\"\u0017BA3A\u0003\rIe\u000e^\u0019\u0005I\u001d\\\u0017I\u0004\u0002iW6\t\u0011N\u0003\u0002kw\u00051AH]8pizJ\u0011!Q\u0019\u0006G5t\u0007o\u001c\b\u0003\u007f9L!a\u001c!\u0002\u000b\u0019cw.\u0019;2\t\u0011:7.Q\u0019\u0006GI\u001cX\u000f\u001e\b\u0003\u007fML!\u0001\u001e!\u0002\t1{gnZ\u0019\u0005I\u001d\\\u0017)M\u0003$obT\u0018P\u0004\u0002@q&\u0011\u0011\u0010Q\u0001\u0007\t>,(\r\\32\t\u0011:7.\u0011\t\u0003\u000fr$\u0011\" \u0001!\u0002\u0003\u0005)\u0019\u0001&\u0003\u0005Y\u0013\u0004&\u0003?`\u007f\u0006\r\u0011qAA\u0006c\u0019\u00193\rZA\u0001KF\"AeZ6Bc\u0019\u0019SN\\A\u0003_F\"AeZ6Bc\u0019\u0019#o]A\u0005iF\"AeZ6Bc\u0019\u0019s\u000f_A\u0007sF\"AeZ6B\u0003%i\u0017\r]!di&4X\rF\u0003G\u0003'\t)\u0002C\u0003S\u0005\u0001\u00071\u000bC\u0003X\u0005\u0001\u0007\u0001,\u0001\u0007DC:l\u0015\r\u001d,bYV,7\u000fE\u0002\u0002\u001c\u0011i\u0011!N\n\u0005\ty\ny\u0002\u0005\u0003\u0002\u001c\u0005\u0005\u0012bAA\u0012k\t\u00192)\u00198NCB4\u0016\r\\;fg2{w\u000f\u0015:j_\u00061A(\u001b8jiz\"\"!!\u0007\u0003#\u0011+gn]3DC:l\u0015\r\u001d,bYV,7/\u0006\u0006\u0002.\u0005M\u0012qGA\u001e\u0003\u007f\u0019BA\u0002 \u00020AY\u00111\u0004\u0001\u00022\u0005U\u0012\u0011HA\u001f!\r9\u00151\u0007\u0003\u0006+\u001a\u0011\rA\u0013\t\u0004\u000f\u0006]B!B/\u0007\u0005\u0004Q\u0005cA$\u0002<\u0011)QP\u0002b\u0001\u0015B\u0019q)a\u0010\u0005\u000b%3!\u0019\u0001&\u0002\r\u0011Jg.\u001b;%)\t\t)\u0005E\u0002@\u0003\u000fJ1!!\u0013A\u0005\u0011)f.\u001b;\u0015\r\u0005u\u0012QJA(\u0011\u0019\u0011\u0006\u00021\u0001\u00022!1q\u000b\u0003a\u0001\u0003#\u0002baP-\u00026\u0005e\u0012\u0001E2b]6\u000b\u0007oU3mM\u0012{WO\u00197f+\u0011\t9&a\u0019\u0016\u0005\u0005e\u0003cCA\u000e\u0001\u0005m\u00131LA1\u0003C\u00022aPA/\u0013\r\ty\u0006\u0011\u0002\u0007\t>,(\r\\3\u0011\u0007\u001d\u000b\u0019\u0007B\u0003~\u0013\t\u0007!*A\u0007dC:l\u0015\r]*fY\u001aLe\u000e^\u000b\u0005\u0003S\n)(\u0006\u0002\u0002lAY\u00111\u0004\u0001\u0002n\u00055\u00141OA:!\ry\u0014qN\u0005\u0004\u0003c\u0002%aA%oiB\u0019q)!\u001e\u0005\u000buT!\u0019\u0001&\u0002\u001f\r\fg.T1q'\u0016dgM\u00127pCR,B!a\u001f\u0002\bV\u0011\u0011Q\u0010\t\f\u00037\u0001\u0011qPA@\u0003\u000b\u000b)\tE\u0002@\u0003\u0003K1!a!A\u0005\u00151En\\1u!\r9\u0015q\u0011\u0003\u0006{.\u0011\rAS\u0001\u000fG\u0006tW*\u00199TK24Gj\u001c8h+\u0011\ti)!'\u0016\u0005\u0005=\u0005cCA\u000e\u0001\u0005E\u0015\u0011SAL\u0003/\u00032aPAJ\u0013\r\t)\n\u0011\u0002\u0005\u0019>tw\rE\u0002H\u00033#Q! \u0007C\u0002)\u000bqbY1o\u001b\u0006\u00048+\u001a7g'\"|'\u000f^\u000b\u0005\u0003?\u000bY+\u0006\u0002\u0002\"BY\u00111\u0004\u0001\u0002$\u0006\r\u0016\u0011VAU!\ry\u0014QU\u0005\u0004\u0003O\u0003%!B*i_J$\bcA$\u0002,\u0012)Q0\u0004b\u0001\u0015\u0006q1-\u00198NCB\u001cV\r\u001c4CsR,W\u0003BAY\u0003{+\"!a-\u0011\u0017\u0005m\u0001!!.\u00026\u0006m\u00161\u0018\t\u0004\u007f\u0005]\u0016bAA]\u0001\n!!)\u001f;f!\r9\u0015Q\u0018\u0003\u0006{:\u0011\rAS\u0001\u000fG\u0006tW*\u00199TK247\t[1s+\u0011\t\u0019-a4\u0016\u0005\u0005\u0015\u0007cCA\u000e\u0001\u0005\u001d\u0017qYAg\u0003\u001b\u00042aPAe\u0013\r\tY\r\u0011\u0002\u0005\u0007\"\f'\u000fE\u0002H\u0003\u001f$Q!`\bC\u0002)\u0013qa\u00149BeJ\f\u00170\u0006\u0004\u0002V\u0006\u0005\u0018\u0011`\n\u0005!y\n9\u000eE\u0006\u0002\u001c\u0001\tI.a8\u0002x\n=\u0001#B \u0002\\\u0006}\u0017bAAo\u0001\n)\u0011I\u001d:bsB\u0019q)!9\u0005\u0015\u0005\r\b\u0003)A\u0001\u0002\u000b\u0007!JA\u0001BQ-\t\toXAt\u0003W\fy/a=2\r\r:\b0!;zc\u0011!sm[!2\r\r\u001aG-!<fc\u0011!sm[!2\r\rjg.!=pc\u0011!sm[!2\r\r\u00128/!>uc\u0011!sm[!\u0011\u0007\u001d\u000bI\u0010\u0002\u0006\u0002|B\u0001\u000b\u0011!AC\u0002)\u0013\u0011A\u0011\u0015\f\u0003s|\u0016q B\u0002\u0005\u000f\u0011Y!\r\u0004$ob\u0014\t!_\u0019\u0005I\u001d\\\u0017)\r\u0004$G\u0012\u0014)!Z\u0019\u0005I\u001d\\\u0017)\r\u0004$[:\u0014Ia\\\u0019\u0005I\u001d\\\u0017)\r\u0004$eN\u0014i\u0001^\u0019\u0005I\u001d\\\u0017\tE\u0003@\u00037\f90\u0001\u0006fm&$WM\\2fIE\u0002bA!\u0006\u0003\u001c\u0005]XB\u0001B\f\u0015\r\u0011I\u0002Q\u0001\be\u00164G.Z2u\u0013\u0011\u0011iBa\u0006\u0003\u0011\rc\u0017m]:UC\u001e$\"A!\t\u0015\t\t\r\"q\u0005\t\b\u0005K\u0001\u0012q\\A|\u001b\u0005!\u0001b\u0002B\t%\u0001\u000f!1\u0003\u000b\u0007\u0005\u001f\u0011YC!\f\t\rI\u001b\u0002\u0019AAm\u0011\u001996\u00031\u0001\u00030A1q(WAp\u0003o$bAa\u0004\u00034\tU\u0002B\u0002*\u0015\u0001\u0004\tI\u000e\u0003\u0004X)\u0001\u0007!qF\u0001\b_B\f%O]1z+\u0019\u0011YD!\u0011\u0003HQ!!Q\bB&!\u001d\u0011)\u0003\u0005B \u0005\u000b\u00022a\u0012B!\t)\t\u0019/\u0006Q\u0001\u0002\u0003\u0015\rA\u0013\u0015\u0004\u0005\u0003z\u0006cA$\u0003H\u0011Q\u00111`\u000b!\u0002\u0003\u0005)\u0019\u0001&)\u0007\t\u001ds\fC\u0005\u0003NU\t\t\u0011q\u0001\u0003P\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\r\tU!1\u0004B#\u0003%y\u0005/\u0011:sCfL\u0015\nE\u0002\u0003&]\u0011\u0011b\u00149BeJ\f\u00170S%\u0014\u0007]\u0011I\u0006E\u0004\u0003&A\ti'!\u001c\u0015\u0005\tM\u0013!C(q\u0003J\u0014\u0018-_*T!\r\u0011)C\u0007\u0002\n\u001fB\f%O]1z'N\u001b2A\u0007B3!\u001d\u0011)\u0003EAR\u0003G#\"Aa\u0018\u0002\u0013=\u0003\u0018I\u001d:bs2c\u0005c\u0001B\u0013;\tIq\n]!se\u0006LH\nT\n\u0004;\tE\u0004c\u0002B\u0013!\u0005E\u0015\u0011\u0013\u000b\u0003\u0005W\n\u0011b\u00149BeJ\f\u0017P\u0012$\u0011\u0007\t\u0015\u0002EA\u0005Pa\u0006\u0013(/Y=G\rN\u0019\u0001E! \u0011\u000f\t\u0015\u0002#a \u0002\u0000Q\u0011!qO\u0001\n\u001fB\f%O]1z\t\u0012\u00032A!\n$\u0005%y\u0005/\u0011:sCf$EiE\u0002$\u0005\u0013\u0003rA!\n\u0011\u00037\nY\u0006\u0006\u0002\u0003\u0004\u0006Iq\n]!se\u0006L8i\u0011\t\u0004\u0005K1#!C(q\u0003J\u0014\u0018-_\"D'\r1#Q\u0013\t\b\u0005K\u0001\"q\u0013BL!\u0011\u0011IJa(\u000e\u0005\tm%b\u0001BOs\u0005!Q.\u0019;i\u0013\u0011\u0011\tKa'\u0003\u000f\r{W\u000e\u001d7fqR\u0011!qR\u0001\n\u001fB\f%O]1z\u0013\u0012\u00032A!\n*\u0005%y\u0005/\u0011:sCfLEiE\u0002*\u0005[\u0003rA!\n\u0011\u0003[\nY\u0006\u0006\u0002\u0003(\u0006Iq\n]!se\u0006L8\u000b\u0012\t\u0004\u0005Ka#!C(q\u0003J\u0014\u0018-_*E'\ra#\u0011\u0018\t\b\u0005K\u0001\u00121UA.)\t\u0011\u0019,A\u0005Pa\u0006\u0013(/Y=M\tB\u0019!QE\u0018\u0003\u0013=\u0003\u0018I\u001d:bs2#5cA\u0018\u0003FB9!Q\u0005\t\u0002\u0012\u0006mCC\u0001B`\u0003%y\u0005/\u0011:sCf4E\tE\u0002\u0003&I\u0012\u0011b\u00149BeJ\f\u0017P\u0012#\u0014\u0007I\u0012\t\u000eE\u0004\u0003&A\ty(a\u0017\u0015\u0005\t-\u0007"
)
public interface CanMapValues {
   static OpArray opArray(final ClassTag evidence$2) {
      return CanMapValues$.MODULE$.opArray(evidence$2);
   }

   static CanMapValues canMapSelfChar() {
      return CanMapValues$.MODULE$.canMapSelfChar();
   }

   static CanMapValues canMapSelfByte() {
      return CanMapValues$.MODULE$.canMapSelfByte();
   }

   static CanMapValues canMapSelfShort() {
      return CanMapValues$.MODULE$.canMapSelfShort();
   }

   static CanMapValues canMapSelfLong() {
      return CanMapValues$.MODULE$.canMapSelfLong();
   }

   static CanMapValues canMapSelfFloat() {
      return CanMapValues$.MODULE$.canMapSelfFloat();
   }

   static CanMapValues canMapSelfInt() {
      return CanMapValues$.MODULE$.canMapSelfInt();
   }

   static CanMapValues canMapSelfDouble() {
      return CanMapValues$.MODULE$.canMapSelfDouble();
   }

   static CanMapValues canMapSelf() {
      return CanMapValues$.MODULE$.canMapSelf();
   }

   Object map(final Object from, final Function1 fn);

   Object mapActive(final Object from, final Function1 fn);

   default Object map$mcDD$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcDF$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcDI$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcDJ$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcFD$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcFF$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcFI$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcFJ$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcID$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcIF$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcII$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcIJ$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcJD$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcJF$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcJI$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object map$mcJJ$sp(final Object from, final Function1 fn) {
      return this.map(from, fn);
   }

   default Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcID$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcII$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   default Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
      return this.mapActive(from, fn);
   }

   public interface DenseCanMapValues extends CanMapValues {
      // $FF: synthetic method
      static Object mapActive$(final DenseCanMapValues $this, final Object from, final Function1 fn) {
         return $this.mapActive(from, fn);
      }

      default Object mapActive(final Object from, final Function1 fn) {
         return this.map(from, fn);
      }

      static void $init$(final DenseCanMapValues $this) {
      }
   }

   public static class OpArray implements CanMapValues {
      public final ClassTag breeze$linalg$support$CanMapValues$OpArray$$evidence$1;

      public Object map$mcDD$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcDD$sp(from, fn);
      }

      public Object map$mcDF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcDF$sp(from, fn);
      }

      public Object map$mcDI$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcDI$sp(from, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcDJ$sp(from, fn);
      }

      public Object map$mcFD$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcFD$sp(from, fn);
      }

      public Object map$mcFF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcFF$sp(from, fn);
      }

      public Object map$mcFI$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcFI$sp(from, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcFJ$sp(from, fn);
      }

      public Object map$mcID$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcID$sp(from, fn);
      }

      public Object map$mcIF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcIF$sp(from, fn);
      }

      public Object map$mcII$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcII$sp(from, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcIJ$sp(from, fn);
      }

      public Object map$mcJD$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcJD$sp(from, fn);
      }

      public Object map$mcJF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcJF$sp(from, fn);
      }

      public Object map$mcJI$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcJI$sp(from, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.map$mcJJ$sp(from, fn);
      }

      public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcDD$sp(from, fn);
      }

      public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcDF$sp(from, fn);
      }

      public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcDI$sp(from, fn);
      }

      public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcDJ$sp(from, fn);
      }

      public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcFD$sp(from, fn);
      }

      public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcFF$sp(from, fn);
      }

      public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcFI$sp(from, fn);
      }

      public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcFJ$sp(from, fn);
      }

      public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcID$sp(from, fn);
      }

      public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcIF$sp(from, fn);
      }

      public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcII$sp(from, fn);
      }

      public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcIJ$sp(from, fn);
      }

      public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcJD$sp(from, fn);
      }

      public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcJF$sp(from, fn);
      }

      public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcJI$sp(from, fn);
      }

      public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
         return CanMapValues.super.mapActive$mcJJ$sp(from, fn);
      }

      public Object map(final Object from, final Function1 fn) {
         Object arr = this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1.newArray(.MODULE$.array_length(from));
         int index$macro$2 = 0;

         for(int limit$macro$4 = .MODULE$.array_length(from); index$macro$2 < limit$macro$4; ++index$macro$2) {
            .MODULE$.array_update(arr, index$macro$2, fn.apply(.MODULE$.array_apply(from, index$macro$2)));
         }

         return arr;
      }

      public Object mapActive(final Object from, final Function1 fn) {
         return this.map(from, fn);
      }

      public double[] map$mcDD$sp(final double[] from, final Function1 fn) {
         return (double[])this.map(from, fn);
      }

      public float[] map$mcDF$sp(final double[] from, final Function1 fn) {
         return (float[])this.map(from, fn);
      }

      public int[] map$mcDI$sp(final double[] from, final Function1 fn) {
         return (int[])this.map(from, fn);
      }

      public long[] map$mcDJ$sp(final double[] from, final Function1 fn) {
         return (long[])this.map(from, fn);
      }

      public double[] map$mcFD$sp(final float[] from, final Function1 fn) {
         return (double[])this.map(from, fn);
      }

      public float[] map$mcFF$sp(final float[] from, final Function1 fn) {
         return (float[])this.map(from, fn);
      }

      public int[] map$mcFI$sp(final float[] from, final Function1 fn) {
         return (int[])this.map(from, fn);
      }

      public long[] map$mcFJ$sp(final float[] from, final Function1 fn) {
         return (long[])this.map(from, fn);
      }

      public double[] map$mcID$sp(final int[] from, final Function1 fn) {
         return (double[])this.map(from, fn);
      }

      public float[] map$mcIF$sp(final int[] from, final Function1 fn) {
         return (float[])this.map(from, fn);
      }

      public int[] map$mcII$sp(final int[] from, final Function1 fn) {
         return (int[])this.map(from, fn);
      }

      public long[] map$mcIJ$sp(final int[] from, final Function1 fn) {
         return (long[])this.map(from, fn);
      }

      public double[] map$mcJD$sp(final long[] from, final Function1 fn) {
         return (double[])this.map(from, fn);
      }

      public float[] map$mcJF$sp(final long[] from, final Function1 fn) {
         return (float[])this.map(from, fn);
      }

      public int[] map$mcJI$sp(final long[] from, final Function1 fn) {
         return (int[])this.map(from, fn);
      }

      public long[] map$mcJJ$sp(final long[] from, final Function1 fn) {
         return (long[])this.map(from, fn);
      }

      public double[] mapActive$mcDD$sp(final double[] from, final Function1 fn) {
         return (double[])this.mapActive(from, fn);
      }

      public float[] mapActive$mcDF$sp(final double[] from, final Function1 fn) {
         return (float[])this.mapActive(from, fn);
      }

      public int[] mapActive$mcDI$sp(final double[] from, final Function1 fn) {
         return (int[])this.mapActive(from, fn);
      }

      public long[] mapActive$mcDJ$sp(final double[] from, final Function1 fn) {
         return (long[])this.mapActive(from, fn);
      }

      public double[] mapActive$mcFD$sp(final float[] from, final Function1 fn) {
         return (double[])this.mapActive(from, fn);
      }

      public float[] mapActive$mcFF$sp(final float[] from, final Function1 fn) {
         return (float[])this.mapActive(from, fn);
      }

      public int[] mapActive$mcFI$sp(final float[] from, final Function1 fn) {
         return (int[])this.mapActive(from, fn);
      }

      public long[] mapActive$mcFJ$sp(final float[] from, final Function1 fn) {
         return (long[])this.mapActive(from, fn);
      }

      public double[] mapActive$mcID$sp(final int[] from, final Function1 fn) {
         return (double[])this.mapActive(from, fn);
      }

      public float[] mapActive$mcIF$sp(final int[] from, final Function1 fn) {
         return (float[])this.mapActive(from, fn);
      }

      public int[] mapActive$mcII$sp(final int[] from, final Function1 fn) {
         return (int[])this.mapActive(from, fn);
      }

      public long[] mapActive$mcIJ$sp(final int[] from, final Function1 fn) {
         return (long[])this.mapActive(from, fn);
      }

      public double[] mapActive$mcJD$sp(final long[] from, final Function1 fn) {
         return (double[])this.mapActive(from, fn);
      }

      public float[] mapActive$mcJF$sp(final long[] from, final Function1 fn) {
         return (float[])this.mapActive(from, fn);
      }

      public int[] mapActive$mcJI$sp(final long[] from, final Function1 fn) {
         return (int[])this.mapActive(from, fn);
      }

      public long[] mapActive$mcJJ$sp(final long[] from, final Function1 fn) {
         return (long[])this.mapActive(from, fn);
      }

      public OpArray(final ClassTag evidence$1) {
         this.breeze$linalg$support$CanMapValues$OpArray$$evidence$1 = evidence$1;
      }
   }

   public static class OpArrayII$ extends CanMapValues$OpArray$mcII$sp {
      public static final OpArrayII$ MODULE$ = new OpArrayII$();

      public OpArrayII$() {
         super(scala.reflect.ClassTag..MODULE$.Int());
      }
   }

   public static class OpArraySS$ extends OpArray {
      public static final OpArraySS$ MODULE$ = new OpArraySS$();

      public OpArraySS$() {
         super(scala.reflect.ClassTag..MODULE$.Short());
      }
   }

   public static class OpArrayLL$ extends CanMapValues$OpArray$mcJJ$sp {
      public static final OpArrayLL$ MODULE$ = new OpArrayLL$();

      public OpArrayLL$() {
         super(scala.reflect.ClassTag..MODULE$.Long());
      }
   }

   public static class OpArrayFF$ extends CanMapValues$OpArray$mcFF$sp {
      public static final OpArrayFF$ MODULE$ = new OpArrayFF$();

      public OpArrayFF$() {
         super(scala.reflect.ClassTag..MODULE$.Float());
      }
   }

   public static class OpArrayDD$ extends CanMapValues$OpArray$mcDD$sp {
      public static final OpArrayDD$ MODULE$ = new OpArrayDD$();

      public OpArrayDD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayCC$ extends OpArray {
      public static final OpArrayCC$ MODULE$ = new OpArrayCC$();

      public OpArrayCC$() {
         super(scala.reflect.ClassTag..MODULE$.apply(Complex.class));
      }
   }

   public static class OpArrayID$ extends CanMapValues$OpArray$mcID$sp {
      public static final OpArrayID$ MODULE$ = new OpArrayID$();

      public OpArrayID$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArraySD$ extends OpArray {
      public static final OpArraySD$ MODULE$ = new OpArraySD$();

      public OpArraySD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayLD$ extends CanMapValues$OpArray$mcJD$sp {
      public static final OpArrayLD$ MODULE$ = new OpArrayLD$();

      public OpArrayLD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }

   public static class OpArrayFD$ extends CanMapValues$OpArray$mcFD$sp {
      public static final OpArrayFD$ MODULE$ = new OpArrayFD$();

      public OpArrayFD$() {
         super(scala.reflect.ClassTag..MODULE$.Double());
      }
   }
}
