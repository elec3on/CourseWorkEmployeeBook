public class Main {
    public static void main(String[] args) {
        EmployeeBook book = new EmployeeBook();

        // Создаём сотрудников
        Employee e1 = new Employee("Иванов", "Иван", "Иванович", 1, 120.4);
        Employee e2 = new Employee("Петрова", "Анна", "Сергеевна", 2, 300.6);
        Employee e3 = new Employee("Сидоров", "Петр", "Ильич", 3, 89.5);
        Employee e4 = new Employee("Кузнецова", "Елена", "Владимировна", 1, 249.5);
        Employee e5 = new Employee("Смирнов", "Дмитрий", "Олегович", 5, 400.0);
        Employee e6 = new Employee("Соколов", "Сергей", "Петрович", 2, 160.0);
        Employee e7 = new Employee("Васильев", "Василий", "Васильевич", 3, 320.0);
        Employee e8 = new Employee("Михайлов", "Михаил", "Михайлович", 4, 210.0);
        Employee e9 = new Employee("Новиков", "Николай", "Николаевич", 5, 190.0);
        Employee e10 = new Employee("Федоров", "Федор", "Федорович", 1, 360.0);
        Employee e11 = new Employee("Морозов", "Максим", "Максимович", 2, 280.0); // 11-й сотрудник

        // Добавляем 11 раз, выводим результат каждого вызова
        System.out.println("=== Результаты добавления сотрудников ===");
        System.out.println("1. " + book.addEmployee(e1));
        System.out.println("2. " + book.addEmployee(e2));
        System.out.println("3. " + book.addEmployee(e3));
        System.out.println("4. " + book.addEmployee(e4));
        System.out.println("5. " + book.addEmployee(e5));
        System.out.println("6. " + book.addEmployee(e6));
        System.out.println("7. " + book.addEmployee(e7));
        System.out.println("8. " + book.addEmployee(e8));
        System.out.println("9. " + book.addEmployee(e9));
        System.out.println("10. " + book.addEmployee(e10));
        System.out.println("11. " + book.addEmployee(e11)); // ожидается false

        System.out.println("\n=== Список всех сотрудников ===");
        book.printAllEmployees();

        System.out.println("\n=== Средняя зарплата ===");
        double avg = book.calculateAverageSalary();
        System.out.printf("Средняя зарплата (до первого null): %.2f%n", avg);

        System.out.println("\n=== Налоги (PROPORTIONAL) ===");
        book.printTaxes("PROPORTIONAL");

        System.out.println("\n=== Налоги (PROGRESSIVE) ===");
        book.printTaxes("PROGRESSIVE");

        System.out.println("\n=== Индексация зарплат отдела 1 на 10% ===");
        book.indexSalariesByDepartment(1, 0.10);

        System.out.println("\n=== Поиск первого сотрудника отдела 2 с зарплатой > 250 ===");
        book.findFirstEmployeeAboveWage(2, 250);

        System.out.println("\n=== Первые 3 сотрудника с зарплатой < 300 ===");
        book.printFirstEmployeesBelowWage(300, 3);

        System.out.println("\n=== Проверка наличия сотрудника (по зарплате) ===");
        Employee checkEmp = new Employee("Другой", "Человек", "Петрович", 3, 120.0); // зарплата как у Иванова
        boolean exists = book.containsEmployeeByAccounting(checkEmp);
        System.out.println("Есть ли сотрудник с такой же зарплатой (бухгалтерское сравнение)? " + exists);

        System.out.println("\n=== Получение сотрудника по ID ===");
        Employee found = book.getEmployeeById(5);
        if (found != null) {
            found.printShortInfo();
        } else {
            System.out.println("Сотрудник с ID=5 не найден.");
        }
    }
}