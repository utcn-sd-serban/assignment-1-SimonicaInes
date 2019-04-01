package ro.utcn.is.a1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.utcn.is.a1.service.UserManagementService;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="question")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int authorId;
    @Column (name= "textq")
    private String text;
    @Column (name= "dateq")
    private Date date;


    public Question(String title, int authorId, String text, Date date) {
        this.title = title;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
    }



}
