package org.apache.spark.internal.config;

import scala.Enumeration;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\tErAB.]\u0011\u0003\u0001gM\u0002\u0004i9\"\u0005\u0001-\u001b\u0005\u0006a\u0006!\tA\u001d\u0005\bg\u0006\u0011\r\u0011\"\u0001u\u0011\u0019i\u0018\u0001)A\u0005k\"9a0\u0001b\u0001\n\u0003y\b\u0002CA\u000e\u0003\u0001\u0006I!!\u0001\t\u0013\u0005u\u0011A1A\u0005\u0002\u0005}\u0001\u0002CA\u0015\u0003\u0001\u0006I!!\t\t\u0013\u0005-\u0012A1A\u0005\u0002\u0005}\u0001\u0002CA\u0017\u0003\u0001\u0006I!!\t\t\u0013\u0005=\u0012A1A\u0005\u0002\u0005E\u0002\u0002CA\u001e\u0003\u0001\u0006I!a\r\t\u0013\u0005u\u0012A1A\u0005\u0002\u0005}\u0002\u0002CA%\u0003\u0001\u0006I!!\u0011\t\u0013\u0005-\u0013A1A\u0005\u0002\u0005}\u0001\u0002CA'\u0003\u0001\u0006I!!\t\t\u0013\u0005=\u0013A1A\u0005\u0002\u0005}\u0001\u0002CA)\u0003\u0001\u0006I!!\t\t\u0013\u0005M\u0013A1A\u0005\u0002\u0005E\u0002\u0002CA+\u0003\u0001\u0006I!a\r\t\u0013\u0005]\u0013A1A\u0005\u0002\u0005e\u0003\u0002CA1\u0003\u0001\u0006I!a\u0017\b\u000f\u0005\r\u0014\u0001#\u0001\u0002f\u00199\u0011\u0011N\u0001\t\u0002\u0005-\u0004B\u00029\u0019\t\u0003\t\u0019\bC\u0005\u0002va\u0011\r\u0011\"\u0001\u0002x!A\u0011\u0011\u0011\r!\u0002\u0013\tI\bC\u0005\u0002\u0004b\u0011\r\u0011\"\u0001\u0002x!A\u0011Q\u0011\r!\u0002\u0013\tI\bC\u0005\u0002\bb\t\t\u0011\"\u0003\u0002\n\"A\u0011\u0011S\u0001C\u0002\u0013\u0005q\u0010\u0003\u0005\u0002\u0014\u0006\u0001\u000b\u0011BA\u0001\u0011%\t)*\u0001b\u0001\n\u0003\ty\u0002\u0003\u0005\u0002\u0018\u0006\u0001\u000b\u0011BA\u0011\u0011!\tI*\u0001b\u0001\n\u0003y\b\u0002CAN\u0003\u0001\u0006I!!\u0001\t\u0013\u0005u\u0015A1A\u0005\u0002\u0005E\u0002\u0002CAP\u0003\u0001\u0006I!a\r\t\u0013\u0005\u0005\u0016A1A\u0005\u0002\u0005}\u0002\u0002CAR\u0003\u0001\u0006I!!\u0011\t\u0013\u0005\u0015\u0016A1A\u0005\u0002\u0005}\u0001\u0002CAT\u0003\u0001\u0006I!!\t\t\u0015\u0005%\u0016A1A\u0005\u0002\u0001\f\t\u0004\u0003\u0005\u0002,\u0006\u0001\u000b\u0011BA\u001a\u0011)\ti+\u0001b\u0001\n\u0003\u0001\u0017q\u0016\u0005\t\u0003s\u000b\u0001\u0015!\u0003\u00022\"I\u00111X\u0001C\u0002\u0013\u0005\u0011q\b\u0005\t\u0003{\u000b\u0001\u0015!\u0003\u0002B!I\u0011qX\u0001C\u0002\u0013\u0005\u0011q\u0004\u0005\t\u0003\u0003\f\u0001\u0015!\u0003\u0002\"!I\u00111Y\u0001C\u0002\u0013\u0005\u0011q\u0004\u0005\t\u0003\u000b\f\u0001\u0015!\u0003\u0002\"!I\u0011qY\u0001C\u0002\u0013\u0005\u0011q\b\u0005\t\u0003\u0013\f\u0001\u0015!\u0003\u0002B!I\u00111Z\u0001C\u0002\u0013\u0005\u0011Q\u001a\u0005\t\u0003G\f\u0001\u0015!\u0003\u0002P\"I\u0011Q]\u0001C\u0002\u0013\u0005\u0011Q\u001a\u0005\t\u0003O\f\u0001\u0015!\u0003\u0002P\"I\u0011\u0011^\u0001C\u0002\u0013\u0005\u0011\u0011\u0007\u0005\t\u0003W\f\u0001\u0015!\u0003\u00024!I\u0011Q^\u0001C\u0002\u0013\u0005\u0011\u0011\u0007\u0005\t\u0003_\f\u0001\u0015!\u0003\u00024!I\u0011\u0011_\u0001C\u0002\u0013\u0005\u0011\u0011\u0007\u0005\t\u0003g\f\u0001\u0015!\u0003\u00024!A\u0011Q_\u0001C\u0002\u0013\u0005q\u0010\u0003\u0005\u0002x\u0006\u0001\u000b\u0011BA\u0001\u0011%\tI0\u0001b\u0001\n\u0003\ty\u0004\u0003\u0005\u0002|\u0006\u0001\u000b\u0011BA!\u0011%\ti0\u0001b\u0001\n\u0003\tI\u0006\u0003\u0005\u0002\u0000\u0006\u0001\u000b\u0011BA.\u0011%\u0011\t!\u0001b\u0001\n\u0003\tI\u0006\u0003\u0005\u0003\u0004\u0005\u0001\u000b\u0011BA.\u0011%\u0011)!\u0001b\u0001\n\u0003\tI\u0006\u0003\u0005\u0003\b\u0005\u0001\u000b\u0011BA.\u0011%\u0011I!\u0001b\u0001\n\u0003\ty\u0004\u0003\u0005\u0003\f\u0005\u0001\u000b\u0011BA!\u0011%\u0011i!\u0001b\u0001\n\u0003\ty\u0004\u0003\u0005\u0003\u0010\u0005\u0001\u000b\u0011BA!\u0011%\u0011\t\"\u0001b\u0001\n\u0003\ty\u0002\u0003\u0005\u0003\u0014\u0005\u0001\u000b\u0011BA\u0011\u000f\u001d\u0011)\"\u0001E\u0001\u0005/1qA!\u0007\u0002\u0011\u0003\u0011Y\u0002\u0003\u0004q%\u0012\u0005!Q\u0004\u0005\n\u0005?\u0011&\u0019!C\u0001\u0005CA\u0001Ba\nSA\u0003%!1\u0005\u0005\n\u0005S\u0011&\u0019!C\u0001\u0005CA\u0001Ba\u000bSA\u0003%!1\u0005\u0005\n\u0003\u000f\u0013\u0016\u0011!C\u0005\u0003\u0013C\u0001B!\f\u0002\u0005\u0004%\ta \u0005\t\u0005_\t\u0001\u0015!\u0003\u0002\u0002\u00059\u0001*[:u_JL(BA/_\u0003\u0019\u0019wN\u001c4jO*\u0011q\fY\u0001\tS:$XM\u001d8bY*\u0011\u0011MY\u0001\u0006gB\f'o\u001b\u0006\u0003G\u0012\fa!\u00199bG\",'\"A3\u0002\u0007=\u0014x\r\u0005\u0002h\u00035\tALA\u0004ISN$xN]=\u0014\u0005\u0005Q\u0007CA6o\u001b\u0005a'\"A7\u0002\u000bM\u001c\u0017\r\\1\n\u0005=d'AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u00051\u0017a\u0004#F\r\u0006+F\nV0M\u001f\u001e{F)\u0013*\u0016\u0003U\u0004\"A^>\u000e\u0003]T!\u0001_=\u0002\t1\fgn\u001a\u0006\u0002u\u0006!!.\u0019<b\u0013\taxO\u0001\u0004TiJLgnZ\u0001\u0011\t\u00163\u0015)\u0016'U?2{ui\u0018#J%\u0002\nq\u0002S%T)>\u0013\u0016l\u0018'P\u000f~#\u0015JU\u000b\u0003\u0003\u0003\u0001RaZA\u0002\u0003\u000fI1!!\u0002]\u0005-\u0019uN\u001c4jO\u0016sGO]=\u0011\t\u0005%\u0011q\u0003\b\u0005\u0003\u0017\t\u0019\u0002E\u0002\u0002\u000e1l!!a\u0004\u000b\u0007\u0005E\u0011/\u0001\u0004=e>|GOP\u0005\u0004\u0003+a\u0017A\u0002)sK\u0012,g-C\u0002}\u00033Q1!!\u0006m\u0003AA\u0015j\u0015+P%f{FjT$`\t&\u0013\u0006%A\rT\u0003\u001a+Uj\u0014#F?\u000eCUiQ&`\u0013:#VI\u0015,B\u0019~\u001bVCAA\u0011!\u00159\u00171AA\u0012!\rY\u0017QE\u0005\u0004\u0003Oa'\u0001\u0002'p]\u001e\f!dU!G\u000b6{E)R0D\u0011\u0016\u001b5jX%O)\u0016\u0013f+\u0011'`'\u0002\n\u0011#\u0016)E\u0003R+u,\u0013(U\u000bJ3\u0016\tT0T\u0003I)\u0006\u000bR!U\u000b~Ke\nV#S-\u0006cul\u0015\u0011\u0002!U\u0003F)\u0011+F?\n\u000bEk\u0011%T\u0013j+UCAA\u001a!\u00159\u00171AA\u001b!\rY\u0017qG\u0005\u0004\u0003sa'aA%oi\u0006\tR\u000b\u0015#B)\u0016{&)\u0011+D\u0011NK%,\u0012\u0011\u0002\u001f\rcU)\u0011(F%~+e*\u0011\"M\u000b\u0012+\"!!\u0011\u0011\u000b\u001d\f\u0019!a\u0011\u0011\u0007-\f)%C\u0002\u0002H1\u0014qAQ8pY\u0016\fg.\u0001\tD\u0019\u0016\u000be*\u0012*`\u000b:\u000b%\tT#EA\u0005\u00112\tT#B\u001d\u0016\u0013v,\u0013(U\u000bJ3\u0016\tT0T\u0003M\u0019E*R!O\u000bJ{\u0016J\u0014+F%Z\u000bEjX*!\u00035i\u0015\tW0M\u001f\u001e{\u0016iR#`'\u0006qQ*\u0011-`\u0019>;u,Q$F?N\u0003\u0013aC'B1~cujR0O+6\u000bA\"T!Y?2{ui\u0018(V\u001b\u0002\nq\u0002T(D\u00032{6\u000bV(S\u000b~#\u0015JU\u000b\u0003\u00037\u0002RaZA/\u0003\u000fI1!a\u0018]\u0005My\u0005\u000f^5p]\u0006d7i\u001c8gS\u001e,e\u000e\u001e:z\u0003AaujQ!M?N#vJU#`\t&\u0013\u0006%\u0001\u000bM_\u000e\fGn\u0015;pe\u0016\u001cVM]5bY&TXM\u001d\t\u0004\u0003OBR\"A\u0001\u0003)1{7-\u00197Ti>\u0014XmU3sS\u0006d\u0017N_3s'\rA\u0012Q\u000e\t\u0004W\u0006=\u0014bAA9Y\nYQI\\;nKJ\fG/[8o)\t\t)'\u0001\u0003K'>sUCAA=!\u0011\tY(! \u000e\u0003aIA!a \u0002p\t)a+\u00197vK\u0006)!jU(OA\u0005A\u0001KU(U\u001f\n+f)A\u0005Q%>#vJQ+GA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0012\t\u0004m\u00065\u0015bAAHo\n1qJ\u00196fGR\fa\u0003T(D\u00032{6\u000bV(S\u000b~\u001bVIU%B\u0019&SVIU\u0001\u0018\u0019>\u001b\u0015\tT0T)>\u0013ViX*F%&\u000bE*\u0013.F%\u0002\nA#T!Y?2{5)\u0011'`\t&\u001b6jX+T\u0003\u001e+\u0015!F'B1~cujQ!M?\u0012K5kS0V'\u0006;U\tI\u0001\u0018\u0011&\u001bFk\u0014*Z?N+%KV#S?VKu\fV%U\u0019\u0016\u000b\u0001\u0004S%T)>\u0013\u0016lX*F%Z+%kX+J?RKE\u000bT#!\u0003YA\u0015j\u0015+P%f{6+\u0012*W\u000bJ{V+S0Q\u001fJ#\u0016a\u0006%J'R{%+W0T\u000bJ3VIU0V\u0013~\u0003vJ\u0015+!\u0003a1\u0015i\u0015+`\u0013:{\u0006KU(H%\u0016\u001b6k\u0018)B%NKejR\u0001\u001a\r\u0006\u001bFkX%O?B\u0013vj\u0012*F'N{\u0006+\u0011*T\u0013:;\u0005%\u0001\u000fF\u001d\u0012{VIV#O)~\u0013V\tU!S'\u0016{6\tS+O\u0017~\u001b\u0016JW#\u0002;\u0015sEiX#W\u000b:#vLU#Q\u0003J\u001bViX\"I+:[ulU%[\u000b\u0002\nQ%\u0012,F\u001dR{FjT$`%>cE*\u0013(H?6\u000b\u0005l\u0018$J\u0019\u0016\u001bv\fV(`%\u0016#\u0016)\u0013(\u0002M\u00153VI\u0014+`\u0019>;uLU(M\u0019&suiX'B1~3\u0015\nT#T?R{uLU#U\u0003&s\u0005%\u0001\u0013F-\u0016sEk\u0018'P\u000f~\u001bu*\u0014)B\u0007RKuJT0T\u0007>\u0013Vi\u0018+I%\u0016\u001b\u0006j\u0014'E+\t\t\t\fE\u0003h\u0003\u0007\t\u0019\fE\u0002l\u0003kK1!a.m\u0005\u0019!u.\u001e2mK\u0006)SIV#O)~cujR0D\u001f6\u0003\u0016i\u0011+J\u001f:{6kQ(S\u000b~#\u0006JU#T\u0011>cE\tI\u0001\u001b\tJKe+\u0012*`\u0019>;ul\u0011'F\u0003:+%kX#O\u0003\ncU\tR\u0001\u001c\tJKe+\u0012*`\u0019>;ul\u0011'F\u0003:+%kX#O\u0003\ncU\t\u0012\u0011\u0002)5\u000b\u0005l\u0018#S\u0013Z+%k\u0018'P\u000f~\u000bu)R0T\u0003Ui\u0015\tW0E%&3VIU0M\u001f\u001e{\u0016iR#`'\u0002\n1\u0004\u0012*J-\u0016\u0013v\fT(H?\u000ecU)\u0011(F%~Ke\nV#S-\u0006c\u0015\u0001\b#S\u0013Z+%k\u0018'P\u000f~\u001bE*R!O\u000bJ{\u0016J\u0014+F%Z\u000bE\nI\u0001\u001e\u0011&\u001bFk\u0014*Z?N+%KV#S?VKu,Q\"M'~+e*\u0011\"M\u000b\u0006q\u0002*S*U\u001fJKvlU#S-\u0016\u0013v,V%`\u0003\u000ec5kX#O\u0003\ncU\tI\u0001\u001d\u0011&\u001bFk\u0014*Z?N+%KV#S?VKu,\u0011#N\u0013:{\u0016i\u0011'T+\t\ty\rE\u0003h\u0003\u0007\t\t\u000e\u0005\u0004\u0002T\u0006u\u0017q\u0001\b\u0005\u0003+\fIN\u0004\u0003\u0002\u000e\u0005]\u0017\"A7\n\u0007\u0005mG.A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0017\u0011\u001d\u0002\u0004'\u0016\f(bAAnY\u0006i\u0002*S*U\u001fJKvlU#S-\u0016\u0013v,V%`\u0003\u0012k\u0015JT0B\u00072\u001b\u0006%A\u0012I\u0013N#vJU-`'\u0016\u0013f+\u0012*`+&{\u0016\tR'J\u001d~\u000b5\tT*`\u000fJ{U\u000bU*\u0002I!K5\u000bV(S3~\u001bVI\u0015,F%~+\u0016jX!E\u001b&su,Q\"M'~;%kT+Q'\u0002\n1\u0003S%T)>\u0013\u0016lX+J?6\u000b\u0005lX!Q!N\u000bA\u0003S%T)>\u0013\u0016lX+J?6\u000b\u0005lX!Q!N\u0003\u0013A\u0005(V\u001b~\u0013V\t\u0015'B3~#\u0006JU#B\tN\u000b1CT+N?J+\u0005\u000bT!Z?RC%+R!E'\u0002\nQCU#U\u0003&sU\tR0B!Bc\u0015jQ!U\u0013>s5+\u0001\fS\u000bR\u000b\u0015JT#E?\u0006\u0003\u0006\u000bT%D\u0003RKuJT*!\u0003!\u0001&k\u0014,J\t\u0016\u0013\u0016!\u0003)S\u001fZKE)\u0012*!\u0003AYUI\u0015\"F%>\u001bv,\u0012(B\u00052+E)A\tL\u000bJ\u0013UIU(T?\u0016s\u0015I\u0011'F\t\u0002\n!cS#S\u0005\u0016\u0013vjU0Q%&s5)\u0013)B\u0019\u0006\u00192*\u0012*C\u000bJ{5k\u0018)S\u0013:\u001b\u0015\nU!MA\u0005y1*\u0012*C\u000bJ{5kX&F3R\u000b%)\u0001\tL\u000bJ\u0013UIU(T?.+\u0015\fV!CA\u000592)V*U\u001f6{V\tW#D+R{%k\u0018'P\u000f~+&\u000bT\u0001\u0019\u0007V\u001bFkT'`\u000bb+5)\u0016+P%~cujR0V%2\u0003\u0013aL!Q!2KvlQ+T)>ku,\u0012-F\u0007V#vJU0M\u001f\u001e{VK\u0015'`)>{\u0016JT\"P\u001bBcU\tV#`\u0003B\u0003\u0016\u0001M!Q!2KvlQ+T)>ku,\u0012-F\u0007V#vJU0M\u001f\u001e{VK\u0015'`)>{\u0016JT\"P\u001bBcU\tV#`\u0003B\u0003\u0006%\u0001\u000bI3\n\u0013\u0016\nR0T)>\u0013ViX#O\u0003\ncU\tR\u0001\u0016\u0011f\u0013%+\u0013#`'R{%+R0F\u001d\u0006\u0013E*\u0012#!\u0003ei\u0015\tW0J\u001d~kU)T(S3~\u001bFk\u0014*F?V\u001b\u0016iR#\u000255\u000b\u0005lX%O?6+Uj\u0014*Z?N#vJU#`+N\u000bu)\u0012\u0011\u0002-!K(M]5e'R|'/\u001a#jg.\u0014\u0015mY6f]\u0012\u00042!a\u001aS\u0005YA\u0015P\u0019:jIN#xN]3ESN\\')Y2lK:$7c\u0001*\u0002nQ\u0011!qC\u0001\b\u0019\u00163V\t\u0014#C+\t\u0011\u0019\u0003\u0005\u0003\u0003&\u0005uT\"\u0001*\u0002\u00111+e+\u0012'E\u0005\u0002\nqAU(D\u0017N#%)\u0001\u0005S\u001f\u000e[5\u000b\u0012\"!\u0003eA\u0015L\u0011*J\t~\u001bFk\u0014*F?\u0012K5kS0C\u0003\u000e[UI\u0014#\u00025!K&IU%E?N#vJU#`\t&\u001b6j\u0018\"B\u0007.+e\n\u0012\u0011"
)
public final class History {
   public static ConfigEntry HYBRID_STORE_DISK_BACKEND() {
      return History$.MODULE$.HYBRID_STORE_DISK_BACKEND();
   }

   public static ConfigEntry MAX_IN_MEMORY_STORE_USAGE() {
      return History$.MODULE$.MAX_IN_MEMORY_STORE_USAGE();
   }

   public static ConfigEntry HYBRID_STORE_ENABLED() {
      return History$.MODULE$.HYBRID_STORE_ENABLED();
   }

   public static ConfigEntry APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP() {
      return History$.MODULE$.APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP();
   }

   public static OptionalConfigEntry CUSTOM_EXECUTOR_LOG_URL() {
      return History$.MODULE$.CUSTOM_EXECUTOR_LOG_URL();
   }

   public static OptionalConfigEntry KERBEROS_KEYTAB() {
      return History$.MODULE$.KERBEROS_KEYTAB();
   }

   public static OptionalConfigEntry KERBEROS_PRINCIPAL() {
      return History$.MODULE$.KERBEROS_PRINCIPAL();
   }

   public static ConfigEntry KERBEROS_ENABLED() {
      return History$.MODULE$.KERBEROS_ENABLED();
   }

   public static ConfigEntry PROVIDER() {
      return History$.MODULE$.PROVIDER();
   }

   public static ConfigEntry RETAINED_APPLICATIONS() {
      return History$.MODULE$.RETAINED_APPLICATIONS();
   }

   public static ConfigEntry NUM_REPLAY_THREADS() {
      return History$.MODULE$.NUM_REPLAY_THREADS();
   }

   public static ConfigEntry HISTORY_UI_MAX_APPS() {
      return History$.MODULE$.HISTORY_UI_MAX_APPS();
   }

   public static ConfigEntry HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS() {
      return History$.MODULE$.HISTORY_SERVER_UI_ADMIN_ACLS_GROUPS();
   }

   public static ConfigEntry HISTORY_SERVER_UI_ADMIN_ACLS() {
      return History$.MODULE$.HISTORY_SERVER_UI_ADMIN_ACLS();
   }

   public static ConfigEntry HISTORY_SERVER_UI_ACLS_ENABLE() {
      return History$.MODULE$.HISTORY_SERVER_UI_ACLS_ENABLE();
   }

   public static ConfigEntry DRIVER_LOG_CLEANER_INTERVAL() {
      return History$.MODULE$.DRIVER_LOG_CLEANER_INTERVAL();
   }

   public static ConfigEntry MAX_DRIVER_LOG_AGE_S() {
      return History$.MODULE$.MAX_DRIVER_LOG_AGE_S();
   }

   public static ConfigEntry DRIVER_LOG_CLEANER_ENABLED() {
      return History$.MODULE$.DRIVER_LOG_CLEANER_ENABLED();
   }

   public static ConfigEntry END_EVENT_REPARSE_CHUNK_SIZE() {
      return History$.MODULE$.END_EVENT_REPARSE_CHUNK_SIZE();
   }

   public static ConfigEntry FAST_IN_PROGRESS_PARSING() {
      return History$.MODULE$.FAST_IN_PROGRESS_PARSING();
   }

   public static ConfigEntry HISTORY_SERVER_UI_PORT() {
      return History$.MODULE$.HISTORY_SERVER_UI_PORT();
   }

   public static ConfigEntry HISTORY_SERVER_UI_TITLE() {
      return History$.MODULE$.HISTORY_SERVER_UI_TITLE();
   }

   public static ConfigEntry MAX_LOCAL_DISK_USAGE() {
      return History$.MODULE$.MAX_LOCAL_DISK_USAGE();
   }

   public static ConfigEntry LOCAL_STORE_SERIALIZER() {
      return History$.MODULE$.LOCAL_STORE_SERIALIZER();
   }

   public static OptionalConfigEntry LOCAL_STORE_DIR() {
      return History$.MODULE$.LOCAL_STORE_DIR();
   }

   public static ConfigEntry MAX_LOG_NUM() {
      return History$.MODULE$.MAX_LOG_NUM();
   }

   public static ConfigEntry MAX_LOG_AGE_S() {
      return History$.MODULE$.MAX_LOG_AGE_S();
   }

   public static ConfigEntry CLEANER_INTERVAL_S() {
      return History$.MODULE$.CLEANER_INTERVAL_S();
   }

   public static ConfigEntry CLEANER_ENABLED() {
      return History$.MODULE$.CLEANER_ENABLED();
   }

   public static ConfigEntry UPDATE_BATCHSIZE() {
      return History$.MODULE$.UPDATE_BATCHSIZE();
   }

   public static ConfigEntry UPDATE_INTERVAL_S() {
      return History$.MODULE$.UPDATE_INTERVAL_S();
   }

   public static ConfigEntry SAFEMODE_CHECK_INTERVAL_S() {
      return History$.MODULE$.SAFEMODE_CHECK_INTERVAL_S();
   }

   public static ConfigEntry HISTORY_LOG_DIR() {
      return History$.MODULE$.HISTORY_LOG_DIR();
   }

   public static String DEFAULT_LOG_DIR() {
      return History$.MODULE$.DEFAULT_LOG_DIR();
   }

   public static class LocalStoreSerializer$ extends Enumeration {
      public static final LocalStoreSerializer$ MODULE$ = new LocalStoreSerializer$();
      private static final Enumeration.Value JSON;
      private static final Enumeration.Value PROTOBUF;

      static {
         JSON = MODULE$.Value();
         PROTOBUF = MODULE$.Value();
      }

      public Enumeration.Value JSON() {
         return JSON;
      }

      public Enumeration.Value PROTOBUF() {
         return PROTOBUF;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(LocalStoreSerializer$.class);
      }
   }

   public static class HybridStoreDiskBackend$ extends Enumeration {
      public static final HybridStoreDiskBackend$ MODULE$ = new HybridStoreDiskBackend$();
      private static final Enumeration.Value LEVELDB;
      private static final Enumeration.Value ROCKSDB;

      static {
         LEVELDB = MODULE$.Value();
         ROCKSDB = MODULE$.Value();
      }

      public Enumeration.Value LEVELDB() {
         return LEVELDB;
      }

      public Enumeration.Value ROCKSDB() {
         return ROCKSDB;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(HybridStoreDiskBackend$.class);
      }
   }
}
