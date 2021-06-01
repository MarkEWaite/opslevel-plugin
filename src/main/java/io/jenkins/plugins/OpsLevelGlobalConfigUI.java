package io.jenkins.plugins;

import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.model.Run;
import hudson.model.listeners.RunListener;
import hudson.Extension;
import jenkins.model.Jenkins;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

import java.util.logging.Logger;


@Extension
public class OpsLevelGlobalConfigUI extends RunListener<Run<?, ?>> implements Describable<OpsLevelGlobalConfigUI> {

    private static final Logger logger = Logger.getLogger(OpsLevelGlobalConfigUI.class.getName());

    public Descriptor<OpsLevelGlobalConfigUI> getDescriptor() {
        return getDescriptorImpl();
    }

    public DescriptorImpl getDescriptorImpl() {
        return (DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(OpsLevelGlobalConfigUI.class);
    }

    @Extension @Symbol("OpsLevelGlobalNotifier")
    public static final class DescriptorImpl extends Descriptor<OpsLevelGlobalConfigUI> {
        private OpsLevelConfig globalConfig = OpsLevelGlobalConfig.get();

        public String getWebHookUrl() {
            return globalConfig.webHookUrl;
        }

        public String getEnvironment() {
            return globalConfig.environment;
        }

        public String getDescription() {
            return globalConfig.description;
        }

        public String getDeployerId() {
            return globalConfig.deployerId;
        }

        public String getDeployerEmail() {
            return globalConfig.deployerEmail;
        }

        public String getDeployerName() {
            return globalConfig.deployerName;
        }

        @DataBoundSetter
        public void setWebHookUrl(String webHookUrl) {
            globalConfig.webHookUrl = webHookUrl;
        }

        @DataBoundSetter
        public void setEnvironment(String environment) {
            globalConfig.environment = environment;
        }

        @DataBoundSetter
        public void setDescription(String description) {
            globalConfig.description = description;
        }

        @DataBoundSetter
        public void setDeployerId(String deployerId) {
            globalConfig.deployerId = deployerId;
        }

        @DataBoundSetter
        public void setDeployerEmail(String deployerEmail) {
            globalConfig.deployerEmail = deployerEmail;
        }

        @DataBoundSetter
        public void setDeployerName(String deployerName) {
            globalConfig.deployerName = deployerName;
        }

        public DescriptorImpl() {
            try {
                load();
            } catch(NullPointerException e) {
            }
        }

        public OpsLevelConfig generateOpsLevelConfig() {
            load();
            return globalConfig;
        }

        public String getDefaultEnvironment() {
            return "Prod Buddy";
        }

        public String getDisplayName() {
            return "Global OpsLevel Integration";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) {
            req.bindJSON(this, formData);
            save();
            return true;
        }

    }
}
