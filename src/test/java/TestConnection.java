import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestConnection {
    public static void main(String[] args) {
        // Создание EntityManagerFactory с использованием persistence unit "prod"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("prod");

        // Получение EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Проверка подключения
        try {
            entityManager.getTransaction().begin();
            // Выполнение тестового запроса к базе данных
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            entityManager.getTransaction().commit();
            System.out.println("Подключение к базе данных успешно");
        } catch (Exception e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}