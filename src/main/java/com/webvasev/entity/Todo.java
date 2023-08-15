package com.webvasev.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todo")
public class Todo {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "content")
    @NotEmpty
    private String content;

    @Column(nullable = false, name = "creation_time")
    @NotNull
    private LocalDate creationTime;

    @Column(nullable = false, name = "due_date")
    @NotNull
    @Future
//    @Email
    private LocalDate dueDate;

    @Column(nullable = false, name = "completed")
    @NotNull
    private boolean completed;

    @Column(nullable = false, name = "important")
    @NotNull
    private boolean important;
    @Column(nullable = false, name = "todo_id")
    @NotNull
    private String todoId;


    public Todo() {

    }

    public Todo(String content, LocalDate creationTime, LocalDate dueDate, boolean completed, boolean important) {
        this.content = content;
        this.creationTime = LocalDate.now();
        this.dueDate = dueDate;
        this.completed = completed;
        this.important = important;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

}