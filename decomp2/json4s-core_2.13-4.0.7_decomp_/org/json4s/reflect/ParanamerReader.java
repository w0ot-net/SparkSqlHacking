package org.json4s.reflect;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005;Q!\u0002\u0004\t\u000251Qa\u0004\u0004\t\u0002AAQAG\u0001\u0005\u0002mAa\u0001H\u0001!\u0002\u0013i\u0002\"\u0002\u0014\u0002\t\u00039\u0013a\u0004)be\u0006t\u0017-\\3s%\u0016\fG-\u001a:\u000b\u0005\u001dA\u0011a\u0002:fM2,7\r\u001e\u0006\u0003\u0013)\taA[:p]R\u001a(\"A\u0006\u0002\u0007=\u0014xm\u0001\u0001\u0011\u00059\tQ\"\u0001\u0004\u0003\u001fA\u000b'/\u00198b[\u0016\u0014(+Z1eKJ\u001c2!A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011a\u0002G\u0005\u00033\u0019\u00111\u0003U1sC6,G/\u001a:OC6,'+Z1eKJ\fa\u0001P5oSRtD#A\u0007\u0002\u0013A\f'/\u00198b[\u0016\u0014\bC\u0001\u0010%\u001b\u0005y\"B\u0001\u000f!\u0015\t\t#%\u0001\u0007uQ>,x\r\u001b;x_J\\7OC\u0001$\u0003\r\u0019w.\\\u0005\u0003K}\u0011\u0001cQ1dQ&tw\rU1sC:\fW.\u001a:\u0002)1|wn[;q!\u0006\u0014\u0018-\\3uKJt\u0015-\\3t)\tAC\bE\u0002*cQr!AK\u0018\u000f\u0005-rS\"\u0001\u0017\u000b\u00055b\u0011A\u0002\u001fs_>$h(C\u0001\u0015\u0013\t\u00014#A\u0004qC\u000e\\\u0017mZ3\n\u0005I\u001a$aA*fc*\u0011\u0001g\u0005\t\u0003ker!AN\u001c\u0011\u0005-\u001a\u0012B\u0001\u001d\u0014\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a\u001a\u0002\"B\u001f\u0005\u0001\u0004q\u0014aC2p]N$(/^2u_J\u0004\"AD \n\u0005\u00013!AC#yK\u000e,H/\u00192mK\u0002"
)
public final class ParanamerReader {
   public static Seq lookupParameterNames(final Executable constructor) {
      return ParanamerReader$.MODULE$.lookupParameterNames(constructor);
   }
}
