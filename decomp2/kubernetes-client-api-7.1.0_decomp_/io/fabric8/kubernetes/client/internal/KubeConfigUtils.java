package io.fabric8.kubernetes.client.internal;

import io.fabric8.kubernetes.api.model.AuthInfo;
import io.fabric8.kubernetes.api.model.Cluster;
import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.ExecConfig;
import io.fabric8.kubernetes.api.model.ExecEnvVar;
import io.fabric8.kubernetes.api.model.NamedAuthInfo;
import io.fabric8.kubernetes.api.model.NamedCluster;
import io.fabric8.kubernetes.api.model.NamedContext;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.IOHelpers;
import io.fabric8.kubernetes.client.utils.Serialization;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KubeConfigUtils {
   private static final Logger logger = LoggerFactory.getLogger(KubeConfigUtils.class);
   private static final String KUBERNETES_CONFIG_CONTEXT_FILE_KEY = "KUBERNETES_CONFIG_CONTEXT_FILE_KEY";
   private static final String KUBERNETES_CONFIG_CLUSTER_FILE_KEY = "KUBERNETES_CONFIG_CLUSTER_FILE_KEY";
   private static final String KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY = "KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY";
   private static final String ACCESS_TOKEN = "access-token";
   private static final String ID_TOKEN = "id-token";

   private KubeConfigUtils() {
   }

   public static Config parseConfig(File kubeconfig) {
      if (kubeconfig == null) {
         throw new KubernetesClientException("kubeconfig (File) cannot be null");
      } else {
         try {
            InputStream fis = Files.newInputStream(kubeconfig.toPath());

            Config var3;
            try {
               Config ret = (Config)Serialization.unmarshal(fis, Config.class);
               if (ret.getContexts() != null) {
                  ret.getContexts().forEach((ctx) -> ctx.getAdditionalProperties().put("KUBERNETES_CONFIG_CONTEXT_FILE_KEY", kubeconfig));
               }

               if (ret.getClusters() != null) {
                  ret.getClusters().forEach((cluster) -> cluster.getAdditionalProperties().put("KUBERNETES_CONFIG_CLUSTER_FILE_KEY", kubeconfig));
               }

               if (ret.getUsers() != null) {
                  ret.getUsers().forEach((user) -> user.getAdditionalProperties().put("KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY", kubeconfig));
               }

               var3 = ret;
            } catch (Throwable var5) {
               if (fis != null) {
                  try {
                     fis.close();
                  } catch (Throwable var4) {
                     var5.addSuppressed(var4);
                  }
               }

               throw var5;
            }

            if (fis != null) {
               fis.close();
            }

            return var3;
         } catch (Exception e) {
            throw KubernetesClientException.launderThrowable((String)(kubeconfig + " (File) is not a parseable Kubernetes Config"), e);
         }
      }
   }

   public static Config parseConfigFromString(String contents) {
      return (Config)Serialization.unmarshal(contents, Config.class);
   }

   public static void persistKubeConfigIntoFile(Config kubeconfig, File kubeConfigPath) throws IOException {
      if (kubeconfig.getContexts() != null) {
         kubeconfig.getContexts().forEach((c) -> {
            Objects.requireNonNull(c);
            removeAdditionalProperties(c::getAdditionalProperties);
         });
      }

      if (kubeconfig.getClusters() != null) {
         kubeconfig.getClusters().forEach((c) -> {
            Objects.requireNonNull(c);
            removeAdditionalProperties(c::getAdditionalProperties);
         });
      }

      if (kubeconfig.getUsers() != null) {
         kubeconfig.getUsers().forEach((c) -> {
            Objects.requireNonNull(c);
            removeAdditionalProperties(c::getAdditionalProperties);
         });
      }

      Files.writeString(kubeConfigPath.toPath(), Serialization.asYaml(kubeconfig));
   }

   public static File getFileWithNamedContext(NamedContext namedContext) {
      Supplier var10000;
      if (namedContext != null) {
         Objects.requireNonNull(namedContext);
         var10000 = namedContext::getAdditionalProperties;
      } else {
         var10000 = null;
      }

      return getFile(var10000, "KUBERNETES_CONFIG_CONTEXT_FILE_KEY");
   }

   public static File getFileWithNamedCluster(NamedContext namedContext) {
      Supplier var10000;
      if (namedContext != null) {
         Objects.requireNonNull(namedContext);
         var10000 = namedContext::getAdditionalProperties;
      } else {
         var10000 = null;
      }

      return getFile(var10000, "KUBERNETES_CONFIG_CLUSTER_FILE_KEY");
   }

   public static File getFileWithNamedAuthInfo(NamedContext namedContext) {
      Supplier var10000;
      if (namedContext != null) {
         Objects.requireNonNull(namedContext);
         var10000 = namedContext::getAdditionalProperties;
      } else {
         var10000 = null;
      }

      return getFile(var10000, "KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY");
   }

   private static File getFileWithNamedCluster(NamedCluster namedCluster) {
      Supplier var10000;
      if (namedCluster != null) {
         Objects.requireNonNull(namedCluster);
         var10000 = namedCluster::getAdditionalProperties;
      } else {
         var10000 = null;
      }

      return getFile(var10000, "KUBERNETES_CONFIG_CLUSTER_FILE_KEY");
   }

   private static File getFileWithNamedAuthInfo(NamedAuthInfo namedAuthInfo) {
      Supplier var10000;
      if (namedAuthInfo != null) {
         Objects.requireNonNull(namedAuthInfo);
         var10000 = namedAuthInfo::getAdditionalProperties;
      } else {
         var10000 = null;
      }

      return getFile(var10000, "KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY");
   }

   private static File getFile(Supplier provider, String key) {
      return provider != null && provider.get() != null && ((Map)provider.get()).get(key) instanceof File ? (File)((Map)provider.get()).get(key) : null;
   }

   public static void merge(io.fabric8.kubernetes.client.Config clientConfig, String context, Config... kubeconfigs) {
      Map<String, NamedContext> mergedContexts = mergeContexts(clientConfig, kubeconfigs);
      clientConfig.setContexts(new ArrayList(mergedContexts.values()));
      NamedContext currentContext = null;

      for(String contextName : contextPreference(context, kubeconfigs)) {
         if (mergedContexts.containsKey(contextName)) {
            currentContext = (NamedContext)mergedContexts.get(contextName);
            break;
         }
      }

      if (currentContext != null && currentContext.getContext() != null) {
         clientConfig.setCurrentContext(currentContext);
         clientConfig.setNamespace(currentContext.getContext().getNamespace());
         Map<String, NamedCluster> mergedClusters = mergeClusters(kubeconfigs);
         NamedCluster currentNamedCluster = (NamedCluster)mergedClusters.get(currentContext.getContext().getCluster());
         if (currentNamedCluster != null) {
            File configFile = getFileWithNamedCluster(currentNamedCluster);
            currentContext.setAdditionalProperty("KUBERNETES_CONFIG_CLUSTER_FILE_KEY", configFile);
            Cluster currentCluster = currentNamedCluster.getCluster();
            clientConfig.setMasterUrl(currentCluster.getServer());
            clientConfig.setTrustCerts(Objects.equals(currentCluster.getInsecureSkipTlsVerify(), true));
            clientConfig.setDisableHostnameVerification(Objects.equals(currentCluster.getInsecureSkipTlsVerify(), true));
            String caCertFile = configFile != null ? absolutify(configFile, currentCluster.getCertificateAuthority()) : currentCluster.getCertificateAuthority();
            clientConfig.setCaCertFile(caCertFile);
            clientConfig.setCaCertData(currentCluster.getCertificateAuthorityData());
            String proxyUrl = currentCluster.getProxyUrl();
            if (Utils.isNotNullOrEmpty(proxyUrl)) {
               if (proxyUrl.startsWith("socks5://") && clientConfig.getMasterUrl().startsWith("https://")) {
                  clientConfig.setHttpsProxy(proxyUrl);
               } else if (proxyUrl.startsWith("socks5://")) {
                  clientConfig.setHttpProxy(proxyUrl);
               } else if (proxyUrl.startsWith("http://")) {
                  clientConfig.setHttpProxy(proxyUrl);
               } else if (proxyUrl.startsWith("https://")) {
                  clientConfig.setHttpsProxy(proxyUrl);
               }
            }
         }

         Map<String, NamedAuthInfo> mergedUsers = mergeUsers(kubeconfigs);
         NamedAuthInfo currentNamedAuthInfo = (NamedAuthInfo)mergedUsers.get(currentContext.getContext().getUser());
         if (currentNamedAuthInfo != null) {
            File configFile = getFileWithNamedAuthInfo(currentNamedAuthInfo);
            currentContext.setAdditionalProperty("KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY", configFile);
            AuthInfo currentAuthInfo = currentNamedAuthInfo.getUser();
            String clientCertFile = currentAuthInfo.getClientCertificate();
            String clientKeyFile = currentAuthInfo.getClientKey();
            if (configFile != null) {
               clientCertFile = absolutify(configFile, currentAuthInfo.getClientCertificate());
               clientKeyFile = absolutify(configFile, currentAuthInfo.getClientKey());
            }

            clientConfig.setClientCertFile(clientCertFile);
            clientConfig.setClientCertData(currentAuthInfo.getClientCertificateData());
            clientConfig.setClientKeyFile(clientKeyFile);
            clientConfig.setClientKeyData(currentAuthInfo.getClientKeyData());
            clientConfig.setClientKeyAlgo(io.fabric8.kubernetes.client.Config.getKeyAlgorithm(clientConfig.getClientKeyFile(), clientConfig.getClientKeyData()));
            clientConfig.setAutoOAuthToken(currentAuthInfo.getToken());
            clientConfig.setUsername(currentAuthInfo.getUsername());
            clientConfig.setPassword(currentAuthInfo.getPassword());
            if (Utils.isNullOrEmpty(clientConfig.getAutoOAuthToken()) && currentAuthInfo.getAuthProvider() != null) {
               mergeKubeConfigAuthProviderConfig(clientConfig, currentAuthInfo);
            } else if (clientConfig.getOauthTokenProvider() == null) {
               mergeKubeConfigExecCredential(clientConfig, currentAuthInfo.getExec(), configFile);
            }
         }

      }
   }

   private static Map mergeContexts(io.fabric8.kubernetes.client.Config config, Config... kubeconfigs) {
      Map<String, NamedContext> mergedContexts = new HashMap();

      for(int i = kubeconfigs.length - 1; i >= 0; --i) {
         if (kubeconfigs[i].getContexts() != null) {
            for(NamedContext ctx : kubeconfigs[i].getContexts()) {
               if (ctx.getContext() != null) {
                  mergedContexts.put(ctx.getName(), ctx);
               }
            }
         }
      }

      if (config.getContexts() != null) {
         for(NamedContext ctx : config.getContexts()) {
            mergedContexts.put(ctx.getName(), ctx);
         }
      }

      return mergedContexts;
   }

   private static Map mergeClusters(Config... kubeconfigs) {
      Map<String, NamedCluster> mergedClusters = new HashMap();

      for(int i = kubeconfigs.length - 1; i >= 0; --i) {
         if (kubeconfigs[i].getClusters() != null) {
            for(NamedCluster cluster : kubeconfigs[i].getClusters()) {
               if (cluster.getCluster() != null) {
                  mergedClusters.put(cluster.getName(), cluster);
               }
            }
         }
      }

      return mergedClusters;
   }

   private static Map mergeUsers(Config... kubeconfigs) {
      Map<String, NamedAuthInfo> mergedUsers = new HashMap();

      for(int i = kubeconfigs.length - 1; i >= 0; --i) {
         if (kubeconfigs[i].getUsers() != null) {
            for(NamedAuthInfo user : kubeconfigs[i].getUsers()) {
               if (user.getUser() != null) {
                  mergedUsers.put(user.getName(), user);
               }
            }
         }
      }

      return mergedUsers;
   }

   private static List contextPreference(String context, Config... kubeconfigs) {
      List<String> contextPreference = new ArrayList();
      if (Utils.isNotNullOrEmpty(context)) {
         contextPreference.add(context);
      }

      for(Config kubeconfig : kubeconfigs) {
         if (Utils.isNotNullOrEmpty(kubeconfig.getCurrentContext())) {
            contextPreference.add(kubeconfig.getCurrentContext());
         }
      }

      return contextPreference;
   }

   private static void mergeKubeConfigAuthProviderConfig(io.fabric8.kubernetes.client.Config config, AuthInfo currentAuthInfo) {
      if (currentAuthInfo.getAuthProvider().getConfig() != null) {
         config.setAuthProvider(currentAuthInfo.getAuthProvider());
         if (!Utils.isNullOrEmpty((String)currentAuthInfo.getAuthProvider().getConfig().get("access-token"))) {
            config.setAutoOAuthToken((String)currentAuthInfo.getAuthProvider().getConfig().get("access-token"));
         } else if (!Utils.isNullOrEmpty((String)currentAuthInfo.getAuthProvider().getConfig().get("id-token"))) {
            config.setAutoOAuthToken((String)currentAuthInfo.getAuthProvider().getConfig().get("id-token"));
         }
      }

   }

   private static void mergeKubeConfigExecCredential(io.fabric8.kubernetes.client.Config config, ExecConfig exec, File configFile) {
      if (exec != null) {
         io.fabric8.kubernetes.client.Config.ExecCredential ec = getExecCredentialFromExecConfig(exec, configFile);
         if (ec != null && ec.status != null) {
            if (ec.status.token != null) {
               config.setAutoOAuthToken(ec.status.token);
            } else if (Utils.isNotNullOrEmpty(ec.status.clientCertificateData) && Utils.isNotNullOrEmpty(ec.status.clientKeyData)) {
               config.setClientCertData(ec.status.clientCertificateData);
               config.setClientKeyData(ec.status.clientKeyData);
            } else {
               logger.warn("No token or certificate returned");
            }
         }
      }

   }

   protected static io.fabric8.kubernetes.client.Config.ExecCredential getExecCredentialFromExecConfig(ExecConfig exec, File configFile) {
      String apiVersion = exec.getApiVersion();
      List<ExecEnvVar> env = exec.getEnv();
      ProcessBuilder pb = new ProcessBuilder(getAuthenticatorCommandFromExecConfig(exec, configFile, Utils.getSystemPathVariable()));
      pb.redirectErrorStream(true);
      if (env != null) {
         Map<String, String> environment = pb.environment();
         env.forEach((var) -> environment.put(var.getName(), var.getValue()));
      }

      String output;
      try {
         Process p = pb.start();
         InputStream is = p.getInputStream();

         try {
            output = IOHelpers.readFully(is);
         } catch (Throwable var12) {
            if (is != null) {
               try {
                  is.close();
               } catch (Throwable var10) {
                  var12.addSuppressed(var10);
               }
            }

            throw var12;
         }

         if (is != null) {
            is.close();
         }

         if (p.waitFor() != 0) {
            logger.warn(output);
         }
      } catch (IOException ex) {
         throw KubernetesClientException.launderThrowable(ex);
      } catch (InterruptedException ex) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(ex);
      }

      try {
         io.fabric8.kubernetes.client.Config.ExecCredential ec = (io.fabric8.kubernetes.client.Config.ExecCredential)Serialization.unmarshal(output, io.fabric8.kubernetes.client.Config.ExecCredential.class);
         if (ec != null && Objects.equals(apiVersion, ec.apiVersion)) {
            return ec;
         }

         logger.warn("Wrong apiVersion {} vs. {}", ec == null ? null : ec.apiVersion, apiVersion);
      } catch (Exception ex) {
         logger.warn("Error unmarshalling ExecCredential", ex);
      }

      return null;
   }

   protected static List getAuthenticatorCommandFromExecConfig(ExecConfig exec, File configFile, String systemPathValue) {
      String command = exec.getCommand();
      if (command.contains(File.separator) && !command.startsWith(File.separator) && configFile != null) {
         command = Paths.get(configFile.getAbsolutePath()).resolveSibling(command).normalize().toString();
      }

      List<String> argv = new ArrayList(Utils.getCommandPlatformPrefix());
      command = findExecutable(command, systemPathValue);
      command = shellQuote(command);
      List<String> args = exec.getArgs();
      if (args != null && !args.isEmpty()) {
         command = command + " " + (String)args.stream().map(KubeConfigUtils::shellQuote).collect(Collectors.joining(" "));
      }

      argv.add(command);
      return argv;
   }

   private static String shellQuote(String value) {
      return !value.contains(" ") && !value.contains("\"") && !value.contains("'") ? value : "\"" + value.replace("\"", "\\\"") + "\"";
   }

   protected static String findExecutable(String command, String pathValue) {
      for(String pathPart : pathValue.split(File.pathSeparator)) {
         File commandFile = new File(pathPart + File.separator + command);
         if (commandFile.exists()) {
            return commandFile.getAbsolutePath();
         }
      }

      return command;
   }

   private static String absolutify(File relativeTo, String filename) {
      if (filename == null) {
         return null;
      } else {
         File file = new File(filename);
         return file.isAbsolute() ? file.getAbsolutePath() : (new File(relativeTo.getParentFile(), filename)).getAbsolutePath();
      }
   }

   private static void removeAdditionalProperties(Supplier provider) {
      if (provider != null) {
         ((Map)provider.get()).remove("KUBERNETES_CONFIG_CONTEXT_FILE_KEY");
         ((Map)provider.get()).remove("KUBERNETES_CONFIG_CLUSTER_FILE_KEY");
         ((Map)provider.get()).remove("KUBERNETES_CONFIG_AUTH_INFO_FILE_KEY");
      }
   }
}
