package com.omade.monitor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ShellCommandUtil {

    public static final String OK = "ok";
    public static final String FAILED = "fail";
    private static final Logger LOG = LoggerFactory.getLogger(ShellCommandUtil.class);

    public static String executeCmd(List<String> cmds) {

        Preconditions.checkArgument(cmds != null, "the parameter is null");
        ProcessBuilder pb = new ProcessBuilder(cmds);

        try {
            Process start = pb.start();
            int waitFor = start.waitFor();
            int exitValue = start.exitValue();
            InputStream errorStream = start.getErrorStream();

            LOG.info("finished in process exitValue: " + waitFor + "exitValue:" + exitValue);

            BufferedReader outbufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));

            String line = null;
            while ((line = outbufferedReader.readLine()) != null) {
                LOG.info(line);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));

            while ((line = bufferedReader.readLine()) != null) {
                LOG.info(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ShellCommandUtil.FAILED;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ShellCommandUtil.FAILED;
        }

        return ShellCommandUtil.OK;

    }

    public static String executeCmd(String cmd) {

        Preconditions.checkArgument(cmd != null, "the parameter is null");

        LOG.info("shell cmd is: " + cmd);

        List<String> cmds = Lists.newArrayList();
        cmds.add(cmd);
        ProcessBuilder pb = new ProcessBuilder(cmds);

        int exitValue = -1;
        try {
            Process start = pb.start();
            int waitFor = start.waitFor();
            exitValue = start.exitValue();
            InputStream errorStream = start.getErrorStream();

            LOG.debug("finished exitValue: " + exitValue);
            LOG.debug("finished waitFor: " + waitFor);

            BufferedReader outbufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));

            String line = null;
            while ((line = outbufferedReader.readLine()) != null) {
                LOG.debug(line);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));

            while ((line = bufferedReader.readLine()) != null) {
                LOG.debug(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ShellCommandUtil.FAILED;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ShellCommandUtil.FAILED;

        }

        return exitValue == 0 ? ShellCommandUtil.OK : ShellCommandUtil.FAILED;
    }

    @SuppressWarnings("unused")
    private static void printEnv(ProcessBuilder pb) {
        Map<String, String> env = pb.environment();
        Iterator<String> it = env.keySet().iterator();

        while (it.hasNext()) {
            String sysatt = (String) it.next();
            LOG.info("System Attribute:" + sysatt + "=" + env.get(sysatt));
        }
    }

    public static String execCmd(String cmd) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(cmd), "the commond is null or empty");
        LOG.info("execute shell command from java : " + cmd);

        try {

            Process exec = ((Runtime) Class.forName("java.lang.Runtime").getMethod("getRuntime", new Class[] {})
                    .invoke(null, new Object[] {})).exec(cmd);

            LOG.info("running in process ");

            int waitFor = exec.waitFor();
            int exitValue = exec.exitValue();
            InputStream errorStream = exec.getErrorStream();

            LOG.info("finished in process exitValue: " + waitFor + "exitValue:" + exitValue);

            BufferedReader outbufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

            String line = null;
            while ((line = outbufferedReader.readLine()) != null) {
                // FileUtils.write(ShieldApplication.getConfiguration().getSqoopLogPath(),
                // line);
                LOG.info(line);
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));

            while ((line = bufferedReader.readLine()) != null) {
                LOG.info(line);
                // FileUtils.write(ShieldApplication.getConfiguration().getSqoopLogPath(),
                // line);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ShellCommandUtil.FAILED;
        }

        return ShellCommandUtil.OK;

    }

    public static String executeCmdByScirpt(String command) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(command));

        LOG.info("executeCmdByScirpt command: " + command);
        String file = "/tmp/" + UUID.randomUUID() + ".sh";
        GateFileUtils.write(file, "#!/usr/bin/env bash\n", true);
        GateFileUtils.write(file, command, true);

        String promote = "chmod 777 " + file;
        ShellCommandUtil.executeCmd(Splitter.on(" ").splitToList(promote));
        String executeStatus = ShellCommandUtil.executeCmd(file);
        LOG.info("executeStatus: " + executeStatus);

        LOG.info("delete file: " + file);
        GateFileUtils.delete(file);

        return executeStatus;
    }

}
