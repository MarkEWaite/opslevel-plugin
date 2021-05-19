package io.jenkins.plugins;

import org.kohsuke.stapler.DataBoundConstructor;
import hudson.tasks.Notifier;
import hudson.tasks.BuildStepMonitor;
import hudson.Extension;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.model.AbstractProject;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import java.io.IOException;


public class WebHookPublisher extends Notifier {
    public String webHookUrl;
    public Boolean onStart;
    public Boolean onSuccess;
    public Boolean onFailure;
    public Boolean onUnstable;

    @DataBoundConstructor
    public WebHookPublisher(String webHookUrl, boolean onStart, boolean onSuccess, boolean onFailure, boolean onUnstable) {
        super();
        this.webHookUrl = webHookUrl;
        this.onStart = onStart;
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;
        this.onUnstable = onUnstable;
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
            throws InterruptedException, IOException {
        return true;
    }

    @Override
    public WebHookPublisherDescriptor getDescriptor() {
        return (WebHookPublisherDescriptor) super.getDescriptor();
    }

    @Extension
    public static class WebHookPublisherDescriptor extends BuildStepDescriptor<Publisher> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public String getDisplayName() {
            // return "OpsLevel Integration";
            return "Outbound WebHook notification";
        }
    }
}
