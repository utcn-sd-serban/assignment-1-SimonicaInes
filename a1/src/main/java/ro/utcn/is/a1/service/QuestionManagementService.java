package ro.utcn.is.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.exception.QuestionNotFoundException;
import ro.utcn.is.a1.repository.QuestionRepository;
import ro.utcn.is.a1.repository.QuestionRepositoryFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionManagementService {
    private final QuestionRepositoryFactory repositoryFactory; //asta unde e exact implementata in JDBC tau? FACTORY-ul

    @Transactional
    public List<Question> listQuestions() {
        return repositoryFactory.createQuestionRepository().findAll();
    }

    @Transactional
    public Question insert (String title, int authorId, String text, Date date) {
        return repositoryFactory.createQuestionRepository().save(new Question(title,authorId,text,date));
    }

    @Transactional
    public void update(int id, String title,int authorId,String text, Date date) {
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        question.setText(text);
        question.setText(title);
        question.setAuthorId(authorId);
        question.setText(date.toString());
        repository.save(question);
    }

    @Transactional
    public void remove(int id) {
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        repository.remove(question);
    }

    @Transactional
    public List<Question> ListQuestionByUser(User user){
       List<Question> initialList= repositoryFactory.createQuestionRepository().findAll();
       List<Question> finalList = new ArrayList<>();
       for(Question q: initialList){
           if(q.getAuthorId() == user.getId()){
               finalList.add(q);
           }
       }

       return BubbleSortByDate(finalList);
    }

    @Transactional
    public Optional<Question> findById(int id){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        return Optional.ofNullable(question);

    }
    public List<Question> BubbleSortByDate(List<Question> qList) {
        List<Question> resList = qList;
        if(resList.size()>1) {
            for (int i = 0; i < resList.size(); i++) {
                for (int j = 0; j < resList.size()-j; j++) {
                    if (resList.get(j).getDate().before(resList.get(j + 1).getDate())) {
                        Question qTemp = resList.get(j);
                        resList.set(i, resList.get(i+1));
                        resList.set(i+1,qTemp);

                    }
                }

            }
        }
        return resList;
    }


}
