package models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement {
    private int id;
    private double amount; //required
    private Timestamp submitted; //required
    private Timestamp resolved;
    private String description;
    private byte[] reciept;
    private File file;
    private int author; //required
    private String authorName;
    private String submittedDate;
    private String resolvedDate;
    private int resolver;
    private String resolverName;
    private int statusId; //required
    private String status;
    private int typeId; //required
    private String type;

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public Reimbursement() {
    }

    public Reimbursement(int id, double amount, Timestamp submitted,
                         int author, int statusId, int typeId) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.author = author;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
                         byte[] reciept, int author, int resolver, int statusId, int typeId) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.reciept = reciept;
        this.author = author;
        this.resolver = resolver;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReciept() {
        return reciept;
    }

    public void setReciept(byte[] reciept) {
        this.reciept = reciept;
    }

    public File getFile() {return file;}

    public void setFile(File file) {this.file = file;}

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getAuthorName() {return authorName;}

    public void setAuthorName(String authorName) {this.authorName = authorName;}

    public String getResolverName() {return resolverName;}

    public void setResolverName(String resolverName) {this.resolverName = resolverName;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", reciept=" + Arrays.toString(reciept) +
                ", author=" + author +
                ", resolver=" + resolver +
                ", statusId=" + statusId +
                ", status='" + status + '\'' +
                ", typeId=" + typeId +
                ", type='" + type + '\'' +
                '}';
    }
}
