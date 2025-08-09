package scala.reflect.macros.blackbox;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.macros.Aliases;
import scala.reflect.macros.Enclosures;
import scala.reflect.macros.Evals;
import scala.reflect.macros.ExprUtils;
import scala.reflect.macros.FrontEnds;
import scala.reflect.macros.Infrastructure;
import scala.reflect.macros.Internals;
import scala.reflect.macros.Names;
import scala.reflect.macros.Parsers;
import scala.reflect.macros.Reifiers;
import scala.reflect.macros.Typers;
import scala.reflect.macros.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005Q3q!\u0002\u0004\u0011\u0002G\u0005q\u0002C\u00047\u0001\t\u0007i\u0011A\u001c\t\u000fm\u0002!\u0019!D\u0001y\u0011)a\t\u0001B\u0001\u000f\"9a\n\u0001b\u0001\u000e\u0003y%aB\"p]R,\u0007\u0010\u001e\u0006\u0003\u000f!\t\u0001B\u00197bG.\u0014w\u000e\u001f\u0006\u0003\u0013)\ta!\\1de>\u001c(BA\u0006\r\u0003\u001d\u0011XM\u001a7fGRT\u0011!D\u0001\u0006g\u000e\fG.Y\u0002\u0001'5\u0001\u0001\u0003\u0006\r\u001c=\u0005\"sEK\u00171gA\u0011\u0011CE\u0007\u0002\u0019%\u00111\u0003\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005U1R\"\u0001\u0005\n\u0005]A!aB!mS\u0006\u001cXm\u001d\t\u0003+eI!A\u0007\u0005\u0003\u0015\u0015s7\r\\8tkJ,7\u000f\u0005\u0002\u00169%\u0011Q\u0004\u0003\u0002\u0006\u001d\u0006lWm\u001d\t\u0003+}I!\u0001\t\u0005\u0003\u0011I+\u0017NZ5feN\u0004\"!\u0006\u0012\n\u0005\rB!!\u0003$s_:$XI\u001c3t!\t)R%\u0003\u0002'\u0011\tq\u0011J\u001c4sCN$(/^2ukJ,\u0007CA\u000b)\u0013\tI\u0003B\u0001\u0004UsB,'o\u001d\t\u0003+-J!\u0001\f\u0005\u0003\u000fA\u000b'o]3sgB\u0011QCL\u0005\u0003_!\u0011Q!\u0012<bYN\u0004\"!F\u0019\n\u0005IB!!C#yaJ,F/\u001b7t!\t)B'\u0003\u00026\u0011\tI\u0011J\u001c;fe:\fGn]\u0001\tk:Lg/\u001a:tKV\t\u0001\b\u0005\u0002\u0016s%\u0011!\b\u0003\u0002\t+:Lg/\u001a:tK\u00061Q.\u001b:s_J,\u0012!\u0010\t\u0003}\u0001s!aP\u0001\u000e\u0003\u0001I!!\u0011\"\u0003\r5K'O]8s\u0013\t\u0019EIA\u0004NSJ\u0014xN]:\u000b\u0005\u0015S\u0011aA1qS\nQ\u0001K]3gSb$\u0016\u0010]3\u0012\u0005![\u0005CA\tJ\u0013\tQEBA\u0004O_RD\u0017N\\4\u0011\u0005Ea\u0015BA'\r\u0005\r\te._\u0001\u0007aJ,g-\u001b=\u0016\u0003A\u00032aP)T\u0013\t\u0011fC\u0001\u0003FqB\u0014\bCA \u0004\u0001"
)
public interface Context extends Aliases, Enclosures, Names, Reifiers, FrontEnds, Infrastructure, Typers, Parsers, Evals, ExprUtils, Internals {
   Universe universe();

   Mirror mirror();

   Exprs.Expr prefix();
}
