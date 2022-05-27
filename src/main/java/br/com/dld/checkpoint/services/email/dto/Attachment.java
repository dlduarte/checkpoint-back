package br.com.dld.checkpoint.services.email.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author David Duarte
 */
public class Attachment {

    private String name;
    private String contentType;
    private String base64;

    public void convertAndSetBase64(File file) throws FileNotFoundException, IOException {
        if (file.exists()) {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);

            this.base64 = Base64.encodeBase64String(bytes);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
