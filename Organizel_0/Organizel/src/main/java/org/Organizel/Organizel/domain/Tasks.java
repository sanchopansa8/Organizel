package org.Organizel.Organizel.domain;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String task;

    @Nullable
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;



    public Tasks() {
    }

    public Tasks(String task, Date date,User author) {
        this.task = task;
        this.date = date;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", date=" + date +
                ", author=" + author +
                '}';
    }
}
