package org.Organizel.Organizel.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Task can't be empty")
    private String task;

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
