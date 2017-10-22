package com.houoy.cms.utils;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

public class SftpUtils {
    protected String host;
    protected int port;
    protected String username;
    protected String password;

    /**
     * @param host     ip
     * @param port     端口
     * @param username 账号
     * @param password 密码
     */
    public SftpUtils(String host, int port, String username, String password) {
        set(host, port, username, password);
    }

    public void set(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    Session sshSession = null;

    /**
     * 链接linux
     */
    public ChannelSftp connect() {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            close(null);
            return null;
        }
        return sftp;
    }

    /**
     * linux上传文件
     */
    public void upload(String directory, File file) {
        ChannelSftp sftp = connect();
        if (null != sftp) {
            try {
                Vector content = sftp.ls(directory);
            } catch (SftpException e) {
                try {
                    sftp.mkdir(directory);
                } catch (SftpException e1) {
                    e1.printStackTrace();
                }
            }

            try {
                sftp.cd(directory);
                sftp.put(new FileInputStream(file), file.getName());
            } catch (SftpException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                sftp.disconnect();
                sftp.exit();
                sshSession.disconnect();
            }
        }
    }

    /**
     * linux上传文件
     */
    public void upload(String directory, InputStream inputStream, String fileName) {
        ChannelSftp sftp = connect();
        try {
            Vector content = sftp.ls(directory);
        } catch (SftpException e) {
            try {
                sftp.mkdir(directory+"/");
            } catch (SftpException e1) {
                e1.printStackTrace();
            }
        }

        try {
            if (null != sftp) {
                sftp.cd(directory+"/");
                sftp.put(inputStream, fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sftp.disconnect();
            sftp.exit();
            sshSession.disconnect();
        }
    }

    /**
     * linux下载文件
     */
    public void get(String directory, String downloadFile) {
        ChannelSftp sftp = connect();
        try {
            if (null != sftp) {
                File file = new File(directory);
                String parent = file.getParent();
                sftp.cd(parent);
                File desc = new File(downloadFile);
                FileOutputStream outputStream = new FileOutputStream(desc);
                sftp.get(file.getName(), outputStream);
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(sftp);
        }
    }

    protected void close(ChannelSftp sftp) {
        if (sftp != null) {
            sftp.disconnect();
            sftp.exit();
        }
        if (sshSession != null) {
            sshSession.disconnect();
        }
    }

    public static void main(String[] args) {

        /**
         * @param host ip
         * @param port 端口
         * @param username 账号
         * @param password 密码
         * */
        SftpUtils sftpUtils = new SftpUtils("47.94.6.120", 22, "root", "Andy891014@");
        sftpUtils.upload("/usr/local/nginx/html/image/aa", new File("f://aaa.bat"));

        //sftpUtils.get("/opt/datas/uploads/config.ini", "c://1.ini");
    }
}
