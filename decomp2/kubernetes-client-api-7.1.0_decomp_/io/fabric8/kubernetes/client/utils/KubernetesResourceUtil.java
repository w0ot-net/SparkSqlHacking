package io.fabric8.kubernetes.client.utils;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapFluent;
import io.fabric8.kubernetes.api.model.DefaultKubernetesResourceList;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import io.fabric8.kubernetes.api.model.Event;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesList;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretBuilder;
import io.fabric8.kubernetes.api.model.SecretFluent;
import io.fabric8.kubernetes.client.readiness.Readiness;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class KubernetesResourceUtil {
   public static final Pattern KUBERNETES_SUBDOMAIN_REGEX = Pattern.compile("[a-z0-9]([-a-z0-9]*[a-z0-9])?(\\.[a-z0-9]([-a-z0-9]*[a-z0-9])?)*");
   public static final Pattern KUBERNETES_DNS1123_LABEL_REGEX = Pattern.compile("[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?");
   public static final Pattern KUBERNETES_KEY_REGEX;
   private static final Pattern INVALID_LABEL_CHARS_PATTERN;
   private static final String DEFAULT_CONTAINER_IMAGE_REGISTRY_SECRET_NAME = "container-image-registry-secret";

   private KubernetesResourceUtil() {
   }

   public static String getResourceVersion(HasMetadata entity) {
      if (entity != null) {
         ObjectMeta metadata = entity.getMetadata();
         if (metadata != null) {
            String resourceVersion = metadata.getResourceVersion();
            if (!Utils.isNullOrEmpty(resourceVersion)) {
               return resourceVersion;
            }
         }
      }

      return null;
   }

   public static void setResourceVersion(HasMetadata entity, String resourceVersion) {
      if (entity != null) {
         ObjectMeta metadata = entity.getMetadata();
         if (metadata != null) {
            metadata.setResourceVersion(resourceVersion);
         }
      }

   }

   public static void setNamespace(HasMetadata entity, String namespace) {
      if (entity != null) {
         ObjectMeta metadata = entity.getMetadata();
         if (metadata != null) {
            metadata.setNamespace(namespace);
         }
      }

   }

   public static String getKind(HasMetadata entity) {
      if (entity != null) {
         return entity instanceof KubernetesList ? "List" : entity.getClass().getSimpleName();
      } else {
         return null;
      }
   }

   public static String getQualifiedName(HasMetadata entity) {
      if (entity != null) {
         String var10000 = getNamespace(entity);
         return var10000 + "/" + getName(entity);
      } else {
         return null;
      }
   }

   public static String getName(HasMetadata entity) {
      return entity != null ? getName(entity.getMetadata()) : null;
   }

   public static boolean hasResourceVersion(HasMetadata entity) {
      return getResourceVersion(entity) != null;
   }

   public static String getName(ObjectMeta entity) {
      return entity != null ? Utils.coalesce(entity.getName(), getAdditionalPropertyText(entity.getAdditionalProperties(), "id"), entity.getUid()) : null;
   }

   protected static String getAdditionalPropertyText(Map additionalProperties, String name) {
      if (additionalProperties != null) {
         Object value = additionalProperties.get(name);
         if (value != null) {
            return value.toString();
         }
      }

      return null;
   }

   public static String getNamespace(ObjectMeta entity) {
      return entity != null ? entity.getNamespace() : null;
   }

   public static String getNamespace(HasMetadata entity) {
      return entity != null ? getNamespace(entity.getMetadata()) : null;
   }

   public static Map getOrCreateAnnotations(HasMetadata entity) {
      ObjectMeta metadata = getOrCreateMetadata(entity);
      Map<String, String> answer = metadata.getAnnotations();
      if (answer == null) {
         answer = new LinkedHashMap();
         metadata.setAnnotations(answer);
      }

      return answer;
   }

   public static String sanitizeName(String name) {
      if (name != null) {
         name = INVALID_LABEL_CHARS_PATTERN.matcher(name).replaceAll("-");
         if (!Character.isLetterOrDigit(name.charAt(0))) {
            name = "a" + name;
         }

         if (name.length() > 63) {
            name = name.substring(0, 63);
         }

         name = name.toLowerCase();
         int lastChar = name.length() - 1;
         if (!Character.isLetterOrDigit(name.charAt(lastChar))) {
            String var10000 = name.substring(0, lastChar - 1);
            name = var10000 + "a";
         }

         return name;
      } else {
         return null;
      }
   }

   public static Map getOrCreateLabels(HasMetadata entity) {
      ObjectMeta metadata = getOrCreateMetadata(entity);
      Map<String, String> answer = metadata.getLabels();
      if (answer == null) {
         answer = new LinkedHashMap();
         metadata.setLabels(answer);
      }

      return answer;
   }

   public static Map getLabels(ObjectMeta metadata) {
      if (metadata != null) {
         Map<String, String> labels = metadata.getLabels();
         if (labels != null) {
            return labels;
         }
      }

      return Collections.emptyMap();
   }

   public static ObjectMeta getOrCreateMetadata(HasMetadata entity) {
      ObjectMeta metadata = entity.getMetadata();
      if (metadata == null) {
         metadata = new ObjectMeta();
         entity.setMetadata(metadata);
      }

      return metadata;
   }

   public static boolean isValidName(String name) {
      return Utils.isNotNullOrEmpty(name) && KUBERNETES_DNS1123_LABEL_REGEX.matcher(name).matches();
   }

   public static boolean isValidKey(String key) {
      return Utils.isNotNullOrEmpty(key) && key.length() < 254 && KUBERNETES_KEY_REGEX.matcher(key).matches();
   }

   public static boolean isValidSubdomainName(String name) {
      return Utils.isNotNullOrEmpty(name) && name.length() < 254 && KUBERNETES_SUBDOMAIN_REGEX.matcher(name).matches();
   }

   /** @deprecated */
   @Deprecated
   public static boolean isValidLabelOrAnnotation(Map map) {
      return areLabelsValid(map);
   }

   public static boolean areLabelsValid(Map map) {
      return map.entrySet().stream().allMatch((e) -> isValidKey((String)e.getKey()) && isValidName((String)e.getValue()));
   }

   public static boolean areAnnotationsValid(Map map) {
      return map.keySet().stream().allMatch(KubernetesResourceUtil::isValidKey);
   }

   public static boolean hasController(HasMetadata resource) {
      return getControllerUid(resource) != null;
   }

   public static OwnerReference getControllerUid(HasMetadata resource) {
      if (resource.getMetadata() != null) {
         for(OwnerReference ownerReference : resource.getMetadata().getOwnerReferences()) {
            if (Boolean.TRUE.equals(ownerReference.getController())) {
               return ownerReference;
            }
         }
      }

      return null;
   }

   public static void sortEventListBasedOnTimestamp(List eventList) {
      if (eventList != null) {
         eventList.sort(Comparator.comparing(Event::getLastTimestamp, Comparator.nullsFirst(Comparator.comparing(Instant::parse).reversed())));
      }

   }

   public static List convertMapToEnvVarList(Map envVarMap) {
      List<EnvVar> envVars = new ArrayList();

      for(Map.Entry entry : envVarMap.entrySet()) {
         if (entry.getKey() != null && entry.getValue() != null) {
            envVars.add(((EnvVarBuilder)((EnvVarBuilder)(new EnvVarBuilder()).withName((String)entry.getKey())).withValue((String)entry.getValue())).build());
         }
      }

      return envVars;
   }

   /** @deprecated */
   @Deprecated
   public static boolean isResourceReady(HasMetadata item) {
      return Readiness.getInstance().isReady(item);
   }

   public static Duration getAge(HasMetadata kubernetesResource) {
      Instant instant = Instant.parse(kubernetesResource.getMetadata().getCreationTimestamp());
      return Duration.between(instant, Instant.now()).abs();
   }

   public static Class inferListType(Class type) {
      return loadRelated(type, "List", DefaultKubernetesResourceList.class);
   }

   public static Class inferBuilderType(Class type) {
      return loadRelated(type, "Builder", (Class)null);
   }

   private static Class loadRelated(Class type, String suffix, Class defaultClass) {
      try {
         ClassLoader var7 = Thread.currentThread().getContextClassLoader();
         String var8 = type.getName();
         return var7.loadClass(var8 + suffix);
      } catch (ClassCastException | NullPointerException | ClassNotFoundException var6) {
         try {
            ClassLoader var10000 = type.getClassLoader();
            String var10001 = type.getName();
            return var10000.loadClass(var10001 + suffix);
         } catch (ClassCastException | NullPointerException | ClassNotFoundException var5) {
            return defaultClass;
         }
      }
   }

   public static Secret createDockerRegistrySecret(String dockerServer, String username, String password) {
      Map<String, Object> dockerConfigMap = createDockerRegistryConfigMap(dockerServer, username, password);
      String dockerConfigAsStr = Serialization.asJson(dockerConfigMap);
      return createDockerSecret("container-image-registry-secret", dockerConfigAsStr);
   }

   public static Secret createDockerRegistrySecret(String dockerServer, String username, String password, String secretName) {
      Map<String, Object> dockerConfigMap = createDockerRegistryConfigMap(dockerServer, username, password);
      String dockerConfigAsStr = Serialization.asJson(dockerConfigMap);
      return createDockerSecret(secretName, dockerConfigAsStr);
   }

   public static ConfigMap createConfigMapFromDirOrFiles(String name, Path... dirOrFilePaths) throws IOException {
      ConfigMapBuilder configMapBuilder = new ConfigMapBuilder();
      ((ConfigMapFluent.MetadataNested)configMapBuilder.withNewMetadata().withName(name)).endMetadata();

      for(Path dirOrFilePath : dirOrFilePaths) {
         File file = dirOrFilePath.toFile();
         addEntriesFromDirOrFileToConfigMap(configMapBuilder, file.getName(), dirOrFilePath);
      }

      return configMapBuilder.build();
   }

   private static Map.Entry createConfigMapEntry(String key, Path file) throws IOException {
      byte[] bytes = Files.readAllBytes(file);
      if (isFileWithBinaryContent(file)) {
         String value = Base64.getEncoder().encodeToString(bytes);
         return new AbstractMap.SimpleEntry(key, value);
      } else {
         return new AbstractMap.SimpleEntry(key, new String(bytes));
      }
   }

   private static boolean isFileWithBinaryContent(Path file) throws IOException {
      byte[] bytes = Files.readAllBytes(file);

      try {
         StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap(bytes));
         return false;
      } catch (CharacterCodingException var3) {
         return true;
      }
   }

   private static void addEntriesFromDirectoryToConfigMap(ConfigMapBuilder configMapBuilder, Path path) throws IOException {
      Stream<Path> files = Files.list(path);

      try {
         files.filter((p) -> !Files.isDirectory(p, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})).forEach((file) -> {
            try {
               addEntryToConfigMap(configMapBuilder, createConfigMapEntry(file.getFileName().toString(), file), file);
            } catch (IOException e) {
               throw new IllegalArgumentException(e);
            }
         });
      } catch (Throwable var6) {
         if (files != null) {
            try {
               files.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (files != null) {
         files.close();
      }

   }

   private static void addEntryFromFileToConfigMap(ConfigMapBuilder configMapBuilder, String key, Path file) throws IOException {
      String entryKey = (String)Optional.ofNullable(key).orElse(file.toFile().getName());
      Map.Entry<String, String> configMapEntry = createConfigMapEntry(entryKey, file);
      addEntryToConfigMap(configMapBuilder, configMapEntry, file);
   }

   private static void addEntryToConfigMap(ConfigMapBuilder configMapBuilder, Map.Entry entry, Path file) throws IOException {
      if (isFileWithBinaryContent(file)) {
         configMapBuilder.addToBinaryData((String)entry.getKey(), (String)entry.getValue());
      } else {
         configMapBuilder.addToData((String)entry.getKey(), (String)entry.getValue());
      }

   }

   public static ConfigMapBuilder addEntriesFromDirOrFileToConfigMap(ConfigMapBuilder configMapBuilder, String key, Path dirOrFilePath) throws IOException {
      if (!Files.exists(dirOrFilePath, new LinkOption[0])) {
         throw new IllegalArgumentException("invalid file path provided " + dirOrFilePath);
      } else {
         if (Files.isDirectory(dirOrFilePath, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            addEntriesFromDirectoryToConfigMap(configMapBuilder, dirOrFilePath);
         } else {
            addEntryFromFileToConfigMap(configMapBuilder, key, dirOrFilePath);
         }

         return configMapBuilder;
      }
   }

   private static Map createDockerRegistryConfigMap(String dockerServer, String username, String password) {
      Map<String, Object> dockerConfigMap = new HashMap();
      Map<String, Object> auths = new HashMap();
      Map<String, Object> credentials = new HashMap();
      credentials.put("username", username);
      credentials.put("password", password);
      String usernameAndPasswordAuth = username + ":" + password;
      credentials.put("auth", Base64.getEncoder().encodeToString(usernameAndPasswordAuth.getBytes(StandardCharsets.UTF_8)));
      auths.put(dockerServer, credentials);
      dockerConfigMap.put("auths", auths);
      return dockerConfigMap;
   }

   private static Secret createDockerSecret(String secretName, String dockerConfig) {
      return ((SecretBuilder)((SecretBuilder)((SecretBuilder)((SecretFluent.MetadataNested)(new SecretBuilder()).withNewMetadata().withName(secretName)).endMetadata()).withType("kubernetes.io/dockerconfigjson")).addToData(".dockerconfigjson", Base64.getEncoder().encodeToString(dockerConfig.getBytes(StandardCharsets.UTF_8)))).build();
   }

   static {
      KUBERNETES_KEY_REGEX = Pattern.compile("(" + KUBERNETES_SUBDOMAIN_REGEX.toString() + "/)?[a-z0-9]([-_.a-z0-9]{0,61}[a-z0-9])?");
      INVALID_LABEL_CHARS_PATTERN = Pattern.compile("[^-A-Za-z0-9]+");
   }
}
