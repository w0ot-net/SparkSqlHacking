package io.fabric8.kubernetes.client.extended.run;

import io.fabric8.kubernetes.api.model.Quantity;
import java.util.List;
import java.util.Map;

public class RunConfig {
   private String name;
   private String image;
   private String imagePullPolicy;
   private String command;
   private List args;
   private String restartPolicy;
   private String serviceAccount;
   private Map labels;
   private Map env;
   private Map limits;
   private Map requests;
   private int port;

   public RunConfig(String name, String image, String imagePullPolicy, String command, List args, String restartPolicy, String serviceAccount, Map labels, Map env, Map limits, Map requests, int port) {
      this.name = name;
      this.image = image;
      this.imagePullPolicy = imagePullPolicy;
      this.command = command;
      this.args = args;
      this.restartPolicy = restartPolicy;
      this.serviceAccount = serviceAccount;
      this.labels = labels;
      this.env = env;
      this.limits = limits;
      this.requests = requests;
      this.port = port;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImage() {
      return this.image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getImagePullPolicy() {
      return this.imagePullPolicy;
   }

   public void setImagePullPolicy(String imagePullPolicy) {
      this.imagePullPolicy = imagePullPolicy;
   }

   public String getCommand() {
      return this.command;
   }

   public void setCommand(String command) {
      this.command = command;
   }

   public List getArgs() {
      return this.args;
   }

   public void setArgs(List args) {
      this.args = args;
   }

   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   public void setRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
   }

   public String getServiceAccount() {
      return this.serviceAccount;
   }

   public void setServiceAccount(String serviceAccount) {
      this.serviceAccount = serviceAccount;
   }

   public Map getLabels() {
      return this.labels;
   }

   public void setLabels(Map labels) {
      this.labels = labels;
   }

   public Map getEnv() {
      return this.env;
   }

   public void setEnv(Map env) {
      this.env = env;
   }

   public Map getLimits() {
      return this.limits;
   }

   public void setLimits(Map limits) {
      this.limits = limits;
   }

   public Map getRequests() {
      return this.requests;
   }

   public void setRequests(Map requests) {
      this.requests = requests;
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int port) {
      this.port = port;
   }
}
