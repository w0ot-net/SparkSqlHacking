package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
   private final JcaJceHelper helper;
   private final boolean isForCRLCheck;

   public PKIXCertPathValidatorSpi() {
      this(false);
   }

   public PKIXCertPathValidatorSpi(boolean var1) {
      this.helper = new BCJcaJceHelper();
      this.isForCRLCheck = var1;
   }

   public CertPathValidatorResult engineValidate(CertPath var1, CertPathParameters var2) throws CertPathValidatorException, InvalidAlgorithmParameterException {
      PKIXExtendedParameters var3;
      if (var2 instanceof PKIXParameters) {
         PKIXExtendedParameters.Builder var4 = new PKIXExtendedParameters.Builder((PKIXParameters)var2);
         if (var2 instanceof ExtendedPKIXParameters) {
            ExtendedPKIXParameters var5 = (ExtendedPKIXParameters)var2;
            var4.setUseDeltasEnabled(var5.isUseDeltasEnabled());
            var4.setValidityModel(var5.getValidityModel());
         }

         var3 = var4.build();
      } else if (var2 instanceof PKIXExtendedBuilderParameters) {
         var3 = ((PKIXExtendedBuilderParameters)var2).getBaseParameters();
      } else {
         if (!(var2 instanceof PKIXExtendedParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
         }

         var3 = (PKIXExtendedParameters)var2;
      }

      if (var3.getTrustAnchors() == null) {
         throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
      } else {
         List var40 = var1.getCertificates();
         int var41 = var40.size();
         if (var40.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", (Throwable)null, var1, -1);
         } else {
            Date var6 = new Date();
            Date var7 = CertPathValidatorUtilities.getValidityDate(var3, var6);
            Set var8 = var3.getInitialPolicies();

            TrustAnchor var9;
            try {
               var9 = CertPathValidatorUtilities.findTrustAnchor((X509Certificate)var40.get(var40.size() - 1), var3.getTrustAnchors(), var3.getSigProvider());
               if (var9 == null) {
                  throw new CertPathValidatorException("Trust anchor for certification path not found.", (Throwable)null, var1, -1);
               }

               checkCertificate(var9.getTrustedCert());
            } catch (AnnotatedException var38) {
               throw new CertPathValidatorException(var38.getMessage(), var38.getUnderlyingException(), var1, var40.size() - 1);
            }

            var3 = (new PKIXExtendedParameters.Builder(var3)).setTrustAnchor(var9).build();
            int var11 = 0;
            ArrayList[] var13 = new ArrayList[var41 + 1];

            for(int var14 = 0; var14 < var13.length; ++var14) {
               var13[var14] = new ArrayList();
            }

            HashSet var43 = new HashSet();
            var43.add("2.5.29.32.0");
            PKIXPolicyNode var15 = new PKIXPolicyNode(new ArrayList(), 0, var43, (PolicyNode)null, new HashSet(), "2.5.29.32.0", false);
            var13[0].add(var15);
            PKIXNameConstraintValidator var16 = new PKIXNameConstraintValidator();
            HashSet var18 = new HashSet();
            int var17;
            if (var3.isExplicitPolicyRequired()) {
               var17 = 0;
            } else {
               var17 = var41 + 1;
            }

            int var19;
            if (var3.isAnyPolicyInhibited()) {
               var19 = 0;
            } else {
               var19 = var41 + 1;
            }

            int var20;
            if (var3.isPolicyMappingInhibited()) {
               var20 = 0;
            } else {
               var20 = var41 + 1;
            }

            X509Certificate var23 = var9.getTrustedCert();

            PublicKey var21;
            X500Name var22;
            try {
               if (var23 != null) {
                  var22 = PrincipalUtils.getSubjectPrincipal(var23);
                  var21 = var23.getPublicKey();
               } else {
                  var22 = PrincipalUtils.getCA(var9);
                  var21 = var9.getCAPublicKey();
               }
            } catch (RuntimeException var37) {
               throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", var37, var1, -1);
            }

            AlgorithmIdentifier var24 = null;

            try {
               var24 = CertPathValidatorUtilities.getAlgorithmIdentifier(var21);
            } catch (CertPathValidatorException var36) {
               throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", var36, var1, -1);
            }

            ASN1ObjectIdentifier var25 = var24.getAlgorithm();
            ASN1Encodable var26 = var24.getParameters();
            int var27 = var41;
            if (var3.getTargetConstraints() != null && !var3.getTargetConstraints().match((Certificate)((X509Certificate)var40.get(0)))) {
               throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", (Throwable)null, var1, 0);
            } else {
               List var28 = var3.getCertPathCheckers();
               Iterator var10 = var28.iterator();

               while(var10.hasNext()) {
                  ((PKIXCertPathChecker)var10.next()).init(false);
               }

               ProvCrlRevocationChecker var29;
               if (var3.isRevocationEnabled()) {
                  var29 = new ProvCrlRevocationChecker(this.helper);
               } else {
                  var29 = null;
               }

               X509Certificate var30 = null;

               for(var11 = var40.size() - 1; var11 >= 0; --var11) {
                  int var12 = var41 - var11;
                  var30 = (X509Certificate)var40.get(var11);
                  boolean var31 = var11 == var40.size() - 1;

                  try {
                     checkCertificate(var30);
                  } catch (AnnotatedException var34) {
                     throw new CertPathValidatorException(var34.getMessage(), var34.getUnderlyingException(), var1, var11);
                  }

                  RFC3280CertPathUtilities.processCertA(var1, var3, var7, var29, var11, var21, var31, var22, var23);
                  RFC3280CertPathUtilities.processCertBC(var1, var11, var16, this.isForCRLCheck);
                  PKIXPolicyNode var44 = RFC3280CertPathUtilities.processCertD(var1, var11, var18, var15, var13, var19, this.isForCRLCheck);
                  var15 = RFC3280CertPathUtilities.processCertE(var1, var11, var44);
                  RFC3280CertPathUtilities.processCertF(var1, var11, var15, var17);
                  if (var12 != var41) {
                     if (var30 != null && var30.getVersion() == 1) {
                        if (var12 != 1 || !var30.equals(var9.getTrustedCert())) {
                           throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", (Throwable)null, var1, var11);
                        }
                     } else {
                        RFC3280CertPathUtilities.prepareNextCertA(var1, var11);
                        var15 = RFC3280CertPathUtilities.prepareCertB(var1, var11, var13, var15, var20);
                        RFC3280CertPathUtilities.prepareNextCertG(var1, var11, var16);
                        var17 = RFC3280CertPathUtilities.prepareNextCertH1(var1, var11, var17);
                        var20 = RFC3280CertPathUtilities.prepareNextCertH2(var1, var11, var20);
                        var19 = RFC3280CertPathUtilities.prepareNextCertH3(var1, var11, var19);
                        var17 = RFC3280CertPathUtilities.prepareNextCertI1(var1, var11, var17);
                        var20 = RFC3280CertPathUtilities.prepareNextCertI2(var1, var11, var20);
                        var19 = RFC3280CertPathUtilities.prepareNextCertJ(var1, var11, var19);
                        RFC3280CertPathUtilities.prepareNextCertK(var1, var11);
                        var27 = RFC3280CertPathUtilities.prepareNextCertL(var1, var11, var27);
                        var27 = RFC3280CertPathUtilities.prepareNextCertM(var1, var11, var27);
                        RFC3280CertPathUtilities.prepareNextCertN(var1, var11);
                        Set var32 = var30.getCriticalExtensionOIDs();
                        if (var32 != null) {
                           var32 = new HashSet(var32);
                           var32.remove(RFC3280CertPathUtilities.KEY_USAGE);
                           var32.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                           var32.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                           var32.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                           var32.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                           var32.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                           var32.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                           var32.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                           var32.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                           var32.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                        } else {
                           var32 = new HashSet();
                        }

                        RFC3280CertPathUtilities.prepareNextCertO(var1, var11, var32, var28);
                        var23 = var30;
                        var22 = PrincipalUtils.getSubjectPrincipal(var30);

                        try {
                           var21 = CertPathValidatorUtilities.getNextWorkingKey(var1.getCertificates(), var11, this.helper);
                        } catch (CertPathValidatorException var35) {
                           throw new CertPathValidatorException("Next working key could not be retrieved.", var35, var1, var11);
                        }

                        var24 = CertPathValidatorUtilities.getAlgorithmIdentifier(var21);
                        var25 = var24.getAlgorithm();
                        var26 = var24.getParameters();
                     }
                  }
               }

               var17 = RFC3280CertPathUtilities.wrapupCertA(var17, var30);
               var17 = RFC3280CertPathUtilities.wrapupCertB(var1, var11 + 1, var17);
               Set var55 = var30.getCriticalExtensionOIDs();
               if (var55 != null) {
                  var55 = new HashSet(var55);
                  var55.remove(RFC3280CertPathUtilities.KEY_USAGE);
                  var55.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                  var55.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                  var55.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                  var55.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                  var55.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                  var55.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                  var55.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                  var55.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                  var55.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                  var55.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                  var55.remove(Extension.extendedKeyUsage.getId());
               } else {
                  var55 = new HashSet();
               }

               RFC3280CertPathUtilities.wrapupCertF(var1, var11 + 1, var28, var55);
               PKIXPolicyNode var58 = RFC3280CertPathUtilities.wrapupCertG(var1, var3, var8, var11 + 1, var13, var15, var18);
               if (var17 <= 0 && var58 == null) {
                  throw new CertPathValidatorException("Path processing failed on policy.", (Throwable)null, var1, var11);
               } else {
                  return new PKIXCertPathValidatorResult(var9, var58, var30.getPublicKey());
               }
            }
         }
      }
   }

   static void checkCertificate(X509Certificate var0) throws AnnotatedException {
      if (var0 instanceof BCX509Certificate) {
         RuntimeException var1 = null;

         try {
            if (null != ((BCX509Certificate)var0).getTBSCertificateNative()) {
               return;
            }
         } catch (RuntimeException var3) {
            var1 = var3;
         }

         throw new AnnotatedException("unable to process TBSCertificate", var1);
      } else {
         try {
            TBSCertificate.getInstance(var0.getTBSCertificate());
         } catch (CertificateEncodingException var4) {
            throw new AnnotatedException("unable to process TBSCertificate", var4);
         } catch (IllegalArgumentException var5) {
            throw new AnnotatedException(var5.getMessage());
         }
      }
   }
}
