package com.webvasev.beans;

import com.webvasev.control.TodoFilter;
import com.webvasev.control.TodoManager;
import com.webvasev.entity.Todo;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TodosBean implements Serializable {

    private List<Todo> todos;

    private Todo todo = new Todo();

    @Inject
    private TodoManager todoManager;

    @PostConstruct
    public void init() {
        this.todos = todoManager.loadAllTodos();
    }

    public void delete(Todo todo) {
        todoManager.delete(todo);
        todos.remove(todo);
    }

    public void findCompleted() {
        TodoFilter filter = Todo::isCompleted;

        this.todos = todoManager.filterTodos(filter);
    }

    public void findImportant() {
        TodoFilter filter = Todo::isImportant;

        this.todos = todoManager.filterTodos(filter);
    }

    public void add() {
        todoManager.addNewTodo(todo);
        this.todos = todoManager.loadAllTodos();
        this.todo = new Todo();

    }

    public void update() {
        todoManager.update(todos);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Обновление успешно"));
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public TodoManager getTodoManager() {
        return todoManager;
    }

    public void setTodoManager(TodoManager todoManager) {
        this.todoManager = todoManager;
    }
}