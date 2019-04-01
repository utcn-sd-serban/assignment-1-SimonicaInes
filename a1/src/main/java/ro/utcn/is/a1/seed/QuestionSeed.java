package ro.utcn.is.a1.seed;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.repository.QuestionRepository;
import ro.utcn.is.a1.repository.QuestionRepositoryFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {
    private final QuestionRepositoryFactory factory;

    @Override
    public void run(String... args) throws Exception {
        QuestionRepository repository= factory.createQuestionRepository();
        Date date= new SimpleDateFormat("dd/MM/yyyy").parse("11/07/2015");
        if (repository.findAll().isEmpty()) {
            repository.save(new Question(
                    "Blender3D textures",
                    1,
                    "How do I texture a cube?",
                    date));
            repository.save(new Question(
                    "mehh",
                    2,
                    "fd",
                    date));
            repository.save(new Question(
                    " textures",
                    1,
                    "How ",
                    date));
        }
    }
}

