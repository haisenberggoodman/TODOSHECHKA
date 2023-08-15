    package com.webvasev.control;


    import com.webvasev.entity.Todo;

    import javax.ejb.Stateless;
    import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;
    import javax.transaction.Transactional;
    import java.util.List;
    import java.util.UUID;
    import java.util.stream.Collectors;

    @Stateless
    public class TodoManager {

        @PersistenceContext
        private EntityManager entityManager;

        public List<Todo> loadAllTodos() {
            return this.entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
        }

        public List<Todo> findCompletedTodos() {
            List<Todo> todos = this.entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();

            return todos.stream()
                    .filter(Todo::isCompleted)
                    .collect(Collectors.toList());
        }

        public List<Todo> findImportantTodos() {
            List<Todo> todos = this.entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();

            return todos.stream()
                    .filter(Todo::isImportant)
                    .collect(Collectors.toList());
        }


        public void delete(Todo todo) {
            if (entityManager.contains(todo)) {
                entityManager.remove(todo);
            } else {
                Todo managedTodo = entityManager.find(Todo.class, todo.getId());
                if (managedTodo != null) {
                    entityManager.remove(managedTodo);
                }
            }
        }


        public void addNewTodo(Todo todo) {

            Todo newTodo = new Todo();
            newTodo.setContent(todo.getContent());
            newTodo.setCreationTime(todo.getCreationTime());
            newTodo.setDueDate(todo.getDueDate());
            newTodo.setCompleted(todo.isCompleted());
            newTodo.setImportant(todo.isImportant());
            newTodo.setTodoId(UUID.randomUUID().toString().substring(0, 8));

            this.entityManager.persist(newTodo);
        }

        public void update(List<Todo> todos) {
            todos.forEach(entityManager::merge);
        }
    }