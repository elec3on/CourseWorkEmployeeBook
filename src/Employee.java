public class Employee {
    // Статический счётчик ID
    private static int id = 1;

    private final int employeeId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private int department;
    private int salary;

    public Employee(String lastName, String firstName, String middleName,
                    int department, double salaryRaw) {
        this.employeeId = id;
        id++;

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.department = department;

        // Округление и проверка диапазона
        this.salary = (int) Math.round(salaryRaw);
        if (this.salary < 50 || this.salary > 450) {
            System.out.println("Предупреждение: зарплата " + salaryRaw +
                    " после округления выходит за диапазон [50, 450]. Установлено граничное значение.");
            if (this.salary < 50) {
                this.salary = 50;
            }
            if (this.salary > 450) {
                this.salary = 450;
            }
        }
    }

    // Геттеры
    public int getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    // Сеттеры (только отдел и зарплата)
    public void setDepartment(int department) {
        if (department >= 1 && department <= 5) {
            this.department = department;
        } else {
            System.out.println("Ошибка: отдел должен быть от 1 до 5.");
        }
    }

    public void setSalary(double salaryRaw) {
        int salaryRounded = (int) Math.round(salaryRaw);
        if (salaryRounded < 50 || salaryRounded > 450) {
            System.out.println("Ошибка: зарплата после округления должна быть в диапазоне 50–450.");
        } else {
            this.salary = salaryRounded;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Employee targetEmployee = (Employee) obj;

        // Бухгалтерское сравнение: только по зарплате
        return this.salary == targetEmployee.salary;
    }

    @Override
    public String toString() {
        return "Сотрудник[ID=" + employeeId +
                ", Ф.И.О.=" + lastName + " " + firstName + " " + middleName +
                ", Отдел=" + department +
                ", Зарплата=" + salary + "]";
    }

    public void printShortInfo() {
        System.out.println(lastName + " " + firstName + " — " + salary);
    }
}
