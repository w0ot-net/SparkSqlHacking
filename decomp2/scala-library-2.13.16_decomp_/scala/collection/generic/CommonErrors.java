package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M:a!\u0002\u0004\t\u0002!aaA\u0002\b\u0007\u0011\u0003Aq\u0002C\u0003\u0015\u0003\u0011\u0005a\u0003C\u0003\u0018\u0003\u0011\u0005\u0001\u0004C\u0003\u0018\u0003\u0011\u0005\u0001'\u0001\u0007D_6lwN\\#se>\u00148O\u0003\u0002\b\u0011\u00059q-\u001a8fe&\u001c'BA\u0005\u000b\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0017\u0005)1oY1mCB\u0011Q\"A\u0007\u0002\r\ta1i\\7n_:,%O]8sgN\u0011\u0011\u0001\u0005\t\u0003#Ii\u0011AC\u0005\u0003')\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u00031\t\u0001#\u001b8eKb|U\u000f^(g\u0005>,h\u000eZ:\u0015\u0007e)#\u0006\u0005\u0002\u001bE9\u00111\u0004\t\b\u00039}i\u0011!\b\u0006\u0003=U\ta\u0001\u0010:p_Rt\u0014\"A\u0006\n\u0005\u0005R\u0011a\u00029bG.\fw-Z\u0005\u0003G\u0011\u0012\u0011$\u00138eKb|U\u000f^(g\u0005>,h\u000eZ:Fq\u000e,\u0007\u000f^5p]*\u0011\u0011E\u0003\u0005\u0006M\r\u0001\raJ\u0001\u0006S:$W\r\u001f\t\u0003#!J!!\u000b\u0006\u0003\u0007%sG\u000fC\u0003,\u0007\u0001\u0007q%A\u0002nCbD#aA\u0017\u0011\u0005Eq\u0013BA\u0018\u000b\u0005!qw.\u001b8mS:,GCA\r2\u0011\u00151C\u00011\u0001(Q\t!Q\u0006"
)
public final class CommonErrors {
   public static IndexOutOfBoundsException indexOutOfBounds(final int index) {
      return CommonErrors$.MODULE$.indexOutOfBounds(index);
   }

   public static IndexOutOfBoundsException indexOutOfBounds(final int index, final int max) {
      return CommonErrors$.MODULE$.indexOutOfBounds(index, max);
   }
}
