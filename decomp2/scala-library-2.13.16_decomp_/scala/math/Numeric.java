package scala.math;

import java.io.Serializable;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.StringParsers$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try$;
import scala.util.control.NonFatal$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019ew\u0001CAk\u0003/D\t!!9\u0007\u0011\u0005\u0015\u0018q\u001bE\u0001\u0003ODqA!\u0001\u0002\t\u0003\u0011\u0019\u0001C\u0004\u0003\u0006\u0005!\tAa\u0002\u0007\u0013\r=\u0012\u0001%A\u0002\u0002\rE\u0002b\u0002B\u001d\t\u0011\u0005!1\b\u0005\b\u0007g!A1AB\u001b\u000f\u001d\u00199%\u0001E\u0001\u0007\u00132qa!\u0014\u0002\u0011\u0003\u0019y\u0005C\u0004\u0003\u0002!!\taa\u0015\u0007\u0013\rU\u0013\u0001%A\u0002\u0002\r]\u0003b\u0002B\u001d\u0015\u0011\u0005!1\b\u0005\b\u0005\u0007RA\u0011AB3\u0011\u001d\u0011yE\u0003C\u0001\u0007WBqAa\u0016\u000b\t\u0003\u0019\t\bC\u0004\u0004x)!\ta!\u001f\t\u000f\r}$\u0002\"\u0001\u0004\u0002\"9!q\f\u0006\u0005\u0002\r\u001d\u0005b\u0002B3\u0015\u0011\u000511\u0012\u0005\b\u0005cRA\u0011ABH\u0011\u001d\u0011)J\u0003C\u0001\u0007+CqAa'\u000b\t\u0003\u0019I\nC\u0004\u0003(*!\ta!(\t\u000f\tM&\u0002\"\u0001\u0004\"\u001e91QU\u0001\t\u0004\r\u001dfaBB+\u0003!\u00051\u0011\u0016\u0005\b\u0005\u0003IB\u0011AB^\u0011%\u0019i,GA\u0001\n\u0013\u0019yLB\u0005\u0004B\u0006\u0001\n1!\u0001\u0004D\"9!\u0011\b\u000f\u0005\u0002\tm\u0002b\u0002B\"9\u0011\u00051q\u0019\u0005\b\u0005\u001fbB\u0011ABg\u0011\u001d\u00119\u0006\bC\u0001\u0007'Dqaa\u001e\u001d\t\u0003\u0019I\u000eC\u0004\u0004\u0000q!\taa8\t\u000f\t}C\u0004\"\u0001\u0004f\"9!Q\r\u000f\u0005\u0002\r%\bb\u0002B99\u0011\u00051Q\u001e\u0005\b\u0005+cB\u0011ABz\u0011\u001d\u0011Y\n\bC\u0001\u0007oDqAa*\u001d\t\u0003\u0019Y\u0010C\u0004\u00034r!\taa@\t\u000f\t-G\u0004\"\u0011\u0005\u0004!9!Q\u001d\u000f\u0005B\u0011\u001dqa\u0002C\u0006\u0003!\rAQ\u0002\u0004\b\u0007\u0003\f\u0001\u0012\u0001C\b\u0011\u001d\u0011\t!\fC\u0001\t3A\u0011b!0.\u0003\u0003%Iaa0\u0007\u0013\u0011m\u0011\u0001%A\u0002\u0002\u0011u\u0001b\u0002B\u001da\u0011\u0005!1\b\u0005\b\u0005\u0007\u0002D\u0011\u0001C\u0014\u0011\u001d\u0011y\u0005\rC\u0001\t[AqAa\u00161\t\u0003!\u0019\u0004C\u0004\u0004xA\"\t\u0001\"\u000f\t\u000f\r}\u0004\u0007\"\u0001\u0005@!9!q\f\u0019\u0005\u0002\u0011\u0015\u0003b\u0002B3a\u0011\u0005A\u0011\n\u0005\b\u0005c\u0002D\u0011\u0001C'\u0011\u001d\u0011)\n\rC\u0001\t'BqAa'1\t\u0003!9\u0006C\u0004\u0003(B\"\t\u0001b\u0017\t\u000f\tM\u0006\u0007\"\u0001\u0005`!9!1\u001a\u0019\u0005B\u0011\r\u0004b\u0002Bsa\u0011\u0005CqM\u0004\b\tW\n\u00012\u0001C7\r\u001d!Y\"\u0001E\u0001\t_BqA!\u0001B\t\u0003!I\bC\u0005\u0004>\u0006\u000b\t\u0011\"\u0003\u0004@\u001aIA1P\u0001\u0011\u0002\u0007\u0005AQ\u0010\u0005\b\u0005s!E\u0011\u0001B\u001e\u0011\u001d\u0011\u0019\u0005\u0012C\u0001\t\u000fCqAa\u0014E\t\u0003!i\tC\u0004\u0003X\u0011#\t\u0001b%\t\u000f\r]D\t\"\u0001\u0005\u001a\"91q\u0010#\u0005\u0002\u0011}\u0005b\u0002B0\t\u0012\u0005AQ\u0015\u0005\b\u0005K\"E\u0011\u0001CU\u0011\u001d\u0011\t\b\u0012C\u0001\t[CqA!&E\t\u0003!\u0019\fC\u0004\u0003\u001c\u0012#\t\u0001b.\t\u000f\t\u001dF\t\"\u0001\u0005<\"9!1\u0017#\u0005\u0002\u0011}\u0006b\u0002Bf\t\u0012\u0005C1\u0019\u0005\b\u0005K$E\u0011\tCd\u000f\u001d!Y-\u0001E\u0002\t\u001b4q\u0001b\u001f\u0002\u0011\u0003!y\rC\u0004\u0003\u0002U#\t\u0001\"7\t\u0013\ruV+!A\u0005\n\r}f!\u0003Cn\u0003A\u0005\u0019\u0011\u0001Co\u0011\u001d\u0011I\u0004\u0017C\u0001\u0005wAqAa\u0011Y\t\u0003!9\u000fC\u0004\u0003Pa#\t\u0001\"<\t\u000f\t]\u0003\f\"\u0001\u0005t\"91q\u000f-\u0005\u0002\u0011e\bbBB@1\u0012\u0005Aq \u0005\b\u0005?BF\u0011AC\u0003\u0011\u001d\u0011)\u0007\u0017C\u0001\u000b\u0013AqA!\u001dY\t\u0003)i\u0001C\u0004\u0003\u0016b#\t!b\u0005\t\u000f\tm\u0005\f\"\u0001\u0006\u0018!9!q\u0015-\u0005\u0002\u0015m\u0001b\u0002BZ1\u0012\u0005Qq\u0004\u0005\b\u0005\u0017DF\u0011IC\u0012\u0011\u001d\u0011)\u000f\u0017C!\u000bO9q!b\u000b\u0002\u0011\u0007)iCB\u0004\u0005\\\u0006A\t!b\f\t\u000f\t\u0005\u0011\u000e\"\u0001\u0006:!I1QX5\u0002\u0002\u0013%1q\u0018\u0004\n\u000bw\t\u0001\u0013aA\u0001\u000b{AqA!\u000fm\t\u0003\u0011Y\u0004C\u0004\u0003D1$\t!\"\u0011\t\u000f\t=C\u000e\"\u0001\u0006H!9!q\u000b7\u0005\u0002\u00155\u0003bBB<Y\u0012\u0005Q1\u000b\u0005\b\u0007\u007fbG\u0011AC-\u0011\u001d\u0011y\u0006\u001cC\u0001\u000b?BqA!\u001am\t\u0003)\u0019\u0007C\u0004\u0003r1$\t!b\u001a\t\u000f\tUE\u000e\"\u0001\u0006n!9!1\u00147\u0005\u0002\u0015E\u0004b\u0002BTY\u0012\u0005QQ\u000f\u0005\b\u0005gcG\u0011AC=\u0011\u001d\u0011Y\r\u001cC!\u000b{BqA!:m\t\u0003*\tiB\u0004\u0006\u0006\u0006A\u0019!b\"\u0007\u000f\u0015m\u0012\u0001#\u0001\u0006\n\"9!\u0011A?\u0005\u0002\u0015M\u0005\"CB_{\u0006\u0005I\u0011BB`\r%))*\u0001I\u0001\u0004\u0003)9\n\u0003\u0005\u0003:\u0005\u0005A\u0011\u0001B\u001e\u0011!\u0011\u0019%!\u0001\u0005\u0002\u0015}\u0005\u0002\u0003B(\u0003\u0003!\t!\"*\t\u0011\t]\u0013\u0011\u0001C\u0001\u000bWC\u0001Ba\u0018\u0002\u0002\u0011\u0005Q\u0011\u0017\u0005\t\u0005K\n\t\u0001\"\u0001\u00066\"A!\u0011OA\u0001\t\u0003)I\f\u0003\u0005\u0003\u0016\u0006\u0005A\u0011AC`\u0011!\u0011Y*!\u0001\u0005\u0002\u0015\r\u0007\u0002\u0003BT\u0003\u0003!\t!b2\t\u0011\tM\u0016\u0011\u0001C\u0001\u000b\u0017D\u0001\"b4\u0002\u0002\u0011\u0005Q\u0011\u001b\u0005\t\u0005\u000b\f\t\u0001\"\u0011\u0006X\"A!Q]A\u0001\t\u0003*YnB\u0004\u0006`\u0006A\u0019!\"9\u0007\u000f\u0015U\u0015\u0001#\u0001\u0006d\"A!\u0011AA\u0011\t\u0003))\u0010\u0003\u0006\u0004>\u0006\u0005\u0012\u0011!C\u0005\u0007\u007f3\u0011\"b>\u0002!\u0003\r\t!\"?\t\u0011\te\u0012q\u0005C\u0001\u0005wA\u0001Ba\u0011\u0002(\u0011\u0005QQ \u0005\t\u0005\u001f\n9\u0003\"\u0001\u0007\u0004!A!qKA\u0014\t\u00031I\u0001\u0003\u0005\u0003`\u0005\u001dB\u0011\u0001D\b\u0011!\u0011)'a\n\u0005\u0002\u0019M\u0001\u0002\u0003B9\u0003O!\tAb\u0006\t\u0011\tU\u0015q\u0005C\u0001\r;A\u0001Ba'\u0002(\u0011\u0005a\u0011\u0005\u0005\t\u0005O\u000b9\u0003\"\u0001\u0007&!A!1WA\u0014\t\u00031I\u0003\u0003\u0005\u0006P\u0006\u001dB\u0011\u0001D\u0017\u0011!\u0011)-a\n\u0005B\u0019M\u0002\u0002\u0003Bs\u0003O!\tEb\u000e\b\u000f\u0019m\u0012\u0001c\u0001\u0007>\u00199Qq_\u0001\t\u0002\u0019}\u0002\u0002\u0003B\u0001\u0003\u000f\"\tAb\u0014\t\u0015\ru\u0016qIA\u0001\n\u0013\u0019yLB\u0005\u0007R\u0005\u0001\n1!\u0001\u0007T!A!\u0011HA'\t\u0003\u0011Y\u0004\u0003\u0005\u0003D\u00055C\u0011\u0001D/\u0011!\u0011y%!\u0014\u0005\u0002\u0019\r\u0004\u0002\u0003B,\u0003\u001b\"\tA\"\u001b\t\u0011\t}\u0013Q\nC\u0001\r_B\u0001B!\u001a\u0002N\u0011\u0005a1\u000f\u0005\t\u0005c\ni\u0005\"\u0001\u0007x!A!QSA'\t\u00031i\b\u0003\u0005\u0003\u001c\u00065C\u0011\u0001DA\u0011!\u00119+!\u0014\u0005\u0002\u0019\u0015\u0005\u0002\u0003BZ\u0003\u001b\"\tA\"#\b\u000f\u00195\u0015\u0001#\u0003\u0007\u0010\u001a9a\u0011K\u0001\t\n\u0019E\u0005\u0002\u0003B\u0001\u0003O\"\tAb%\t\u0015\u0019U\u0015q\rb\u0001\n\u001319\nC\u0005\u0007\u001a\u0006\u001d\u0004\u0015!\u0003\u0007X!Qa1TA4\u0005\u0004%IAb&\t\u0013\u0019u\u0015q\rQ\u0001\n\u0019]\u0003BCB_\u0003O\n\t\u0011\"\u0003\u0004@\u001aIaqT\u0001\u0011\u0002\u0007\u0005a\u0011\u0015\u0005\t\u0005s\t)\b\"\u0001\u0003<!AQqZA;\t\u000319KB\u0005\u0007.\u0006\u0001\n1!\u0001\u00070\"A!\u0011HA>\t\u0003\u0011Y\u0004\u0003\u0005\u0004x\u0005mD\u0011\u0001DZ\u0011!\u0019y(a\u001f\u0005\u0002\u0019eva\u0002D`\u0003!\ra\u0011\u0019\u0004\b\r?\u000b\u0001\u0012\u0001Db\u0011!\u0011\t!!\"\u0005\u0002\u00195\u0007BCB_\u0003\u000b\u000b\t\u0011\"\u0003\u0004@\u001e9aqZ\u0001\t\u0002\u0019Ega\u0002DW\u0003!\u0005a1\u001b\u0005\t\u0005\u0003\ti\t\"\u0001\u0007X\"Q1QXAG\u0003\u0003%Iaa0\t\u0013\ru\u0016!!A\u0005\n\r}fACAs\u0003/\u0004\n1!\u0001\u0003\u000e!A!\u0011HAK\t\u0003\u0011Y\u0004\u0003\u0005\u0003D\u0005Ue\u0011\u0001B#\u0011!\u0011y%!&\u0007\u0002\tE\u0003\u0002\u0003B,\u0003+3\tA!\u0017\t\u0011\t}\u0013Q\u0013D\u0001\u0005CB\u0001B!\u001a\u0002\u0016\u001a\u0005!q\r\u0005\t\u0005c\n)J\"\u0001\u0003t!A!QSAK\r\u0003\u00119\n\u0003\u0005\u0003\u001c\u0006Ue\u0011\u0001BO\u0011!\u00119+!&\u0007\u0002\t%\u0006\u0002\u0003BZ\u0003+3\tA!.\t\u0011\t}\u0016Q\u0013C\u0001\u0005\u0003D\u0001Ba1\u0002\u0016\u0012\u0005!\u0011\u0019\u0005\t\u0005\u000b\f)\n\"\u0001\u0003H\"A!1ZAK\t\u0003\u0011i\r\u0003\u0005\u0003f\u0006UE\u0011\u0001Bt\r\u001d\u0011Y/!&\u0001\u0005[D1Ba<\u00028\n\u0005\t\u0015!\u0003\u0003$!A!\u0011AA\\\t\u0003\u0011\t\u0010\u0003\u0005\u0003z\u0006]F\u0011\u0001B~\u0011!\u0019\t!a.\u0005\u0002\r\r\u0001\u0002CB\u0004\u0003o#\ta!\u0003\t\u0011\r5\u0011q\u0017C\u0001\u0005\u0003D\u0001B!2\u00028\u0012\u0005!\u0011\u0019\u0005\t\u0005\u0017\f9\f\"\u0001\u0004\u0010!A!Q]A\\\t\u0003\u0011\t\r\u0003\u0005\u0003\u0016\u0006]F\u0011AB\b\u0011!\u0011Y*a.\u0005\u0002\rM\u0001\u0002\u0003BT\u0003o#\ta!\u0006\t\u0011\tM\u0016q\u0017C\u0001\u0007/A\u0001b!\u0007\u0002\u0016\u0012\r11D\u0001\b\u001dVlWM]5d\u0015\u0011\tI.a7\u0002\t5\fG\u000f\u001b\u0006\u0003\u0003;\fQa]2bY\u0006\u001c\u0001\u0001E\u0002\u0002d\u0006i!!a6\u0003\u000f9+X.\u001a:jGN)\u0011!!;\u0002rB!\u00111^Aw\u001b\t\tY.\u0003\u0003\u0002p\u0006m'AB!osJ+g\r\u0005\u0003\u0002t\u0006uXBAA{\u0015\u0011\t90!?\u0002\u0005%|'BAA~\u0003\u0011Q\u0017M^1\n\t\u0005}\u0018Q\u001f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005\u0005\u0018!B1qa2LX\u0003\u0002B\u0005\u0007C!BAa\u0003\u0004$A1\u00111]AK\u0007?)BAa\u0004\u0003(M1\u0011Q\u0013B\t\u0005;\u0001BAa\u0005\u0003\u001a5\u0011!Q\u0003\u0006\u0005\u0005/\tI0\u0001\u0003mC:<\u0017\u0002\u0002B\u000e\u0005+\u0011aa\u00142kK\u000e$\bCBAr\u0005?\u0011\u0019#\u0003\u0003\u0003\"\u0005]'\u0001C(sI\u0016\u0014\u0018N\\4\u0011\t\t\u0015\"q\u0005\u0007\u0001\t!\u0011I#!&C\u0002\t-\"!\u0001+\u0012\t\t5\"1\u0007\t\u0005\u0003W\u0014y#\u0003\u0003\u00032\u0005m'a\u0002(pi\"Lgn\u001a\t\u0005\u0003W\u0014)$\u0003\u0003\u00038\u0005m'aA!os\u00061A%\u001b8ji\u0012\"\"A!\u0010\u0011\t\u0005-(qH\u0005\u0005\u0005\u0003\nYN\u0001\u0003V]&$\u0018\u0001\u00029mkN$bAa\t\u0003H\t-\u0003\u0002\u0003B%\u00033\u0003\rAa\t\u0002\u0003aD\u0001B!\u0014\u0002\u001a\u0002\u0007!1E\u0001\u0002s\u0006)Q.\u001b8vgR1!1\u0005B*\u0005+B\u0001B!\u0013\u0002\u001c\u0002\u0007!1\u0005\u0005\t\u0005\u001b\nY\n1\u0001\u0003$\u0005)A/[7fgR1!1\u0005B.\u0005;B\u0001B!\u0013\u0002\u001e\u0002\u0007!1\u0005\u0005\t\u0005\u001b\ni\n1\u0001\u0003$\u00051a.Z4bi\u0016$BAa\t\u0003d!A!\u0011JAP\u0001\u0004\u0011\u0019#A\u0004ge>l\u0017J\u001c;\u0015\t\t\r\"\u0011\u000e\u0005\t\u0005\u0013\n\t\u000b1\u0001\u0003lA!\u00111\u001eB7\u0013\u0011\u0011y'a7\u0003\u0007%sG/A\u0006qCJ\u001cXm\u0015;sS:<G\u0003\u0002B;\u0005w\u0002b!a;\u0003x\t\r\u0012\u0002\u0002B=\u00037\u0014aa\u00149uS>t\u0007\u0002\u0003B?\u0003G\u0003\rAa \u0002\u0007M$(\u000f\u0005\u0003\u0003\u0002\n=e\u0002\u0002BB\u0005\u0017\u0003BA!\"\u0002\\6\u0011!q\u0011\u0006\u0005\u0005\u0013\u000by.\u0001\u0004=e>|GOP\u0005\u0005\u0005\u001b\u000bY.\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005#\u0013\u0019J\u0001\u0004TiJLgn\u001a\u0006\u0005\u0005\u001b\u000bY.A\u0003u_&sG\u000f\u0006\u0003\u0003l\te\u0005\u0002\u0003B%\u0003K\u0003\rAa\t\u0002\rQ|Gj\u001c8h)\u0011\u0011yJ!*\u0011\t\u0005-(\u0011U\u0005\u0005\u0005G\u000bYN\u0001\u0003M_:<\u0007\u0002\u0003B%\u0003O\u0003\rAa\t\u0002\u000fQ|g\t\\8biR!!1\u0016BY!\u0011\tYO!,\n\t\t=\u00161\u001c\u0002\u0006\r2|\u0017\r\u001e\u0005\t\u0005\u0013\nI\u000b1\u0001\u0003$\u0005AAo\u001c#pk\ndW\r\u0006\u0003\u00038\nu\u0006\u0003BAv\u0005sKAAa/\u0002\\\n1Ai\\;cY\u0016D\u0001B!\u0013\u0002,\u0002\u0007!1E\u0001\u0005u\u0016\u0014x.\u0006\u0002\u0003$\u0005\u0019qN\\3\u0002\u0007\u0005\u00147\u000f\u0006\u0003\u0003$\t%\u0007\u0002\u0003B%\u0003c\u0003\rAa\t\u0002\rMLwM\\;n)\u0011\u0011YGa4\t\u0011\t%\u00131\u0017a\u0001\u0005GAC\"a-\u0003T\ne'1\u001cBp\u0005C\u0004B!a;\u0003V&!!q[An\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u0011i.A\rvg\u0016\u0004\u0003m]5h]\u0002\u0004S.\u001a;i_\u0012\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017E\u0001Br\u0003\u0019\u0011d&M\u001a/a\u0005!1/[4o)\u0011\u0011\u0019C!;\t\u0011\t%\u0013Q\u0017a\u0001\u0005G\u0011!BT;nKJL7m\u00149t'\u0011\t9,!;\u0002\u00071D7\u000f\u0006\u0003\u0003t\n]\b\u0003\u0002B{\u0003ok!!!&\t\u0011\t=\u00181\u0018a\u0001\u0005G\tQ\u0001\n9mkN$BAa\t\u0003~\"A!q`A_\u0001\u0004\u0011\u0019#A\u0002sQN\fa\u0001J7j]V\u001cH\u0003\u0002B\u0012\u0007\u000bA\u0001Ba@\u0002@\u0002\u0007!1E\u0001\u0007IQLW.Z:\u0015\t\t\r21\u0002\u0005\t\u0005\u007f\f\t\r1\u0001\u0003$\u0005aQO\\1ss~#S.\u001b8vgV\u0011!1\u000e\u0015\r\u0003\u000f\u0014\u0019N!7\u0003\\\n}'\u0011]\u000b\u0003\u0005?+\"Aa+\u0016\u0005\t]\u0016\u0001D7l\u001dVlWM]5d\u001fB\u001cH\u0003\u0002Bz\u0007;A\u0001Ba<\u0002T\u0002\u0007!1\u0005\t\u0005\u0005K\u0019\t\u0003B\u0004\u0003*\r\u0011\rAa\u000b\t\u000f\r\u00152\u0001q\u0001\u0003\f\u0005\u0019a.^7)\u0007\r\u0019I\u0003\u0005\u0003\u0002l\u000e-\u0012\u0002BB\u0017\u00037\u0014a!\u001b8mS:,'AD#yiJ\f\u0017*\u001c9mS\u000eLGo]\n\u0004\t\u0005%\u0018aD5oM&Dh*^7fe&\u001cw\n]:\u0016\t\r]2\u0011\t\u000b\u0005\u0007s\u0019)\u0005\u0006\u0003\u0004<\r\r\u0003\u0003BB\u001f\u0003o\u0003b!a9\u0002\u0016\u000e}\u0002\u0003\u0002B\u0013\u0007\u0003\"qA!\u000b\u0007\u0005\u0004\u0011Y\u0003C\u0004\u0004&\u0019\u0001\u001da!\u0010\t\u000f\t%c\u00011\u0001\u0004@\u0005I\u0011*\u001c9mS\u000eLGo\u001d\t\u0004\u0007\u0017BQ\"A\u0001\u0003\u0013%k\u0007\u000f\\5dSR\u001c8#\u0002\u0005\u0002j\u000eE\u0003cAB&\tQ\u00111\u0011\n\u0002\u0011\u0005&<\u0017J\u001c;Jg&sG/Z4sC2\u001cRA\u0003B\t\u00073\u0002b!a9\u0004\\\r}\u0013\u0002BB/\u0003/\u0014\u0001\"\u00138uK\u001e\u0014\u0018\r\u001c\t\u0005\u0003G\u001c\t'\u0003\u0003\u0004d\u0005]'A\u0002\"jO&sG\u000f\u0006\u0004\u0004`\r\u001d4\u0011\u000e\u0005\b\u0005\u0013b\u0001\u0019AB0\u0011\u001d\u0011i\u0005\u0004a\u0001\u0007?\"baa\u0018\u0004n\r=\u0004b\u0002B%\u001b\u0001\u00071q\f\u0005\b\u0005\u001bj\u0001\u0019AB0)\u0019\u0019yfa\u001d\u0004v!9!\u0011\n\bA\u0002\r}\u0003b\u0002B'\u001d\u0001\u00071qL\u0001\u0005cV|G\u000f\u0006\u0004\u0004`\rm4Q\u0010\u0005\b\u0005\u0013z\u0001\u0019AB0\u0011\u001d\u0011ie\u0004a\u0001\u0007?\n1A]3n)\u0019\u0019yfa!\u0004\u0006\"9!\u0011\n\tA\u0002\r}\u0003b\u0002B'!\u0001\u00071q\f\u000b\u0005\u0007?\u001aI\tC\u0004\u0003JE\u0001\raa\u0018\u0015\t\r}3Q\u0012\u0005\b\u0005\u0013\u0012\u0002\u0019\u0001B6)\u0011\u0019\tja%\u0011\r\u0005-(qOB0\u0011\u001d\u0011ih\u0005a\u0001\u0005\u007f\"BAa\u001b\u0004\u0018\"9!\u0011\n\u000bA\u0002\r}C\u0003\u0002BP\u00077CqA!\u0013\u0016\u0001\u0004\u0019y\u0006\u0006\u0003\u0003,\u000e}\u0005b\u0002B%-\u0001\u00071q\f\u000b\u0005\u0005o\u001b\u0019\u000bC\u0004\u0003J]\u0001\raa\u0018\u0002!\tKw-\u00138u\u0013NLe\u000e^3he\u0006d\u0007cAB&3M9\u0011D!\u0005\u0004,\u000e5\u0006cAB&\u0015A!1qVB[\u001d\u0011\t\u0019o!-\n\t\rM\u0016q[\u0001\t\u001fJ$WM]5oO&!1qWB]\u00059\u0011\u0015nZ%oi>\u0013H-\u001a:j]\u001eTAaa-\u0002XR\u00111qU\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005#\u0011Q\"\u00138u\u0013NLe\u000e^3he\u0006d7#\u0002\u000f\u0003\u0012\r\u0015\u0007CBAr\u00077\u0012Y\u0007\u0006\u0004\u0003l\r%71\u001a\u0005\b\u0005\u0013r\u0002\u0019\u0001B6\u0011\u001d\u0011iE\ba\u0001\u0005W\"bAa\u001b\u0004P\u000eE\u0007b\u0002B%?\u0001\u0007!1\u000e\u0005\b\u0005\u001bz\u0002\u0019\u0001B6)\u0019\u0011Yg!6\u0004X\"9!\u0011\n\u0011A\u0002\t-\u0004b\u0002B'A\u0001\u0007!1\u000e\u000b\u0007\u0005W\u001aYn!8\t\u000f\t%\u0013\u00051\u0001\u0003l!9!QJ\u0011A\u0002\t-DC\u0002B6\u0007C\u001c\u0019\u000fC\u0004\u0003J\t\u0002\rAa\u001b\t\u000f\t5#\u00051\u0001\u0003lQ!!1NBt\u0011\u001d\u0011Ie\ta\u0001\u0005W\"BAa\u001b\u0004l\"9!\u0011\n\u0013A\u0002\t-D\u0003BBx\u0007c\u0004b!a;\u0003x\t-\u0004b\u0002B?K\u0001\u0007!q\u0010\u000b\u0005\u0005W\u001a)\u0010C\u0004\u0003J\u0019\u0002\rAa\u001b\u0015\t\t}5\u0011 \u0005\b\u0005\u0013:\u0003\u0019\u0001B6)\u0011\u0011Yk!@\t\u000f\t%\u0003\u00061\u0001\u0003lQ!!q\u0017C\u0001\u0011\u001d\u0011I%\u000ba\u0001\u0005W\"BAa\u001b\u0005\u0006!9!\u0011\n\u0016A\u0002\t-D\u0003\u0002B6\t\u0013AqA!\u0013,\u0001\u0004\u0011Y'A\u0007J]RL5/\u00138uK\u001e\u0014\u0018\r\u001c\t\u0004\u0007\u0017j3cB\u0017\u0003\u0012\u0011EA1\u0003\t\u0004\u0007\u0017b\u0002\u0003BBX\t+IA\u0001b\u0006\u0004:\nY\u0011J\u001c;Pe\u0012,'/\u001b8h)\t!iAA\bTQ>\u0014H/S:J]R,wM]1m'\u0015\u0001$\u0011\u0003C\u0010!\u0019\t\u0019oa\u0017\u0005\"A!\u00111\u001eC\u0012\u0013\u0011!)#a7\u0003\u000bMCwN\u001d;\u0015\r\u0011\u0005B\u0011\u0006C\u0016\u0011\u001d\u0011IE\ra\u0001\tCAqA!\u00143\u0001\u0004!\t\u0003\u0006\u0004\u0005\"\u0011=B\u0011\u0007\u0005\b\u0005\u0013\u001a\u0004\u0019\u0001C\u0011\u0011\u001d\u0011ie\ra\u0001\tC!b\u0001\"\t\u00056\u0011]\u0002b\u0002B%i\u0001\u0007A\u0011\u0005\u0005\b\u0005\u001b\"\u0004\u0019\u0001C\u0011)\u0019!\t\u0003b\u000f\u0005>!9!\u0011J\u001bA\u0002\u0011\u0005\u0002b\u0002B'k\u0001\u0007A\u0011\u0005\u000b\u0007\tC!\t\u0005b\u0011\t\u000f\t%c\u00071\u0001\u0005\"!9!Q\n\u001cA\u0002\u0011\u0005B\u0003\u0002C\u0011\t\u000fBqA!\u00138\u0001\u0004!\t\u0003\u0006\u0003\u0005\"\u0011-\u0003b\u0002B%q\u0001\u0007!1\u000e\u000b\u0005\t\u001f\"\t\u0006\u0005\u0004\u0002l\n]D\u0011\u0005\u0005\b\u0005{J\u0004\u0019\u0001B@)\u0011\u0011Y\u0007\"\u0016\t\u000f\t%#\b1\u0001\u0005\"Q!!q\u0014C-\u0011\u001d\u0011Ie\u000fa\u0001\tC!BAa+\u0005^!9!\u0011\n\u001fA\u0002\u0011\u0005B\u0003\u0002B\\\tCBqA!\u0013>\u0001\u0004!\t\u0003\u0006\u0003\u0003l\u0011\u0015\u0004b\u0002B%}\u0001\u0007A\u0011\u0005\u000b\u0005\tC!I\u0007C\u0004\u0003J}\u0002\r\u0001\"\t\u0002\u001fMCwN\u001d;Jg&sG/Z4sC2\u00042aa\u0013B'\u001d\t%\u0011\u0003C9\tg\u00022aa\u00131!\u0011\u0019y\u000b\"\u001e\n\t\u0011]4\u0011\u0018\u0002\u000e'\"|'\u000f^(sI\u0016\u0014\u0018N\\4\u0015\u0005\u00115$A\u0004\"zi\u0016L5/\u00138uK\u001e\u0014\u0018\r\\\n\u0006\t\nEAq\u0010\t\u0007\u0003G\u001cY\u0006\"!\u0011\t\u0005-H1Q\u0005\u0005\t\u000b\u000bYN\u0001\u0003CsR,GC\u0002CA\t\u0013#Y\tC\u0004\u0003J\u0019\u0003\r\u0001\"!\t\u000f\t5c\t1\u0001\u0005\u0002R1A\u0011\u0011CH\t#CqA!\u0013H\u0001\u0004!\t\tC\u0004\u0003N\u001d\u0003\r\u0001\"!\u0015\r\u0011\u0005EQ\u0013CL\u0011\u001d\u0011I\u0005\u0013a\u0001\t\u0003CqA!\u0014I\u0001\u0004!\t\t\u0006\u0004\u0005\u0002\u0012mEQ\u0014\u0005\b\u0005\u0013J\u0005\u0019\u0001CA\u0011\u001d\u0011i%\u0013a\u0001\t\u0003#b\u0001\"!\u0005\"\u0012\r\u0006b\u0002B%\u0015\u0002\u0007A\u0011\u0011\u0005\b\u0005\u001bR\u0005\u0019\u0001CA)\u0011!\t\tb*\t\u000f\t%3\n1\u0001\u0005\u0002R!A\u0011\u0011CV\u0011\u001d\u0011I\u0005\u0014a\u0001\u0005W\"B\u0001b,\u00052B1\u00111\u001eB<\t\u0003CqA! N\u0001\u0004\u0011y\b\u0006\u0003\u0003l\u0011U\u0006b\u0002B%\u001d\u0002\u0007A\u0011\u0011\u000b\u0005\u0005?#I\fC\u0004\u0003J=\u0003\r\u0001\"!\u0015\t\t-FQ\u0018\u0005\b\u0005\u0013\u0002\u0006\u0019\u0001CA)\u0011\u00119\f\"1\t\u000f\t%\u0013\u000b1\u0001\u0005\u0002R!!1\u000eCc\u0011\u001d\u0011IE\u0015a\u0001\t\u0003#B\u0001\"!\u0005J\"9!\u0011J*A\u0002\u0011\u0005\u0015A\u0004\"zi\u0016L5/\u00138uK\u001e\u0014\u0018\r\u001c\t\u0004\u0007\u0017*6cB+\u0003\u0012\u0011EG1\u001b\t\u0004\u0007\u0017\"\u0005\u0003BBX\t+LA\u0001b6\u0004:\na!)\u001f;f\u001fJ$WM]5oOR\u0011AQ\u001a\u0002\u000f\u0007\"\f'/S:J]R,wM]1m'\u0015A&\u0011\u0003Cp!\u0019\t\u0019oa\u0017\u0005bB!\u00111\u001eCr\u0013\u0011!)/a7\u0003\t\rC\u0017M\u001d\u000b\u0007\tC$I\u000fb;\t\u000f\t%#\f1\u0001\u0005b\"9!Q\n.A\u0002\u0011\u0005HC\u0002Cq\t_$\t\u0010C\u0004\u0003Jm\u0003\r\u0001\"9\t\u000f\t53\f1\u0001\u0005bR1A\u0011\u001dC{\toDqA!\u0013]\u0001\u0004!\t\u000fC\u0004\u0003Nq\u0003\r\u0001\"9\u0015\r\u0011\u0005H1 C\u007f\u0011\u001d\u0011I%\u0018a\u0001\tCDqA!\u0014^\u0001\u0004!\t\u000f\u0006\u0004\u0005b\u0016\u0005Q1\u0001\u0005\b\u0005\u0013r\u0006\u0019\u0001Cq\u0011\u001d\u0011iE\u0018a\u0001\tC$B\u0001\"9\u0006\b!9!\u0011J0A\u0002\u0011\u0005H\u0003\u0002Cq\u000b\u0017AqA!\u0013a\u0001\u0004\u0011Y\u0007\u0006\u0003\u0006\u0010\u0015E\u0001CBAv\u0005o\"\t\u000fC\u0004\u0003~\u0005\u0004\rAa \u0015\t\t-TQ\u0003\u0005\b\u0005\u0013\u0012\u0007\u0019\u0001Cq)\u0011\u0011y*\"\u0007\t\u000f\t%3\r1\u0001\u0005bR!!1VC\u000f\u0011\u001d\u0011I\u0005\u001aa\u0001\tC$BAa.\u0006\"!9!\u0011J3A\u0002\u0011\u0005H\u0003\u0002B6\u000bKAqA!\u0013g\u0001\u0004!\t\u000f\u0006\u0003\u0005b\u0016%\u0002b\u0002B%O\u0002\u0007A\u0011]\u0001\u000f\u0007\"\f'/S:J]R,wM]1m!\r\u0019Y%[\n\bS\nEQ\u0011GC\u001a!\r\u0019Y\u0005\u0017\t\u0005\u0007_+)$\u0003\u0003\u00068\re&\u0001D\"iCJ|%\u000fZ3sS:<GCAC\u0017\u00059auN\\4Jg&sG/Z4sC2\u001cR\u0001\u001cB\t\u000b\u007f\u0001b!a9\u0004\\\t}EC\u0002BP\u000b\u0007*)\u0005C\u0004\u0003J9\u0004\rAa(\t\u000f\t5c\u000e1\u0001\u0003 R1!qTC%\u000b\u0017BqA!\u0013p\u0001\u0004\u0011y\nC\u0004\u0003N=\u0004\rAa(\u0015\r\t}UqJC)\u0011\u001d\u0011I\u0005\u001da\u0001\u0005?CqA!\u0014q\u0001\u0004\u0011y\n\u0006\u0004\u0003 \u0016USq\u000b\u0005\b\u0005\u0013\n\b\u0019\u0001BP\u0011\u001d\u0011i%\u001da\u0001\u0005?#bAa(\u0006\\\u0015u\u0003b\u0002B%e\u0002\u0007!q\u0014\u0005\b\u0005\u001b\u0012\b\u0019\u0001BP)\u0011\u0011y*\"\u0019\t\u000f\t%3\u000f1\u0001\u0003 R!!qTC3\u0011\u001d\u0011I\u0005\u001ea\u0001\u0005W\"B!\"\u001b\u0006lA1\u00111\u001eB<\u0005?CqA! v\u0001\u0004\u0011y\b\u0006\u0003\u0003l\u0015=\u0004b\u0002B%m\u0002\u0007!q\u0014\u000b\u0005\u0005?+\u0019\bC\u0004\u0003J]\u0004\rAa(\u0015\t\t-Vq\u000f\u0005\b\u0005\u0013B\b\u0019\u0001BP)\u0011\u00119,b\u001f\t\u000f\t%\u0013\u00101\u0001\u0003 R!!1NC@\u0011\u001d\u0011IE\u001fa\u0001\u0005?#BAa(\u0006\u0004\"9!\u0011J>A\u0002\t}\u0015A\u0004'p]\u001eL5/\u00138uK\u001e\u0014\u0018\r\u001c\t\u0004\u0007\u0017j8cB?\u0003\u0012\u0015-UQ\u0012\t\u0004\u0007\u0017b\u0007\u0003BBX\u000b\u001fKA!\"%\u0004:\naAj\u001c8h\u001fJ$WM]5oOR\u0011Qq\u0011\u0002\u0012\r2|\u0017\r^%t\rJ\f7\r^5p]\u0006d7CBA\u0001\u0005#)I\n\u0005\u0004\u0002d\u0016m%1V\u0005\u0005\u000b;\u000b9N\u0001\u0006Ge\u0006\u001cG/[8oC2$bAa+\u0006\"\u0016\r\u0006\u0002\u0003B%\u0003\u000b\u0001\rAa+\t\u0011\t5\u0013Q\u0001a\u0001\u0005W#bAa+\u0006(\u0016%\u0006\u0002\u0003B%\u0003\u000f\u0001\rAa+\t\u0011\t5\u0013q\u0001a\u0001\u0005W#bAa+\u0006.\u0016=\u0006\u0002\u0003B%\u0003\u0013\u0001\rAa+\t\u0011\t5\u0013\u0011\u0002a\u0001\u0005W#BAa+\u00064\"A!\u0011JA\u0006\u0001\u0004\u0011Y\u000b\u0006\u0003\u0003,\u0016]\u0006\u0002\u0003B%\u0003\u001b\u0001\rAa\u001b\u0015\t\u0015mVQ\u0018\t\u0007\u0003W\u00149Ha+\t\u0011\tu\u0014q\u0002a\u0001\u0005\u007f\"BAa\u001b\u0006B\"A!\u0011JA\t\u0001\u0004\u0011Y\u000b\u0006\u0003\u0003 \u0016\u0015\u0007\u0002\u0003B%\u0003'\u0001\rAa+\u0015\t\t-V\u0011\u001a\u0005\t\u0005\u0013\n)\u00021\u0001\u0003,R!!qWCg\u0011!\u0011I%a\u0006A\u0002\t-\u0016a\u00013jmR1!1VCj\u000b+D\u0001B!\u0013\u0002\u001a\u0001\u0007!1\u0016\u0005\t\u0005\u001b\nI\u00021\u0001\u0003,R!!1VCm\u0011!\u0011I%a\u0007A\u0002\t-F\u0003\u0002BV\u000b;D\u0001B!\u0013\u0002\u001e\u0001\u0007!1V\u0001\u0012\r2|\u0017\r^%t\rJ\f7\r^5p]\u0006d\u0007\u0003BB&\u0003C\u0019\u0002\"!\t\u0003\u0012\u0015\u0015Xq\u001d\t\u0005\u0007\u0017\n\t\u0001\u0005\u0003\u0006j\u0016=h\u0002BBX\u000bWLA!\"<\u0004:\u0006)a\t\\8bi&!Q\u0011_Cz\u00051IU-Z3Pe\u0012,'/\u001b8h\u0015\u0011)io!/\u0015\u0005\u0015\u0005(A\u0005#pk\ndW-S:Ge\u0006\u001cG/[8oC2\u001cb!a\n\u0003\u0012\u0015m\bCBAr\u000b7\u00139\f\u0006\u0004\u00038\u0016}h\u0011\u0001\u0005\t\u0005\u0013\nY\u00031\u0001\u00038\"A!QJA\u0016\u0001\u0004\u00119\f\u0006\u0004\u00038\u001a\u0015aq\u0001\u0005\t\u0005\u0013\ni\u00031\u0001\u00038\"A!QJA\u0017\u0001\u0004\u00119\f\u0006\u0004\u00038\u001a-aQ\u0002\u0005\t\u0005\u0013\ny\u00031\u0001\u00038\"A!QJA\u0018\u0001\u0004\u00119\f\u0006\u0003\u00038\u001aE\u0001\u0002\u0003B%\u0003c\u0001\rAa.\u0015\t\t]fQ\u0003\u0005\t\u0005\u0013\n\u0019\u00041\u0001\u0003lQ!a\u0011\u0004D\u000e!\u0019\tYOa\u001e\u00038\"A!QPA\u001b\u0001\u0004\u0011y\b\u0006\u0003\u0003l\u0019}\u0001\u0002\u0003B%\u0003o\u0001\rAa.\u0015\t\t}e1\u0005\u0005\t\u0005\u0013\nI\u00041\u0001\u00038R!!1\u0016D\u0014\u0011!\u0011I%a\u000fA\u0002\t]F\u0003\u0002B\\\rWA\u0001B!\u0013\u0002>\u0001\u0007!q\u0017\u000b\u0007\u0005o3yC\"\r\t\u0011\t%\u0013q\ba\u0001\u0005oC\u0001B!\u0014\u0002@\u0001\u0007!q\u0017\u000b\u0005\u0005o3)\u0004\u0003\u0005\u0003J\u0005\u0005\u0003\u0019\u0001B\\)\u0011\u00119L\"\u000f\t\u0011\t%\u00131\ta\u0001\u0005o\u000b!\u0003R8vE2,\u0017j\u001d$sC\u000e$\u0018n\u001c8bYB!11JA$'!\t9E!\u0005\u0007B\u0019\r\u0003\u0003BB&\u0003O\u0001BA\"\u0012\u0007L9!1q\u0016D$\u0013\u00111Ie!/\u0002\r\u0011{WO\u00197f\u0013\u0011)\tP\"\u0014\u000b\t\u0019%3\u0011\u0018\u000b\u0003\r{\u0011aCQ5h\t\u0016\u001c\u0017.\\1m\u0013N\u001cuN\u001c4mS\u000e$X\rZ\n\u0007\u0003\u001b\u0012\tB\"\u0016\u0011\r\u0005\r\u0018Q\u0013D,!\u0011\t\u0019O\"\u0017\n\t\u0019m\u0013q\u001b\u0002\u000b\u0005&<G)Z2j[\u0006dGC\u0002D,\r?2\t\u0007\u0003\u0005\u0003J\u0005E\u0003\u0019\u0001D,\u0011!\u0011i%!\u0015A\u0002\u0019]CC\u0002D,\rK29\u0007\u0003\u0005\u0003J\u0005M\u0003\u0019\u0001D,\u0011!\u0011i%a\u0015A\u0002\u0019]CC\u0002D,\rW2i\u0007\u0003\u0005\u0003J\u0005U\u0003\u0019\u0001D,\u0011!\u0011i%!\u0016A\u0002\u0019]C\u0003\u0002D,\rcB\u0001B!\u0013\u0002X\u0001\u0007aq\u000b\u000b\u0005\r/2)\b\u0003\u0005\u0003J\u0005e\u0003\u0019\u0001B6)\u00111IHb\u001f\u0011\r\u0005-(q\u000fD,\u0011!\u0011i(a\u0017A\u0002\t}D\u0003\u0002B6\r\u007fB\u0001B!\u0013\u0002^\u0001\u0007aq\u000b\u000b\u0005\u0005?3\u0019\t\u0003\u0005\u0003J\u0005}\u0003\u0019\u0001D,)\u0011\u0011YKb\"\t\u0011\t%\u0013\u0011\ra\u0001\r/\"BAa.\u0007\f\"A!\u0011JA2\u0001\u000419&\u0001\fCS\u001e$UmY5nC2L5oQ8oM2L7\r^3e!\u0011\u0019Y%a\u001a\u0014\r\u0005\u001d\u0014\u0011^Ay)\t1y)\u0001\u0002`aU\u0011aqK\u0001\u0004?B\u0002\u0013AA02\u0003\ry\u0016\u0007\t\u0002\u0017\u0005&<G)Z2j[\u0006d\u0017j\u001d$sC\u000e$\u0018n\u001c8bYNA\u0011Q\u000fB\t\rG3)\u000b\u0005\u0003\u0004L\u00055\u0003CBAr\u000b739\u0006\u0006\u0004\u0007X\u0019%f1\u0016\u0005\t\u0005\u0013\nI\b1\u0001\u0007X!A!QJA=\u0001\u000419F\u0001\fCS\u001e$UmY5nC2\f5/\u00134J]R,wM]1m'!\tYH!\u0005\u0007$\u001aE\u0006CBAr\u0007729\u0006\u0006\u0004\u0007X\u0019Ufq\u0017\u0005\t\u0005\u0013\ny\b1\u0001\u0007X!A!QJA@\u0001\u000419\u0006\u0006\u0004\u0007X\u0019mfQ\u0018\u0005\t\u0005\u0013\n\t\t1\u0001\u0007X!A!QJAA\u0001\u000419&\u0001\fCS\u001e$UmY5nC2L5O\u0012:bGRLwN\\1m!\u0011\u0019Y%!\"\u0014\u0011\u0005\u0015%\u0011\u0003Dc\r\u000f\u0004Baa\u0013\u0002vA!1q\u0016De\u0013\u00111Ym!/\u0003%\tKw\rR3dS6\fGn\u0014:eKJLgn\u001a\u000b\u0003\r\u0003\faCQ5h\t\u0016\u001c\u0017.\\1m\u0003NLe-\u00138uK\u001e\u0014\u0018\r\u001c\t\u0005\u0007\u0017\nii\u0005\u0005\u0002\u000e\nEaQ\u001bDd!\u0011\u0019Y%a\u001f\u0015\u0005\u0019E\u0007"
)
public interface Numeric extends Ordering {
   static Numeric apply(final Numeric num) {
      Numeric$ var10000 = Numeric$.MODULE$;
      return num;
   }

   Object plus(final Object x, final Object y);

   Object minus(final Object x, final Object y);

   Object times(final Object x, final Object y);

   Object negate(final Object x);

   Object fromInt(final int x);

   Option parseString(final String str);

   int toInt(final Object x);

   long toLong(final Object x);

   float toFloat(final Object x);

   double toDouble(final Object x);

   default Object zero() {
      return this.fromInt(0);
   }

   default Object one() {
      return this.fromInt(1);
   }

   default Object abs(final Object x) {
      return this.lt(x, this.zero()) ? this.negate(x) : x;
   }

   /** @deprecated */
   default int signum(final Object x) {
      if (this.lt(x, this.zero())) {
         return -1;
      } else {
         return this.gt(x, this.zero()) ? 1 : 0;
      }
   }

   default Object sign(final Object x) {
      if (this.lt(x, this.zero())) {
         return this.negate(this.one());
      } else {
         return this.gt(x, this.zero()) ? this.one() : this.zero();
      }
   }

   // $FF: synthetic method
   static NumericOps mkNumericOps$(final Numeric $this, final Object lhs) {
      return $this.mkNumericOps(lhs);
   }

   default NumericOps mkNumericOps(final Object lhs) {
      return new NumericOps(lhs);
   }

   static void $init$(final Numeric $this) {
   }

   public interface ExtraImplicits {
      default NumericOps infixNumericOps(final Object x, final Numeric num) {
         return num.new NumericOps(x);
      }

      static void $init$(final ExtraImplicits $this) {
      }
   }

   public static class Implicits$ implements ExtraImplicits {
      public static final Implicits$ MODULE$ = new Implicits$();

      static {
         Implicits$ var10000 = MODULE$;
      }

      public NumericOps infixNumericOps(final Object x, final Numeric num) {
         return Numeric.ExtraImplicits.super.infixNumericOps(x, num);
      }
   }

   public interface BigIntIsIntegral extends Integral {
      default BigInt plus(final BigInt x, final BigInt y) {
         return x.$plus(y);
      }

      default BigInt minus(final BigInt x, final BigInt y) {
         return x.$minus(y);
      }

      default BigInt times(final BigInt x, final BigInt y) {
         return x.$times(y);
      }

      default BigInt quot(final BigInt x, final BigInt y) {
         return x.$div(y);
      }

      default BigInt rem(final BigInt x, final BigInt y) {
         return x.$percent(y);
      }

      default BigInt negate(final BigInt x) {
         return x.unary_$minus();
      }

      default BigInt fromInt(final int x) {
         return scala.math.BigInt$.MODULE$.apply(x);
      }

      default Option parseString(final String str) {
         Try$ var10000 = Try$.MODULE$;

         try {
            Object apply_r1 = scala.math.BigInt$.MODULE$.apply(str);
            var10000 = new Success(apply_r1);
         } catch (Throwable var4) {
            if (var4 == null || !NonFatal$.MODULE$.apply(var4)) {
               throw var4;
            }

            var10000 = new Failure(var4);
         }

         Object var5 = null;
         Object var3 = null;
         return ((Try)var10000).toOption();
      }

      default int toInt(final BigInt x) {
         return x.intValue();
      }

      default long toLong(final BigInt x) {
         return x.longValue();
      }

      default float toFloat(final BigInt x) {
         return x.floatValue();
      }

      default double toDouble(final BigInt x) {
         return x.doubleValue();
      }

      // $FF: synthetic method
      static BigInt $anonfun$parseString$1(final String str$1) {
         return scala.math.BigInt$.MODULE$.apply(str$1);
      }

      static void $init$(final BigIntIsIntegral $this) {
      }
   }

   public static class BigIntIsIntegral$ implements BigIntIsIntegral, Ordering.BigIntOrdering {
      public static final BigIntIsIntegral$ MODULE$ = new BigIntIsIntegral$();

      static {
         BigIntIsIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final BigInt x, final BigInt y) {
         return Ordering.BigIntOrdering.compare$(this, x, y);
      }

      public BigInt plus(final BigInt x, final BigInt y) {
         return Numeric.BigIntIsIntegral.super.plus(x, y);
      }

      public BigInt minus(final BigInt x, final BigInt y) {
         return Numeric.BigIntIsIntegral.super.minus(x, y);
      }

      public BigInt times(final BigInt x, final BigInt y) {
         return Numeric.BigIntIsIntegral.super.times(x, y);
      }

      public BigInt quot(final BigInt x, final BigInt y) {
         return Numeric.BigIntIsIntegral.super.quot(x, y);
      }

      public BigInt rem(final BigInt x, final BigInt y) {
         return Numeric.BigIntIsIntegral.super.rem(x, y);
      }

      public BigInt negate(final BigInt x) {
         return Numeric.BigIntIsIntegral.super.negate(x);
      }

      public BigInt fromInt(final int x) {
         return Numeric.BigIntIsIntegral.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.BigIntIsIntegral.super.parseString(str);
      }

      public int toInt(final BigInt x) {
         return Numeric.BigIntIsIntegral.super.toInt(x);
      }

      public long toLong(final BigInt x) {
         return Numeric.BigIntIsIntegral.super.toLong(x);
      }

      public float toFloat(final BigInt x) {
         return Numeric.BigIntIsIntegral.super.toFloat(x);
      }

      public double toDouble(final BigInt x) {
         return Numeric.BigIntIsIntegral.super.toDouble(x);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.super.signum(x);
      }

      public Object sign(final Object x) {
         return Numeric.super.sign(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(BigIntIsIntegral$.class);
      }
   }

   public interface IntIsIntegral extends Integral {
      default int plus(final int x, final int y) {
         return x + y;
      }

      default int minus(final int x, final int y) {
         return x - y;
      }

      default int times(final int x, final int y) {
         return x * y;
      }

      default int quot(final int x, final int y) {
         return x / y;
      }

      default int rem(final int x, final int y) {
         return x % y;
      }

      default int negate(final int x) {
         return -x;
      }

      default int fromInt(final int x) {
         return x;
      }

      default Option parseString(final String str) {
         return StringParsers$.MODULE$.parseInt(str);
      }

      default int toInt(final int x) {
         return x;
      }

      default long toLong(final int x) {
         return (long)x;
      }

      default float toFloat(final int x) {
         return (float)x;
      }

      default double toDouble(final int x) {
         return (double)x;
      }

      default int signum(final int x) {
         package$ var10000 = package$.MODULE$;
         return Integer.signum(x);
      }

      default int sign(final int x) {
         package$ var10000 = package$.MODULE$;
         return Integer.signum(x);
      }

      static void $init$(final IntIsIntegral $this) {
      }
   }

   public static class IntIsIntegral$ implements IntIsIntegral, Ordering.IntOrdering {
      public static final IntIsIntegral$ MODULE$ = new IntIsIntegral$();

      static {
         IntIsIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final int x, final int y) {
         return Ordering.IntOrdering.compare$(this, x, y);
      }

      public int plus(final int x, final int y) {
         return Numeric.IntIsIntegral.super.plus(x, y);
      }

      public int minus(final int x, final int y) {
         return Numeric.IntIsIntegral.super.minus(x, y);
      }

      public int times(final int x, final int y) {
         return Numeric.IntIsIntegral.super.times(x, y);
      }

      public int quot(final int x, final int y) {
         return Numeric.IntIsIntegral.super.quot(x, y);
      }

      public int rem(final int x, final int y) {
         return Numeric.IntIsIntegral.super.rem(x, y);
      }

      public int negate(final int x) {
         return Numeric.IntIsIntegral.super.negate(x);
      }

      public int fromInt(final int x) {
         return Numeric.IntIsIntegral.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.IntIsIntegral.super.parseString(str);
      }

      public int toInt(final int x) {
         return Numeric.IntIsIntegral.super.toInt(x);
      }

      public long toLong(final int x) {
         return Numeric.IntIsIntegral.super.toLong(x);
      }

      public float toFloat(final int x) {
         return Numeric.IntIsIntegral.super.toFloat(x);
      }

      public double toDouble(final int x) {
         return Numeric.IntIsIntegral.super.toDouble(x);
      }

      public int signum(final int x) {
         return Numeric.IntIsIntegral.super.signum(x);
      }

      public int sign(final int x) {
         return Numeric.IntIsIntegral.super.sign(x);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(IntIsIntegral$.class);
      }
   }

   public interface ShortIsIntegral extends Integral {
      default short plus(final short x, final short y) {
         return (short)(x + y);
      }

      default short minus(final short x, final short y) {
         return (short)(x - y);
      }

      default short times(final short x, final short y) {
         return (short)(x * y);
      }

      default short quot(final short x, final short y) {
         return (short)(x / y);
      }

      default short rem(final short x, final short y) {
         return (short)(x % y);
      }

      default short negate(final short x) {
         return (short)(-x);
      }

      default short fromInt(final int x) {
         return (short)x;
      }

      default Option parseString(final String str) {
         return StringParsers$.MODULE$.parseShort(str);
      }

      default int toInt(final short x) {
         return x;
      }

      default long toLong(final short x) {
         return (long)x;
      }

      default float toFloat(final short x) {
         return (float)x;
      }

      default double toDouble(final short x) {
         return (double)x;
      }

      default int signum(final short x) {
         package$ var10000 = package$.MODULE$;
         return Integer.signum(x);
      }

      default short sign(final short x) {
         package$ var10000 = package$.MODULE$;
         return (short)Integer.signum(x);
      }

      static void $init$(final ShortIsIntegral $this) {
      }
   }

   public static class ShortIsIntegral$ implements ShortIsIntegral, Ordering.ShortOrdering {
      public static final ShortIsIntegral$ MODULE$ = new ShortIsIntegral$();

      static {
         ShortIsIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final short x, final short y) {
         return Ordering.ShortOrdering.compare$(this, x, y);
      }

      public short plus(final short x, final short y) {
         return Numeric.ShortIsIntegral.super.plus(x, y);
      }

      public short minus(final short x, final short y) {
         return Numeric.ShortIsIntegral.super.minus(x, y);
      }

      public short times(final short x, final short y) {
         return Numeric.ShortIsIntegral.super.times(x, y);
      }

      public short quot(final short x, final short y) {
         return Numeric.ShortIsIntegral.super.quot(x, y);
      }

      public short rem(final short x, final short y) {
         return Numeric.ShortIsIntegral.super.rem(x, y);
      }

      public short negate(final short x) {
         return Numeric.ShortIsIntegral.super.negate(x);
      }

      public short fromInt(final int x) {
         return Numeric.ShortIsIntegral.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.ShortIsIntegral.super.parseString(str);
      }

      public int toInt(final short x) {
         return Numeric.ShortIsIntegral.super.toInt(x);
      }

      public long toLong(final short x) {
         return Numeric.ShortIsIntegral.super.toLong(x);
      }

      public float toFloat(final short x) {
         return Numeric.ShortIsIntegral.super.toFloat(x);
      }

      public double toDouble(final short x) {
         return Numeric.ShortIsIntegral.super.toDouble(x);
      }

      public int signum(final short x) {
         return Numeric.ShortIsIntegral.super.signum(x);
      }

      public short sign(final short x) {
         return Numeric.ShortIsIntegral.super.sign(x);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(ShortIsIntegral$.class);
      }
   }

   public interface ByteIsIntegral extends Integral {
      default byte plus(final byte x, final byte y) {
         return (byte)(x + y);
      }

      default byte minus(final byte x, final byte y) {
         return (byte)(x - y);
      }

      default byte times(final byte x, final byte y) {
         return (byte)(x * y);
      }

      default byte quot(final byte x, final byte y) {
         return (byte)(x / y);
      }

      default byte rem(final byte x, final byte y) {
         return (byte)(x % y);
      }

      default byte negate(final byte x) {
         return (byte)(-x);
      }

      default byte fromInt(final int x) {
         return (byte)x;
      }

      default Option parseString(final String str) {
         return StringParsers$.MODULE$.parseByte(str);
      }

      default int toInt(final byte x) {
         return x;
      }

      default long toLong(final byte x) {
         return (long)x;
      }

      default float toFloat(final byte x) {
         return (float)x;
      }

      default double toDouble(final byte x) {
         return (double)x;
      }

      default int signum(final byte x) {
         package$ var10000 = package$.MODULE$;
         return Integer.signum(x);
      }

      default byte sign(final byte x) {
         package$ var10000 = package$.MODULE$;
         return (byte)Integer.signum(x);
      }

      static void $init$(final ByteIsIntegral $this) {
      }
   }

   public static class ByteIsIntegral$ implements ByteIsIntegral, Ordering.ByteOrdering {
      public static final ByteIsIntegral$ MODULE$ = new ByteIsIntegral$();

      static {
         ByteIsIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final byte x, final byte y) {
         return Ordering.ByteOrdering.compare$(this, x, y);
      }

      public byte plus(final byte x, final byte y) {
         return Numeric.ByteIsIntegral.super.plus(x, y);
      }

      public byte minus(final byte x, final byte y) {
         return Numeric.ByteIsIntegral.super.minus(x, y);
      }

      public byte times(final byte x, final byte y) {
         return Numeric.ByteIsIntegral.super.times(x, y);
      }

      public byte quot(final byte x, final byte y) {
         return Numeric.ByteIsIntegral.super.quot(x, y);
      }

      public byte rem(final byte x, final byte y) {
         return Numeric.ByteIsIntegral.super.rem(x, y);
      }

      public byte negate(final byte x) {
         return Numeric.ByteIsIntegral.super.negate(x);
      }

      public byte fromInt(final int x) {
         return Numeric.ByteIsIntegral.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.ByteIsIntegral.super.parseString(str);
      }

      public int toInt(final byte x) {
         return Numeric.ByteIsIntegral.super.toInt(x);
      }

      public long toLong(final byte x) {
         return Numeric.ByteIsIntegral.super.toLong(x);
      }

      public float toFloat(final byte x) {
         return Numeric.ByteIsIntegral.super.toFloat(x);
      }

      public double toDouble(final byte x) {
         return Numeric.ByteIsIntegral.super.toDouble(x);
      }

      public int signum(final byte x) {
         return Numeric.ByteIsIntegral.super.signum(x);
      }

      public byte sign(final byte x) {
         return Numeric.ByteIsIntegral.super.sign(x);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(ByteIsIntegral$.class);
      }
   }

   public interface CharIsIntegral extends Integral {
      default char plus(final char x, final char y) {
         return (char)(x + y);
      }

      default char minus(final char x, final char y) {
         return (char)(x - y);
      }

      default char times(final char x, final char y) {
         return (char)(x * y);
      }

      default char quot(final char x, final char y) {
         return (char)(x / y);
      }

      default char rem(final char x, final char y) {
         return (char)(x % y);
      }

      default char negate(final char x) {
         return (char)(-x);
      }

      default char fromInt(final int x) {
         return (char)x;
      }

      default Option parseString(final String str) {
         Try$ var10000 = Try$.MODULE$;

         try {
            Object apply_r1 = BoxesRunTime.boxToCharacter($anonfun$parseString$2(str));
            var10000 = new Success(apply_r1);
         } catch (Throwable var4) {
            if (var4 == null || !NonFatal$.MODULE$.apply(var4)) {
               throw var4;
            }

            var10000 = new Failure(var4);
         }

         Object var5 = null;
         Object var3 = null;
         return ((Try)var10000).toOption();
      }

      default int toInt(final char x) {
         return x;
      }

      default long toLong(final char x) {
         return (long)x;
      }

      default float toFloat(final char x) {
         return (float)x;
      }

      default double toDouble(final char x) {
         return (double)x;
      }

      default int signum(final char x) {
         package$ var10000 = package$.MODULE$;
         return Integer.signum(x);
      }

      default char sign(final char x) {
         package$ var10000 = package$.MODULE$;
         return (char)Integer.signum(x);
      }

      // $FF: synthetic method
      static char $anonfun$parseString$2(final String str$2) {
         return (char)Integer.parseInt(str$2);
      }

      static void $init$(final CharIsIntegral $this) {
      }
   }

   public static class CharIsIntegral$ implements CharIsIntegral, Ordering.CharOrdering {
      public static final CharIsIntegral$ MODULE$ = new CharIsIntegral$();

      static {
         CharIsIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final char x, final char y) {
         return Ordering.CharOrdering.compare$(this, x, y);
      }

      public char plus(final char x, final char y) {
         return Numeric.CharIsIntegral.super.plus(x, y);
      }

      public char minus(final char x, final char y) {
         return Numeric.CharIsIntegral.super.minus(x, y);
      }

      public char times(final char x, final char y) {
         return Numeric.CharIsIntegral.super.times(x, y);
      }

      public char quot(final char x, final char y) {
         return Numeric.CharIsIntegral.super.quot(x, y);
      }

      public char rem(final char x, final char y) {
         return Numeric.CharIsIntegral.super.rem(x, y);
      }

      public char negate(final char x) {
         return Numeric.CharIsIntegral.super.negate(x);
      }

      public char fromInt(final int x) {
         return Numeric.CharIsIntegral.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.CharIsIntegral.super.parseString(str);
      }

      public int toInt(final char x) {
         return Numeric.CharIsIntegral.super.toInt(x);
      }

      public long toLong(final char x) {
         return Numeric.CharIsIntegral.super.toLong(x);
      }

      public float toFloat(final char x) {
         return Numeric.CharIsIntegral.super.toFloat(x);
      }

      public double toDouble(final char x) {
         return Numeric.CharIsIntegral.super.toDouble(x);
      }

      public int signum(final char x) {
         return Numeric.CharIsIntegral.super.signum(x);
      }

      public char sign(final char x) {
         return Numeric.CharIsIntegral.super.sign(x);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(CharIsIntegral$.class);
      }
   }

   public interface LongIsIntegral extends Integral {
      default long plus(final long x, final long y) {
         return x + y;
      }

      default long minus(final long x, final long y) {
         return x - y;
      }

      default long times(final long x, final long y) {
         return x * y;
      }

      default long quot(final long x, final long y) {
         return x / y;
      }

      default long rem(final long x, final long y) {
         return x % y;
      }

      default long negate(final long x) {
         return -x;
      }

      default long fromInt(final int x) {
         return (long)x;
      }

      default Option parseString(final String str) {
         return StringParsers$.MODULE$.parseLong(str);
      }

      default int toInt(final long x) {
         return (int)x;
      }

      default long toLong(final long x) {
         return x;
      }

      default float toFloat(final long x) {
         return (float)x;
      }

      default double toDouble(final long x) {
         return (double)x;
      }

      default int signum(final long x) {
         package$ var10000 = package$.MODULE$;
         return (int)((long)Long.signum(x));
      }

      default long sign(final long x) {
         package$ var10000 = package$.MODULE$;
         return (long)Long.signum(x);
      }

      static void $init$(final LongIsIntegral $this) {
      }
   }

   public static class LongIsIntegral$ implements LongIsIntegral, Ordering.LongOrdering {
      public static final LongIsIntegral$ MODULE$ = new LongIsIntegral$();

      static {
         LongIsIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final long x, final long y) {
         return Ordering.LongOrdering.compare$(this, x, y);
      }

      public long plus(final long x, final long y) {
         return Numeric.LongIsIntegral.super.plus(x, y);
      }

      public long minus(final long x, final long y) {
         return Numeric.LongIsIntegral.super.minus(x, y);
      }

      public long times(final long x, final long y) {
         return Numeric.LongIsIntegral.super.times(x, y);
      }

      public long quot(final long x, final long y) {
         return Numeric.LongIsIntegral.super.quot(x, y);
      }

      public long rem(final long x, final long y) {
         return Numeric.LongIsIntegral.super.rem(x, y);
      }

      public long negate(final long x) {
         return Numeric.LongIsIntegral.super.negate(x);
      }

      public long fromInt(final int x) {
         return Numeric.LongIsIntegral.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.LongIsIntegral.super.parseString(str);
      }

      public int toInt(final long x) {
         return Numeric.LongIsIntegral.super.toInt(x);
      }

      public long toLong(final long x) {
         return Numeric.LongIsIntegral.super.toLong(x);
      }

      public float toFloat(final long x) {
         return Numeric.LongIsIntegral.super.toFloat(x);
      }

      public double toDouble(final long x) {
         return Numeric.LongIsIntegral.super.toDouble(x);
      }

      public int signum(final long x) {
         return Numeric.LongIsIntegral.super.signum(x);
      }

      public long sign(final long x) {
         return Numeric.LongIsIntegral.super.sign(x);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(LongIsIntegral$.class);
      }
   }

   public interface FloatIsFractional extends Fractional {
      default float plus(final float x, final float y) {
         return x + y;
      }

      default float minus(final float x, final float y) {
         return x - y;
      }

      default float times(final float x, final float y) {
         return x * y;
      }

      default float negate(final float x) {
         return -x;
      }

      default float fromInt(final int x) {
         return (float)x;
      }

      default Option parseString(final String str) {
         return (Option)(StringParsers$.MODULE$.checkFloatFormat(str) ? new Some(Float.parseFloat(str)) : None$.MODULE$);
      }

      default int toInt(final float x) {
         return (int)x;
      }

      default long toLong(final float x) {
         return (long)x;
      }

      default float toFloat(final float x) {
         return x;
      }

      default double toDouble(final float x) {
         return (double)x;
      }

      default float div(final float x, final float y) {
         return x / y;
      }

      default float abs(final float x) {
         package$ var10000 = package$.MODULE$;
         return Math.abs(x);
      }

      default float sign(final float x) {
         package$ var10000 = package$.MODULE$;
         return Math.signum(x);
      }

      static void $init$(final FloatIsFractional $this) {
      }
   }

   public static class FloatIsFractional$ implements FloatIsFractional, Ordering$Float$IeeeOrdering {
      public static final FloatIsFractional$ MODULE$ = new FloatIsFractional$();

      static {
         FloatIsFractional$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.compare$(this, x, y);
      }

      public boolean lteq(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.lteq$(this, x, y);
      }

      public boolean gteq(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.gteq$(this, x, y);
      }

      public boolean lt(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.lt$(this, x, y);
      }

      public boolean gt(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.gt$(this, x, y);
      }

      public boolean equiv(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.equiv$(this, x, y);
      }

      public float max(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.max$(this, x, y);
      }

      public float min(final float x, final float y) {
         return Ordering$Float$IeeeOrdering.min$(this, x, y);
      }

      public float plus(final float x, final float y) {
         return Numeric.FloatIsFractional.super.plus(x, y);
      }

      public float minus(final float x, final float y) {
         return Numeric.FloatIsFractional.super.minus(x, y);
      }

      public float times(final float x, final float y) {
         return Numeric.FloatIsFractional.super.times(x, y);
      }

      public float negate(final float x) {
         return Numeric.FloatIsFractional.super.negate(x);
      }

      public float fromInt(final int x) {
         return Numeric.FloatIsFractional.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.FloatIsFractional.super.parseString(str);
      }

      public int toInt(final float x) {
         return Numeric.FloatIsFractional.super.toInt(x);
      }

      public long toLong(final float x) {
         return Numeric.FloatIsFractional.super.toLong(x);
      }

      public float toFloat(final float x) {
         return Numeric.FloatIsFractional.super.toFloat(x);
      }

      public double toDouble(final float x) {
         return Numeric.FloatIsFractional.super.toDouble(x);
      }

      public float div(final float x, final float y) {
         return Numeric.FloatIsFractional.super.div(x, y);
      }

      public float abs(final float x) {
         return Numeric.FloatIsFractional.super.abs(x);
      }

      public float sign(final float x) {
         return Numeric.FloatIsFractional.super.sign(x);
      }

      public Fractional.FractionalOps mkNumericOps(final Object lhs) {
         return Fractional.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.super.signum(x);
      }

      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
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

      private Object writeReplace() {
         return new ModuleSerializationProxy(FloatIsFractional$.class);
      }
   }

   public interface DoubleIsFractional extends Fractional {
      default double plus(final double x, final double y) {
         return x + y;
      }

      default double minus(final double x, final double y) {
         return x - y;
      }

      default double times(final double x, final double y) {
         return x * y;
      }

      default double negate(final double x) {
         return -x;
      }

      default double fromInt(final int x) {
         return (double)x;
      }

      default Option parseString(final String str) {
         return (Option)(StringParsers$.MODULE$.checkFloatFormat(str) ? new Some(Double.parseDouble(str)) : None$.MODULE$);
      }

      default int toInt(final double x) {
         return (int)x;
      }

      default long toLong(final double x) {
         return (long)x;
      }

      default float toFloat(final double x) {
         return (float)x;
      }

      default double toDouble(final double x) {
         return x;
      }

      default double div(final double x, final double y) {
         return x / y;
      }

      default double abs(final double x) {
         package$ var10000 = package$.MODULE$;
         return Math.abs(x);
      }

      default double sign(final double x) {
         package$ var10000 = package$.MODULE$;
         return Math.signum(x);
      }

      static void $init$(final DoubleIsFractional $this) {
      }
   }

   public static class DoubleIsFractional$ implements DoubleIsFractional, Ordering$Double$IeeeOrdering {
      public static final DoubleIsFractional$ MODULE$ = new DoubleIsFractional$();

      static {
         DoubleIsFractional$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.compare$(this, x, y);
      }

      public boolean lteq(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.lteq$(this, x, y);
      }

      public boolean gteq(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.gteq$(this, x, y);
      }

      public boolean lt(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.lt$(this, x, y);
      }

      public boolean gt(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.gt$(this, x, y);
      }

      public boolean equiv(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.equiv$(this, x, y);
      }

      public double max(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.max$(this, x, y);
      }

      public double min(final double x, final double y) {
         return Ordering$Double$IeeeOrdering.min$(this, x, y);
      }

      public double plus(final double x, final double y) {
         return Numeric.DoubleIsFractional.super.plus(x, y);
      }

      public double minus(final double x, final double y) {
         return Numeric.DoubleIsFractional.super.minus(x, y);
      }

      public double times(final double x, final double y) {
         return Numeric.DoubleIsFractional.super.times(x, y);
      }

      public double negate(final double x) {
         return Numeric.DoubleIsFractional.super.negate(x);
      }

      public double fromInt(final int x) {
         return Numeric.DoubleIsFractional.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.DoubleIsFractional.super.parseString(str);
      }

      public int toInt(final double x) {
         return Numeric.DoubleIsFractional.super.toInt(x);
      }

      public long toLong(final double x) {
         return Numeric.DoubleIsFractional.super.toLong(x);
      }

      public float toFloat(final double x) {
         return Numeric.DoubleIsFractional.super.toFloat(x);
      }

      public double toDouble(final double x) {
         return Numeric.DoubleIsFractional.super.toDouble(x);
      }

      public double div(final double x, final double y) {
         return Numeric.DoubleIsFractional.super.div(x, y);
      }

      public double abs(final double x) {
         return Numeric.DoubleIsFractional.super.abs(x);
      }

      public double sign(final double x) {
         return Numeric.DoubleIsFractional.super.sign(x);
      }

      public Fractional.FractionalOps mkNumericOps(final Object lhs) {
         return Fractional.mkNumericOps$(this, lhs);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.super.signum(x);
      }

      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
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

      private Object writeReplace() {
         return new ModuleSerializationProxy(DoubleIsFractional$.class);
      }
   }

   public interface BigDecimalIsConflicted extends Numeric {
      default BigDecimal plus(final BigDecimal x, final BigDecimal y) {
         return x == Numeric.BigDecimalIsConflicted$.MODULE$.scala$math$Numeric$BigDecimalIsConflicted$$_0() ? y : x.$plus(y);
      }

      default BigDecimal minus(final BigDecimal x, final BigDecimal y) {
         return x == Numeric.BigDecimalIsConflicted$.MODULE$.scala$math$Numeric$BigDecimalIsConflicted$$_0() ? y.unary_$minus() : x.$minus(y);
      }

      default BigDecimal times(final BigDecimal x, final BigDecimal y) {
         return x == Numeric.BigDecimalIsConflicted$.MODULE$.scala$math$Numeric$BigDecimalIsConflicted$$_1() ? y : x.$times(y);
      }

      default BigDecimal negate(final BigDecimal x) {
         return x.unary_$minus();
      }

      default BigDecimal fromInt(final int x) {
         return scala.math.BigDecimal$.MODULE$.apply(x);
      }

      default Option parseString(final String str) {
         Try$ var10000 = Try$.MODULE$;

         try {
            Object apply_r1 = scala.math.BigDecimal$.MODULE$.exact(str);
            var10000 = new Success(apply_r1);
         } catch (Throwable var4) {
            if (var4 == null || !NonFatal$.MODULE$.apply(var4)) {
               throw var4;
            }

            var10000 = new Failure(var4);
         }

         Object var5 = null;
         Object var3 = null;
         return ((Try)var10000).toOption();
      }

      default int toInt(final BigDecimal x) {
         return x.intValue();
      }

      default long toLong(final BigDecimal x) {
         return x.longValue();
      }

      default float toFloat(final BigDecimal x) {
         return x.floatValue();
      }

      default double toDouble(final BigDecimal x) {
         return x.doubleValue();
      }

      // $FF: synthetic method
      static BigDecimal $anonfun$parseString$3(final String str$3) {
         return scala.math.BigDecimal$.MODULE$.apply(str$3);
      }

      static void $init$(final BigDecimalIsConflicted $this) {
      }
   }

   private static class BigDecimalIsConflicted$ implements Serializable {
      public static final BigDecimalIsConflicted$ MODULE$ = new BigDecimalIsConflicted$();
      private static final BigDecimal scala$math$Numeric$BigDecimalIsConflicted$$_0;
      private static final BigDecimal scala$math$Numeric$BigDecimalIsConflicted$$_1;

      static {
         scala$math$Numeric$BigDecimalIsConflicted$$_0 = scala.math.BigDecimal$.MODULE$.apply(0);
         scala$math$Numeric$BigDecimalIsConflicted$$_1 = scala.math.BigDecimal$.MODULE$.apply(1);
      }

      public BigDecimal scala$math$Numeric$BigDecimalIsConflicted$$_0() {
         return scala$math$Numeric$BigDecimalIsConflicted$$_0;
      }

      public BigDecimal scala$math$Numeric$BigDecimalIsConflicted$$_1() {
         return scala$math$Numeric$BigDecimalIsConflicted$$_1;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(BigDecimalIsConflicted$.class);
      }

      public BigDecimalIsConflicted$() {
      }
   }

   public interface BigDecimalIsFractional extends BigDecimalIsConflicted, Fractional {
      default BigDecimal div(final BigDecimal x, final BigDecimal y) {
         return x.$div(y);
      }

      static void $init$(final BigDecimalIsFractional $this) {
      }
   }

   public interface BigDecimalAsIfIntegral extends BigDecimalIsConflicted, Integral {
      default BigDecimal quot(final BigDecimal x, final BigDecimal y) {
         return x.quot(y);
      }

      default BigDecimal rem(final BigDecimal x, final BigDecimal y) {
         return x.remainder(y);
      }

      static void $init$(final BigDecimalAsIfIntegral $this) {
      }
   }

   public static class BigDecimalIsFractional$ implements BigDecimalIsFractional, Ordering.BigDecimalOrdering {
      public static final BigDecimalIsFractional$ MODULE$ = new BigDecimalIsFractional$();

      static {
         BigDecimalIsFractional$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final BigDecimal x, final BigDecimal y) {
         return Ordering.BigDecimalOrdering.compare$(this, x, y);
      }

      public BigDecimal div(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsFractional.super.div(x, y);
      }

      public Fractional.FractionalOps mkNumericOps(final Object lhs) {
         return Fractional.mkNumericOps$(this, lhs);
      }

      public BigDecimal plus(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsConflicted.super.plus(x, y);
      }

      public BigDecimal minus(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsConflicted.super.minus(x, y);
      }

      public BigDecimal times(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsConflicted.super.times(x, y);
      }

      public BigDecimal negate(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.negate(x);
      }

      public BigDecimal fromInt(final int x) {
         return Numeric.BigDecimalIsConflicted.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.BigDecimalIsConflicted.super.parseString(str);
      }

      public int toInt(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toInt(x);
      }

      public long toLong(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toLong(x);
      }

      public float toFloat(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toFloat(x);
      }

      public double toDouble(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toDouble(x);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.super.signum(x);
      }

      public Object sign(final Object x) {
         return Numeric.super.sign(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(BigDecimalIsFractional$.class);
      }
   }

   public static class BigDecimalAsIfIntegral$ implements BigDecimalAsIfIntegral, Ordering.BigDecimalOrdering {
      public static final BigDecimalAsIfIntegral$ MODULE$ = new BigDecimalAsIfIntegral$();

      static {
         BigDecimalAsIfIntegral$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public int compare(final BigDecimal x, final BigDecimal y) {
         return Ordering.BigDecimalOrdering.compare$(this, x, y);
      }

      public BigDecimal quot(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalAsIfIntegral.super.quot(x, y);
      }

      public BigDecimal rem(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalAsIfIntegral.super.rem(x, y);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public BigDecimal plus(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsConflicted.super.plus(x, y);
      }

      public BigDecimal minus(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsConflicted.super.minus(x, y);
      }

      public BigDecimal times(final BigDecimal x, final BigDecimal y) {
         return Numeric.BigDecimalIsConflicted.super.times(x, y);
      }

      public BigDecimal negate(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.negate(x);
      }

      public BigDecimal fromInt(final int x) {
         return Numeric.BigDecimalIsConflicted.super.fromInt(x);
      }

      public Option parseString(final String str) {
         return Numeric.BigDecimalIsConflicted.super.parseString(str);
      }

      public int toInt(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toInt(x);
      }

      public long toLong(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toLong(x);
      }

      public float toFloat(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toFloat(x);
      }

      public double toDouble(final BigDecimal x) {
         return Numeric.BigDecimalIsConflicted.super.toDouble(x);
      }

      public Object zero() {
         return Numeric.super.zero();
      }

      public Object one() {
         return Numeric.super.one();
      }

      public Object abs(final Object x) {
         return Numeric.super.abs(x);
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.super.signum(x);
      }

      public Object sign(final Object x) {
         return Numeric.super.sign(x);
      }

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

      private Object writeReplace() {
         return new ModuleSerializationProxy(BigDecimalAsIfIntegral$.class);
      }
   }

   public class NumericOps {
      private final Object lhs;
      // $FF: synthetic field
      public final Numeric $outer;

      public Object $plus(final Object rhs) {
         return this.scala$math$Numeric$NumericOps$$$outer().plus(this.lhs, rhs);
      }

      public Object $minus(final Object rhs) {
         return this.scala$math$Numeric$NumericOps$$$outer().minus(this.lhs, rhs);
      }

      public Object $times(final Object rhs) {
         return this.scala$math$Numeric$NumericOps$$$outer().times(this.lhs, rhs);
      }

      public Object unary_$minus() {
         return this.scala$math$Numeric$NumericOps$$$outer().negate(this.lhs);
      }

      public Object abs() {
         return this.scala$math$Numeric$NumericOps$$$outer().abs(this.lhs);
      }

      /** @deprecated */
      public int signum() {
         return this.scala$math$Numeric$NumericOps$$$outer().signum(this.lhs);
      }

      public Object sign() {
         return this.scala$math$Numeric$NumericOps$$$outer().sign(this.lhs);
      }

      public int toInt() {
         return this.scala$math$Numeric$NumericOps$$$outer().toInt(this.lhs);
      }

      public long toLong() {
         return this.scala$math$Numeric$NumericOps$$$outer().toLong(this.lhs);
      }

      public float toFloat() {
         return this.scala$math$Numeric$NumericOps$$$outer().toFloat(this.lhs);
      }

      public double toDouble() {
         return this.scala$math$Numeric$NumericOps$$$outer().toDouble(this.lhs);
      }

      // $FF: synthetic method
      public Numeric scala$math$Numeric$NumericOps$$$outer() {
         return this.$outer;
      }

      public NumericOps(final Object lhs) {
         this.lhs = lhs;
         if (Numeric.this == null) {
            throw null;
         } else {
            this.$outer = Numeric.this;
            super();
         }
      }
   }
}
