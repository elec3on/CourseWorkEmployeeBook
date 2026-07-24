public class EmployeeBook {
    private final Employee[] employees;

    public EmployeeBook() {
        employees = new Employee[10]; // хранилище на 10 сотрудников
    }

    // Метод добавления сотрудника в первую свободную ячейку
    public boolean addEmployee(Employee emp) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = emp;
                return true;
            }
        }
        return false;
    }

    // Вывод всех сотрудников через for-each, пропуская null
    public void printAllEmployees() {
        for (Employee currentEmployee : employees) {
            if (currentEmployee != null) {
                System.out.println(currentEmployee.toString());
            }
        }
    }

    // Среднее значение зарплат: считаем до первого null
    public double calculateAverageSalary() {
        int count = 0;
        long sum = 0;

        for (Employee currentEmployee : employees) {
            if (currentEmployee == null) {
                break;
            }
            count++;
            sum += currentEmployee.getSalary();
        }

        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }

    // Налоги: switch по схеме PROPORTIONAL / PROGRESSIVE
    public void printTaxes(String scheme) {
        double totalTax = 0;

        for (Employee currentEmployee : employees) {
            if (currentEmployee == null) {
                break;
            }

            double tax = 0;
            int salary = currentEmployee.getSalary();

            switch (scheme) {
                case "PROPORTIONAL":
                    tax = salary * 0.13;
                    break;
                case "PROGRESSIVE":
                    if (salary <= 150) {
                        tax = salary * 0.13;
                    } else if (salary <= 350) {
                        tax = salary * 0.17;
                    } else {
                        tax = salary * 0.21;
                    }
                    break;
                default:
                    System.out.println("Неизвестная схема расчёта налогов: " + scheme);
                    return;
            }

            totalTax += tax;
            System.out.printf("%s: НДФЛ = %.2f%n", currentEmployee.getFullName(), tax);
        }

        System.out.printf("Итого налог со всех: %.2f%n", totalTax);
    }

    // Индексация зарплат отдела: используем continue для пропуска, если зарплата не меняется
    public void indexSalariesByDepartment(int dept, double percent) {
        for (Employee currentEmployee : employees) {
            if (currentEmployee == null) {
                continue; // пропускаем пустые ячейки
            }

            if (currentEmployee.getDepartment() != dept) {
                continue; // пропускаем сотрудников не из нужного отдела
            }

            int currentSalary = currentEmployee.getSalary();
            double newSalaryRaw = currentSalary * (1 + percent);
            int newSalary = (int) Math.round(newSalaryRaw);

            if (currentSalary == newSalary) {
                continue; // зарплата не изменилась — пропускаем этого сотрудника
            }

            currentEmployee.setSalary(newSalary);
            System.out.println("Зарплата проиндексирована: " + currentEmployee.getFullName() + " -> " + newSalary);
        }
    }

    // Поиск первого сотрудника отдела с зарплатой выше wage
    // Используем цикл for и явный break
    public void findFirstEmployeeAboveWage(int dept, int wage) {
        boolean found = false;
        for (int i = 0; i < employees.length; i++) {
            Employee currentEmployee = employees[i];
            if (currentEmployee == null) {
                break;
            }

            if (currentEmployee.getDepartment() == dept && currentEmployee.getSalary() > wage) {
                System.out.print("Найден сотрудник (порядковый номер в списке: " + (i + 1) + "): ");
                currentEmployee.printShortInfo();
                found = true;
                break; // явный выход из цикла после первого совпадения
            }
        }

        if (!found) {
            System.out.println("Сотрудник не найден.");
        }
    }

    // Первые employeeNumber сотрудников с зарплатой ниже wage
    // Используем while и break
    public void printFirstEmployeesBelowWage(int wage, int employeeNumber) {
        int count = 0;
        int index = 0;

        while (index < employees.length) {
            Employee currentEmployee = employees[index];
            if (currentEmployee == null) {
                break;
            }

            if (currentEmployee.getSalary() < wage) {
                currentEmployee.printShortInfo();
                count++;
                if (count >= employeeNumber) {
                    break; // нужное количество найдено
                }
            }
            index++;
        }

        if (count == 0) {
            System.out.println("Подходящих сотрудников не найдено.");
        }
    }

    // Проверка наличия сотрудника по бухгалтерскому equals (только по зарплате)
    public boolean containsEmployeeByAccounting(Employee target) {
        for (Employee currentEmployee : employees) {
            if (currentEmployee == null) {
                break;
            }
            if (currentEmployee.equals(target)) {
                return true;
            }
        }
        return false;
    }

    // Получение сотрудника по ID
    public Employee getEmployeeById(int id) {
        for (Employee currentEmployee : employees) {
            if (currentEmployee == null) {
                break;
            }
            if (currentEmployee.getEmployeeId() == id) {
                return currentEmployee;
            }
        }
        return null;
    }
}