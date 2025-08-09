package scala.collection;

import scala.None$;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tmr!\u0002\u0014(\u0011\u0003ac!\u0002\u0018(\u0011\u0003y\u0003\"\u0002\u001b\u0002\t\u0003)T\u0001\u0002\u001c\u0002\u0001]Bq\u0001U\u0001C\u0002\u0013\u0005\u0011\u000b\u0003\u0004W\u0003\u0001\u0006IAU\u0003\u00051\u0006\u0001\u0011\fC\u0004c\u0003\t\u0007I\u0011A2\t\r!\f\u0001\u0015!\u0003e\u000b\u0011Q\u0017\u0001A6\u0006\tm\f\u0001\u0001`\u0003\u0007\u0003\u0013\t\u0001!a\u0003\t\u0011\u0005e\u0011A1A\u0005\u0002\rDq!!\b\u0002A\u0003%A-\u0002\u0004\u0002\"\u0005\u0001\u00111\u0005\u0005\t\u0003[\t!\u0019!C\u0001#\"9\u0011\u0011G\u0001!\u0002\u0013\u0011VABA\u001b\u0003\u0001\t9\u0004\u0003\u0005\u0002B\u0005\u0011\r\u0011\"\u0001R\u0011\u001d\t)%\u0001Q\u0001\nI+a!!\u0013\u0002\u0001\u0005-\u0003\"CA+\u0003\t\u0007I\u0011AA,\u0011!\t\t'\u0001Q\u0001\n\u0005eSABA3\u0003\u0001\t9\u0007C\u0005\u0002v\u0005\u0011\r\u0011\"\u0001\u0002x!A\u0011\u0011Q\u0001!\u0002\u0013\tI(\u0002\u0004\u0002\u0006\u0006\u0001\u0011q\u0011\u0005\n\u0003;\u000b!\u0019!C\u0001\u0003?C\u0001\"!+\u0002A\u0003%\u0011\u0011U\u0003\b\u0003[\u000b\u0001!KAX\u000f\u001d\t\u0019,\u0001E\u0001\u0003k3q!!/\u0002\u0011\u0003\tY\f\u0003\u00045?\u0011\u0005\u0011Q\u0018\u0005\b\u0003\u007f{B\u0011AAa\u000f\u001d\u0011\t!\u0001E\u0001\u0005\u00071qA!\u0002\u0002\u0011\u0003\u00119\u0001\u0003\u00045G\u0011\u0005!\u0011\u0002\u0005\b\u0003\u007f\u001bC\u0011\u0001B\u0006\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001K\u0015\u0002\u0015\r|G\u000e\\3di&|gNC\u0001+\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!L\u0001\u000e\u0003\u001d\u0012q\u0001]1dW\u0006<Wm\u0005\u0002\u0002aA\u0011\u0011GM\u0007\u0002S%\u00111'\u000b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005a#a\u0003+sCZ,'o]1cY\u0016,\"\u0001O\u001f\u0011\u00075J4(\u0003\u0002;O\tA\u0011\n^3sC\ndW\r\u0005\u0002={1\u0001AA\u0002 \u0004\t\u000b\u0007qHA\u0001Y#\t\u00015\t\u0005\u00022\u0003&\u0011!)\u000b\u0002\b\u001d>$\b.\u001b8h!\t\tD)\u0003\u0002FS\t\u0019\u0011I\\=)\r\r9%jS'O!\t\t\u0004*\u0003\u0002JS\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\nA*A\u0012Vg\u0016\u0004\u0013\n^3sC\ndW\rI5ogR,\u0017\r\u001a\u0011pM\u0002\"&/\u0019<feN\f'\r\\3\u0002\u000bMLgnY3\"\u0003=\u000baA\r\u00182g9\u0002\u0014a\u0003+sCZ,'o]1cY\u0016,\u0012A\u0015\b\u0003[MK!\u0001V\u0014\u0002\u0011%#XM]1cY\u0016Dc\u0001B$K\u00176s\u0015\u0001\u0004+sCZ,'o]1cY\u0016\u0004\u0003FB\u0003H\u0015.keJA\bUe\u00064XM]:bE2,wJ\\2f+\tQf\fE\u0002.7vK!\u0001X\u0014\u0003\u0019%#XM]1cY\u0016|enY3\u0011\u0005qrFA\u0002 \u0007\t\u000b\u0007q\b\u000b\u0004\u0007\u000f*\u0003WJT\u0011\u0002C\u0006YSk]3!\u0013R,'/\u00192mK>s7-\u001a\u0011j]N$X-\u00193!_\u001a\u0004CK]1wKJ\u001c\u0018M\u00197f\u001f:\u001cW-A\bUe\u00064XM]:bE2,wJ\\2f+\u0005!gBA\u0017f\u0013\t1w%\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW\r\u000b\u0004\b\u000f*\u0003WJT\u0001\u0011)J\fg/\u001a:tC\ndWm\u00148dK\u0002Bc\u0001C$KA6s%aB*fc2K7.Z\u000b\u0004YB4\b#B\u0017n_J,\u0018B\u00018(\u0005\u0019\u0019V-](qgB\u0011A\b\u001d\u0003\u0006c&\u0011\ra\u0010\u0002\u0002\u0003B\u0011Qf]\u0005\u0003i\u001e\u00121aU3r!\tad\u000fB\u0003x\u0013\t\u0007qHA\u0001UQ\u0019IqIS=N\u001d\u0006\n!0A\u000fVg\u0016\u00043+Z9PaN\u0004\u0013N\\:uK\u0006$\u0007e\u001c4!'\u0016\fH*[6f\u0005%\t%O]1z\u0019&\\W-\u0006\u0002~\u007fB1Q&\u001c@s\u0003\u0003\u0001\"\u0001P@\u0005\u000bET!\u0019A \u0011\u00075\u001ah\u0010K\u0004\u000b\u000f*\u000b)!\u0014(\"\u0005\u0005\u001d\u0011\u0001X+tK\u0002\u001aV-](qg\u0002Bcm\u001c:!i\",\u0007%\\3uQ>$7/\u000b\u0011pe\u0002Je\u000eZ3yK\u0012\u001cV-](qg\u0002Bcm\u001c:!M\u0006\u001cH\u000fI5oI\u0016DX\r\u001a\u0011bG\u000e,7o]\u0015!S:\u001cH/Z1eA=4\u0007%\u0011:sCfd\u0015n[3\u0003%\u001d+g\u000e\u0016:bm\u0016\u00148/\u00192mK>s7-Z\u000b\u0005\u0003\u001b\t\t\u0002\u0005\u0003.7\u0006=\u0001c\u0001\u001f\u0002\u0012\u00111ah\u0003CC\u0002}BsaC$K\u0003+ie*\t\u0002\u0002\u0018\u00059s)\u001a8+A\r|G\u000e\\3di&|g\u000e\t;za\u0016\u001c\b\u0005[1wK\u0002\u0012W-\u001a8!e\u0016lwN^3e\u0003I9UM\u001c+sCZ,'o]1cY\u0016|enY3)\u000f19%*!\u0006N\u001d\u0006\u0019r)\u001a8Ue\u00064XM]:bE2,wJ\\2fA!:Qb\u0012&\u0002\u00165s%AD$f]R\u0013\u0018M^3sg\u0006\u0014G.Z\u000b\u0005\u0003K\tI\u0003\u0005\u0003.s\u0005\u001d\u0002c\u0001\u001f\u0002*\u00111aH\u0004CC\u0002}BsAD$K\u0003+ie*\u0001\bHK:$&/\u0019<feN\f'\r\\3)\u000f=9%*!\u0006N\u001d\u0006yq)\u001a8Ue\u00064XM]:bE2,\u0007\u0005K\u0004\u0011\u000f*\u000b)\"\u0014(\u0003\u0017\u001d+g.\u0013;fe\u0006\u0014G.Z\u000b\u0005\u0003s\ti\u0004\u0005\u0003.s\u0005m\u0002c\u0001\u001f\u0002>\u00111a(\u0005CC\u0002}Bs!E$K\u0003+ie*A\u0006HK:LE/\u001a:bE2,\u0007f\u0002\nH\u0015\u0006UQJT\u0001\r\u000f\u0016t\u0017\n^3sC\ndW\r\t\u0015\b'\u001dS\u0015QC'O\u0005\u00199UM\\*fcV!\u0011QJA)!\u0011i3/a\u0014\u0011\u0007q\n\t\u0006\u0002\u0004?)\u0011\u0015\ra\u0010\u0015\b)\u001dS\u0015QC'O\u0003\u00199UM\\*fcV\u0011\u0011\u0011\f\b\u0004[\u0005m\u0013bAA/O\u0005\u00191+Z9)\u000fU9%*!\u0006N\u001d\u00069q)\u001a8TKF\u0004\u0003f\u0002\fH\u0015\u0006UQJ\u0014\u0002\u0007\u000f\u0016t7+\u001a;\u0016\t\u0005%\u0014\u0011\u000f\t\u0006[\u0005-\u0014qN\u0005\u0004\u0003[:#aA*fiB\u0019A(!\u001d\u0005\u000by:\"\u0019A )\u000f]9%*!\u0006N\u001d\u00061q)\u001a8TKR,\"!!\u001f\u000f\u00075\nY(C\u0002\u0002~\u001d\n1aU3uQ\u001dArISA\u000b\u001b:\u000bqaR3o'\u0016$\b\u0005K\u0004\u001a\u000f*\u000b)\"\u0014(\u0003\r\u001d+g.T1q+\u0019\tI)!%\u0002\u0018B9Q&a#\u0002\u0010\u0006U\u0015bAAGO\t\u0019Q*\u00199\u0011\u0007q\n\t\n\u0002\u0004\u0002\u0014j\u0011\ra\u0010\u0002\u0002\u0017B\u0019A(a&\u0005\u000f\u0005e%\u0004\"b\u0001\u007f\t\ta\u000bK\u0004\u001b\u000f*\u000b)\"\u0014(\u0002\r\u001d+g.T1q+\t\t\tKD\u0002.\u0003GK1!!*(\u0003\ri\u0015\r\u001d\u0015\b7\u001dS\u0015QC'O\u0003\u001d9UM\\'ba\u0002Bs\u0001H$K\u0003+ieJA\u0005B]f\u001cuN\\:ueV\u00191)!-\u0005\u000byj\"\u0019A \u0002\u0017\u0011\u0002H.^:%G>dwN\u001c\t\u0004\u0003o{R\"A\u0001\u0003\u0017\u0011\u0002H.^:%G>dwN\\\n\u0003?A\"\"!!.\u0002\u000fUt\u0017\r\u001d9msVA\u00111YAj\u0003C\f9\u000e\u0006\u0003\u0002F\u0006]\b#B\u0019\u0002H\u0006-\u0017bAAeS\t1q\n\u001d;j_:\u0004r!MAg\u0003#\f).C\u0002\u0002P&\u0012a\u0001V;qY\u0016\u0014\u0004c\u0001\u001f\u0002T\u0012)\u0011/\tb\u0001\u007fA\u0019A(a6\u0005\u000f\u0005e\u0017E1\u0001\u0002\\\n\t1)E\u0002A\u0003;\u0004\u0002\"L7\u0002R\u0006}\u0017Q\u001b\t\u0004y\u0005\u0005HaBArC\t\u0007\u0011Q\u001d\u0002\u0003\u0007\u000e+B!a:\u0002tF\u0019\u0001)!;1\t\u0005-\u0018q\u001e\t\u0005[M\fi\u000fE\u0002=\u0003_$1\"!=\u0002b\u0006\u0005\t\u0011!B\u0001\u007f\t\u0019q\fJ\u0019\u0005\u000f\u0005U\u0018\u0011\u001db\u0001\u007f\t!q\f\n\u00132\u0011\u001d\tI0\ta\u0001\u0003w\f\u0011\u0001\u001e\n\u0007\u0003{\f).!8\u0007\r\u0005}x\u0004AA~\u00051a$/\u001a4j]\u0016lWM\u001c;?\u0003-!3m\u001c7p]\u0012\u0002H.^:\u0011\u0007\u0005]6EA\u0006%G>dwN\u001c\u0013qYV\u001c8CA\u00121)\t\u0011\u0019!\u0006\u0005\u0003\u000e\tu!\u0011\u0005B\u000b)\u0011\u0011yA!\u000e\u0011\u000bE\n9M!\u0005\u0011\u000fE\niMa\u0005\u0003\u001cA\u0019AH!\u0006\u0005\u000f\u0005eWE1\u0001\u0003\u0018E\u0019\u0001I!\u0007\u0011\u00115j'1\u0004B\u0010\u0005'\u00012\u0001\u0010B\u000f\t\u0015\tXE1\u0001@!\ra$\u0011\u0005\u0003\b\u0003G,#\u0019\u0001B\u0012+\u0011\u0011)C!\r\u0012\u0007\u0001\u00139\u0003\r\u0003\u0003*\t5\u0002\u0003B\u0017t\u0005W\u00012\u0001\u0010B\u0017\t-\u0011yC!\t\u0002\u0002\u0003\u0005)\u0011A \u0003\u0007}##\u0007B\u0004\u00034\t\u0005\"\u0019A \u0003\t}#CE\r\u0005\b\u0003s,\u0003\u0019\u0001B\u001c%\u0019\u0011IDa\u0005\u0003\u001a\u00191\u0011q`\u0012\u0001\u0005o\u0001"
)
public final class package {
   /** @deprecated */
   public static Map$ GenMap() {
      return package$.MODULE$.GenMap();
   }

   /** @deprecated */
   public static Set$ GenSet() {
      return package$.MODULE$.GenSet();
   }

   /** @deprecated */
   public static Seq$ GenSeq() {
      return package$.MODULE$.GenSeq();
   }

   /** @deprecated */
   public static Iterable$ GenIterable() {
      return package$.MODULE$.GenIterable();
   }

   /** @deprecated */
   public static Iterable$ GenTraversable() {
      return package$.MODULE$.GenTraversable();
   }

   /** @deprecated */
   public static IterableOnce$ GenTraversableOnce() {
      return package$.MODULE$.GenTraversableOnce();
   }

   /** @deprecated */
   public static IterableOnce$ TraversableOnce() {
      return package$.MODULE$.TraversableOnce();
   }

   /** @deprecated */
   public static Iterable$ Traversable() {
      return package$.MODULE$.Traversable();
   }

   public static class $plus$colon$ {
      public static final $plus$colon$ MODULE$ = new $plus$colon$();

      public Option unapply(final SeqOps t) {
         if (t.isEmpty()) {
            return None$.MODULE$;
         } else {
            Predef.ArrowAssoc$ var10002 = Predef.ArrowAssoc$.MODULE$;
            var10002 = (Predef.ArrowAssoc$)t.head();
            Object $minus$greater$extension_y = t.tail();
            Object $minus$greater$extension_$this = var10002;
            Tuple2 var7 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
            $minus$greater$extension_$this = null;
            $minus$greater$extension_y = null;
            return new Some(var7);
         }
      }
   }

   public static class $colon$plus$ {
      public static final $colon$plus$ MODULE$ = new $colon$plus$();

      public Option unapply(final SeqOps t) {
         if (t.isEmpty()) {
            return None$.MODULE$;
         } else {
            Predef.ArrowAssoc$ var10002 = Predef.ArrowAssoc$.MODULE$;
            var10002 = (Predef.ArrowAssoc$)t.init();
            Object $minus$greater$extension_y = t.last();
            Object $minus$greater$extension_$this = var10002;
            Tuple2 var7 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
            $minus$greater$extension_$this = null;
            $minus$greater$extension_y = null;
            return new Some(var7);
         }
      }
   }
}
