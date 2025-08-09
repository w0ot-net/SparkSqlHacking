package org.apache.ivy.util;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.ivy.Ivy;

public final class CredentialsUtil {
   public static Credentials promptCredentials(Credentials c, File passfile) {
      c = loadPassfile(c, passfile);
      if (c.getUserName() != null && c.getPasswd() != null) {
         return c;
      } else {
         CredentialPanel credentialPanel = new CredentialPanel(c, passfile);
         if (JOptionPane.showOptionDialog((Component)null, credentialPanel, c.getHost() + " credentials", 2, 0, new ImageIcon(Ivy.class.getResource("logo.png")), (Object[])null, 0) == 0) {
            String username = credentialPanel.userNameField.getText();
            String passwd = credentialPanel.passwordField.getText();
            if (credentialPanel.rememberDataCB.isSelected()) {
               Properties props = new EncryptedProperties();
               props.setProperty("username", username);
               props.setProperty("passwd", passwd);

               try {
                  FileOutputStream fos = new FileOutputStream(passfile);
                  Throwable var7 = null;

                  try {
                     props.store(fos, "");
                  } catch (Throwable var17) {
                     var7 = var17;
                     throw var17;
                  } finally {
                     if (fos != null) {
                        if (var7 != null) {
                           try {
                              fos.close();
                           } catch (Throwable var16) {
                              var7.addSuppressed(var16);
                           }
                        } else {
                           fos.close();
                        }
                     }

                  }
               } catch (Exception e) {
                  Message.warn("error occurred while saving password file " + passfile, e);
               }
            }

            c = new Credentials(c.getRealm(), c.getHost(), username, passwd);
         }

         return c;
      }
   }

   public static Credentials loadPassfile(Credentials c, File passfile) {
      if (passfile != null && passfile.exists()) {
         Properties props = new EncryptedProperties();

         try {
            FileInputStream fis = new FileInputStream(passfile);
            Throwable var4 = null;

            Credentials var7;
            try {
               props.load(fis);
               String username = c.getUserName();
               String passwd = c.getPasswd();
               if (username == null) {
                  username = props.getProperty("username");
               }

               if (passwd == null) {
                  passwd = props.getProperty("passwd");
               }

               var7 = new Credentials(c.getRealm(), c.getHost(), username, passwd);
            } catch (Throwable var17) {
               var4 = var17;
               throw var17;
            } finally {
               if (fis != null) {
                  if (var4 != null) {
                     try {
                        fis.close();
                     } catch (Throwable var16) {
                        var4.addSuppressed(var16);
                     }
                  } else {
                     fis.close();
                  }
               }

            }

            return var7;
         } catch (IOException e) {
            Message.warn("error occurred while loading password file " + passfile, e);
         }
      }

      return c;
   }

   private CredentialsUtil() {
   }

   private static final class CredentialPanel extends JPanel {
      private static final int FIELD_LENGTH = 20;
      private JTextField userNameField = new JTextField(20);
      private JTextField passwordField = new JPasswordField(20);
      private JCheckBox rememberDataCB = new JCheckBox("remember my information");

      CredentialPanel(Credentials credentials, File passfile) {
         GridBagLayout layout = new GridBagLayout();
         this.setLayout(layout);
         GridBagConstraints c = new GridBagConstraints();
         c.insets = new Insets(2, 2, 2, 2);
         c.gridx = 1;
         c.gridheight = 1;
         c.gridwidth = 2;
         String prompt = credentials.getRealm() != null ? "Enter username and password for \"" + credentials.getRealm() + "\" at " + credentials.getHost() : "Enter username and password for " + credentials.getHost();
         this.add(new JLabel(prompt), c);
         c.gridy = 1;
         c.gridwidth = 1;
         this.add(new JLabel("username: "), c);
         c.gridx = 2;
         this.add(this.userNameField, c);
         c.gridx = 1;
         ++c.gridy;
         if (credentials.getUserName() != null) {
            this.userNameField.setText(credentials.getUserName());
         }

         if (credentials.getPasswd() == null) {
            this.add(new JLabel("passwd:  "), c);
            c.gridx = 2;
            this.add(this.passwordField, c);
            c.gridx = 1;
            ++c.gridy;
         } else {
            this.passwordField.setText(credentials.getPasswd());
         }

         if (passfile != null) {
            c.gridwidth = 2;
            this.add(this.rememberDataCB, c);
            ++c.gridy;
         }

         c.gridwidth = 2;
         this.add(new JLabel(), c);
      }
   }
}
