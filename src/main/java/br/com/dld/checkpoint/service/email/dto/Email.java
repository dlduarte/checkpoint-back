package br.com.dld.checkpoint.service.email.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Duarte
 */
public class Email {

    private Credential credential;
    private List<String> addressee;
    private String title;
    private String content;
    private List<Attachment> attachments;
    private boolean html;

    public void addAddressee(String address) {
        if (this.addressee == null) {
            this.addressee = new ArrayList();
        }

        this.addressee.add(address);
    }

    public void addAttachment(Attachment attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList();
        }

        this.attachments.add(attachment);
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public List<String> getAddressee() {
        return addressee;
    }

    public void setAddressee(List<String> addressee) {
        this.addressee = addressee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }
}
