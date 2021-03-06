package io.github.ningyu.fastdfs.protocol.storage.request;

import io.github.ningyu.fastdfs.constant.CmdConstants;
import io.github.ningyu.fastdfs.mapper.DynamicFieldType;
import io.github.ningyu.fastdfs.mapper.FastDFSColumn;
import io.github.ningyu.fastdfs.protocol.BaseRequest;
import io.github.ningyu.fastdfs.protocol.ProtocolHead;

import java.nio.charset.Charset;

/**
 * 文件Truncate命令<br/>
 * 使用限制：创建文件时候需要采用[源追加]模式,之后才能Truncate<br/>
 * size使用也有限制<br/>
 * <p>
 * @author ningyu
 * @date 2017年5月17日 下午6:56 <br/>
 */
public class TruncateRequest extends BaseRequest {
    /**
     * 文件路径长度
     */
    @FastDFSColumn(index = 0)
    private long pathSize;
    /**
     * 截取文件长度
     */
    @FastDFSColumn(index = 1)
    private long fileSize;
    /**
     * 文件路径
     */
    @FastDFSColumn(index = 2, dynamicField = DynamicFieldType.allRestByte)
    private String path;

    /**
     * 文件Truncate命令
     *
     * @param path     文件路径
     * @param fileSize 截取文件长度
     */
    public TruncateRequest(String path, long fileSize) {
        super();
        this.fileSize = fileSize;
        this.path = path;
        head = new ProtocolHead(CmdConstants.STORAGE_PROTO_CMD_TRUNCATE_FILE);
    }

    /**
     * 打包参数
     */
    @Override
    public byte[] encodeParam(Charset charset) {
        // 运行时参数在此计算值
        this.pathSize = path.getBytes(charset).length;
        return super.encodeParam(charset);
    }

    public long getPathSize() {
        return pathSize;
    }

    public void setPathSize(long pathSize) {
        this.pathSize = pathSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "TruncateRequest [pathSize=" + pathSize + ", fileSize=" + fileSize + ", path=" + path + "]";
    }
}
