import java.util.*;
import java.util.stream.Collectors;

class Employee{
    int id;
    String name;
    int age;

    Double salary;
    String department;

    Employee(int id,String name,int age,Double salary,String department){
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    //    public void setId(int id){
//        this.id = id;
//    }
//
//    public int getId(){
//        return id;
//    }
//
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
//
//    public void setAge(int age){
//        this.age = age;
//    }
//
//    public int getAge(){
//        return age;
//    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getDepartment(){
        return department;
    }

    public void setSalary(Double salary){
        this.salary = salary;
    }

    public Double getSalary(){
        return salary;
    }
}

public class Stream {

    public static void main(String[] args) {

        List<Employee> e_list = new ArrayList<Employee>();

        e_list.add(new Employee(1,"A",20,20500d,"BUILD"));
        e_list.add(new Employee(2,"B",30,40500d,"QA"));
        e_list.add(new Employee(3,"C",35,35500d,"DEV"));
        e_list.add(new Employee(4,"D",25,45500d,"BUILD"));
        e_list.add(new Employee(5,"E",40,50500d,"DEV"));
        e_list.add(new Employee(6,"F",33,30500d,"QA"));

        List<Employee> e_age_list = e_list.stream().filter(a -> a.age >= 25).collect(Collectors.toList());

//        System.out.println(e_age_list);
        System.out.println("Employees with age greater than 25 are: ");

        e_age_list.forEach(a -> {
            System.out.print(a.name + " ");
        });

        System.out.println();

        Map<String ,List<Employee>> group_emp = e_list.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        //System.out.println(group_emp);
        group_emp.forEach((department,employees) -> {

            //Finding the highest salary employee
            Optional<Employee> highest_salary = employees.stream().reduce((e1,e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2);

            if (highest_salary.isPresent()){
                System.out.println("Highest Salary in " + department + " is for employee " + highest_salary.get().getName() + " with " + highest_salary.get().getSalary());
            }

            else{
                System.out.println("The employee list is empty");
            }

            //Finding the average salary in each department
            List<Double> salaries_dep = employees.stream().map(Employee::getSalary).toList();

            Double average_salary = salaries_dep.stream().reduce((double) 0,(x, y) -> x + y) / employees.size();

            System.out.println("Average of the " + department + " employees: " + average_salary);

        });

        Map<String, Double> high_avg_sal = e_list.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));

        Map.Entry<String, Double> high_avg_dep = high_avg_sal.entrySet().stream().max(Map.Entry.comparingByValue()).get();

        System.out.println("The highest average salaried department is "  + high_avg_dep.getKey() + " with average " + high_avg_dep.getValue());

    }
}
