package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059:Q\u0001B\u0003\t\u0002A1QAE\u0003\t\u0002MAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005\u0002q\t\u0001CV3sg&|g.\u0012=ue\u0006\u001cGo\u001c:\u000b\u0005\u00199\u0011!B:dC2\f'B\u0001\u0005\n\u0003\u0019iw\u000eZ;mK*\u0011!bC\u0001\bU\u0006\u001c7n]8o\u0015\taQ\"A\u0005gCN$XM\u001d=nY*\ta\"A\u0002d_6\u001c\u0001\u0001\u0005\u0002\u0012\u00035\tQA\u0001\tWKJ\u001c\u0018n\u001c8FqR\u0014\u0018m\u0019;peN\u0011\u0011\u0001\u0006\t\u0003+]i\u0011A\u0006\u0006\u0002\r%\u0011\u0001D\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0001\u0012aB;oCB\u0004H.\u001f\u000b\u0003;\u0019\u00022!\u0006\u0010!\u0013\tybC\u0001\u0003T_6,\u0007\u0003B\u000b\"G\rJ!A\t\f\u0003\rQ+\b\u000f\\33!\t)B%\u0003\u0002&-\t\u0019\u0011J\u001c;\t\u000b\u001d\u001a\u0001\u0019\u0001\u0015\u0002\u0003Y\u0004\"!\u000b\u0017\u000e\u0003)R!aK\u0005\u0002\t\r|'/Z\u0005\u0003[)\u0012qAV3sg&|g\u000e"
)
public final class VersionExtractor {
   public static Some unapply(final Version v) {
      return VersionExtractor$.MODULE$.unapply(v);
   }
}
