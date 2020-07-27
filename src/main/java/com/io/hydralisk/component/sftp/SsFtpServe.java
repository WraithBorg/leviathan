package com.io.hydralisk.component.sftp;

import com.io.hydralisk.component.FfileServer;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * sftp服务
 */
@Component
@PropertySource("classpath:extra-prod.properties")
public class SsFtpServe {
    private static final Logger logger = LoggerFactory.getLogger(SsFtpServe.class);
    @Value("${sftp.countVal}")
    private String countVal;
    @Value("${sftp.sleepTimeVal}")
    private String sleepTimeVal;

    @Resource
    private SsFtpConfig defaultSsftpConfig;
    @Resource
    private FfileServer ffileServer;

    private long count;
    private long sleepTime;
    /**
     * 已经连接次数
     */
    private long count1 = 0;


    /**
     * 连接sftp服务器
     *
     * @return
     */
    public ChannelSftp connect() {
      return   connect(defaultSsftpConfig);
    }
    public ChannelSftp connect(SsFtpConfig sftpConfig) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(sftpConfig.getUsername(), sftpConfig.getHostname(), sftpConfig.getPort());
            Session sshSession = jsch.getSession(sftpConfig.getUsername(), sftpConfig.getHostname(), sftpConfig.getPort());
            logger.info("Session created ... UserName=" + sftpConfig.getUsername() + ";host=" + sftpConfig.getHostname() + ";port=" + sftpConfig.getPort());
            sshSession.setPassword(sftpConfig.getPassword());
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.info("Session connected ...");
            logger.info("Opening Channel ...");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("登录成功");
        } catch (Exception e) {
            try {
                count1 += 1;
                if (count == count1) {
                    throw new RuntimeException(e);
                }
                Thread.sleep(sleepTime);
                logger.info("重新连接....");
                connect(sftpConfig);
            } catch (InterruptedException e1) {
                throw new RuntimeException(e1);
            }
        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param uploadFile 要上传的文件
     * @param sftpConfig
     */
    public void upload(String directory, String uploadFile, SsFtpConfig sftpConfig) {
        ChannelSftp sftp = connect(sftpConfig);
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            try {
                sftp.mkdir(directory);
                sftp.cd(directory);
            } catch (SftpException e1) {
                throw new RuntimeException("ftp创建文件路径失败" + directory);
            }
        }
        File file = new File(uploadFile);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            sftp.put(inputStream, file.getName());
        } catch (Exception e) {
            throw new RuntimeException("sftp异常" + e);
        } finally {
            disConnect(sftp);
            closeStream(inputStream, null);
        }
    }

    /**
     * 上传图片
     */
    public String upload4InputStream4Image(InputStream inputStream,  String fileName) {
        upload4InputStream(inputStream, ffileServer.getImgFolderPath(), fileName, defaultSsftpConfig);
        return  fileName;
    }
    /**
     * 上传文件
     */
    public void upload4InputStream(InputStream inputStream, String directory, String fileName) {
        upload4InputStream(inputStream, directory, fileName, defaultSsftpConfig);
    }
    /**
     * 上传文件
     */
    public void upload4InputStream(InputStream inputStream, String directory, String fileName, SsFtpConfig sftpConfig) {
        ChannelSftp sftp = connect(sftpConfig);
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            try {
                sftp.mkdir(directory);
                sftp.cd(directory);
            } catch (SftpException e1) {
                throw new RuntimeException("ftp创建文件路径失败" + directory);
            }
        }
        try {
            sftp.put(inputStream, fileName);
        } catch (Exception e) {
            throw new RuntimeException("sftp异常" + e);
        } finally {
            disConnect(sftp);
            closeStream(inputStream, null);
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @param sftpConfig
     */
    public void download(String directory, String downloadFile, String saveFile, SsFtpConfig sftpConfig) {
        OutputStream output = null;
        try {
            File localDirFile = new File(saveFile);
            // 判断本地目录是否存在，不存在需要新建各级目录
            if (!localDirFile.exists()) {
                localDirFile.mkdirs();
            }
            if (logger.isInfoEnabled()) {
                logger.info("开始获取远程文件:[{}]---->[{}]", new Object[]{directory, saveFile});
            }
            ChannelSftp sftp = connect(sftpConfig);
            sftp.cd(directory);
            if (logger.isInfoEnabled()) {
                logger.info("打开远程文件:[{}]", new Object[]{directory});
            }
            output = new FileOutputStream(new File(saveFile.concat(File.separator).concat(downloadFile)));
            sftp.get(downloadFile, output);
            if (logger.isInfoEnabled()) {
                logger.info("文件下载成功");
            }
            disConnect(sftp);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("文件下载出现异常，[{}]", e);
            }
            throw new RuntimeException("文件下载出现异常，[{}]", e);
        } finally {
            closeStream(null, output);
        }
    }

    /**
     * 下载远程文件夹下的所有文件
     *
     * @param remoteFilePath
     * @param localDirPath
     * @throws Exception
     */
    public void getFileDir(String remoteFilePath, String localDirPath, SsFtpConfig sftpConfig) throws Exception {
        File localDirFile = new File(localDirPath);
        // 判断本地目录是否存在，不存在需要新建各级目录
        if (!localDirFile.exists()) {
            localDirFile.mkdirs();
        }
        if (logger.isInfoEnabled()) {
            logger.info("sftp文件服务器文件夹[{}],下载到本地目录[{}]", new Object[]{remoteFilePath, localDirFile});
        }
        ChannelSftp channelSftp = connect(sftpConfig);
        Vector<ChannelSftp.LsEntry> lsEntries = channelSftp.ls(remoteFilePath);
        if (logger.isInfoEnabled()) {
            logger.info("远程目录下的文件为[{}]", lsEntries);
        }
        for (ChannelSftp.LsEntry entry : lsEntries) {
            String fileName = entry.getFilename();
            if (checkFileName(fileName)) {
                continue;
            }
            String remoteFileName = getRemoteFilePath(remoteFilePath, fileName);
            channelSftp.get(remoteFileName, localDirPath);
        }
        disConnect(channelSftp);
    }

    /**
     * 关闭流
     *
     * @param outputStream
     */
    private void closeStream(InputStream inputStream, OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkFileName(String fileName) {
        if (".".equals(fileName) || "..".equals(fileName)) {
            return true;
        }
        return false;
    }

    private String getRemoteFilePath(String remoteFilePath, String fileName) {
        if (remoteFilePath.endsWith("/")) {
            return remoteFilePath.concat(fileName);
        } else {
            return remoteFilePath.concat("/").concat(fileName);
        }
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory  要列出的目录
     * @param sftpConfig
     * @return
     * @throws SftpException
     */
    public List<String> listFiles(String directory, SsFtpConfig sftpConfig) throws SftpException {
        ChannelSftp sftp = connect(sftpConfig);
        List fileNameList = new ArrayList();
        try {
            sftp.cd(directory);
        } catch (SftpException e) {
            return fileNameList;
        }
        Vector vector = sftp.ls(directory);
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i) instanceof ChannelSftp.LsEntry) {
                ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) vector.get(i);
                String fileName = lsEntry.getFilename();
                if (".".equals(fileName) || "..".equals(fileName)) {
                    continue;
                }
                fileNameList.add(fileName);
            }
        }
        disConnect(sftp);
        return fileNameList;
    }

    /**
     * 断掉连接
     */
    public void disConnect(ChannelSftp sftp) {
        try {
            sftp.disconnect();
            sftp.getSession().disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SsFtpServe(long count, long sleepTime) {
        this.count = Long.parseLong(countVal);
        this.sleepTime = Long.parseLong(sleepTimeVal);
    }

    public SsFtpServe() {
    }
}
