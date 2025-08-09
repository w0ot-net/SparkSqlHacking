package scala.util.parsing.combinator;

import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.util.parsing.input.Reader;

public class Parsers$NoSuccess$I$ {
   public Some unapply(final Parsers.NoSuccess x) {
      if (x instanceof Parsers.Failure) {
         Parsers.Failure var4 = (Parsers.Failure)x;
         String msg = var4.msg();
         Reader next = var4.next();
         return new Some(new Tuple2(msg, next));
      } else if (x instanceof Parsers.Error) {
         Parsers.Error var7 = (Parsers.Error)x;
         String msg = var7.msg();
         Reader next = var7.next();
         return new Some(new Tuple2(msg, next));
      } else {
         throw new MatchError(x);
      }
   }

   public Parsers$NoSuccess$I$(final Parsers.NoSuccess$ $outer) {
   }
}
