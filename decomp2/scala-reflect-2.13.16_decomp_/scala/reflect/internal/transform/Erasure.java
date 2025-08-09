package scala.reflect.internal.transform;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Nil.;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.StdAttachments;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tMga\u0002+V!\u0003\r\tA\u0018\u0005\u0006G\u0002!\t\u0001\u001a\u0005\bQ\u0002\u0011\rQ\"\u0001j\u000f\u0015q\u0007\u0001#\u0001p\r\u0015\t\b\u0001#\u0001s\u0011\u0015\u0019H\u0001\"\u0001u\u0011\u0015)H\u0001\"\u0003w\u0011\u001d\ti\u0001\u0002C\u0001\u0003\u001fAq!!\n\u0001\t\u0003\t9\u0003C\u0004\u0002,\u0001!\t\"!\f\t\u000f\u0005\u0005\u0003\u0001\"\u0001\u0002D!9\u0011q\n\u0001\u0005\u0002\u0005EcaBA/\u0001\u0005\u0005\u0011q\f\u0005\u0007g2!\t!a\u001c\t\u000f\u0005MDB\"\u0001\u0002v!9\u0011\u0011\u0012\u0007\u0007\u0002\u0005-\u0005bBAL\u0019\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003;cA\u0011CAP\u0011\u001d\t\u0019\u000b\u0004C\u0001\u0003KCq!!+\r\t\u0003\tY\u000bC\u0004\u00020\u0002!\t\"!-\t\u000f\u0005M\u0006\u0001\"\u0001\u00026\"9\u00111\u0018\u0001\u0005\u0002\u0005u\u0006bBAc\u0001\u0011\u0005\u0011q\u0019\u0004\b\u0003\u001b\u0004\u0011\u0011AAh\u0011\u0019\u0019\b\u0004\"\u0001\u0002f\"9\u00111\u000f\r\u0005\u0002\u0005%haCAj\u0001A\u0005\u0019\u0011AAk\u0003?DQaY\u000e\u0005\u0002\u0011Dq!!#\u001c\t\u0003\t9N\u0002\u0004\u0002n\u0002\u0001\u0011q\u001e\u0005\u0007gz!\t!!=\t\u000f\u0005Md\u0004\"\u0001\u0002v\"9\u0011\u0011 \u0010\u0005\u0002\u0005m\bbBAU=\u0011\u0005\u0013q \u0005\b\u0003\u0013sB\u0011\u0001B\u0002\u0011\u001d\u0011YA\bC\u0005\u0005\u001bAqAa\u0005\u001f\t\u0013\u0011)\u0002C\u0007\u0003\u001ay\u0001\n1!A\u0001\n\u0013\u0011Yb\u0005\u0004\u0007\u0005?\u0001\u0001A!\t\t\rM<C\u0011\u0001B\u0012\u0011\u001d\t\u0019h\nC\u0001\u0005OAq!!((\t#\u0012YcB\u0004\u00030\u0001A\tA!\r\u0007\u000f\tM\u0002\u0001#\u0001\u00036!11\u000f\fC\u0001\u0005o9qA!\u000f\u0001\u0011\u0003\u0011YDB\u0004\u0003>\u0001A\tAa\u0010\t\rM|C\u0011\u0001B!\r%\u0011\u0019\u0005\u0001I\u0001\u0004\u0003\u0011)\u0005C\u0003dc\u0011\u0005A\rC\u0004\u0002\u001eF\"\tEa\u0012\b\u000f\t-\u0003\u0001#\u0001\u0003N\u00199!q\n\u0001\t\u0002\tE\u0003BB:6\t\u0003\u0011)fB\u0004\u0003X\u0001A\tA!\u0017\u0007\u000f\tm\u0003\u0001#\u0001\u0003^!11\u000f\u000fC\u0001\u0005?BqA!\u0019\u0001\t\u0003\u0011\u0019gB\u0004\u0003h\u0001A\tA!\u001b\u0007\u000f\t-\u0004\u0001#\u0001\u0003n!11\u000f\u0010C\u0001\u0005_:qA!\u001d\u0001\u0011\u0003\u0011\u0019HB\u0004\u0003v\u0001A\tAa\u001e\t\rM|D\u0011\u0001B=\u0011\u001d\t\u0019k\u0010C!\u0005w2\u0011Ba \u0001!\u0003\r\tA!!\t\u000b\r\u0014E\u0011\u00013\t\u0013\t\r%\t1Q\u0005\n\u0005E\u0006\"\u0003BC\u0005\u0002\u0007K\u0011\u0002BD\u0011\u001d\tIK\u0011C!\u0005\u001bCq!a&C\t\u0003\u0012\t\nC\u0004\u0002\u001e\n#\tE!&\t\u001b\te!\t%A\u0002\u0002\u0003%IA!'\u0014\u00115\u0011iJ\u0011I\u0001\u0004\u0003\u0005I\u0011\u0002BP!\u001d9!1\u0015\u0001\t\u0002\t\u0015fa\u0002BT\u0001!\u0005!\u0011\u0016\u0005\u0007g2#\tA!,\b\u000f\t=\u0006\u0001#\u0001\u00032\u001a9!1\u0017\u0001\t\u0002\tU\u0006BB:P\t\u0003\u00119\fC\u0004\u0003:\u0002!\tAa/\t\u000f\t}\u0006\u0001\"\u0001\u0003B\"9!1\u001a\u0001\u0005\u0002\t5'aB#sCN,(/\u001a\u0006\u0003-^\u000b\u0011\u0002\u001e:b]N4wN]7\u000b\u0005aK\u0016\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005i[\u0016a\u0002:fM2,7\r\u001e\u0006\u00029\u0006)1oY1mC\u000e\u00011C\u0001\u0001`!\t\u0001\u0017-D\u0001\\\u0013\t\u00117L\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0015\u0004\"\u0001\u00194\n\u0005\u001d\\&\u0001B+oSR\faa\u001a7pE\u0006dW#\u00016\u0011\u0005-dW\"A,\n\u00055<&aC*z[\n|G\u000eV1cY\u0016\fAbR3oKJL7-\u0011:sCf\u0004\"\u0001\u001d\u0003\u000e\u0003\u0001\u0011AbR3oKJL7-\u0011:sCf\u001c\"\u0001B0\u0002\rqJg.\u001b;?)\u0005y\u0017aC4f]\u0016\u0014\u0018nY\"pe\u0016$\"a^?\u0011\u0005aLhB\u00019\u0003\u0013\tQ8P\u0001\u0003UsB,\u0017B\u0001?X\u0005\u0015!\u0016\u0010]3t\u0011\u0015qh\u00011\u0001x\u0003\t!\b\u000fK\u0002\u0007\u0003\u0003\u0001B!a\u0001\u0002\n5\u0011\u0011Q\u0001\u0006\u0004\u0003\u000fY\u0016AC1o]>$\u0018\r^5p]&!\u00111BA\u0003\u0005\u001d!\u0018-\u001b7sK\u000e\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0012\u0005\r\u0002#\u00021\u0002\u0014\u0005]\u0011bAA\u000b7\n1q\n\u001d;j_:\u0004b\u0001YA\r\u0003;9\u0018bAA\u000e7\n1A+\u001e9mKJ\u00022\u0001YA\u0010\u0013\r\t\tc\u0017\u0002\u0004\u0013:$\b\"\u0002@\b\u0001\u00049\u0018AG;oE>,h\u000eZ3e\u000f\u0016tWM]5d\u0003J\u0014\u0018-\u001f'fm\u0016dG\u0003BA\u000f\u0003SAQA \u0005A\u0002]\f\u0001C]3cS:$\u0017J\u001c8fe\u000ec\u0017m]:\u0015\u000b]\fy#a\r\t\r\u0005E\u0012\u00021\u0001x\u0003\r\u0001(/\u001a\u0005\b\u0003kI\u0001\u0019AA\u001c\u0003\r\u0019Gn\u001d\t\u0004q\u0006e\u0012\u0002BA\u001e\u0003{\u0011aaU=nE>d\u0017bAA /\n91+_7c_2\u001c\u0018aE3sCN,GMV1mk\u0016\u001cE.Y:t\u0003J<GcA<\u0002F!9\u0011q\t\u0006A\u0002\u0005%\u0013\u0001\u0002;sK\u001a\u00042\u0001_A&\u0013\r\tie\u001f\u0002\b)f\u0004XMU3g\u0003Y1\u0018\r\\;f\u00072\f7o]%t!\u0006\u0014\u0018-\\3ue&\u001cG\u0003BA*\u00033\u00022\u0001YA+\u0013\r\t9f\u0017\u0002\b\u0005>|G.Z1o\u0011\u001d\tYf\u0003a\u0001\u0003o\tQa\u00197buj\u0014!\"\u0012:bgV\u0014X-T1q'\ra\u0011\u0011\r\t\u0004q\u0006\r\u0014\u0002BA3\u0003O\u0012q\u0001V=qK6\u000b\u0007/\u0003\u0003\u0002j\u0005-$\u0001\u0003+za\u0016l\u0015\r]:\u000b\u0007\u00055t+A\u0002ua\u0016$\"!!\u001d\u0011\u0005Ad\u0011\u0001D7fe\u001e,\u0007+\u0019:f]R\u001cHcA<\u0002x!9\u0011\u0011\u0010\bA\u0002\u0005m\u0014a\u00029be\u0016tGo\u001d\t\u0006\u0003{\n\u0019i\u001e\b\u0004A\u0006}\u0014bAAA7\u00069\u0001/Y2lC\u001e,\u0017\u0002BAC\u0003\u000f\u0013A\u0001T5ti*\u0019\u0011\u0011Q.\u0002\u0015\u0015\u0014\u0018m]3BeJ\f\u0017\u0010F\u0004x\u0003\u001b\u000b\t*a%\t\r\u0005=u\u00021\u0001x\u0003!\t'O]1z%\u00164\u0007BBA\u0019\u001f\u0001\u0007q\u000fC\u0004\u0002\u0016>\u0001\r!a\u001f\u0002\t\u0005\u0014xm]\u0001\u0014KJ\f7/\u001a(pe6\fGn\u00117bgN\u0014VM\u001a\u000b\u0004o\u0006m\u0005bBA$!\u0001\u0007\u0011\u0011J\u0001\u001aKJ\f7/\u001a#fe&4X\r\u001a,bYV,7\t\\1tgJ+g\rF\u0002x\u0003CCq!a\u0012\u0012\u0001\u0004\tI%A\u0003baBd\u0017\u0010F\u0002x\u0003OCQA \nA\u0002]\fA\"\u00199qYfLe.\u0011:sCf$2a^AW\u0011\u0015q8\u00031\u0001x\u0003E1XM]5gs*\u000bg/Y#sCN,(/Z\u000b\u0003\u0003'\nq!\u001a:bgV\u0014X\r\u0006\u0003\u0002r\u0005]\u0006bBA]+\u0001\u0007\u0011qG\u0001\u0004gfl\u0017AD:qK\u000eL\u0017\r\\#sCN,(/\u001a\u000b\u0005\u0003\u007f\u000b\u0019\rF\u0002x\u0003\u0003DQA \fA\u0002]Dq!!/\u0017\u0001\u0004\t9$A\rta\u0016\u001c\u0017.\u00197D_:\u001cHO];di>\u0014XI]1tkJ,G#B<\u0002J\u0006-\u0007bBA./\u0001\u0007\u0011q\u0007\u0005\u0007\u0003[:\u0002\u0019A<\u0003\u001fM\u001b\u0017\r\\1Fe\u0006\u001cXO]3NCB\u001cR\u0001GA9\u0003#\u0004\"\u0001]\u000e\u0003-M\u001b\u0017\r\\13\u0015\u00064\u0018-\u0011:sCf,%/Y:ve\u0016\u001c\"aG0\u0015\u000f]\fI.a7\u0002^\"1\u0011qR\u000fA\u0002]Da!!\r\u001e\u0001\u00049\bbBAK;\u0001\u0007\u00111\u0010\n\u0007\u0003C\f\t.!\u001d\u0007\r\u0005\r\b\u0001AAp\u00051a$/\u001a4j]\u0016lWM\u001c;?)\t\t9\u000f\u0005\u0002q1Q\u0019q/a;\t\u000f\u0005e$\u00041\u0001\u0002|\t\u00012kY1mCN*%/Y:ve\u0016l\u0015\r]\n\u0004=\u0005EDCAAz!\t\u0001h\u0004F\u0002x\u0003oDq!!\u001f!\u0001\u0004\tY(A\nnKJ<W\rU1sK:$8/\u00138BeJ\f\u0017\u0010F\u0002x\u0003{Dq!!\u001f\"\u0001\u0004\tY\bF\u0002x\u0005\u0003AQA \u0012A\u0002]$ra\u001eB\u0003\u0005\u000f\u0011I\u0001\u0003\u0004\u0002\u0010\u000e\u0002\ra\u001e\u0005\u0007\u0003c\u0019\u0003\u0019A<\t\u000f\u0005U5\u00051\u0001\u0002|\u0005IQM]1tK\u0012<EN\u0019\u000b\u0004o\n=\u0001b\u0002B\tI\u0001\u0007\u00111P\u0001\u000bG>l\u0007o\u001c8f]R\u001c\u0018!F5t\u000f\u0016tWM]5d\u0003J\u0014\u0018-_#mK6,g\u000e\u001e\u000b\u0005\u0003'\u00129\u0002C\u0003\u007fK\u0001\u0007q/\u0001\ntkB,'\u000fJ1qa2L\u0018J\\!se\u0006LHcA<\u0003\u001e!)aP\na\u0001o\nq!*\u0019<b\u000bJ\f7/\u001e:f\u001b\u0006\u00048#B\u0014\u0002r\u0005EGC\u0001B\u0013!\t\u0001x\u0005F\u0002x\u0005SAq!!\u001f*\u0001\u0004\tY\bF\u0002x\u0005[Aq!a\u0012+\u0001\u0004\tI%\u0001\u0007tG\u0006d\u0017-\u0012:bgV\u0014X\r\u0005\u0002qY\ta1oY1mC\u0016\u0013\u0018m];sKN\u0019A&a:\u0015\u0005\tE\u0012!D:dC2\f7'\u0012:bgV\u0014X\r\u0005\u0002q_\ti1oY1mCN*%/Y:ve\u0016\u001c2aLAz)\t\u0011YDA\nTa\u0016\u001c\u0017.\u00197TG\u0006d\u0017-\u0012:bgV\u0014XmE\u00022\u0003c\"2a\u001eB%\u0011\u001d\t9e\ra\u0001\u0003\u0013\n1c\u001d9fG&\fGnU2bY\u0006,%/Y:ve\u0016\u0004\"\u0001]\u001b\u0003'M\u0004XmY5bYN\u001b\u0017\r\\1Fe\u0006\u001cXO]3\u0014\u000bU\n9Oa\u0015\u0011\u0005A\fDC\u0001B'\u0003Q\u0019\b/Z2jC2\u001c6-\u00197bg\u0015\u0013\u0018m];sKB\u0011\u0001\u000f\u000f\u0002\u0015gB,7-[1m'\u000e\fG.Y\u001aFe\u0006\u001cXO]3\u0014\u000ba\n\u0019Pa\u0015\u0015\u0005\te\u0013AF:qK\u000eL\u0017\r\\*dC2\fWI]1tkJ,gi\u001c:\u0015\t\u0005E$Q\r\u0005\b\u0003sS\u0004\u0019AA\u001c\u0003-Q\u0017M^1Fe\u0006\u001cXO]3\u0011\u0005Ad$a\u00036bm\u0006,%/Y:ve\u0016\u001c2\u0001\u0010B\u0013)\t\u0011I'A\nwKJLg-[3e\u0015\u00064\u0018-\u0012:bgV\u0014X\r\u0005\u0002q\u007f\t\u0019b/\u001a:jM&,GMS1wC\u0016\u0013\u0018m];sKN\u0019qH!\n\u0015\u0005\tMDcA<\u0003~!)a0\u0011a\u0001o\ni!i\u001c=j]\u001e,%/Y:ve\u0016\u001c2AQA9\u00035\u0011w\u000e\u001f)sS6LG/\u001b<fg\u0006\t\"m\u001c=Qe&l\u0017\u000e^5wKN|F%Z9\u0015\u0007\u0015\u0014I\tC\u0005\u0003\f\u0016\u000b\t\u00111\u0001\u0002T\u0005\u0019\u0001\u0010J\u0019\u0015\u0007]\u0014y\tC\u0003\u007f\r\u0002\u0007q\u000fF\u0002x\u0005'Cq!a\u0012H\u0001\u0004\tI\u0005F\u0002x\u0005/Cq!a\u0012I\u0001\u0004\tI\u0005F\u0002x\u00057CQA`%A\u0002]\f\u0011d];qKJ$SM]1tK:{'/\\1m\u00072\f7o\u001d*fMR\u0019qO!)\t\u000f\u0005\u001d#\n1\u0001\u0002J\u0005i!m\u001c=j]\u001e,%/Y:ve\u0016\u0004\"\u0001\u001d'\u0003\u001b\t|\u00070\u001b8h\u000bJ\f7/\u001e:f'\u0015a\u0015q\u001dBV!\t\u0001(\t\u0006\u0002\u0003&\u0006q!m\u001c=j]\u001e\u001cTI]1tkJ,\u0007C\u00019P\u00059\u0011w\u000e_5oON*%/Y:ve\u0016\u001cRaTAz\u0005W#\"A!-\u0002+%tG/\u001a:tK\u000e$\u0018n\u001c8E_6Lg.\u0019;peR\u0019qO!0\t\u000f\u0005e\u0014\u000b1\u0001\u0002|\u0005\u0011BO]1ogB\f'/\u001a8u\t\u0016\fG.[1t)\u001d9(1\u0019Bc\u0005\u000fDq!!/S\u0001\u0004\t9\u0004\u0003\u0004\u00022I\u0003\ra\u001e\u0005\b\u0005\u0013\u0014\u0006\u0019AA\u001c\u0003\u0015ywO\\3s\u00035!(/\u00198tM>\u0014X.\u00138g_R)qOa4\u0003R\"9\u0011\u0011X*A\u0002\u0005]\u0002\"\u0002@T\u0001\u00049\b"
)
public interface Erasure {
   GenericArray$ GenericArray();

   scalaErasure$ scalaErasure();

   scala3Erasure$ scala3Erasure();

   specialScalaErasure$ specialScalaErasure();

   specialScala3Erasure$ specialScala3Erasure();

   javaErasure$ javaErasure();

   verifiedJavaErasure$ verifiedJavaErasure();

   boxingErasure$ boxingErasure();

   boxing3Erasure$ boxing3Erasure();

   SymbolTable global();

   // $FF: synthetic method
   static int unboundedGenericArrayLevel$(final Erasure $this, final Types.Type tp) {
      return $this.unboundedGenericArrayLevel(tp);
   }

   default int unboundedGenericArrayLevel(final Types.Type tp) {
      if (tp != null) {
         Option var2 = this.GenericArray().unapply(tp);
         if (!var2.isEmpty()) {
            int level = ((Tuple2)var2.get())._1$mcI$sp();
            Types.Type core = (Types.Type)((Tuple2)var2.get())._2();
            if (!core.$less$colon$less(this.global().definitions().AnyRefTpe())) {
               Types.Type var10000 = core.upperBound();
               Types.ObjectTpeJavaRef var5 = this.global().definitions().ObjectTpeJava();
               if (var10000 == null) {
                  if (var5 != null) {
                     return level;
                  }
               } else if (!var10000.equals(var5)) {
                  return level;
               }
            }
         }
      }

      if (tp instanceof Types.RefinedType) {
         List ps = ((Types.RefinedType)tp).parents();
         if (ps.nonEmpty()) {
            SymbolTable var10 = this.global();
            Function0 var10001 = () -> (new StringBuilder(31)).append("Unbounded generic level for ").append(tp).append(" is").toString();
            int boxToInteger_i = this.unboundedGenericArrayLevel(this.intersectionDominator(ps));
            Integer logResult_result = boxToInteger_i;
            Function0 logResult_msg = var10001;
            if (var10 == null) {
               throw null;
            }

            var10.log(SymbolTable::$anonfun$logResult$1);
            return boxToInteger_i;
         }
      }

      return 0;
   }

   // $FF: synthetic method
   static Types.Type rebindInnerClass$(final Erasure $this, final Types.Type pre, final Symbols.Symbol cls) {
      return $this.rebindInnerClass(pre, cls);
   }

   default Types.Type rebindInnerClass(final Types.Type pre, final Symbols.Symbol cls) {
      return !cls.isTopLevel() && !cls.isLocalToBlock() ? cls.owner().tpe_$times() : pre;
   }

   // $FF: synthetic method
   static Types.Type erasedValueClassArg$(final Erasure $this, final Types.TypeRef tref) {
      return $this.erasedValueClassArg(tref);
   }

   default Types.Type erasedValueClassArg(final Types.TypeRef tref) {
      SymbolTable var10000 = this.global();
      boolean assert_assertion = !this.global().phase().erasedTypes();
      if (var10000 == null) {
         throw null;
      } else {
         SymbolTable assert_this = var10000;
         if (!assert_assertion) {
            throw assert_this.throwAssertionError("Types are erased");
         } else {
            assert_this = null;
            Symbols.Symbol clazz = tref.sym();
            if (this.valueClassIsParametric(clazz)) {
               if (clazz == null) {
                  throw null;
               } else {
                  return ((ErasureMap)(clazz.hasFlag(1152921504606846976L) ? this.boxing3Erasure() : this.boxingErasure())).apply(tref.memberType(clazz.derivedValueClassUnbox()).resultType());
               }
            } else if (clazz == null) {
               throw null;
            } else {
               var10000 = (SymbolTable)(clazz.hasFlag(1152921504606846976L) ? this.scala3Erasure() : this.scalaErasure());
               Definitions.definitions$ var10001 = this.global().definitions();
               if (var10001 == null) {
                  throw null;
               } else {
                  return ((ErasureMap)var10000).apply(Definitions.ValueClassDefinitions.underlyingOfValueClass$(var10001, clazz));
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static boolean valueClassIsParametric$(final Erasure $this, final Symbols.Symbol clazz) {
      return $this.valueClassIsParametric(clazz);
   }

   default boolean valueClassIsParametric(final Symbols.Symbol clazz) {
      SymbolTable var10000 = this.global();
      boolean assert_assertion = !this.global().phase().erasedTypes();
      if (var10000 == null) {
         throw null;
      } else {
         SymbolTable assert_this = var10000;
         if (!assert_assertion) {
            throw assert_this.throwAssertionError("valueClassIsParametric called after erasure");
         } else {
            assert_this = null;
            List var5 = clazz.typeParams();
            Symbols.Symbol var10001 = clazz.derivedValueClassUnbox();
            if (var10001 == null) {
               throw null;
            } else {
               return var5.contains(var10001.tpe_$times().resultType().typeSymbol());
            }
         }
      }
   }

   // $FF: synthetic method
   static boolean verifyJavaErasure$(final Erasure $this) {
      return $this.verifyJavaErasure();
   }

   default boolean verifyJavaErasure() {
      return false;
   }

   // $FF: synthetic method
   static ErasureMap erasure$(final Erasure $this, final Symbols.Symbol sym) {
      return $this.erasure(sym);
   }

   default ErasureMap erasure(final Symbols.Symbol sym) {
      Symbols.NoSymbol var2 = this.global().NoSymbol();
      if (sym == null) {
         if (var2 == null) {
            return this.scalaErasure();
         }
      } else if (sym.equals(var2)) {
         return this.scalaErasure();
      }

      Symbols.Symbol enclosing = sym.enclClass();
      if (enclosing.isJavaDefined()) {
         if (this.verifyJavaErasure() && sym.isMethod()) {
            return this.verifiedJavaErasure();
         } else {
            return this.javaErasure();
         }
      } else if (enclosing.hasFlag(1152921504606846976L)) {
         return this.scala3Erasure();
      } else {
         return this.scalaErasure();
      }
   }

   // $FF: synthetic method
   static Types.Type specialErasure$(final Erasure $this, final Symbols.Symbol sym, final Types.Type tp) {
      return $this.specialErasure(sym, tp);
   }

   default Types.Type specialErasure(final Symbols.Symbol sym, final Types.Type tp) {
      Symbols.NoSymbol var3 = this.global().NoSymbol();
      if (sym == null) {
         if (var3 == null) {
            return sym.isClassConstructor() ? this.specialConstructorErasure(sym.owner(), tp) : this.specialScalaErasureFor(sym).apply(tp);
         }
      } else if (sym.equals(var3)) {
         return sym.isClassConstructor() ? this.specialConstructorErasure(sym.owner(), tp) : this.specialScalaErasureFor(sym).apply(tp);
      }

      if (sym.enclClass().isJavaDefined()) {
         return this.erasure(sym).apply(tp);
      } else {
         return sym.isClassConstructor() ? this.specialConstructorErasure(sym.owner(), tp) : this.specialScalaErasureFor(sym).apply(tp);
      }
   }

   // $FF: synthetic method
   static Types.Type specialConstructorErasure$(final Erasure $this, final Symbols.Symbol clazz, final Types.Type tpe) {
      return $this.specialConstructorErasure(clazz, tpe);
   }

   default Types.Type specialConstructorErasure(final Symbols.Symbol clazz, final Types.Type tpe) {
      if (tpe instanceof Types.PolyType) {
         Types.Type restpe = ((Types.PolyType)tpe).resultType();
         return this.specialConstructorErasure(clazz, restpe);
      } else if (tpe instanceof Types.ExistentialType) {
         Types.Type restpe = ((Types.ExistentialType)tpe).underlying();
         return this.specialConstructorErasure(clazz, restpe);
      } else if (tpe instanceof Types.MethodType) {
         Types.MethodType var5 = (Types.MethodType)tpe;
         List params = var5.params();
         Types.Type restpe = var5.resultType();
         return this.global().new MethodType(this.global().cloneSymbolsAndModify(params, this.specialScalaErasureFor(clazz)), this.specialConstructorErasure(clazz, restpe));
      } else {
         Types.Type pre;
         label60: {
            if (tpe instanceof Types.TypeRef) {
               Types.TypeRef var8 = (Types.TypeRef)tpe;
               pre = var8.pre();
               Symbols.Symbol var10 = var8.sym();
               if (clazz == null) {
                  if (var10 == null) {
                     break label60;
                  }
               } else if (clazz.equals(var10)) {
                  break label60;
               }
            }

            SymbolTable var10000;
            boolean var10001;
            label45: {
               label44: {
                  var10000 = this.global();
                  Symbols.ClassSymbol var11 = this.global().definitions().ArrayClass();
                  if (clazz == null) {
                     if (var11 == null) {
                        break label44;
                     }
                  } else if (clazz.equals(var11)) {
                     break label44;
                  }

                  if (!tpe.isError()) {
                     var10001 = false;
                     break label45;
                  }
               }

               var10001 = true;
            }

            boolean assert_assertion = var10001;
            if (var10000 == null) {
               throw null;
            }

            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$specialConstructorErasure$1(tpe, clazz));
            }

            assert_this = null;
            return this.specialScalaErasureFor(clazz).apply(tpe);
         }

         SymbolTable var16 = this.global();
         Nil typeRef_args = .MODULE$;
         if (var16 == null) {
            throw null;
         } else {
            return Types.typeRef$(var16, pre, clazz, typeRef_args);
         }
      }
   }

   // $FF: synthetic method
   static ErasureMap specialScalaErasureFor$(final Erasure $this, final Symbols.Symbol sym) {
      return $this.specialScalaErasureFor(sym);
   }

   default ErasureMap specialScalaErasureFor(final Symbols.Symbol sym) {
      if (sym == null) {
         throw null;
      } else {
         return (ErasureMap)(sym.hasFlag(1152921504606846976L) ? this.specialScala3Erasure() : this.specialScalaErasure());
      }
   }

   // $FF: synthetic method
   static Types.Type intersectionDominator$(final Erasure $this, final List parents) {
      return $this.intersectionDominator(parents);
   }

   default Types.Type intersectionDominator(final List parents) {
      if (parents.isEmpty()) {
         return this.global().definitions().ObjectTpe();
      } else {
         Object var10000;
         if (parents == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)parents.head()).typeSymbol(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)parents.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).typeSymbol(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var29 = null;
         Object var30 = null;
         Object var31 = null;
         Object var32 = null;
         List psyms = (List)var10000;
         if (!psyms.contains(this.global().definitions().ArrayClass())) {
            Iterator cs = parents.iterator().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$intersectionDominator$5(psyms, p)));
            return (Types.Type)(cs.hasNext() ? cs : parents.iterator().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$intersectionDominator$6(psyms, p)))).next();
         } else {
            Definitions.definitions$ var72 = this.global().definitions();
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = parents;

            Object var10002;
            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var10002 = .MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               Types.Type var28 = (Types.Type)filter_filterCommon_noneIn$1_h;
               if ($anonfun$intersectionDominator$2(this, var28) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var10002 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var28 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_x;
                     if ($anonfun$intersectionDominator$2(this, var28) == filter_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           var28 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if ($anonfun$intersectionDominator$2(this, var28) != filter_filterCommon_isFlipped) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var10002 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var41 = null;
                        Object var44 = null;
                        Object var47 = null;
                        Object var50 = null;
                        Object var53 = null;
                        Object var56 = null;
                        Object var59 = null;
                        Object var62 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var37 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  Object var48 = null;
                  Object var51 = null;
                  Object var54 = null;
                  Object var57 = null;
                  Object var60 = null;
                  Object var63 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var34 = null;
            Object var35 = null;
            Object var36 = null;
            Object var38 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            Object var49 = null;
            Object var52 = null;
            Object var55 = null;
            Object var58 = null;
            Object var61 = null;
            Object var64 = null;
            List filter_filterCommon_result = (List)var10002;
            Statics.releaseFence();
            var10002 = filter_filterCommon_result;
            filter_filterCommon_result = null;
            List map_this = (List)var10002;
            if (map_this == .MODULE$) {
               var10002 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$intersectionDominator$3((Types.Type)map_this.head()), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$intersectionDominator$3((Types.Type)map_rest.head()), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10002 = map_h;
            }

            map_this = null;
            Object var66 = null;
            Object var67 = null;
            Object var68 = null;
            Object var69 = null;
            return var72.arrayType(this.intersectionDominator((List)var10002));
         }
      }
   }

   // $FF: synthetic method
   static Types.Type transparentDealias$(final Erasure $this, final Symbols.Symbol sym, final Types.Type pre, final Symbols.Symbol owner) {
      return $this.transparentDealias(sym, pre, owner);
   }

   default Types.Type transparentDealias(final Symbols.Symbol sym, final Types.Type pre, final Symbols.Symbol owner) {
      if (sym == null) {
         throw null;
      } else if (sym.hasFlag(1152921504606846976L) && !sym.isClass()) {
         Option var10000 = sym.attachments().get(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.DottyOpaqueTypeAlias.class));
         if (var10000 == null) {
            throw null;
         } else {
            Option map_this = var10000;
            Object var8;
            if (map_this.isEmpty()) {
               var8 = scala.None..MODULE$;
            } else {
               StdAttachments.DottyOpaqueTypeAlias var6 = (StdAttachments.DottyOpaqueTypeAlias)map_this.get();
               var8 = new Some($anonfun$transparentDealias$1(pre, owner, var6));
            }

            Object var7 = null;
            Option getOrElse_this = (Option)var8;
            return (Types.Type)(getOrElse_this.isEmpty() ? $anonfun$transparentDealias$2(sym, pre, owner) : getOrElse_this.get());
         }
      } else {
         return sym.info().asSeenFrom(pre, owner);
      }
   }

   // $FF: synthetic method
   static Types.Type transformInfo$(final Erasure $this, final Symbols.Symbol sym, final Types.Type tp) {
      return $this.transformInfo(sym, tp);
   }

   default Types.Type transformInfo(final Symbols.Symbol sym, final Types.Type tp) {
      Symbols.MethodSymbol var3 = this.global().definitions().Object_asInstanceOf();
      if (sym == null) {
         if (var3 == null) {
            return sym.info();
         }
      } else if (sym.equals(var3)) {
         return sym.info();
      }

      if (!this.synchronizedPrimitive$1(sym)) {
         Symbols.MethodSymbol var4 = this.global().definitions().Object_isInstanceOf();
         if (sym == null) {
            if (var4 == null) {
               return this.global().new PolyType(sym.info().typeParams(), this.specialErasure(sym, sym.info().resultType()));
            }
         } else if (sym.equals(var4)) {
            return this.global().new PolyType(sym.info().typeParams(), this.specialErasure(sym, sym.info().resultType()));
         }

         Symbols.ClassSymbol var5 = this.global().definitions().ArrayClass();
         if (sym == null) {
            if (var5 == null) {
               return this.global().new PolyType(sym.info().typeParams(), this.specialErasure(sym, sym.info().resultType()));
            }
         } else if (sym.equals(var5)) {
            return this.global().new PolyType(sym.info().typeParams(), this.specialErasure(sym, sym.info().resultType()));
         }

         if (sym.isAbstractType()) {
            return this.global().TypeBounds().apply(this.global().WildcardType(), this.global().WildcardType());
         } else {
            label162: {
               if (sym.isTerm()) {
                  Symbols.Symbol var10000 = sym.owner();
                  Symbols.ClassSymbol var6 = this.global().definitions().ArrayClass();
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label162;
                     }
                  } else if (var10000.equals(var6)) {
                     break label162;
                  }
               }

               Symbols.Symbol var29 = sym.owner();
               Symbols.NoSymbol var20 = this.global().NoSymbol();
               if (var29 == null) {
                  if (var20 == null) {
                     return this.specialErasure(sym, tp);
                  }
               } else if (var29.equals(var20)) {
                  return this.specialErasure(sym, tp);
               }

               var29 = sym.owner().owner();
               Symbols.ClassSymbol var21 = this.global().definitions().ArrayClass();
               if (var29 == null) {
                  if (var21 != null) {
                     return this.specialErasure(sym, tp);
                  }
               } else if (!var29.equals(var21)) {
                  return this.specialErasure(sym, tp);
               }

               if (sym.equals(((LinearSeqOps)this.global().definitions().Array_update().paramss().head()).apply(1))) {
                  return tp;
               }

               return this.specialErasure(sym, tp);
            }

            if (sym.isClassConstructor()) {
               if (tp instanceof Types.MethodType) {
                  Types.MethodType var7 = (Types.MethodType)tp;
                  List params = var7.params();
                  Types.Type var9 = var7.resultType();
                  if (var9 instanceof Types.TypeRef) {
                     Types.TypeRef var10 = (Types.TypeRef)var9;
                     Types.Type pre = var10.pre();
                     Symbols.Symbol sym1 = var10.sym();
                     List args = var10.args();
                     Types.MethodType var42 = new Types.MethodType;
                     SymbolTable var10002 = this.global();
                     List var10003 = this.global().cloneSymbolsAndModify(params, (tpx) -> this.specialErasure(sym, tpx));
                     SymbolTable var10004 = this.global();
                     Types.Type typeRef_pre = this.specialErasure(sym, pre);
                     if (var10004 == null) {
                        throw null;
                     }

                     Types.Type var43 = Types.typeRef$(var10004, typeRef_pre, sym1, args);
                     typeRef_pre = null;
                     var42.<init>(var10003, var43);
                     return var42;
                  }
               }

               throw new MatchError(tp);
            } else {
               Names.Name var31 = sym.name();
               Names.TermName var14 = this.global().nme().apply();
               if (var31 == null) {
                  if (var14 == null) {
                     return tp;
                  }
               } else if (var31.equals(var14)) {
                  return tp;
               }

               var31 = sym.name();
               Names.TermName var15 = this.global().nme().update();
               if (var31 == null) {
                  if (var15 != null) {
                     return this.specialErasure(sym, tp);
                  }
               } else if (!var31.equals(var15)) {
                  return this.specialErasure(sym, tp);
               }

               if (tp instanceof Types.MethodType) {
                  List var16 = ((Types.MethodType)tp).params();
                  if (var16 != null) {
                     List var33 = scala.package..MODULE$.List();
                     if (var33 == null) {
                        throw null;
                     }

                     List unapplySeq_this = var33;
                     SeqOps var34 = SeqFactory.unapplySeq$(unapplySeq_this, var16);
                     Object var28 = null;
                     SeqOps var17 = var34;
                     SeqFactory.UnapplySeqWrapper var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     new SeqFactory.UnapplySeqWrapper(var17);
                     var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int lengthCompare$extension_len = 2;
                     if (var17.lengthCompare(lengthCompare$extension_len) == 0) {
                        var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        int apply$extension_i = 0;
                        Symbols.Symbol index = (Symbols.Symbol)var17.apply(apply$extension_i);
                        var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        var35 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        int apply$extension_i = 1;
                        Symbols.Symbol tvar = (Symbols.Symbol)var17.apply(apply$extension_i);
                        return this.global().new MethodType(new scala.collection.immutable..colon.colon(index.cloneSymbol().setInfo(this.specialErasure(sym, index.tpe_$times())), new scala.collection.immutable..colon.colon(tvar, .MODULE$)), this.global().definitions().UnitTpe());
                     }
                  }
               }

               throw new MatchError(tp);
            }
         }
      } else {
         return sym.info();
      }
   }

   // $FF: synthetic method
   static String $anonfun$erasedValueClassArg$1() {
      return "Types are erased";
   }

   // $FF: synthetic method
   static String $anonfun$valueClassIsParametric$1() {
      return "valueClassIsParametric called after erasure";
   }

   // $FF: synthetic method
   static String $anonfun$specialConstructorErasure$1(final Types.Type x1$1, final Symbols.Symbol clazz$1) {
      return (new StringBuilder(36)).append("unexpected constructor erasure ").append(x1$1).append(" for ").append(clazz$1).toString();
   }

   // $FF: synthetic method
   static Symbols.Symbol $anonfun$intersectionDominator$1(final Types.Type x$5) {
      return x$5.typeSymbol();
   }

   // $FF: synthetic method
   static boolean $anonfun$intersectionDominator$2(final Erasure $this, final Types.Type x$6) {
      Symbols.Symbol var10000 = x$6.typeSymbol();
      Symbols.ClassSymbol var2 = $this.global().definitions().ArrayClass();
      if (var10000 == null) {
         if (var2 == null) {
            return true;
         }
      } else if (var10000.equals(var2)) {
         return true;
      }

      return false;
   }

   // $FF: synthetic method
   static Types.Type $anonfun$intersectionDominator$3(final Types.Type x$7) {
      return (Types.Type)x$7.typeArgs().head();
   }

   // $FF: synthetic method
   static boolean $anonfun$intersectionDominator$4(final Symbols.Symbol psym$1, final Symbols.Symbol qsym) {
      return psym$1 != qsym && qsym.isNonBottomSubClass(psym$1);
   }

   private static boolean isUnshadowed$1(final Symbols.Symbol psym, final List psyms$1) {
      if (psyms$1 == null) {
         throw null;
      } else {
         List exists_these = psyms$1;

         boolean var10000;
         while(true) {
            if (exists_these.isEmpty()) {
               var10000 = false;
               break;
            }

            Symbols.Symbol var3 = (Symbols.Symbol)exists_these.head();
            if ($anonfun$intersectionDominator$4(psym, var3)) {
               var10000 = true;
               break;
            }

            exists_these = (List)exists_these.tail();
         }

         Object var4 = null;
         return !var10000;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$intersectionDominator$5(final List psyms$1, final Types.Type p) {
      Symbols.Symbol psym = p.typeSymbol();
      psym.initialize();
      return psym.isClass() && !psym.isTrait() && isUnshadowed$1(psym, psyms$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$intersectionDominator$6(final List psyms$1, final Types.Type p) {
      return isUnshadowed$1(p.typeSymbol(), psyms$1);
   }

   private static Types.Type visible$1(final Types.Type tp, final Types.Type pre$1, final Symbols.Symbol owner$1) {
      return tp.asSeenFrom(pre$1, owner$1);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$transparentDealias$1(final Types.Type pre$1, final Symbols.Symbol owner$1, final StdAttachments.DottyOpaqueTypeAlias alias) {
      return alias.tpe().asSeenFrom(pre$1, owner$1);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$transparentDealias$2(final Symbols.Symbol sym$1, final Types.Type pre$1, final Symbols.Symbol owner$1) {
      return sym$1.info().asSeenFrom(pre$1, owner$1);
   }

   private boolean synchronizedPrimitive$1(final Symbols.Symbol sym) {
      Symbols.MethodSymbol var2 = this.global().definitions().Object_synchronized();
      if (sym == null) {
         if (var2 == null) {
            return true;
         }
      } else if (sym.equals(var2)) {
         return true;
      }

      Symbols.Symbol var10000 = sym.owner();
      Symbols.MethodSymbol var3 = this.global().definitions().Object_synchronized();
      if (var10000 == null) {
         if (var3 != null) {
            return false;
         }
      } else if (!var10000.equals(var3)) {
         return false;
      }

      if (sym.isTerm()) {
         return true;
      } else {
         return false;
      }
   }

   static void $init$(final Erasure $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$intersectionDominator$2$adapted(final Erasure $this, final Types.Type x$6) {
      return BoxesRunTime.boxToBoolean($anonfun$intersectionDominator$2($this, x$6));
   }

   // $FF: synthetic method
   static Object $anonfun$intersectionDominator$4$adapted(final Symbols.Symbol psym$1, final Symbols.Symbol qsym) {
      return BoxesRunTime.boxToBoolean($anonfun$intersectionDominator$4(psym$1, qsym));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class GenericArray$ {
      // $FF: synthetic field
      private final Erasure $outer;

      private Types.Type genericCore(final Types.Type tp) {
         while(true) {
            Types.Type var2 = tp.dealiasWiden();
            if (var2 instanceof Types.TypeRef) {
               Symbols.Symbol sym = ((Types.TypeRef)var2).sym();
               if (sym.isAbstractType() && (!sym.owner().isJavaDefined() || sym.hasFlag(34359738368L))) {
                  return tp;
               }
            }

            if (!(var2 instanceof Types.ExistentialType)) {
               return this.$outer.global().NoType();
            }

            tp = ((Types.ExistentialType)var2).underlying();
         }
      }

      public Option unapply(final Types.Type tp) {
         while(true) {
            Types.Type var2 = tp.dealiasWiden();
            if (var2 instanceof Types.TypeRef) {
               label41: {
                  Types.TypeRef var3 = (Types.TypeRef)var2;
                  Symbols.Symbol var4 = var3.sym();
                  List var5 = var3.args();
                  Symbols.ClassSymbol var10000 = this.$outer.global().definitions().ArrayClass();
                  if (var10000 == null) {
                     if (var4 != null) {
                        break label41;
                     }
                  } else if (!var10000.equals(var4)) {
                     break label41;
                  }

                  if (var5 != null) {
                     List var17 = scala.package..MODULE$.List();
                     if (var17 == null) {
                        throw null;
                     }

                     List unapplySeq_this = var17;
                     SeqOps var18 = SeqFactory.unapplySeq$(unapplySeq_this, var5);
                     Object var16 = null;
                     SeqOps var6 = var18;
                     SeqFactory.UnapplySeqWrapper var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     new SeqFactory.UnapplySeqWrapper(var6);
                     var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int lengthCompare$extension_len = 1;
                     if (var6.lengthCompare(lengthCompare$extension_len) == 0) {
                        var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        int apply$extension_i = 0;
                        Types.Type arg = (Types.Type)var6.apply(apply$extension_i);
                        Types.Type var8 = this.genericCore(arg);
                        if (this.$outer.global().NoType().equals(var8)) {
                           Option var9 = this.unapply(arg);
                           if (var9 instanceof Some) {
                              Tuple2 var10 = (Tuple2)((Some)var9).value();
                              if (var10 != null) {
                                 int level = var10._1$mcI$sp();
                                 Types.Type core = (Types.Type)var10._2();
                                 return new Some(new Tuple2(level + 1, core));
                              }
                           }

                           if (scala.None..MODULE$.equals(var9)) {
                              return scala.None..MODULE$;
                           }

                           throw new MatchError(var9);
                        }

                        return new Some(new Tuple2(1, var8));
                     }
                  }
               }
            }

            if (!(var2 instanceof Types.ExistentialType)) {
               return scala.None..MODULE$;
            }

            tp = ((Types.ExistentialType)var2).underlying();
         }
      }

      public GenericArray$() {
         if (Erasure.this == null) {
            throw null;
         } else {
            this.$outer = Erasure.this;
            super();
         }
      }
   }

   public abstract class ErasureMap extends TypeMaps.TypeMap {
      // $FF: synthetic field
      public final Erasure $outer;

      public abstract Types.Type mergeParents(final List parents);

      public abstract Types.Type eraseArray(final Types.Type arrayRef, final Types.Type pre, final List args);

      public Types.Type eraseNormalClassRef(final Types.TypeRef tref) {
         if (tref != null) {
            Types.Type pre = tref.pre();
            Symbols.Symbol clazz = tref.sym();
            List args = tref.args();
            Types.Type pre1 = this.apply(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().rebindInnerClass(pre, clazz));
            Nil args1 = .MODULE$;
            if (pre == pre1 && args == args1) {
               return tref;
            } else {
               SymbolTable var10000 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global();
               if (var10000 == null) {
                  throw null;
               } else {
                  return Types.typeRef$(var10000, pre1, clazz, args1);
               }
            }
         } else {
            throw new MatchError((Object)null);
         }
      }

      public Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().erasedValueClassArg(tref);
      }

      public Types.Type apply(final Types.Type tp) {
         if (tp instanceof Types.FoldableConstantType) {
            Constants.Constant ct = ((Types.FoldableConstantType)tp).value();
            if (ct.tag() == 12) {
               Symbols.Symbol var113 = ct.typeValue().typeSymbol();
               Symbols.ClassSymbol var3 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitClass();
               if (var113 == null) {
                  if (var3 != null) {
                     return this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().ConstantType().apply(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().new Constant(this.apply(ct.typeValue())));
                  }
               } else if (!var113.equals(var3)) {
                  return this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().ConstantType().apply(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().new Constant(this.apply(ct.typeValue())));
               }
            }

            return tp;
         } else if (tp instanceof Types.ThisType && ((Types.ThisType)tp).sym().isPackageClass()) {
            return tp;
         } else if (tp instanceof Types.SubType) {
            Types.SubType var4 = (Types.SubType)tp;
            return this.apply(var4.supertype());
         } else if (tp instanceof Types.TypeRef) {
            Types.TypeRef var5 = (Types.TypeRef)tp;
            Types.Type pre = var5.pre();
            Symbols.Symbol sym = var5.sym();
            List args = var5.args();
            if (sym == this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ArrayClass()) {
               return this.eraseArray(tp, pre, args);
            } else if (sym != this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().AnyClass() && sym != this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().AnyValClass() && sym != this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().SingletonClass()) {
               if (sym == this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitClass()) {
                  return this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().BoxedUnitTpe();
               } else if (sym.isRefinementClass()) {
                  return this.apply(this.mergeParents(tp.parents()));
               } else if (sym.isDerivedValueClass()) {
                  return this.eraseDerivedValueClassRef(var5);
               } else if (isDottyEnumSingleton$1(sym)) {
                  return this.apply(this.mergeParents(tp.parents()));
               } else {
                  return sym.isClass() ? this.eraseNormalClassRef(var5) : this.apply(this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().transparentDealias(sym, pre, sym.owner()));
               }
            } else {
               return this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe();
            }
         } else if (tp instanceof Types.PolyType) {
            Types.Type restpe = ((Types.PolyType)tp).resultType();
            return this.apply(restpe);
         } else if (tp instanceof Types.ExistentialType) {
            Types.Type restpe = ((Types.ExistentialType)tp).underlying();
            return this.apply(restpe);
         } else if (tp instanceof Types.MethodType) {
            Types.MethodType var112;
            Types.Type var114;
            SymbolTable var10002;
            List var10003;
            label166: {
               label165: {
                  Types.MethodType var11 = (Types.MethodType)tp;
                  List params = var11.params();
                  Types.Type restpe = var11.resultType();
                  var112 = new Types.MethodType;
                  var10002 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global();
                  var10003 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().cloneSymbolsAndModify(params, this);
                  Symbols.Symbol var10004 = restpe.typeSymbol();
                  Symbols.ClassSymbol var14 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitClass();
                  if (var10004 == null) {
                     if (var14 == null) {
                        break label165;
                     }
                  } else if (var10004.equals(var14)) {
                     break label165;
                  }

                  var114 = this.apply(var11.resultTypeOwnParamTypes());
                  break label166;
               }

               var114 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().UnitTpe();
            }

            var112.<init>(var10003, var114);
            return var112;
         } else if (tp instanceof Types.RefinedType) {
            List parents = ((Types.RefinedType)tp).parents();
            return this.apply(this.mergeParents(parents));
         } else if (tp instanceof Types.AnnotatedType) {
            Types.Type atp = ((Types.AnnotatedType)tp).underlying();
            return this.apply(atp);
         } else if (!(tp instanceof Types.ClassInfoType)) {
            return (Types.Type)(tp instanceof Types.ProtoType ? (Types.ProtoType)tp : tp.mapOver(this));
         } else {
            Types.ClassInfoType var17 = (Types.ClassInfoType)tp;
            List parents = var17.parents();
            Scopes.Scope decls = var17.decls();
            Symbols.Symbol clazz = var17.typeSymbol();
            Object var10000;
            if (!parents.isEmpty() && clazz != this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectClass() && !this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().isPrimitiveValueClass(clazz)) {
               if (clazz == this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ArrayClass()) {
                  Types.Type var22 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe();
                  List $colon$colon_this = .MODULE$;
                  var10000 = new scala.collection.immutable..colon.colon(var22, $colon$colon_this);
                  $colon$colon_this = null;
               } else {
                  List mapConserve_loop$3_pending = parents;
                  List mapConserve_loop$3_unchanged = parents;
                  scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
                  List mapConserve_loop$3_mappedHead = null;

                  while(!mapConserve_loop$3_pending.isEmpty()) {
                     Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
                     Object mapConserve_loop$3_head1 = this.apply(mapConserve_loop$3_head0);
                     if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                        mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                        mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                        mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                        mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                     } else {
                        List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                        List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                        scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                        for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                           scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), .MODULE$);
                           if (mapConserve_loop$3_mappedHead1 == null) {
                              mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                           }

                           if (mapConserve_loop$3_mappedLast1 != null) {
                              mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                           }

                           mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                        }

                        scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, .MODULE$);
                        if (mapConserve_loop$3_mappedHead1 == null) {
                           mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                        }

                        if (mapConserve_loop$3_mappedLast1 != null) {
                           mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                        }

                        List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                        mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                        mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                        mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                        mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                     }
                  }

                  if (mapConserve_loop$3_mappedHead == null) {
                     var10000 = mapConserve_loop$3_unchanged;
                  } else {
                     mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
                     var10000 = mapConserve_loop$3_mappedHead;
                  }

                  mapConserve_loop$3_mappedHead = null;
                  Object var59 = null;
                  Object var60 = null;
                  Object var61 = null;
                  Object var62 = null;
                  Object var63 = null;
                  Object var64 = null;
                  Object var65 = null;
                  Object var66 = null;
                  Object var67 = null;
                  Object var68 = null;
                  Object var69 = null;
                  List mapConserve_result = (List)var10000;
                  Statics.releaseFence();
                  var10000 = mapConserve_result;
                  mapConserve_result = null;
                  List erasedParents = (List)var10000;
                  if (clazz.hasFlag(33554432L) && !clazz.hasFlag(1048576L)) {
                     Types.Type var24 = this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectTpe();
                     List var109 = (List)erasedParents.tail();
                     if (var109 == null) {
                        throw null;
                     }

                     List filter_this = var109;
                     boolean filter_filterCommon_isFlipped = false;
                     List filter_filterCommon_noneIn$1_l = filter_this;

                     while(true) {
                        if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                           var110 = .MODULE$;
                           break;
                        }

                        Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
                        List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
                        Types.Type var56 = (Types.Type)filter_filterCommon_noneIn$1_h;
                        if ($anonfun$apply$1(this, var56) != filter_filterCommon_isFlipped) {
                           List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                           while(true) {
                              if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                                 var110 = filter_filterCommon_noneIn$1_l;
                                 break;
                              }

                              Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                              var56 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_x;
                              if ($anonfun$apply$1(this, var56) == filter_filterCommon_isFlipped) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                                 List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                                 for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                    scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 }

                                 List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                                 List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                                 while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                    Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                    var56 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                                    if ($anonfun$apply$1(this, var56) != filter_filterCommon_isFlipped) {
                                       filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    } else {
                                       while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                          scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                          filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                          filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                          filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                       }

                                       filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                       filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    }
                                 }

                                 if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                                 }

                                 var110 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                                 Object var79 = null;
                                 Object var82 = null;
                                 Object var85 = null;
                                 Object var88 = null;
                                 Object var91 = null;
                                 Object var94 = null;
                                 Object var97 = null;
                                 Object var100 = null;
                                 break;
                              }

                              filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           }

                           Object var75 = null;
                           Object var77 = null;
                           Object var80 = null;
                           Object var83 = null;
                           Object var86 = null;
                           Object var89 = null;
                           Object var92 = null;
                           Object var95 = null;
                           Object var98 = null;
                           Object var101 = null;
                           break;
                        }

                        filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
                     }

                     Object var72 = null;
                     Object var73 = null;
                     Object var74 = null;
                     Object var76 = null;
                     Object var78 = null;
                     Object var81 = null;
                     Object var84 = null;
                     Object var87 = null;
                     Object var90 = null;
                     Object var93 = null;
                     Object var96 = null;
                     Object var99 = null;
                     Object var102 = null;
                     List filter_filterCommon_result = (List)var110;
                     Statics.releaseFence();
                     Object var111 = filter_filterCommon_result;
                     Object var70 = null;
                     filter_filterCommon_result = null;
                     List $colon$colon_this = (List)var111;
                     var10000 = new scala.collection.immutable..colon.colon(var24, $colon$colon_this);
                     $colon$colon_this = null;
                  } else {
                     var10000 = erasedParents;
                  }
               }
            } else {
               var10000 = .MODULE$;
            }

            List newParents = (List)var10000;
            return (Types.Type)(newParents == parents ? tp : this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().new ClassInfoType(newParents, decls, clazz));
         }
      }

      public Types.Type applyInArray(final Types.Type tp) {
         return tp.typeSymbol().isDerivedValueClass() ? this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().javaErasure().apply(tp) : this.apply(tp);
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$ErasureMap$$$outer() {
         return this.$outer;
      }

      private static final boolean isDottyEnumSingleton$1(final Symbols.Symbol sym) {
         return sym.hasFlag(1152921504606846976L) && sym.isModuleClass() && sym.sourceModule().hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.DottyEnumSingleton$.class));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$apply$1(final ErasureMap $this, final Types.Type x$2) {
         return x$2.typeSymbol() != $this.scala$reflect$internal$transform$Erasure$ErasureMap$$$outer().global().definitions().ObjectClass();
      }

      public ErasureMap() {
         if (Erasure.this == null) {
            throw null;
         } else {
            this.$outer = Erasure.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$apply$1$adapted(final ErasureMap $this, final Types.Type x$2) {
         return BoxesRunTime.boxToBoolean($anonfun$apply$1($this, x$2));
      }
   }

   public abstract class ScalaErasureMap extends ErasureMap implements Scala2JavaArrayErasure {
      public Types.Type eraseArray(final Types.Type arrayRef, final Types.Type pre, final List args) {
         return Erasure.Scala2JavaArrayErasure.super.eraseArray(arrayRef, pre, args);
      }

      public Types.Type mergeParents(final List parents) {
         return this.scala$reflect$internal$transform$Erasure$ScalaErasureMap$$$outer().intersectionDominator(parents);
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$ScalaErasureMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer() {
         return this.scala$reflect$internal$transform$Erasure$ScalaErasureMap$$$outer();
      }
   }

   public interface Scala2JavaArrayErasure {
      default Types.Type eraseArray(final Types.Type arrayRef, final Types.Type pre, final List args) {
         if (this.scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer().unboundedGenericArrayLevel(arrayRef) == 1) {
            return this.scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer().global().definitions().ObjectTpe();
         } else if (((Types.Type)args.head()).typeSymbol().isBottomClass()) {
            return this.scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer().global().definitions().arrayType(this.scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer().global().definitions().ObjectTpe());
         } else {
            SymbolTable var10000 = this.scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer().global();
            Types.Type var10001 = ((ErasureMap)this).apply(pre);
            Symbols.ClassSymbol var10002 = this.scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer().global().definitions().ArrayClass();
            Object var10003;
            if (args == .MODULE$) {
               var10003 = .MODULE$;
            } else {
               Types.Type var11 = (Types.Type)args.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((ErasureMap)this).applyInArray(var11), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)args.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var11 = (Types.Type)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((ErasureMap)this).applyInArray(var11), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10003 = map_h;
            }

            Object var12 = null;
            Object var13 = null;
            Object var14 = null;
            Object var15 = null;
            Object typeRef_args = var10003;
            Symbols.ClassSymbol typeRef_sym = var10002;
            Types.Type typeRef_pre = var10001;
            if (var10000 == null) {
               throw null;
            } else {
               return Types.typeRef$(var10000, typeRef_pre, typeRef_sym, (List)typeRef_args);
            }
         }
      }

      // $FF: synthetic method
      Erasure scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer();

      // $FF: synthetic method
      static Types.Type $anonfun$eraseArray$1(final Scala2JavaArrayErasure $this, final Types.Type tp) {
         return ((ErasureMap)$this).applyInArray(tp);
      }

      static void $init$(final Scala2JavaArrayErasure $this) {
      }
   }

   public class Scala3ErasureMap extends ErasureMap {
      // $FF: synthetic method
      private Types.Type super$applyInArray(final Types.Type tp) {
         return super.applyInArray(tp);
      }

      public Types.Type mergeParents(final List parents) {
         if (parents == null) {
            throw null;
         } else {
            Object var10001;
            if (parents == .MODULE$) {
               var10001 = .MODULE$;
            } else {
               Types.Type var6 = (Types.Type)parents.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.apply(var6), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)parents.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var6 = (Types.Type)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.apply(var6), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10001 = map_h;
            }

            Object var7 = null;
            Object var8 = null;
            Object var9 = null;
            Object var10 = null;
            return this.erasedGlb((List)var10001);
         }
      }

      public Types.Type mergeParentsInArray(final List parents) {
         if (parents == null) {
            throw null;
         } else {
            Object var10001;
            if (parents == .MODULE$) {
               var10001 = .MODULE$;
            } else {
               Types.Type var6 = (Types.Type)parents.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$mergeParentsInArray$1(this, var6), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)parents.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var6 = (Types.Type)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$mergeParentsInArray$1(this, var6), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10001 = map_h;
            }

            Object var7 = null;
            Object var8 = null;
            Object var9 = null;
            Object var10 = null;
            return this.erasedGlb((List)var10001);
         }
      }

      public Types.Type applyInArray(final Types.Type tp) {
         if (tp instanceof Types.RefinedType) {
            List parents = ((Types.RefinedType)tp).parents();
            return super.applyInArray(this.mergeParentsInArray(parents));
         } else {
            return super.applyInArray(tp);
         }
      }

      public Types.Type eraseArray(final Types.Type arrayRef, final Types.Type pre, final List args) {
         if (this.isGenericArrayElement((Types.Type)args.head())) {
            return this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().ObjectTpe();
         } else {
            SymbolTable var10000 = this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global();
            Types.Type var10001 = this.apply(pre);
            Symbols.ClassSymbol var10002 = this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().ArrayClass();
            Object var10003;
            if (args == .MODULE$) {
               var10003 = .MODULE$;
            } else {
               Types.Type var11 = (Types.Type)args.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.applyInArray(var11), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)args.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var11 = (Types.Type)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.applyInArray(var11), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10003 = map_h;
            }

            Object var12 = null;
            Object var13 = null;
            Object var14 = null;
            Object var15 = null;
            Object typeRef_args = var10003;
            Symbols.ClassSymbol typeRef_sym = var10002;
            Types.Type typeRef_pre = var10001;
            if (var10000 == null) {
               throw null;
            } else {
               return Types.typeRef$(var10000, typeRef_pre, typeRef_sym, (List)typeRef_args);
            }
         }
      }

      private Types.Type erasedGlb(final List components) {
         return (Types.Type)components.min(new Ordering() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final Scala3ErasureMap $outer;

            public Some tryCompare(final Object x, final Object y) {
               return Ordering.tryCompare$(this, x, y);
            }

            public boolean lteq(final Object x, final Object y) {
               return Ordering.lteq$(this, x, y);
            }

            public boolean gteq(final Object x, final Object y) {
               return Ordering.gteq$(this, x, y);
            }

            public boolean lt(final Object x, final Object y) {
               return Ordering.lt$(this, x, y);
            }

            public boolean gt(final Object x, final Object y) {
               return Ordering.gt$(this, x, y);
            }

            public boolean equiv(final Object x, final Object y) {
               return Ordering.equiv$(this, x, y);
            }

            public Object max(final Object x, final Object y) {
               return Ordering.max$(this, x, y);
            }

            public Object min(final Object x, final Object y) {
               return Ordering.min$(this, x, y);
            }

            public Ordering reverse() {
               return Ordering.reverse$(this);
            }

            public boolean isReverseOf(final Ordering other) {
               return Ordering.isReverseOf$(this, other);
            }

            public Ordering on(final Function1 f) {
               return Ordering.on$(this, f);
            }

            public Ordering orElse(final Ordering other) {
               return Ordering.orElse$(this, other);
            }

            public Ordering orElseBy(final Function1 f, final Ordering ord) {
               return Ordering.orElseBy$(this, f, ord);
            }

            public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
               return Ordering.mkOrderingOps$(this, lhs);
            }

            public final int compare(final Types.Type x, final Types.Type y) {
               return this.$outer.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$anonfun$erasedGlb$1(x, y);
            }

            public {
               if (Scala3ErasureMap.this == null) {
                  throw null;
               } else {
                  this.$outer = Scala3ErasureMap.this;
               }
            }
         });
      }

      private boolean isGenericArrayElement(final Types.Type tp) {
         while(true) {
            LazyRef DottyTypeProxy$module = new LazyRef();
            LazyRef DottyAndType$module = new LazyRef();
            Types.Type var4 = tp.dealias();
            if (var4 instanceof Types.TypeRef) {
               Types.TypeRef var5 = (Types.TypeRef)var4;
               Symbols.Symbol sym = var5.sym();
               if (!isOpaque$1(sym)) {
                  if (!sym.isClass() && !sym.isJavaDefined() && !this.fitsInJVMArray$1(var5, DottyTypeProxy$module, DottyAndType$module)) {
                     return true;
                  }

                  return false;
               }
            }

            if (var4 != null) {
               Option var7 = this.DottyTypeProxy$2(DottyTypeProxy$module).unapply(var4);
               if (!var7.isEmpty()) {
                  tp = (Types.Type)var7.get();
                  continue;
               }
            }

            if (var4 instanceof Types.RefinedType) {
               Types.RefinedType var8 = (Types.RefinedType)var4;
               if (this.DottyAndType$2(DottyAndType$module).unapply(var8)) {
                  List var10000 = var8.parents();
                  if (var10000 == null) {
                     throw null;
                  }

                  for(List forall_these = var10000; !forall_these.isEmpty(); forall_these = (List)forall_these.tail()) {
                     Types.Type var10 = (Types.Type)forall_these.head();
                     if (!$anonfun$isGenericArrayElement$1(this, var10)) {
                        return false;
                     }
                  }

                  return true;
               }
            }

            return false;
         }
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$mergeParents$1(final Scala3ErasureMap $this, final Types.Type x$3) {
         return $this.apply(x$3);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$mergeParentsInArray$1(final Scala3ErasureMap $this, final Types.Type x$4) {
         return $this.super$applyInArray(x$4);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$eraseArray$2(final Scala3ErasureMap $this, final Types.Type tp) {
         return $this.applyInArray(tp);
      }

      private static final int compareClasses$1(final Symbols.Symbol sym1$1, final Symbols.Symbol sym2$1) {
         if (sym1$1.isSubClass(sym2$1)) {
            return -1;
         } else {
            return sym2$1.isSubClass(sym1$1) ? 1 : sym1$1.fullName('.').compareTo(sym2$1.fullName('.'));
         }
      }

      private final int compareErasedGlb$1(final Types.Type tp1, final Types.Type tp2) {
         if (tp1 == tp2) {
            return 0;
         } else {
            boolean isEVT1 = tp1 instanceof Types.ErasedValueType;
            boolean isEVT2 = tp2 instanceof Types.ErasedValueType;
            if (isEVT1 && isEVT2) {
               return this.compareErasedGlb$1(((Types.ErasedValueType)tp1).valueClazz().tpe_$times(), ((Types.ErasedValueType)tp2).valueClazz().tpe_$times());
            } else if (isEVT1) {
               return -1;
            } else if (isEVT2) {
               return 1;
            } else {
               Symbols.Symbol sym1 = (Symbols.Symbol)tp1.baseClasses().head();
               Symbols.Symbol sym2 = (Symbols.Symbol)tp2.baseClasses().head();
               boolean isArray1 = tp1.typeArgs().nonEmpty() && sym1.isSubClass(this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().ArrayClass());
               boolean isArray2 = tp2.typeArgs().nonEmpty() && sym2.isSubClass(this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().ArrayClass());
               if (isArray1 && isArray2) {
                  return this.compareErasedGlb$1((Types.Type)tp1.typeArgs().head(), (Types.Type)tp2.typeArgs().head());
               } else if (isArray1) {
                  return -1;
               } else if (isArray2) {
                  return 1;
               } else {
                  boolean isPrimitive1 = sym1.isPrimitiveValueClass();
                  boolean isPrimitive2 = sym2.isPrimitiveValueClass();
                  if (isPrimitive1 && isPrimitive2) {
                     return compareClasses$1(sym1, sym2);
                  } else if (isPrimitive1) {
                     return -1;
                  } else if (isPrimitive2) {
                     return 1;
                  } else {
                     boolean isRealClass1 = sym1.isClass() && !sym1.isTrait();
                     boolean isRealClass2 = sym2.isClass() && !sym2.isTrait();
                     if (isRealClass1 && isRealClass2) {
                        return compareClasses$1(sym1, sym2);
                     } else if (isRealClass1) {
                        return -1;
                     } else {
                        return isRealClass2 ? 1 : compareClasses$1(sym1, sym2);
                     }
                  }
               }
            }
         }
      }

      // $FF: synthetic method
      public final int scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$anonfun$erasedGlb$1(final Types.Type t, final Types.Type u) {
         return this.compareErasedGlb$1(t, u);
      }

      // $FF: synthetic method
      private final DottyTypeProxy$1$ DottyTypeProxy$lzycompute$1(final LazyRef DottyTypeProxy$module$1) {
         synchronized(DottyTypeProxy$module$1){}

         DottyTypeProxy$1$ var2;
         try {
            class DottyTypeProxy$1$ {
               // $FF: synthetic field
               private final Scala3ErasureMap $outer;

               public Option unapply(final Types.Type tp) {
                  Types.Type superTpe = this.translucentSuperType(tp);
                  return (Option)(superTpe != this.$outer.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoType() ? new Some(superTpe) : scala.None..MODULE$);
               }

               public Types.Type translucentSuperType(final Types.Type tp) {
                  if (tp instanceof Types.TypeRef) {
                     Types.TypeRef var2 = (Types.TypeRef)tp;
                     return this.$outer.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().transparentDealias(var2.sym(), var2.pre(), var2.sym().owner());
                  } else if (tp instanceof Types.SingleType) {
                     return ((Types.SingleType)tp).underlying();
                  } else if (tp instanceof Types.ThisType) {
                     return ((Types.ThisType)tp).sym().typeOfThis();
                  } else if (tp instanceof Types.ConstantType) {
                     return ((Types.ConstantType)tp).value().tpe();
                  } else {
                     if (tp instanceof Types.RefinedType) {
                        Types.RefinedType var3 = (Types.RefinedType)tp;
                        if (var3.decls().nonEmpty()) {
                           return this.$outer.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().intersectionType(var3.parents());
                        }
                     }

                     if (tp instanceof Types.PolyType) {
                        return ((Types.PolyType)tp).resultType();
                     } else if (tp instanceof Types.ExistentialType) {
                        return ((Types.ExistentialType)tp).underlying();
                     } else if (tp instanceof Types.TypeBounds) {
                        return ((Types.TypeBounds)tp).hi();
                     } else if (tp instanceof Types.AnnotatedType) {
                        return ((Types.AnnotatedType)tp).underlying();
                     } else if (tp instanceof Types.SuperType) {
                        Types.SuperType var4 = (Types.SuperType)tp;
                        return var4.thistpe().baseType(var4.supertpe().typeSymbol());
                     } else {
                        return this.$outer.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoType();
                     }
                  }
               }

               public DottyTypeProxy$1$() {
                  if (Scala3ErasureMap.this == null) {
                     throw null;
                  } else {
                     this.$outer = Scala3ErasureMap.this;
                     super();
                  }
               }
            }

            var2 = DottyTypeProxy$module$1.initialized() ? (DottyTypeProxy$1$)DottyTypeProxy$module$1.value() : (DottyTypeProxy$1$)DottyTypeProxy$module$1.initialize(new DottyTypeProxy$1$());
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }

      private final DottyTypeProxy$1$ DottyTypeProxy$2(final LazyRef DottyTypeProxy$module$1) {
         return DottyTypeProxy$module$1.initialized() ? (DottyTypeProxy$1$)DottyTypeProxy$module$1.value() : this.DottyTypeProxy$lzycompute$1(DottyTypeProxy$module$1);
      }

      // $FF: synthetic method
      private static final DottyAndType$1$ DottyAndType$lzycompute$1(final LazyRef DottyAndType$module$1) {
         synchronized(DottyAndType$module$1){}

         DottyAndType$1$ var1;
         try {
            class DottyAndType$1$ {
               public boolean unapply(final Types.RefinedType tp) {
                  return tp.decls().isEmpty();
               }

               public DottyAndType$1$() {
               }
            }

            var1 = DottyAndType$module$1.initialized() ? (DottyAndType$1$)DottyAndType$module$1.value() : (DottyAndType$1$)DottyAndType$module$1.initialize(new DottyAndType$1$());
         } catch (Throwable var3) {
            throw var3;
         }

         return var1;
      }

      private final DottyAndType$1$ DottyAndType$2(final LazyRef DottyAndType$module$1) {
         return DottyAndType$module$1.initialized() ? (DottyAndType$1$)DottyAndType$module$1.value() : DottyAndType$lzycompute$1(DottyAndType$module$1);
      }

      private final Symbols.Symbol loop$1(final List tpsx, final LazyRef DottyTypeProxy$module$1, final LazyRef DottyAndType$module$1) {
         while(tps instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var4 = (scala.collection.immutable..colon.colon)tps;
            Types.Type p = (Types.Type)var4.head();
            List tps = var4.next$access$1();
            Symbols.Symbol ub = this.arrayUpperBound$1(p, DottyTypeProxy$module$1, DottyAndType$module$1);
            if (ub != this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoSymbol()) {
               return ub;
            }

            tps = tps;
         }

         return this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoSymbol();
      }

      private final Symbols.Symbol arrayUpperBound$1(final Types.Type tp, final LazyRef DottyTypeProxy$module$1, final LazyRef DottyAndType$module$1) {
         while(true) {
            Types.Type var4 = tp.dealias();
            if (var4 instanceof Types.TypeRef) {
               Symbols.Symbol sym = ((Types.TypeRef)var4).sym();
               if (sym.isClass()) {
                  if (sym != this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().AnyClass() && sym != this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().AnyValClass() && sym != this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().SingletonClass()) {
                     if (sym.isPrimitiveValueClass()) {
                        return sym;
                     }

                     return this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().definitions().ObjectClass();
                  }

                  return this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoSymbol();
               }
            }

            if (var4 != null) {
               Option var6 = this.DottyTypeProxy$2(DottyTypeProxy$module$1).unapply(var4);
               if (!var6.isEmpty()) {
                  tp = (Types.Type)var6.get();
                  continue;
               }
            }

            if (var4 instanceof Types.RefinedType) {
               Types.RefinedType var7 = (Types.RefinedType)var4;
               if (this.DottyAndType$2(DottyAndType$module$1).unapply(var7)) {
                  return this.loop$1(var7.parents(), DottyTypeProxy$module$1, DottyAndType$module$1);
               }
            }

            return this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoSymbol();
         }
      }

      private final boolean fitsInJVMArray$1(final Types.Type tp, final LazyRef DottyTypeProxy$module$1, final LazyRef DottyAndType$module$1) {
         return this.arrayUpperBound$1(tp, DottyTypeProxy$module$1, DottyAndType$module$1) != this.scala$reflect$internal$transform$Erasure$Scala3ErasureMap$$$outer().global().NoSymbol();
      }

      private static final boolean isOpaque$1(final Symbols.Symbol sym) {
         return sym.hasFlag(1152921504606846976L) && !sym.isClass() && sym.hasAttachment(scala.reflect.ClassTag..MODULE$.apply(StdAttachments.DottyOpaqueTypeAlias.class));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isGenericArrayElement$1(final Scala3ErasureMap $this, final Types.Type tp) {
         return $this.isGenericArrayElement(tp);
      }

      // $FF: synthetic method
      public static final Object $anonfun$isGenericArrayElement$1$adapted(final Scala3ErasureMap $this, final Types.Type tp) {
         return BoxesRunTime.boxToBoolean($anonfun$isGenericArrayElement$1($this, tp));
      }
   }

   public class JavaErasureMap extends ErasureMap implements Scala2JavaArrayErasure {
      public Types.Type eraseArray(final Types.Type arrayRef, final Types.Type pre, final List args) {
         return Erasure.Scala2JavaArrayErasure.super.eraseArray(arrayRef, pre, args);
      }

      public Types.Type mergeParents(final List parents) {
         return parents.isEmpty() ? this.scala$reflect$internal$transform$Erasure$JavaErasureMap$$$outer().global().definitions().ObjectTpe() : (Types.Type)parents.head();
      }

      public Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return this.eraseNormalClassRef(tref);
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$JavaErasureMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$Scala2JavaArrayErasure$$$outer() {
         return this.scala$reflect$internal$transform$Erasure$JavaErasureMap$$$outer();
      }
   }

   public class scalaErasure$ extends ScalaErasureMap {
   }

   public class scala3Erasure$ extends Scala3ErasureMap {
   }

   public interface SpecialScalaErasure {
      default Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return this.scala$reflect$internal$transform$Erasure$SpecialScalaErasure$$$outer().global().ErasedValueType().apply(tref.sym(), this.scala$reflect$internal$transform$Erasure$SpecialScalaErasure$$$outer().erasedValueClassArg(tref));
      }

      // $FF: synthetic method
      Erasure scala$reflect$internal$transform$Erasure$SpecialScalaErasure$$$outer();

      static void $init$(final SpecialScalaErasure $this) {
      }
   }

   public class specialScalaErasure$ extends ScalaErasureMap implements SpecialScalaErasure {
      public Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return Erasure.SpecialScalaErasure.super.eraseDerivedValueClassRef(tref);
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$specialScalaErasure$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$SpecialScalaErasure$$$outer() {
         return this.scala$reflect$internal$transform$Erasure$specialScalaErasure$$$outer();
      }
   }

   public class specialScala3Erasure$ extends Scala3ErasureMap implements SpecialScalaErasure {
      public Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return Erasure.SpecialScalaErasure.super.eraseDerivedValueClassRef(tref);
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$specialScala3Erasure$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$SpecialScalaErasure$$$outer() {
         return this.scala$reflect$internal$transform$Erasure$specialScala3Erasure$$$outer();
      }
   }

   public class javaErasure$ extends JavaErasureMap {
   }

   public class verifiedJavaErasure$ extends JavaErasureMap {
      public Types.Type apply(final Types.Type tp) {
         Types.Type res = this.scala$reflect$internal$transform$Erasure$verifiedJavaErasure$$$outer().javaErasure().apply(tp);
         Types.Type old = this.scala$reflect$internal$transform$Erasure$verifiedJavaErasure$$$outer().scalaErasure().apply(tp);
         if (!res.$eq$colon$eq(old)) {
            this.scala$reflect$internal$transform$Erasure$verifiedJavaErasure$$$outer().global().log(() -> (new StringBuilder(69)).append("Identified divergence between java/scala erasure:\n  scala: ").append(old).append("\n   java: ").append(res).toString());
         }

         return res;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$verifiedJavaErasure$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface BoxingErasure {
      // $FF: synthetic method
      Types.Type scala$reflect$internal$transform$Erasure$BoxingErasure$$super$applyInArray(final Types.Type tp);

      // $FF: synthetic method
      Types.Type scala$reflect$internal$transform$Erasure$BoxingErasure$$super$eraseNormalClassRef(final Types.TypeRef tref);

      boolean scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives();

      void scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(final boolean x$1);

      default Types.Type applyInArray(final Types.Type tp) {
         boolean saved = this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives();
         this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(false);

         Types.Type var10000;
         try {
            var10000 = this.scala$reflect$internal$transform$Erasure$BoxingErasure$$super$applyInArray(tp);
         } finally {
            this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(saved);
         }

         return var10000;
      }

      default Types.Type eraseNormalClassRef(final Types.TypeRef tref) {
         if (this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives() && this.scala$reflect$internal$transform$Erasure$BoxingErasure$$$outer().global().definitions().isPrimitiveValueClass(tref.sym())) {
            Symbols.Symbol var10000 = (Symbols.Symbol)this.scala$reflect$internal$transform$Erasure$BoxingErasure$$$outer().global().definitions().boxedClass().apply(tref.sym());
            if (var10000 == null) {
               throw null;
            } else {
               return var10000.tpe_$times();
            }
         } else {
            return this.scala$reflect$internal$transform$Erasure$BoxingErasure$$super$eraseNormalClassRef(tref);
         }
      }

      default Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return this.scala$reflect$internal$transform$Erasure$BoxingErasure$$super$eraseNormalClassRef(tref);
      }

      // $FF: synthetic method
      Erasure scala$reflect$internal$transform$Erasure$BoxingErasure$$$outer();

      static void $init$(final BoxingErasure $this) {
         $this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(true);
      }
   }

   public class boxingErasure$ extends ScalaErasureMap implements BoxingErasure {
      private boolean scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives;

      // $FF: synthetic method
      public Types.Type scala$reflect$internal$transform$Erasure$BoxingErasure$$super$applyInArray(final Types.Type tp) {
         return super.applyInArray(tp);
      }

      // $FF: synthetic method
      public Types.Type scala$reflect$internal$transform$Erasure$BoxingErasure$$super$eraseNormalClassRef(final Types.TypeRef tref) {
         return super.eraseNormalClassRef(tref);
      }

      public Types.Type applyInArray(final Types.Type tp) {
         return Erasure.BoxingErasure.super.applyInArray(tp);
      }

      public Types.Type eraseNormalClassRef(final Types.TypeRef tref) {
         return Erasure.BoxingErasure.super.eraseNormalClassRef(tref);
      }

      public Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return Erasure.BoxingErasure.super.eraseDerivedValueClassRef(tref);
      }

      public boolean scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives() {
         return this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives;
      }

      public void scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(final boolean x$1) {
         this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives = x$1;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$boxingErasure$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$BoxingErasure$$$outer() {
         return this.scala$reflect$internal$transform$Erasure$boxingErasure$$$outer();
      }

      public boxingErasure$() {
         this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(true);
      }
   }

   public class boxing3Erasure$ extends Scala3ErasureMap implements BoxingErasure {
      private boolean scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives;

      // $FF: synthetic method
      public Types.Type scala$reflect$internal$transform$Erasure$BoxingErasure$$super$applyInArray(final Types.Type tp) {
         return super.applyInArray(tp);
      }

      // $FF: synthetic method
      public Types.Type scala$reflect$internal$transform$Erasure$BoxingErasure$$super$eraseNormalClassRef(final Types.TypeRef tref) {
         return super.eraseNormalClassRef(tref);
      }

      public Types.Type applyInArray(final Types.Type tp) {
         return Erasure.BoxingErasure.super.applyInArray(tp);
      }

      public Types.Type eraseNormalClassRef(final Types.TypeRef tref) {
         return Erasure.BoxingErasure.super.eraseNormalClassRef(tref);
      }

      public Types.Type eraseDerivedValueClassRef(final Types.TypeRef tref) {
         return Erasure.BoxingErasure.super.eraseDerivedValueClassRef(tref);
      }

      public boolean scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives() {
         return this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives;
      }

      public void scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(final boolean x$1) {
         this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives = x$1;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$boxing3Erasure$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Erasure scala$reflect$internal$transform$Erasure$BoxingErasure$$$outer() {
         return this.scala$reflect$internal$transform$Erasure$boxing3Erasure$$$outer();
      }

      public boxing3Erasure$() {
         this.scala$reflect$internal$transform$Erasure$BoxingErasure$$boxPrimitives_$eq(true);
      }
   }
}
