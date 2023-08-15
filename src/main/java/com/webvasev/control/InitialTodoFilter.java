package com.webvasev.control;

import com.webvasev.entity.Todo;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.UUID;

@Startup
@Singleton
public class InitialTodoFilter {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {

        System.out.println("Storing three initial customers");

        this.entityManager.persist(createTodo("Уроки", LocalDate.now(), LocalDate.of(2023, 8, 20), false, true));
        this.entityManager.persist(createTodo("Покупки", LocalDate.now(), LocalDate.of(2023, 8, 20), true, true));
        this.entityManager.persist(createTodo("Спорт", LocalDate.now(), LocalDate.of(2023, 8, 20), true, true));

    }

    private Todo createTodo(String content, LocalDate creationTime, LocalDate dueDate, boolean completed, boolean important) {
        Todo result = new Todo();
        result.setContent(content);
        result.setCreationTime(creationTime);
        result.setDueDate(dueDate);
        result.setCompleted(completed);
        result.setImportant(important);
        result.setTodoId(UUID.randomUUID().toString().substring(0, 8));
        return result;
    }
}