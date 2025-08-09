package org.bouncycastle.jce.provider;

import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.PKIXCertPathChecker;
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXCertRevocationCheckerParameters;

class WrappedRevocationChecker implements PKIXCertRevocationChecker {
   private final PKIXCertPathChecker checker;

   public WrappedRevocationChecker(PKIXCertPathChecker var1) {
      this.checker = var1;
   }

   public void setParameter(String var1, Object var2) {
   }

   public void initialize(PKIXCertRevocationCheckerParameters var1) throws CertPathValidatorException {
      this.checker.init(false);
   }

   public void check(Certificate var1) throws CertPathValidatorException {
      this.checker.check(var1);
   }
}
