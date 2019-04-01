package ro.utcn.is.a1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.entity.QuestionTag;
import ro.utcn.is.a1.entity.Tag;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.exception.UserNotFoundException;
import ro.utcn.is.a1.repository.QuestionRepositoryFactory;
import ro.utcn.is.a1.repository.QuestionTagRepositoryFactory;
import ro.utcn.is.a1.repository.TagRepositoryFactory;
import ro.utcn.is.a1.repository.UserRepositoryFactory;
import ro.utcn.is.a1.repository.memory.InMemoryQuestionRepositoryFactory;
import ro.utcn.is.a1.repository.memory.InMemoryQuestionTagRepositoryFactory;
import ro.utcn.is.a1.repository.memory.InMemoryTagRepositoryFactory;
import ro.utcn.is.a1.repository.memory.InMemoryUserRepositoryFactory;
import ro.utcn.is.a1.service.QuestionManagementService;
import ro.utcn.is.a1.service.QuestionTagManagementService;
import ro.utcn.is.a1.service.TagManagementService;
import ro.utcn.is.a1.service.UserManagementService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class A1ApplicationTests {

	private UserRepositoryFactory generateUserFactory(){
		UserRepositoryFactory factory = new InMemoryUserRepositoryFactory();
		factory.createUserRepository().save(new User(1,"Granma", "password1"));
		factory.createUserRepository().save(new User(2, "Grampa", "icantsee"));
		factory.createUserRepository().save(new User(3, "Anna", "chihuahuas"));
		return factory;
	}

	private QuestionRepositoryFactory generateQuestionFactory() throws ParseException {
		Date date= new SimpleDateFormat("dd/MM/yyyy").parse("11/07/2015");
		QuestionRepositoryFactory factory = new InMemoryQuestionRepositoryFactory();
		factory.createQuestionRepository().save(new Question(1, "title", 2, "mwhahah", date));
		factory.createQuestionRepository().save(new Question(2, "cats", 3, "i like cats", date));
		return factory;
	}

	private TagRepositoryFactory generateTagFactory() throws ParseException {
		TagRepositoryFactory factory = new InMemoryTagRepositoryFactory();
		factory.createTagRepository().save(new Tag(1, "socks"));
		factory.createTagRepository().save(new Tag(2, "games"));
		return factory;
	}

	private QuestionTagRepositoryFactory generateQuestionTagFactory(){
		QuestionTagRepositoryFactory factory = new InMemoryQuestionTagRepositoryFactory();
		factory.createQuestionTagRepository().save(new QuestionTag(1,2,1));
		factory.createQuestionTagRepository().save(new QuestionTag(2,2,2));
		return factory;
	}

	@Test
	public void testRemoveUser(){
		UserRepositoryFactory factory =generateUserFactory();
		UserManagementService s = new UserManagementService(factory);

		s.removeUser(3);
		Assert.assertEquals(2, factory.createUserRepository().findAll().size());
		Assert.assertTrue(factory.createUserRepository().findById(1).isPresent());
		Assert.assertTrue(factory.createUserRepository().findById(2).isPresent());


	}

	@Test public void testRemoveQuestion() throws ParseException {
		QuestionRepositoryFactory factory = generateQuestionFactory();
		QuestionManagementService s = new QuestionManagementService(factory);

		s.remove(1);
		Assert.assertEquals(1,factory.createQuestionRepository().findAll().size());
		Assert.assertTrue(factory.createQuestionRepository().findById(2).isPresent());
	}

	@Test public void testAddTag() throws ParseException {
		TagRepositoryFactory factory = generateTagFactory();
		TagManagementService s = new TagManagementService((factory));

		s.addTag("2AM");
		Assert.assertEquals(3, factory.createTagRepository().findAll().size());

	}

	@Test public void testRemoveQuestionTag(){
		QuestionTagRepositoryFactory factory = generateQuestionTagFactory();
		QuestionTagManagementService s = new QuestionTagManagementService((factory));

		s.removeQuestionTag(1);
		Assert.assertEquals(1,factory.createQuestionTagRepository().findAll().size());
		Assert.assertTrue(factory.createQuestionTagRepository().findById(2).isPresent());
	}
	@Test(expected = UserNotFoundException.class)
	public void testUserNotExisting(){
		UserRepositoryFactory factory = generateUserFactory();
		UserManagementService s = new UserManagementService(factory);

		s.removeUser(999);
	}

}
