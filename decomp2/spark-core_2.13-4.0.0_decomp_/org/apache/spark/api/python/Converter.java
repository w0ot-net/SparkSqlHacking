package org.apache.spark.api.python;

import java.io.Serializable;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U4qa\u0002\u0005\u0011\u0002G\u00051\u0003C\u0003(\u0001\u0019\u0005\u0001f\u0002\u0004:\u0011!\u0005\u0001B\u000f\u0004\u0007\u000f!A\t\u0001\u0003\u001f\t\u000b)\u001bA\u0011A&\t\u000b1\u001bA\u0011A'\t\u000f5\u001c\u0011\u0011!C\u0005]\nI1i\u001c8wKJ$XM\u001d\u0006\u0003\u0013)\ta\u0001]=uQ>t'BA\u0006\r\u0003\r\t\u0007/\u001b\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sO\u000e\u0001Qc\u0001\u000b8WM\u0019\u0001!F\u000e\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g!\taBE\u0004\u0002\u001eE9\u0011a$I\u0007\u0002?)\u0011\u0001EE\u0001\u0007yI|w\u000e\u001e \n\u0003aI!aI\f\u0002\u000fA\f7m[1hK&\u0011QE\n\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003G]\tqaY8om\u0016\u0014H\u000f\u0006\u0002*iA\u0011!f\u000b\u0007\u0001\t\u0019a\u0003\u0001\"b\u0001[\t\tQ+\u0005\u0002/cA\u0011acL\u0005\u0003a]\u0011qAT8uQ&tw\r\u0005\u0002\u0017e%\u00111g\u0006\u0002\u0004\u0003:L\b\"B\u001b\u0002\u0001\u00041\u0014aA8cUB\u0011!f\u000e\u0003\u0007q\u0001A)\u0019A\u0017\u0003\u0003Q\u000b\u0011bQ8om\u0016\u0014H/\u001a:\u0011\u0005m\u001aQ\"\u0001\u0005\u0014\t\r)Rh\u0011\t\u0003}\u0005k\u0011a\u0010\u0006\u0003\u00012\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u0005~\u0012q\u0001T8hO&tw\r\u0005\u0002E\u00136\tQI\u0003\u0002G\u000f\u0006\u0011\u0011n\u001c\u0006\u0002\u0011\u0006!!.\u0019<b\u0013\t)S)\u0001\u0004=S:LGO\u0010\u000b\u0002u\u0005Yq-\u001a;J]N$\u0018M\\2f+\rq\u0015k\u0015\u000b\u0004\u001fR\u000b\u0007\u0003B\u001e\u0001!J\u0003\"AK)\u0005\u000ba*!\u0019A\u0017\u0011\u0005)\u001aF!\u0002\u0017\u0006\u0005\u0004i\u0003\"B+\u0006\u0001\u00041\u0016AD2p]Z,'\u000f^3s\u00072\f7o\u001d\t\u0004-]K\u0016B\u0001-\u0018\u0005\u0019y\u0005\u000f^5p]B\u0011!L\u0018\b\u00037r\u0003\"AH\f\n\u0005u;\u0012A\u0002)sK\u0012,g-\u0003\u0002`A\n11\u000b\u001e:j]\u001eT!!X\f\t\u000b\t,\u0001\u0019A2\u0002!\u0011,g-Y;mi\u000e{gN^3si\u0016\u0014\bg\u00013gUB!1\bA3j!\tQc\rB\u0005hC\u0006\u0005\t\u0011!B\u0001Q\n\u0019q\fJ\u0019\u0012\u0005A\u000b\u0004C\u0001\u0016k\t%Y\u0017-!A\u0001\u0002\u000b\u0005ANA\u0002`II\n\"A\f*\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003=\u0004\"\u0001]:\u000e\u0003ET!A]$\u0002\t1\fgnZ\u0005\u0003iF\u0014aa\u00142kK\u000e$\b"
)
public interface Converter extends Serializable {
   static Converter getInstance(final Option converterClass, final Converter defaultConverter) {
      return Converter$.MODULE$.getInstance(converterClass, defaultConverter);
   }

   static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Converter$.MODULE$.LogStringContext(sc);
   }

   Object convert(final Object obj);
}
