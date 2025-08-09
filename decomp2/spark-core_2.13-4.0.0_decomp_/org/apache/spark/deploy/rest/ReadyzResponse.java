package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}1Qa\u0001\u0003\u0001\u00119AQa\u0005\u0001\u0005\u0002UAQa\u0006\u0001\u0005Ra\u0011aBU3bIfT(+Z:q_:\u001cXM\u0003\u0002\u0006\r\u0005!!/Z:u\u0015\t9\u0001\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sON\u0011\u0001a\u0004\t\u0003!Ei\u0011\u0001B\u0005\u0003%\u0011\u0011!dU;c[&$(+Z:u!J|Go\\2pYJ+7\u000f]8og\u0016\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002-A\u0011\u0001\u0003A\u0001\u000bI>4\u0016\r\\5eCR,G#A\r\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\tUs\u0017\u000e\u001e"
)
public class ReadyzResponse extends SubmitRestProtocolResponse {
   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.success(), "success");
   }
}
