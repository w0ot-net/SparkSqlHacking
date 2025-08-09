package org.json4s.reflect;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552qAA\u0002\u0011\u0002G\u0005!\u0002C\u0003\u0012\u0001\u0019\u0005!CA\nQCJ\fW.\u001a;fe:\u000bW.\u001a*fC\u0012,'O\u0003\u0002\u0005\u000b\u00059!/\u001a4mK\u000e$(B\u0001\u0004\b\u0003\u0019Q7o\u001c85g*\t\u0001\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u0017A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\fA\u0003\\8pWV\u0004\b+\u0019:b[\u0016$XM\u001d(b[\u0016\u001cHCA\n(!\r!Bd\b\b\u0003+iq!AF\r\u000e\u0003]Q!\u0001G\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011BA\u000e\u000e\u0003\u001d\u0001\u0018mY6bO\u0016L!!\b\u0010\u0003\u0007M+\u0017O\u0003\u0002\u001c\u001bA\u0011\u0001\u0005\n\b\u0003C\t\u0002\"AF\u0007\n\u0005\rj\u0011A\u0002)sK\u0012,g-\u0003\u0002&M\t11\u000b\u001e:j]\u001eT!aI\u0007\t\u000b!\n\u0001\u0019A\u0015\u0002\u0017\r|gn\u001d;sk\u000e$xN\u001d\t\u0003U-j\u0011aA\u0005\u0003Y\r\u0011!\"\u0012=fGV$\u0018M\u00197f\u0001"
)
public interface ParameterNameReader {
   Seq lookupParameterNames(final Executable constructor);
}
