package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.ReasonFlags;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXCertRevocationCheckerParameters;
import org.bouncycastle.jcajce.PKIXCertStoreSelector;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;

class RFC3280CertPathUtilities {
   private static final Class revChkClass = ClassUtil.loadClass(RFC3280CertPathUtilities.class, "java.security.cert.PKIXRevocationChecker");
   public static final String CERTIFICATE_POLICIES;
   public static final String POLICY_MAPPINGS;
   public static final String INHIBIT_ANY_POLICY;
   public static final String ISSUING_DISTRIBUTION_POINT;
   public static final String FRESHEST_CRL;
   public static final String DELTA_CRL_INDICATOR;
   public static final String POLICY_CONSTRAINTS;
   public static final String BASIC_CONSTRAINTS;
   public static final String CRL_DISTRIBUTION_POINTS;
   public static final String SUBJECT_ALTERNATIVE_NAME;
   public static final String NAME_CONSTRAINTS;
   public static final String AUTHORITY_KEY_IDENTIFIER;
   public static final String KEY_USAGE;
   public static final String CRL_NUMBER;
   public static final String ANY_POLICY = "2.5.29.32.0";
   protected static final int KEY_CERT_SIGN = 5;
   protected static final int CRL_SIGN = 6;
   protected static final String[] crlReasons;

   protected static void processCRLB2(DistributionPoint var0, Object var1, X509CRL var2) throws AnnotatedException {
      Object var3 = null;

      try {
         var15 = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(var2, ISSUING_DISTRIBUTION_POINT));
      } catch (Exception var13) {
         throw new AnnotatedException("Issuing distribution point extension could not be decoded.", var13);
      }

      if (var15 != null) {
         if (var15.getDistributionPoint() != null) {
            DistributionPointName var4 = IssuingDistributionPoint.getInstance(var15).getDistributionPoint();
            ArrayList var5 = new ArrayList();
            if (var4.getType() == 0) {
               GeneralName[] var6 = GeneralNames.getInstance(var4.getName()).getNames();

               for(int var7 = 0; var7 < var6.length; ++var7) {
                  var5.add(var6[var7]);
               }
            }

            if (var4.getType() == 1) {
               ASN1EncodableVector var19 = new ASN1EncodableVector();

               try {
                  Enumeration var21 = ASN1Sequence.getInstance(PrincipalUtils.getIssuerPrincipal(var2)).getObjects();

                  while(var21.hasMoreElements()) {
                     var19.add((ASN1Encodable)var21.nextElement());
                  }
               } catch (Exception var14) {
                  throw new AnnotatedException("Could not read CRL issuer.", var14);
               }

               var19.add(var4.getName());
               var5.add(new GeneralName(X500Name.getInstance(new DERSequence(var19))));
            }

            boolean var20 = false;
            if (var0.getDistributionPoint() != null) {
               var4 = var0.getDistributionPoint();
               GeneralName[] var23 = null;
               if (var4.getType() == 0) {
                  var23 = GeneralNames.getInstance(var4.getName()).getNames();
               }

               if (var4.getType() == 1) {
                  if (var0.getCRLIssuer() != null) {
                     var23 = var0.getCRLIssuer().getNames();
                  } else {
                     var23 = new GeneralName[1];

                     try {
                        var23[0] = new GeneralName(PrincipalUtils.getEncodedIssuerPrincipal(var1));
                     } catch (Exception var12) {
                        throw new AnnotatedException("Could not read certificate issuer.", var12);
                     }
                  }

                  for(int var24 = 0; var24 < var23.length; ++var24) {
                     Enumeration var9 = ASN1Sequence.getInstance(var23[var24].getName().toASN1Primitive()).getObjects();
                     ASN1EncodableVector var10 = new ASN1EncodableVector();

                     while(var9.hasMoreElements()) {
                        var10.add((ASN1Encodable)var9.nextElement());
                     }

                     var10.add(var4.getName());
                     var23[var24] = new GeneralName(X500Name.getInstance(new DERSequence(var10)));
                  }
               }

               if (var23 != null) {
                  for(int var25 = 0; var25 < var23.length; ++var25) {
                     if (var5.contains(var23[var25])) {
                        var20 = true;
                        break;
                     }
                  }
               }

               if (!var20) {
                  throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
               }
            } else {
               if (var0.getCRLIssuer() == null) {
                  throw new AnnotatedException("Either the cRLIssuer or the distributionPoint field must be contained in DistributionPoint.");
               }

               GeneralName[] var22 = var0.getCRLIssuer().getNames();

               for(int var8 = 0; var8 < var22.length; ++var8) {
                  if (var5.contains(var22[var8])) {
                     var20 = true;
                     break;
                  }
               }

               if (!var20) {
                  throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
               }
            }
         }

         Object var17 = null;

         try {
            var18 = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Extension)var1, BASIC_CONSTRAINTS));
         } catch (Exception var11) {
            throw new AnnotatedException("Basic constraints extension could not be decoded.", var11);
         }

         if (var1 instanceof X509Certificate) {
            if (var15.onlyContainsUserCerts() && var18 != null && var18.isCA()) {
               throw new AnnotatedException("CA Cert CRL only contains user certificates.");
            }

            if (var15.onlyContainsCACerts() && (var18 == null || !var18.isCA())) {
               throw new AnnotatedException("End CRL only contains CA certificates.");
            }
         }

         if (var15.onlyContainsAttributeCerts()) {
            throw new AnnotatedException("onlyContainsAttributeCerts boolean is asserted.");
         }
      }

   }

   protected static void processCRLB1(DistributionPoint var0, Object var1, X509CRL var2) throws AnnotatedException {
      ASN1Primitive var3 = CertPathValidatorUtilities.getExtensionValue(var2, ISSUING_DISTRIBUTION_POINT);
      boolean var4 = false;
      if (var3 != null && IssuingDistributionPoint.getInstance(var3).isIndirectCRL()) {
         var4 = true;
      }

      byte[] var5;
      try {
         var5 = PrincipalUtils.getIssuerPrincipal(var2).getEncoded();
      } catch (IOException var11) {
         throw new AnnotatedException("Exception encoding CRL issuer: " + var11.getMessage(), var11);
      }

      boolean var6 = false;
      if (var0.getCRLIssuer() != null) {
         GeneralName[] var7 = var0.getCRLIssuer().getNames();

         for(int var8 = 0; var8 < var7.length; ++var8) {
            if (var7[var8].getTagNo() == 4) {
               try {
                  if (Arrays.areEqual(var7[var8].getName().toASN1Primitive().getEncoded(), var5)) {
                     var6 = true;
                  }
               } catch (IOException var10) {
                  throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", var10);
               }
            }
         }

         if (var6 && !var4) {
            throw new AnnotatedException("Distribution point contains cRLIssuer field but CRL is not indirect.");
         }

         if (!var6) {
            throw new AnnotatedException("CRL issuer of CRL does not match CRL issuer of distribution point.");
         }
      } else if (PrincipalUtils.getIssuerPrincipal(var2).equals(PrincipalUtils.getEncodedIssuerPrincipal(var1))) {
         var6 = true;
      }

      if (!var6) {
         throw new AnnotatedException("Cannot find matching CRL issuer for certificate.");
      }
   }

   protected static ReasonsMask processCRLD(X509CRL var0, DistributionPoint var1) throws AnnotatedException {
      Object var2 = null;

      try {
         var5 = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(var0, ISSUING_DISTRIBUTION_POINT));
      } catch (Exception var4) {
         throw new AnnotatedException("Issuing distribution point extension could not be decoded.", var4);
      }

      if (var5 != null && var5.getOnlySomeReasons() != null && var1.getReasons() != null) {
         return (new ReasonsMask(var1.getReasons())).intersect(new ReasonsMask(var5.getOnlySomeReasons()));
      } else {
         return (var5 == null || var5.getOnlySomeReasons() == null) && var1.getReasons() == null ? ReasonsMask.allReasons : (var1.getReasons() == null ? ReasonsMask.allReasons : new ReasonsMask(var1.getReasons())).intersect(var5 == null ? ReasonsMask.allReasons : new ReasonsMask(var5.getOnlySomeReasons()));
      }
   }

   protected static Set processCRLF(X509CRL var0, Object var1, X509Certificate var2, PublicKey var3, PKIXExtendedParameters var4, List var5, JcaJceHelper var6) throws AnnotatedException {
      X509CertSelector var7 = new X509CertSelector();

      try {
         byte[] var8 = PrincipalUtils.getIssuerPrincipal(var0).getEncoded();
         var7.setSubject(var8);
      } catch (IOException var23) {
         throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate for CRL could not be set.", var23);
      }

      PKIXCertStoreSelector var24 = (new PKIXCertStoreSelector.Builder(var7)).build();
      LinkedHashSet var9 = new LinkedHashSet();

      try {
         CertPathValidatorUtilities.findCertificates(var9, var24, var4.getCertificateStores());
         CertPathValidatorUtilities.findCertificates(var9, var24, var4.getCertStores());
      } catch (AnnotatedException var22) {
         throw new AnnotatedException("Issuer certificate for CRL cannot be searched.", var22);
      }

      var9.add(var2);
      Iterator var10 = var9.iterator();
      ArrayList var11 = new ArrayList();
      ArrayList var12 = new ArrayList();

      while(var10.hasNext()) {
         X509Certificate var13 = (X509Certificate)var10.next();
         if (var13.equals(var2)) {
            var11.add(var13);
            var12.add(var3);
         } else {
            try {
               Object var14 = revChkClass != null ? new PKIXCertPathBuilderSpi_8(true) : new PKIXCertPathBuilderSpi(true);
               X509CertSelector var15 = new X509CertSelector();
               var15.setCertificate(var13);
               PKIXExtendedParameters.Builder var16 = (new PKIXExtendedParameters.Builder(var4)).setTargetConstraints((new PKIXCertStoreSelector.Builder(var15)).build());
               if (var5.contains(var13)) {
                  var16.setRevocationEnabled(false);
               } else {
                  var16.setRevocationEnabled(true);
               }

               PKIXExtendedBuilderParameters var17 = (new PKIXExtendedBuilderParameters.Builder(var16.build())).build();
               List var18 = ((CertPathBuilderSpi)var14).engineBuild(var17).getCertPath().getCertificates();
               var11.add(var13);
               var12.add(CertPathValidatorUtilities.getNextWorkingKey(var18, 0, var6));
            } catch (CertPathBuilderException var19) {
               throw new AnnotatedException("CertPath for CRL signer failed to validate.", var19);
            } catch (CertPathValidatorException var20) {
               throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", var20);
            } catch (Exception var21) {
               throw new AnnotatedException(var21.getMessage());
            }
         }
      }

      HashSet var25 = new HashSet();
      AnnotatedException var26 = null;

      for(int var27 = 0; var27 < var11.size(); ++var27) {
         X509Certificate var28 = (X509Certificate)var11.get(var27);
         boolean[] var29 = var28.getKeyUsage();
         if (var29 == null) {
            if (Properties.isOverrideSet("org.bouncycastle.x509.allow_ca_without_crl_sign", true)) {
               var25.add(var12.get(var27));
            } else {
               var26 = new AnnotatedException("No key usage extension on CRL issuer certificate.");
            }
         } else if (var29.length > 6 && var29[6]) {
            var25.add(var12.get(var27));
         } else {
            var26 = new AnnotatedException("Issuer certificate key usage extension does not permit CRL signing.");
         }
      }

      if (var25.isEmpty() && var26 == null) {
         throw new AnnotatedException("Cannot find a valid issuer certificate.");
      } else if (var25.isEmpty() && var26 != null) {
         throw var26;
      } else {
         return var25;
      }
   }

   protected static PublicKey processCRLG(X509CRL var0, Set var1) throws AnnotatedException {
      Exception var2 = null;

      for(PublicKey var4 : var1) {
         try {
            var0.verify(var4);
            return var4;
         } catch (Exception var6) {
            var2 = var6;
         }
      }

      throw new AnnotatedException("Cannot verify CRL.", var2);
   }

   protected static X509CRL processCRLH(Set var0, PublicKey var1) throws AnnotatedException {
      Exception var2 = null;

      for(X509CRL var4 : var0) {
         try {
            var4.verify(var1);
            return var4;
         } catch (Exception var6) {
            var2 = var6;
         }
      }

      if (var2 != null) {
         throw new AnnotatedException("Cannot verify delta CRL.", var2);
      } else {
         return null;
      }
   }

   protected static void processCRLC(X509CRL var0, X509CRL var1, PKIXExtendedParameters var2) throws AnnotatedException {
      if (var0 != null) {
         if (var0.hasUnsupportedCriticalExtension()) {
            throw new AnnotatedException("delta CRL has unsupported critical extensions");
         } else {
            Object var3 = null;

            try {
               var13 = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(var1, ISSUING_DISTRIBUTION_POINT));
            } catch (Exception var12) {
               throw new AnnotatedException("Issuing distribution point extension could not be decoded.", var12);
            }

            if (var2.isUseDeltasEnabled()) {
               if (!PrincipalUtils.getIssuerPrincipal(var0).equals(PrincipalUtils.getIssuerPrincipal(var1))) {
                  throw new AnnotatedException("Complete CRL issuer does not match delta CRL issuer.");
               }

               Object var4 = null;

               try {
                  var14 = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(var0, ISSUING_DISTRIBUTION_POINT));
               } catch (Exception var11) {
                  throw new AnnotatedException("Issuing distribution point extension from delta CRL could not be decoded.", var11);
               }

               boolean var5 = false;
               if (var13 == null) {
                  if (var14 == null) {
                     var5 = true;
                  }
               } else if (var13.equals(var14)) {
                  var5 = true;
               }

               if (!var5) {
                  throw new AnnotatedException("Issuing distribution point extension from delta CRL and complete CRL does not match.");
               }

               Object var6 = null;

               try {
                  var15 = CertPathValidatorUtilities.getExtensionValue(var1, AUTHORITY_KEY_IDENTIFIER);
               } catch (AnnotatedException var10) {
                  throw new AnnotatedException("Authority key identifier extension could not be extracted from complete CRL.", var10);
               }

               Object var7 = null;

               try {
                  var16 = CertPathValidatorUtilities.getExtensionValue(var0, AUTHORITY_KEY_IDENTIFIER);
               } catch (AnnotatedException var9) {
                  throw new AnnotatedException("Authority key identifier extension could not be extracted from delta CRL.", var9);
               }

               if (var15 == null) {
                  throw new AnnotatedException("CRL authority key identifier is null.");
               }

               if (var16 == null) {
                  throw new AnnotatedException("Delta CRL authority key identifier is null.");
               }

               if (!var15.equals(var16)) {
                  throw new AnnotatedException("Delta CRL authority key identifier does not match complete CRL authority key identifier.");
               }
            }

         }
      }
   }

   protected static void processCRLI(Date var0, X509CRL var1, Object var2, CertStatus var3, PKIXExtendedParameters var4) throws AnnotatedException {
      if (var4.isUseDeltasEnabled() && var1 != null) {
         CertPathValidatorUtilities.getCertStatus(var0, var1, var2, var3);
      }

   }

   protected static void processCRLJ(Date var0, X509CRL var1, Object var2, CertStatus var3) throws AnnotatedException {
      if (var3.getCertStatus() == 11) {
         CertPathValidatorUtilities.getCertStatus(var0, var1, var2, var3);
      }

   }

   protected static PKIXPolicyNode prepareCertB(CertPath var0, int var1, List[] var2, PKIXPolicyNode var3, int var4) throws CertPathValidatorException {
      List var5 = var0.getCertificates();
      X509Certificate var6 = (X509Certificate)var5.get(var1);
      int var7 = var5.size();
      int var8 = var7 - var1;
      Object var9 = null;

      try {
         var29 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var6, POLICY_MAPPINGS));
      } catch (AnnotatedException var28) {
         throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", var28, var0, var1);
      }

      PKIXPolicyNode var10 = var3;
      if (var29 != null) {
         ASN1Sequence var11 = var29;
         HashMap var12 = new HashMap();
         HashSet var13 = new HashSet();

         for(int var14 = 0; var14 < var11.size(); ++var14) {
            ASN1Sequence var15 = (ASN1Sequence)var11.getObjectAt(var14);
            String var16 = ((ASN1ObjectIdentifier)var15.getObjectAt(0)).getId();
            String var17 = ((ASN1ObjectIdentifier)var15.getObjectAt(1)).getId();
            if (!var12.containsKey(var16)) {
               HashSet var18 = new HashSet();
               var18.add(var17);
               var12.put(var16, var18);
               var13.add(var16);
            } else {
               Set var37 = (Set)var12.get(var16);
               var37.add(var17);
            }
         }

         for(String var31 : var13) {
            if (var4 > 0) {
               boolean var33 = false;

               for(PKIXPolicyNode var39 : var2[var8]) {
                  if (var39.getValidPolicy().equals(var31)) {
                     var33 = true;
                     var39.expectedPolicies = (Set)var12.get(var31);
                     break;
                  }
               }

               if (!var33) {
                  for(PKIXPolicyNode var40 : var2[var8]) {
                     if ("2.5.29.32.0".equals(var40.getValidPolicy())) {
                        Set var41 = null;
                        ASN1Sequence var42 = null;

                        try {
                           var42 = (ASN1Sequence)CertPathValidatorUtilities.getExtensionValue(var6, CERTIFICATE_POLICIES);
                        } catch (AnnotatedException var27) {
                           throw new ExtCertPathValidatorException("Certificate policies extension could not be decoded.", var27, var0, var1);
                        }

                        Enumeration var44 = var42.getObjects();

                        while(var44.hasMoreElements()) {
                           Object var45 = null;

                           try {
                              var46 = PolicyInformation.getInstance(var44.nextElement());
                           } catch (Exception var26) {
                              throw new CertPathValidatorException("Policy information could not be decoded.", var26, var0, var1);
                           }

                           if ("2.5.29.32.0".equals(var46.getPolicyIdentifier().getId())) {
                              try {
                                 var41 = CertPathValidatorUtilities.getQualifierSet(var46.getPolicyQualifiers());
                                 break;
                              } catch (CertPathValidatorException var25) {
                                 throw new ExtCertPathValidatorException("Policy qualifier info set could not be decoded.", var25, var0, var1);
                              }
                           }
                        }

                        boolean var47 = false;
                        if (var6.getCriticalExtensionOIDs() != null) {
                           var47 = var6.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES);
                        }

                        PKIXPolicyNode var23 = (PKIXPolicyNode)var40.getParent();
                        if ("2.5.29.32.0".equals(var23.getValidPolicy())) {
                           PKIXPolicyNode var24 = new PKIXPolicyNode(new ArrayList(), var8, (Set)var12.get(var31), var23, var41, var31, var47);
                           var23.addChild(var24);
                           var2[var8].add(var24);
                        }
                        break;
                     }
                  }
               }
            } else if (var4 <= 0) {
               Iterator var32 = var2[var8].iterator();

               while(var32.hasNext()) {
                  PKIXPolicyNode var34 = (PKIXPolicyNode)var32.next();
                  if (var34.getValidPolicy().equals(var31)) {
                     PKIXPolicyNode var38 = (PKIXPolicyNode)var34.getParent();
                     var38.removeChild(var34);
                     var32.remove();

                     for(int var19 = var8 - 1; var19 >= 0; --var19) {
                        List var20 = var2[var19];

                        for(int var21 = 0; var21 < var20.size(); ++var21) {
                           PKIXPolicyNode var22 = (PKIXPolicyNode)var20.get(var21);
                           if (!var22.hasChildren()) {
                              var10 = CertPathValidatorUtilities.removePolicyNode(var10, var2, var22);
                              if (var10 == null) {
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var10;
   }

   protected static void prepareNextCertA(CertPath var0, int var1) throws CertPathValidatorException {
      List var2 = var0.getCertificates();
      X509Certificate var3 = (X509Certificate)var2.get(var1);
      Object var4 = null;

      try {
         var12 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var3, POLICY_MAPPINGS));
      } catch (AnnotatedException var11) {
         throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", var11, var0, var1);
      }

      if (var12 != null) {
         ASN1Sequence var5 = var12;

         for(int var6 = 0; var6 < var5.size(); ++var6) {
            Object var7 = null;
            Object var8 = null;

            try {
               ASN1Sequence var9 = ASN1Sequence.getInstance(var5.getObjectAt(var6));
               var13 = ASN1ObjectIdentifier.getInstance(var9.getObjectAt(0));
               var14 = ASN1ObjectIdentifier.getInstance(var9.getObjectAt(1));
            } catch (Exception var10) {
               throw new ExtCertPathValidatorException("Policy mappings extension contents could not be decoded.", var10, var0, var1);
            }

            if ("2.5.29.32.0".equals(var13.getId())) {
               throw new CertPathValidatorException("IssuerDomainPolicy is anyPolicy", (Throwable)null, var0, var1);
            }

            if ("2.5.29.32.0".equals(var14.getId())) {
               throw new CertPathValidatorException("SubjectDomainPolicy is anyPolicy", (Throwable)null, var0, var1);
            }
         }
      }

   }

   protected static void processCertF(CertPath var0, int var1, PKIXPolicyNode var2, int var3) throws CertPathValidatorException {
      if (var3 <= 0 && var2 == null) {
         throw new ExtCertPathValidatorException("No valid policy tree found when one expected.", (Throwable)null, var0, var1);
      }
   }

   protected static PKIXPolicyNode processCertE(CertPath var0, int var1, PKIXPolicyNode var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      Object var5 = null;

      try {
         var8 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, CERTIFICATE_POLICIES));
      } catch (AnnotatedException var7) {
         throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", var7, var0, var1);
      }

      if (var8 == null) {
         var2 = null;
      }

      return var2;
   }

   protected static void processCertBC(CertPath var0, int var1, PKIXNameConstraintValidator var2, boolean var3) throws CertPathValidatorException {
      List var4 = var0.getCertificates();
      X509Certificate var5 = (X509Certificate)var4.get(var1);
      int var6 = var4.size();
      int var7 = var6 - var1;
      if (!CertPathValidatorUtilities.isSelfIssued(var5) || var7 >= var6 && !var3) {
         X500Name var8 = PrincipalUtils.getSubjectPrincipal(var5);

         ASN1Sequence var9;
         try {
            var9 = ASN1Sequence.getInstance(var8);
         } catch (Exception var21) {
            throw new CertPathValidatorException("Exception extracting subject name when checking subtrees.", var21, var0, var1);
         }

         try {
            var2.checkPermittedDN(var9);
            var2.checkExcludedDN(var9);
         } catch (PKIXNameConstraintValidatorException var20) {
            throw new CertPathValidatorException("Subtree check for certificate subject failed.", var20, var0, var1);
         }

         GeneralNames var10 = null;

         try {
            var10 = GeneralNames.getInstance(CertPathValidatorUtilities.getExtensionValue(var5, SUBJECT_ALTERNATIVE_NAME));
         } catch (Exception var19) {
            throw new CertPathValidatorException("Subject alternative name extension could not be decoded.", var19, var0, var1);
         }

         RDN[] var11 = X500Name.getInstance(var9).getRDNs(BCStyle.EmailAddress);

         for(int var12 = 0; var12 != var11.length; ++var12) {
            String var13 = ((ASN1String)var11[var12].getFirst().getValue()).getString();
            GeneralName var14 = new GeneralName(1, var13);

            try {
               var2.checkPermitted(var14);
               var2.checkExcluded(var14);
            } catch (PKIXNameConstraintValidatorException var18) {
               throw new CertPathValidatorException("Subtree check for certificate subject alternative email failed.", var18, var0, var1);
            }
         }

         if (var10 != null) {
            Object var23 = null;

            try {
               var24 = var10.getNames();
            } catch (Exception var17) {
               throw new CertPathValidatorException("Subject alternative name contents could not be decoded.", var17, var0, var1);
            }

            for(int var25 = 0; var25 < var24.length; ++var25) {
               try {
                  var2.checkPermitted(var24[var25]);
                  var2.checkExcluded(var24[var25]);
               } catch (PKIXNameConstraintValidatorException var16) {
                  throw new CertPathValidatorException("Subtree check for certificate subject alternative name failed.", var16, var0, var1);
               }
            }
         }
      }

   }

   protected static PKIXPolicyNode processCertD(CertPath var0, int var1, Set var2, PKIXPolicyNode var3, List[] var4, int var5, boolean var6) throws CertPathValidatorException {
      List var7 = var0.getCertificates();
      X509Certificate var8 = (X509Certificate)var7.get(var1);
      int var9 = var7.size();
      int var10 = var9 - var1;
      ASN1Sequence var11 = null;

      try {
         var11 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var8, CERTIFICATE_POLICIES));
      } catch (AnnotatedException var27) {
         throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", var27, var0, var1);
      }

      if (var11 != null && var3 != null) {
         Enumeration var12 = var11.getObjects();
         HashSet var13 = new HashSet();

         while(var12.hasMoreElements()) {
            PolicyInformation var14 = PolicyInformation.getInstance(var12.nextElement());
            ASN1ObjectIdentifier var15 = var14.getPolicyIdentifier();
            var13.add(var15.getId());
            if (!"2.5.29.32.0".equals(var15.getId())) {
               Object var16 = null;

               try {
                  var37 = CertPathValidatorUtilities.getQualifierSet(var14.getPolicyQualifiers());
               } catch (CertPathValidatorException var26) {
                  throw new ExtCertPathValidatorException("Policy qualifier info set could not be build.", var26, var0, var1);
               }

               boolean var17 = CertPathValidatorUtilities.processCertD1i(var10, var4, var15, var37);
               if (!var17) {
                  CertPathValidatorUtilities.processCertD1ii(var10, var4, var15, var37);
               }
            }
         }

         if (!var2.isEmpty() && !var2.contains("2.5.29.32.0")) {
            Iterator var30 = var2.iterator();
            HashSet var33 = new HashSet();

            while(var30.hasNext()) {
               Object var38 = var30.next();
               if (var13.contains(var38)) {
                  var33.add(var38);
               }
            }

            var2.clear();
            var2.addAll(var33);
         } else {
            var2.clear();
            var2.addAll(var13);
         }

         if (var5 > 0 || (var10 < var9 || var6) && CertPathValidatorUtilities.isSelfIssued(var8)) {
            var12 = var11.getObjects();

            while(var12.hasMoreElements()) {
               PolicyInformation var31 = PolicyInformation.getInstance(var12.nextElement());
               if ("2.5.29.32.0".equals(var31.getPolicyIdentifier().getId())) {
                  Set var34 = CertPathValidatorUtilities.getQualifierSet(var31.getPolicyQualifiers());
                  List var39 = var4[var10 - 1];

                  label125:
                  for(int var42 = 0; var42 < var39.size(); ++var42) {
                     PKIXPolicyNode var18 = (PKIXPolicyNode)var39.get(var42);
                     Iterator var19 = var18.getExpectedPolicies().iterator();

                     while(true) {
                        String var21;
                        while(true) {
                           if (!var19.hasNext()) {
                              continue label125;
                           }

                           Object var20 = var19.next();
                           if (var20 instanceof String) {
                              var21 = (String)var20;
                              break;
                           }

                           if (var20 instanceof ASN1ObjectIdentifier) {
                              var21 = ((ASN1ObjectIdentifier)var20).getId();
                              break;
                           }
                        }

                        boolean var22 = false;
                        Iterator var23 = var18.getChildren();

                        while(var23.hasNext()) {
                           PKIXPolicyNode var24 = (PKIXPolicyNode)var23.next();
                           if (var21.equals(var24.getValidPolicy())) {
                              var22 = true;
                           }
                        }

                        if (!var22) {
                           HashSet var48 = new HashSet();
                           var48.add(var21);
                           PKIXPolicyNode var25 = new PKIXPolicyNode(new ArrayList(), var10, var48, var18, var34, var21, false);
                           var18.addChild(var25);
                           var4[var10].add(var25);
                        }
                     }
                  }
                  break;
               }
            }
         }

         PKIXPolicyNode var32 = var3;

         for(int var35 = var10 - 1; var35 >= 0; --var35) {
            List var40 = var4[var35];

            for(int var43 = 0; var43 < var40.size(); ++var43) {
               PKIXPolicyNode var45 = (PKIXPolicyNode)var40.get(var43);
               if (!var45.hasChildren()) {
                  var32 = CertPathValidatorUtilities.removePolicyNode(var32, var4, var45);
                  if (var32 == null) {
                     break;
                  }
               }
            }
         }

         Set var36 = var8.getCriticalExtensionOIDs();
         if (var36 != null) {
            boolean var41 = var36.contains(CERTIFICATE_POLICIES);
            List var44 = var4[var10];

            for(int var46 = 0; var46 < var44.size(); ++var46) {
               PKIXPolicyNode var47 = (PKIXPolicyNode)var44.get(var46);
               var47.setCritical(var41);
            }
         }

         return var32;
      } else {
         return null;
      }
   }

   protected static void processCertA(CertPath var0, PKIXExtendedParameters var1, Date var2, PKIXCertRevocationChecker var3, int var4, PublicKey var5, boolean var6, X500Name var7, X509Certificate var8) throws CertPathValidatorException {
      List var9 = var0.getCertificates();
      X509Certificate var10 = (X509Certificate)var9.get(var4);
      if (!var6) {
         try {
            CertPathValidatorUtilities.verifyX509Certificate(var10, var5, var1.getSigProvider());
         } catch (GeneralSecurityException var16) {
            throw new ExtCertPathValidatorException("Could not validate certificate signature.", var16, var0, var4);
         }
      }

      Date var11;
      try {
         var11 = CertPathValidatorUtilities.getValidCertDateFromValidityModel(var2, var1.getValidityModel(), var0, var4);
      } catch (AnnotatedException var15) {
         throw new ExtCertPathValidatorException("Could not validate time of certificate.", var15, var0, var4);
      }

      try {
         var10.checkValidity(var11);
      } catch (CertificateExpiredException var13) {
         throw new ExtCertPathValidatorException("Could not validate certificate: " + var13.getMessage(), var13, var0, var4);
      } catch (CertificateNotYetValidException var14) {
         throw new ExtCertPathValidatorException("Could not validate certificate: " + var14.getMessage(), var14, var0, var4);
      }

      if (var3 != null) {
         var3.initialize(new PKIXCertRevocationCheckerParameters(var1, var11, var0, var4, var8, var5));
         var3.check(var10);
      }

      X500Name var12 = PrincipalUtils.getIssuerPrincipal(var10);
      if (!var12.equals(var7)) {
         throw new ExtCertPathValidatorException("IssuerName(" + var12 + ") does not match SubjectName(" + var7 + ") of signing certificate.", (Throwable)null, var0, var4);
      }
   }

   protected static int prepareNextCertI1(CertPath var0, int var1, int var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      ASN1Sequence var5 = null;

      try {
         var5 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, POLICY_CONSTRAINTS));
      } catch (Exception var9) {
         throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", var9, var0, var1);
      }

      if (var5 != null) {
         Enumeration var7 = var5.getObjects();

         while(var7.hasMoreElements()) {
            try {
               ASN1TaggedObject var8 = ASN1TaggedObject.getInstance(var7.nextElement());
               if (var8.getTagNo() == 0) {
                  int var6 = ASN1Integer.getInstance(var8, false).intValueExact();
                  if (var6 < var2) {
                     return var6;
                  }
                  break;
               }
            } catch (IllegalArgumentException var10) {
               throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", var10, var0, var1);
            }
         }
      }

      return var2;
   }

   protected static int prepareNextCertI2(CertPath var0, int var1, int var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      ASN1Sequence var5 = null;

      try {
         var5 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, POLICY_CONSTRAINTS));
      } catch (Exception var9) {
         throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", var9, var0, var1);
      }

      if (var5 != null) {
         Enumeration var7 = var5.getObjects();

         while(var7.hasMoreElements()) {
            try {
               ASN1TaggedObject var8 = ASN1TaggedObject.getInstance(var7.nextElement());
               if (var8.getTagNo() == 1) {
                  int var6 = ASN1Integer.getInstance(var8, false).intValueExact();
                  if (var6 < var2) {
                     return var6;
                  }
                  break;
               }
            } catch (IllegalArgumentException var10) {
               throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", var10, var0, var1);
            }
         }
      }

      return var2;
   }

   protected static void prepareNextCertG(CertPath var0, int var1, PKIXNameConstraintValidator var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      NameConstraints var5 = null;

      try {
         ASN1Sequence var6 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, NAME_CONSTRAINTS));
         if (var6 != null) {
            var5 = NameConstraints.getInstance(var6);
         }
      } catch (Exception var12) {
         throw new ExtCertPathValidatorException("Name constraints extension could not be decoded.", var12, var0, var1);
      }

      if (var5 != null) {
         GeneralSubtree[] var13 = var5.getPermittedSubtrees();
         if (var13 != null) {
            try {
               var2.intersectPermittedSubtree(var13);
            } catch (Exception var11) {
               throw new ExtCertPathValidatorException("Permitted subtrees cannot be build from name constraints extension.", var11, var0, var1);
            }
         }

         GeneralSubtree[] var7 = var5.getExcludedSubtrees();
         if (var7 != null) {
            for(int var8 = 0; var8 != var7.length; ++var8) {
               try {
                  var2.addExcludedSubtree(var7[var8]);
               } catch (Exception var10) {
                  throw new ExtCertPathValidatorException("Excluded subtrees cannot be build from name constraints extension.", var10, var0, var1);
               }
            }
         }
      }

   }

   private static void checkCRL(PKIXCertRevocationCheckerParameters var0, DistributionPoint var1, PKIXExtendedParameters var2, Date var3, Date var4, X509Certificate var5, X509Certificate var6, PublicKey var7, CertStatus var8, ReasonsMask var9, List var10, JcaJceHelper var11) throws AnnotatedException, RecoverableCertPathValidatorException {
      if (var3 == null) {
         boolean var12 = true;
      }

      if (var4.getTime() > var3.getTime()) {
         throw new AnnotatedException("Validation time is in future.");
      } else {
         Set var23 = CertPathValidatorUtilities.getCompleteCRLs(var0, var1, var5, var2, var4);
         boolean var13 = false;
         AnnotatedException var14 = null;
         Iterator var15 = var23.iterator();

         while(var15.hasNext() && var8.getCertStatus() == 11 && !var9.isAllReasons()) {
            try {
               X509CRL var16 = (X509CRL)var15.next();
               ReasonsMask var17 = processCRLD(var16, var1);
               if (var17.hasNewReasons(var9)) {
                  Set var18 = processCRLF(var16, var5, var6, var7, var2, var10, var11);
                  PublicKey var19 = processCRLG(var16, var18);
                  X509CRL var20 = null;
                  if (var2.isUseDeltasEnabled()) {
                     Set var21 = CertPathValidatorUtilities.getDeltaCRLs(var4, var16, var2.getCertStores(), var2.getCRLStores(), var11);
                     var20 = processCRLH(var21, var19);
                  }

                  if (var2.getValidityModel() != 1 && var5.getNotAfter().getTime() < var16.getThisUpdate().getTime()) {
                     throw new AnnotatedException("No valid CRL for current time found.");
                  }

                  processCRLB1(var1, var5, var16);
                  processCRLB2(var1, var5, var16);
                  processCRLC(var20, var16, var2);
                  processCRLI(var4, var20, var5, var8, var2);
                  processCRLJ(var4, var16, var5, var8);
                  if (var8.getCertStatus() == 8) {
                     var8.setCertStatus(11);
                  }

                  var9.addReasons(var17);
                  Set var24 = var16.getCriticalExtensionOIDs();
                  if (var24 != null) {
                     var24 = new HashSet(var24);
                     var24.remove(Extension.issuingDistributionPoint.getId());
                     var24.remove(Extension.deltaCRLIndicator.getId());
                     if (!var24.isEmpty()) {
                        throw new AnnotatedException("CRL contains unsupported critical extensions.");
                     }
                  }

                  if (var20 != null) {
                     var24 = var20.getCriticalExtensionOIDs();
                     if (var24 != null) {
                        var24 = new HashSet(var24);
                        var24.remove(Extension.issuingDistributionPoint.getId());
                        var24.remove(Extension.deltaCRLIndicator.getId());
                        if (!var24.isEmpty()) {
                           throw new AnnotatedException("Delta CRL contains unsupported critical extension.");
                        }
                     }
                  }

                  var13 = true;
               }
            } catch (AnnotatedException var22) {
               var14 = var22;
            }
         }

         if (!var13) {
            throw var14;
         }
      }
   }

   protected static void checkCRLs(PKIXCertRevocationCheckerParameters var0, PKIXExtendedParameters var1, Date var2, Date var3, X509Certificate var4, X509Certificate var5, PublicKey var6, List var7, JcaJceHelper var8) throws AnnotatedException, RecoverableCertPathValidatorException {
      AnnotatedException var9 = null;
      CRLDistPoint var10 = null;

      try {
         var10 = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, CRL_DISTRIBUTION_POINTS));
      } catch (Exception var23) {
         throw new AnnotatedException("CRL distribution point extension could not be read.", var23);
      }

      PKIXExtendedParameters.Builder var11 = new PKIXExtendedParameters.Builder(var1);

      try {
         List var12 = CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(var10, var1.getNamedCRLStoreMap(), var3, var8);
         Iterator var13 = var12.iterator();

         while(var13.hasNext()) {
            var11.addCRLStore((PKIXCRLStore)var13.next());
         }
      } catch (AnnotatedException var24) {
         throw new AnnotatedException("No additional CRL locations could be decoded from CRL distribution point extension.", var24);
      }

      CertStatus var26 = new CertStatus();
      ReasonsMask var27 = new ReasonsMask();
      PKIXExtendedParameters var14 = var11.build();
      boolean var15 = false;
      if (var10 != null) {
         Object var16 = null;

         try {
            var28 = var10.getDistributionPoints();
         } catch (Exception var22) {
            throw new AnnotatedException("Distribution points could not be read.", var22);
         }

         if (var28 != null) {
            for(int var17 = 0; var17 < var28.length && var26.getCertStatus() == 11 && !var27.isAllReasons(); ++var17) {
               try {
                  checkCRL(var0, var28[var17], var14, var2, var3, var4, var5, var6, var26, var27, var7, var8);
                  var15 = true;
               } catch (AnnotatedException var21) {
                  var9 = var21;
               }
            }
         }
      }

      if (var26.getCertStatus() == 11 && !var27.isAllReasons()) {
         try {
            X500Name var29;
            try {
               var29 = PrincipalUtils.getIssuerPrincipal(var4);
            } catch (RuntimeException var19) {
               throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", var19);
            }

            DistributionPoint var31 = new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, var29))), (ReasonFlags)null, (GeneralNames)null);
            PKIXExtendedParameters var18 = (PKIXExtendedParameters)var1.clone();
            checkCRL(var0, var31, var18, var2, var3, var4, var5, var6, var26, var27, var7, var8);
            var15 = true;
         } catch (AnnotatedException var20) {
            var9 = var20;
         }
      }

      if (!var15) {
         if (var9 instanceof AnnotatedException) {
            throw var9;
         } else {
            throw new AnnotatedException("No valid CRL found.", var9);
         }
      } else if (var26.getCertStatus() != 11) {
         SimpleDateFormat var30 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
         var30.setTimeZone(TimeZone.getTimeZone("UTC"));
         String var32 = "Certificate revocation after " + var30.format(var26.getRevocationDate());
         var32 = var32 + ", reason: " + crlReasons[var26.getCertStatus()];
         throw new AnnotatedException(var32);
      } else {
         if (!var27.isAllReasons() && var26.getCertStatus() == 11) {
            var26.setCertStatus(12);
         }

         if (var26.getCertStatus() == 12) {
            throw new AnnotatedException("Certificate status could not be determined.");
         }
      }
   }

   protected static int prepareNextCertJ(CertPath var0, int var1, int var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      ASN1Integer var5 = null;

      try {
         var5 = ASN1Integer.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, INHIBIT_ANY_POLICY));
      } catch (Exception var7) {
         throw new ExtCertPathValidatorException("Inhibit any-policy extension cannot be decoded.", var7, var0, var1);
      }

      if (var5 != null) {
         int var6 = var5.intValueExact();
         if (var6 < var2) {
            return var6;
         }
      }

      return var2;
   }

   protected static void prepareNextCertK(CertPath var0, int var1) throws CertPathValidatorException {
      List var2 = var0.getCertificates();
      X509Certificate var3 = (X509Certificate)var2.get(var1);
      Object var4 = null;

      try {
         var7 = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue(var3, BASIC_CONSTRAINTS));
      } catch (Exception var6) {
         throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", var6, var0, var1);
      }

      if (var7 != null) {
         if (!var7.isCA()) {
            throw new CertPathValidatorException("Not a CA certificate", (Throwable)null, var0, var1);
         }
      } else {
         throw new CertPathValidatorException("Intermediate certificate lacks BasicConstraints", (Throwable)null, var0, var1);
      }
   }

   protected static int prepareNextCertL(CertPath var0, int var1, int var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      if (!CertPathValidatorUtilities.isSelfIssued(var4)) {
         if (var2 <= 0) {
            throw new ExtCertPathValidatorException("Max path length not greater than zero", (Throwable)null, var0, var1);
         } else {
            return var2 - 1;
         }
      } else {
         return var2;
      }
   }

   protected static int prepareNextCertM(CertPath var0, int var1, int var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      BasicConstraints var5 = null;

      try {
         var5 = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, BASIC_CONSTRAINTS));
      } catch (Exception var7) {
         throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", var7, var0, var1);
      }

      if (var5 != null && var5.isCA()) {
         ASN1Integer var6 = var5.getPathLenConstraintInteger();
         if (var6 != null) {
            var2 = Math.min(var2, var6.intPositiveValueExact());
         }
      }

      return var2;
   }

   protected static void prepareNextCertN(CertPath var0, int var1) throws CertPathValidatorException {
      List var2 = var0.getCertificates();
      X509Certificate var3 = (X509Certificate)var2.get(var1);
      boolean[] var4 = var3.getKeyUsage();
      if (var4 != null && (var4.length <= 5 || !var4[5])) {
         throw new ExtCertPathValidatorException("Issuer certificate keyusage extension is critical and does not permit key signing.", (Throwable)null, var0, var1);
      }
   }

   protected static void prepareNextCertO(CertPath var0, int var1, Set var2, List var3) throws CertPathValidatorException {
      List var4 = var0.getCertificates();
      X509Certificate var5 = (X509Certificate)var4.get(var1);
      Iterator var6 = var3.iterator();

      while(var6.hasNext()) {
         try {
            ((PKIXCertPathChecker)var6.next()).check(var5, var2);
         } catch (CertPathValidatorException var8) {
            throw new CertPathValidatorException(var8.getMessage(), var8.getCause(), var0, var1);
         }
      }

      if (!var2.isEmpty()) {
         throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + var2, (Throwable)null, var0, var1);
      }
   }

   protected static int prepareNextCertH1(CertPath var0, int var1, int var2) {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      return !CertPathValidatorUtilities.isSelfIssued(var4) && var2 != 0 ? var2 - 1 : var2;
   }

   protected static int prepareNextCertH2(CertPath var0, int var1, int var2) {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      return !CertPathValidatorUtilities.isSelfIssued(var4) && var2 != 0 ? var2 - 1 : var2;
   }

   protected static int prepareNextCertH3(CertPath var0, int var1, int var2) {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      return !CertPathValidatorUtilities.isSelfIssued(var4) && var2 != 0 ? var2 - 1 : var2;
   }

   protected static int wrapupCertA(int var0, X509Certificate var1) {
      if (!CertPathValidatorUtilities.isSelfIssued(var1) && var0 != 0) {
         --var0;
      }

      return var0;
   }

   protected static int wrapupCertB(CertPath var0, int var1, int var2) throws CertPathValidatorException {
      List var3 = var0.getCertificates();
      X509Certificate var4 = (X509Certificate)var3.get(var1);
      ASN1Sequence var6 = null;

      try {
         var6 = ASN1Sequence.getInstance(CertPathValidatorUtilities.getExtensionValue(var4, POLICY_CONSTRAINTS));
      } catch (AnnotatedException var11) {
         throw new ExtCertPathValidatorException("Policy constraints could not be decoded.", var11, var0, var1);
      }

      if (var6 != null) {
         Enumeration var7 = var6.getObjects();

         while(var7.hasMoreElements()) {
            ASN1TaggedObject var8 = (ASN1TaggedObject)var7.nextElement();
            switch (var8.getTagNo()) {
               case 0:
                  int var5;
                  try {
                     var5 = ASN1Integer.getInstance(var8, false).intValueExact();
                  } catch (Exception var10) {
                     throw new ExtCertPathValidatorException("Policy constraints requireExplicitPolicy field could not be decoded.", var10, var0, var1);
                  }

                  if (var5 == 0) {
                     return 0;
                  }
            }
         }
      }

      return var2;
   }

   protected static void wrapupCertF(CertPath var0, int var1, List var2, Set var3) throws CertPathValidatorException {
      List var4 = var0.getCertificates();
      X509Certificate var5 = (X509Certificate)var4.get(var1);
      Iterator var6 = var2.iterator();

      while(var6.hasNext()) {
         try {
            ((PKIXCertPathChecker)var6.next()).check(var5, var3);
         } catch (CertPathValidatorException var8) {
            throw new ExtCertPathValidatorException(var8.getMessage(), var8, var0, var1);
         } catch (Exception var9) {
            throw new CertPathValidatorException("Additional certificate path checker failed.", var9, var0, var1);
         }
      }

      if (!var3.isEmpty()) {
         throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + var3, (Throwable)null, var0, var1);
      }
   }

   protected static PKIXPolicyNode wrapupCertG(CertPath var0, PKIXExtendedParameters var1, Set var2, int var3, List[] var4, PKIXPolicyNode var5, Set var6) throws CertPathValidatorException {
      int var7 = var0.getCertificates().size();
      PKIXPolicyNode var8;
      if (var5 == null) {
         if (var1.isExplicitPolicyRequired()) {
            throw new ExtCertPathValidatorException("Explicit policy requested but none available.", (Throwable)null, var0, var3);
         }

         var8 = null;
      } else if (CertPathValidatorUtilities.isAnyPolicy(var2)) {
         if (var1.isExplicitPolicyRequired()) {
            if (var6.isEmpty()) {
               throw new ExtCertPathValidatorException("Explicit policy requested but none available.", (Throwable)null, var0, var3);
            }

            HashSet var9 = new HashSet();

            for(int var10 = 0; var10 < var4.length; ++var10) {
               List var11 = var4[var10];

               for(int var12 = 0; var12 < var11.size(); ++var12) {
                  PKIXPolicyNode var13 = (PKIXPolicyNode)var11.get(var12);
                  if ("2.5.29.32.0".equals(var13.getValidPolicy())) {
                     Iterator var14 = var13.getChildren();

                     while(var14.hasNext()) {
                        var9.add(var14.next());
                     }
                  }
               }
            }

            for(PKIXPolicyNode var20 : var9) {
               String var25 = var20.getValidPolicy();
               if (!var6.contains(var25)) {
               }
            }

            if (var5 != null) {
               for(int var21 = var7 - 1; var21 >= 0; --var21) {
                  List var26 = var4[var21];

                  for(int var30 = 0; var30 < var26.size(); ++var30) {
                     PKIXPolicyNode var33 = (PKIXPolicyNode)var26.get(var30);
                     if (!var33.hasChildren()) {
                        var5 = CertPathValidatorUtilities.removePolicyNode(var5, var4, var33);
                     }
                  }
               }
            }
         }

         var8 = var5;
      } else {
         HashSet var16 = new HashSet();

         for(int var18 = 0; var18 < var4.length; ++var18) {
            List var22 = var4[var18];

            for(int var27 = 0; var27 < var22.size(); ++var27) {
               PKIXPolicyNode var31 = (PKIXPolicyNode)var22.get(var27);
               if ("2.5.29.32.0".equals(var31.getValidPolicy())) {
                  Iterator var34 = var31.getChildren();

                  while(var34.hasNext()) {
                     PKIXPolicyNode var15 = (PKIXPolicyNode)var34.next();
                     if (!"2.5.29.32.0".equals(var15.getValidPolicy())) {
                        var16.add(var15);
                     }
                  }
               }
            }
         }

         for(PKIXPolicyNode var23 : var16) {
            String var28 = var23.getValidPolicy();
            if (!var2.contains(var28)) {
               var5 = CertPathValidatorUtilities.removePolicyNode(var5, var4, var23);
            }
         }

         if (var5 != null) {
            for(int var24 = var7 - 1; var24 >= 0; --var24) {
               List var29 = var4[var24];

               for(int var32 = 0; var32 < var29.size(); ++var32) {
                  PKIXPolicyNode var35 = (PKIXPolicyNode)var29.get(var32);
                  if (!var35.hasChildren()) {
                     var5 = CertPathValidatorUtilities.removePolicyNode(var5, var4, var35);
                  }
               }
            }
         }

         var8 = var5;
      }

      return var8;
   }

   static {
      CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
      POLICY_MAPPINGS = Extension.policyMappings.getId();
      INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
      ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
      FRESHEST_CRL = Extension.freshestCRL.getId();
      DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
      POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
      BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
      CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
      SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
      NAME_CONSTRAINTS = Extension.nameConstraints.getId();
      AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
      KEY_USAGE = Extension.keyUsage.getId();
      CRL_NUMBER = Extension.cRLNumber.getId();
      crlReasons = new String[]{"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};
   }
}
