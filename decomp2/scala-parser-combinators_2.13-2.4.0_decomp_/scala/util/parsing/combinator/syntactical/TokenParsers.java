package scala.util.parsing.combinator.syntactical;

import scala.reflect.ScalaSignature;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.token.Tokens;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002G\u0005\u0001\u0003B\u0003\u001a\u0001\t\u0005!\u0004C\u0004$\u0001\t\u0007i\u0011\u0001\u0013\u0006\t\u001d\u0002\u0001\u0001\u000b\u0002\r)>\\WM\u001c)beN,'o\u001d\u0006\u0003\r\u001d\t1b]=oi\u0006\u001cG/[2bY*\u0011\u0001\"C\u0001\u000bG>l'-\u001b8bi>\u0014(B\u0001\u0006\f\u0003\u001d\u0001\u0018M]:j]\u001eT!\u0001D\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u001d\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u0012+A\u0011!cE\u0007\u0002\u001b%\u0011A#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y9R\"A\u0004\n\u0005a9!a\u0002)beN,'o\u001d\u0002\u0007)>\\WM\\:\u0012\u0005mq\u0002C\u0001\n\u001d\u0013\tiRBA\u0004O_RD\u0017N\\4\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0005\u0005:\u0011!\u0002;pW\u0016t\u0017BA\r!\u0003\u001daW\r_5dC2,\u0012!\n\t\u0003M\u0005i\u0011\u0001\u0001\u0002\u0005\u000b2,W\u000e\u0005\u0002*U9\u0011aEA\u0005\u0003W\t\u0012Q\u0001V8lK:\u0004"
)
public interface TokenParsers extends Parsers {
   Tokens lexical();
}
