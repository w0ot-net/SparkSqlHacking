package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r=ca\u0002\u000f\u001e!\u0003\r\tA\t\u0005\u0006a\u0001!\t!\r\u0005\u0006k\u00011\tA\u000e\u0005\u0006\u007f\u0001!)\u0001\u0011\u0005\u0006C\u0002!)A\u0019\u0005\u0006_\u0002!)\u0001\u001d\u0005\u0006{\u0002!)A \u0005\b\u0003/\u0001AQAA\r\u0011\u001d\ty\u0003\u0001C\u0003\u0003cAq!a\u0013\u0001\t\u000b\ti\u0005C\u0004\u0002b\u0001!)!a\u0019\t\u000f\u0005u\u0004\u0001\"\u0002\u0002\u0000!9\u00111\u0013\u0001\u0005\u0006\u0005U\u0005bBAX\u0001\u0011\u0015\u0011\u0011\u0017\u0005\b\u0003\u000b\u0004AQAAd\u0011\u001d\t\t\u000f\u0001C\u0003\u0003GDqA!\u0002\u0001\t\u000b\u00119\u0001C\u0004\u0003\u001a\u0001!)Aa\u0007\t\u000f\tU\u0002\u0001\"\u0002\u00038!9!\u0011\u000b\u0001\u0005\u0006\tM\u0003b\u0002B7\u0001\u0011\u0015!q\u000e\u0005\b\u0005\u0007\u0003AQ\u0001BC\u0011\u001d\u0011I\n\u0001C\u0003\u00057CqAa,\u0001\t\u000b\u0011\t\fC\u0004\u0003L\u0002!)A!4\t\u000f\t\r\b\u0001\"\u0001\u0003f\"9!1\u001a\u0001\u0005\u0006\t}\bb\u0002Bf\u0001\u0011\u00151q\u0006\u0002\u0014\u00136lW\u000f^1cY\u0016tU/\\3sS\u000e|\u0005o\u001d\u0006\u0003=}\ta\u0001\\5oC2<'\"\u0001\u0011\u0002\r\t\u0014X-\u001a>f\u0007\u0001)\"aI\u001d\u0014\u0007\u0001!#\u0006\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcEA\u0002B]f\u0004\"a\u000b\u0018\u000e\u00031R!!L\u000f\u0002\u0013=\u0004XM]1u_J\u001c\u0018BA\u0018-\u0005\u0019A\u0015m](qg\u00061A%\u001b8ji\u0012\"\u0012A\r\t\u0003KMJ!\u0001\u000e\u0014\u0003\tUs\u0017\u000e^\u0001\u0005e\u0016\u0004(/F\u00018!\tA\u0014\b\u0004\u0001\u0005\ri\u0002AQ1\u0001<\u0005\u0011!\u0006.[:\u0012\u0005q\"\u0003CA\u0013>\u0013\tqdEA\u0004O_RD\u0017N\\4\u0002!\u0011\u0002H.^:%G>dwN\u001c\u0013qYV\u001cXcA!^\tR\u0011!i\u0018\u000b\u0003\u0007\u001a\u0003\"\u0001\u000f#\u0005\u000b\u0015\u001b!\u0019A\u001e\u0003\tQC\u0017\r\u001e\u0005\u0006\u000f\u000e\u0001\u001d\u0001S\u0001\u0003_B\u0004R!\u0013'S9\u000es!a\u000b&\n\u0005-c\u0013!B(q\u0003\u0012$\u0017BA'O\u0005\u0015IU\u000e\u001d73\u0013\ty\u0005KA\u0003V\rVt7M\u0003\u0002R?\u00059q-\u001a8fe&\u001c'FA\u001cTW\u0005!\u0006CA+[\u001b\u00051&BA,Y\u0003%)hn\u00195fG.,GM\u0003\u0002ZM\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005m3&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dKB\u0011\u0001(\u0018\u0003\u0006=\u000e\u0011\ra\u000f\u0002\u0002\u0005\")\u0001m\u0001a\u00019\u0006\t!-\u0001\n%i&lWm\u001d\u0013d_2|g\u000e\n;j[\u0016\u001cXcA2nMR\u0011AM\u001c\u000b\u0003K\u001e\u0004\"\u0001\u000f4\u0005\u000b\u0015#!\u0019A\u001e\t\u000b\u001d#\u00019\u00015\u0011\u000b%d%\u000b\\3\u000f\u0005-R\u0017BA6-\u0003-y\u0005/T;m'\u000e\fG.\u0019:\u0011\u0005ajG!\u00020\u0005\u0005\u0004Y\u0004\"\u00021\u0005\u0001\u0004a\u0017\u0001\u0004\u0013d_2|g\u000eJ3rI\u0015\fXcA9|iR\u0011!\u000f \u000b\u0003gV\u0004\"\u0001\u000f;\u0005\u000b\u0015+!\u0019A\u001e\t\u000b\u001d+\u00019\u0001<\u0011\u000b]d%K_:\u000f\u0005-B\u0018BA=-\u0003\u0011y\u0005/R9\u0011\u0005aZH!\u00020\u0006\u0005\u0004Y\u0004\"\u00021\u0006\u0001\u0004Q\u0018A\u0004\u0013d_2|g\u000e\n2b]\u001e$S-]\u000b\u0006\u007f\u0006M\u0011Q\u0001\u000b\u0005\u0003\u0003\t)\u0002\u0006\u0003\u0002\u0004\u0005\u001d\u0001c\u0001\u001d\u0002\u0006\u0011)QI\u0002b\u0001w!1qI\u0002a\u0002\u0003\u0013\u0001\u0002\"a\u0003M%\u0006E\u00111\u0001\b\u0004W\u00055\u0011bAA\bY\u0005!q\n\u001d(f!\rA\u00141\u0003\u0003\u0006=\u001a\u0011\ra\u000f\u0005\u0007A\u001a\u0001\r!!\u0005\u0002\u0019Ut\u0017M]=`I5Lg.^:\u0016\t\u0005m\u0011q\u0004\u000b\u0005\u0003;\t\t\u0003E\u00029\u0003?!Q!R\u0004C\u0002mBaaR\u0004A\u0004\u0005\r\u0002cBA\u0013\u0003W\u0011\u0016Q\u0004\b\u0004W\u0005\u001d\u0012bAA\u0015Y\u0005)q\n\u001d(fO&\u0019\u0011Q\u0006(\u0003\t%k\u0007\u000f\\\u0001\u0013I5Lg.^:%G>dwN\u001c\u0013nS:,8/\u0006\u0004\u00024\u0005\u001d\u0013\u0011\b\u000b\u0005\u0003k\tI\u0005\u0006\u0003\u00028\u0005m\u0002c\u0001\u001d\u0002:\u0011)Q\t\u0003b\u0001w!1q\t\u0003a\u0002\u0003{\u0001\u0002\"a\u0010M%\u0006\u0015\u0013q\u0007\b\u0004W\u0005\u0005\u0013bAA\"Y\u0005)q\n]*vEB\u0019\u0001(a\u0012\u0005\u000byC!\u0019A\u001e\t\r\u0001D\u0001\u0019AA#\u0003\u0019!S.\u001b8vgV1\u0011qJA/\u0003+\"B!!\u0015\u0002`Q!\u00111KA,!\rA\u0014Q\u000b\u0003\u0006\u000b&\u0011\ra\u000f\u0005\u0007\u000f&\u0001\u001d!!\u0017\u0011\u0011\u0005}BJUA.\u0003'\u00022\u0001OA/\t\u0015q\u0016B1\u0001<\u0011\u0019\u0001\u0017\u00021\u0001\u0002\\\u00051B\u0005]3sG\u0016tG\u000fJ2pY>tG\u0005]3sG\u0016tG/\u0006\u0004\u0002f\u0005e\u00141\u000e\u000b\u0005\u0003O\nY\b\u0006\u0003\u0002j\u00055\u0004c\u0001\u001d\u0002l\u0011)QI\u0003b\u0001w!1qI\u0003a\u0002\u0003_\u0002\u0002\"!\u001dM%\u0006]\u0014\u0011\u000e\b\u0004W\u0005M\u0014bAA;Y\u0005)q\n]'pIB\u0019\u0001(!\u001f\u0005\u000byS!\u0019A\u001e\t\r\u0001T\u0001\u0019AA<\u0003!!\u0003/\u001a:dK:$XCBAA\u0003\u001f\u000b9\t\u0006\u0003\u0002\u0004\u0006EE\u0003BAC\u0003\u0013\u00032\u0001OAD\t\u0015)5B1\u0001<\u0011\u001995\u0002q\u0001\u0002\fBA\u0011\u0011\u000f'S\u0003\u001b\u000b)\tE\u00029\u0003\u001f#QAX\u0006C\u0002mBa\u0001Y\u0006A\u0002\u00055\u0015A\u0004\u0013eSZ$3m\u001c7p]\u0012\"\u0017N^\u000b\u0007\u0003/\u000bY+!(\u0015\t\u0005e\u0015Q\u0016\u000b\u0005\u00037\u000by\nE\u00029\u0003;#Q!\u0012\u0007C\u0002mBaa\u0012\u0007A\u0004\u0005\u0005\u0006\u0003CAR\u0019J\u000bI+a'\u000f\u0007-\n)+C\u0002\u0002(2\nQa\u00149ESZ\u00042\u0001OAV\t\u0015qFB1\u0001<\u0011\u0019\u0001G\u00021\u0001\u0002*\u0006!A\u0005Z5w+\u0019\t\u0019,!1\u0002:R!\u0011QWAb)\u0011\t9,a/\u0011\u0007a\nI\fB\u0003F\u001b\t\u00071\b\u0003\u0004H\u001b\u0001\u000f\u0011Q\u0018\t\t\u0003Gc%+a0\u00028B\u0019\u0001(!1\u0005\u000byk!\u0019A\u001e\t\r\u0001l\u0001\u0019AA`\u00031!S\u000f\u001d\u0013d_2|g\u000eJ;q+\u0019\tI-!8\u0002PR!\u00111ZAp)\u0011\ti-!5\u0011\u0007a\ny\rB\u0003F\u001d\t\u00071\b\u0003\u0004H\u001d\u0001\u000f\u00111\u001b\t\t\u0003+d%+a7\u0002N:\u00191&a6\n\u0007\u0005eG&A\u0003PaB{w\u000fE\u00029\u0003;$QA\u0018\bC\u0002mBa\u0001\u0019\bA\u0002\u0005m\u0017a\u00013piVA\u0011Q\u001dB\u0001\u0003s\fY\u000f\u0006\u0003\u0002h\n\rA\u0003BAu\u0003[\u00042\u0001OAv\t\u0015)uB1\u0001<\u0011\u00199u\u0002q\u0001\u0002pBA\u0011\u0011\u001f'S\u0003o\fIOD\u0002,\u0003gL1!!>-\u0003)y\u0005/T;m\u0013:tWM\u001d\t\u0004q\u0005eHaBA~\u001f\t\u0007\u0011Q \u0002\u0003\u0005\n\u000b2!a@%!\rA$\u0011\u0001\u0003\u0006=>\u0011\ra\u000f\u0005\u0007A>\u0001\r!a@\u0002\u0017Ut\u0017M]=`I\t\fgnZ\u000b\u0005\u0005\u0013\u0011i\u0001\u0006\u0003\u0003\f\t=\u0001c\u0001\u001d\u0003\u000e\u0011)Q\t\u0005b\u0001w!1q\t\u0005a\u0002\u0005#\u0001rAa\u0005\u0002,I\u0013YAD\u0002,\u0005+I1Aa\u0006-\u0003\u0015y\u0005OT8u\u00039!\u0013-\u001c9%G>dwN\u001c\u0013b[B,bA!\b\u00032\t\rB\u0003\u0002B\u0010\u0005g!BA!\t\u0003&A\u0019\u0001Ha\t\u0005\u000b\u0015\u000b\"\u0019A\u001e\t\r\u001d\u000b\u00029\u0001B\u0014!!\u0011I\u0003\u0014*\u00030\t\u0005bbA\u0016\u0003,%\u0019!Q\u0006\u0017\u0002\u000b=\u0003\u0018I\u001c3\u0011\u0007a\u0012\t\u0004B\u0003_#\t\u00071\b\u0003\u0004a#\u0001\u0007!qF\u0001\u000fI\t\f'\u000fJ2pY>tGEY1s+\u0019\u0011ID!\u0014\u0003@Q!!1\bB()\u0011\u0011iD!\u0011\u0011\u0007a\u0012y\u0004B\u0003F%\t\u00071\b\u0003\u0004H%\u0001\u000f!1\t\t\t\u0005\u000bb%Ka\u0013\u0003>9\u00191Fa\u0012\n\u0007\t%C&\u0001\u0003Pa>\u0013\bc\u0001\u001d\u0003N\u0011)aL\u0005b\u0001w!1\u0001M\u0005a\u0001\u0005\u0017\n!\u0003J;qIU\u0004HeY8m_:$S\u000f\u001d\u0013vaV1!Q\u000bB5\u00057\"BAa\u0016\u0003lQ!!\u0011\fB/!\rA$1\f\u0003\u0006\u000bN\u0011\ra\u000f\u0005\u0007\u000fN\u0001\u001dAa\u0018\u0011\u0011\t\u0005DJ\u0015B4\u00053r1a\u000bB2\u0013\r\u0011)\u0007L\u0001\u0006\u001fBDvN\u001d\t\u0004q\t%D!\u00020\u0014\u0005\u0004Y\u0004B\u00021\u0014\u0001\u0004\u00119'\u0001\u0003%C6\u0004XC\u0002B9\u0005\u007f\u00129\b\u0006\u0003\u0003t\t\u0005E\u0003\u0002B;\u0005s\u00022\u0001\u000fB<\t\u0015)EC1\u0001<\u0011\u00199E\u0003q\u0001\u0003|AA!\u0011\u0006'S\u0005{\u0012)\bE\u00029\u0005\u007f\"QA\u0018\u000bC\u0002mBa\u0001\u0019\u000bA\u0002\tu\u0014\u0001\u0002\u0013cCJ,bAa\"\u0003\u0016\n5E\u0003\u0002BE\u0005/#BAa#\u0003\u0010B\u0019\u0001H!$\u0005\u000b\u0015+\"\u0019A\u001e\t\r\u001d+\u00029\u0001BI!!\u0011)\u0005\u0014*\u0003\u0014\n-\u0005c\u0001\u001d\u0003\u0016\u0012)a,\u0006b\u0001w!1\u0001-\u0006a\u0001\u0005'\u000ba\u0001J;qIU\u0004XC\u0002BO\u0005W\u0013\u0019\u000b\u0006\u0003\u0003 \n5F\u0003\u0002BQ\u0005K\u00032\u0001\u000fBR\t\u0015)eC1\u0001<\u0011\u00199e\u0003q\u0001\u0003(BA!\u0011\r'S\u0005S\u0013\t\u000bE\u00029\u0005W#QA\u0018\fC\u0002mBa\u0001\u0019\fA\u0002\t%\u0016A\u0002\u0013uS6,7/\u0006\u0004\u00034\n\u001d'\u0011\u0018\u000b\u0005\u0005k\u0013I\r\u0006\u0003\u00038\nm\u0006c\u0001\u001d\u0003:\u0012)Qi\u0006b\u0001w!1qi\u0006a\u0002\u0005{\u0003\u0002Ba0M%\n\u0015'q\u0017\b\u0004W\t\u0005\u0017b\u0001BbY\u0005Yq\n]'vY6\u000bGO]5y!\rA$q\u0019\u0003\u0006=^\u0011\ra\u000f\u0005\u0007A^\u0001\rA!2\u0002\u0003Q,BAa4\u0003TR!!\u0011\u001bBk!\rA$1\u001b\u0003\u0006\u000bb\u0011\ra\u000f\u0005\u0007\u000fb\u0001\u001dAa6\u0011\u000f\te'q\u001c*\u0003R6\u0011!1\u001c\u0006\u0004\u0005;l\u0012aB:vaB|'\u000f^\u0005\u0005\u0005C\u0014YN\u0001\u0007DC:$&/\u00198ta>\u001cX-A\u0004%ENd\u0017m\u001d5\u0016\r\t\u001d(1 Bw)\u0011\u0011IO!@\u0015\t\t-(q\u001e\t\u0004q\t5H!B#\u001a\u0005\u0004Y\u0004BB$\u001a\u0001\b\u0011\t\u0010\u0005\u0005\u0003t2\u0013&\u0011 Bv\u001d\rY#Q_\u0005\u0004\u0005od\u0013aD(q'>dg/Z'biJL\u0007PQ=\u0011\u0007a\u0012Y\u0010B\u0003_3\t\u00071\b\u0003\u0004a3\u0001\u0007!\u0011`\u000b\u000b\u0007\u0003\u0019\tba\b\u0004&\r\u001dACBB\u0002\u0007S\u0019i\u0003\u0006\u0004\u0004\u0006\r-11\u0003\t\u0004q\r\u001dAABB\u00055\t\u00071H\u0001\u0004SKN,H\u000e\u001e\u0005\u0007\u000fj\u0001\u001da!\u0004\u0011\u000f\te'q\u001c*\u0004\u0010A\u0019\u0001h!\u0005\u0005\u000b\u0015S\"\u0019A\u001e\t\u000f\rU!\u0004q\u0001\u0004\u0018\u0005A1-\u00198TY&\u001cW\r\u0005\u0007\u0003Z\u000ee1qBB\u000f\u0007G\u0019)!\u0003\u0003\u0004\u001c\tm'!C\"b]Nc\u0017nY33!\rA4q\u0004\u0003\u0007\u0007CQ\"\u0019A\u001e\u0003\rMc\u0017nY32!\rA4Q\u0005\u0003\u0007\u0007OQ\"\u0019A\u001e\u0003\rMc\u0017nY33\u0011\u001d\u0019YC\u0007a\u0001\u0007;\t\u0011!\u0019\u0005\u0007Aj\u0001\raa\t\u0016\u0011\rE2qHB&\u0007o!Baa\r\u0004NQ11QGB\u001d\u0007\u0003\u00022\u0001OB\u001c\t\u0019\u0019Ia\u0007b\u0001w!1qi\u0007a\u0002\u0007w\u0001rA!7\u0003`J\u001bi\u0004E\u00029\u0007\u007f!Q!R\u000eC\u0002mBqa!\u0006\u001c\u0001\b\u0019\u0019\u0005\u0005\u0006\u0003Z\u000e\u00153QHB%\u0007kIAaa\u0012\u0003\\\nA1)\u00198TY&\u001cW\rE\u00029\u0007\u0017\"aa!\t\u001c\u0005\u0004Y\u0004bBB\u00167\u0001\u00071\u0011\n"
)
public interface ImmutableNumericOps extends HasOps {
   Object repr();

   // $FF: synthetic method
   static Object $plus$colon$plus$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$plus$colon$plus(b, op);
   }

   default Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $times$colon$times$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$times$colon$times(b, op);
   }

   default Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $colon$eq$eq$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$colon$eq$eq(b, op);
   }

   default Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $colon$bang$eq$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$colon$bang$eq(b, op);
   }

   default Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object unary_$minus$(final ImmutableNumericOps $this, final UFunc.UImpl op) {
      return $this.unary_$minus(op);
   }

   default Object unary_$minus(final UFunc.UImpl op) {
      return op.apply(this.repr());
   }

   // $FF: synthetic method
   static Object $minus$colon$minus$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$minus$colon$minus(b, op);
   }

   default Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $minus$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$minus(b, op);
   }

   default Object $minus(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $percent$colon$percent$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$percent$colon$percent(b, op);
   }

   default Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $percent$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$percent(b, op);
   }

   default Object $percent(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $div$colon$div$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$div$colon$div(b, op);
   }

   default Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $div$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$div(b, op);
   }

   default Object $div(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $up$colon$up$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$up$colon$up(b, op);
   }

   default Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object dot$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.dot(b, op);
   }

   default Object dot(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object unary_$bang$(final ImmutableNumericOps $this, final UFunc.UImpl op) {
      return $this.unary_$bang(op);
   }

   default Object unary_$bang(final UFunc.UImpl op) {
      return op.apply(this.repr());
   }

   // $FF: synthetic method
   static Object $amp$colon$amp$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$amp$colon$amp(b, op);
   }

   default Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $bar$colon$bar$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$bar$colon$bar(b, op);
   }

   default Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $up$up$colon$up$up$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$up$up$colon$up$up(b, op);
   }

   default Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $amp$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$amp(b, op);
   }

   default Object $amp(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $bar$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$bar(b, op);
   }

   default Object $bar(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $up$up$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$up$up(b, op);
   }

   default Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object $times$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$times(b, op);
   }

   default Object $times(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object t$(final ImmutableNumericOps $this, final CanTranspose op) {
      return $this.t(op);
   }

   default Object t(final CanTranspose op) {
      return op.apply(this.repr());
   }

   // $FF: synthetic method
   static Object $bslash$(final ImmutableNumericOps $this, final Object b, final UFunc.UImpl2 op) {
      return $this.$bslash(b, op);
   }

   default Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return op.apply(this.repr(), b);
   }

   // $FF: synthetic method
   static Object t$(final ImmutableNumericOps $this, final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return $this.t(a, b, op, canSlice);
   }

   default Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return canSlice.apply(op.apply(this.repr()), a, b);
   }

   // $FF: synthetic method
   static Object t$(final ImmutableNumericOps $this, final Object a, final CanTranspose op, final CanSlice canSlice) {
      return $this.t(a, op, canSlice);
   }

   default Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return canSlice.apply(op.apply(this.repr()), a);
   }

   static void $init$(final ImmutableNumericOps $this) {
   }
}
