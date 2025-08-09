package org.apache.spark.deploy.worker;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;Q!\u0002\u0004\t\u0002E1Qa\u0005\u0004\t\u0002QAQ!I\u0001\u0005\u0002\tBQaI\u0001\u0005\u0002\u0011BQ\u0001O\u0001\u0005\ne\nQ\u0002\u0012:jm\u0016\u0014xK]1qa\u0016\u0014(BA\u0004\t\u0003\u00199xN]6fe*\u0011\u0011BC\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0013\u00035\taAA\u0007Ee&4XM],sCB\u0004XM]\n\u0004\u0003UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\u0015\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002!;\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$h\bF\u0001\u0012\u0003\u0011i\u0017-\u001b8\u0015\u0005\u0015B\u0003C\u0001\f'\u0013\t9sC\u0001\u0003V]&$\b\"B\u0015\u0004\u0001\u0004Q\u0013\u0001B1sON\u00042AF\u0016.\u0013\tasCA\u0003BeJ\f\u0017\u0010\u0005\u0002/k9\u0011qf\r\t\u0003a]i\u0011!\r\u0006\u0003eA\ta\u0001\u0010:p_Rt\u0014B\u0001\u001b\u0018\u0003\u0019\u0001&/\u001a3fM&\u0011ag\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Q:\u0012!E:fiV\u0004H)\u001a9f]\u0012,gnY5fgR\u0019QE\u000f\"\t\u000bm\"\u0001\u0019\u0001\u001f\u0002\r1|\u0017\rZ3s!\ti\u0004)D\u0001?\u0015\ty$\"\u0001\u0003vi&d\u0017BA!?\u0005UiU\u000f^1cY\u0016,&\u000bT\"mCN\u001cHj\\1eKJDQa\u0011\u0003A\u00025\nq!^:fe*\u000b'\u000f"
)
public final class DriverWrapper {
   public static void main(final String[] args) {
      DriverWrapper$.MODULE$.main(args);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return DriverWrapper$.MODULE$.LogStringContext(sc);
   }
}
