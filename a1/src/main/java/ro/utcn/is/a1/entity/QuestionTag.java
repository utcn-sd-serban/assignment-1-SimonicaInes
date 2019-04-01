package ro.utcn.is.a1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="questiontags")
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int questionId;
    private int tagId;

    public QuestionTag(int questionId, int tagId) {
        this.questionId = questionId;
        this.tagId = tagId;
    }
}
