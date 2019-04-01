package ro.utcn.is.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.is.a1.entity.Question;
import ro.utcn.is.a1.entity.Tag;
import ro.utcn.is.a1.entity.User;
import ro.utcn.is.a1.exception.UserNotFoundException;
import ro.utcn.is.a1.service.QuestionManagementService;
import ro.utcn.is.a1.service.QuestionTagManagementService;
import ro.utcn.is.a1.service.TagManagementService;
import ro.utcn.is.a1.service.UserManagementService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final UserManagementService userManagementService;
    private final QuestionManagementService questionManagementService;
    private final TagManagementService tagManagementService;
    private final QuestionTagManagementService questionTagManagementService;

    @Override
    public void run(String... args) throws Exception {
        print("Welcome to the attendance tracking system.");
        boolean done = false;
        print("LOG IN :");
        print("Username :");
        String usernameInput = scanner.next().trim();
        print("Password :");
        String passwordInput = scanner.next().trim();

           if(checkInputData(usernameInput, passwordInput)) {
               while (!done) {
                   print("Enter a command: ");
                   print("      [users]");
                   print("      [questions]");
                   print("      [tags]");
                   String command = scanner.next().trim();
                   try {
                       done = pickCommandList(command, userManagementService.getUser(usernameInput));
                   } catch (UserNotFoundException userNotFoundException) {
                       print("The user with the given ID was not found!");
                   }
               }
           }
           else{
               print("WRONG PASSWORD!");
           }
        }

    private boolean checkInputData(String usernameInput, String passwordInput){

       User tempUser = userManagementService.getUser(usernameInput);
       if(tempUser.getUsername().equals(usernameInput)){
           if(tempUser.getPassword().equals(passwordInput)){
               return true;
           }
           else{
               return false;
           }
       }
       else{
           return false;
       }
    }


private boolean pickCommandList(String command, User user) throws Exception{

    switch (command) {
        case "users":
            print("Enter a command:");
            print("     [list]");
            print("     [add]");
            print("     [update]");
            print("     [remove]");
            String command1 = scanner.next().trim();
            handleCommandU(command1);
            return false;
        case "questions":
            print("Enter a command:");
            print("     [list]");
            print("     [add]");
            print("     [update]");
            print("     [remove]");
            print("     [search]");
            String command2 = scanner.next().trim();
            handleCommandQ(command2, user);
            return false;
        case "tags":
            print("Enter a command:");
            print("     [list]");
            print("     [add]");
            print("     [update]");
            print("     [remove]");
            print("     [filter questions]");
            String command3 = scanner.next().trim();
            handleCommandT(command3);
            return false;
        default:
            print("Unknown command. Try again.");


            return false;
    }
}
    //-------------- HANDLE COMMANDS
    private boolean handleCommandU(String command) {

        switch (command) {
            case "list":
                handleListU();
                return false;
            case "add":
                handleAddU();
                return false;
            case "update":
                handleUpdateUsername();
                return false;
            case "remove":
                handleRemoveU();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    private boolean handleCommandQ(String command, User user) throws Exception{

        switch (command) {
            case "list":
                handleListQ();
                return false;
            case "add":
                handleAddQ(user);
                return false;
            case "update":
                handleUpdateQ(user);
                return false;
            case "remove":
                handleRemoveQ(user);
                return false;
            case "search":
                qTextSearch();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    private boolean handleCommandT(String command) throws Exception{
        switch (command) {
            case "list":
                handleListT();
                return false;
            case "add":
                handleAddT();
                return false;
            case "update":
                handleUpdateT();
                return false;
            case "remove":
                handleRemoveT();
                return false;
            case "filterq":
                printQuestionsByTag();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    //-------------- HANDLE LISTS
    private void handleListU() {
        userManagementService.listUsers().forEach(s -> print(s.toString()));
    }

    private void handleListQ() {
        questionManagementService.listQuestions().forEach(s-> {
                print(s.toString());
                questionTagManagementService.findByQuestionId(s.getId()).forEach(
                        t->print( tagManagementService.findById(t).toString())
                      );
                }
        );
    }

    private void handleListT() {
        tagManagementService.listTags().forEach(s-> print(s.toString()));
    }


    //-------------- ADDS
    private void handleAddU() {
        print("Username:");
        String username = scanner.next().trim();
        print("Password:");
        String password = scanner.next().trim();
        User user = userManagementService.addUser(username, password);
        print("Created user: " + user + ".");
    }

    private void handleAddQ(User user) throws Exception{
        print("Title:");
        String title = scanner.next().trim();
        int authorId = user.getId();
        print("Text:");
        String text = scanner.next().trim();
        Date dateText = new Date();

        Question question= questionManagementService.insert(title, authorId, text, dateText);
        print("Created question: "+ title + ".");
    }

    private void handleAddT() throws Exception{
        print("Tag Text:");
        String tagText = scanner.next().trim();

        Tag tag = tagManagementService.addTag(tagText);
        print("Created tag: " + tag + ".");
    }

    //-------------- UPDATES
    private void handleUpdateUsername() {
        print("User ID:");
        int id = scanner.nextInt();
        print("Username:");
        String username = scanner.next().trim();
        userManagementService.updateUsername(id, username);
    }

    private void handleUpdateQ(User user) throws Exception{
        print("Question ID:");
        int id = scanner.nextInt();
        print("Title:");
        String title = scanner.next().trim();
        //print("Author:");
        int authorId = user.getId();
        print("Text:");
        String text = scanner.next().trim();
        //print("Date:");
        Date dateText = new Date();

        questionManagementService.update(id, title, authorId, text, dateText);
    }

    private void handleUpdateT() {
        print("Tag ID:");
        int id = scanner.nextInt();
        print("Tag Text:");
        String tagText = scanner.next().trim();
        tagManagementService.updateTag(id, tagText);
    }

    //-------------- REMOVES
    private void handleRemoveU() {
        print("User ID:");
        int id = scanner.nextInt();
        userManagementService.removeUser(id);
    }

    private void handleRemoveQ(User user) {
        print("Question ID:");
        int id = scanner.nextInt();
        List<Question> qList = questionManagementService.ListQuestionByUser(user);
        for(Question q :qList){
            if(q.getId()== id){
                questionManagementService.remove(id);
            }
        }

    }

    private void handleRemoveT() {
        print("Tag ID:");
        int id = scanner.nextInt();
        tagManagementService.removeTag(id);
    }


    //-------------- PRINT
    private void print(String value) {
        System.out.println(value);
    }

    private void printQuestionsByTag(){
        print("filter Tag: ");
        String tagText = scanner.next().trim();
        Tag t= tagManagementService.getTag(tagText);

            questionTagManagementService.findByTagId(t.getId()).forEach(s-> print(questionManagementService.findById(s).toString()));


    }

    private void qTextSearch(){
        print("Search title: ");
        String title = scanner.next().trim();
        List<Question> questions = questionManagementService.listQuestions();

        for(Question q: questions ){
            if(q.getTitle().equals(title)){
                print(q.toString());
            }
        }

    }

}
